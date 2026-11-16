import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

// Meniru @GetMapping(produces = {json, xml}) dan @PostMapping(consumes = json).
// Accept = format yang klien MAU terima -> nggak cocok = 406. Content-Type = format body yang klien KIRIM -> nggak didukung = 415.
public class ContentNegotiationDemo {
    static void reply(HttpExchange x, int code, String type, String body) throws java.io.IOException {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        if (type != null) x.getResponseHeaders().add("Content-Type", type);
        x.sendResponseHeaders(code, b.length);
        x.getResponseBody().write(b);
        x.close();
    }

    static void handle(HttpExchange x) throws java.io.IOException {
        if (x.getRequestMethod().equals("POST")) { // consumes = application/json
            String ct = x.getRequestHeaders().getFirst("Content-Type");
            if (ct == null || !ct.startsWith("application/json")) { reply(x, 415, null, "hanya terima application/json"); return; }
            reply(x, 201, "application/json", "{\"id\":1}");
            return;
        }
        // produces = json (default, urutan pertama) + xml
        String accept = x.getRequestHeaders().getFirst("Accept");
        if (accept == null || accept.contains("*/*") || accept.contains("application/json"))
            reply(x, 200, "application/json", "{\"id\":1,\"item\":\"Kopi\"}");
        else if (accept.contains("application/xml"))
            reply(x, 200, "application/xml", "<order><id>1</id><item>Kopi</item></order>");
        else reply(x, 406, null, "bisa kasih: application/json, application/xml");
    }

    public static void main(String[] a) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/orders/1", x -> { try { handle(x); } catch (Exception e) { x.close(); } });
        s.start();
        String base = "http://localhost:" + s.getAddress().getPort() + "/orders/1";
        HttpClient c = HttpClient.newHttpClient();
        String[][] calls = { // {method, header-name, header-value}
            {"GET", "Accept", "application/json"},
            {"GET", "Accept", "application/xml"},
            {"GET", "Accept", "text/csv"},
            {"GET", "X-Tanpa", "accept"},
            {"POST", "Content-Type", "application/json"},
            {"POST", "Content-Type", "text/plain"},
        };
        for (String[] k : calls) {
            HttpRequest.Builder rb = HttpRequest.newBuilder(URI.create(base)).header(k[1], k[2]);
            if (k[0].equals("POST")) rb.POST(HttpRequest.BodyPublishers.ofString("{\"item\":\"Kopi\"}")); else rb.GET();
            HttpResponse<String> r = c.send(rb.build(), HttpResponse.BodyHandlers.ofString());
            System.out.println(k[0] + " " + k[1] + ": " + k[2] + "\n  -> " + r.statusCode()
                + " Content-Type=" + r.headers().firstValue("Content-Type").orElse("(nggak ada)") + " " + r.body());
        }
        s.stop(0);
    }
}
