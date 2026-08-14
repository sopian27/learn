# Course Roadmap

## Course Information

* Course Name: Biologi untuk Software Engineer — dari Sel sampai Bioinformatika, Neuroscience & Mental Model Desain Sistem
* Category: Biology for Software Engineers (Domain: biology-for-software-engineers — lihat `domains/biology-for-software-engineers/DOMAIN.md`)
* Difficulty: Level 0 (bisa coding, tapi biologi SMA sudah lama tidak dipakai) → Level 3 (mampu memakai prinsip biologi sebagai mental model arsitektur software secara jujur/bermekanisme DAN memproses data biologis dasar dengan Python/Biopython)
* Estimated Duration: 10 modul, 4-5 lesson/modul. Applied Practice domain ini punya dua bentuk (lihat `domains/biology-for-software-engineers/DOMAIN.md` bagian Applied Practice Definition): mayoritas modul berujung dokumen analogi biologi→software bermekanisme jelas, modul Bioinformatika & AI-in-biology berujung kode Python/Biopython nyata.
* Prerequisites: Bisa coding di minimal satu bahasa (Python diutamakan untuk modul bioinformatika) dengan dasar OOP; biologi SMA (tidak wajib mendalam). TIDAK butuh latar belakang biologi/kedokteran formal. Domain ini murni literasi terapan — pertanyaan yang menyentuh diagnosis/pengobatan kesehatan nyata akan diarahkan eksplisit ke dokter/tenaga medis berlisensi, bukan dijawab di sini (lihat `domains/biology-for-software-engineers/DOMAIN.md` Domain-Specific Standards).

---

# Catatan Scope (baca dulu sebelum approve)

1. **Dua bentuk Applied Practice**: modul konseptual (1, 2, 3, 5, 6, 7, 10) berakhir dengan dokumen pemetaan analogi biologi→software yang WAJIB menjelaskan mekanisme (bukan cuma kemiripan nama) DAN batas di mana analogi berhenti berlaku. Modul evolusi (4) dan bioinformatika/AI (8, 9) berakhir dengan kode Python yang benar-benar berjalan.
2. **Bukan pengganti kredensial medis/biologi formal**: Module 5 (Sistem Tubuh Manusia) murni literasi struktur/fungsi, TIDAK PERNAH dipakai untuk diagnosis atau saran kesehatan pribadi.
3. **Tidak overlap dengan `astronomy-and-life-in-the-universe`**: domain itu mencakup astrobiologi/asal-usul kehidupan kosmik (Drake Equation, extremophile, habitable zone) — course ini fokus biologi Bumi yang sudah established (sel, DNA, evolusi organisme nyata).
4. Riset disusun mengikuti `ai-los/AI-LOS.md` & `domains/biology-for-software-engineers/DOMAIN.md` Trusted Sources: Molecular Biology of the Cell (NCBI Bookshelf), MIT OCW 7.01SC (Eric Lander dkk.), Khan Academy Biology, NCBI/GenBank/BLAST, Biopython docs resmi, Principles of Neural Science (Kandel), AlphaFold Protein Structure Database (EMBL-EBI & DeepMind).

---

# Learning Objectives

* [ ] Menjelaskan struktur & fungsi sel, central dogma (DNA→RNA→protein), dan genetika Mendel dengan benar secara sains (Molecular Biology of the Cell, MIT OCW 7.01SC)
* [ ] Memetakan central dogma ke pipeline software (source→build→deploy) secara bermekanisme, bukan sekadar analogi permukaan
* [ ] Memahami evolusi & seleksi alam cukup dalam untuk mengimplementasikan genetic algorithm sederhana dari nol di Python
* [ ] Memiliki literasi sistem tubuh manusia & neuroscience dasar (struktur neuron, sinapsis, potensial aksi) tanpa pernah dipakai untuk diagnosis kesehatan nyata
* [ ] Menjelaskan secara eksplisit di mana analogi artificial neural network berhenti mewakili neuron biologis sungguhan
* [ ] Memetakan prinsip ekologi (keystone species, resiliensi, redundancy) ke pola desain distributed systems (cascading failure, bulkhead, multi-region redundancy)
* [ ] Mahir memproses data biologis dasar dengan Python/Biopython: parsing FASTA/GenBank, sequence alignment, query NCBI
* [ ] Memiliki literasi konseptual AI-in-biology (AlphaFold/protein folding, drug discovery ML) cukup untuk membedah arsitektur sistem AI-nya secara umum
* [ ] Menghasilkan Capstone Project "Biological Design Patterns for Resilient Software" yang menggabungkan analogi arsitektur jujur (dengan batasannya) DAN toolkit bioinformatika kode nyata

