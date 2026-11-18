import java.util.*;

// Lesson 7.3: filter dinamis dengan whitelist field. Plain Java, tanpa Spring.
public class DynamicFilteringDemo {

    record Order(long id, String status, String customer, String internalNote) {}

    // whitelist: nama param publik -> predicate. Kolom sensitif (internalNote) sengaja tidak ada.
    static final Map<String, java.util.function.BiPredicate<Order, String>> ALLOWED = Map.of(
        "status", (o, v) -> o.status().equalsIgnoreCase(v),
        "customer", (o, v) -> o.customer().toLowerCase().contains(v.toLowerCase()));

    static List<Order> filter(List<Order> data, Map<String, String> params) {
        for (String key : params.keySet())
            if (!ALLOWED.containsKey(key))
                throw new IllegalArgumentException("filter tidak diizinkan: " + key); // di Spring: 400
        return data.stream()
            .filter(o -> params.entrySet().stream().allMatch(e -> ALLOWED.get(e.getKey()).test(o, e.getValue())))
            .toList();
    }

    public static void main(String[] args) {
        List<Order> data = List.of(
            new Order(1, "PAID", "Isryad", "vip, jangan refund"),
            new Order(2, "NEW", "Akbar", "-"),
            new Order(3, "PAID", "Yuni", "-"));

        var paid = filter(data, Map.of("status", "paid"));
        System.out.println("status=paid -> " + paid.stream().map(Order::id).toList());

        var combo = filter(data, Map.of("status", "paid", "customer", "yun"));
        System.out.println("status=paid&customer=yun -> " + combo.stream().map(Order::id).toList());

        try {
            filter(data, Map.of("internalNote", "vip"));
            throw new AssertionError("harusnya ditolak");
        } catch (IllegalArgumentException e) {
            System.out.println("ditolak: " + e.getMessage());
        }
        if (paid.size() != 2 || combo.size() != 1) throw new AssertionError();
        System.out.println("OK: field di luar whitelist ditolak, filter kombinasi jalan");
    }
}
