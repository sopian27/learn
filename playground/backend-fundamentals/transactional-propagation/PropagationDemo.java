import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Simulasi mini propagation @Transactional (tanpa Spring/DB) buat lihat efeknya.
// Jalankan: java PropagationDemo.java
public class PropagationDemo {

    static final List<String> db = new ArrayList<>(); // baris yang sudah COMMIT
    static final Deque<Tx> stack = new ArrayDeque<>(); // transaksi aktif di paling atas

    static class Tx {
        final List<String> pending = new ArrayList<>();
        boolean rollbackOnly;
    }

    static void write(String row) {
        stack.peek().pending.add(row);
    }

    // REQUIRED: gabung kalau ada transaksi, kalau tidak buat baru.
    static void required(Runnable body) {
        Tx cur = stack.peek();
        if (cur == null) {
            begin(body);
            return;
        }
        try {
            body.run();
        } catch (RuntimeException e) {
            cur.rollbackOnly = true;
            throw e;
        }
    }

    // REQUIRES_NEW: transaksi baru yang commit/rollback sendiri; yang luar
    // "ditidurkan".
    static void requiresNew(Runnable body) {
        begin(body);
    }

    // NESTED: savepoint di transaksi yang sama; gagal = balik ke savepoint saja.
    static void nested(Runnable body) {
        Tx cur = stack.peek();
        int savepoint = cur.pending.size();
        try {
            body.run();
        } catch (RuntimeException e) {
            cur.pending.subList(savepoint, cur.pending.size()).clear();
            throw e;
        }
    }

    static void begin(Runnable body) {
        Tx tx = new Tx();
        stack.push(tx);
        try {
            body.run();
            if (tx.rollbackOnly)
                throw new IllegalStateException("UnexpectedRollbackException: ditandai rollback-only");
            db.addAll(tx.pending); // COMMIT
        } finally {
            stack.pop();
        } // exception = tidak commit = ROLLBACK
    }

    static void placeOrder(boolean auditNew) {
        required(() -> {
            write("order#1");
            Runnable audit = () -> write("audit: pembayaran order#1 dicoba");
            if (auditNew)
                requiresNew(audit);
            else
                required(audit);
            throw new RuntimeException("payment gagal");
        });
    }

    static void swallowInner(boolean useNested) {
        required(() -> {
            write("order#2");
            Runnable loyalty = () -> {
                write("poin loyalty");
                throw new RuntimeException("loyalty down");
            };
            try {
                if (useNested)
                    nested(loyalty);
                else
                    required(loyalty);
            } catch (RuntimeException e) {
                /* ditelan: "kan sudah ditangani" */ }
        });
    }

    static void run(String title, Runnable scenario) {
        db.clear();
        String outcome = "commit";
        try {
            scenario.run();
        } catch (RuntimeException e) {
            outcome = e.getMessage();
        }
        System.out.printf("%-42s -> %s | db=%s%n", title, outcome, db);
    }

    public static void main(String[] args) {
        run("1. audit REQUIRED, payment gagal", () -> placeOrder(false)); // audit ikut hilang
        run("2. audit REQUIRES_NEW, payment gagal", () -> placeOrder(true)); // audit selamat
        run("3. inner REQUIRED gagal, ditelan outer", () -> swallowInner(false)); // rollback-only
        run("4. inner NESTED gagal, ditelan outer", () -> swallowInner(true)); // order tetap commit
    }
}
