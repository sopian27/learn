import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Demo Lesson 2.1: race condition, visibility, dan synchronized yang tidak cukup.
public class ThreadSafetyDemo {

    static int plainStock = 800_000;
    static volatile int vplainStock2 = 800_000;
    static final AtomicInteger atomicStock = new AtomicInteger(800_000);
    static boolean running = true; // 2a: tanpa volatile
    static volatile boolean runningFixed = true; // 2b

    public static void main(String[] args) throws Exception {
        // 1a: race condition. 8 thread masing-masing kurangi stok 100.000 kali ->
        // harusnya 0.
        runAll(8, () -> {
            for (int i = 0; i < 100_000; i++)
                plainStock--;
        });
        System.out.println("1a stok biasa (harus 0): " + plainStock);

        // 1b: fix pakai atomic operation.
        runAll(8, () -> {
            for (int i = 0; i < 100_000; i++)
                atomicStock.decrementAndGet();
        });
        System.out.println("1b stok atomic (harus 0): " + atomicStock.get());

        // 1c: fix pakai volatile.
        runAll(8, () -> {
            for (int i = 0; i < 100_000; i++)
                vplainStock2--;
        });
        System.out.println("1c stok volatile (harus 0): " + vplainStock2);

        // 2a: visibility. Worker bisa tidak pernah lihat running=false
        // (nondeterministik).
        Thread w1 = new Thread(() -> {
            while (running) {
            }
        });
        w1.setDaemon(true);
        w1.start();
        Thread.sleep(200);
        running = false;
        w1.join(1000);
        System.out.println("2a worker tanpa volatile berhenti? " + !w1.isAlive() + " (bisa false, tergantung JIT)");

        // 2b: volatile menjamin perubahan terlihat.
        Thread w2 = new Thread(() -> {
            while (runningFixed) {
            }
        });
        w2.setDaemon(true);
        w2.start();
        Thread.sleep(200);
        runningFixed = false;
        w2.join(1000);
        System.out.println("2b worker dengan volatile berhenti? " + !w2.isAlive());

        // 3a: tiap method synchronized, tapi check-then-act di luar lock -> tetap race.
        SafeLookingWallet wallet = new SafeLookingWallet(10);
        runAll(20, () -> {
            if (wallet.balance() >= 10) {
                sleepQuietly();
                wallet.withdraw(10);
            }
        });
        System.out.println("3a saldo (harus >= 0, hasil minus = race): " + wallet.balance());

        SafeLookingWallet wallet2 = new SafeLookingWallet(10);
        runAll(20, () -> {
            synchronized (wallet2) {
                if (wallet2.balance() >= 10) {
                    sleepQuietly();
                    wallet2.withdraw(10);
                }
            }
        });
        System.out.println(
                "3a1 saldo (harus >= 0, semua request antre, throughput jatuh ke satu thread): "
                        + wallet2.balance());

        // 3b: check dan act dalam satu lock yang sama.
        SafeLookingWallet fixed = new SafeLookingWallet(10);
        runAll(20, () -> fixed.withdrawIfEnough(10));
        System.out.println("3b saldo (harus >= 0): " + fixed.balance());

        // 3c: Map thread-safe pun, compound action butuh operasi atomik.
        Map<String, Integer> hits = new ConcurrentHashMap<>();
        runAll(1000, () -> hits.merge("home", 1, Integer::sum));
        System.out.println("3c hits (harus 1000): " + hits.get("home"));
    }

    static class SafeLookingWallet {
        private int balance;

        SafeLookingWallet(int balance) {
            this.balance = balance;
        }

        synchronized int balance() {
            return balance;
        }

        synchronized void withdraw(int amount) {
            balance -= amount;
        }

        synchronized void withdrawIfEnough(int amount) {
            if (balance >= amount)
                balance -= amount;
        }
    }

    // Memperlebar jendela antara check dan act supaya race terlihat konsisten.
    static void sleepQuietly() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void runAll(int n, Runnable task) throws InterruptedException {
        Thread[] ts = new Thread[n];
        for (int i = 0; i < n; i++) {
            ts[i] = new Thread(task);
            ts[i].start();
        }
        for (Thread t : ts)
            t.join();
    }
}
