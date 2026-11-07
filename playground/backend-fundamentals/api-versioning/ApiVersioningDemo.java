import com.sun.net.httpserver.*;
import java.net.URI;
import java.net.http.*;

// Demo Lesson 5.2: perubahan aditif vs breaking change. Jalankan: java ApiVersioningDemo.java
public class ApiVersioningDemo {
    static void send(HttpExchange ex, String body, String... headers) throws java.io.IOException {
        for (int i = 0; i < headers.length; i += 2) ex.getResponseHeaders().add(headers[i], headers[i + 1]);
        byte[] b = body.getBytes();
        ex.sendResponseHeaders(200, b.length);
        ex.getResponseBody().write(b);
        ex.close();
    }

    // Klien lama (mobile app yang sudah rilis): cuma tahu "amount" itu angka.
    static long oldClientReadsAmount(String json) {
        int s = json.indexOf("\"amount\":") + 9;
        int e = json.indexOf(json.indexOf(',', s) > 0 ? "," : "}", s);
        return Long.parseLong(json.substring(s, e));
    }

    public static void main(String[] args) throws Exception {
        HttpServer s = HttpServer.create(new java.net.InetSocketAddress(8090), 0);

        // 1. Aditif: tambah field "currency" di v1. Klien lama aman, TIDAK perlu versi baru.
        s.createContext("/v1/orders/1", ex -> send(ex, "{\"id\":1,\"amount\":500,\"currency\":\"IDR\"}",
            "Deprecation", "true", "Sunset", "Wed, 31 Dec 2027 00:00:00 GMT", "Link", "</v2/orders/1>; rel=\"successor-version\""));
        // 2. Breaking diam-diam di URL yang sama: amount berubah dari angka jadi objek.
        s.createContext("/broken/orders/1", ex -> send(ex, "{\"id\":1,\"amount\":{\"value\":500,\"currency\":\"IDR\"}}"));
        // 3. Breaking dengan versi baru: v1 tetap hidup, v2 bentuk baru.
        s.createContext("/v2/orders/1", ex -> send(ex, "{\"id\":1,\"amount\":{\"value\":500,\"currency\":\"IDR\"}}"));
        s.start();

        var http = HttpClient.newHttpClient();
        for (String path : new String[] {"/v1/orders/1", "/broken/orders/1", "/v2/orders/1"}) {
            var res = http.send(HttpRequest.newBuilder(URI.create("http://localhost:8090" + path)).build(),
                HttpResponse.BodyHandlers.ofString());
            String outcome;
            try { outcome = "klien lama OK, amount=" + oldClientReadsAmount(res.body()); }
            catch (NumberFormatException ex) { outcome = "klien lama CRASH: " + ex.getMessage(); }
            System.out.printf("%-18s %s%n  header: Deprecation=%s Sunset=%s%n  %s%n", path, res.body(),
                res.headers().firstValue("Deprecation").orElse("-"), res.headers().firstValue("Sunset").orElse("-"), outcome);
        }
        s.stop(0);
    }
}
