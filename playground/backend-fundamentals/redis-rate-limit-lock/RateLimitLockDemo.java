import java.util.*;

// Model mini perilaku Redis: INCR + EXPIRE (rate limit) dan SET NX PX (lock). Bukan Redis asli.
// Waktu dipalsukan lewat "now" (detik) biar deterministik.
public class RateLimitLockDemo {
    static long now = 0;
    record Entry(String value, long expiresAt) {}
    static final Map<String, Entry> store = new HashMap<>();

    static Entry live(String k) {
        Entry e = store.get(k);
        if (e != null && now >= e.expiresAt()) { store.remove(k); return null; }
        return e;
    }
    // INCR: key baru dapat TTL window; key lama hanya naik.
    static int incr(String k, long windowTtl) {
        Entry e = live(k);
        int n = e == null ? 1 : Integer.parseInt(e.value()) + 1;
        store.put(k, new Entry(String.valueOf(n), e == null ? now + windowTtl : e.expiresAt()));
        return n;
    }
    // SET key value NX EX ttl
    static boolean setNx(String k, String v, long ttl) {
        if (live(k) != null) return false;
        store.put(k, new Entry(v, now + ttl));
        return true;
    }
    static void del(String k) { store.remove(k); }
    static boolean delIfOwner(String k, String owner) { // di Redis asli: Lua script biar atomik
        Entry e = live(k);
        if (e != null && e.value().equals(owner)) { store.remove(k); return true; }
        return false;
    }

    static boolean allow(String user) { return incr("rate:" + user + ":" + (now / 10), 10) <= 3; } // key per window 10 detik (now/10), batas 3

    public static void main(String[] args) {
        // 1) Rate limit fixed window
        now = 0;
        int ok = 0;
        for (int i = 0; i < 5; i++) if (allow("budi")) ok++;
        System.out.println("Rate limit : 5 request di detik 0, lolos " + ok + " (batas 3)");

        // Kelemahan fixed window: burst di perbatasan window
        store.clear();
        int burst = 0;
        now = 9;  for (int i = 0; i < 3; i++) if (allow("budi")) burst++;
        now = 10; for (int i = 0; i < 3; i++) if (allow("budi")) burst++;
        System.out.println("Perbatasan : 3 di detik 9 + 3 di detik 10, lolos " + burst + " dalam 2 detik (batas 3/10 detik)");

        // 2) Lock tanpa owner check: lock A kadaluarsa, B ambil, A hapus lock milik B
        store.clear(); now = 0;
        setNx("lock:job", "A", 5);
        System.out.println("Lock       : B coba ambil saat A pegang -> " + setNx("lock:job", "B", 5));
        now = 6; // A masih bekerja, TTL habis
        System.out.println("             TTL A habis, B ambil -> " + setNx("lock:job", "B", 5));
        del("lock:job"); // A selesai, DEL buta
        System.out.println("             A DEL buta, C ambil -> " + setNx("lock:job", "C", 5) + " (B dan C jalan bersamaan!)");

        // 3) Dengan owner token
        store.clear(); now = 0;
        setNx("lock:job", "A", 5);
        now = 6;
        setNx("lock:job", "B", 5);
        System.out.println("Owner check: A coba lepas lock milik B -> " + delIfOwner("lock:job", "A") + " (ditolak, aman)");
    }
}
