import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
// Lesson 7.2: cursor pagination = WHERE id < :cursor ORDER BY id DESC LIMIT size. Tidak kena page drift.
public class CursorPaginationDemo {
    static final List<Integer> orders = new ArrayList<>(); // id order, terbaru dulu

    static void insert(int id) { orders.add(id); orders.sort(Comparator.reverseOrder()); }

    // cursor opaque: klien tidak boleh tahu isinya
    static String encode(int id) { return Base64.getUrlEncoder().encodeToString(("id:" + id).getBytes()); }
    static int decode(String c) { return Integer.parseInt(new String(Base64.getUrlDecoder().decode(c)).substring(3)); }

    record Result(List<Integer> items, String nextCursor) {}

    // ambil size+1 baris: kalau dapat lebih, berarti masih ada halaman berikutnya (tanpa COUNT)
    static Result page(String cursor, int size) {
        int after = cursor == null ? Integer.MAX_VALUE : decode(cursor);
        List<Integer> rows = orders.stream().filter(id -> id < after).limit(size + 1).toList();
        boolean hasMore = rows.size() > size;
        List<Integer> items = hasMore ? rows.subList(0, size) : rows;
        return new Result(items, hasMore ? encode(items.get(items.size() - 1)) : null);
    }

    public static void main(String[] args) {
        for (int id = 1; id <= 10; id++) insert(id);
        Result p0 = page(null, 3);
        System.out.println("page 0: " + p0.items() + " next=" + p0.nextCursor());
        insert(11); // order baru masuk di antara dua request
        Result p1 = page(p0.nextCursor(), 3);
        System.out.println("page 1: " + p1.items());

        List<Integer> seen = new ArrayList<>(p0.items());
        seen.addAll(p1.items());
        boolean ok = seen.stream().distinct().count() == seen.size() && p1.items().equals(List.of(7, 6, 5));
        System.out.println(ok ? "OK: tidak ada duplikat meski data berubah" : "GAGAL");
    }
}
