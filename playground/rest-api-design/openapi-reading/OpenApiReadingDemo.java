import java.util.List;
import java.util.Map;

// Demo Lesson 10.1: spec OpenAPI = tree biasa (paths + components). Reader ini jalan di struktur itu
// supaya kelihatan: baca paths dulu, ikuti $ref ke components, dan $ref yang putus ketahuan.
// Bukan parser YAML: di kerjaan pakai swagger-parser atau Swagger Editor.
public class OpenApiReadingDemo {

    @SuppressWarnings("unchecked")
    static Map<String, Object> m(Object o) { return (Map<String, Object>) o; }

    static final Map<String, Object> SPEC = Map.of(
        "paths", Map.of(
            "/v1/orders", Map.of(
                "get", Map.of("summary", "List orders", "responses", Map.of(
                    "200", Map.of("$ref", "#/components/schemas/OrderPage"))),
                "post", Map.of("summary", "Create order", "responses", Map.of(
                    "201", Map.of("$ref", "#/components/schemas/Order"),
                    "422", Map.of("$ref", "#/components/schemas/Problem")))),
            "/v1/orders/{id}", Map.of(
                "get", Map.of("summary", "Get order", "responses", Map.of(
                    "200", Map.of("$ref", "#/components/schemas/Order"),
                    "404", Map.of("$ref", "#/components/schemas/ProblemDetail"))))), // typo: tidak ada di components
        "components", Map.of("schemas", Map.of(
            "Order", Map.of("required", List.of("id", "status"),
                "properties", Map.of("id", "string", "status", "string", "total", "number")),
            "OrderPage", Map.of("properties", Map.of("items", "array<Order>", "nextCursor", "string")),
            "Problem", Map.of("properties", Map.of("type", "string", "title", "string", "status", "integer")))));

    static Map<String, Object> resolve(String ref) {
        Object node = SPEC;
        for (String part : ref.substring(2).split("/")) {
            node = node == null ? null : m(node).get(part);
        }
        return node == null ? null : m(node);
    }

    public static void main(String[] args) {
        m(SPEC.get("paths")).forEach((path, ops) -> m(ops).forEach((method, op) -> {
            System.out.println(method.toUpperCase() + " " + path + "  -> " + m(op).get("summary"));
            m(m(op).get("responses")).forEach((code, resp) -> {
                String ref = (String) m(resp).get("$ref");
                Map<String, Object> schema = resolve(ref);
                String name = ref.substring(ref.lastIndexOf('/') + 1);
                System.out.println("    " + code + " : " + name + (schema == null
                    ? "   <-- $ref PUTUS, schema tidak ada di components"
                    : " " + m(schema.get("properties")).keySet()));
            });
        }));
    }
}
