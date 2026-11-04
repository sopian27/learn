import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Demo Lesson 2.2: sizing pool, queue tak terbatas, dan synchronizedMap vs ConcurrentHashMap.
public class ExecutorPoolDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("CPU core: " + Runtime.getRuntime().availableProcessors());

        // 1: 40 task I/O-bound (tiap task "nunggu" 100 ms). Pool kecil = antre, pool pas = paralel.
        System.out.println("1a pool 4 thread  : " + timeIoTasks(4) + " ms (harapan ~1000)");
        System.out.println("1b pool 40 thread : " + timeIoTasks(40) + " ms (harapan ~100)");

        // 2a: newFixedThreadPool = antrean tak terbatas. Producer cepat, consumer lambat -> antrean numpuk diam-diam.
        ThreadPoolExecutor unbounded = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i = 0; i < 1000; i++) unbounded.submit(ExecutorPoolDemo::slowTask);
        System.out.println("2a antrean numpuk tanpa error, ukuran queue: " + unbounded.getQueue().size());
        unbounded.shutdownNow();

        // 2b: queue dibatasi -> pool menolak (backpressure) dan kita tahu lebih awal.
        ThreadPoolExecutor bounded = new ThreadPoolExecutor(
                2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)); // AbortPolicy default
        int rejected = 0;
        for (int i = 0; i < 1000; i++) {
            try { bounded.submit(ExecutorPoolDemo::slowTask); }
            catch (RejectedExecutionException e) { rejected++; }
        }
        System.out.println("2b ditolak karena queue penuh: " + rejected + " dari 1000");
        bounded.shutdownNow();

        // 3a: synchronizedMap, tiap call aman, tapi get lalu put terpisah = lost update.
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put("hits", 0);
        runPool(8, () -> { for (int i = 0; i < 10_000; i++) syncMap.put("hits", syncMap.get("hits") + 1); });
        System.out.println("3a synchronizedMap get+put (harus 80000): " + syncMap.get("hits"));

        // 3b: ConcurrentHashMap + merge = satu operasi atomik.
        Map<String, Integer> chm = new ConcurrentHashMap<>();
        runPool(8, () -> { for (int i = 0; i < 10_000; i++) chm.merge("hits", 1, Integer::sum); });
        System.out.println("3b ConcurrentHashMap merge (harus 80000): " + chm.get("hits"));

        // 3c: iterasi sambil map berubah. synchronizedMap melempar CME, ConcurrentHashMap tidak.
        Map<String, Integer> sm = Collections.synchronizedMap(new HashMap<>(Map.of("a", 1, "b", 2)));
        try {
            for (String k : sm.keySet()) sm.put(k + "x", 0);
            System.out.println("3c synchronizedMap: tidak error");
        } catch (ConcurrentModificationException e) {
            System.out.println("3c synchronizedMap: ConcurrentModificationException");
        }
        Map<String, Integer> cm = new ConcurrentHashMap<>(Map.of("a", 1, "b", 2));
        for (String k : cm.keySet()) cm.put(k + "x", 0);
        System.out.println("3c ConcurrentHashMap: tidak error, size = " + cm.size());
    }

    static long timeIoTasks(int poolSize) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        long start = System.nanoTime();
        List<java.util.concurrent.Future<?>> fs = new ArrayList<>();
        for (int i = 0; i < 40; i++) fs.add(pool.submit(() -> sleepQuietly(100)));
        for (var f : fs) f.get();
        pool.shutdown();
        return (System.nanoTime() - start) / 1_000_000;
    }

    static void runPool(int n, Runnable task) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(n);
        List<java.util.concurrent.Future<?>> fs = new ArrayList<>();
        for (int i = 0; i < n; i++) fs.add(pool.submit(task));
        for (var f : fs) f.get();
        pool.shutdown();
    }

    static void slowTask() { sleepQuietly(1000); }

    static void sleepQuietly(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
