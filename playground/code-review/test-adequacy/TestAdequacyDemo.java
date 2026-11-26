// Run: java TestAdequacyDemo.java
import java.util.function.LongUnaryOperator;

public class TestAdequacyDemo {

    // Rule from the ticket: orders of Rp100,000 or more ship for free, otherwise Rp15,000.
    static long buggyFee(long total) {
        if (total > 100_000) return 0; // bug: should be >=
        return 15_000;
    }

    static long fixedFee(long total) {
        if (total >= 100_000) return 0;
        return 15_000;
    }

    // Runs every line and both branches, but only with values far from the boundary.
    static boolean weakSuitePasses(LongUnaryOperator fee) {
        return fee.applyAsLong(150_000) == 0
                && fee.applyAsLong(50_000) == 15_000;
    }

    // Same rule, checked on both sides of the boundary.
    static boolean boundarySuitePasses(LongUnaryOperator fee) {
        return fee.applyAsLong(99_999) == 15_000
                && fee.applyAsLong(100_000) == 0
                && fee.applyAsLong(100_001) == 0;
    }

    static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    static void report(String name, LongUnaryOperator fee) {
        System.out.println(name + ": weak=" + (weakSuitePasses(fee) ? "PASS" : "FAIL")
                + " boundary=" + (boundarySuitePasses(fee) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        report("buggy (> 100000) ", TestAdequacyDemo::buggyFee);
        report("fixed (>= 100000)", TestAdequacyDemo::fixedFee);

        check(weakSuitePasses(TestAdequacyDemo::buggyFee), "weak suite should miss the bug");
        check(!boundarySuitePasses(TestAdequacyDemo::buggyFee), "boundary suite should catch the bug");
        check(weakSuitePasses(TestAdequacyDemo::fixedFee), "weak suite accepts the fix");
        check(boundarySuitePasses(TestAdequacyDemo::fixedFee), "boundary suite accepts the fix");
        System.out.println("checks passed");
    }
}
