import java.util.*;
import java.util.regex.Pattern;

/**
 * Demo Lesson 14.2: sampling profiler mini. Sampler thread ngambil stack trace
 * thread kerja tiap 5 ms; frame yang sering muncul = hot path.
 * Bug: Pattern.compile dipanggil tiap baris. Fix: precompile (static final).
 * Run: java CpuProfilingDemo.java
 */
public class CpuProfilingDemo {

    static final Pattern SKU = Pattern.compile("^SKU-([0-9]+)-([A-Z]{2})$");

    static boolean validBuggy(String s) {
        return Pattern.compile("^SKU-([0-9]+)-([A-Z]{2})$").matcher(s).matches(); // compile tiap panggilan
    }

    static boolean validFixed(String s) { return SKU.matcher(s).matches(); }

    static int process(List<String> rows, boolean fixed) {
        int ok = 0;
        for (String r : rows) if (fixed ? validFixed(r) : validBuggy(r)) ok++;
        return ok;
    }

    static void profile(String label, boolean fixed, List<String> rows) throws Exception {
        Map<String, Integer> hits = new HashMap<>();
        Thread worker = new Thread(() -> { for (int i = 0; i < 40; i++) process(rows, fixed); }, "worker");
        long t0 = System.nanoTime();
        worker.start();
        int samples = 0;
        while (worker.isAlive()) {
            StackTraceElement[] st = worker.getStackTrace();
            if (st.length > 0) {
                // ambil frame pertama milik kita / JDK regex, cukup untuk lihat hot spot
                String top = st[0].getClassName() + "." + st[0].getMethodName();
                hits.merge(top, 1, Integer::sum);
                samples++;
            }
            Thread.sleep(2);
        }
        final int total = samples;
        long ms = (System.nanoTime() - t0) / 1_000_000;
        System.out.printf("%n== %s: %d ms, %d samples ==%n", label, ms, samples);
        hits.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(4)
            .forEach(e -> System.out.printf("  %5.1f%%  %s%n", 100.0 * e.getValue() / Math.max(1, total), e.getKey()));
    }

    public static void main(String[] args) throws Exception {
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < 20_000; i++) rows.add("SKU-" + i + "-ID");
        process(rows, true); // warmup JIT
        profile("sebelum (compile tiap baris)", false, rows);
        profile("sesudah (precompiled)", true, rows);
        System.out.println("\nCatatan: getStackTrace() kena safepoint bias; produksi pakai JFR / async-profiler.");
    }
}
