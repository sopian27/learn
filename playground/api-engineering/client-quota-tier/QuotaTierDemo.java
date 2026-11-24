import java.util.HashMap;
import java.util.Map;

// Lesson 2.3: satu limit global vs quota per client per tier. Satu window, urutan request deterministik.
public class QuotaTierDemo {

    static final Map<String, Integer> TIER_LIMIT = Map.of("free", 5, "paid", 20);

    // Naif: satu counter untuk semua client. Client rakus menghabiskan jatah client lain.
    static int globalUsed = 0;
    static boolean global(String apiKey, String tier) {
        if (globalUsed >= 25) return false;
        globalUsed++;
        return true;
    }

    // Per client: counter per apiKey, limit diambil dari tier. Di produksi: key Redis rl:{apiKey} + Lua (lesson 2.2).
    static final Map<String, Integer> used = new HashMap<>();
    static boolean perClient(String apiKey, String tier) {
        int n = used.merge(apiKey, 0, Integer::sum);
        if (n >= TIER_LIMIT.get(tier)) return false;
        used.put(apiKey, n + 1);
        return true;
    }

    // client rakus (free) nembak 30x dulu, lalu client paid nembak 10x. Balikin {lolos free, lolos paid}.
    static int[] scenario(java.util.function.BiPredicate<String, String> limiter) {
        int free = 0, paid = 0;
        for (int i = 0; i < 30; i++) if (limiter.test("key-free", "free")) free++;
        for (int i = 0; i < 10; i++) if (limiter.test("key-paid", "paid")) paid++;
        return new int[]{free, paid};
    }

    public static void main(String[] args) {
        int[] g = scenario(QuotaTierDemo::global);
        int[] p = scenario(QuotaTierDemo::perClient);
        System.out.printf("global     : free lolos %d, paid lolos %d%n", g[0], g[1]);
        System.out.printf("per client : free lolos %d, paid lolos %d%n", p[0], p[1]);
        if (g[1] != 0 || p[0] != 5 || p[1] != 10) throw new AssertionError("hasil tidak sesuai ekspektasi");
    }
}
