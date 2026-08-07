# Course Roadmap

## Course Information

* Course Name: Observability Engineering — Prometheus, Grafana, ELK/Loki, Jaeger/Tempo, OpenTelemetry
* Category: Observability Engineering (Backend/DevOps, hands-on tool-specific) — layer implementasi di bawah lapisan konsep SRE (SLI/SLO/error budget) yang sudah dibahas di `courses/cloud-devops-foundations`, dan lebih dalam dari kebiasaan structured logging/metrics/tracing dasar yang disinggung sebagai objective (bukan modul penuh) di `courses/backend-fundamentals`
* Difficulty: Level 1 (tahu konsep SLI/SLO, belum pernah instrumentasi & operasikan stack observability sungguhan) → Level 4 (bisa merancang & mengoperasikan observability end-to-end untuk sistem multi-service production)
* Estimated Duration: 9 modul, 3-4 lesson/modul, ~30-40 menit/lesson. Perkiraan ≈ 3-4 bulan pace sedang.
* Prerequisites: Familiar dengan Spring Boot & Docker (untuk menjalankan stack lokal). Konsep SLI/SLO dari `courses/cloud-devops-foundations` membantu tapi tidak wajib selesai dulu — course ini tetap menjelaskan ulang secukupnya di Module 9.

---

# Catatan Scope (draft — menunggu approval & kalibrasi user)

Draft ini dibuat atas permintaan eksplisit user (2026-08-07) untuk mengisi gap "Observability tool-specific (Prometheus/Grafana/ELK/Jaeger/OTel)". **Belum ada sesi kalibrasi** — asumsi di bawah ini perlu dikonfirmasi:

1. **Tidak menduplikasi `courses/cloud-devops-foundations`**: course itu membahas observability sebagai *disiplin SRE* (SLI/SLO/error budget, konseptual, provider-agnostic). Course ini adalah lapisan *tool hands-on*: benar-benar instrumentasi kode, deploy Prometheus/Grafana/ELK/Jaeger, baca dashboard sungguhan.
2. **Asumsi stack open-source-first**: Prometheus+Grafana untuk metrics, ELK **atau** Loki untuk logs (Module 4 & 5 membahas keduanya sebagai alternatif, bukan wajib pakai dua-duanya), Jaeger/Tempo untuk tracing — semua bisa dijalankan lokal via Docker Compose, tidak butuh akun cloud berbayar.
3. **Splunk** (diminta eksplisit user) dibahas di Module 8 secara **konseptual/perbandingan** (bukan hands-on penuh) karena Splunk adalah produk komersial closed-source — fokusnya memahami kapan enterprise memilih Splunk dibanding stack open-source, bukan operasional mendalam. Perlu dikonfirmasi apakah ini cukup atau user ingin hands-on penuh (butuh trial account).
4. **Module 9** eksplisit menyambung balik ke `courses/cloud-devops-foundations` (SLO burn-rate alerting) — course ini menerapkan konsep itu dengan tool nyata.

Referensi riset (untuk disusun jadi roadmap final saat approval):

