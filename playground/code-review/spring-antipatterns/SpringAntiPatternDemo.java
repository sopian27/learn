import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

// Tiruan kecil proxy @Transactional milik Spring, tanpa dependency.
public class SpringAntiPatternDemo {

    @Retention(RetentionPolicy.RUNTIME)
    @interface Tx { Class<? extends Throwable>[] rollbackFor() default {}; }

    static final List<String> db = new ArrayList<>();
    static List<String> pending; // null = tidak ada transaksi (autocommit)

    static void save(String row) { (pending == null ? db : pending).add(row); }

    static class PaymentException extends Exception {} // checked

    interface Orders {
        void importOne();
        void importViaThis();
        void payDefault() throws PaymentException;
        void payFixed() throws PaymentException;
    }

    static class OrderService implements Orders {
        @Tx public void importOne() {
            save("order");
            throw new IllegalStateException("stok habis");
        }
        public void importViaThis() { this.importOne(); } // BUG: lewati proxy
        @Tx public void payDefault() throws PaymentException {
            save("order PAID");
            throw new PaymentException();
        }
        @Tx(rollbackFor = PaymentException.class)
        public void payFixed() throws PaymentException {
            save("order PAID");
            throw new PaymentException();
        }
    }

    static Orders proxy(Orders target) {
        return (Orders) Proxy.newProxyInstance(Orders.class.getClassLoader(), new Class<?>[]{Orders.class},
            (p, method, args) -> {
                Tx tx = target.getClass().getMethod(method.getName()).getAnnotation(Tx.class);
                if (tx != null) pending = new ArrayList<>();
                boolean commit = true;
                try {
                    return method.invoke(target, args);
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    // aturan default Spring: rollback cuma untuk RuntimeException dan Error
                    commit = !(cause instanceof RuntimeException || cause instanceof Error
                        || tx != null && Arrays.stream(tx.rollbackFor()).anyMatch(c -> c.isInstance(cause)));
                    throw cause;
                } finally {
                    if (tx != null) {
                        if (commit) db.addAll(pending);
                        pending = null;
                    }
                }
            });
    }

    interface Call { void run() throws Exception; }

    static void show(String label, int expectedRows, Call call) {
        db.clear();
        try { call.run(); } catch (Exception e) { /* error memang sengaja */ }
        System.out.println(label + " -> sisa di DB: " + db);
        if (db.size() != expectedRows) throw new AssertionError(label);
    }

    public static void main(String[] args) {
        Orders orders = proxy(new OrderService());
        show("1. dipanggil lewat proxy      (aman)", 0, orders::importOne);
        show("2. dipanggil lewat this       (BUG) ", 1, orders::importViaThis);
        show("3. checked exception, default (BUG) ", 1, orders::payDefault);
        show("4. rollbackFor                (aman)", 0, orders::payFixed);
    }
}
