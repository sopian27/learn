import java.util.*;

/**
 * Demo SSE in-memory: format event teks, id per event, dan resume lewat Last-Event-ID.
 * Tanpa socket: yang ditunjukkan cuma bentuk stream dan logika catch-up.
 * Run: java SseResumeDemo.java
 */
public class SseResumeDemo {

    record Event(int id, String name, String data) {
        String wire() { // persis format text/event-stream
            return "id: " + id + "\nevent: " + name + "\ndata: " + data + "\n\n";
        }
    }

    static class Server {
        final List<Event> log = new ArrayList<>(); // simpan event, ini yang bikin resume mungkin

        void publish(String name, String data) {
            log.add(new Event(log.size() + 1, name, data));
        }

        // client baru/reconnect: kirim semua event setelah Last-Event-ID (kosong = 0)
        String connect(int lastEventId) {
            StringBuilder sb = new StringBuilder("HTTP/1.1 200 OK\nContent-Type: text/event-stream\n\n");
            log.stream().filter(e -> e.id() > lastEventId).forEach(e -> sb.append(e.wire()));
            return sb.toString();
        }
    }

    static String show(String s) { // header dan tiap event satu baris, newline ditampilkan escaped
        return String.join("\n", Arrays.stream(s.split("\n\n")).map(x -> x.replace("\n", "\\n") + "\\n\\n").toList());
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.publish("status", "ORD-1 PAID");
        server.publish("status", "ORD-1 PACKED");

        System.out.println("[1] client konek pertama, tanpa Last-Event-ID");
        System.out.println(show(server.connect(0)));

        System.out.println("[2] koneksi putus (WiFi pindah). Server tetap publish 2 event");
        server.publish("status", "ORD-1 SHIPPED");
        server.publish("status", "ORD-1 DELIVERED");

        System.out.println("[3] browser reconnect OTOMATIS, kirim header Last-Event-ID: 2");
        String resumed = server.connect(2);
        System.out.println(show(resumed));
        System.out.println("dikirim ulang: 2 dari 4 event (hanya id 3 dan 4)");
    }
}
