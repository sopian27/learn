import java.util.Map;

// Code Review, Lesson 3.3: how a secret leaks, and what the fix looks like. All values are fake.
public class SecretsDemo {

    // Before: the secret lives in source, and the record prints it.
    record PaymentConfigBefore(String url, String apiKey) {}

    // After: the secret comes from outside, startup fails without it, and toString() hides it.
    record PaymentConfig(String url, String apiKey) {
        PaymentConfig {
            if (apiKey == null || apiKey.isBlank()) {
                throw new IllegalStateException("PAYMENT_API_KEY is not set");
            }
        }

        @Override
        public String toString() {
            return "PaymentConfig[url=" + url + ", apiKey=***]";
        }
    }

    static PaymentConfig load(Map<String, String> env) {
        return new PaymentConfig("https://pay.example.test", env.get("PAYMENT_API_KEY"));
    }

    static boolean failsToStart(Map<String, String> env) {
        try {
            load(env);
            return false;
        } catch (IllegalStateException e) {
            return true;
        }
    }

    static void check(boolean ok, String what) {
        if (!ok) throw new AssertionError(what);
    }

    public static void main(String[] args) {
        var before = new PaymentConfigBefore("https://pay.example.test", "FAKE-KEY-123");
        System.out.println("log before : " + before);
        check(before.toString().contains("FAKE-KEY-123"), "leak should reproduce");

        // A Map stands in for System.getenv() so the demo needs no setup.
        var after = load(Map.of("PAYMENT_API_KEY", "FAKE-KEY-456"));
        System.out.println("log after  : " + after);
        check(!after.toString().contains("FAKE-KEY-456"), "secret must be masked");
        check(after.apiKey().equals("FAKE-KEY-456"), "value still usable");

        check(failsToStart(Map.of()), "missing secret must stop startup");
        check(failsToStart(Map.of("PAYMENT_API_KEY", "  ")), "blank secret must stop startup");

        System.out.println("ALL CHECKS PASSED");
    }
}
