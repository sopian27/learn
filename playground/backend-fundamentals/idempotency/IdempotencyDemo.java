import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Lesson 12.3: retry tanpa idempotency = dobel charge. Dengan idempotency key = aman.
 * Plus consumer dedup untuk pesan at-least-once.
 * Run: java IdempotencyDemo.java
 */
public class IdempotencyDemo {

    static final AtomicInteger chargesExecuted = new AtomicInteger();   // berapa kali uang benar-benar ditarik
    static final Map<String, String> processedKeys = new ConcurrentHashMap<>(); // key -> hasil pertama

    // Server payment: eksekusi selalu sukses, tapi response bisa hilang di jalan (timeout di sisi client).
    static String chargeNaive(String orderId) {
        chargesExecuted.incrementAndGet();
        return "CHARGED " + orderId;
    }

    // Idempotent: key yang sama = hasil yang sama, charge cuma jalan sekali.
    // computeIfAbsent atomik, jadi dua retry bersamaan juga cuma satu yang jalan.
    static String chargeIdempotent(String idempotencyKey, String orderId) {
        return processedKeys.computeIfAbsent(idempotencyKey, k -> {
            chargesExecuted.incrementAndGet();
            return "CHARGED " + orderId;
        });
    }

    // Client: kirim, response hilang (timeout), retry. Server nggak tahu itu request yang sama.
    static void clientWithLostResponse(Runnable send) {
        send.run();           // request 1: sampai, dieksekusi, tapi response hilang
        send.run();           // client timeout -> retry
    }

    // Consumer at-least-once: broker bisa kirim pesan yang sama lebih dari sekali.
    static final Set<String> seenMessageIds = ConcurrentHashMap.newKeySet();
    static final AtomicInteger emailsSent = new AtomicInteger();

    static void onMessage(String messageId) {
        if (!seenMessageIds.add(messageId)) return; // sudah diproses, buang
        emailsSent.incrementAndGet();
    }

    public static void main(String[] args) {
        System.out.println("== 1. Retry TANPA idempotency ==");
        chargesExecuted.set(0);
        clientWithLostResponse(() -> chargeNaive("ORD-1"));
        System.out.println("client kirim 2x -> uang ditarik " + chargesExecuted + "x (customer kena charge dobel)\n");

        System.out.println("== 2. Retry DENGAN idempotency key ==");
        chargesExecuted.set(0);
        String key = UUID.randomUUID().toString(); // dibuat SEKALI per aksi user, dipakai ulang tiap retry
        clientWithLostResponse(() -> chargeIdempotent(key, "ORD-2"));
        System.out.println("client kirim 2x, key sama -> uang ditarik " + chargesExecuted + "x\n");

        System.out.println("== 3. Consumer dedup (broker kirim ulang pesan) ==");
        for (String id : List.of("msg-1", "msg-2", "msg-1", "msg-1")) onMessage(id);
        System.out.println("4 pesan masuk (2 unik, msg-1 dikirim 3x) -> email terkirim " + emailsSent + "x");
    }
}