---

# Course Modules

## Module 1 — Sel & Biologi Molekuler Dasar

Description: Fondasi paling dasar sebelum DNA/genetika — struktur sel, organel, membran, dan metabolisme dasar, mengikuti struktur awal Molecular Biology of the Cell & MIT OCW 7.01SC.

Lessons:

* [ ] 1.1 Sel sebagai unit dasar kehidupan — teori sel, prokariotik vs eukariotik
* [ ] 1.2 Organel & fungsinya — nukleus, mitokondria, ribosom, retikulum endoplasma, dst
* [ ] 1.3 Membran sel & transport (difusi, osmosis, transport aktif)
* [ ] 1.4 Metabolisme dasar — respirasi seluler & fotosintesis secukupnya (ATP sebagai "energi kerja" sel)

Analogi ke Software (poin, didalami di Mini Project):

* Sel sebagai objek/instance terenkapsulasi — membran sebagai encapsulation boundary (private state)
* Organel sebagai modul/komponen terspesialisasi dalam satu proses (separation of concerns dalam monolith)
* ATP/metabolisme sebagai resource/compute budget internal sebuah service

Mini Project: Dokumen pemetaan sel→arsitektur modular (organel-organel dipetakan ke komponen aplikasi monolith nyata/hipotetis) + diagram sederhana, menjelaskan mekanisme kenapa analogi encapsulation berlaku dan di mana ia berhenti (sel nyata jauh lebih interdependen daripada OOP encapsulation ideal).

Status: Not Started

---

## Module 2 — DNA, RNA & Central Dogma

Description: Struktur DNA/RNA, replikasi, transkripsi, translasi — central dogma sebagai "compile-deploy pipeline" biologis, mengikuti MIT OCW 7.01SC & Molecular Biology of the Cell.

Lessons:

* [ ] 2.1 Struktur DNA & RNA — double helix, basa nitrogen, aturan pairing
* [ ] 2.2 Replikasi DNA — cara sel menyalin "source code"-nya sendiri, termasuk proofreading/error-correction
* [ ] 2.3 Transkripsi — DNA ke mRNA
* [ ] 2.4 Translasi — mRNA ke protein via ribosom & kode genetik (codon)
* [ ] 2.5 Central dogma sebagai satu alur end-to-end, plus pengecualian secukupnya (reverse transcription pada retrovirus)

Analogi ke Software (poin):

* DNA sebagai source code (single source of truth, version-controlled secara biologis lewat replikasi)
* Proofreading enzim saat replikasi sebagai checksum/validation pada proses copy data
* Transkripsi (DNA→mRNA) sebagai compile step — menghasilkan intermediate representation
* Translasi (mRNA→protein) sebagai deployment/runtime execution — artifact yang benar-benar "berjalan"

Mini Project: Dokumen "Central Dogma as Software Pipeline" v1 — memetakan tiap tahap central dogma ke tahap source→build→deploy nyata, dilengkapi contoh konkret (mis. cuplikan CI/CD config) yang menunjukkan paralel compile/deploy secara konkret, bukan cuma naratif.

Status: Not Started

---

## Module 3 — Genetika & Pewarisan Sifat

Description: Hukum Mendel, genotype vs phenotype, mutasi, dan rekombinasi genetik — dasar sebelum masuk evolusi (Module 4), mengikuti MIT OCW 7.01SC & Khan Academy Biology.

Lessons:

* [ ] 3.1 Hukum Mendel — dominan/resesif, Punnett square
* [ ] 3.2 Genotype vs phenotype, alel ganda, pewarisan poligenik secukupnya
* [ ] 3.3 Mutasi genetik — jenis & efeknya (silent, missense, nonsense, frameshift)
* [ ] 3.4 Rekombinasi genetik & sumber keragaman genetik dalam populasi

