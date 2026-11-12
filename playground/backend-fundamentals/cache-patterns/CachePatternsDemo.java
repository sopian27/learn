import java.util.*;

// Tiga pola cache untuk satu operasi: update harga produk, lalu baca lagi.
// "DB" dan "cache" sama-sama Map biar fokus ke urutan operasinya.
public class CachePatternsDemo {
    static Map<String, Integer> db = new HashMap<>();
    static Map<String, Integer> cache = new HashMap<>();
    static Deque<String> writeQueue = new ArrayDeque<>(); // buat write-behind

    // 1. Cache-aside: aplikasi yang ngatur. Tulis ke DB, buang entri cache.
    static int readAside(String k) {
        Integer v = cache.get(k);
        if (v != null) return v;           // hit
        v = db.get(k);                     // miss -> baca DB -> isi cache
        cache.put(k, v);
        return v;
    }
    static void writeAside(String k, int v) {
        db.put(k, v);
        cache.remove(k);                   // invalidate, bukan update
    }

    // 2. Write-through: tulis ke cache dan DB sekaligus, baru sukses.
    static void writeThrough(String k, int v) {
        db.put(k, v);
        cache.put(k, v);
    }

    // 3. Write-behind: tulis ke cache dulu, DB menyusul belakangan (async).
    static void writeBehind(String k, int v) {
        cache.put(k, v);
        writeQueue.add(k);
    }
    static void flush() {
        while (!writeQueue.isEmpty()) {
            String k = writeQueue.poll();
            db.put(k, cache.get(k));
        }
    }

    static void reset() {
        db.clear(); cache.clear(); writeQueue.clear();
        db.put("sku-1", 100);
    }

    public static void main(String[] args) {
        reset();
        readAside("sku-1");                // cache terisi 100
        writeAside("sku-1", 150);
        System.out.println("cache-aside   : baca setelah update = " + readAside("sku-1")
                + " (DB=" + db.get("sku-1") + ")");

        reset();
        writeThrough("sku-1", 150);
        System.out.println("write-through : cache=" + cache.get("sku-1")
                + ", DB=" + db.get("sku-1") + " (konsisten, tulis lebih lambat)");

        reset();
        writeBehind("sku-1", 150);
        System.out.println("write-behind  : cache=" + cache.get("sku-1")
                + ", DB=" + db.get("sku-1") + " (DB ketinggalan sebelum flush)");
        // kalau proses mati di sini, update 150 hilang selamanya
        writeQueue.clear();
        System.out.println("write-behind  : setelah crash sebelum flush, DB=" + db.get("sku-1")
                + " (update hilang)");

        reset();
        writeBehind("sku-1", 150);
        flush();
        System.out.println("write-behind  : setelah flush, DB=" + db.get("sku-1"));
    }
}
