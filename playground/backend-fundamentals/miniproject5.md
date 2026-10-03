### Konteks

Anda baru join tim yang mengelola backend "Manajemen Produk Toko Online". Tiga controller di bawah ditulis oleh tiga orang berbeda di waktu berbeda, tidak pernah direview lintas file. Tidak ada satupun yang salah secara sintaks — semuanya lolos code review individual dan jalan di production sekarang. Tugas Anda: audit ketiganya sebagai **satu kontrak API**, lalu desain ulang jadi konsisten.

```java
// ── ProductController.java ──────────────────────────────────────────
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll(); // entity JPA dibalikin langsung, tanpa pagination
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null); // null -> body kosong, status tetap 200
    }

    @PostMapping("/products/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getPrice() == null) {
            return ResponseEntity.badRequest().body("name and price required"); // error = String polos
        }
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved); // 200, bukan 201, tanpa header Location
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existing = productRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.status(404).body(Map.of("error", "not found")); // error = Map, beda lagi bentuknya
        }
        if (product.getName() != null) existing.setName(product.getName());
        if (product.getPrice() != null) existing.setPrice(product.getPrice());
        productRepository.save(existing);
        return ResponseEntity.ok(existing);
    }

    @GetMapping("/product/delete/{id}") // GET dipakai untuk aksi delete
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "deleted successfully"; // error = String lagi, kali ini bukan error tapi tetap inkonsisten
    }
}

// ── CategoryController.java ─────────────────────────────────────────
@RestController
@RequestMapping("/api/category") // prefix "/api" + singular, beda konvensi dari ProductController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest request) {
        try {
            Category c = categoryService.create(request);
            return ResponseEntity.ok(CategoryResponse.from(c)); // 200 untuk create, bukan 201
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // exception apapun ditelan jadi 500 generik, body null
        }
    }

    @GetMapping("/list")
    public ResponseEntity<CategoryPageResponse> listCategories(
            @RequestParam(defaultValue = "1") int pageNumber,  // 1-based, beda dari kebiasaan Spring Data (0-based)
            @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(categoryService.getPage(pageNumber, pageSize));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) { // String, padahal ID aslinya Long
        categoryService.delete(Long.parseLong(categoryId)); // categoryId="abc" -> NumberFormatException -> 500 generik
        return ResponseEntity.ok().build(); // 200 untuk delete, bukan 204
    }
}

// ── StockController.java ────────────────────────────────────────────
@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @PostMapping("/restock")
    public String restock(@RequestParam Long productId, @RequestParam int quantity) {
        Stock stock = stockRepository.findByProductId(productId);
        stock.setQuantity(stock.getQuantity() + quantity); // retry dari klien (timeout, dsb) = stok nambah dobel
        stockRepository.save(stock);
        return "restocked";
    }

    @PatchMapping("/{productId}/adjust")
    public ResponseEntity<Stock> adjustStock(@PathVariable Long productId, @RequestBody Map<String, Object> body) {
        Stock stock = stockRepository.findByProductId(productId);
        stock.setQuantity((Integer) body.get("quantity")); // tanpa validasi, tanpa cek null, trust body mentah
        stockRepository.save(stock); // dua request adjust bersamaan -> lost update, tidak ada @Version/ETag
        return ResponseEntity.ok(stock);
    }
}
```

Anggap `Product`, `Category`, `Stock` adalah `@Entity` JPA biasa (`Product` punya `id`, `name`, `price`, `categoryId`; `Category` punya `id`, `name`; `Stock` punya `id`, `productId`, `quantity`). `CategoryRequest`/`CategoryResponse` sudah ada sebagai DTO (satu-satunya di antara ketiga controller yang sudah pakai DTO — walau prosesnya sendiri masih bocor di tempat lain).

### Deliverable

Jawab di vault ini (di bawah heading Mini Project ini) atau kirim di chat — kode boleh potongan, tidak perlu proyek jalan penuh. Tulis **alasan** di tiap keputusan, bukan cuma hasil akhirnya. Ini rangkuman semua lesson 5.1–5.5, jadi kalau ragu, tengok balik ke lesson yang relevan sebelum menjawab.