Analogi ke Software (poin):

* Genotype vs phenotype sebagai config/state internal vs observable behavior/output eksternal
* Mutasi sebagai perubahan kode acak — mayoritas netral/merusak, sedikit menguntungkan (analog mutation testing/fuzzing)
* Rekombinasi genetik sebagai crossover pada genetic algorithm — jembatan langsung ke Module 4

Mini Project: Dokumen pemetaan genotype/phenotype ke config-driven software behavior, dilengkapi skrip Python kecil yang mensimulasikan Punnett square (probabilitas pewarisan sifat untuk persilangan sederhana) sebagai jembatan awal ke coding.

Status: Not Started

---

## Module 4 — Evolusi & Seleksi Alam

Description: Teori evolusi Darwin, seleksi alam, genetic drift, dan spesiasi — dijembatani langsung ke genetic algorithm & evolutionary computing sebagai aplikasi komputasi nyata, bukan cuma analogi konseptual.

Lessons:

* [ ] 4.1 Teori evolusi Darwin & seleksi alam — fitness, adaptasi terhadap lingkungan
* [ ] 4.2 Genetic drift, gene flow, dan spesiasi
* [ ] 4.3 Evolusi sebagai algoritma optimasi — pengantar genetic algorithm & evolutionary computing
* [ ] 4.4 Anatomi genetic algorithm: populasi, fitness function, seleksi, crossover, mutasi

Analogi ke Software (poin — di modul ini analogi LANGSUNG jadi kode, bukan cuma dokumen):

* Seleksi alam sebagai fitness function dalam optimization algorithm
* Populasi & generasi sebagai eksplorasi search space
* Genetic drift sebagai analog random exploration vs exploitation trade-off

Mini Project: Implementasi genetic algorithm dari nol di Python (mis. optimasi fungsi matematis sederhana atau traveling salesman skala kecil). Validasi: fitness terbaik membaik secara konsisten antar generasi, dibuktikan dengan angka/grafik eksplisit — bukan klaim "kelihatan membaik".

Status: Not Started

---

## Module 5 — Sistem Tubuh Manusia (Overview)

Description: Gambaran umum sistem sirkulasi, respirasi, pencernaan, imun, dan endokrin — level literasi umum, BUKAN kedalaman medis (lihat `domains/biology-for-software-engineers/DOMAIN.md` Scope & Domain-Specific Standards: modul ini tidak pernah dipakai untuk diagnosis/saran kesehatan).

Lessons:

* [ ] 5.1 Sistem sirkulasi & respirasi — overview cara kerja & interkoneksinya
* [ ] 5.2 Sistem pencernaan & endokrin — overview homeostasis hormon & energi
* [ ] 5.3 Sistem imun — overview pertahanan tubuh (innate vs adaptive secukupnya)
* [ ] 5.4 Homeostasis sebagai prinsip pemersatu seluruh sistem tubuh (preview Module 10)

Analogi ke Software (poin):

* Sistem organ sebagai microservices yang saling terhubung lewat "kontrak" (hormon/sinyal saraf sebagai message passing/event bus)
* Homeostasis sebagai feedback control loop — preview eksplisit ke self-healing systems yang didalami penuh di Module 10

Mini Project: Diagram + dokumen pemetaan interaksi antar sistem tubuh (mis. sirkulasi-respirasi-endokrin) ke arsitektur microservices event-driven, disertai catatan eksplisit "ini literasi sains, bukan referensi medis."

Status: Not Started

---

## Module 6 — Neuroscience Dasar

Description: Struktur neuron, sinapsis, potensial aksi, dan plastisitas otak, mengikuti *Principles of Neural Science* (Kandel) — dijembatani ke artificial neural network DENGAN batas analogi yang dinyatakan eksplisit.

Lessons:

* [ ] 6.1 Struktur neuron & jenis-jenisnya
* [ ] 6.2 Sinapsis & neurotransmitter — cara neuron "berkomunikasi"
* [ ] 6.3 Potensial aksi — cara sinyal listrik/kimia merambat sepanjang neuron
* [ ] 6.4 Plastisitas otak (synaptic plasticity) — dasar biologis pembelajaran/memori
* [ ] 6.5 Neuron biologis vs artificial neural network — di mana analogi berlaku, di mana berhenti

