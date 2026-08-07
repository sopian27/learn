# Course Roadmap

## Course Information

* Course Name: Software & System Architecture — dari Backend Developer ke Software Architect
* Category: Software/System Architecture — mengisi slot **"System Design Mastery"** di Master Goal (`progress/roadmap.md`)
* Difficulty: Level 2 (sudah bisa bikin REST API & service, tapi belum pernah mendesain arsitektur sistem secara sadar) → Level 4 (bisa membuat keputusan arsitektur untuk sistem skala besar, mendokumentasikannya, dan mempertahankannya di depan stakeholder/interviewer)
* Estimated Duration: 14 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`). Perkiraan ≈ 5-7 bulan dengan pace sedang.
* Prerequisites: Pengalaman kerja Java/Spring Boot (bisa bikin REST API, CRUD, tahu dasar database) — idealnya sejalan dengan atau setelah `courses/backend-fundamentals/roadmap.md`, karena banyak lesson di sini mempraktikkan ulang kode dari sana di level yang lebih tinggi (bukan "bagaimana menulis service", tapi "bagaimana memutuskan bentuk sistemnya").

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda (2026-08-06):

1. **Scope yang dipilih**: **Software/System Architecture** — arsitektur untuk satu sistem/platform (layering, Clean/Hexagonal Architecture, DDD, monolith-vs-microservices, C4 model, ADR, batas API/data) — bukan Enterprise/Solution Architecture penuh (TOGAF, tata kelola lintas-organisasi antar-puluhan-sistem). Kalau di titik tertentu nanti Anda butuh sisi enterprise/governance, itu jadi perluasan course ini, bukan course terpisah dari awal.
2. **Posisi di Master Goal**: Course ini secara eksplisit **menggantikan/mengisi** gap "System Design Mastery" yang sudah tercatat di `progress/roadmap.md` sejak sebelum permintaan ini — jadi cakupannya sengaja digabung: pola arsitektur project-level (Module 1-6) **dan** pola desain sistem skala besar (load balancing, sharding, CAP, message queue, distributed transaction — Module 7-11) yang tadinya jadi deskripsi gap tersebut. Tidak ada course "System Design Mastery" terpisah lagi — ini course-nya.
3. **Urutan**: Menunggu keputusan final Anda soal urutan vs `courses/backend-fundamentals` (lihat pembaruan `progress/roadmap.md`). Secara desain, course ini paling berguna **setelah atau paralel dengan** Backend Fundamentals Module 4 (Spring Core) — karena Module 3 course ini (Clean/Hexagonal Architecture) mengasumsikan Anda sudah nyaman dengan DI di Spring.
4. **Bukan** dari nol "apa itu REST API" — course ini mengasumsikan Anda sudah bisa membangun service, dan mengajarkan bagaimana **mendesain bentuk sistem sebelum menulis kode**, plus bagaimana mempertahankan keputusan itu.

Referensi riset yang dipakai menyusun roadmap ini: *Fundamentals of Software Architecture* (Mark Richards & Neal Ford) untuk kerangka architectural thinking & trade-off analysis, *Clean Architecture* (Robert C. Martin), *Implementing Domain-Driven Design* (Vaughn Vernon) & *Domain-Driven Design* (Eric Evans) untuk pilar DDD, *Building Microservices* (Sam Newman) untuk pola dekomposisi & komunikasi service, *Designing Data-Intensive Applications* (Martin Kleppmann) untuk pilar data-at-scale/CAP/consistency, C4 Model ([c4model.com](https://c4model.com), Simon Brown) untuk diagramming, ADR ([Michael Nygard's original post](https://cognitect.com/blog/2011/11/15/documenting-architecture-decisions) & [adr.github.io](https://adr.github.io)) untuk dokumentasi keputusan, struktur jalur [roadmap.sh — Software Architect](https://roadmap.sh/software-architect) & [roadmap.sh — System Design](https://roadmap.sh/system-design), serta `standards/*.md` repo ini (`MICROSERVICE_STANDARDS.md`, `CACHE_STANDARDS.md`, `MESSAGING_STANDARDS.md`, `RESILIENCY_STANDARDS.md`, `API_STANDARDS.md`) sebagai kontrak konvensi yang dipraktikkan langsung.

---

# Learning Objectives

* [ ] Membedakan architecture vs design, memahami "architecture characteristics" (-ilities) dan cara melakukan trade-off analysis, bukan mengklaim "satu arsitektur benar"
* [ ] Mendokumentasikan keputusan arsitektur secara profesional: Architecture Decision Record (ADR) dan diagram C4 (Context, Container, Component, Code)
* [ ] Menguasai Layered, Hexagonal (Ports & Adapters), dan Clean Architecture — menerapkannya untuk merestrukturisasi service Spring Boot yang sudah ada
* [ ] Menguasai Domain-Driven Design strategic (ubiquitous language, bounded context, context mapping) dan tactical (entity, value object, aggregate, domain event, repository)
* [ ] Mengambil keputusan monolith vs microservices vs modular monolith berdasarkan konteks nyata (ukuran tim, Conway's Law), bukan ikut tren
* [ ] Mendesain komunikasi antar-service: sync (REST/gRPC) vs async (event), saga pattern untuk distributed transaction, CQRS & Event Sourcing dasar
* [ ] Memahami data-at-scale: sharding/partitioning, replication, CAP/PACELC theorem, consistency model, strategi caching di level arsitektur
* [ ] Menerapkan pola reliability & scalability: load balancing, circuit breaker/bulkhead/retry, rate limiting, idempotency, backpressure
* [ ] Mendesain arsitektur event-driven skala produksi dengan Kafka: outbox pattern, exactly-once semantics, event schema evolution
* [ ] Mendesain arsitektur integrasi: API gateway, Backend-for-Frontend (BFF), strategi versioning, REST vs GraphQL vs gRPC trade-off
* [ ] Memahami arsitektur deployment cloud-native: container/K8s di level keputusan arsitektur, serverless trade-off, strategi multi-region/disaster recovery
* [ ] Menerapkan security architecture (defense in depth, zero trust dasar) dan observability architecture sebagai bagian dari desain, bukan tambahan akhir
* [ ] Mampu mengerjakan system design interview (URL shortener, rate limiter, chat system, news feed, ride-sharing, dst.) dengan kerangka yang terstruktur
* [ ] Menyelesaikan capstone: dokumen arsitektur lengkap (C4 + ADR + data & scalability plan) untuk satu sistem skala besar, portfolio-ready untuk klien freelance internasional

---

# Course Modules

## Module 1 — Fondasi Berpikir Arsitektur

Description: Titik awal semua modul lain — membedakan architecture dari design, memahami peran seorang architect, dan mulai mendokumentasikan keputusan secara profesional alih-alih hanya "di kepala" atau di chat Slack.

Lessons:

* [ ] Architecture vs Design: kenapa keputusan arsitektur sulit diubah (dan mana yang sebenarnya murah untuk diubah — "reversible vs irreversible decisions")
* [ ] Architecture characteristics ("-ilities"): scalability, availability, maintainability, testability — kenapa tidak mungkin memaksimalkan semuanya sekaligus
* [ ] Trade-off analysis: cara membandingkan 2-3 opsi arsitektur secara eksplisit, bukan memilih berdasarkan "yang paling familiar"
* [ ] Architecture Decision Record (ADR): kapan menulis satu, format Michael Nygard, contoh ADR nyata dari proyek open-source
* [ ] C4 Model: Context, Container, Component, Code — cara memilih level diagram yang tepat untuk audiens yang tepat (stakeholder vs engineer)

Mini Project: Ambil satu service Spring Boot yang sudah pernah Anda buat (dari course lain atau kerjaan) — buat 1 diagram C4 Context+Container dan 2 ADR untuk keputusan yang sebenarnya sudah terjadi tapi belum pernah didokumentasikan.

Status: Not Started

---

## Module 2 — Architectural Thinking & Quality Attribute Trade-offs

Description: Memperdalam Module 1 dengan kerangka *Fundamentals of Software Architecture* — bagaimana architect profesional benar-benar bernalar saat requirement saling bertentangan.

Lessons:

* [ ] Strategic vs tactical decisions — mengenali mana keputusan yang butuh architect, mana yang cukup keputusan tim
* [ ] Fitness functions: cara membuat "quality attribute" bisa diuji otomatis (bukan cuma slide), pengantar evolutionary architecture
* [ ] Studi kasus: bagaimana Netflix/Amazon/Uber memilih trade-off availability vs consistency untuk sistem mereka (real-world engineering blog synthesis, bukan copy-paste)
* [ ] Anti-pattern arsitektur umum: big ball of mud, accidental complexity, over-engineering "resume-driven architecture"
* [ ] Cara mempresentasikan keputusan arsitektur ke stakeholder non-teknis vs ke sesama engineer

Mini Project: Diberi 1 studi kasus (sistem checkout e-commerce dengan lonjakan traffic saat flash sale) — buat trade-off analysis tertulis untuk 3 opsi desain berbeda, pilih satu, dan justifikasi lewat ADR.

Status: Not Started

---

## Module 3 — Layered, Hexagonal & Clean Architecture

Description: Dari arsitektur "de facto" (Controller-Service-Repository standar Spring Boot) ke arsitektur yang secara sadar memisahkan domain dari infrastruktur — dasar untuk semua modul DDD berikutnya.

Lessons:

* [ ] Layered/N-tier Architecture klasik: kekuatan dan keterbatasannya (kenapa "service besar memanggil repository besar" akhirnya jadi big ball of mud)
* [ ] Dependency Inversion Principle diterapkan di level arsitektur (bukan cuma DI Spring) — kenapa domain tidak boleh bergantung ke infrastructure
* [ ] Hexagonal Architecture (Ports & Adapters, Alistair Cockburn): port, adapter primer vs sekunder, cara menjaga domain "murni" dari Spring/JPA
* [ ] Clean Architecture (Robert C. Martin): dependency rule, use case layer, perbandingan langsung dengan Hexagonal (mirip tapi tidak identik)
* [ ] Kapan pola ini *tidak* sepadan (overkill untuk CRUD service sederhana) — trade-off kompleksitas vs manfaat jangka panjang

Mini Project: Restrukturisasi 1 service Spring Boot standar (Controller-Service-Repository) menjadi Hexagonal Architecture — domain model murni tanpa anotasi JPA, port/adapter eksplisit, test domain tanpa Spring context sama sekali.

Status: Not Started

---

## Module 4 — Domain-Driven Design: Strategic Design

Description: Bagian DDD yang paling sering dilewati developer (langsung loncat ke Aggregate) padahal ini yang menentukan *bentuk* sistem — ubiquitous language dan batas antar-domain.

Lessons:

* [ ] Ubiquitous Language: kenapa nama variabel/class harus sama dengan bahasa yang dipakai business, dan bug komunikasi yang muncul kalau tidak
* [ ] Bounded Context: cara mengenali batas domain yang sebenarnya (satu istilah bisa berarti beda di context berbeda — studi kasus "Customer" di Sales vs Support)
* [ ] Context Mapping: pola relasi antar-context (Partnership, Customer-Supplier, Conformist, Anti-Corruption Layer)
* [ ] Subdomain: core, supporting, generic — cara memutuskan mana yang layak effort besar, mana yang cukup pakai solusi off-the-shelf
* [ ] Event Storming sebagai teknik discovery: cara memfasilitasi sesi (walau dilakukan sendiri) untuk memetakan domain dari proses bisnis nyata

Mini Project: Lakukan "event storming" mini (soliter, dipandu template) untuk domain e-commerce (order, payment, shipping, inventory) — hasilkan peta bounded context dan context map, plus justifikasi kenapa batasnya di situ.

Status: Not Started

---

## Module 5 — Domain-Driven Design: Tactical Design

Description: Menerjemahkan strategic design jadi kode Java/Spring Boot yang benar-benar mengekspresikan domain — bukan sekadar `@Entity` yang isinya getter/setter.

Lessons:

* [ ] Entity vs Value Object: identitas vs kesetaraan berdasarkan nilai — kenapa perbedaan ini penting untuk `equals()`/`hashCode()` yang benar
* [ ] Aggregate & Aggregate Root: batas konsistensi transaksional, aturan "satu transaksi = satu aggregate", kenapa aggregate besar jadi bottleneck
* [ ] Domain Event: cara memodelkan "sesuatu yang sudah terjadi" di domain, dan menghubungkannya ke integrasi antar-bounded-context
* [ ] Repository pattern (DDD version, bukan Spring Data generik) — abstraksi persistence yang benar-benar melindungi domain
* [ ] Anti-Corruption Layer implementasi nyata: mengintegrasikan bounded context baru dengan sistem legacy tanpa mencemari domain model baru

Mini Project: Implementasikan 1 aggregate (misal `Order` dengan `OrderLine` sebagai bagian aggregate) di Spring Boot dengan invariant yang benar-benar ditegakkan di constructor/method, bukan divalidasi terpisah di service layer.

Status: Not Started

---

## Module 6 — Monolith, Modular Monolith & Microservices

Description: Modul paling sering disalahpahami di industri — kapan microservices benar-benar dibutuhkan, dan kenapa modular monolith sering jadi pilihan lebih rasional daripada langsung microservices.

Lessons:

* [ ] Monolith klasik: kenapa ini bukan kata kotor — kekuatan sebenarnya (deployment sederhana, transaksi ACID mudah, tidak ada network hop)
* [ ] Conway's Law: kenapa struktur organisasi Anda akan (mau tidak mau) tercermin di arsitektur sistem
* [ ] Modular Monolith: menggunakan bounded context Module 4 sebagai batas modul dalam satu deployable — jalan tengah yang sering diabaikan
* [ ] Microservices: kapan benar-benar sepadan (tim besar, skala independen, deployment independen) dan biaya tersembunyinya (distributed system complexity, data consistency, operational overhead)
* [ ] Strategi migrasi: Strangler Fig Pattern — cara memecah monolith secara bertahap tanpa "big bang rewrite" yang berisiko

Mini Project: Diberi 1 monolith fiktif (deskripsi domain e-commerce) — buat rekomendasi tertulis: tetap monolith, jadi modular monolith, atau dipecah microservices, lengkap dengan kriteria keputusan dan ADR.

Status: Not Started

---

## Module 7 — Komunikasi Antar-Service & Distributed Transaction

Description: Begitu sistem terpecah (Module 6), muncul masalah baru: bagaimana service saling bicara dan bagaimana menjaga konsistensi tanpa transaksi database tunggal.

Lessons:

* [ ] Synchronous communication: REST vs gRPC — trade-off latency, contract, tooling; kapan API composition/aggregator dibutuhkan
* [ ] Asynchronous communication: event-driven, message queue vs event stream — kapan async benar-benar lebih tepat daripada sync
* [ ] Saga Pattern: choreography vs orchestration untuk distributed transaction, compensating transaction saat langkah gagal di tengah
* [ ] CQRS (Command Query Responsibility Segregation): kapan memisahkan model write dari read benar-benar bermanfaat (dan kapan cuma menambah kompleksitas)
* [ ] Event Sourcing dasar: menyimpan state sebagai rangkaian event — trade-off vs CRUD tradisional, kapan pola ini sepadan

Mini Project: Desain saga (orchestration-based) untuk proses checkout yang melibatkan 3 service (Order, Payment, Inventory) — termasuk skenario kegagalan dan compensating action-nya, didokumentasikan dengan sequence diagram.

Status: Not Started

---

## Module 8 — Data Architecture at Scale

Description: Pilar "Designing Data-Intensive Applications" — bagaimana data benar-benar berperilaku saat sistem tumbuh besar, bukan lagi asumsi "satu database PostgreSQL cukup".

Lessons:

* [ ] Database-per-service vs shared database: kenapa shared database jadi anti-pattern paling umum di "microservices palsu"
* [ ] Sharding & Partitioning: strategi (range, hash, directory-based), hot shard problem, cara memilih shard key yang benar
* [ ] Replication: leader-follower, multi-leader, leaderless — trade-off read scalability vs write consistency
* [ ] CAP Theorem & PACELC: bukan sekadar hafalan "pilih 2 dari 3" — bagaimana ini benar-benar memengaruhi keputusan desain nyata
* [ ] Consistency model: strong vs eventual consistency, read-your-writes, causal consistency — memilih level yang tepat per use case (ties `standards/CACHE_STANDARDS.md`, `standards/DATABASE_STANDARDS.md`)

Mini Project: Diberi skenario sistem dengan 10 juta user aktif — tentukan strategi sharding untuk tabel `orders`, jelaskan trade-off consistency yang diambil, dan skema replication yang dipilih.

Status: Not Started

---

## Module 9 — Scalability & Reliability Patterns

Description: Pola yang membuat sistem tetap hidup saat traffic naik atau satu komponen gagal — menyambungkan teori arsitektur ke `standards/RESILIENCY_STANDARDS.md` yang sudah ada di repo ini.

Lessons:

* [ ] Load Balancing: algoritma (round robin, least connections, consistent hashing), L4 vs L7, kapan sticky session jadi masalah arsitektur
* [ ] Horizontal vs Vertical Scaling: batas vertical scaling, kenapa stateless service jadi prasyarat horizontal scaling yang mulus
* [ ] Resiliency patterns di level arsitektur: circuit breaker, bulkhead, timeout, retry dengan backoff — kapan tiap pola dipakai, kombinasi yang salah yang bikin cascading failure
* [ ] Rate Limiting & Backpressure: algoritma (token bucket, sliding window), melindungi service dari downstream yang lambat
* [ ] Idempotency di level arsitektur: kenapa ini prasyarat untuk retry yang aman di sistem terdistribusi

Mini Project: Desain arsitektur resiliency untuk 1 service yang bergantung pada 2 downstream service eksternal yang tidak reliable — termasuk circuit breaker, retry policy, dan rencana graceful degradation saat downstream down total.

Status: Not Started

---

## Module 10 — Event-Driven Architecture & Kafka di Level Arsitektur

Description: Lanjutan Module 7, fokus ke pola produksi nyata dengan Kafka — bukan lagi "cara pakai producer/consumer" tapi "bagaimana mendesain topologi event-driven yang benar" (ties `standards/MESSAGING_STANDARDS.md`).

Lessons:

* [ ] Event-driven architecture topology: event notification vs event-carried state transfer vs event sourcing — kapan tiap gaya dipakai
* [ ] Outbox Pattern: menyelesaikan dual-write problem (database + message broker) tanpa distributed transaction
* [ ] Exactly-once semantics: kenapa ini secara teori sulit dijamin end-to-end, dan bagaimana idempotent consumer jadi solusi praktis
* [ ] Schema evolution: backward/forward compatibility untuk event, schema registry, strategi versioning event
* [ ] Kafka sebagai backbone arsitektur: topic design, partitioning strategy, kapan Kafka dipilih vs kapan RabbitMQ/SQS lebih tepat

Mini Project: Desain topologi event-driven untuk sistem inventory real-time (multi-warehouse) menggunakan Kafka — termasuk outbox pattern di service producer dan strategi idempotent consumer di service consumer.

Status: Not Started

---

## Module 11 — API & Integration Architecture

Description: Bagaimana klien (web, mobile, partner eksternal) benar-benar mengakses sistem yang sudah didesain di modul-modul sebelumnya (ties `standards/API_STANDARDS.md`, `standards/OPENAPI_STANDARDS.md`).

Lessons:

* [ ] API Gateway: routing, auth terpusat, rate limiting di edge — kapan gateway jadi bottleneck/single point of failure kalau salah desain
* [ ] Backend-for-Frontend (BFF): kapan satu API generik tidak cukup untuk web vs mobile vs partner
* [ ] Strategi API versioning yang sepadan untuk sistem besar: URI vs header versioning, deprecation policy, contract testing untuk mencegah breaking change
* [ ] REST vs GraphQL vs gRPC: trade-off nyata (over-fetching/under-fetching, tooling, learning curve tim, kesesuaian dengan gaya komunikasi internal vs eksternal)
* [ ] API sebagai produk: dokumentasi (OpenAPI), developer experience, backward compatibility sebagai komitmen arsitektur, bukan detail teknis

Mini Project: Desain API Gateway + BFF untuk sistem dengan 2 jenis klien (web dashboard admin, mobile app customer) yang mengakses kumpulan microservices yang sama — termasuk keputusan versioning dan auth strategy.

Status: Not Started

---

## Module 12 — Arsitektur Deployment & Cloud-Native

Description: Keputusan arsitektur yang menyentuh infrastruktur — bukan cara pakai Docker/K8s (itu ranah `courses/kubernetes`), tapi bagaimana keputusan deployment memengaruhi desain sistem.

Lessons:

* [ ] Cloud-native pattern di level keputusan: 12-Factor App, container sebagai unit deployment, service mesh secukupnya untuk paham kapan dibutuhkan
* [ ] Serverless trade-off: cold start, vendor lock-in, kapan FaaS benar-benar lebih murah/simpel daripada container long-running
* [ ] Multi-region & Disaster Recovery: active-active vs active-passive, RTO/RPO, kenapa "multi-region" bukan keputusan yang bisa ditambah belakangan
* [ ] Cost sebagai architecture characteristic: bagaimana keputusan arsitektur (sharding berlebihan, over-provisioning, chatty service) langsung berdampak ke biaya cloud
* [ ] Deployment strategy: blue-green, canary, rolling update — trade-off risiko vs kompleksitas operasional

Mini Project: Buat rencana disaster recovery (RTO/RPO ditentukan sendiri dengan justifikasi) untuk sistem yang sudah didesain di modul-modul sebelumnya, termasuk pilihan strategi multi-region.

Status: Not Started

---

## Module 13 — Security & Observability Architecture

Description: Dua pilar yang paling sering "ditempel di akhir" padahal harus jadi bagian desain sejak awal — menutup celah antara arsitektur "di atas kertas" dan sistem yang benar-benar bisa dipercaya production.

Lessons:

* [ ] Security architecture: defense in depth, zero trust dasar (setiap service memverifikasi, bukan percaya jaringan internal), threat modeling sederhana (STRIDE)
* [ ] Authentication & authorization di level arsitektur terdistribusi: token propagation antar-service, service-to-service auth (mTLS, API key, OAuth2 client credentials)
* [ ] Observability architecture: three pillars (logs, metrics, traces) sebagai keputusan desain, distributed tracing untuk request yang melintasi banyak service
* [ ] Architecture governance: bagaimana menjaga konsistensi arsitektur di banyak tim tanpa menjadi bottleneck (architecture review, fitness function otomatis, golden path/paved road)
* [ ] Studi kasus: bagaimana Stripe/GitHub mendesain sistem mereka dengan security & observability sebagai first-class concern (sintesis dari engineering blog, bukan tebakan)

Mini Project: Buat threat model sederhana (STRIDE) dan rencana observability (log/metric/trace apa yang wajib ada) untuk sistem checkout yang sudah didesain di Module 7.

Status: Not Started

---

## Module 14 — System Design Interview & Capstone

Description: Modul penutup — melatih kemampuan mengerjakan system design di bawah tekanan waktu (format interview) sekaligus menghasilkan portfolio piece nyata dari seluruh course.

Lessons:

* [ ] Kerangka menjawab system design interview: clarify requirement → estimate scale → high-level design → deep dive → trade-off — latihan dengan studi kasus URL shortener
* [ ] Studi kasus interview klasik #1: Rate Limiter & News Feed — latihan penuh dengan framework di atas
* [ ] Studi kasus interview klasik #2: Chat System (WhatsApp-like) & Ride-Sharing (Uber-like) — latihan penuh, fokus ke trade-off real-time & lokasi
* [ ] Cara mempresentasikan hasil system design ke interviewer teknis vs ke klien freelance (framing berbeda: "membuktikan kompetensi" vs "membangun kepercayaan")
* [ ] Review kumpulan capstone: cross-check semua ADR & diagram C4 yang dihasilkan sepanjang course untuk konsistensi satu sama lain

Portfolio Project: Dokumen arsitektur lengkap untuk satu sistem skala besar pilihan Anda (mis. platform e-commerce multi-tenant atau sistem booking) — berisi C4 diagram (Context sampai Component), minimal 8 ADR untuk keputusan kunci, desain data & sharding strategy, resiliency plan, dan security/observability plan. Disimpan di `portofolio/`, portfolio-ready untuk klien freelance internasional.

Status: Not Started

---

# Enterprise Project

Description: Capstone Module 14 sekaligus jadi Enterprise Project course ini — bukan implementasi kode penuh (itu peran `courses/backend-fundamentals` & course lain), tapi dokumen arsitektur end-to-end yang membuktikan Anda bisa berpikir dan berkomunikasi sebagai Software Architect: mulai dari requirement, trade-off analysis, sampai rencana deployment/security/observability.

Acceptance Criteria:

* [ ] Minimal 1 diagram C4 Context, 1 Container, 2 Component untuk sub-sistem kritis
* [ ] Minimal 8 ADR yang saling konsisten (tidak ada keputusan yang bertentangan tanpa penjelasan)
* [ ] Data architecture plan (sharding/replication/consistency) dengan justifikasi
* [ ] Resiliency & scalability plan (load balancing, circuit breaker, rate limiting)
* [ ] Security & observability plan (threat model dasar, three pillars observability)
* [ ] Dokumen bisa dipertahankan secara verbal — simulasikan Q&A seolah presentasi ke klien/interviewer

Status: Not Started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: Belum dimulai — roadmap **disetujui 2026-08-07**, siap mulai kapan saja

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Architecture vs Design: kenapa keputusan arsitektur sulit diubah
