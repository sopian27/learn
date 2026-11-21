import java.util.*;

/** Simulasi: test "duplikat order PENDING harus ditolak" di dua jenis DB. Plain Java, bukan Docker beneran. */
public class TestcontainersRealDbDemo {

    record Order(String customer, String sku, String status) {}

    /** Fake DB. partialUnique = true meniru Postgres + Flyway: UNIQUE (customer, sku) WHERE status='PENDING'. */
    static class Db {
        final boolean partialUnique;
        final List<Order> rows = new ArrayList<>();
        Db(boolean partialUnique) { this.partialUnique = partialUnique; }

        void insert(Order o) {
            boolean dup = rows.stream().anyMatch(r -> r.customer().equals(o.customer())
                    && r.sku().equals(o.sku()) && r.status().equals("PENDING") && o.status().equals("PENDING"));
            if (partialUnique && dup) throw new IllegalStateException("duplicate key violates unique constraint uq_pending_order");
            rows.add(o);
        }
    }

    /** Test: insert 2 order PENDING yang sama, harapannya ditolak. */
    static void run(String label, Db db) {
        System.out.println("== " + label);
        db.insert(new Order("rani", "A1", "PENDING"));
        try {
            db.insert(new Order("rani", "A1", "PENDING"));
            System.out.println("  duplikat PENDING ditolak -> FAIL (duplikat lolos, test bohong kalau dianggap aman)");
        } catch (IllegalStateException e) {
            System.out.println("  duplikat PENDING ditolak -> PASS (" + e.getMessage() + ")");
        }
    }

    public static void main(String[] args) {
        run("H2 (schema dari entity, tanpa partial index Flyway)", new Db(false));
        run("Postgres Testcontainers (schema dari Flyway, sama seperti production)", new Db(true));
    }
}
