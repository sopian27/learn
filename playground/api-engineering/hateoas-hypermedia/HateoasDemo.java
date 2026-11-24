import java.util.LinkedHashMap;
import java.util.Map;

// Lesson 1.1: server yang bilang aksi apa yang boleh (link), client tidak hardcode aturan state.
public class HateoasDemo {

    enum Status { PENDING, PAID, SHIPPED, CANCELLED }

    // Aturan server v2: order PAID sekarang TIDAK boleh cancel lagi (harus lewat refund).
    static boolean canCancelV2(Status s) { return s == Status.PENDING; }

    // Respons gaya HAL: _links dibangun dari state + aturan terkini.
    static Map<String, String> links(long id, Status s) {
        var l = new LinkedHashMap<String, String>();
        l.put("self", "/orders/" + id);
        l.put("items", "/orders/" + id + "/items");
        if (canCancelV2(s)) l.put("cancel", "/orders/" + id + "/cancel");
        if (s == Status.PAID) l.put("refund", "/orders/" + id + "/refund");
        return l;
    }

    // Client lama: hardcode aturan v1 (cancel boleh selama belum SHIPPED/CANCELLED).
    static boolean oldClientShowsCancel(Status s) { return s == Status.PENDING || s == Status.PAID; }

    // Client hypermedia: tampilkan tombol kalau linknya ada. Titik.
    static boolean linkClientShowsCancel(Map<String, String> links) { return links.containsKey("cancel"); }

    public static void main(String[] args) {
        for (Status s : Status.values()) {
            var l = links(42, s);
            boolean old = oldClientShowsCancel(s);
            boolean smart = linkClientShowsCancel(l);
            System.out.printf("%-9s links=%s%n", s, l.keySet());
            System.out.printf("          tombol Cancel -> client hardcode: %-5s | client baca link: %-5s %s%n",
                old, smart, old != smart ? "<-- BEDA, client hardcode salah" : "");
        }
    }
}
