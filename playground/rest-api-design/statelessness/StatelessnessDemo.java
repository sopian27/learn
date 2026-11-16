import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

// Demo: dua instance server di belakang "load balancer" (kita pilih manual A atau B).
// /stateful : session disimpan di memori instance -> request nyasar ke instance lain = 401.
// /stateless: token ditandatangani, instance mana pun bisa verifikasi sendiri -> 200.
public class StatelessnessDemo {
    static final byte[] SECRET = "demo-secret-jangan-dipakai-di-prod".getBytes(StandardCharsets.UTF_8);

    public static void main(String[] args) throws Exception {
        HttpServer a = instance(), b = instance();
        HttpClient c = HttpClient.newHttpClient();
        String A = "http://localhost:" + a.getAddress().getPort(), B = "http://localhost:" + b.getAddress().getPort();

        System.out.println("== STATEFUL: login di A, lalu request ke A dan B ==");
        String sid = call(c, "POST", A + "/stateful/login", null).split("\\|")[1];
        call(c, "GET", A + "/stateful/cart", "sid=" + sid);
        call(c, "GET", B + "/stateful/cart", "sid=" + sid);   // load balancer lempar ke B -> 401

        System.out.println("== STATELESS: login di A, lalu request ke A dan B ==");
        String token = call(c, "POST", A + "/stateless/login", null).split("\\|")[1];
        call(c, "GET", A + "/stateless/cart", "Bearer " + token);
        call(c, "GET", B + "/stateless/cart", "Bearer " + token);  // B verifikasi sendiri -> 200
        call(c, "GET", B + "/stateless/cart", "Bearer " + token + "x"); // token dirusak -> 401
        a.stop(0); b.stop(0);
    }

    // Satu "instance" server. Map session sengaja per-instance: itulah state yang bikin masalah.
    static HttpServer instance() throws Exception {
        Map<String, String> sessions = new ConcurrentHashMap<>();
        HttpServer s = HttpServer.create(new InetSocketAddress(0), 0);
        s.createContext("/stateful/login", ex -> {
            String id = UUID.randomUUID().toString().substring(0, 8);
            sessions.put(id, "budi");
            reply(ex, 200, "ok|" + id);
        });
        s.createContext("/stateful/cart", ex -> {
            String user = sessions.get(ex.getRequestHeaders().getFirst("Authorization").replace("sid=", ""));
            if (user == null) reply(ex, 401, "session tidak dikenal di instance ini");
            else reply(ex, 200, "keranjang " + user);
        });
        s.createContext("/stateless/login", ex -> reply(ex, 200, "ok|" + sign("budi")));
        s.createContext("/stateless/cart", ex -> {
            String user = verify(ex.getRequestHeaders().getFirst("Authorization").replace("Bearer ", ""));
            if (user == null) reply(ex, 401, "token tidak valid");
            else reply(ex, 200, "keranjang " + user);
        });
        s.start();
        return s;
    }

    // token = base64(user).base64(HMAC). Sengaja minimal; JWT beneran dibahas di modul security.
    static String sign(String user) {
        String p = Base64.getUrlEncoder().withoutPadding().encodeToString(user.getBytes(StandardCharsets.UTF_8));
        return p + "." + hmac(p);
    }

    static String verify(String token) {
        try {
            String[] t = token.split("\\.");
            if (t.length != 2 || !hmac(t[0]).equals(t[1])) return null;
            return new String(Base64.getUrlDecoder().decode(t[0]), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    static String hmac(String data) {
        try {
            Mac m = Mac.getInstance("HmacSHA256");
            m.init(new SecretKeySpec(SECRET, "HmacSHA256"));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(m.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (java.security.GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    static String call(HttpClient c, String m, String url, String auth) throws Exception {
        var b = HttpRequest.newBuilder(URI.create(url)).method(m, HttpRequest.BodyPublishers.noBody());
        if (auth != null) b.header("Authorization", auth);
        var r = c.send(b.build(), BodyHandlers.ofString());
        System.out.println("  " + m + " :" + url.substring(url.lastIndexOf(':') + 1) + " -> " + r.statusCode() + " " + r.body());
        return r.body();
    }

    static void reply(HttpExchange ex, int status, String body) throws java.io.IOException {
        ex.getRequestBody().readAllBytes();
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(status, b.length);
        ex.getResponseBody().write(b);
        ex.close();
    }
}
