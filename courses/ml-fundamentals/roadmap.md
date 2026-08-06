# Course Roadmap

## Course Information

* Course Name: Machine Learning & AI Fundamentals — dari 0 sampai Siap Lanjut ke LLM/Agents
* Category: Machine Learning / Data Science Foundations
* Difficulty: Level 0 (Total pemula ML & matematika AI) → Level 3 (siap lanjut ke `courses/llm` dan `courses/agents`)
* Estimated Duration: 10 modules, ~3-5 lessons per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Programming kuat (Java/Spring Boot dari repo ini). **Tidak** perlu pengalaman ML/AI sebelumnya — Python-for-data-science dan seluruh matematika (aljabar linear, kalkulus, probabilitas & statistik) diajarkan dari nol di dalam course ini.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban Anda:

1. **Jalur yang dipilih**: "Full path" — fondasi Machine Learning/Deep Learning dulu, baru lanjut ke `courses/llm/roadmap.md` (LLM applied engineering) dan `courses/agents/roadmap.md` (AI Agents), yang sudah didraft lebih dulu tapi belum di-approve.
2. **Background**: Programmer Java/Spring Boot berpengalaman, tapi matematika untuk AI (aljabar linear, kalkulus, probabilitas/statistik) benar-benar nol → matematika diajarkan **intuitif & applied** (divisualisasikan, langsung dipraktikkan dengan NumPy/kode), **bukan** pembuktian formal ala buku textbook matematika murni.
3. **Posisi course ini terhadap `courses/llm`**: Course ini adalah "Module 0" konseptual sebelum LLM — course LLM sengaja **melompati** matematika ML dasar dan langsung ke intuisi Transformer (lihat catatan scope di `courses/llm/roadmap.md`). Course ini mengisi fondasi itu: cara kerja neural network dari nol, algoritma ML klasik, dan proses training (loss, gradient descent) — supaya saat masuk ke Module 1-2 LLM course, konsep "next-token prediction" dan "attention" tidak terasa seperti sihir. Module 1 LLM course (Python-for-AI dasar) akan terasa seperti *refresher* singkat, bukan materi baru.
4. **Tidak** mencakup: riset akademis ML (paper-heavy), matematika pembuktian formal, atau big data engineering (Spark/Hadoop) — course ini fokus fondasi konsep + kemampuan membangun model ML/DL yang benar dan bisa dievaluasi.

