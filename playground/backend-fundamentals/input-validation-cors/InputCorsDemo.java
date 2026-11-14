import java.util.Set;

/** Demo 11.5: validasi input, escape output, dan CORS allowlist. Bug lalu fix, tanpa Spring. */
public class InputCorsDemo {

    // ---------- 1. Validasi input ----------
    // BUG: apa pun diterima, panjang dan karakter nggak dicek.
    static boolean usernameBug(String s) {
        return s != null;
    }

    // FIX: allowlist (huruf kecil, angka, underscore) + batas panjang. Tolak sisanya, jangan "dibersihkan".
    static boolean usernameFix(String s) {
        return s != null && s.matches("^[a-z0-9_]{3,20}$");
    }

    // ---------- 2. Escape output (lapisan terakhir lawan XSS) ----------
    // BUG: teks user ditempel mentah ke HTML.
    static String renderBug(String comment) {
        return "<p>" + comment + "</p>";
    }

    // FIX: karakter khusus HTML di-escape, jadi tampil sebagai teks, bukan tag.
    static String renderFix(String comment) {
        String safe = comment.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
        return "<p>" + safe + "</p>";
    }

    // ---------- 3. CORS ----------
    // BUG: cek "mengandung" domain kita, jadi domain attacker yang namanya mirip ikut lolos.
    static boolean corsBug(String origin) {
        return origin != null && origin.contains("example.com");
    }

    // FIX: cocokkan persis dengan daftar origin yang diizinkan. Bukan "*", bukan refleksi origin.
    static final Set<String> ALLOWED_ORIGINS = Set.of("https://app.example.com");

    static boolean corsFix(String origin) {
        return origin != null && ALLOWED_ORIGINS.contains(origin);
    }

    public static void main(String[] args) {
        System.out.println("--- 1. Validasi username ---");
        String[] inputs = {"budi_01", "ab", "budi; DROP TABLE users", "a".repeat(500)};
        for (String in : inputs) {
            String shown = in.length() > 25 ? in.substring(0, 22) + "..." : in;
            System.out.println(shown + "  bug=" + usernameBug(in) + "  fix=" + usernameFix(in));
        }

        System.out.println("--- 2. Escape output ---");
        String xss = "<script>alert(1)</script>";
        System.out.println("bug : " + renderBug(xss));
        System.out.println("fix : " + renderFix(xss));

        System.out.println("--- 3. CORS origin ---");
        String[] origins = {"https://app.example.com", "https://app.example.com.evil.io", "https://evil.io"};
        for (String o : origins) {
            System.out.println(o + "  bug=" + corsBug(o) + "  fix=" + corsFix(o));
        }

        // self-check: demo gagal keras kalau fix nggak bekerja
        if (usernameFix("budi; DROP TABLE users") || usernameFix("a".repeat(500)) || !usernameFix("budi_01")
                || renderFix(xss).contains("<script>")
                || corsFix("https://app.example.com.evil.io") || !corsFix("https://app.example.com")) {
            throw new AssertionError("fix nggak bekerja");
        }
    }
}