1. **Audit lintas-controller.** Buat 1 tabel berisi **minimal 12 pelanggaran berbeda** dari ketiga controller di atas. Kolom wajib: `Controller` | `Endpoint` | `Pelanggaran` | `Standard yang dilanggar` (rujuk `standards/API_STANDARDS.md` atau `standards/ERROR_HANDLING_STANDARDS.md` section spesifik) | `Konsekuensi nyata buat konsumen`. Jangan cuma sebut "penamaan salah" — tulis konsekuensinya (siapa yang kena, dalam skenario apa), sama seperti audit Bagian 1 Lesson 5.1. Pastikan tercakup: resource naming, HTTP method/status code, bentuk error yang beda-beda (String vs Map vs DTO vs 500 generik), pagination yang tidak konsisten (0-based vs 1-based, ada vs tidak ada), entity JPA yang di-expose langsung, exception yang ditelan diam-diam, dan risiko idempotency/concurrency di `StockController`.

| No | Controller | Endpoint                               | Pelanggaran                                                           | Konsekuensi nyata                                                                                                                           | Standard yang dilanggar                                                 |
| -: | ---------- | -------------------------------------- | --------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------- |
|  1 | Product    | `GET /products`                        | Tidak ada pagination                                                  | Dataset besar dapat membuat response lambat, memory/CPU tinggi, dan membebani client/network.                                               | REST API — Pagination / Resource Collection                             |
|  2 | Product    | `GET /product/{id}`                    | Resource singular dan not-found dikembalikan `200 + null`             | Client dapat menganggap request berhasil padahal resource tidak ada.                                                                        | REST API — Resource-oriented URI & HTTP 404 Not Found                   |
|  3 | Product    | `POST /products/create`                | Verb `create` dimasukkan ke URL                                       | URL tidak merepresentasikan resource; routing menjadi tidak konsisten dengan Category/REST convention.                                      | REST API — Resource-oriented URI, HTTP method semantics                 |
|  4 | Product    | `POST /products/create`                | Entity JPA diterima langsung sebagai request body                     | Client dapat mengirim field entity yang seharusnya tidak boleh diubah dan coupling API–database meningkat.                                  | API Design — DTO boundary / Separation of API & persistence model       |
|  5 | Product    | `POST /products/create`                | Validation manual di controller                                       | Logic validation tersebar dan response validation tidak mengikuti kontrak global.                                                           | Jakarta Bean Validation / Separation of concerns                        |
|  6 | Product    | `POST /products/create`                | Create mengembalikan `200`, bukan `201`                               | Client kehilangan semantic distinction antara successful retrieval/update dan resource creation.                                            | HTTP Semantics — `201 Created`                                          |
|  7 | Product    | `PUT /product/update/{id}`             | Error menggunakan `Map`, sedangkan endpoint lain menggunakan `String` | Client membutuhkan parsing berbeda untuk error yang seharusnya punya satu kontrak.                                                          | API Error Handling — Unified error contract                             |
|  8 | Product    | `PUT /product/update/{id}`             | PUT hanya mengubah field non-null                                     | Semantik sebenarnya partial update, tetapi method HTTP menyatakan full replacement.                                                         | HTTP Semantics — `PUT` as full replacement                              |
|  9 | Product    | `GET /product/delete/{id}`             | GET digunakan untuk mutation/delete                                   | Client, cache, crawler, atau retry GET dapat memicu penghapusan secara tidak sengaja.                                                       | HTTP Semantics — Safe methods (`GET` must not mutate state)             |
| 10 | Product    | Semua endpoint                         | URL menggunakan singular/plural dan verb secara tidak konsisten       | Client SDK, dokumentasi, dan maintenance menjadi lebih kompleks.                                                                            | REST API — Consistent resource-oriented URI                             |
| 11 | Category   | `/api/category/list`                   | Pagination hanya tersedia di Category dan memakai 1-based             | Client harus menggunakan aturan pagination berbeda antar-resource.                                                                          | REST API — Consistent pagination contract                               |
| 12 | Category   | `DELETE /delete/{categoryId}`          | ID diterima `String`, lalu di-parse manual                            | Input seperti `abc` menghasilkan `NumberFormatException` yang masuk fallback `500`, padahal invalid path parameter seharusnya client error. | API Validation — Path parameter validation / HTTP `400 Bad Request`     |
| 13 | Category   | `POST /add`                            | Verb `add` redundant dengan POST                                      | URL menjadi action-oriented dan tidak konsisten dengan resource-based routing.                                                              | REST API — Resource-oriented URI, HTTP method semantics                 |
| 14 | Category   | `POST /add`                            | Exception ditelan `catch (Exception)`                                 | Root cause hilang dari layer API dan semua jenis error dipukul rata menjadi `500`.                                                          | Error Handling — Exception propagation / centralized exception handling |
| 15 | Category   | `POST /add`                            | Error response `null`                                                 | Client menerima `500` tanpa payload error yang dapat digunakan untuk diagnosis.                                                             | API Error Handling — Unified error response                             |
| 16 | Stock      | `POST /restock`                        | Tidak idempotent                                                      | Retry akibat timeout dapat menambah stok dua kali untuk satu operasi bisnis.                                                                | API Reliability — Idempotency for retryable mutation                    |
| 17 | Stock      | `PATCH /{productId}/adjust`            | `Map<String,Object>` tanpa DTO/validation                             | `quantity` null/salah tipe dapat menyebabkan runtime error dan kontrak request tidak jelas.                                                 | API Contract — Typed DTO + Bean Validation                              |
| 18 | Stock      | `PATCH /{productId}/adjust`            | Tidak ada optimistic locking/concurrency control                      | Dua update bersamaan dapat menyebabkan lost update sehingga stok akhir salah.                                                               | Data Consistency — Optimistic concurrency control                       |
| 19 | Product    | `GET /products`, detail, create/update | Entity JPA diekspos langsung sebagai response                         | Perubahan schema entity dapat menjadi breaking change API dan field internal bisa ikut terekspos.                                           | API Design — DTO boundary / Persistence–API separation                  |

