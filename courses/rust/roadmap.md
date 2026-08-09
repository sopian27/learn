# Course Roadmap

## Course Information

* Course Name: Rust — dari 0 sampai Expert
* Category: Systems Programming / Backend & CLI Development
* Difficulty: Level 1 (belum pernah menulis satu baris Rust, konsep ownership benar-benar dari nol) → Level 4 (Expert, mampu membangun dan mengoperasikan web service async production-shaped dalam Rust, serta membaca dan memperbaiki error borrow checker tanpa panik)
* Estimated Duration: 11 modul, 3-6 lesson/modul (Modul Ownership sengaja lebih dalam), ~30-45 menit/lesson
* Prerequisites: Sudah bisa pemrograman umum dengan baik (backend Java/Spring Boot dari repo ini) — variabel, fungsi, control flow, OOP dasar tidak diajarkan dari nol, hanya "translate" ke sintaks Rust. Konsep yang benar-benar Rust-spesifik (ownership, borrowing, lifetimes, trait system) diasumsikan **nol** dan diajarkan dari dasar dengan porsi lebih besar karena ini adalah cara berpikir baru, bukan sekadar sintaks baru.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini adalah **course systems-programming/Rust pertama di repo** — tidak ada overlap dengan course lain yang sudah ada (cek `courses/backend-fundamentals`, `courses/api-engineering`, `courses/performance-engineering`: semua itu bahasa/stack-agnostic atau berbasis JVM, tidak menyentuh Rust sama sekali).

Scope course ini sengaja **dipersempit** agar achievable dan tidak melebar ke area yang tidak relevan dengan tujuan utama (backend developer freelance yang sudah kuat di Java/Spring Boot, menambah Rust sebagai skill kedua untuk kasus performance-critical/CLI tooling):

1. **Fokus: general-purpose + backend/CLI systems programming.** Course ini membawa peserta dari nol sampai bisa membangun CLI tool (Clap) dan web service async production-shaped (Axum + PostgreSQL via sqlx) — dua kasus pemakaian Rust yang paling relevan untuk backend developer.
2. **Eksplisit TIDAK termasuk (out of scope):**
   * **Embedded/`no_std` programming** (microcontroller, bare-metal) — domain terpisah dengan tooling dan mental model berbeda (tidak ada heap standar, tidak ada OS). Kalau nanti dibutuhkan, ini jadi course lanjutan sendiri.
   * **Game development** (Bevy, wgpu, ECS pattern) — domain terpisah dengan ekosistem sendiri, tidak relevan dengan tujuan backend/freelance.
   * **WebAssembly (Wasm) target** — disinggung sekilas sebagai "kemungkinan lain" di modul penutup, tidak dibahas mendalam.
   * **Unsafe Rust mendalam** (FFI, custom allocator, membangun `unsafe` abstractions) — disinggung secukupnya agar tahu itu ada dan kapan dihindari, bukan tujuan course level Expert di sini. Level "Expert" pada course ini berarti *expert pada safe Rust untuk backend/CLI*, bukan expert compiler/unsafe internals.
3. **Bridging ke Java/Spring Boot secara sengaja.** Karena background utama peserta adalah JVM (Java/Spring Boot, sudah GC, sudah OOP interface-based), setiap konsep besar Rust (ownership vs GC, `cargo` vs Maven/Gradle, `trait` vs `interface`, `Result`/`Option` vs checked exception/`Optional`) diberi catatan perbandingan singkat — bukan untuk bilang salah satu lebih baik, tapi supaya intuisi lama tidak menyesatkan saat belajar model mental baru.
4. **Modul PostgreSQL (Modul 10) sengaja reuse stack yang sama dengan Spring Boot** (PostgreSQL, konsep migration) supaya peserta bisa membandingkan langsung "begini rasanya query database tanpa JPA/Hibernate, dengan compile-time checked SQL (`sqlx`)" — bukan belajar database dari nol.

Referensi riset yang dipakai menyusun roadmap ini:

