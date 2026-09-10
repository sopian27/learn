# Course Roadmap

## Course Information

* Course Name: gRPC & Elasticsearch — dari 0 sampai Production
* Category: Backend Engineering (protokol komunikasi antar-service + search/read model) — layer implementasi di bawah `courses/system-design` (trade-off arsitektur REST/gRPC/event-driven, Module 6/9/10) dan berdampingan dengan `courses/api-engineering` (multi-protokol API surface)
* Difficulty: Level 2 (sudah bisa REST production-grade ala `courses/backend-fundamentals` Module 5) → Level 4 (bisa merancang, mengimplementasikan, dan troubleshoot sistem gRPC+Elasticsearch+Kafka production)
* Estimated Duration: 11 modul, 3-4 lesson/modul, ~30-40 menit/lesson. Perkiraan ≈ 4-5 bulan pace sedang.
* Prerequisites: `courses/backend-fundamentals` (Java/Spring Boot production-grade), pemahaman dasar PostgreSQL dan Kafka (`courses/system-design` Module 10 event-driven) membantu tapi tidak wajib — dijelaskan ulang secukupnya di Module 11.

---

# Catatan Scope (draft — menunggu approval user)

Dibuat atas permintaan eksplisit user (2026-09-11): course gRPC + Elasticsearch dari fundamental sampai production, stack Java/Spring Boot, ditutup dengan project integrasi REST+gRPC+PostgreSQL+Elasticsearch+Kafka di Kubernetes.

Keputusan cakupan (dikonfirmasi via AskUserQuestion 2026-09-11):

1. **Overlap dengan `courses/api-engineering` Module 4 (gRPC Fundamentals & Implementasi, draft belum approved/mulai)** — diselesaikan dengan course ini berdiri sendiri, full depth. `courses/api-engineering` Module 4 di-slim jadi cross-reference ke course ini (lihat perubahan di file itu), supaya isi tidak dobel.
2. **Elasticsearch belum ada course sama sekali di repo** — satu-satunya singgungan sebelumnya cuma "Elasticsearch" sebagai komponen ELK di `courses/observability` Module 4.3 (log storage/Kibana), beda konteks total dari course ini (search engine sebagai read model aplikasi, Query DSL, relevance tuning). Tidak ada overlap.
3. **Status slot**: didraft dulu menunggu approval, TIDAK langsung jadi "Course Aktif" ketiga — konsisten kebijakan cap 2-course-aktif (`progress/roadmap.md` update 2026-08-07). Kalau di-approve, ambil alih slot "Mastering Claude" yang sudah Selesai.

Referensi riset (untuk disusun jadi materi lesson saat mulai):

