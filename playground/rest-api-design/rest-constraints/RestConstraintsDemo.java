import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

// Demo enam constraint REST dalam satu file:
// client-server  : HttpClient (client) terpisah dari backend (server), cuma ngobrol lewat HTTP.
// stateless      : backend nolak request tanpa Authorization, nggak ada session di memori.
// cacheable      : ETag + Cache-Control, request ulang dengan If-None-Match dapat 304.
// uniform iface  : resource /orders/42 + representasi JSON + _links (hypermedia).
// layered system : client cuma kenal gateway; backend disembunyikan, gateway nambah header Via.
// code-on-demand : /widget.js, server ngirim kode (OPSIONAL, jarang dipakai di API JSON).
public class RestConstraintsDemo {
    static final String ETAG = "\"v1\"";
    static final String ORDER = "{\"id\":42,\"status\":\"PAID\",\"_links\":{\"self\":\"/orders/42\",\"cancel\":\"/orders/42/cancel\"}}";
    static final HttpClient HTTP = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {
        HttpServer backend = start(RestConstraintsDemo::backend);
        String back = "http://localhost:" + backend.getAddress().getPort();
        HttpServer gateway = start(ex -> gateway(ex, back));
        String gw = "http://localhost:" + gateway.getAddress().getPort(); // client cuma tau ini

        System.out.println("== STATELESS: tanpa Authorization ditolak ==");
        show(gw + "/orders/42", null, null);
        System.out.println("== UNIFORM INTERFACE + CACHEABLE: 200 + ETag + _links ==");
        show(gw + "/orders/42", "Bearer abc", null);
        System.out.println("== CACHEABLE: If-None-Match sama -> 304 tanpa body ==");
        show(gw + "/orders/42", "Bearer abc", ETAG);
        System.out.println("== CODE-ON-DEMAND (opsional): server ngirim kode ==");
        show(gw + "/widget.js", "Bearer abc", null);
        backend.stop(0);
        gateway.stop(0);
    }

    // Server di belakang gateway. Nggak simpan apa-apa soal client.
    static void backend(HttpExchange ex) throws IOException {
        String path = ex.getRequestURI().getPath();
        if (ex.getRequestHeaders().getFirst("Authorization") == null) {
            send(ex, 401, "text/plain", "butuh Authorization di tiap request");
            return;
        }
        if (path.equals("/widget.js")) {
            send(ex, 200, "application/javascript", "console.log('kode ini dikirim server')");
            return;
        }
        if (!path.equals("/orders/42")) {
            send(ex, 404, "text/plain", "nggak ada");
            return;
        }
        ex.getResponseHeaders().set("ETag", ETAG);
        ex.getResponseHeaders().set("Cache-Control", "max-age=60");
        if (ETAG.equals(ex.getRequestHeaders().getFirst("If-None-Match"))) {
            ex.sendResponseHeaders(304, -1);
            ex.close();
            return;
        }
        send(ex, 200, "application/json", ORDER);
    }

    // Layer perantara: client nggak tau backend ada di mana. Gateway cuma nerusin +
    // nambah Via.
    static void gateway(HttpExchange ex, String back) throws IOException {
        HttpRequest.Builder rq = HttpRequest.newBuilder(URI.create(back + ex.getRequestURI().getPath()));
        for (String h : new String[] { "Authorization", "If-None-Match" })
            if (ex.getRequestHeaders().getFirst(h) != null)
                rq.header(h, ex.getRequestHeaders().getFirst(h));
        try {
            HttpResponse<String> r = HTTP.send(rq.build(), BodyHandlers.ofString());
            ex.getResponseHeaders().set("Via", "1.1 gateway");
            r.headers().firstValue("etag").ifPresent(v -> ex.getResponseHeaders().set("ETag", v));
            r.headers().firstValue("content-type").ifPresent(v -> ex.getResponseHeaders().set("Content-Type", v));
            byte[] body = r.body().getBytes(StandardCharsets.UTF_8);
            ex.sendResponseHeaders(r.statusCode(), r.statusCode() == 304 ? -1 : body.length);
            if (body.length > 0)
                ex.getResponseBody().write(body);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ex.close();
    }

    static void show(String url, String auth, String ifNoneMatch) throws Exception {
        HttpRequest.Builder rq = HttpRequest.newBuilder(URI.create(url));
        if (auth != null)
            rq.header("Authorization", auth);
        if (ifNoneMatch != null)
            rq.header("If-None-Match", ifNoneMatch);
        HttpResponse<String> r = HTTP.send(rq.build(), BodyHandlers.ofString());
        System.out.printf("  %d | Via=%s | ETag=%s | %s%n", r.statusCode(),
                r.headers().firstValue("via").orElse("-"), r.headers().firstValue("etag").orElse("-"),
                r.body().isEmpty() ? "(tanpa body)" : r.body());
    }

    static HttpServer start(HttpHandler h) throws IOException {
        HttpServer s = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        s.createContext("/", h);
        s.start();
        return s;
    }

    static void send(HttpExchange ex, int code, String type, String body) throws IOException {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", type);
        ex.sendResponseHeaders(code, b.length);
        ex.getResponseBody().write(b);
        ex.close();
    }
}
