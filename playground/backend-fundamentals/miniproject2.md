### Konteks

`OrderEnrichmentService` dipanggil oleh job batch tiap malam untuk menyiapkan laporan penjualan harian. Untuk tiap order yang masuk hari itu, servis ini perlu:

1. Ambil harga terkini dari `PricingService` (panggilan HTTP ke servis lain — harga bisa berubah dari harga saat order dibuat, jadi harus diambil ulang) — rata-rata **150ms**.
2. Ambil estimasi pengiriman dari `ShippingService` (panggilan HTTP ke servis eksternal) — rata-rata **200ms**.
3. Gabungkan jadi satu `EnrichedOrder`, lalu akumulasikan ke ringkasan: total revenue hari itu, dan berapa order yang pengirimannya diprediksi delay.

Volume: **500 order/hari**. Mesin yang menjalankan job ini punya **8 core**.

Kode yang berjalan sekarang (dan sudah jadi keluhan tim ops karena job ini makan waktu ~3 menit tiap malam, menunda laporan pagi):

```java
public class OrderEnrichmentService {

    private final PricingClient pricingClient;
    private final ShippingClient shippingClient;

    public OrderEnrichmentService(PricingClient pricingClient, ShippingClient shippingClient) {
        this.pricingClient = pricingClient;
        this.shippingClient = shippingClient;
    }

    public OrderSummary enrichOrders(List<Order> orders) {
        List<EnrichedOrder> enriched = new ArrayList<>();
        double totalRevenue = 0;
        int delayedShipmentCount = 0;

        for (Order order : orders) {
            PriceQuote price = pricingClient.getCurrentPrice(order.getProductId());     // HTTP, ~150ms
            ShippingEstimate shipping = shippingClient.estimate(order.getDestination()); // HTTP, ~200ms

            EnrichedOrder result = new EnrichedOrder(order, price, shipping);
            enriched.add(result);

            totalRevenue += price.getAmount() * order.getQuantity();
            if (shipping.isDelayed()) {
                delayedShipmentCount++;
            }
        }

        return new OrderSummary(enriched, totalRevenue, delayedShipmentCount);
    }
}

// Supporting types — anggap semua ini sudah ada, tidak perlu diubah:
// record Order(String orderId, String productId, String destination, int quantity)
// record PriceQuote(String productId, double amount)
// record ShippingEstimate(boolean isDelayed, int estimatedDays)
// record EnrichedOrder(Order order, PriceQuote price, ShippingEstimate shipping)
// record OrderSummary(List<EnrichedOrder> enrichedOrders, double totalRevenue, int delayedShipmentCount)
//
// interface PricingClient { PriceQuote getCurrentPrice(String productId); }
// interface ShippingClient { ShippingEstimate estimate(String destination); }
```

Hitungan cepat kenapa ini lambat: 500 order × (150ms + 200ms) = **175.000ms ≈ 175 detik ≈ hampir 3 menit** — dan itu asumsi tidak ada satu pun panggilan yang lelet dari rata-rata.

### Tugas Anda

**1. Analisis dulu, jangan langsung nulis kode.** Kalau seseorang di tim Anda mengusulkan fix tercepat: "gampang, bungkus aja loop-nya jadi `orders.forEach(order -> executor.submit(() -> { ...isi loop yang sama... }))`" — tunjukkan **persis 3 variabel** di kode ini yang akan jadi sumber race condition kalau usulan itu dituruti mentah-mentah, dan untuk masing-masing jelaskan: ini soal atomicity (compound action) atau soal struktur data yang tidak thread-safe (rujuk konsep spesifik dari Lesson 2.1 dan 2.2, bukan cuma bilang "race condition").
    
    Kalau loop langsung di-submit ke executor:

    orders.forEach(order -> executor.submit(() -> {
        // isi loop yang sama
    }));

    maka ada 3 shared mutable state:

    Variabel | Masalah | Konsep
    totalRevenue | totalRevenue += ... adalah read → calculate → write, bukan operasi atomic | Compound action / atomicity
    delayedShipmentCount | delayedShipmentCount++ juga read → increment → write | Compound action / atomicity
    enriched | ArrayList.add() dipanggil bersamaan dari banyak thread; ArrayList bukan thread-safe | Thread-unsafe data structure 

    Contoh:

    Thread A: read totalRevenue = 100
    Thread B: read totalRevenue = 100
    Thread A: write 150
    Thread B: write 200

    Hasil = 200
    Seharusnya = 250

    Jadi jangan menyelesaikannya dengan synchronized. Lebih baik hilangkan shared mutable state tersebut.

