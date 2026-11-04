import java.util.ArrayList;
import java.util.List;

/**
 * Companion Lesson 1.4 (Idiom Effective Java yang sering dilanggar).
 * Run: java EffectiveJavaIdiomsDemo.java   (Java 17+ single-file mode)
 */
public class EffectiveJavaIdiomsDemo {

    // --- BURUK: mutable, bocorin list internal ---
    static class BrokenOrder {
        private final List<Long> itemPrices;
        BrokenOrder(List<Long> itemPrices) { this.itemPrices = itemPrices; }
        List<Long> getItemPrices() { return itemPrices; }
        long total() { return itemPrices.stream().mapToLong(Long::longValue).sum(); }
    }

    // --- BENAR: immutable, defensive copy masuk dan keluar ---
    record Order(List<Long> itemPrices) {
        Order { itemPrices = List.copyOf(itemPrices); }   // copy + immutable, null ditolak
        long total() { return itemPrices.stream().mapToLong(Long::longValue).sum(); }
    }

    // --- BURUK: toString otomatis ikut bocorin secret ke log ---
    static class BrokenUser {
        String email = "dina@kantor.com";
        String apiKey = "sk-live-123456";
        @Override public String toString() { return "User{email=" + email + ", apiKey=" + apiKey + "}"; }
    }

    // --- BENAR: toString sengaja milih field aman ---
    static class User {
        private final String email;
        private final String apiKey;
        User(String email, String apiKey) { this.email = email; this.apiKey = apiKey; }
        @Override public String toString() { return "User{email=" + email + "}"; }
    }

    // --- BURUK: constructor dengan argumen sama tipe, gampang ketuker ---
    static class BrokenMoney {
        final String currency; final String amount;
        BrokenMoney(String currency, String amount) { this.currency = currency; this.amount = amount; }
        @Override public String toString() { return currency + " " + amount; }
    }

    // --- BENAR: static factory bernama + validasi ---
    record Money(String currency, long amount) {
        Money {
            if (amount < 0) throw new IllegalArgumentException("amount negatif: " + amount);
        }
        static Money idr(long amount) { return new Money("IDR", amount); }
    }

    public static void main(String[] args) {
        List<Long> prices = new ArrayList<>(List.of(100L, 200L));

        BrokenOrder broken = new BrokenOrder(prices);
        System.out.println("1a. broken total sebelum = " + broken.total());
        prices.add(9_000_000L);                       // caller luar ngubah list
        broken.getItemPrices().clear();               // atau lewat getter
        System.out.println("1b. broken total sesudah = " + broken.total() + "  (order berubah tanpa izin!)");

        Order safe = new Order(new ArrayList<>(List.of(100L, 200L)));
        try {
            safe.itemPrices().add(1L);
        } catch (UnsupportedOperationException e) {
            System.out.println("2.  immutable order = total " + safe.total() + ", add() ditolak");
        }

        System.out.println("3a. log broken = " + new BrokenUser());
        System.out.println("3b. log fixed  = " + new User("dina@kantor.com", "sk-live-123456"));

        BrokenMoney swapped = new BrokenMoney("50000", "IDR");   // kebalik, compile tetap lolos
        System.out.println("4a. broken money = " + swapped);
        System.out.println("4b. factory      = " + Money.idr(50_000));
        try {
            Money.idr(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("4c. invalid ditolak = " + e.getMessage());
        }
    }
}
