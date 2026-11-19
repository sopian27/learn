import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

// Demo Lesson 9.1: JWT HS256 dibuat manual supaya kelihatan apa yang sebenarnya divalidasi.
// Bukan production-grade: di kerjaan pakai Spring Security Resource Server (Nimbus), jangan tulis sendiri.
public class JwtAuthenticationDemo {

    static final Base64.Encoder B64 = Base64.getUrlEncoder().withoutPadding();

    static String b64(String s) { return B64.encodeToString(s.getBytes(StandardCharsets.UTF_8)); }

    static String sign(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        return B64.encodeToString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    static String token(String alg, String sub, long exp, String secret) throws Exception {
        String head = b64("{\"alg\":\"" + alg + "\",\"typ\":\"JWT\"}");
        String body = b64("{\"sub\":\"" + sub + "\",\"exp\":" + exp + "}");
        return head + "." + body + "." + ("none".equals(alg) ? "" : sign(head + "." + body, secret));
    }

    // Versi salah: signature dicek, tapi exp tidak, dan alg dari header dipercaya.
    static boolean naive(String jwt, String secret) throws Exception {
        String[] p = jwt.split("[.]", -1);
        String header = new String(Base64.getUrlDecoder().decode(p[0]), StandardCharsets.UTF_8);
        if (header.contains("\"none\"")) return true;
        return sign(p[0] + "." + p[1], secret).equals(p[2]);
    }

    // Versi benar: alg dikunci HS256, signature constant-time, exp wajib ada dan belum lewat.
    static boolean strict(String jwt, String secret, long now) throws Exception {
        String[] p = jwt.split("[.]", -1);
        if (p.length != 3) return false;
        String header = new String(Base64.getUrlDecoder().decode(p[0]), StandardCharsets.UTF_8);
        if (!header.contains("\"HS256\"")) return false;
        byte[] expected = sign(p[0] + "." + p[1], secret).getBytes(StandardCharsets.UTF_8);
        if (!MessageDigest.isEqual(expected, p[2].getBytes(StandardCharsets.UTF_8))) return false;
        String body = new String(Base64.getUrlDecoder().decode(p[1]), StandardCharsets.UTF_8);
        int i = body.indexOf("\"exp\":");
        if (i < 0) return false;
        long exp = Long.parseLong(body.substring(i + 6).replaceAll("[^0-9].*", ""));
        return exp > now;
    }

    public static void main(String[] args) throws Exception {
        long now = System.currentTimeMillis() / 1000;
        String realSecret = "x9Fq2-very-long-random-secret-32-bytes-min!";

        String expired = token("HS256", "budi", now - 3600, realSecret);
        System.out.println("1. Token expired 1 jam lalu");
        System.out.println("   naive  -> " + naive(expired, realSecret) + "  (harusnya false: exp tidak dicek)");
        System.out.println("   strict -> " + strict(expired, realSecret, now));

        String forged = token("none", "admin", now + 3600, "");
        System.out.println("2. Token alg=none tanpa signature, sub=admin");
        System.out.println("   naive  -> " + naive(forged, realSecret) + "  (harusnya false: alg dipercaya dari header)");
        System.out.println("   strict -> " + strict(forged, realSecret, now));

        String weak = token("HS256", "budi", now + 3600, "secret");
        String sig = weak.substring(weak.lastIndexOf('.') + 1);
        String data = weak.substring(0, weak.lastIndexOf('.'));
        System.out.println("3. Secret lemah: tebak pakai kamus kecil");
        for (String guess : List.of("123456", "password", "admin", "secret")) {
            if (sign(data, guess).equals(sig)) {
                System.out.println("   secret ketemu: '" + guess + "' -> penyerang bisa bikin token siapa saja");
                break;
            }
        }
        String ok = token("HS256", "budi", now + 3600, realSecret);
        System.out.println("4. Token valid, secret kuat -> strict: " + strict(ok, realSecret, now));
    }
}
