import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

// Demo header: Content-Type (415), Accept (406), ETag + If-None-Match (304), Cache-Control.
public class HttpHeadersDemo {
    static final String CATALOG = "[{\"id\":1,\"name\":\"kopi\"},{\"id\":2,\"name\":\"teh\"}]";
    static final String ETAG = "\"v1\"";

    public static void main(String[] args) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/products", ex -> {
            drain(ex);
            if (ex.getRequestMethod().equals("POST")) {
                String ct = ex.getRequestHeaders().getFirst("Content-Type");
                if (ct == null || !ct.startsWith("application/json")) { send(ex, 415, "hanya application/json", null); return; }
                ex.getResponseHeaders().add("Location", "/products/3");
                send(ex, 201, "created", null);
                return;
            }
            String accept = ex.getRequestHeaders().getFirst("Accept");
            if (accept != null && !accept.contains("json") && !accept.contains("*/*")) { send(ex, 406, "cuma bisa json", null); return; }
            ex.getResponseHeaders().add("ETag", ETAG);
            ex.getResponseHeaders().add("Cache-Control", "max-age=60");
            ex.getResponseHeaders().add("Content-Type", "application/json");
            if (ETAG.equals(ex.getRequestHeaders().getFirst("If-None-Match"))) { send(ex, 304, "", null); return; } // tanpa body
            send(ex, 200, CATALOG, null);
        });
        s.start();
        String url = "http://localhost:" + s.getAddress().getPort() + "/products";
        HttpClient c = HttpClient.newHttpClient();

        System.out.println("== POST tanpa vs dengan Content-Type ==");
        show(c, HttpRequest.newBuilder(URI.create(url)).POST(BodyPublishers.ofString("{}")).build());
        show(c, HttpRequest.newBuilder(URI.create(url)).header("Content-Type", "application/json").POST(BodyPublishers.ofString("{}")).build());
        System.out.println("== GET dengan Accept yang tidak didukung ==");
        show(c, HttpRequest.newBuilder(URI.create(url)).header("Accept", "application/xml").build());
        System.out.println("== GET pertama, lalu GET ulang dengan If-None-Match ==");
        show(c, HttpRequest.newBuilder(URI.create(url)).build());
        show(c, HttpRequest.newBuilder(URI.create(url)).header("If-None-Match", ETAG).build());
        s.stop(0);
    }

    static void show(HttpClient c, HttpRequest r) throws Exception {
        HttpResponse<String> res = c.send(r, BodyHandlers.ofString());
        var h = res.headers();
        System.out.println("  " + r.method() + " -> " + res.statusCode() + " body=" + res.body().length() + "B"
                + h.firstValue("ETag").map(v -> " ETag=" + v).orElse("")
                + h.firstValue("Cache-Control").map(v -> " Cache-Control=" + v).orElse("")
                + h.firstValue("Location").map(v -> " Location=" + v).orElse(""));
    }

    static void drain(HttpExchange ex) throws java.io.IOException { ex.getRequestBody().readAllBytes(); }

    static void send(HttpExchange ex, int status, String body, String unused) throws java.io.IOException {
        byte[] b = body.getBytes();
        ex.sendResponseHeaders(status, status == 304 ? -1 : b.length);
        if (status != 304) ex.getResponseBody().write(b);
        ex.close();
    }
}
