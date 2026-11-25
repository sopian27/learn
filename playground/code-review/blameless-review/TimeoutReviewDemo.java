import java.time.Duration;

/** Lesson 1.3: "30" meant seconds, the code read milliseconds, nobody dared to ask. */
public class TimeoutReviewDemo {

    // Pretend the bank normally answers in 800 ms.
    static final Duration BANK_LATENCY = Duration.ofMillis(800);

    // Version in the PR: the unit lives only in the author's head.
    static Duration timeoutBuggy(String configValue) {
        return Duration.ofMillis(Long.parseLong(configValue));
    }

    // After review: the config carries its unit (ISO-8601), a bare number is rejected.
    static Duration timeoutFixed(String configValue) {
        return Duration.parse(configValue);
    }

    static String call(Duration timeout) {
        return BANK_LATENCY.compareTo(timeout) <= 0 ? "OK" : "TIMEOUT";
    }

    public static void main(String[] args) {
        System.out.println("buggy \"30\"    -> " + timeoutBuggy("30") + " " + call(timeoutBuggy("30")));
        System.out.println("fixed \"PT30S\" -> " + timeoutFixed("PT30S") + " " + call(timeoutFixed("PT30S")));
        try {
            timeoutFixed("30");
            System.out.println("fixed \"30\"    -> accepted (unexpected)");
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("fixed \"30\"    -> rejected: unit missing");
        }
    }
}
