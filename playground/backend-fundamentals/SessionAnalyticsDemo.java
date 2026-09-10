import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionAnalyticsDemo {

    private static final Map<String, List<UserActivity>> sessionActivityLog =
            new ConcurrentHashMap<>();

    private static final int MAX_ENTRIES_PER_SESSION = 100;

    public static void main(String[] args) throws Exception {

        // Simulasi ribuan session yang berbeda
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

        System.out.println("Finished.");
        System.out.println("Total sessions: " + sessionActivityLog.size());

        // Supaya program tetap hidup ketika dianalisis dengan jcmd / jmap
        Thread.sleep(300_000);
    }

    static void recordActivity(String sessionId, UserActivity activity) {
        sessionActivityLog
                .computeIfAbsent(sessionId, k -> new ArrayList<>())
                .add(activity);
    }

    static List<UserActivity> getActivityForSession(String sessionId) {
        return sessionActivityLog.getOrDefault(sessionId, List.of());
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