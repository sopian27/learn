import java.util.*;

/**
 * Demo STOMP in-memory: frame teks, SUBSCRIBE ke topic, broker fan-out, cleanup saat DISCONNECT.
 * Tanpa socket dan tanpa Spring: yang ditunjukkan cuma bentuk frame dan logika broker.
 * Run: java StompPubSubDemo.java
 */
public class StompPubSubDemo {

    record Frame(String command, Map<String, String> headers, String body) {
        static Frame parse(String raw) {
            String[] head = raw.split("\n\n", 2);
            String[] lines = head[0].split("\n");
            Map<String, String> h = new LinkedHashMap<>();
            for (int i = 1; i < lines.length; i++) {
                String[] kv = lines[i].split(":", 2);
                h.put(kv[0], kv[1]);
            }
            String body = head.length > 1 ? head[1].replace("\0", "") : "";
            return new Frame(lines[0], h, body);
        }

        String text() {
            StringBuilder sb = new StringBuilder(command).append('\n');
            headers.forEach((k, v) -> sb.append(k).append(':').append(v).append('\n'));
            return sb.append('\n').append(body).append('\0').toString();
        }
    }

    static class Broker {
        final Map<String, Set<String>> subs = new LinkedHashMap<>(); // destination -> sessionId

        void handle(String sessionId, String raw) {
            Frame f = Frame.parse(raw);
            switch (f.command()) {
                case "SUBSCRIBE" -> subs.computeIfAbsent(f.headers().get("destination"), d -> new LinkedHashSet<>()).add(sessionId);
                case "SEND" -> publish(f.headers().get("destination"), f.body());
                case "DISCONNECT" -> subs.values().forEach(s -> s.remove(sessionId)); // tanpa ini: memory leak
                default -> System.out.println("  (frame " + f.command() + " diabaikan)");
            }
        }

        void publish(String destination, String body) {
            Set<String> targets = subs.getOrDefault(destination, Set.of());
            System.out.println("broker: " + destination + " -> " + targets.size() + " subscriber " + targets);
            for (String s : targets) {
                Frame msg = new Frame("MESSAGE", new LinkedHashMap<>(Map.of("destination", destination, "subscription", s)), body);
                System.out.println("  kirim ke " + s + ": " + msg.text().replace("\n", "\\n").replace("\0", "^@"));
            }
        }
    }

    public static void main(String[] args) {
        Broker broker = new Broker();
        String sub1 = "SUBSCRIBE\ndestination:/topic/orders/ORD-1\n\n\0";

        broker.handle("rani", sub1);
        broker.handle("septi", sub1);
        broker.handle("wahyu", "SUBSCRIBE\ndestination:/topic/orders/ORD-2\n\n\0");

        System.out.println("\n[1] status ORD-1 berubah: cuma yang subscribe ORD-1 dapat pesan");
        broker.handle("server", "SEND\ndestination:/topic/orders/ORD-1\n\nPAID->SHIPPED\0");

        System.out.println("\n[2] rani DISCONNECT, lalu publish lagi");
        broker.handle("rani", "DISCONNECT\n\n\0");
        broker.handle("server", "SEND\ndestination:/topic/orders/ORD-1\n\nSHIPPED->DELIVERED\0");

        System.out.println("\n[3] topic tanpa subscriber (ORD-9): pesan hilang, tidak ada error");
        broker.handle("server", "SEND\ndestination:/topic/orders/ORD-9\n\nPAID\0");
    }
}