Referensi riset yang dipakai menyusun roadmap ini: [roadmap.sh/machine-learning](https://roadmap.sh/machine-learning), [roadmap.sh/ai-data-scientist](https://roadmap.sh/ai-data-scientist), struktur *Andrew Ng — Machine Learning Specialization* (Coursera, urutan: regresi → klasifikasi → neural network → praktik ML production), buku *Mathematics for Machine Learning* (Deisenroth, Faisal, Ong — pendekatan matematika applied untuk ML), dan *Hands-On Machine Learning with Scikit-Learn, Keras & TensorFlow* (Aurélien Géron — urutan scikit-learn dulu baru deep learning).

---

# Learning Objectives

* [ ] Paham peta besar bidang AI: hubungan AI, Machine Learning, Deep Learning, dan LLM — serta di mana masing-masing dipakai di industri (Netflix, Uber, Stripe, Google)
* [ ] Mahir Python untuk data science: NumPy, Pandas, Matplotlib — cukup untuk eksplorasi data dan membangun model
* [ ] Punya intuisi kerja aljabar linear, kalkulus, dan probabilitas/statistik yang dipakai ML — tanpa perlu pembuktian formal, tapi cukup dalam untuk debugging model
* [ ] Mampu membangun, mengevaluasi, dan men-tuning model supervised learning (regresi, klasifikasi, tree-based, ensemble) dengan scikit-learn
* [ ] Mampu menerapkan unsupervised learning (clustering, dimensionality reduction) untuk eksplorasi data tanpa label
* [ ] Paham cara kerja neural network dari nol (forward pass, backpropagation, gradient descent) dengan mengimplementasikannya sendiri
* [ ] Mahir deep learning dasar dengan PyTorch: CNN untuk gambar, RNN/LSTM untuk sequence data
* [ ] Mampu menjalankan siklus proyek ML end-to-end: problem framing → data → model → evaluasi → deployment dasar
* [ ] Siap melanjutkan ke `courses/llm/roadmap.md` dan `courses/agents/roadmap.md` tanpa merasa ada lompatan konsep

---

# Course Modules

## Module 1 — Peta Besar AI & Python untuk Data Science

Description: Sebelum masuk detail, pahami dulu di mana Anda berada di peta besar AI (AI vs ML vs DL vs LLM, use case nyata di industri), lalu siapkan tooling Python khusus untuk data science (bukan Python umum).

Lessons:

* [ ] AI vs Machine Learning vs Deep Learning vs LLM — hierarki dan hubungan, studi kasus industri (Netflix recsys, Uber ETA/fraud, Stripe fraud detection, Google Search ranking)
* [ ] Setup environment data science (Python venv, Jupyter/VS Code, `uv`/`pip`, virtual environment hygiene)
* [ ] NumPy essentials: array, indexing, broadcasting, operasi vektor/matriks
* [ ] Pandas essentials: DataFrame, filtering, groupby, handling missing data
* [ ] Matplotlib/Seaborn dasar: visualisasi data untuk eksplorasi

Mini Project: Exploratory Data Analysis (EDA) lengkap pada dataset nyata (mis. Titanic atau housing prices) — load, bersihkan, visualisasikan, temukan insight awal.

Status: In Progress (Lesson 1.1)

---

## Module 2 — Matematika ML Part 1: Aljabar Linear Terapan

Description: Aljabar linear adalah bahasa data dalam ML (data = matriks, model = transformasi vektor). Diajarkan visual & langsung dipraktikkan dengan NumPy, bukan pembuktian teorema.

Lessons:

* [ ] Vektor & matriks: representasi data sebagai matriks, kenapa ML "berpikir" dalam bentuk ini
* [ ] Operasi matriks: dot product, perkalian matriks, transpose — intuisi geometris (bukan hanya mekanis)
* [ ] Norm & jarak (Euclidean, cosine similarity) — dasar untuk banyak algoritma ML (KNN, clustering, embedding)
* [ ] Intuisi eigenvalue/eigenvector (visual, tanpa derivasi) — fondasi untuk PCA di Module 7

Mini Project: Implementasi operasi vektor/matriks dari nol dengan NumPy + visualisasi 2D (mis. transformasi vektor, proyeksi, similarity antar titik data).

Status: Not Started

---

## Module 3 — Matematika ML Part 2: Kalkulus & Optimisasi

Description: Inti dari "belajar" di machine learning adalah optimisasi — mencari parameter yang meminimalkan error. Modul ini membangun intuisi turunan/gradient sampai bisa mengimplementasikan gradient descent sendiri.

Lessons:

* [ ] Turunan & gradient — intuisi "kemiringan" dan "arah perubahan tercepat" (visual, bukan pembuktian limit)
* [ ] Chain rule — kenapa penting (fondasi backpropagation di Module 8)
* [ ] Loss function — apa artinya "model belajar" secara matematis (MSE, cross-entropy secara intuitif)
* [ ] Gradient descent: batch, stochastic, mini-batch — cara kerja & trade-off

Mini Project: Implementasi gradient descent dari nol (tanpa library ML) untuk fit garis linear ke data, visualisasikan proses konvergensinya step-by-step.

Status: Not Started

---

## Module 4 — Matematika ML Part 3: Probabilitas & Statistik

Description: ML pada dasarnya adalah membuat keputusan di bawah ketidakpastian. Modul ini membangun intuisi probabilitas & statistik yang dipakai untuk evaluasi model dan pengambilan keputusan berbasis data.

Lessons:

* [ ] Probabilitas dasar: distribusi, mean, variance, standard deviation — makna praktisnya untuk data
* [ ] Conditional probability & Bayes' theorem — intuisi + contoh applied (spam filter sederhana)
* [ ] Distribusi umum (normal, binomial) — kenapa muncul di mana-mana dalam data nyata
* [ ] Statistical inference dasar: sampling, confidence interval, hypothesis testing — kenapa penting untuk validasi hasil eksperimen ML

Mini Project: Analisis statistik pada dataset nyata — hitung distribusi, uji hipotesis sederhana (mis. "apakah fitur A berkorelasi signifikan dengan target?").

Status: Not Started

---

## Module 5 — Supervised Learning: Regresi & Klasifikasi

Description: Algoritma ML pertama yang benar-benar dibangun dan dievaluasi — menyatukan aljabar linear (Module 2), gradient descent (Module 3), dan statistik (Module 4) menjadi model yang bekerja.

Lessons:

* [ ] Linear regression — dari matematika (Module 2-3) ke implementasi dengan scikit-learn
* [ ] Logistic regression untuk klasifikasi — decision boundary, sigmoid
* [ ] Train/test split, overfitting vs underfitting, bias-variance trade-off
* [ ] Regularization: L1 (Lasso) vs L2 (Ridge) — kapan dan kenapa
* [ ] Evaluation metrics: MSE/RMSE untuk regresi; accuracy, precision, recall, F1, ROC-AUC untuk klasifikasi

Mini Project: Bangun model regresi (prediksi harga) dan klasifikasi (prediksi kategori) pada dataset nyata dengan scikit-learn, evaluasi dengan metric yang tepat.

Status: Not Started

---

## Module 6 — Supervised Learning: Tree-based & Ensemble Methods

Description: Kelas algoritma yang paling sering menang di kompetisi ML tabular data (Kaggle) dan banyak dipakai production — dari decision tree tunggal sampai ensemble yang powerful.

Lessons:

* [ ] Decision tree: cara kerja splitting, entropy/Gini impurity
* [ ] Random forest — bagging & kenapa "banyak model lemah" bisa jadi kuat
* [ ] Gradient boosting (XGBoost/LightGBM) — konsep boosting, kenapa sering jadi pilihan default di industri
* [ ] Feature engineering & feature importance
* [ ] Hyperparameter tuning: grid search, random search, cross-validation

Mini Project: Kompetisi-style project (dataset Kaggle) — bangun & tuning model ensemble, bandingkan performa random forest vs gradient boosting.

Status: Not Started

---

## Module 7 — Unsupervised Learning & Dimensionality Reduction

Description: Bekerja dengan data tanpa label — menemukan struktur tersembunyi, mengurangi dimensi data untuk visualisasi dan efisiensi.

Lessons:

* [ ] Clustering: k-means (cara kerja, cara memilih k), hierarchical clustering
* [ ] Dimensionality reduction: PCA — menyambung kembali ke intuisi eigenvector (Module 2)
* [ ] Visualisasi data dimensi tinggi: t-SNE/UMAP
* [ ] Anomaly detection dasar (kapan dipakai: fraud detection, monitoring)

Mini Project: Customer segmentation — cluster data pelanggan tanpa label, visualisasikan hasil clustering dengan PCA/t-SNE, interpretasikan segmen yang ditemukan.

Status: Not Started

---

## Module 8 — Neural Network dari Nol

Description: Membuka "kotak hitam" neural network dengan mengimplementasikannya sendiri — inilah titik di mana semua matematika Module 2-3 (aljabar linear, chain rule, gradient descent) menyatu.

Lessons:

* [ ] Perceptron & activation function (ReLU, sigmoid, softmax) — kenapa non-linearity penting
* [ ] Forward pass: bagaimana input menjadi prediksi lewat layer-layer
* [ ] Backpropagation — chain rule (Module 3) diterapkan untuk menghitung gradient tiap layer
* [ ] Training loop lengkap: forward → loss → backward → update weight

Mini Project: Implementasi neural network dari nol (pure Python + NumPy, tanpa framework) untuk klasifikasi digit tulisan tangan (subset MNIST) — verifikasi manual bahwa backprop bekerja benar.

Status: Not Started

---

## Module 9 — Deep Learning dengan PyTorch

Description: Setelah paham cara kerja internal neural network (Module 8), sekarang pakai framework production-grade untuk membangun model yang lebih kompleks secara efisien.

Lessons:

* [ ] PyTorch fundamentals: tensor, autograd, `nn.Module`, training loop idiomatis
* [ ] CNN (Convolutional Neural Network): convolution & pooling — intuisi untuk data gambar
* [ ] RNN/LSTM: cara kerja untuk data sequence — ini adalah jembatan konsep menuju arsitektur Transformer di `courses/llm/roadmap.md` Module 2
* [ ] GPU training dasar, kenapa deep learning butuh hardware khusus

Mini Project: Bangun & latih image classifier (CNN) dengan PyTorch pada dataset nyata (mis. CIFAR-10), ukur akurasi dan visualisasikan kesalahan model.

Status: Not Started

---

## Module 10 — Capstone: Proyek ML End-to-End & Jembatan ke LLM/Agents

Description: Menyatukan seluruh course menjadi siklus proyek ML nyata dari data mentah sampai model yang bisa diakses lewat API — sekaligus titik transisi eksplisit menuju `courses/llm/roadmap.md`.

Lessons:

* [ ] Siklus proyek ML: problem framing, data collection, model selection, evaluation, iterasi
* [ ] Deployment model dasar: serving model lewat API sederhana (FastAPI/Flask)
* [ ] MLOps overview (tidak dipraktikkan mendalam): model versioning, monitoring, data drift — sekadar peta konsep
* [ ] Rekap & jembatan: bagaimana neural network (Module 8-9) berkembang menjadi Transformer/attention — kenapa `courses/llm/roadmap.md` Module 1-2 akan terasa seperti kelanjutan alami, bukan topik baru

Portfolio Project: Lihat "Enterprise Project" di bawah.

**Production Discussion: Spring Boot Integration** — pola umum men-serve model Python (FastAPI) sebagai internal service yang dipanggil dari backend Spring Boot (REST client, timeout budget), sama seperti pola yang akan dipakai lebih detail di `courses/llm/roadmap.md` Module 4 & 9.

Status: Not Started

---

# Enterprise Project

Description: **Full ML Project Simulation** — pilih 1 masalah nyata (mis. prediksi churn pelanggan, deteksi fraud, atau klasifikasi gambar) dan selesaikan end-to-end: (1) EDA & data cleaning, (2) minimal 2 model dibandingkan (satu classical ML dari Module 5/6, satu deep learning dari Module 8/9), (3) evaluasi rigorous dengan metric yang tepat, (4) deployment dasar sebagai API (FastAPI/Flask + Docker), (5) 1 halaman "Model Card" yang menjelaskan trade-off, limitasi, dan asumsi model.

Acceptance Criteria:

* Model terbaik mengalahkan baseline sederhana (mis. random/majority-class prediction) dengan margin jelas pada test set yang benar-benar terpisah
* Evaluasi memakai metric yang sesuai jenis masalah (bukan hanya accuracy untuk data imbalanced)
* Ada perbandingan eksplisit antara classical ML dan deep learning, dengan alasan model mana yang dipilih untuk production
* API model berjalan via Docker dan bisa menerima request nyata
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Module 1 — Peta Besar AI & Python untuk Data Science (In Progress)

Current Lesson: 1.1 — AI vs Machine Learning vs Deep Learning vs LLM

Next Lesson: Module 1, Lesson 1.2 — Setup environment data science (Python venv, Jupyter/VS Code, uv/pip)
