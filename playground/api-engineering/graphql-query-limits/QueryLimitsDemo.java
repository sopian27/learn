import java.util.List;

// Lesson 3.4: depth limit + complexity limit. Simulasi tanpa library:
// query = pohon field; list field dikali ukuran halaman (first), scalar = 1.
public class QueryLimitsDemo {

    static final int MAX_DEPTH = 6;
    static final int MAX_COMPLEXITY = 1000;

    // multiplier > 1 berarti field list dengan `first: multiplier`.
    record Field(String name, int multiplier, List<Field> children) {
        static Field leaf(String n) { return new Field(n, 1, List.of()); }
        static Field list(String n, int first, Field... c) { return new Field(n, first, List.of(c)); }
    }

    static int depth(Field f) {
        return 1 + f.children().stream().mapToInt(QueryLimitsDemo::depth).max().orElse(0);
    }

    // cost(f) = multiplier * (1 + sum cost(children)) -> list bersarang saling mengalikan.
    static int cost(Field f) {
        return f.multiplier() * (1 + f.children().stream().mapToInt(QueryLimitsDemo::cost).sum());
    }

    static String check(Field q) {
        int d = depth(q), c = cost(q);
        String verdict = d > MAX_DEPTH ? "DITOLAK (depth)" : c > MAX_COMPLEXITY ? "DITOLAK (complexity)" : "OK";
        return String.format("depth=%d cost=%d -> %s", d, c, verdict);
    }

    public static void main(String[] args) {
        // { orders(first:20) { id items(first:10) { name } } }
        Field normal = Field.list("orders", 20, Field.leaf("id"), Field.list("items", 10, Field.leaf("name")));

        // dangkal tapi mahal: { orders(first:100) { items(first:100) { name } } }
        Field wide = Field.list("orders", 100, Field.list("items", 100, Field.leaf("name")));

        // dalam: order -> customer -> orders -> customer -> ... (siklus di schema)
        Field deep = Field.leaf("id");
        for (int i = 0; i < 4; i++) deep = Field.list("orders", 1, Field.list("customer", 1, deep));

        System.out.println("normal : " + check(normal));
        System.out.println("lebar  : " + check(wide));
        System.out.println("dalam  : " + check(deep));

        if (!check(normal).endsWith("OK")) throw new AssertionError("query normal harus lolos");
        if (!check(wide).contains("complexity")) throw new AssertionError("query lebar harus kena complexity");
        if (!check(deep).contains("depth")) throw new AssertionError("query dalam harus kena depth");
    }
}
