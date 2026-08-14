# Course Roadmap

## Course Information

* Course Name: Neural Network — dari 0 sampai Expert (Mathematical & From-Scratch Track)
* Category: Deep Learning / Neural Network Engineering
* Difficulty: Level 0 (paham programming, belum tentu paham matematika neural network secara mendalam) → Level 4 (Expert — mampu menurunkan matematika dan mengimplementasikan dari nol semua arsitektur utama: MLP, CNN, RNN/LSTM, Transformer)
* Estimated Duration: 12 modules, ~4-5 lessons per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Programming Python solid. **Disarankan** (bukan wajib) sudah menyelesaikan `courses/ml-fundamentals/roadmap.md` Module 1 (Python untuk data science: NumPy/Pandas) untuk tooling, dan Module 2-4 (aljabar linear, kalkulus, probabilitas — versi intuitif) sebagai pemanasan. **Tidak wajib** karena course ini menurunkan ulang seluruh matematika yang dibutuhkan secara formal dari nol. **Tidak** perlu pengalaman ML/deep learning sebelumnya.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini dibuat sebagai pendalaman khusus topik "Neural Network" yang **secara sengaja** mengambil sikap berbeda dari dua course yang sudah ada di repo ini (`courses/ml-fundamentals` dan `courses/llm`), supaya tidak duplikat. Berikut posisinya:

1. **Sikap yang diambil — pure math derivation + full from-scratch implementation.** Course ini **bukan** treatment intuitif/applied. Setiap konsep inti (gradient, chain rule, backpropagation, convolution, attention) diturunkan secara matematis penuh (derivasi, bukan hanya ilustrasi visual), lalu **diimplementasikan dari nol dengan NumPy sebelum** memakai framework (PyTorch). Ini adalah sikap eksplisit — kalau nanti terasa terlalu berat/lambat, opsi "turunkan ke versi lebih intuitif" bisa didiskusikan ulang sebelum approval final.

2. **Perbedaan vs `courses/ml-fundamentals/roadmap.md`:**
   * Module 2-4 course itu ("Matematika ML Part 1-3") mengajarkan aljabar linear/kalkulus/probabilitas secara **intuitif & visual, eksplisit "bukan pembuktian teorema"** — dipakai luas untuk semua algoritma ML klasik (regresi, tree, clustering), bukan spesifik neural network.
   * Module 8 course itu ("Neural Network dari Nol") adalah **1 modul tunggal** (perceptron → forward pass → backprop → training loop) untuk MNIST subset, sebagai bagian dari peta besar ML, dengan kedalaman secukupnya untuk "paham cara kerja", bukan derivasi matematis penuh.
   * Module 9 course itu menyentuh CNN & RNN/LSTM **hanya lewat PyTorch** (tidak from-scratch), sebagai pengenalan.
   * Course baru ini **mengganti kedalaman "secukupnya" itu menjadi kedalaman penuh**: setiap arsitektur (MLP, CNN, RNN/LSTM, Transformer) diturunkan matematis dan diimplementasikan from-scratch, bukan hanya dipakai lewat `sklearn`/PyTorch. Course ini bisa dipandang sebagai versi "expanded & rigorous" dari Module 8-9 `ml-fundamentals`, cocok untuk yang sudah lewat course itu dan ingin bukti matematis penuh, atau bagi yang ingin loncat langsung ke kedalaman ini tanpa treatment intuitif dulu.

