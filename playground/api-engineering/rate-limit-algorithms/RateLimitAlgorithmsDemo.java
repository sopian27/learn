import java.util.ArrayDeque;
import java.util.Deque;

// Lesson 2.1: limit 5 request / 10 detik. Burst 5 req di detik 9 dan 5 req lagi di detik 10.
// Waktu disimulasi (detik), bukan real clock.
public class RateLimitAlgorithmsDemo {

    static final int LIMIT = 5, WINDOW = 10;

    // Fixed window: counter per jendela [0-9], [10-19], ... Murah (1 angka), tapi boundary burst lolos.
    static class FixedWindow {
        long window = -1; int count;
        boolean allow(double t) {
            long w = (long) t / WINDOW;
            if (w != window) { window = w; count = 0; }
            return ++count <= LIMIT;
        }
    }

    // Sliding window log: simpan timestamp tiap request. Akurat, memori O(limit) per client.
    static class SlidingLog {
        final Deque<Double> log = new ArrayDeque<>();
        boolean allow(double t) {
            while (!log.isEmpty() && log.peekFirst() <= t - WINDOW) log.pollFirst();
            if (log.size() >= LIMIT) return false;
            log.addLast(t);
            return true;
        }
    }

    // Token bucket: kapasitas 5, isi ulang 0.5 token/detik. Burst boleh sampai kapasitas.
    static class TokenBucket {
        double tokens = LIMIT, last;
        boolean allow(double t) {
            tokens = Math.min(LIMIT, tokens + (t - last) * LIMIT / WINDOW);
            last = t;
            if (tokens < 1) return false;
            tokens -= 1;
            return true;
        }
    }

    // Leaky bucket (as meter): isi naik 1 per request, bocor 0.5/detik. Output dipaksa rata.
    static class LeakyBucket {
        double level, last;
        boolean allow(double t) {
            level = Math.max(0, level - (t - last) * LIMIT / WINDOW);
            last = t;
            if (level + 1 > LIMIT) return false;
            level += 1;
            return true;
        }
    }

    public static void main(String[] args) {
        // 5 request di t=9.0..9.4, lalu 5 request di t=10.0..10.4
        double[] times = {9.0, 9.1, 9.2, 9.3, 9.4, 10.0, 10.1, 10.2, 10.3, 10.4};
        var fw = new FixedWindow(); var sl = new SlidingLog();
        var tb = new TokenBucket(); var lb = new LeakyBucket();
        int a = 0, b = 0, c = 0, d = 0;
        System.out.printf("%-6s %-6s %-6s %-6s %-6s%n", "t", "fixed", "slideL", "tokenB", "leakyB");
        for (double t : times) {
            boolean x = fw.allow(t), y = sl.allow(t), z = tb.allow(t), w = lb.allow(t);
            a += x ? 1 : 0; b += y ? 1 : 0; c += z ? 1 : 0; d += w ? 1 : 0;
            System.out.printf("%-6.1f %-6s %-6s %-6s %-6s%n", t, x ? "OK" : "-", y ? "OK" : "-", z ? "OK" : "-", w ? "OK" : "-");
        }
        System.out.printf("lolos  %-6d %-6d %-6d %-6d  (limit sebenarnya %d per %ds)%n", a, b, c, d, LIMIT, WINDOW);
    }
}
