# Course Roadmap

## Course Information

* Course Name: Data Scientist — dari 0 sampai Expert
* Category: Data Science / Applied Machine Learning (Career Path)
* Difficulty: Level 1 (belum tentu bisa Python, hanya tahu istilah "data scientist") → Level 4 (Expert, mampu menjalankan siklus data science end-to-end — dari problem framing sampai model production-aware — dan lulus interview Data Scientist tanpa panduan)
* Estimated Duration: 11 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Programming dasar (familiar sintaks Java/Spring Boot dari repo ini sudah cukup sebagai modal logika program). **Tidak** diasumsikan sudah bisa Python — Module 1 mengajarkan Python-for-data-science dari nol (scoped tight, lihat Catatan Scope). `courses/ml-fundamentals` **tidak wajib** diselesaikan lebih dulu — course ini berdiri sendiri dan mengajarkan ulang statistik & ML klasik dalam konteks aplikatif Data Scientist. Namun kalau `ml-fundamentals` sudah pernah dijalani, Module 2 (statistik) dan Module 6-7 (ML klasik) akan terasa seperti *refresher* yang lebih cepat, bukan materi baru dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini adalah jalur karier **Data Scientist terapan, end-to-end** — problem framing bisnis → data collection/cleaning → EDA → feature engineering → modeling klasik → evaluasi & desain eksperimen → komunikasi hasil → kesadaran production/deployment → siap interview. Repo ini sudah punya dua course yang scope-nya bersinggungan, jadi harus dipertegas dulu batasnya:

1. **Beda dari `courses/data-analysis-visualization`.** Course itu adalah jalur karier **Data Analyst/BI Analyst** — fokus pada tooling (SQL analytics lanjut, Power BI, DAX) dan komunikasi insight ke bisnis, dengan kedalaman modeling yang sengaja dangkal (course itu bahkan tidak membahas scikit-learn sama sekali; statistiknya berhenti di hypothesis testing/A-B testing untuk *pengambilan keputusan*, bukan untuk *membangun model prediktif*). Course ini adalah kebalikannya di sisi modeling: Module 6-7 di course ini membangun & men-tuning model prediktif sungguhan (regresi, klasifikasi, ensemble, clustering) dengan scikit-learn — sesuatu yang **tidak ada sama sekali** di `data-analysis-visualization`. Sebaliknya, course ini sengaja **tidak** mengajarkan Power BI/DAX atau SQL analytics window-function mendalam — Module 3 course ini hanya menyentuh SQL/API secukupnya untuk *mengambil* data, lalu merujuk balik ke `courses/data-analysis-visualization` Module 2 kalau butuh SQL analytics tingkat lanjut. Kedua course independen (tidak ada urutan wajib), tapi kalau harus dipilih satu kalimat pembeda: Analyst *menjelaskan* data dengan tool BI, Data Scientist *memodelkan* data dengan ML untuk prediksi/keputusan otomatis.
2. **Beda dari `courses/ml-fundamentals`.** Course itu adalah **fondasi ML/DL umum** yang sengaja generik dan menjadi jembatan matematis menuju `courses/llm` dan `courses/agents` — modul matematikanya (aljabar linear, kalkulus, probabilitas) diajarkan dari nol dengan tujuan supaya konsep neural network/Transformer "tidak terasa seperti sihir", dan modul intinya (Module 8-9) berhenti di neural network dari nol + PyTorch dasar (CNN/RNN). Course ini punya tujuan berbeda: **karier Data Scientist**, bukan jembatan ke deep learning/LLM. Course ini **tidak** mengajarkan neural network/backpropagation/PyTorch sama sekali — Module 9 course ini secara eksplisit menandai batas itu dan merujuk balik ke `courses/ml-fundamentals` Module 8-9 (lalu `courses/llm`) untuk siapa pun yang butuh deep learning. Sebagai gantinya, course ini jauh lebih dalam di sisi yang `ml-fundamentals` lewati cepat: data cleaning dunia nyata (Module 3), feature engineering praktis (Module 5), desain eksperimen & causal inference basics (Module 8 — sama sekali tidak ada di `ml-fundamentals`), MLOps-lite awareness (Module 9), komunikasi stakeholder (Module 10), dan persiapan interview Data Scientist spesifik (Module 11 — case study, take-home, portfolio review). Statistik di Module 2 course ini juga lebih dalam dari `ml-fundamentals` Module 4 (menambahkan estimasi/CLT, Bayesian thinking dasar, power analysis) karena dipakai langsung untuk merancang eksperimen di Module 8, bukan hanya untuk evaluasi model.
3. **Kenapa Python-for-data-science diajarkan lagi (tapi ringkas) di Module 1**, padahal `ml-fundamentals` Module 1 dan `data-analysis-visualization` Module 3 sudah mengajarkannya. Course ini sengaja dibuat **tidak mensyaratkan** salah satu dari keduanya sebagai prasyarat keras (lihat Prerequisites di atas) — supaya siapa pun dengan latar belakang programming (Java/Spring Boot) bisa mulai langsung dari course ini tanpa terjebak menyelesaikan course lain dulu. Karena itu Module 1.3 memberi NumPy/Pandas *essentials* yang cukup untuk lanjut ke Module 3 dan seterusnya — bukan wrangling mendalam (itu tetap domain `data-analysis-visualization` Module 3, dirujuk balik untuk kasus data yang sangat berantakan) dan bukan pengantar AI/ML landscape (itu tetap domain `ml-fundamentals` Module 1).
4. **Tidak mencakup**: deep learning/neural network (lihat poin 2), BI tooling & SQL analytics mendalam (lihat poin 1), big data engineering (Spark/Hadoop), atau MLOps penuh (CI/CD model, orchestration seperti Airflow/Kubeflow) — Module 9 hanya *kesadaran* level konsep, bukan hands-on tool MLOps, dan secara eksplisit menyebut bahwa ini bukan pengganti course MLOps penuh kalau nanti dibutuhkan.

