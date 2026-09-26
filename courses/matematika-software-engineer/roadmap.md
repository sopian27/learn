# Course Roadmap

## Course Information

* Course Name: Matematika untuk Software Engineer — Berpikir Matematis untuk Coding, Sistem, dan Keputusan Engineering
* Category: Mathematics Foundations untuk engineering (logika, bilangan di mesin, pertumbuhan, latency, queueing, reliability, estimasi kapasitas) (Domain: mathematics-for-software-engineers — lihat `domains/mathematics-for-software-engineers/DOMAIN.md`)
* Basis: outline "Matematika untuk Software Engineer" buatan user (40 modul, 30-Day Mathematics Lab, Final Test 15 pertanyaan, Final Engineer Mindset). Urutan gagasan dipertahankan; 40 modul dilipat jadi 11 modul course karena ±22 modul outline sudah punya rumah di course lain.
* Difficulty: Level 1 (bisa coding, tahu Big-O dan "average latency" secara kabur; belum bisa memodelkan masalah, menghitung kapasitas, atau menjelaskan `0.1 + 0.2`) → Level 3 (bisa memodelkan masalah software dengan asumsi berlabel, menghitung latency/antrean/reliability/kapasitas dengan rentang dan sanity check)
* Level: Beginner → Intermediate
* Estimated Duration: 11 modul, 3-5 lesson/modul (±42 lesson) + 30-Day Mathematics Lab (paralel) + Capstone. Pace mengikuti kedalaman hitungan, bukan dikebut.
* Prerequisites: Aljabar SMA dan kemampuan coding Java dasar. Tidak wajib menyelesaikan `statistics-probability` atau `computational-thinking` lebih dulu; rujukan silang diberikan per modul.

---

# Catatan Scope (menunggu konfirmasi saat approval)

* **Dibuat 2026-09-26 setelah dua keputusan user lewat AskUserQuestion:** "audit overlap dulu" (guard 50%), lalu "course terlipat ~11 modul". Nama domain dan nama course adalah **asumsi mentor**.
* **Hasil audit (dari daftar modul dan grep, bukan pembacaan lesson penuh semua course):** ±22 dari 40 modul outline sudah punya rumah. Mapping:
  * Outline 11, 14, 15, 20-22 (Big-O, sigma, rekursi, graph, tree, algoritma graph) → `computational-thinking` M2, M4, M7, M9.
  * Outline 16, 17, 18 (sebagian), 34 → `statistics-probability` M1-11.
  * Outline 23-27 (linear algebra, vector, embedding, turunan, gradient descent, matrix di ML) → `ml-fundamentals` M2-3, M8; `neural-network`; `llm`.
  * Outline 28 → `cryptography` M3-4.
  * Outline 31-32 (sebagian) → `performance-engineering` M7 (capacity dari hasil load/stress test), `backend-fundamentals` M14.
  * Outline 33 → `personal-finance`, `investing-stocks`.
  * Outline 25 (physics simulation) → `physics-for-software-engineers`.
  * Overflow C dan fixed-point sebagian → `nasa-software-engineer` 3.4, 12.4; `satellite-programming` 2.4.
* **Gap nyata (jadi modul native):** logika/boolean/himpunan/fungsi (outline 2-4, 9), proof/invariant (5), bilangan di mesin (6, 8, 10, 37 sebagian, 7 terapan), eksponen dan compound growth (13), percentile dan tail latency (18 sisa), queueing dan Little's Law (19), matematika reliability (30), information theory (29), estimasi dan back-of-envelope (1, 35-39), capstone (40).
* **Penyesuaian atas outline (menunggu persetujuan):**
  * **Label tiga jenis klaim** [Teorema/definisi/standar], [Model/asumsi], [Heuristik] di setiap lesson. Outline menyajikan mis. "10 → 50 → 100 service" sebagai jawaban tunggal; hasilnya hanya benar bila kegagalan independen dan dependensi serial.
  * **Capstone diberi rentang dan bagian "yang membuat perhitungan ini salah"** — outline hanya meminta satu set angka.
  * **Outline 36 dan 39 (system design dan Fermi)** digabung ke Module 9 dengan sanity check dan sensitivitas wajib.
  * **Outline 37 (jebakan matematis)** disebar ke Module 4 (overflow, floating point, rounding, unit mismatch, percentage vs percentage point), Module 6 (average vs percentile), dan Module 5 (Big-O tanpa memahami input); correlation ≠ causation dirujuk ke `statistics-probability`.
  * **Outline 25-27 (calculus dan matrix ML)** tidak jadi modul; dirujuk. Final Test 9-10 dijawab dengan rujukan ke `ml-fundamentals`.
