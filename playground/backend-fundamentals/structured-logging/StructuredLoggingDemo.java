import java.util.*;
import java.util.concurrent.*;

/**
 * Lesson 13.1: log string biasa vs log JSON + correlationId.
 * Dua request jalan bersamaan; log-nya campur aduk. Cari "request Rakha" lewat grep vs lewat field.
 * MDC disimulasikan pakai ThreadLocal (SLF4J MDC cara kerjanya sama).
 * Run: java StructuredLoggingDemo.java
 */
public class StructuredLoggingDemo {

    static final List<String> plainLogs = Collections.synchronizedList(new ArrayList<>());
    static final List<String> jsonLogs = Collections.synchronizedList(new ArrayList<>());
    static final ThreadLocal<String> correlationId = new ThreadLocal<>(); // = MDC.put("correlationId", ...)

    // Anti-pattern: kalimat bebas, nggak ada field.
    static void plain(String msg) { plainLogs.add("INFO " + msg); }

    // Structured: satu baris JSON, field konsisten, correlationId otomatis dari context.
    static void json(String event, String key, Object value) {
        jsonLogs.add("{\"level\":\"INFO\",\"correlationId\":\"" + correlationId.get()
                + "\",\"event\":\"" + event + "\",\"" + key + "\":" + value + "}");
    }

    static void handleOrder(String cid, String user, int orderId, boolean fail) {
        correlationId.set(cid);
        try {
            plain("user " + user + " bikin order " + orderId);          // bahaya: user dan id nyatu di kalimat
            json("order_created", "orderId", orderId);
            if (fail) {
                plain("payment gagal untuk order " + orderId);
                json("payment_failed", "orderId", orderId);
            } else {
                plain("payment sukses untuk order " + orderId);
                json("payment_completed", "orderId", orderId);
            }
        } finally {
            correlationId.remove(); // thread pool dipakai ulang; kalau lupa, ID request lama bocor ke request baru
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch go = new CountDownLatch(1);
        Future<?> a = pool.submit(() -> { await(go); handleOrder("req-rakha-001", "rakha", 101, true); });
        Future<?> b = pool.submit(() -> { await(go); handleOrder("req-yuni-002", "yuni", 102, false); });
        go.countDown();
        a.get(); b.get(); pool.shutdown();

        System.out.println("== 1. Log biasa: mana yang gagal punya Rakha? Harus nebak dari kalimat ==");
        plainLogs.forEach(System.out::println);

        System.out.println("\n== 2. Log JSON: filter satu field, semua jejak request Rakha langsung ketemu ==");
        jsonLogs.stream().filter(l -> l.contains("\"correlationId\":\"req-rakha-001\"")).forEach(System.out::println);

        System.out.println("\n== 3. Log JSON: hitung semua payment_failed (query, bukan baca satu-satu) ==");
        System.out.println(jsonLogs.stream().filter(l -> l.contains("payment_failed")).count() + " payment gagal");
    }

    static void await(CountDownLatch l) { try { l.await(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); } }
}
