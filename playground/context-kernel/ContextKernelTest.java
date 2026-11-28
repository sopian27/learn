import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Plain-Java tests, no dependencies: javac *.java && java ContextKernelTest */
public class ContextKernelTest {

    public static void main(String[] args) {
        Map<String, Runnable> tests = new LinkedHashMap<>();
        tests.put("append assigns sequential refs", ContextKernelTest::appendAssignsRefs);
        tests.put("compress folds range into one summary", ContextKernelTest::compressFoldsRange);
        tests.put("compress keeps originals and does not mutate input", ContextKernelTest::compressKeepsOriginals);
        tests.put("restore returns the exact original view", ContextKernelTest::restoreIsExact);
        tests.put("restore twice is a no-op", ContextKernelTest::restoreTwice);
        tests.put("block ids are never reused", ContextKernelTest::blockIdsNotReused);
        tests.put("two separate blocks keep order", ContextKernelTest::twoBlocks);
        tests.put("overlapping range is rejected", ContextKernelTest::overlapRejected);
        tests.put("splitting a tool call/result pair is rejected", ContextKernelTest::toolPairNotSplit);
        tests.put("bad input is rejected", ContextKernelTest::badInputRejected);

        int failed = 0;
        for (var test : tests.entrySet()) {
            try {
                test.getValue().run();
                System.out.println("PASS " + test.getKey());
            } catch (Throwable t) {
                failed++;
                System.out.println("FAIL " + test.getKey() + " -> " + t);
            }
        }
        System.out.println((tests.size() - failed) + "/" + tests.size() + " passed");
        if (failed > 0) System.exit(1);
    }

    static void appendAssignsRefs() {
        var state = conversation(2);
        equal(List.of("m00001", "m00002"), refs(ContextKernel.view(state)));
    }

    static void compressFoldsRange() {
        var state = ContextKernel.compress(conversation(5), "m00002", "m00004", "did steps 2-4");
        var view = ContextKernel.view(state);
        equal(List.of("m00001", "b1", "m00005"), refs(view));
        equal("[b1 m00002..m00004] did steps 2-4", view.get(1).text());
    }

    static void compressKeepsOriginals() {
        var before = conversation(3);
        var after = ContextKernel.compress(before, "m00001", "m00002", "s");
        equal(before.history(), after.history());
        equal(3, ContextKernel.view(before).size());
    }

    static void restoreIsExact() {
        var before = conversation(4);
        var folded = ContextKernel.compress(before, "m00002", "m00003", "s");
        var restored = ContextKernel.restore(folded, "b1");
        equal(ContextKernel.view(before), ContextKernel.view(restored));
    }

    static void restoreTwice() {
        var folded = ContextKernel.compress(conversation(3), "m00001", "m00002", "s");
        var once = ContextKernel.restore(folded, "b1");
        equal(once, ContextKernel.restore(once, "b1"));
    }

    static void blockIdsNotReused() {
        var state = ContextKernel.compress(conversation(3), "m00001", "m00002", "first");
        state = ContextKernel.restore(state, "b1");
        state = ContextKernel.compress(state, "m00001", "m00002", "second");
        equal(List.of("b2", "m00003"), refs(ContextKernel.view(state)));
    }

    static void twoBlocks() {
        var state = ContextKernel.compress(conversation(5), "m00004", "m00005", "late");
        state = ContextKernel.compress(state, "m00001", "m00002", "early");
        equal(List.of("b2", "m00003", "b1"), refs(ContextKernel.view(state)));
    }

    static void overlapRejected() {
        var state = ContextKernel.compress(conversation(5), "m00002", "m00003", "s");
        rejected(() -> ContextKernel.compress(state, "m00003", "m00004", "s"));
    }

    static void toolPairNotSplit() {
        var state = ContextKernel.append(ContextKernel.State.empty(), "user", "read file", null);
        state = ContextKernel.append(state, "assistant", "call read", "call-1");
        state = ContextKernel.append(state, "tool", "file body", "call-1");
        var full = state;
        rejected(() -> ContextKernel.compress(full, "m00001", "m00002", "s"));
        rejected(() -> ContextKernel.compress(full, "m00003", "m00003", "s"));
        equal(List.of("m00001", "b1"),
                refs(ContextKernel.view(ContextKernel.compress(full, "m00002", "m00003", "s"))));
    }

    static void badInputRejected() {
        var state = conversation(3);
        rejected(() -> ContextKernel.compress(state, "m00009", "m00010", "s"));
        rejected(() -> ContextKernel.compress(state, "m00002", "m00001", "s"));
        rejected(() -> ContextKernel.compress(state, "m00001", "m00002", "  "));
        rejected(() -> ContextKernel.compress(state, "m00001", "m00002", null));
        rejected(() -> ContextKernel.restore(state, "b1"));
    }

    // --- helpers ---

    static ContextKernel.State conversation(int size) {
        var state = ContextKernel.State.empty();
        for (int i = 1; i <= size; i++) {
            state = ContextKernel.append(state, i % 2 == 1 ? "user" : "assistant", "text " + i, null);
        }
        return state;
    }

    static List<String> refs(List<ContextKernel.Message> view) {
        List<String> refs = new ArrayList<>();
        view.forEach(message -> refs.add(message.ref()));
        return refs;
    }

    static void equal(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("expected " + expected + " but was " + actual);
        }
    }

    static void rejected(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("expected IllegalArgumentException");
    }
}
