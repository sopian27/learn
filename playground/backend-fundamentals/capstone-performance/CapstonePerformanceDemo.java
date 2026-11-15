import java.util.*;
import java.util.stream.*;

// Jalankan: java CapstonePerformanceDemo.java
// Performance pass: ukur dulu, perbaiki bottleneck terbesar, ukur lagi. Angka before/after, bukan tebakan.
// Latensi DB disimulasikan 3ms per query; endpoint "ketersediaan semua lapangan" dipanggil 60 kali.
public class CapstonePerformanceDemo {

    static final int COURTS = 20;
    static int queryCount;

    static void dbRoundTrip() {
        queryCount++;
        try { Thread.sleep(3); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // Before: N+1. Satu query ambil lapangan, lalu satu query per lapangan ambil slot terisi.
    static int availabilityNaive() {
        dbRoundTrip();
        int free = 0;
        for (int c = 0; c < COURTS; c++) { dbRoundTrip(); free += 10; }
        return free;
    }

    // After: satu query JOIN/GROUP BY untuk semua lapangan.
    static int availabilityBatch() {
        dbRoundTrip();
        return COURTS * 10;
    }

    static long[] measure(java.util.function.IntSupplier endpoint) {
        queryCount = 0;
        long[] ms = new long[60];
        for (int i = 0; i < ms.length; i++) {
            long t = System.nanoTime();
            endpoint.getAsInt();
            ms[i] = (System.nanoTime() - t) / 1_000_000;
        }
        Arrays.sort(ms);
        return new long[]{ ms[(int) Math.ceil(ms.length * 0.95) - 1], queryCount / ms.length };
    }

    public static void main(String[] args) {
        long[] before = measure(CapstonePerformanceDemo::availabilityNaive);
        long[] after = measure(CapstonePerformanceDemo::availabilityBatch);

        System.out.println("Sebelum: p95 = " + before[0] + " ms, query per request = " + before[1]);
        System.out.println("Sesudah: p95 = " + after[0] + " ms, query per request = " + after[1]);

        System.out.println("\nCatatan performance pass (ikut commit yang sama):");
        System.out.println("  [availability] bottleneck = N+1 query (21 query/request); fix = 1 query agregasi");
        System.out.println("  bukti          : p95 " + before[0] + " ms -> " + after[0] + " ms, load test yang sama");
        if (after[0] >= before[0] || after[1] != 1) throw new AssertionError("fix harusnya lebih cepat dan 1 query");
    }
}
