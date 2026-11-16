import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;

// Meniru perilaku @PostMapping + @RequestBody + ResponseEntity.
// /naive  = method return objek saja (Spring default: 200, tanpa Location).
// /orders = ResponseEntity.created(location).body(order) (201 + Location). Body rusak/kosong -> 400.
public class RequestBodyResponseEntityDemo {
    static final Map<Integer, String> orders = new TreeMap<>();
    static int nextId = 1;
    static final Pattern ITEM = Pattern.compile("^\\s*\\{\\s*\"item\"\\s*:\\s*\"([^\"]+)\"\\s*\\}\\s*$");

    static void reply(HttpExchange x, int code, String loc, String body) throws java.io.IOException {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        if (loc != null) x.getResponseHeaders().add("Location", loc);
        x.sendResponseHeaders(code, b.length);
        x.getResponseBody().write(b);
        x.close();
    }

    static void handle(HttpExchange x, boolean explicit) throws java.io.IOException {
        // @RequestBody: body mentah -> objek. Gagal parse = 400 (Spring: HttpMessageNotReadableException).
        String raw = new String(x.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Matcher m = ITEM.matcher(raw);
        if (!m.matches()) { reply(x, 400, null, "{\"error\":\"body kosong atau bukan JSON {item}\"}"); return; }
        int id = nextId++;
        orders.put(id, m.group(1));
        String json = "{\"id\":" + id + ",\"item\":\"" + m.group(1) + "\"}";
        if (explicit) reply(x, 201, "/orders/" + id, json); // ResponseEntity.created(...)
        else reply(x, 200, null, json);                      // return Order; -> 200 saja
    }

    public static void main(String[] a) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/naive", x -> { try { handle(x, false); } catch (Exception e) { x.close(); } });
        s.createContext("/orders", x -> { try { handle(x, true); } catch (Exception e) { x.close(); } });
        s.start();
        String base = "http://localhost:" + s.getAddress().getPort();
        HttpClient c = HttpClient.newHttpClient();
        String[][] calls = {
            {"/naive", "{\"item\":\"Kopi\"}"},
            {"/orders", "{\"item\":\"Kopi\"}"},
            {"/orders", "{item: Kopi"},
            {"/orders", ""},
        };
        for (String[] call : calls) {
            HttpResponse<String> r = c.send(HttpRequest.newBuilder(URI.create(base + call[0]))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(call[1])).build(), HttpResponse.BodyHandlers.ofString());
            System.out.println("POST " + call[0] + " body=" + call[1] + "\n  -> " + r.statusCode()
                + " Location=" + r.headers().firstValue("Location").orElse("(nggak ada)") + " " + r.body());
        }
        s.stop(0);
    }
}
