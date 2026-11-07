import com.sun.net.httpserver.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;

// Demo Lesson 5.4: kontrak (spec) sebagai sumber kebenaran, implementasi dicek terhadap kontrak.
// Spec ditulis ringkas ala OpenAPI (bukan parser OpenAPI beneran). Jalankan: java OpenApiContractDemo.java
public class OpenApiContractDemo {
    // Kontrak POST /orders: status 201, field wajib + tipe JSON-nya. Di OpenAPI asli ini bagian "responses.201.content.schema".
    static final int SPEC_STATUS = 201;
    static final Map<String, String> SPEC_FIELDS = Map.of("id", "number", "currency", "string");

    static void respond(HttpExchange ex, int code, String body) throws java.io.IOException {
        byte[] b = body.getBytes();
        ex.sendResponseHeaders(code, b.length);
        ex.getResponseBody().write(b);
        ex.close();
    }

    // Cek sederhana: field ada dan tipe literal-nya cocok. Cukup buat demo, bukan validator JSON penuh.
    static List<String> violations(int status, String body) {
        List<String> v = new ArrayList<>();
        if (status != SPEC_STATUS) v.add("status " + status + " != " + SPEC_STATUS);
        SPEC_FIELDS.forEach((name, type) -> {
            var m = java.util.regex.Pattern.compile("\"" + name + "\":(\"?)([^\",}]*)").matcher(body);
            if (!m.find()) { v.add("field '" + name + "' hilang"); return; }
            boolean isString = !m.group(1).isEmpty();
            if (isString != type.equals("string")) v.add("field '" + name + "' harusnya " + type);
        });
        return v;
    }

    public static void main(String[] args) throws Exception {
        HttpServer s = HttpServer.create(new java.net.InetSocketAddress(8092), 0);
        // Implementasi "dokumentasi belakangan": kode berubah, spec tidak ikut. id jadi string, currency lupa.
        s.createContext("/drifted/orders", ex -> respond(ex, 200, "{\"id\":\"7\"}"));
        // Implementasi yang mengikuti kontrak.
        s.createContext("/conforming/orders", ex -> respond(ex, 201, "{\"id\":7,\"currency\":\"IDR\"}"));
        s.start();

        var http = HttpClient.newHttpClient();
        for (String path : new String[] {"/drifted/orders", "/conforming/orders"}) {
            var res = http.send(HttpRequest.newBuilder(URI.create("http://localhost:8092" + path))
                .POST(HttpRequest.BodyPublishers.ofString("{}")).build(), HttpResponse.BodyHandlers.ofString());
            var v = violations(res.statusCode(), res.body());
            System.out.printf("%-20s %d %s%n  -> %s%n", path, res.statusCode(), res.body(), v.isEmpty() ? "SESUAI KONTRAK" : "MELANGGAR: " + v);
        }
        s.stop(0);
    }
}
