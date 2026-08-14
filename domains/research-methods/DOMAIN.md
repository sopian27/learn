# Research Methods — Domain Pack (Riset Ilmiah dari 0 sampai Expert)

## Domain Name

Research Methods (Metodologi Riset Ilmiah: Mindset, Literature Review, Statistik, Eksperimen, Penulisan Ilmiah)

## Scope

Metodologi riset ilmiah domain-agnostic dari 0 sampai expert: research mindset & epistemology (falsifiability, paradigma), critical evaluation of evidence versi terapan riset, merumuskan research question & menemukan research gap, literature review (termasuk systematic review/PRISMA), desain metodologi (kuantitatif/kualitatif/mixed methods), statistik terapan lewat kode (Python: pandas/scipy/statsmodels), desain eksperimen & validitas, data analysis & visualization gaya akademik, scientific writing (struktur IMRaD), sitasi (APA/IEEE + tool Zotero), reproducibility & open science, sampai proses peer review/publishing. Topik riset tiap Applied Practice/Capstone bebas dipilih user — course ini mengajarkan METODE-nya, bukan satu bidang riset spesifik.

Secara eksplisit TIDAK mencakup:

- **Reasoning generik & logical fallacy** (argumentasi sehari-hari, bias kognitif umum di luar konteks riset) — domain `critical-thinking` (lihat `domains/critical-thinking/DOMAIN.md`). Domain ini hanya mengajarkan critical thinking versi TERAPAN ke evaluasi evidence ilmiah (Module 2), merujuk balik ke `critical-thinking` untuk fondasi reasoning generik, tidak mengulang.
- **Riset akademik khusus satu bidang** (mis. CS paper venue, benchmark ML, riset khusus software engineering) — course ini domain-agnostic, topik riset bebas dipilih user per proyek. Kalau user nanti mau pendalaman riset CS/SWE spesifik, itu jadi domain terpisah.
- **Motivasi/habit/flow saat belajar** — domain `learning-science`, hanya dirujuk sesaat bila relevan (mis. menjaga ritme mengerjakan literature review tetap berkelanjutan).
- **Teknik retensi memori** (active recall, spaced repetition) — domain `memory-mastery`. Boleh dirujuk untuk mengingat literatur yang sudah dibaca, tidak diajarkan ulang di sini.
- **Data Analysis & Visualization sebagai skill BI/bisnis** — `courses/data-analysis-visualization/roadmap.md` (SQL analytics, Power BI/DAX, storytelling data bisnis). Domain ini mengajarkan analisis data KHUSUS konteks riset ilmiah (hypothesis testing, regression untuk inferensi, bukan dashboard bisnis).

## Trusted Sources / Research Priority

1. **Karl Popper — *The Logic of Scientific Discovery*** — dasar falsifiability, kriteria demarkasi sains vs pseudosains, fondasi Module 1.
2. **Thomas Kuhn — *The Structure of Scientific Revolutions*** — konsep paradigma & normal science vs revolusi ilmiah, Module 1.
3. **PRISMA Statement (Page et al., 2021, peer-reviewed)** — standar resmi systematic review & meta-analysis, dasar Module 4.
4. **Anthony Fowler / Judea Pearl (causal inference dasar) & standar textbook metodologi (Research Methods for Business Students — Saunders, Lewis, Thornhill; The Craft of Research — Booth, Colomb, Williams)** — dasar Module 3, 5, 9.
5. **Practical Statistics for Data Scientists (Bruce, Bruce & Gedeck)** dan dokumentasi resmi `scipy.stats`/`statsmodels`** — dasar statistik terapan lewat kode, Module 6-8.
6. **John Ioannidis — "Why Most Published Research Findings Are False" (2005, PLOS Medicine, peer-reviewed)** — dasar kritis reproducibility crisis, Module 11, wajib dirujuk sebelum mengajarkan open science sebagai solusi.
7. **Center for Open Science (OSF) — preregistration & open science guidelines** — praktik reproducibility nyata, Module 11.
8. **APA Publication Manual (7th ed.) & IEEE Citation Style Guide** — standar sitasi resmi, Module 11.
9. **Cochrane Handbook for Systematic Reviews of Interventions** — pelengkap PRISMA untuk sintesis evidence, Module 2 & 4.

**House rule khusus domain ini:** setiap klaim metodologi/statistik yang diajarkan wajib bisa dilacak ke sumber di atas atau setara peer-reviewed — tolak "hack riset" viral (mis. p-hacking dibungkus sebagai tips, cherry-picking hasil) tanpa disertai peringatan eksplisit bahwa itu praktik buruk.

