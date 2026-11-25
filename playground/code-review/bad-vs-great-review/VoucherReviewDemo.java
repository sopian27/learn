import java.math.BigDecimal;

/** Lesson 1.2: "50% off, max 20000" where the PR forgot the max. */
public class VoucherReviewDemo {

    static final BigDecimal RATE = new BigDecimal("0.50");
    static final BigDecimal MAX_DISCOUNT = new BigDecimal("20000");

    // Version in the PR: the cap is never applied.
    static BigDecimal discountBuggy(BigDecimal total) {
        return total.multiply(RATE);
    }

    // After review.
    static BigDecimal discountFixed(BigDecimal total) {
        return total.multiply(RATE).min(MAX_DISCOUNT);
    }

    public static void main(String[] args) {
        // 30000 is the PR's only test case; 40000 is the boundary; 500000 exposes the bug.
        for (String value : new String[] {"30000", "40000", "500000"}) {
            BigDecimal total = new BigDecimal(value);
            System.out.println("total=" + total
                    + " buggy=" + discountBuggy(total).toBigInteger()
                    + " fixed=" + discountFixed(total).toBigInteger());
        }
    }
}
