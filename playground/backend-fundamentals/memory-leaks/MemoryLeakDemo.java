import java.util.*;
import java.util.concurrent.*;

// Tiga leak klasik Spring Boot versi mini: static collection, listener, ThreadLocal.
// Tiap skenario: jalankan versi bocor, paksa GC, ukur heap; lalu versi fix.
public class MemoryLeakDemo {
    record Session(long id, byte[] payload) {}

    // --- 1. static collection yang tumbuh tanpa batas ---
    static final Map<Long, Session> LEAKY_CACHE = new HashMap<>();
    static final Map<Long, Session> BOUNDED_CACHE = new LinkedHashMap<>() {
        protected boolean removeEldestEntry(Map.Entry<Long, Session> e) { return size() > 100; }
    };

    static void sessions(Map<Long, Session> cache) {
        for (long i = 0; i < 20_000; i++) cache.put(i, new Session(i, new byte[1024]));
    }

    // --- 2. listener yang tidak di-unregister ---
    static final List<Runnable> EVENT_BUS = new ArrayList<>(); // singleton, hidup selama app hidup

    static void listeners(boolean unregister) {
        for (int i = 0; i < 2_000; i++) {
            byte[] screenState = new byte[10 * 1024]; // state milik komponen short-lived
            Runnable listener = () -> screenState[0]++;
            EVENT_BUS.add(listener);
            if (unregister) EVENT_BUS.remove(listener); // fix: pasangkan register dengan unregister
        }
    }

    // --- 3. ThreadLocal di thread pool yang tidak dibersihkan ---
    static final ThreadLocal<byte[]> REQUEST_BUFFER = new ThreadLocal<>();

    static void threadLocal(boolean cleanup) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4); // thread hidup terus, seperti Tomcat
        for (int i = 0; i < 8; i++) {
            pool.submit(() -> {
                try {
                    REQUEST_BUFFER.set(new byte[8 * 1024 * 1024]);
                    // ...proses request...
                } finally {
                    if (cleanup) REQUEST_BUFFER.remove(); // fix
                }
            }).get();
        }
        report("  thread pool masih hidup, request sudah selesai");
        pool.shutdown();
    }

    static long usedMb() {
        System.gc(); // cuma hint, tapi cukup untuk demo
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        Runtime rt = Runtime.getRuntime();
        return (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
    }

    static void report(String label) { System.out.printf("%-52s heap terpakai: %4d MB%n", label, usedMb()); }

    public static void main(String[] args) throws Exception {
        report("baseline");

        System.out.println("\n[1] static collection");
        sessions(LEAKY_CACHE);   report("  cache tanpa batas (leak)");
        LEAKY_CACHE.clear();
        sessions(BOUNDED_CACHE); report("  cache dengan batas 100 (fix)");

        System.out.println("\n[2] listener");
        listeners(false); report("  tidak di-unregister (leak)");
        EVENT_BUS.clear();
        listeners(true);  report("  di-unregister (fix)");

        System.out.println("\n[3] ThreadLocal");
        System.out.println("  tanpa remove():");
        threadLocal(false);
        System.out.println("  dengan remove() di finally:");
        threadLocal(true);
        report("selesai");
    }
}
