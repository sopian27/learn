import java.lang.management.*;
import java.util.List;

// Tampilkan apa yang JVM ini sebenarnya pakai: heap, GC, CPU, dan flag yang lu kasih.
// Jalankan beberapa kali dengan flag beda dan bandingkan output-nya (lihat README).
public class FlagsDemo {
    static final long MB = 1024 * 1024;

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        System.out.println("== sizing ==");
        System.out.printf("Max heap (-Xmx)     : %d MB%n", rt.maxMemory() / MB);
        System.out.printf("Heap sekarang       : %d MB (-Xms = awal)%n", rt.totalMemory() / MB);
        System.out.printf("CPU yang dilihat JVM: %d%n", rt.availableProcessors());

        System.out.println("\n== GC yang aktif ==");
        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.printf("%s (pool: %s)%n", gc.getName(), String.join(", ", gc.getMemoryPoolNames()));
        }

        System.out.println("\n== flag dari command line ==");
        List<String> flags = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println(flags.isEmpty() ? "(kosong: semua default ergonomics)" : String.join("\n", flags));

        // Alokasi sampah 200 MB: lihat berapa kali GC jalan di heap sesuai flag.
        long before = totalGcCount();
        for (int i = 0; i < 200; i++) {
            byte[] garbage = new byte[(int) MB];
            garbage[0] = 1;
        }
        System.out.printf("%n== alokasi 200 MB sampah ==%nGC jalan %d kali%n", totalGcCount() - before);
    }

    static long totalGcCount() {
        return ManagementFactory.getGarbageCollectorMXBeans().stream().mapToLong(GarbageCollectorMXBean::getCollectionCount).sum();
    }
}
