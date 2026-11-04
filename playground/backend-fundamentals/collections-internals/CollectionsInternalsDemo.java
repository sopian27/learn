import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Companion Lesson 1.2 (Collections internals, mutable key, hashCode/equals).
 * Run: java CollectionsInternalsDemo.java   (Java 11+ single-file mode)
 * Domain dummy: cache customer dan SKU produk.
 */
public class CollectionsInternalsDemo {

    // Key MUTABLE: hashCode dihitung dari field yang bisa berubah.
    static class CustomerKey {
        String email;
        CustomerKey(String email) { this.email = email; }
        @Override public boolean equals(Object o) {
            return o instanceof CustomerKey k && Objects.equals(email, k.email);
        }
        @Override public int hashCode() { return Objects.hash(email); }
    }

    // equals di-override, hashCode LUPA. Kontrak rusak.
    static class BrokenSku {
        final String code;
        BrokenSku(String code) { this.code = code; }
        @Override public boolean equals(Object o) {
            return o instanceof BrokenSku s && code.equals(s.code);
        }
    }

    // Fix: record bikin equals/hashCode dari field final.
    record Sku(String code) {}

    public static void main(String[] args) {
        // --- 1. Mutable key: entry "hilang" setelah key diubah ---
        Map<CustomerKey, String> cache = new HashMap<>();
        CustomerKey key = new CustomerKey("raka@mail.com");
        cache.put(key, "Premium");
        key.email = "raka@kantor.com"; // key diubah SETELAH masuk map
        System.out.println("get setelah mutasi  = " + cache.get(key));            // null
        System.out.println("containsKey         = " + cache.containsKey(key));    // false
        System.out.println("size (entry masih)  = " + cache.size());              // 1: bocor memori
        System.out.println("key                 = " + key.email);

        // --- 2. equals tanpa hashCode: HashSet gak nemu ---
        Set<BrokenSku> broken = new HashSet<>();
        broken.add(new BrokenSku("SKU-1"));
        System.out.println("broken contains     = " + broken.contains(new BrokenSku("SKU-1"))); // false (hampir pasti)

        Set<Sku> fixed = new HashSet<>();
        fixed.add(new Sku("SKU-1"));
        System.out.println("record contains     = " + fixed.contains(new Sku("SKU-1")));        // true

        // --- 3. TreeSet pakai compareTo, HashSet pakai equals/hashCode ---
        BigDecimal a = new BigDecimal("2.0"), b = new BigDecimal("2.00");
        System.out.println("HashSet size        = " + new HashSet<>(List.of(a, b)).size());  // 2
        System.out.println("TreeSet size        = " + new TreeSet<>(List.of(a, b)).size());  // 1

        // --- 4. ArrayList vs LinkedList: akses per index ---
        int n = 50_000;
        List<Integer> array = new ArrayList<>(), linked = new LinkedList<>();
        for (int i = 0; i < n; i++) { array.add(i); linked.add(i); }
        System.out.println("ArrayList  get(i) x n = " + timeMs(array) + " ms");
        System.out.println("LinkedList get(i) x n = " + timeMs(linked) + " ms"); // jauh lebih lambat
    }

    static long timeMs(List<Integer> list) {
        long start = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < list.size(); i++) sum += list.get(i);
        return (System.nanoTime() - start) / 1_000_000 + (sum < 0 ? 1 : 0);
    }
}
