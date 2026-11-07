import java.lang.reflect.Proxy;

// Lesson 4.4: proxy mencegat panggilan dari luar, tapi self-invocation (this.xxx()) lolos.
// Tanpa Spring: JDK dynamic proxy dipakai sebagai "transaksi" palsu.
// Jalankan: java AopProxyDemo.java
public class AopProxyDemo {

    interface PaymentService {
        void pay(String order);
        void saveAudit(String order);
    }

    // Bean asli. pay() memanggil saveAudit() lewat this -> tidak lewat proxy.
    static class PaymentServiceImpl implements PaymentService {
        public void pay(String order) {
            System.out.println("  pay(" + order + ")");
            this.saveAudit(order); // self-invocation
        }
        public void saveAudit(String order) {
            System.out.println("  saveAudit(" + order + ")");
        }
    }

    // Bean terpisah: dipanggil lewat proxy dari luar, jadi tercegat.
    static class PaymentFlow {
        private final PaymentService payment;
        private final PaymentService audit;
        PaymentFlow(PaymentService payment, PaymentService audit) {
            this.payment = payment;
            this.audit = audit;
        }
        void run(String order) {
            payment.pay(order);
            audit.saveAudit(order); // lewat proxy -> tercegat
        }
    }

    // "Advice": cetak BEGIN/COMMIT di sekitar method, seperti @Transactional.
    static PaymentService withTransaction(PaymentService target) {
        return (PaymentService) Proxy.newProxyInstance(
            PaymentService.class.getClassLoader(),
            new Class<?>[]{PaymentService.class},
            (proxy, method, args) -> {
                System.out.println("[TX BEGIN] " + method.getName());
                Object result = method.invoke(target, args);
                System.out.println("[TX COMMIT] " + method.getName());
                return result;
            });
    }

    public static void main(String[] args) {
        PaymentService proxy = withTransaction(new PaymentServiceImpl());
        System.out.println("tipe bean: " + proxy.getClass().getSimpleName() + " (bukan PaymentServiceImpl)");

        System.out.println("--- panggil pay() lewat proxy: saveAudit() lewat this tidak dapat TX ---");
        proxy.pay("ORD-1");

        System.out.println("--- panggil saveAudit() langsung lewat proxy: dapat TX ---");
        new PaymentFlow(proxy, proxy).run("ORD-2");
    }
}
