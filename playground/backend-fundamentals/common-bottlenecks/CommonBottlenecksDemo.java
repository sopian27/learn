import java.util.*;
import java.util.concurrent.*;

/** Dua gejala bottleneck: thread pool starvation (waktu antre) dan cache miss (hit ratio). */
public class CommonBottlenecksDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("== 1. Thread pool starvation: 40 request, tiap request nunggu DB 100 ms ==");
        runPool(4);
        runPool(40);

        System.out.println("\n== 2. Cache miss: miss = 50 ms ke DB, hit = 1 ms ==");
        for (double hit : new double[]{0.95, 0.80, 0.50}) {
            double avg = hit * 1 + (1 - hit) * 50;
            System.out.printf("hit ratio %.0f%% -> rata-rata %.1f ms, beban DB %.0f%% dari semua request%n",
                    hit * 100, avg, (1 - hit) * 100);
        }
    }

    static void runPool(int poolSize) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        List<Future<long[]>> futures = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            final long submitted = System.nanoTime();
            futures.add(pool.submit(() -> {
                long start = System.nanoTime();
                Thread.sleep(100); // nunggu DB: thread diam, CPU nganggur
                long end = System.nanoTime();
                return new long[]{(start - submitted) / 1_000_000, (end - start) / 1_000_000};
            }));
        }
        long maxWait = 0, maxService = 0;
        for (Future<long[]> f : futures) {
            long[] r = f.get();
            maxWait = Math.max(maxWait, r[0]);
            maxService = Math.max(maxService, r[1]);
        }
        pool.shutdown();
        System.out.printf("pool=%2d  waktu antre terlama=%4d ms  waktu kerja terlama=%d ms%n",
                poolSize, maxWait, maxService);
    }
}
