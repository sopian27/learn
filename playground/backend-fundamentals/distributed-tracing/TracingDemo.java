import java.util.*;

/**
 * Lesson 13.3: Distributed tracing dasar (trace ID + span ID + parent span).
 * Simulasi 3 service dalam satu proses. "Header" dikirim lewat Map, mirip header W3C traceparent.
 * Run: java TracingDemo.java
 */
public class TracingDemo {

    record Span(String traceId, String spanId, String parentId, String service, String name, long ms) {}

    static final List<Span> collected = Collections.synchronizedList(new ArrayList<>());
    static int counter = 0;

    static String newId() { return Integer.toHexString(0xA000 + (++counter)); }

    /** Satu span: ambil traceId dari header (atau bikin baru), catat durasi, teruskan spanId sebagai parent. */
    static void span(Map<String, String> headers, String service, String name, java.util.function.Consumer<Map<String, String>> body) {
        String traceId = headers.computeIfAbsent("trace-id", k -> "T" + newId());
        String parent = headers.get("parent-span-id");
        String spanId = newId();
        long start = System.nanoTime();
        try {
            Map<String, String> downstream = new HashMap<>(headers);
            downstream.put("parent-span-id", spanId); // yang dipanggil service ini akan jadi anak span ini
            body.accept(downstream);
        } finally {
            collected.add(new Span(traceId, spanId, parent, service, name, (System.nanoTime() - start) / 1_000_000));
        }
    }

    static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); } }

    public static void main(String[] args) {
        Map<String, String> incoming = new HashMap<>(); // request dari klien, belum punya trace-id

        span(incoming, "gateway", "POST /checkout", h1 -> {
            sleep(5);
            span(h1, "order-service", "createOrder", h2 -> {
                sleep(10);
                span(h2, "inventory-service", "reserveStock", h3 -> sleep(15));
                span(h2, "payment-service", "charge", h3 -> sleep(800)); // biang lambat
            });
        });

        System.out.println("== Tanpa tracing: tiap service cuma tahu durasinya sendiri ==");
        collected.stream().sorted(Comparator.comparing(Span::service))
                .forEach(s -> System.out.printf("%-18s %-14s %4d ms%n", s.service(), s.name(), s.ms()));

        System.out.println("\n== Dengan tracing: satu trace, pohon span ==");
        String traceId = collected.get(0).traceId();
        System.out.println("trace-id = " + traceId);
        print(null, 0);

        Span slowest = collected.stream().filter(s -> collected.stream().noneMatch(c -> s.spanId().equals(c.parentId())))
                .max(Comparator.comparingLong(Span::ms)).get();
        System.out.println("\nSpan daun paling lambat: " + slowest.service() + " / " + slowest.name() + " (" + slowest.ms() + " ms)");
    }

    static void print(String parentId, int depth) {
        collected.stream().filter(s -> Objects.equals(s.parentId(), parentId))
                .forEach(s -> {
                    System.out.printf("%s%s [%s] %s  span=%s %d ms%n", "  ".repeat(depth), depth == 0 ? "" : "└ ", s.service(), s.name(), s.spanId(), s.ms());
                    print(s.spanId(), depth + 1);
                });
    }
}
