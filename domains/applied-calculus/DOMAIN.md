# Applied Calculus for Software Engineers — Domain Pack

> **Status: DRAFT, terdaftar Paused, menunggu approval user.** Course: `courses/kalkulus-untuk-software-engineer/roadmap.md`.

## Domain Name

Kalkulus Terapan untuk Software Engineer — kalkulus sebagai bahasa untuk memodelkan perubahan dan akumulasi pada sistem software (performance, capacity, optimasi parameter, metode numerik di Java).

## Scope

Kalkulus **sebagai alat modeling dan estimasi engineering**, bukan matematika murni. Setiap topik harus terhubung ke pertanyaan yang benar-benar muncul di pekerjaan software engineer: seberapa cepat latency naik saat load naik, di mana titik optimal batch size/jumlah worker, berapa total request/biaya yang terkumpul, seberapa akurat sebuah angka hasil komputasi, kapan backlog meledak.

Cakupan:

- **Perubahan dan fungsi sebagai model**: rate of change, fungsi `y = f(x)` untuk sistem, domain/range dan hubungannya dengan validasi/boundary, membaca grafik (slope, peak, asymptote, growth), limit dan kontinuitas dalam konteks floating point, konvergensi, dan perilaku "cliff".
- **Turunan sebagai sensitivitas**: slope, aturan turunan (cukup untuk menurunkan model sistem), chain rule, aproksimasi lokal `Δy ≈ f'(x)Δx`, elastisitas ("kalau user naik 10%, biaya naik berapa persen"), propagasi error.
- **Optimasi satu variabel**: titik kritis, first/second derivative test, trade-off dengan constraint, cost curve, knee point.
- **Multivariabel secukupnya**: partial derivative, gradient sebagai sensitivitas multi-faktor, gradient descent sebagai **alat tuning parameter sistem** (bukan ML), learning rate sebagai step size dan stabilitas, local vs global minimum.
- **Integral sebagai akumulasi**: rate → total, area under curve, definite integral, Fundamental Theorem, nilai rata-rata, total traffic/biaya/error budget yang terkumpul.
- **Metode numerik di Java**: finite difference, rectangle/trapezoid/Simpson, Newton-Raphson, truncation vs round-off error, pemilihan step size, perbandingan hasil analitik vs numerik dengan JUnit.
- **Kalkulus untuk performance dan capacity**: kurva `latency = f(load)`, model antrean M/M/1 sebagai contoh kurva dengan "knee", Little's Law, utilization, backlog sebagai `dQ/dt = λ − μ`, Universal Scalability Law sebagai model kurva throughput, model pertumbuhan (linear, kuadratik, eksponensial, logaritmik), rantai capacity planning (users → RPS → CPU → instance).
- **Persamaan diferensial pengantar**: state, rate of change, kondisi awal, equilibrium, stabilitas, simulasi dengan Euler; cukup untuk mensimulasikan antrean/backlog.
- **Capstone**: Performance Optimization Simulator (model → data → turunan → titik kritis → optimasi → sensitivity → rekomendasi engineering berbasis data).

Secara eksplisit TIDAK mencakup (dimiliki course lain; di sini hanya **rujukan**, tidak diajarkan ulang):

- **Kalkulus untuk ML/AI**: loss function, gradient descent untuk model, neural network, backpropagation, activation function, vanishing gradient → `courses/ml-fundamentals/roadmap.md` Module 3 dan 8, dan `courses/neural-network/roadmap.md` (derivasi formal). Course ini hanya memberi satu jembatan singkat (kenapa chain rule dan autodiff), bukan derivasi jaringan.
- **Probabilitas kontinu**: PDF, CDF, expectation, variance, distribusi → `courses/statistics-probability/roadmap.md` Module 4-6. Di sini integral hanya dipakai untuk akumulasi dan rata-rata.
- **Integrator numerik untuk fisika dan simulasi rigid body**: Euler/Verlet/RK4 dan ODE mekanika → `courses/physics-for-software-engineers/roadmap.md` Module 6. Di sini hanya Euler sederhana untuk backlog/antrean.
- **Metodologi performance engineering praktis**: profiling, JMH, load testing, USE Method → `courses/performance-engineering/roadmap.md`. Course ini memberi kerangka matematisnya, bukan alat ukurnya.
- **Kalkulus formal tingkat universitas**: pembuktian epsilon-delta, teknik integrasi lanjutan (integration by parts, substitusi trigonometri berlebihan), deret tak hingga, analisis riil, kalkulus vektor lanjutan.
- **Statistika dan machine learning untuk fitting data nyata secara rigor** → `courses/statistics-probability`, `courses/ml-fundamentals`.

