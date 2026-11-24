import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Lesson 3.2: resolver = fungsi tipis per field yang nyambung ke service lama.
// Simulasi tanpa Spring: Map "Type.field" -> resolver, mirip @QueryMapping / @SchemaMapping.
public class ResolverDemo {

    static final List<String> CALLS = new ArrayList<>();

    // Service lama (sudah ada sebelum GraphQL). Resolver cuma memanggilnya, nggak menduplikasi logika.
    static class OrderService {
        Map<String, Object> findById(String id) { CALLS.add("OrderService.findById"); return Map.of("id", id, "status", "PAID"); }
        List<String> findItems(String orderId) { CALLS.add("OrderService.findItems"); return List.of("kopi", "roti"); }
    }

    static final OrderService SERVICE = new OrderService();

    // Satu resolver per field yang butuh data. Field scalar (id, status) dibaca dari parent, nggak butuh resolver.
    static final Map<String, Function<Map<String, Object>, Object>> RESOLVERS = Map.of(
            "Query.order", args -> SERVICE.findById((String) args.get("id")),
            "Order.items", parent -> SERVICE.findItems((String) parent.get("id")));

    // Executor mini: jalanin resolver hanya untuk field yang diminta client.
    static Map<String, Object> order(String id, List<String> fields) {
        Map<String, Object> parent = (Map<String, Object>) RESOLVERS.get("Query.order").apply(Map.of("id", id));
        Map<String, Object> result = new java.util.LinkedHashMap<>();
        for (String f : fields)
            result.put(f, RESOLVERS.containsKey("Order." + f) ? RESOLVERS.get("Order." + f).apply(parent) : parent.get(f));
        return result;
    }

    public static void main(String[] args) {
        System.out.println("{ order { id status } }       -> " + order("o-1", List.of("id", "status")));
        System.out.println("  resolver terpanggil         : " + CALLS);
        boolean itemsSkipped = !CALLS.contains("OrderService.findItems");

        CALLS.clear();
        System.out.println("{ order { id items } }        -> " + order("o-1", List.of("id", "items")));
        System.out.println("  resolver terpanggil         : " + CALLS);

        if (!itemsSkipped || !CALLS.contains("OrderService.findItems")) throw new AssertionError("resolver harus lazy per field");
    }
}
