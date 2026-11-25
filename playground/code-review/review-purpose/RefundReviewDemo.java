import java.math.BigDecimal;

/** Lesson 1.1: the refund bug that passes a happy-path test, then the fix. */
public class RefundReviewDemo {

    static class Order {
        final BigDecimal paid = new BigDecimal("100000");
        BigDecimal refunded = BigDecimal.ZERO;
    }

    // Version in the PR: checks only the amount of THIS request.
    static boolean refundBuggy(Order order, BigDecimal amount) {
        if (amount.compareTo(order.paid) > 0) return false;
        order.refunded = order.refunded.add(amount);
        return true;
    }

    // After review: positive amount, and the total of ALL refunds is capped.
    // shortcut: in-memory and not thread-safe; a real service needs a row lock or a conditional UPDATE.
    static boolean refundFixed(Order order, BigDecimal amount) {
        if (amount.signum() <= 0) return false;
        if (order.refunded.add(amount).compareTo(order.paid) > 0) return false;
        order.refunded = order.refunded.add(amount);
        return true;
    }

    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal("70000");

        Order buggy = new Order();
        System.out.println("buggy  #1 accepted=" + refundBuggy(buggy, amount));
        System.out.println("buggy  #2 accepted=" + refundBuggy(buggy, amount));
        System.out.println("buggy  paid=" + buggy.paid + " refunded=" + buggy.refunded);

        Order fixed = new Order();
        System.out.println("fixed  #1 accepted=" + refundFixed(fixed, amount));
        System.out.println("fixed  #2 accepted=" + refundFixed(fixed, amount));
        System.out.println("fixed  paid=" + fixed.paid + " refunded=" + fixed.refunded);
    }
}
