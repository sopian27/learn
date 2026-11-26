import java.util.List;
import java.util.Map;
import java.util.Optional;

// Code Review, Lesson 3.1: three holes that fit in one small diff. SQL is simulated, no database needed.
public class SecurityDiffDemo {

    record Order(long id, String ownerId, String address) {}

    // Default record toString() prints every component, including the token.
    record LeakyLogin(String username, String token) {}

    record SafeLogin(String username, String token) {
        @Override
        public String toString() {
            return "SafeLogin[username=" + username + ", token=***]";
        }
    }

    record Query(String sql, List<Object> params) {}

    static final Map<Long, Order> ORDERS = Map.of(
            1L, new Order(1, "rakha", "Jl. Melati 1"),
            2L, new Order(2, "yuni", "Jl. Kenanga 2"));

    // 1. Injection: input becomes part of the SQL text.
    static Query searchBefore(String ownerId, String keyword) {
        return new Query("SELECT * FROM orders WHERE owner_id = '" + ownerId
                + "' AND note LIKE '%" + keyword + "%'", List.of());
    }

    // Fix: SQL text is constant, input travels as a parameter.
    static Query searchAfter(String ownerId, String keyword) {
        return new Query("SELECT * FROM orders WHERE owner_id = ? AND note LIKE ?",
                List.of(ownerId, "%" + keyword + "%"));
    }

    // 2. Access control: logged in is enough to read anyone's order.
    static Optional<Order> findBefore(long id, String currentUser) {
        return Optional.ofNullable(ORDERS.get(id));
    }

    // Fix: ownership is part of the lookup.
    static Optional<Order> findAfter(long id, String currentUser) {
        return findBefore(id, currentUser).filter(o -> o.ownerId().equals(currentUser));
    }

    static void check(boolean ok, String what) {
        if (!ok) throw new AssertionError(what);
    }

    public static void main(String[] args) {
        String evil = "x%' OR '1'='1' --";
        Query bad = searchBefore("rakha", evil);
        Query good = searchAfter("rakha", evil);
        System.out.println("injection before : " + bad.sql());
        System.out.println("injection after  : " + good.sql() + "  params=" + good.params());
        check(bad.sql().contains("OR '1'='1'"), "input should have changed the SQL");
        check(!good.sql().contains("OR '1'"), "SQL text must not change with input");

        System.out.println("rakha reads #2 before : " + findBefore(2, "rakha"));
        System.out.println("rakha reads #2 after  : " + findAfter(2, "rakha"));
        check(findBefore(2, "rakha").isPresent(), "bug should reproduce");
        check(findAfter(2, "rakha").isEmpty(), "other user's order must be hidden");
        check(findAfter(1, "rakha").isPresent(), "own order must still work");

        var leaky = new LeakyLogin("rakha", "tok-SECRET-123");
        var safe = new SafeLogin("rakha", "tok-SECRET-123");
        System.out.println("log before : login " + leaky);
        System.out.println("log after  : login " + safe);
        check(leaky.toString().contains("SECRET"), "leak should reproduce");
        check(!safe.toString().contains("SECRET"), "token must be masked");

        System.out.println("ALL CHECKS PASSED");
    }
}
