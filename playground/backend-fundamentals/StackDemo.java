public class StackDemo {
    static int depth = 0;

    static void recurse() {
        depth++;
        recurse(); // tidak ada base case, akan terus push frame baru
    }

    public static void main(String[] args) {
        try {
            recurse();
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError pada kedalaman: " + depth);
            // Coba jalankan dengan -Xss256k lalu -Xss8m, bandingkan angka depth-nya.
            // Ini membuktikan errornya soal jatah stack per-thread, bukan soal RAM total.
        }
    }
}