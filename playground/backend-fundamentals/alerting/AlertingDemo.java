import java.util.*;

/**
 * Lesson 13.4: alert berisik vs alert bermakna.
 * Simulasi 60 menit error rate (%) per menit, lalu dua aturan alert dijalankan di data yang sama.
 * Run: java AlertingDemo.java
 */
public class AlertingDemo {

    public static void main(String[] args) {
        double[] errorRate = new double[60];
        Arrays.fill(errorRate, 0.5);                       // kondisi normal
        for (int m : new int[]{5, 11, 17, 23, 29, 35}) errorRate[m] = 3.5; // lonjakan 1 menit, sembuh sendiri
        for (int m = 40; m < 55; m++) errorRate[m] = 12.0; // insiden beneran, 15 menit

        List<Integer> noisy = new ArrayList<>();
        List<Integer> meaningful = new ArrayList<>();
        boolean noisyFiring = false;
        int streak = 0;
        boolean meaningfulFiring = false;

        for (int m = 0; m < 60; m++) {
            // Aturan A: satu menit di atas 2% langsung page
            boolean a = errorRate[m] > 2.0;
            if (a && !noisyFiring) noisy.add(m);
            noisyFiring = a;

            // Aturan B: di atas 5% selama 5 menit berturut-turut baru page
            streak = errorRate[m] > 5.0 ? streak + 1 : 0;
            boolean b = streak >= 5;
            if (b && !meaningfulFiring) meaningful.add(m);
            meaningfulFiring = b;
        }

        System.out.println("Insiden asli: menit 40-54 (error 12%). Lonjakan palsu: menit 5, 11, 17, 23, 29, 35.\n");
        System.out.println("Aturan A (1 menit > 2%)          : " + noisy.size() + " page di menit " + noisy);
        System.out.println("Aturan B (5 menit berturut > 5%) : " + meaningful.size() + " page di menit " + meaningful);

        long falseA = noisy.stream().filter(m -> m < 40 || m > 54).count();
        System.out.println("\nAturan A: " + falseA + " dari " + noisy.size() + " page itu palsu. Orang on-call mulai mengabaikan, termasuk yang asli.");
        System.out.println("Aturan B: telat " + (meaningful.get(0) - 40) + " menit dari awal insiden, tapi nol page palsu.");
    }
}