* **Temuan riset (ringkasan referensi, belum teks primer):** Little's Law butuh sistem stabil/stationary tetapi tidak butuh asumsi distribusi; M/M/1 adalah model dengan asumsi; SRE Book memakai "nines" dan error budget = 1 - SLO; Dean & Barroso memberi dasar tail latency di fan-out. Belum diverifikasi: Shannon/MacKay, buku antrean, Guesstimation, tabel latency numbers, versi IEEE 754.
* **Bukan gerbang.** 11 modul bukan syarat jadi engineer yang baik. Berhenti di titik yang cukup adalah hasil sah; Module 1, 4, 6, 7, 8, 9 memberi hasil praktis terbesar (prioritas jika waktu terbatas). Selaras dengan catatan overstudy user.
* **Yang belum ditanyakan ke user:** apakah ada buku sumber outline, apakah capstone sebaiknya memakai sistem dari pekerjaan sekarang, dan apakah `computational-thinking` M2 perlu catatan silang ke course ini.

Asumsi di atas menunggu konfirmasi/koreksi user saat approval, sebelum Lesson 1 dimulai.

---

# Pemetaan Outline ke Modul

| Outline (40 modul) | Modul di course ini |
|---|---|
| 1, 35, 38, 39 (thinking, debugging, mental math, Fermi) | Module 1 |
| 2, 3, 4, 9 (logic, sets, functions, boolean) | Module 2 |
| 5 (proof) | Module 3 |
| 6, 7, 8, 10, 37 (arithmetic, modular, binary, number systems, mistakes) | Module 4 |
| 12, 13, 14 (log, exponents, sequences) | Module 5 |
| 18 (sisa: percentile latency) | Module 6 |
| 19 (queueing) | Module 7 |
| 30 (distributed math) | Module 8 |
| 31, 32, 36 (capacity, performance, system design) | Module 9 |
| 29, 16 (sisa: config space) (information, counting terapan) | Module 10 |
| 40 (capstone), Final Test | Module 11 |
| 11, 15, 20-22 | dirujuk ke `computational-thinking` |
| 16-17, 18 (inti), 34 | dirujuk ke `statistics-probability` |
| 23-27 | dirujuk ke `ml-fundamentals`, `neural-network`, `llm` |
| 28 | dirujuk ke `cryptography` |
| 33 | dirujuk ke `personal-finance`, `investing-stocks` |

---

## Module 1 — Berpikir Model dan Estimasi

Description: Mengubah masalah software jadi model yang bisa dihitung, lalu memeriksa apakah hasilnya masuk akal. Ini modul yang mengikat semua modul lain.

Lessons:

* [ ] 1.1 Model: variabel, konstanta, constraint, asumsi — dari "jumlah user × request/user × data/request" ke estimasi traffic; label: [Heuristik]
* [ ] 1.2 Estimasi Fermi dan back-of-envelope — estimasi → asumsi → hitungan → sanity check (Bentley kolom 7); label: [Heuristik]
* [ ] 1.3 Satuan, orde besaran, dan powers of 2/10 — KB vs KiB, bit vs byte, detik per hari, kesalahan unit; label: [Teorema/definisi/standar]
* [ ] 1.4 Debugging berbasis model — Observation → Hypothesis → Model → Prediction → Test; contoh "DB lambat": berapa query, rows, concurrency, linear atau ada threshold; rujukan `performance-engineering` M1; label: [Heuristik]

Mini Project: Tiga estimasi Fermi dengan rentang (rendah/tengah/tinggi) dari konteks kerja sendiri, tiap estimasi diverifikasi dengan data nyata atau pengukuran kecil, dan selisih dijelaskan.

