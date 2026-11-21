import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

// Demo: kapan gzip membantu, kapan tidak, dan payload minimization.
public class CompressionDemo {

    static int gzipSize(byte[] data) throws Exception {
        var out = new ByteArrayOutputStream();
        try (var gz = new GZIPOutputStream(out)) { gz.write(data); }
        return out.size();
    }

    static void report(String label, byte[] data) throws Exception {
        int raw = data.length, gz = gzipSize(data);
        System.out.printf("%-34s raw=%7d  gzip=%7d  hemat=%3d%%%n", label, raw, gz, 100 - gz * 100 / raw);
    }

    static String orders(int n, boolean minimal) {
        var sb = new StringBuilder("[");
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(',');
            if (minimal) {
                sb.append("{\"id\":").append(i).append(",\"status\":\"PAID\",\"total\":150000}");
            } else {
                sb.append("{\"id\":").append(i).append(",\"status\":\"PAID\",\"total\":150000,")
                  .append("\"customerName\":\"Budi Santoso\",\"customerEmail\":\"budi@example.com\",")
                  .append("\"shippingAddress\":\"Jl. Merdeka No. 10, Jakarta Pusat, DKI Jakarta\",")
                  .append("\"createdAt\":\"2026-11-22T10:15:30Z\",\"updatedAt\":\"2026-11-22T10:15:30Z\",")
                  .append("\"internalNote\":\"\",\"auditTrail\":[],\"legacyField\":null}");
            }
        }
        return sb.append(']').toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("== 1. gzip membantu: JSON besar, berulang ==");
        report("JSON 500 order (penuh)", orders(500, false).getBytes(StandardCharsets.UTF_8));

        System.out.println("== 2. gzip kurang berguna: payload kecil ==");
        report("JSON 1 order (kecil)", orders(1, true).getBytes(StandardCharsets.UTF_8));

        System.out.println("== 3. gzip sia-sia: data sudah terkompres (mirip JPEG/ZIP) ==");
        byte[] random = new byte[50_000];
        new Random(42).nextBytes(random);
        report("50KB byte acak (sudah padat)", random);

        System.out.println("== 4. payload minimization lebih dulu, gzip menyusul ==");
        report("500 order, field penuh", orders(500, false).getBytes(StandardCharsets.UTF_8));
        report("500 order, field seperlunya", orders(500, true).getBytes(StandardCharsets.UTF_8));
    }
}
