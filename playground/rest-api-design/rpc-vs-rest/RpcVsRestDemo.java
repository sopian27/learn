import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Tiga cara bikin "batalkan order": PATCH status mentah vs POST /orders/{id}/cancel.
// Yang dilihat: PATCH naif nerima loncatan status yang nggak masuk akal, /cancel menjaga aturan bisnis.
public class RpcVsRestDemo {
    static final Map<Integer, String> orders = new HashMap<>(Map.of(1, "PAID", 2, "SHIPPED", 3, "NEW"));

    static void reply(HttpExchange x, int code, String body) throws java.io.IOException {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        x.sendResponseHeaders(code, b.length);
        x.getResponseBody().write(b);
        x.close();
    }

    static void handle(HttpExchange x) throws java.io.IOException {
        String[] p = x.getRequestURI().getPath().split("/"); // ["", "orders", "{id}", "cancel"?]
        int id = Integer.parseInt(p[2]);
        String status = orders.get(id);
        if (status == null) { reply(x, 404, "{}"); return; }
        String method = x.getRequestMethod();

        if (p.length == 4 && p[3].equals("cancel") && method.equals("POST")) {
            if (status.equals("SHIPPED") || status.equals("DELIVERED")) {
                reply(x, 409, "{\"error\":\"order sudah " + status + ", nggak bisa dibatalkan\"}");
                return;
            }
            orders.put(id, "CANCELLED"); // sudah CANCELLED juga balas 200: cancel dua kali = idempotent
            reply(x, 200, "{\"status\":\"CANCELLED\"}");
        } else if (p.length == 3 && method.equals("PATCH")) {
            // shortcut: PATCH naif, nggak ada aturan transisi. Di produksi validasi di sini atau pakai endpoint aksi.
            String body = new String(x.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String next = body.replaceAll(".*\"status\"\\s*:\\s*\"(\\w+)\".*", "$1");
            orders.put(id, next);
            reply(x, 200, "{\"status\":\"" + next + "\"}");
        } else {
            reply(x, 405, "{}");
        }
    }

    public static void main(String[] a) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/orders", x -> {
            try { handle(x); } catch (java.io.IOException e) { throw new RuntimeException(e); }
        });
        s.start();
        String base = "http://localhost:" + s.getAddress().getPort();
        HttpClient c = HttpClient.newHttpClient();

        call(c, base, "PATCH", "/orders/3", "{\"status\":\"DELIVERED\"}", "PATCH naif: NEW loncat ke DELIVERED, diterima");
        call(c, base, "PATCH", "/orders/2", "{\"status\":\"PAID\"}",      "PATCH naif: SHIPPED mundur ke PAID, diterima");
        call(c, base, "POST",  "/orders/1/cancel", "",                   "cancel order PAID");
        call(c, base, "POST",  "/orders/1/cancel", "",                   "cancel lagi (idempotent)");
        orders.put(2, "SHIPPED");
        call(c, base, "POST",  "/orders/2/cancel", "",                   "cancel order SHIPPED, aturan bisnis menolak");
        s.stop(0);
    }

    static void call(HttpClient c, String base, String method, String path, String body, String note) throws Exception {
        HttpResponse<String> r = c.send(HttpRequest.newBuilder(URI.create(base + path))
            .method(method, HttpRequest.BodyPublishers.ofString(body)).build(), HttpResponse.BodyHandlers.ofString());
        System.out.printf("%-6s %-18s -> %d %-34s %s%n", method, path, r.statusCode(), r.body(), note);
    }
}
