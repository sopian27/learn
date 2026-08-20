# Course Roadmap

## Course Information

* Course Name: Natural Language & NLP — dari 0 sampai Expert
* Category: Computational Linguistics / Natural Language Processing
* Difficulty: Level 0 (belum pernah linguistik/NLP) → Level 4 (Expert, mampu merancang sistem NLP end-to-end termasuk RAG & agent)
* Estimated Duration: 12 modules + Capstone Project, ~3-5 lessons per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Programming kuat (Java/Spring Boot dari repo ini) + Python dasar. **Tidak** wajib sudah menyelesaikan `courses/ml-fundamentals` atau `courses/llm`, tapi Module 8-11 course ini akan lebih mulus kalau sudah paham neural network dasar (`courses/ml-fundamentals` Module 8) — bila belum, Module 8 course ini menyediakan intuisi secukupnya untuk lanjut.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini didraft atas permintaan langsung: "Natural Language & NLP 0–expert: linguistics, morphology, syntax, semantics, pragmatics, tokenization, embeddings, transformers, attention, LLM, RAG, agents, dan NLP project."

**Overlap-check terhadap course existing (wajib sebelum draft, per pola course lain di repo ini):**

* `courses/llm/roadmap.md` (10 modul) sudah membahas tokenization, embedding, attention/Transformer (Module 2), prompt engineering, RAG (Module 5), agents (Module 7), evaluation/safety (Module 8), production (Module 9) — **tapi dari sudut pandang Applied AI/Product Engineer**: cara *memakai* LLM lewat API, bukan cara kerja bahasa itu sendiri secara linguistik, bukan juga NLP task klasik (POS tagging, parsing, NER, coreference, word sense disambiguation) yang mendahului era LLM dan tetap relevan (mis. untuk sistem yang tidak butuh/tidak mampu pakai LLM besar, atau untuk debugging kenapa LLM salah paham struktur kalimat).
* `courses/ml-fundamentals/roadmap.md` (10 modul) membangun fondasi matematika ML/DL umum (aljabar linear, kalkulus, probabilitas, neural network dari nol, CNN/RNN) — generic, tidak spesifik bahasa/teks.

**Keputusan pembagian scope (supaya tidak duplikasi):**

1. Module 1-7 course ini adalah wilayah **murni belum tercakup di course manapun**: linguistik (morfologi, sintaksis, semantik, pragmatik) sebagai fondasi ilmiah bahasa, lalu NLP klasik/statistik (tokenization linguistik, POS tagging, parsing, NER, word sense disambiguation, statistical language model, text classification) — ini adalah "bagaimana bahasa manusia terstruktur dan bagaimana computer memprosesnya sebelum era deep learning", yang tidak diajarkan di `llm` maupun `ml-fundamentals`.
2. Module 8-9 (embeddings & Transformer) **cross-reference** `courses/llm` Module 2 dan `courses/ml-fundamentals` Module 8 — tidak mengulang derivasi/implementasi yang sama, fokus pada sudut pandang *linguistik* (bagaimana distributional semantics dari Module 4 menjadi word2vec, bagaimana subword tokenization menangani morfologi dari Module 2) dan pada task NLP spesifik (sequence labeling, seq2seq, machine translation) yang tidak dibahas di `llm` course.
3. Module 10-11 (LLM untuk NLP task, RAG, agents) **secara eksplisit tidak mengulang** `courses/llm` Module 3-9 — hanya menyambungkan: kapan pakai fine-tuned encoder (BERT-style) klasik vs LLM prompting untuk task NLP spesifik (klasifikasi, NER, QA), dan bagaimana RAG/agent (sudah diajarkan mendalam di `courses/llm`) diterapkan ke use case NLP (document understanding, question answering). Peserta yang sudah menyelesaikan `courses/llm` akan menganggap dua modul ini sebagai *aplikasi*, bukan materi baru.
4. Module 12 (Evaluation) fokus metric **spesifik NLP** (BLEU, ROUGE, METEOR, perplexity, kappa inter-annotator agreement) yang tidak dibahas di `courses/llm` Module 8 (yang fokus LLM-as-judge/human eval generik).

