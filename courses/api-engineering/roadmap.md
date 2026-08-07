# Course Roadmap

## Course Information

* Course Name: API Engineering — REST Lanjut, gRPC, GraphQL, WebSocket, dari Kontrak sampai Gateway
* Category: API Engineering (Backend, hands-on multi-protocol) — layer implementasi di bawah `courses/system-design` (yang membahas REST vs gRPC vs GraphQL di level *keputusan* arsitektur, Module 9-10) dan lanjutan langsung dari `courses/backend-fundamentals` Module 5 (REST production-grade dasar)
* Difficulty: Level 2 (sudah bisa desain REST API production-grade ala Module 5 Backend Fundamentals) → Level 4 (bisa membangun & mempertahankan permukaan API multi-protokol — REST, gRPC, GraphQL, WebSocket — lengkap dengan gateway, versioning, dan rate limiting production-grade)
* Estimated Duration: 8 modul, 3-4 lesson/modul, ~30-40 menit/lesson. Perkiraan ≈ 3-4 bulan pace sedang.
* Prerequisites: `courses/backend-fundamentals` Module 5 (REST API Production-Grade) — course ini **tidak** mengulang dasar desain resource/HTTP method/status code, langsung lanjut ke pola lanjutan dan protokol lain.

---

# Catatan Scope (draft — menunggu approval & kalibrasi user)

Draft ini dibuat atas permintaan eksplisit user (2026-08-07) untuk mengisi gap "API Engineering standalone (gRPC/GraphQL/WebSocket/Rate Limiting hands-on)" yang teridentifikasi saat memetakan daftar besar topik ke course yang sudah ada. **Belum ada sesi kalibrasi** — asumsi di bawah ini perlu dikonfirmasi/dikoreksi user sebelum roadmap final:

1. **Tidak menduplikasi `courses/system-design`**: system-design Module 9-10 membahas REST vs GraphQL vs gRPC di level *trade-off keputusan arsitektur* (kapan pilih apa). Course ini adalah lapisan *implementasi*: benar-benar menulis service gRPC, schema GraphQL, koneksi WebSocket yang bekerja.
2. **Tidak menduplikasi `courses/backend-fundamentals` Module 5**: modul itu sudah cover desain resource REST dasar, validation, OpenAPI. Course ini mulai dari pola REST *lanjut* (Module 1) baru masuk protokol lain.
3. **Asumsi stack**: Java/Spring Boot untuk semua hands-on (`grpc-spring-boot-starter`, Spring for GraphQL, Spring WebSocket/STOMP) — konsisten dengan stack utama repo. Perlu dikonfirmasi apakah ini sesuai ekspektasi.
4. **Asumsi API Gateway**: menggunakan Kong atau Spring Cloud Gateway sebagai contoh hands-on (belum diputuskan mana yang jadi primary — perlu keputusan user, karena keduanya punya filosofi berbeda: Kong = gateway dedicated di luar aplikasi, Spring Cloud Gateway = gateway sebagai service Spring biasa).
5. **Module 8 (capstone)** menggabungkan REST+gRPC+WebSocket+Gateway dalam satu sistem kecil — ini modul terberat, mungkin perlu dipecah jadi 2 modul tergantung kecepatan belajar aktual.

Referensi riset (untuk disusun jadi roadmap final saat approval):

