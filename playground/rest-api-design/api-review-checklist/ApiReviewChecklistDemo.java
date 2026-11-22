import java.util.ArrayList;
import java.util.List;

// Lesson 14.2: checklist review API dijalankan ke desain endpoint dalam PR. Severity MUST/SHOULD ala Zalando.
public class ApiReviewChecklistDemo {

    enum Level { MUST, SHOULD }

    record Endpoint(String method, String path, int successStatus, boolean hasIdempotencyKey,
                    boolean problemJsonError, boolean hasExample, boolean paginated, boolean listEndpoint) {}

    record Finding(Level level, String msg) {}

    static List<Finding> review(Endpoint e) {
        List<Finding> f = new ArrayList<>();
        if (!e.path().matches("/v\\d+/[a-z-]+(/\\{[a-zA-Z]+\\}(/[a-z-]+)?)?"))
            f.add(new Finding(Level.MUST, "path harus /v{n}/<resource-jamak-kebab>, tanpa kata kerja"));
        if (e.method().equals("POST") && e.successStatus() == 200)
            f.add(new Finding(Level.MUST, "POST yang bikin resource harus 201 (+Location), bukan 200"));
        if (e.method().equals("POST") && !e.hasIdempotencyKey())
            f.add(new Finding(Level.MUST, "POST butuh Idempotency-Key biar aman di-retry"));
        if (!e.problemJsonError())
            f.add(new Finding(Level.MUST, "error harus RFC 9457 problem+json"));
        if (e.listEndpoint() && !e.paginated())
            f.add(new Finding(Level.MUST, "list endpoint wajib pagination"));
        if (!e.hasExample())
            f.add(new Finding(Level.SHOULD, "spec belum punya contoh request/response nyata"));
        return f;
    }

    public static void main(String[] args) {
        List<Endpoint> pr = List.of(
            new Endpoint("POST", "/v1/orders/create", 200, false, false, false, false, false),
            new Endpoint("GET", "/v1/orders", 200, false, true, true, false, true),
            new Endpoint("POST", "/v1/orders", 201, true, true, true, false, false));
        for (Endpoint e : pr) {
            List<Finding> f = review(e);
            long must = f.stream().filter(x -> x.level() == Level.MUST).count();
            System.out.println("== " + e.method() + " " + e.path() + " -> " + (must == 0 ? "APPROVE" : "REQUEST CHANGES (" + must + " MUST)"));
            f.forEach(x -> System.out.println("   [" + x.level() + "] " + x.msg()));
        }
    }
}
