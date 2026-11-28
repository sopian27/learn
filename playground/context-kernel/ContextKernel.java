import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Pure context kernel: no I/O, never calls a model. History is append-only; a block is only an
 * overlay on top of it, so restore is exact by construction.
 */
public final class ContextKernel {

    public record Message(String ref, String role, String text, String toolCallId) {}

    /** {@code from} and {@code to} are inclusive indexes into history. */
    public record Block(String id, int from, int to, String summary, boolean active) {}

    public record State(List<Message> history, List<Block> blocks) {
        public State {
            history = List.copyOf(history);
            blocks = List.copyOf(blocks);
        }

        public static State empty() {
            return new State(List.of(), List.of());
        }
    }

    public static State append(State state, String role, String text, String toolCallId) {
        var history = new ArrayList<>(state.history());
        history.add(new Message("m%05d".formatted(history.size() + 1), role, text, toolCallId));
        return new State(history, state.blocks());
    }

    public static State compress(State state, String fromRef, String toRef, String summary) {
        int from = indexOf(state, fromRef);
        int to = indexOf(state, toRef);
        if (from > to) {
            throw new IllegalArgumentException("reversed range: " + fromRef + ".." + toRef);
        }
        if (summary == null || summary.isBlank()) {
            throw new IllegalArgumentException("summary is required");
        }
        if (state.blocks().stream().anyMatch(b -> b.active() && from <= b.to() && to >= b.from())) {
            throw new IllegalArgumentException("range overlaps an active block");
        }
        requireWholeToolPairs(state.history(), from, to);

        // Restored blocks stay in the list, so size() never hands out an old id again.
        var blocks = new ArrayList<>(state.blocks());
        blocks.add(new Block("b" + (blocks.size() + 1), from, to, summary, true));
        return new State(state.history(), blocks);
    }

    public static State restore(State state, String blockId) {
        if (state.blocks().stream().noneMatch(b -> b.id().equals(blockId))) {
            throw new IllegalArgumentException("unknown block: " + blockId);
        }
        var blocks = state.blocks().stream()
                .map(b -> b.id().equals(blockId)
                        ? new Block(b.id(), b.from(), b.to(), b.summary(), false)
                        : b)
                .toList();
        return new State(state.history(), blocks);
    }

    /** What would be sent to the model this turn. */
    public static List<Message> view(State state) {
        var view = new ArrayList<Message>();
        for (int i = 0; i < state.history().size(); i++) {
            Block block = activeBlockStartingAt(state, i);
            if (block == null) {
                view.add(state.history().get(i));
                continue;
            }
            String header = "[%s %s..%s] ".formatted(block.id(),
                    state.history().get(block.from()).ref(), state.history().get(block.to()).ref());
            view.add(new Message(block.id(), "user", header + block.summary(), null));
            i = block.to();
        }
        return List.copyOf(view);
    }

    private static Block activeBlockStartingAt(State state, int index) {
        return state.blocks().stream()
                .filter(b -> b.active() && b.from() == index)
                .findFirst()
                .orElse(null);
    }

    private static int indexOf(State state, String ref) {
        for (int i = 0; i < state.history().size(); i++) {
            if (state.history().get(i).ref().equals(ref)) {
                return i;
            }
        }
        throw new IllegalArgumentException("unknown ref: " + ref);
    }

    /** A provider API rejects a tool result without its call, so a pair folds together or not at all. */
    private static void requireWholeToolPairs(List<Message> history, int from, int to) {
        Set<String> inside = history.subList(from, to + 1).stream()
                .map(Message::toolCallId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        for (int i = 0; i < history.size(); i++) {
            boolean outside = i < from || i > to;
            String callId = history.get(i).toolCallId();
            if (outside && callId != null && inside.contains(callId)) {
                throw new IllegalArgumentException("range splits tool call/result pair " + callId);
            }
        }
    }
}
