import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.HexFormat;

// Demo: tanpa caching vs conditional request (ETag + If-None-Match) vs Cache-Control max-age.
public class HttpCachingDemo {

    record Response(int status, String etag, String cacheControl, String body) {
        int bytes() { return body == null ? 0 : body.getBytes(StandardCharsets.UTF_8).length; }
    }

    // "Database": satu order dengan 200 item, jadi payload cukup besar.
    static String orderJson(int version) {
        StringBuilder sb = new StringBuilder("{\"id\":42,\"version\":" + version + ",\"items\":[");
        for (int i = 0; i < 200; i++) sb.append(i == 0 ? "" : ",").append("{\"sku\":\"SKU-").append(i).append("\",\"qty\":1}");
        return sb.append("]}").toString();
    }

    // ETag = hash isi response. Strong validator: berubah kalau 1 byte berubah.
    static String etagOf(String body) throws Exception {
        byte[] h = MessageDigest.getInstance("SHA-256").digest(body.getBytes(StandardCharsets.UTF_8));
        return "\"" + HexFormat.of().formatHex(h, 0, 8) + "\"";
    }

    // Server: GET /orders/42, dengan dukungan If-None-Match.
    static Response get(int dbVersion, String ifNoneMatch) throws Exception {
        String body = orderJson(dbVersion);
        String etag = etagOf(body);
        String cc = "private, max-age=0, must-revalidate";   // boleh simpan, tapi tanya server tiap kali
        if (etag.equals(ifNoneMatch)) return new Response(304, etag, cc, null);   // tanpa body
        return new Response(200, etag, cc, body);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("== 1. Tanpa conditional request: 3x GET, body penuh tiap kali");
        int total = 0;
        for (int i = 0; i < 3; i++) total += get(1, null).bytes();
        System.out.println("total bytes = " + total);

        System.out.println("\n== 2. Dengan ETag: GET pertama 200, sisanya 304");
        Response first = get(1, null);
        System.out.println("GET #1 -> " + first.status() + ", " + first.bytes() + " bytes, ETag " + first.etag());
        total = first.bytes();
        for (int i = 2; i <= 3; i++) {
            Response r = get(1, first.etag());
            System.out.println("GET #" + i + " -> " + r.status() + ", " + r.bytes() + " bytes");
            total += r.bytes();
        }
        System.out.println("total bytes = " + total);

        System.out.println("\n== 3. Data berubah: ETag lama tidak cocok, server kirim 200 + ETag baru");
        Response changed = get(2, first.etag());
        System.out.println("GET -> " + changed.status() + ", " + changed.bytes() + " bytes, ETag " + changed.etag());

        System.out.println("\n== 4. Cache-Control max-age=60: dalam 60 detik klien tidak kontak server sama sekali");
        Instant stored = Instant.parse("2026-11-21T10:00:00Z");
        for (int sec : new int[]{10, 59, 61}) {
            boolean fresh = sec < 60;
            System.out.println("t+" + sec + "s -> " + (fresh ? "pakai cache lokal (0 request)" : "kadaluarsa, revalidate pakai If-None-Match"));
        }
        System.out.println("(disimpan " + stored + "; bahaya: data berubah di detik 30, klien tetap lihat versi lama sampai 60)");
    }
}
