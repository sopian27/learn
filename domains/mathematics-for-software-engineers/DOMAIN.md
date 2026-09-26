# Mathematics for Software Engineers — Domain Pack

> **Status: DRAFT, terdaftar Paused, menunggu approval user.** Course: `courses/matematika-software-engineer/roadmap.md`.

## Domain Name

Mathematics for Software Engineers — Berpikir Matematis untuk Coding, Sistem, dan Keputusan Engineering (logika, bilangan di mesin, pertumbuhan, latency, queueing, reliability, estimasi kapasitas)

## Scope

Belajar matematika **sebagai alat memodelkan masalah software**: membaca kondisi tanpa menebak (logika, boolean, himpunan), memahami bagaimana mesin menyimpan bilangan (biner, overflow, floating point, modulo), menghitung pertumbuhan (eksponen, logaritma, deret), membaca latency lewat percentile, memodelkan antrean (Little's Law), menghitung reliability (availability, redundancy, retry), dan membuat estimasi kapasitas dari nol (Fermi, back-of-envelope). Diakhiri capstone "Design a Million-User System" dan 30-Day Mathematics Lab.

Basis awal: outline "Matematika untuk Software Engineer" buatan user (40 modul, 30-Day Mathematics Lab, Final Test 15 pertanyaan, Final Engineer Mindset). Urutan gagasan dipertahankan; 40 modul dilipat jadi 11 modul course karena ±22 modul outline sudah punya rumah di course lain (lihat "Pemetaan Outline ke Modul" di roadmap).

**Yang membuat domain ini berbeda dari domain lain:** repo sudah punya matematika **terapan ke satu tujuan** (probabilitas dan statistik di `statistics-probability`, aljabar linear/kalkulus untuk ML di `ml-fundamentals`, Big-O/graph/rekursi di `computational-thinking`, RSA/ECC di `cryptography`, physics numerik di `physics-for-software-engineers`). Yang **tidak** ada (grep dan pembacaan daftar modul 2026-09-26): logika dan boolean formal, himpunan dan fungsi, proof/invariant, bilangan di mesin (biner, two's complement, IEEE 754), eksponen dan compound growth, percentile dan tail latency sebagai matematika, queueing dan Little's Law, matematika reliability (availability gabungan N service), information theory, dan estimasi Fermi/back-of-envelope. Domain ini mengisi celah itu. Disiplin pembeda: **setiap klaim matematis diberi label apakah teorema/definisi, model dengan asumsi, atau heuristik industri** — karena banyak "rumus" engineering (mis. 0.999^N, M/M/1) benar hanya di bawah asumsi tertentu.

## Posisi Sumber (diverifikasi lewat pencarian 2026-09-26, sebagian lewat ringkasan referensi, belum lewat teks primer; dibaca ulang saat lesson ditulis)

- **Lapis 1 — teks matematika diskrit:** Lehman, Leighton, Meyer, *Mathematics for Computer Science* (MIT 6.042J / 6.1200J; teks berlisensi CC BY-SA, gratis di MIT OCW, Internet Archive, LibreTexts). Cakupan terverifikasi: logika, metode bukti, induksi, himpunan, relasi, graph dasar, kongruensi bilangan bulat, notasi asymptotic, counting, probabilitas diskrit. Kandidat lain (belum diverifikasi isinya): Rosen, *Discrete Mathematics and Its Applications*; Graham-Knuth-Patashnik, *Concrete Mathematics*; Warren, *Hacker's Delight* (bit). **Label: [Teorema/definisi].**
- **Lapis 2 — floating point:** Goldberg, "What Every Computer Scientist Should Know About Floating-Point Arithmetic," *ACM Computing Surveys* 23(1), 1991, hlm. 5-48 (terverifikasi ada; membahas representasi, rounding error, standar IEEE). Standar IEEE 754 sendiri dirujuk untuk detail format, dicek versinya. **Label: [Standar].**
- **Lapis 3 — tail latency:** Dean & Barroso, "The Tail at Scale," *Communications of the ACM* 56(2), Feb 2013, hlm. 74-80 (terverifikasi ada; inti: teknik toleransi variabilitas latency seperti hedged requests). Rumus 1-(1-p)^N untuk fan-out adalah turunan course, **bukan** kutipan; angka contoh diverifikasi dari paper sebelum dipakai. **Label: [Praktik industri] + [Model/asumsi].**
- **Lapis 4 — queueing:** Little's Law, L = λW, dipublikasikan buktinya oleh J. D. C. Little 1961 (setelah diajukan Morse). Kondisi terverifikasi lewat ringkasan referensi: berlaku untuk sistem **stabil/stationary**, tanpa asumsi distribusi kedatangan atau layanan. Rumus M/M/1 (W = 1/(μ-λ)) adalah **model dengan asumsi Poisson dan eksponensial**, tidak berlaku umum; dicek dari buku antrean sebelum ditulis (kandidat: Harchol-Balter, *Performance Modeling and Design of Computer Systems*; Jain, *The Art of Computer Systems Performance Analysis*; belum dibaca mentor). **Label: [Teorema] untuk Little, [Model/asumsi] untuk M/M/1.**
- **Lapis 5 — reliability:** Google SRE Book (bab Embracing Risk, Service Level Objectives; terverifikasi): availability dinyatakan dalam "nines", error budget = 1 - SLO, contoh 1.000.000 request dan SLO 99,9% = budget 1.000 error. Perkalian availability serial (0,999^N) adalah **turunan matematis dengan asumsi independensi dan dependensi keras**, bukan klaim SRE Book. **Label: [Praktik industri] + [Model/asumsi].**
- **Lapis 6 — estimasi:** Bentley, *Programming Pearls*, kolom 7 "The Back of the Envelope" (CACM 27(3), 1984; terverifikasi ada). Kandidat belum diverifikasi: Weinstein & Adam, *Guesstimation*; tabel "latency numbers" (Jeff Dean/Peter Norvig, versi interaktif Colin Scott). **Angka latency hardware cepat usang**; dipakai sebagai orde besaran dan ditandai tahun. **Label: [Heuristik].**
- **Lapis 7 — information theory:** Shannon, "A Mathematical Theory of Communication" (1948); Cover & Thomas; MacKay (gratis online). **Belum diverifikasi lewat pencarian**; dicek sebelum Module 10 ditulis.
- **Lapis 8 — outline user.** Berisi beberapa pernyataan yang perlu dinilai, bukan diterima: mis. "Big O tanpa memahami input" (benar, ditangani M5/M11), "correlation ≠ causation" (dirujuk ke `statistics-probability`), dan contoh availability "10 → 50 → 100 service" (hasilnya bergantung asumsi independensi). Buku sumber outline tidak disebut; **belum dibaca mentor**.

**Tiga label yang dipakai di seluruh course:**

- **[Teorema/definisi/standar]** — benar secara matematis atau standar (Little's Law, IEEE 754, De Morgan).
- **[Model/asumsi]** — benar di bawah asumsi eksplisit (0,999^N, M/M/1, Poisson arrival, fan-out independen). Lesson wajib menulis asumsinya dan kapan model gagal.
- **[Heuristik]** — aturan praktis (rule of 72, peak = 2-3x rata-rata, angka latency hardware). Berguna untuk orde besaran, bukan hasil pasti.

Secara eksplisit TIDAK mencakup (dirujuk, tidak diduplikasi):

- **Probabilitas, distribusi, inferensi statistik, regresi, hypothesis testing, counting formal** — [[statistics-probability]] (Module 1-11). Module 6 dan 8 di sini hanya memakai probabilitas untuk latency dan reliability.
- **Big-O, rekursi/recurrence, graph, tree, sorting, hashing (struktur data)** — [[computational-thinking]] (Module 2-9). Module 5 di sini hanya pertumbuhan eksponen/log dari sisi kapasitas, backoff, dan intuisi; tidak mengulang analisis algoritma.
- **Aljabar linear, kalkulus, gradient descent, embedding** — [[ml-fundamentals]] (Module 2-4), [[neural-network]], [[llm]].
- **RSA, Diffie-Hellman, ECC, hash kriptografi** — [[cryptography]] (Module 3-4).
- **Load/stress test, profiling, capacity dari hasil pengukuran** — [[performance-engineering]] (Module 7), [[backend-fundamentals]] (Module 14). Module 9 di sini estimasi **sebelum** ada sistem; di sana pengukuran **setelah** ada.
- **SLO, burn-rate alert, chaos experiment** — [[chaos-resilience-engineering]]. Module 8 di sini hanya matematika di balik angka.
- **Simulasi fisika numerik dan integrator** — [[physics-for-software-engineers]]. **Integer overflow C/undefined behavior, fixed-point** — [[nasa-software-engineer]] (Module 3, 12), [[satellite-programming]] (2.4). Module 4 di sini versi Java dan lintas-bahasa.
- **Arsitektur dan trade-off sistem** — [[system-design]]. **Matematika keuangan (compound interest, ROI, expected value)** — [[personal-finance]], [[investing-stocks]]; Module 5 hanya compound growth untuk data dan kapasitas.

## Trusted Sources / Research Priority

1. **Lehman-Leighton-Meyer, *Mathematics for Computer Science*** — logika, proof, induksi, himpunan, kongruensi (Module 2-4). Gratis, terverifikasi.
2. **Goldberg (1991) dan standar IEEE 754** — Module 4.
3. **Dean & Barroso (2013), Google SRE Book (Embracing Risk, SLO)** — Module 6, 8.
4. **Little (1961) dan buku antrean (Harchol-Balter, Jain)** — Module 7; dicek keberadaan isi sebelum dipakai.
5. **Bentley, *Programming Pearls* kolom 7** — Module 1 dan 9.
6. **Shannon (1948), MacKay/Cover & Thomas** — Module 10; belum diverifikasi.
7. **Dokumentasi Java** (`Math.addExact`, `Math.floorMod`, `BigDecimal`, `Integer`/`Long` bit operations) — dicek lewat context7 saat Module 4 ditulis; versi Java 25 dicek.
8. **Blog engineering dan talk (mis. Gil Tene tentang coordinated omission)** — hanya bila diverifikasi; **belum diverifikasi**.

House rule sumber: (a) tiap rumus diberi label **[Teorema/definisi/standar]/[Model/asumsi]/[Heuristik]**; (b) asumsi ditulis eksplisit dan diuji dengan satu contoh yang melanggarnya; (c) tidak ada angka hardware/latency tanpa tahun; (d) tidak ada klaim "matematika membuktikan sistem aman" — model hanya sebaik asumsinya; (e) tidak menyimpulkan dari satu simulasi; (f) hasil estimasi selalu diberi rentang dan sanity check, bukan satu angka.

## Applied Practice Definition

**Model-Hitung-Uji-Validasi** — untuk satu masalah engineering nyata atau kasus: tulis model (variabel, konstanta, constraint, asumsi berlabel), hitung dengan tangan atau kode Java/Python, uji dengan pengukuran atau simulasi kecil, lalu **validasi** (sanity check orde besaran, sensitivitas terhadap asumsi terlemah). Format tetap: **Masalah → Model → Asumsi → Hitungan → Sanity Check → Yang Membuat Model Salah → Keputusan**.

## Project Tiers

- **Mini Project** (per modul): satu hitungan atau eksperimen konkret (mis. tabel kebenaran + penyederhanaan `if` nyata dari kode sendiri; eksperimen `0.1 + 0.2` dan overflow di Java; tabel percentile dari log latency nyata; simulasi antrean kecil untuk membuktikan Little's Law; kalkulator availability serial-paralel).
- **Intermediate Project**: **30-Day Mathematics Lab** (empat minggu sesuai outline: Logic+Binary+Modular, Growth+Log+Recursion+Counting, Probability+Latency+Queueing, Estimation+Capacity+System Design). Hari terlewat dicatat sebagai terlewat.
- **Capstone**: **"Design a Million-User System" (numbers-first)** — dari 10 juta user hitung DAU, requests/hari, peak RPS, storage/hari dan tahun, bandwidth, kapasitas DB, cache hit ratio, throughput Kafka, replikasi, availability gabungan; lalu garis besar arsitektur API → Redis → Kafka → DB → object storage → Kubernetes. Disertai **daftar asumsi berlabel dengan rentang** dan **satu bagian "yang membuat perhitungan ini salah"**. Disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), tiap hitungan dan capstone dicek:

- **Asumsi eksplisit** — apakah asumsi ditulis dan diberi label, terutama independensi, stationarity, distribusi?
- **Satuan dan orde besaran** — apakah satuan konsisten (detik/hari, bit/byte, KB/KiB), dan hasil lolos sanity check?
- **Rentang, bukan titik** — apakah hasil punya rentang (rendah/tengah/tinggi) dan sensitivitas?
- **Batas model** — apakah disebut kapan model gagal (korelasi kegagalan, non-stationary, tail berat)?
- **Verifikasi** — apakah hitungan diuji dengan kode/simulasi/pengukuran, bukan hanya rumus?
- **Ketepatan istilah** — percentage vs percentage point, average vs median vs percentile, kapan Big-O menyesatkan.

## Assessment Form

Soal hitungan tertulis dengan langkah (bukan pilihan ganda), tabel kebenaran, eksperimen Java kecil (overflow, floating point), interpretasi tabel percentile, hitungan Little's Law dan availability dengan asumsi ditulis, estimasi Fermi dengan rentang, dan Final Test 15 pertanyaan outline dijawab tertulis secara intuitif. Bukan hafalan rumus.

## Practitioner Reference Frame

- **SRE dan performance engineer** (Google SRE, Gregg, Tene) — lensa latency, reliability, dan kapasitas.
- **Peneliti sistem dan dosen matematika diskrit** (Dean & Barroso, Lehman-Leighton-Meyer, Goldberg) — lensa teorema dan standar.
- **Praktisi estimasi** (Bentley) — lensa back-of-the-envelope.
- **Konteks software engineer:** semua contoh memakai Java/Spring Boot, PostgreSQL, Redis, Kafka, Kubernetes sesuai profil user.

## Domain-Specific Standards

- **Setiap rumus berlabel** [Teorema/definisi/standar], [Model/asumsi], atau [Heuristik]. Rumus tanpa label tidak boleh dipakai sebagai dasar keputusan.
- **Asumsi selalu tertulis.** 0,999^N hanya berlaku bila kegagalan independen dan dependensi serial keras; M/M/1 hanya untuk Poisson/eksponensial.
- **Hitungan diverifikasi.** Setiap angka di lesson dihitung ulang dengan kode atau tangan sebelum ditulis; tidak ada angka dari ingatan.
- **Sanity check wajib.** Estimasi tanpa pemeriksaan orde besaran dianggap belum selesai.
- **Angka hardware punya tahun.** Latency numbers, harga cloud, dan kapasitas disk diberi tahun dan dicek ulang.
- **Bukan alat mengintimidasi diri.** Tidak menjadikan "harus menguasai 40 modul matematika" sebagai syarat menjadi engineer; berhenti di titik yang cukup adalah hasil yang sah. Selaras dengan catatan overstudy di `feedback_subscription_sunk_cost_overstudy`.
- **Tidak menggantikan pengukuran.** Model memandu pengukuran, tidak menggantikannya; kontradiksi antara model dan data selalu dimenangkan data.
- **Sumber dicek, bukan diingat.** Nama paper, angka, dan detail teks dibaca ulang sebelum ditulis ke lesson.

## Domain Goal

Learner bisa mengubah masalah software menjadi model matematis berlabel asumsi, membaca kondisi logika dan boolean tanpa menebak, menjelaskan kenapa overflow dan floating point terjadi, membaca percentile dan tail latency, memakai Little's Law dan availability gabungan dengan sadar akan asumsinya, membuat estimasi kapasitas dengan rentang dan sanity check, dan memakai pola Observation → Hypothesis → Model → Prediction → Test saat debugging. Dibuktikan lewat Model-Hitung-Uji-Validasi, 30-Day Mathematics Lab, dan capstone "Design a Million-User System". Prinsip utama (dari penutup outline user): *ketika melihat masalah software, tanya: "bisakah ini dimodelkan?" lalu: Model → Hitung → Uji → Validasi → Implementasikan.*

## Registration History

Dibuat 2026-09-26 atas permintaan langsung user (paste outline "Matematika untuk Software Engineer" tanpa instruksi tambahan). Urutan: (1) mentor mencocokkan dengan guard "Course Aktif di bawah 50%" — Character Development pas 3/6 modul (50%) dengan log 28 hari berjalan sampai 2026-09-27, dan `male-grooming` masih Module 1 — lalu bertanya lewat AskUserQuestion; user memilih **"Audit overlap dulu"**. (2) Audit: grep kata kunci lintas `courses/*/roadmap.md` dan membaca daftar modul `computational-thinking`, `statistics-probability`, `ml-fundamentals`, `cryptography`, `performance-engineering`, `system-design`, `backend-fundamentals`, plus lesson-level `computational-thinking` M2, `ml-fundamentals` M2-4, `statistics-probability` M2, `performance-engineering` M7, `cryptography` M4. Hasil: ±22 dari 40 modul sudah punya rumah; gap nyata di logika/himpunan/fungsi, proof, bilangan di mesin, eksponen, percentile, queueing, reliability, information theory, estimasi. Overflow C dan fixed-point sebagian ada di `nasa-software-engineer` dan `satellite-programming`. **Belum dibuka lesson-level:** `neural-network`, `data-scientist`, `llm`, `data-analysis-visualization`, `personal-finance` (overlap dari daftar modul dan grep saja). (3) User memilih **"Course terlipat ~11 modul"** (rekomendasi mentor) dibanding extend course existing, course penuh 40 modul, atau simpan audit saja. Nama domain `mathematics-for-software-engineers` dan nama course `matematika-software-engineer` adalah **asumsi mentor yang tidak ditanyakan eksplisit**. Riset web (pencarian dan ringkasan halaman referensi, **bukan teks primer**) memverifikasi keberadaan dan cakupan: Lehman-Leighton-Meyer (gratis, CC BY-SA), Goldberg 1991, Dean & Barroso 2013, Little's Law (berlaku tanpa asumsi distribusi tetapi butuh stationarity), Google SRE Book (nines, error budget), Bentley kolom 7. **Belum diverifikasi:** Shannon/MacKay, Harchol-Balter/Jain, Guesstimation, tabel latency numbers, Gil Tene, versi IEEE 754 terkini. **Belum diaudit:** apakah user punya buku sumber outline, dan apakah capstone sebaiknya memakai skenario tertentu (mis. sistem dari pekerjaan sekarang) — ditanyakan saat approval. Course **Draft, menunggu approval**; belum jadi Course Aktif.