Status: Not started

---

## Module 2 — Logika, Boolean, Himpunan, dan Fungsi

Description: Membaca kondisi dan struktur data tanpa menebak; fondasi untuk query, authorization, feature flag, dan mapping.

Lessons:

* [ ] 2.1 Proposisi, AND/OR/NOT/XOR, implikasi, ekuivalensi, tabel kebenaran — membaca `if (A && !B || C)` tanpa menebak, termasuk prioritas operator dan short-circuit di Java; label: [Teorema/definisi/standar]
* [ ] 2.2 De Morgan dan penyederhanaan boolean — merapikan kondisi bersarang, feature flag, filter pencarian; kapan penyederhanaan merusak keterbacaan; label: [Teorema/definisi/standar] + [Heuristik]
* [ ] 2.3 Himpunan — union, intersection, difference, complement, Cartesian product; hubungan ke JOIN, deduplikasi, authorization ("role A **dan** permission B" = A ∩ B), dan hati-hati NULL di SQL (logika tiga nilai); label: [Teorema/definisi/standar]
* [ ] 2.4 Fungsi — domain, range, komposisi f(g(x)), injective/surjective/bijective; hubungan ke pipeline request → validation → transformation → response, hashing, mapping ID, dan idempotency; label: [Teorema/definisi/standar]

Mini Project: Ambil lima kondisi `if` nyata dari kode sendiri, buat tabel kebenaran, sederhanakan, dan tulis test yang membuktikan ekuivalensi; tambahkan satu kasus set/JOIN yang salah karena NULL.

Status: Not started

---

## Module 3 — Proof, Induksi, dan Invariant

Description: Membuktikan bahwa solusi benar, secukupnya untuk engineer — bukan matematikawan.

Lessons:

* [ ] 3.1 Direct proof, contradiction, counterexample — kapan satu counterexample cukup untuk membatalkan klaim "cache tidak pernah salah"; label: [Teorema/definisi/standar]
* [ ] 3.2 Induksi matematika — pola dasar dan step; rujukan `computational-thinking` 2.4 (tidak diulang); label: [Teorema/definisi/standar]
* [ ] 3.3 Loop invariant dan terminasi — kenapa loop ini benar dan pasti berhenti; contoh binary search dan partisi; label: [Teorema/definisi/standar]
* [ ] 3.4 Invariant di sistem — state machine, cache, dan transaksi: apa yang selalu benar; batas: invariant dibuktikan di bawah asumsi (mis. tanpa crash di tengah); label: [Model/asumsi]

Mini Project: Tulis loop invariant dan argumen terminasi untuk dua fungsi Java, ditambah satu invariant untuk state machine pesanan dan satu counterexample yang melanggarnya.

Status: Not started

---

## Module 4 — Bilangan di Mesin: Biner, Overflow, Floating Point, Modulo

Description: Bagaimana mesin sebenarnya menyimpan angka, dan kenapa banyak bug matematika di produksi terjadi di sini.

Lessons:

* [ ] 4.1 Biner, hex, bit, byte, dan operasi bitwise — permission flags, mask, shift; kapan bit flag lebih baik dari enum set; label: [Teorema/definisi/standar]
* [ ] 4.2 Integer bertanda, two's complement, overflow — `Integer.MAX_VALUE + 1` di Java, `Math.addExact`, overflow di ID, timestamp, dan perhitungan uang; rujukan `nasa-software-engineer` 3.4 dan 12.4 (C dan Ariane 5) tanpa mengulang; label: [Standar]
* [ ] 4.3 Floating point (IEEE 754) — kenapa `0.1 + 0.2 != 0.3`, epsilon, NaN, dan kenapa uang memakai `BigDecimal` atau integer minor unit; rujukan Goldberg (1991); label: [Standar]
* [ ] 4.4 Modulo dan aritmetika modular terapan — `%` vs `Math.floorMod` untuk angka negatif, pagination, sharding, circular buffer, dan hubungan dasar ke hashing; rujukan `cryptography` 4.1 dan `computational-thinking` M6; label: [Teorema/definisi/standar]
* [ ] 4.5 Rounding, persentase, dan unit — percentage vs percentage point, rounding mode, unit mismatch (Mars Climate Orbiter sudah dibahas di `nasa-software-engineer` 12.4, dirujuk); label: [Teorema/definisi/standar] + [Heuristik]

