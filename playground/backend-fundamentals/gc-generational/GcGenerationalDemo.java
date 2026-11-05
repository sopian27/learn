import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo Lesson 3.2: kenapa GC generasional ada, dan beda collector.
 * Jalankan: java -Xmx256m GcGenerationalDemo.java
 * Bandingkan: tambah -XX:+UseParallelGC, -XX:+UseSerialGC, atau -XX:+UseZGC
 */
public class GcGenerationalDemo {

    record Order(long id, byte[] payload) {}

    static final List<Order> LONG_LIVED = new ArrayList<>(); // sedikit yang bertahan

    public static void main(String[] args) {
        System.out.println("Collector aktif:");
        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.println("  " + gc.getName());
        }

        long start = System.nanoTime();
        for (long i = 0; i < 2_000_000; i++) {
            Order o = new Order(i, new byte[1024]); // mayoritas mati muda (weak generational hypothesis)
            if (i % 20_000 == 0) LONG_LIVED.add(o); // ~100 objek bertahan lama
        }
        long ms = (System.nanoTime() - start) / 1_000_000;

        System.out.println("Selesai dalam " + ms + " ms, objek yang bertahan: " + LONG_LIVED.size());
        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.printf("  %-28s jumlah=%d total pause/waktu=%d ms%n",
                    gc.getName(), gc.getCollectionCount(), gc.getCollectionTime());
        }
    }
}
