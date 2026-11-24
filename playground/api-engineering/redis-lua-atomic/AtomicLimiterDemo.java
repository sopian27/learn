import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

// Lesson 2.2: limit 10 request / 60 detik, 50 thread nembak bersamaan.
// "Redis" disimulasi in-memory. Naif = GET lalu SET terpisah (race). Atomic = satu langkah utuh, seperti Lua script.
public class AtomicLimiterDemo {

    static final int LIMIT = 10, WINDOW_MS = 60_000, THREADS = 50;

    // Mirip ZSET: simpan timestamp request (sliding window log).
    static final Deque<Long> zset = new ArrayDeque<>();

    // Naif: ZREMRANGEBYSCORE, ZCARD, ZADD sebagai 3 perintah terpisah. Antar perintah, thread lain bisa nyelip.
    static boolean naive(long now) {
        synchronized (zset) { while (!zset.isEmpty() && zset.peekFirst() <= now - WINDOW_MS) zset.pollFirst(); }
        int size; synchronized (zset) { size = zset.size(); }          // GET
        if (size >= LIMIT) return false;
        pause();                                                       // jeda jaringan antar perintah
        synchronized (zset) { zset.addLast(now); }                     // SET
        return true;
    }

    // Atomic: seluruh cek + tulis dalam satu blok. Di Redis ini = EVAL Lua script (server single-threaded).
    //   redis.call('ZREMRANGEBYSCORE', KEYS[1], 0, now - window)
    //   if redis.call('ZCARD', KEYS[1]) >= limit then return 0 end
    //   redis.call('ZADD', KEYS[1], now, now); return 1
    static boolean atomic(long now) {
        synchronized (zset) {
            while (!zset.isEmpty() && zset.peekFirst() <= now - WINDOW_MS) zset.pollFirst();
            if (zset.size() >= LIMIT) return false;
            zset.addLast(now);
            return true;
        }
    }

    static void pause() { try { Thread.sleep(2); } catch (InterruptedException e) { Thread.currentThread().interrupt(); } }

    static int run(java.util.function.LongPredicate limiter) throws Exception {
        zset.clear();
        var allowed = new AtomicInteger();
        var start = new CountDownLatch(1);
        var threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                try { start.await(); } catch (InterruptedException e) { return; }
                if (limiter.test(System.currentTimeMillis())) allowed.incrementAndGet();
            });
            threads[i].start();
        }
        start.countDown();
        for (var t : threads) t.join();
        return allowed.get();
    }

    public static void main(String[] args) throws Exception {
        System.out.printf("limit = %d, thread = %d%n", LIMIT, THREADS);
        System.out.printf("naive  (GET lalu SET): lolos %d%n", run(AtomicLimiterDemo::naive));
        System.out.printf("atomic (satu langkah): lolos %d%n", run(AtomicLimiterDemo::atomic));
    }
}
