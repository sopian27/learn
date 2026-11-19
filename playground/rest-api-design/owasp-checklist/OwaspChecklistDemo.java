import java.net.URI;
import java.util.*;

/** Tiga cek otomatis dari OWASP API Top 10 2023: API9 inventory, API3 field sensitif, API7 SSRF. */
public class OwaspChecklistDemo {

    // API9: endpoint yang hidup tapi tidak ada di dokumentasi (shadow endpoint)
    static Set<String> shadow(Set<String> live, Set<String> documented) {
        Set<String> s = new TreeSet<>(live);
        s.removeAll(documented);
        return s;
    }

    // API3: field sensitif yang ikut keluar di response
    static final Set<String> SENSITIVE = Set.of("passwordHash", "ssn", "internalNote");
    static Set<String> leaked(Map<String, Object> response) {
        Set<String> s = new TreeSet<>(response.keySet());
        s.retainAll(SENSITIVE);
        return s;
    }

    // API7: URL dari klien hanya boleh ke host allowlist (shortcut: exact host match, tidak resolve DNS; produksi wajib cek IP hasil resolve)
    static final Set<String> ALLOWED_HOSTS = Set.of("hooks.partner.example");
    static boolean safeUrl(String url) {
        try {
            URI u = URI.create(url);
            return "https".equals(u.getScheme()) && u.getHost() != null && ALLOWED_HOSTS.contains(u.getHost());
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    static void check(boolean cond, String msg) {
        if (!cond) throw new AssertionError(msg);
    }

    public static void main(String[] args) {
        Set<String> live = Set.of("GET /v2/users", "GET /v1/users", "POST /v2/orders");
        Set<String> documented = Set.of("GET /v2/users", "POST /v2/orders");
        Set<String> sh = shadow(live, documented);
        System.out.println("[API9 inventory] endpoint hidup tapi tidak terdokumentasi: " + sh);
        check(sh.equals(Set.of("GET /v1/users")), "shadow endpoint harus ketemu");

        Map<String, Object> resp = Map.of("id", 7, "name", "Yuni", "passwordHash", "$2a$10$x");
        Set<String> lk = leaked(resp);
        System.out.println("[API3 exposure] field sensitif bocor: " + lk);
        check(lk.equals(Set.of("passwordHash")), "passwordHash harus terdeteksi");
        check(leaked(Map.of("id", 7, "name", "Yuni")).isEmpty(), "response bersih lolos");

        String[] urls = {"https://hooks.partner.example/cb", "http://169.254.169.254/latest/meta-data",
                "https://localhost:8080/admin", "https://evil.example/x"};
        boolean[] want = {true, false, false, false};
        for (int i = 0; i < urls.length; i++) {
            boolean ok = safeUrl(urls[i]);
            System.out.println("[API7 ssrf] " + urls[i] + " -> " + (ok ? "boleh" : "ditolak"));
            check(ok == want[i], "salah untuk " + urls[i]);
        }
        System.out.println("OK");
    }
}
