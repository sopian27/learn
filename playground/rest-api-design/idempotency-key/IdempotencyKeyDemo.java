import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Demo: POST /orders tanpa vs dengan header Idempotency-Key (retry setelah network timeout).
public class IdempotencyKeyDemo {

    record Resp(int status, String body) {}
    // state: IN_PROGRESS (belum selesai) atau DONE (hasil tersimpan, siap di-replay)
    record Entry(String fingerprint, String state, Resp resp) {}

    static final List<String> orders = new ArrayList<>();                    // "tabel orders"
    static final Map<String, Entry> keyStore = new ConcurrentHashMap<>();   // "tabel idempotency_key"

    static Resp createOrderRaw(String body) {
        String id = "ORD-" + (orders.size() + 1);
        orders.add(id + ":" + body);
        return new Resp(201, "{\"id\":\"" + id + "\"}");
    }

    // Tanpa key: server tidak bisa bedakan retry vs order baru.
    static Resp postWithoutKey(String body) { return createOrderRaw(body); }

    // Dengan key: claim atomik, replay kalau sudah selesai, tolak kalau body beda atau masih jalan.
    static Resp postWithKey(String key, String body, boolean slow) {
        String fp = Integer.toHexString(body.hashCode());                   // produksi: SHA-256 dari body kanonik
        Entry prev = keyStore.putIfAbsent(key, new Entry(fp, "IN_PROGRESS", null));
        if (prev != null) {
            if (!prev.fingerprint().equals(fp)) return new Resp(422, "key dipakai untuk request lain");
            if (prev.state().equals("IN_PROGRESS")) return new Resp(409, "request pertama masih diproses");
            return prev.resp();                                              // replay: status + body persis sama
        }
        if (slow) return null;                                               // simulasi: respons hilang / klien timeout, entry tertinggal IN_PROGRESS
        Resp r = createOrderRaw(body);
        keyStore.put(key, new Entry(fp, "DONE", r));
        return r;
    }

    public static void main(String[] args) {
        String body = "{\"sku\":\"A1\",\"qty\":2}";

        System.out.println("== 1. Tanpa key: klien timeout lalu retry");
        postWithoutKey(body);                                                // respons hilang di jaringan
        postWithoutKey(body);                                                // klien retry
        System.out.println("jumlah order = " + orders.size() + "  (harusnya 1, order dobel!)");

        orders.clear();
        System.out.println("\n== 2. Dengan key: request sukses, respons hilang, klien retry");
        postWithKey("k-123", body, false);                                   // respons hilang di jaringan
        Resp retry = postWithKey("k-123", body, false);
        System.out.println("retry -> " + retry + "  jumlah order = " + orders.size());

        System.out.println("\n== 3. Key sama, body beda");
        System.out.println(postWithKey("k-123", "{\"sku\":\"B2\",\"qty\":9}", false));

        System.out.println("\n== 4. Retry saat request pertama masih jalan");
        postWithKey("k-456", body, true);                                    // pertama: nyangkut
        System.out.println(postWithKey("k-456", body, false));
    }
}
