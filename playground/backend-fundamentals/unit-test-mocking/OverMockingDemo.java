import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

// Run: java OverMockingDemo.java
// Dua test untuk logic total checkout. Satu ngecek INTERAKSI (over-mocking), satu ngecek HASIL.
// Lalu logic direfactor (perilaku sama). Test mana yang ikut rusak?
public class OverMockingDemo {

    interface PriceRepo { int priceOf(String sku); }

    interface Checkout { int total(List<String> skus); }

    // Versi 1: tanya repo tiap item.
    record CheckoutV1(PriceRepo repo) implements Checkout {
        public int total(List<String> skus) {
            int sum = 0;
            for (String sku : skus) sum += repo.priceOf(sku);
            return sum;
        }
    }

    // Versi 2 (refactor): memoize harga per sku. Hasil sama, panggilan ke repo berkurang.
    record CheckoutV2(PriceRepo repo) implements Checkout {
        public int total(List<String> skus) {
            Map<String, Integer> memo = new HashMap<>();
            int sum = 0;
            for (String sku : skus) sum += memo.computeIfAbsent(sku, repo::priceOf);
            return sum;
        }
    }

    // MOCK: ngitung panggilan, test ngecek caranya. STUB: cuma ngasih jawaban tetap.
    static class MockRepo implements PriceRepo {
        int calls = 0;
        public int priceOf(String sku) { calls++; return 100; }
    }

    static boolean overMockedTest(Supplier<Checkout> build, MockRepo mock) {
        build.get().total(List.of("A", "A", "A"));
        return mock.calls == 3; // terikat ke implementasi: "harus 3 kali tanya repo"
    }

    static boolean behaviorTest(Checkout c) {
        return c.total(List.of("A", "A", "B")) == 300; // cuma peduli hasil
    }

    public static void main(String[] args) {
        MockRepo m1 = new MockRepo();
        MockRepo m2 = new MockRepo();
        PriceRepo stub = sku -> 100;

        System.out.println("                      V1 (awal)   V2 (refactor)");
        System.out.printf("over-mocked test      %-11s %s%n",
                label(overMockedTest(() -> new CheckoutV1(m1), m1)),
                label(overMockedTest(() -> new CheckoutV2(m2), m2)));
        System.out.printf("behavior test (stub)  %-11s %s%n",
                label(behaviorTest(new CheckoutV1(stub))),
                label(behaviorTest(new CheckoutV2(stub))));
    }

    static String label(boolean ok) { return ok ? "HIJAU" : "MERAH"; }
}
