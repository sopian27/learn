### Context

Ini kode dari sebuah tim kecil yang butuh melacak order pelanggan. Kodenya dipakai di production selama beberapa bulan tanpa insiden besar — tapi setiap kali ada bug aneh (data order berubah sendiri di tempat lain, exception yang hilang tanpa jejak), tidak ada yang tahu kenapa. Anda diminta melakukan code review menyeluruh sebelum tim ini menambah fitur baru di atasnya.

### Kode yang harus diperbaiki

```java
package com.example.Tracking;

import java.util.*;

public class order_tracker {

    public List orders = new ArrayList();
    public static final int maxOrder = 100;
    private HashMap<String, Order> orderIndex = new HashMap<String, Order>();

    public order_tracker(List initialOrders) {
        this.orders = initialOrders;
    }

    public void AddOrder(Order order) {
        try {
            orders.add(order);
            orderIndex.put(order.id, order);
        } catch (Exception e) {
        }
    }

    public List getOrders() {
        return orders;
    }

    public boolean getCancelled(String orderId) {
        Order o = orderIndex.get(orderId);
        return o.status.equals("CANCELLED");
    }
}

class Order {
    public String id;
    public String status;

    public Order(String id, String status) {
        this.id = id;
        this.status = status;
    }
}
```

### Task

1. **Identifikasi setiap pelanggaran** di kode di atas — baris demi baris. Untuk tiap pelanggaran, sebutkan: lesson mana yang membahasnya (1.1–1.5), dan **kenapa** itu masalah nyata (bukan cuma "melanggar aturan") — kalau bisa, bayangkan skenario konkret di mana pelanggaran itu menyebabkan bug atau insiden.

