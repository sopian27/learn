import java.util.ArrayList;
import java.util.List;
// Lesson 6.5: 4xx = salah klien (kasih tahu cara memperbaiki), 5xx = salah kita (log + alert, jangan bocorkan).
public class ClientVsServerErrorDemo {
    record Result(int status, String detail) {}
    static final List<String> alerts = new ArrayList<>();

    // satu pintu keluar: level log dan alert ditentukan dari status
    static Result respond(int status, String internalReason, String clientHint, String traceId) {
        if (status >= 500) {
            System.out.println("  ERROR traceId=" + traceId + " " + internalReason); // lengkap, hanya di log
            alerts.add(traceId);                                                      // tim dibangunkan
            return new Result(status, "Terjadi kesalahan di server. Sebutkan traceId " + traceId + " ke support.");
        }
        System.out.println("  INFO  traceId=" + traceId + " " + status + " " + clientHint); // tanpa alert
        return new Result(status, clientHint);
    }

    public static void main(String[] args) {
        Result[] r = {
            respond(400, "", "quantity harus lebih dari 0", "t1"),
            respond(404, "", "Order ORD-1 tidak ditemukan", "t2"),
            respond(409, "", "Order sudah dikirim, tidak bisa dibatalkan", "t3"),
            respond(500, "NPE di OrderService.java:88, db=jdbc:postgresql://db-internal:5432", "", "t4"),
        };
        for (Result x : r) System.out.println(x.status() + " -> " + x.detail());
        System.out.println("alert terkirim: " + alerts + " (harus hanya [t4])");
        boolean ok = alerts.equals(List.of("t4"))
            && r[3].detail().indexOf("db-internal") < 0 && r[3].detail().contains("t4")
            && r[0].detail().contains("harus");
        System.out.println("self-check: " + (ok ? "OK" : "GAGAL"));
    }
}
