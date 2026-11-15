import java.util.*;
import java.util.stream.*;

/**
 * Lesson 13.5: debug insiden dengan urutan metric -> trace -> log.
 * Data buatan (bukan dari sistem beneran). Run: java IncidentDebugDemo.java
 */
public class IncidentDebugDemo {

    record Metric(String endpoint, double errorRate, int p99Ms) {}
    record Span(String traceId, String service, int durationMs, boolean error) {}
    record Log(String traceId, String service, String level, String msg) {}

    public static void main(String[] args) {
        // Sinyal yang tersedia saat alert "CheckoutHighErrorRate" bunyi
        List<Metric> metrics = List.of(
            new Metric("/checkout", 0.12, 5200),
            new Metric("/products", 0.004, 90),
            new Metric("/login", 0.003, 120));

        List<Span> spans = List.of(
            new Span("t1", "gateway", 5210, true), new Span("t1", "order-service", 5200, true),
            new Span("t1", "payment-service", 5150, true), new Span("t1", "inventory-service", 14, false),
            new Span("t2", "gateway", 5190, true), new Span("t2", "order-service", 5180, true),
            new Span("t2", "payment-service", 5130, true), new Span("t2", "inventory-service", 12, false),
            new Span("t3", "gateway", 160, false), new Span("t3", "order-service", 150, false),
            new Span("t3", "payment-service", 110, false));

        List<Log> logs = List.of(
            new Log("t1", "payment-service", "WARN", "waiting for db connection, pool=10/10 active, waiting=34"),
            new Log("t1", "payment-service", "ERROR", "HikariPool timeout after 5000ms"),
            new Log("t2", "payment-service", "ERROR", "HikariPool timeout after 5000ms"),
            new Log("t3", "payment-service", "INFO", "charge ok"));

        System.out.println("== LANGKAH 1 METRIC: ada masalah apa, seberapa besar, di mana? ==");
        metrics.stream().filter(m -> m.errorRate() > 0.05)
            .forEach(m -> System.out.printf("%s error=%.1f%% p99=%dms  <- cuma ini yang rusak%n", m.endpoint(), m.errorRate() * 100, m.p99Ms()));

        System.out.println("\n== LANGKAH 2 TRACE: ambil trace yang error, service mana yang makan waktu? ==");
        Set<String> badTraces = spans.stream().filter(Span::error).map(Span::traceId).collect(Collectors.toCollection(TreeSet::new));
        Map<String, Integer> slowest = new TreeMap<>();
        for (String t : badTraces) {
            Span top = spans.stream().filter(s -> s.traceId().equals(t) && !s.service().equals("gateway") && !s.service().equals("order-service"))
                .max(Comparator.comparingInt(Span::durationMs)).orElseThrow();
            slowest.merge(top.service(), 1, Integer::sum);
            System.out.printf("trace %s: terlama di %s (%dms)%n", t, top.service(), top.durationMs());
        }
        String suspect = Collections.max(slowest.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Tersangka: " + suspect);

        System.out.println("\n== LANGKAH 3 LOG: filter pakai trace ID + service tersangka, cari penyebabnya ==");
        logs.stream().filter(l -> badTraces.contains(l.traceId()) && l.service().equals(suspect) && !l.level().equals("INFO"))
            .forEach(l -> System.out.printf("[%s] trace=%s %s%n", l.level(), l.traceId(), l.msg()));

        System.out.println("\nHipotesis: connection pool DB payment-service habis (10/10, 34 antre), bukan bug logic.");
        System.out.println("Mitigasi dulu (scale/restart/rollback), akar masalah (query lambat?) dicari setelah pelanggan aman.");
    }
}
