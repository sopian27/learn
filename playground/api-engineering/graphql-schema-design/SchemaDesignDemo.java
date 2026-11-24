import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// Lesson 3.1: schema = kontrak. Client milih field, server nolak field yang nggak ada di schema.
// Simulasi kecil tanpa library GraphQL; schema asli ada di schema.graphqls.
public class SchemaDesignDemo {

    static final Set<String> ORDER_FIELDS = Set.of("id", "status", "total", "items");
    static final Map<String, Object> ORDER = Map.of(
            "id", "o-1", "status", "PAID", "total", 150000, "items", "2 item");

    // REST: server yang nentuin bentuk. Client cuma butuh status, tapi dapat semuanya.
    static Map<String, Object> rest() { return ORDER; }

    // GraphQL: client nyebut field, server balikin persis itu. Field di luar schema = error, bukan null diam-diam.
    static Map<String, Object> query(List<String> fields) {
        for (String f : fields)
            if (!ORDER_FIELDS.contains(f)) throw new IllegalArgumentException("Cannot query field '" + f + "' on type 'Order'");
        return fields.stream().collect(Collectors.toMap(f -> f, ORDER::get));
    }

    public static void main(String[] args) {
        System.out.println("REST     : " + rest());
        System.out.println("GraphQL  : " + query(List.of("id", "status")));
        try {
            query(List.of("id", "customerEmail"));
        } catch (IllegalArgumentException e) {
            System.out.println("Ditolak  : " + e.getMessage());
        }

        if (rest().size() != 4 || query(List.of("status")).size() != 1) throw new AssertionError("over-fetch vs pilih field");
    }
}
