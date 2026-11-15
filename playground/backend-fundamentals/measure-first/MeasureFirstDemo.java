import java.util.*;

/**
 * Demo Lesson 14.1: optimasi tanpa ukur vs ukur dulu.
 * Endpoint "laporan order" punya 3 tahap. Tim nebak yang lambat itu formatting,
 * padahal profiling sederhana (timer per tahap) nunjukin query yang makan waktu.
 * Run: java MeasureFirstDemo.java
 */
public class MeasureFirstDemo {

    static void slowQuery() { sleep(120); }           // simulasi query tanpa index (I/O)

    static String formatSlow(List<Integer> rows) {    // versi awal: string concat
        String s = "";
        for (int r : rows) s += r + ",";
        return s;
    }

    static String formatFast(List<Integer> rows) {    // hasil "optimasi" tebakan: StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int r : rows) sb.append(r).append(',');
        return sb.toString();
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    /** Satu request = query -> format -> serialize. Return ms per tahap. */
    static long[] handle(boolean optimizedFormat, List<Integer> rows) {
        long t0 = System.nanoTime();
        slowQuery();
        long t1 = System.nanoTime();
        String out = optimizedFormat ? formatFast(rows) : formatSlow(rows);
        long t2 = System.nanoTime();
        sleep(5);                                      // simulasi serialize/network
        long t3 = System.nanoTime();
        if (out.isEmpty()) throw new IllegalStateException();
        return new long[]{(t1 - t0) / 1_000_000, (t2 - t1) / 1_000_000, (t3 - t2) / 1_000_000};
    }

    static double p(List<Long> sorted, double pct) {
        return sorted.get((int) Math.min(sorted.size() - 1, Math.ceil(pct * sorted.size()) - 1));
    }

    static void bench(String label, boolean optimized, List<Integer> rows) {
        for (int i = 0; i < 5; i++) handle(optimized, rows); // warmup
        List<Long> totals = new ArrayList<>();
        long[] sum = new long[3];
        for (int i = 0; i < 30; i++) {
            long[] s = handle(optimized, rows);
            for (int k = 0; k < 3; k++) sum[k] += s[k];
            totals.add(s[0] + s[1] + s[2]);
        }
        Collections.sort(totals);
        System.out.printf("%-22s query=%3dms format=%3dms serialize=%2dms | p50=%.0fms p95=%.0fms%n",
                label, sum[0] / 30, sum[1] / 30, sum[2] / 30, p(totals, .5), p(totals, .95));
    }

    public static void main(String[] args) {
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < 2_000; i++) rows.add(i);

        System.out.println("== Before vs after optimasi TEBAKAN (format) ==");
        bench("sebelum (concat)", false, rows);
        bench("sesudah (StringBuilder)", true, rows);
        System.out.println("-> Tahap query dominan. Optimasi format salah sasaran: gain kecil di total.");
        System.out.println("-> Amdahl: percepat bagian 10% jadi 0 ms pun cuma untung <=10% total.");
    }
}
