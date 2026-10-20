# Course Roadmap

## Course Information

* Course Name: Kalkulus untuk Software Engineer — Memahami Perubahan, Akumulasi, dan Optimasi pada Sistem Software (dengan Java)
* Category: Applied Calculus (Domain: applied-calculus — lihat `domains/applied-calculus/DOMAIN.md`)
* Basis: outline "Kalkulus untuk Software Engineer" buatan user (48 seksi bernomor, 30-Day Calculus Lab, Final Challenge 10 pertanyaan, Final Test, Personal Engineer Principle). Urutan isi badan dokumen dipertahankan; 48 seksi dilipat jadi 7 modul isi + capstone karena hasil audit overlap. Outline menyebut "12 level" di bagian atas tetapi badan dokumen memuat 20 level; badan dokumen dipakai sebagai acuan.
* Difficulty: Level 1 (paham aljabar dasar dan programming, belum memakai kalkulus sebagai alat berpikir) → Level 3 (bisa menurunkan dan menginterpretasi turunan/integral untuk model sistem, mengimplementasikan metode numerik di Java dengan analisis error, membuat dan menguji model latency/capacity, serta memutuskan kapan model layak)
* Level: Intermediate
* Estimated Duration: Module 0 jembatan aljabar (4 lesson) + 7 modul isi × 4 lesson (±28 lesson) + Capstone (Modul 8) + 30-Day Calculus Lab (paralel). Pace mengikuti pemahaman, tidak dikebut.
* Prerequisites: Java dasar (repo ini). Aljabar dasar (fungsi, persamaan, eksponen, logaritma) ditambal di Module 0 karena learner melaporkan masih kurang. Tidak perlu pernah belajar kalkulus. `courses/ml-fundamentals` Module 2-3 opsional. Kalibrasi tingkat matematika learner belum dilakukan (lihat Catatan Scope).

---

# Catatan Scope (menunggu konfirmasi saat approval)

* **Dibuat 2026-09-26 lewat AskUserQuestion**: user memilih audit overlap dulu (override guard 50%: Character Development pas 3/6 modul, `male-grooming` masih Module 1), lalu memilih **"Standalone terlipat ~8 modul"** setelah audit. Domain baru `applied-calculus` adalah asumsi mentor.
* **Hasil overlap audit (baca daftar modul, bukan lesson per lesson):**
  * `ml-fundamentals` Module 3 (turunan, gradient, chain rule, gradient descent, intuitif) dan Module 8 (backprop) — **sudah ada**.
  * `neural-network` (derivasi formal: turunan, chain rule multivariabel, backprop, gradient checking) — **sudah ada**.
  * `statistics-probability` Module 4-6 (PDF/CDF, expectation, variance) — **sudah ada**.
  * `physics-for-software-engineers` Module 6 (integrator numerik Euler/Verlet/RK4, ODE), Module 14 (PID) — **sudah ada**; Module 1.1 memakai turunan/integral "secukupnya".
  * `performance-engineering` Module 1, 7 (USE Method, capacity planning dasar dari hasil load test) — metodologi ada, **matematika modelnya belum**.
  * Tidak ditemukan di repo: sensitivity/optimasi sebagai kerangka engineering, integral sebagai akumulasi traffic/biaya, metode numerik di Java dengan analisis error (finite difference, Simpson, Newton-Raphson), model antrean/latency/backlog/USL sebagai matematika, dan simulator.
* **Perkiraan kasar**: ±55% topik outline sudah punya rumah (per topik, bukan per baris). Bagian itu dirujuk, tidak diajarkan ulang.
* **Cross-reference (tidak diduplikasi):**
  * Modul 2 (chain rule) ↔ `ml-fundamentals` M3, `neural-network` (jembatan ke backprop).
  * Modul 4 (gradient descent) ↔ `ml-fundamentals` M3 (versi ML), di sini versi tuning parameter sistem.
  * Modul 5 (integral) ↔ `statistics-probability` M4 (PDF/CDF); di sini hanya akumulasi dan rata-rata.
  * Modul 6-7 (ODE/Euler) ↔ `physics-for-software-engineers` M6; `performance-engineering` M1, M7 (alat ukur dan load testing).
