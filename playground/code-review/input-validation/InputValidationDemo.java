import java.nio.file.Path;
import java.util.Map;

// Code Review, Lesson 3.2: well-formed input that is still dangerous. Plain Java, no Spring needed.
public class InputValidationDemo {

    static final long PRICE_CENTS = 50_000;
    static final int MAX_QUANTITY = 100;
    static final Map<String, String> SORT_COLUMNS = Map.of("date", "created_at", "total", "total_cents");
    static final Path INVOICE_DIR = Path.of("invoices").toAbsolutePath().normalize();

    // Before: the type is right, so it is accepted.
    static long totalBefore(int quantity) {
        return quantity * PRICE_CENTS;
    }

    // After: reject anything outside the business range.
    static long totalAfter(int quantity) {
        if (quantity < 1 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("quantity must be 1.." + MAX_QUANTITY);
        }
        return quantity * PRICE_CENTS;
    }

    // Allowlist: the request picks a key, never the column text.
    static String sortColumn(String requested) {
        String column = SORT_COLUMNS.get(requested);
        if (column == null) throw new IllegalArgumentException("unknown sort");
        return column;
    }

    static Path invoiceBefore(String name) {
        return INVOICE_DIR.resolve(name).normalize();
    }

    // Normalize first, then check. shortcut: does not follow symlinks, use toRealPath() if the dir can contain them.
    static Path invoiceAfter(String name) {
        Path file = invoiceBefore(name);
        if (!file.startsWith(INVOICE_DIR)) throw new IllegalArgumentException("outside invoice dir");
        return file;
    }

    static boolean rejects(Runnable action) {
        try {
            action.run();
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    static void check(boolean ok, String what) {
        if (!ok) throw new AssertionError(what);
    }

    public static void main(String[] args) {
        System.out.println("total before, quantity=-5 : " + totalBefore(-5));
        check(totalBefore(-5) < 0, "bug should reproduce");
        check(rejects(() -> totalAfter(-5)), "negative rejected");
        check(rejects(() -> totalAfter(0)), "zero rejected");
        check(rejects(() -> totalAfter(101)), "above max rejected");
        check(totalAfter(1) == PRICE_CENTS && totalAfter(100) == 100 * PRICE_CENTS, "boundaries accepted");

        check(sortColumn("date").equals("created_at"), "known sort accepted");
        check(rejects(() -> sortColumn("total; DROP TABLE orders")), "unknown sort rejected");

        String evil = "../secret.txt";
        System.out.println("path before : " + invoiceBefore(evil));
        check(!invoiceBefore(evil).startsWith(INVOICE_DIR), "traversal should reproduce");
        check(rejects(() -> invoiceAfter(evil)), "traversal rejected");
        check(!rejects(() -> invoiceAfter("INV-1.pdf")), "normal file accepted");

        System.out.println("ALL CHECKS PASSED");
    }
}
