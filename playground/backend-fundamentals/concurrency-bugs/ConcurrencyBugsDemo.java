import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

// Demo Lesson 2.5: deadlock, deteksinya (seperti baca thread dump), livelock, dan fix lock ordering.
public class ConcurrencyBugsDemo {

    static final ReentrantLock accountA = new ReentrantLock();
    static final ReentrantLock accountB = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        // 1: deadlock. Transfer A->B ambil A lalu B; transfer B->A ambil B lalu A. Urutan terbalik = saling tunggu.
        Thread t1 = daemon("transfer-A-ke-B", () -> transfer(accountA, accountB));
        Thread t2 = daemon("transfer-B-ke-A", () -> transfer(accountB, accountA));
        t1.start();
        t2.start();
        t1.join(1000);
        System.out.println("1  thread masih hidup setelah 1 detik: " + t1.isAlive() + ", " + t2.isAlive() + " (harapan true, true)");

        // 2: deteksi seperti thread dump: siapa nunggu lock milik siapa.
        ThreadMXBean mx = ManagementFactory.getThreadMXBean();
        long[] ids = mx.findDeadlockedThreads();
        System.out.println("2  deadlock terdeteksi: " + (ids == null ? 0 : ids.length) + " thread");
        if (ids != null) {
            for (ThreadInfo ti : mx.getThreadInfo(ids)) {
                System.out.println("   " + ti.getThreadName() + " state=" + ti.getThreadState()
                        + " nunggu lock milik '" + ti.getLockOwnerName() + "'");
            }
        }

        // 3: fix = urutan lock konsisten. Dua-duanya ambil accountA dulu baru accountB.
        // Pakai lock baru supaya tidak tabrakan dengan thread macet di atas.
        ReentrantLock a = new ReentrantLock(), b = new ReentrantLock();
        Thread f1 = daemon("fix-1", () -> transfer(a, b));
        Thread f2 = daemon("fix-2", () -> transfer(a, b)); // urutan sama, bukan terbalik
        f1.start();
        f2.start();
        f1.join(1000);
        f2.join(1000);
        System.out.println("3  urutan konsisten selesai: " + (!f1.isAlive() && !f2.isAlive()) + " (harapan true)");

        // 4: livelock. Dua thread sama-sama "sopan": gagal ambil lock kedua -> lepas lock pertama -> coba lagi.
        // Barrier bikin keduanya selalu serempak, jadi tidak pernah ada yang maju. CPU sibuk, progres nol.
        System.out.println("4  livelock: " + livelock(1000));
    }

    static void transfer(ReentrantLock first, ReentrantLock second) {
        first.lock();
        try {
            sleep(100); // beri waktu thread lain ambil lock pertamanya
            second.lock();
            try { /* pindah saldo */ } finally { second.unlock(); }
        } finally {
            first.unlock();
        }
    }

    // Kembalikan hasil: berapa putaran sibuk tanpa ada yang berhasil.
    static String livelock(int maxRounds) throws Exception {
        ReentrantLock x = new ReentrantLock(), y = new ReentrantLock();
        CyclicBarrier sync = new CyclicBarrier(2);
        int[] done = {0};
        Runnable r1 = () -> polite(x, y, sync, done, maxRounds);
        Runnable r2 = () -> polite(y, x, sync, done, maxRounds);
        Thread p1 = daemon("polite-1", r1), p2 = daemon("polite-2", r2);
        p1.start();
        p2.start();
        p1.join(3000);
        p2.join(3000);
        return "selesai=" + done[0] + " dari 2 thread setelah " + maxRounds + " putaran (harapan 0)";
    }

    static void polite(ReentrantLock mine, ReentrantLock other, CyclicBarrier sync, int[] done, int maxRounds) {
        try {
            for (int i = 0; i < maxRounds; i++) {
                mine.lock();
                sync.await(); // keduanya sudah pegang lock masing-masing
                boolean got = other.tryLock();
                sync.await(); // tunggu lawan selesai coba, baru sama-sama lepas
                if (got) { // tidak pernah terjadi: lawan juga pegang lock-nya
                    other.unlock();
                    synchronized (done) { done[0]++; }
                    mine.unlock();
                    return;
                }
                mine.unlock(); // "silakan duluan"
                sync.await();
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    static Thread daemon(String name, Runnable r) {
        Thread t = new Thread(r, name);
        t.setDaemon(true); // supaya JVM bisa keluar walau thread macet
        return t;
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
