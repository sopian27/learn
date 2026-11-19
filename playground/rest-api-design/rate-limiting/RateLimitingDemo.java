import java.util.HashMap;
import java.util.Map;

// Demo: tanpa rate limit vs token bucket per client. Waktu diinjeksi supaya deterministik.
public class RateLimitingDemo {

    record Decision(boolean allowed, long retryAfterSeconds) {}

    // Token bucket: kapasitas = burst maksimum, refill = laju stabil per detik.
    static class TokenBucket {
        final double capacity, refillPerSec;
        double tokens;
        long lastMillis;

        TokenBucket(double capacity, double refillPerSec, long nowMillis) {
            this.capacity = capacity;
            this.refillPerSec = refillPerSec;
            this.tokens = capacity;
            this.lastMillis = nowMillis;
        }

        Decision tryConsume(long nowMillis) {
            tokens = Math.min(capacity, tokens + (nowMillis - lastMillis) / 1000.0 * refillPerSec);
            lastMillis = nowMillis;
            if (tokens >= 1) {
                tokens -= 1;
                return new Decision(true, 0);
            }
            long wait = (long) Math.ceil((1 - tokens) / refillPerSec);
            return new Decision(false, wait);   // dikirim sebagai header Retry-After
        }
    }

    // Satu bucket per client (di produksi: key di Redis, mis. rl:{userId}:{endpoint}).
    static final Map<String, TokenBucket> buckets = new HashMap<>();

    static Decision limited(String clientId, long now) {
        return buckets.computeIfAbsent(clientId, k -> new TokenBucket(5, 1, now)).tryConsume(now);
    }

    public static void main(String[] args) {
        // Tanpa limit: Raihan nembak 100 request dalam 1 detik, semua lolos.
        int passedNaive = 100;
        System.out.println("[tanpa limit] lolos=" + passedNaive + "/100");

        // Dengan limit: burst 5, sisanya 429.
        int ok = 0, rejected = 0;
        long retryAfter = 0;
        for (int i = 0; i < 100; i++) {
            Decision d = limited("raihan", 0);
            if (d.allowed()) ok++; else { rejected++; retryAfter = d.retryAfterSeconds(); }
        }
        System.out.println("[token bucket] lolos=" + ok + " ditolak(429)=" + rejected + " Retry-After=" + retryAfter + "s");

        // Client lain tidak ikut kena.
        System.out.println("[client lain] yuni lolos=" + limited("yuni", 0).allowed());

        // Setelah menunggu sesuai Retry-After, boleh lagi.
        System.out.println("[setelah " + retryAfter + "s] raihan lolos=" + limited("raihan", retryAfter * 1000).allowed());

        // Self-check
        if (ok != 5 || rejected != 95 || retryAfter != 1) throw new AssertionError("bucket salah");
        if (!limited("yuni", 0).allowed()) throw new AssertionError("client lain terdampak");
        if (!limited("raihan", 3000).allowed()) throw new AssertionError("refill gagal");
        System.out.println("OK");
    }
}