3. **Perbedaan vs `courses/llm/roadmap.md`:**
   * Module 1-2 course itu eksplisit menyatakan Transformer/attention diajarkan sebagai **"intuisi visual"** dan **"conceptual walkthrough, bukan implementasi from scratch"** — sengaja melompati matematika karena target course itu adalah Applied/Product AI Engineer (memakai LLM via API, bukan membangun model).
   * Module 10 course baru ini justru mengisi lompatan itu: menurunkan self-attention, multi-head attention, dan positional encoding secara matematis, lalu mengimplementasikan Transformer block dari nol.
   * **Relasi prasyarat/lanjutan**: course ini **tidak menggantikan** `courses/llm` — course itu tetap jalur untuk applied AI engineering (prompt engineering, RAG, agents, fine-tuning, production). Course ini adalah pendalaman *di bawah* itu: setelah menyelesaikan Module 10 di sini, Module 1-2 `courses/llm` akan terasa seperti refresher trivial, dan pembaca akan paham *kenapa* attention bekerja, bukan hanya *bahwa* attention bekerja. Direkomendasikan sebagai **companion/prasyarat opsional** untuk siapa pun yang mengambil `courses/llm` dan ingin fondasi lebih dalam sebelum atau paralel dengan course itu — bukan urutan wajib.

4. **Tidak termasuk dalam scope** (supaya jelas batasnya): algoritma ML non-neural (regresi, decision tree, clustering — itu `courses/ml-fundamentals` Module 5-7), prompt engineering/RAG/agents/fine-tuning model LLM pretrained (itu `courses/llm`), riset pretraining skala besar & distributed training infrastructure (disinggung sekilas sebagai konteks di Module 12, tidak dipraktikkan), serta MLOps/deployment skala production mendalam (pointer ke `courses/ml-fundamentals` Module 11 draft & `courses/cloud-devops-foundations`, tidak diulang di sini).

5. **Stack & pedagogi implementasi**: NumPy from-scratch dulu untuk **setiap** arsitektur (mengikuti pola Andrej Karpathy — micrograd → makemore → nanoGPT, dan pola assignment CS231n — implementasikan layer manual sebelum pakai framework), baru kemudian PyTorch idiomatis (Module 11) untuk versi production-grade. Karena `standards/` repo ini fokus Java/Spring Boot, modul-modul yang relevan tetap bisa menambahkan sub-bagian singkat **"Production Discussion"** yang mengarah balik ke pola integrasi Python-service-dipanggil-dari-Spring-Boot yang sudah dibahas detail di `courses/llm/roadmap.md` Module 4 & 9 — tidak diulang penuh di sini untuk menghindari duplikasi.