Basis awal: outline "Kalkulus untuk Software Engineer" buatan user (roadmap 12 level di bagian atas, 20 level di badan dokumen; 48 seksi bernomor; 30-Day Calculus Lab; Final Challenge 10 pertanyaan; Final Test; Personal Engineer Principle). Ketidakkonsistenan 12 vs 20 level di outline dicatat, urutan isi badan dokumen dipertahankan. 48 seksi dilipat menjadi 7 modul isi + capstone karena hasil audit overlap.

**Catatan bukti yang menentukan desain course ini (diverifikasi lewat pencarian 2026-09-26 kecuali disebut lain):**

- **Kalkulus itu sendiri tuntas dan terverifikasi.** Materi turunan/integral/multivariabel adalah matematika mapan; rujukan: MIT 18.01SC dan buku terbuka Strang *Calculus* di MIT OpenCourseWare. Label: *matematika baku*.
- **Model performance = model, bukan realitas.** Kurva latency M/M/1 (`W = 1/(μ−λ)`, meledak mendekati utilization 1) valid **hanya** bila asumsi model terpenuhi (kedatangan Poisson, waktu layanan eksponensial, satu server, antrean tak terbatas). Sistem nyata sering melanggar salah satunya (kedatangan berkelompok, waktu layanan berekor panjang, timeout, retry storm). "Knee" adalah properti model, bukan hukum. Label: *model dengan asumsi, wajib dicek terhadap data*. Rujukan: Harchol-Balter, *Performance Modeling and Design of Computer Systems* (Cambridge UP, 2013).
- **Little's Law** (`L = λW`) berlaku sangat umum untuk sistem stabil (kondisi kestasioneran), tidak butuh asumsi distribusi. Label: *hasil teorema, dengan kondisi*. Kondisi tepat dibaca ulang dari Harchol-Balter saat lesson ditulis.
- **Universal Scalability Law (Gunther)** adalah model kurva throughput dengan dua parameter (contention dan coherency) yang di-*fit* ke data; dipakai praktisi capacity planning (Gunther, *Guerrilla Capacity Planning*, Springer 2007; USL dipresentasikan di CMG 1993). Kecocokan fit tidak membuktikan mekanisme penyebab, dan tiga-empat titik data mudah di-*fit* berlebihan. Label: *model praktisi, fit empiris*.
- **Turunan numerik dari data ber-noise itu buruk.** Selisih hingga memperbesar noise; memperkecil step size mengurangi truncation error tetapi menaikkan round-off error (trade-off klasik, Goldberg 1991, *ACM Computing Surveys* 23(1):5-48). Kesimpulan "titik saturasi ada di mana `dy/dx` melonjak" dari metrik produksi yang noisy harus dihaluskan dan divalidasi, bukan dibaca langsung. Label: *pitfall numerik yang terverifikasi*.
- **Autodiff ≠ numerical ≠ symbolic differentiation.** Baydin dkk. (2018, *JMLR* 18(153):1-43) mendefinisikan ketiganya dan hubungannya; backpropagation adalah kasus khusus reverse-mode AD. Dipakai untuk jembatan singkat ke ML, tanpa derivasi jaringan.
- **Klaim populer yang ditandai:** "kalkulus membuat kita paham sistem" (sebagian besar hanya model penyederhana; lihat di atas), "titik `f'(x)=0` adalah optimum sistem" (harus dicek jenisnya, constraint, dan apakah fungsi tujuan benar), "gradient descent selalu menemukan optimum" (hanya untuk fungsi konveks dan step size wajar), "growth eksponensial vs logistik" pada data traffic (sering kesalahan ekstrapolasi).
- **Kapan kalkulus overkill:** untuk banyak keputusan sehari-hari (naikkan replika sampai p99 aman, ukur, ulangi) tabel dan eksperimen lebih murah dan lebih dapat dipercaya daripada model; course ini melatih **memutuskan** kapan model layak.

