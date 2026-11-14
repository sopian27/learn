import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Lesson 12.2: retry+backoff+jitter, circuit breaker, bulkhead.
 * Versi mini tanpa library supaya mekanismenya kelihatan. Di production pakai Resilience4j.
 * Run: java ResiliencePatternsDemo.java
 */
public class ResiliencePatternsDemo {

    // Simulasi payment gateway yang down. Hitung berapa kali benar-benar dipanggil.
    static final AtomicInteger gatewayCalls = new AtomicInteger();
    static volatile boolean gatewayUp = false;

    static String callGateway() {
        gatewayCalls.incrementAndGet();
        if (!gatewayUp) throw new IllegalStateException("gateway 503");
        return "OK";
    }

    // ---- Retry: backoff eksponensial + jitter, batas percobaan ----
    static String withRetry(Callable<String> call, int maxAttempts) throws Exception {
        long wait = 50;
        for (int attempt = 1; ; attempt++) {
            try { return call.call(); }
            catch (Exception e) {
                if (attempt == maxAttempts) throw e;
                long jitter = ThreadLocalRandom.current().nextLong(wait / 2 + 1);
                Thread.sleep(wait + jitter);
                wait *= 2;
            }
        }
    }

    // ---- Circuit breaker: CLOSED -> OPEN -> HALF_OPEN ----
    static class CircuitBreaker {
        enum State { CLOSED, OPEN, HALF_OPEN }
        State state = State.CLOSED;
        int failures = 0;
        long openedAt;
        final int threshold = 3;
        final long openMillis = 300;

        synchronized String call(Callable<String> call) throws Exception {
            if (state == State.OPEN) {
                if (System.currentTimeMillis() - openedAt < openMillis) {
                    throw new IllegalStateException("circuit OPEN, ditolak tanpa nyentuh gateway");
                }
                state = State.HALF_OPEN; // kasih satu percobaan
            }
            try {
                String result = call.call();
                failures = 0;
                state = State.CLOSED;
                return result;
            } catch (Exception e) {
                failures++;
                if (state == State.HALF_OPEN || failures >= threshold) {
                    state = State.OPEN;
                    openedAt = System.currentTimeMillis();
                }
                throw e;
            }
        }
    }

    // ---- Bulkhead: batasi call paralel ke satu dependency ----
    static final Semaphore bulkhead = new Semaphore(2);

    static String bulkheadCall() throws Exception {
        if (!bulkhead.tryAcquire()) throw new IllegalStateException("bulkhead penuh, ditolak cepat");
        try { Thread.sleep(200); return "OK"; } finally { bulkhead.release(); }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("== 1. Retry membabi buta vs backoff (gateway down) ==");
        gatewayCalls.set(0);
        try { withRetry(ResiliencePatternsDemo::callGateway, 3); } catch (Exception e) { /* habis */ }
        System.out.println("1 request, retry 3x -> gateway kena " + gatewayCalls + " call");
        System.out.println("Kalau 100 request bareng: ~" + (100 * gatewayCalls.get()) + " call ke service yang lagi sekarat\n");

        System.out.println("== 2. Circuit breaker ==");
        CircuitBreaker cb = new CircuitBreaker();
        gatewayCalls.set(0);
        for (int i = 1; i <= 8; i++) {
            try { cb.call(ResiliencePatternsDemo::callGateway); }
            catch (Exception e) { System.out.println("req " + i + " [" + cb.state + "] " + e.getMessage()); }
        }
        System.out.println("8 request, gateway cuma kena " + gatewayCalls + " call\n");

        System.out.println("gateway pulih, tunggu open window...");
        gatewayUp = true;
        Thread.sleep(350);
        System.out.println("req 9 -> " + cb.call(ResiliencePatternsDemo::callGateway) + " [" + cb.state + "]\n");

        System.out.println("== 3. Bulkhead (maks 2 call paralel) ==");
        ExecutorService pool = Executors.newFixedThreadPool(5);
        var futures = new java.util.ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++) futures.add(pool.submit(ResiliencePatternsDemo::bulkheadCall));
        int ok = 0, rejected = 0;
        for (var f : futures) {
            try { f.get(); ok++; } catch (ExecutionException e) { rejected++; }
        }
        pool.shutdown();
        System.out.println("5 request bareng -> lolos " + ok + ", ditolak cepat " + rejected);
    }
}
