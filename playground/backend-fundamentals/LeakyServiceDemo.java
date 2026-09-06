import java.util.HashMap;
import java.util.Map;

public class LeakyServiceDemo {
    // Persis Pola 1 Lesson 3.3: static cache tanpa eviction.
    private static final Map<Integer, byte[]> cache = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("PID proses ini, cari lewat: jcmd -l");
        int i = 0;
        while (true) {
            cache.put(i, new byte[512 * 1024]); // 512KB per entry, sengaja tidak pernah dihapus
            i++;
            if (i % 50 == 0) {
                System.out.println("Entry ke-" + i + ", perkiraan cache size: " + (i * 512 / 1024) + " MB");
            }
            Thread.sleep(100); // beri waktu supaya sempat diambil heap dump di tengah jalan
        }
    }
}