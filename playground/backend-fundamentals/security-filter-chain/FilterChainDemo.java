import java.util.List;
import java.util.Map;

// Lesson 11.1: filter chain ala Spring Security, versi mini.
// Tiap filter: putuskan stop (401/403) atau oper ke filter berikutnya.
public class FilterChainDemo {

    record Request(String path, String token) {}
    record Response(int status, String body) {}
    static class Ctx { String user; String role; }   // "SecurityContext" mini

    interface Filter { Response handle(Request req, Ctx ctx, Chain next); }

    static class Chain {
        private final List<Filter> filters; private final int i;
        Chain(List<Filter> filters, int i) { this.filters = filters; this.i = i; }
        Response proceed(Request req, Ctx ctx) {
            if (i == filters.size()) return new Response(200, "data rahasia untuk " + ctx.user);
            return filters.get(i).handle(req, ctx, new Chain(filters, i + 1));
        }
    }

    // token -> "user:role" (pura-pura verifikasi token)
    static final Map<String, String> TOKENS = Map.of("tok-budi", "budi:USER", "tok-yuni", "yuni:ADMIN");

    // Authentication: "lu siapa?" -> gagal = 401
    static final Filter authentication = (req, ctx, next) -> {
        String who = req.token() == null ? null : TOKENS.get(req.token());
        if (who == null) return new Response(401, "belum login / token salah");
        ctx.user = who.split(":")[0]; ctx.role = who.split(":")[1];
        return next.proceed(req, ctx);
    };

    // Authorization: "lu boleh ngapain?" -> gagal = 403
    static final Filter authorization = (req, ctx, next) -> {
        if (req.path().startsWith("/admin") && !"ADMIN".equals(ctx.role))
            return new Response(403, "login sih, tapi bukan admin");
        return next.proceed(req, ctx);
    };

    static void run(String label, List<Filter> filters, Request req) {
        Response r = new Chain(filters, 0).proceed(req, new Ctx());
        System.out.printf("%-34s: %d %s%n", label, r.status(), r.body());
    }

    public static void main(String[] args) {
        List<Filter> bener = List.of(authentication, authorization);
        run("anonim -> /admin", bener, new Request("/admin", null));
        run("budi (USER) -> /admin", bener, new Request("/admin", "tok-budi"));
        run("yuni (ADMIN) -> /admin", bener, new Request("/admin", "tok-yuni"));

        System.out.println("--- bug: lupa authorization ---");
        run("budi (USER) -> /admin", List.of(authentication), new Request("/admin", "tok-budi"));

        System.out.println("--- bug: urutan kebalik ---");
        run("yuni (ADMIN) -> /admin", List.of(authorization, authentication), new Request("/admin", "tok-yuni"));
    }
}