2. **Desain ulang resource & routing.** Tentukan struktur URL final untuk ketiga resource ini dalam satu API yang konsisten. Putuskan: apakah `stock` jadi sub-resource dari `product` (`/products/{id}/stock`) atau tetap top-level (`/stocks?productId=...`) — justifikasi pilihan Anda merujuk balik ke aturan "kapan nested masuk akal" (Lesson 5.1). Tulis tabel kontrak akhir: `Method` | `Path` | `Status Sukses` | `Status Error yang mungkin` untuk **semua** operasi CRUD dari ketiga resource (termasuk list dengan pagination yang konsisten — pilih 0-based atau 1-based, satu saja untuk semua endpoint).

| Method | Path                                 | Sukses | Error yang mungkin           |
| ------ | ------------------------------------ | -----: | ---------------------------- |
| GET    | `/api/v1/products?page=0&size=20`    |    200 | 400, 401                     |
| GET    | `/api/v1/products/{productId}`       |    200 | 400, 401, 404                |
| POST   | `/api/v1/products`                   |    201 | 400, 401, 409                |
| PUT    | `/api/v1/products/{productId}`       |    200 | 400, 401, 404, 409           |
| DELETE | `/api/v1/products/{productId}`       |    204 | 400, 401, 404, 409           |
| GET    | `/api/v1/categories?page=0&size=20`  |    200 | 400, 401                     |
| GET    | `/api/v1/categories/{categoryId}`    |    200 | 400, 401, 404                |
| POST   | `/api/v1/categories`                 |    201 | 400, 401, 409                |
| PUT    | `/api/v1/categories/{categoryId}`    |    200 | 400, 401, 404, 409           |
| DELETE | `/api/v1/categories/{categoryId}`    |    204 | 400, 401, 404, 409           |
| GET    | `/api/v1/products/{productId}/stock` |    200 | 400, 401, 404                |
| POST   | `/api/v1/products/{productId}/stock` |    200 | 400, 401, 404, 422     |
| PUT    | `/api/v1/products/{productId}/stock` |    200 | 400, 401, 404, 409, 412, 428 |


    
    Stock dipilih sebagai nested resource dari Product: /products/{productId}/stock.

    Keputusan ini mengikuti prinsip "kapan nested resource masuk akal" pada Lesson 5.1: nested resource cocok ketika child mempunyai hubungan kepemilikan yang kuat terhadap parent, konteks parent diperlukan untuk mengidentifikasi resource, dan child tidak mempunyai lifecycle/domain identity yang benar-benar independen.

    Pada domain ini, Stock merepresentasikan persediaan untuk satu Product. Operasi bisnis stock selalu berada dalam konteks product tertentu, misalnya "restock product 123" atau "set stock product 123 menjadi 110". Karena itu /products/{productId}/stock lebih mencerminkan hubungan domain daripada /stocks/{stockId}.

    Stock juga tidak memiliki lifecycle bisnis independen dalam requirement ini. Client tidak membuat atau menghapus stock sebagai resource yang berdiri sendiri; stock ada sebagai bagian dari product.

    Karena itu kontrak final menggunakan:

    GET  /products/{productId}/stock
    POST /products/{productId}/stock
    PUT  /products/{productId}/stock

    Nested resource tidak dipilih hanya karena "lebih RESTful", tetapi karena hubungan ownership dan lifecycle-nya memang mendukung nesting, sesuai prinsip Lesson 5.1.

    Jika pada masa depan Stock mempunyai lifecycle independen, digunakan oleh banyak Product, memiliki workflow sendiri, atau client terutama beroperasi berdasarkan stockId, maka top-level /stocks/{stockId} dapat menjadi desain yang lebih sesuai.

