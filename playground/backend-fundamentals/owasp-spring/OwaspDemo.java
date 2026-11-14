import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Demo 11.4: tiga celah OWASP yang sering muncul di Spring Boot, bug lalu fix. Tanpa DB, cuma simulasi logika. */
public class OwaspDemo {

    // ---------- 1. Injection ----------
    // BUG: input user ditempel ke SQL.
    static String buildQueryBug(String name) {
        return "SELECT * FROM users WHERE name = '" + name + "'";
    }

    // FIX: query tetap, nilai dikirim terpisah (PreparedStatement / JPA binding). Input cuma pernah jadi data.
    static String buildQueryFix(String name, List<String> params) {
        params.add(name);
        return "SELECT * FROM users WHERE name = ?";
    }

    // ---------- 2. Broken access control (IDOR) ----------
    record Order(int id, String owner, int total) {}

    static final Map<Integer, Order> ORDERS = Map.of(
            1, new Order(1, "budi", 100),
            2, new Order(2, "yuni", 999));

    // BUG: login cukup, id mana pun boleh dibuka.
    static Order getOrderBug(int id, String caller) {
        return ORDERS.get(id);
    }

    // FIX: cek kepemilikan di server di tiap akses. Bukan 403, tapi "nggak ketemu" supaya id nggak bisa ditebak.
    static Order getOrderFix(int id, String caller) {
        Order o = ORDERS.get(id);
        return (o != null && o.owner().equals(caller)) ? o : null;
    }

    // ---------- 3. Mass assignment ----------
    record User(String name, String role) {}

    // BUG: semua field dari request di-bind, termasuk role.
    static User registerBug(Map<String, String> body) {
        return new User(body.get("name"), body.getOrDefault("role", "USER"));
    }

    // FIX: DTO hanya punya field yang boleh diisi client. Role selalu ditentukan server.
    static final Set<String> ALLOWED = Set.of("name");

    static User registerFix(Map<String, String> body) {
        Map<String, String> clean = new HashMap<>(body);
        clean.keySet().retainAll(ALLOWED);
        return new User(clean.get("name"), "USER");
    }

    public static void main(String[] args) {
        System.out.println("--- 1. Injection ---");
        String evil = "x' OR '1'='1";
        System.out.println("bug : " + buildQueryBug(evil));
        List<String> params = new java.util.ArrayList<>();
        System.out.println("fix : " + buildQueryFix(evil, params) + "   params=" + params);

        System.out.println("--- 2. Broken access control (budi buka order milik yuni) ---");
        System.out.println("bug : " + getOrderBug(2, "budi"));
        System.out.println("fix : " + getOrderFix(2, "budi") + "   (order budi sendiri: " + getOrderFix(1, "budi") + ")");

        System.out.println("--- 3. Mass assignment (register dengan role=ADMIN) ---");
        Map<String, String> body = Map.of("name", "budi", "role", "ADMIN");
        System.out.println("bug : " + registerBug(body));
        System.out.println("fix : " + registerFix(body));

        // self-check: demo gagal keras kalau fix nggak bekerja
        if (getOrderFix(2, "budi") != null || !registerFix(body).role().equals("USER")
                || buildQueryFix(evil, new java.util.ArrayList<>()).contains("OR")) {
            throw new AssertionError("fix nggak bekerja");
        }
    }
}