**Kalibrasi**: course ini sengaja dimulai dari linguistik murni (bukan langsung ke embedding/Transformer) karena riset menunjukkan pemahaman struktur bahasa (morfologi/sintaksis/semantik/pragmatik) mempertajam intuisi *kenapa* NLP modern bekerja seperti sekarang (mis. kenapa tokenizer BPE "meniru" morfologi tanpa tahu morfologi, kenapa attention menyerupai dependency parsing, kenapa LLM tetap gagal di pragmatik/implicature). Ini konsisten dengan struktur *Stanford CS224N* dan *Speech and Language Processing* (Jurafsky & Martin) yang keduanya membuka dengan linguistik sebelum masuk statistik/neural.

Referensi riset yang dipakai menyusun roadmap ini: *Speech and Language Processing* — Daniel Jurafsky & James H. Martin (3rd ed. draft, referensi standar akademik NLP, struktur morfologi→sintaksis→semantik→pragmatik→aplikasi), *Stanford CS224N: NLU with Deep Learning* (struktur word vectors → neural net → dependency parsing → seq2seq → Transformer), Hugging Face NLP Course (chapter tokenizers, fine-tuning task klasik: NER/classification/QA), spaCy & NLTK documentation resmi (tooling linguistik komputasional applied), dan `courses/llm/roadmap.md` + `courses/ml-fundamentals/roadmap.md` (untuk memastikan tidak ada duplikasi, lihat pembagian scope di atas).

---

# Learning Objectives

* [ ] Paham 5 level struktur bahasa (fonologi/morfologi/sintaksis/semantik/pragmatik) dan bisa menganalisis kalimat di tiap level secara manual
* [ ] Mahir memproses teks di level kata: tokenization, stemming, lemmatization, morphological analysis — termasuk kasus bahasa non-Inggris (mis. Bahasa Indonesia yang aglutinatif ringan)
* [ ] Mampu membangun & menggunakan parser sintaksis (POS tagging, constituency & dependency parsing) dan memahami algoritma di baliknya
* [ ] Paham semantik leksikal (WordNet, word sense disambiguation) dan semantik komposisional (semantic role labeling) sebagai jembatan ke distributional semantics
* [ ] Paham pragmatik & wacana (coreference resolution, speech acts, implicature) — batas yang masih jadi tantangan terbuka NLP modern termasuk LLM
* [ ] Mahir statistical NLP: n-gram language model, smoothing, HMM/CRF untuk sequence labeling (POS, NER)
* [ ] Mampu membangun sistem text classification & information extraction klasik (TF-IDF, Naive Bayes/SVM, NER, relation extraction) dan tahu kapan ini masih lebih baik dari LLM (latency, cost, explainability)
* [ ] Paham word embeddings (word2vec, GloVe, fastText) sebagai realisasi distributional semantics, dan bagaimana subword tokenization menangani morfologi
* [ ] Paham arsitektur Transformer & attention dari sudut pandang linguistik (apa yang "dipelajari" attention head, hubungan dengan dependency parsing), plus seq2seq & machine translation history
* [ ] Mampu memilih & fine-tune model encoder (BERT-style) untuk task NLP klasik, vs kapan pakai LLM prompting — decision framework applied
* [ ] Mampu menerapkan RAG & agent (fondasi dari `courses/llm`) untuk use case NLP spesifik: document QA, information extraction dari dokumen panjang
* [ ] Mampu mengevaluasi sistem NLP dengan metric yang tepat (BLEU/ROUGE/METEOR/perplexity/kappa) dan tahu keterbatasan tiap metric
* [ ] Menyelesaikan Capstone Project: sistem NLP end-to-end yang menggabungkan linguistik, klasik, dan modern (LLM/RAG) untuk 1 use case nyata

---

# Course Modules

## Module 1 — Fondasi Linguistik: Peta Besar Bahasa Manusia

Description: Sebelum memproses bahasa dengan komputer, pahami dulu bagaimana ahli bahasa (linguist) memodelkan bahasa. Modul ini membangun peta 5 level struktur bahasa yang akan dipakai sebagai kerangka sepanjang course.

