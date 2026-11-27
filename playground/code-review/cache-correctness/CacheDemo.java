import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

// Fake DB and cache (plain maps) to show stale data and cache stampede without Redis.
public class CacheDemo {

    static final Map<String, Integer> DB = new ConcurrentHashMap<>();
    static final Map<String, Integer> CACHE = new ConcurrentHashMap<>();
    static final AtomicInteger dbReads = new AtomicInteger();

    static int load(String sku) {
        dbReads.incrementAndGet();
        return DB.get(sku);
    }

    static int price(String sku) {
        return CACHE.computeIfAbsent(sku, CacheDemo::load);
    }

    // Bug 1: a second write path that forgets the cache.
    static int forgottenWritePath() {
        reset();
        price("A");
        DB.put("A", 200);
        return price("A");
    }

    // Bug 2: evict before commit, a reader slips in and caches the old value again.
    static int evictBeforeCommit() {
        reset();
        CACHE.remove("A");
        price("A");        // concurrent reader: DB still holds the old value
        DB.put("A", 200);  // commit happens now
        return price("A");
    }

    // Fix: commit first, then evict.
    static int evictAfterCommit() {
        reset();
        price("A");
        DB.put("A", 200);
        CACHE.remove("A");
        return price("A");
    }

    // Many requests hit an expired key at once; returns how many times the DB was read.
    static int stampede(int requests, boolean singleFlight) throws Exception {
        reset();
        var allMissed = new CyclicBarrier(requests);
        var pool = Executors.newFixedThreadPool(requests); // no try-with-resources: also runs on Java 17
        try {
            var tasks = new ArrayList<Callable<Integer>>();
            for (int i = 0; i < requests; i++) {
                tasks.add(() -> {
                    Integer hit = CACHE.get("A");
                    allMissed.await(5, TimeUnit.SECONDS); // everyone sees the miss together
                    if (hit != null) return hit;
                    if (singleFlight) return price("A");  // one loader, the rest wait
                    int value = load("A");                // everyone loads
                    CACHE.put("A", value);
                    return value;
                });
            }
            for (var future : pool.invokeAll(tasks)) future.get();
        } finally {
            pool.shutdown();
        }
        return dbReads.get();
    }

    static void reset() {
        DB.put("A", 100);
        CACHE.clear();
        dbReads.set(0);
    }

    static void check(String label, int actual, int expected) {
        System.out.printf("%-40s %d%n", label, actual);
        if (actual != expected) throw new AssertionError(label + ": expected " + expected);
    }

    public static void main(String[] args) throws Exception {
        check("forgotten write path, price seen:", forgottenWritePath(), 100);
        check("evict before commit, price seen:", evictBeforeCommit(), 100);
        check("evict after commit, price seen:", evictAfterCommit(), 200);
        check("stampede, 50 requests, DB reads:", stampede(50, false), 50);
        check("single flight, 50 requests, DB reads:", stampede(50, true), 1);
    }
}
