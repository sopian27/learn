import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

// Simulasi mini lost update + dua obat: optimistic (@Version) dan pessimistic (FOR UPDATE).
// Tanpa Spring/DB: "baris" stok disimulasikan di memori. Jalankan: java LockingDemo.java
public class LockingDemo {

    static final int STOK_AWAL = 100;
    static final int REQUEST = 50;   // 50 pembeli, masing-masing beli 1 -> stok akhir harusnya 50

    // Satu "baris" tabel stok.
    static class Row {
        int stok = STOK_AWAL;
        int version = 0;
        final ReentrantLock rowLock = new ReentrantLock(); // pengganti SELECT ... FOR UPDATE
        synchronized int[] read() { return new int[] { stok, version }; }
        // UPDATE stok SET stok=?, version=version+1 WHERE version=?  -> true kalau 1 baris kena
        synchronized boolean updateIfVersion(int stokBaru, int versionLama) {
            if (version != versionLama) return false;
            stok = stokBaru; version++;
            return true;
        }
        synchronized void write(int stokBaru) { stok = stokBaru; }
    }

    static void pikir() { try { Thread.sleep(2); } catch (InterruptedException ignored) { } } // jeda "logika bisnis"

    // 1. Tanpa locking: baca -> hitung -> tulis. Dua thread bisa baca nilai yang sama.
    static void beliTanpaLock(Row r) {
        int stok = r.read()[0];
        pikir();
        r.write(stok - 1);
    }

    // 2. Optimistic: tulis hanya kalau version belum berubah; kalau gagal, ulang dari baca.
    static void beliOptimistic(Row r, AtomicInteger retry) {
        while (true) {
            int[] s = r.read();
            pikir();
            if (r.updateIfVersion(s[0] - 1, s[1])) return;
            retry.incrementAndGet();   // "OptimisticLockException" -> coba lagi
        }
    }

    // 3. Pessimistic: kunci baris dulu; yang lain antre sampai selesai.
    static void beliPessimistic(Row r) {
        r.rowLock.lock();
        try {
            int stok = r.read()[0];
            pikir();
            r.write(stok - 1);
        } finally { r.rowLock.unlock(); }
    }

    static void jalankan(String judul, Row r, Runnable pembeli, AtomicInteger retry) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(REQUEST);
        CountDownLatch start = new CountDownLatch(1);
        long t0 = System.nanoTime();
        for (int i = 0; i < REQUEST; i++) pool.submit(() -> { start.await(); pembeli.run(); return null; });
        start.countDown();
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
        long ms = (System.nanoTime() - t0) / 1_000_000;
        int harusnya = STOK_AWAL - REQUEST;
        System.out.printf("%-14s stok akhir=%3d (harusnya %d) %-8s retry=%-3d %dms%n",
                judul, r.stok, harusnya, r.stok == harusnya ? "BENAR" : "SALAH", retry.get(), ms);
    }

    public static void main(String[] args) throws Exception {
        Row a = new Row();
        jalankan("tanpa lock", a, () -> beliTanpaLock(a), new AtomicInteger());

        Row b = new Row();
        AtomicInteger retry = new AtomicInteger();
        jalankan("optimistic", b, () -> beliOptimistic(b, retry), retry);

        Row c = new Row();
        jalankan("pessimistic", c, () -> beliPessimistic(c), new AtomicInteger());
    }
}
