# Course Roadmap

## Course Information

* Course Name: Functional Programming — dari Imperative ke Functional Thinking (Java-first)
* Category: Software Engineering Fundamentals — paradigma pemrograman, melengkapi (bukan menggantikan) `courses/design-patterns` (OOP) dan idiom Stream/lambda yang disinggung di `courses/backend-fundamentals` Module 1
* Difficulty: Level 1 (terbiasa gaya imperative, pernah pakai Stream API tapi belum paham kenapa/kapan) → Level 3 (bisa mendesain kode Java bergaya fungsional secara sadar — immutability, komposisi fungsi, error handling tanpa exception untuk kasus expected — dan tahu kapan **tidak** memaksakan gaya ini)
* Estimated Duration: 7 modul, 3-4 lesson/modul, ~30-40 menit/lesson. Perkiraan ≈ 2-3 bulan pace sedang.
* Prerequisites: Nyaman dengan Java dasar-menengah (lambda, `Stream` sederhana pernah dipakai). Tidak perlu pengalaman bahasa fungsional murni (Haskell/Scala) sebelumnya.

---

# Catatan Scope (draft — menunggu approval & kalibrasi user)

Draft ini dibuat atas permintaan eksplisit user (2026-08-07) untuk mengisi gap "Functional Programming" dari daftar besar topik yang diminta. **Belum ada sesi kalibrasi** — asumsi di bawah ini perlu dikonfirmasi/dikoreksi:

1. **Asumsi bahasa: Java-first**, karena seluruh stack repo ini Java/Spring Boot dan tidak ada permintaan eksplisit bahasa lain. Course ini mengajarkan *cara berpikir* fungsional lewat fitur Java (Stream, `Optional`, functional interface, `record`), bukan Java menjadi bahasa fungsional murni (Java tetap multi-paradigma).
2. **Module 7** memberi "cicipan" bahasa fungsional lain (kemungkinan Kotlin, karena interop JVM paling relevan untuk portofolio backend) murni untuk perspektif — **bukan** course Kotlin penuh. Perlu dikonfirmasi apakah user ingin ini diperdalam jadi modul terpisah, atau malah dihapus kalau dianggap di luar scope "Java-first".
3. **Tidak mengulang** idiom *Effective Java* soal Stream/lambda yang sudah disinggung di `courses/backend-fundamentals` Module 1 — course ini pindah dari "tahu sintaks" ke "paham model mental" (pure function, immutability, komposisi, monad pattern).
4. **Tidak masuk ke teori kategori (category theory) secara formal** — Module 6 sengaja dibatasi "cukup untuk mengenali pola", bukan matematika penuh di balik monad/functor. Ini keputusan kalibrasi default; boleh diperdalam jika user memang mau.

Referensi riset (untuk disusun jadi roadmap final saat approval):