* **Klaim yang ditandai eksplisit:** (a) kurva M/M/1 dan "knee" adalah properti model dengan asumsi, bukan hukum sistem nyata; (b) Little's Law berlaku umum tetapi dengan kondisi; (c) USL adalah model fit empiris (Gunther), bukan bukti mekanisme; (d) turunan numerik dari data noisy memperbesar noise; (e) `f'(x)=0` hanyalah kandidat, bukan otomatis optimum; (f) "gradient descent selalu menemukan optimum" hanya untuk kasus konveks; (g) ekstrapolasi eksponensial dari data traffic awal sering keliru; (h) sumber yang belum dicek ulang di sesi draft (3Blue1Brown, Google SRE Book, Boyd, Numerical Recipes) diverifikasi dulu sebelum dikutip di lesson.
* **Kapan overkill:** tiap modul diakhiri pertanyaan "apakah mengukur langsung lebih murah dan lebih dapat dipercaya daripada model ini?" (Final Challenge 10 dan outline "kapan kalkulus overkill").
* **Skala dan Master Goal:** kecocokan dengan Master Goal (backend freelance internasional) sedang: relevan untuk performance/capacity reasoning dan sebagai fondasi ML. Tidak diklaim wajib.
* **Kalibrasi (2026-09-27):** learner melaporkan aljabar/logaritma/eksponen "masih kurang, perlu belajar lagi". Modul 1 tetap tidak dipadatkan; ditambah **Module 0 (4 lesson, jembatan aljabar)** sebelum Modul 1. Tingkat kekurangan spesifik belum diukur (hanya laporan diri); Exit gate Module 0 memakai soal konkret sebagai pengukur. `matematika-software-engineer` tidak menutup celah ini karena prasyaratnya juga aljabar SMA.
* **Alat plot belum dipilih:** Java tidak punya plotting bawaan; pilihan CSV + spreadsheet/matplotlib atau library Java (mis. JFreeChart) ditanyakan saat approval.
* **Pacing dan overstudy:** memori user mencatat kecenderungan overstudy. Aturan: satu modul boleh berhari-hari; bobot pada kode dan model yang bekerja; hari Lab terlewat dicatat terlewat; course ini **bukan alasan membuka lebih banyak course**. Bila daftar "yang harus dipelajari" membesar, itu sinyal berhenti.

Asumsi di atas menunggu konfirmasi/koreksi user saat approval, sebelum Lesson 1 dimulai.

---

# Learning Objectives

* [ ] Membedakan perubahan dan rate of change dan bertanya "perubahan terhadap apa?" pada metrik sistem
* [ ] Memodelkan sistem sebagai fungsi, membaca domain/range sebagai validasi, dan membaca grafik (slope, peak, asymptote, growth)
* [ ] Menjelaskan limit dan kontinuitas sebagai perilaku di sekitar suatu nilai, dan mengaitkannya dengan floating point serta perilaku "cliff"
* [ ] Menurunkan dan menginterpretasi turunan fungsi dasar dan aturan (power, sum, product, quotient, chain) dengan satuan yang benar
* [ ] Memakai aproksimasi lokal `Δy ≈ f'(x)Δx` dan elastisitas untuk estimasi cepat
* [ ] Mencari dan mengklasifikasi titik kritis (first/second derivative test) dan menyelesaikan optimasi satu variabel dengan constraint
* [ ] Menjelaskan partial derivative dan gradient dan memakai gradient descent sebagai alat tuning parameter, termasuk peran learning rate
* [ ] Menjelaskan integral sebagai akumulasi (area, total, rata-rata) dan Fundamental Theorem sebagai hubungan rate ↔ total
* [ ] Mengimplementasikan turunan numerik, integrasi numerik (rectangle/trapezoid/Simpson), dan Newton-Raphson di Java dan menganalisis errornya
* [ ] Membuat model kurva latency/antrean/backlog/USL dengan asumsi eksplisit, dan mengujinya terhadap data
* [ ] Menghitung rantai capacity (users → RPS → CPU → instance) dan memakai model pertumbuhan dengan sadar risiko ekstrapolasi
* [ ] Menjelaskan (tanpa derivasi penuh) kenapa chain rule dan autodiff penting untuk ML dan merujuk ke course ML
* [ ] Menyelesaikan Performance Optimization Simulator, 30-Day Calculus Lab, Final Challenge 10 pertanyaan, dan Final Test dari bukti nyata

