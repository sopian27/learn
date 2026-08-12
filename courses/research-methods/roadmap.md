# Course Roadmap

## Course Information

* Course Name: Researcher — dari 0 sampai Expert (Research Mindset, Literature Review, Metodologi, Statistik, Eksperimen, Penulisan Ilmiah)
* Category: Research Methods (Domain: research-methods — lihat `domains/research-methods/DOMAIN.md`)
* Difficulty: Level 0 (belum pernah menulis research question atau membaca paper secara kritis) → Level 3 (mampu menjalankan siklus riset penuh — gap finding sampai paper IMRaD siap submit — dengan disiplin statistik & reproducibility)
* Estimated Duration: 12 modul, 3-4 lesson/modul (~44 lesson), pace mengikuti Applied Practice nyata (tiap modul menghasilkan artefak menuju satu topik riset yang sama) — bukan dikebut
* Prerequisites: Tidak ada prasyarat akademik formal. Kesediaan memilih satu topik riset nyata di awal (bebas bidang) dan membawanya konsisten lintas modul sampai jadi Capstone. Modul 6-8 (statistik) butuh Python dasar (pandas/scipy/statsmodels) — dijelaskan dari nol tapi lebih lancar kalau user sudah terbiasa coding (sudah dipenuhi lewat background software engineering user). Modul 4 butuh akun Zotero (gratis).

---

# Learning Objectives

* [ ] Membedakan klaim ilmiah dari pseudosains lewat kriteria falsifiability (Popper) dan memahami pergeseran paradigma (Kuhn)
* [ ] Mengevaluasi kekuatan evidence sebuah klaim/paper secara kritis (hierarchy of evidence, bias umum, kualitas sumber)
* [ ] Merumuskan research question yang baik (FINER/PICO) dan menemukan research gap nyata dari literatur
* [ ] Menjalankan literature review (termasuk systematic review ala PRISMA) dengan matriks sintesis dan Zotero
* [ ] Memilih & menjustifikasi desain metodologi (kuantitatif/kualitatif/mixed methods) yang cocok untuk research question sendiri
* [ ] Menjalankan statistik terapan lewat kode (descriptive, inferential, regression) pakai Python, dan menginterpretasi hasil dengan jujur
* [ ] Merancang eksperimen yang valid (kontrol, randomization, sampling, power) dan mengenali ancaman validitas
* [ ] Menulis paper ilmiah berstruktur IMRaD dengan sitasi benar, mengikuti standar reproducibility & open science
* [ ] Menyelesaikan Capstone: satu Original Research Paper nyata dari research question sampai draft siap submit

---

# Course Modules

## Module 1 — Research Mindset & Epistemology

Description: Fondasi filsafat sains sebelum masuk teknik apa pun — apa yang membuat sebuah klaim layak disebut ilmiah, dan bagaimana ilmu pengetahuan sebenarnya berubah dari waktu ke waktu.

Lessons:

* [ ] 1.1 Sains vs pseudosains — demarcation problem, ciri-ciri klaim yang bisa diuji secara ilmiah
* [ ] 1.2 Falsifiability (Popper) — kenapa klaim yang "tidak bisa dibantah" itu lemah, bukan kuat
* [ ] 1.3 Paradigma, normal science, dan revolusi ilmiah (Kuhn) — kenapa sains tidak berkembang linear
* [ ] 1.4 Peta besar 12 modul course ini — bagaimana tiap modul menyambung jadi satu siklus riset utuh

Mini Project: Pilih 3 klaim populer yang lagi viral (internet/media), uji lewat kriteria falsifiability — mana yang bisa diuji ilmiah, mana yang tidak, dan kenapa.

Status: Not started

---

## Module 2 — Critical Evaluation of Evidence (Terapan Riset)

Description: Critical thinking versi terapan langsung ke evaluasi evidence ilmiah — bukan reasoning generik (itu di `critical-thinking`), tapi skill spesifik menilai kualitas sebuah klaim/paper.

Lessons:

* [ ] 2.1 Hierarchy of evidence — anecdote < case study < observational study < RCT < meta-analysis/systematic review
* [ ] 2.2 Bias umum dalam riset: funding bias, publication bias, selection bias — cara mendeteksinya di sebuah paper
* [ ] 2.3 Menilai sumber: peer-reviewed vs preprint vs predatory journal vs media populer
* [ ] 2.4 Memisahkan klaim asli paper dari overclaim media saat sains "diterjemahkan" jadi berita

Mini Project: Ambil 1 artikel berita bertema sains, bandingkan klaimnya dengan paper aslinya (kalau ada), tandai bagian yang overclaim/misrepresentasi.

Status: Not started

---

## Module 3 — Research Questions & Finding Research Gaps

Description: Dari topik luas ke pertanyaan riset yang spesifik dan bisa dijawab — sekaligus cara sistematis menemukan celah yang belum terjawab di literatur.

Lessons:

