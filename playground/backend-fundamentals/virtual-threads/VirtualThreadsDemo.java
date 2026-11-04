import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

// Demo Lesson 2.4: 2000 request I/O-bound (tiap 200 ms nunggu). Platform thread pool vs virtual thread. Butuh Java 21+.
public class VirtualThreadsDemo {

    public static void main(String[] args) throws Exception {
        // 1a: pool 100 platform thread. Cuma 100 request jalan bareng, sisanya antre: 2000/100 x 200 ms ~ 4000 ms.
        long t = System.nanoTime();
        try (ExecutorService pool = Executors.newFixedThreadPool(100)) {
            run(pool, 2000, 200);
        }
        System.out.println("1a platform pool(100): " + ms(t) + " ms (harapan ~4000)");

        // 1b: satu virtual thread per task. Nunggu I/O tidak menahan carrier thread: ~200 ms.
        t = System.nanoTime();
        try (ExecutorService vt = Executors.newVirtualThreadPerTaskExecutor()) {
            run(vt, 2000, 200);
        }
        System.out.println("1b virtual thread    : " + ms(t) + " ms (harapan ~200)");

        // 2: 100.000 task nunggu 1 detik bareng. Platform thread sebanyak ini tidak realistis; virtual thread murah.
        t = System.nanoTime();
        try (ExecutorService vt = Executors.newVirtualThreadPerTaskExecutor()) {
            run(vt, 100_000, 1000);
        }
        System.out.println("2  100.000 virtual   : " + ms(t) + " ms (harapan ~1000-2000)");

        // 3: virtual thread itu tetap Thread biasa untuk kode kita: nama kosong, daemon, isVirtual() true.
        Thread v = Thread.ofVirtual().start(() -> {});
        v.join();
        System.out.println("3  isVirtual=" + v.isVirtual() + ", isDaemon=" + v.isDaemon() + ", name='" + v.getName() + "'");
    }

    // Simulasi panggilan I/O blocking (DB/HTTP) sebanyak n kali.
    static void run(ExecutorService ex, int n, long sleepMs) {
        IntStream.range(0, n).forEach(i -> ex.submit(() -> {
            try { Thread.sleep(sleepMs); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }));
    }

    static long ms(long startNanos) {
        return (System.nanoTime() - startNanos) / 1_000_000;
    }
}
