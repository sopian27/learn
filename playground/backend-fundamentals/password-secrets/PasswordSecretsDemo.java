import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.HexFormat;

/** Demo 11.3: hash password (cepat tanpa salt vs lambat + salt) dan secret dari env. */
public class PasswordSecretsDemo {

    static final SecureRandom RNG = new SecureRandom();
    static final int ITER = 210_000; // sengaja mahal; BCrypt/Argon2 punya "cost" serupa

    // BUG: SHA-256 polos, tanpa salt. Cepat dan deterministik.
    static String sha256(String pw) throws Exception {
        byte[] d = MessageDigest.getInstance("SHA-256").digest(pw.getBytes(StandardCharsets.UTF_8));
        return HexFormat.of().formatHex(d);
    }

    // FIX: salt acak per user + iterasi banyak (PBKDF2 dari JDK, stand-in untuk BCrypt/Argon2).
    static String slowHash(String pw, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt, ITER, 256);
        byte[] h = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        return HexFormat.of().formatHex(salt) + ":" + HexFormat.of().formatHex(h);
    }

    static boolean verify(String pw, String stored) throws Exception {
        byte[] salt = HexFormat.of().parseHex(stored.split(":")[0]);
        // constant-time compare, bukan equals()
        return MessageDigest.isEqual(slowHash(pw, salt).getBytes(), stored.getBytes());
    }

    static byte[] newSalt() { byte[] s = new byte[16]; RNG.nextBytes(s); return s; }

    public static void main(String[] args) throws Exception {
        System.out.println("--- 1. SHA-256 polos: dua user, password sama ---");
        String a = sha256("password123"), b = sha256("password123");
        System.out.println("budi : " + a.substring(0, 24) + "...");
        System.out.println("yuni : " + b.substring(0, 24) + "...  sama? " + a.equals(b));

        System.out.println("--- 2. Salted + lambat: password sama, hash beda ---");
        String h1 = slowHash("password123", newSalt()), h2 = slowHash("password123", newSalt());
        System.out.println("budi : " + h1.substring(0, 24) + "...");
        System.out.println("yuni : " + h2.substring(0, 24) + "...  sama? " + h1.equals(h2));
        System.out.println("login benar : " + verify("password123", h1));
        System.out.println("login salah : " + verify("password124", h1));

        System.out.println("--- 3. Biaya nebak (attacker punya dump DB) ---");
        long t0 = System.nanoTime();
        for (int i = 0; i < 1000; i++) sha256("tebak" + i);
        long fastMs = (System.nanoTime() - t0) / 1_000_000;
        byte[] salt = newSalt();
        t0 = System.nanoTime();
        for (int i = 0; i < 5; i++) slowHash("tebak" + i, salt);
        long slowMs = (System.nanoTime() - t0) / 1_000_000;
        System.out.println("SHA-256   : 1000 tebakan = " + fastMs + " ms");
        System.out.println("slow hash : 5 tebakan    = " + slowMs + " ms  (per tebakan ~" + (slowMs / 5) + " ms)");

        System.out.println("--- 4. Secret dari env, bukan hardcoded ---");
        String secret = System.getenv("JWT_SECRET");
        if (secret == null || secret.length() < 32) {
            System.out.println("JWT_SECRET belum di-set / terlalu pendek -> app MENOLAK start (fail fast)");
        } else {
            System.out.println("JWT_SECRET ok, panjang " + secret.length() + " (nilainya nggak pernah dicetak)");
        }
    }
}
