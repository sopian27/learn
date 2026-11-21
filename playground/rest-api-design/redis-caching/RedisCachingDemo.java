import java.util.HashMap;
import java.util.Map;

// Demo: cache-aside + TTL + invalidasi. "Redis" disimulasikan Map in-memory dengan expiry.
public class RedisCachingDemo {

    record Entry(String value, long expiresAtMs) {}

    static final Map<String, Entry> redis = new HashMap<>();   // pengganti Redis
    static final Map<Integer, String> db = new HashMap<>();    // pengganti Postgres
    static long now = 0;                                       // jam palsu (ms)
    static int dbHits = 0;

    static String queryDb(int id) { dbHits++; return db.get(id); }   // query "mahal"

    // Cache-aside: cek cache dulu, miss -> DB -> isi cache dengan TTL.
    static String getOrder(int id, long ttlMs) {
        String key = "order:" + id;
        Entry e = redis.get(key);
        if (e != null && e.expiresAtMs() > now) return e.value() + " [HIT]";
        String v = queryDb(id);
        redis.put(key, new Entry(v, now + ttlMs));
        return v + " [MISS]";
    }

    // Tulis: update DB lalu hapus key cache (invalidasi).
    static void updateOrder(int id, String status, boolean invalidate) {
        db.put(id, "order " + id + " status=" + status);
        if (invalidate) redis.remove("order:" + id);
    }

    public static void main(String[] args) {
        db.put(42, "order 42 status=PAID");

        System.out.println("== 1. Cache-aside: 3x baca, DB cuma disentuh sekali");
        for (int i = 0; i < 3; i++) System.out.println(getOrder(42, 60_000));
        System.out.println("dbHits = " + dbHits);

        System.out.println("\n== 2. Update TANPA invalidasi: data basi sampai TTL habis");
        updateOrder(42, "SHIPPED", false);
        System.out.println(getOrder(42, 60_000) + "   <- basi, padahal DB sudah SHIPPED");

        System.out.println("\n== 3. Update DENGAN invalidasi: baca berikutnya langsung segar");
        updateOrder(42, "DELIVERED", true);
        System.out.println(getOrder(42, 60_000));
        System.out.println(getOrder(42, 60_000));

        System.out.println("\n== 4. TTL jadi jaring pengaman: invalidasi terlewat, tetap sembuh setelah TTL");
        updateOrder(42, "RETURNED", false);
        now += 61_000;
        System.out.println(getOrder(42, 60_000) + "   <- TTL habis, ambil ulang dari DB");
        System.out.println("dbHits = " + dbHits);
    }
}
