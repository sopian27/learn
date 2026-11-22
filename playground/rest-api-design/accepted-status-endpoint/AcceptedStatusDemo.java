import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Demo: konfirmasi pembayaran lambat (2 detik) sebagai sync vs 202 Accepted + status endpoint.
public class AcceptedStatusDemo {

    record Resp(int status, String location, String retryAfter, String body) {}

    static final Map<String, String> jobs = new ConcurrentHashMap<>();     // jobId -> PENDING/PROCESSING/DONE
    static final ExecutorService worker = Executors.newSingleThreadExecutor();

    static void slowPayment() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // Sync: klien menunggu 2 detik, kalau timeout klien nggak tahu hasilnya.
    static Resp confirmSync() {
        slowPayment();
        return new Resp(200, null, null, "{\"paid\":true}");
    }

    // Async: terima, antri, balas cepat dengan 202 + Location + Retry-After.
    static Resp confirmAsync() {
        String id = "job-" + (jobs.size() + 1);
        jobs.put(id, "PENDING");
        worker.submit(() -> {
            jobs.put(id, "PROCESSING");
            slowPayment();
            jobs.put(id, "DONE");
        });
        return new Resp(202, "/jobs/" + id, "1", "{\"status\":\"PENDING\"}");
    }

    // GET /jobs/{id}: selesai -> 303 ke resource hasil; belum -> 200 + status + Retry-After.
    static Resp getJob(String id) {
        String s = jobs.get(id);
        if (s == null) return new Resp(404, null, null, "job tidak ada");
        if (s.equals("DONE")) return new Resp(303, "/orders/ORD-1", null, "{\"status\":\"DONE\"}");
        return new Resp(200, null, "1", "{\"status\":\"" + s + "\"}");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("== 1. Sync");
        long t = System.currentTimeMillis();
        System.out.println(confirmSync() + "  (" + (System.currentTimeMillis() - t) + " ms)");

        System.out.println("\n== 2. Async: balas cepat");
        t = System.currentTimeMillis();
        Resp accepted = confirmAsync();
        System.out.println(accepted + "  (" + (System.currentTimeMillis() - t) + " ms)");

        System.out.println("\n== 3. Polling sesuai Location + Retry-After");
        String id = accepted.location().substring("/jobs/".length());
        Resp r;
        do {
            r = getJob(id);
            System.out.println(r);
            if (r.status() == 200) Thread.sleep(Long.parseLong(r.retryAfter()) * 1000);
        } while (r.status() == 200);

        System.out.println("\n== 4. Job tidak ada");
        System.out.println(getJob("job-99"));
        worker.shutdown();
    }
}
