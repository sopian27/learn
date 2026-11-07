import java.util.function.Supplier;

// Lesson 4.3: bug singleton menyimpan state per-user, dan prototype yang "nempel" di singleton.
// Tanpa Spring: "container" disimulasikan dengan satu instance (singleton) vs new tiap kali (prototype).
// Jalankan: java BeanScopeDemo.java
public class BeanScopeDemo {

    // Bug: service singleton menyimpan data satu request di field.
    static class BuggyCheckoutService {
        private String currentUser; // state per-request di bean yang dipakai semua orang

        String checkout(String user) throws InterruptedException {
            currentUser = user;
            Thread.sleep(50); // request lain masuk di sini
            return "struk untuk " + currentUser;
        }
    }

    // Fix 1: stateless, semua data lewat parameter / variabel lokal.
    static class FixedCheckoutService {
        String checkout(String user) throws InterruptedException {
            Thread.sleep(50);
            return "struk untuk " + user;
        }
    }

    // Bean prototype: harusnya satu per pemakaian.
    static class ShoppingCart {
        private static int created = 0;
        final int id = ++created;
    }

    // Pitfall: prototype di-inject sekali ke singleton, jadi "prototype"-nya beku.
    static class StuckCartService {
        private final ShoppingCart cart;

        StuckCartService(ShoppingCart cart) {
            this.cart = cart;
        }

        int cartId() {
            return cart.id;
        }
    }

    // Fix 2: minta instance baru tiap dipakai (di Spring:
    // ObjectProvider<ShoppingCart>).
    static class FreshCartService {
        private final Supplier<ShoppingCart> carts;

        FreshCartService(Supplier<ShoppingCart> carts) {
            this.carts = carts;
        }

        int cartId() {
            return carts.get().id;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--- Singleton dengan state per-user (bug) ---");
        BuggyCheckoutService buggy = new BuggyCheckoutService(); // satu instance, dipakai 2 thread
        runTwoUsers(buggy::checkout);

        System.out.println("--- Singleton stateless (fix) ---");
        FixedCheckoutService fixed = new FixedCheckoutService();
        runTwoUsers(fixed::checkout);

        System.out.println("--- Prototype di-inject ke singleton ---");
        StuckCartService stuck = new StuckCartService(new ShoppingCart());
        System.out.println("pemanggilan 1 -> cart #" + stuck.cartId() + ", pemanggilan 2 -> cart #" + stuck.cartId()
                + " (sama terus)");

        System.out.println("--- Prototype lewat provider ---");
        FreshCartService fresh = new FreshCartService(ShoppingCart::new);
        System.out.println("pemanggilan 1 -> cart #" + fresh.cartId() + ", pemanggilan 2 -> cart #" + fresh.cartId()
                + " (baru tiap kali)");
    }

    interface Checkout {
        String run(String user) throws Exception;
    }

    static void runTwoUsers(Checkout c) throws InterruptedException {
        Thread t1 = new Thread(() -> print(c, "Rani"));
        Thread t2 = new Thread(() -> print(c, "Akbar"));
        t1.start();
        Thread.sleep(10);
        t2.start();
        t1.join();
        t2.join();
    }

    static void print(Checkout c, String user) {
        try {
            System.out.println(user + " -> " + c.run(user));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
