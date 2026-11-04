import java.io.IOException;

/**
 * Companion Lesson 1.3 (Exception handling idiomatis).
 * Run: java ExceptionHandlingDemo.java   (Java 17+ single-file mode)
 * Domain dummy: charge pembayaran untuk Order.
 */
public class ExceptionHandlingDemo {

    // Gateway dummy: selalu gagal, kayak timeout ke payment provider.
    static void charge(String orderId) throws IOException {
        throw new IOException("gateway timeout untuk " + orderId);
    }

    // Business error: unchecked, bawa konteks + cause asli.
    static class PaymentFailedException extends RuntimeException {
        PaymentFailedException(String orderId, Throwable cause) {
            super("Pembayaran gagal untuk order " + orderId, cause);
        }
    }

    // --- BURUK: exception ditelan, caller mengira sukses ---
    static String payBroken(String orderId) {
        try {
            charge(orderId);
        } catch (Exception e) {
            // kosong: error hilang tanpa jejak
        }
        return "PAID";
    }

    // --- BURUK: wrap tanpa cause, stack trace asli hilang ---
    static void payLostCause(String orderId) {
        try {
            charge(orderId);
        } catch (IOException e) {
            throw new RuntimeException("gagal bayar " + orderId);
        }
    }

    // --- BENAR: tangkap yang spesifik, wrap dengan cause, jangan log di sini ---
    static String payFixed(String orderId) {
        try {
            charge(orderId);
            return "PAID";
        } catch (IOException e) {
            throw new PaymentFailedException(orderId, e);
        }
    }

    // Resource yang gagal saat close(); try-with-resources jaga exception utama.
    static class Connection implements AutoCloseable {
        @Override public void close() { throw new IllegalStateException("close gagal"); }
    }

    public static void main(String[] args) {
        System.out.println("1. payBroken       = " + payBroken("ORD-1") + "  (padahal charge gagal!)");

        try {
            payLostCause("ORD-2");
        } catch (RuntimeException e) {
            System.out.println("2. lost cause      = " + e.getMessage() + ", cause = " + e.getCause());
        }

        try {
            payFixed("ORD-3");
        } catch (PaymentFailedException e) {
            System.out.println("3. fixed           = " + e.getMessage() + ", cause = " + e.getCause());
        }

        try (Connection c = new Connection()) {
            throw new IllegalArgumentException("error utama");
        } catch (RuntimeException e) {
            System.out.println("4. try-with-res    = " + e.getMessage()
                + ", suppressed = " + e.getSuppressed()[0].getMessage());
        }
    }
}
