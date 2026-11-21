import java.util.*;

/** Simulasi: endpoint dengan test happy path saja vs endpoint yang jalur gagalnya diuji. Plain Java, tanpa Spring. */
public class FailureScenariosDemo {

    record Order(int id, String owner) {}
    record Resp(int status, String body) {}

    static final List<Order> DB = new ArrayList<>();
    static { for (int i = 1; i <= 5; i++) DB.add(new Order(i, i <= 3 ? "rani" : "budi")); }

    /** strict = false meniru versi awal (cuma happy path yang dipikirkan). */
    static Resp getOrder(boolean strict, String user, int id) {
        if (strict && user == null) return new Resp(401, "no token");
        Optional<Order> o = DB.stream().filter(x -> x.id() == id).findFirst();
        if (o.isEmpty()) return new Resp(404, "not found");
        // 404 (bukan 403) supaya id order orang lain tidak bisa dienumerasi
        if (strict && !o.get().owner().equals(user)) return new Resp(404, "not found");
        return new Resp(200, "order " + id);
    }

    static Resp list(boolean strict, int page, int size) {
        if (strict && (page < 0 || size < 1 || size > 100)) return new Resp(400, "invalid page/size");
        int from = page * size;
        if (!strict && size < 1) throw new ArithmeticException("size=0");
        if (from >= DB.size()) return new Resp(200, "items=0 hasNext=false");
        int to = Math.min(from + size, DB.size());
        return new Resp(200, "items=" + (to - from) + " hasNext=" + (to < DB.size()));
    }

    static int fails = 0;
    static void check(String name, boolean strict, int want, java.util.function.Supplier<Resp> call) {
        String got;
        boolean ok;
        try { Resp r = call.get(); ok = r.status() == want; got = r.status() + " " + r.body(); }
        catch (RuntimeException e) { ok = false; got = "500 (" + e.getMessage() + ")"; }
        if (!ok) fails++;
        System.out.printf("  %-34s want %d -> %s %s%n", name, want, ok ? "PASS" : "FAIL", got);
    }

    static void suite(String label, boolean strict) {
        System.out.println("== " + label);
        check("tanpa token", strict, 401, () -> getOrder(strict, null, 1));
        check("order milik orang lain", strict, 404, () -> getOrder(strict, "budi", 1));
        check("order sendiri (happy path)", strict, 200, () -> getOrder(strict, "rani", 1));
        check("size=0", strict, 400, () -> list(strict, 0, 0));
        check("halaman terakhir (sisa 2)", strict, 200, () -> list(strict, 1, 3));
        check("halaman melewati data", strict, 200, () -> list(strict, 9, 3));
    }

    public static void main(String[] args) {
        suite("Versi awal: test cuma happy path", false);
        suite("Versi fix: jalur gagal ikut diuji", true);
        if (fails > 0) System.out.println("\nada skenario gagal: " + fails + " (dari versi awal, memang disengaja)");
    }
}
