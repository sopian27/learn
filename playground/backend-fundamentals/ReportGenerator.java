import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {
    private static final Map<String, Report> reportCache = new HashMap<>(); // (1)

    public void generate(String customerId) {
        int retryCount = 0;                          // (2)
        Report report = buildReport(customerId);      // (3)

        if (shouldCache(customerId)) {
            reportCache.put(customerId, report);       // (4)
        }

        report = null;                                 // (5)
        System.out.println("Selesai generate untuk " + customerId);
    }

    private Report buildReport(String customerId) {
        Report r = new Report(customerId);              // (6)
        return r;
    }

    private boolean shouldCache(String customerId) {
        return customerId.startsWith("VIP");
    }
}