| Baris | Kode                                       | Pelanggaran                                                          | Lesson        | Dampak nyata                                                                                                                                                                                                               |
| ----- | ------------------------------------------ | -------------------------------------------------------------------- | ------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1     | `package com.example.Tracking;`            | Package `Tracking` memakai uppercase                                 | **1.1**       | Java convention memakai lowercase package. Bukan bug runtime, tetapi membuat struktur package tidak konsisten dan menyulitkan navigasi/tooling.                                                                            |
| 3     | `import java.util.*;`                      | Wildcard import                                                      | **1.1**       | Tidak langsung menyebabkan bug, tetapi dependency menjadi kurang eksplisit. Saat class bertambah, lebih sulit mengetahui tipe apa yang sebenarnya dipakai.                                                                 |
| 5     | `public class order_tracker`               | Nama class tidak PascalCase                                          | **1.1**       | `OrderTracker` jauh lebih mudah dibaca dan konsisten dengan Java convention. Nama seperti `order_tracker` membuat API terlihat seperti naming style bahasa lain.                                                           |
| 6     | `public List orders`                       | Raw type + public mutable field                                      | **1.2 + 1.3** | Caller bebas memasukkan object apa pun dan bebas menghapus/mengubah isi list. Bisa merusak invariant internal `OrderTracker`.                                                                                              |
| 7     | `public static final int maxOrder = 100`   | Constant tidak `UPPER_SNAKE_CASE`; kemungkinan juga tidak dipakai    | **1.1**       | `MAX_ORDER` adalah convention. Lebih buruk lagi, field ini memberi kesan ada limit 100 padahal kode tidak pernah menegakkannya.                                                                                            |
| 8     | `private HashMap<String, Order>`           | Concrete implementation diekspos sebagai field type                  | **1.3**       | Lebih baik deklarasikan `Map<String, Order>`. Class seharusnya bergantung pada abstraction, bukan implementasi.                                                                                                            |
| 10    | `public order_tracker(List initialOrders)` | Constructor dan parameter menggunakan naming yang salah + raw `List` | **1.1 + 1.2** | Caller bisa memberikan `List` berisi tipe apa pun. Error baru muncul ketika object digunakan, bukan ketika compile-time.                                                                                                   |
| 11    | `this.orders = initialOrders`              | Menyimpan reference milik caller                                     | **1.3**       | Ini salah satu sumber bug "data berubah sendiri". Caller masih memegang list yang sama dan bisa mengubahnya setelah constructor selesai.                                                                                   |
| 14    | `public void AddOrder`                     | Method harus `addOrder`                                              | **1.1**       | `AddOrder()` melanggar lowerCamelCase.                                                                                                                                                                                     |
| 15    | `try`                                      | Exception handling tidak diperlukan di sini                          | **1.5**       | Membungkus operasi normal dengan `try/catch` tanpa recovery hanya menyamarkan kegagalan.                                                                                                                                   |
| 16    | `orders.add(order)`                        | Tidak ada validasi `order`/ID/invariant                              | **1.4/1.5**   | `null` bisa masuk. Duplikasi ID juga mungkin. Akibatnya index dan list dapat menjadi tidak konsisten.                                                                                                                      |
| 17    | `orderIndex.put(order.id, order)`          | Akses field langsung + kemungkinan `NullPointerException`            | **1.3 + 1.5** | `order == null` menyebabkan NPE. `order.id` juga dapat berubah setelah dimasukkan sehingga key index tidak lagi merepresentasikan object secara benar.                                                                     |
| 18–19 | `catch (Exception e) {}`                   | **Exception swallowed**                                              | **1.5**       | Ini masalah paling serius. Database/network tidak relevan di sini—bahkan `NullPointerException` atau runtime bug pun bisa hilang tanpa trace. Production bisa mengira order berhasil ditambahkan padahal sebenarnya gagal. |
| 21    | `public List getOrders()`                  | Raw type + mutable internal state diekspos                           | **1.2 + 1.3** | Caller dapat melakukan `tracker.getOrders().clear()`, sehingga state internal berubah tanpa melalui `OrderTracker`.                                                                                                        |
| 22    | `return orders`                            | Representation exposure                                              | **1.3**       | Contoh konkret: service A mengambil list, service B kemudian `clear()` list tersebut. Service A melihat data order tiba-tiba hilang.                                                                                       |
| 25    | `getCancelled`                             | Boolean getter/predicate naming tidak idiomatis                      | **1.1**       | `getCancelled()` terdengar seperti mengambil property. Untuk predicate, `isCancelled()` lebih natural.                                                                                                                     |
| 26    | `Order o`                                  | Nama variabel terlalu pendek                                         | **1.1**       | `order` lebih jelas. Dampaknya kecil di sini, tetapi menjadi masalah ketika method kompleks.                                                                                                                               |
| 27    | `o.status.equals("CANCELLED")`             | String literal sebagai state + null-sensitive comparison             | **1.4/1.5**   | Jika `status == null`, terjadi NPE. `"CANCELLED".equals(o.status)` lebih aman, tetapi enum lebih baik.                                                                                                                     |
| 27    | `orderIndex.get(orderId)`                  | Tidak menangani order tidak ditemukan                                | **1.5**       | `get()` bisa menghasilkan `null`, kemudian `o.status` menyebabkan NPE. Caller mendapat exception yang tidak menjelaskan bahwa order memang tidak ditemukan.                                                                |
| 31    | `public class Order`                       | Class/package visibility mungkin terlalu luas                        | **1.3**       | Kalau memang domain object harus exposed, public masuk akal; tetapi state di dalamnya tetap harus encapsulated.                                                                                                            |
| 32    | `public String id`                         | Public mutable field                                                 | **1.3**       | Order yang sudah masuk `HashMap` bisa berubah ID: `order.id = "NEW-ID"`. Map masih menyimpan key lama. Sekarang index secara logis rusak.                                                                                  |
| 33    | `public String status`                     | Public mutable field                                                 | **1.3**       | Code di mana pun dapat melakukan `order.status = "CANCELLED"` atau `"whatever"`, bypassing business rules.                                                                                                                 |
| 35    | constructor                                | Tidak melakukan validation                                           | **1.4**       | Bisa membuat `Order(null, null)` sehingga object invalid sudah sejak awal.                                                                                                                                                 |
| 36    | `this.id = id`                             | State mutable                                                        | **1.3**       | ID biasanya identity sebuah order dan seharusnya tidak berubah setelah object dibuat.                                                                                                                                      |
| 37    | `this.status = status`                     | State mutable + unrestricted String                                  | **1.3**       | Tidak ada jaminan status valid. `"CANCEL"`, `"cancelled"`, `"CANCELLED "` semuanya bisa masuk.                                                                                                                             |


2. **Tulis ulang** `order_tracker` dan `Order` supaya idiomatis, sesuai `standards/JAVA_STANDARDS.md` dan `standards/ERROR_HANDLING_STANDARDS.md`. Boleh mengubah struktur (mis. menambah method, mengubah tipe field) selama kontrak fungsionalnya (menambah order, mengecek status cancelled) tetap jalan.

    Kalau saya harus menentukan prioritas production, saya tidak akan menempatkan naming sebagai prioritas pertama.

    Urutannya:

    🔴 P0 — swallowed exception
    catch (Exception e) {
    }

    Ini membuat sistem gagal secara diam-diam.

    Misalnya:

    AddOrder(order)
        ↓
    orders.add(order)          SUCCESS
        ↓
    orderIndex.put(...)        ERROR
        ↓
    catch(Exception)           DIAM
        ↓
    caller mengira SUCCESS

    Akibatnya sistem bisa berada dalam keadaan:

    orders      = [Order A]
    orderIndex  = {}

    Kemudian:

    getCancelled("A")

    tidak menemukan order.

    Yang lebih buruk: engineer melihat production behavior dan tidak punya stack trace untuk mengetahui kenapa index tidak pernah terisi.

    🔴 P0 — internal collection exposed

    Ini sangat mungkin menjelaskan bug:

    "data order berubah sendiri di tempat lain"

    Contoh:

    List<Order> orders = tracker.getOrders();

    orders.clear();

    tracker sekarang juga kehilangan semua order.

    Tidak ada method OrderTracker.removeAll() yang dipanggil. State berubah melalui reference yang bocor.

    Constructor punya masalah yang sama:

    List<Order> externalList = new ArrayList<>();
    externalList.add(orderA);

    OrderTracker tracker = new OrderTracker(externalList);

    externalList.clear();

    tracker ikut kosong.

    🔴 P0 — mutable ID merusak Map invariant

    Ini lebih subtle.

    Misalnya:

    Order order = new Order("ORD-001", "NEW");

    tracker.addOrder(order);

    order.setId("ORD-999"); // pada versi mutable

    Map secara internal masih:

    "ORD-001" -> order

    tetapi:

    order.getId()

    menghasilkan:

    ORD-999

    Jadi object mengatakan dirinya ORD-999, sementara index mengatakan object tersebut berada di ORD-001.

    Inilah alasan identity field seperti id sangat cocok dibuat immutable.


