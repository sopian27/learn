// Run: java ReadabilityReviewDemo.java
public class ReadabilityReviewDemo {

    // ---- Before: the reader has to decode status numbers, "d", "flag" and a negated condition ----
    record Order(int status, int d) {}

    static boolean check(Order o, boolean flag) {
        if (!(o.status() != 2 || o.d() > 7)) {
            return true;
        } else if (flag) {
            if (o.status() == 2 || o.status() == 3) {
                return true;
            }
        }
        return false;
    }

    // ---- After: same behaviour, the names carry the rules ----
    enum Status { NEW, CANCELLED, PAID, SHIPPED, DONE } // ordinal matches the old numbers

    static final int REFUND_WINDOW_DAYS = 7;

    static boolean canCustomerRefund(Status status, int daysSincePayment) {
        return status == Status.PAID && daysSincePayment <= REFUND_WINDOW_DAYS;
    }

    static boolean canAdminRefund(Status status) {
        return status == Status.PAID || status == Status.SHIPPED; // no time limit
    }

    static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        // The call site hides the meaning: is "true" notify? force? admin?
        System.out.println("before check(shipped, 90 days, true) = " + check(new Order(3, 90), true));
        System.out.println("after  canCustomerRefund(SHIPPED, 90) = " + canCustomerRefund(Status.SHIPPED, 90));
        System.out.println("after  canAdminRefund(SHIPPED)        = " + canAdminRefund(Status.SHIPPED));

        // A readability refactor must not change behaviour, so compare every combination.
        for (Status status : Status.values()) {
            for (int days = 0; days <= 10; days++) {
                Order order = new Order(status.ordinal(), days);
                boolean customer = canCustomerRefund(status, days);
                check(check(order, false) == customer, "customer " + status + " " + days);
                check(check(order, true) == (customer || canAdminRefund(status)), "admin " + status + " " + days);
            }
        }
        System.out.println("checks passed");
    }
}
