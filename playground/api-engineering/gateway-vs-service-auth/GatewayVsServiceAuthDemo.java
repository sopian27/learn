import java.util.*;

/**
 * Demo pembagian tugas auth & rate limit: gateway (kasar) vs service (halus).
 * [1] gateway saja: token valid, scope cukup, tapi Rani bisa baca order milik Akbar (BOLA) karena cuma service yang tahu pemilik order.
 * [2] service cek kepemilikan: 403.
 * [3] rate limit di gateway: batas per client (kasar). Batas endpoint mahal di service tidak didemokan.
 * Run: java GatewayVsServiceAuthDemo.java
 */
public class GatewayVsServiceAuthDemo {

    record Resp(int status, String body) {}

    static final Map<String, String> TOKENS = Map.of("tok-rani", "rani", "tok-akbar", "akbar");
    static final Map<String, String> ORDER_OWNER = Map.of("ORD-1", "rani", "ORD-2", "akbar");

    // ---- service ----
    static Resp orderService(String user, String orderId, boolean checkOwner) {
        if (!ORDER_OWNER.containsKey(orderId)) return new Resp(404, "not found");
        if (checkOwner && !ORDER_OWNER.get(orderId).equals(user)) return new Resp(403, "bukan order lu");
        return new Resp(200, "detail " + orderId + " milik " + ORDER_OWNER.get(orderId));
    }

    // ---- gateway ----
    static final Map<String, Integer> hits = new HashMap<>();
    static final int LIMIT_PER_CLIENT = 3;

    static Resp gateway(String token, String orderId, boolean serviceChecksOwner) {
        String user = TOKENS.get(token);
        if (user == null) return new Resp(401, "unauthorized");                  // authn di edge
        if (hits.merge(user, 1, Integer::sum) > LIMIT_PER_CLIENT) return new Resp(429, "too many requests"); // rate limit kasar
        return orderService(user, orderId, serviceChecksOwner);                  // gateway tak tahu siapa pemilik ORD-2
    }

    public static void main(String[] args) {
        System.out.println("[1] gateway saja, service tidak cek pemilik");
        System.out.println("    rani minta ORD-2 (milik akbar): " + gateway("tok-rani", "ORD-2", false));

        hits.clear();
        System.out.println("[2] service cek pemilik");
        System.out.println("    rani minta ORD-2: " + gateway("tok-rani", "ORD-2", true));
        System.out.println("    rani minta ORD-1: " + gateway("tok-rani", "ORD-1", true));

        hits.clear();
        System.out.println("[3] rate limit gateway, batas " + LIMIT_PER_CLIENT + " per client");
        for (int i = 1; i <= 4; i++)
            System.out.println("    request " + i + ": " + gateway("tok-akbar", "ORD-2", true));
    }
}