Konsekuensi desain: tiap lesson berlabel bukti (*matematika baku* / *model dengan asumsi* / *heuristik populer*), setiap model performance wajib diakhiri "asumsi apa yang bisa salah dan bagaimana mengujinya", dan capstone wajib memuat bagian "asumsi model yang ternyata salah" (Final Challenge nomor 10 dari outline).

## Trusted Sources / Research Priority

Diverifikasi lewat pencarian 2026-09-26 (hanya keberadaan dan metadata publikasi, isi buku/bab belum dibaca): 1, 2, 5, 6, 7, 8. Dikenal tetapi **belum dicek ulang di sesi ini** (verifikasi sebelum dikutip di lesson): 3, 4, 9.

1. **MIT OpenCourseWare 18.01SC Single Variable Calculus** dan 18.02 Multivariable Calculus (https://ocw.mit.edu/courses/18-01sc-single-variable-calculus-fall-2010/) — kuliah, problem set, dan solusi resmi.
2. **Gilbert Strang, *Calculus*** (buku terbuka di MIT OCW, https://ocw.mit.edu/courses/res-18-001-calculus-fall-2023/) — teks gratis dengan penekanan aplikasi; tulang punggung bacaan teori.
3. **3Blue1Brown, *Essence of Calculus*** (YouTube) — intuisi visual turunan/integral sebelum formalisme. Belum dicek ulang.
4. **Google, *Site Reliability Engineering*** (Beyer dkk., 2016, gratis di sre.google/books) — konteks SLO, error budget, dan capacity di dunia nyata. Belum dicek ulang.
5. **Mor Harchol-Balter, *Performance Modeling and Design of Computer Systems: Queueing Theory in Action*** (Cambridge University Press, 2013, ISBN 9781107027503) — referensi utama antrean, utilization, Little's Law, dan kenapa latency meledak.
6. **Neil J. Gunther, *Guerrilla Capacity Planning*** (Springer, 2007) dan tulisan USL di perfdynamics.com — referensi USL dan capacity planning praktisi.
7. **David Goldberg, "What Every Computer Scientist Should Know About Floating-Point Arithmetic"** (*ACM Computing Surveys* 23(1), 1991, 5-48) — dasar round-off error dan kestabilan numerik.
8. **Atilim G. Baydin, Pearlmutter, Radul, Siskind, "Automatic Differentiation in Machine Learning: a Survey"** (*JMLR* 18(153), 2018) — untuk jembatan singkat ke autodiff.9. **Boyd & Vandenberghe, *Convex Optimization*** (Cambridge, PDF gratis di penulis) dan **Burden & Faires / Press dkk. *Numerical Recipes*** — rujukan konveksitas dan metode numerik saat perlu dalam. Belum dicek ulang.

Klaim matematika dicek silang minimal ke satu sumber baku (1-2) dan, bila bisa, dibuktikan lewat kode di Java (bandingkan hasil analitik vs numerik). Klaim performance dicek ke Harchol-Balter/Gunther **dan** ke data yang dibangkitkan sendiri, dengan asumsi ditulis eksplisit.

## Applied Practice Definition

Applied practice di domain ini selalu berupa **kode yang jalan dan hasil yang bisa diperiksa**:

- **Implementasi di Java 25** untuk konsep numerik (turunan selisih hingga, integrasi numerik, Newton-Raphson, gradient descent tuning, simulasi Euler) dengan **JUnit** membandingkan hasil numerik terhadap solusi analitik dan menampilkan error.
- **Perhitungan tangan dulu, lalu kode.** Untuk tiap konsep: satu soal matematika (turunkan `f(x)=x²`), intuisi, pertanyaan engineering, kode, visualisasi, interpretasi (format 6 langkah dari outline user).
- **Model performance yang dibangkitkan sendiri**: bangkitkan data (dari model dan dari benchmark mikro sederhana bila ada), plot kurva, hitung turunan/elastisitas, cari knee, lalu uji asumsi.
- **Visualisasi**: plot lewat CSV lalu alat apa pun yang tersedia (spreadsheet, matplotlib, atau library plot Java); pilihan alat dikonfirmasi saat approval.
- **Sensitivity analysis nyata**: ubah satu parameter, ukur perubahan output, bandingkan dengan `f'(x)`.

Modul teori tidak dianggap selesai bila tidak berakhir di kode atau perhitungan yang bisa diperiksa, sesuai `# Hands-on First` di `CLAUDE.md` root.

## Project Tiers

- **Mini Project** (per modul): satu artefak kecil (mis. `Derivative.java` + tes akurasi; kalkulator elastisitas biaya; solver Newton-Raphson; integrator trapezoid vs Simpson; kurva latency M/M/1 dengan deteksi knee).
- **Intermediate Project** (setelah Modul 3-4): **Tuner Parameter** — cari batch size/worker count optimal untuk fungsi biaya sintetis dengan gradient descent dan bandingkan dengan pencarian grid.
- **Capstone / Portfolio Project**: **Performance Optimization Simulator** (Java 25): input request rate, CPU, memory, DB latency, worker count → model `latency = f(load, workers, db_latency)` → plot, turunan, titik kritis, optimasi, simulasi perubahan load, bottleneck, sensitivity analysis, dokumentasi, dan rekomendasi engineering. Disimpan ke `portofolio/`, dengan bagian wajib "asumsi model yang ternyata salah".

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), tiap artefak dicek lewat lensa berikut:

- **Ketepatan matematika** — turunan/integral benar, satuan konsisten (dimensional analysis: `dy/dx` punya satuan `y/x`).
- **Numerical Soundness** — pilihan step size dan metode dijelaskan; truncation vs round-off dipertimbangkan; tidak ada divisi dengan nol atau overflow yang tak ditangani.
- **Asumsi Model Eksplisit** — asumsi ditulis, bisa diuji, dan ada catatan bila data melanggarnya.
- **Interpretasi Engineer** — hasil dijelaskan dalam bahasa keputusan ("p99 mulai naik tajam di sekitar 70% utilization pada model ini"), bukan hanya angka.
- **Bukan Overkill** — ada pertimbangan apakah model ini lebih baik daripada mengukur langsung.
- **Production Awareness** — data noisy, metrik yang di-sampling, dan risiko ekstrapolasi di luar rentang data.

## Assessment Form

Concept-check singkat per lesson (skenario "apa yang terjadi pada `dy/dx` bila...", bukan hafalan rumus), soal matematika kecil dengan kunci untuk self-check, kode Java dengan tes JUnit yang membandingkan analitik vs numerik, dan **Final Test** dari outline: menjelaskan dengan kata sendiri function/limit/derivative/integral, rate of change, sensitivity, optimum, akumulasi, partial derivative, gradient, kenapa gradient descent bekerja, kenapa chain rule penting untuk neural network (jawaban boleh merujuk ML course), dan beda solusi analitik vs numerik, serta 5 kemampuan software (implement derivative/integral/gradient descent, membuat grafik, menganalisis performance). **Final Challenge 10 pertanyaan** dijawab pada capstone.

## Practitioner Reference Frame

- **Performance/capacity engineer** (Gunther, Harchol-Balter, praktik SRE) — lensa kurva load-latency, saturation, dan capacity planning; menekankan validasi model terhadap data.
- **Numerical analyst / scientific computing** (Goldberg, Numerical Recipes) — lensa error, stabilitas, dan pemilihan metode; sering mengutamakan akurasi dan kestabilan daripada kecepatan.
- **ML/optimization practitioner** (Boyd, Baydin) — lensa gradient dan optimasi; menerima solusi "cukup baik" dan menekankan konveksitas.
- **Praktisi pragmatis** (sebagian besar tim produksi) — lebih suka ukur dan eksperimen daripada model; lensa ini melatih kapan kalkulus layak dipakai dan kapan tidak.

Keempatnya bisa menghasilkan jawaban berbeda untuk soal yang sama (mis. "berapa instance yang dibutuhkan"); domain ini melatih mengenali kapan tiap lensa cocok.

## Domain-Specific Standards

- **Tidak ada teori tanpa perhitungan atau kode.** Setiap modul berakhir di soal yang dihitung atau kode yang dijalankan dan diperiksa.
- **Model bukan realitas.** Setiap model performance memuat daftar asumsi dan satu cara mengujinya; kesimpulan tidak diklaim di luar rentang data.
- **Batas kalkulus dinyatakan.** Bila masalah lebih cocok ke statistika (distribusi ekor, percentile), queueing lanjut, atau eksperimen, mentor menyatakan itu dan mengarahkan ke course terkait, bukan memaksakan kalkulus.
- **Bukan matematika murni.** Tidak ada pembuktian epsilon-delta; intuisi dan penerapan diutamakan (konsisten dengan kalibrasi `ml-fundamentals`), tetapi rumus tetap harus benar.
- **Satuan wajib.** Semua turunan/integral menyertakan satuan (request/detik, ms, USD) supaya interpretasi tidak menyesatkan.
- **Jangan overstudy.** Kalkulus mudah jadi "satu topik lagi". Hasil diukur dari kode dan model yang bekerja, bukan dari jumlah lesson; course ini bukan alasan membuka lebih banyak course.

## Domain Goal

Learner dapat menjelaskan dan memakai turunan (sensitivitas), integral (akumulasi), dan gradient (arah perbaikan) untuk memodelkan sistem software; mencari titik optimal dengan constraint; mengimplementasikan metode numerik di Java dan menjelaskan error-nya; membuat model performance dan capacity (kurva latency, antrean, backlog, pertumbuhan) dengan asumsi eksplisit dan mengujinya terhadap data; serta memutuskan kapan model layak dan kapan cukup mengukur — dibuktikan lewat Performance Optimization Simulator dan Final Challenge di portofolio. Prinsip utama dari outline: *jangan belajar kalkulus untuk menyelesaikan soal matematika, belajar untuk memahami sistem yang berubah.*

## Registration History

Dibuat 2026-09-26 atas permintaan langsung user (paste outline "Kalkulus untuk Software Engineer: dari 'Kenapa Turunan Dipakai?' sampai Optimization, ML, dan Performance Engineering" tanpa instruksi tambahan). Ditanya lewat AskUserQuestion: (1) maksud paste dan guard "Course Aktif di bawah 50%" — Character Development pas 3/6 modul dan `male-grooming` masih Module 1 — user memilih **audit overlap dulu** (sekaligus meng-override guard); (2) setelah audit, user memilih **"Standalone terlipat ~8 modul"** (bukan 20 level penuh, bukan extension `performance-engineering`). Nama domain `applied-calculus` adalah **asumsi mentor yang tidak ditanyakan eksplisit**. Audit overlap dilakukan dengan grep lintas `courses/` (kalkulus, turunan, gradient, integral, chain rule, backprop, queueing, Little's Law, USL) dan membaca daftar modul `ml-fundamentals` (Module 3, 8), `neural-network`, `statistics-probability` (Module 4-6), `physics-for-software-engineers` (Module 6, 14), `performance-engineering` (Module 1, 7). Hasil (perkiraan kasar per topik, bukan per baris): ±55% topik outline sudah punya rumah — turunan/gradient/chain rule/gradient descent/backprop di `ml-fundamentals` dan `neural-network`, probabilitas kontinu di `statistics-probability`, integrasi numerik ODE dan PID di `physics-for-software-engineers`, capacity planning dasar di `performance-engineering` 7.3. **Yang belum ada di repo:** sensitivity dan optimasi sebagai kerangka engineering, integral sebagai akumulasi traffic/biaya, metode numerik di Java (finite difference, Simpson, Newton-Raphson) dengan analisis error, model kurva latency/antrean/USL/backlog sebagai matematika, dan project simulator. Kecocokan dengan Master Goal (backend freelance internasional) sedang: relevan untuk performance/capacity reasoning dan sebagai fondasi ML, tidak langsung. Belum ditanyakan: tingkat aljabar/kalkulus SMA-kuliah learner saat ini (kalibrasi), alat plot yang dipakai, dan apakah 7 modul isi + capstone terlalu banyak. Course **Draft, menunggu approval**; belum jadi Course Aktif.
