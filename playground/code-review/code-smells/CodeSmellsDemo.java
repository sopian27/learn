// Run: java CodeSmellsDemo.java
public class CodeSmellsDemo {

    // ---- Before: every rule switches on the same type string ----
    static long beforeFee(String method, long amount) {
        return switch (method) {
            case "BANK" -> 4_000;
            case "CARD" -> amount * 2 / 100;
            case "EWALLET" -> amount * 1 / 100;
            default -> 0;
        };
    }

    static String beforeLabel(String method) {
        return switch (method) {
            case "BANK" -> "Transfer Bank";
            case "CARD" -> "Kartu Kredit";
            case "EWALLET" -> "E-Wallet";
            default -> "-";
        };
    }

    static int beforeSettlementDays(String method) {
        return switch (method) {
            case "BANK" -> 1;
            case "CARD" -> 3;
            default -> 0; // EWALLET was never added here
        };
    }

    // ---- After: one place per method; the constructor demands every value ----
    enum PaymentMethod {
        BANK("Transfer Bank", 4_000, 0, 1),
        CARD("Kartu Kredit", 0, 2, 3),
        EWALLET("E-Wallet", 0, 1, 2);

        final String label;
        final long flatFee;
        final int feePercent;
        final int settlementDays;

        PaymentMethod(String label, long flatFee, int feePercent, int settlementDays) {
            this.label = label;
            this.flatFee = flatFee;
            this.feePercent = feePercent;
            this.settlementDays = settlementDays;
        }

        long fee(long amount) {
            return flatFee + amount * feePercent / 100;
        }
    }

    static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        long amount = 200_000;
        System.out.println("before, EWALLET settlement days=" + beforeSettlementDays("EWALLET")
                + "  <- paid out the same day");
        System.out.println("after,  EWALLET settlement days=" + PaymentMethod.EWALLET.settlementDays);

        // Characterization: behavior that was already right must not move.
        for (PaymentMethod m : PaymentMethod.values()) {
            check(m.fee(amount) == beforeFee(m.name(), amount), "fee changed for " + m);
            check(m.label.equals(beforeLabel(m.name())), "label changed for " + m);
            if (m != PaymentMethod.EWALLET) {
                check(m.settlementDays == beforeSettlementDays(m.name()), "days changed for " + m);
            }
        }
        check(beforeSettlementDays("EWALLET") == 0, "bug should reproduce");
        check(PaymentMethod.EWALLET.settlementDays == 2, "bug fixed");
        System.out.println("checks passed");
    }
}
