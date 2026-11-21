import java.util.*;
import java.util.concurrent.*;

// Simulasi load test: GET /orders dengan N+1 (21 query) vs batch (2 query).
// DB palsu: pool 10 koneksi, tiap query 2 ms. 50 virtual user x 20 request.
public class LoadTestDemo {
    static final Semaphore POOL = new Semaphore(10);

    static void query() throws InterruptedException {
        POOL.acquire();
        try { Thread.sleep(2); } finally { POOL.release(); }
    }

    static long request(int queries) throws InterruptedException {
        long t0 = System.nanoTime();
        for (int i = 0; i < queries; i++) query();
        return (System.nanoTime() - t0) / 1_000_000;
    }

    static void run(String label, int queries) throws Exception {
        int vus = 50, iterations = 20;
        List<Long> lat = Collections.synchronizedList(new ArrayList<>());
        ExecutorService ex = Executors.newFixedThreadPool(vus);
        long t0 = System.nanoTime();
        List<Future<?>> fs = new ArrayList<>();
        for (int v = 0; v < vus; v++) fs.add(ex.submit(() -> {
            for (int i = 0; i < iterations; i++) {
                try { lat.add(request(queries)); } catch (InterruptedException e) { return; }
            }
        }));
        for (Future<?> f : fs) f.get();
        double secs = (System.nanoTime() - t0) / 1e9;
        ex.shutdown();
        Collections.sort(lat);
        double avg = lat.stream().mapToLong(Long::longValue).average().orElse(0);
        System.out.printf("%-14s avg=%5.0f ms  p95=%4d ms  p99=%4d ms  throughput=%5.0f req/s%n",
            label, avg, pct(lat, 95), pct(lat, 99), lat.size() / secs);
    }

    static long pct(List<Long> sorted, int p) {
        return sorted.get(Math.min(sorted.size() - 1, (int) Math.ceil(p / 100.0 * sorted.size()) - 1));
    }

    public static void main(String[] a) throws Exception {
        run("sebelum (N+1)", 21);
        run("sesudah (batch)", 2);
    }
}
