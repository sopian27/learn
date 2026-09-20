### Konteks

`OrderService` menangani proses pemesanan sederhana: simpan order, lalu proses pembayaran lewat payment gateway eksternal. Tim QA melaporkan bug aneh: kalau kartu pelanggan ditolak gateway, order **tidak pernah kembali ke status `PENDING`** — malah nyangkut selamanya di status `CHARGING`, padahal method yang menangani pembayaran sudah diberi `@Transactional` supaya rollback otomatis kalau gagal.

```java
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentGatewayClient paymentGatewayClient;

    public OrderService(OrderRepository orderRepository, PaymentGatewayClient paymentGatewayClient) {
        this.orderRepository = orderRepository;
        this.paymentGatewayClient = paymentGatewayClient;
    }

    // Dipanggil langsung oleh OrderController
    public OrderResult placeOrder(OrderRequest request) {
        Order order = orderRepository.save(Order.pending(request));

        processPayment(order); // <-- panggilan internal, bukan lewat controller

        return OrderResult.success(order.getId());
    }

    @Transactional
    public void processPayment(Order order) {
        order.setStatus(OrderStatus.CHARGING);
        orderRepository.save(order);

        paymentGatewayClient.charge(order.getAmount()); // bisa throw PaymentDeclinedException

        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }
}

// PaymentDeclinedException extends RuntimeException -- anggap sudah ada
// OrderController.placeOrder() cukup memanggil orderService.placeOrder(request) -- anggap sudah ada
```

Rekan Anda yang menulis kode ini bersikeras "sudah benar, kan sudah dikasih `@Transactional`" — dan secara sekilas, penempatannya memang kelihatan masuk akal.

### Deliverable

1. **Diagnosis akar masalah.** Jelaskan **persis** kenapa `@Transactional` di `processPayment()` tidak pernah aktif sama sekali di alur ini — rujuk balik ke mekanisme proxy dari Lesson 4.4 (JDK dynamic proxy/CGLIB, kenapa proxy cuma "membungkus" panggilan yang datang dari luar object). Sebagai bagian dari diagnosis, jelaskan juga kenapa `order.setStatus(CHARGING)` beserta `save()`-nya tetap **tersimpan permanen** ke database walau `paymentGatewayClient.charge()` melempar exception setelahnya — padahal tidak ada `@Transactional` yang "aktif" di titik itu.
    @Transactional pada processPayment() tidak aktif karena placeOrder() melakukan self-invocation:

    processPayment(order);

    Di dalam instance OrderService, pemanggilan tersebut secara efektif adalah:

    this.processPayment(order);

    Sedangkan this selalu menunjuk ke target object, bukan Spring proxy.

    Spring AOP bekerja ketika pemanggilan datang melalui proxy:

    Controller
    ↓
    OrderService Proxy
    ↓  @Transactional advice
    OrderService target

    Tetapi self-invocation melewati proxy:

    OrderService target
    ↓
    this.processPayment()
    ↓
    processPayment()

    Akibatnya TransactionInterceptor tidak pernah dijalankan. @Transactional hanya metadata pada method; annotation tersebut tidak otomatis membuat transaksi tanpa AOP proxy yang mencegat pemanggilan.

    Karena tidak ada transaksi yang membungkus seluruh processPayment(), maka:

    order.setStatus(CHARGING);
    orderRepository.save(order);

    tetap bisa tersimpan. save() pada repository Spring Data sendiri berjalan dalam mekanisme transaksi repository. Setelah operasi tersebut selesai, perubahan dapat ter-commit pada transaksi repository tersebut.

    Kemudian:

    paymentGatewayClient.charge(...);

    melempar PaymentDeclinedException.

    Karena tidak ada satu transaksi yang membungkus CHARGING → charge() → PAID, exception tersebut tidak mempunyai transaksi processPayment() yang bisa di-rollback. Akibatnya database dapat tertinggal pada status CHARGING.

2. **Perbaikan Pendekatan 1 — tanpa mengubah struktur class.** Perbaiki bug ini dengan cara memaksa panggilan `processPayment()` lewat proxy Spring, bukan lewat `this` langsung (petunjuk: `AopContext.currentProxy()`, atau *self-injection* lewat `ApplicationContext`/`@Lazy`). Tunjukkan kode lengkapnya, termasuk konfigurasi tambahan yang dibutuhkan supaya pendekatan ini bekerja.

    ```java
        @Service
        public class OrderService {

            private final OrderRepository orderRepository;
            private final PaymentGatewayClient paymentGatewayClient;
            private final ApplicationContext context;

            public OrderService(OrderRepository orderRepository, PaymentGatewayClient paymentGatewayClient, ApplicationContext context) {
                this.orderRepository = orderRepository;
                this.paymentGatewayClient = paymentGatewayClient;
                this.context = context;
            }

            public OrderResult placeOrder(OrderRequest request) {
                Order order = orderRepository.save(Order.pending(request));
                OrderService self = context.getBean(OrderService.class);
                self.processPayment(order);

                return OrderResult.success(order.getId());
            }

            @Transactional
            public void processPayment(Order order) {
                order.setStatus(OrderStatus.CHARGING);
                orderRepository.save(order);

                paymentGatewayClient.charge(order.getAmount());

                order.setStatus(OrderStatus.PAID);
                orderRepository.save(order);
            }
        }

    ```