* [ ] 3.1 Anatomi research question yang baik — kriteria FINER (Feasible, Interesting, Novel, Ethical, Relevant)
* [ ] 3.2 Framework PICO/PICOT untuk menstrukturkan pertanyaan riset
* [ ] 3.3 Cara menemukan research gap nyata — bagian "future work" paper, temuan yang saling bertentangan, populasi/konteks yang belum banyak diteliti
* [ ] 3.4 Mempersempit topik riset bebas pilihan sendiri jadi satu research question konkret

Mini Project: Pilih 1 topik riset bebas (jadi topik yang dibawa sampai Capstone), tulis draft research question pakai FINER + PICO, temukan minimal 1 gap nyata dari 3 paper terkait.

Status: Not started

---

## Module 4 — Literature Review

Description: Menelusuri, mengelola, dan mensintesis literatur secara sistematis — bukan sekadar membaca banyak paper lalu meringkas satu-satu.

Lessons:

* [ ] 4.1 Strategi searching — database (Google Scholar, Semantic Scholar, database sesuai bidang), boolean search, teknik snowballing
* [ ] 4.2 Systematic review methodology & alur PRISMA (identification → screening → eligibility → included)
* [ ] 4.3 Zotero — reference management, tagging, ekspor sitasi otomatis
* [ ] 4.4 Sintesis literatur lewat matriks sintesis — mengorganisir per tema/temuan, bukan per paper

Mini Project: Bangun literature review 10 paper untuk research question dari Modul 3, lengkap dengan matriks sintesis dan library Zotero yang tertata.

Status: Not started

---

## Module 5 — Research Methodology & Design

Description: Memilih "cara menjawab" research question — pendekatan besar (kuantitatif/kualitatif/mixed) sampai desain riset konkret yang cocok.

Lessons:

* [ ] 5.1 Kuantitatif vs kualitatif vs mixed methods — kapan masing-masing cocok dipakai
* [ ] 5.2 Tipe desain riset: survey, eksperimen, studi kasus, longitudinal, cross-sectional
* [ ] 5.3 Populasi & sampling — random, stratified, convenience sampling, isu ukuran sampel
* [ ] 5.4 Memilih & menjustifikasi metodologi untuk research question sendiri secara tertulis

Mini Project: Tulis draft Methods section (±1 halaman) untuk research question sendiri — desain riset, populasi/sampel, instrumen pengumpulan data.

Status: Not started

---

## Module 6 — Statistics Foundations via Code

Description: Statistik dasar dipelajari langsung lewat kode (Python), bukan rumus manual dulu — cocok dengan background coding user.

Lessons:

* [ ] 6.1 Descriptive statistics — mean/median/mode/variance/std deviation pakai pandas
* [ ] 6.2 Probability basics & distribusi umum (normal, binomial) pakai scipy.stats
* [ ] 6.3 Visualisasi distribusi data — histogram, boxplot, dan kenapa bentuk visual ini penting sebelum uji statistik
* [ ] 6.4 Eksplorasi 1 dataset publik nyata (Kaggle/UCI) end-to-end pakai pandas

Mini Project: Ambil 1 dataset publik relevan ke topik riset sendiri (atau dataset latihan bila belum ada), hitung descriptive statistics lengkap + visualisasi dasar.

Status: Not started

---

## Module 7 — Inferential Statistics & Hypothesis Testing

Description: Dari deskripsi data ke klaim yang bisa digeneralisasi — uji hipotesis, dan jebakan statistik yang paling sering merusak riset.

Lessons:

* [ ] 7.1 Null vs alternative hypothesis, p-value, significance level — makna sebenarnya (bukan "p<0.05 = terbukti benar")
* [ ] 7.2 t-test, ANOVA, chi-square — kapan pakai yang mana, dijalankan pakai scipy/statsmodels
* [ ] 7.3 Effect size & confidence interval — kenapa p-value saja tidak cukup untuk menilai kepentingan hasil
* [ ] 7.4 P-hacking & multiple comparison problem — cara mendeteksi dan menghindarinya di riset sendiri

Mini Project: Jalankan 1 hypothesis test nyata pada dataset Modul 6, laporkan p-value + effect size + interpretasi jujur (termasuk kalau hasilnya tidak signifikan).

Status: Not started

---

## Module 8 — Regression & Multivariate Analysis

Description: Melampaui uji beda sederhana — memodelkan hubungan antar variabel, dan menjaga jarak tegas antara korelasi dan kausalitas.

Lessons:

* [ ] 8.1 Correlation vs causation — kenapa keduanya paling sering tertukar dalam klaim riset populer
* [ ] 8.2 Simple & multiple linear regression pakai statsmodels
* [ ] 8.3 Logistic regression untuk outcome kategorikal
* [ ] 8.4 Membaca & melaporkan koefisien regresi dengan benar — interpretasi praktis, bukan sekadar angka

Mini Project: Bangun 1 model regresi (linear/logistic) di dataset nyata terkait topik riset sendiri, interpretasi hasil, cek asumsi dasar model.

Status: Not started

---

## Module 9 — Experimentation Design

