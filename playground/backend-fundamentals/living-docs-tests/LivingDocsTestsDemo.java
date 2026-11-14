import java.util.*;

/**
 * Demo test sebagai dokumentasi hidup (disederhanakan, tanpa JUnit).
 * Satu bug yang sama (diskon gold nggak kepotong), dua gaya test.
 * Liat mana yang bikin lu langsung tahu apa yang rusak.
 */
public class LivingDocsTestsDemo {
    // Kode produksi dengan bug: diskon gold harusnya 10%, tapi kelupaan.
    static long hargaAkhir(String tier, long harga) {
        return harga; // BUG: seharusnya gold -> harga * 90 / 100
    }

    // Gaya buruk: nama kabur, assert telanjang, nggak ada konteks.
    static void test1() {
        long r = hargaAkhir("gold", 100_000);
        if (!(r < 100_000)) throw new AssertionError("expected true but was false");
    }

    // Gaya bagus: nama = aturan bisnis, Given-When-Then, pesan gagal ada expected/actual.
    static void member_gold_dapat_diskon_10_persen() {
        // Given: member gold belanja 100.000
        String tier = "gold";
        long harga = 100_000;
        // When
        long actual = hargaAkhir(tier, harga);
        // Then
        long expected = 90_000;
        if (actual != expected)
            throw new AssertionError("tier=" + tier + ", harga=" + harga
                    + ": expected " + expected + " tapi dapat " + actual);
    }

    static void jalanin(String nama, Runnable test) {
        try {
            test.run();
            System.out.println("LULUS  " + nama);
        } catch (AssertionError e) {
            System.out.println("GAGAL  " + nama + "\n       -> " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        jalanin("test1", LivingDocsTestsDemo::test1);
        jalanin("member_gold_dapat_diskon_10_persen", LivingDocsTestsDemo::member_gold_dapat_diskon_10_persen);
    }
}
