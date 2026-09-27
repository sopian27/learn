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

2. **Desain ulang resource & routing.** Tentukan struktur URL final untuk ketiga resource ini dalam satu API yang konsisten. Putuskan: apakah `stock` jadi sub-resource dari `product` (`/products/{id}/stock`) atau tetap top-level (`/stocks?productId=...`) — justifikasi pilihan Anda merujuk balik ke aturan "kapan nested masuk akal" (Lesson 5.1). Tulis tabel kontrak akhir: `Method` | `Path` | `Status Sukses` | `Status Error yang mungkin` untuk **semua** operasi CRUD dari ketiga resource (termasuk list dengan pagination yang konsisten — pilih 0-based atau 1-based, satu saja untuk semua endpoint).

3. **Kontrak error terpadu.** Desain satu skema `ApiError` (field sesuai `standards/ERROR_HANDLING_STANDARDS.md`: timestamp, status, errorCode, message, path, traceId, fieldErrors) dan implementasikan `@RestControllerAdvice` global yang menangani minimal: (a) validation error (`MethodArgumentNotValidException`) dengan `fieldErrors` terisi per field, (b) business exception (mis. `ProductNotFoundException`, `CategoryNotFoundException`) jadi `404` dengan `errorCode` yang stabil, dan (c) exception tak terduga (termasuk kasus `NumberFormatException` dari `deleteCategory` di atas) jadi `500` **tanpa** membocorkan pesan exception mentah ke klien. Tunjukkan kodenya, bukan cuma skema.

4. **Validation layer.** Ganti `Product`/`Category` entity yang jadi request body langsung dengan DTO request terpisah (`CreateProductRequest`, `UpdateProductRequest`, dst), pasang `@Valid` + anotasi Bean Validation yang tepat (`@NotBlank`, `@NotNull`, `@Positive`, dll) untuk tiap field, dan jelaskan kenapa `PUT /product/update/{id}` yang sekarang (skip field `null`) sebenarnya berperilaku seperti apa (rujuk balik ke gap yang sama yang dibahas di Bagian 1.1 Lesson 5.5) — putuskan apakah endpoint ini tetap `PUT` semantik penuh (replace, field kosong = dihapus) atau diubah jadi `PATCH`.

5. **Idempotency & concurrency untuk `StockController`.** (a) `POST /stocks/restock` berbahaya kalau di-retry klien (timeout jaringan, double-click) — desain perbaikannya: apakah cukup `Idempotency-Key` ala Lesson 5.5, atau ada alasan untuk mengubah bentuk endpoint-nya sama sekali? Jelaskan skenario konkret sebelum dan sesudah perbaikan. (b) `adjustStock` rawan lost update kalau dua request datang bersamaan — perbaiki dengan `@Version` di entity `Stock` plus `ETag`/`If-Match` di endpoint, tunjukkan kode lengkapnya termasuk response saat konflik terjadi.

6. **Dokumentasi OpenAPI.** Tulis spesifikasi OpenAPI (anotasi springdoc di atas kode hasil desain ulang Anda, atau YAML mentah — pilih salah satu dan konsisten) untuk **seluruh** endpoint hasil Deliverable 2–5: skema request/response tiap resource, `ApiError` sebagai reusable component (`components/schemas`) dipakai ulang di semua response error, contoh request/response yang realistis (bukan placeholder `"string"`), dan semua kode status yang mungkin per endpoint (termasuk `409`/`412`/`428` untuk endpoint stock kalau relevan dari Deliverable 5).