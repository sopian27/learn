// AllocationChurnDemo.java
// Simulasi beban kerja khas web service: banyak object jangka pendek,
// sedikit yang jadi long-lived (masuk "cache").
import java.util.ArrayList;
import java.util.List;

public class AllocationChurnDemo {
    // long-lived, sengaja disimpan -> akan naik ke Old Gen
    private static final List<byte[]> longLivedCache = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int iteration = 0;

        while (System.currentTimeMillis() - start < 15_000) { // jalan 15 detik
            iteration++;

            // short-lived: dibuat, dipakai sebentar, langsung tidak direference lagi
            for (int i = 0; i < 500; i++) {
                byte[] shortLived = new byte[4 * 1024]; // 4KB, khas ukuran DTO/response kecil
                consume(shortLived);
            }

            // sekali-sekali, sesuatu "menempel" jadi long-lived (mis. cache entry)
            if (iteration % 200 == 0) {
                longLivedCache.add(new byte[256 * 1024]); // 256KB
                if (longLivedCache.size() > 50) {
                    longLivedCache.remove(0); // dibatasi -- bukan unbounded leak (bandingkan Lesson 3.3)
                }
            }
        }
        System.out.println("Selesai, iterasi: " + iteration);
    }

    private static void consume(byte[] data) {
        data[0] = 1; // sekadar "dipakai" biar tidak dioptimasi hilang JIT
    }
}