Lessons:

* [ ] Apa itu linguistik & NLP — kenapa keduanya beririsan tapi tidak sama; ilmu murni vs applied engineering
* [ ] 5 level struktur bahasa: fonologi/fonetik (sekilas), morfologi, sintaksis, semantik, pragmatik — peta besar & contoh tiap level
* [ ] Bahasa sebagai sistem generatif (Chomsky) vs bahasa sebagai pola statistik (Shannon) — dua tradisi yang melahirkan NLP modern
* [ ] Studi kasus: kenapa Bahasa Indonesia (aglutinatif ringan, tanpa infleksi kompleks) berbeda tantangan NLP-nya dari Bahasa Inggris (infleksi) dan Bahasa Arab/Jerman (morfologi kompleks)

Mini Project: Analisis manual 5 kalimat (Indonesia & Inggris) di kelima level linguistik — dokumentasikan sebagai referensi pribadi untuk modul-modul berikutnya.

Status: Not Started

---

## Module 2 — Morfologi & Pemrosesan Level Kata

Description: Level struktur bahasa paling dasar untuk komputer: bagaimana kata terbentuk, dan bagaimana computer memecah/menyederhanakan kata sebelum analisis lebih lanjut.

Lessons:

* [ ] Morfologi: morfem, infleksi vs derivasi, kata majemuk — analisis morfologi manual
* [ ] Tokenization berbasis aturan (rule-based): whitespace, punctuation handling, edge case (kontraksi, hyphenation, emoji/URL)
* [ ] Stemming (Porter/Snowball) vs Lemmatization — perbedaan, trade-off akurasi vs kecepatan
* [ ] Morphological analysis untuk bahasa aglutinatif/fusional — kenapa Bahasa Indonesia butuh pendekatan berbeda dari Bahasa Inggris (studi kasus Sastrawi untuk stemming Bahasa Indonesia)

Mini Project: Bangun pipeline tokenizer + stemmer/lemmatizer dari nol (Python, tanpa library NLP) untuk teks Bahasa Indonesia, bandingkan hasilnya dengan library (NLTK/Sastrawi).

Status: Not Started

---

## Module 3 — Sintaksis & Parsing

Description: Bagaimana kata tersusun jadi kalimat yang valid secara gramatikal — fondasi untuk memahami struktur, yang nanti terhubung ke attention mechanism di Module 9.

Lessons:

* [ ] Part-of-Speech (POS) tagging: tagset (Penn Treebank), ambiguitas kategori kata
* [ ] Context-Free Grammar (CFG) & constituency parsing — pohon sintaksis, ambiguitas struktural
* [ ] Dependency parsing — hubungan head-dependent, kenapa lebih populer di NLP modern dibanding constituency parsing
* [ ] Algoritma parsing: CYK (constituency), transition-based/shift-reduce (dependency) — cukup dalam untuk paham kompleksitas & trade-off, bukan derivasi formal penuh
* [ ] Treebank sebagai data training (Penn Treebank, Universal Dependencies) — bagaimana parser modern dilatih

Mini Project: Bangun POS tagger sederhana (rule-based + statistik) dan visualisasikan dependency parse tree untuk 10 kalimat menggunakan spaCy, analisis kasus ambiguitas.

Status: Not Started

---

## Module 4 — Semantik: Makna Kata & Kalimat

Description: Setelah struktur, masuk ke makna — level yang menjembatani NLP klasik (WordNet, symbolic) dengan NLP modern (distributional semantics, embeddings di Module 8).

Lessons:

* [ ] Semantik leksikal: sinonim, antonim, hipernim/hiponim, WordNet sebagai lexical database
* [ ] Word Sense Disambiguation (WSD) — polisemi, algoritma klasik (Lesk algorithm)
* [ ] Semantik komposisional: bagaimana makna kata bergabung jadi makna frasa/kalimat, Semantic Role Labeling (SRL) — siapa melakukan apa ke siapa
* [ ] Distributional semantics ("you shall know a word by the company it keeps" — Firth) — jembatan konseptual menuju word embeddings

