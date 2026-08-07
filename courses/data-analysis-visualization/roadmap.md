# Course Roadmap

## Course Information

* Course Name: Data Analysis & Visualization — dari 0 sampai Expert (Data Analyst/BI Track)
* Category: Data Analytics / Business Intelligence
* Difficulty: Level 1 (sudah nyaman SQL, belum pernah statistik/visualisasi/dashboard terstruktur) → Level 4 (Expert, siap kerja/interview sebagai Data Analyst/BI Analyst)
* Estimated Duration: 9 modul, 3-5 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Nyaman menulis query SQL dasar (dari pekerjaan backend). Tidak perlu pengalaman statistik, visualisasi data, atau BI tool sebelumnya — semua diajarkan dari nol di course ini.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda (2026-08-06):

1. **Berdiri sendiri, bukan pengganti/pengulangan `courses/ml-fundamentals` Module 1.** Module 1 course ML itu hanya mengajarkan Pandas/NumPy/Matplotlib *secukupnya* sebagai prasyarat untuk membangun model. Course ini jauh lebih dalam dan punya tujuan berbeda: analisis data sebagai *disiplin sendiri* (data analyst/BI), bukan langkah persiapan ML — mencakup statistik untuk pengambilan keputusan bisnis (bukan cuma evaluasi model), visualisasi tingkat lanjut & storytelling, SQL analytics, dan BI tooling (Power BI) yang sama sekali tidak disentuh course ML. Kedua course independen, tidak ada urutan wajib di antara keduanya.
2. **Terhubung dengan `courses/backend-fundamentals` Module 7 (Advanced SQL & Reporting Query Patterns)**, bukan mengulangnya. CTE/window function akan **dipakai** di Modul 2 course ini dalam konteks analytics (cohort, funnel, period-over-period) — bila Anda belum sampai Modul 7 backend-fundamentals saat memulai course ini, Modul 2 tetap mengajarkan window function secukupnya untuk dipakai, hanya tidak sedalam treatment SQL engine/query planning di sana.
3. **Tujuan: standalone Data Analyst/BI skill** — bukan sekadar skill pendukung, dan bukan pula dikemas eksplisit sebagai jasa freelance seperti Course #1 (meski tidak menutup kemungkinan dipakai untuk itu nanti). Karena itu course dikalibrasi ke kedalaman & keluasan level "siap kerja/interview sebagai Data Analyst/BI Analyst", termasuk modul komunikasi insight dan persiapan interview di Modul 9.
4. **Level awal**: SQL dasar sudah nyaman → Modul 2 langsung ke pola analytics (window function, CTE, cohort/funnel), tidak mengulang `SELECT`/`JOIN` dasar. Statistik, visualisasi, dan BI tool semuanya dari nol.
5. **Tool BI**: **Power BI** — dipilih karena permintaan pasar kerja tertinggi (global & Indonesia), versi desktop gratis, dan DAX adalah skill yang transferable ke ekosistem Microsoft yang luas dipakai enterprise. Tableau/Looker Studio tidak dipakai di course ini (bisa jadi course terpisah kalau suatu saat dibutuhkan).

