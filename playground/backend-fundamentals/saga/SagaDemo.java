import java.util.ArrayList;
import java.util.List;

// Simulasi mini Saga: tiap service punya "DB" sendiri (tidak ada satu transaksi lintas service).
// Tanpa Spring/DB/broker. Jalankan: java SagaDemo.java
public class SagaDemo {

    // "DB" milik tiap service
    static int saldoUser = 100;
    static int stokGudang = 0;          // sengaja habis supaya langkah 3 gagal
    static String statusOrder = "-";

    static class Langkah {
        final String nama; final Runnable aksi; final Runnable kompensasi;
        Langkah(String nama, Runnable aksi, Runnable kompensasi) {
            this.nama = nama; this.aksi = aksi; this.kompensasi = kompensasi;
        }
    }

    static void reset() { saldoUser = 100; stokGudang = 0; statusOrder = "-"; }

    static List<Langkah> langkah() {
        return List.of(
            new Langkah("buat order", () -> statusOrder = "PENDING", () -> statusOrder = "BATAL"),
            new Langkah("tagih pembayaran", () -> saldoUser -= 40, () -> saldoUser += 40), // refund
            new Langkah("kurangi stok", () -> {
                if (stokGudang <= 0) throw new IllegalStateException("stok habis");
                stokGudang--;
            }, () -> stokGudang++)
        );
    }

    // Tanpa kompensasi: langkah gagal, langkah sebelumnya dibiarkan.
    static void tanpaSaga() {
        reset();
        try { for (Langkah l : langkah()) l.aksi.run(); }
        catch (RuntimeException e) { System.out.println("  gagal: " + e.getMessage()); }
        System.out.printf("tanpa saga : saldo=%d (harusnya 100) order=%s %s%n",
                saldoUser, statusOrder, saldoUser == 100 ? "BENAR" : "SALAH (uang user tertahan)");
    }

    // Saga: gagal -> jalankan kompensasi langkah yang sudah sukses, urutan terbalik.
    static void denganSaga() {
        reset();
        List<Langkah> selesai = new ArrayList<>();
        try {
            for (Langkah l : langkah()) { l.aksi.run(); selesai.add(l); }
        } catch (RuntimeException e) {
            System.out.println("  gagal: " + e.getMessage() + " -> kompensasi");
            for (int i = selesai.size() - 1; i >= 0; i--) {
                System.out.println("  batalkan: " + selesai.get(i).nama);
                selesai.get(i).kompensasi.run();
            }
        }
        System.out.printf("dengan saga: saldo=%d (harusnya 100) order=%s %s%n",
                saldoUser, statusOrder, saldoUser == 100 ? "BENAR" : "SALAH");
    }

    public static void main(String[] args) {
        tanpaSaga();
        denganSaga();
    }
}
