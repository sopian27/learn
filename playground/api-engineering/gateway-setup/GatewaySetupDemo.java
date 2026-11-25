import java.util.*;
import java.util.function.Function;

/**
 * Demo setup gateway berbasis konfigurasi: route table (predicate path + StripPrefix + timeout) + filter global request ID.
 * Mirip route di Spring Cloud Gateway / Kong, tapi in-memory.
 * [1] route cocok -> prefix dibuang -> service dapat path internal
 * [2] request ID disuntik gateway dan ikut ke service
 * [3] service lambat -> timeout per route -> 504, route lain tetap normal
 * Run: java GatewaySetupDemo.java
 */
public class GatewaySetupDemo {

    record Route(String id, String pathPrefix, int stripSegments, long timeoutMs, Function<Map<String, String>, String> service) {}
    record Resp(int status, String body) {}

    static final List<Route> ROUTES = List.of(
            new Route("orders", "/api/orders", 1, 200, ctx -> "order-service path=" + ctx.get("path") + " rid=" + ctx.get("rid")),
            new Route("reports", "/api/reports", 1, 200, ctx -> { sleep(500); return "report-service selesai"; }));

    static int counter = 0;

    static Resp handle(String path) {
        Optional<Route> match = ROUTES.stream().filter(r -> path.startsWith(r.pathPrefix())).findFirst();
        if (match.isEmpty()) return new Resp(404, "no route");
        Route r = match.get();

        // StripPrefix: /api/orders/42 -> /orders/42
        String[] seg = path.split("/");
        String internal = "/" + String.join("/", Arrays.copyOfRange(seg, 1 + r.stripSegments(), seg.length));
        Map<String, String> ctx = Map.of("path", internal, "rid", "req-" + (++counter));

        // timeout per route: service lambat tidak menahan koneksi selamanya
        long start = System.currentTimeMillis();
        String body = r.service().apply(ctx);
        long took = System.currentTimeMillis() - start;
        if (took > r.timeoutMs()) return new Resp(504, "gateway timeout route=" + r.id() + " (" + took + "ms > " + r.timeoutMs() + "ms)");
        return new Resp(200, body);
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public static void main(String[] args) {
        System.out.println("[1] route + StripPrefix");
        System.out.println("    " + handle("/api/orders/42"));
        System.out.println("    " + handle("/api/unknown/1"));

        System.out.println("[2] request ID beda tiap request");
        System.out.println("    " + handle("/api/orders/43"));

        System.out.println("[3] timeout per route");
        System.out.println("    " + handle("/api/reports/monthly"));
        System.out.println("    " + handle("/api/orders/44"));
    }
}