3. **Kontrak error terpadu.** Desain satu skema `ApiError` (field sesuai `standards/ERROR_HANDLING_STANDARDS.md`: timestamp, status, errorCode, message, path, traceId, fieldErrors) dan implementasikan `@RestControllerAdvice` global yang menangani minimal: (a) validation error (`MethodArgumentNotValidException`) dengan `fieldErrors` terisi per field, (b) business exception (mis. `ProductNotFoundException`, `CategoryNotFoundException`) jadi `404` dengan `errorCode` yang stabil, dan (c) exception tak terduga (termasuk kasus `NumberFormatException` dari `deleteCategory` di atas) jadi `500` **tanpa** membocorkan pesan exception mentah ke klien. Tunjukkan kodenya, bukan cuma skema.

        D:\learn\playground\backend-fundamentals\GlobalExceptionHandler.java

        ```java
        public record ApiError( Instant timestamp, int status, String errorCode, String message, String path, String traceId, List<FieldErrorDetail> fieldErrors ) { public static ApiError of( HttpStatus status, String errorCode, String message, String path, String traceId) { return new ApiError( Instant.now(), status.value(), errorCode, message, path, traceId, List.of() ); } public static ApiError of( HttpStatus status, String errorCode, String message, String path, String traceId, List<FieldErrorDetail> fieldErrors) { return new ApiError( Instant.now(), status.value(), errorCode, message, path, traceId, fieldErrors ); } }
        ```
        

4. **Validation layer.** Ganti `Product`/`Category` entity yang jadi request body langsung dengan DTO request terpisah (`CreateProductRequest`, `UpdateProductRequest`, dst), pasang `@Valid` + anotasi Bean Validation yang tepat (`@NotBlank`, `@NotNull`, `@Positive`, dll) untuk tiap field, dan jelaskan kenapa `PUT /product/update/{id}` yang sekarang (skip field `null`) sebenarnya berperilaku seperti apa (rujuk balik ke gap yang sama yang dibahas di Bagian 1.1 Lesson 5.5) — putuskan apakah endpoint ini tetap `PUT` semantik penuh (replace, field kosong = dihapus) atau diubah jadi `PATCH`.

    ```java
        public record CreateProductRequest( @NotBlank String name, @NotNull @PositiveOrZero  @JsonFormat(shape = JsonFormat.Shape.STRING) BigDecimal price, @NotNull @Positive Long categoryId ) {}

        public record UpdateProductRequest( @NotBlank String name, @NotNull @PositiveOrZero  @JsonFormat(shape = JsonFormat.Shape.STRING) BigDecimal price, @NotNull @Positive Long categoryId ) {}

        @PostMapping("/products")
        public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {}

        @PutMapping("/products/{id}")
        public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest updateProductRequest) {}
    ```

    Implementasi lama sebenarnya adalah partial update karena field null diabaikan. Karena itu perilakunya lebih dekat ke PATCH daripada PUT. Dalam redesign ini saya mempertahankan PUT, tetapi mengubah request menjadi full replacement dengan seluruh field wajib. Jika kebutuhan bisnis memang membutuhkan partial update, endpoint seharusnya diubah menjadi PATCH dengan mekanisme presence-aware DTO.

