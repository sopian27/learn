import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// Model mini cache stampede: 20 request bersamaan menemukan entri sudah kadaluarsa.
// Bukan Redis asli; "DB" cuma method lambat yang menghitung berapa kali dipanggil.
public class CacheStampedeDemo {
    static final int REQUESTS = 20;
    static final AtomicInteger dbCalls = new AtomicInteger();
    static final ConcurrentHashMap<String, Integer> cache = new ConcurrentHashMap<>();
    static final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    static int loadFromDb() {
        dbCalls.incrementAndGet();
        try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return 100;
    }

    // Tanpa proteksi: semua yang miss ikut ke DB.
    static int naive(String key) {
        Integer v = cache.get(key);
        if (v != null) return v;
        v = loadFromDb();
        cache.put(key, v);
        return v;
    }

    // Single-flight per key: satu yang rebuild, sisanya menunggu lalu baca cache.
    static int locked(String key) {
        Integer v = cache.get(key);
        if (v != null) return v;
        synchronized (locks.computeIfAbsent(key, k -> new Object())) {
            v = cache.get(key); // cek ulang setelah dapat lock
            if (v == null) { v = loadFromDb(); cache.put(key, v); }
            return v;
        }
    }

    static int burst(java.util.function.Function<String, Integer> reader) throws Exception {
        cache.clear(); dbCalls.set(0);
        ExecutorService pool = Executors.newFixedThreadPool(REQUESTS);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<?>> fs = new ArrayList<>();
        for (int i = 0; i < REQUESTS; i++)
            fs.add(pool.submit(() -> { start.await(); return reader.apply("price:1"); }));
        start.countDown();
        for (Future<?> f : fs) f.get();
        pool.shutdown();
        return dbCalls.get();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Stampede   : " + REQUESTS + " request, tanpa proteksi, DB dipanggil " + burst(CacheStampedeDemo::naive) + "x");
        System.out.println("Single-flight: " + REQUESTS + " request, pakai lock per key, DB dipanggil " + burst(CacheStampedeDemo::locked) + "x");

        Random rnd = new Random(42); // seed tetap biar hasil sama
        List<Integer> jitter = new ArrayList<>();
        for (int i = 0; i < 5; i++) jitter.add(600 + rnd.nextInt(120)); // TTL 600 detik + acak 0-119
        System.out.println("TTL sama   : 5 key kadaluarsa di detik [600, 600, 600, 600, 600] (serentak)");
        System.out.println("TTL jitter : 5 key kadaluarsa di detik " + jitter + " (tersebar)");
    }
}
