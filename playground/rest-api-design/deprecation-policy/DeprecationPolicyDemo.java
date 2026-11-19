import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

// Lesson 8.3: v1 ditandai deprecated lewat header, lalu dimatikan (410) setelah tanggal sunset.
public class DeprecationPolicyDemo {

    static final Instant DEPRECATED_AT = Instant.parse("2026-11-19T00:00:00Z");
    static final Instant SUNSET_AT = Instant.parse("2027-05-19T00:00:00Z"); // window 6 bulan

    record Response(int status, Map<String, String> headers, String body) {}

    static Response getOrderV1(Instant now) {
        Map<String, String> h = new LinkedHashMap<>();
        if (!now.isBefore(SUNSET_AT)) {
            // Sesudah sunset: 410 Gone + arah ke versi baru, bukan 404 yang membingungkan.
            h.put("Link", "</v2/orders/7>; rel=\"successor-version\"");
            return new Response(410, h, "{\"title\":\"v1 sudah dimatikan\",\"migrate\":\"/v2/orders\"}");
        }
        if (!now.isBefore(DEPRECATED_AT)) {
            h.put("Deprecation", "@" + DEPRECATED_AT.getEpochSecond());
            h.put("Sunset", DateTimeFormatter.RFC_1123_DATE_TIME.format(SUNSET_AT.atOffset(ZoneOffset.UTC)));
            h.put("Link", "</docs/migrate-v1-to-v2>; rel=\"deprecation\", </v2/orders/7>; rel=\"successor-version\"");
        }
        return new Response(200, h, "{\"id\":7,\"total\":150000}");
    }

    static void show(String label, Instant now) {
        Response r = getOrderV1(now);
        System.out.println("== " + label + " -> " + r.status());
        r.headers().forEach((k, v) -> System.out.println("   " + k + ": " + v));
        System.out.println("   " + r.body());
    }

    public static void main(String[] args) {
        show("sebelum deprecate", Instant.parse("2026-11-01T00:00:00Z"));
        show("masa migrasi", Instant.parse("2026-12-01T00:00:00Z"));
        show("sesudah sunset", Instant.parse("2027-05-19T00:00:00Z"));

        // Cek: masa migrasi wajib punya Deprecation + Sunset; sesudah sunset wajib 410.
        Response mid = getOrderV1(Instant.parse("2026-12-01T00:00:00Z"));
        if (mid.status() != 200 || !mid.headers().containsKey("Deprecation") || !mid.headers().containsKey("Sunset"))
            throw new AssertionError("masa migrasi harus 200 + Deprecation + Sunset");
        if (getOrderV1(SUNSET_AT).status() != 410) throw new AssertionError("sesudah sunset harus 410");
    }
}
