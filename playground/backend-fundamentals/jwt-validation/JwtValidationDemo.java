import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

// Lesson 11.2: JWT mini (HS256). Bandingin verifier ceroboh vs verifier yang bener.
public class JwtValidationDemo {

    static final byte[] SECRET = "ganti-di-env-bukan-hardcode".getBytes(StandardCharsets.UTF_8); // demo saja
    static final Base64.Encoder B64 = Base64.getUrlEncoder().withoutPadding();

    static String b64(String s) { return B64.encodeToString(s.getBytes(StandardCharsets.UTF_8)); }
    static String sign(String data) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(SECRET, "HmacSHA256"));
        return B64.encodeToString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }
    static String token(String alg, String payload, boolean signed) throws Exception {
        String head = b64("{\"alg\":\"" + alg + "\"}"), body = b64(payload);
        return head + "." + body + "." + (signed ? sign(head + "." + body) : "");
    }
    static String decode(String part) { return new String(Base64.getUrlDecoder().decode(part), StandardCharsets.UTF_8); }

    // CEROBOH: cuma baca isi token, percaya begitu saja.
    static String buggy(String jwt) { return "OK  " + decode(jwt.split("[.]")[1]); }

    // BENER: algoritma dikunci, signature, exp, iss, aud dicek.
    static String strict(String jwt, long now) throws Exception {
        String[] p = jwt.split("[.]", -1);
        if (p.length != 3 || !decode(p[0]).contains("\"HS256\"")) return "TOLAK algoritma bukan HS256";
        if (!MessageDigest.isEqual(sign(p[0] + "." + p[1]).getBytes(), p[2].getBytes())) return "TOLAK signature salah";
        String c = decode(p[1]);
        if (!c.contains("\"iss\":\"auth.toko\"")) return "TOLAK issuer salah";
        if (!c.contains("\"aud\":\"order-api\"")) return "TOLAK audience salah";
        long exp = Long.parseLong(c.replaceAll(".*\"exp\":([0-9]+).*", "$1"));
        if (exp < now) return "TOLAK expired";
        return "OK  " + c;
    }

    static String claims(String role, long exp, String aud) {
        return "{\"sub\":\"budi\",\"role\":\"" + role + "\",\"iss\":\"auth.toko\",\"aud\":\"" + aud + "\",\"exp\":" + exp + "}";
    }

    public static void main(String[] a) throws Exception {
        long now = 1_000;
        String good = token("HS256", claims("USER", now + 900, "order-api"), true);
        String[] parts = good.split("[.]");
        String tampered = parts[0] + "." + b64(claims("ADMIN", now + 900, "order-api")) + "." + parts[2];
        String expired = token("HS256", claims("USER", now - 1, "order-api"), true);
        String none = token("none", claims("ADMIN", now + 900, "order-api"), false);
        String wrongAud = token("HS256", claims("USER", now + 900, "payment-api"), true);

        String[][] cases = {{"token sah", good}, {"payload diubah (USER->ADMIN)", tampered},
                {"sudah expired", expired}, {"alg=none tanpa signature", none}, {"audience salah", wrongAud}};
        for (String[] c : cases) {
            System.out.printf("%-30s ceroboh: %s%n", c[0], (buggy(c[1]).isEmpty() ? "" : "LOLOS"));
            System.out.printf("%-30s bener  : %s%n", "", strict(c[1], now));
        }
    }
}
