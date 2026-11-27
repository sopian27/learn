import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// Connection, thread and memory leaks with stand-ins, no database needed.
public class ResourceLeakDemo {

    static final class Pool {
        final Semaphore free = new Semaphore(2);

        Lease borrow() {
            if (!free.tryAcquire())
                throw new IllegalStateException("pool exhausted");
            return free::release;
        }
    }

    interface Lease extends AutoCloseable {
        @Override
        void close();
    }

    // Bug: the exception path skips close().
    static void exportLeaky(Pool pool) {
        Lease con = pool.borrow();
        failingQuery();
        con.close();
    }

    // Fix: try-with-resources closes on every path.
    static void exportSafe(Pool pool) {
        try (Lease con = pool.borrow()) {
            failingQuery();
        }
    }

    static void failingQuery() {
        throw new UnsupportedOperationException("query failed");
    }

    static int freeAfterTwoFailures(java.util.function.Consumer<Pool> export) {
        var pool = new Pool();
        for (int i = 0; i < 2; i++) {
            try {
                export.accept(pool);
            } catch (UnsupportedOperationException expected) {
                // the request fails, the question is what happens to the connection
                // expected.printStackTrace(System.out);
            }
        }
        return pool.free.availablePermits();
    }

    // Threads still alive after 5 requests, with a new executor each time and no
    // shutdown.
    static int threadsLeft(boolean shutdown) throws Exception {
        var alive = new AtomicInteger();
        ThreadFactory counting = task -> new Thread(() -> {
            alive.incrementAndGet();
            try {
                task.run();
            } finally {
                alive.decrementAndGet();
            }
        });
        var created = new ArrayList<ExecutorService>();
        for (int i = 0; i < 5; i++) {
            var executor = Executors.newFixedThreadPool(1, counting);
            created.add(executor);
            executor.submit(() -> {
            }).get();
            if (shutdown) {
                executor.shutdown();
                executor.awaitTermination(5, TimeUnit.SECONDS);
            }
        }
        int left = alive.get();
        created.forEach(ExecutorService::shutdown); // clean up so the demo can exit
        return left;
    }

    static int entriesAfter(Map<Integer, byte[]> cache) {
        for (int user = 0; user < 1_000; user++)
            cache.put(user, new byte[1024]);
        return cache.size();
    }

    static Map<Integer, byte[]> bounded(int max) {
        return new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, byte[]> eldest) {
                return size() > max;
            }
        };
    }

    static void check(String label, int actual, int expected) {
        System.out.printf("%-44s %d%n", label, actual);
        if (actual != expected)
            throw new AssertionError(label + ": expected " + expected);
    }

    public static void main(String[] args) throws Exception {
        check("leaky export, free connections (of 2):", freeAfterTwoFailures(ResourceLeakDemo::exportLeaky), 0);
        check("try-with-resources, free connections:", freeAfterTwoFailures(ResourceLeakDemo::exportSafe), 2);
        check("executor per request, threads left:", threadsLeft(false), 5);
        check("with shutdown, threads left:", threadsLeft(true), 0);
        check("unbounded map, entries after 1000 users:", entriesAfter(new HashMap<>()), 1000);
        check("bounded LRU, entries after 1000 users:", entriesAfter(bounded(100)), 100);
    }
}
