import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

// Demo: worker di balik 202 -- state machine job, at-least-once delivery, worker idempotent, webhook bertanda tangan.
public class AsyncProcessingDemo {

    enum State { PENDING, PROCESSING, DONE, FAILED }

    // transisi legal; DONE/FAILED final
    static final Map<State, Set<State>> ALLOWED = Map.of(
            State.PENDING, Set.of(State.PROCESSING),
            State.PROCESSING, Set.of(State.DONE, State.FAILED),
            State.DONE, Set.of(),
            State.FAILED, Set.of());

    static final Map<String, State> jobs = new HashMap<>();   // "tabel jobs"
    static int chargeCount = 0;                               // efek samping: berapa kali kartu ditagih

    static void move(String id, State to) {
        State from = jobs.get(id);
        if (!ALLOWED.get(from).contains(to)) throw new IllegalStateException(from + " -> " + to + " ilegal");
        jobs.put(id, to);
    }

    // Worker naif: percaya pesan cuma datang sekali.
    static void workerNaive(String id) { chargeCount++; }

    // Worker idempotent: cek state dulu, pesan duplikat jadi no-op.
    static void workerIdempotent(String id) {
        if (jobs.get(id) != State.PENDING) return;            // produksi: UPDATE ... WHERE state='PENDING' (atomik)
        move(id, State.PROCESSING);
        chargeCount++;
        move(id, State.DONE);
    }

    static String sign(String secret, String body) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
        return HexFormat.of().formatHex(mac.doFinal(body.getBytes()));
    }

    static boolean verify(String secret, String body, String signature) throws Exception {
        return java.security.MessageDigest.isEqual(sign(secret, body).getBytes(), signature.getBytes()); // constant-time
    }

    public static void main(String[] args) throws Exception {
        System.out.println("== 1. At-least-once: broker kirim pesan job-1 DUA kali (ack hilang)");
        jobs.put("job-1", State.PENDING);
        workerNaive("job-1"); workerNaive("job-1");
        System.out.println("worker naif      -> ditagih " + chargeCount + "x (harusnya 1)");

        chargeCount = 0;
        jobs.put("job-2", State.PENDING);
        workerIdempotent("job-2"); workerIdempotent("job-2");
        System.out.println("worker idempotent -> ditagih " + chargeCount + "x, state=" + jobs.get("job-2"));

        System.out.println("\n== 2. State machine menolak transisi ilegal");
        try { move("job-2", State.PROCESSING); }
        catch (IllegalStateException e) { System.out.println("ditolak: " + e.getMessage()); }

        System.out.println("\n== 3. Webhook bertanda tangan (HMAC-SHA256)");
        String secret = "whsec_demo", body = "{\"job\":\"job-2\",\"state\":\"DONE\"}";
        String sig = sign(secret, body);
        System.out.println("body asli  -> valid? " + verify(secret, body, sig));
        System.out.println("body diubah -> valid? " + verify(secret, body.replace("DONE", "FAILED"), sig));
    }
}
