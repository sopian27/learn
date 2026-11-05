import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo Lesson 3.1: heap vs stack vs metaspace, dan kapan object eligible GC.
 * Run: java JvmMemoryModelDemo.java
 * Demo, bukan production code. System.gc() cuma hint, tapi di HotSpot default biasanya jalan.
 */
public class JvmMemoryModelDemo {

    record Order(long id, byte[] payload) {}

    // GC root: static field hidup selama class-nya ke-load. Ini sumber leak klasik.
    static final List<Order> CACHE = new ArrayList<>();

    public static void main(String[] args) {
        stackVsHeap();
        eligibleWhenUnreachable();
        eligibleAfterMethodReturns();
        staticRootKeepsAlive();
        metaspaceInfo();
        stackOverflow();
    }

    static void stackVsHeap() {
        int qty = 3;                                  // primitif: nilai ada di stack frame
        Order order = new Order(1, new byte[1024]);   // 'order' (referensi) di stack, object-nya di heap
        System.out.println("[1] qty di stack=" + qty + ", order.id di heap=" + order.id());
    }

    static void eligibleWhenUnreachable() {
        Order order = new Order(2, new byte[1024 * 1024]);
        WeakReference<Order> probe = new WeakReference<>(order); // weak: tidak menahan GC
        System.gc();
        System.out.println("[2a] masih ada strong ref -> alive? " + (probe.get() != null));
        order = null;                                 // strong ref terakhir lepas -> eligible
        System.gc();
        System.out.println("[2b] strong ref dilepas  -> alive? " + (probe.get() != null));
    }

    static void eligibleAfterMethodReturns() {
        WeakReference<Order> probe = createAndForget();
        System.gc();
        System.out.println("[3] method sudah return  -> alive? " + (probe.get() != null));
    }

    static WeakReference<Order> createAndForget() {
        Order local = new Order(3, new byte[1024 * 1024]); // stack frame hilang saat return
        return new WeakReference<>(local);
    }

    static void staticRootKeepsAlive() {
        Order order = new Order(4, new byte[1024 * 1024]);
        WeakReference<Order> probe = new WeakReference<>(order);
        CACHE.add(order);                             // static root menunjuk ke object
        order = null;
        System.gc();
        System.out.println("[4] variabel lokal null, tapi ada di static CACHE -> alive? " + (probe.get() != null));
    }

    static void metaspaceInfo() {
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (pool.getName().equals("Metaspace")) {
                System.out.println("[5] Metaspace terpakai (metadata class, bukan object): "
                        + pool.getUsage().getUsed() / 1024 + " KB");
            }
        }
    }

    static int depth = 0;

    static void recurse() { depth++; recurse(); }

    static void stackOverflow() {
        try {
            recurse();
        } catch (StackOverflowError e) {
            System.out.println("[6] StackOverflowError di kedalaman ~" + depth + " frame (stack habis, heap aman)");
        }
    }
}
