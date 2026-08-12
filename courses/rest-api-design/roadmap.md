# Course Roadmap

## Course Information

* Course Name: REST API Design — dari 0 sampai Expert (Spring Boot)
* Category: API Engineering (Backend) — course REST-only, standalone, dari fondasi HTTP sampai API governance level organisasi
* Difficulty: Level 1 (paham HTTP/REST secara konseptual tapi belum pernah didesain sistematis) → Level 4 (bisa merancang, mengamankan, menguji, dan mengoperasikan REST API production-grade, plus menulis/menegakkan API governance untuk tim)
* Estimated Duration: 15 modul, 3-5 lesson/modul, ~40-60 menit/lesson. Perkiraan ≈ 4-5 bulan pace sedang.
* Prerequisites: Sudah bisa menulis Java & Spring Boot dasar (CRUD, JPA, controller) — course ini **tidak** mengajarkan syntax Java atau "apa itu Spring Boot" dari nol. "Dari 0" di course ini berarti dari 0 di sisi *desain* REST API (HTTP semantics, REST architectural style, standar production-grade) — bukan dari 0 pemrograman.

---

# Catatan Scope (draft — menunggu approval user)

Dibuat 2026-08-12 atas permintaan user: *"Buat course REST API design dari 0 hingga expert khusus Spring Boot. Ajarkan HTTP, REST, API standards, DTO, validation, error handling, versioning, pagination, security, OpenAPI, testing, performance, resilience, observability, architecture, dan API governance. Gunakan proyek nyata bertahap."*

Keputusan scope (dikonfirmasi user lewat pertanyaan kalibrasi sebelum roadmap ini ditulis):

1. **Course baru berdiri sendiri** (bukan gabung ke `courses/api-engineering` atau perluasan `courses/backend-fundamentals` Module 5) — opsi yang dipilih user secara eksplisit.
2. **REST-only** — gRPC, GraphQL, WebSocket, dan API Gateway multi-protokol sengaja **tidak** dibahas di sini; itu tetap wilayah `courses/api-engineering` (masih draft, belum di-approve) kalau nanti dilanjutkan.
3. **Overlap yang diketahui, sengaja dibiarkan untuk saat ini** (bisa dirapikan belakangan, bukan blocker untuk mulai course ini):
   - `courses/backend-fundamentals` Module 5 ("REST API Production-Grade") sudah menyentuh desain resource, validation, error contract, versioning dasar, pagination, ETag — course ini mengajarkan topik yang sama tapi jauh lebih dalam & sistematis (dari HTTP semantics naik ke API governance), jadi ada duplikasi konsep dasar yang disengaja untuk membuat course ini benar-benar berdiri sendiri "dari 0".
   - `courses/api-engineering` Module 1-3 (pola REST lanjut, versioning, rate limiting) beririsan dengan Module 7-9 course ini — course ini lebih lengkap di sisi REST (testing, observability, governance yang tidak ada di api-engineering), api-engineering lebih lengkap di sisi multi-protokol.
4. Course ini menjadi **acuan utama REST API** di repo — course lain yang menyentuh REST (backend-fundamentals, api-engineering, system-design) boleh mulai merujuk ke sini alih-alih mengulang, saat dirapikan nanti.
5. Proyek nyata bertahap: satu case study **Order Management API** (order, item, customer) dipakai konsisten sebagai Mini Project tiap modul, tumbuh jadi API production-grade lengkap di Capstone — dipilih karena cukup kaya untuk menyentuh semua topik (pagination, relasi data, business rule validation, status transition, dsb.) tanpa terlalu besar. User boleh ganti ke domain proyek kerja nyata sendiri kalau lebih relevan.

Referensi riset (dasar penyusunan modul):

