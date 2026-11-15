import java.util.*;
import java.util.regex.Pattern;

// Jalankan: java CapstoneReviewDemo.java
// Review capstone = cek tiap acceptance criteria dengan bukti, bukan kesan "kayaknya udah bagus".
// Di sini: scan potongan kode/config buat anti-pattern yang dilarang acceptance criteria capstone.
public class CapstoneReviewDemo {

    record Rule(String name, String dimension, Pattern pattern) {}

    static final List<Rule> RULES = List.of(
        new Rule("ddl-auto: update", "Production Readiness", Pattern.compile("ddl-auto:\\s*update")),
        new Rule("secret hardcoded", "Security", Pattern.compile("(?i)(password|secret|api[-_]?key)\\s*[:=]\\s*\"?[A-Za-z0-9]{6,}")),
        new Rule("catch menelan exception", "Correctness", Pattern.compile("catch\\s*\\([^)]*\\)\\s*\\{\\s*\\}")),
        new Rule("raw type", "Maintainability", Pattern.compile("\\b(List|Map|Set)\\s+\\w+\\s*=\\s*new\\s+(ArrayList|HashMap|HashSet)\\s*\\(")));

    static List<String> review(String source) {
        List<String> findings = new ArrayList<>();
        for (Rule r : RULES)
            if (r.pattern().matcher(source).find()) findings.add("[" + r.dimension() + "] " + r.name());
        return findings;
    }

    public static void main(String[] args) {
        String draft = """
            spring.jpa.hibernate.ddl-auto: update
            payment.api-key: sk12345678
            try { gateway.charge(); } catch (Exception e) { }
            List items = new ArrayList();
            """;
        String fixed = """
            spring.jpa.hibernate.ddl-auto: validate
            payment.api-key: ${PAYMENT_API_KEY}
            try { gateway.charge(); } catch (Exception e) { log.error("charge gagal", e); throw e; }
            List<String> items = new ArrayList<>();
            """;

        var before = review(draft);
        var after = review(fixed);
        System.out.println("Review pertama : " + before.size() + " temuan");
        before.forEach(f -> System.out.println("  " + f));
        System.out.println("Review ulang   : " + after.size() + " temuan " + after);
        System.out.println("\nCatatan: scan ini cuma nangkap pola teks. Kriteria lain (test hijau, load test, insiden bisa didiagnosis) butuh bukti dijalankan.");

        if (before.size() != 4) throw new AssertionError("draft harusnya kena 4 anti-pattern, dapat " + before);
        if (!after.isEmpty()) throw new AssertionError("versi fixed harusnya bersih, dapat " + after);
    }
}