---

# Pemetaan Outline ke Modul

| Outline (seksi) | Modul | Jenis |
|---|---|---|
| 1-2 (apa itu kalkulus, change vs rate), 3-5 (fungsi, domain/range, grafik), 6-8 (limit, continuity), Level 19 (debugging) | 1 | **Native** — perubahan, fungsi sebagai model, limit di floating point |
| 9-12 (derivative, slope, rules, chain rule), 16-18 (sensitivity, local approximation, error) | 2 | **Native sebagian** + rujukan `ml-fundamentals` M3 untuk chain rule |
| 13-15 (max/min, critical point, optimization) | 3 | **Native** — optimasi satu variabel dengan constraint |
| 26-28 (multivariabel, partial derivative, gradient), 29-31 (gradient descent, learning rate, local/global) | 4 | **Native sebagian** (tuning sistem) + jembatan ke ML 4.4; rujukan `ml-fundamentals` M3 |
| 19-24 (integral, area, definite, FTC, total traffic, average), 25/45 (probabilitas kontinu) | 5 | **Native** (akumulasi); probabilitas kontinu **dirujuk** ke `statistics-probability` M4 |
| 36-37 (numerical methods, Newton-Raphson), 46-48 (numerical derivative/integration, GD di Java) | 6 | **Native** — metode numerik di Java |
| 38-42 (latency curve, capacity, queueing, modeling, growth), 43-44 (differential equations) | 7 | **Native** — model performance dan dinamika; rujukan `performance-engineering`, `physics` M6 |
| 32-35 (loss, neural network, backprop, activation) | 4.4 (jembatan) | **Dirujuk** ke `ml-fundamentals` M3/M8, `neural-network` |
| Project utama, Final Challenge, Final Test, 30-Day Lab, Personal Engineer Principle | 8 | **Native** — capstone |

---

# Course Modules

## Module 0 — Fondasi Aljabar untuk Kalkulus (jembatan prasyarat; ditambah 2026-09-27)

Description: Menambal celah aljabar yang dilaporkan learner ("masih kurang, perlu belajar lagi"): manipulasi persamaan, fungsi dan grafiknya, eksponen, logaritma, dan notasi jumlah. Bukan kalkulus. Tiap konsep diverifikasi lewat kode Java kecil supaya tidak jadi hafalan rumus. Eksponen/log sebagai *pertumbuhan* dirujuk ke `matematika-software-engineer` Module 5 (yang sendiri mengasumsikan aljabar SMA), tidak diulang di sini.

Lessons:

* [ ] 0.1 Aljabar sebagai manipulasi yang menjaga kesetaraan — menyelesaikan persamaan, memfaktorkan, pecahan dan pertidaksamaan; cek hasil dengan substitusi di kode; label: *matematika baku*
* [ ] 0.2 Fungsi dan grafik — notasi `f(x)`, linear vs kuadrat vs `1/x`, gradient (slope) garis, membaca grafik dari tabel nilai yang dihitung dengan Java; label: *matematika baku*
* [ ] 0.3 Eksponen dan akar — aturan pangkat (`aᵐ·aⁿ`, `(aᵐ)ⁿ`, `a⁻ⁿ`, `a^(1/2)`), notasi ilmiah, hubungan ke overflow `int`/`long`; label: *matematika baku*
* [ ] 0.4 Logaritma dan notasi Σ — log sebagai kebalikan eksponen, aturan log, ganti basis, `log₂` dan jumlah berulang (`for` loop = Σ); label: *matematika baku*