* [The Rust Programming Language (buku resmi)](https://doc.rust-lang.org/book/) — sumber utama & kanonik. Struktur modul course ini mengikuti urutan konsep buku ini (Getting Started → Common Concepts → Ownership → Structs/Enums → Error Handling → Generics/Traits/Lifetimes → Collections/Iterators → Fearless Concurrency → Async), dengan Chapter 4 (Understanding Ownership) dan Chapter 10 (Generics, Traits, and Lifetimes) dipakai sebagai kerangka Modul 3 dan Modul 6.
* [Rust by Example](https://doc.rust-lang.org/rust-by-example/) — dipakai sebagai sumber latihan kode singkat per konsep, saling melengkapi buku utama yang lebih naratif.
* [Rustlings (official interactive exercises)](https://github.com/rust-lang/rustlings) — urutan exercise resminya (`01_variables` → `06_move_semantics` → `07_structs` → `08_enums` → `12_options` → `13_error_handling` → `14_generics` → `15_traits` → `16_lifetimes` → `18_iterators` → `19_smart_pointers` → `20_threads`) dipakai memvalidasi urutan & cakupan modul course ini, dan direkomendasikan sebagai hands-on drill di tiap modul terkait.
* [roadmap.sh — Rust Roadmap](https://roadmap.sh/rust) — dipakai sebagai pembanding cakupan topik level tinggi (memastikan tidak ada topik penting backend-relevant yang terlewat).
* [Tokio official tutorial](https://tokio.rs/tokio/tutorial) — kerangka Modul 9 (Async Rust): Setup → Spawning → Shared State → Channels → I/O → Select → Streams → Graceful Shutdown, dipakai sebagai urutan resmi belajar async runtime paling dominan di ekosistem Rust.
* Dokumentasi resmi [Axum](https://docs.rs/axum), [sqlx](https://docs.rs/sqlx), dan [Clap](https://docs.rs/clap) — dipakai sebagai kerangka Modul 10 (web service) dan Modul 9 (CLI tool), plus dokumentasi resmi [Clippy](https://doc.rust-lang.org/clippy/) dan `rustfmt` untuk Modul 11 (tooling).
* PostgreSQL — sudah menjadi trusted source standar repo ini (dipakai bersama `sqlx` di Modul 10, reuse dari stack Spring Boot).

---

# Learning Objectives

* [ ] Memahami *kenapa* Rust ada: masalah memory safety & data race yang diselesaikan tanpa garbage collector, dan trade-off-nya dibanding JVM
* [ ] Mahir toolchain Rust: `rustup`, `cargo` (build, run, test, add dependency), membaca `Cargo.toml`/`Cargo.lock`
* [ ] Menguasai ownership, borrowing, dan lifetimes cukup dalam untuk membaca dan memperbaiki error borrow checker secara mandiri (bukan asal `.clone()` semua)
* [ ] Mahir struct, enum, dan pattern matching (`match`, `if let`) sebagai cara idiomatic Rust memodelkan data dan control flow
* [ ] Mampu menangani error secara idiomatic dengan `Result`/`Option`, operator `?`, dan library `thiserror`/`anyhow`
* [ ] Memahami trait system dan generics cukup dalam untuk membedakan kapan pakai generic (`impl Trait`/monomorphization) vs trait object (`dyn Trait`)
* [ ] Mahir collections standar (`Vec`, `HashMap`, `String`) dan iterator adapters sebagai gaya pemrograman idiomatic Rust
* [ ] Mampu menulis kode concurrent yang "fearless" (thread, channel, `Arc`/`Mutex`) yang compile-time terjamin bebas data race
* [ ] Mampu menulis aplikasi async dengan `tokio` (task, channel async, `select!`, graceful shutdown)
* [ ] Mampu membangun CLI tool yang proper dengan `clap` (argument parsing, subcommand, help text otomatis)
* [ ] Mampu membangun web service async production-shaped dengan `axum` + PostgreSQL (`sqlx`, compile-time checked query, migration)
* [ ] Mahir tooling produksi: `cargo test`, `clippy`, `rustfmt`, dan dasar dokumentasi (`cargo doc`)
* [ ] Menyelesaikan proyek portofolio: web service async Rust production-shaped, live dengan test, plus tulisan perbandingan ownership model vs JVM garbage collector

---

# Course Modules

## Module 1 — Fondasi Rust & Toolchain

Description: Sebelum masuk konsep bahasa, kenalan dulu dengan toolchain — sama seperti sesi pertama belajar Java dulu adalah kenalan JDK & Maven, bukan langsung generics.

Lessons:

* [ ] 1.1 Instalasi `rustup`, `cargo`, `rustc`; toolchain channel (stable/beta/nightly) dan edition (2021/2024)
* [ ] 1.2 `cargo new`, struktur project (`src/main.rs`, `Cargo.toml`), `cargo build`/`run`/`check`; bridging: `Cargo.toml` vs `pom.xml`/`build.gradle`, `Cargo.lock` vs `mvn dependency:tree`/lockfile
* [ ] 1.3 Dasar sintaks: variabel & mutability (`let` vs `let mut`), tipe data skalar & compound (integer, float, bool, char, tuple, array), konstanta
* [ ] 1.4 Fungsi, control flow (`if`/`else` sebagai expression, `loop`/`while`/`for`), komentar & dokumentasi inline (`///`)
* [ ] 1.5 crates.io dan menambah dependency eksternal (`cargo add`); konsep crate vs module (pengantar, dibahas lebih dalam nanti)

Mini Project: Program CLI kecil (mis. konversi suhu atau generator bilangan Fibonacci) yang menunjukkan variabel, fungsi, control flow, dan minimal satu dependency eksternal dari crates.io.

Status: Not Started

---

## Module 2 — Ownership, Borrowing & Lifetimes

Description: **Modul terpenting di course ini.** Ini adalah pembeda utama Rust dari hampir semua bahasa lain yang pernah dipakai (termasuk Java) — cara compiler menjamin memory safety tanpa garbage collector. Modul ini sengaja punya lebih banyak lesson dan porsi latihan dibanding modul lain karena ini bukan sekadar sintaks baru, tapi model mental baru yang perlu waktu untuk "klik".

Lessons:

* [ ] 2.1 Kenapa ownership ada: masalah use-after-free, double-free, dan data race yang biasanya dicegah runtime (GC di JVM) atau tidak dicegah sama sekali (C/C++) — Rust mencegahnya di **compile time**. Trade-off: kurva belajar lebih curam vs tidak ada GC pause, prediktabilitas performa
* [ ] 2.2 Tiga aturan ownership: satu value punya tepat satu owner, value di-drop saat owner keluar scope, assignment/passing value = **move** (bukan copy) untuk tipe heap-allocated (`String`, `Vec`) — kenapa `let s2 = s1;` membuat `s1` tidak valid lagi
* [ ] 2.3 `Clone` vs `Copy`: kapan Rust diam-diam copy (tipe stack-only kecil seperti integer) vs kapan harus eksplisit `.clone()`, dan kenapa `.clone()` bukan solusi gratis (biaya runtime yang eksplisit terlihat di kode, beda dengan Java yang assignment reference-nya "gratis" tapi objeknya di-GC nanti)
* [ ] 2.4 Borrowing: referensi `&T` (immutable borrow) dan `&mut T` (mutable borrow); aturan borrow checker — boleh banyak immutable borrow ATAU tepat satu mutable borrow dalam satu waktu, tidak pernah bersamaan. Ini yang membuat data race mustahil lolos compile
* [ ] 2.5 Slices: `&str` vs `String` (borrowed view vs owned data), slice pada array/`Vec` (`&[T]`) — kenapa fungsi idiomatic menerima `&str`/`&[T]` bukan `String`/`Vec<T>`
* [ ] 2.6 Lifetimes: apa itu dangling reference dan kenapa borrow checker menolaknya; anotasi lifetime (`'a`) di fungsi & struct; lifetime elision rules (kenapa biasanya tidak perlu ditulis manual)
* [ ] 2.7 Klinik membaca error borrow checker: pola error umum (`E0502` cannot borrow as mutable, `E0382` use of moved value, `E0106` missing lifetime specifier), strategi memperbaiki secara struktural (restructure kode, bukan `.clone()` refleks di semua tempat)

Mini Project: Selesaikan set exercise `06_move_semantics` dan `07_structs` dari Rustlings, plus tulis program kecil (mis. text processor yang meminjam data dari beberapa fungsi) yang sengaja dibuat gagal compile 2-3 kali dulu, lalu diperbaiki — dan tulis catatan reflektif: "kenapa compiler menolak versi pertama, apa yang diajarkan errornya."

Status: Not Started

---

## Module 3 — Structs, Enums & Pattern Matching

Description: Cara idiomatic Rust memodelkan data — enum di Rust jauh lebih powerful dari enum Java (bisa membawa data berbeda per variant), dan `match` menggantikan banyak pola `if/else`/`instanceof` chain yang biasa dipakai di Java.

Lessons:

* [ ] 3.1 Struct: definisi, instansiasi, method (`impl` block), associated function (setara static factory method Java)
* [ ] 3.2 Enum dengan data: variant yang membawa tipe berbeda-beda (bukan sekadar named constant seperti Java enum), use case: memodelkan state machine atau union type
* [ ] 3.3 `Option<T>` sebagai pengganti null — bridging eksplisit ke `Optional<T>` Java: bedanya, di Rust *tidak ada* null sama sekali di safe code, jadi `Option` bukan opsional dipakai, tapi satu-satunya cara merepresentasikan "mungkin tidak ada value"
* [ ] 3.4 `match` exhaustive pattern matching (compiler memaksa semua case ditangani), `if let`/`while let` untuk kasus satu pattern saja
* [ ] 3.5 Destructuring lanjutan: pattern matching pada struct, tuple, nested enum, match guard (`if` di dalam arm)

Mini Project: Modelkan domain kecil (mis. state order e-commerce: `Pending`, `Paid { amount, method }`, `Shipped { tracking_id }`, `Cancelled { reason }`) sebagai enum dengan data, lalu tulis fungsi yang exhaustively handle semua state via `match`.

Status: Not Started

---

## Module 4 — Error Handling

Description: Rust tidak punya exception seperti Java — semua error yang bisa gagal dinyatakan eksplisit di return type. Ini memaksa disiplin yang di Java biasanya "opsional" (checked exception sering di-swallow atau di-wrap `RuntimeException`).

Lessons:

* [ ] 4.1 `panic!` vs `Result<T, E>`: kapan program harus crash (bug/invariant rusak) vs kapan error harus di-handle sebagai nilai balik biasa
* [ ] 4.2 `Result<T, E>` mendalam: `.unwrap()`/`.expect()` (kapan boleh dipakai — prototyping/test, bukan production), kombinator (`.map()`, `.and_then()`, `.unwrap_or_else()`)
* [ ] 4.3 Operator `?` untuk propagasi error singkat — bridging ke `throws` Java: bedanya `?` eksplisit di setiap call site, tidak ada "unchecked exception" yang diam-diam menjalar
* [ ] 4.4 Custom error type dengan `thiserror` (derive macro untuk error enum yang rapi) vs `anyhow` (error type generik untuk aplikasi, bukan library) — kapan pakai yang mana
* [ ] 4.5 Error handling di boundary aplikasi: konversi error antar layer, `From`/`Into` untuk error conversion otomatis lewat `?`

Mini Project: Refactor mini project Modul 1 (konversi suhu/Fibonacci) agar semua input tidak valid ditangani via `Result` + custom error type dengan `thiserror`, bukan `panic!`/`unwrap()`.

Status: Not Started

---

## Module 5 — Traits & Generics

Description: Trait adalah cara Rust mencapai polimorfisme — mirip interface Java, tapi dengan mekanisme dispatch yang berbeda (dan implikasi performa yang perlu dipahami).

Lessons:

* [ ] 5.1 Generics: fungsi & struct generic, trait bound (`<T: Display>`), `where` clause untuk bound yang kompleks
* [ ] 5.2 Trait: definisi, implementasi untuk tipe sendiri, default method — bridging ke `interface` Java (termasuk default method Java 8+)
* [ ] 5.3 `impl Trait` (return position, argument position) vs `dyn Trait` (trait object): static dispatch (monomorphization, zero-cost tapi binary lebih besar) vs dynamic dispatch (vtable, mirip interface dispatch Java tapi eksplisit dipilih)
* [ ] 5.4 Trait umum dari standard library yang wajib dikenal: `Display`, `Debug`, `Clone`, `PartialEq`, `Default`, `From`/`Into`, dan `derive` macro untuk auto-implement
* [ ] 5.5 Operator overloading via trait (`Add`, `Sub`, dll) secukupnya untuk paham mekanismenya

Mini Project: Buat trait `Shape` dengan method `area()`/`perimeter()`, implementasikan untuk 2-3 struct (`Circle`, `Rectangle`, `Triangle`), lalu tulis fungsi yang menerima `&dyn Shape` dan fungsi generic yang menerima `<T: Shape>` — bandingkan kapan masing-masing dipakai.

Status: Not Started

---

## Module 6 — Collections & Iterators

Description: Gaya idiomatic Rust untuk mengolah data — iterator adapter (`.map()`, `.filter()`, `.collect()`) terasa familiar dari Java Stream API, tapi lazy evaluation dan zero-cost abstraction-nya bekerja beda di baliknya.

Lessons:

* [ ] 6.1 `Vec<T>`: operasi dasar, kapan pakai `Vec` vs array/slice
* [ ] 6.2 `HashMap<K, V>` dan `HashSet<T>`: operasi dasar, entry API untuk update-or-insert
* [ ] 6.3 `String` mendalam: UTF-8 internal representation, kenapa indexing `String` by index tidak sesederhana Java (`String` Rust bukan array `char`), method umum
* [ ] 6.4 Closures: `Fn`/`FnMut`/`FnOnce`, capture by reference vs by move (`move` keyword) — bridging ke lambda Java dan effectively-final capture
* [ ] 6.5 Iterator trait & adapter chain (`.map()`, `.filter()`, `.fold()`, `.collect()`), lazy evaluation — bridging eksplisit ke Java Stream API, termasuk beda performa (Rust iterator sering zero-cost, ter-compile jadi loop biasa)

Mini Project: Selesaikan exercise `18_iterators` dari Rustlings, lalu tulis program pengolah data nyata (mis. parse CSV kecil, agregasi, filter) yang idiomatic pakai iterator chain, bukan `for` loop imperatif manual.

Status: Not Started

---

## Module 7 — Fearless Concurrency

Description: "Fearless concurrency" adalah slogan resmi Rust — borrow checker yang sama yang mencegah bug ownership single-thread juga mencegah data race multi-thread, di compile time.

Lessons:

* [ ] 7.1 Thread dasar: `std::thread::spawn`, `join`, memindahkan data ke thread dengan `move` closure
* [ ] 7.2 `Send` dan `Sync`: dua marker trait yang jadi dasar jaminan thread-safety compiler Rust — kenapa tipe tertentu (mis. `Rc<T>`) sengaja tidak `Send`
* [ ] 7.3 Shared state: `Arc<T>` (atomic reference counting, mirip shared ownership tapi thread-safe) + `Mutex<T>`/`RwLock<T>` — bridging ke `synchronized`/`ReentrantLock` Java, bedanya Mutex Rust "membungkus" data-nya sehingga lupa lock jadi *compile error*, bukan bug runtime
* [ ] 7.4 Channel: `std::sync::mpsc` untuk message passing antar thread — pola "share memory by communicating" ala Go, tersedia juga di Rust
* [ ] 7.5 Deadlock, race condition yang *masih* mungkin terjadi (borrow checker mencegah data race, bukan semua bug concurrency) — studi kasus singkat

Mini Project: Selesaikan exercise `20_threads` dari Rustlings, lalu bangun program multi-thread kecil (mis. word counter paralel atas beberapa file) yang membagi kerja ke beberapa thread dan menggabungkan hasil lewat channel atau `Arc<Mutex<_>>`.

Status: Not Started

---

## Module 8 — Async Rust dengan Tokio

Description: Async Rust adalah model concurrency berbeda dari OS thread — cocok untuk I/O-bound workload seperti web service. Modul ini mengikuti urutan tutorial resmi Tokio.

Lessons:

* [ ] 8.1 Kenapa async: I/O-bound vs CPU-bound, cost OS thread vs task async yang jauh lebih ringan; `async fn`, `.await`, dan konsep future sebagai "computation belum selesai" — bridging ke `CompletableFuture`/reactive (Project Reactor) Java, bedanya future Rust *lazy* (tidak jalan sampai di-`.await`/poll)
* [ ] 8.2 Setup Tokio runtime (`#[tokio::main]`), `tokio::spawn` untuk task async — beda task async vs OS thread (banyak task bisa jalan di sedikit thread)
* [ ] 8.3 Shared state di dunia async: `tokio::sync::Mutex` vs `std::sync::Mutex` (kapan masing-masing dipakai), `tokio::sync::mpsc`/`oneshot` channel
* [ ] 8.4 I/O async: file & network dasar dengan `tokio::fs`/`tokio::net`
* [ ] 8.5 `select!` untuk menunggu beberapa future sekaligus, dan pola graceful shutdown (menangani sinyal Ctrl+C, menyelesaikan task yang sedang berjalan sebelum exit)

Mini Project: Program async kecil yang melakukan beberapa HTTP request paralel (mis. dengan `reqwest`) ke beberapa endpoint sekaligus, menampilkan hasil begitu masing-masing selesai, dengan graceful shutdown saat menerima Ctrl+C.

Status: Not Started

---

## Module 9 — Membangun CLI Tool dengan Clap

Description: Rust adalah pilihan populer untuk CLI tool production-grade (lihat `ripgrep`, `fd`, `bat`) — modul ini menyatukan Modul 1-6 jadi tool nyata yang bisa dipakai orang lain.

Lessons:

* [ ] 9.1 `clap`: derive API untuk argument parsing, subcommand, flag, positional argument
* [ ] 9.2 Help text otomatis, validasi argument, exit code yang benar (konvensi Unix: 0 sukses, non-zero gagal)
* [ ] 9.3 Struktur project CLI yang scalable: memisahkan `main.rs` (entry point tipis) dari logic (`lib.rs`) agar testable
* [ ] 9.4 Packaging & distribusi dasar: `cargo install --path .`, cross-compilation secukupnya untuk paham konsepnya

Mini Project: CLI tool nyata dan berguna (mis. clone sederhana dari `grep`/`wc`, atau tool khusus kebutuhan sendiri seperti pembaca `progress/roadmap.md` di repo ini) dengan subcommand, help text, dan exit code yang benar.

Status: Not Started

---

## Module 10 — Membangun Web Service dengan Axum + PostgreSQL

Description: Puncak sintesis semua modul sebelumnya — web service async production-shaped, memakai stack PostgreSQL yang sama dengan yang sudah dikenal dari Spring Boot, tapi dengan pendekatan berbeda: compile-time checked SQL, bukan ORM/ORM-like JPA.

Lessons:

* [ ] 10.1 `axum` dasar: router, handler, extractor (`Path`, `Query`, `Json`), response — bridging ke `@RestController`/`@GetMapping` Spring Boot
* [ ] 10.2 State sharing antar handler (`State<T>`, biasanya membungkus connection pool) — bridging ke dependency injection Spring, bedanya di Axum eksplisit lewat parameter, bukan annotation magic
* [ ] 10.3 `sqlx` + PostgreSQL: connection pool, compile-time checked query (`query!`/`query_as!` — SQL divalidasi saat compile terhadap skema database asli), migration dengan `sqlx-cli`
* [ ] 10.4 Middleware: logging request, error handling terpusat (mapping error aplikasi ke HTTP status code yang tepat), CORS secukupnya
* [ ] 10.5 Testing endpoint: integration test dengan test database (pola serupa Testcontainers yang sudah dikenal dari Spring Boot, versi Rust)

Mini Project: Endpoint CRUD sederhana (mis. todo list atau bookmark manager) tersambung ke PostgreSQL nyata, dengan minimal 1 integration test yang benar-benar hit database test.

Status: Not Started

---

## Module 11 — Testing, Tooling & Production Readiness

Description: Modul penutup sebelum capstone — kebiasaan yang membedakan kode Rust "jalan" dari kode Rust production-ready.

Lessons:

* [ ] 11.1 `cargo test`: unit test (`#[test]` di module yang sama), integration test (folder `tests/`), test organization
* [ ] 11.2 `clippy`: linter resmi Rust, cara membaca dan menindaklanjuti saran (`cargo clippy --fix` secukupnya, tidak selalu auto-apply buta)
* [ ] 11.3 `rustfmt`: format kode konsisten otomatis, konfigurasi `rustfmt.toml` dasar
* [ ] 11.4 Dokumentasi: doc comment (`///`), `cargo doc --open`, doctest (contoh kode di komentar yang benar-benar dieksekusi sebagai test)
* [ ] 11.5 Sekilas `unsafe` Rust: apa itu, kapan benar-benar dibutuhkan (FFI, optimasi ekstrem), kenapa dihindari di kode aplikasi biasa — cukup untuk mengenali, bukan mendalami (di luar scope course ini)

Mini Project: Ambil CLI tool dari Modul 9 atau web service dari Modul 10, tambahkan unit + integration test yang memadai, jalankan `clippy` sampai bersih, format dengan `rustfmt`, dan tulis doc comment untuk public API-nya.

Status: Not Started

---

# Capstone Project

Description: **Async Web Service Production-Shaped dengan Axum + PostgreSQL** — bangun satu web service Rust nyata (skala kecil tapi lengkap, mis. URL shortener, bookmark manager, atau task tracker — bebas asal domain nyata, bukan skeleton kosong) yang menyatukan seluruh course: ownership/borrowing dipakai sadar (bukan `.clone()` refleks), error handling idiomatic dengan custom error type, trait untuk abstraksi (mis. repository trait agar bisa di-mock saat test), async handler dengan `axum`, persistence ke PostgreSQL lewat `sqlx` dengan migration, plus test suite dan tooling bersih (`clippy`, `rustfmt`). Ditutup dengan tulisan reflektif singkat membandingkan pengalaman ownership model Rust vs garbage-collected JVM yang sudah dikuasai dari Spring Boot.

Acceptance Criteria:

* Service berjalan (`cargo run`), endpoint CRUD minimal (create/read/update/delete satu resource) berfungsi dan bisa dites manual (mis. lewat `curl`/Postman)
* Data persisten ke PostgreSQL nyata lewat `sqlx`, dengan migration yang bisa dijalankan ulang dari kosong (`sqlx migrate run`)
* Error handling idiomatic: tidak ada `.unwrap()`/`panic!` di jalur request handling normal; error aplikasi punya custom error type yang di-mapping ke HTTP status code yang tepat
* Minimal 1 trait dipakai untuk abstraksi nyata (bukan sekadar formalitas) — mis. `Repository` trait yang punya implementasi PostgreSQL nyata dan implementasi in-memory untuk test
* Test suite jalan (`cargo test`) mencakup minimal unit test untuk logic inti dan integration test untuk minimal satu endpoint yang benar-benar hit database test
* `cargo clippy` bersih tanpa warning yang diabaikan tanpa alasan, kode sudah `cargo fmt`
* Tulisan reflektif (`README.md` atau file terpisah) yang membandingkan ownership/borrow checker Rust vs garbage collector JVM: pengalaman nyata menulis kode, jenis bug yang dicegah masing-masing, dan trade-off yang dirasakan langsung (bukan teori dari buku)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, termasuk dokumentasi arsitektur singkat (struktur module, alur request, skema database)

Status: Not Started

---

# Overall Progress

Module Completion: 0/11

Overall Completion: 0%

Current Module: Roadmap draft — menunggu approval user, belum dimulai (mengikuti kebijakan hemat-token repo, tidak otomatis jadi course aktif)

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Instalasi `rustup`, `cargo`, `rustc`; toolchain channel (stable/beta/nightly) dan edition (2021/2024)
