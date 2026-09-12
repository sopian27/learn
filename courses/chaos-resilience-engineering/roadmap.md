# Course Roadmap

## Course Information

* Course Name: Chaos & Resilience Engineering — Sistem Tetap Hidup Saat Semuanya Rusak
* Category: Backend Engineering / Production Reliability — layer **praktik hands-on** di atas pola yang sudah dikenalkan konseptual di `courses/backend-fundamentals` (Resilience4j dasar) dan `courses/system-design` (resiliency pattern level arsitektur); melengkapi (bukan menggantikan) sisi budaya di `courses/engineering-culture` (incident response & blameless postmortem)
* Difficulty: Level 3 (sudah bisa bangun REST API production-grade, paham circuit breaker/retry sebagai konsep) → Level 5 (bisa merancang, menguji, dan membuktikan sistem tetap correct saat dependency gagal — chaos experiment, fault injection, SLO/error budget, capstone Chaos Week)
* Estimated Duration: 10 modul, 3-4 lesson/modul, ~30-40 menit/lesson. Perkiraan ≈ 4-5 bulan pace sedang. Capstone Module 10 sendiri butuh 1 minggu penuh (Chaos Week, 7 hari eksperimen berurutan).
* Prerequisites: `courses/backend-fundamentals` (Java/Spring Boot production-grade, Resilience4j pengantar, Testcontainers) — **wajib**, course ini adalah kelanjutan langsung. `courses/system-design` Module 9 (resiliency pattern level arsitektur) membantu tapi tidak wajib, dijelaskan ulang lebih dalam di sini dari sisi implementasi+pengujian.

---

# Catatan Scope (draft — menunggu approval user)

Dibuat atas permintaan eksplisit user (2026-09-12): course fokus **hardening, failure testing, resilience, dan production readiness** — dengan asumsi eksplisit bahwa AI mempercepat development, sehingga waktu belajar dialihkan penuh ke sisi "apa yang terjadi kalau semuanya rusak", bukan sisi "cara membangun fitur" yang sudah dikuasai.

**Guard check sebelum draft (`ai-los/COURSE_CREATION.md` + `progress/roadmap.md`):** Course Aktif "Character Development" pas di garis 50% (3/6 modul, Module 6 capstone log 28 hari masih berjalan sampai 2026-09-27). Dikonfirmasi via AskUserQuestion 2026-09-12 — user pilih lanjut sekarang, pola sama seperti override guard sebelumnya di repo ini.

**Overlap check dengan course existing** (grep `circuit breaker|chaos|resilien|retry|bulkhead|idempoten|SLO|SLI|error budget|postmortem|observability` di seluruh `courses/`):

1. `courses/backend-fundamentals` Module 9 — sudah mengenalkan Resilience4j (retry+backoff, circuit breaker, timeout, bulkhead), idempotency, graceful degradation, TAPI hanya 1 lesson pengantar + 1 mini project ("tambahkan circuit breaker ke satu call eksternal"). Tidak ada fault injection nyata, tidak ada chaos experiment, tidak ada SLO/observability. **Tidak diduplikasi** — course ini treat itu sebagai prasyarat yang di-recap singkat di Module 2, lalu langsung ke depth: config production-grade, kombinasi pola yang salah, dan yang paling penting: **membuktikan** pola itu bekerja lewat fault injection, bukan cuma menulis kode lalu percaya.
2. `courses/system-design` Module 9 — resiliency pattern di level **desain arsitektur** (kapan pakai circuit breaker vs bulkhead, trade-off). Course ini di level **implementasi + pengujian**: bagaimana benar-benar mensimulasikan DB mati/Kafka stuck/pod restart dan memverifikasi sistem berperilaku sesuai desain. Komplementer, tidak overlap konten.
3. `courses/engineering-culture` Module 4 — incident response & blameless postmortem dari sisi **budaya/komunikasi** (bagaimana menulis postmortem yang dipercaya tim). Course ini menambahkan sisi **teknis**: bagaimana men-generate insiden itu sendiri lewat chaos experiment, dan kerangka Detect→Contain→Recover→Degrade→Alert→Recover Data→Prevent Recurrence yang lebih granular secara engineering daripada budaya. Module 9 course ini cross-reference eksplisit ke `engineering-culture` Module 4 untuk format postmortem, tidak menulis ulang.
4. `courses/ci-cd` Module 8 — retry/fail-fast/idempotent job **di level CI/CD pipeline**, beda konteks total dari resiliency aplikasi runtime. Tidak overlap.
5. **Belum ada course sama sekali** yang membahas: chaos engineering principles (steady-state hypothesis, blast radius, experiment design), fault injection tooling (Chaos Mesh/Litmus/Toxiproxy), SLI/SLO/error budget sebagai kerangka kerja terukur, atau capstone "Chaos Week" sistematis. Ini gap murni.

