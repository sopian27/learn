import java.util.*;

/**
 * Demo contract testing (disederhanakan, tanpa Pact).
 * Consumer (app mobile) nulis kontrak: field apa + tipe apa yang dia pakai.
 * Provider test: response asli harus memenuhi kontrak itu.
 * Rename field = merah. Nambah field baru = tetap hijau.
 */
public class ContractTestingDemo {

    // Kontrak dari sisi consumer: cuma field yang BENAR-BENAR dipakai.
    static final Map<String, Class<?>> CONTRACT_MOBILE = Map.of(
            "id", Long.class,
            "fullName", String.class,
            "email", String.class);

    // Provider v1: response lama.
    static Map<String, Object> providerV1() {
        return Map.of("id", 7L, "fullName", "Budi", "email", "budi@x.com");
    }

    // Provider v2: Safa rename fullName -> name. Test unit provider tetap hijau.
    static Map<String, Object> providerV2Rename() {
        return Map.of("id", 7L, "name", "Budi", "email", "budi@x.com");
    }

    // Provider v3: nambah field baru (additive), field lama utuh.
    static Map<String, Object> providerV3Additive() {
        return Map.of("id", 7L, "fullName", "Budi", "email", "budi@x.com", "avatarUrl", "http://x/a.png");
    }

    static List<String> verify(Map<String, Class<?>> contract, Map<String, Object> response) {
        List<String> violations = new ArrayList<>();
        new TreeMap<>(contract).forEach((field, type) -> {
            Object v = response.get(field);
            if (v == null) violations.add("field '" + field + "' hilang");
            else if (!type.isInstance(v))
                violations.add("field '" + field + "' harus " + type.getSimpleName()
                        + ", dapat " + v.getClass().getSimpleName());
        });
        return violations;
    }

    static void report(String label, Map<String, Object> response) {
        List<String> v = verify(CONTRACT_MOBILE, response);
        System.out.printf("%-34s: %s%s%n", label, v.isEmpty() ? "HIJAU" : "MERAH",
                v.isEmpty() ? "" : " (" + String.join("; ", v) + ")");
    }

    public static void main(String[] args) {
        report("provider v1 (awal)", providerV1());
        report("provider v2 (rename fullName)", providerV2Rename());
        report("provider v3 (tambah avatarUrl)", providerV3Additive());
    }
}
