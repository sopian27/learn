import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
// Lesson 7.1: offset pagination = LIMIT size OFFSET page*size. Simpel, tapi page drift saat data berubah.
public class OffsetPaginationDemo {
    static final int MAX_SIZE = 5; // batas atas size, jangan percaya klien
    static final List<Integer> orders = new ArrayList<>(); // id order, terbaru dulu (ORDER BY id DESC)

    static void insert(int id) { orders.add(id); orders.sort(Comparator.reverseOrder()); }

    // setara Pageable.of(page, size): OFFSET page*size LIMIT size
    static List<Integer> page(int page, int size) {
        size = Math.min(Math.max(size, 1), MAX_SIZE);
        int from = Math.min(page * size, orders.size());
        return new ArrayList<>(orders.subList(from, Math.min(from + size, orders.size())));
    }

    public static void main(String[] args) {
        for (int id = 1; id <= 10; id++) insert(id);
        List<Integer> p0 = page(0, 3);
        System.out.println("page 0: " + p0);        // [10, 9, 8]
        insert(11);                                  // order baru masuk di antara dua request
        List<Integer> p1 = page(1, 3);
        System.out.println("page 1: " + p1);        // [8, 7, 6] -> 8 muncul lagi (duplikat)
        System.out.println("size=1000 dipotong ke: " + page(0, 1000).size());

        List<Integer> seen = new ArrayList<>(p0);
        seen.addAll(p1);
        boolean ok = seen.stream().distinct().count() < seen.size() && page(0, 1000).size() == MAX_SIZE;
        System.out.println(ok ? "OK: duplikat terjadi (page drift), size dibatasi" : "GAGAL");
    }
}
