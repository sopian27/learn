

public class DeadlockDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread-A pegang lockA, mau ambil lockB...");
                sleepQuietly(500); // beri waktu Thread-B pegang lockB duluan
                synchronized (lockB) {
                    System.out.println("Thread-A pegang keduanya (tidak akan pernah tercetak)");
                }
            }
        }, "Thread-A");

        Thread threadB = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread-B pegang lockB, mau ambil lockA...");
                sleepQuietly(500);
                synchronized (lockA) {
                    System.out.println("Thread-B pegang keduanya (tidak akan pernah tercetak)");
                }
            }
        }, "Thread-B");

        threadA.start();
        threadB.start();
        // Program ini TIDAK AKAN PERNAH selesai normal -- ini sengaja,
        // supaya ada proses hidup untuk diambil thread dump-nya.
    }

    private static void sleepQuietly(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}