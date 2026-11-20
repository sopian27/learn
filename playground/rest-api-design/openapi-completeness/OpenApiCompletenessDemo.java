import java.util.*;

// Demo Lesson 10.4: "linter" kelengkapan dokumentasi ala standards/OPENAPI_STANDARDS.md.
// Spec disederhanakan jadi Map; di dunia nyata ini Spectral/Redocly lint di CI terhadap openapi.yaml.
public class OpenApiCompletenessDemo {

    record Endpoint(String route, Set<String> responses, boolean hasRequestExample,
                    boolean hasErrorExample, boolean hasSecurity, boolean hasRateLimitHeaders) {}

    static final List<Endpoint> SPEC = List.of(
        // Versi "formalitas": cuma happy path.
        new Endpoint("GET /v1/orders/{id}", Set.of("200"), false, false, false, false),
        // Versi lengkap: error, contoh, auth, rate limit.
        new Endpoint("POST /v1/orders", Set.of("201", "400", "401", "403", "409", "422", "429"),
            true, true, true, true)
    );

    public static void main(String[] args) {
        int failed = 0;
        for (Endpoint e : SPEC) {
            List<String> gaps = new ArrayList<>();
            if (e.responses().stream().noneMatch(c -> c.startsWith("4"))) gaps.add("tidak ada response 4xx");
            if (!e.responses().contains("429")) gaps.add("429 + header rate limit tidak didokumentasikan");
            if (!e.hasRateLimitHeaders()) gaps.add("header X-RateLimit-* tidak ada");
            if (!e.hasRequestExample()) gaps.add("tidak ada contoh request/response sukses");
            if (!e.hasErrorExample()) gaps.add("tidak ada contoh error (problem+json)");
            if (!e.hasSecurity()) gaps.add("security scheme tidak ditentukan");

            if (gaps.isEmpty()) {
                System.out.println("OK   " + e.route());
            } else {
                failed++;
                System.out.println("FAIL " + e.route() + " | " + String.join("; ", gaps));
            }
        }
        System.out.println(failed == 0
            ? "Lint lolos: dokumentasi lengkap."
            : "Lint GAGAL: " + failed + " endpoint belum lengkap. Lengkapi spec sebelum merge.");
    }
}