**2. Refactor jadi paralel — tapi pilih pendekatan yang menghindari shared mutable state, bukan menambal race condition dengan `synchronized`/lock di sekeliling 3 variabel tadi.** Ingat prinsip dari Real-world Example Lesson 2.1: cara paling murah membuat concurrency benar bukan mencari lock yang lebih pintar, tapi mengurangi data yang di-share sejak desain. Pakai `CompletableFuture` supaya tiap order diproses independen, kumpulkan semua hasilnya dulu, baru digabung (reduce) jadi satu `OrderSummary` **setelah** semua future selesai — bukan menulis ke `totalRevenue`/`delayedShipmentCount`/`enriched` bersama dari banyak thread yang jalan bersamaan.

    ```java
    public class OrderEnrichmentService {

        private final PricingClient pricingClient;
        private final ShippingClient shippingClient;
        private final Executor executor;

        public OrderEnrichmentService(
                PricingClient pricingClient,
                ShippingClient shippingClient,
                Executor executor) {

            this.pricingClient = pricingClient;
            this.shippingClient = shippingClient;
            this.executor = executor;
        }

        public OrderSummary enrichOrders(List<Order> orders) {

            List<CompletableFuture<OrderProcessingResult>> futures =
                    orders.stream()
                            .map(this::processOrder)
                            .toList();

            CompletableFuture.allOf(
                    futures.toArray(CompletableFuture[]::new)
            ).join();

            List<EnrichedOrder> enrichedOrders = new ArrayList<>();
            double totalRevenue = 0;
            int delayedShipmentCount = 0;

            for (CompletableFuture<OrderProcessingResult> future : futures) {
                OrderProcessingResult result = future.join();

                if (result.failed()) {
                    // dicatat untuk retry/manual handling
                    continue;
                }

                EnrichedOrder enriched = result.enrichedOrder();

                enrichedOrders.add(enriched);

                totalRevenue +=
                        enriched.price().getAmount()
                                * enriched.order().getQuantity();

                if (enriched.shipping().isDelayed()) {
                    delayedShipmentCount++;
                }
            }

            return new OrderSummary(
                    enrichedOrders,
                    totalRevenue,
                    delayedShipmentCount
            );
        }

        private CompletableFuture<OrderProcessingResult> processOrder(Order order) {

            CompletableFuture<PriceQuote> priceFuture =
                    CompletableFuture.supplyAsync(
                            () -> pricingClient.getCurrentPrice(
                                    order.getProductId()),
                            executor
                    );

            CompletableFuture<ShippingEstimate> shippingFuture =
                    CompletableFuture.supplyAsync(
                            () -> shippingClient.estimate(
                                    order.getDestination()),
                            executor
                    );

            return priceFuture
                    .thenCombine(
                            shippingFuture,
                            (price, shipping) ->
                                    new EnrichedOrder(
                                            order,
                                            price,
                                            shipping
                                    )
                    )
                    .<OrderProcessingResult>handle(
                            (enriched, exception) -> {

                                if (exception != null) {
                                    return OrderProcessingResult.failure(
                                            order,
                                            exception
                                    );
                                }

                                return OrderProcessingResult.success(
                                        enriched
                                );
                            }
                    );
        }
    }

    public record OrderProcessingResult(
        Order order,
        EnrichedOrder enrichedOrder,
        Throwable error
    ) {

        public boolean failed() {
            return error != null;
        }

        public static OrderProcessingResult success(
                EnrichedOrder enrichedOrder) {

            return new OrderProcessingResult(
                    enrichedOrder.order(),
                    enrichedOrder,
                    null
            );
        }

        public static OrderProcessingResult failure(
                Order order,
                Throwable error) {

            return new OrderProcessingResult(
                    order,
                    null,
                    error
            );
        }
    }
    ```

    Dengan desain ini, satu order gagal → order tersebut ditandai gagal → order lain tetap bisa diproses.

