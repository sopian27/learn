import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

// Aksi yang sama (batalin order 42, lalu order 99 yang nggak ada) di 4 level Richardson.
public class RichardsonDemo {
    static void reply(com.sun.net.httpserver.HttpExchange x, int code, String body) {
        try {
            byte[] b = body.getBytes(StandardCharsets.UTF_8);
            x.getResponseHeaders().add("Content-Type", "application/json");
            x.sendResponseHeaders(code, b.length == 0 ? -1 : b.length);
            if (b.length > 0) x.getResponseBody().write(b);
            x.close();
        } catch (java.io.IOException e) { throw new RuntimeException(e); }
    }

    public static void main(String[] a) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        // Level 0: satu endpoint, satu method. Error dibungkus di body, status selalu 200.
        s.createContext("/api", x -> {
            String in = new String(x.getRequestBody().readAllBytes());
            reply(x, 200, in.contains("\"id\":42") ? "{\"ok\":true}" : "{\"ok\":false,\"error\":\"not found\"}");
        });
        // Level 1: ada resource (URI per order), tapi masih POST untuk semuanya dan 200 terus.
        s.createContext("/l1/orders/", x -> {
            boolean ada = x.getRequestURI().getPath().endsWith("/42");
            reply(x, 200, ada ? "{\"status\":\"CANCELLED\"}" : "{\"error\":\"not found\"}");
        });
        // Level 2: method + status code bermakna.
        s.createContext("/l2/orders/", x -> {
            boolean ada = x.getRequestURI().getPath().endsWith("/42");
            if (!x.getRequestMethod().equals("DELETE")) { reply(x, 405, ""); return; }
            if (ada) reply(x, 204, ""); else reply(x, 404, "{\"error\":\"order not found\"}");
        });
        // Level 3: Level 2 + hypermedia, server bilang aksi apa yang masih boleh.
        s.createContext("/l3/orders/", x -> {
            boolean ada = x.getRequestURI().getPath().endsWith("/42");
            if (ada) reply(x, 200, "{\"status\":\"CANCELLED\",\"_links\":{\"self\":\"/l3/orders/42\",\"refund\":\"/l3/orders/42/refund\"}}");
            else reply(x, 404, "{\"error\":\"order not found\"}");
        });
        s.start();
        String base = "http://localhost:" + s.getAddress().getPort();
        HttpClient c = HttpClient.newHttpClient();

        show(c, "L0 POST /api (id 42)", HttpRequest.newBuilder(URI.create(base + "/api"))
            .POST(HttpRequest.BodyPublishers.ofString("{\"action\":\"cancelOrder\",\"id\":42}")));
        show(c, "L0 POST /api (id 99)", HttpRequest.newBuilder(URI.create(base + "/api"))
            .POST(HttpRequest.BodyPublishers.ofString("{\"action\":\"cancelOrder\",\"id\":99}")));
        show(c, "L1 POST /l1/orders/99", HttpRequest.newBuilder(URI.create(base + "/l1/orders/99"))
            .POST(HttpRequest.BodyPublishers.noBody()));
        show(c, "L2 DELETE /l2/orders/42", HttpRequest.newBuilder(URI.create(base + "/l2/orders/42")).DELETE());
        show(c, "L2 DELETE /l2/orders/99", HttpRequest.newBuilder(URI.create(base + "/l2/orders/99")).DELETE());
        show(c, "L3 DELETE /l3/orders/42", HttpRequest.newBuilder(URI.create(base + "/l3/orders/42")).DELETE());
        s.stop(0);
    }

    static void show(HttpClient c, String label, HttpRequest.Builder b) throws Exception {
        HttpResponse<String> r = c.send(b.build(), HttpResponse.BodyHandlers.ofString());
        System.out.printf("%-26s -> %d %s%n", label, r.statusCode(), r.body());
    }
}
