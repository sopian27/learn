import java.lang.reflect.Field;
import java.util.Objects;

// Lesson 4.2: tiga gaya DI tanpa Spring. Jalankan: java DiInjectionStylesDemo.java
public class DiInjectionStylesDemo {

    interface PricingRepository {
        int priceOf(String sku);
    }

    // 1. Field injection: tidak ada pintu masuk selain reflection (ini yang
    // dilakukan @Autowired).
    static class FieldOrderService {
        private PricingRepository repo; // bukan final, tidak ada setter/constructor

        int total(String sku, int qty) {
            return repo.priceOf(sku) * qty;
        }
    }

    // 2. Setter injection: dependency bisa terlupa dipanggil.
    static class SetterOrderService {
        private PricingRepository repo;

        void setRepo(PricingRepository repo) {
            this.repo = repo;
        }

        int total(String sku, int qty) {
            return repo.priceOf(sku) * qty;
        }
    }

    // 3. Constructor injection: final + wajib ada saat objek lahir.
    static class CtorOrderService {
        private final PricingRepository repo;

        CtorOrderService(PricingRepository repo) {
            this.repo = Objects.requireNonNull(repo, "repo wajib");
        }

        int total(String sku, int qty) {
            return repo.priceOf(sku) * qty;
        }
    }

    public static void main(String[] args) throws Exception {
        PricingRepository fake = sku -> 10_000; // test double murah, tanpa Mockito

        System.out.println("--- Field: new biasa di unit test ---");
        try {
            new FieldOrderService().total("SKU-1", 2);
        } catch (NullPointerException e) {
            System.out.println("NPE saat dipakai (compiler diam): " + e.getMessage());
        }

        System.out.println("--- Field: baru jalan kalau 'container' menyuntik via reflection ---");
        FieldOrderService f = new FieldOrderService();
        Field field = FieldOrderService.class.getDeclaredField("repo");
        field.setAccessible(true);
        field.set(f, fake);
        System.out.println("total = " + f.total("SKU-1", 2));

        System.out.println("--- Setter: lupa panggil setter ---");
        try {
            new SetterOrderService().total("SKU-1", 2);
        } catch (NullPointerException e) {
            System.out.println("NPE saat dipakai (baru ketahuan di runtime): " + e.getMessage());
        }

        System.out.println("--- Constructor: dependency hilang ---");
        try {
            new CtorOrderService(null);
        } catch (NullPointerException e) {
            System.out.println("gagal saat dibuat: " + e.getMessage());
        }

        System.out.println("--- Constructor: unit test tanpa container ---");
        System.out.println("total = " + new CtorOrderService(fake).total("SKU-1", 2));
    }
}
