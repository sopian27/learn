import java.util.*;

// Lesson 11.1: unit test service layer dengan repository palsu. Plain Java, tanpa Mockito.
public class ServiceUnitTestDemo {

    record Order(long id, String status) {}

    interface OrderRepository {
        Optional<Order> findById(long id);
        void save(Order o);
    }

    // Versi BUG: lupa cek SHIPPED. Versi benar: lihat cancelFixed.
    static class OrderService {
        final OrderRepository repo;
        final boolean fixed;
        OrderService(OrderRepository repo, boolean fixed) { this.repo = repo; this.fixed = fixed; }

        void cancel(long id) {
            Order o = repo.findById(id).orElseThrow(() -> new NoSuchElementException("order " + id)); // di Spring: 404
            if (fixed && o.status().equals("SHIPPED"))
                throw new IllegalStateException("sudah dikirim"); // di Spring: 409
            repo.save(new Order(o.id(), "CANCELLED"));
        }
    }

    // Repository palsu: data di-set test, save() dicatat supaya bisa dicek.
    static class FakeRepo implements OrderRepository {
        final Map<Long, Order> db = new HashMap<>();
        final List<Order> saved = new ArrayList<>();
        public Optional<Order> findById(long id) { return Optional.ofNullable(db.get(id)); }
        public void save(Order o) { saved.add(o); }
    }

    static void check(String name, boolean ok) { System.out.println((ok ? "PASS  " : "FAIL  ") + name); }

    static void run(String label, boolean fixed) {
        System.out.println("== " + label);
        // Test jelek: cuma cek "save dipanggil", tidak mengecek aturan bisnis -> lulus walau bug.
        var r1 = new FakeRepo(); r1.db.put(1L, new Order(1, "PAID"));
        new OrderService(r1, fixed).cancel(1);
        check("[jelek] cancel memanggil save()", r1.saved.size() == 1);

        // Test bagus 1: happy path, cek HASIL (status baru), bukan cara internal.
        check("[bagus] PAID -> CANCELLED", r1.saved.get(0).status().equals("CANCELLED"));

        // Test bagus 2: aturan bisnis. Order SHIPPED tidak boleh dibatalkan dan tidak boleh disimpan.
        var r2 = new FakeRepo(); r2.db.put(2L, new Order(2, "SHIPPED"));
        boolean threw = false;
        try { new OrderService(r2, fixed).cancel(2); } catch (IllegalStateException e) { threw = true; }
        check("[bagus] SHIPPED ditolak dan tidak disimpan", threw && r2.saved.isEmpty());

        // Test bagus 3: order tidak ada.
        boolean notFound = false;
        try { new OrderService(new FakeRepo(), fixed).cancel(99); } catch (NoSuchElementException e) { notFound = true; }
        check("[bagus] id tidak ada -> NoSuchElementException", notFound);
    }

    public static void main(String[] args) {
        run("service BUG (lupa cek SHIPPED)", false);
        run("service FIXED", true);
    }
}
