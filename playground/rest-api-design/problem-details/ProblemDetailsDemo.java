import java.util.LinkedHashMap;
import java.util.Map;

// Lesson 6.2: bentuk error ad-hoc vs RFC 9457 Problem Details (tanpa framework).
// Di Spring 6+, kelas ProblemDetail membungkus field yang sama persis.
public class ProblemDetailsDemo {

    // Field standar RFC 9457; "extensions" = field tambahan (mis. errorId, errors)
    record Problem(String type, String title, int status, String detail, String instance,
                   Map<String, Object> extensions) {
        String toJson() {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("type", type);
            m.put("title", title);
            m.put("status", status);
            m.put("detail", detail);
            m.put("instance", instance);
            m.putAll(extensions);
            StringBuilder sb = new StringBuilder("{\n");
            m.forEach((k, v) -> sb.append("  \"").append(k).append("\": ")
                    .append(v instanceof Number ? v : "\"" + v + "\"").append(",\n"));
            sb.setLength(sb.length() - 2);
            return sb.append("\n}").toString();
        }
    }

    public static void main(String[] args) {
        // Sebelum: tiap endpoint punya bentuk error sendiri-sendiri
        System.out.println("== Ad-hoc (3 endpoint, 3 bentuk)");
        System.out.println("{\"error\":\"not found\"}");
        System.out.println("{\"message\":\"Stok habis\",\"code\":42}");
        System.out.println("{\"success\":false,\"msg\":\"invalid\"}");

        // Sesudah: satu bentuk, Content-Type: application/problem+json
        System.out.println("\n== Problem Details (application/problem+json)");
        System.out.println(new Problem(
                "https://api.example.com/problems/out-of-stock",
                "Out of Stock", 409,
                "Stok SKU-77 tinggal 2, diminta 5.",
                "/orders/ORD-1",
                Map.of("errorId", "85725268")).toJson());

        // type "about:blank" = tidak ada makna di luar status code; title = frase status HTTP
        System.out.println("\n== Tanpa type khusus");
        System.out.println(new Problem("about:blank", "Not Found", 404,
                "Order ORD-9 tidak ditemukan.", "/orders/ORD-9", Map.of()).toJson());
    }
}
