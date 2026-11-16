import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Demo: kenapa "selalu 200" bikin client buta, dan status code jadi dasar keputusan retry.
// /bad  : semua balik 200, error disembunyikan di body.
// /good : status code jujur (201+Location, 400, 404, 409, 503).
public class HttpStatusCodesDemo {
    static final Set<String> orders = ConcurrentHashMap.newKeySet();
    static int flaky = 0; // /good/report gagal 503 dua kali, ketiga sukses

    public static void main(String[] args) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/bad", ex -> reply(ex, 200, "{\"success\":false,\"error\":\"order not found\"}", null));
        s.createContext("/good/orders", ex -> {
            String id = ex.getRequestURI().getPath().replaceFirst("^/good/orders/?", "");
            if (ex.getRequestMethod().equals("POST")) {
                String newId = "o" + (orders.size() + 1);
                orders.add(newId);
                reply(ex, 201, "created", "/good/orders/" + newId);       // 201 + Location
            } else if (!id.matches("o\\d+")) {
                reply(ex, 400, "id format salah", null);                   // salah di client
            } else if (!orders.contains(id)) {
                reply(ex, 404, "order tidak ada", null);                   // resource tidak ada
            } else {
                reply(ex, 200, "order " + id, null);
            }
        });
        s.createContext("/good/report", ex -> {
            if (++flaky < 3) reply(ex, 503, "busy", null);                 // sementara gagal di server
            else reply(ex, 200, "report ready", null);
        });
        s.start();

        String base = "http://localhost:" + s.getAddress().getPort();
        HttpClient c = HttpClient.newHttpClient();

        System.out.println("== /bad: GET order yang tidak ada ==");
        show(c, "GET", base + "/bad/orders/999");               // 200 -> client kira sukses
        System.out.println("== /good: POST, GET ada, GET format salah, GET tidak ada ==");
        show(c, "POST", base + "/good/orders");
        show(c, "GET", base + "/good/orders/o1");
        show(c, "GET", base + "/good/orders/abc");
        show(c, "GET", base + "/good/orders/o99");
        System.out.println("== /good/report: retry hanya untuk 5xx ==");
        for (int i = 1; i <= 3; i++) if (show(c, "GET", base + "/good/report")) break;
        s.stop(0);
    }

    // true = selesai (jangan retry), false = coba lagi
    static boolean show(HttpClient c, String m, String url) throws Exception {
        var req = HttpRequest.newBuilder(URI.create(url))
                .method(m, m.equals("POST") ? BodyPublishers.ofString("{}") : BodyPublishers.noBody()).build();
        var r = c.send(req, BodyHandlers.ofString());
        int code = r.statusCode();
        String verdict = code < 300 ? "sukses" : code < 500 ? "salah client, JANGAN retry" : "salah server, boleh retry";
        System.out.println("  " + m + " " + url.substring(url.indexOf("/", 8)) + " -> " + code + " " + r.body()
                + r.headers().firstValue("Location").map(l -> "  [Location: " + l + "]").orElse("") + "   => " + verdict);
        return code < 500;
    }

    static void reply(com.sun.net.httpserver.HttpExchange ex, int status, String body, String location) throws java.io.IOException {
        ex.getRequestBody().readAllBytes();
        if (location != null) ex.getResponseHeaders().add("Location", location);
        byte[] b = body.getBytes();
        ex.sendResponseHeaders(status, b.length);
        ex.getResponseBody().write(b);
        ex.close();
    }
}
