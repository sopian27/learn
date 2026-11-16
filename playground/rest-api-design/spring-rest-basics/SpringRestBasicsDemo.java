import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Meniru perilaku @GetMapping("/orders/{id}") (path variable) vs @GetMapping("/orders") + @RequestParam (query).
// Yang dilihat: path variable = identitas (nggak ada -> 404), query param = filter (nggak ada hasil -> 200 list kosong).
public class SpringRestBasicsDemo {
    static final Map<Integer, String> orders = new TreeMap<>(Map.of(1, "PAID", 2, "SHIPPED", 3, "NEW", 4, "PAID"));

    static void reply(HttpExchange x, int code, String body) throws java.io.IOException {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        x.sendResponseHeaders(code, b.length);
        x.getResponseBody().write(b);
        x.close();
    }

    static Map<String, String> query(String raw) {
        Map<String, String> m = new HashMap<>();
        if (raw == null) return m;
        for (String kv : raw.split("&")) { String[] p = kv.split("=", 2); m.put(p[0], p.length > 1 ? p[1] : ""); }
        return m;
    }

    static void handle(HttpExchange x) throws java.io.IOException {
        String[] p = x.getRequestURI().getPath().split("/"); // ["", "orders", "{id}"?]
        if (p.length == 3) { // GET /orders/{id}  -> @PathVariable
            String s = orders.get(Integer.parseInt(p[2]));
            if (s == null) reply(x, 404, "{\"error\":\"order " + p[2] + " nggak ada\"}");
            else reply(x, 200, "{\"id\":" + p[2] + ",\"status\":\"" + s + "\"}");
            return;
        }
        // GET /orders?status=PAID&limit=1  -> @RequestParam (opsional)
        Map<String, String> q = query(x.getRequestURI().getRawQuery());
        int limit = Integer.parseInt(q.getOrDefault("limit", "10"));
        List<String> out = new ArrayList<>();
        orders.forEach((id, st) -> { if (!q.containsKey("status") || q.get("status").equals(st)) out.add("{\"id\":" + id + ",\"status\":\"" + st + "\"}"); });
        reply(x, 200, out.subList(0, Math.min(limit, out.size())).toString());
    }

    public static void main(String[] a) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/orders", x -> { try { handle(x); } catch (Exception e) { reply(x, 400, "{\"error\":\"" + e.getMessage() + "\"}"); } });
        s.start();
        String base = "http://localhost:" + s.getAddress().getPort();
        HttpClient c = HttpClient.newHttpClient();
        for (String path : List.of("/orders/1", "/orders/99", "/orders", "/orders?status=PAID", "/orders?status=CANCELLED", "/orders?status=PAID&limit=1")) {
            HttpResponse<String> r = c.send(HttpRequest.newBuilder(URI.create(base + path)).build(), HttpResponse.BodyHandlers.ofString());
            System.out.println("GET " + path + " -> " + r.statusCode() + " " + r.body());
        }
        s.stop(0);
    }
}