Referensi riset yang dipakai menyusun roadmap ini: [roadmap.sh/data-analyst](https://roadmap.sh/data-analyst) & [roadmap.sh/bi-analyst](https://roadmap.sh/bi-analyst) (struktur skill data analyst & BI analyst 2026), buku *Storytelling with Data* — Cole Nussbaumer Knaflic (prinsip visualisasi & komunikasi insight, dipakai sebagai kerangka Modul 5 & 8), [Microsoft Learn — Power BI documentation & DAX learning paths](https://learn.microsoft.com/en-us/power-bi/) (dipakai sebagai kerangka Modul 6-7).

---

# Learning Objectives

* [ ] Mahir menulis query SQL analytics tingkat lanjut: window function, CTE, cohort/funnel/period-over-period analysis
* [ ] Mahir data wrangling dengan Pandas untuk data nyata yang berantakan: merge/join, reshape, handling data kotor (tanggal, teks, duplikat)
* [ ] Punya intuisi statistik terapan untuk keputusan bisnis: distribusi, korelasi vs kausalitas, hypothesis testing, confidence interval
* [ ] Mampu merancang dan menginterpretasikan A/B test dengan benar (termasuk jebakan statistiknya)
* [ ] Menguasai prinsip visualisasi data (Storytelling with Data) dan mampu memilih chart yang tepat untuk pertanyaan yang tepat
* [ ] Mahir Power BI: data modeling (star schema), DAX (measure, filter context, time intelligence), dan desain dashboard interaktif
* [ ] Mampu mengomunikasikan insight ke stakeholder non-teknis: executive summary, narrative deck, menjawab "so what"
* [ ] Siap interview Data Analyst/BI Analyst: SQL screen, case study, take-home analysis
* [ ] Menyelesaikan proyek portofolio: analisis data end-to-end dari dataset mentah sampai dashboard & narasi bisnis

---

# Course Modules

## Module 1 — Fondasi: Mindset Data Analyst & Lanskap Data

Description: Sebelum masuk tool, pahami dulu peran ini sebenarnya dan alur kerja analisis yang benar — supaya tidak langsung lompat ke "bikin chart" tanpa proses.

Lessons:

* [ ] 1.1 Apa yang sebenarnya dikerjakan Data Analyst — beda dengan Data Scientist, Analytics Engineer, BI Developer
* [ ] 1.2 Sumber & koleksi data: database, API, spreadsheet, flat file
* [ ] 1.3 Kualitas data: jenis data kotor & kenapa "garbage in, garbage out" nyata
* [ ] 1.4 Alur kerja analisis: ask → collect → clean → analyze → visualize → communicate → act

Mini Project: Audit 1 dataset nyata yang berantakan (CSV publik), tulis laporan kualitas data (masalah ditemukan + rekomendasi perbaikan).

Status: Not Started

---

## Module 2 — SQL untuk Analytics

Description: Dari SQL dasar yang sudah Anda kuasai, naik ke pola query yang dipakai analyst sehari-hari untuk menjawab pertanyaan bisnis.

Lessons:

* [ ] 2.1 Aggregate function lanjutan: `GROUP BY`/`HAVING` untuk pertanyaan bisnis nyata
* [ ] 2.2 Window function: `ROW_NUMBER`, `RANK`, `LAG`/`LEAD`, running total (rujuk `standards/` & `courses/backend-fundamentals` Module 7 untuk kedalaman query planning)
* [ ] 2.3 CTE untuk query analisis multi-langkah yang terbaca
* [ ] 2.4 Pola query analytics umum: cohort analysis, funnel analysis, period-over-period comparison

Mini Project: Tulis serangkaian query analytics terhadap dataset e-commerce/sales contoh — cohort retention, growth month-over-month, top-N per kategori.

Status: Not Started

---

## Module 3 — Data Wrangling dengan Python (Pandas Mendalam)

Description: Melampaui Pandas dasar — menangani data nyata yang berantakan dan tersebar di banyak sumber.

Lessons:

* [ ] 3.1 Merge & join dataset — kombinasi berbagai sumber data
* [ ] 3.2 Reshape data: pivot, melt, wide vs long format
* [ ] 3.3 Membersihkan data kotor nyata: parsing tanggal, normalisasi teks, deteksi & penanganan duplikat
* [ ] 3.4 Bekerja dengan banyak file & pertimbangan performa dasar

Mini Project: Bersihkan & gabungkan 2-3 dataset nyata yang berantakan jadi satu dataset siap-analisis.

Status: Not Started

---

## Module 4 — Statistik untuk Analyst

Description: Statistik di sini untuk mendukung keputusan bisnis, bukan evaluasi model ML — kerangka berpikir yang berbeda dari `courses/ml-fundamentals` Module 4.

Lessons:

* [ ] 4.1 Statistik deskriptif mendalam: distribusi, outlier, skewness
* [ ] 4.2 Korelasi vs kausalitas, Simpson's paradox — jebakan interpretasi yang paling sering terjadi
* [ ] 4.3 Hypothesis testing & confidence interval untuk pertanyaan bisnis nyata
* [ ] 4.4 Fondasi A/B testing: merancang eksperimen, ukuran sampel, interpretasi hasil yang benar

Mini Project: Rancang & analisis 1 A/B test (mock atau dataset A/B test publik), tulis kesimpulan lengkap dengan catatan statistik yang tepat (bukan overclaim).

Status: Not Started

---

## Module 5 — Prinsip Visualisasi & Visualisasi Python

Description: Membuat chart yang benar butuh prinsip, bukan sekadar tahu sintaks library.

Lessons:

* [ ] 5.1 Memilih chart yang tepat untuk pertanyaan yang tepat (bukan "matplotlib punya bar chart")
* [ ] 5.2 Prinsip Storytelling with Data: eliminasi clutter, pre-attentive attributes, prinsip Gestalt
* [ ] 5.3 Visualisasi Python tingkat lanjut: Seaborn/Plotly untuk plot statistik & interaktif
* [ ] 5.4 Kesalahan umum & "kebohongan" dengan data (sumbu menyesatkan, cherry-picking, dll)

Mini Project: Ambil hasil A/B test Modul 4, rancang 3 kandidat visualisasi, kritik masing-masing memakai prinsip storytelling, pilih & sempurnakan yang terbaik.

Status: Not Started

---

## Module 6 — Fondasi Power BI

Description: Masuk ke tool BI — dari koneksi data sampai laporan interaktif pertama.

Lessons:

* [ ] 6.1 Arsitektur Power BI: Desktop vs Service, sumber data & connector
* [ ] 6.2 Data modeling: star schema, relationship, fact vs dimension table
* [ ] 6.3 Membangun report pertama: visual, filter, slicer

Mini Project: Bangun report Power BI yang terhubung ke dataset siap-analisis dari Modul 3, lengkap dengan visual & filter dasar.

Status: Not Started

---

## Module 7 — DAX & Power BI Tingkat Lanjut

Description: Skill yang membedakan Power BI user biasa dari BI Analyst yang benar-benar dipercaya.

Lessons:

* [ ] 7.1 Fondasi DAX: calculated column vs measure, filter context vs row context
* [ ] 7.2 Pola DAX umum: time intelligence, running total, rasio/percentage of total
* [ ] 7.3 Desain dashboard: layout, interaktivitas, drill-through, performa

Mini Project: Bangun measure (YoY growth, running total, % of total) dan dashboard interaktif yang rapi dari data Modul 6.

Status: Not Started

---

## Module 8 — Mengomunikasikan Insight & Business Storytelling

Description: Analisis terbaik pun tidak berguna kalau tidak bisa disampaikan ke orang yang mengambil keputusan.

Lessons:

* [ ] 8.1 Menyusun presentasi/laporan analisis untuk stakeholder non-teknis
* [ ] 8.2 Menulis executive summary — lulus uji "so what"
* [ ] 8.3 Menangani pushback & permintaan ambigu ("tampilkan semuanya saja")

Mini Project: Ubah salah satu analisis Anda sebelumnya jadi executive summary 1 halaman + narrative deck 5 slide.

Status: Not Started

---

## Module 9 — Menjadi Data Analyst/BI yang Dipercaya

Description: Level penutup — siap bekerja dan siap interview, plus tahu ke mana arah berikutnya.

Lessons:

* [ ] 9.1 Studi kasus: bagaimana tim analytics Netflix/Uber/Spotify/Amazon bekerja (budaya metrik & eksperimentasi)
* [ ] 9.2 Overview analytics engineering (dbt, konsep data warehouse) — secukupnya untuk tahu jenjang berikutnya, tidak dipraktikkan mendalam
* [ ] 9.3 Membangun portofolio analytics & persiapan interview: SQL screen, case study interview, take-home analysis

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Full Data Analysis & BI Deliverable** — pilih 1 dataset nyata yang berantakan (dataset publik atau data pribadi, mis. finansial/kesehatan/metrik kerja), jalankan pipeline penuh: (1) koleksi & audit kualitas data (Modul 1), (2) pembersihan/wrangling SQL + Python (Modul 2-3), (3) analisis statistik untuk menjawab 1 pertanyaan bisnis nyata (Modul 4), (4) visualisasi yang dirancang mengikuti prinsip storytelling (Modul 5), (5) dashboard Power BI interaktif dengan DAX measure (Modul 6-7), (6) executive summary & narrative deck (Modul 8).

Acceptance Criteria:

* Pertanyaan bisnis yang dijawab jelas dan terukur — bukan sekadar "eksplorasi data"
* Query SQL analytics (window function/CTE) dipakai secara nyata, bukan hanya `SELECT *`
* Klaim statistik (korelasi, hasil test) disertai catatan validitas/limitasi, tidak overclaim
* Dashboard Power BI berjalan interaktif dengan minimal 3 measure DAX kustom
* Executive summary lulus uji "so what" — pembaca non-teknis paham insight & rekomendasinya dalam < 2 menit baca
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: Belum dimulai — roadmap approved 2026-08-06, menunggu mulai

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Apa yang sebenarnya dikerjakan Data Analyst
