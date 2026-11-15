import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// Jalankan: java CapstoneImplementationDemo.java
// Satu langkah implementasi bertahap: pilar "locking" di Booking Slot, dibuktiin pakai test konkuren.
// Map di sini mewakili tabel slot_booking; putIfAbsent mewakili UNIQUE(court_id, start_at) di database.
public class CapstoneImplementationDemo {

    static final Map<String, String> table = new ConcurrentHashMap<>();

    // Tahap 1 (naif): cek dulu, baru tulis. Ada celah antara "cek" dan "tulis".
    static boolean bookNaive(String slot, String user) {
        if (table.containsKey(slot)) return false;
        pause();                       // simulasi latency DB/network antar dua langkah
        table.put(slot, user);         // yang kalah balapan menimpa, keduanya merasa "berhasil"
        return true;
    }

    // Tahap 2: constraint sebagai garis pertahanan terakhir. Atomik: hanya satu yang menang.
    static boolean bookWithConstraint(String slot, String user) {
        pause();
        return table.putIfAbsent(slot, user) == null;
    }

    static void pause() { try { Thread.sleep(5); } catch (InterruptedException e) { Thread.currentThread().interrupt(); } }

    // Bukti = test konkuren: 20 user rebutan 1 slot, berapa yang "berhasil"?
    static int race(java.util.function.BiPredicate<String, String> booker) throws Exception {
        table.clear();
        var pool = Executors.newFixedThreadPool(20);
        var start = new CountDownLatch(1);
        var winners = new AtomicInteger();
        for (int i = 0; i < 20; i++) {
            String user = "user-" + i;
            pool.submit(() -> {
                start.await();
                if (booker.test("court-1@19:00", user)) winners.incrementAndGet();
                return null;
            });
        }
        start.countDown();
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        return winners.get();
    }

    public static void main(String[] args) throws Exception {
        int naif = race(CapstoneImplementationDemo::bookNaive);
        int aman = race(CapstoneImplementationDemo::bookWithConstraint);

        System.out.println("Tahap 1 naif       : " + naif + " user merasa berhasil booking (harusnya 1)");
        System.out.println("Tahap 2 constraint : " + aman + " user berhasil booking");

        System.out.println("\nCatatan keputusan (satu baris per tahap, ikut commit yang sama):");
        System.out.println("  [locking] constraint unik = pertahanan terakhir; lock di service = optimasi, bukan jaminan");
        System.out.println("  bukti     : test konkuren 20 thread -> tepat 1 pemenang");
        if (aman != 1) throw new AssertionError("constraint harusnya menyisakan tepat 1 pemenang");
    }
}
