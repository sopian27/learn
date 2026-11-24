import java.util.*;
import java.util.function.BiConsumer;

/**
 * Demo scaling WebSocket in-memory: 2 instance server, tiap instance cuma kenal client miliknya.
 * [1] tanpa bus: publish di instance B tidak sampai ke client di A.
 * [2] dengan bus pub/sub (tiruan Redis): semua instance dapat pesan, lalu meneruskan ke client lokal.
 * [3] sticky session: client yang sama selalu ke instance yang sama.
 * Run: java WsScalingDemo.java
 */
public class WsScalingDemo {

    // tiruan Redis pub/sub: fire-and-forget, tanpa simpan pesan (sama seperti Redis asli)
    static class Bus {
        final Map<String, List<BiConsumer<String, String>>> subs = new HashMap<>();
        void subscribe(String channel, BiConsumer<String, String> handler) {
            subs.computeIfAbsent(channel, c -> new ArrayList<>()).add(handler);
        }
        void publish(String channel, String msg) {
            subs.getOrDefault(channel, List.of()).forEach(h -> h.accept(channel, msg));
        }
    }

    static class Instance {
        final String name;
        final Map<String, Set<String>> localClients = new HashMap<>(); // channel -> client lokal
        Instance(String name) { this.name = name; }

        void connect(String client, String channel) {
            localClients.computeIfAbsent(channel, c -> new LinkedHashSet<>()).add(client);
        }
        void deliverLocal(String channel, String msg) {
            localClients.getOrDefault(channel, Set.of())
                .forEach(c -> System.out.println("  " + name + " -> " + c + ": " + msg));
        }
    }

    public static void main(String[] args) {
        Instance a = new Instance("A"), b = new Instance("B");
        a.connect("rani", "orders/ORD-1");   // rani nyangkut di A
        b.connect("septi", "orders/ORD-1");  // septi nyangkut di B

        System.out.println("[1] tanpa bus: service di B publish, hanya klien lokal B dapat");
        b.deliverLocal("orders/ORD-1", "SHIPPED");
        System.out.println("  (rani di A tidak dapat apa-apa, tanpa error)");

        System.out.println("[2] dengan bus: tiap instance subscribe ke bus, lalu forward ke klien lokal");
        Bus bus = new Bus();
        bus.subscribe("orders/ORD-1", (ch, m) -> a.deliverLocal(ch, m));
        bus.subscribe("orders/ORD-1", (ch, m) -> b.deliverLocal(ch, m));
        bus.publish("orders/ORD-1", "SHIPPED");

        System.out.println("[3] sticky session: hash(clientId) % jumlah instance, selalu sama");
        for (String c : List.of("rani", "septi", "rani", "septi")) {
            System.out.println("  " + c + " -> instance " + (Math.floorMod(c.hashCode(), 2) == 0 ? "A" : "B"));
        }
    }
}