Mini Project: `AlgebraCheck.java` — uji properti (aturan pangkat, aturan log, kesetaraan persamaan) terhadap ribuan input acak dengan JUnit; catat di mana floating point membuat "sama" jadi "hampir sama".

Exit gate (bukan ujian formal): bisa memecahkan `2^x = 1000` dan `y = 3x + 2` (cari x saat y = 50) tanpa melihat catatan, dan menjelaskan kenapa `log₂(1.000.000) ≈ 20`. Bila sudah lancar sejak 0.1, lompati lesson terkait; jangan dijalani hanya demi menyelesaikan daftar.

Status: Not started

---

## Module 1 — Berpikir dalam Perubahan: Fungsi, Rate, Limit ([Outline] seksi 1-8, Level 19)

Description: Fondasi cara berpikir: perubahan vs rate of change, sistem sebagai fungsi, batas input valid, membaca grafik, dan perilaku di sekitar suatu nilai (limit, kontinuitas) yang muncul sebagai bug floating point dan "cliff".

Lessons:

* [ ] 1.1 Perubahan vs rate of change — "perubahan terhadap apa?"; contoh traffic `100→150→300→700` dalam detik; rate rata-rata vs sesaat (tanpa formal); label: *matematika baku*
* [ ] 1.2 Fungsi sebagai model sistem — `latency = f(rate)`, `cost = f(users)`, `memory = f(objects)`; input/output; kesalahan "fungsi" yang sebenarnya banyak-ke-satu (metrik nyata itu noisy)
* [ ] 1.3 Domain dan range sebagai validasi — `f(x)=1/x`, division by zero, overflow, null, boundary condition; menulis fungsi Java yang menolak input tidak valid; grafik: slope, naik/turun, peak/valley, asymptote, growth (latihan: ceritakan grafik CPU vs request rate tanpa rumus)
* [ ] 1.4 Limit dan kontinuitas — intuisi "mendekati" vs "sama dengan"; floating point (0.1+0.2), konvergensi vs mencapai target, jump/removable discontinuity, "input berubah sedikit, output melonjak?" sebagai perilaku cliff pada sistem (mis. timeout, connection pool penuh); rujukan Goldberg 1991 untuk floating point; label: *matematika baku* + *pitfall numerik*

Mini Project: `Change.java` — hitung rate of change dari deret metrik, tandai titik lonjakan, dan tulis satu paragraf interpretasi tanpa rumus.

Status: Not started

---

## Module 2 — Turunan sebagai Sensitivitas ([Outline] seksi 9-12, 16-18)

Description: Turunan sebagai ukuran sensitivitas output terhadap input, aturan turunan secukupnya untuk menurunkan model sistem, aproksimasi lokal, dan error. Chain rule ditekankan; derivasi jaringan neural dirujuk.

Lessons:

* [ ] 2.1 Slope ke turunan — `Δy/Δx` menyusut ke `dy/dx`; turunan `c`, `x`, `x²`, `xⁿ`, `eˣ`, `ln x` (opsional `sin`, `cos`); tiap rumus: bentuk, makna, turunan, implementasi kode; satuan `dy/dx` (ms per request/detik)
* [ ] 2.2 Aturan turunan — sum, product, quotient, chain; chain rule sebagai "sistem berlapis" (input → A → B → C → output); latihan: turunkan model `latency` bertumpuk; rujukan `ml-fundamentals` M3 untuk versi ML, tidak diulang
* [ ] 2.3 Sensitivitas dan aproksimasi lokal — `Δy ≈ f'(x)Δx`; elastisitas ("user naik 10%, biaya naik berapa persen"); jebakan: aproksimasi lokal gagal jauh dari titik; latihan estimasi latency untuk kenaikan RPS kecil
* [ ] 2.4 Error dan propagasi — truncation vs measurement vs numerical error; propagasi error lewat `f'`; kaitan dengan metrik yang di-sampling dan sensor; label: *matematika baku* + *pitfall*

