import java.time.Instant;
import java.util.*;

/** Demo 6.4: kontrak error standar (API_STANDARDS.md) dipetakan ke Problem Details + extension members. */
public class ErrorContractDemo {

    record FieldError(String field, String message) {}

    record ErrorBody(int status, String type, String title, String detail, String instance,
                     String code, Instant timestamp, String traceId, List<FieldError> details) {
        String json() {
            StringBuilder sb = new StringBuilder("{\n");
            sb.append("  \"type\": \"").append(type).append("\",\n");
            sb.append("  \"title\": \"").append(title).append("\",\n");
            sb.append("  \"status\": ").append(status).append(",\n");
            sb.append("  \"detail\": \"").append(detail).append("\",\n");
            sb.append("  \"instance\": \"").append(instance).append("\",\n");
            sb.append("  \"code\": \"").append(code).append("\",\n");
            sb.append("  \"timestamp\": \"").append(timestamp).append("\",\n");
            sb.append("  \"traceId\": \"").append(traceId).append("\"");
            if (!details.isEmpty()) {
                sb.append(",\n  \"details\": [");
                for (int i = 0; i < details.size(); i++) {
                    FieldError d = details.get(i);
                    sb.append(i == 0 ? "" : ", ")
                      .append("{\"field\":\"").append(d.field()).append("\",\"message\":\"").append(d.message()).append("\"}");
                }
                sb.append("]");
            }
            return sb.append("\n}").toString();
        }
    }

    static ErrorBody build(int status, String code, String title, String detail,
                           String path, String traceId, List<FieldError> details) {
        return new ErrorBody(status, "https://api.example.com/problems/" + code.toLowerCase().replace('_', '-'),
                title, detail, path, code, Instant.parse("2026-11-18T10:15:30Z"), traceId, details);
    }

    public static void main(String[] args) {
        // Tiga jenis error, satu bentuk. Kontrak sama; hanya isi yang beda.
        List<ErrorBody> all = List.of(
            build(404, "ORDER_NOT_FOUND", "Order Not Found", "Order ORD-9 tidak ditemukan.",
                  "/orders/ORD-9", "a1b2c3", List.of()),
            build(400, "VALIDATION_FAILED", "Validation Failed", "Ada 2 field yang tidak valid.",
                  "/orders", "d4e5f6", List.of(new FieldError("quantity", "harus lebih dari 0"),
                                               new FieldError("sku", "tidak boleh kosong"))),
            build(500, "INTERNAL_ERROR", "Internal Error", "Terjadi kesalahan di server. Sebut traceId ke support.",
                  "/orders/ORD-1/cancel", "g7h8i9", List.of()));

        all.forEach(e -> System.out.println(e.json() + "\n"));

        // Self-check kontrak: field wajib ada di SEMUA error, dan details hanya kalau relevan.
        for (ErrorBody e : all) {
            String j = e.json();
            for (String f : List.of("\"code\"", "\"timestamp\"", "\"traceId\"", "\"instance\"", "\"status\": " + e.status()))
                if (!j.contains(f)) throw new AssertionError(e.code() + " kehilangan " + f);
        }
        System.out.println("Kontrak OK: semua error punya code, timestamp, traceId, instance, status.");
    }
}