5. **Idempotency & concurrency untuk `StockController`.** (a) `POST /stocks/restock` berbahaya kalau di-retry klien (timeout jaringan, double-click) — desain perbaikannya: apakah cukup `Idempotency-Key` ala Lesson 5.5, atau ada alasan untuk mengubah bentuk endpoint-nya sama sekali? Jelaskan skenario konkret sebelum dan sesudah perbaikan. (b) `adjustStock` rawan lost update kalau dua request datang bersamaan — perbaiki dengan `@Version` di entity `Stock` plus `ETag`/`If-Match` di endpoint, tunjukkan kode lengkapnya termasuk response saat konflik terjadi.

    (a)
    
    Delta / increment

    POST /products/{productId}/stock
    Idempotency-Key: abc-123

    {
    "quantity": 10
    }

    Semantiknya:

    stock = stock + 10

    Secara alami non-idempotent. Kalau request dijalankan dua kali, stok naik 20.

    Solusinya adalah Idempotency-Key untuk membuat retry aman secara application-level.

    Skenario timeout:

    Stok awal = 100

    Client → POST +10
    Server → stok menjadi 110
    Server → response 200
        X response hilang karena network timeout

    Client retry dengan Idempotency-Key yang sama
    Server → mengenali request yang sama
        → tidak menjalankan +10 lagi
        → replay response sebelumnya

    Stok akhir = 110

    Tanpa Idempotency-Key:

    100
    +10 → 110
    timeout
    retry +10 → 120

    Stok akhir = 120 ❌

    Absolute / set

    PUT /products/{productId}/stock
    If-Match: "42"

    {
    "quantity": 110
    }

    Semantiknya:

    stock = 110

    PUT secara konsep idempotent. Request yang sama dijalankan berulang kali tetap menghasilkan 110.

    Tetapi ada masalah berbeda: lost update.

    Misalnya:

    Stok = 100

    Client A membaca 100
    Client B membaca 100

    A → set 110
    B → set 105

    Tanpa concurrency control:

    hasil akhir = 105

    Update A hilang.

    Karena itu absolute update sebaiknya memakai:

    If-Match: "42"

    Server hanya menerima update jika version/resource state masih sesuai.

    2. Trade-off
    Pendekatan	Semantik	Retry	Concurrency	Kompleksitas
    POST + quantity + Idempotency-Key	+10	Aman dengan key	Perlu mekanisme concurrency terpisah	Lebih tinggi
    PUT + quantity + If-Match	set 110	Idempotent secara alami	If-Match mencegah lost update	Lebih sederhana untuk retry
    POST + quantity tanpa key	+10	Tidak aman	—	Sederhana tapi berisiko
    Kapan pilih yang mana?

    Pilih POST + delta + Idempotency-Key kalau domain memang berbicara tentang operasi bisnis/increment:

    “Restock 10 unit.”

    Ini cocok kalau restock merupakan event/command yang memang ingin dicatat sebagai suatu aksi.

    Pilih PUT + absolute + If-Match kalau domain lebih cocok dengan state:

    “Stock produk ini harus menjadi 110.”

    Ini cocok ketika client memiliki representasi state terbaru dan ingin menggantinya secara aman.

    Jadi untuk API kita

    Kalau requirement-nya memang “restock sejumlah N unit”, mengubah endpoint menjadi PUT hanya demi mendapatkan idempotency bukan keharusan.

    POST /products/{productId}/stock + Idempotency-Key adalah desain yang masuk akal karena mempertahankan semantik restock sebagai delta.

    Sedangkan PUT /products/{productId}/stock adalah alternatif desain, dengan trade-off berbeda: idempotency alami, tetapi membutuhkan If-Match untuk optimistic concurrency.

    Intinya: jangan mengubah bentuk endpoint hanya untuk menyelesaikan masalah retry. Tentukan dulu apakah operasi bisnisnya adalah “tambah N” atau “set menjadi N”.


    
    ```java
        @PostMapping("/products/{productId}/stock")
        public ResponseEntity<StockDto> restock(
                @RequestHeader(value = "Idempotency-Key", required = false) String key,
                @PathVariable Long productId,
                @Valid @RequestBody RestockRequest request) {

            if (key == null || key.isBlank()) {
                throw new MissingIdempotencyKeyException();
            }

            IdempotentResult<StockDto> result =
                    stockRestockService.create(productId, key, request.quantity());

            return ResponseEntity
                    .status(result.status())
                    .header(
                            "Idempotent-Replayed",
                            String.valueOf(result.replayed())
                    )
                    .body(result.body());
        }

        @Service
        public class StockRestockService {

            private final StockRepository stockRepository;
            private final IdempotencyKeyRepository keys;

            public StockRestockService(
                    StockRepository stockRepository,
                    IdempotencyKeyRepository keys) {

                this.stockRepository = stockRepository;
                this.keys = keys;
            }

            @Transactional
            public IdempotentResult<StockDto> create(
                    Long productId,
                    String key,
                    int quantity) {

                String hash = Sha256.hex(quantity + ":" + productId);

                int inserted = keys.tryInsert(productId, key, hash);

                // =====================================================
                // Request dengan Idempotency-Key yang sudah pernah dipakai
                // =====================================================

                if (inserted == 0) {

                    IdempotencyKeyRecord existing =
                            keys.findByProductIdAndIdemKey(productId, key)
                                    .orElseThrow(() ->
                                            new IllegalStateException(
                                                    "Idempotency record tidak ditemukan"
                                            ));

                    if (!existing.getRequestHash().equals(hash)) {
                        throw new IdempotencyKeyReuseException(
                                "Idempotency-Key '%s' sudah dipakai "
                                        + "untuk request dengan isi berbeda."
                                        .formatted(key)
                        );
                    }

                    return IdempotentResult.replayed(
                            existing.getResponseStatus(),
                            readBody(existing)
                    );
                }

                // =====================================================
                // Request pertama
                // =====================================================

                Stock stock = stockRepository.findByProductId(productId)
                        .orElseThrow(() ->
                                new StockNotFoundException(
                                        "Stock untuk product " + productId
                                                + " tidak ditemukan"
                                )
                        );

                stock.setQuantity(
                        stock.getQuantity() + quantity
                );

                Stock saved = stockRepository.save(stock);

                // Jangan serialize entity.
                // Simpan DTO sebagai response idempotency.
                StockDto response = toDto(saved);

                keys.markCompleted(
                        productId,
                        key,
                        200,
                        toJson(response)
                );

                return IdempotentResult.fresh(
                        200,
                        response
                );
            }

            private StockDto readBody(IdempotencyKeyRecord record) {
                return fromJson(record.getResponseBody());
            }

            private StockDto toDto(Stock stock) {
                return new StockDto(
                        stock.getId(),
                        stock.getProductId(),
                        stock.getQuantity()
                );
            }

            private String toJson(StockDto dto) {
                return JsonUtils.write(dto);
            }

            private StockDto fromJson(String json) {
                return JsonUtils.read(json, StockDto.class);
            }
        }

        public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKeyRecord, Long> {

            @Modifying
            @Query(value = """
                    INSERT INTO idempotency_key (product_id, idem_key, request_hash)
                    VALUES (:product_id, :key, :hash)
                    ON CONFLICT (product_id, idem_key) DO NOTHING
                    """, nativeQuery = true)
            int tryInsert(@Param("product_id") Long productId, @Param("key") String key, @Param("hash") String hash);

            Optional<IdempotencyKeyRecord> findByProductIdAndIdemKey(Long productId, String idemKey);
        }
    ```

    Idempotency-Key scope dan TTL

    Idempotency-Key berlaku dalam scope productId + key, sesuai constraint database UNIQUE (product_id, idem_key). Dengan demikian, key yang sama dapat digunakan untuk product berbeda tanpa dianggap sebagai request yang sama.

    Record idempotency disimpan selama 24 jam (TTL). TTL dipilih untuk mencakup retry akibat timeout, network failure, atau client retry yang biasanya terjadi setelah request awal, sekaligus mencegah tabel idempotency tumbuh tanpa batas.

    Selama record masih berada dalam TTL:

    productId + key sama dan request payload sama → response sebelumnya di-replay.
    productId + key sama tetapi payload berbeda → reject sebagai penggunaan key yang tidak konsisten, misalnya 422 IDEMPOTENCY_KEY_REUSE.
    Setelah 24 jam → record boleh dibersihkan oleh scheduled cleanup dan key tidak lagi dijamin dapat di-replay.

    Untuk mencegah dua request dengan key yang sama dieksekusi bersamaan, insert record idempotency harus dilindungi oleh unique constraint/transaction. Request pertama menjadi pemilik eksekusi; request berikutnya tidak boleh menjalankan restock kedua kali.

    (b) di api yang telah saya revisi adjust stock endpoint menjadi seperti ini : @PutMapping("/products/{productId}/stock")
        ```java
                @Entity
                public class Stock {

                    @Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    private Long id;

                    private Long productId;

                    private Integer quantity;

                    @Version
                    private long version;
                }

                final class ETags {
                    private ETags() {}

                    static String of(Stock p) {
                        return "\"" + p.getVersion() + "\"";       // strong ETag, ber-kutip sesuai spesifikasi
                    }

                    // If-Match memakai STRONG comparison: weak ETag (W/"..") tidak pernah cocok.
                    // Header bisa berisi daftar ("1", "2") atau "*" (cocok kalau resource ada).
                    static boolean ifMatchSatisfied(String ifMatchHeader, String currentEtag) {
                        for (String candidate : ifMatchHeader.split(",")) {
                            String tag = candidate.trim();
                            if (tag.equals("*") || tag.equals(currentEtag)) {
                                return true;
                            }
                        }
                        return false;
                    }
                }

                @Service
                public class StockService {

                    private final StockRepository stockRepository;
                    private final Validator validator;

                    public StockService(
                            StockRepository stockRepository,
                            Validator validator) {
                        this.stockRepository = stockRepository;
                        this.validator = validator;
                    }

                    @Transactional
                    public Stock replace(long productId, String ifMatch, ReplaceStockRequest req) {
                        Stock stock = loadAndCheckPrecondition(productId, ifMatch);

                        stock.replaceQuantity(req.quantity());

                        return stockRepository.saveAndFlush(stock);
                    }

                    private Stock loadAndCheckPrecondition(
                            long productId,
                            String ifMatch) {

                        if (ifMatch == null || ifMatch.isBlank()) {
                            throw new PreconditionRequiredException(
                                    "Header If-Match wajib untuk operasi ini.");
                        }

                        Stock stock = stockRepository.findByProductId(productId)
                                .orElseThrow(() ->
                                        new ResourceNotFoundException(
                                                "Stock untuk product id=%d tidak ditemukan"
                                                        .formatted(productId)));

                        if (!ETags.ifMatchSatisfied(ifMatch, ETags.of(stock))) {
                            throw new PreconditionFailedException(
                                    "Stock untuk product id=%d sudah berubah sejak terakhir "
                                    + "Anda ambil. GET ulang lalu coba lagi."
                                            .formatted(productId));
                        }

                        return stock;
                    }
                }

                @PutMapping("/products/{productId}/stock")
                public ResponseEntity<StockDto> replace(
                        @PathVariable long productId,
                        @RequestHeader(value = HttpHeaders.IF_MATCH, required = false) String ifMatch,
                        @Valid @RequestBody ReplaceStockRequest body) {
                    Stock updated = stockService.replace(productId, ifMatch, body);
                    return ResponseEntity.ok().eTag(ETags.of(updated)).body(StockDto.from(updated));
                }

        ```
        | Scenario                 |  Status |
        | ------------------------ | ------: |
        | Stock tidak ditemukan    |     404 |
        | Body invalid             |     400 |
        | `If-Match` tidak dikirim | **428** |
        | `If-Match` salah/stale   | **412** |
        | Conflict business rule   |     409 |
        | sukses                   |     200 |

        Perbedaan 409 Conflict dan 412 Precondition Failed

        Keduanya sama-sama menunjukkan request tidak dapat menghasilkan perubahan yang diminta, tetapi penyebabnya berbeda.

        412 Precondition Failed digunakan ketika client memberikan precondition HTTP dan precondition tersebut tidak terpenuhi.

        Pada endpoint:

        PUT /api/v1/products/123/stock
        If-Match: "41"

        misalnya server sekarang memiliki:

        ETag: "42"

        Maka:

        If-Match "41"
            ↓
        current ETag "42"
            ↓
        precondition gagal
            ↓
        412 Precondition Failed

        Artinya masalahnya adalah representasi/state yang digunakan client sudah stale. Client perlu GET ulang untuk memperoleh ETag terbaru sebelum mencoba kembali.

        Sebaliknya, 409 Conflict digunakan ketika request secara syntactically valid dan precondition HTTP dapat terpenuhi, tetapi operasi bertentangan dengan business rule atau state domain.

        Contohnya:

        Product status = DISCONTINUED

        POST /products/123/stock
        {
        "quantity": 10
        }

        Jika business rule melarang restock product yang sudah discontinued:

        request valid
            ↓
        Idempotency-Key valid
            ↓
        resource ada
            ↓
        business rule menolak
            ↓
        409 Conflict

        Contoh lain:

        categoryId = 999

        ketika business rule menetapkan category tersebut tidak boleh digunakan pada product tertentu, atau operasi duplicate yang memang dikategorikan sebagai domain conflict.


