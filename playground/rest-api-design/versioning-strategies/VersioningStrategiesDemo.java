import java.util.Map;

// Lesson 8.2: tiga cara klien memilih versi. Satu handler, tiga cara baca versi.
public class VersioningStrategiesDemo {

    // v1: total angka. v2: total objek {amount, currency}.
    static String order(int version) {
        return switch (version) {
            case 1 -> "{\"id\":7,\"total\":150000}";
            case 2 -> "{\"id\":7,\"total\":{\"amount\":150000,\"currency\":\"IDR\"}}";
            default -> throw new IllegalArgumentException("404/400: versi " + version + " tidak dikenal");
        };
    }

    // 1) URI: /v2/orders/7. Kelihatan di log dan browser, tapi URL resource ikut berubah.
    static int fromUri(String path) {
        return Integer.parseInt(path.split("/")[1].substring(1));
    }

    // 2) Custom header: X-Api-Version: 2. URL tetap, tapi gampang kelupaan. Default wajib jelas.
    static int fromHeader(Map<String, String> h) {
        return Integer.parseInt(h.getOrDefault("X-Api-Version", "1"));
    }

    // 3) Content negotiation: Accept: application/vnd.shop.v2+json. Paling "benar" secara REST, paling ribet dites.
    static int fromAccept(Map<String, String> h) {
        String accept = h.getOrDefault("Accept", "application/json");
        int i = accept.indexOf(".v");
        return i < 0 ? 1 : Integer.parseInt(accept.substring(i + 2, accept.indexOf('+', i)));
    }

    static void show(String label, int version) {
        System.out.printf("%-34s -> v%d %s%n", label, version, order(version));
    }

    public static void main(String[] args) {
        show("GET /v1/orders/7", fromUri("/v1/orders/7"));
        show("GET /v2/orders/7", fromUri("/v2/orders/7"));
        show("X-Api-Version: 2", fromHeader(Map.of("X-Api-Version", "2")));
        show("(tanpa header, default)", fromHeader(Map.of()));
        show("Accept: ...vnd.shop.v2+json", fromAccept(Map.of("Accept", "application/vnd.shop.v2+json")));
        show("Accept: application/json", fromAccept(Map.of("Accept", "application/json")));

        // Cek: default tanpa versi harus tetap versi lama, supaya klien lama tidak pecah.
        if (fromHeader(Map.of()) != 1 || fromAccept(Map.of()) != 1) throw new AssertionError("default harus v1");
        try {
            order(9);
        } catch (IllegalArgumentException e) {
            System.out.println("v9 -> " + e.getMessage());
        }
    }
}
