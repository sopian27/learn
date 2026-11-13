import java.util.HashSet;
import java.util.Set;

// Run: java MockLiesDemo.java
// Mock/fake yang "baik hati" bikin test hijau. Database asli punya aturan (unique constraint) yang nolak.
// Di sini aturan DB asli disimulasikan di RealisticRepo. Di proyek nyata, itu tugas Testcontainers (Postgres asli).
public class MockLiesDemo {

    static class DuplicateKeyException extends RuntimeException {}

    interface UserRepo { void save(String email); }

    // Service punya bug: nggak nangani email duplikat (beda huruf besar/kecil).
    record Register(UserRepo repo) {
        String register(String email) {
            repo.save(email);
            return "OK";
        }
    }

    // Mock "baik hati": save selalu sukses.
    static class FriendlyMock implements UserRepo {
        public void save(String email) { }
    }

    // Meniru Postgres: unique index pada lower(email) -> duplikat dilempar sebagai exception.
    static class RealisticRepo implements UserRepo {
        final Set<String> seen = new HashSet<>();
        public void save(String email) {
            if (!seen.add(email.toLowerCase())) throw new DuplicateKeyException();
        }
    }

    static String run(UserRepo repo) {
        Register s = new Register(repo);
        try {
            s.register("Budi@x.com");
            s.register("budi@x.com"); // orang yang sama, huruf beda
            return "HIJAU";
        } catch (DuplicateKeyException e) {
            return "MERAH (500: DuplicateKeyException belum ditangani)";
        }
    }

    public static void main(String[] args) {
        System.out.println("test dengan mock baik hati : " + run(new FriendlyMock()));
        System.out.println("test dengan constraint asli: " + run(new RealisticRepo()));
    }
}
