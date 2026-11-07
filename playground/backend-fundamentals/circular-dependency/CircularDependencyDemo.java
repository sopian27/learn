import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

// Lesson 4.5: constructor injection tidak bisa membangun dua bean yang saling butuh.
// Tanpa Spring: "container" mini yang mendeteksi bean yang sedang dibuat.
// Jalankan: java CircularDependencyDemo.java
public class CircularDependencyDemo {

    // Container palsu: mencatat bean yang sedang dalam proses pembuatan.
    static class MiniContainer {
        private final Set<String> inCreation = new HashSet<>();

        <T> T create(String name, Supplier<T> factory) {
            if (!inCreation.add(name)) {
                throw new IllegalStateException("BeanCurrentlyInCreation: " + name);
            }
            try {
                return factory.get();
            } finally {
                inCreation.remove(name);
            }
        }
    }

    // --- Versi bermasalah: saling butuh lewat constructor ---
    static class OrderService {
        final PaymentService payment;
        OrderService(PaymentService payment) { this.payment = payment; }
    }
    static class PaymentService {
        final OrderService order;
        PaymentService(OrderService order) { this.order = order; }
    }

    static OrderService buildOrder(MiniContainer c) {
        return c.create("orderService", () -> new OrderService(buildPayment(c)));
    }
    static PaymentService buildPayment(MiniContainer c) {
        return c.create("paymentService", () -> new PaymentService(buildOrder(c)));
    }

    // --- Versi fix: ekstrak tanggung jawab bersama ke bean ketiga, arah dependency satu arah ---
    static class PaymentStatusReader { String statusOf(String order) { return "PAID"; } }
    static class OrderService2 {
        final PaymentStatusReader status;
        OrderService2(PaymentStatusReader status) { this.status = status; }
    }
    static class PaymentService2 {
        final PaymentStatusReader status;
        PaymentService2(PaymentStatusReader status) { this.status = status; }
    }

    public static void main(String[] args) {
        System.out.println("--- OrderService <-> PaymentService via constructor ---");
        try {
            buildOrder(new MiniContainer());
        } catch (IllegalStateException e) {
            System.out.println("GAGAL: " + e.getMessage());
        }

        System.out.println("--- setelah dipecah: keduanya bergantung ke PaymentStatusReader ---");
        MiniContainer c = new MiniContainer();
        PaymentStatusReader reader = c.create("statusReader", PaymentStatusReader::new);
        OrderService2 o = c.create("orderService2", () -> new OrderService2(reader));
        PaymentService2 p = c.create("paymentService2", () -> new PaymentService2(reader));
        System.out.println("OK: " + o.status.statusOf("ORD-1") + " / " + (p.status == o.status));
    }
}
