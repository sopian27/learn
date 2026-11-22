import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Lesson 14.1: 3 tim, 3 gaya API. Konsumen harus nulis 3 adapter. Linter sederhana nunjukin bedanya.
public class ApiAsProductDemo {

    record Endpoint(String team, String path, String errorShape, String pageStyle, String idField) {}

    static final List<Endpoint> APIS = List.of(
        new Endpoint("orders",   "/v1/orders",   "problem+json", "cursor",      "id"),
        new Endpoint("payments", "/payments/get", "{error:\"msg\"}", "page+size", "paymentId"),
        new Endpoint("catalog",  "/api/Products", "{code:1001}",   "offset",      "ID"));

    // Konvensi tim: path /v{n}/<resource-jamak>, error problem+json, pagination cursor, id field "id".
    static List<String> lint(Endpoint e) {
        List<String> v = new java.util.ArrayList<>();
        if (!e.path().matches("/v\\d+/[a-z-]+s")) v.add("path bukan /v{n}/<resource-jamak-lowercase>");
        if (!e.errorShape().equals("problem+json")) v.add("error bukan problem+json");
        if (!e.pageStyle().equals("cursor")) v.add("pagination bukan cursor");
        if (!e.idField().equals("id")) v.add("id field bukan 'id'");
        return v;
    }

    public static void main(String[] args) {
        Map<String, Integer> adapters = new LinkedHashMap<>();
        for (Endpoint e : APIS) {
            List<String> v = lint(e);
            System.out.println("== " + e.team() + " " + e.path() + " -> " + (v.isEmpty() ? "OK" : v.size() + " pelanggaran"));
            v.forEach(x -> System.out.println("   - " + x));
            adapters.put(e.team(), v.size());
        }
        // Konsumen (mobile app) butuh 1 adapter per gaya unik. Makin beda, makin banyak kode glue.
        long unik = APIS.stream().map(e -> e.errorShape() + e.pageStyle() + e.idField()).distinct().count();
        System.out.println("\nKonsumen harus nulis " + unik + " adapter untuk " + APIS.size() + " API. Target: 1.");
    }
}
