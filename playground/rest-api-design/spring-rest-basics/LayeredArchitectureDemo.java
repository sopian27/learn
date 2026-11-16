import java.util.*;

// Meniru Controller -> Service -> Repository tanpa Spring.
// Fat: aturan bisnis ditulis di controller, jadi job import terpaksa menyalin (dan melenceng).
// Layered: controller cuma urus HTTP, aturan ada di service, importer ikut memanggil service yang sama.
public class LayeredArchitectureDemo {
    record Order(int id, String item, int qty, double total) {}

    // Repository: cuma simpan dan ambil.
    static class OrderRepository {
        final Map<Integer, Order> data = new TreeMap<>();
        int next = 1;
        Order save(String item, int qty, double total) {
            Order o = new Order(next++, item, qty, total);
            data.put(o.id(), o);
            return o;
        }
    }

    // Service: aturan bisnis (validasi qty, diskon 10% kalau qty >= 10).
    static class OrderService {
        final OrderRepository repo;
        OrderService(OrderRepository repo) { this.repo = repo; }
        Order create(String item, int qty, double price) {
            if (qty <= 0) throw new IllegalArgumentException("qty harus > 0");
            double total = qty * price * (qty >= 10 ? 0.9 : 1.0);
            return repo.save(item, qty, total);
        }
    }

    // Controller tipis: terjemahkan input HTTP -> panggil service -> terjemahkan hasil ke status code.
    static class ThinController {
        final OrderService service;
        ThinController(OrderService service) { this.service = service; }
        String post(String item, int qty, double price) {
            try { return "201 " + service.create(item, qty, price); }
            catch (IllegalArgumentException e) { return "400 " + e.getMessage(); }
        }
    }

    // Controller gemuk: aturan bisnis dan penyimpanan ada di sini.
    static class FatController {
        final OrderRepository repo;
        FatController(OrderRepository repo) { this.repo = repo; }
        String post(String item, int qty, double price) {
            if (qty <= 0) return "400 qty harus > 0";
            double total = qty * price * (qty >= 10 ? 0.9 : 1.0);
            return "201 " + repo.save(item, qty, total);
        }
    }

    // Job import CSV malam hari. Versi gemuk: aturan disalin, dan sudah melenceng (> 10, bukan >= 10; tanpa validasi qty).
    static void importFat(OrderRepository repo, String item, int qty, double price) {
        double total = qty * price * (qty > 10 ? 0.9 : 1.0);
        System.out.println("  import (salinan aturan) -> " + repo.save(item, qty, total));
    }

    static void importLayered(OrderService service, String item, int qty, double price) {
        System.out.println("  import (panggil service) -> " + service.create(item, qty, price));
    }

    public static void main(String[] a) {
        System.out.println("== Controller gemuk ==");
        OrderRepository r1 = new OrderRepository();
        FatController fat = new FatController(r1);
        System.out.println("  POST qty=10 -> " + fat.post("Kopi", 10, 10_000));
        importFat(r1, "Kopi", 10, 10_000);
        importFat(r1, "Kopi", -3, 10_000); // lolos, karena validasi nggak ikut tersalin

        System.out.println("== Controller tipis + service ==");
        OrderRepository r2 = new OrderRepository();
        OrderService service = new OrderService(r2);
        ThinController thin = new ThinController(service);
        System.out.println("  POST qty=10 -> " + thin.post("Kopi", 10, 10_000));
        importLayered(service, "Kopi", 10, 10_000);
        try { importLayered(service, "Kopi", -3, 10_000); }
        catch (IllegalArgumentException e) { System.out.println("  import qty=-3 ditolak: " + e.getMessage()); }
        System.out.println("  POST qty=0 -> " + thin.post("Kopi", 0, 10_000));
    }
}
