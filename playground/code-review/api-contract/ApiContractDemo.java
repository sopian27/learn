import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

// Pendamping Library Lesson 5.3. Response JSON ditiru pakai Map, tanpa library.
public class ApiContractDemo {

    // Client lama: dibuat berdasarkan kontrak v1 dan tidak ikut di-deploy ulang.
    enum Status {
        PENDING, PAID
    }

    static String oldClient(Map<String, Object> body) {
        long amount = (Long) body.get("amount");
        Status status = Status.valueOf((String) body.get("status"));
        return amount + " " + status;
    }

    static Map<String, Object> response(Object... pairs) {
        Map<String, Object> body = new LinkedHashMap<>();
        for (int i = 0; i < pairs.length; i += 2)
            body.put((String) pairs[i], pairs[i + 1]);
        return body;
    }

    static String call(Supplier<String> client) {
        try {
            return "OK   " + client.get();
        } catch (RuntimeException e) {
            return "RUSAK " + e.getClass().getSimpleName();
        }
    }

    static void check(String label, Map<String, Object> body, boolean expectOk) {
        String result = call(() -> oldClient(body));
        System.out.println(label + " -> " + result);
        if (result.startsWith("OK") != expectOk)
            throw new AssertionError(label);
    }

    public static void main(String[] args) {
        check("1. v1 asli                    ", response("amount", 5000L, "status", "PAID"), true);
        check("2. rename amount->totalAmount ", response("totalAmount", 5000L, "status", "PAID"), false);
        check("3. tipe number->string        ", response("amount", "5000", "status", "PAID"), false);
        check("4. nilai enum baru            ", response("amount", 5000L, "status", "REFUNDED"), false);
        check("5. tambah field, lama tetap   ",
                response("amount", 5000L, "totalAmount", 5000L, "status", "PAID"), true);
    }
}