3. **Perbaikan Pendekatan 2 — restrukturisasi.** Perbaiki bug yang sama dengan cara mengekstrak `processPayment()` ke *bean* terpisah, supaya panggilannya otomatis lewat proxy tanpa trik apa pun. Tunjukkan kode class barunya dan bagaimana `OrderService` memanggilnya.

     ```java
        @Service
        public class OrderService {

            private final OrderRepository orderRepository;
            private final PaymentService paymentService;

            public OrderService(OrderRepository orderRepository, PaymentService paymentService) {
                this.orderRepository = orderRepository;
                this.paymentService = paymentService;
            }

            public OrderResult placeOrder(OrderRequest request) {
                Order order = orderRepository.save(Order.pending(request));
                paymentService.processPayment(order);

                return OrderResult.success(order.getId());
            }
        }

        @Service
        public class PaymentService {

            private final OrderRepository orderRepository;
            private final PaymentGatewayClient paymentGatewayClient;

            public PaymentGatewayClient(OrderRepository orderRepository, PaymentGatewayClient paymentGatewayClient) {
                this.orderRepository = orderRepository;
                this.paymentGatewayClient = paymentGatewayClient;
            }

            @Transactional
            public void processPayment(Order order) {
                order.setStatus(OrderStatus.CHARGING);
                orderRepository.save(order);

                paymentGatewayClient.charge(order.getAmount());

                order.setStatus(OrderStatus.PAID);
                orderRepository.save(order);
            }
        }
    ```

4. **Trade-off.** Bandingkan kedua pendekatan: mana yang lebih "cepat ditambal" vs mana yang lebih sehat secara desain jangka panjang, apa risiko tersembunyi Pendekatan 1 (rujuk kembali kenapa `AopContext.currentProxy()` dianggap kode bau/leaky abstraction oleh banyak senior engineer), dan dalam situasi seperti apa Pendekatan 1 tetap bisa diterima sebagai jalan pintas yang wajar (misalnya: legacy class besar yang belum bisa direfactor sekarang).

    Pendekatan 1 — memanggil bean sendiri melalui proxy

        Kelebihan:

        perubahan kode relatif kecil;
        cocok untuk legacy code yang sulit direfactor;
        dapat memperbaiki bug tanpa memecah class besar.

        Kekurangan:

        OrderService menjadi bergantung pada mekanisme Spring/AOP;
        business logic menjadi mengetahui ApplicationContext atau AopContext;
        lebih sulit dibaca karena pemanggilan method terlihat seperti pemanggilan biasa, tetapi sebenarnya sengaja harus melewati proxy;
        mudah disalahgunakan untuk mengatasi masalah desain yang seharusnya diselesaikan dengan pemisahan responsibility.

        Karena itu, AopContext.currentProxy() atau mengambil bean sendiri melalui ApplicationContext sering dianggap leaky abstraction/code smell: detail implementasi AOP framework bocor ke business code.

    Pendekatan 2 — ekstrak ke bean terpisah

        Lebih bersih secara desain karena dependency terlihat eksplisit:

        paymentService.processPayment(order);

        OrderService tidak perlu tahu bahwa PaymentService menggunakan proxy atau @Transactional.

        Trade-off-nya adalah perubahan struktur code lebih besar karena responsibility perlu dipisahkan.

        Jadi, untuk legacy production code yang membutuhkan hotfix cepat, pendekatan proxy/self-injection dapat menjadi solusi pragmatis. Untuk code yang masih aktif dikembangkan atau direfactor, ekstraksi ke bean terpisah biasanya membuat boundary transaksi lebih eksplisit dan lebih mudah dipahami.

### Production Discussion (tuliskan sebagai bagian penutup jawaban Anda)

Bug ini **tidak pernah muncul di unit test biasa** kalau `processPayment()` di-mock atau ditest terpisah dari `placeOrder()` — jelaskan kenapa, dan kaitkan dengan `standards/TESTING_STANDARDS.md`: jenis test apa (unit vs integration) yang sebenarnya sanggup menangkap bug self-invocation seperti ini sebelum sampai ke production, dan kenapa.
    
    Unit test biasa tidak otomatis menangkap masalah ini karena unit test dapat menjalankan OrderService sebagai object Java biasa:

    new OrderService(orderRepository, paymentGatewayClient)

    Tidak ada Spring proxy di sana. Bahkan jika method processPayment() diberi @Transactional, annotation tersebut tidak melakukan apa-apa dalam unit test murni.

    Bug self-invocation baru terlihat ketika aplikasi dijalankan dengan Spring AOP dan kita menguji apakah pemanggilan benar-benar melewati proxy serta apakah transaksi memiliki boundary yang benar.

    Karena itu, integration test dengan Spring context lebih tepat untuk menangkap bug ini. Test tersebut dapat menggunakan bean yang sebenarnya dari Spring dan memverifikasi perilaku transaksi ketika paymentGatewayClient.charge() melempar exception.

    Unit test tetap berguna untuk menguji business logic processPayment(), tetapi unit test tersebut tidak memverifikasi wiring proxy dan transaction boundary Spring.