Analogi ke Software (poin):

* Neuron & sinapsis sebagai node & weighted edge dalam graph komputasi (ANN)
* Potensial aksi (all-or-nothing firing) sebagai activation function/threshold
* Synaptic plasticity (Hebbian learning) sebagai inspirasi historis backpropagation — TAPI mekanisme sungguhan sangat berbeda (backprop tidak biologically plausible), dibahas eksplisit sebagai batas analogi

Mini Project: Dokumen tabel perbandingan eksplisit neuron biologis vs artificial neuron (kesamaan & perbedaan mekanistik) DILENGKAPI implementasi perceptron sederhana dari nol di Python (tanpa framework ML) untuk menunjukkan pemahaman mekanisme dasar ANN.

Status: Not Started

---

## Module 7 — Ekologi & Sistem Kompleks

Description: Rantai makanan, ekosistem, keystone species, dan resiliensi ekosistem — dijembatani eksplisit ke distributed systems/microservices resilience (redundancy, graceful degradation, cascading failure).

Lessons:

* [ ] 7.1 Rantai makanan, jaring makanan, & aliran energi dalam ekosistem
* [ ] 7.2 Keystone species & efek kaskade (cascading failure) saat satu komponen hilang
* [ ] 7.3 Resiliensi ekosistem — redundancy, diversity, & recovery setelah gangguan
* [ ] 7.4 Studi kasus gangguan ekosistem nyata sebagai analog "outage" distributed system

Analogi ke Software (poin):

* Keystone species sebagai single point of failure (SPOF) dalam distributed system
* Cascading failure ekologi sebagai analog cascading failure microservices (mis. retry storm, thundering herd)
* Diversity/redundancy ekosistem sebagai multi-region/multi-AZ redundancy & bulkhead pattern

Mini Project: Studi kasus tertulis memetakan satu skenario kegagalan ekosistem nyata (mis. hilangnya satu spesies kunci) ke satu insiden distributed system nyata/hipotetis (cascading outage), menjelaskan mekanisme paralelnya DAN rekomendasi desain resilient yang terinspirasi darinya.

Status: Not Started

---

## Module 8 — Bioinformatika Praktis dengan Python

Description: Modul coding utama domain ini — memproses data biologis nyata dengan Biopython: parsing sekuens, sequence alignment, dan query database publik NCBI, mengikuti dokumentasi resmi Biopython & sumber NCBI.

Lessons:

* [ ] 8.1 Setup Biopython & format data bioinformatika umum (FASTA, GenBank)
* [ ] 8.2 Manipulasi sekuens DNA/protein terprogram — transkripsi/translasi, reverse complement, GC content
* [ ] 8.3 Pairwise sequence alignment (global/local — Needleman-Wunsch/Smith-Waterman via Biopython)
* [ ] 8.4 Multiple sequence alignment & interpretasi dasar hasilnya
* [ ] 8.5 Query database publik NCBI via `Bio.Entrez` — mengambil & memproses data sekuens nyata

Analogi ke Software (poin — lebih tipis di modul ini, fokus utama tetap kode):

* Sequence alignment sebagai diff algorithm (edit distance) — Needleman-Wunsch berkerabat dekat dengan algoritma LCS/diff yang dipakai `git diff`
* FASTA/GenBank sebagai format serialisasi data terstandardisasi (analog skema JSON/XML di software)

Mini Project (Intermediate Project — "Sequence Analysis Toolkit"): Kumpulan skrip Python/Biopython: parsing FASTA, hitung GC content, pairwise alignment 2 sekuens, dan query NCBI untuk satu gen nyata. Validasi: hasil alignment & GC content dicek manual untuk sekuens pendek buatan sendiri sebelum dipercaya untuk sekuens nyata.

Status: Not Started

---

## Module 9 — AI-in-Biology

Description: Literasi konseptual bagaimana AI mengubah biologi — protein folding (AlphaFold) dan drug discovery ML — level "bedah arsitektur sistem," bukan reproduksi model riset, mengikuti AlphaFold Protein Structure Database (EMBL-EBI & DeepMind).

Lessons:

