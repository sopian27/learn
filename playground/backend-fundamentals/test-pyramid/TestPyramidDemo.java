import java.util.List;

// Run: java TestPyramidDemo.java
// Simulasi biaya dua suite dengan jumlah test sama (100): pyramid vs ice-cream cone.
// Angka durasi & flakiness adalah asumsi umum, ganti sesuai project lu.
public class TestPyramidDemo {

    record Layer(String name, int count, double secondsPerTest, double flakeRate) {}

    static void report(String title, List<Layer> layers) {
        double totalSeconds = 0;
        double allPass = 1.0; // peluang SEMUA test lulus murni karena flake
        for (Layer l : layers) {
            totalSeconds += l.count() * l.secondsPerTest();
            allPass *= Math.pow(1 - l.flakeRate(), l.count());
        }
        System.out.printf("%n== %s ==%n", title);
        layers.forEach(l -> System.out.printf("  %-12s %3d test x %6.2fs%n", l.name(), l.count(), l.secondsPerTest()));
        System.out.printf("  Waktu 1x run CI      : %.1f detik (%.1f menit)%n", totalSeconds, totalSeconds / 60);
        System.out.printf("  Build merah karena flake (tanpa bug asli): %.0f%%%n", (1 - allPass) * 100);
    }

    public static void main(String[] args) {
        report("PYRAMID (70 unit / 20 integration / 10 e2e)", List.of(
                new Layer("unit", 70, 0.01, 0.0),
                new Layer("integration", 20, 0.5, 0.002),
                new Layer("e2e", 10, 10.0, 0.02)));

        report("ICE-CREAM CONE (10 unit / 20 integration / 70 e2e)", List.of(
                new Layer("unit", 10, 0.01, 0.0),
                new Layer("integration", 20, 0.5, 0.002),
                new Layer("e2e", 70, 10.0, 0.02)));
    }
}