**Kesimpulan**: course baru, berdiri sendiri, domain `software-engineering` (existing, sudah Active). Cross-reference ke 3 course di atas ditambahkan di modul terkait supaya tidak ada materi dobel.

Referensi riset (WebSearch 2026-09-12, untuk disusun jadi materi lesson saat mulai):

* [Principles of Chaos Engineering](https://principlesofchaos.org/) — fondasi Module 5 (steady-state hypothesis, blast radius, 4 langkah eksperimen)
* *Chaos Engineering* (Rosenthal & Jones, O'Reilly, gratis dari Netflix/PagerDuty) — narasi Netflix Chaos Monkey, kasus nyata — Module 5-6
* [Google SRE Book — Embracing Risk](https://sre.google/sre-book/embracing-risk/) & [Service Level Objectives](https://sre.google/sre-book/service-level-objectives/) — fondasi Module 4
* [Google SRE Workbook — Error Budget Policy](https://sre.google/workbook/error-budget-policy/), [Implementing SLOs](https://sre.google/workbook/implementing-slos/), [Alerting on SLOs](https://sre.google/workbook/alerting-on-slos/) — Module 4, 9
* [Resilience4j docs](https://resilience4j.readme.io/) — Module 2-3 (dipakai juga di `backend-fundamentals`, di-deepen di sini)
* [Chaos Mesh docs](https://chaos-mesh.org/docs/) — fault injection Kubernetes (network/pod/IO/stress/DNS/HTTP) — Module 5, 8
* [LitmusChaos docs](https://litmuschaos.io/) — alternatif tooling, cakupan lebih luas (VM, bare metal) — Module 5
* [Toxiproxy (Shopify)](https://github.com/Shopify/toxiproxy) — network fault injection level TCP proxy, ringan untuk local/CI — Module 6, 7
* [Kubernetes docs — Pod Disruption, Node failure handling](https://kubernetes.io/docs/concepts/workloads/pods/disruptions/) — Module 8
* [OpenTelemetry docs](https://opentelemetry.io/docs/) — distributed tracing untuk observability — Module 4
* `standards/RESILIENCY_STANDARDS.md`, `standards/OBSERVABILITY_STANDARDS.md` — konvensi wajib repo ini, dirujuk sepanjang course
* `courses/backend-fundamentals/roadmap.md` Module 9, `courses/system-design/roadmap.md` Module 9, `courses/engineering-culture/roadmap.md` Module 4 — cross-reference, lihat poin overlap di atas

---

# Learning Objectives

* [ ] Menganalisis "apa yang terjadi kalau X mati" untuk setiap dependency (PostgreSQL, Redis, Kafka, external API, HTTP service, DNS, network, Kubernetes pod/node, storage, auth service) — bukan cuma infrastruktur, tapi dampaknya ke kode/state/transaksi
* [ ] Mengimplementasikan dan **membuktikan lewat pengujian nyata** (bukan cuma menulis kode lalu percaya) pola resiliency production-grade: timeout, retry+backoff, circuit breaker, bulkhead, rate limiting, fallback, graceful degradation, idempotency, backpressure
* [ ] Mendesain dan menjalankan chaos experiment: failure hypothesis, steady-state hypothesis, blast radius, fault injection, verifikasi recovery
* [ ] Mendefinisikan SLI/SLO/error budget untuk sebuah service, dan membangun observability (metrics, structured logging, distributed tracing, alerting) yang cukup untuk mendeteksi kegagalan sebelum user melapor
* [ ] Menjalankan kerangka **Detect → Contain → Recover → Degrade → Alert → Recover Data → Prevent Recurrence** untuk setiap kategori failure, dan menuliskannya sebagai artefak konkret (bukan cuma diskusi lisan)
* [ ] Menyelesaikan capstone Chaos Week 7 hari pada sistem distributed nyata (Java/Spring Boot + PostgreSQL + Redis + Kafka + external AI API + Kubernetes), menghasilkan dokumentasi eksperimen lengkap yang portfolio-ready

---

# Course Modules

## Module 1 — Failure Thinking & Mental Model Sistem

Description: Sebelum menulis kode resiliency, harus bisa memetakan "kalau komponen ini mati, apa yang terjadi ke sistem" — untuk setiap dependency, bukan cuma yang paling jelas.

Lessons:

* [ ] 1.1 Kenapa "kode saya bekerja saat semuanya normal" adalah standar yang salah — pergeseran mental dari feature-complete ke failure-aware
* [ ] 1.2 Memetakan dependency graph sistem: PostgreSQL, Redis, Kafka, external API (OpenAI/pihak ketiga), HTTP service internal, DNS, network, Kubernetes pod/node, storage, auth service — mana yang single point of failure
* [ ] 1.3 Blast radius: kalau komponen A mati, siapa yang kena dampak langsung vs cascading — studi kasus outage nyata (AWS us-east-1, Cloudflare, dsb.) dari postmortem publik
* [ ] 1.4 Failure mode per dependency: partial failure vs total failure vs degraded/lambat — kenapa "lambat" seringkali lebih berbahaya daripada "mati total"

Mini Project: Ambil 1 service nyata (dari `backend-fundamentals` atau kerjaan Anda), gambar dependency graph lengkap, dan untuk tiap dependency tulis jawaban singkat "apa yang terjadi kalau ini mati" — sebelum ada satu baris kode resiliency ditulis.

Status: Not Started

---

## Module 2 — Resilience Patterns: Timeout, Retry, Circuit Breaker, Bulkhead

Description: Recap singkat Resilience4j dari `backend-fundamentals` Module 9, lalu masuk depth production: konfigurasi yang benar, kombinasi pola yang salah (yang justru bikin cascading failure), dan cara membuktikan pola itu benar-benar bekerja.

Lessons:

* [ ] 2.1 Recap: timeout, retry+exponential backoff, circuit breaker, bulkhead — kapan tiap pola dipakai (rujukan `standards/RESILIENCY_STANDARDS.md`)
* [ ] 2.2 Konfigurasi production-grade: circuit breaker threshold/window/half-open state, retry jitter (kenapa backoff tanpa jitter bikin thundering herd), bulkhead sizing (thread pool vs semaphore isolation)
* [ ] 2.3 Kombinasi pola yang salah: retry tanpa circuit breaker (memperparah downstream yang sudah struggling), timeout lebih panjang dari deadline caller (resource leak), bulkhead yang terlalu longgar (tidak melindungi apa-apa)
* [ ] 2.4 Rate limiting & fallback: token bucket/sliding window (Redis-backed), fallback ke cache/data stale/default response — kapan fallback lebih baik daripada error total

Mini Project: Ambil service dari Module 1, implementasikan circuit breaker+retry+bulkhead+fallback untuk 1 dependency eksternal, lalu **buktikan** lewat test otomatis (bukan manual click) bahwa tiap pola benar-benar aktif pada kondisi yang tepat.

Status: Not Started

---

## Module 3 — Idempotency, Backpressure & Consistency Under Failure

Description: Resiliency tanpa idempotency berbahaya (duplicate charge, double processing). Modul ini fokus pada state, transaksi, dan concurrency saat retry/failure terjadi.

Lessons:

* [ ] 3.1 Idempotency key & deduplication: request masuk dua kali (retry client, at-least-once delivery) — desain key yang benar, storage dedup (Redis/DB dengan TTL)
* [ ] 3.2 Queue-based architecture & backpressure: kapan sistem harus menolak/menunda request alih-alih menerima semua dan collapse — bounded queue, load shedding
* [ ] 3.3 Connection pool resilience: pool exhaustion saat DB lambat, kenapa timeout di level pool sama pentingnya dengan timeout di level query
* [ ] 3.4 Transaction & consistency strategy saat partial failure: outbox pattern, saga (recap dari `system-design` jika sudah diambil), kompensasi transaksi

Mini Project: Simulasikan request duplikat (retry ganda dari client) ke 1 endpoint pembayaran/order tiruan, buktikan idempotency key mencegah double-processing; lalu simulasikan connection pool exhaustion dan buktikan sistem menolak dengan graceful (bukan hang).

Status: Not Started

---

## Module 4 — SLI/SLO/Error Budget & Observability Foundations

Description: Tanpa metrik yang jelas, "resilient" cuma klaim. Modul ini membangun kerangka terukur ala Google SRE plus observability yang cukup untuk melihat kegagalan sebelum user melapor.

Lessons:

* [ ] 4.1 SLI vs SLO vs SLA — cara pilih SLI yang benar-benar mewakili pengalaman user (bukan metrik yang mudah diukur tapi tidak relevan)
* [ ] 4.2 Error budget: cara hitung, cara pakai untuk keputusan rilis (freeze feature vs lanjut), studi kasus dari Google SRE Workbook
* [ ] 4.3 Observability 3 pilar: structured logging, metrics (Prometheus), distributed tracing (OpenTelemetry) — korelasi trace ID lintas service
* [ ] 4.4 Alerting berbasis SLO (burn rate alert) — kenapa alert berbasis threshold statis menghasilkan alert fatigue, alerting yang benar memberi sinyal actionable

Mini Project: Definisikan SLO untuk 1 service (misal: 99.9% request sukses dalam 500ms), instrumentasi dengan metrics+tracing, buat dashboard sederhana, dan buat 1 burn-rate alert yang benar-benar trigger saat disimulasikan error rate naik.

Status: Not Started

---

## Module 5 — Prinsip Chaos Engineering & Tooling

Description: Dari resiliency pattern (defense) ke chaos engineering (verifikasi aktif) — cara berpikir dan tooling untuk sengaja merusak sistem secara terkendali.

Lessons:

* [ ] 5.1 Empat prinsip [Principles of Chaos Engineering](https://principlesofchaos.org/): steady-state hypothesis, variasi kejadian nyata, kelompok kontrol vs eksperimen, minimalkan blast radius
* [ ] 5.2 Sejarah & konteks nyata: Netflix Chaos Monkey — kenapa perusahaan sengaja mematikan servernya sendiri di production, bukan cuma di staging
* [ ] 5.3 Merancang chaos experiment: hypothesis → metric steady-state → cara inject failure → kriteria berhenti (abort condition) — template yang dipakai konsisten sepanjang course
* [ ] 5.4 Tooling: Chaos Mesh & LitmusChaos (Kubernetes-native, CRD-based) vs Toxiproxy (network-level, ringan untuk local/CI) — kapan pakai yang mana

Mini Project: Tulis 1 chaos experiment plan lengkap (hypothesis, steady-state metric, blast radius, abort condition) untuk 1 dependency dari Module 1 — belum dieksekusi, ini latihan desain eksperimen sebelum masuk eksekusi nyata di Module 6-8.

Status: Not Started

---

## Module 6 — Chaos Experiment: Database & Caching Layer

Description: Eksperimen nyata pertama — dependency yang paling sering jadi single point of failure: PostgreSQL dan Redis.

Lessons:

* [ ] 6.1 Setup fault injection lokal: Toxiproxy di depan koneksi PostgreSQL/Redis (Docker Compose), kontrol latency/timeout/connection reset dari test
* [ ] 6.2 Eksperimen: PostgreSQL mati total & PostgreSQL lambat (bukan mati) — beda respons yang diharapkan, verifikasi circuit breaker+fallback dari Module 2 benar-benar jalan
* [ ] 6.3 Eksperimen: Redis mati & cache stampede saat Redis baru pulih (semua request tiba-tiba hit DB bersamaan) — mitigasi (jitter, lock, probabilistic early expiration)
* [ ] 6.4 Menuliskan hasil eksperimen dengan format konsisten: hypothesis, actual behavior, root cause, resilience improvement, automated test, monitoring/alert, recovery procedure

Mini Project: Jalankan 2 eksperimen (DB & Redis) end-to-end pada service dari Module 2-3, hasilkan dokumen eksperimen lengkap untuk masing-masing sesuai format 6.4.

Status: Not Started

---

## Module 7 — Chaos Experiment: Async Processing & External API

Description: Kegagalan yang lebih halus — bukan "mati", tapi "stuck", "lambat", atau "mengembalikan garbage".

Lessons:

* [ ] 7.1 Eksperimen: Kafka unavailable saat producer mengirim — apa yang terjadi ke request yang sedang berjalan, retry policy producer
* [ ] 7.2 Eksperimen: Kafka consumer delay/lag — data datang terlambat, dampak ke business logic yang mengasumsikan near-real-time
* [ ] 7.3 Eksperimen: external API (OpenAI/pihak ketiga) timeout, rate limit (429), dan error 500 — fallback strategy yang berbeda untuk tiap jenis kegagalan
* [ ] 7.4 Dependency mengembalikan garbage (response 200 tapi data tidak valid/tidak lengkap) — validasi response, kenapa ini paling sering terlewat dibanding "down total"

Mini Project: Simulasikan keempat skenario di atas pada 1 fitur yang memanggil Kafka + external API, dokumentasikan tiap eksperimen sesuai format Module 6.4.

Status: Not Started

---

## Module 8 — Chaos Experiment: Network, Kubernetes & Resource Exhaustion

Description: Kegagalan di layer infrastruktur — yang paling sulit direproduksi tanpa tooling yang tepat.

Lessons:

* [ ] 8.1 Network chaos: latency tinggi & packet loss (Toxiproxy/`tc netem`) — dampak ke timeout yang dikonfigurasi terlalu ketat/longgar
* [ ] 8.2 Kubernetes pod & node failure: pod kill (Chaos Mesh `PodChaos`), node drain — perilaku yang diharapkan dari readiness/liveness probe dan graceful shutdown
* [ ] 8.3 Resource exhaustion: memory/CPU (Chaos Mesh `StressChaos`), disk penuh — OOMKill, kenapa resource limit yang salah lebih berbahaya daripada tidak ada limit
* [ ] 8.4 DNS failure & auth service down — dependency yang jarang dipikirkan tapi bisa merobohkan semua service sekaligus

Mini Project: Jalankan minimal 3 eksperimen dari modul ini di cluster Kubernetes (local: kind/minikube), dokumentasikan sesuai format Module 6.4, termasuk verifikasi readiness/liveness probe bekerja sesuai desain.

Status: Not Started

---

## Module 9 — Incident Response, Recovery Verification & Prevention

Description: Menutup siklus tiap eksperimen dengan kerangka penuh, dan menyambungkan sisi teknis di sini dengan sisi budaya di `courses/engineering-culture` Module 4.

Lessons:

* [ ] 9.1 Kerangka Detect → Contain → Recover → Degrade → Alert → Recover Data → Prevent Recurrence — diterapkan ke seluruh eksperimen Module 6-8, bukan cuma dihafal
* [ ] 9.2 Runbook: cara menulis runbook yang benar-benar bisa diikuti orang lain jam 3 pagi, bukan dokumen yang basi begitu ditulis
* [ ] 9.3 Recovery verification: cara membuktikan sistem benar-benar pulih (bukan cuma "sudah tidak error"), termasuk recovery data (backup/restore, replay dari Kafka)
* [ ] 9.4 Menutup siklus dengan action item pencegahan konkret — cross-reference `courses/engineering-culture` Module 4 untuk format postmortem blameless (tidak ditulis ulang di sini)

Mini Project: Ambil 3 eksperimen dari Module 6-8, lengkapi masing-masing dengan runbook dan bukti recovery verification yang belum ada, lalu tulis 1 postmortem blameless penuh (format dari `engineering-culture`) untuk eksperimen yang hasilnya paling mengejutkan.

Status: Not Started

---

## Module 10 — Capstone: Sistem Distributed & Chaos Week

Description: Membangun 1 sistem Java/Spring Boot distributed nyata, lalu menjalankan Chaos Week 7 hari — setiap fitur yang "selesai" harus lulus diserang sebelum disebut production-ready.

Lessons:

* [ ] 10.1 Arsitektur capstone: Spring Boot + PostgreSQL (source of truth) + Redis (cache) + Kafka (async processing) + external AI API (mis. OpenAI) + deploy ke Kubernetes — terapkan semua pola Module 2-4 sejak awal (bukan ditambah belakangan)
* [ ] 10.2 Chaos Week Day 1-3: Database failures → Redis & caching failures → Kafka & asynchronous processing failures (eksekusi ulang eksperimen Module 6-7 di sistem capstone, bukan sistem latihan)
* [ ] 10.3 Chaos Week Day 4-5: External API/OpenAI failures → Network/Kubernetes failures (eksekusi ulang eksperimen Module 7-8 di sistem capstone)
* [ ] 10.4 Chaos Week Day 6-7: Load, concurrency & resource failures → Full production disaster simulation (kombinasi 2+ failure sekaligus — mis. DB lambat BERSAMAAN Kafka down — skenario yang paling jarang dilatih tapi paling realistis saat insiden nyata)

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Chaos Week — Distributed System Survival Test**. Satu sistem Java/Spring Boot (domain bebas — mis. order processing dengan AI-assisted recommendation) dengan PostgreSQL, Redis, Kafka, external AI API, dan deploy Kubernetes, yang telah diuji tahan lewat 7 hari eksperimen chaos berurutan.

Acceptance Criteria:

* Sistem dibangun lengkap dengan pola resiliency Module 2-4 sejak desain awal (timeout, retry+backoff, circuit breaker, bulkhead, idempotency, backpressure, SLO+observability) — bukan ditambahkan setelah chaos week gagal
* 7 hari eksperimen chaos dijalankan sesuai jadwal (DB, Redis/cache, Kafka, external API, network/Kubernetes, load/resource, disaster simulation gabungan), masing-masing menghasilkan dokumen: failure hypothesis, expected behavior, actual behavior, root cause, resilience improvement, automated test, monitoring/alert, recovery procedure
* Minimal 1 skenario "full production disaster" (2+ failure simultan) dijalankan dan didokumentasikan di Day 7
* Automated test untuk tiap resilience improvement (bukan manual verification) — regresi tertangkap otomatis kalau pola resiliency rusak di kemudian hari
* Observability: dashboard SLO/error budget, alert berbasis burn rate yang terbukti trigger saat disimulasikan
* Minimal 1 postmortem blameless penuh (format `engineering-culture` Module 4) untuk eksperimen yang paling mengejutkan hasilnya
* Deploy jalan di Kubernetes, chaos experiment Kubernetes-level (Module 8) didemonstrasikan di cluster yang sama
* Hasil (arsitektur + 7 dokumen eksperimen + postmortem) disimpan di `portofolio/` sebagai portfolio-ready artifact — bukti konkret "failure-aware", bukan cuma "feature-complete"

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Kenapa "kode saya bekerja saat semuanya normal" adalah standar yang salah
