import java.util.Map;

// Demo: binding body langsung ke entity (rentan) vs lewat DTO whitelist (aman).
public class MassAssignmentDemo {

    static class User {
        String name = "";
        String email = "";
        String role = "CUSTOMER";   // tidak boleh diisi klien
        long balance = 0;           // tidak boleh diisi klien
    }

    record UpdateProfileRequest(String name, String email) {}   // DTO: cuma field yang boleh

    // Meniru Jackson/@RequestBody ke entity: semua key di body ikut ke-set.
    static void bindVulnerable(User u, Map<String, Object> body) {
        body.forEach((k, v) -> {
            switch (k) {
                case "name" -> u.name = (String) v;
                case "email" -> u.email = (String) v;
                case "role" -> u.role = (String) v;
                case "balance" -> u.balance = ((Number) v).longValue();
                default -> { }
            }
        });
    }

    // Meniru DTO: key lain tidak punya tempat, jadi dibuang.
    static void bindSecure(User u, Map<String, Object> body) {
        UpdateProfileRequest dto = new UpdateProfileRequest(
            (String) body.getOrDefault("name", u.name),
            (String) body.getOrDefault("email", u.email));
        u.name = dto.name();
        u.email = dto.email();
    }

    public static void main(String[] args) {
        Map<String, Object> attack = Map.of(
            "name", "Ikbal", "email", "ikbal@x.id", "role", "ADMIN", "balance", 1_000_000);

        User a = new User();
        bindVulnerable(a, attack);
        System.out.println("vulnerable: role=" + a.role + " balance=" + a.balance);

        User b = new User();
        bindSecure(b, attack);
        System.out.println("secure    : role=" + b.role + " balance=" + b.balance);

        if (!a.role.equals("ADMIN") || a.balance != 1_000_000) throw new AssertionError("vulnerable");
        if (!b.role.equals("CUSTOMER") || b.balance != 0 || !b.name.equals("Ikbal")) throw new AssertionError("secure");
        System.out.println("OK");
    }
}
