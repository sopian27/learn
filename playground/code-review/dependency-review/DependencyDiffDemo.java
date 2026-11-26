import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// Code Review, Lesson 3.4: one line in pom.xml, several changes in what actually ships. All data is made up.
public class DependencyDiffDemo {

    // Stand-in for the groupIds your team already knows and trusts.
    static final Set<String> KNOWN_GROUPS = Set.of("org.springframework", "com.fasterxml.jackson.core", "org.apache.commons");

    // Compares two resolved dependency lists ("group:artifact" -> version), direct and transitive alike.
    static List<String> review(Map<String, String> before, Map<String, String> after) {
        var findings = new ArrayList<String>();
        new TreeMap<>(after).forEach((name, version) -> {
            var old = before.get(name);
            if (old == null) findings.add("ADDED    " + name + " " + version);
            else if (!old.equals(version)) findings.add("CHANGED  " + name + " " + old + " -> " + version);

            if (isFloating(version)) findings.add("FLOATING " + name + " " + version);
            if (!KNOWN_GROUPS.contains(name.split(":")[0])) findings.add("UNKNOWN  " + name);
        });
        return findings;
    }

    // Versions that can resolve to something different on the next build.
    static boolean isFloating(String version) {
        return version.endsWith("-SNAPSHOT") || version.equals("LATEST") || version.equals("RELEASE")
                || version.startsWith("[") || version.startsWith("(");
    }

    static void check(boolean ok, String what) {
        if (!ok) throw new AssertionError(what);
    }

    public static void main(String[] args) {
        var before = Map.of(
                "org.springframework:spring-web", "7.0.0",
                "com.fasterxml.jackson.core:jackson-databind", "2.19.0");

        // The PR adds ONE library. This is what the build resolves afterwards.
        var after = Map.of(
                "org.springframework:spring-web", "7.0.0",
                "com.fasterxml.jackson.core:jackson-databind", "2.13.0",
                "io.example.csv:easy-csv", "[1.0,)",
                "io.example.util:old-utils", "0.3.1");

        var findings = review(before, after);
        findings.forEach(System.out::println);

        check(findings.stream().anyMatch(f -> f.startsWith("CHANGED") && f.contains("jackson-databind")), "downgrade");
        check(findings.stream().anyMatch(f -> f.startsWith("ADDED") && f.contains("old-utils")), "transitive");
        check(findings.stream().anyMatch(f -> f.startsWith("FLOATING") && f.contains("easy-csv")), "range");
        check(review(before, before).isEmpty(), "no change means no findings");

        System.out.println("ALL CHECKS PASSED");
    }
}