* [RFC 9110 — HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110) — fondasi Module 1
* [RFC 9457 — Problem Details for HTTP APIs](https://www.rfc-editor.org/rfc/rfc9457) (obsoletes RFC 7807) — fondasi Module 6
* Roy Fielding, *Architectural Styles and the Design of Network-based Software Architectures* (disertasi REST asli, 2000) & Richardson Maturity Model — fondasi Module 2
* [Google API Design Guide](https://cloud.google.com/apis/design), [Microsoft REST API Guidelines](https://github.com/microsoft/api-guidelines), [Zalando RESTful API Guidelines](https://opensource.zalando.com/restful-api-guidelines/) — pembanding standar industri di Module 2, 7, 8, 15
* [Stripe API versioning](https://stripe.com/blog/api-versioning) & [Stripe API design](https://stripe.com/docs/api) — studi kasus praktisi Module 8
* [OWASP API Security Top 10 (2023)](https://owasp.org/API-Security/editions/2023/en/0x00-header/) — fondasi Module 9
* [OpenAPI Specification](https://spec.openapis.org/oas/latest.html) & [springdoc-openapi docs](https://springdoc.org/) — fondasi Module 10
* [Spring Framework — Web MVC docs](https://docs.spring.io/spring-framework/reference/web/webmvc.html), [Spring Data docs](https://docs.spring.io/spring-data/jpa/reference/), [Spring Security docs](https://docs.spring.io/spring-security/reference/) — hands-on Module 3, 7, 9
* [Testcontainers docs](https://testcontainers.com/), Baeldung `@WebMvcTest`/`@SpringBootTest` guides — Module 11
* [Resilience4j docs](https://resilience4j.readme.io/) — Module 13
* [Micrometer docs](https://micrometer.io/docs), [OpenTelemetry docs](https://opentelemetry.io/docs/) — Module 14
* Martin Fowler, *Consumer-Driven Contracts* — Module 8
* `standards/API_STANDARDS.md` & `standards/OPENAPI_STANDARDS.md` — kontrak konvensi repo ini, jadi rubric utama tiap Code Review lesson

---

# Learning Objectives

* [ ] Menjelaskan HTTP semantics (method, status code, header, statelessness, caching) sesuai RFC 9110, bukan sekadar hafalan
* [ ] Menerapkan REST sebagai architectural style (constraint Fielding, Richardson Maturity Model) untuk memodelkan resource dari domain bisnis nyata
* [ ] Merancang DTO yang aman & jelas, terpisah tegas dari entity, dengan mapping yang benar
* [ ] Menerapkan validation berlapis (format, business rule) yang menghasilkan pesan error yang berguna
* [ ] Merancang kontrak error yang konsisten (RFC 9457 Problem Details) dan tidak membocorkan detail internal
* [ ] Mengimplementasikan pagination, filtering, sorting yang aman untuk dataset besar
* [ ] Merancang strategi versioning & mengelola breaking change tanpa merusak konsumen API
* [ ] Mengamankan REST API sesuai OWASP API Security Top 10 (authN, authZ, rate limiting, mass assignment, dll.)
* [ ] Mendokumentasikan API lewat OpenAPI yang akurat & selalu sinkron dengan implementasi
* [ ] Menguji REST API di semua layer (unit, slice, integration, contract) termasuk skenario gagal
* [ ] Mengoptimalkan performa API (caching, compression, N+1, load testing) dengan bukti pengukuran
* [ ] Menerapkan pola resilience (timeout, retry, circuit breaker, idempotency) untuk API yang tahan gagal
* [ ] Membangun observability API (structured logging, trace ID, metrics, health check) yang berguna saat insiden nyata
* [ ] Menulis & menegakkan API governance (review checklist, style guide, deprecation policy) untuk tim/organisasi
* [ ] Menyelesaikan Capstone: satu REST API production-grade lengkap, portfolio-ready

---

# Course Modules

## Module 1 — HTTP Foundations untuk API Design

Description: Sebelum bicara REST, kuasai dulu protokol yang jadi fondasinya. Kebanyakan bug desain API (status code salah, caching salah, method salah) berasal dari kesalahpahaman HTTP dasar, bukan REST.

Lessons:

* [ ] 1.1 Anatomy HTTP request/response: start line, header, body — dan kenapa REST "menumpang" di atas HTTP, bukan protokol sendiri
* [ ] 1.2 HTTP methods & safety/idempotency semantics (RFC 9110) — kenapa `GET` harus safe, kenapa `PUT` harus idempotent tapi `POST` tidak
* [ ] 1.3 HTTP status code by category (1xx-5xx) — memilih status code yang tepat, bukan default ke 200/500
* [ ] 1.4 Header penting untuk API: `Content-Type`, `Accept`, `Authorization`, `ETag`, `Cache-Control`, `Location`
* [ ] 1.5 Statelessness — kenapa REST server tidak boleh menyimpan session state klien, dan konsekuensinya ke desain

Mini Project: Tulis ulang 5 endpoint "REST" yang salah kaprah umum (`POST /getUser`, selalu return 200, dll.) jadi versi yang benar secara HTTP semantics — tanpa Spring dulu, cukup desain di kertas/markdown.

Status: Not Started

---

## Module 2 — REST sebagai Architectural Style

Description: REST bukan "API pakai HTTP" — ini gaya arsitektur dengan constraint spesifik dari disertasi Roy Fielding. Modul ini membangun kemampuan memodelkan resource dari domain bisnis nyata, bukan cuma niru-niru endpoint orang lain.

Lessons:

* [ ] 2.1 Enam constraint REST Fielding (client-server, stateless, cacheable, uniform interface, layered system, code-on-demand) — mana yang wajib, mana yang opsional
* [ ] 2.2 Richardson Maturity Model (Level 0-3) — mengukur seberapa "RESTful" API sungguhan, termasuk API perusahaan besar yang ternyata Level 2
* [ ] 2.3 Resource modeling: memetakan domain bisnis (Order Management) jadi resource, sub-resource, dan relasi — kapan nested URI (`/orders/{id}/items`), kapan tidak
* [ ] 2.4 URI naming sesuai `standards/API_STANDARDS.md`: lowercase, hyphen, plural, no verbs — dan kenapa aturan ini penting untuk predictability
* [ ] 2.5 RPC-style vs REST-style — kapan endpoint non-CRUD (`POST /orders/{id}/cancel`) tetap dianggap RESTful yang wajar

Mini Project: Desain resource model penuh untuk **Order Management API** (order, item, customer, relasi antar-resource) — output berupa daftar endpoint + method + status code, sebelum satu baris kode Spring pun ditulis.

Status: Not Started

---

## Module 3 — Spring Boot REST Fundamentals

Description: Menerjemahkan desain Module 1-2 jadi kode Spring Boot yang benar — bagian paling "hands-on pertama" di course ini.

Lessons:

* [ ] 3.1 `@RestController`, `@RequestMapping`, path variable vs query parameter — kapan pakai yang mana
* [ ] 3.2 Request/response body binding: `@RequestBody`, `@ResponseBody`, `ResponseEntity` untuk kontrol status code & header eksplisit
* [ ] 3.3 Content negotiation: `Accept`/`Content-Type`, `produces`/`consumes`, JSON sebagai default sesuai standar
* [ ] 3.4 Layered architecture untuk REST: Controller → Service → Repository, tanggung jawab tiap layer, kenapa controller harus "tipis"

Mini Project: Implementasikan resource model Module 2 jadi Controller Spring Boot penuh (CRUD `/orders`) dengan status code & `ResponseEntity` yang benar — belum ada DTO/validation dulu (sengaja pakai entity langsung untuk kontraskan dengan Module 4).

Status: Not Started

---

## Module 4 — DTO Design & Data Mapping

Description: Kenapa mengekspos entity langsung ke API adalah kesalahan produksi klasik — dan cara mendesain DTO yang benar.

Lessons:

* [ ] 4.1 DTO vs Entity — coupling, over-exposure, dan masalah nyata saat entity berubah tapi API tidak boleh
* [ ] 4.2 Request DTO vs Response DTO — kenapa dipisah, kapan boleh digabung
* [ ] 4.3 Mapping: manual mapper vs MapStruct — trade-off kontrol vs boilerplate
* [ ] 4.4 Over-fetching & under-fetching — mendesain DTO sesuai kebutuhan konsumen, bukan sesuai struktur database

Mini Project: Refactor Controller Module 3 supaya 100% pakai DTO (request & response terpisah), tambahkan MapStruct mapper, buktikan entity tidak pernah bocor ke response JSON.

Status: Not Started

---

## Module 5 — Request Validation

Description: Validasi adalah garis pertahanan pertama API — modul ini membangun validasi berlapis yang benar-benar berguna buat konsumen API, bukan cuma `@NotNull` di mana-mana.

Lessons:

* [ ] 5.1 Jakarta Bean Validation dasar: `@NotNull`, `@Size`, `@Pattern`, `@Email`, dan kapan masing-masing tepat
* [ ] 5.2 Custom validator untuk business rule yang tidak bisa diekspresikan anotasi bawaan
* [ ] 5.3 Validation groups — validasi berbeda untuk create vs update pada DTO yang sama
* [ ] 5.4 Nested object validation & validasi collection (`@Valid` pada list item order)

Mini Project: Tambahkan validasi penuh ke DTO Order Management (format, range, business rule seperti "item quantity minimal 1, order harus punya minimal 1 item") — buktikan pesan error yang dihasilkan jelas dan actionable.

Status: Not Started

---

## Module 6 — Error Handling & Problem Details

Description: Kontrak error yang konsisten adalah pembeda API amatir vs production-grade. Modul ini menyatukan validasi Module 5 dengan penanganan error sistematis di seluruh aplikasi.

Lessons:

* [ ] 6.1 Kenapa stack trace tidak boleh bocor ke klien — dan apa yang harus ditampilkan sebagai gantinya
* [ ] 6.2 RFC 9457 Problem Details for HTTP APIs — struktur standar (`type`, `title`, `status`, `detail`, `instance`) dan `ProblemDetail` di Spring 6+
* [ ] 6.3 Global exception handling: `@ControllerAdvice` + `@ExceptionHandler`, memetakan exception domain ke status code yang tepat
* [ ] 6.4 Error contract sesuai `standards/API_STANDARDS.md`: error code, message, timestamp, traceId, path, details
* [ ] 6.5 Membedakan error klien (4xx, actionable) vs error server (5xx, harus di-log & dialert, bukan cuma ditampilkan ke user)

Mini Project: Bangun `GlobalExceptionHandler` untuk Order Management API — validasi gagal → 400 dengan detail field, resource tidak ditemukan → 404, business rule dilanggar (contoh: cancel order yang sudah shipped) → 409, semua pakai format Problem Details konsisten.

Status: Not Started

---

## Module 7 — Pagination, Filtering & Sorting

Description: List endpoint yang tidak dibatasi adalah insiden production yang menunggu waktu. Modul ini membangun kontrol data besar yang aman dan terdokumentasi.

Lessons:

* [ ] 7.1 Offset-based pagination dengan Spring Data `Pageable` — kelebihan (simpel) dan kelemahannya (page drift saat data berubah)
* [ ] 7.2 Cursor-based pagination — kapan wajib dipakai (dataset besar/real-time), cara implementasi di atas JPA
* [ ] 7.3 Filtering dinamis: JPA Specification atau Querydsl, whitelist field yang boleh difilter (jangan biarkan klien filter kolom sensitif)
* [ ] 7.4 Sorting dengan whitelist field & default sort yang aman — mencegah `ORDER BY` ke kolom yang tidak diindeks

Mini Project: Tambahkan `GET /orders` dengan pagination (offset dulu, lalu cursor), filtering (status, tanggal, keyword customer), dan sorting bervalidasi ke Order Management API.

Status: Not Started

**Checkpoint — Intermediate Project 1**: Order Management API sekarang punya CRUD lengkap dengan DTO, validasi, error handling konsisten, dan list endpoint yang aman untuk data besar. Deploy lokal, uji manual lewat Postman/HTTP client, dokumentasikan endpoint yang sudah ada secara manual (belum OpenAPI — itu Module 10).

---

## Module 8 — API Versioning & Contract Evolution

Description: API yang sukses pasti berubah — tantangannya adalah berubah tanpa merusak klien yang sudah bergantung padanya.

Lessons:

* [ ] 8.1 Breaking vs non-breaking change — definisi konkret (field baru = aman, field dihapus/tipe berubah = breaking)
* [ ] 8.2 Strategi versioning: URI (`/v1/`), header, content negotiation — trade-off dan mana yang dipakai Stripe/GitHub/Google
* [ ] 8.3 Deprecation policy: `Sunset` header, `Deprecation` header, komunikasi ke konsumen, migration window yang wajar
* [ ] 8.4 Consumer-driven contract testing (konsep) — mencegah breaking change lolos ke production tanpa disadari

Mini Project: Buat perubahan breaking di Order Management API (ubah struktur response order), terapkan versioning URI (`/v1` vs `/v2`) dengan `/v1` di-deprecate resmi lewat header, dokumentasikan migration guide singkat.

Status: Not Started

---

## Module 9 — API Security

Description: OWASP mencatat kesalahan authorization sebagai penyebab #1 insiden API. Modul ini menutup celah paling umum secara sistematis, bukan tambal sulam.

Lessons:

* [ ] 9.1 Authentication di Spring Security: JWT stateless — cara kerja, validasi, dan kesalahan umum (secret lemah, expiry tidak dicek)
* [ ] 9.2 Authorization: method-level security (`@PreAuthorize`), object-level authorization (BOLA/IDOR — API1:2023 OWASP), jangan percaya `id` dari request
* [ ] 9.3 Mass assignment protection — kenapa binding DTO otomatis bisa jadi celah (field `isAdmin` yang tidak boleh diisi klien)
* [ ] 9.4 Rate limiting dasar untuk API — token bucket dengan Redis, response `429` + header `Retry-After`
* [ ] 9.5 OWASP API Security Top 10 (2023) checklist penuh — sisanya (excessive data exposure, security misconfiguration, dll.) dan cara ceknya

Mini Project: Amankan Order Management API — JWT auth, pastikan customer A tidak bisa akses order customer B (object-level authorization), tambahkan rate limit ke endpoint create order, buktikan mass assignment tidak mungkin lewat DTO yang sudah didesain di Module 4.

Status: Not Started

---

## Module 10 — OpenAPI & Documentation

Description: API tanpa dokumentasi akurat efektif tidak bisa dipakai orang lain — modul ini membuat dokumentasi jadi bagian dari kode, bukan tugas terpisah yang basi.

Lessons:

* [ ] 10.1 OpenAPI Specification: struktur dasar (paths, schemas, components) — cara membaca sebelum menulis
* [ ] 10.2 `springdoc-openapi`: generate dokumentasi dari kode lewat anotasi (`@Operation`, `@Schema`, `@ApiResponse`)
* [ ] 10.3 Contract-first vs code-first — trade-off, kapan tim besar memilih contract-first (`.yaml` sebagai sumber kebenaran)
* [ ] 10.4 Dokumentasi lengkap sesuai `standards/OPENAPI_STANDARDS.md`: contoh request/response, kode error, rate limit, versi

Mini Project: Hasilkan dokumentasi OpenAPI penuh untuk Order Management API `/v1` dan `/v2`, sinkron 100% dengan implementasi aktual (dibuktikan lewat Swagger UI yang benar-benar bisa dicoba).

Status: Not Started

---

## Module 11 — Testing REST APIs

Description: API yang tidak diuji otomatis adalah API yang akan rusak diam-diam saat direfactor. Modul ini membangun test pyramid khusus REST API.

Lessons:

* [ ] 11.1 Unit test service layer (mock repository) — apa yang layak/tidak layak diuji di layer ini
* [ ] 11.2 Slice test `@WebMvcTest` — menguji controller, validation, dan error handling tanpa naikkan seluruh context
* [ ] 11.3 Integration test dengan Testcontainers — database sungguhan, bukan H2, untuk menangkap bug spesifik dialect
* [ ] 11.4 Menguji skenario yang sering dilewatkan: validasi gagal, authorization gagal, authentication gagal, edge case (pagination di halaman terakhir, dsb.)
* [ ] 11.5 Contract testing konsep lanjutan (Pact) — memverifikasi konsumen & provider tidak diam-diam berbeda asumsi

Mini Project: Tulis test pyramid lengkap untuk Order Management API — minimal 1 unit test, 1 slice test, dan 1 integration test Testcontainers per endpoint utama, termasuk test untuk jalur gagal (bukan cuma happy path).

Status: Not Started

---

## Module 12 — Performance & Caching

Description: API yang benar tapi lambat tetap gagal di production. Modul ini mengukur dulu, baru optimasi — dengan bukti, bukan tebakan.

Lessons:

* [ ] 12.1 Caching HTTP: `ETag`, `Last-Modified`, `Cache-Control` — conditional request untuk mengurangi payload
* [ ] 12.2 Application-level caching dengan Redis — kapan cache response, kapan cache query result, strategi invalidation
* [ ] 12.3 Menghindari N+1 query di response API (lazy loading JPA yang tidak disadari) — deteksi & perbaikan
* [ ] 12.4 Response compression & payload minimization — kapan gzip membantu, kapan tidak
* [ ] 12.5 Load testing dasar dengan k6 — mengukur latency/throughput sebelum & sesudah optimasi, bukan asumsi

Mini Project: Ukur baseline performa `GET /orders` (k6, 50-100 concurrent user), identifikasi bottleneck (kemungkinan N+1 di item order), perbaiki, ukur ulang, dokumentasikan angka before/after.

Status: Not Started

**Checkpoint — Intermediate Project 2**: Order Management API sekarang aman (Module 9), terdokumentasi (Module 10), teruji (Module 11), dan performanya terukur (Module 12) — sudah layak disebut "production-grade" di 4 dimensi ini.

---

## Module 13 — Resilience Patterns

Description: API production akan berhadapan dengan dependency yang gagal (database lambat, service lain down). Modul ini membangun ketahanan, bukan asumsi semua selalu berjalan lancar.

Lessons:

* [ ] 13.1 Timeout & retry — kenapa retry tanpa backoff bisa memperparah insiden (retry storm)
* [ ] 13.2 Circuit breaker dengan Resilience4j — state closed/open/half-open, kapan dipakai
* [ ] 13.3 Idempotency key — mencegah efek ganda saat klien retry `POST` (contoh: create order dua kali karena network timeout)
* [ ] 13.4 Graceful degradation & asynchronous processing — `202 Accepted` + status endpoint untuk operasi lambat, alih-alih membuat klien menunggu

Mini Project: Tambahkan idempotency key ke `POST /orders`, bungkus 1 dependency eksternal (simulasi payment service) dengan circuit breaker + timeout Resilience4j, buktikan API tetap merespons masuk akal saat dependency itu sengaja dimatikan.

Status: Not Started

---

## Module 14 — Observability

Description: Saat insiden production terjadi jam 2 pagi, observability yang baik adalah beda antara debug 10 menit vs 3 jam.

Lessons:

* [ ] 14.1 Structured logging: request ID, trace ID, user ID (bukan data sensitif) — format yang bisa di-query, bukan `System.out.println`
* [ ] 14.2 Correlation ID lintas service — memetakan satu request sampai ke semua layer yang dilaluinya
* [ ] 14.3 Metrics dengan Micrometer/Prometheus: latency, error rate, throughput per endpoint (RED method)
* [ ] 14.4 Health check & readiness check (Spring Actuator) — bedanya, dan kenapa keduanya penting untuk orchestrator
* [ ] 14.5 Pengenalan distributed tracing (OpenTelemetry) — kapan logging & metrics tidak cukup

Mini Project: Instrumentasi Order Management API — structured logging dengan trace ID di setiap request, metrics Micrometer untuk endpoint utama, health/readiness endpoint aktif, buktikan bisa menelusuri 1 request penuh dari log.

Status: Not Started

---

## Module 15 — Architecture, API Governance & Capstone

Description: Modul penutup — naik dari "API yang saya bangun" ke "standar API yang saya tegakkan untuk tim/organisasi", sekaligus menyatukan seluruh course jadi satu Capstone portfolio-ready.

Lessons:

* [ ] 15.1 API sebagai produk — perspektif konsumen API (internal & eksternal), kenapa consistency lintas tim lebih penting dari "API yang sempurna sendirian"
* [ ] 15.2 API Review Checklist (`standards/API_STANDARDS.md`) — praktik code review khusus API, studi kasus Google/Microsoft/Zalando API guidelines
* [ ] 15.3 API governance di organisasi nyata: style guide enforcement (linting OpenAPI), API lifecycle (design → review → publish → deprecate → sunset), siapa yang punya otoritas approve breaking change
* [ ] 15.4 Menyatukan Module 1-14 jadi satu sistem koheren — code review penuh terhadap Order Management API dengan lensa "apakah ini siap jadi standar tim?"

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Order Management API — Production-Grade, Portfolio-Ready.** Satu REST API lengkap yang menggabungkan seluruh skill Module 1-15, didesain, diimplementasikan, diamankan, didokumentasikan, diuji, dioptimasi, dan diobservasi seperti API yang benar-benar akan dipercaya klien freelance atau tim engineering nyata. Boleh diganti domain (bukan order management) kalau ada proyek kerja nyata yang lebih relevan untuk portofolio — struktur acceptance criteria tetap sama.

Acceptance Criteria:

* Resource model & URI mengikuti `standards/API_STANDARDS.md` sepenuhnya (naming, method, status code)
* Semua endpoint pakai DTO (request/response terpisah), tidak ada entity yang bocor ke response
* Validasi berlapis aktif (format + business rule), pesan error actionable
* Error contract konsisten sesuai RFC 9457 Problem Details di seluruh endpoint
* List endpoint punya pagination, filtering, sorting yang aman untuk dataset besar
* Minimal 1 breaking change didemonstrasikan lewat versioning + deprecation policy yang benar
* Authentication (JWT) & object-level authorization aktif dan terbukti (test membuktikan customer A tidak bisa akses data customer B)
* Rate limiting aktif di minimal 1 endpoint kritikal
* Dokumentasi OpenAPI lengkap & 100% sinkron dengan implementasi (Swagger UI bisa langsung dicoba)
* Test pyramid lengkap (unit, slice, integration Testcontainers) termasuk jalur gagal, bukan cuma happy path
* Minimal 1 optimasi performa dibuktikan dengan angka before/after (k6)
* Minimal 1 pola resilience aktif (idempotency key atau circuit breaker) dan terbukti lewat test/demo
* Observability aktif: structured logging + trace ID, metrics, health/readiness check
* Lulus API Review Checklist penuh dari `standards/API_STANDARDS.md`
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/15

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Anatomy HTTP request/response
