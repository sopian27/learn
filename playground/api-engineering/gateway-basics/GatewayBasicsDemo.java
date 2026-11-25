import java.util.*;
import java.util.function.Function;

/**
 * Demo API Gateway in-memory: routing, auth di edge, transformasi request/response.
 * [1] tanpa gateway: tiap service cek auth sendiri, satu service lupa -> terbuka.
 * [2] dengan gateway: 1 pintu, token dicek sekali, route by path, header internal disuntik gateway.
 * [3] transformasi: header palsu dari client dibuang, response dibersihkan dari field internal.
 * Run: java GatewayBasicsDemo.java
 */
public class GatewayBasicsDemo {

    record Req(String path, Map<String, String> headers) {}
    record Resp(int status, String body) {}

    // service "internal": percaya header X-User-Id apa adanya (asumsi: hanya gateway yang boleh memanggil)
    static final Function<Req, Resp> ORDER_SERVICE = r ->
            new Resp(200, "orders milik " + r.headers().get("X-User-Id") + " {internalCost=12000}");
    static final Function<Req, Resp> PAYMENT_SERVICE = r ->
            new Resp(200, "payments milik " + r.headers().get("X-User-Id"));

    static final Map<String, String> TOKENS = Map.of("tok-rani", "rani", "tok-akbar", "akbar");

    static Resp gateway(Req in) {
        String token = in.headers().getOrDefault("Authorization", "");
        String user = TOKENS.get(token);
        if (user == null) return new Resp(401, "unauthorized");          // auth di edge, sekali

        Map<String, String> h = new HashMap<>(in.headers());
        h.remove("X-User-Id");                                            // buang header palsu dari client
        h.put("X-User-Id", user);                                         // suntik identitas terverifikasi

        Req out = new Req(in.path(), h);
        Resp r;
        if (in.path().startsWith("/orders")) r = ORDER_SERVICE.apply(out);       // routing by path
        else if (in.path().startsWith("/payments")) r = PAYMENT_SERVICE.apply(out);
        else return new Resp(404, "no route");

        return new Resp(r.status(), r.body().replaceAll(" \\{internalCost=\\d+\\}", "")); // transformasi response
    }

    public static void main(String[] args) {
        System.out.println("[1] tanpa gateway: client langsung ke service tanpa token");
        System.out.println("    " + PAYMENT_SERVICE.apply(new Req("/payments", Map.of("X-User-Id", "siapa-saja"))));

        System.out.println("[2] lewat gateway");
        System.out.println("    tanpa token : " + gateway(new Req("/orders", Map.of())));
        System.out.println("    token valid : " + gateway(new Req("/orders", Map.of("Authorization", "tok-rani"))));
        System.out.println("    path asing  : " + gateway(new Req("/admin", Map.of("Authorization", "tok-rani"))));

        System.out.println("[3] client coba menyamar via header");
        System.out.println("    " + gateway(new Req("/payments",
                Map.of("Authorization", "tok-rani", "X-User-Id", "akbar"))));
    }
}