Mini Project: Suite eksperimen Java (overflow, floating point, floorMod, rounding) dengan test yang menampilkan tiap kegagalan, plus satu perbaikan tiap kasus dan penjelasan kenapa perbaikan itu benar.

Status: Not started

---

## Module 5 — Pertumbuhan: Eksponen, Logaritma, dan Deret

Description: Intuisi pertumbuhan untuk kapasitas, retry, dan biaya — bukan analisis algoritma (itu di `computational-thinking`).

Lessons:

* [ ] 5.1 Eksponen dan compound growth — doubling time, aturan 72 sebagai heuristik, pertumbuhan data dan traffic; label: [Teorema/definisi/standar] + [Heuristik]
* [ ] 5.2 Logaritma sebagai "berapa kali dibagi dua" — log₂, kenapa binary search dan tinggi tree logaritmis, dan hubungan ke kedalaman index; rujukan `computational-thinking` M2 dan M7; label: [Teorema/definisi/standar]
* [ ] 5.3 Deret geometrik — exponential backoff, retry storm, dan total waktu tunggu; label: [Teorema/definisi/standar] + [Model/asumsi]
* [ ] 5.4 Deret aritmetika dan sigma — biaya total loop bersarang dan pagination offset; label: [Teorema/definisi/standar]
* [ ] 5.5 Big-O tanpa memahami input — kapan `O(n log n)` kalah dari `O(n²)` untuk n kecil dan konstanta besar; label: [Heuristik]

Mini Project: Kalkulator backoff dan retry (jitter vs tanpa jitter) dengan tabel total waktu tunggu untuk 5, 10, 15 percobaan, ditambah proyeksi pertumbuhan data 12 bulan dengan tiga laju pertumbuhan.

Status: Not started

---

## Module 6 — Distribusi dan Percentile Latency

Description: Membaca latency dengan benar. Ringkas terhadap `statistics-probability`; hanya bagian yang menyangkut sistem.

Lessons:

* [ ] 6.1 Mean, median, percentile (p50/p90/p95/p99/p99.9) — kenapa rata-rata sering menyesatkan pada distribusi berekor panjang; rujukan `statistics-probability` M1; label: [Teorema/definisi/standar] + [Praktik industri]
* [ ] 6.2 Percentile tidak bisa dirata-ratakan — agregasi lintas instance, histogram vs summary; label: [Model/asumsi]
* [ ] 6.3 Tail at scale — request fan-out: P(minimal satu lambat) = 1 - (1 - p)^N (asumsi independensi); gagasan hedged request dari Dean & Barroso (2013); label: [Model/asumsi] + [Praktik industri]
* [ ] 6.4 Outlier, sampling, dan kapan p99 pun menyesatkan — sample kecil, bucket terlalu kasar; label: [Heuristik]

Mini Project: Ambil log latency nyata (atau sintetis berekor panjang), hitung mean/median/p95/p99, tunjukkan satu keputusan yang berubah kalau hanya melihat rata-rata, dan hitung P(lambat) untuk fan-out 10, 50, 100.

Status: Not started

---

## Module 7 — Queueing dan Little's Law

Description: Apa yang terjadi bila request masuk lebih cepat daripada kemampuan service memprosesnya.

Lessons:

* [ ] 7.1 Arrival rate, service rate, utilization, throughput, latency — vocabulary dan satuan; label: [Teorema/definisi/standar]
* [ ] 7.2 Little's Law L = λW — kenapa berlaku tanpa asumsi distribusi, dan syarat stabil/stationary; contoh thread pool, connection pool, dan WIP; label: [Teorema]
* [ ] 7.3 Utilization dan kurva "hockey stick" — model M/M/1 sebagai ilustrasi, asumsinya (Poisson, eksponensial), dan kenapa 80-90% utilization bisa berbahaya; label: [Model/asumsi]
* [ ] 7.4 Backpressure, queue tak terbatas, dan Kafka consumer lag — kapan antrean menyelamatkan dan kapan menunda kegagalan; rujukan `backend-fundamentals` M12; label: [Praktik industri]
* [ ] 7.5 Kapan model antrean gagal — beban bursty, prioritas, retry menambah beban; label: [Heuristik]