* [OpenTelemetry official docs](https://opentelemetry.io/docs/) — fondasi Module 1, standar instrumentasi vendor-neutral
* [Prometheus docs](https://prometheus.io/docs/) & *Prometheus: Up & Running* (Brian Brazil) — Module 2
* [Grafana docs](https://grafana.com/docs/grafana/latest/) — Module 3
* [Elastic (ELK) official docs](https://www.elastic.co/guide/index.html) — Module 4
* [Grafana Loki docs](https://grafana.com/docs/loki/latest/) — Module 5
* [Jaeger docs](https://www.jaegertracing.io/docs/) & [Grafana Tempo docs](https://grafana.com/docs/tempo/latest/) — Module 6
* *Google SRE Book* (sre.google/books) — cross-ref `courses/cloud-devops-foundations`, dipakai di Module 9
* [Micrometer docs](https://docs.micrometer.io/) — jembatan instrumentasi Spring Boot ke Prometheus
* `standards/OBSERVABILITY_STANDARDS.md` — kontrak konvensi repo ini

---

# Learning Objectives

* [ ] Memahami & menerapkan tiga pilar observability (metrics, logs, traces) dengan OpenTelemetry sebagai standar instrumentasi
* [ ] Menguasai PromQL dan model data Prometheus, mengekspos metrics Spring Boot lewat Micrometer/Actuator
* [ ] Membangun dashboard & alerting rule di Grafana, memahami RED/USE method untuk memilih metrik yang tepat
* [ ] Menerapkan structured logging dan mengoperasikan stack ELK untuk pencarian & analisis log
* [ ] Memahami Loki sebagai alternatif label-based logging dan kapan memilihnya dibanding ELK
* [ ] Mengimplementasikan distributed tracing dengan Jaeger/Tempo, memahami trace context propagation antar-service
* [ ] Mengorelasikan metrics-logs-traces jadi satu alur debugging incident yang koheren
* [ ] Memahami posisi Splunk di lanskap enterprise observability dan kapan itu dipilih
* [ ] Menyelesaikan capstone: instrumentasi sistem multi-service end-to-end dengan dashboard & runbook incident-ready

---

# Course Modules

## Module 1 — Tiga Pilar & Fondasi OpenTelemetry

Description: Titik awal semua modul lain — memahami metrics/logs/traces sebagai satu kesatuan, bukan tiga tool terpisah, lewat standar vendor-neutral OpenTelemetry.

Lessons:

* [ ] 1.1 Tiga pilar observability: apa yang dijawab masing-masing (metrics = "apa yang terjadi", logs = "detail kejadian", traces = "di mana waktu dihabiskan")
* [ ] 1.2 Arsitektur OpenTelemetry: SDK, Collector, exporter — kenapa standar vendor-neutral penting (hindari vendor lock-in)
* [ ] 1.3 Instrumentasi Spring Boot dengan OpenTelemetry SDK: auto-instrumentation vs manual span
* [ ] 1.4 Menjalankan OTel Collector lokal via Docker Compose, verifikasi data mengalir

Mini Project: Instrumentasi 1 endpoint Spring Boot dengan OpenTelemetry (metrics + 1 custom span), verifikasi data muncul di OTel Collector.

Status: Not Started

---

## Module 2 — Metrics Mendalam dengan Prometheus

Description: Model data time-series Prometheus dan bahasa query-nya — dasar untuk semua dashboard & alert di modul berikutnya.

Lessons:

* [ ] 2.1 Model data Prometheus: metric, label, time series — kenapa label cardinality yang tinggi berbahaya
* [ ] 2.2 PromQL dasar: `rate()`, `histogram_quantile()`, agregasi
* [ ] 2.3 Micrometer & Spring Boot Actuator: mengekspos metrics aplikasi ke endpoint `/actuator/prometheus`
* [ ] 2.4 RED method (Rate, Errors, Duration) untuk service, USE method (Utilization, Saturation, Errors) untuk resource — memilih metrik yang benar-benar berguna

Mini Project: Ekspos custom business metric (misal: jumlah order per menit) dari Spring Boot ke Prometheus, tulis 3 PromQL query yang menjawab pertanyaan operasional nyata.

Status: Not Started

---

## Module 3 — Dashboard & Alerting dengan Grafana

Description: Mengubah data mentah Prometheus jadi sesuatu yang bisa dibaca manusia saat insiden — dan mengingatkan sebelum insiden terjadi.

Lessons:

* [ ] 3.1 Membangun dashboard Grafana dari data source Prometheus: panel, variable, template
* [ ] 3.2 Alerting rule di Grafana: threshold, evaluasi periode, notification channel
* [ ] 3.3 Alertmanager: routing, grouping, silencing — mencegah alert fatigue
* [ ] 3.4 Prinsip dashboard yang baik: apa yang dilihat on-call engineer dalam 10 detik pertama saat insiden

Mini Project: Bangun 1 dashboard Grafana untuk service Module 2 (RED metrics) lengkap dengan 1 alerting rule yang benar-benar terverifikasi bisa trigger.

Status: Not Started

---

## Module 4 — Structured Logging & Stack ELK

Description: Log yang bisa dicari dan dikorelasikan, bukan sekadar teks yang ditumpuk di file.

Lessons:

* [ ] 4.1 Structured logging (JSON) dari Spring Boot — kenapa `log.info("user {} logged in", id)` lebih baik dari string concatenation
* [ ] 4.2 Korelasi log dengan trace ID — menyisipkan trace context ke setiap baris log
* [ ] 4.3 Arsitektur ELK/EFK: Elasticsearch (index & search), Logstash/Fluentd (ingest & transform), Kibana (visualisasi)
* [ ] 4.4 Query & analisis log di Kibana: mencari insiden lewat kombinasi trace ID + error level

Mini Project: Alirkan structured log dari service Module 1-2 ke stack ELK lokal (Docker Compose), lakukan 1 investigasi simulasi ("cari semua error untuk user X dalam 1 jam terakhir") lewat Kibana.

Status: Not Started

---

## Module 5 — Alternatif Log Aggregation: Grafana Loki

Description: Pendekatan berbeda untuk masalah yang sama — label-based, lebih ringan resource, terintegrasi native dengan Grafana.

Lessons:

* [ ] 5.1 Filosofi Loki: index label bukan full-text, kenapa ini lebih murah untuk skala besar
* [ ] 5.2 LogQL: query dasar, filter, agregasi
* [ ] 5.3 Loki vs ELK: trade-off nyata (biaya storage, kecepatan full-text search, kompleksitas operasional) — bukan "yang satu selalu lebih baik"

Mini Project: Alirkan log yang sama dari Module 4 ke Loki, bandingkan pengalaman query LogQL vs Kibana untuk skenario investigasi yang sama.

Status: Not Started

---

## Module 6 — Distributed Tracing dengan Jaeger/Tempo

Description: Melacak satu request yang melintasi banyak service — jawaban untuk "kenapa request ini lambat, di service mana?"

Lessons:

* [ ] 6.1 Konsep trace, span, span context — bagaimana satu request jadi pohon span
* [ ] 6.2 Trace context propagation antar-service (HTTP header W3C Trace Context)
* [ ] 6.3 Setup Jaeger atau Tempo lokal, kirim trace dari OpenTelemetry SDK Module 1
* [ ] 6.4 Membaca trace untuk debugging: mengidentifikasi span mana yang jadi bottleneck di request multi-service

Mini Project: Buat 2 service Spring Boot yang saling memanggil, propagate trace context di antara keduanya, temukan bottleneck lewat visualisasi trace di Jaeger/Tempo.

Status: Not Started

---

## Module 7 — Mengorelasikan Tiga Pilar

Description: Kekuatan sesungguhnya observability modern bukan di satu tool, tapi di kemampuan lompat dari satu pilar ke pilar lain saat debugging.

Lessons:

* [ ] 7.1 Exemplars: menghubungkan metric (Prometheus) langsung ke trace (Tempo/Jaeger) yang relevan
* [ ] 7.2 Navigasi trace-to-log-to-metric di Grafana (data source terhubung)
* [ ] 7.3 Membangun 1 alur kerja debugging insiden: alert metric → lihat trace terkait → lihat log detail span yang bermasalah

Mini Project: Simulasikan 1 insiden (endpoint sengaja dibuat lambat/error), selesaikan investigasi lengkap memakai alur metric→trace→log di Grafana, dokumentasikan langkah-langkahnya.

Status: Not Started

---

## Module 8 — Splunk & Lanskap Observability Enterprise

Description: Memahami di mana open-source stack berhenti dan kenapa enterprise besar sering tetap bayar untuk Splunk atau platform komersial lain.

Lessons:

* [ ] 8.1 Apa yang ditawarkan Splunk di luar yang sudah bisa dilakukan ELK/Loki (governance, compliance, enterprise support)
* [ ] 8.2 Trade-off biaya: lisensi Splunk vs biaya operasional self-hosted stack open-source
* [ ] 8.3 Kapan enterprise realistis memilih Splunk (regulasi ketat, tim ops kecil, butuh vendor support) vs kapan open-source lebih masuk akal

Mini Project: Tulis perbandingan tertulis singkat (1 halaman): untuk skenario startup vs skenario enterprise regulated (finansial/kesehatan), stack observability mana yang direkomendasikan dan kenapa.

Status: Not Started

---

## Module 9 — Observability Production: Dari SLO ke Praktik

Description: Menutup lingkaran kembali ke konsep SRE (`courses/cloud-devops-foundations`) — menerapkan SLO burn-rate alerting dengan tool nyata dari modul-modul sebelumnya.

Lessons:

* [ ] 9.1 Recap singkat SLI/SLO/error budget (cross-ref `courses/cloud-devops-foundations`) — kenapa alert berbasis burn-rate lebih baik dari threshold statis
* [ ] 9.2 Implementasi SLO burn-rate alert dengan PromQL di Grafana/Alertmanager
* [ ] 9.3 Dashboard-as-code: menyimpan definisi dashboard/alert sebagai kode (Grafana provisioning), bukan klik-klik manual
* [ ] 9.4 Menulis runbook incident: format yang benar-benar dipakai on-call engineer, bukan dokumentasi teoretis

Mini Project: Definisikan 1 SLO nyata untuk service capstone (misal: 99% request < 300ms), implementasikan burn-rate alert-nya, tulis runbook 1 halaman untuk insiden terkait.

Status: Not Started

---

# Capstone Project

Description: **Observability End-to-End untuk Sistem Multi-Service** — instrumentasi sistem 2-3 service Spring Boot secara penuh: metrics ke Prometheus+Grafana, logs ke Loki atau ELK, traces ke Tempo/Jaeger, semua lewat OpenTelemetry, plus SLO burn-rate alert dan runbook.

Acceptance Criteria:

* Minimal 2 service terinstrumentasi penuh (metrics + structured log + trace), terhubung lewat trace context propagation
* Dashboard Grafana menampilkan RED metrics dan bisa dinavigasi ke trace terkait (exemplar atau link manual)
* Minimal 1 SLO didefinisikan dengan burn-rate alert yang terverifikasi bisa trigger
* Runbook insiden 1 halaman tertulis dan bisa diikuti orang lain (bukan cuma dipahami penulisnya)
* Seluruh stack bisa dijalankan ulang dari `docker-compose up` (reproducible, bukan setup manual sekali pakai)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Tiga pilar observability
