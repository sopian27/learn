import java.util.*;

/**
 * Lesson 12.4: dependency tambahan mati. Tanpa fallback halaman gagal total;
 * dengan fallback halaman tampil degraded. Harga (inti) sengaja TIDAK di-fallback.
 * Run: java GracefulDegradationDemo.java
 */
public class GracefulDegradationDemo {

    static boolean recommendationUp = true;
    static boolean priceUp = true;
    static List<String> recommendationCache = List.of("Sepatu B", "Sepatu C"); // hasil terakhir yang sukses
    static int fallbackCount = 0; // metrik: fallback harus kelihatan

    static List<String> callRecommendation() {
        if (!recommendationUp) throw new IllegalStateException("recommendation down");
        return List.of("Sepatu B", "Sepatu C");
    }

    static int callPrice() {
        if (!priceUp) throw new IllegalStateException("price down");
        return 500_000;
    }

    // Tanpa fallback: satu dependency tambahan mati = seluruh halaman gagal.
    static String pageNaive() {
        return "harga=" + callPrice() + " rekomendasi=" + callRecommendation();
    }

    // Dengan fallback: tambahan boleh hilang, inti tetap gagal jelas.
    static String pageDegraded() {
        int price = callPrice(); // inti: nggak ada fallback ke data stale
        try {
            return "harga=" + price + " rekomendasi=" + callRecommendation();
        } catch (RuntimeException e) {
            fallbackCount++;
            return "harga=" + price + " rekomendasi=" + recommendationCache + " [degraded: stale]";
        }
    }

    static String tryPage(java.util.function.Supplier<String> page) {
        try { return page.get(); } catch (RuntimeException e) { return "ERROR 500 (" + e.getMessage() + ")"; }
    }

    public static void main(String[] args) {
        recommendationUp = false;
        System.out.println("== 1. recommendation mati, TANPA fallback ==");
        System.out.println(tryPage(GracefulDegradationDemo::pageNaive) + "\n");

        System.out.println("== 2. recommendation mati, DENGAN fallback ==");
        System.out.println(tryPage(GracefulDegradationDemo::pageDegraded));
        System.out.println("fallback terpakai " + fallbackCount + "x (dihitung di metrik)\n");

        recommendationUp = true;
        priceUp = false;
        System.out.println("== 3. harga (inti) mati: gagal jelas, bukan harga stale ==");
        System.out.println(tryPage(GracefulDegradationDemo::pageDegraded));
    }
}
