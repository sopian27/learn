import java.util.List;
import java.util.Map;

// Lesson 14.3: governance = lint otomatis + lifecycle yang dijaga + gerbang approve breaking change.
public class ApiGovernanceDemo {

    enum Stage { DESIGN, REVIEW, PUBLISHED, DEPRECATED, SUNSET }

    // transisi yang boleh. Lompat (mis. PUBLISHED -> SUNSET) ditolak.
    static final Map<Stage, List<Stage>> ALLOWED = Map.of(
        Stage.DESIGN, List.of(Stage.REVIEW),
        Stage.REVIEW, List.of(Stage.DESIGN, Stage.PUBLISHED),
        Stage.PUBLISHED, List.of(Stage.DEPRECATED),
        Stage.DEPRECATED, List.of(Stage.SUNSET),
        Stage.SUNSET, List.of());

    record Spec(String path, boolean hasProblemJson, boolean hasOwner, boolean breakingChange, boolean councilApproved) {}

    static List<String> lint(Spec s) {
        var out = new java.util.ArrayList<String>();
        if (!s.path().matches("/v\\d+/[a-z-]+(/\\{[a-zA-Z]+\\}(/[a-z-]+)?)?")) out.add("path melanggar konvensi");
        if (!s.hasProblemJson()) out.add("error bukan problem+json");
        if (!s.hasOwner()) out.add("x-owner kosong, siapa yang dihubungi?");
        if (s.breakingChange() && !s.councilApproved()) out.add("breaking change tanpa approval owner API");
        return out;
    }

    static Stage move(Stage from, Stage to) {
        if (!ALLOWED.get(from).contains(to)) {
            System.out.println("   TOLAK " + from + " -> " + to);
            return from;
        }
        System.out.println("   OK    " + from + " -> " + to);
        return to;
    }

    public static void main(String[] args) {
        System.out.println("== CI lint ==");
        List<Spec> specs = List.of(
            new Spec("/v1/orders", true, true, false, false),
            new Spec("/payments/get", false, false, false, false),
            new Spec("/v2/orders", true, true, true, false));
        for (Spec s : specs) {
            var v = lint(s);
            System.out.println(s.path() + " -> " + (v.isEmpty() ? "PASS" : "FAIL " + v));
        }
        System.out.println("== Lifecycle /v1/orders ==");
        Stage st = Stage.DESIGN;
        st = move(st, Stage.REVIEW);
        st = move(st, Stage.PUBLISHED);
        st = move(st, Stage.SUNSET);     // lompat, ditolak
        st = move(st, Stage.DEPRECATED);
        st = move(st, Stage.SUNSET);
    }
}