**3. Tentukan ukuran thread pool dari rumus, bukan tebakan.** Ini kerja I/O-bound (dua HTTP call per order, hampir semua waktunya nunggu, bukan compute). Pakai rumus `N_threads = N_cpu * U_cpu * (1 + W/C)` dari Lesson 2.2 — turunkan `W/C` dari angka latency yang sudah diberikan di atas (anggap waktu compute murni per order, di luar 2 HTTP call itu, sangat kecil/diabaikan), tunjukkan hitungannya, lalu konstruksi `ThreadPoolExecutor`-nya secara eksplisit (bukan `Executors.newFixedThreadPool` mentah — ingat kenapa itu berisiko dari Lesson 2.2).

    N_threads = N_cpu × U_cpu × (1 + W/C)

    CPU:

    N_cpu = 8

    Anggap utilization target:

    U_cpu = 0.8

    Per order:

    I/O wait = 150ms + 200ms
            = 350ms

    Compute murni diabaikan, jadi:

    C ≈ sangat kecil
    W/C → sangat besar

    Artinya rumus teoritis menjadi tidak praktis kalau C benar-benar dianggap 0.

    Ini poin penting: jangan memasukkan C = 0 ke rumus dan menghasilkan thread pool tak terhingga.

    Untuk sizing praktis, kita butuh asumsi compute kecil. Misalnya anggap:

    C = 10ms
    W = 350ms

    W/C = 350 / 10
        = 35

    Maka:

    N_threads
    = 8 × 0.8 × (1 + 35)
    = 230.4

    Secara teoritis ≈ 230 threads.

    Tetapi untuk production, saya tidak langsung membuat 230 thread tanpa batas, karena downstream HTTP service justru bisa menjadi bottleneck.

    Misalnya mulai dengan bounded executor:

    int corePoolSize = 64;
    int maxPoolSize = 128;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            30,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(500),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    Kenapa bukan:

    Executors.newFixedThreadPool(...)

    Karena fixed thread pool menggunakan unbounded queue, sehingga kalau producer lebih cepat daripada kemampuan worker/downstream, task bisa terus menumpuk → memory pressure dan latency memburuk.

    Catatan: angka 64–128 adalah starting point production, bukan hasil matematis final. Benchmark + rate limit Pricing/Shipping + timeout + observability tetap diperlukan.


**4. Tangani kegagalan salah satu panggilan dengan sadar, bukan default.** Kalau `pricingClient.getCurrentPrice()` atau `shippingClient.estimate()` gagal (lempar exception) untuk 1 dari 500 order, keputusan desain apa yang Anda ambil: gagalkan seluruh batch, atau skip order itu saja dan tetap proses 499 sisanya (dengan order yang gagal ditandai terpisah untuk retry manual)? Implementasikan pilihan Anda pakai `exceptionally`/`handle` di titik yang tepat dalam rantai `CompletableFuture` (Lesson 2.3) — jangan biarkan satu kegagalan membuat `CompletableFuture.allOf(...).join()` melempar exception tanpa penjelasan yang jelas soal order mana yang gagal dan kenapa.

    Saya memilih:

    Fail per order, bukan fail seluruh batch.

    Karena satu order gagal tidak seharusnya membuat 499 order valid ikut gagal.

    handle() diletakkan setelah kombinasi kedua HTTP call:

    return priceFuture
            .thenCombine(
                    shippingFuture,
                    (price, shipping) ->
                            new EnrichedOrder(order, price, shipping)
            )
            .handle((enriched, exception) -> {

                if (exception != null) {
                    return OrderProcessingResult.failure(
                            order,
                            exception
                    );
                }

                return OrderProcessingResult.success(enriched);
            });

    Kalau pricing gagal:

    pricingFuture = FAILED
    shippingFuture = SUCCESS

            ↓

    thenCombine = FAILED

            ↓

    handle()

            ↓

    OrderProcessingResult.failure(order, exception)

    Jadi kita tidak mendapatkan:

    allOf().join()
        ↓
    CompletionException
        ↓
    "entah order mana yang gagal"

    Tetapi mendapatkan:

    Order ABC
    status = FAILED
    error = PricingService timeout

    yang bisa dimasukkan ke retry queue/manual retry.

