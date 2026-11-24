import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Lesson 3.3: N+1 di resolver relasi vs batching. Simulasi tanpa Spring/DB; "query" = hitungan panggilan.
public class NPlusOneDemo {

    static int queries;

    static final List<String> ORDER_IDS = IntStream.rangeClosed(1, 50).mapToObj(i -> "o-" + i).toList();

    // "DB": satu item per order, kecuali o-3 yang kosong.
    static List<String> queryItemsFor(String orderId) { queries++; return orderId.equals("o-3") ? List.of() : List.of("item-" + orderId); }

    static Map<String, List<String>> queryItemsForAll(List<String> orderIds) {
        queries++; // satu "WHERE order_id IN (...)"
        Map<String, List<String>> byOrder = new LinkedHashMap<>();
        for (String id : orderIds) byOrder.put(id, id.equals("o-3") ? List.of() : List.of("item-" + id)); // parent tanpa relasi tetap dapat list kosong
        return byOrder;
    }

    static Map<String, List<String>> naive() {
        queries = 1; // query daftar order
        return ORDER_IDS.stream().collect(Collectors.toMap(id -> id, NPlusOneDemo::queryItemsFor, (a, b) -> a, LinkedHashMap::new));
    }

    static Map<String, List<String>> batched() {
        queries = 1; // query daftar order
        return queryItemsForAll(new ArrayList<>(ORDER_IDS));
    }

    public static void main(String[] args) {
        Map<String, List<String>> a = naive();
        int naiveQueries = queries;
        Map<String, List<String>> b = batched();
        int batchQueries = queries;

        System.out.println("order           : " + ORDER_IDS.size());
        System.out.println("query naif      : " + naiveQueries);
        System.out.println("query batch     : " + batchQueries);
        System.out.println("hasil sama      : " + a.equals(b));

        if (naiveQueries != 51 || batchQueries != 2 || !a.equals(b)) throw new AssertionError("N+1 harus 51 query, batch 2 query, hasil identik");
    }
}
