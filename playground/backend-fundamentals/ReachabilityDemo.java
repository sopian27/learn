import java.util.ArrayList;
import java.util.List;

public class ReachabilityDemo {
    // GC Root: static field
    private static final List<byte[]> leak = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            byte[] chunk = new byte[10 * 1024 * 1024]; // 10MB per iterasi
            leak.add(chunk); // sengaja disimpan -> tetap reachable dari GC Root "leak"
            chunk = null;    // reference lokal putus, TAPI objectnya tetap hidup lewat "leak"

            System.gc(); // cuma "saran", JVM boleh mengabaikannya
            Thread.sleep(200);
            System.out.printf("Iterasi %d — used heap: %.1f MB%n",
                i, (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024.0 / 1024.0);
        }
        // Jalankan dengan -Xmx64m untuk lihat OutOfMemoryError: Java heap space
        // begitu "leak" cukup besar -- buktikan heap usage TIDAK PERNAH turun antar iterasi,
        // walau "chunk" di-null-kan tiap akhir loop.
    }
}