Referensi riset yang dipakai menyusun roadmap ini: [roadmap.sh — AI and Data Scientist Roadmap](https://roadmap.sh/ai-data-scientist) (kerangka topik & urutan level: matematika/statistik → programming → ML → deep learning → MLOps, dipakai sebagai kerangka umum modul, dengan batas deep learning/MLOps sengaja dipotong sesuai poin 2 & 4 di atas), [Kaggle Learn — Intro to Machine Learning](https://www.kaggle.com/learn/intro-to-machine-learning) dan [Kaggle Learn — Feature Engineering](https://www.kaggle.com/learn/feature-engineering) (struktur praktis & urutan hands-on untuk Module 5-7: EDA → feature engineering → model klasik dengan scikit-learn), dokumentasi resmi [scikit-learn](https://scikit-learn.org/stable/) (API & best practice Pipeline/ColumnTransformer dipakai sebagai kerangka Module 5-7), *An Introduction to Statistical Learning* (ISLR, James/Witten/Hastie/Tibshirani — [PDF resmi gratis di statlearning.com](https://www.statlearning.com/)) sebagai kerangka konseptual regresi/klasifikasi/regularization di Module 6, Google — [Rules of Machine Learning: Best Practices for ML Engineering](https://developers.google.com/machine-learning/guides/rules-of-ml) (dipakai sebagai kerangka Module 9: kapan ML dibutuhkan, feature engineering di production, monitoring/drift), dan *Ace the Data Science Interview* (Singh & Huo — 201 Real Interview Questions Asked By FAANG, Tech Startups & Wall Street; struktur babak interview: SQL screen, statistik/ML, coding, open-ended case study, dipakai sebagai kerangka Module 11).

---

# Learning Objectives

* [ ] Memahami peran & siklus kerja Data Scientist (problem framing → data → model → keputusan) dan bedanya dengan Data Analyst/ML Engineer
* [ ] Mahir Python untuk data science secukupnya (NumPy/Pandas essentials) untuk menopang seluruh siklus kerja course ini
* [ ] Punya fondasi statistik & probabilitas terapan yang cukup dalam untuk mendesain eksperimen, bukan cuma menghitung angka
* [ ] Mampu mengumpulkan, membersihkan, dan memvalidasi data dunia nyata yang berantakan sampai siap dianalisis
* [ ] Mampu melakukan EDA sistematis yang menghasilkan hipotesis awal terarah, bukan eksplorasi acak
* [ ] Mahir feature engineering (encoding, scaling, seleksi fitur, handling imbalanced data) untuk menyiapkan data ke model
* [ ] Mampu membangun, men-tuning, dan mengevaluasi model klasik (regresi, klasifikasi, tree-based/ensemble, clustering) dengan scikit-learn
* [ ] Mampu merancang dan menganalisis eksperimen (A/B testing) dengan benar, termasuk memahami dasar causal inference dan jebakan statistiknya
* [ ] Memahami kapan classical ML cukup vs kapan butuh deep learning, dan punya kesadaran dasar production/MLOps (deployment, monitoring, drift) tanpa perlu jadi ahli MLOps
* [ ] Mampu menerjemahkan hasil model jadi rekomendasi bisnis yang dipahami stakeholder non-teknis
* [ ] Siap menjalani proses interview Data Scientist: SQL/statistik screen, teori ML, coding, case study, take-home assignment
* [ ] Menyelesaikan proyek portofolio: satu proyek data science end-to-end dari dataset mentah sampai laporan hasil untuk stakeholder

---

# Course Modules

## Module 1 — Fondasi: Peran Data Scientist & Python untuk Data Science

Description: Sebelum menyentuh model apa pun, pahami dulu apa sebenarnya pekerjaan Data Scientist dan siklus kerjanya, lalu siapkan tooling Python yang cukup (bukan mendalam) untuk menopang seluruh course.

Lessons:

* [ ] 1.1 Apa itu Data Scientist: beda dengan Data Analyst, ML Engineer, Data Engineer; siklus kerja data science (problem framing → data → model → keputusan, gaya CRISP-DM/OSEMN)
* [ ] 1.2 Problem framing: mengubah pertanyaan bisnis kabur ("naikkan retention") jadi problem statement data science yang terukur
* [ ] 1.3 Python untuk data science secukupnya: NumPy essentials (array, operasi vektor) & Pandas essentials (DataFrame, filtering, groupby) — untuk wrangling mendalam & data yang sangat berantakan, rujuk `courses/data-analysis-visualization` Module 3
* [ ] 1.4 Tooling & struktur proyek: Jupyter/VS Code, virtual environment, struktur folder proyek data science yang reproducible

Mini Project: Pilih 1 dataset publik nyata (mis. Kaggle/UCI), tulis problem statement yang terukur, dan setup struktur proyek data science yang rapi.

Status: Not Started

---

## Module 2 — Statistik & Probabilitas untuk Data Scientist

Description: Statistik di sini bukan sekadar teori — ini fondasi untuk mendesain eksperimen (Module 8) dan mengevaluasi model (Module 6-7) dengan benar. Lebih dalam dari `courses/ml-fundamentals` Module 4 karena dipakai untuk *merancang*, bukan cuma *mengevaluasi*.

Lessons:

* [ ] 2.1 Probabilitas & distribusi terapan: joint/marginal/conditional probability, distribusi normal/binomial/Poisson dan makna praktisnya
* [ ] 2.2 Estimasi & inferensi: sampling, Central Limit Theorem, standard error, confidence interval
* [ ] 2.3 Hypothesis testing lanjutan: t-test, chi-square test, p-value yang benar (dan kesalahpahaman umum), statistical power & effect size
* [ ] 2.4 Bayesian thinking dasar: prior, likelihood, posterior — intuisi kapan cara berpikir Bayesian lebih masuk akal dibanding frequentist

Mini Project: Analisis statistik lengkap pada 1 dataset nyata — rumuskan hipotesis, pilih test yang tepat, hitung confidence interval & effect size, tulis kesimpulan yang tidak overclaim.

Status: Not Started

---

## Module 3 — Data Collection, Cleaning & Data Quality

Description: Di dunia nyata, data tidak datang bersih dalam CSV rapi. Modul ini membangun kemampuan mengumpulkan dan membersihkan data sampai layak dianalisis — fondasi yang menentukan kualitas semua modul setelahnya.

Lessons:

* [ ] 3.1 Sumber data nyata: query SQL dasar untuk ekstraksi (rujuk `courses/data-analysis-visualization` Module 2 untuk pola analytics SQL lanjut), REST API, web scraping secukupnya, dataset publik (Kaggle/UCI)
* [ ] 3.2 Data cleaning: strategi menangani missing value (drop vs imputasi — mean/median/model-based), deteksi & penanganan outlier, duplikat, tipe data yang salah
* [ ] 3.3 Data validation & quality checks: mendefinisikan skema/ekspektasi data (range, tipe, uniqueness) sebelum data dipakai lebih jauh
* [ ] 3.4 Menyusun pipeline data yang reproducible: dari notebook eksploratif ke script yang bisa dijalankan ulang

Mini Project: Bangun pipeline pembersihan data untuk 1 dataset nyata yang berantakan, dengan laporan keputusan cleaning (apa yang di-drop/diimputasi dan kenapa).

Status: Not Started

---

## Module 4 — Exploratory Data Analysis (EDA) Mendalam

Description: EDA yang baik menghasilkan arah, bukan sekadar histogram. Modul ini melatih EDA sistematis yang berujung pada hipotesis awal untuk modeling.

Lessons:

* [ ] 4.1 EDA sistematis: analisis univariate, bivariate, dan multivariate secara terstruktur (bukan asal-asalan)
* [ ] 4.2 Correlation analysis & mendeteksi multicollinearity sejak dini
* [ ] 4.3 Visualisasi untuk EDA dengan Matplotlib/Seaborn (ringkas — untuk prinsip visualisasi & storytelling mendalam, rujuk `courses/data-analysis-visualization` Module 5)
* [ ] 4.4 Menyusun EDA report yang actionable: temuan kunci + hipotesis awal yang akan diuji di tahap modeling

Mini Project: EDA report lengkap pada dataset kandidat capstone, ditutup dengan 2-3 hipotesis konkret yang akan diuji lewat modeling.

Status: Not Started

---

## Module 5 — Feature Engineering

Description: "Better features beat better algorithms." Modul ini fokus penuh pada menyiapkan data mentah menjadi fitur yang benar-benar bisa dipakai model.

Lessons:

* [ ] 5.1 Encoding variabel kategorikal (one-hot, ordinal, target encoding) dan scaling/normalisasi variabel numerik
* [ ] 5.2 Feature engineering lanjut: binning, interaction features, ekstraksi fitur dari tanggal/waktu, fitur teks dasar (bag-of-words/TF-IDF secukupnya)
* [ ] 5.3 Feature selection: filter method (korelasi), wrapper method (recursive feature elimination), embedded method (feature importance dari model)
* [ ] 5.4 Menangani data imbalanced: oversampling (SMOTE), undersampling, class weight — dan kapan masing-masing tepat dipakai

Mini Project: Bangun feature engineering pipeline (scikit-learn `Pipeline`/`ColumnTransformer`) yang siap dipakai langsung untuk modeling di Module 6.

Status: Not Started

---

## Module 6 — Classical ML: Regresi & Klasifikasi

Description: Model prediktif pertama yang benar-benar dibangun dan dievaluasi dengan scikit-learn — inti kerja teknis seorang Data Scientist sehari-hari.

Lessons:

* [ ] 6.1 Regresi: linear regression, polynomial regression, regularization (Ridge/Lasso) — kerangka konsep dari ISLR, implementasi dengan scikit-learn
* [ ] 6.2 Klasifikasi: logistic regression, K-Nearest Neighbors, Naive Bayes, pengenalan Support Vector Machine
* [ ] 6.3 Model selection yang benar: train/validation/test split, cross-validation, hyperparameter tuning (grid search/random search) tanpa data leakage
* [ ] 6.4 Evaluation metrics dalam konteks bisnis: precision/recall trade-off, ROC-AUC, kapan accuracy menyesatkan (data imbalanced), memilih metric sesuai biaya kesalahan bisnis

Mini Project: Bangun & tuning model regresi dan klasifikasi pada dataset nyata, pilih metric evaluasi yang selaras dengan konteks bisnisnya (bukan default accuracy).

Status: Not Started

---

## Module 7 — Classical ML: Tree-based, Ensemble & Unsupervised Learning

Description: Kelas algoritma yang paling sering dipakai di industri untuk data tabular, plus kemampuan bekerja dengan data tanpa label.

Lessons:

* [ ] 7.1 Decision tree, Random Forest (bagging), Gradient Boosting (XGBoost/LightGBM) — cara kerja & kenapa sering jadi pilihan default
* [ ] 7.2 Feature importance & interpretability dasar (permutation importance, pengenalan SHAP) — model yang akurat tapi tidak bisa dijelaskan sering ditolak stakeholder
* [ ] 7.3 Clustering (k-means, hierarchical clustering) untuk segmentasi tanpa label
* [ ] 7.4 Dimensionality reduction (PCA) untuk eksplorasi data dimensi tinggi & mengurangi noise

Mini Project: Bandingkan model ensemble vs baseline sederhana pada dataset klasifikasi/regresi, sertakan laporan interpretability; plus 1 latihan customer segmentation dengan clustering.

Status: Not Started

---

## Module 8 — Experimentation & Causal Inference untuk Data Scientist

Description: Kemampuan yang membedakan Data Scientist dari sekadar "orang yang bisa scikit-learn" — merancang eksperimen yang valid dan tahu kapan korelasi bukan sebab-akibat. Modul ini sepenuhnya tidak ada di `courses/ml-fundamentals`.

Lessons:

* [ ] 8.1 Merancang A/B test: merumuskan hipotesis, menentukan metrik utama, sample size & power analysis, randomization yang benar
* [ ] 8.2 Menganalisis A/B test: uji signifikansi, dan jebakan umum (peeking/optional stopping, novelty effect, multiple testing tanpa koreksi)
* [ ] 8.3 Causal inference basics: korelasi vs kausalitas, confounder, pengenalan konsep difference-in-differences & propensity score matching (level kesadaran, bukan implementasi mendalam)
* [ ] 8.4 Dari eksperimen ke rekomendasi: menyusun kesimpulan eksperimen yang jujur soal limitasi dan tidak overclaim

Mini Project: Rancang dan analisis 1 A/B test (dataset publik A/B test atau simulasi terkontrol), tulis kesimpulan lengkap dengan catatan validitas & limitasi statistik.

Status: Not Started

---

## Module 9 — Batas Classical ML: Kapan Butuh Deep Learning & Kesadaran MLOps

Description: Modul penutup teknis — tahu kapan pendekatan course ini tidak lagi cukup, dan kesadaran dasar (bukan kedalaman) soal menjalankan model di production.

Lessons:

* [ ] 9.1 Kapan classical ML cukup vs kapan butuh deep learning (volume data, data tidak terstruktur seperti gambar/teks/audio) — rujuk `courses/ml-fundamentals` Module 8-9 dan `courses/llm` untuk mendalami deep learning/LLM
* [ ] 9.2 Overview deployment model: batch prediction vs real-time serving, menyajikan model sebagai API sederhana (FastAPI) — level "cukup tahu", bukan mendalam
* [ ] 9.3 Kesadaran MLOps-lite: model versioning, monitoring dasar, model/data drift — sekadar peta konsep; ini sengaja **tidak** menggantikan course MLOps penuh kalau suatu saat dibutuhkan
* [ ] 9.4 Production considerations ala Google Rules of ML: reproducibility, menghindari training-serving skew, kenapa model sederhana yang robust sering menang dari model kompleks yang rapuh

Mini Project: Sajikan model terbaik dari Module 6/7 lewat API sederhana (FastAPI), plus tulis checklist monitoring/drift dasar untuk model tersebut.

Status: Not Started

---

## Module 10 — Komunikasi Bisnis & Stakeholder Storytelling untuk Data Scientist

Description: Model yang akurat tapi tidak bisa dijelaskan ke stakeholder adalah model yang tidak dipakai. Modul ini fokus pada menerjemahkan hasil teknis jadi keputusan bisnis.

Lessons:

* [ ] 10.1 Menerjemahkan hasil model jadi bahasa bisnis: dampak/ROI, bukan metrik teknis (AUC, RMSE) yang tidak dipahami stakeholder
* [ ] 10.2 Menyusun laporan hasil data science end-to-end untuk audiens non-teknis (untuk prinsip visualisasi & narrative deck yang lebih dalam, rujuk `courses/data-analysis-visualization` Module 5 & 8)
* [ ] 10.3 Menghadapi pertanyaan sulit stakeholder: "kenapa model salah di kasus ini?", "apakah model ini bisa dipercaya?", menjelaskan uncertainty tanpa jargon

Mini Project: Ubah 1 hasil modeling sebelumnya jadi laporan stakeholder-facing (ringkasan eksekutif + rekomendasi keputusan).

Status: Not Started

---

## Module 11 — Persiapan Interview Data Scientist

Description: Modul penutup — menyatukan seluruh kemampuan teknis course ini menjadi kesiapan menghadapi proses rekrutmen Data Scientist yang nyata.

Lessons:

* [ ] 11.1 Anatomi proses interview Data Scientist: SQL/statistik screen, teori ML & statistik, coding round, open-ended case study, take-home assignment
* [ ] 11.2 Latihan case study & take-home ala *Ace the Data Science Interview*: pendekatan terstruktur untuk problem open-ended (klarifikasi asumsi, framing metrik, trade-off)
* [ ] 11.3 Portfolio review: menyajikan proyek dari course ini (khususnya Capstone) sebagai cerita yang meyakinkan interviewer, bukan sekadar daftar teknis
* [ ] 11.4 Behavioral interview & resume Data Scientist: menjawab pertanyaan "ceritakan proyek data science paling menantang" dengan struktur yang jelas

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Full Data Science Project End-to-End** — pilih 1 dataset nyata/realistis (publik atau data pribadi, mis. e-commerce, churn pelanggan, kesehatan, finansial) dan jalankan siklus data science penuh: (1) problem framing yang terukur (Modul 1), (2) pengumpulan & pembersihan data dengan dokumentasi keputusan cleaning (Modul 3), (3) EDA sistematis dengan hipotesis awal (Modul 4), (4) feature engineering pipeline (Modul 5), (5) minimal 2 model dibandingkan — satu baseline sederhana dan satu model klasik/ensemble yang di-tuning (Modul 6-7), (6) evaluasi dengan metric yang selaras konteks bisnis, plus catatan validitas statistik kalau ada elemen eksperimen (Modul 8), (7) laporan tertulis stakeholder-facing yang menjelaskan hasil, rekomendasi, dan limitasi (Modul 10).

Acceptance Criteria:

* Problem statement jelas dan terukur — bukan sekadar "eksplorasi dataset"
* Pipeline data (cleaning → feature engineering → modeling) reproducible: bisa dijalankan ulang dari awal sampai akhir tanpa langkah manual tersembunyi
* Model terbaik mengalahkan baseline sederhana dengan margin jelas pada test set yang benar-benar terpisah (tidak ada data leakage)
* Metric evaluasi dipilih dan dijustifikasi sesuai konteks bisnis masalah (bukan default accuracy untuk data imbalanced)
* Klaim statistik/model disertai catatan limitasi dan uncertainty — tidak overclaim
* Ada laporan stakeholder-facing terpisah dari notebook teknis: ringkasan eksekutif + rekomendasi yang bisa dipahami pembaca non-teknis dalam < 5 menit baca
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, termasuk notebook/kode, dokumentasi pipeline, dan laporan stakeholder

Status: Not Started

---

# Overall Progress

Module Completion: 0/11

Overall Completion: 0%

Current Module: Roadmap draft — menunggu approval user, belum dimulai (mengikuti kebijakan hemat-token repo, tidak otomatis jadi course aktif)

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Apa itu Data Scientist: beda dengan Data Analyst, ML Engineer, Data Engineer; siklus kerja data science