* [gRPC official docs](https://grpc.io/docs/) & [Protocol Buffers docs](https://protobuf.dev/) — fondasi Module 4
* [GraphQL spec](https://spec.graphql.org/) & [Spring for GraphQL docs](https://docs.spring.io/spring-graphql/reference/) — fondasi Module 5
* *RESTful Web APIs* (Leonard Richardson & Mike Amundsen) — pola REST lanjut (HATEOAS, hypermedia) Module 1
* [OWASP API Security Top 10](https://owasp.org/www-project-api-security/) — cross-ref ke `courses/cybersecurity`, dipakai di Module 3 & 7
* [Kong Gateway docs](https://docs.konghq.com/) / [Spring Cloud Gateway docs](https://docs.spring.io/spring-cloud-gateway/reference/) — Module 7
* [Stripe API design guide](https://stripe.com/blog/api-versioning) — referensi praktisi nyata untuk Module 2 (versioning)
* `standards/API_STANDARDS.md` & `standards/OPENAPI_STANDARDS.md` — kontrak konvensi repo ini

---

# Learning Objectives

* [ ] Menguasai pola REST lanjut: cursor vs offset pagination, HATEOAS, idempotency key, conditional request (`ETag`/`If-Match`)
* [ ] Merancang strategi API versioning & contract evolution, termasuk consumer-driven contract testing
* [ ] Mengimplementasikan rate limiting production-grade (token bucket/sliding window) dengan Redis
* [ ] Membangun service & client gRPC dengan Protocol Buffers, memahami unary vs streaming RPC
* [ ] Membangun API GraphQL: schema design, resolver, mengatasi N+1 problem dengan DataLoader
* [ ] Membangun real-time API dengan WebSocket/STOMP, memahami trade-off vs Server-Sent Events
* [ ] Mengonfigurasi API Gateway: routing, auth di edge, request/response transformation, BFF pattern
* [ ] Menyelesaikan capstone: satu domain diekspos lewat REST (eksternal) + gRPC (internal) + WebSocket (real-time), di belakang gateway, portfolio-ready

---

# Course Modules

## Module 1 — Pola REST Lanjut

Description: Naik dari REST dasar (Backend Fundamentals Module 5) ke pola yang dipakai API production skala besar.

Lessons:

* [ ] 1.1 Pagination: offset vs cursor-based, kapan masing-masing tepat untuk dataset besar
* [ ] 1.2 HATEOAS & hypermedia — kapan benar-benar bermanfaat, kapan overkill
* [ ] 1.3 Idempotency key untuk operasi non-idempotent (`POST` pembayaran, dsb.)
* [ ] 1.4 Conditional request: `ETag`, `If-Match`/`If-None-Match`, optimistic concurrency di level HTTP

Mini Project: Tambahkan cursor-based pagination dan idempotency key ke satu endpoint REST yang sudah ada (dari course lain atau kerjaan).

Status: Not Started

---

## Module 2 — API Versioning & Contract Evolution

Description: Bagaimana mengubah API tanpa merusak klien yang sudah bergantung padanya — masalah nyata paling sering diremehkan tim backend.

Lessons:

* [ ] 2.1 Breaking vs non-breaking change — definisi konkret, bukan tebak-tebakan
* [ ] 2.2 Strategi versioning: URI (`/v1/`), header, content negotiation — trade-off masing-masing
* [ ] 2.3 Deprecation policy: sunset header, komunikasi ke konsumen API, migration window
* [ ] 2.4 Consumer-driven contract testing (Pact) — mencegah breaking change lolos ke production

Mini Project: Buat 1 breaking change pada API Module 1, jalankan lewat proses versioning + deprecation policy yang benar, lengkap contract test.

Status: Not Started

---

## Module 3 — Rate Limiting & Throttling

Description: Melindungi API dari abuse dan memastikan fair usage antar klien — skill yang langsung relevan untuk API publik freelance.

Lessons:

* [ ] 3.1 Algoritma rate limiting: token bucket, leaky bucket, sliding window log/counter — trade-off akurasi vs memori
* [ ] 3.2 Implementasi rate limiter dengan Redis (atomic counter, Lua script untuk sliding window)
* [ ] 3.3 Response contract saat limit tercapai: `429`, header `Retry-After`/`X-RateLimit-*`
* [ ] 3.4 Per-client quota & tier (free vs paid) — desain skema quota di gateway vs di aplikasi

Mini Project: Implementasikan rate limiter berbasis Redis (sliding window) untuk 1 endpoint, uji dengan load test sederhana untuk memverifikasi limit benar-benar ditegakkan.

Status: Not Started

---

## Module 4 — gRPC Fundamentals & Implementasi

Description: Protokol RPC berperforma tinggi yang jadi standar komunikasi internal service-to-service di banyak perusahaan skala besar (Google, Netflix).

Lessons:

* [ ] 4.1 Protocol Buffers: schema-first design, tipe data, backward/forward compatibility field rules
* [ ] 4.2 Unary RPC: implementasi service & client gRPC di Spring Boot
* [ ] 4.3 Streaming RPC: server streaming, client streaming, bidirectional streaming — use case masing-masing
* [ ] 4.4 gRPC vs REST: benchmark performa nyata (latency, payload size), kapan gRPC benar-benar menang

Mini Project: Bangun 1 service gRPC (unary + 1 streaming method) dengan Spring Boot, plus client yang memanggilnya, dan bandingkan latency-nya dengan endpoint REST setara.

Status: Not Started

---

## Module 5 — GraphQL Fundamentals & Implementasi

Description: Alternatif REST untuk client yang butuh fleksibilitas query — dan jebakan performanya yang sering tidak disadari pemula.

Lessons:

* [ ] 5.1 Schema design: type, query, mutation, subscription — prinsip schema-first
* [ ] 5.2 Resolver di Spring for GraphQL, menghubungkan ke service/repository yang sudah ada
* [ ] 5.3 N+1 problem di GraphQL dan solusinya dengan DataLoader/batching
* [ ] 5.4 Query complexity & depth limiting — mencegah query jahat/mahal dari klien

Mini Project: Bangun 1 GraphQL API di atas domain yang sudah ada (reuse dari Module 1-4), termasuk DataLoader untuk mengatasi N+1 yang sengaja dibuat dulu lalu diperbaiki.

Status: Not Started

---

## Module 6 — WebSocket & Real-time API

Description: Untuk kasus yang benar-benar butuh push dari server, bukan polling — chat, notifikasi, live update.

Lessons:

* [ ] 6.1 WebSocket protocol dasar: handshake, frame, full-duplex vs HTTP request-response
* [ ] 6.2 STOMP over WebSocket dengan Spring — pub/sub topic, session management
* [ ] 6.3 Server-Sent Events (SSE) sebagai alternatif lebih ringan — kapan cukup SSE, kapan butuh WebSocket penuh
* [ ] 6.4 Scaling WebSocket: sticky session, message broker eksternal (Redis pub/sub) untuk multi-instance

Mini Project: Bangun fitur notifikasi real-time sederhana (misal: update status order) via WebSocket/STOMP, uji dengan lebih dari 1 client terhubung bersamaan.

Status: Not Started

---

## Module 7 — API Gateway & Edge Concerns

Description: Titik masuk tunggal yang menyatukan semua protokol di atas untuk konsumen eksternal — dan tempat menegakkan kebijakan lintas-service.

Lessons:

* [ ] 7.1 Peran API Gateway: routing, auth di edge, request/response transformation
* [ ] 7.2 Setup gateway (Kong atau Spring Cloud Gateway) di depan service REST/gRPC dari modul sebelumnya
* [ ] 7.3 Backend-for-Frontend (BFF) pattern: agregasi beberapa service jadi satu kontrak untuk client tertentu
* [ ] 7.4 Auth & rate limiting di level gateway vs di level service — kapan masing-masing tepat (cross-ref OWASP API Security Top 10)

Mini Project: Pasang gateway di depan minimal 2 service dari modul sebelumnya (REST + gRPC/GraphQL), terapkan auth dan rate limiting di level gateway.

Status: Not Started

---

## Module 8 — Capstone: Permukaan API Multi-Protokol

Description: Menyatukan semua modul jadi satu sistem nyata — persis yang akan ditemui di backend production skala menengah-besar.

Lessons:

* [ ] 8.1 Desain 1 domain (misal: order management) yang diekspos lewat REST (eksternal, klien pihak ketiga), gRPC (internal, service-to-service), dan WebSocket (notifikasi real-time)
* [ ] 8.2 Pasang API Gateway di depan REST & WebSocket, dokumentasikan kontrak (OpenAPI untuk REST, `.proto` untuk gRPC)
* [ ] 8.3 Terapkan versioning, rate limiting, dan idempotency key dari modul-modul sebelumnya di sistem gabungan ini

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Permukaan API Multi-Protokol untuk Satu Domain** — pilih satu domain (order management, booking, atau domain lain yang relevan dengan portofolio Anda), ekspos lewat REST untuk klien eksternal, gRPC untuk komunikasi internal antar-service, dan WebSocket untuk notifikasi real-time, semuanya di belakang API Gateway dengan rate limiting dan versioning yang benar.

Acceptance Criteria:

* Kontrak REST terdokumentasi lengkap (OpenAPI) dan mengikuti `standards/API_STANDARDS.md`
* Service gRPC punya `.proto` yang jelas, mendukung minimal 1 streaming method
* WebSocket/STOMP endpoint berfungsi untuk minimal 1 skenario notifikasi real-time
* API Gateway menegakkan rate limiting dan auth secara nyata (dibuktikan dengan test/demo, bukan konfigurasi yang belum diuji)
* Versioning strategy diterapkan dan didemonstrasikan lewat 1 breaking change yang ditangani dengan benar
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/8

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Pagination: offset vs cursor-based
