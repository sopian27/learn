import java.util.*;
import java.util.concurrent.*;

/**
 * Demo Lesson 12.1: satu dependency lambat menghabiskan thread pool service kita.
 * Run: java FailureModesDemo.java
 * Pool checkout cuma 4 thread. Recommendation service "hang" 2000 ms.
 */
public class FailureModesDemo {

    static final int HANG_MS = 2000;

    // Simulasi HTTP call ke Recommendation. timeoutMs = batas tunggu client.
    static String callRecommendation(long timeoutMs) throws Exception {
        if (timeoutMs < HANG_MS) {
            Thread.sleep(timeoutMs);
            throw new TimeoutException("recommendation timeout " + timeoutMs + "ms");
        }
        Thread.sleep(HANG_MS);
        return "recs";
    }

    static void run(String label, long timeoutMs) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4); // thread Tomcat versi mini
        long start = System.currentTimeMillis();

        // 12 request checkout yang ikut manggil Recommendation
        for (int i = 0; i < 12; i++) {
            pool.submit(() -> {
                try { return callRecommendation(timeoutMs); }
                catch (Exception e) { return "fallback"; } // request tetap selesai
            });
        }

        // Request sehat: /health, tidak butuh Recommendation sama sekali
        Future<String> health = pool.submit(() -> "OK");
        health.get();
        long waited = System.currentTimeMillis() - start;

        System.out.printf("%-28s /health baru kebalas setelah %5d ms%n", label, waited);
        pool.shutdownNow();
    }

    public static void main(String[] args) throws Exception {
        run("TANPA timeout (tunggu 2s):", HANG_MS);
        run("DENGAN timeout 200ms:", 200);
        System.out.println("Perhatikan: request sehat ikut mati karena thread habis dipakai call yang hang.");
    }
}
