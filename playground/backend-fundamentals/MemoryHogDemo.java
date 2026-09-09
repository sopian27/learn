import java.util.ArrayList;
import java.util.List;

public class MemoryHogDemo {
    public static void main(String[] args) {
        List<byte[]> hog = new ArrayList<>();
        System.out.println("Mulai alokasi... PID: cari lewat jcmd -l");
        while (true) {
            hog.add(new byte[10 * 1024 * 1024]); // 10MB per iterasi, tidak pernah dilepas
        }
    }
}