Mini Project: `Sensitivity.java` — kalkulator elastisitas untuk model biaya sintetis; bandingkan prediksi `f'(x)Δx` dengan hitung langsung dan catat kapan meleset.

Status: Not started

---

## Module 3 — Optimasi Satu Variabel ([Outline] seksi 13-15)

Description: Menemukan titik terbaik dengan turunan: titik kritis, jenis titik, constraint, dan kenapa "optimum" bergantung pada fungsi tujuan yang dipilih.

Lessons:

* [ ] 3.1 Maximum dan minimum — cost curve, `f'(x)=0` sebagai kandidat kritis (bukan otomatis optimum); first derivative test; endpoint dan optimum lokal vs global pada domain terbatas
* [ ] 3.2 Second derivative test dan kelengkungan — konkavitas, inflection; interpretasi engineer (diminishing returns, tambah worker ke-N memberi lebih sedikit)
* [ ] 3.3 Optimasi dengan constraint — objective + constraint (`CPU ≤ 80%`, `memory ≤ 16 GB`); batas sebagai kandidat optimum; contoh batch size dan jumlah worker; pengantar Lagrange hanya konsep, tidak dihitung
* [ ] 3.4 Merumuskan fungsi tujuan — "throughput maksimum" vs "biaya per request minimum" vs "p99 terbatas"; optimum yang salah karena tujuan salah; kapan lebih baik ukur langsung (**overkill check**)

Mini Project: `Optimize.java` — cari batch size optimal untuk fungsi biaya sintetis analitik dan bandingkan dengan pencarian grid; catat kapan turunan menang atau kalah.

Status: Not started

---

## Module 4 — Banyak Variabel dan Gradient Descent untuk Tuning ([Outline] seksi 26-31; jembatan 32-35)

Description: Partial derivative dan gradient untuk sistem dengan banyak parameter, gradient descent sebagai alat tuning (bukan ML), learning rate, konveksitas. Jembatan singkat ke ML; derivasi jaringan dirujuk.

Lessons:

* [ ] 4.1 Partial derivative — `∂latency/∂request_rate` dengan variabel lain tetap; tabel sensitivitas multi-parameter; satuan
* [ ] 4.2 Gradient — arah perubahan tercepat, `∇f`; permukaan dan kontur; membaca gradient sebagai "parameter mana paling berpengaruh"
* [ ] 4.3 Gradient descent dan learning rate — loop: hitung gradient, update, ulang; terlalu kecil (lambat), terlalu besar (overshoot/diverge); local vs global minimum, saddle, konveks vs non-konveks; label: *matematika baku*, klaim "selalu menemukan optimum" salah di luar konveks
* [ ] 4.4 Jembatan ke ML (tanpa derivasi penuh) — kenapa chain rule menjadi backpropagation; beda numerical, symbolic, dan automatic differentiation (Baydin dkk. 2018); rujukan `ml-fundamentals` M3, M8 dan `neural-network`, tidak diulang

Mini Project: **Tuner Parameter** (Intermediate) — gradient descent di Java untuk fungsi biaya dua-tiga parameter (batch size, worker, timeout); bandingkan dengan grid search; visualisasikan konvergensi.

Status: Not started

---

## Module 5 — Integral sebagai Akumulasi ([Outline] seksi 19-24, 45)

Description: Integral sebagai total yang terkumpul, area di bawah kurva, Fundamental Theorem, dan penerapan ke traffic, biaya, dan error budget. Probabilitas kontinu dirujuk.

Lessons:

* [ ] 5.1 Akumulasi — dari `requests/sec` ke total request; jumlahan Riemann sebagai jembatan (untuk Modul 6); area di bawah kurva throughput
* [ ] 5.2 Integral tentu — `∫ₐᵇ f(x)dx`, batas, area bertanda, satuan (`(request/detik)·detik = request`); antiturunan dasar untuk `xⁿ`, `eˣ`, `1/x`
* [ ] 5.3 Fundamental Theorem dan hubungan turunan ↔ integral — total ↔ rate; kenapa "turunkan lalu integralkan kembali" (konstanta); latihan dua arah dengan counter monotonic
* [ ] 5.4 Aplikasi engineering — total traffic 1 jam saat rate berubah; rata-rata nilai fungsi (average latency/load); biaya kumulatif token/compute; error budget sebagai akumulasi kegagalan; rujukan `statistics-probability` M4 untuk probabilitas kontinu; label: perhitungan dari *rate* yang benar-benar diukur atau dimodelkan

Mini Project: `Traffic.java` — total request dari `R(t)` piecewise; bandingkan hitung analitik dan numerik, dan hitung biaya kumulatif.

Status: Not started

---

## Module 6 — Metode Numerik di Java ([Outline] seksi 36-37, 46-48)

Description: Kapan rumus exact tidak praktis, dan bagaimana mengimplementasikan turunan dan integral secara numerik dengan analisis error yang jujur.

Lessons:

* [ ] 6.1 Turunan numerik — forward/backward/central difference; pemilihan `h`: truncation vs round-off (Goldberg 1991); analitik vs numerik dengan JUnit; peringatan noise pada data nyata
* [ ] 6.2 Integrasi numerik — rectangle, trapezoid, Simpson; orde akurasi; tabel error vs jumlah interval; kapan cukup
* [ ] 6.3 Newton-Raphson — mencari akar `f(x)=0`; tangent, tebakan awal, konvergensi kuadratik; kapan gagal (`f'≈0`, tebakan buruk); contoh: cari beban di mana latency mencapai batas SLO
* [ ] 6.4 Konvergensi, stabilitas, dan biaya — kapan berhenti (toleransi absolut vs relatif), iterasi tak berhenti, floating point; rujukan `physics-for-software-engineers` M6 untuk integrator ODE

Mini Project: `Numerics.java` + tes JUnit — turunan numerik, tiga integrator, dan Newton-Raphson, dengan tabel akurasi vs analitik dan catatan error.

Status: Not started

---

## Module 7 — Kalkulus untuk Performance, Capacity, dan Dinamika ([Outline] seksi 38-44)

Description: Kalkulus dipakai sebagai kerangka matematis performance engineering: kurva load-latency, antrean, USL, backlog dan persamaan diferensial sederhana, growth, dan rantai capacity. Setiap model wajib disertai asumsi dan cara mengujinya.

Lessons:

* [ ] 7.1 Kurva latency vs load dan knee — model M/M/1 `W = 1/(μ−λ)`; turunan `dW/dλ` yang meledak mendekati utilization 1; "kenapa normal di 100 RPS, meledak di 1.000 RPS"; **asumsi model (Poisson, eksponensial, satu server) dan kapan sistem nyata melanggarnya**; rujukan Harchol-Balter; label: *model dengan asumsi*
* [ ] 7.2 Little's Law, utilization, dan USL — `L = λW` (kondisi berlaku); utilization dan saturation (USE Method, rujukan `performance-engineering` M1); throughput dengan contention dan coherency (Gunther) sebagai model fit; batas fit dengan sedikit data
* [ ] 7.3 Backlog dan persamaan diferensial pengantar — `dQ/dt = λ − μ`; kondisi awal, equilibrium, stabilitas; simulasi Euler; kapan backlog tumbuh tak terbatas; rujukan `physics` M6 untuk metode lanjut
* [ ] 7.4 Capacity planning dan model pertumbuhan — `1M request/hari → RPS → peak multiplier → CPU → instance → DB → biaya`; linear/kuadratik/eksponensial/logaritmik; risiko ekstrapolasi; rujukan `performance-engineering` M7.3 dan `system-design` untuk versi arsitektur

