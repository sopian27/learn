// Run: java CleanPrinciplesDemo.java
public class CleanPrinciplesDemo {

    // ---- Before: one method for two rules that only happen to share numbers ----
    static long tenPercentCapped(long amount, int percent) {
        // "percent" stands for the literal Budi edited from 10 to 20
        return Math.min(amount * percent / 100, 50_000);
    }

    static long beforeDiscount(long amount, int editedPercent)   { return tenPercentCapped(amount, editedPercent); }
    static long beforeCommission(long amount, int editedPercent) { return tenPercentCapped(amount, editedPercent); }

    // ---- After: the arithmetic is shared, each rule owns its numbers ----
    record Rule(int percent, long cap) {
        long applyTo(long amount) {
            return Math.min(amount * percent / 100, cap);
        }
    }

    static final Rule COMMISSION = new Rule(10, 50_000); // owned by the merchant contract

    static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        long amount = 200_000;

        System.out.println("before, promo 10%: discount=" + beforeDiscount(amount, 10)
                + " commission=" + beforeCommission(amount, 10));
        System.out.println("before, promo 20%: discount=" + beforeDiscount(amount, 20)
                + " commission=" + beforeCommission(amount, 20) + "  <- nobody asked for this");

        Rule promo = new Rule(20, 50_000); // owned by marketing
        System.out.println("after,  promo 20%: discount=" + promo.applyTo(amount)
                + " commission=" + COMMISSION.applyTo(amount));

        check(beforeCommission(amount, 20) == 40_000, "bug should reproduce");
        check(promo.applyTo(amount) == 40_000, "discount follows the promo");
        check(COMMISSION.applyTo(amount) == 20_000, "commission must not move");
        check(COMMISSION.applyTo(1_000_000) == 50_000, "cap applies");
        System.out.println("checks passed");
    }
}
