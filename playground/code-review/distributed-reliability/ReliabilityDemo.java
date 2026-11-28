import java.util.HashSet;
import java.util.Set;

// Pendamping Library Lesson 5.4. Jaringan, database, dan Kafka ditiru di memori.
public class ReliabilityDemo {

    static class LostResponse extends RuntimeException {
    }

    static class PaymentServer {
        long charged = 0;
        private final Set<String> seenKeys = new HashSet<>();
        private boolean dropNextResponse = true;

        // key == null berarti client tidak mengirim Idempotency-Key.
        void charge(String key, long amount) {
            if (key == null || seenKeys.add(key))
                charged += amount;
            if (dropNextResponse) { // uang sudah dipotong, jawabannya hilang
                dropNextResponse = false;
                throw new LostResponse();
            }
        }
    }

    static long payWithRetry(String key) {
        PaymentServer server = new PaymentServer();
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                server.charge(key, 100);
                break;
            } catch (LostResponse e) {
                System.out.println("   percobaan " + attempt + " timeout, ulang");
            }
        }
        return server.charged;
    }

    // Event yang sama dikirim dua kali (at-least-once).
    static long consumeTwice(boolean dedup) {
        Set<String> processed = new HashSet<>();
        long points = 0;
        for (String eventId : new String[] { "evt-1", "evt-1" }) {
            if (dedup && !processed.add(eventId))
                continue;
            points += 10;
        }
        return points;
    }

    static void check(String label, long actual, long expected) {
        System.out.println(label + " -> " + actual);
        if (actual != expected)
            throw new AssertionError(label);
    }

    public static void main(String[] args) {
        check("1. retry tanpa key, total terpotong ", payWithRetry(null), 200);
        check("2. retry dengan key, total terpotong", payWithRetry("order-7"), 100);
        check("3. consumer tanpa dedup, poin       ", consumeTwice(false), 20);
        check("4. consumer dengan dedup, poin      ", consumeTwice(true), 10);
    }
}
