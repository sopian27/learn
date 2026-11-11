import java.util.*;

// Lesson 9.1: kapan cache menolong, kapan cuma nambah kompleksitas.
public class CacheNeedDemo {
    static int dbCalls;
    static int version = 1; // "data di DB", bisa berubah

    static int loadFromDb(int id) { // DB lambat ~5 ms
        dbCalls++;
        try { Thread.sleep(5); } catch (InterruptedException e) { throw new RuntimeException(e); }
        return version;
    }

    // Cache-aside paling sederhana: cek cache, miss -> DB -> isi cache.
    static int[] run(String name, int reads, java.util.function.IntUnaryOperator keyOf,
                     boolean useCache, boolean dataChanges) {
        Map<Integer, Integer> cache = new HashMap<>();
        dbCalls = 0; version = 1;
        int hits = 0, stale = 0;
        long t0 = System.nanoTime();
        for (int i = 0; i < reads; i++) {
            if (dataChanges) version++; // data berubah tiap baca
            int key = keyOf.applyAsInt(i);
            Integer v = useCache ? cache.get(key) : null;
            if (v != null) { hits++; if (v != version) stale++; }
            else { v = loadFromDb(key); if (useCache) cache.put(key, v); }
        }
        long ms = (System.nanoTime() - t0) / 1_000_000;
        System.out.printf("%-34s cache=%-5s waktu=%4d ms  DB=%3d  hit=%3d  stale=%3d  entri=%d%n",
                name, useCache, ms, dbCalls, hits, stale, cache.size());
        return new int[]{hits, stale};
    }

    public static void main(String[] args) {
        int n = 200;
        System.out.println("-- A. Data hot (10 key, 200 baca), data stabil: cache layak --");
        run("A tanpa cache", n, i -> i % 10, false, false);
        run("A dengan cache", n, i -> i % 10, true, false);

        System.out.println("\n-- B. Tiap baca key beda: cache cuma nambah memori, hit = 0 --");
        run("B tanpa cache", n, i -> i, false, false);
        run("B dengan cache", n, i -> i, true, false);

        System.out.println("\n-- C. Data berubah tiap baca: cache menyajikan data basi --");
        run("C tanpa cache", n, i -> i % 10, false, true);
        run("C dengan cache", n, i -> i % 10, true, true);
    }
}