Mini Project: Bangun WSD sederhana dengan Lesk algorithm + eksplorasi WordNet (hierarki hipernim/hiponim) untuk 20 kata ambigu, bandingkan hasil dengan intuisi manusia.

Status: Not Started

---

## Module 5 — Pragmatik & Wacana (Discourse)

Description: Level bahasa paling sulit diformalkan — makna yang bergantung konteks, bukan kalimat itu sendiri. Ini juga batas yang masih jadi tantangan terbuka untuk LLM modern.

Lessons:

* [ ] Pragmatik: speech acts (Austin/Searle), implicature (Grice's Maxims) — makna tersirat vs makna literal
* [ ] Coreference resolution: menentukan kata ganti/frasa merujuk ke entitas yang sama (Winograd Schema sebagai benchmark klasik)
* [ ] Discourse structure: cohesion & coherence, discourse markers, Rhetorical Structure Theory (RST) sekilas
* [ ] Kenapa pragmatik masih jadi kelemahan LLM: sarcasm, implicature kompleks, contextual ambiguity — studi kasus kegagalan nyata

Mini Project: Kumpulkan 10 contoh kalimat dengan pragmatik/implicature yang sulit (sarkasme, referensi ambigu, ironi), uji ke sebuah LLM, analisis pola kegagalannya.

Status: Not Started

---

## Module 6 — Statistical NLP & Language Modeling

Description: Titik temu antara linguistik dan probabilitas — bagaimana NLP sebelum deep learning memodelkan bahasa secara statistik, fondasi konseptual untuk memahami kenapa LLM modern disebut "language model".

Lessons:

* [ ] N-gram language model: unigram/bigram/trigram, cara menghitung probabilitas kalimat
* [ ] Smoothing techniques (Laplace, Kneser-Ney) — mengatasi masalah data sparsity/unseen n-gram
* [ ] Hidden Markov Model (HMM) untuk sequence labeling (POS tagging sebagai masalah statistik, Viterbi algorithm)
* [ ] Conditional Random Fields (CRF) — kenapa lebih baik dari HMM untuk NER & sequence labeling
* [ ] Evaluasi language model: perplexity — apa artinya dan kenapa masih dipakai untuk LLM modern

Mini Project: Bangun n-gram language model dari nol (dengan smoothing) dari corpus teks Bahasa Indonesia, hitung perplexity, dan bandingkan dengan HMM POS tagger (Viterbi) yang dilatih dari treebank kecil.

Status: Not Started

---

## Module 7 — Text Classification & Information Extraction

Description: Task NLP applied paling umum di industri sebelum (dan tetap relevan sesudah) era LLM — mengubah teks jadi keputusan/struktur terstruktur.

Lessons:

* [ ] Representasi teks klasik: Bag-of-Words, TF-IDF
* [ ] Text classification: Naive Bayes, SVM untuk sentiment analysis & topic classification
* [ ] Named Entity Recognition (NER): sequence labeling applied (menyambung CRF dari Module 6), BIO tagging scheme
* [ ] Relation Extraction & Information Extraction — dari teks tidak terstruktur ke data terstruktur (knowledge base population)
* [ ] Kapan model klasik masih menang vs LLM: latency, cost, explainability, data terbatas, on-device/offline requirement

Mini Project: Bangun pipeline sentiment classifier (TF-IDF + SVM) dan NER classifier (CRF, BIO tagging) untuk domain nyata (mis. review produk Bahasa Indonesia), ukur akurasi & bandingkan waktu inferensi dengan memanggil LLM untuk task yang sama.

Status: Not Started

---

## Module 8 — Word Embeddings & Jembatan ke Neural NLP

Description: Realisasi nyata distributional semantics (Module 4) sebagai vektor — titik di mana NLP klasik bertemu deep learning. **Cross-reference**: intuisi neural network dari `courses/ml-fundamentals` Module 8 membantu, tapi tidak wajib — modul ini menjelaskan secukupnya dari sudut pandang bahasa.

Lessons:

* [ ] Dari one-hot encoding ke dense vector — kenapa representasi lama gagal menangkap makna
* [ ] word2vec: CBOW vs Skip-gram, negative sampling — intuisi cara kerja, bukan derivasi matematis penuh
* [ ] GloVe & fastText — perbedaan pendekatan, kenapa fastText lebih baik untuk bahasa dengan morfologi kaya (subword-based)
* [ ] Evaluasi embeddings: analogy task (king-man+woman=queen), similarity benchmark — dan keterbatasannya (bias, polisemi tidak tertangani single vector)
* [ ] Dari embedding statis ke contextual embedding — kenapa "bank" (sungai vs keuangan) butuh representasi berbeda tergantung konteks (jembatan ke Module 9)

Mini Project: Latih word2vec sendiri (gensim) pada corpus Bahasa Indonesia, eksplorasi analogy & similarity, visualisasikan cluster kata dengan t-SNE/PCA (menyambung intuisi eigenvector dari `courses/ml-fundamentals` Module 2 bila sudah diambil).

Status: Not Started

---

## Module 9 — Tokenization Modern, Attention & Arsitektur Transformer (Sudut Pandang NLP)

Description: **Cross-reference eksplisit** `courses/llm` Module 2 (yang sudah membahas tokenization/attention/Transformer dari sudut pandang applied engineer) — modul ini tidak mengulang, tapi menyambungkan ke linguistik: bagaimana subword tokenization "menemukan kembali" morfologi tanpa linguistik eksplisit, dan bagaimana attention menyerupai dependency parsing.

Lessons:

* [ ] Subword tokenization (BPE/WordPiece/SentencePiece) sebagai solusi morfologi statistik — hubungan dengan Module 2 (morfologi manual) dan kenapa tokenizer "menemukan" imbuhan tanpa diajari aturan
* [ ] Sequence-to-sequence (seq2seq) & encoder-decoder — sejarah singkat Statistical Machine Translation → Neural Machine Translation, konteks historis sebelum Transformer
* [ ] Attention mechanism dari sudut pandang linguistik: apa yang dipelajari attention head (studi interpretability — beberapa head meniru pola dependency parsing dari Module 3)
* [ ] Encoder-only (BERT) vs decoder-only (GPT) vs encoder-decoder (T5) — kapan arsitektur mana cocok untuk task NLP apa

Mini Project: Eksperimen tokenizer BPE pada kata berimbuhan Bahasa Indonesia (mis. "mempertanggungjawabkan"), bandingkan split-nya dengan analisis morfologi manual dari Module 2 — dokumentasikan pola kemiripan/perbedaan.

Status: Not Started

---

## Module 10 — Fine-Tuning Model Klasik vs LLM Prompting untuk Task NLP

Description: Decision framework applied — kapan fine-tune encoder (BERT-style) untuk task NLP klasik (Module 3, 6, 7), vs kapan pakai LLM prompting (`courses/llm` Module 3). **Tidak mengulang** dasar fine-tuning/LoRA dari `courses/llm` Module 6 — fokus pada perbandingan & task NLP spesifik.

Lessons:

* [ ] Fine-tuning BERT-style model untuk task klasik: text classification, NER, question answering (extractive)
* [ ] Perbandingan langsung: fine-tuned BERT vs zero-shot/few-shot LLM prompting untuk task yang sama (akurasi, cost, latency, kebutuhan data)
* [ ] Named Entity Recognition & Semantic Role Labeling dengan model modern — menyambung Module 3-4 (linguistik) dengan tooling neural
* [ ] Multilingual & cross-lingual NLP: mBERT/XLM-R, tantangan NLP untuk Bahasa Indonesia & bahasa low-resource

Mini Project: Fine-tune model encoder kecil (mis. IndoBERT) untuk NER Bahasa Indonesia, bandingkan hasilnya (akurasi, waktu, biaya) dengan prompting LLM untuk task NER yang sama.

Status: Not Started

---

## Module 11 — RAG & Agents untuk Aplikasi NLP

Description: **Cross-reference eksplisit** `courses/llm` Module 5 (RAG) dan Module 7 (Agents) — modul ini tidak mengajarkan ulang RAG/agent dari nol, tapi menerapkannya ke use case NLP spesifik: document understanding, question answering atas dokumen panjang, information extraction otomatis.

Lessons:

* [ ] Document QA & long-document understanding — kapan RAG (chunking dari `courses/llm` Module 5) dibutuhkan vs context window besar cukup
* [ ] Information extraction otomatis dengan agent: menyambung NER/relation extraction klasik (Module 7) dengan LLM agent untuk ekstraksi terstruktur dari dokumen tidak terstruktur
* [ ] Coreference & discourse-aware retrieval — kenapa RAG naif kadang gagal pada dokumen dengan referensi lintas paragraf (menyambung Module 5 pragmatik/wacana)
* [ ] Studi kasus sistem NLP hybrid: kombinasi pipeline klasik (Module 6-7) + LLM/RAG (`courses/llm`) di production nyata

Mini Project: Bangun sistem document QA untuk kumpulan dokumen Bahasa Indonesia (mis. isi `standards/` repo ini) yang menggabungkan RAG dengan post-processing NER/coreference untuk menjawab pertanyaan yang butuh pelacakan entitas lintas dokumen.

Status: Not Started

---

## Module 12 — Evaluasi Sistem NLP

Description: Metric yang spesifik untuk NLP — melengkapi (bukan mengulang) evaluasi generik LLM (`courses/llm` Module 8: LLM-as-judge/human eval).

Lessons:

* [ ] Metric klasifikasi & sequence labeling: precision/recall/F1 per-entity (untuk NER), confusion matrix untuk multi-class
* [ ] Metric generasi teks: BLEU, ROUGE, METEOR — cara kerja, kapan tepat dipakai, kenapa sering dikritik (tidak menangkap makna, hanya n-gram overlap)
* [ ] Perplexity sebagai metric language model — menyambung Module 6, dan kenapa perplexity rendah tidak selalu berarti kualitas tinggi
* [ ] Inter-annotator agreement (Cohen's Kappa) — mengukur kualitas data anotasi manusia sebelum training/evaluasi
* [ ] Bias & fairness dalam NLP evaluation: dataset bias, evaluasi lintas bahasa/dialek

Mini Project: Evaluasi lengkap sistem dari Module 10-11 (NER + Document QA) dengan metric yang tepat (F1 per-entity, ROUGE untuk jawaban generatif), buat laporan evaluasi yang menjelaskan trade-off & keterbatasan tiap metric.

Status: Not Started

---

# Enterprise Project

Description: **Capstone NLP System** — bangun 1 aplikasi NLP end-to-end yang menggabungkan seluruh course: (1) pipeline preprocessing linguistik (tokenization + morphological analysis, Module 2), (2) minimal 1 komponen NLP klasik yang berjalan (POS tagger/NER/classifier, Module 3/6/7), (3) representasi modern (embeddings atau fine-tuned encoder, Module 8/10), (4) komponen berbasis LLM (RAG atau agent, Module 11) untuk bagian yang butuh reasoning/generasi, (5) evaluasi rigorous dengan metric yang tepat per komponen (Module 12), (6) 1 halaman "NLP System Design Doc" yang menjelaskan kenapa tiap komponen dipilih klasik vs modern (trade-off cost/latency/akurasi/explainability).

Acceptance Criteria:

* Sistem memproses input teks Bahasa Indonesia nyata (bukan dataset toy/mainan) end-to-end
* Minimal 1 komponen NLP klasik (bukan LLM) terukur akurasinya dengan metric yang tepat
* Komponen LLM (RAG/agent) terintegrasi untuk task yang memang butuh reasoning/generasi, bukan dipakai untuk semua hal secara default
* Evaluasi menghasilkan angka terukur (F1/BLEU/ROUGE/perplexity sesuai komponen), bukan penilaian subjektif
* Design doc menjelaskan trade-off tiap keputusan arsitektur (kenapa klasik di sini, kenapa LLM di sana)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Apa itu linguistik & NLP
