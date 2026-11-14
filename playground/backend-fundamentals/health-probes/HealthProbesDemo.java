import java.util.*;

/**
 * Lesson 12.5: DB melambat. Liveness yang ngecek DB bikin pod di-restart massal;
 * liveness cuma "proses hidup" + readiness yang ngecek DB bikin pod cuma dicabut dari traffic.
 * Run: java HealthProbesDemo.java
 */
public class HealthProbesDemo {

    static boolean dbUp = true;

    record Pod(String name, boolean warmedUp) {}

    // Liveness salah: ikut ngecek DB.
    static boolean livenessBad(Pod p) { return dbUp; }

    // Liveness benar: cuma "proses masih jalan dan nggak deadlock".
    static boolean livenessGood(Pod p) { return true; }

    // Readiness: siap terima traffic = sudah warm-up dan dependency kritis tersedia.
    static boolean readiness(Pod p) { return p.warmedUp() && dbUp; }

    static void kubelet(String title, List<Pod> pods, java.util.function.Predicate<Pod> liveness) {
        System.out.println("== " + title + " ==");
        int restarted = 0, inTraffic = 0;
        for (Pod p : pods) {
            if (!liveness.test(p)) { restarted++; continue; }       // liveness gagal: pod DIBUNUH
            if (readiness(p)) inTraffic++;                          // readiness lolos: dapat traffic
        }
        System.out.println("pod di-restart : " + restarted + " dari " + pods.size());
        System.out.println("pod terima traffic: " + inTraffic);
        System.out.println();
    }

    public static void main(String[] args) {
        List<Pod> pods = List.of(new Pod("order-1", true), new Pod("order-2", true), new Pod("order-3", true));

        dbUp = false;
        kubelet("1. DB mati, liveness ngecek DB (SALAH)", pods, HealthProbesDemo::livenessBad);
        kubelet("2. DB mati, liveness cuma proses hidup + readiness ngecek DB", pods, HealthProbesDemo::livenessGood);

        dbUp = true;
        List<Pod> deploy = List.of(new Pod("order-1", true), new Pod("order-2", true), new Pod("order-4-baru", false));
        kubelet("3. Rolling deploy, pod baru belum warm-up (readiness tahan traffic)", deploy, HealthProbesDemo::livenessGood);
    }
}
