# Course Roadmap

## Course Information

* Course Name: Performance Engineering — Profiling, Benchmarking (JMH), Load & Stress Testing, dari Menebak ke Mengukur
* Category: Performance Engineering (Backend, hands-on tooling & methodology) — lebih dalam dari `courses/backend-fundamentals` Module 3 (JVM Internals untuk Debugging Production, yang hanya cukup untuk baca heap/thread dump dasar saat insiden) dan dari objective "performance engineering dasar" course itu
* Difficulty: Level 2 (bisa baca heap/thread dump dasar ala Backend Fundamentals Module 3) → Level 4 (bisa menjalankan siklus performance engineering penuh: baseline → profil → diagnosis → benchmark perbaikan → verifikasi, dengan data, bukan tebakan)
* Estimated Duration: 8 modul, 3-4 lesson/modul, ~30-40 menit/lesson. Perkiraan ≈ 3-4 bulan pace sedang.
* Prerequisites: `courses/backend-fundamentals` Module 1-3 (Java lanjut, concurrency, JVM internals dasar) — course ini melanjutkan langsung dari situ ke tooling dan metodologi yang lebih dalam.

---

# Catatan Scope (draft — menunggu approval & kalibrasi user)

Draft ini dibuat atas permintaan eksplisit user (2026-08-07) untuk mengisi gap "Performance Engineering mendalam (JMH/profiling/load test)". **Belum ada sesi kalibrasi** — asumsi di bawah ini perlu dikonfirmasi:

1. **Tidak menduplikasi `courses/backend-fundamentals` Module 3**: modul itu cukup untuk "baca heap dump/thread dump dasar saat insiden" (reactive, level minimum). Course ini adalah disiplin penuh: metodologi sistematis (USE/RED method), microbenchmarking JMH yang benar, profiling proaktif, load/stress testing terstruktur, capacity planning.
2. **Asumsi tool load testing**: menggunakan **k6** sebagai tool utama (modern, scriptable JavaScript, developer-friendly) dengan JMeter/Gatling disinggung sebagai alternatif di Module 6 — perlu dikonfirmasi apakah user punya preferensi tool lain (misal Gatling karena berbasis Scala/JVM, lebih dekat ke stack utama repo).
3. **Fokus JVM/Java** (konsisten stack repo) — JMH, async-profiler, JFR, Eclipse MAT semuanya tool ekosistem Java/JVM.
4. **Module 8** eksplisit tentang budaya performance berkelanjutan (continuous profiling, performance regression di CI) — cross-ref `courses/ci-cd` untuk mekanisme pipeline, tidak mengulang.

Referensi riset (untuk disusun jadi roadmap final saat approval):

