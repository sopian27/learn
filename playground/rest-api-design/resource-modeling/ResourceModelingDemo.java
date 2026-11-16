import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Order Management: order punya items (nested, hidup-matinya ikut order),
// product berdiri sendiri (cukup direferensikan), aksi jadi noun (payments).
public class ResourceModelingDemo {
    static final Map<Integer, List<String>> items = new HashMap<>(); // orderId -> items (JSON)
    static int nextOrder = 1;

    static void reply(HttpExchange x, int code, String loc, String body) {
        try {
            byte[] b = body.getBytes(StandardCharsets.UTF_8);
            x.getResponseHeaders().add("Content-Type", "application/json");
            if (loc != null) x.getResponseHeaders().add("Location", loc);
            x.sendResponseHeaders(code, b.length == 0 ? -1 : b.length);
            if (b.length > 0) x.getResponseBody().write(b);
            x.close();
        } catch (java.io.IOException e) { throw new RuntimeException(e); }
    }

    // Routing minimal: /orders, /orders/{id}, /orders/{id}/items, /orders/{id}/items/{n}, /orders/{id}/payments
    static void orders(HttpExchange x) {
        String[] p = x.getRequestURI().getPath().split("/"); // ["", "orders", id, sub, n]
        String m = x.getRequestMethod();
        if (p.length == 2) {
            if (m.equals("POST")) { int id = nextOrder++; items.put(id, new ArrayList<>()); reply(x, 201, "/orders/" + id, "{\"id\":" + id + "}"); }
            else reply(x, 200, null, "{\"filter\":\"" + String.valueOf(x.getRequestURI().getQuery()) + "\"}");
            return;
        }
        int id = Integer.parseInt(p[2]);
        if (!items.containsKey(id)) { reply(x, 404, null, "{\"error\":\"order not found\"}"); return; }
        if (p.length == 3) { reply(x, 200, null, "{\"id\":" + id + ",\"items\":\"/orders/" + id + "/items\"}"); return; }
        List<String> list = items.get(id);
        switch (p[3]) {
            case "items" -> {
                if (p.length == 4 && m.equals("POST")) {
                    String in = new String(readAll(x));
                    list.add(in);
                    reply(x, 201, "/orders/" + id + "/items/" + list.size(), in);
                } else if (p.length == 4) reply(x, 200, null, "[" + String.join(",", list) + "]");
                else {
                    int n = Integer.parseInt(p[4]);
                    if (n < 1 || n > list.size()) reply(x, 404, null, "{\"error\":\"item not found\"}");
                    else reply(x, 200, null, list.get(n - 1));
                }
            }
            // Aksi "bayar" dimodelkan sebagai resource baru, bukan /payOrder.
            case "payments" -> reply(x, 201, "/orders/" + id + "/payments/1", "{\"status\":\"PAID\"}");
            default -> reply(x, 404, null, "{\"error\":\"unknown sub-resource\"}");
        }
    }

    static byte[] readAll(HttpExchange x) {
        try { return x.getRequestBody().readAllBytes(); } catch (java.io.IOException e) { throw new RuntimeException(e); }
    }

    public static void main(String[] a) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/orders", ResourceModelingDemo::orders);
        s.start();
        String base = "http://localhost:" + s.getAddress().getPort();
        HttpClient c = HttpClient.newHttpClient();
        String item = "{\"productId\":10,\"product\":\"/products/10\",\"qty\":2}"; // referensi, bukan nested product

        show(c, "POST /orders", req(base, "/orders").POST(body("")));
        show(c, "POST /orders/1/items", req(base, "/orders/1/items").POST(body(item)));
        show(c, "GET  /orders/1/items", req(base, "/orders/1/items").GET());
        show(c, "GET  /orders/1/items/1", req(base, "/orders/1/items/1").GET());
        show(c, "GET  /orders/1/items/9", req(base, "/orders/1/items/9").GET());
        show(c, "GET  /orders/999/items", req(base, "/orders/999/items").GET());
        show(c, "GET  /orders?customerId=5", req(base, "/orders?customerId=5").GET());
        show(c, "POST /orders/1/payments", req(base, "/orders/1/payments").POST(body("")));
        s.stop(0);
    }

    static HttpRequest.Builder req(String base, String path) { return HttpRequest.newBuilder(URI.create(base + path)); }
    static HttpRequest.BodyPublisher body(String s) { return HttpRequest.BodyPublishers.ofString(s); }

    static void show(HttpClient c, String label, HttpRequest.Builder b) throws Exception {
        HttpResponse<String> r = c.send(b.build(), HttpResponse.BodyHandlers.ofString());
        System.out.printf("%-26s -> %d %s %s%n", label, r.statusCode(),
            r.headers().firstValue("Location").map(l -> "Location:" + l).orElse(""), r.body());
    }
}