Mini Project: `LatencyCurve.java` — kurva M/M/1 dengan deteksi knee (turunan ke-2 atau ambang slope), simulasi backlog Euler, dan satu tabel "asumsi yang bisa salah".

Status: Not started

---

## Module 8 — Capstone: Performance Optimization Simulator

Description: Integrasi semua modul; lihat bagian Capstone Project di bawah. Modul ini juga memuat Final Challenge 10 pertanyaan, Final Test, dan 30-Day Calculus Lab.

Lessons:

* [ ] 8.1 Desain model — pilih variabel (request rate, CPU, memory, DB latency, worker count), tentukan fungsi `latency = f(load, workers, db_latency)`, tulis asumsi
* [ ] 8.2 Bangun simulator — bangkitkan data, plot kurva, hitung turunan/elastisitas, titik kritis, dan optimasi
* [ ] 8.3 Analisis — simulasi perubahan load, cari bottleneck, sensitivity analysis, dan validasi terhadap data benchmark nyata sederhana (mis. hasil load test lokal)
* [ ] 8.4 Rekomendasi dan refleksi — dokumen rekomendasi engineering, **asumsi model yang ternyata salah**, dan kapan model ini lebih buruk daripada mengukur langsung

Status: Not started

---

# 30-Day Calculus Lab (paralel)

Dari outline user; dijalankan paralel, bukan wajib harian.

| Minggu | Fokus | Setelah modul |
|---|---|---|
| 1 | Fundamental — function, graph, slope, limit, continuity, turunan dasar | Modul 1-2 |
| 2 | Derivative — rules, chain rule, max/min, optimization, sensitivity, approximation | Modul 2-3 |
| 3 | Integral dan multivariabel — integral, area, akumulasi, FTC, partial derivative, gradient | Modul 4-5 |
| 4 | Engineering — gradient descent, numerical methods, performance modeling, capacity planning, ML connection, final project | Modul 6-8 |

Aturan tambahan: hari terlewat dicatat terlewat, bukan dikejar; kewajiban kerja dan keluarga tidak dikorbankan; bila belajar ini terasa seperti tekanan "harus menyelesaikan semuanya", hentikan dan kurangi lingkup.

---

# Capstone Project

Description: **Performance Optimization Simulator** dan **Final Challenge 10 pertanyaan** (latency vs traffic, titik saturasi, total request, sensitivity, parameter optimal, aproksimasi numerik, gradient, perubahan terhadap waktu, validasi dengan data aktual, asumsi model yang ternyata salah) serta **Final Test** — lihat Module 8.

Acceptance Criteria:

* Kode Java 25 dengan tes JUnit yang membandingkan hasil numerik dan analitik, plus catatan error
* Model punya daftar asumsi dan minimal satu pengujian terhadap data (benchmark lokal atau data sintetis dengan noise)
* Grafik kurva latency dan titik kritis dengan satuan yang benar
* Sensitivity analysis: minimal tiga parameter diurutkan menurut pengaruh dengan `∂f/∂x`
* Optimasi dengan constraint dan perbandingan dengan pencarian grid atau ukur langsung
* Simulasi backlog dengan kondisi kedatangan > pelayanan
* Bagian "asumsi model yang ternyata salah" berisi minimal dua asumsi yang dites dan hasilnya
* Bagian "kapan model ini overkill" (mengukur langsung lebih baik)
* Review Style dipenuhi: Ketepatan Matematika, Numerical Soundness, Asumsi Eksplisit, Interpretasi Engineer, Bukan Overkill, Production Awareness
* Final Challenge 10 pertanyaan dan Final Test dijawab dengan kata sendiri
* Disimpan ke `portofolio/`

Status: Not started

---

# Overall Progress

Module Completion: 0/9 (Module 0 jembatan aljabar + Module 1-7 + Capstone)

Overall Completion: 0%

Current Module: —

Current Lesson: —

Next Lesson: 0.1 (menunggu approval user; Module 0 ditambah 2026-09-27 setelah learner melaporkan aljabar masih kurang)
