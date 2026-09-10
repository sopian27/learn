import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionAnalyticsDemoV2 {

    private static final Map<String, List<UserActivity>> sessionActivityLog =
            new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {

        // Simulasi 1 juta session unik
        for (int i = 0; i < 1_000_000; i++) {

            String sessionId = "session-" + i;

            recordActivity(
                    sessionId,
                    new UserActivity("LOGIN", "User activity " + i)
            );

            if (i % 100_000 == 0) {
                System.out.println(
                        "Recorded: " + i +
                        ", sessions: " + sessionActivityLog.size()
                );
            }
        }

        System.out.println();
        System.out.println("Before flush:");
        System.out.println("Total sessions: " + sessionActivityLog.size());

        // Simulasi scheduled job jam 2 pagi
        flushDailyAnalytics();

        System.out.println();
        System.out.println("After flush:");
        System.out.println("Total sessions: " + sessionActivityLog.size());

        // Beri waktu untuk dianalisis menggunakan jcmd
        Thread.sleep(300_000);
    }

    static void recordActivity(
            String sessionId,
            UserActivity activity) {

        sessionActivityLog
                .computeIfAbsent(sessionId, k -> new ArrayList<>())
                .add(activity);
    }

    static void flushDailyAnalytics() {

        for (Map.Entry<String, List<UserActivity>> entry
                : sessionActivityLog.entrySet()) {

            String sessionId = entry.getKey();
            List<UserActivity> activities = entry.getValue();

            // Simulasi kirim ke data warehouse
            sendToDataWarehouse(sessionId, activities);

            // Setelah berhasil diproses, hapus dari memory
            sessionActivityLog.remove(sessionId);
        }
    }

    static void sendToDataWarehouse(
            String sessionId,
            List<UserActivity> activities) {

        // Simulasi proses pengiriman.
        // Anggap selalu berhasil.
    }

    static class UserActivity {

        private final String type;
        private final String description;

        UserActivity(String type, String description) {
            this.type = type;
            this.description = description;
        }
    }
}