Referensi riset yang dipakai menyusun roadmap ini: [3Blue1Brown — Neural Networks series](https://www.3blue1brown.com/?topic=neural-networks) (intuisi visual gradient descent & backpropagation, dipakai sebagai jembatan sebelum derivasi formal), [DeepLearning.AI — Deep Learning Specialization (Andrew Ng)](https://www.deeplearning.ai/specializations/deep-learning) (struktur 5-course: neural network dasar → hyperparameter/regularization/optimization → CNN → sequence models, dipakai sebagai kerangka urutan modul), [Stanford CS231n — Convolutional Neural Networks for Visual Recognition](https://cs231n.github.io/) (pola assignment "implementasikan layer manual dulu, baru framework", dipakai untuk struktur Module 8), buku [*Deep Learning* — Goodfellow, Bengio, Courville](https://www.deeplearningbook.org/) (referensi matematis utama: Part I aljabar linear/probabilitas, Part II feedforward network/regularization/optimization/CNN/RNN, dipakai sebagai kerangka kedalaman derivasi), [PyTorch Official Tutorials](https://docs.pytorch.org/tutorials/) (tensor, autograd, `nn.Module`, `nn.Transformer`, dipakai untuk Module 11), dan sebagai tambahan yang sangat relevan dengan penekanan "from-scratch" course ini: [Andrej Karpathy — "Neural Networks: Zero to Hero"](https://karpathy.ai/zero-to-hero.html) (micrograd: autograd engine dari nol; makemore: MLP/backprop manual; nanoGPT: Transformer dari nol — pola pedagogi yang paling dekat dengan tujuan course ini, dipakai sebagai referensi struktur Module 5, 10, dan Enterprise Project).

---

# Learning Objectives

* [ ] Paham & mampu menurunkan matematika inti neural network (aljabar linear, turunan, chain rule multivariable) secara formal, bukan hanya intuitif
* [ ] Paham evolusi dari neuron/perceptron tunggal ke multi-layer perceptron, termasuk kenapa perceptron tunggal punya limitasi mendasar
* [ ] Mampu menurunkan dan mengimplementasikan forward pass untuk network dengan banyak hidden layer dari nol
* [ ] Paham loss function dan mampu menurunkan gradient descent dari first principles
* [ ] Mampu menurunkan backpropagation secara manual (chain rule step-by-step) dan mengimplementasikannya dari nol tanpa autograd, diverifikasi dengan gradient checking
* [ ] Paham cara kerja & mampu mengimplementasikan optimizer modern (Momentum, RMSProp, Adam) dari nol beserta derivasinya
* [ ] Mampu menerapkan dan menurunkan teknik regularization (L1/L2, dropout, batch normalization) dari nol
* [ ] Mampu menurunkan dan mengimplementasikan CNN dari nol (termasuk backprop convolution), lalu membangunnya dengan PyTorch
* [ ] Mampu menurunkan dan mengimplementasikan RNN/LSTM dari nol (termasuk backpropagation through time)
* [ ] Mampu menurunkan dan mengimplementasikan arsitektur Transformer dari nol (self-attention, multi-head attention, positional encoding)
* [ ] Mahir PyTorch idiomatis (tensor, autograd, `nn.Module`) untuk mereimplementasikan seluruh arsitektur di atas secara production-grade
* [ ] Mampu men-debug masalah training neural network di praktik (loss tidak turun, NaN, exploding/vanishing gradient) dan siap melanjutkan ke `courses/llm` dengan fondasi matematis penuh

---

# Course Modules

## Module 1 — Fondasi Matematika untuk Neural Network

Description: Menurunkan ulang aljabar linear dan kalkulus yang dibutuhkan neural network secara formal — bukan intuisi visual seperti `courses/ml-fundamentals` Module 2-3, tapi derivasi yang akan dipakai langsung untuk membuktikan backpropagation di Module 5.

Lessons:

* [ ] Scalar, vector, matrix, tensor — notasi formal & operasi dasar (rujuk balik intuisi `courses/ml-fundamentals` Module 2, kali ini dengan pembuktian)
* [ ] Dot product & perkalian matriks sebagai transformasi linear — kenapa neural network = rangkaian transformasi linear + non-linear
* [ ] Turunan, partial derivative, dan gradient vector — notasi dan definisi formal
* [ ] Chain rule multivariable — fondasi matematis backpropagation, diturunkan langkah demi langkah
* [ ] Jacobian & gradient matriks secukupnya untuk backprop multi-layer

Mini Project: Implementasi operasi vector/matrix dari nol dengan NumPy, plus numerical gradient checker (turunan numerik via finite difference) untuk memverifikasi turunan analitik pada beberapa fungsi sederhana.

Status: Not Started

---

## Module 2 — Neuron, Perceptron & Model Linear

Description: Titik awal historis dan konseptual neural network — dari neuron biologis ke perceptron matematis, termasuk kenapa arsitektur ini sempat dianggap jalan buntu (AI winter) sebelum multi-layer network ditemukan solusinya.

Lessons:

* [ ] Analogi neuron biologis ke artificial neuron; sejarah singkat Perceptron (Rosenblatt) dan masalah XOR
* [ ] Perceptron: weighted sum + bias + step function, perceptron learning rule
* [ ] Limitasi perceptron tunggal (hanya linearly separable) — kenapa dibutuhkan multi-layer
* [ ] Hubungan single neuron dengan logistic regression (`courses/ml-fundamentals` Module 5) — neuron sebagai generalisasi

Mini Project: Implementasi perceptron tunggal dari nol untuk klasifikasi biner pada dataset linearly-separable, visualisasikan decision boundary dan proses belajarnya.

Status: Not Started

---

## Module 3 — Forward Pass & Activation Functions

Description: Menyusun neuron menjadi network berlapis dan menurunkan bagaimana input mengalir menjadi prediksi — fondasi yang dipakai ulang di setiap modul arsitektur berikutnya.

Lessons:

* [ ] Arsitektur Multi-Layer Perceptron (MLP): layer, neuron, weight matrix, bias vector
* [ ] Forward propagation step-by-step dalam notasi matriks, layer demi layer
* [ ] Activation function: sigmoid, tanh, ReLU, Leaky ReLU, softmax — derivasi kenapa non-linearity wajib (pembuktian: stacking linear layer tanpa activation = 1 linear layer)
* [ ] Pengenalan masalah vanishing/exploding activation (dibahas tuntas dengan optimizer di Module 6)

Mini Project: Implementasi forward pass MLP dari nol (NumPy) dengan beberapa hidden layer, bandingkan efek pemilihan activation function berbeda terhadap distribusi output.

Status: Not Started

---

## Module 4 — Loss Function & Gradient Descent

Description: Mendefinisikan apa artinya "network belajar" secara matematis, dan menurunkan algoritma optimisasi paling dasar dari first principles.

Lessons:

* [ ] Loss function: Mean Squared Error, Cross-Entropy — derivasi matematis dan alasan pemilihan per jenis task
* [ ] Gradient descent: derivasi update rule dari first principles
* [ ] Batch, stochastic, dan mini-batch gradient descent — trade-off matematis & praktis
* [ ] Learning rate — efek terlalu besar/kecil, visualisasi loss landscape

Mini Project: Implementasi gradient descent dari nol untuk fit model linear dan logistic, visualisasikan proses konvergensi pada loss landscape 2D/3D.

Status: Not Started

---

## Module 5 — Backpropagation: Derivasi Manual Penuh

Description: Modul paling penting di course ini — menurunkan backpropagation secara matematis penuh dan mengimplementasikannya dari nol tanpa bantuan autograd framework apa pun.

Lessons:

* [ ] Computational graph — forward pass dan backward pass sebagai graph traversal
* [ ] Derivasi backprop untuk single neuron: turunan loss terhadap tiap weight, step-by-step chain rule
* [ ] Generalisasi derivasi ke multi-layer network (bentuk matriks, delta rule per layer)
* [ ] Implementasi backprop dari nol (pure NumPy, tanpa autograd) untuk network 2-3 layer
* [ ] Gradient checking (numerical vs analytical) sebagai teknik verifikasi wajib sebelum mempercayai implementasi

Mini Project: Implementasi neural network 2-3 layer lengkap dari nol (forward + backward + weight update) untuk klasifikasi subset MNIST, diverifikasi dengan gradient checking hingga selisih numerik sangat kecil.

Status: Not Started

---

## Module 6 — Optimizer & Teknik Training Lanjutan

Description: Vanilla gradient descent sering gagal konvergen dengan baik di network dalam — modul ini menurunkan optimizer modern yang mengatasi masalah tersebut.

Lessons:

* [ ] Masalah vanilla gradient descent: local minima, saddle point, vanishing/exploding gradient (revisit dari Module 3)
* [ ] Momentum & Nesterov momentum — derivasi dan intuisi fisik
* [ ] Adaptive learning rate: AdaGrad, RMSProp — derivasi
* [ ] Adam optimizer — derivasi lengkap (first/second moment, bias correction)
* [ ] Learning rate scheduling & warmup; weight initialization (Xavier/Glorot, He) — kenapa penting

Mini Project: Implementasi SGD+Momentum, RMSProp, dan Adam dari nol, bandingkan kecepatan & stabilitas konvergensi pada network dari Module 5.

Status: Not Started

---

## Module 7 — Regularization & Generalization

Description: Network berkapasitas besar mudah overfitting — modul ini menurunkan teknik regularization yang membuat network generalize dengan baik ke data baru.

Lessons:

* [ ] Overfitting vs underfitting dalam konteks neural network berkapasitas besar (revisit bias-variance dari `courses/ml-fundamentals` Module 5)
* [ ] L1/L2 regularization (weight decay) — derivasi efeknya pada gradient update
* [ ] Dropout — cara kerja, kenapa efektif, implementasi dari nol
* [ ] Batch Normalization & Layer Normalization — derivasi forward/backward, kenapa mempercepat & menstabilkan training
* [ ] Early stopping & data augmentation dasar

Mini Project: Implementasi dropout dan batch normalization dari nol, ukur efeknya terhadap overfitting pada network Module 5-6 lewat kurva train/validation loss.

Status: Not Started

---

## Module 8 — Convolutional Neural Network (CNN) dari Nol

Description: Arsitektur yang mengeksploitasi struktur spasial data (gambar) lewat parameter sharing — diturunkan dan diimplementasikan penuh dari nol, bukan hanya dipakai lewat PyTorch seperti `courses/ml-fundamentals` Module 9.

Lessons:

* [ ] Kenapa fully-connected layer tidak efisien untuk gambar — parameter sharing & local connectivity
* [ ] Convolution operation — derivasi matematis, stride, padding, kernel/filter
* [ ] Pooling layer (max/average) dan alasannya
* [ ] Forward & backward pass CNN dari nol — derivasi backprop convolution (bagian tersulit di modul ini)
* [ ] Arsitektur CNN klasik (LeNet, AlexNet, VGG) — overview sejarah dan filosofi desain

Mini Project: Implementasi convolution layer beserta backprop-nya dari nol (NumPy), lalu bangun CNN klasifikasi gambar sederhana dengan PyTorch untuk perbandingan kecepatan & performa.

Status: Not Started

---

## Module 9 — Recurrent Neural Network (RNN) & LSTM

Description: Arsitektur untuk data sequence — diturunkan dan diimplementasikan dari nol, termasuk backpropagation through time yang tidak dibahas mendalam di `courses/ml-fundamentals` Module 9.

Lessons:

* [ ] Kenapa data sequence butuh arsitektur berbeda — konsep hidden state/memory
* [ ] Vanilla RNN — forward pass dan derivasi Backpropagation Through Time (BPTT)
* [ ] Masalah vanishing/exploding gradient pada RNN dengan sequence panjang
* [ ] LSTM — gate mechanism (forget, input, output gate), derivasi kenapa mengatasi vanishing gradient
* [ ] GRU sebagai varian yang lebih sederhana (overview perbandingan)

Mini Project: Implementasi vanilla RNN dari nol (NumPy) untuk character-level text generation sederhana, lalu bangun LSTM dengan PyTorch untuk task sequence yang lebih kompleks.

Status: Not Started

---

## Module 10 — Transformer Architecture: Derivasi & Implementasi dari Nol

Description: Mengisi bagian yang secara eksplisit dilewati `courses/llm/roadmap.md` Module 2 ("conceptual walkthrough, bukan implementasi from scratch") — menurunkan self-attention secara matematis penuh dan mengimplementasikan Transformer block dari nol.

Lessons:

* [ ] Kenapa attention menggantikan recurrence — paralelisasi & long-range dependency (jembatan eksplisit ke `courses/llm` Module 2)
* [ ] Self-attention — derivasi matematis penuh (Query, Key, Value, scaled dot-product attention)
* [ ] Multi-head attention — alasan multiple head, implementasi dari nol
* [ ] Positional encoding — kenapa dibutuhkan, derivasi sinusoidal positional encoding
* [ ] Transformer block lengkap: attention + feedforward + residual connection + layer normalization; perbedaan encoder vs decoder architecture

Mini Project: Implementasi Transformer block dari nol (NumPy/PyTorch murni, tanpa `nn.Transformer`) untuk task character-level next-token prediction sederhana (gaya Karpathy's nanoGPT/makemore), verifikasi kebenarannya terhadap `nn.Transformer` PyTorch sebagai pembanding.

Status: Not Started

---

## Module 11 — Dari NumPy ke PyTorch: Implementasi Production-Grade

Description: Setelah memahami dan membuktikan setiap mekanisme secara manual (Module 1-10), modul ini beralih ke PyTorch idiomatis — memahami bahwa autograd framework hanya mengotomatiskan apa yang sudah diturunkan dan diimplementasikan manual sejauh ini.

Lessons:

* [ ] PyTorch fundamentals: Tensor, autograd — menghubungkan mekanisme autograd dengan derivasi manual Module 5
* [ ] Membangun ulang seluruh arsitektur (MLP, CNN, RNN/LSTM, Transformer) dengan `nn.Module` idiomatis
* [ ] Custom autograd `Function` & teknik debugging gradient di PyTorch
* [ ] GPU training, mixed precision, dan profiling dasar

Mini Project: Reimplementasi network dari Module 5, 8, 9, dan 10 dengan PyTorch idiomatis; bandingkan kecepatan development, performa training, dan keterbacaan kode vs implementasi NumPy manual.

Status: Not Started

---

## Module 12 — Capstone: Sintesis & Jembatan ke LLM Engineering

Description: Menyatukan seluruh course menjadi satu peta mental utuh — dari neuron tunggal sampai Transformer — sekaligus titik transisi eksplisit menuju `courses/llm/roadmap.md`.

Lessons:

* [ ] Rekap end-to-end: dari perceptron (Module 2) sampai Transformer (Module 10) — peta mental lengkap arsitektur neural network
* [ ] Studi kasus ringkas: bagaimana Google, OpenAI, Meta mendesain arsitektur neural network production (pointer ke `courses/llm` Module 9-10 untuk detail production yang tidak diulang di sini)
* [ ] Debugging neural network di praktik: loss tidak turun, NaN, gradient exploding — checklist troubleshooting production
* [ ] Jembatan eksplisit ke `courses/llm`: setelah course ini, Module 1-2 course itu akan terasa seperti refresher; opsi lanjutan eksplorasi arsitektur LLM modern (RoPE, Grouped Query Attention, Mixture of Experts) sebagai extension opsional di luar scope course ini

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **"Neural Network Library from Scratch"** — bangun mini-framework deep learning sendiri (mirip skala kecil micrograd/PyTorch): autograd engine sendiri (scalar/tensor-based, hasil dari Module 1 & 5), abstraksi layer (Linear, Conv2D, RNN/LSTM cell, MultiHeadAttention — hasil Module 5, 8, 9, 10), optimizer (SGD, Momentum, Adam — hasil Module 6), dan loss function. Gunakan library buatan sendiri untuk melatih 2 model nyata: (1) MNIST classifier, dan (2) character-level Transformer language model kecil (gaya nanoGPT). Bandingkan hasilnya dengan implementasi PyTorch dari Module 11, dan dokumentasikan derivasi matematis yang mendasari setiap komponen.

Acceptance Criteria:

* Autograd engine sendiri menghasilkan gradient yang sama (dalam toleransi numerik gradient checking) dengan PyTorch autograd untuk minimal 5 operasi berbeda
* Library berhasil melatih MNIST classifier dengan akurasi kompetitif (>95%) tanpa memakai PyTorch/TensorFlow untuk bagian training
* Character-level Transformer kecil buatan sendiri berhasil menghasilkan teks yang koheren secara lokal (bukan random/garbage)
* Ada perbandingan eksplisit dan terukur: waktu training, akurasi, dan kemudahan development antara library buatan sendiri vs PyTorch
* Dokumentasi derivasi matematis untuk setiap komponen utama (linear layer, convolution, attention) disertakan, bukan hanya kode
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Scalar, vector, matrix, tensor: notasi formal & operasi dasar
