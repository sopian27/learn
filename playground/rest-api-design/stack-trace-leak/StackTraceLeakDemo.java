import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.UUID;

// Lesson 6.1: simulasi handler error tanpa framework. Bandingkan response bocor vs aman.
public class StackTraceLeakDemo {

    // Pura-pura repository gagal konek ke DB: pesan exception memuat detail internal
    static void findOrder(String id) throws SQLException {
        throw new SQLException("Connection refused: jdbc:postgresql://10.0.3.7:5432/orders_prod user=orders_app");
    }

    // VERSI BOCOR: apa pun yang dilempar langsung jadi body response
    static String leakyHandler(String id) {
        try {
            findOrder(id);
            return "200 OK";
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return "500 {\"error\":\"" + e.getMessage() + "\",\"trace\":\"" + sw.toString().lines().limit(3).reduce("", (a, b) -> a + b.trim() + " | ") + "\"}";
        }
    }

    // VERSI AMAN: detail ke log server (dengan errorId), klien cuma terima pesan generik + errorId
    static String safeHandler(String id) {
        try {
            findOrder(id);
            return "200 OK";
        } catch (Exception e) {
            String errorId = UUID.randomUUID().toString().substring(0, 8);
            System.out.println("[SERVER LOG] errorId=" + errorId + " orderId=" + id + " -> " + e);
            return "500 {\"title\":\"Internal Server Error\",\"detail\":\"Terjadi kesalahan, hubungi support dengan errorId\",\"errorId\":\"" + errorId + "\"}";
        }
    }

    public static void main(String[] args) {
        System.out.println("== Bocor (yang dilihat klien/penyerang)");
        System.out.println(leakyHandler("ORD-1"));
        System.out.println();
        System.out.println("== Aman (log server vs response klien)");
        System.out.println(safeHandler("ORD-1"));
    }
}
