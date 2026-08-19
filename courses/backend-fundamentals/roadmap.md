# Course Roadmap

## Course Information

* Course Name: Backend Engineering Fundamentals Rebuild — dari "Sudah Kerja Tapi Rapuh" sampai Production-Grade Expert
* Category: Backend Engineering Fundamentals (Java/Spring Boot) — fondasi untuk Master Goal "World-Class Backend Developer" (`progress/roadmap.md`)
* Difficulty: Level 1 (sudah kerja profesional, tapi banyak konsep dipahami setengah-setengah) → Level 4 (production-grade backend engineer, siap dipercaya proyek freelance internasional skala besar)
* Estimated Duration: 15 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`). Dengan pace sedang (5-10 jam/minggu) ≈ 5-7 bulan.
* Prerequisites: Sudah kerja profesional sebagai Java/Spring Boot developer — paham cara bikin REST API dasar, CRUD dengan JPA, git basic. Course ini **bukan** dari nol syntax Java — ini rebuild fondasi yang sudah ada supaya benar-benar solid sampai akar, bukan sekadar "tahu cara pakai".

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda (2026-08-06):

1. **Kedalaman**: Anda memilih **"semua area terasa rapuh secara merata"** — bukan satu titik lemah spesifik. Karena itu course ini tidak menyempit ke satu topik, tapi rebuild menyeluruh: Java lanjut (concurrency, JVM), Spring Boot production-grade, dan database mendalam — tiga pilar yang Anda sebutkan eksplisit — plus pilar pendukung yang wajib menyertainya di dunia production (testing, security, resiliency, observability, performance), karena "production-grade" tidak lengkap tanpa lima itu.
2. **Pace**: **Sedang, 5-10 jam/minggu** — modul dibuat cukup padat (bukan santai ala `courses/kubernetes`), tapi tetap dipecah 3-5 lesson pendek per modul supaya bisa dicicil realistis di sela kerja.
3. **Posisi terhadap Master Goal**: Course ini adalah **fondasi #1** dari payung "World-Class Backend Developer — dipercaya freelance internasional" (`progress/roadmap.md`). Setiap modul di course lain yang sudah ada di repo ini (`design-patterns`, `aws-api-integration`, `code-review`, dll) **mengasumsikan** fondasi yang dibangun di sini. Setelah course ini selesai, jalur berikutnya adalah `courses/system-design` (belum dibuat) lalu track kesiapan freelance internasional (belum dibuat).
4. **Bukan** dari nol Java: tidak ada modul "pengenalan syntax Java" atau "apa itu REST API" — course langsung masuk ke lapisan mendalam dengan asumsi Anda sudah bisa membuat CRUD API biasa.
5. **Setiap modul** eksplisit menyambung ke satu atau lebih file `standards/*.md` di repo ini — course ini pada dasarnya adalah "cara mempraktikkan standards/ dengan benar dan paham *kenapa*-nya", bukan sekadar mengikuti checklist.
6. **Ditambahkan 2026-08-06** — Module 7 (Advanced SQL & Reporting Query Patterns): Anda melihat langsung di lapangan bahwa REST API reporting sering menaruh 80% business logic (agregasi, ranking, perbandingan) di satu query SQL kompleks (CTE, `GREATEST()`, window function, dll), dengan service layer Java yang tipis (cuma validasi & filter). Ini pola industri nyata yang belum tercakup oleh Module 6 (yang fokus ke indexing/skema/JPA, bukan menulis query analitik kompleks) — jadi ditambahkan sebagai modul terpisah tepat setelah Module 6, sebelum masuk ke Module 8 (Transaksi) yang butuh fondasi SQL yang sama kuatnya.

Referensi riset yang dipakai menyusun roadmap ini: *Effective Java* (Joshua Bloch) untuk idiom Java yang benar, *Java Concurrency in Practice* (Brian Goetz) untuk pilar concurrency, *Designing Data-Intensive Applications* (Martin Kleppmann) untuk pilar database/transaksi/caching, dokumentasi resmi [Spring Framework](https://docs.spring.io/spring-framework/reference/) & [Spring Boot](https://docs.spring.io/spring-boot/documentation.html), struktur jalur [roadmap.sh — Java](https://roadmap.sh/java) & [roadmap.sh — Backend](https://roadmap.sh/backend), [OWASP Top 10](https://owasp.org/www-project-top-ten/) untuk pilar security, *Google SRE Book* untuk pilar observability & resiliency, serta seluruh `standards/*.md` repo ini sebagai kontrak konvensi yang dipraktikkan langsung di tiap modul.

---

# Learning Objectives

* [ ] Menguasai Java lanjut: generics, collections internals, exception handling yang benar, idiom *Effective Java* — bukan cuma tahu cara pakai, tapi tahu kenapa idiom itu ada
* [ ] Benar-benar paham concurrency: thread-safety, executor service, `CompletableFuture`, virtual threads (Java 25/Project Loom), dan kelas bug yang ditimbulkan kalau salah
* [ ] Paham JVM secukupnya untuk debug production: memory model, garbage collection, class loading, cara membaca heap dump/thread dump saat insiden
* [ ] Paham Spring Core sampai ke mekanisme internal: bean lifecycle, dependency injection, AOP proxy — bukan cuma anotasi yang "kerja secara ajaib"
* [ ] Mendesain REST API production-grade sesuai `standards/API_STANDARDS.md` & `standards/OPENAPI_STANDARDS.md`: versioning, validation, error contract yang konsisten
* [ ] Menguasai database mendalam: indexing, query plan, isolation level, N+1 query, migrasi terkontrol (`standards/DATABASE_STANDARDS.md`, `standards/SQL_STANDARDS.md`, `standards/FLYWAY_STANDARDS.md`)
* [ ] Menulis query SQL analitik tingkat lanjut untuk REST API reporting: CTE (termasuk recursive), window function, agregasi kondisional (`CASE WHEN`, `GREATEST()`/`LEAST()`) — mampu membaca & menulis pola "fat query, thin service" yang umum dipakai industri untuk endpoint reporting
* [ ] Paham `@Transactional` sampai ke akar: propagation, isolation, self-invocation pitfall, kapan transaksi lokal tidak cukup
* [ ] Menerapkan caching yang benar dengan Redis: cache-aside, write-through, invalidation strategy, thundering herd (`standards/CACHE_STANDARDS.md`)
* [ ] Menulis testing pyramid yang proporsional: unit, integration (Testcontainers), contract testing — bukan cuma "ada test" tapi test yang berguna (`standards/TESTING_STANDARDS.md`)
* [ ] Menerapkan security fundamental: authn/authz, Spring Security, JWT/OAuth2, OWASP Top 10 (`standards/SECURITY_STANDARDS.md`)
* [ ] Menerapkan resiliency (retry/circuit breaker/timeout) dan observability (structured logging, metrics, tracing) sebagai kebiasaan default, bukan tambahan di akhir (`standards/RESILIENCY_STANDARDS.md`, `standards/OBSERVABILITY_STANDARDS.md`)
* [ ] Mampu melakukan performance engineering dasar: profiling, load testing, dan tahu di mana bottleneck sebelum menebak-nebak (`standards/PERFORMANCE_STANDARDS.md`)
* [ ] Menyelesaikan capstone: satu backend service production-grade yang menggabungkan semua pilar di atas, portfolio-ready untuk klien freelance internasional

---

# Course Modules

## Module 1 — Java Lanjut: Idiom yang Benar, Bukan Sekadar Jalan

Description: Membongkar kebiasaan Java yang "jalan tapi bukan idiom yang benar" — titik awal paling umum dari fondasi yang terasa rapuh. Fokus ke generics, collections internals, dan exception handling sesuai `standards/JAVA_STANDARDS.md`.

Status: In Progress

Lessons:

* [ ] 1.1 Generics mendalam: bounded type, wildcard (`? extends`/`? super`), type erasure — dan bug yang muncul kalau salah paham erasure (materi + task tergenerate 2026-08-19, lihat vault `Courses/Backend Engineering Fundamentals Rebuild/Module 1 - Java Lanjut.md`)
* [ ] 1.2 Collections internals: kompleksitas `ArrayList` vs `LinkedList` vs `HashMap` vs `TreeMap`, kapan `HashMap` jadi sumber bug (mutable key, hashCode/equals contract)
* [ ] 1.3 Exception handling idiomatis: checked vs unchecked, kapan bikin custom exception, anti-pattern `catch (Exception e) {}` (`standards/ERROR_HANDLING_STANDARDS.md`)
* [ ] 1.4 Idiom *Effective Java* yang paling sering dilanggar: immutability by default, `equals`/`hashCode`/`toString` yang benar, static factory method vs constructor
* [ ] 1.5 Naming & code style sesuai `standards/NAMING_CONVENTIONS.md` — kenapa konsistensi ini bukan soal selera

Mini Project: Diberi 1 class Java "yang jalan" tapi melanggar sebagian besar idiom di atas (raw type, mutable public field, `catch` yang menelan exception) — Anda diminta memperbaikinya baris demi baris dengan justifikasi tiap perubahan.

Status: Not Started

---

## Module 2 — Concurrency & Multithreading

Description: Pilar yang paling sering jadi "tahu istilahnya, belum pernah pegang sendiri". Modul ini membangun model mental thread-safety dari nol sampai virtual threads Java 25.

Lessons:

* [ ] 2.1 Model mental thread-safety: race condition, visibility problem, kenapa `synchronized` bukan solusi ajaib untuk semua kasus
* [ ] 2.2 `java.util.concurrent`: `ExecutorService`, thread pool sizing yang benar (bukan asal angka), `ConcurrentHashMap` vs `Collections.synchronizedMap`
* [ ] 2.3 `CompletableFuture`: composing async operation, exception handling di rantai async, kapan ini vs kapan reactive (`Mono`/`Flux`) lebih tepat
* [ ] 2.4 Virtual Threads (Project Loom, Java 25): beda fundamental dengan platform thread, kapan benar-benar menguntungkan untuk backend I/O-bound
* [ ] 2.5 Kelas bug concurrency yang umum di production: deadlock, livelock, stale read — cara mendiagnosisnya dari thread dump

Mini Project: Refactor sebuah service yang memproses banyak request eksternal secara sekuensial (lambat) menjadi paralel yang aman menggunakan `ExecutorService`/`CompletableFuture`, lengkap dengan penjelasan kenapa aman dari race condition.

Status: Not Started

---

## Module 3 — JVM Internals untuk Debugging Production

Description: Bukan kursus JVM akademis — fokus ke pemahaman minimum yang membuat Anda bisa membaca sinyal saat aplikasi production bermasalah (OOM, GC pause, memory leak), bukan menebak-nebak.

Lessons:

* [ ] 3.1 Memory model JVM: heap vs stack vs metaspace, object lifecycle, kapan sesuatu jadi eligible untuk GC
* [ ] 3.2 Garbage Collector: perbedaan mental model GC generasional, GC modern (G1, ZGC) secukupnya untuk memahami trade-off throughput vs latency
* [ ] 3.3 Memory leak klasik di aplikasi Spring Boot: static collection yang terus tumbuh, listener yang tidak di-unregister, `ThreadLocal` yang tidak dibersihkan
* [ ] 3.4 Cara baca heap dump & thread dump dasar (tooling: `jcmd`, VisualVM/JFR) untuk mendiagnosis `OutOfMemoryError` atau aplikasi yang "hang"
* [ ] 3.5 JVM flags production-relevan secukupnya (heap sizing, GC selection) — bukan tuning mendalam, cukup untuk tahu apa yang sedang dikonfigurasi dan kenapa

Mini Project: Diberi aplikasi Spring Boot kecil dengan memory leak yang disengaja — Anda diminta mengambil heap dump, mengidentifikasi objek yang bocor, dan memperbaikinya.

Status: Not Started

---

## Module 4 — Spring Core & IoC Deep Dive

Description: Membuka "kotak hitam" Spring yang selama ini dipakai tanpa benar-benar dipahami — bean lifecycle, DI, dan AOP sampai ke mekanisme di baliknya.

Lessons:

* [ ] 4.1 IoC Container & `ApplicationContext`: bean definition, bean lifecycle (instantiation → dependency injection → post-processing → destruction)
* [ ] 4.2 Dependency Injection: constructor vs setter vs field injection — kenapa constructor injection jadi standar (immutability, testability, fail-fast)
* [ ] 4.3 Bean scope (singleton, prototype, request, session) dan kapan tiap scope relevan
* [ ] 4.4 AOP & Proxy: JDK dynamic proxy vs CGLIB, kenapa `@Transactional`/`@Cacheable` tidak bekerja pada self-invocation (pitfall paling umum)
* [ ] 4.5 Circular dependency & cara Spring (gagal) mengatasinya — kapan ini sinyal desain yang salah, bukan sekadar error teknis

Mini Project: Diberi service dengan bug "self-invocation `@Transactional` tidak jalan" — Anda diminta mendiagnosis akar masalahnya lewat pemahaman proxy, lalu memperbaikinya dengan 2 pendekatan berbeda dan menjelaskan trade-off-nya.

Status: Not Started

---

## Module 5 — REST API Production-Grade

Description: Naik dari "bisa bikin endpoint CRUD" ke "bisa mendesain kontrak API yang akan dipakai klien lain selama bertahun-tahun tanpa breaking change".

Lessons:

* [ ] 5.1 Desain resource & kontrak REST yang konsisten sesuai `standards/API_STANDARDS.md` — naming, HTTP method/status code yang benar, pagination, filtering
* [ ] 5.2 Versioning strategy (URI vs header) dan kapan breaking change benar-benar butuh versi baru
* [ ] 5.3 Validation layer yang benar: `@Valid`, custom validator, error response yang konsisten (bukan stack trace mentah) sesuai `standards/ERROR_HANDLING_STANDARDS.md`
* [ ] 5.4 Dokumentasi API-first dengan OpenAPI/Swagger sesuai `standards/OPENAPI_STANDARDS.md` — kontrak sebagai sumber kebenaran, bukan dokumentasi belakangan
* [ ] 5.5 Idempotency & konvensi REST lanjut: `PUT` vs `PATCH` semantics, `ETag`/optimistic concurrency dasar

Mini Project: Desain ulang & dokumentasikan (OpenAPI) sebuah API CRUD yang sebelumnya asal-asalan (endpoint tidak konsisten, error response berbeda-beda tiap controller) menjadi kontrak yang production-grade dan konsisten.

Status: Not Started

---

## Module 6 — Database & Persistence Mendalam

Description: Modul paling menentukan untuk "dipercaya proyek besar" — kebanyakan bug performa production berakar dari sini. Fokus ke SQL mendalam dan JPA/Hibernate tanpa jebakan tersembunyi.

Lessons:

* [ ] 6.1 Indexing mendalam: B-tree index, composite index, kapan index justru memperlambat write, `EXPLAIN`/query plan membaca hasilnya sesuai `standards/SQL_STANDARDS.md`
* [ ] 6.2 Normalisasi vs denormalisasi yang sadar trade-off (bukan dogma), desain skema sesuai `standards/DATABASE_STANDARDS.md`
* [ ] 6.3 JPA/Hibernate internals: lazy vs eager loading, N+1 query problem dan cara mendeteksinya, `@Transactional` read-only untuk optimisasi
* [ ] 6.4 Migrasi database terkontrol dengan Flyway (`standards/FLYWAY_STANDARDS.md`) — kenapa `ddl-auto: update` adalah anti-pattern di production
* [ ] 6.5 Connection pooling (HikariCP) — sizing yang benar dan gejala saat pool kehabisan koneksi

Mini Project: Diberi endpoint yang lambat karena N+1 query & index yang salah — Anda diminta membaca `EXPLAIN` plan, mendiagnosis akar masalah, memperbaikinya (fetch strategy + index), dan mengukur perbaikannya dengan angka (before/after).

Status: Not Started

---

## Module 7 — Advanced SQL & Reporting Query Patterns

Description: Menjawab langsung gap yang Anda temukan di lapangan — pola industri umum untuk REST API reporting: 80% business logic (agregasi, ranking, perbandingan) ditaruh di satu query SQL yang kompleks, service layer jadi tipis (cuma validasi & pemetaan filter). Modul ini membangun kemampuan menulis & membaca query se-kompleks itu, plus kapan pola "fat query, thin service" ini tepat dipakai.

Lessons:

* [ ] 7.1 CTE (Common Table Expression): non-recursive untuk memecah query kompleks jadi tahapan yang terbaca (`WITH ... AS`), recursive CTE untuk data hierarkis (kategori bertingkat, struktur organisasi)
* [ ] 7.2 Window function: `ROW_NUMBER()`, `RANK()`/`DENSE_RANK()`, `LAG()`/`LEAD()`, `SUM()`/`AVG() OVER (PARTITION BY ... ORDER BY ...)` — inti dari hampir semua laporan (running total, top-N per grup, perbandingan periode)
* [ ] 7.3 Fungsi kondisional & agregasi kondisional: `CASE WHEN` di dalam `SUM`/`COUNT` (pivot manual), `GREATEST()`/`LEAST()`, `COALESCE()`/`NULLIF()` untuk data yang tidak lengkap — pola yang Anda temukan langsung di kode teman Anda
* [ ] 7.4 Query reporting dinamis yang aman: membangun filter dari parameter API (date range, kategori, status) tanpa terjebak SQL injection atau validasi filter yang bocor ke banyak `if` di Java
* [ ] 7.5 Performance untuk query reporting: `EXPLAIN ANALYZE` pada query kompleks (CTE/window function punya karakteristik performa berbeda dari query OLTP biasa), kapan butuh materialized view atau summary/pre-aggregated table untuk dataset besar

Mini Project: Diberi dataset transaksi mentah, buat 1 endpoint reporting nyata — mis. "laporan penjualan bulanan per kategori, dengan running total & ranking kategori terlaris" — seluruh logic agregasi ada di satu query SQL (CTE + window function + conditional aggregation), service layer Java **hanya** memvalidasi & memetakan parameter filter (rentang tanggal, kategori) ke query, lalu mem-return DTO hasil proyeksi.

**Production Discussion**: Trade-off pola "fat query, thin service" — kenapa ini masuk akal untuk reporting/read path (database jauh lebih efisien untuk agregasi besar daripada loop di Java, dan hasil query bisa langsung jadi projection DTO tanpa mapping berlapis), tapi berbahaya kalau dipakai untuk business logic write-path yang butuh validasi & aturan bisnis kompleks (lebih cocok tetap di Service layer, lihat Module 8 dan pola Domain-Driven di `courses/design-patterns`). Juga dibahas: bagaimana menguji query sekompleks ini (integration test dengan Testcontainers + dataset seed yang terkontrol, lihat Module 10) karena logic-nya tidak bisa di-unit-test seperti method Java biasa.

Status: Not Started

---

## Module 8 — Transaksi & Data Integrity

Description: Melanjutkan Module 6 ke lapisan konsistensi data — area yang sering dianggap "sudah otomatis" padahal penuh keputusan desain.

Lessons:

* [ ] 8.1 ACID secara mendalam dan konsekuensi praktisnya, bukan cuma definisi akronim
* [ ] 8.2 Isolation level (`READ_COMMITTED`, `REPEATABLE_READ`, `SERIALIZABLE`) — dirty read, non-repeatable read, phantom read dengan contoh nyata
* [ ] 8.3 `@Transactional` propagation (`REQUIRED`, `REQUIRES_NEW`, `NESTED`, dll) — kapan tiap jenis benar-benar dibutuhkan
* [ ] 8.4 Optimistic vs pessimistic locking — `@Version`, `SELECT ... FOR UPDATE`, kapan pakai yang mana
* [ ] 8.5 Pengantar distributed transaction: kenapa transaksi lokal tidak cukup lintas service, intuisi Saga pattern (kedalaman penuh ada di `courses/system-design`, belum dibuat)

Mini Project: Simulasikan race condition pada operasi "kurangi stok barang" tanpa locking (jalankan concurrent request), buktikan bug-nya secara terukur, lalu perbaiki dengan optimistic locking dan buktikan lagi hasilnya benar.

Status: Not Started

---

## Module 9 — Caching dengan Redis

Description: Caching yang salah desain sering membuat sistem lebih buruk, bukan lebih cepat — modul ini membangun intuisi kapan dan bagaimana caching benar dipasang.

Lessons:

* [ ] 9.1 Kapan caching benar-benar dibutuhkan (dan kapan justru menambah kompleksitas tanpa manfaat) sesuai `standards/CACHE_STANDARDS.md`
* [ ] 9.2 Pola caching: cache-aside, write-through, write-behind — trade-off konsistensi vs performa tiap pola
* [ ] 9.3 Redis dari Spring Boot: `@Cacheable`/`@CacheEvict`, `RedisTemplate`, TTL & eviction policy
* [ ] 9.4 Masalah klasik caching: cache stampede/thundering herd, stale data, cache invalidation ("two hardest problems in CS")
* [ ] 9.5 Redis sebagai lebih dari cache: rate limiting sederhana, distributed lock dasar (`Redisson`/`SETNX`)

Mini Project: Tambahkan caching ke endpoint read-heavy dari Module 6, ukur peningkatan performa dengan angka, lalu simulasikan & tangani skenario cache stampede saat cache expired bersamaan di beban tinggi.

Status: Not Started

---

## Module 10 — Testing Pyramid yang Proporsional

Description: "Ada test" dan "test yang berguna" adalah dua hal berbeda. Modul ini membangun kemampuan menulis test yang benar-benar memberi kepercayaan diri untuk deploy, sesuai `standards/TESTING_STANDARDS.md`.

Lessons:

* [ ] 10.1 Testing pyramid: proporsi unit vs integration vs end-to-end yang sehat, kenapa ice-cream cone anti-pattern (kebanyakan E2E, sedikit unit) mahal
* [ ] 10.2 Unit testing yang baik: JUnit 5, Mockito — mock vs stub vs spy, kapan mocking justru membuat test rapuh (over-mocking)
* [ ] 10.3 Integration testing dengan Testcontainers: test melawan database/Redis/Kafka asli via container, bukan H2 in-memory yang menyembunyikan bug
* [ ] 10.4 Contract testing dasar: kenapa ini penting saat API dikonsumsi klien eksternal (relevan langsung untuk konteks freelance)
* [ ] 10.5 Test sebagai dokumentasi hidup: penamaan test yang jelas, Given-When-Then, test yang gagal dengan pesan yang berguna

Mini Project: Ambil service dari Module 6, 8, dan 9 (database, transaksi, dan cache), tulis test suite proporsional: unit test untuk business logic (mock dependency), integration test dengan Testcontainers (database & Redis asli) — dengan target coverage yang bermakna, bukan sekadar angka tinggi.

Status: Not Started

---

## Module 11 — Security Fundamentals

Description: Keamanan yang sering "ditempel belakangan" padahal harus jadi keputusan desain dari awal — krusial untuk dipercaya klien internasional yang menangani data sensitif.

Lessons:

* [ ] 11.1 Authentication vs Authorization — Spring Security filter chain, kenapa ini juga Chain of Responsibility pattern sesuai `standards/SECURITY_STANDARDS.md`
* [ ] 11.2 JWT & OAuth2/OIDC: cara kerja, pitfall umum (menyimpan JWT di localStorage, tidak validasi expiry/signature dengan benar)
* [ ] 11.3 Password & secret handling yang benar: hashing (BCrypt/Argon2), kenapa `standards/CONFIGURATION_STANDARDS.md` melarang secret hardcoded
* [ ] 11.4 OWASP Top 10 diterapkan ke konteks Spring Boot: SQL injection (kenapa JPA/parameterized query melindungi), broken access control, mass assignment
* [ ] 11.5 Input validation & sanitization sebagai lapisan pertahanan, CORS yang dikonfigurasi benar (bukan `*` di production)

Mini Project: Audit sebuah service (dari modul sebelumnya) terhadap checklist OWASP Top 10, temukan minimal 3 celah nyata, perbaiki, dan dokumentasikan sebagai laporan security review singkat.

Status: Not Started

---

## Module 12 — Resiliency & Fault Tolerance

Description: Sistem production akan gagal — pertanyaannya bukan "kalau", tapi "seberapa graceful". Modul ini membangun kebiasaan defensif yang jadi default, bukan tambahan darurat.

Lessons:

* [ ] 12.1 Failure mode di sistem terdistribusi: timeout, partial failure, cascading failure — kenapa tanpa proteksi satu service lambat bisa merobohkan semua
* [ ] 12.2 Resilience4j: retry (dengan backoff, bukan retry membabi buta), circuit breaker, timeout, bulkhead — sesuai `standards/RESILIENCY_STANDARDS.md`
* [ ] 12.3 Idempotency di sisi consumer/producer — kenapa retry tanpa idempotency berbahaya (duplicate charge, dsb)
* [ ] 12.4 Graceful degradation & fallback strategy — kapan menampilkan data stale lebih baik daripada error total
* [ ] 12.5 Health check & readiness/liveness probe yang benar (relevan langsung ke `courses/kubernetes`, paused, tapi konsepnya dipakai di sini)

Mini Project: Tambahkan circuit breaker + retry + fallback ke satu call eksternal (mis. panggilan API pihak ketiga) dari service yang sudah dibangun, lalu simulasikan service eksternal itu down dan buktikan sistem Anda tetap merespons dengan graceful (bukan crash/hang).

Status: Not Started

---

## Module 13 — Observability

Description: Kalau tidak bisa diobservasi, tidak bisa didebug saat production bermasalah jam 2 pagi. Modul ini membangun tiga pilar observability sebagai kebiasaan default.

Lessons:

* [ ] 13.1 Structured logging: kenapa `log.info("user " + id)` adalah anti-pattern, logging sebagai data (JSON), correlation ID lintas request sesuai `standards/OBSERVABILITY_STANDARDS.md`
* [ ] 13.2 Metrics: RED (Rate, Error, Duration) dan USE (Utilization, Saturation, Errors) method, expose metrics via Micrometer/Actuator
* [ ] 13.3 Distributed tracing dasar: trace ID/span ID, kenapa ini krusial begitu sistem punya lebih dari 1 service
* [ ] 13.4 Alerting yang bermakna: kenapa alert fatigue (terlalu banyak alert palsu) sama buruknya dengan tidak ada alert sama sekali
* [ ] 13.5 Debugging insiden production dari log/metric/trace — simulasi "on-call" membaca sinyal untuk menemukan akar masalah

Mini Project: Instrumentasi service dari modul-modul sebelumnya dengan structured logging + metrics + correlation ID, lalu diberi skenario "insiden" (endpoint tiba-tiba lambat) yang harus didiagnosis murni dari observability data yang sudah dipasang.

Status: Not Started

---

## Module 14 — Performance Engineering

Description: Menutup rebuild fondasi dengan kemampuan mengukur, bukan menebak — mengikat kembali semua pilar sebelumnya (JVM, database, caching, concurrency) ke satu pertanyaan: seberapa cepat, dan kenapa.

Lessons:

* [ ] 14.1 Mindset performance engineering: ukur dulu (profiling), baru optimasi — kenapa optimasi tanpa data sering salah sasaran sesuai `standards/PERFORMANCE_STANDARDS.md`
* [ ] 14.2 Profiling aplikasi Java: CPU profiling, mengidentifikasi hot path, korelasi dengan pemahaman JVM dari Module 3
* [ ] 14.3 Load testing dasar (mis. k6/Gatling): menentukan baseline, throughput, latency percentile (p50/p95/p99) — kenapa rata-rata menyesatkan
* [ ] 14.4 Bottleneck umum backend: query lambat (Module 6-7), thread pool starvation (Module 2), cache miss (Module 9) — mengenali pola dari gejalanya
* [ ] 14.5 Capacity planning secukupnya: memperkirakan kebutuhan resource dari hasil load test, bukan asal tebak

Mini Project: Jalankan load test terhadap capstone service (Module 15), identifikasi bottleneck terbesar dari hasil profiling/metrics, perbaiki, lalu buktikan peningkatan dengan angka before/after (throughput & p95 latency).

Status: Not Started

---

## Module 15 — Capstone: Production-Grade Backend Service

Description: Menyatukan seluruh 14 modul menjadi satu layanan backend nyata yang portfolio-ready — bukti konkret bahwa fondasi Anda sudah production-grade, siap ditunjukkan ke klien freelance internasional.

Lessons:

* [ ] 15.1 Perencanaan: memilih domain layanan (mis. booking/inventory/payment-lite, idealnya dengan minimal 1 sisi reporting) dan menyusun desain awal (API contract, skema data, keputusan arsitektur)
* [ ] 15.2 Implementasi bertahap: setiap pilar (concurrency, database, reporting query, caching, security, resiliency, observability, testing) diterapkan sadar dan terdokumentasi, bukan sekadar "ditempel semua"
* [ ] 15.3 Performance pass: profiling & load test sesuai Module 14, perbaiki bottleneck yang ditemukan
* [ ] 15.4 Dokumentasi: README production-grade (`templates/README_TEMPLATE.md`), OpenAPI spec, dan singkat "Engineering Decisions" (kenapa pilih X bukan Y di titik-titik krusial)
* [ ] 15.5 Code review penuh (REVIEW_MODE) atas keseluruhan capstone sebelum dianggap selesai

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Production-Grade Backend Service** — satu layanan Spring Boot yang menggabungkan seluruh course: (1) Java lanjut & concurrency yang aman, (2) database ternormalisasi dengan index yang benar & migrasi Flyway, (3) minimal 1 endpoint reporting dengan pola "fat query, thin service" (CTE/window function), (4) transaksi dengan locking yang tepat, (5) caching Redis dengan strategi invalidation yang jelas, (6) testing pyramid proporsional (unit + integration Testcontainers), (7) security (auth, OWASP-aware), (8) resiliency (retry/circuit breaker/fallback) untuk setiap dependency eksternal, (9) observability penuh (structured logging, metrics, tracing), (10) hasil load test & bukti perbaikan performa.

Acceptance Criteria:

* Tidak ada anti-pattern dari modul manapun yang lolos (raw type, `ddl-auto: update`, secret hardcoded, `catch` yang menelan exception, dsb)
* Minimal 1 endpoint reporting yang seluruh logic agregasinya ada di query SQL (Module 7), bukan di-loop manual di Java
* Test suite proporsional hijau, termasuk integration test dengan Testcontainers (database & Redis asli, bukan mock/in-memory)
* Minimal 1 dependency eksternal terlindungi resiliency pattern dan terbukti graceful saat disimulasikan gagal
* Observability lengkap: bisa mendiagnosis skenario "insiden" murni dari log/metric/trace yang terpasang
* Ada laporan performa before/after dari Module 14 dengan angka konkret (throughput, p95 latency)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, siap ditunjukkan ke klien freelance internasional (Upwork/Toptal) sebagai bukti kemampuan production-grade

Status: Not Started

---

# Overall Progress

Module Completion: 0/15

Overall Completion: 0%

Current Module: Module 1 — Java Lanjut: Idiom yang Benar, Bukan Sekadar Jalan

Current Lesson: Lesson 1.1 — Generics mendalam: bounded type, wildcard, dan type erasure (materi + task tergenerate di vault, tunggu jawaban user)

Next Lesson: Module 1, Lesson 2 — Collections internals