3. **Justifikasi tiap perubahan** — jangan cuma tempel kode hasil perbaikan. Untuk tiap keputusan desain (mis. kenapa pakai tipe generic tertentu, kenapa `Order` dibuat immutable atau tidak, kenapa exception ditangani/dilempar dengan cara tertentu), tulis 1-2 kalimat alasannya, terutama kalau lebih dari satu pendekatan valid dan Anda memilih salah satunya.

    Hint mengatakan:

    "Order tidak override equals/hashCode padahal disimpan sebagai value di Map"

    Ini bukan pelanggaran dengan sendirinya.

    Contoh:

    Map<String, Order> orders = new HashMap<>();

    Yang dipakai untuk lookup adalah:

    String key
    ↓
    equals()
    hashCode()

    Bukan:

    Order value
    ↓
    equals()
    hashCode()

    Jadi:

    orders.get("ORD-001");

    tidak membutuhkan Order.equals().

    Order.equals/hashCode baru menjadi penting kalau misalnya:

    order1.equals(order2)

    atau:

    Set<Order>

    atau:

    Map<Order, Something>

    digunakan.

    Saya justru akan menulis ini sebagai review comment: jangan menambahkan equals/hashCode hanya karena object kebetulan menjadi Map value.

Hint arah pencarian (bukan daftar lengkap — jangan berhenti kalau sudah menemukan sejumlah ini):

* Ada tipe generic yang dipakai tanpa parameter — apa akibatnya di titik pemanggilan?
* Ada field yang bisa diakses dan diubah langsung dari luar class, di dua tempat berbeda (constructor dan getter) — apa risikonya kalau caller menyimpan referensi itu dan mengubahnya belakangan?
* Ada `catch` yang menangkap lalu tidak melakukan apa pun — apa yang hilang saat ini terjadi di production, dan bagaimana Anda tahu itu terjadi?
* Ada dua nama boolean-returning method/pattern yang bentuknya tidak sesuai konvensi Java — yang mana, dan apa nama yang benar?
* Class `Order` tidak override `equals`/`hashCode` padahal disimpan sebagai value di `Map` — kapan ini jadi masalah nyata?

versi fixing

```java
package com.example.tracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderTracker {

    private static final int MAX_ORDER = 100;

    private final List<Order> orders;
    private final Map<String, Order> orderIndex;

    public OrderTracker(List<Order> initialOrders) {
        Objects.requireNonNull(initialOrders, "initialOrders must not be null");

        if (initialOrders.size() > MAX_ORDER) {
            throw new IllegalArgumentException(
                    "Initial orders must not exceed " + MAX_ORDER
            );
        }

        this.orders = new ArrayList<>(initialOrders);
        this.orderIndex = new HashMap<>();

        for (Order order : initialOrders) {
            addOrderToIndex(order);
        }
    }

    public void addOrder(Order order) {
        Objects.requireNonNull(order, "order must not be null");

        if (orders.size() >= MAX_ORDER) {
            throw new IllegalStateException(
                    "Maximum number of orders is " + MAX_ORDER
            );
        }

        if (orderIndex.containsKey(order.id())) {
            throw new IllegalArgumentException(
                    "Order already exists: " + order.id()
            );
        }

        orders.add(order);
        orderIndex.put(order.id(), order);
    }

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    public boolean isCancelled(String orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");

        Order order = orderIndex.get(orderId);

        if (order == null) {
            throw new IllegalArgumentException(
                    "Order not found: " + orderId
            );
        }

        return order.isCancelled();
    }

    private void addOrderToIndex(Order order) {
        Objects.requireNonNull(order, "order must not be null");

        if (orderIndex.putIfAbsent(order.id(), order) != null) {
            throw new IllegalArgumentException(
                    "Duplicate order id: " + order.id()
            );
        }
    }
}

final class Order {

    private final String id;
    private final String status;

    public Order(String id, String status) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
    }

    public String id() {
        return id;
    }

    public String status() {
        return status;
    }

    public boolean isCancelled() {
        return "CANCELLED".equals(status);
    }
}
```