* *Functional Programming in Java* (Venkat Subramaniam) — fondasi utama, mengajarkan FP lewat idiom Java asli
* *Effective Java* (Joshua Bloch), Item 42-48 (lambda, stream, functional interface) — cross-ref, tidak diulang
* [Java Stream API official docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/package-summary.html)
* [Vavr library docs](https://www.vavr.io/) — referensi pola `Either`/`Try` untuk Module 5
* *Functional Programming in Scala* (Paul Chiusano & Rúnar Bjarnason) — referensi konsep (bukan wajib baca penuh) untuk Module 6, dasar pemikiran monad/functor yang diadaptasi ke Java
* [Kotlin official docs](https://kotlinlang.org/docs/home.html) — referensi ringan Module 7

---

# Learning Objectives

* [ ] Menjelaskan pure function, referential transparency, dan side effect — serta kelas bug yang dihindari dengan menguranginya
* [ ] Menerapkan immutability secara sadar di Java (record, immutable collection) dan memahami trade-off-nya vs mutasi
* [ ] Menulis dan mengomposisikan higher-order function dengan functional interface Java tanpa jadi "callback soup"
* [ ] Menguasai Stream API secara mendalam: laziness, custom collector, jebakan parallel stream
* [ ] Menerapkan `Optional` dengan benar dan pola error handling ala Either/Result untuk expected failure
* [ ] Mengenali pola map/flatMap (monad/functor) yang berulang di `Optional`/`Stream`/`CompletableFuture`
* [ ] Merefactor kode imperative bergaya side-effect-heavy jadi gaya fungsional yang lebih aman & mudah diuji

---

# Course Modules

## Module 1 — Fondasi Berpikir Fungsional

Description: Mengubah kebiasaan berpikir imperative jadi fungsional — dasar untuk semua modul berikutnya.

Lessons:

* [ ] 1.1 Pure function & referential transparency: definisi konkret, bukan slogan
* [ ] 1.2 Side effect: apa yang dianggap side effect (I/O, mutasi state di luar scope, exception) dan kenapa ini sumber bug tersembunyi
* [ ] 1.3 Kelas bug yang dihindari FP: shared mutable state, aliasing bug, race condition yang lahir dari mutasi tersembunyi
* [ ] 1.4 FP bukan "lebih baik selalu" — kapan gaya imperative tetap lebih jelas/performa lebih baik (trade-off jujur, bukan dogma)

Mini Project: Identifikasi 3 method di kode Java yang sudah ada (course lain/kerjaan) yang punya side effect tersembunyi, jelaskan bug potensial yang bisa muncul.

Status: Not Started

---

## Module 2 — Immutability & Struktur Data Persisten

Description: Kenapa "jangan mutasi" bukan sekadar gaya penulisan, tapi strategi menghindari seluruh kelas bug concurrency.

Lessons:

* [ ] 2.1 Java `record`: immutability by construction, kapan tepat dipakai vs class biasa
* [ ] 2.2 Immutable collection (`List.of()`, `Collections.unmodifiableList`) vs defensive copy
* [ ] 2.3 Konsep structural sharing (persistent data structure) — kenapa immutable tidak selalu berarti "copy semuanya" secara performa
* [ ] 2.4 Trade-off immutability vs performa: kapan mutasi terkontrol tetap lebih masuk akal (hot path performance-critical)

Mini Project: Refactor 1 domain model mutable (dengan setter di mana-mana) jadi immutable memakai `record`, identifikasi bug yang tercegah secara struktural setelah perubahan.

Status: Not Started

---

## Module 3 — Higher-Order Function & Komposisi Fungsi

Description: Memperlakukan fungsi sebagai nilai — dasar untuk memahami Stream API dan menghindari duplikasi logic lewat komposisi, bukan copy-paste.

Lessons:

* [ ] 3.1 Functional interface Java: `Function`, `Predicate`, `Supplier`, `Consumer` — kapan masing-masing dipakai
* [ ] 3.2 Method reference vs lambda — kapan masing-masing lebih terbaca
* [ ] 3.3 Komposisi fungsi: `andThen`, `compose`, membangun pipeline logic dari fungsi-fungsi kecil
* [ ] 3.4 Menghindari "callback soup": kapan komposisi fungsi membantu, kapan justru mengaburkan alur kontrol

Mini Project: Bangun 1 pipeline validasi (misal: validasi form multi-field) memakai komposisi `Predicate`, bandingkan keterbacaan dengan versi if-else bertumpuk.

Status: Not Started

---

## Module 4 — Java Stream API Mendalam

Description: Naik dari "tahu cara pakai `.stream().filter().map()`" ke paham model eksekusinya secara penuh.

Lessons:

* [ ] 4.1 Intermediate vs terminal operation, dan laziness: kenapa stream tidak jalan sampai ada terminal operation
* [ ] 4.2 Custom `Collector`: membangun agregasi kompleks di luar `toList()`/`groupingBy()` bawaan
* [ ] 4.3 Kesalahan umum: side effect di dalam `.map()`/`.forEach()`, stream yang dipakai ulang (sudah consumed)
* [ ] 4.4 Parallel stream: kapan benar-benar menguntungkan, kapan justru lebih lambat/berbahaya (shared mutable state di dalam parallel stream)

Mini Project: Tulis 1 custom `Collector` untuk kebutuhan agregasi nyata (misal: mengelompokkan & meringkas data transaksi), bandingkan dengan versi imperative for-loop dari sisi keterbacaan & correctness.

Status: Not Started

---

## Module 5 — Optional & Error Handling ala Functional

Description: Menangani "tidak ada nilai" dan "gagal" tanpa `null` check bertumpuk atau exception untuk kasus yang sebenarnya diharapkan terjadi.

Lessons:

* [ ] 5.1 `Optional` yang benar vs yang disalahgunakan (anti-pattern: `Optional` sebagai field, `Optional.get()` tanpa cek)
* [ ] 5.2 Kapan exception tetap tepat (kondisi benar-benar luar biasa) vs kapan "expected failure" (validasi gagal, not found) lebih baik direpresentasikan sebagai nilai
* [ ] 5.3 Pola Either/Result (referensi: Vavr `Either`) untuk error handling tanpa exception — cara kerja & kapan lebih baik dari `Optional`
* [ ] 5.4 Railway-oriented programming: merangkai operasi yang bisa gagal tanpa if-else bertingkat

Mini Project: Refactor 1 method yang melempar exception untuk kondisi "expected" (misal: `UserNotFoundException` untuk validasi biasa) menjadi pola Either/Result, bandingkan alur pemanggilnya.

Status: Not Started

---

## Module 6 — Monad & Functor: Secukupnya untuk Dikenali

Description: Pola yang sudah dipakai sejak Module 4-5 tanpa disadari punya nama — dikenali sebagai satu pola berulang, bukan teori matematika penuh.

Lessons:

* [ ] 6.1 Pola `map`/`flatMap` yang muncul berulang di `Optional`, `Stream`, `CompletableFuture` — kenapa ini bukan kebetulan
* [ ] 6.2 Functor secukupnya: "wadah yang bisa di-`map`" — intuisi, bukan definisi kategori formal
* [ ] 6.3 Monad secukupnya: kenapa `flatMap` dibutuhkan saat fungsi yang di-map sendiri mengembalikan "wadah" (menghindari `Optional<Optional<T>>`)
* [ ] 6.4 Kenapa mengenali pola ini berguna: begitu Anda mengenalinya, API baru yang punya `map`/`flatMap` (reactive `Mono`/`Flux`, dll.) langsung terasa familiar

Mini Project: Identifikasi 3 tempat di kode Java standard library (`Optional`, `Stream`, `CompletableFuture`) yang memakai pola map/flatMap yang sama, jelaskan kesamaan strukturalnya dalam kata-kata sendiri.

Status: Not Started

---

## Module 7 — FP di Luar Java: Cicipan untuk Perspektif

Description: Melihat sejenak bagaimana bahasa yang dirancang fungsional sejak awal terasa berbeda — untuk mempertajam pemahaman, lalu kembali menerapkannya di Java.

Lessons:

* [ ] 7.1 Latihan singkat di Kotlin (interop JVM, sintaks lebih ringkas untuk konsep Module 1-6 yang sama)
* [ ] 7.2 Perbandingan sadar: apa yang "dipaksakan" di Java (verbosity, boilerplate functional interface) yang di bahasa FP-native jadi bawaan bahasa
* [ ] 7.3 Kembali ke Java: menerapkan insight dari latihan Kotlin ke gaya penulisan Java sehari-hari

Mini Project: Tulis ulang 1 solusi dari Module 5 atau 6 di Kotlin, bandingkan keterbacaan & verbosity dengan versi Java aslinya secara tertulis.

Status: Not Started

---

# Capstone Project

Description: **Refactor Imperative ke Functional** — ambil 1 service Java yang ditulis bergaya imperative & side-effect-heavy (mutable state di mana-mana, exception untuk expected failure, nested if-else), refactor penuh jadi gaya fungsional: domain model immutable, error handling ala Either/Result, pipeline data dengan Stream, komposisi fungsi untuk logic yang berulang.

Acceptance Criteria:

* Domain model immutable (memakai `record` di mana sesuai)
* Tidak ada exception dipakai untuk kondisi "expected" — memakai Either/Result/Optional yang tepat
* Minimal 1 pipeline data ditulis dengan Stream API yang benar (bukan sekadar mengganti for-loop 1:1 tanpa alasan)
* Tertulis perbandingan before/after: bug class apa yang tercegah secara struktural, trade-off apa yang diterima (misal: sedikit lebih verbose untuk sebagian pembaca)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/7

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Pure function & referential transparency
