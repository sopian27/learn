import com.sun.net.httpserver.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;

// Demo Lesson 5.1: kontrak REST asal-asalan vs konsisten. Jalankan: java RestContractDemo.java
public class RestContractDemo {
    record Order(int id, String status, long amount) {}
    static final Map<Integer, Order> DB = new TreeMap<>(Map.of(
        1, new Order(1, "PAID", 500), 2, new Order(2, "NEW", 200), 3, new Order(3, "PAID", 900),
        4, new Order(4, "PAID", 100), 5, new Order(5, "NEW", 300)));

    static void send(HttpExchange ex, int code, String body) throws java.io.IOException {
        if (code == 204) { ex.sendResponseHeaders(204, -1); ex.close(); return; }
        byte[] b = body.getBytes();
        ex.getResponseHeaders().add("Content-Type", "application/json");
        ex.sendResponseHeaders(code, b.length);
        ex.getResponseBody().write(b);
        ex.close();
    }

    static Map<String, String> query(String raw) {
        Map<String, String> q = new HashMap<>();
        if (raw != null) for (String kv : raw.split("&")) { var p = kv.split("=", 2); q.put(p[0], p[1]); }
        return q;
    }

    public static void main(String[] args) throws Exception {
        HttpServer s = HttpServer.create(new java.net.InetSocketAddress(8089), 0);

        // VERSI ASAL-ASALAN: verb di URL, semua 200, error cuma di body
        s.createContext("/createOrder", ex -> send(ex, 200, "{\"success\":false,\"msg\":\"amount invalid\"}"));
        s.createContext("/getOrders", ex -> send(ex, 200, DB.values().toString())); // tanpa pagination

        // VERSI KONSISTEN: noun jamak, method + status code bermakna, pagination/filter/sort lewat query
        s.createContext("/orders", ex -> {
            String m = ex.getRequestMethod(), path = ex.getRequestURI().getPath();
            var q = query(ex.getRequestURI().getRawQuery());
            if (path.equals("/orders") && m.equals("GET")) {
                String sort = q.getOrDefault("sort", "id");
                if (!List.of("id", "amount").contains(sort)) { send(ex, 400, "{\"code\":\"INVALID_SORT\"}"); return; }
                int page = Integer.parseInt(q.getOrDefault("page", "0"));
                int size = Math.min(Integer.parseInt(q.getOrDefault("size", "2")), 100); // cap size
                var all = DB.values().stream()
                    .filter(o -> !q.containsKey("status") || o.status().equals(q.get("status")))
                    .sorted(sort.equals("amount") ? Comparator.comparingLong(Order::amount) : Comparator.comparingInt(Order::id))
                    .toList();
                var slice = all.stream().skip((long) page * size).limit(size).toList();
                send(ex, 200, "{\"data\":" + slice + ",\"page\":" + page + ",\"size\":" + size + ",\"total\":" + all.size() + "}");
            } else if (path.equals("/orders") && m.equals("POST")) {
                ex.getResponseHeaders().add("Location", "/orders/6");
                send(ex, 201, "{\"id\":6}");
            } else if (path.matches("/orders/[0-9]+")) {
                int id = Integer.parseInt(path.substring(8));
                if (!DB.containsKey(id)) { send(ex, 404, "{\"code\":\"ORDER_NOT_FOUND\"}"); return; }
                if (m.equals("GET")) send(ex, 200, DB.get(id).toString());
                else if (m.equals("DELETE")) send(ex, 204, "");
                else send(ex, 405, "{\"code\":\"METHOD_NOT_ALLOWED\"}");
            } else send(ex, 404, "{\"code\":\"NOT_FOUND\"}");
        });
        s.start();

        var http = HttpClient.newHttpClient();
        String[][] calls = {
            {"POST", "/createOrder"}, {"GET", "/getOrders"},
            {"GET", "/orders?status=PAID&page=0&size=2&sort=amount"}, {"GET", "/orders?sort=password"},
            {"POST", "/orders"}, {"GET", "/orders/99"}, {"DELETE", "/orders/1"}};
        for (String[] c : calls) {
            var req = HttpRequest.newBuilder(URI.create("http://localhost:8089" + c[1]))
                .method(c[0], HttpRequest.BodyPublishers.noBody()).build();
            var res = http.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.printf("%-6s %-52s -> %d %s%n", c[0], c[1], res.statusCode(), res.body());
        }
        s.stop(0);
    }
}