* [ ] 9.1 Masalah protein folding & kenapa penting (struktur menentukan fungsi)
* [ ] 9.2 AlphaFold overview — bagaimana deep learning memprediksi struktur protein (konseptual)
* [ ] 9.3 Machine learning untuk drug discovery — overview use-case (virtual screening, molecule generation)
* [ ] 9.4 Genomics + ML — overview use-case (variant calling, disease risk prediction) beserta isu etika/bias data secukupnya

Analogi ke Software (poin):

* Protein folding sebagai "runtime interpretation" dari source code (DNA) ke bentuk fungsional 3D — AlphaFold sebagai analog "static analyzer" yang memprediksi behavior tanpa full execution
* Pipeline AI-in-biology sebagai contoh nyata ML system design (data pipeline, model serving, evaluation) yang pola arsitekturnya bisa dipelajari lepas dari domain biologinya

Mini Project: Dokumen studi kasus tertulis — pilih SATU (AlphaFold ATAU drug discovery ML) dan bedah arsitektur sistem AI-nya secara konseptual (data, model, evaluation, deployment) sebagai latihan system design, dirujuk ke sumber (AlphaFold Protein Structure Database/paper terkait).

Status: Not Started

---

## Module 10 — Sintesis: Prinsip Biologi sebagai Mental Model Desain Software

Description: Modul penutup yang mengikat Module 1-9 kembali ke software engineering — evolvability, redundancy, dan homeostasis disusun eksplisit sebagai kerangka desain self-healing/resilient systems.

Lessons:

* [ ] 10.1 Evolvability — desain software yang berevolusi incremental (versioning, backward compatibility, strangler fig pattern) sebagai analog evolusi biologis (Module 4)
* [ ] 10.2 Redundancy & diversity — menyatukan pelajaran Module 1 (organel), 5 (sistem tubuh), dan 7 (ekosistem) jadi satu prinsip desain resilient architecture
* [ ] 10.3 Homeostasis sebagai self-healing systems — feedback loop, autoscaling, circuit breaker, dan Chaos Engineering (Netflix) sebagai penerapan nyata
* [ ] 10.4 Menyusun katalog pribadi "Biological Design Patterns" — daftar prinsip biologi→software yang sudah dipelajari sepanjang course, siap dipakai sebagai referensi desain nyata

Portfolio Project: Lihat "Enterprise Project" di bawah — modul ini adalah fondasi langsung capstone course.

Status: Not Started

---

# Enterprise Project

Description: **"Biological Design Patterns for Resilient Software"** — dokumen arsitektur portofolio yang mengintegrasikan minimal 3 prinsip biologi (mis. evolvability dari Module 4/10, redundancy dari Module 1/5/7, homeostasis dari Module 5/10) sebagai kerangka desain eksplisit untuk satu sistem software nyata/hipotetis (arsitektur microservices/distributed system), DILENGKAPI "Sequence Analysis Toolkit" dari Module 8 (kode Biopython nyata) sebagai bukti kemampuan applied coding di sisi bioinformatika.

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` DITAMBAH keempat lensa Review Style domain ini dari `domains/biology-for-software-engineers/DOMAIN.md`:

* **Biological Accuracy** — konsep biologi yang dipakai sebagai dasar analogi benar secara sains, merujuk Trusted Sources (Molecular Biology of the Cell, MIT OCW 7.01SC, Kandel, dst)
* **Analogy Fidelity** — tiap analogi biologi→software menjelaskan mekanisme sungguhan, bukan cuma kemiripan nama/permukaan
* **Analogy Boundary Awareness** — dokumen menyatakan eksplisit di mana tiap analogi berhenti berlaku (terutama ANN vs neuron biologis, evolutionary computing vs evolusi biologis)
* **Code Correctness** — toolkit Biopython benar secara sintaks & benar secara biologis (hasil alignment/GC content tervalidasi manual)

Disimpan ke `portofolio/` disertai penjelasan tertulis kenapa tiap prinsip biologi dipilih dan batasan analoginya.

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Module 1 — Sel & Biologi Molekuler Dasar (menunggu approval roadmap)

Current Lesson: 1.1 Sel sebagai unit dasar kehidupan — teori sel, prokariotik vs eukariotik

Next Lesson: 1.1 Sel sebagai unit dasar kehidupan — teori sel, prokariotik vs eukariotik
