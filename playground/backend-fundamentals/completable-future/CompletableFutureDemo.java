import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Demo Lesson 2.3: checkout manggil 3 layanan. Sekuensial vs paralel, error handling, timeout.
public class CompletableFutureDemo {

    static final ExecutorService IO = Executors.newFixedThreadPool(8); // pool I/O sendiri, bukan commonPool

    public static void main(String[] args) {
        // 1a: sekuensial, 3 x 200 ms.
        long t = System.nanoTime();
        String seq = call("promo", 200) + "," + call("ongkir", 200) + "," + call("fraud", 200);
        System.out.println("1a sekuensial: " + ms(t) + " ms -> " + seq + " (harapan ~600)");

        // 1b: paralel, gabungkan dengan thenCombine. Waktu = layanan paling lambat.
        t = System.nanoTime();
        var promo = CompletableFuture.supplyAsync(() -> call("promo", 200), IO);
        var ongkir = CompletableFuture.supplyAsync(() -> call("ongkir", 200), IO);
        var fraud = CompletableFuture.supplyAsync(() -> call("fraud", 200), IO);
        String par = promo.thenCombine(ongkir, (p, o) -> p + "," + o)
                .thenCombine(fraud, (po, f) -> po + "," + f).join();
        System.out.println("1b paralel   : " + ms(t) + " ms -> " + par + " (harapan ~200)");

        // 2a: exception dibungkus CompletionException; join() melempar bungkusannya, bukan aslinya.
        try {
            CompletableFuture.supplyAsync(() -> fail("fraud"), IO).join();
        } catch (CompletionException e) {
            System.out.println("2a join melempar CompletionException, penyebab asli: " + e.getCause());
        }

        // 2b: exceptionally = fallback. Tanpa ini satu layanan gagal menjatuhkan seluruh rantai.
        String safe = CompletableFuture.supplyAsync(() -> fail("promo"), IO)
                .exceptionally(ex -> "promo=0 (fallback)").join();
        System.out.println("2b fallback  : " + safe);

        // 2c: tanpa join/get/handler, error hilang diam-diam (tidak ada log, tidak ada stack trace).
        CompletableFuture<String> silent = CompletableFuture.supplyAsync(() -> fail("ongkir"), IO);
        sleepQuietly(100);
        System.out.println("2c future gagal tapi tidak ada yang tahu: isCompletedExceptionally=" + silent.isCompletedExceptionally());

        // 3: layanan hilir macet 3 detik. orTimeout memotong jadi 300 ms lalu fallback.
        t = System.nanoTime();
        String slow = CompletableFuture.supplyAsync(() -> call("fraud-lambat", 3000), IO)
                .orTimeout(300, TimeUnit.MILLISECONDS)
                .exceptionally(ex -> "fraud=skip (timeout)").join();
        System.out.println("3  timeout   : " + ms(t) + " ms -> " + slow + " (harapan ~300)");

        IO.shutdownNow();
    }

    static String call(String name, long ms) {
        sleepQuietly(ms);
        return name + "=ok";
    }

    static String fail(String name) {
        throw new IllegalStateException(name + " down");
    }

    static long ms(long startNanos) {
        return (System.nanoTime() - startNanos) / 1_000_000;
    }

    static void sleepQuietly(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
