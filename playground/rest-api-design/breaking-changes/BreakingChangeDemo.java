import java.util.*;

// Lesson 8.1: consumer v1 dibangun terhadap kontrak order v1. Server berubah, consumer tidak.
public class BreakingChangeDemo {

    // Kode consumer lama (jangan diubah): ini yang "dibekukan" di HP / service orang lain.
    static String consumerV1(Map<String, Object> order) {
        String id = (String) order.get("id");
        long total = ((Number) order.get("total")).longValue();
        String label = switch ((String) order.get("status")) {
            case "PAID" -> "lunas";
            case "PENDING" -> "menunggu";
            default -> throw new IllegalStateException("status tak dikenal: " + order.get("status"));
        };
        return id + " | Rp" + total + " | " + label;
    }

    // Server menolak request tanpa field wajib (sisi request juga bisa breaking).
    static String createOrderV2(Map<String, Object> body) {
        if (body.get("currency") == null) throw new IllegalArgumentException("400: currency wajib");
        return "201 created";
    }

    static void run(String name, boolean breaking, java.util.function.Supplier<String> call) {
        String result;
        try { result = "OK    -> " + call.get(); }
        catch (Exception e) { result = "BREAK -> " + e.getClass().getSimpleName() + ": " + e.getMessage(); }
        System.out.printf("%-34s (%s)%n   %s%n", name, breaking ? "breaking" : "non-breaking", result);
    }

    public static void main(String[] args) {
        run("baseline v1", false, () -> consumerV1(
                Map.of("id", "ORD-1", "total", 150000, "status", "PAID")));
        run("tambah field 'currency'", false, () -> consumerV1(
                Map.of("id", "ORD-1", "total", 150000, "status", "PAID", "currency", "IDR")));
        run("rename 'total' -> 'grandTotal'", true, () -> consumerV1(
                Map.of("id", "ORD-1", "grandTotal", 150000, "status", "PAID")));
        run("hapus field 'status'", true, () -> consumerV1(
                Map.of("id", "ORD-1", "total", 150000)));
        run("tipe 'total' int -> string", true, () -> consumerV1(
                Map.of("id", "ORD-1", "total", "150000.00", "status", "PAID")));
        run("enum baru 'REFUNDED'", true, () -> consumerV1(
                Map.of("id", "ORD-1", "total", 150000, "status", "REFUNDED")));
        run("request: field wajib baru", true, () -> createOrderV2(
                Map.of("sku", "A-1", "qty", 2)));
        run("request: field opsional baru", false, () -> createOrderV2(
                Map.of("sku", "A-1", "qty", 2, "currency", "IDR")));
    }
}