6. **Dokumentasi OpenAPI.** Tulis spesifikasi OpenAPI (anotasi springdoc di atas kode hasil desain ulang Anda, atau YAML mentah — pilih salah satu dan konsisten) untuk **seluruh** endpoint hasil Deliverable 2–5: skema request/response tiap resource, `ApiError` sebagai reusable component (`components/schemas`) dipakai ulang di semua response error, contoh request/response yang realistis (bukan placeholder `"string"`), dan semua kode status yang mungkin per endpoint (termasuk `409`/`412`/`428` untuk endpoint stock kalau relevan dari Deliverable 5)
    D:\learn\playground\backend-fundamentals\product-openapi.yaml


production discussion :

## Production Discussion

Perubahan kontrak pada API ini berpotensi menjadi **breaking change**, terutama untuk existing mobile client dan integration script.

### 1. Dampak terhadap mobile client

Perubahan berikut tidak backward-compatible:

* `/product/{id}` → `/products/{productId}`
* `/products/create` → `POST /products`
* `categoryId` menjadi field wajib pada create/update Product
* `PUT Product` berubah menjadi full replacement sehingga client harus mengirim seluruh field yang diwajibkan
* format error berubah menjadi unified `ApiError`

Existing mobile client tidak boleh langsung diarahkan ke kontrak baru tanpa migration plan.

