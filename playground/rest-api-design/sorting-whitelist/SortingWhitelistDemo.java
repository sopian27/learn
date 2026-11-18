import java.util.*;

// Lesson 7.4: sort whitelist + default sort + tie-breaker id.
public class SortingWhitelistDemo {
    record Order(int id, String createdAt, int total, String customerNote) {}

    // nama publik -> properti; customerNote sengaja tidak ada (tanpa index)
    static final Map<String, Comparator<Order>> SORTABLE = Map.of(
            "created", Comparator.comparing(Order::createdAt),
            "total", Comparator.comparingInt(Order::total));

    static List<Order> sort(List<Order> data, String sortParam) {
        Comparator<Order> cmp;
        if (sortParam == null) {
            cmp = SORTABLE.get("created").reversed();               // default aman
        } else {
            String[] p = sortParam.split(",");
            Comparator<Order> base = SORTABLE.get(p[0]);
            if (base == null) throw new IllegalArgumentException("sort tidak diizinkan: " + p[0]);
            cmp = p.length > 1 && p[1].equalsIgnoreCase("desc") ? base.reversed() : base;
        }
        cmp = cmp.thenComparing(Comparator.comparingInt(Order::id).reversed()); // tie-breaker
        return data.stream().sorted(cmp).toList();
    }

    static List<Integer> ids(List<Order> l) { return l.stream().map(Order::id).toList(); }

    public static void main(String[] args) {
        List<Order> data = List.of(
                new Order(1, "2026-01-01", 50, "a"),
                new Order(2, "2026-01-02", 50, "b"),
                new Order(3, "2026-01-02", 10, "c"));

        System.out.println("default           -> " + ids(sort(data, null)));
        System.out.println("total,asc         -> " + ids(sort(data, "total,asc")));
        System.out.println("total,desc        -> " + ids(sort(data, "total,desc")));

        String rejected = null;
        try { sort(data, "customerNote,desc"); }
        catch (IllegalArgumentException e) { rejected = e.getMessage(); }
        System.out.println("ditolak: " + rejected);

        // self-check
        if (!ids(sort(data, null)).equals(List.of(3, 2, 1))) throw new AssertionError("default");
        if (!ids(sort(data, "total,desc")).equals(List.of(2, 1, 3))) throw new AssertionError("tie-breaker");
        if (rejected == null) throw new AssertionError("whitelist");
        System.out.println("OK: default, tie-breaker id, dan whitelist jalan");
    }
}
