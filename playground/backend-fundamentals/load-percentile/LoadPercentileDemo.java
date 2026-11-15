import java.util.*;
import java.util.concurrent.*;

/** Mini load test: 98% request cepat, 2% lambat. Rata-rata keliatan sehat, p99 nggak. */
public class LoadPercentileDemo {
    static void handleRequest() throws InterruptedException {
        boolean slow = ThreadLocalRandom.current().nextInt(100) < 2; // 2% kena lock contention / GC / cache miss
        Thread.sleep(slow ? 800 : 20);
    }

    static long pct(long[] sorted, double p) {
        return sorted[(int) Math.min(sorted.length - 1, Math.ceil(p / 100 * sorted.length) - 1)];
    }

    public static void main(String[] a) throws Exception {
        int requests = 3_000, users = 50;
        long[] ms = new long[requests];
        ExecutorService pool = Executors.newFixedThreadPool(users);
        long t0 = System.nanoTime();
        List<Future<?>> fs = new ArrayList<>();
        for (int i = 0; i < requests; i++) {
            final int idx = i;
            fs.add(pool.submit(() -> {
                long s = System.nanoTime();
                try { handleRequest(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                ms[idx] = (System.nanoTime() - s) / 1_000_000;
            }));
        }
        for (Future<?> f : fs) f.get();
        pool.shutdown();
        double secs = (System.nanoTime() - t0) / 1e9;

        long[] sorted = ms.clone();
        Arrays.sort(sorted);
        System.out.printf("requests=%d users=%d throughput=%.0f req/s%n", requests, users, requests / secs);
        System.out.printf("avg=%.0f ms  p50=%d  p95=%d  p99=%d  max=%d%n",
                Arrays.stream(ms).average().orElse(0), pct(sorted, 50), pct(sorted, 95), pct(sorted, 99), sorted[sorted.length - 1]);
        long over = Arrays.stream(ms).filter(x -> x > 200).count();
        System.out.printf("request > 200ms: %d (%.1f%%)%n", over, 100.0 * over / requests);
    }
}
