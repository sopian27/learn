import java.io.File;
import java.lang.management.*;
import java.util.*;
import com.sun.management.HotSpotDiagnosticMXBean;

// Satu proses, dua masalah: heap penuh Order (leak) dan dua thread deadlock.
// Demo ambil heap dump & thread dump dari dalam proses, lalu nunggu supaya lu bisa coba jcmd dari terminal lain.
public class DumpDemo {
    record Order(long id, byte[] payload) {}

    static final List<Order> ORDER_CACHE = new ArrayList<>(); // leak: tidak pernah dibersihkan
    static final Object LOCK_A = new Object(), LOCK_B = new Object();

    static void deadlock() {
        Thread t1 = new Thread(() -> { synchronized (LOCK_A) { pause(); synchronized (LOCK_B) {} } }, "payment-worker-1");
        Thread t2 = new Thread(() -> { synchronized (LOCK_B) { pause(); synchronized (LOCK_A) {} } }, "payment-worker-2");
        t1.setDaemon(true); t2.setDaemon(true);
        t1.start(); t2.start();
    }

    static void pause() { try { Thread.sleep(200); } catch (InterruptedException ignored) {} }

    public static void main(String[] args) throws Exception {
        long pid = ProcessHandle.current().pid();
        for (long i = 0; i < 50_000; i++) ORDER_CACHE.add(new Order(i, new byte[1024]));
        deadlock();
        Thread.sleep(1000);

        // --- thread dump: cari thread BLOCKED dan siapa yang pegang lock-nya ---
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        long[] deadlocked = threads.findDeadlockedThreads();
        System.out.println("== thread dump (deadlock) ==");
        if (deadlocked != null) {
            for (ThreadInfo t : threads.getThreadInfo(deadlocked)) {
                System.out.printf("%s state=%s nunggu=%s dipegang oleh=%s%n",
                        t.getThreadName(), t.getThreadState(), t.getLockName(), t.getLockOwnerName());
            }
        }

        // --- heap dump: file .hprof, buka di VisualVM / Eclipse MAT, lihat dominator Order[] ---
        File hprof = new File(System.getProperty("java.io.tmpdir"), "dump-demo-" + pid + ".hprof");
        hprof.delete();
        ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class).dumpHeap(hprof.getPath(), true); // true = hanya live object
        System.out.printf("%n== heap dump ==%n%s (%d MB)%n", hprof, hprof.length() / (1024 * 1024));

        System.out.printf("%n== coba dari terminal lain (PID %d), proses nunggu 60 detik ==%n", pid);
        System.out.println("jcmd " + pid + " Thread.print                  # thread dump, ada tulisan 'Found one Java-level deadlock'");
        System.out.println("jcmd " + pid + " GC.class_histogram | head -15 # siapa pakai heap, tanpa bikin file");
        System.out.println("jcmd " + pid + " GC.heap_dump C:\\temp\\x.hprof  # heap dump penuh");
        Thread.sleep(60_000);
        hprof.delete();
    }
}
