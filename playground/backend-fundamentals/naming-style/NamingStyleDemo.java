import java.time.Duration;
import java.util.List;

/**
 * Companion Lesson 1.5 (Naming & code style).
 * Run: java NamingStyleDemo.java   (Java 17+ single-file mode)
 */
public class NamingStyleDemo {

    // --- BURUK: nama gak bilang satuan ---
    static long brokenWaitMillis(int timeout) {
        return timeout;                       // pembuat nulis detik, pemakai ngira milidetik
    }

    // --- BENAR: satuan masuk tipe (atau minimal nama) ---
    static long waitMillis(Duration timeout) {
        return timeout.toMillis();
    }

    public static void main(String[] args) {
        // Caller A mikir "timeout = 30" itu detik, method baca sebagai milidetik.
        System.out.println("1a. broken: tunggu " + brokenWaitMillis(30) + " ms  (maksudnya 30 detik)");
        System.out.println("1b. fixed : tunggu " + waitMillis(Duration.ofSeconds(30)) + " ms");

        // Satu konsep (id user) ditulis 4 cara di codebase yang sama.
        List<String> codebase = List.of(
                "long userId = order.getUserId();",
                "repo.findByUser_id(user_id);",
                "String uid = token.subject();",
                "audit(userID, \"login\");",
                "log.info(\"userId={}\", userId);",
                "cache.evict(uId);");
        long hit = codebase.stream().filter(l -> l.contains("userId")).count();
        System.out.println("2a. grep \"userId\" nemu " + hit + " dari " + codebase.size() + " pemakaian id user");

        List<String> consistent = codebase.stream()
                .map(l -> "userId").toList();      // ceritanya semua sudah dirapikan
        long hitAfter = consistent.stream().filter(l -> l.contains("userId")).count();
        System.out.println("2b. setelah konsisten, grep nemu " + hitAfter + " dari " + consistent.size());

        // Nama bohong: 'getX' yang ternyata nulis.
        System.out.println("3.  lihat BrokenRepo vs Repo di bawah");
        BrokenRepo broken = new BrokenRepo();
        broken.getUser("dina");               // dipikir cuma baca
        System.out.println("3a. broken: setelah 'getUser', jumlah user = " + broken.count);

        Repo repo = new Repo();
        repo.findUser("dina");
        System.out.println("3b. fixed : setelah 'findUser', jumlah user = " + repo.count);
        repo.createUser("dina");
        System.out.println("3c. fixed : setelah 'createUser', jumlah user = " + repo.count);
    }

    // --- BURUK: nama bilang baca, isinya nulis ---
    static class BrokenRepo {
        int count = 0;
        String getUser(String name) { count++; return name; }
    }

    // --- BENAR: verb sesuai efek ---
    static class Repo {
        int count = 0;
        String findUser(String name) { return name; }
        String createUser(String name) { count++; return name; }
    }
}