* *Systems Performance* (Brendan Gregg) — metodologi USE method, fondasi Module 1
* *Java Performance: The Definitive Guide* (Scott Oaks) — fondasi profiling & JVM performance Module 3-5
* [JMH samples & docs (OpenJDK)](https://github.com/openjdk/jmh) — Module 2
* [async-profiler docs](https://github.com/async-profiler/async-profiler) & [JDK Flight Recorder docs](https://docs.oracle.com/en/java/javase/21/jfapi/) — Module 3
* [Eclipse Memory Analyzer (MAT) docs](https://eclipse.dev/mat/) — Module 4
* [k6 docs](https://k6.io/docs/) — Module 6 (alternatif: [Gatling docs](https://gatling.io/docs/), [JMeter docs](https://jmeter.apache.org/usermanual/index.html))
* `standards/PERFORMANCE_STANDARDS.md` — "measure before optimizing", prinsip inti course ini

---

# Learning Objectives

* [ ] Menerapkan metodologi performance engineering (USE method, RED method) alih-alih tuning berdasarkan intuisi
* [ ] Menulis microbenchmark JMH yang benar, memahami jebakan JIT warmup, dead code elimination, constant folding
* [ ] Melakukan CPU profiling (sampling vs instrumentation) dan membaca flame graph untuk menemukan hot method
* [ ] Menganalisis heap dump secara sistematis dengan Eclipse MAT untuk menemukan memory leak dan retention path
* [ ] Mendiagnosis deadlock dan lock contention dari thread dump
* [ ] Merancang dan menjalankan load test yang realistis dengan k6
* [ ] Melakukan stress test dan soak test untuk menemukan breaking point dan memory leak di bawah beban berkelanjutan
* [ ] Menerapkan capacity planning dasar berbasis data, bukan asumsi
* [ ] Menyelesaikan capstone: siklus performance engineering penuh pada 1 service, dibuktikan dengan angka before/after

---

# Course Modules

## Module 1 — Metodologi Performance Engineering

Description: Fondasi cara berpikir sebelum menyentuh satu profiler pun — mencegah "tuning berdasarkan tebakan" yang jadi anti-pattern paling umum.

Lessons:

* [ ] 1.1 USE Method (Utilization, Saturation, Errors) untuk resource — cara sistematis mempersempit dugaan bottleneck
* [ ] 1.2 RED Method (Rate, Errors, Duration) untuk service — melengkapi USE dari sisi request
* [ ] 1.3 Mendefinisikan performance requirement/SLO **sebelum** optimasi — "cepat" itu relatif terhadap target apa
* [ ] 1.4 Kenapa "expert intuition tuning" sering salah — studi kasus optimasi yang ternyata tidak berdampak (atau memperburuk)

Mini Project: Ambil 1 service yang "terasa lambat" (dari course lain/kerjaan), terapkan USE+RED method untuk mempersempit dugaan bottleneck **sebelum** melakukan perubahan apa pun — dokumentasikan hipotesis awal vs data.

Status: Not Started

---

## Module 2 — Microbenchmarking dengan JMH

Description: Kenapa `System.currentTimeMillis()` di awal dan akhir method adalah cara mengukur performa yang hampir selalu bohong — dan cara mengukur yang benar.

Lessons:

* [ ] 2.1 Kenapa microbenchmark naif berbohong: JIT warmup, dead code elimination, constant folding
* [ ] 2.2 Menulis benchmark JMH yang benar: `@Benchmark`, `@State`, `@Warmup`, `@Measurement`, `Blackhole`
* [ ] 2.3 Interpretasi hasil JMH: throughput vs average time, standard deviation, kapan hasil bisa dipercaya
* [ ] 2.4 Benchmark perbandingan 2 implementasi (misal: `StringBuilder` vs concatenation, `ArrayList` vs `LinkedList` untuk operasi tertentu) — memverifikasi klaim performa, bukan asumsi dari nama class

Mini Project: Tulis benchmark JMH untuk membandingkan 2 implementasi alternatif dari 1 method nyata (dari course backend-fundamentals atau kerjaan), tarik kesimpulan berbasis angka.

Status: Not Started

---

## Module 3 — CPU Profiling

Description: Menemukan method mana yang benar-benar menghabiskan waktu CPU — tanpa menebak dari membaca kode saja.

Lessons:

* [ ] 3.1 Sampling profiler vs instrumentation profiler — trade-off overhead vs akurasi
* [ ] 3.2 async-profiler & JDK Flight Recorder (JFR): cara menjalankan profiling di aplikasi Spring Boot yang sedang berjalan
* [ ] 3.3 Membaca flame graph: mengidentifikasi hot path, self time vs total time
* [ ] 3.4 Profiling di production dengan overhead minimal — kapan aman dilakukan, kapan berisiko

Mini Project: Profiling 1 endpoint yang sengaja dibuat lambat (misal: algoritma tidak efisien atau N+1 query), temukan hot method lewat flame graph, perbaiki, verifikasi dengan profiling ulang.

Status: Not Started

---

## Module 4 — Memory Profiling & Heap Analysis

Description: Memperdalam heap dump analysis dari sekadar "baca dasar" (Backend Fundamentals Module 3) menjadi workflow investigasi sistematis dengan tooling profesional.

Lessons:

* [ ] 4.1 Mengambil heap dump dari aplikasi production tanpa mematikan service (`jcmd`, `jmap`)
* [ ] 4.2 Eclipse MAT: dominator tree, retained size vs shallow size
* [ ] 4.3 Menemukan memory leak secara sistematis: leak suspect report, retention path ke GC root
* [ ] 4.4 Kelas leak umum di Spring Boot (static collection, listener tak ter-unregister, `ThreadLocal`) — mendiagnosisnya lewat MAT, bukan menebak dari nama class

Mini Project: Diberi aplikasi Spring Boot dengan memory leak yang sengaja disembunyikan (lebih rumit dari Backend Fundamentals Module 3) — ambil heap dump, temukan retention path lengkap dengan Eclipse MAT, perbaiki.

Status: Not Started

---

## Module 5 — Thread Dump & Diagnosis Konkurensi

Description: Mendiagnosis bug konkurensi yang hanya muncul di production di bawah beban nyata.

Lessons:

* [ ] 5.1 Mengambil & membaca thread dump: state thread (`RUNNABLE`, `BLOCKED`, `WAITING`), stack trace tiap thread
* [ ] 5.2 Mendiagnosis deadlock dari thread dump: mengenali circular lock dependency
* [ ] 5.3 Lock contention profiling: menemukan lock yang jadi bottleneck di bawah concurrency tinggi
* [ ] 5.4 Thread pool starvation: mendiagnosis dari thread dump kenapa request "menggantung" padahal tidak ada exception

Mini Project: Diberi service dengan deadlock atau lock contention yang sengaja dibuat di bawah beban paralel — diagnosis dari thread dump, perbaiki, verifikasi dengan load test singkat.

Status: Not Started

---

## Module 6 — Load Testing

Description: Menguji sistem di bawah beban realistis sebelum pengguna nyata yang menemukan batasnya.

Lessons:

* [ ] 6.1 Merancang skenario load test yang realistis: distribusi traffic, ramp-up pattern, bukan sekadar "hantam sekuat mungkin"
* [ ] 6.2 Load testing dengan k6: menulis script, menjalankan, membaca metrik hasil (p95/p99 latency, error rate)
* [ ] 6.3 Menentukan target load dari data nyata (traffic pattern aktual) bukan angka sembarang
* [ ] 6.4 Alternatif tool: Gatling & JMeter — kapan masing-masing lebih cocok dibanding k6

Mini Project: Rancang & jalankan load test k6 untuk 1 endpoint dari course lain, tentukan baseline p95/p99 latency dan error rate di beberapa level traffic.

Status: Not Started

---

## Module 7 — Stress Testing & Capacity Planning

Description: Mencari titik patah sistem secara sengaja, sebelum lonjakan traffic nyata yang menemukannya secara tidak sengaja.

Lessons:

* [ ] 7.1 Stress testing: menaikkan beban sampai sistem gagal, mengidentifikasi failure mode (latency naik gradual vs collapse tiba-tiba)
* [ ] 7.2 Soak testing: beban sedang dalam durasi lama — menemukan memory leak/resource exhaustion yang tidak muncul di test singkat
* [ ] 7.3 Capacity planning dasar: dari hasil load/stress test, hitung berapa instance/resource dibutuhkan untuk target traffic tertentu
* [ ] 7.4 Menentukan bottleneck resource (CPU, memory, DB connection pool, network) dari hasil stress test

Mini Project: Jalankan stress test pada service Module 6 sampai menemukan titik patah, identifikasi resource yang jadi bottleneck, tulis rekomendasi capacity planning berbasis data.

Status: Not Started

---

## Module 8 — Budaya Performance Engineering Berkelanjutan

Description: Performance engineering bukan aktivitas sekali saat ada masalah, tapi kebiasaan berkelanjutan — menutup lingkaran kembali ke prinsip Module 1.

Lessons:

* [ ] 8.1 Continuous profiling di production: overhead rendah, always-on, membandingkan performa antar-deploy
* [ ] 8.2 Performance regression testing di CI: menjalankan benchmark JMH sebagai gate, mencegah regresi lolos ke production (cross-ref `courses/ci-cd` untuk mekanisme pipeline)
* [ ] 8.3 Performance budget: menetapkan batas performa yang tidak boleh dilanggar fitur baru, dan menegakkannya sebagai bagian definition of done

Mini Project: Tulis 1 performance budget untuk service capstone (misal: p95 < 200ms, memory footprint < X MB), rancang bagaimana ini ditegakkan di CI (boleh berupa desain, tidak wajib implementasi CI penuh).

Status: Not Started

---

# Capstone Project

Description: **Siklus Performance Engineering Penuh** — ambil 1 service Spring Boot yang sengaja dibuat suboptimal, jalankan siklus lengkap: baseline load test → profiling CPU & memory → identifikasi bottleneck → benchmark JMH untuk memverifikasi perbaikan → load test ulang untuk membuktikan peningkatan → tulis laporan performance.

Acceptance Criteria:

* Baseline load test terdokumentasi (p95/p99 latency, throughput, error rate) sebelum perubahan apa pun
* Bottleneck diidentifikasi lewat profiling nyata (flame graph/heap dump), bukan dugaan
* Perbaikan diverifikasi dengan benchmark JMH sebelum dianggap selesai
* Load test ulang menunjukkan peningkatan terukur dibanding baseline
* Laporan performance ditulis dalam format yang bisa dipahami stakeholder non-teknis (angka before/after, bukan jargon profiler)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/8

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — USE Method
