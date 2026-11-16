import java.util.*;
import java.util.stream.*;

// Lesson 4.4: over-fetching (response kegemukan) dan under-fetching (butuh banyak request).
// Tanpa Spring/DB; "round trip" dihitung manual, ukuran response = panjang string JSON.
public class OverUnderFetchingDemo {
    record Customer(long id, String name, String address, String phone) {}
    record OrderRow(long id, long customerId, String status, long total, String notes, String itemsJson) {}

    // DTO "salinan tabel": semua kolom keluar.
    record OrderFull(long id, long customerId, String status, long total, String notes, String itemsJson) {}
    // DTO dari kebutuhan layar daftar mobile: id, nama customer, status, total.
    record OrderListItem(long id, String customerName, String status, long total) {}

    static final Map<Long, Customer> CUSTOMERS = new HashMap<>();
    static final List<OrderRow> ORDERS = new ArrayList<>();
    static int roundTrips = 0;

    static String json(Record r) throws Exception {
        StringJoiner sj = new StringJoiner(",", "{", "}");
        for (var c : r.getClass().getRecordComponents())
            sj.add("\"" + c.getName() + "\":\"" + c.getAccessor().invoke(r) + "\"");
        return sj.toString();
    }

    // Endpoint A: GET /orders -> salinan tabel.
    static List<OrderFull> listFull() {
        roundTrips++;
        return ORDERS.stream().map(o -> new OrderFull(o.id(), o.customerId(), o.status(), o.total(), o.notes(), o.itemsJson())).toList();
    }
    // Endpoint B: GET /customers/{id}.
    static Customer customer(long id) { roundTrips++; return CUSTOMERS.get(id); }
    // Endpoint C: GET /orders?view=list -> dirancang dari layar daftar (join sekali di server).
    static List<OrderListItem> listForScreen() {
        roundTrips++;
        return ORDERS.stream().map(o -> new OrderListItem(o.id(), CUSTOMERS.get(o.customerId()).name(), o.status(), o.total())).toList();
    }

    public static void main(String[] args) throws Exception {
        for (long i = 1; i <= 5; i++)
            CUSTOMERS.put(i, new Customer(i, "Customer " + i, "Jl. Panjang Sekali No. " + i + ", Jakarta", "0812000000" + i));
        for (long i = 1; i <= 20; i++)
            ORDERS.add(new OrderRow(i, (i % 5) + 1, "PAID", 100_000 + i,
                    "catatan internal gudang panjang panjang panjang panjang " + i,
                    "[{sku:A1,qty:2},{sku:B7,qty:1},{sku:C3,qty:4},{sku:D9,qty:1}]"));

        System.out.println("== 1. Salinan tabel: over-fetching + under-fetching ==");
        roundTrips = 0;
        int bytes = 0;
        List<OrderFull> full = listFull();
        for (OrderFull o : full) bytes += json(o).length();
        System.out.println("layar daftar butuh: id, nama customer, status, total");
        System.out.println("response /orders: " + bytes + " karakter, 20 order, tiap order bawa notes + itemsJson (nggak dipakai layar)");
        Set<Long> seen = new HashSet<>();
        for (OrderFull o : full) if (seen.add(o.customerId())) bytes += json(customer(o.customerId())).length();
        System.out.println("nama customer cuma ada di customerId -> tambah request /customers/{id}");
        System.out.println("round trip: " + roundTrips + ", total: " + bytes + " karakter\n");

        System.out.println("== 2. DTO dari kebutuhan layar ==");
        roundTrips = 0;
        int bytes2 = 0;
        List<OrderListItem> items = listForScreen();
        for (OrderListItem o : items) bytes2 += json(o).length();
        System.out.println("round trip: " + roundTrips + ", total: " + bytes2 + " karakter");
        System.out.println("contoh: " + json(items.get(0)));
        System.out.printf("hemat: %d%% karakter, %d round trip%n", 100 - (bytes2 * 100 / bytes), 1 + seen.size() - 1);

        System.out.println("\n== 3. Harga yang dibayar ==");
        System.out.println("layar lain (detail order) butuh itemsJson + notes -> endpoint GET /orders/{id} terpisah,");
        System.out.println("bukan menjejalkan semua ke list. Satu DTO per kebutuhan, bukan satu DTO untuk semua.");
    }
}
