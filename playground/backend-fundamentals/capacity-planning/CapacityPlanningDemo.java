// Jalankan: java CapacityPlanningDemo.java
// Rumus sederhana (bukan simulasi sistem asli): Little's Law, kurva utilisasi, hitung jumlah pod.
public class CapacityPlanningDemo {

    public static void main(String[] args) {
        // 1) Little's Law: concurrency = throughput x latency
        double rps = 400, latencySec = 0.120;
        double inFlight = rps * latencySec;
        System.out.printf("Little's Law: %.0f rps x %.0f ms = %.0f request in-flight%n",
                rps, latencySec * 1000, inFlight);
        System.out.printf("  -> pool koneksi/thread kurang dari %.0f berarti request antre%n%n", inFlight);

        // 2) Kurva utilisasi: waktu respons = service / (1 - utilisasi)  (model antrean M/M/1)
        double serviceMs = 20;
        System.out.println("Service time 20 ms, makin penuh makin lambat:");
        for (double u : new double[]{0.5, 0.7, 0.8, 0.9, 0.95}) {
            System.out.printf("  utilisasi %2.0f%% -> rata-rata respons %5.1f ms%n", u * 100, serviceMs / (1 - u));
        }

        // 3) Hitung pod dari hasil load test
        double peakRps = 1200;          // puncak yang diperkirakan (bukan rata-rata)
        double kneePerPod = 300;        // rps per pod sebelum p99 melonjak (dari load test 14.3)
        double targetUtil = 0.70;       // headroom 30%
        int pods = (int) Math.ceil(peakRps / (kneePerPod * targetUtil));
        System.out.printf("%nPuncak %.0f rps, knee %.0f rps/pod, target %.0f%% -> %d pod, +1 cadangan = %d pod%n",
                peakRps, kneePerPod, targetUtil * 100, pods, pods + 1);
        System.out.printf("Kalau pakai knee penuh tanpa headroom: %d pod (nol ruang buat lonjakan)%n",
                (int) Math.ceil(peakRps / kneePerPod));
    }
}
