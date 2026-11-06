import java.util.*;
import java.util.function.Supplier;

/**
 * Mini IoC container: memperlihatkan urutan lifecycle bean ala Spring.
 * Bukan Spring asli, cuma meniru urutannya supaya kelihatan.
 * Jalankan: java IocBeanLifecycleDemo.java
 */
public class IocBeanLifecycleDemo {

    // "Bean definition" = resep, BUKAN objeknya.
    record BeanDefinition(String name, Supplier<Object> factory, List<String> dependsOn) {}

    interface BeanPostProcessor {
        Object before(String name, Object bean);
        Object after(String name, Object bean);
    }

    interface InitializingBean { void afterPropertiesSet(); }
    interface DisposableBean { void destroy(); }

    static class MiniContext {
        final Map<String, BeanDefinition> definitions = new LinkedHashMap<>();
        final Map<String, Object> singletons = new LinkedHashMap<>();
        final List<BeanPostProcessor> processors = new ArrayList<>();

        void register(String name, Supplier<Object> factory, String... dependsOn) {
            definitions.put(name, new BeanDefinition(name, factory, List.of(dependsOn)));
        }

        void refresh() {
            System.out.println("== refresh(): definisi terdaftar = " + definitions.keySet() + ", objek = 0");
            definitions.keySet().forEach(this::getBean);
        }

        Object getBean(String name) {
            Object existing = singletons.get(name);
            if (existing != null) return existing;
            BeanDefinition def = definitions.get(name);
            for (String dep : def.dependsOn()) getBean(dep); // dependency dibuat dulu
            System.out.println("[" + name + "] 1. instantiation");
            Object bean = def.factory().get();
            System.out.println("[" + name + "] 2. dependency injection " + def.dependsOn());
            for (var p : processors) bean = p.before(name, bean);
            if (bean instanceof InitializingBean ib) {
                System.out.println("[" + name + "] 4. init (afterPropertiesSet)");
                ib.afterPropertiesSet();
            }
            for (var p : processors) bean = p.after(name, bean);
            singletons.put(name, bean);
            return bean;
        }

        void close() {
            System.out.println("== close(): destroy dengan urutan terbalik");
            var names = new ArrayList<>(singletons.keySet());
            Collections.reverse(names);
            for (String n : names) {
                if (singletons.get(n) instanceof DisposableBean db) {
                    System.out.println("[" + n + "] 6. destroy");
                    db.destroy();
                }
            }
        }
    }

    static class DataSource implements InitializingBean, DisposableBean {
        DataSource() { System.out.println("   DataSource constructor"); }
        public void afterPropertiesSet() { System.out.println("   DataSource: buka pool koneksi"); }
        public void destroy() { System.out.println("   DataSource: tutup pool koneksi"); }
    }

    static class OrderService implements InitializingBean, DisposableBean {
        DataSource ds;
        OrderService() { System.out.println("   OrderService constructor, ds masih null"); }
        public void afterPropertiesSet() { System.out.println("   OrderService siap, ds != null: " + (ds != null)); }
        public void destroy() { System.out.println("   OrderService: flush pekerjaan"); }
    }

    public static void main(String[] args) {
        var ctx = new MiniContext();
        // BeanPostProcessor: titik di mana Spring menyisipkan proxy (@Transactional, dst).
        ctx.processors.add(new BeanPostProcessor() {
            public Object before(String n, Object b) { System.out.println("[" + n + "] 3a. postProcessBeforeInitialization"); return b; }
            public Object after(String n, Object b) { System.out.println("[" + n + "] 5. postProcessAfterInitialization (di sini proxy dibuat)"); return b; }
        });
        ctx.register("orderService", () -> new OrderService(), "dataSource");
        ctx.register("dataSource", DataSource::new);
        // inject manual setelah dibuat, meniru DI
        ctx.processors.add(0, new BeanPostProcessor() {
            public Object before(String n, Object b) {
                if (b instanceof OrderService os) os.ds = (DataSource) ctx.singletons.get("dataSource");
                return b;
            }
            public Object after(String n, Object b) { return b; }
        });
        ctx.refresh();
        ctx.close();
    }
}