Strategi yang dipilih:

1. Pertahankan kontrak lama sementara.
2. Introduce kontrak baru sebagai **v1**.
3. Migrasikan mobile client secara bertahap.
4. Monitor traffic endpoint lama.
5. Berikan masa deprecation yang jelas.
6. Setelah seluruh consumer bermigrasi dan traffic lama mencapai nol/acceptable threshold, endpoint lama dapat dihapus.

### 2. `/v1` vs version melalui header

Untuk API ini dipilih **URI versioning**, misalnya:

```text
/api/v1/products
/api/v1/categories
/api/v1/products/{productId}/stock
```

Dibanding version melalui custom header, URI versioning lebih mudah:

* ditemukan dan dipahami developer;
* digunakan oleh mobile client;
* diuji melalui browser/curl/Postman;
* dipantau berdasarkan access log;
* dirouting oleh API gateway;
* didokumentasikan dalam OpenAPI.

Header versioning dapat digunakan pada sistem yang sudah memiliki infrastructure/content-negotiation strategy yang matang, tetapi untuk API ini URI versioning memberikan kontrak yang lebih eksplisit.

### 3. Masa deprecation

Deprecation tidak berarti endpoint lama langsung dimatikan.

Contoh lifecycle:

```text
Old API
  ↓
Announce deprecation
  ↓
New /v1 API available
  ↓
Mobile/client migration
  ↓
Monitor old endpoint traffic
  ↓
Deprecation deadline
  ↓
Old API removed
```

