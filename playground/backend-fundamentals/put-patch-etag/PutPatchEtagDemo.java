import com.sun.net.httpserver.*;
import java.net.URI;
import java.net.http.*;
import java.util.regex.*;

// Demo Lesson 5.5: lost update, PUT vs PATCH, ETag + If-Match. Jalankan: java PutPatchEtagDemo.java
public class PutPatchEtagDemo {
    static String name = "Kopi", price = "100", stock = "5";
    static int version = 1;

    static String num(String json, String k) {
        Matcher m = Pattern.compile("\"" + k + "\":\"?([^\",}]*)").matcher(json);
        return m.find() ? m.group(1) : null;
    }
    static String etag() { return "\"v" + version + "\""; }
    static String view() { return "{\"name\":\"" + name + "\",\"price\":" + price + ",\"stock\":" + stock + "}"; }

    static void handle(HttpExchange ex) throws java.io.IOException {
        String body = new String(ex.getRequestBody().readAllBytes());
        String m = ex.getRequestMethod(), ifMatch = ex.getRequestHeaders().getFirst("If-Match");
        int code = 200;
        if (!m.equals("GET")) {
            if (ifMatch != null && !ifMatch.equals(etag())) code = 412; // data sudah berubah sejak klien baca
            else {
                // PUT ganti SEMUA field (yang nggak dikirim jadi kosong); PATCH cuma yang dikirim
                boolean put = m.equals("PUT");
                String n = num(body, "name"), p = num(body, "price"), s = num(body, "stock");
                if (put && (n == null || p == null || s == null)) code = 400;
                else { if (n != null) name = n; if (p != null) price = p; if (s != null) stock = s; version++; }
            }
        }
        byte[] b = (code == 412 ? "{\"code\":\"PRECONDITION_FAILED\"}" : view()).getBytes();
        ex.getResponseHeaders().add("ETag", etag());
        ex.sendResponseHeaders(code, b.length);
        ex.getResponseBody().write(b);
        ex.close();
    }

    static HttpClient http = HttpClient.newHttpClient();
    static String call(String method, String body, String ifMatch) throws Exception {
        var rb = HttpRequest.newBuilder(URI.create("http://localhost:8093/products/1"))
            .method(method, HttpRequest.BodyPublishers.ofString(body));
        if (ifMatch != null) rb.header("If-Match", ifMatch);
        var r = http.send(rb.build(), HttpResponse.BodyHandlers.ofString());
        return r.statusCode() + " etag=" + r.headers().firstValue("ETag").orElse("-") + " " + r.body();
    }
    static void reset() { name = "Kopi"; price = "100"; stock = "5"; version = 1; }

    public static void main(String[] args) throws Exception {
        HttpServer s = HttpServer.create(new java.net.InetSocketAddress(8093), 0);
        s.createContext("/products/1", PutPatchEtagDemo::handle);
        s.start();
        String stale = "\"v1\""; // Yuni dan Rakha sama-sama baca versi v1

        System.out.println("1) PUT tanpa If-Match: Yuni set price 120, Rakha set stock 3 (body lama, price 100)");
        System.out.println("  Yuni : " + call("PUT", "{\"name\":\"Kopi\",\"price\":120,\"stock\":5}", null));
        System.out.println("  Rakha: " + call("PUT", "{\"name\":\"Kopi\",\"price\":100,\"stock\":3}", null) + "   <- price 120 hilang");

        reset();
        System.out.println("2) PUT + If-Match: Rakha ditolak, harus baca ulang");
        System.out.println("  Yuni : " + call("PUT", "{\"name\":\"Kopi\",\"price\":120,\"stock\":5}", stale));
        System.out.println("  Rakha: " + call("PUT", "{\"name\":\"Kopi\",\"price\":100,\"stock\":3}", stale));
        System.out.println("  Rakha GET ulang: " + call("GET", "", null));

        reset();
        System.out.println("3) PATCH: Rakha kirim stock saja, price Yuni selamat");
        System.out.println("  Yuni : " + call("PATCH", "{\"price\":120}", null));
        System.out.println("  Rakha: " + call("PATCH", "{\"stock\":3}", null));
        s.stop(0);
    }
}
