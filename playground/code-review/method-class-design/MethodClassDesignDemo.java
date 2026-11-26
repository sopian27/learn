// Run: java MethodClassDesignDemo.java
public class MethodClassDesignDemo {

    record Order(String id, long amount, boolean paid, String email) {}

    interface Refunds { void refund(Order order); }
    interface Notifier { void orderCancelled(Order order); }

    // Stand-ins for the payment gateway and the mail server.
    static long refunded;
    static int emails;

    // ---- Before: two adjacent booleans, and the caller decides a business rule ----
    static void beforeCancel(Order order, boolean refund, boolean notify) {
        if (refund) refunded += order.amount();
        if (notify) emails++;
    }

    // ---- After: one job, small dependencies, a call site that reads ----
    enum Notification { SEND, SKIP }

    static final class CancelOrder {
        private final Refunds refunds;
        private final Notifier notifier;

        CancelOrder(Refunds refunds, Notifier notifier) {
            this.refunds = refunds;
            this.notifier = notifier;
        }

        void cancel(Order order, Notification notification) {
            if (order.paid()) refunds.refund(order); // not the caller's choice
            if (notification == Notification.SEND) notifier.orderCancelled(order);
        }
    }

    static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    static void reset() {
        refunded = 0;
        emails = 0;
    }

    public static void main(String[] args) {
        Order paid = new Order("ORD-1", 150_000, true, "a@example.com");
        Order unpaid = new Order("ORD-2", 90_000, false, "b@example.com");

        // The ticket says: refund, no email. The arguments are swapped and it still compiles.
        beforeCancel(paid, false, true);
        System.out.println("before: refunded=" + refunded + " emails=" + emails + "  <- opposite of the ticket");
        check(refunded == 0 && emails == 1, "bug should reproduce");

        CancelOrder cancelOrder = new CancelOrder(o -> refunded += o.amount(), o -> emails++);

        reset();
        cancelOrder.cancel(paid, Notification.SKIP);
        System.out.println("after:  refunded=" + refunded + " emails=" + emails);
        check(refunded == 150_000 && emails == 0, "paid order: refund, no email");

        reset();
        cancelOrder.cancel(unpaid, Notification.SEND);
        check(refunded == 0 && emails == 1, "unpaid order: no refund, email sent");
        System.out.println("checks passed");
    }
}
