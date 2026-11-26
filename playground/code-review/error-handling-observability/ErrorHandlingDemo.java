// Run: java ErrorHandlingDemo.java
import java.util.ArrayList;
import java.util.List;

public class ErrorHandlingDemo {

    // Stand-ins for the database, the log file and the metrics registry.
    static final List<String> paidOrders = new ArrayList<>();
    static final List<String> logs = new ArrayList<>();
    static int callbackFailures;
    static boolean databaseDown;

    static void markPaid(String orderId) {
        if (databaseDown) throw new IllegalStateException("connection timeout");
        if (!paidOrders.contains(orderId)) paidOrders.add(orderId); // safe to repeat on retry
    }

    // ---- Before: swallowed, no context, and the provider is told all is well ----
    static int beforeCallback(String orderId) {
        try {
            markPaid(orderId);
        } catch (Exception e) {
            logs.add("ERROR something went wrong");
        }
        return 200;
    }

    // ---- After: add context, keep the cause, handle once at the boundary ----
    static final class PaymentCallbackException extends RuntimeException {
        PaymentCallbackException(String orderId, Throwable cause) {
            super("mark paid failed, orderId=" + orderId, cause);
        }
    }

    static void handle(String orderId) {
        try {
            markPaid(orderId);
        } catch (IllegalStateException e) {
            throw new PaymentCallbackException(orderId, e); // translate, do not log here
        }
    }

    static int afterCallback(String orderId) {
        try {
            handle(orderId);
            return 200;
        } catch (PaymentCallbackException e) {
            logs.add("ERROR " + e.getMessage() + " cause=" + e.getCause().getMessage());
            callbackFailures++;
            return 500; // the provider will send it again
        }
    }

    static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        databaseDown = true;

        int status = beforeCallback("ORD-1");
        System.out.println("before: status=" + status + " paid=" + paidOrders + " log=" + logs);
        check(status == 200 && paidOrders.isEmpty(), "bug should reproduce");

        logs.clear();
        status = afterCallback("ORD-1");
        System.out.println("after:  status=" + status + " failures=" + callbackFailures + " log=" + logs);
        check(status == 500 && callbackFailures == 1, "failure must be visible");
        check(logs.size() == 1 && logs.get(0).contains("ORD-1") && logs.get(0).contains("connection timeout"),
                "one log line with id and cause");

        databaseDown = false;
        check(afterCallback("ORD-1") == 200 && afterCallback("ORD-1") == 200, "retry succeeds");
        check(paidOrders.equals(List.of("ORD-1")), "repeated callback pays once");
        System.out.println("checks passed");
    }
}