Mini Project: Simulasi antrean kecil di Java (satu server, arrival acak) yang memverifikasi Little's Law secara empiris, lalu ubah beban sampai stabilitas rusak dan catat titik patah.

Status: Not started

---

## Module 8 — Matematika Reliability

Description: Menghitung availability, redundancy, dan retry dengan sadar akan asumsi independensi.

Lessons:

* [ ] 8.1 Availability, "nines", dan budget downtime — 99.9% = ±8,76 jam/tahun (dihitung ulang sebelum ditulis); error budget = 1 - SLO (Google SRE Book); label: [Praktik industri] + [Teorema/definisi/standar]
* [ ] 8.2 Sistem serial — availability gabungan = hasil kali: 0,999^10 ≈ 0,990, 0,999^50 ≈ 0,951, 0,999^100 ≈ 0,905 (dihitung ulang); asumsi: kegagalan independen dan dependensi keras; label: [Model/asumsi]
* [ ] 8.3 Redundancy dan replikasi — sistem paralel 1 - (1 - a)^k, dan kenapa kegagalan berkorelasi (satu region, satu deployment buruk) merusak hitungan; label: [Model/asumsi]
* [ ] 8.4 Retry dan probabilitas sukses — expected value percobaan, amplifikasi beban saat retry; rujukan `chaos-resilience-engineering` dan `statistics-probability` M2-3; label: [Model/asumsi]
* [ ] 8.5 Dari angka ke keputusan — kapan menambah redundancy lebih murah daripada menaikkan availability satu komponen; rujukan `system-design` M9; label: [Heuristik]

Mini Project: Kalkulator availability serial-paralel untuk satu arsitektur nyata (5-8 komponen), dengan tabel sensitivitas terhadap korelasi kegagalan dan satu bagian "yang membuat hitungan ini salah".

Status: Not started

---

## Module 9 — Capacity Planning dan Estimasi Sistem

Description: Dari "sistem harus melayani 1 juta user" ke angka yang bisa dipakai memilih arsitektur. Estimasi sebelum ada sistem; pengukuran setelahnya ada di `performance-engineering` M7.

Lessons:

* [ ] 9.1 Dari user ke traffic — DAU, requests/hari, RPS rata-rata (50 juta request/hari ≈ 579 RPS, dihitung ulang), dan peak factor sebagai asumsi (2-3x adalah heuristik, bukan hukum); label: [Heuristik] + [Model/asumsi]
* [ ] 9.2 Storage dan bandwidth — event per user × ukuran × retensi × replikasi; contoh 10 juta user × 20 event × 1 KB (dihitung ulang, dengan KB vs KiB dicatat); label: [Model/asumsi]
* [ ] 9.3 Database, cache, dan Kafka — QPS DB, cache hit ratio dan dampaknya ke beban DB, jumlah partisi dari throughput; rujukan `backend-fundamentals` M9, `system-design` M8-10; label: [Model/asumsi] + [Heuristik]
* [ ] 9.4 Sanity check dan sensitivitas — asumsi mana yang mengubah hasil 10x, dan bagaimana menguji satu asumsi lewat pengukuran kecil; label: [Heuristik]
* [ ] 9.5 Estimasi vs pengukuran — kapan estimasi cukup dan kapan wajib load test; rujukan `performance-engineering` M6-7; label: [Praktik industri]

Mini Project: Lembar estimasi untuk satu fitur nyata (mis. notifikasi atau feed) dengan rentang, asumsi berlabel, dan tiga pertanyaan yang harus dijawab pengukuran sebelum desain final.

Status: Not started

---

## Module 10 — Information Theory dan Ruang Kemungkinan

Description: Seberapa banyak informasi ada dalam data, dan seberapa besar ruang kemungkinan yang harus dihadapi.

