import java.util.*;
import java.util.function.*;
// Lesson 11.2: slice test controller. Service dipalsukan, yang diuji cuma lapisan HTTP (status + body).
// Di Spring: MockMvc + @WebMvcTest + @MockitoBean. Di sini plain Java supaya jalan satu perintah.
public class WebMvcSliceTestDemo {
    record Req(String sku, int qty) {}
    record Res(int status, String body) {}
    interface OrderService { String create(Req r); }

    // Service palsu: meniru service asli (sku null -> NPE, produk tak dikenal -> NoSuchElement).
    static final OrderService FAKE = r -> {
        if (r.sku().isBlank() || !r.sku().equals("A1")) throw new NoSuchElementException("sku " + r.sku());
        return "ORD-1";
    };

    // Controller + @Valid + @RestControllerAdvice, diringkas jadi satu method.
    static Res post(Req r, boolean fixed) {
        if (fixed && (r.sku() == null || r.sku().isBlank() || r.qty() < 1))
            return new Res(400, "{\"error\":\"sku/qty tidak valid\"}"); // @Valid -> 400
        try { return new Res(201, "{\"id\":\"" + FAKE.create(r) + "\"}"); }
        catch (NoSuchElementException e) { return new Res(404, "{\"error\":\"sku tidak ada\"}"); }
        catch (RuntimeException e) { return new Res(500, e.toString()); } // bocor: NPE jadi 500 + detail internal
    }

    static int fails;
    static void expect(String name, Res got, int status) {
        boolean ok = got.status() == status;
        if (!ok) fails++;
        System.out.printf("  %s %s -> %d %s%n", ok ? "PASS" : "FAIL", name, got.status(), got.body());
    }

    static void run(String label, boolean fixed) {
        System.out.println(label);
        expect("POST valid         ", post(new Req("A1", 2), fixed), 201);
        expect("POST qty 0         ", post(new Req("A1", 0), fixed), 400);
        expect("POST sku kosong    ", post(new Req(null, 1), fixed), 400);
        expect("POST sku tak dikenal", post(new Req("ZZ", 1), fixed), 404);
    }

    public static void main(String[] a) {
        run("== BUG: tanpa validasi ==", false);
        run("== FIXED: ada validasi ==", true);
        System.out.println("fail total: " + fails);
    }
}
