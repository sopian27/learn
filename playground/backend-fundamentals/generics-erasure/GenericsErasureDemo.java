import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

/**
 * Companion Lesson 1.1 (Generics, Wildcard, Type Erasure).
 * Run: java GenericsErasureDemo.java   (Java 11+ single-file mode)
 * Domain dummy: Order dan Payment, dua tipe turunan Transaction.
 */
public class GenericsErasureDemo {

    record Transaction(String id, double amount) {}
    record Order(String id, double amount) {}
    record Payment(String id, double amount) {}

    // --- 1. Invariance: List<Order> BUKAN List<Object>/List<Transaction> ---
    static double totalRigid(List<Transaction> list) {
        return list.stream().mapToDouble(Transaction::amount).sum();
    }

    // --- 2. PECS: producer extends. Bisa baca, tidak bisa tulis ---
    static double totalFlexible(List<? extends Number> amounts) {
        double sum = 0;
        for (Number n : amounts) sum += n.doubleValue();
        // amounts.add(1.0); // compile error: tidak tahu tipe persisnya
        return sum;
    }

    // --- 3. PECS: consumer super. Bisa tulis, baca cuma sebagai Object ---
    static void fillWithDefaults(List<? super Integer> sink) {
        sink.add(0);
        sink.add(1);
    }

    // --- 4. Bounded type: boleh compareTo karena T extends Comparable<T> ---
    static <T extends Comparable<T>> T max(List<T> list) {
        T best = list.get(0);
        for (T t : list) if (t.compareTo(best) > 0) best = t;
        return best;
    }

    // --- 5. Bug erasure: generic array via cast ---
    @SuppressWarnings("unchecked")
    static <T> T[] toArrayBroken(List<T> list) {
        return (T[]) list.toArray(new Object[0]); // runtime-nya Object[], bukan String[]
    }

    // Fix: caller oper tipe runtime lewat IntFunction (atau Class<T>)
    static <T> T[] toArrayFixed(List<T> list, IntFunction<T[]> maker) {
        return list.toArray(maker.apply(0));
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(new Order("O-1", 100), new Order("O-2", 250));
        // totalRigid(orders); // compile error: List<Order> bukan List<Transaction>

        System.out.println("total wildcard = " + totalFlexible(List.of(1, 2, 3)));
        System.out.println("total wildcard = " + totalFlexible(List.of(1.5, 2.5)));

        List<Number> sink = new ArrayList<>();
        fillWithDefaults(sink);
        System.out.println("sink = " + sink);

        System.out.println("max = " + max(List.of("budi", "sari", "adi")));

        // Erasure: dua tipe berbeda, class runtime sama
        List<String> s = new ArrayList<>();
        List<Integer> i = new ArrayList<>();
        System.out.println("class sama? " + (s.getClass() == i.getClass())); // true

        // Bug: ClassCastException muncul di CALLER, jauh dari sumber
        try {
            String[] arr = toArrayBroken(List.of("a", "b"));
            System.out.println(arr.length);
        } catch (ClassCastException e) {
            System.out.println("BUG erasure: " + e.getMessage());
        }

        String[] ok = toArrayFixed(List.of("a", "b"), String[]::new);
        System.out.println("fixed = " + ok.length + " elemen, tipe runtime " + ok.getClass().getSimpleName());
        System.out.println(Array.getLength(ok));
    }
}
