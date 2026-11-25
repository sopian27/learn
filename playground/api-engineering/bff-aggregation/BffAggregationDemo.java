import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Demo BFF: layar home mobile butuh data dari 3 service.
 * [1] tanpa BFF: client panggil 3 service berurutan (3 round trip dari HP).
 * [2] dengan BFF: 1 panggilan dari client, BFF memanggil 3 service paralel di jaringan internal, bentuk respons pas untuk mobile.
 * [3] service promo mati: BFF degradasi (promo kosong), home tetap tampil.
 * Run: java BffAggregationDemo.java
 */
public class BffAggregationDemo {

    static final int SERVICE_MS = 100;

    static String userService() { sleep(SERVICE_MS); return "Rani"; }
    static List<String> orderService() { sleep(SERVICE_MS); return List.of("ORD-1", "ORD-2"); }
    static String promoService(boolean down) {
        sleep(SERVICE_MS);
        if (down) throw new IllegalStateException("promo-service down");
        return "Diskon 20%";
    }

    record MobileHome(String name, int activeOrders, String promo) {}

    static MobileHome sequential() {            // yang dilakukan client tanpa BFF
        return new MobileHome(userService(), orderService().size(), promoService(false));
    }

    static MobileHome bff(boolean promoDown) {  // paralel + degradasi
        var user = CompletableFuture.supplyAsync(BffAggregationDemo::userService);
        var orders = CompletableFuture.supplyAsync(BffAggregationDemo::orderService);
        var promo = CompletableFuture.supplyAsync(() -> promoService(promoDown)).exceptionally(e -> "-");
        return new MobileHome(user.join(), orders.join().size(), promo.join());
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    static void time(String label, Supplier<MobileHome> call) {
        long t = System.currentTimeMillis();
        MobileHome h = call.get();
        System.out.println("    " + label + " -> " + h + " (" + (System.currentTimeMillis() - t) + "ms)");
    }

    public static void main(String[] args) {
        System.out.println("[1] tanpa BFF: 3 panggilan berurutan");
        time("client", BffAggregationDemo::sequential);
        System.out.println("[2] dengan BFF: 1 panggilan, 3 service paralel");
        time("bff   ", () -> bff(false));
        System.out.println("[3] promo-service mati");
        time("bff   ", () -> bff(true));
    }
}