Selama masa transisi, dokumentasikan:

* endpoint yang deprecated;
* pengganti endpoint;
* tanggal deprecation;
* target removal date;
* migration guide;
* consumer yang masih menggunakan endpoint lama.

Jika memungkinkan, response endpoint lama juga dapat memberikan header seperti:

```http
Deprecation: true
Sunset: <planned-removal-date>
```

### 4. Finance script / non-mobile integration

Finance script diperlakukan sebagai **consumer yang harus dianggap backward-compatibility sensitive**.

Jangan mengubah endpoint atau response secara diam-diam hanya karena script tersebut bukan mobile application.

Sebelum migration:

1. identifikasi script dan owner-nya;
2. catat endpoint, method, request, dan response yang digunakan;
3. cek apakah script bergantung pada field/error format tertentu;
4. sediakan migration path ke `/v1`;
5. jalankan old dan new contract secara paralel bila diperlukan;
6. validasi hasil new API terhadap hasil existing process;
7. lakukan cutover setelah consumer tervalidasi.

Khusus finance workflow, perubahan yang memengaruhi jumlah transaksi, stock/value calculation, atau parsing response harus diperlakukan sebagai **integration risk**, bukan sekadar perubahan URL.

### 5. Prinsip migration

Targetnya bukan sekadar:

> "API baru sudah tersedia."

Tetapi:

> **"Semua consumer yang terdampak sudah memiliki jalur migrasi yang tervalidasi sebelum kontrak lama dihapus."**

Dengan demikian, breaking change seperti perubahan path, `categoryId` menjadi required, dan `PUT` full replacement dapat dilakukan tanpa melakukan hard cut-over terhadap seluruh consumer sekaligus.