## Applied Practice Definition

Setiap modul menghasilkan artefak riset NYATA, bukan cuma quiz teori: draft research question, matriks sintesis literature review, skrip analisis statistik yang benar-benar dijalankan di dataset nyata, desain eksperimen tertulis, draft bagian paper (Introduction/Methods/Results/Discussion) — dikerjakan berkesinambungan menuju satu topik riset pilihan user, bukan latihan terpisah-pisah tanpa benang merah.

## Project Tiers

- **Mini Project** (per modul): satu artefak riset per modul (mis. matriks sintesis 10 paper, satu hypothesis test dijalankan di dataset nyata dengan interpretasi jujur).
- **Intermediate Project** (per beberapa modul): mini-study end-to-end skala kecil — dari research question sampai hasil analisis data sekunder (dataset publik), tanpa collection data primer berskala besar.
- **Capstone / Portfolio Project** (akhir course): **Original Research Paper** — siklus penuh IMRaD (Introduction, Methods, Results, and Discussion) dari research question sampai draft siap submit ke preprint (OSF/arXiv) atau conference/jurnal kecil, topik bebas dipilih user, disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak juga dicek lewat lensa:

- **Methodological Validity** — apakah desain riset/metodologi cocok menjawab research question yang diajukan?
- **Evidence Strength vs Claim** — apakah klaim yang ditulis proporsional dengan kekuatan evidence yang didapat (tidak overclaim)?
- **Statistical Honesty** — ada indikasi p-hacking, cherry-picking hasil, atau multiple comparison tanpa koreksi?
- **Reproducibility** — bisakah orang lain mengulang analisis ini dari data + kode/langkah yang didokumentasikan?
- **Writing Clarity** — mengikuti struktur IMRaD dan konvensi penulisan ilmiah dengan jelas.

## Assessment Form

Kombinasi: quiz konsep tiap modul, critique paper asli (dikasih 1 paper nyata, user diminta menemukan flaw metodologi/statistik di dalamnya), dan self-audit checklist reproducibility di Capstone (data tersedia? kode/langkah analisis terdokumentasi? klaim sesuai evidence?).

## Practitioner Reference Frame

Karl Popper (falsifiability) dan Thomas Kuhn (paradigma) untuk fondasi filsafat sains; John Ioannidis untuk kritik reproducibility; tim PRISMA/Cochrane untuk standar systematic review; komunitas Open Science (Center for Open Science/OSF) untuk praktik reproducibility modern; dibandingkan dengan pola "riset" konten viral media sosial yang sering overclaim dari studi tunggal kecil tanpa replikasi.

## Domain-Specific Standards

- **Tolak p-hacking dan cherry-picking** — setiap hasil statistik yang tidak signifikan wajib dilaporkan apa adanya, bukan disembunyikan atau di-reframe supaya terlihat signifikan.
- **Klaim kausal wajib dipisahkan dari klaim korelasional** — regresi/korelasi tanpa desain eksperimen/randomization tidak boleh dilaporkan sebagai "menyebabkan".
- **Riset yang melibatkan subjek manusia (survei/wawancara ke orang nyata) wajib disertai catatan etika riset dasar** (informed consent, anonimitas data) — course ini bukan pengganti proses ethics board/IRB formal untuk riset yang benar-benar dipublikasikan ke jurnal berstandar tinggi; arahkan ke prosedur institusi resmi bila user melangkah ke publikasi sungguhan yang butuh itu.
- Selebihnya, `standards/UNIVERSAL_STANDARDS.md` cukup — `domains/research-methods/standards/` dibuat hanya kalau kebutuhan rigor spesifik muncul berulang.

## Domain Goal

Learner mampu menjalankan siklus riset ilmiah penuh secara mandiri — dari menemukan research gap, merumuskan research question, literature review, memilih metodologi, menjalankan analisis statistik lewat kode, sampai menulis paper IMRaD yang jujur secara evidence dan reproducible — dibuktikan lewat satu Original Research Paper nyata di Capstone, bukan sekadar hafal istilah metodologi.

## Registration History

Registered 2026-08-12, domain pack + course roadmap dibangun atas permintaan user ("Buat course menjadi researcher dari 0 hingga expert. Ajarkan research mindset, critical thinking, research questions, literature review, methodology, statistics, experimentation, data analysis, scientific writing, citation, reproducibility, dan menemukan research gap. Gunakan proyek nyata bertahap.").
