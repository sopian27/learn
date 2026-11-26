// Run: java SolidReviewDemo.java
public class SolidReviewDemo {

    // ---- Before: the same "which method?" decision lives in two places ----
    static long feeBefore(String method, long amount) {
        return switch (method) {
            case "VA" -> 4_000;
            case "EWALLET" -> amount * 2 / 100;
            case "PAYLATER" -> amount * 3 / 100; // the PR added this line
            default -> 0;
        };
    }

    // Not in the diff, so the reviewer never saw it.
    static int refundDaysBefore(String method) {
        return switch (method) {
            case "VA" -> 3;
            case "EWALLET" -> 1;
            default -> 0; // PAYLATER silently promises an instant refund
        };
    }

    // ---- After: a new method cannot compile until it answers both questions ----
    sealed interface PaymentMethod permits Va, EWallet, PayLater {
        long fee(long amount);
        int refundDays();
    }

    record Va() implements PaymentMethod {
        public long fee(long amount) { return 4_000; }
        public int refundDays() { return 3; }
    }

    record EWallet() implements PaymentMethod {
        public long fee(long amount) { return amount * 2 / 100; }
        public int refundDays() { return 1; }
    }

    record PayLater() implements PaymentMethod {
        public long fee(long amount) { return amount * 3 / 100; }
        public int refundDays() { return 14; }
    }

    static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        long amount = 100_000;

        System.out.printf("before PAYLATER fee=%d refundDays=%d  <- wrong, no error%n",
                feeBefore("PAYLATER", amount), refundDaysBefore("PAYLATER"));
        System.out.printf("before typo EWALET fee=%d  <- free transaction, no error%n",
                feeBefore("EWALET", amount));

        PaymentMethod payLater = new PayLater();
        System.out.printf("after  PAYLATER fee=%d refundDays=%d%n",
                payLater.fee(amount), payLater.refundDays());

        check(refundDaysBefore("PAYLATER") == 0, "bug should reproduce");
        check(payLater.fee(amount) == 3_000, "fee");
        check(payLater.refundDays() == 14, "refund days");
        System.out.println("checks passed");
    }
}
