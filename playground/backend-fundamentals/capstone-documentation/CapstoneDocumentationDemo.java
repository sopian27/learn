import java.util.*;

// Jalankan: java CapstoneDocumentationDemo.java
// Dokumentasi capstone yang bisa dicek: README wajib punya bagian kunci, tiap keputusan wajib punya alternatif ditolak + bukti.
// "Lint" ini cuma cek keberadaan teks; bukan pengganti reviewer yang mencoba menjalankan README.
public class CapstoneDocumentationDemo {

    record Decision(String title, String chosen, String rejected, String evidence) {}

    static final List<String> README_SECTIONS =
        List.of("Quick start", "Arsitektur", "API (OpenAPI)", "Engineering Decisions", "Hasil performa");

    static List<String> lintReadme(String readme) {
        var missing = new ArrayList<String>();
        for (String s : README_SECTIONS) if (!readme.contains("## " + s)) missing.add(s);
        return missing;
    }

    static List<String> lintDecision(Decision d) {
        var problems = new ArrayList<String>();
        if (d.rejected().isBlank()) problems.add(d.title() + ": alternatif yang ditolak kosong");
        if (d.evidence().isBlank()) problems.add(d.title() + ": bukti kosong");
        return problems;
    }

    public static void main(String[] args) {
        String draftReadme = "# Booking Slot\n## Quick start\n`docker compose up`\n## Arsitektur\n...";
        System.out.println("README draft, bagian hilang : " + lintReadme(draftReadme));

        String fullReadme = draftReadme + "\n## API (OpenAPI)\n## Engineering Decisions\n## Hasil performa";
        var missingFull = lintReadme(fullReadme);
        System.out.println("README lengkap, bagian hilang: " + missingFull);

        var weak = new Decision("Locking", "UNIQUE constraint", "", "");
        var good = new Decision("Locking", "UNIQUE(court_id, start_at)",
            "synchronized (nggak jalan antar instance)", "ConcurrentBookingIT: 20 thread -> 1 pemenang");
        System.out.println("Keputusan lemah : " + lintDecision(weak));
        System.out.println("Keputusan bagus : " + lintDecision(good));

        if (!missingFull.isEmpty() || !lintDecision(good).isEmpty() || lintDecision(weak).isEmpty())
            throw new AssertionError("lint dokumentasi tidak berperilaku sesuai harapan");
    }
}