Description: Merancang eksperimen yang benar-benar bisa mendukung klaim kausal — kontrol, randomization, dan cara mengenali kapan sebuah desain lemah.

Lessons:

* [ ] 9.1 Validitas internal vs eksternal — ancaman umum (confounding, selection bias, history/maturation effect)
* [ ] 9.2 Randomization & control group — kenapa random assignment jadi kunci klaim kausal yang kuat
* [ ] 9.3 Sampling & power analysis — menentukan ukuran sampel cukup sebelum eksperimen dijalankan
* [ ] 9.4 Kaitan ke A/B testing industri — persamaan & perbedaan desain eksperimen akademik vs product experimentation

Mini Project: Rancang (di atas kertas, tidak wajib dijalankan skala besar) satu desain eksperimen lengkap untuk research question sendiri — variabel, kelompok kontrol, randomization, target ukuran sampel.

Status: Not started

---

## Module 10 — Data Analysis & Visualization for Research

Description: Mengubah hasil analisis jadi bagian Results yang jujur dan jelas dibaca — bukan dashboard bisnis, tapi tabel/figur gaya akademik.

Lessons:

* [ ] 10.1 Alur exploratory data analysis (EDA) yang lengkap sebelum pelaporan
* [ ] 10.2 Membuat tabel & figur gaya akademik mengikuti konvensi APA
* [ ] 10.3 Storytelling data untuk audiens ilmiah — apa yang wajib dilaporkan, larangan cherry-pick hasil
* [ ] 10.4 Menyiapkan draft Results section dari analisis nyata yang sudah dikerjakan

Mini Project: Tulis draft Results section (tabel + 1-2 figur + narasi) dari analisis dataset yang sudah dikerjakan di Modul 6-8.

Status: Not started

---

## Module 11 — Scientific Writing, Citation & Reproducibility

Description: Merangkai semua hasil kerja jadi tulisan ilmiah yang benar secara struktur, sitasi, dan bisa direplikasi orang lain.

Lessons:

* [ ] 11.1 Struktur IMRaD — isi tiap bagian dan kesalahan umum yang sering terjadi di masing-masing
* [ ] 11.2 Gaya sitasi APA/IEEE, plagiarism, dan cara parafrase yang benar
* [ ] 11.3 Reproducibility & open science — preregistration, open data/code, replication crisis (Ioannidis)
* [ ] 11.4 Menulis Abstract yang benar — ringkas tapi lengkap (background-method-result-conclusion)

Mini Project: Tulis draft Introduction + Abstract untuk paper Capstone, sitasi minimal 5 sumber dengan gaya konsisten (APA atau IEEE).

Status: Not started

---

## Module 12 — Capstone: Original Research Paper

Description: Integrasi penuh seluruh course jadi satu paper ilmiah nyata, mengikuti `domains/research-methods/DOMAIN.md` bagian "Project Tiers" untuk definisi Capstone di domain ini.

Lessons:

* [ ] 12.1 Menyusun kerangka final paper IMRaD, mengintegrasikan draft dari Modul 5 (Methods), 10 (Results), 11 (Introduction/Abstract)
* [ ] 12.2 Menyelesaikan analisis data final dan mengisi Results secara lengkap
* [ ] 12.3 Menulis Discussion — interpretasi hasil, limitasi riset, implikasi, arah future work
* [ ] 12.4 Self-review memakai `domains/research-methods/DOMAIN.md` bagian "Review Style" (Methodological Validity, Evidence Strength vs Claim, Statistical Honesty, Reproducibility, Writing Clarity) sebelum finalisasi

Capstone Project: **Original Research Paper** — siklus riset penuh (research question → literature review → methodology → analisis data → Results → Discussion) dalam struktur IMRaD, topik bebas pilihan sendiri (dibawa konsisten sejak Modul 3), draft siap submit ke preprint (OSF/arXiv) atau conference/jurnal kecil, disimpan ke `portofolio/`. Review memakai `domains/research-methods/DOMAIN.md` bagian "Review Style" di atas Universal Review Rubric (`ai-los/CORE_LOS.md`), plus Assessment Form-nya.

Status: Not started

---

# Capstone Project

Description: Lihat Module 12 di atas untuk rincian lengkap. Capstone course ini SATU dengan Capstone Project Module 12 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/research-methods/DOMAIN.md` bagian "Project Tiers" ("Capstone / Portfolio Project — akhir course").

Acceptance Criteria: Memenuhi kelima kriteria domain (Methodological Validity, Evidence Strength vs Claim, Statistical Honesty, Reproducibility, Writing Clarity) plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, dan memenuhi bar "Portfolio-Worthy" (Complete, Reviewed, Presentable, Original) di `standards/UNIVERSAL_STANDARDS.md`. Wajib disertai data/kode analisis yang bisa ditelusuri ulang (reproducible), bukan hasil yang cuma dilaporkan tanpa jejak proses.

Status: Not started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: 1.1 Sains vs pseudosains — demarcation problem, ciri-ciri klaim yang bisa diuji secara ilmiah
