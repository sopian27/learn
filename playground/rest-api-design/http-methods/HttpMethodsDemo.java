import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Demo: kenapa retry aman buat GET/PUT/DELETE tapi bikin duplikat di POST.
// Server in-memory: orders disimpan di Map. Client "retry" tiap request 2x
// (simulasi timeout, response hilang, client kirim ulang).
public class HttpMethodsDemo {
    static final Map<Integer, String> orders = new ConcurrentHashMap<>();
    static final AtomicInteger seq = new AtomicInteger(100);

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/orders", ex -> {
            String method = ex.getRequestMethod();
            String path = ex.getRequestURI().getPath();
            String body = new String(ex.getRequestBody().readAllBytes());
            int status = 200;
            String out = "";
            switch (method) {
                case "POST" -> {                       // bukan idempotent: tiap panggilan bikin resource baru
                    int id = seq.incrementAndGet();
                    orders.put(id, body);
                    status = 201;
                    out = "created /orders/" + id;
                }
                case "PUT" -> {                        // idempotent: state akhir sama berapa kali pun dikirim
                    orders.put(Integer.parseInt(path.substring(path.lastIndexOf('/') + 1)), body);
                    out = "stored";
                }
                case "DELETE" -> {                     // idempotent: efek ke server sama (order hilang)
                    orders.remove(Integer.parseInt(path.substring(path.lastIndexOf('/') + 1)));
                    status = 204;
                }
                default -> out = "orders=" + orders;   // GET: safe, tidak mengubah apa pun
            }
            byte[] bytes = out.getBytes();
            ex.sendResponseHeaders(status, status == 204 ? -1 : bytes.length);
            if (status != 204) ex.getResponseBody().write(bytes);
            ex.close();
        });
        server.start();

        String base = "http://localhost:" + server.getAddress().getPort() + "/orders";
        HttpClient client = HttpClient.newHttpClient();

        System.out.println("== GET x2 (safe) ==");
        retry(client, "GET", base, "");
        System.out.println("state: " + orders);

        System.out.println("== PUT x2 (idempotent) ==");
        retry(client, "PUT", base + "/1", "{\"status\":\"PAID\"}");
        System.out.println("state: " + orders);

        System.out.println("== POST x2 (NOT idempotent) ==");
        retry(client, "POST", base, "{\"status\":\"NEW\"}");
        System.out.println("state: " + orders + "   <- duplikat!");

        System.out.println("== DELETE x2 (idempotent) ==");
        retry(client, "DELETE", base + "/1", "");
        System.out.println("state: " + orders);

        server.stop(0);
    }

    // kirim request yang sama dua kali, print status tiap kali
    static void retry(HttpClient c, String method, String url, String body) throws Exception {
        for (int i = 1; i <= 2; i++) {
            var req = HttpRequest.newBuilder(URI.create(url))
                    .method(method, body.isEmpty() ? BodyPublishers.noBody() : BodyPublishers.ofString(body))
                    .build();
            var res = c.send(req, BodyHandlers.ofString());
            System.out.println("  try " + i + ": " + method + " -> " + res.statusCode() + " " + res.body());
        }
    }
}
