import java.util.*;

// Demo Lesson 10.3: kontrak (spec) disepakati dulu, implementasi diverifikasi terhadap kontrak.
// Spec disederhanakan jadi Map; di dunia nyata ini file openapi.yaml + contract test di CI.
public class ContractFirstDemo {

    // Kontrak yang disetujui backend + frontend + mobile SEBELUM kode ditulis.
    static final Map<String, Set<String>> CONTRACT = Map.of(
        "GET /v1/orders/{id}", Set.of("id", "status", "total"),
        "POST /v1/orders", Set.of("id", "status")
    );

    // Implementasi backend (setelah refactor): field "total" diganti nama jadi "totalAmount".
    static final Map<String, Set<String>> IMPLEMENTATION = Map.of(
        "GET /v1/orders/{id}", Set.of("id", "status", "totalAmount"),
        "POST /v1/orders", Set.of("id", "status")
    );

    public static void main(String[] args) {
        int violations = 0;
        for (var route : new TreeSet<>(CONTRACT.keySet())) {
            Set<String> promised = CONTRACT.get(route);
            Set<String> actual = IMPLEMENTATION.getOrDefault(route, Set.of());

            Set<String> missing = new TreeSet<>(promised);
            missing.removeAll(actual);
            Set<String> extra = new TreeSet<>(actual);
            extra.removeAll(promised);

            if (missing.isEmpty() && extra.isEmpty()) {
                System.out.println("OK   " + route);
            } else {
                violations++;
                System.out.println("FAIL " + route
                    + " | dijanjikan tapi hilang: " + missing
                    + " | ada tapi tidak ada di kontrak: " + extra);
            }
        }
        System.out.println(violations == 0
            ? "Build lolos: implementasi sesuai kontrak."
            : "Build GAGAL: " + violations + " endpoint melanggar kontrak. Ubah kode, atau ubah kontrak lewat PR yang disetujui consumer.");
    }
}
