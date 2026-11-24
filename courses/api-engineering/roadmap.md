# Course Roadmap

## Course Information

* Course Name: API Engineering — HATEOAS, Rate Limiting Lanjut, GraphQL, WebSocket, dan Gateway
* Category: API Engineering (Backend, hands-on multi-protokol) — layer implementasi di bawah `courses/system-design` (yang membahas REST vs gRPC vs GraphQL di level *keputusan* arsitektur, Module 9-10) dan lanjutan langsung dari `courses/rest-api-design` + `courses/backend-fundamentals`
* Difficulty: Level 3 (sudah bisa desain REST API production-grade) → Level 4 (bisa membangun & mempertahankan permukaan API multi-protokol — REST, GraphQL, WebSocket — lengkap dengan gateway dan rate limiting production-grade)
* Estimated Duration: 5 modul, 3-5 lesson/modul, ~30-40 menit/lesson.
* Prerequisites: `courses/rest-api-design` (REST end-to-end, sudah mencakup pagination, idempotency key, ETag, versioning, deprecation, contract testing, rate limiting dasar) dan `courses/backend-fundamentals` Module 5 — course ini **tidak** mengulang materi tersebut.

---

# Catatan Scope (diperbarui 2026-11-22)

Draft awal (2026-08-07) punya 8 modul. Setelah `courses/rest-api-design` dan `courses/backend-fundamentals` dikerjakan, materi REST di draft lama sudah tercakup, jadi dibuang dari sini:

| Topik draft lama | Sudah dibahas di |
|---|---|
| Offset vs cursor pagination (old 1.1) | `rest-api-design` Module 7 (7.1, 7.2) |
| Idempotency key (old 1.3) | `rest-api-design` Module 13.1; `backend-fundamentals` Module 12 |
| Conditional request `ETag`/`If-Match` (old 1.4) | `rest-api-design` Module 12.1; `backend-fundamentals` Module 5 |
| Breaking vs non-breaking, strategi versioning, deprecation, contract testing Pact (old Module 2 penuh) | `rest-api-design` Module 8 (8.1-8.4) |
| Rate limiting dasar: token bucket + Redis, `429` + `Retry-After` (old 3.3, sebagian 3.1/3.2) | `rest-api-design` 9.4; `backend-fundamentals` Module 9 |

Yang **tetap** di course ini (belum dibahas di mana pun):

* HATEOAS / hypermedia (di `rest-api-design` hanya muncul sebagai level 3 Richardson Maturity Model, konsep saja, tanpa implementasi)
* Rate limiting lanjut: perbandingan algoritma penuh, sliding window dengan Lua, quota per tier
* GraphQL, WebSocket/SSE, API Gateway/BFF, capstone multi-protokol
* gRPC **dikeluarkan** dari course ini (2026-11-24) — dibahas penuh di `courses/grpc-elasticsearch`

Asumsi yang masih perlu dikonfirmasi user:

1. **Stack**: Java/Spring Boot (Spring for GraphQL, Spring WebSocket/STOMP).
2. **Gateway**: Kong atau Spring Cloud Gateway — belum diputuskan.
3. **Capstone** menggabungkan REST+WebSocket+Gateway; modul terberat, bisa dipecah jadi 2 tergantung kecepatan belajar.
4. Domain reuse: **Order Management API** dari `courses/rest-api-design`, supaya tidak bikin domain baru.

Referensi riset:

* [GraphQL spec](https://spec.graphql.org/) & [Spring for GraphQL docs](https://docs.spring.io/spring-graphql/reference/)
* *RESTful Web APIs* (Richardson & Amundsen) — HATEOAS/hypermedia
* [OWASP API Security Top 10](https://owasp.org/www-project-api-security/) — cross-ref `courses/cybersecurity`
* [Kong Gateway docs](https://docs.konghq.com/) / [Spring Cloud Gateway docs](https://docs.spring.io/spring-cloud-gateway/reference/)
* `standards/API_STANDARDS.md` & `standards/OPENAPI_STANDARDS.md`

---

# Learning Objectives

* [ ] Memahami & menerapkan HATEOAS/hypermedia dengan jujur soal kapan bermanfaat vs overkill
* [ ] Mengimplementasikan rate limiting lanjut (sliding window, Lua atomic, quota per tier) dengan Redis
* [ ] Membangun API GraphQL: schema design, resolver, mengatasi N+1 dengan DataLoader
* [ ] Membangun real-time API dengan WebSocket/STOMP, memahami trade-off vs Server-Sent Events
* [ ] Mengonfigurasi API Gateway: routing, auth di edge, transformation, BFF pattern
* [ ] Menyelesaikan capstone: satu domain diekspos lewat REST + WebSocket di belakang gateway, portfolio-ready

---

# Course Modules

## Module 1 — HATEOAS & Hypermedia

Description: Satu-satunya pola REST lanjut yang belum tersentuh di course lain. Fokus pada nilai praktis, bukan dogma.

Lessons:

* [ ] 1.1 HATEOAS & hypermedia — konsep, format (HAL, JSON:API), kapan benar-benar bermanfaat, kapan overkill
* [ ] 1.2 Implementasi HATEOAS dengan Spring HATEOAS pada Order Management API (link `self`, `cancel`, `items` berdasarkan state order)

Mini Project: Tambahkan link hypermedia ke `GET /orders/{id}` yang berubah sesuai status order (mis. link `cancel` hilang saat order sudah `SHIPPED`).

Status: Not Started

---

## Module 2 — Rate Limiting Lanjut

Description: Lanjutan dari rate limiting dasar (`rest-api-design` 9.4). Fokus pada akurasi algoritma, atomicity di Redis, dan quota per klien.

Lessons:

* [ ] 2.1 Perbandingan algoritma: token bucket, leaky bucket, fixed window, sliding window log/counter — trade-off akurasi vs memori
* [ ] 2.2 Sliding window dengan Redis + Lua script (atomic) — menutup race condition
* [ ] 2.3 Per-client quota & tier (free vs paid) — skema quota di gateway vs di aplikasi

Mini Project: Implementasikan rate limiter sliding window (Lua) untuk 1 endpoint dengan 2 tier, uji dengan load test (k6, reuse dari `rest-api-design` 12.5) untuk membuktikan limit ditegakkan di bawah concurrency.

Status: Not Started

---

## Module 3 — GraphQL Fundamentals & Implementasi

Description: Alternatif REST untuk client yang butuh fleksibilitas query — dan jebakan performanya.

Lessons:

* [ ] 3.1 Schema design: type, query, mutation, subscription — prinsip schema-first
* [ ] 3.2 Resolver di Spring for GraphQL, menghubungkan ke service/repository yang sudah ada
* [ ] 3.3 N+1 problem di GraphQL dan solusinya dengan DataLoader/batching
* [ ] 3.4 Query complexity & depth limiting — mencegah query jahat/mahal

Mini Project: Bangun 1 GraphQL API di atas domain yang sudah ada, termasuk DataLoader untuk N+1 yang sengaja dibuat dulu lalu diperbaiki.

Status: Not Started

---

## Module 4 — WebSocket & Real-time API

Description: Untuk kasus yang benar-benar butuh push dari server, bukan polling.

Lessons:

* [ ] 4.1 WebSocket protocol dasar: handshake, frame, full-duplex vs HTTP request-response
* [ ] 4.2 STOMP over WebSocket dengan Spring — pub/sub topic, session management
* [ ] 4.3 Server-Sent Events (SSE) sebagai alternatif lebih ringan — kapan cukup SSE, kapan butuh WebSocket
* [ ] 4.4 Scaling WebSocket: sticky session, message broker eksternal (Redis pub/sub) untuk multi-instance

Mini Project: Notifikasi real-time update status order via WebSocket/STOMP, uji dengan lebih dari 1 client bersamaan.

Status: Not Started

---

## Module 5 — API Gateway, Edge Concerns & Capstone

Description: Titik masuk tunggal yang menyatukan semua protokol, lalu digabung jadi capstone.

Lessons:

* [ ] 5.1 Peran API Gateway: routing, auth di edge, request/response transformation
* [ ] 5.2 Setup gateway (Kong atau Spring Cloud Gateway) di depan service REST
* [ ] 5.3 Backend-for-Frontend (BFF): agregasi beberapa service jadi satu kontrak untuk client tertentu
* [ ] 5.4 Auth & rate limiting di gateway vs di service — kapan masing-masing tepat (cross-ref OWASP API Security Top 10)
* [ ] 5.5 Capstone: satukan REST + GraphQL + WebSocket (real-time) di belakang gateway, kontrak terdokumentasi (OpenAPI + schema GraphQL)

Mini Project: Pasang gateway di depan minimal 2 service (REST + GraphQL), terapkan auth dan rate limiting di gateway.

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Permukaan API Multi-Protokol untuk Satu Domain** — Order Management diekspos lewat REST untuk klien eksternal, GraphQL untuk klien fleksibel, dan WebSocket untuk notifikasi real-time, semuanya di belakang API Gateway dengan rate limiting.

Acceptance Criteria:

* Kontrak REST terdokumentasi (OpenAPI) dan mengikuti `standards/API_STANDARDS.md`
* GraphQL schema terdokumentasi, N+1 teratasi dengan DataLoader
* WebSocket/STOMP endpoint berfungsi untuk minimal 1 skenario notifikasi real-time
* API Gateway menegakkan rate limiting dan auth secara nyata (dibuktikan dengan test/demo)
* Versioning, idempotency key, dan pola dari `rest-api-design` diterapkan di sistem gabungan
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/5

Overall Completion: 0%

Current Module: Belum dimulai — roadmap sudah dipangkas 2026-11-22 (materi REST yang tumpang tindih dihapus), menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — HATEOAS & hypermedia
