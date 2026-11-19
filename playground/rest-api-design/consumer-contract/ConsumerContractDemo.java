import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Lesson 8.4: consumer menulis kontrak (field + tipe yang dia pakai), provider memverifikasi sebelum rilis.
public class ConsumerContractDemo {

    // Kontrak milik consumer: hanya field yang benar-benar dia baca.
    record Expectation(String field, Class<?> type) {}

    static final List<Expectation> MOBILE_CONTRACT =
            List.of(new Expectation("id", Integer.class), new Expectation("total", Integer.class));

    static Map<String, Object> providerV1() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", 7);
        m.put("total", 150000);
        m.put("note", "kirim pagi"); // field yang tidak dipakai consumer
        return m;
    }

    // Provider membuktikan responsenya masih memenuhi kontrak. Return daftar pelanggaran.
    static List<String> verify(Map<String, Object> response, List<Expectation> contract) {
        return contract.stream().map(e -> {
            Object v = response.get(e.field());
            if (v == null) return e.field() + " hilang";
            if (!e.type().isInstance(v)) return e.field() + " bukan " + e.type().getSimpleName();
            return null;
        }).filter(x -> x != null).toList();
    }

    static void run(String label, Map<String, Object> response) {
        List<String> bad = verify(response, MOBILE_CONTRACT);
        System.out.println("== " + label + " -> " + (bad.isEmpty() ? "AMAN" : "RUSAK " + bad));
    }

    public static void main(String[] args) {
        run("v1 sekarang", providerV1());

        Map<String, Object> tambahField = providerV1();
        tambahField.put("currency", "IDR");
        run("tambah field (additive)", tambahField);

        Map<String, Object> hapusNote = providerV1();
        hapusNote.remove("note");
        run("hapus field yang tidak dipakai consumer", hapusNote);

        Map<String, Object> ubahTipe = providerV1();
        ubahTipe.put("total", Map.of("amount", 150000, "currency", "IDR"));
        run("total jadi objek", ubahTipe);

        Map<String, Object> rename = providerV1();
        rename.put("grandTotal", rename.remove("total"));
        run("rename total", rename);

        // Cek: additive dan hapus field tak terpakai aman; ubah tipe dan rename ketahuan.
        if (!verify(tambahField, MOBILE_CONTRACT).isEmpty()) throw new AssertionError("additive harus aman");
        if (!verify(hapusNote, MOBILE_CONTRACT).isEmpty()) throw new AssertionError("hapus field tak terpakai harus aman");
        if (verify(ubahTipe, MOBILE_CONTRACT).isEmpty()) throw new AssertionError("ubah tipe harus ketahuan");
        if (verify(rename, MOBILE_CONTRACT).isEmpty()) throw new AssertionError("rename harus ketahuan");
    }
}
