import java.util.*;

/** Simulasi Pact: consumer tulis kontrak, provider verifikasi. Plain Java, bukan library Pact asli. */
public class PactContractDemo {

    /** Kontrak dari consumer: request yang dikirim + field respons yang benar-benar dipakai (nama -> tipe). */
    record Pact(String consumer, String method, String path, int status, Map<String, Class<?>> fields) {}

    /** Respons provider: v1 = kondisi saat kontrak ditulis, v2 = setelah tim provider "merapikan" nama field. */
    static Map<String, Object> providerResponse(int version, String method, String path) {
        if (!method.equals("GET") || !path.equals("/orders/7")) return null;
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("id", 7);
        body.put("status", "PAID");
        body.put(version == 1 ? "total" : "amount", 150000); // v2 rename total -> amount
        body.put("internalNote", "tidak dipakai consumer"); // field ekstra tidak boleh bikin gagal
        return body;
    }

    static boolean verify(Pact pact, int providerVersion) {
        System.out.println("== provider v" + providerVersion + " vs kontrak " + pact.consumer());
        Map<String, Object> body = providerResponse(providerVersion, pact.method(), pact.path());
        if (body == null) { System.out.println("  FAIL: endpoint " + pact.method() + " " + pact.path() + " tidak ada"); return false; }
        boolean ok = true;
        for (var f : pact.fields().entrySet()) {
            Object v = body.get(f.getKey());
            if (v == null) { System.out.println("  FAIL: field '" + f.getKey() + "' hilang"); ok = false; }
            else if (!f.getValue().isInstance(v)) { System.out.println("  FAIL: field '" + f.getKey() + "' bukan " + f.getValue().getSimpleName()); ok = false; }
            else System.out.println("  PASS: field '" + f.getKey() + "'");
        }
        return ok;
    }

    public static void main(String[] args) {
        Pact mobileApp = new Pact("mobile-app", "GET", "/orders/7", 200,
                Map.of("id", Integer.class, "status", String.class, "total", Integer.class));

        boolean v1 = verify(mobileApp, 1); // deploy lama: aman
        boolean v2 = verify(mobileApp, 2); // PR provider: rename field -> build provider harusnya merah
        System.out.println(v1 && !v2
                ? "\nProvider v2 DITOLAK sebelum deploy: kontrak mobile-app rusak (sesuai rencana)."
                : "\nHasil tidak sesuai ekspektasi demo.");
    }
}
