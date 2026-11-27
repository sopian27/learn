import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.ReentrantLock;

// Oversell (check-then-act) and lock-order deadlock, forced to happen every run.
public class ConcurrencyDemo {

    // Bug: check and write are two steps. The barrier makes both buyers check before either writes.
    static int sellCheckThenAct() throws Exception {
        var stock = new AtomicInteger(1);
        var sold = new AtomicInteger();
        var bothChecked = new CyclicBarrier(2);
        runTwo(() -> {
            boolean available = stock.get() > 0;
            await(bothChecked);
            if (available) {
                stock.decrementAndGet();
                sold.incrementAndGet();
            }
        });
        return sold.get();
    }

    // Fix: check and write in one atomic step (SQL: UPDATE ... WHERE stock >= 1).
    static int sellAtomic() throws Exception {
        var stock = new AtomicInteger(1);
        var sold = new AtomicInteger();
        var start = new CyclicBarrier(2);
        runTwo(() -> {
            await(start);
            if (stock.getAndUpdate(s -> s > 0 ? s - 1 : s) > 0) sold.incrementAndGet();
        });
        return sold.get();
    }

    // Two transfers in opposite directions. tryLock with timeout, so the demo never hangs.
    static int transfers(boolean orderedLocks) throws Exception {
        var a = new ReentrantLock();
        var b = new ReentrantLock();
        var done = new AtomicInteger();
        var holdingFirst = new CyclicBarrier(2);
        var gaveUp = new CyclicBarrier(2);
        var pool = Executors.newFixedThreadPool(2);
        try {
            Callable<Void> aToB = () -> transfer(a, b, orderedLocks ? null : holdingFirst, gaveUp, done);
            Callable<Void> bToA = () -> orderedLocks
                    ? transfer(a, b, null, gaveUp, done)              // same order as aToB
                    : transfer(b, a, holdingFirst, gaveUp, done);     // opposite order
            for (var f : pool.invokeAll(java.util.List.of(aToB, bToA))) f.get();
        } finally {
            pool.shutdown();
        }
        return done.get();
    }

    static Void transfer(ReentrantLock first, ReentrantLock second, CyclicBarrier holdingFirst,
                         CyclicBarrier gaveUp, AtomicInteger done) throws Exception {
        first.lock();
        try {
            if (holdingFirst != null) await(holdingFirst);
            boolean got = second.tryLock(200, TimeUnit.MILLISECONDS);
            if (got) {
                done.incrementAndGet();
                second.unlock();
            }
            if (holdingFirst != null) await(gaveUp); // keep the first lock until both attempts end
        } finally {
            first.unlock();
        }
        return null;
    }

    static void runTwo(Runnable task) throws Exception {
        var pool = Executors.newFixedThreadPool(2);
        try {
            var x = pool.submit(task);
            var y = pool.submit(task);
            x.get();
            y.get();
        } finally {
            pool.shutdown();
        }
    }

    static void await(CyclicBarrier barrier) {
        try {
            barrier.await(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    static void check(String label, int actual, int expected) {
        System.out.printf("%-42s %d%n", label, actual);
        if (actual != expected) throw new AssertionError(label + ": expected " + expected);
    }

    public static void main(String[] args) throws Exception {
        check("stock 1, check-then-act, items sold:", sellCheckThenAct(), 2);
        check("stock 1, atomic update, items sold:", sellAtomic(), 1);
        check("opposite lock order, transfers done:", transfers(false), 0);
        check("same lock order, transfers done:", transfers(true), 2);
    }
}
