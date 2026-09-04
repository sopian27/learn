import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalLeakDemo {
    private static final ThreadLocal<String> requestData = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        // Pool 1 thread -> gampang lihat thread yang sama dipakai ulang
        ExecutorService pool = Executors.newFixedThreadPool(1);

        // "Request A"
        pool.submit(() -> {
            requestData.set("data milik Request A");
            System.out.println(Thread.currentThread().getName() + " set: " + requestData.get());
            // LUPA requestData.remove() -- ini bug-nya
        });

        Thread.sleep(200);

        // "Request B" -- dilayani thread pool yang SAMA (karena pool cuma 1 thread)
        pool.submit(() -> {
            String leaked = requestData.get();
            System.out.println(Thread.currentThread().getName() + " baca di awal Request B: " + leaked);
            // Kalau output-nya "data milik Request A", itu BUKTI kebocoran --
            // Request B belum pernah memanggil requestData.set() sama sekali!
        });

        Thread.sleep(200);
        pool.shutdown();
    }
}