* [gRPC official docs](https://grpc.io/docs/) & [Protocol Buffers docs](https://protobuf.dev/) — fondasi Module 1-2
* [grpc-spring-boot-starter (yidongnan)](https://github.com/grpc-ecosystem/grpc-spring) — implementasi gRPC di Spring Boot
* [gRPC status codes](https://grpc.io/docs/guides/status-codes/) & [google.rpc.Status](https://cloud.google.com/apis/design/errors) — Module 3
* [gRPC load balancing guide](https://grpc.io/docs/guides/load-balancing/) & xDS/Envoy docs — Module 5
* [Elasticsearch official guide](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html) — fondasi Module 6-10
* [Elasticsearch: The Definitive Guide](https://www.elastic.co/guide/en/elasticsearch/guide/current/index.html) — konsep inverted index, relevance (BM25)
* [Spring Data Elasticsearch docs](https://docs.spring.io/spring-data/elasticsearch/reference/) — integrasi Module 6-9
* [Elasticsearch Index Lifecycle Management](https://www.elastic.co/guide/en/elasticsearch/reference/current/index-lifecycle-management.html) — Module 10
* [Debezium docs](https://debezium.io/documentation/) (CDC) & [transactional outbox pattern (microservices.io)](https://microservices.io/patterns/data/transactional-outbox.html) — Module 11 consistency
* [Confluent Kafka docs](https://docs.confluent.io/) — async indexing pipeline Module 11
* `standards/API_STANDARDS.md`, `courses/system-design/roadmap.md` (Module 6 monolith vs microservices, Module 10 event-driven) — cross-ref arsitektur

---

# Learning Objectives

* [ ] Menjelaskan masalah yang diselesaikan gRPC dan kapan gRPC menang dibanding REST (dan kapan tidak)
* [ ] Mendesain `.proto` schema-first (service, message, backward/forward compatibility) dan mengimplementasikan keempat pola RPC (unary, server/client/bidirectional streaming)
* [ ] Menerapkan error handling, deadline/timeout/retry, metadata/auth, dan interceptor gRPC production-grade
* [ ] Menyiapkan gRPC untuk skala production: load balancing, service discovery, observability, debugging
* [ ] Menjelaskan arsitektur cluster Elasticsearch (node, index, shard, replica) dan model inverted index
* [ ] Merancang mapping, melakukan indexing/CRUD, dan bulk indexing di Elasticsearch
* [ ] Menulis Query DSL untuk full-text search, filtering, sorting, pagination, dan aggregation
* [ ] Memahami relevance scoring (BM25) dan tuning relevansi hasil pencarian
* [ ] Mengoperasikan Elasticsearch production: performance tuning, index lifecycle, reindexing tanpa downtime, HA/disaster recovery
* [ ] Merancang dan membangun sistem Java/Spring Boot: REST (public API) + gRPC (internal) + PostgreSQL (source of truth) + Elasticsearch (search/read model) + Kafka (async indexing), lengkap dengan consistency strategy, failure handling, observability, testing, security, dan deployment ke Kubernetes

---

# Course Modules

## Module 1 — gRPC Fundamentals

Description: Masalah nyata yang diselesaikan gRPC, kenapa Google/Netflix pakai untuk komunikasi internal, dan fondasi Protocol Buffers.

Lessons:

* [ ] 1.1 Masalah yang diselesaikan gRPC: latency, payload size, strong typing lintas bahasa — studi kasus microservice internal
* [ ] 1.2 REST vs gRPC: trade-off nyata (human-readable vs binary, browser support, tooling), bukan "gRPC selalu lebih cepat"
* [ ] 1.3 HTTP/2 sebagai fondasi gRPC: multiplexing, header compression — kenapa ini yang bikin streaming mungkin
* [ ] 1.4 Protocol Buffers: schema-first design, serialization, `.proto` (service, message), generate stub dengan `protoc`

Mini Project: Definisikan `.proto` untuk satu domain sederhana (misal: product lookup), generate stub Java, implementasikan 1 unary RPC end-to-end (client + server Spring Boot).

Status: Not Started

---

## Module 2 — Pola Streaming RPC

Description: Empat pola komunikasi gRPC dan kapan masing-masing tepat dipakai.

Lessons:

* [ ] 2.1 Unary RPC — recap dari Module 1, request/response tunggal
* [ ] 2.2 Server streaming — server kirim banyak response untuk 1 request (contoh: live feed, hasil pencarian besar)
* [ ] 2.3 Client streaming — client kirim banyak request, server 1 response (contoh: upload data batch)
* [ ] 2.4 Bidirectional streaming — full-duplex, use case chat/real-time collaboration, perbedaan dari WebSocket

Mini Project: Implementasikan keempat pola RPC untuk 1 domain (misal: notifikasi order), termasuk 1 skenario bidirectional streaming nyata.

Status: Not Started

---

## Module 3 — Error Handling, Deadline & Retry

Description: gRPC production wajib menangani kegagalan dengan benar — ini yang membedakan demo dari sistem production.

Lessons:

* [ ] 3.1 Status code gRPC dan `google.rpc.Status` — pemetaan error domain ke status code yang benar
* [ ] 3.2 Deadline vs timeout — propagasi deadline lintas service call, kenapa ini beda dari timeout biasa
* [ ] 3.3 Retry policy: exponential backoff, retryable vs non-retryable error, bahaya retry pada operasi non-idempotent
* [ ] 3.4 Client-side error handling: interceptor untuk retry otomatis, circuit breaker sederhana

Mini Project: Tambahkan deadline propagation + retry policy ke service dari Module 2, simulasikan service down/lambat, buktikan retry & deadline bekerja lewat test.

Status: Not Started

---

## Module 4 — Metadata, Auth & Interceptor

Description: Mengamankan dan mengobservasi panggilan gRPC di production tanpa mengubah business logic tiap service.

Lessons:

* [ ] 4.1 Metadata gRPC: cara kirim data di luar payload (headers-equivalent), use case (trace ID, tenant ID)
* [ ] 4.2 Autentikasi: token-based (JWT via metadata) vs mTLS — trade-off kompleksitas vs keamanan
* [ ] 4.3 Server & client interceptor: cross-cutting concern (logging, auth check, metrics) tanpa duplikasi kode
* [ ] 4.4 Interceptor chain — urutan eksekusi, error propagation dalam chain

Mini Project: Bangun auth interceptor (validasi token dari metadata) + logging interceptor terstruktur, pasang di server dari Module 3.

Status: Not Started

---

## Module 5 — gRPC di Skala Production

Description: Dari 1 service ke banyak instance — load balancing, discovery, observability, debugging.

Lessons:

* [ ] 5.1 Load balancing gRPC: client-side load balancing vs proxy (Envoy) — kenapa load balancing L4 biasa gagal untuk gRPC (HTTP/2 multiplexing)
* [ ] 5.2 Service discovery: integrasi dengan Kubernetes DNS/headless service, xDS API (pengantar)
* [ ] 5.3 Observability: distributed tracing (OpenTelemetry) dan metrics untuk gRPC call, korelasi dengan trace ID lintas service
* [ ] 5.4 Debugging & performance: `grpcurl`, server reflection, benchmark gRPC vs REST (latency, payload size) di beban nyata

Mini Project: Deploy 2+ instance service gRPC, uji load balancing, tambahkan tracing, ukur & bandingkan latency dengan endpoint REST setara.

Status: Not Started

---

## Module 6 — Elasticsearch Fundamentals

Description: Apa itu Elasticsearch, kapan tepat dipakai (dan kapan tidak — PostgreSQL full-text search kadang cukup), dan arsitektur cluster.

Lessons:

* [ ] 6.1 Apa itu Elasticsearch dan kapan menggunakannya — search/read model vs source of truth, bukan pengganti database relasional
* [ ] 6.2 Cluster, node, index, shard, replica — bagaimana data terdistribusi dan kenapa jumlah shard sulit diubah setelah index dibuat
* [ ] 6.3 Document model dan inverted index — kenapa Elasticsearch cepat untuk full-text search dibanding `LIKE '%...%'`

Mini Project: Jalankan cluster Elasticsearch (Docker Compose), eksplorasi cluster health & index API, buat 1 index manual dan amati distribusi shard.

Status: Not Started

---

## Module 7 — Mapping, Indexing & CRUD

Description: Mendesain struktur data di Elasticsearch dan operasi dasar mengisinya.

Lessons:

* [ ] 7.1 Mapping dan data types — dynamic mapping vs explicit mapping, kenapa dynamic mapping berbahaya di production
* [ ] 7.2 Indexing dan operasi CRUD — index/get/update/delete document, versioning optimistic concurrency
* [ ] 7.3 Bulk indexing — Bulk API, kenapa bulk jauh lebih efisien dari indexing satu-satu

Mini Project: Desain mapping eksplisit untuk 1 domain nyata (misal: product catalog), bulk index sample data (ribuan dokumen), verifikasi lewat query sederhana.

Status: Not Started

---

## Module 8 — Query DSL & Full-Text Search

Description: Bahasa query Elasticsearch dan mekanisme di balik pencarian teks.

Lessons:

* [ ] 8.1 Struktur Query DSL: `query` vs `filter` context — kenapa filter context lebih cepat (cacheable, tidak scoring)
* [ ] 8.2 Full-text search: `match`, `match_phrase`, keyword vs text field, analyzer (standard, custom tokenizer/filter)
* [ ] 8.3 Filtering, sorting, pagination — `term`/`range` filter, `sort`, `from`/`size` vs `search_after` untuk deep pagination

Mini Project: Bangun search endpoint (Spring Boot REST) untuk domain Module 7 — multi-field query, filter kombinasi, pagination yang benar untuk dataset besar.

Status: Not Started

---

## Module 9 — Aggregation & Relevance Scoring

Description: Analitik langsung dari data yang sama dipakai untuk search, dan cara kerja ranking hasil pencarian.

Lessons:

* [ ] 9.1 Aggregation: bucket (terms, date histogram), metric (avg, sum, stats), nested/pipeline aggregation
* [ ] 9.2 Relevance scoring: algoritma BM25 — field length, term frequency, inverse document frequency secara intuitif
* [ ] 9.3 Tuning relevansi: field boosting, `function_score`, kapan relevansi default sudah cukup vs perlu tuning manual

Mini Project: Bangun faceted search (filter berbasis aggregation, seperti e-commerce category/price range) + tuning boosting untuk 1 skenario query yang hasilnya "terasa salah" secara default.

Status: Not Started

---

## Module 10 — Elasticsearch Production Operations

Description: Menjaga cluster tetap sehat, cepat, dan bisa pulih dari kegagalan.

Lessons:

* [ ] 10.1 Performance tuning: shard sizing (terlalu banyak/sedikit shard sama-sama buruk), refresh interval, query/filter cache
* [ ] 10.2 Index lifecycle management & reindexing tanpa downtime — alias swapping pattern (index baru → reindex → swap alias)
* [ ] 10.3 High availability & disaster recovery: replica untuk failover, snapshot/restore, pengantar cross-cluster replication

Mini Project: Implementasikan reindex tanpa downtime pakai alias (ubah mapping domain Module 7, migrasi data, swap alias tanpa app downtime).

Status: Not Started

---

## Module 11 — Capstone: Sistem Search-Enabled dengan gRPC & Elasticsearch

Description: Menyatukan semua modul jadi 1 sistem Java/Spring Boot production-realistis — persis pola yang dipakai backend skala menengah-besar untuk fitur search.

Lessons:

* [ ] 11.1 Arsitektur sistem: REST (public API) + gRPC (internal service-to-service) + PostgreSQL (source of truth) + Elasticsearch (search/read model) + Kafka (async indexing) — kenapa Elasticsearch tidak pernah jadi source of truth
* [ ] 11.2 Consistency & failure handling: pola outbox/CDC untuk sinkronisasi PostgreSQL → Kafka → Elasticsearch, menangani index lag dan pesan gagal (DLQ, retry)
* [ ] 11.3 Observability & testing: tracing lintas REST→gRPC→Kafka→Elasticsearch, integration test dengan Testcontainers (PostgreSQL, Kafka, Elasticsearch)
* [ ] 11.4 Security & deployment: auth di REST & gRPC, deploy seluruh sistem (app + Kafka + Elasticsearch) ke Kubernetes

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Search-Enabled Microservice** — satu domain (misal: product catalog, job listing, atau domain relevan portofolio Anda) diekspos lewat REST untuk klien eksternal, gRPC untuk komunikasi internal antar-service, ditulis ke PostgreSQL sebagai source of truth, diindex asinkron ke Elasticsearch lewat Kafka untuk fitur search, dan di-deploy ke Kubernetes.

Acceptance Criteria:

* REST API publik terdokumentasi (OpenAPI), mengikuti `standards/API_STANDARDS.md`
* Service gRPC internal punya `.proto` jelas, minimal 1 streaming method, deadline+retry diterapkan
* Data mengalir PostgreSQL → Kafka → Elasticsearch secara asinkron (outbox/CDC pattern), dengan strategi menangani index lag dan pesan gagal
* Search endpoint Elasticsearch: full-text + filter + aggregation (faceted search), relevansi sudah di-tuning untuk minimal 1 skenario nyata
* Observability: tracing lintas REST→gRPC→Kafka→Elasticsearch, bisa menelusuri 1 request dari masuk sampai terindex
* Integration test dengan Testcontainers (PostgreSQL, Kafka, Elasticsearch) membuktikan consistency & failure handling bekerja, bukan cuma happy path
* Deploy jalan di Kubernetes (manifest/Helm), didemonstrasikan
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/11

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Masalah yang diselesaikan gRPC