Lessons:

* [ ] 10.1 Informasi dan entropi — kejutan, bit, dan Shannon (1948); sumber diverifikasi sebelum ditulis; label: [Teorema/definisi/standar]
* [ ] 10.2 Kompresi — batas entropi, lossless vs lossy, dan kenapa data acak tidak bisa dikompres; label: [Teorema/definisi/standar]
* [ ] 10.3 Ruang kemungkinan — 10 feature flag × 2 kondisi = 1.024 konfigurasi, keyspace password, ledakan test matrix; rujukan `statistics-probability` 2.3 untuk counting formal; label: [Teorema/definisi/standar] + [Heuristik]
* [ ] 10.4 Entropi, password, dan random — kenapa "kelihatan acak" bukan "acak"; rujukan `cryptography` M1 dan M3; label: [Praktik industri]

Mini Project: Ukur kompresi tiga jenis data (teks, JSON, acak) dan hitung entropi perkiraan; hitung ruang konfigurasi satu sistem nyata dan usulkan strategi mengurangi test matrix.

Status: Not started

---

## Module 11 — Capstone: Design a Million-User System

Description: Menyatukan model, hitungan, uji, dan validasi menjadi satu dokumen desain berbasis angka.

Lessons:

* [ ] 11.1 Memilih sistem dan asumsi — 10 juta user, DAU, pola akses, retensi; semua asumsi berlabel; label: [Model/asumsi]
* [ ] 11.2 Hitungan penuh — requests/hari, peak RPS, storage/hari dan tahun, bandwidth, DB, cache, Kafka, replikasi, availability gabungan (Modul 1, 5-9); label: [Model/asumsi]
* [ ] 11.3 Desain garis besar — API → Redis → Kafka → DB → object storage → Kubernetes, dengan angka mendukung tiap pilihan; rujukan `system-design`; label: [Praktik industri]
* [ ] 11.4 "Yang membuat perhitungan ini salah" — tiga asumsi terlemah, dampak jika meleset 10x, dan pengukuran yang akan dilakukan; label: [Heuristik]
* [ ] 11.5 Final Test — 15 pertanyaan outline dijawab tertulis secara intuitif (bukan hafalan); label: penilaian

Capstone Project: Dokumen "Design a Million-User System" (numbers-first) + Mathematical Debug Log + jawaban Final Test. Disimpan ke `portofolio/`.

Status: Not started

---

# 30-Day Mathematics Lab (paralel)

Dijalankan paralel sepanjang course. 15-30 menit teori + 3 soal + 1 aplikasi software engineering per hari. Hari terlewat dicatat sebagai terlewat.

* **Week 1 — Logic, Boolean, Binary, Modular:** Modul 2-4.
* **Week 2 — Growth, Log, Recursion, Counting:** Modul 5, 10 (dan rujukan `computational-thinking`).
* **Week 3 — Probability, Latency, Queueing, Reliability:** Modul 6-8 (dan rujukan `statistics-probability`).
* **Week 4 — Estimation, Capacity, System Design:** Modul 1, 9, 11 (dan rujukan `ml-fundamentals` untuk sisi linear algebra/calculus intuisi).

---

# Final Test (outline)

Jawaban tertulis untuk 15 pertanyaan: (1) mengapa binary search O(log n), (2) mengapa nested loop bisa O(n²), (3) kapan O(n log n) muncul, (4) hubungan modulo dan hashing, (5) mengapa floating point menghasilkan error, (6) mengapa p99 lebih berguna dari average, (7) bagaimana probability membantu reliability, (8) graph theory di microservices, (9) mengapa vector penting di AI, (10) hubungan derivative dan machine learning, (11) menghitung peak RPS, (12) menghitung kebutuhan storage, (13) menghitung availability sistem terdistribusi, (14) matematika untuk debugging, (15) matematika untuk keputusan arsitektur. **Pertanyaan 1-3, 8-10 dijawab dengan rujukan ke `computational-thinking`, `ml-fundamentals`, dan `neural-network`;** jawaban yang benar harus menyebut asumsi tiap model.
