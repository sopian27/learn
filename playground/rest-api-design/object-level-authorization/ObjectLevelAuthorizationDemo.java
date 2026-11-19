import java.util.*;

/** Demo BOLA/IDOR: endpoint cuma cek "sudah login", tidak cek "order ini punya siapa". Run: java ObjectLevelAuthorizationDemo.java */
public class ObjectLevelAuthorizationDemo {

    record Order(long id, String ownerId, long total) {}
    record Response(int status, String body) {}

    static final Map<Long, Order> DB = Map.of(
        1L, new Order(1, "budi", 150_000),
        2L, new Order(2, "wahyu", 9_900_000));

    // VULNERABLE: authentication ada (userId dari token), authorization object-level tidak ada.
    static Response getOrderVulnerable(String userId, long orderId) {
        Order o = DB.get(orderId);
        return o == null ? new Response(404, "not found") : new Response(200, o.toString());
    }

    // FIXED: id dari URL tidak dipercaya; cek kepemilikan atau role ADMIN.
    // Order orang lain dibalas 404 (bukan 403) supaya penyerang tidak bisa tahu id itu ada.
    static Response getOrderSecure(String userId, Set<String> roles, long orderId) {
        Order o = DB.get(orderId);
        boolean allowed = o != null && (o.ownerId().equals(userId) || roles.contains("ADMIN"));
        return allowed ? new Response(200, o.toString()) : new Response(404, "not found");
    }

    public static void main(String[] args) {
        System.out.println("== Budi (customer) iseng ganti id 1 -> 2 di URL ==");
        System.out.println("vulnerable : " + getOrderVulnerable("budi", 2));
        System.out.println("secure     : " + getOrderSecure("budi", Set.of("CUSTOMER"), 2));

        System.out.println("\n== Budi buka order miliknya sendiri (id 1) ==");
        System.out.println("secure     : " + getOrderSecure("budi", Set.of("CUSTOMER"), 1));

        System.out.println("\n== Admin buka order siapa saja ==");
        System.out.println("secure     : " + getOrderSecure("zahra", Set.of("ADMIN"), 2));

        // self-check: fix harus menolak lintas-user
        if (getOrderSecure("budi", Set.of("CUSTOMER"), 2).status() != 404) throw new AssertionError("BOLA masih bolong");
    }
}
