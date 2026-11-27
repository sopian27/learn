import java.util.*;
import java.util.stream.*;

// Fake DB that only counts round trips, so N+1 is visible without PostgreSQL.
public class DbAccessDemo {

    record Order(long id, long customerId) {}
    record Customer(long id, String name) {}
    record OrderRow(long orderId, String customerName) {}

    static int queries = 0;
    static final Map<Long, Customer> CUSTOMERS = LongStream.rangeClosed(1, 100).boxed()
            .collect(Collectors.toMap(id -> id, id -> new Customer(id, "Customer " + id)));

    static List<Order> findLatestOrders(int limit) {
        queries++;
        return LongStream.rangeClosed(1, limit).mapToObj(i -> new Order(i, (i % 100) + 1)).toList();
    }

    static Customer findCustomer(long id) {
        queries++;
        return CUSTOMERS.get(id);
    }

    static List<Customer> findCustomersByIds(Set<Long> ids) {
        queries++;
        return ids.stream().map(CUSTOMERS::get).toList();
    }

    // The PR version: looks clean, one query per row.
    static List<OrderRow> before(int limit) {
        return findLatestOrders(limit).stream()
                .map(o -> new OrderRow(o.id(), findCustomer(o.customerId()).name()))
                .toList();
    }

    // The fix: collect ids, fetch once, join in memory.
    static List<OrderRow> after(int limit) {
        var orders = findLatestOrders(limit);
        var ids = orders.stream().map(Order::customerId).collect(Collectors.toSet());
        var names = findCustomersByIds(ids).stream()
                .collect(Collectors.toMap(Customer::id, Customer::name));
        return orders.stream().map(o -> new OrderRow(o.id(), names.get(o.customerId()))).toList();
    }

    public static void main(String[] args) {
        for (int rows : new int[] {3, 50, 500}) {
            queries = 0;
            var slow = before(rows);
            int slowQueries = queries;

            queries = 0;
            var fast = after(rows);
            int fastQueries = queries;

            if (!slow.equals(fast) || slowQueries != rows + 1 || fastQueries != 2)
                throw new AssertionError("demo broken at rows=" + rows);

            // assumes 2 ms per round trip, purely illustrative
            System.out.printf("rows=%3d  before=%3d queries (~%4d ms)  after=%d queries (~%d ms)%n",
                    rows, slowQueries, slowQueries * 2, fastQueries, fastQueries * 2);
        }
    }
}
