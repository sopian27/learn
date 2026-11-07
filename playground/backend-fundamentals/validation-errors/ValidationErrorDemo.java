import com.sun.net.httpserver.*;
import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import java.util.regex.*;

// Demo Lesson 5.3: validasi + error response. Jalankan: java ValidationErrorDemo.java
public class ValidationErrorDemo {
    static String field(String json, String name) {
        Matcher m = Pattern.compile("\"" + name + "\":\"?([^\",}]*)").matcher(json);
        return m.find() ? m.group(1) : null;
    }

    static void send(HttpExchange ex, int code, String body) throws IOException {
        byte[] b = body.getBytes();
        ex.sendResponseHeaders(code, b.length);
        ex.getResponseBody().write(b);
        ex.close();
    }

    // SEBELUM: validasi lempar exception, semua ditangkap jadi 500 + stack trace mentah, error pertama saja.
    static void bad(HttpExchange ex) throws IOException {
        try {
            String json = new String(ex.getRequestBody().readAllBytes());
            long amount = Long.parseLong(field(json, "amount"));
            if (amount <= 0) throw new IllegalArgumentException("amount must be > 0");
            if (!field(json, "email").contains("@")) throw new IllegalArgumentException("bad email");
            send(ex, 201, "{\"id\":1}");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            send(ex, 500, sw.toString());
        }
    }

    // SESUDAH: kumpulin semua field error, 400 + kode stabil + traceId; error tak terduga disamarkan.
    static void good(HttpExchange ex) throws IOException {
        String traceId = UUID.randomUUID().toString().substring(0, 8);
        try {
            String json = new String(ex.getRequestBody().readAllBytes());
            String amount = field(json, "amount"), email = field(json, "email");
            List<String> errs = new ArrayList<>();
            if (amount == null || !amount.matches("[0-9]+") || Long.parseLong(amount) <= 0)
                errs.add("{\"field\":\"amount\",\"message\":\"must be a positive number\"}");
            if (email == null || !email.contains("@"))
                errs.add("{\"field\":\"email\",\"message\":\"must be a valid email\"}");
            if (!errs.isEmpty()) { send(ex, 400, error(400, "INVALID_REQUEST", traceId, errs)); return; }
            send(ex, 201, "{\"id\":1}");
        } catch (Exception e) {
            System.err.println("[" + traceId + "] " + e); // detail cuma di log server
            send(ex, 500, error(500, "INTERNAL_ERROR", traceId, List.of()));
        }
    }

    static String error(int status, String code, String traceId, List<String> fieldErrors) {
        return "{\"status\":" + status + ",\"code\":\"" + code + "\",\"traceId\":\"" + traceId
            + "\",\"fieldErrors\":[" + String.join(",", fieldErrors) + "]}";
    }

    public static void main(String[] args) throws Exception {
        HttpServer s = HttpServer.create(new java.net.InetSocketAddress(8091), 0);
        s.createContext("/bad/orders", ValidationErrorDemo::bad);
        s.createContext("/good/orders", ValidationErrorDemo::good);
        s.start();

        var http = HttpClient.newHttpClient();
        String[] inputs = {"{\"amount\":-5,\"email\":\"x\"}", "{\"amount\":\"abc\",\"email\":\"a@b.id\"}", "{\"amount\":500,\"email\":\"a@b.id\"}"};
        for (String input : inputs) {
            System.out.println("INPUT " + input);
            for (String path : new String[] {"/bad/orders", "/good/orders"}) {
                var res = http.send(HttpRequest.newBuilder(URI.create("http://localhost:8091" + path))
                    .POST(HttpRequest.BodyPublishers.ofString(input)).build(), HttpResponse.BodyHandlers.ofString());
                String first = res.body().lines().findFirst().orElse("");
                System.out.printf("  %-13s %d %s%n", path, res.statusCode(), first.length() > 110 ? first.substring(0, 110) + "..." : first);
            }
        }
        s.stop(0);
    }
}
