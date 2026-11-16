import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Linter kecil buat aturan URI Naming di standards/API_STANDARDS.md
// (lowercase, hyphen, plural, no verbs) + bukti bahwa path itu case-sensitive.
public class UriNamingDemo {
    // shortcut: daftar verb dan cek plural cuma heuristik, upgrade ke linter OpenAPI (Spectral) kalau dipakai serius.
    static final Set<String> VERBS = Set.of("get", "create", "delete", "update", "add", "remove", "pay", "cancel", "fetch", "set");

    static List<String> lint(String uri) {
        List<String> problems = new ArrayList<>();
        for (String seg : uri.split("/")) {
            if (seg.isEmpty() || seg.startsWith("{")) continue; // {id} itu parameter, bukan nama resource
            if (!seg.equals(seg.toLowerCase())) problems.add("'" + seg + "' bukan lowercase (camelCase/PascalCase)");
            if (seg.contains("_")) problems.add("'" + seg + "' pakai underscore, standar pakai hyphen");
            String first = seg.split("(?=[A-Z])|[-_]")[0].toLowerCase();
            if (VERBS.contains(first)) problems.add("'" + seg + "' diawali verb '" + first + "'");
            if (!seg.endsWith("s") && !seg.matches("v\\d+")) problems.add("'" + seg + "' bukan plural");
        }
        if (uri.length() > 1 && uri.endsWith("/")) problems.add("trailing slash");
        return problems;
    }

    static void reply(HttpExchange x, int code, String body) {
        try {
            byte[] b = body.getBytes(StandardCharsets.UTF_8);
            x.sendResponseHeaders(code, b.length == 0 ? -1 : b.length);
            if (b.length > 0) x.getResponseBody().write(b);
            x.close();
        } catch (java.io.IOException e) { throw new RuntimeException(e); }
    }

    public static void main(String[] a) throws Exception {
        for (String u : List.of("/payment-transactions/{id}", "/user-profiles", "/orders/{id}/items",
                "/getUsers", "/Orders", "/order_items", "/paymentTransactions", "/user", "/orders/")) {
            List<String> p = lint(u);
            System.out.printf("%-30s %s%n", u, p.isEmpty() ? "OK" : "X " + String.join("; ", p));
        }

        // Server cuma kenal /orders. Path case-sensitive: /Orders = resource lain (404), bukan alias.
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/orders", x -> reply(x, x.getRequestURI().getPath().equals("/orders") ? 200 : 404, "{}"));
        s.start();
        String base = "http://localhost:" + s.getAddress().getPort();
        HttpClient c = HttpClient.newHttpClient();
        for (String path : List.of("/orders", "/Orders")) {
            int code = c.send(HttpRequest.newBuilder(URI.create(base + path)).GET().build(),
                HttpResponse.BodyHandlers.ofString()).statusCode();
            System.out.printf("GET %-10s -> %d%n", path, code);
        }
        s.stop(0);
    }
}
