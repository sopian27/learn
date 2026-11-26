import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Run: java InstallmentReviewDemo.java
public class InstallmentReviewDemo {

    // PR version: looks right, happy path (1_200_000 / 12) is exact.
    static List<Long> splitBuggy(long total, int months) {
        return Collections.nCopies(months, total / months);
    }

    // After review: inputs rejected, remainder spread 1 rupiah per month.
    static List<Long> splitFixed(long total, int months) {
        if (total <= 0) throw new IllegalArgumentException("total must be > 0: " + total);
        if (months <= 0) throw new IllegalArgumentException("months must be > 0: " + months);
        if (total < months) throw new IllegalArgumentException("total too small for " + months + " months");

        long base = total / months;
        long remainder = total % months;
        List<Long> result = new ArrayList<>(months);
        for (int i = 0; i < months; i++) {
            result.add(i < remainder ? base + 1 : base);
        }
        return result;
    }

    static void show(String label, long total, int months, boolean fixed) {
        try {
            List<Long> parts = fixed ? splitFixed(total, months) : splitBuggy(total, months);
            long sum = parts.stream().mapToLong(Long::longValue).sum();
            System.out.printf("%s total=%d months=%d sum=%d lost=%d%n", label, total, months, sum, total - sum);
        } catch (RuntimeException e) {
            System.out.printf("%s total=%d months=%d -> %s: %s%n",
                    label, total, months, e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public static void main(String[] args) {
        long[][] cases = {
                {1_200_000, 12}, // happy path
                {1_000_000, 3},  // not divisible
                {1_000_000, 0},  // zero
                {-500_000, 5},   // negative
                {5, 12},         // smaller than month count
        };
        for (long[] c : cases) {
            show("buggy", c[0], (int) c[1], false);
            show("fixed", c[0], (int) c[1], true);
        }
    }
}
