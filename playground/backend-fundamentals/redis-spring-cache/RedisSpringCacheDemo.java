import java.util.*;
import java.util.function.Supplier;

// Model mini dari apa yang dilakukan @Cacheable / @CacheEvict, TTL, dan eviction LRU.
// Bukan Redis asli: waktu dipalsukan lewat variabel "now" biar deterministik.
public class RedisSpringCacheDemo {
    static long now = 0;
    static int dbCalls = 0;

    // Cache kecil: maksimal 2 entri (eviction LRU), tiap entri punya TTL.
    static class MiniCache {
        record Entry(Object value, long expiresAt) {}
        final int maxSize;
        final LinkedHashMap<String, Entry> map = new LinkedHashMap<>(16, 0.75f, true); // access-order = LRU
        MiniCache(int maxSize) { this.maxSize = maxSize; }

        Object get(String k) {
            Entry e = map.get(k);
            if (e == null) return null;
            if (now >= e.expiresAt()) { map.remove(k); return null; } // TTL habis
            return e.value();
        }
        void put(String k, Object v, long ttl) {
            map.put(k, new Entry(v, now + ttl));
            if (map.size() > maxSize) {
                String eldest = map.keySet().iterator().next(); // paling lama nggak dipakai
                map.remove(eldest);
                System.out.println("  [evict] " + eldest + " dibuang karena cache penuh");
            }
        }
        void evict(String k) { map.remove(k); }
    }

    static MiniCache cache = new MiniCache(2);

    // Ini yang sebenarnya dikerjakan proxy @Cacheable: cek cache dulu, miss baru panggil method asli.
    static Object cacheable(String key, Supplier<Object> method) {
        Object v = cache.get(key);
        if (v != null) return v;
        v = method.get();
        cache.put(key, v, 10); // TTL 10 "detik"
        return v;
    }

    static Object loadPrice(String sku) { dbCalls++; return 100; }

    public static void main(String[] args) {
        cacheable("price:1", () -> loadPrice("1"));
        cacheable("price:1", () -> loadPrice("1"));
        System.out.println("@Cacheable : 2x baca, DB dipanggil " + dbCalls + "x (kedua dari cache)");

        now = 11;
        cacheable("price:1", () -> loadPrice("1"));
        System.out.println("TTL        : lewat 10 detik, DB dipanggil " + dbCalls + "x (entri kadaluarsa, ambil ulang)");

        cache.evict("price:1"); // @CacheEvict setelah update
        cacheable("price:1", () -> loadPrice("1"));
        System.out.println("@CacheEvict: setelah evict, DB dipanggil " + dbCalls + "x (miss lagi)");

        cacheable("price:2", () -> loadPrice("2"));
        cacheable("price:3", () -> loadPrice("3")); // melebihi kapasitas 2 -> eviction
        System.out.println("Eviction   : isi cache sekarang = " + cache.map.keySet());
    }
}
