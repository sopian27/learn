import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

// Lesson 6.3: satu tempat memetakan exception ke status + Problem Details (tanpa Spring).
// Di Spring, tabel mapping ini = method @ExceptionHandler di dalam @ControllerAdvice.
public class GlobalExceptionHandlerDemo {

    static class OrderNotFoundException extends RuntimeException {
        OrderNotFoundException(String id) { super("Order " + id + " tidak ada"); }
    }
    static class OrderAlreadyShippedException extends RuntimeException {
        OrderAlreadyShippedException(String id) { super("Order " + id + " sudah dikirim"); }
    }
    // Sub-class: handler paling spesifik harus menang atas handler induknya
    static class OrderGoneException extends OrderNotFoundException {
        OrderGoneException(String id) { super(id); }
    }

    record Problem(int status, String title, String detail) {
        public String toString() { return status + " \"" + title + "\" detail=\"" + detail + "\""; }
    }

    // "@ControllerAdvice": mapping exception -> Problem, dikumpulkan di satu tempat
    static final Map<Class<? extends Throwable>, Function<Throwable, Problem>> HANDLERS = new LinkedHashMap<>();
    static {
        HANDLERS.put(OrderNotFoundException.class, e -> new Problem(404, "Order Not Found", e.getMessage()));
        HANDLERS.put(OrderGoneException.class, e -> new Problem(410, "Order Gone", e.getMessage()));
        HANDLERS.put(OrderAlreadyShippedException.class, e -> new Problem(409, "Order Already Shipped", e.getMessage()));
    }

    static Problem handle(Throwable t) {
        // cari handler paling spesifik (kelas terdekat di hirarki), seperti Spring
        Class<?> best = null;
        for (Class<?> c : HANDLERS.keySet()) {
            if (c.isInstance(t) && (best == null || best.isAssignableFrom(c))) best = c;
        }
        if (best != null) return HANDLERS.get(best).apply(t);
        // fallback @ExceptionHandler(Exception.class): jangan bocorkan t.getMessage() (ingat 6.1)
        System.out.println("  [log server] " + t);
        return new Problem(500, "Internal Server Error", "Terjadi kesalahan di server. Coba lagi nanti.");
    }

    // pura-pura controller: tiap case melempar exception beda
    static void controller(String case_) {
        switch (case_) {
            case "tidak-ada" -> throw new OrderNotFoundException("ORD-1");
            case "gone" -> throw new OrderGoneException("ORD-2");
            case "shipped" -> throw new OrderAlreadyShippedException("ORD-3");
            default -> throw new IllegalStateException("NPE di jdbc:postgresql://db-internal:5432/orders");
        }
    }

    public static void main(String[] args) {
        for (String c : new String[]{"tidak-ada", "gone", "shipped", "bug"}) {
            try { controller(c); }
            catch (Exception e) { System.out.println(c + " -> " + handle(e)); }
        }
    }
}
