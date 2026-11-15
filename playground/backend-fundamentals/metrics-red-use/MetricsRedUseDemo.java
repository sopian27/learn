import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Lesson 13.2: RED (Rate, Errors, Duration) + USE (Utilization, Saturation, Errors).
 * Metrics disimulasikan manual (Micrometer Counter/Timer cara kerjanya mirip: angka yang diakumulasi, bukan log).
 * Run: java MetricsRedUseDemo.java
 */
public class MetricsRedUseDemo {

    static final AtomicLong requests = new AtomicLong();            // Rate  = Counter
    static final AtomicLong errors = new AtomicLong();              // Errors = Counter
    static final List<Long> durationsMs = Collections.synchronizedList(new ArrayList<>()); // Duration = Timer

    static void handleCheckout(int i) {
        long start = System.nanoTime();
        requests.incrementAndGet();
        try {
            // 95 request cepat, 5 request lambat (query berat) dan 3 di antaranya gagal
            boolean slow = i % 20 == 0;
            sleep(slow ? 900 : 20);
            if (slow && i % 40 != 0) throw new IllegalStateException("payment timeout");
        } catch (IllegalStateException e) {
            errors.incrementAndGet();
        } finally {
            durationsMs.add((System.nanoTime() - start) / 1_000_000);
        }
    }

    static long percentile(List<Long> sorted, double p) {
        return sorted.get((int) Math.min(sorted.size() - 1, Math.ceil(p * sorted.size()) - 1));
    }

    public static void main(String[] args) throws Exception {
        // USE: pool kecil (4 thread) + queue sengaja dibatasi supaya kelihatan saturation
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        int total = 100;
        long t0 = System.currentTimeMillis();
        for (int i = 1; i <= total; i++) { int n = i; pool.submit(() -> handleCheckout(n)); }

        sleep(300); // ambil snapshot USE saat beban sedang berjalan
        System.out.println("== USE (pool pemroses checkout, saat beban jalan) ==");
        System.out.println("Utilization : " + pool.getActiveCount() + "/" + pool.getMaximumPoolSize() + " thread sibuk");
        System.out.println("Saturation  : " + pool.getQueue().size() + " request antre nunggu thread");

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        double seconds = (System.currentTimeMillis() - t0) / 1000.0;

        List<Long> sorted = new ArrayList<>(durationsMs);
        Collections.sort(sorted);
        double avg = sorted.stream().mapToLong(Long::longValue).average().orElse(0);

        System.out.println("\n== RED (selesai) ==");
        System.out.printf("Rate     : %d request dalam %.1f detik (%.1f req/s)%n", requests.get(), seconds, requests.get() / seconds);
        System.out.printf("Errors   : %d (%.1f%%)%n", errors.get(), 100.0 * errors.get() / requests.get());
        System.out.printf("Duration : avg=%.0f ms | p50=%d ms | p99=%d ms%n", avg, percentile(sorted, 0.50), percentile(sorted, 0.99));
        System.out.println("Rata-rata kelihatan sehat; p99 yang jujur: 1 dari 100 pelanggan nunggu ~1 detik.");
    }

    static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); } }
}