**5. Tulis penjelasan (bukan cuma kode) kenapa versi paralel Anda aman dari race condition.** Untuk tiap lesson berikut, jelaskan secara spesifik bagaimana desain Anda menghindari atau menyelesaikan masalah yang lesson itu bahas — bukan cuma menyebut nama lesson-nya:
   - 2.1 — di mana compound action (`read-modify-write`) yang tadinya rawan sekarang sudah tidak ada sama sekali (bukan diproteksi lock, tapi memang dihindari)?
        
        Compound action

        Versi lama:

        totalRevenue += value;
        delayedShipmentCount++;

        adalah:

        READ → MODIFY → WRITE

        dan dilakukan bersamaan → race condition.

        Versi baru:

        Thread 1 → menghasilkan Result 1
        Thread 2 → menghasilkan Result 2
        Thread 3 → menghasilkan Result 3

        Tidak ada thread yang menyentuh:

        totalRevenue
        delayedShipmentCount
        enriched

        selama proses paralel.

        Aggregation baru dilakukan setelah semua selesai, secara sequential:

        for (CompletableFuture<OrderProcessingResult> future : futures) {
            ...
            totalRevenue += ...
            delayedShipmentCount++;
        }

        Jadi compound action tersebut tetap ada, tetapi tidak concurrent.

        Lebih tepatnya: kita bukan membuat compound action menjadi thread-safe; kita membuatnya tidak lagi menjadi concurrent operation.

   - 2.2 — kenapa Anda tidak butuh `ConcurrentHashMap` atau struktur thread-safe apa pun untuk agregasi di desain ini?
            
            Kita tidak membutuhkan:

            ConcurrentHashMap
            ConcurrentLinkedQueue
            AtomicInteger
            AtomicLong
            Collections.synchronizedList(...)

            untuk aggregation.

            Kenapa?

            Karena tidak ada shared aggregation state selama worker threads bekerja.

            Setiap worker menghasilkan object miliknya:

            Worker 1 → Result 1
            Worker 2 → Result 2
            Worker 3 → Result 3

            Kemudian main aggregation dilakukan setelah semuanya selesai.

            Ini contoh prinsip:

            Don't share mutable state if you don't have to.

   - 2.3 — bagaimana `CompletableFuture` mengoordinasikan "tunggu semua order selesai baru gabungkan" tanpa Anda menulis sinyal/counter manual sendiri?

            CompletableFuture menangani koordinasi:

            CompletableFuture.allOf(...)

            Artinya:

            Future 1 ──┐
            Future 2 ──┤
            Future 3 ──┤
            ...        ├──> allOf ──> selesai
            Future 500 ┘

            untuk kasus ini.

            CompletableFuture sudah memberikan mekanisme composability dan coordination.

   - 2.4 — apakah virtual threads (JEP 444) relevan dipakai untuk kasus ini? Jelaskan alasannya berdasarkan sifat kerjanya (I/O-bound), bukan cuma "katanya lebih cepat".

            Ya, sangat relevan, karena workload-nya I/O-bound.

            Setiap task sebagian besar waktunya:

            150ms → menunggu PricingService
            200ms → menunggu ShippingService

            bukan melakukan CPU computation.

            Platform thread:

            Thread
            ↓
            menunggu HTTP
            ↓
            OS thread tetap terblokir

            Virtual thread memungkinkan thread yang sedang menunggu I/O untuk di-unmount dari carrier thread, sehingga carrier bisa menjalankan pekerjaan lain.

            Untuk workload seperti ini, virtual threads sangat cocok.

            Misalnya:

            ExecutorService executor =
                    Executors.newVirtualThreadPerTaskExecutor();

            Kemudian:

            CompletableFuture.supplyAsync(
                    () -> pricingClient.getCurrentPrice(...),
                    executor
            );

            Namun tetap perlu limit concurrency ke downstream. Virtual thread membuat concurrency murah, tetapi bukan berarti Pricing/Shipping boleh menerima ribuan request sekaligus. Misalnya gunakan semaphore/rate limiter jika downstream memiliki batas concurrency.


**6. Tantangan bonus (opsional, tapi ini jebakan production nyata yang layak dikenali).** Kalau, di dalam task yang Anda submit ke `ExecutorService` untuk memproses satu order, Anda memanggil `.join()`/`.get()` **blocking** terhadap `CompletableFuture` lain yang disubmit ke **executor yang sama** dengan ukuran pool terbatas — apa yang bisa terjadi saat semua thread di pool itu kebetulan sedang menunggu future yang tugasnya sendiri belum kebagian thread untuk jalan? Hubungkan gejalanya ke pembahasan thread pool exhaustion di Lesson 2.5 (kenapa ini "kelihatan" seperti deadlock walau bukan deadlock murni dalam pengertian Coffman). Jelaskan bagaimana desain Anda di Tugas 2-3 menghindari jebakan ini sejak awal.

    Contoh berbahaya:

    Pool size = 4

    Task A → submit Task B → join(B)
    Task C → submit Task D → join(D)
    Task E → join(...)
    Task F → join(...)

    Kemudian:

    Thread 1 → menunggu Future X
    Thread 2 → menunggu Future Y
    Thread 3 → menunggu Future Z
    Thread 4 → menunggu Future W

    Future X/Y/Z/W
        ↓
    butuh executor yang sama
        ↓
    TIDAK ADA THREAD TERSEDIA

    Akhirnya:

    pool penuh
    ↓
    semua thread blocking
    ↓
    task lanjutan tidak pernah mendapat thread
    ↓
    future tidak pernah selesai
    ↓
    kelihatan seperti livelock

    Ini lebih tepat disebut thread-pool starvation/exhaustion.

    Desain kita menghindarinya karena:

    priceFuture
        .thenCombine(shippingFuture, ...)

    adalah composition, bukan:

    submit(...)
        .join()
        .submit(...)
        .join()

    Jadi kita tidak menggunakan worker thread untuk duduk diam menunggu worker thread lain dari pool yang sama.