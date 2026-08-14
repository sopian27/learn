# Course Roadmap

## Course Information

* Course Name: Scale AI & AI Data Infrastructure — dari 0 sampai Expert (Data Engineering untuk AI/LLM Systems)
* Category: AI Data Engineering / ML Data Infrastructure (irisan Data Engineering klasik dengan Applied AI)
* Difficulty: Level 1 (paham backend engineering, belum pernah bangun pipeline data/annotation untuk ML) → Level 4 (Expert, mampu mendesain & mengimplementasikan platform data AI production-grade ala Scale AI/Labelbox)
* Estimated Duration: 10 modul, ~3-5 lesson/modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Programming kuat & familiar REST API/database (Java/Spring Boot dari repo ini, `standards/API_STANDARDS.md`, `standards/DATABASE_STANDARDS.md`). **Tidak wajib**, tapi sangat disarankan sudah menyelesaikan `courses/llm/roadmap.md` Module 1-2 (tokenization, embedding, fine-tuning) supaya istilah tersebut tidak asing — course ini memberi konteks singkat ulang di Module 1 untuk yang belum, tapi tidak mengajarkannya dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

1. **Judul & framing**: "Scale AI" di judul dipakai sebagai istilah umum ("men-scale infrastruktur data AI"), bukan course tentang produk satu perusahaan. Scale AI (perusahaan) tetap jadi salah satu *practitioner reference* utama (bersama Labelbox, Surge AI, Snorkel AI, Argilla/Hugging Face) karena mereka yang mempopulerkan pola "Data Engine" (collect → curate → annotate → train → evaluate → repeat) yang jadi kerangka besar course ini.
2. **Domain**: Topik ini condong kuat ke domain `software-engineering` yang sudah Active (lihat `ai-los/ACTIVE_DOMAIN.md`) — **tidak** dibuatkan domain baru. Course ini adalah course baru di bawah domain existing, sejajar dengan `courses/llm`, `courses/ml-fundamentals`, `courses/data-scientist`. Lihat bagian "Domain yang Dipakai" di bawah untuk alasan lengkap.
3. **Fokus: sisi *bangun platform/pipeline*, bukan sisi *pakai/latih model*.** Course ini eksplisit mengajarkan cara **software engineer mengimplementasikan** sistem data untuk AI — pipeline ingestion, tooling annotasi, workflow human-in-the-loop, infrastruktur evaluasi — sebagai backend/data engineer yang membangun *sistem yang memproduksi data*, bukan sebagai ML engineer yang mengonsumsi data itu untuk melatih model. Ini beda peran, dan course-course lain di repo ini sudah mengisi sisi konsumsi:
   * `courses/llm/roadmap.md` — cara **memakai** LLM (prompting, RAG, agent, fine-tuning ringan). Module 6-nya menyebut "dataset engineering ringan (kualitas > kuantitas)" hanya sebagai *langkah praktis* sebelum fine-tune — course ini yang membedah *bagaimana dataset berkualitas itu sebenarnya diproduksi* (annotation pipeline, QA, consensus) secara mendalam, jadi prasyarat konseptual yang saling melengkapi, bukan duplikat.
   * `courses/llm/roadmap.md` Module 8 (Evaluation, Safety & Observability) mengajarkan **cara mengevaluasi** satu sistem LLM yang sudah dibangun (metric, LLM-as-judge, human eval sebagai *teknik*). Module 7 course ini mengajarkan **cara membangun infrastruktur/platform** yang menjalankan evaluasi itu berulang kali untuk banyak model/dataset (golden dataset versioning, eval harness sebagai service, benchmark registry) — layer platform di atas teknik yang sudah diajarkan di sana, dirujuk balik bukan diulang.
   * `courses/ml-fundamentals/roadmap.md` — fondasi matematika & algoritma ML/DL. Tidak bersinggungan; course ini tidak mengajarkan cara kerja model, hanya cara memproduksi & memvalidasi data yang dipakai melatihnya.
   * `courses/data-scientist/roadmap.md` — siklus kerja Data Scientist (problem framing → EDA → model klasik → komunikasi bisnis). Data cleaning di sana (Module 3) adalah level *satu analis membersihkan satu dataset*; course ini adalah level *membangun sistem/tooling* yang dipakai banyak annotator/tim untuk memproduksi data berlabel secara berkelanjutan — beda skala dan beda artifact (sistem vs laporan analisis).
   * `courses/genai-workflow-design/roadmap.md` Module 3 membahas "human-in-the-loop" sebagai pola *desain proses bisnis* (approval gate di workflow automation klien). Module 4 course ini membahas human-in-the-loop sebagai *rekayasa sistem* (task queue, worker routing, active learning, quality control annotator) — domain aplikasi & levelnya berbeda total meski istilahnya sama; disebutkan eksplisit di Module 4 supaya tidak tertukar.
4. **Cakupan RLHF**: Course ini mengajarkan **sisi data engineering** dari RLHF (mengumpulkan demonstration data, membangun UI/pipeline perbandingan preferensi, quality control terhadap human labeler) berdasarkan struktur 3-tahap InstructGPT (SFT → Reward Model → PPO/RL) dan varian modern seperti DPO. Course ini **tidak** mengajarkan cara mengimplementasikan algoritma RL (PPO/DPO) itu sendiri secara matematis — itu tetap ranah `courses/ml-fundamentals` (jika diperluas ke RL) atau riset ML murni, di luar scope "software engineer yang membangun infrastruktur data".
5. **Tidak mencakup**: big data engineering generik (Spark/Hadoop cluster tuning — course ini menyentuh secukupnya di Module 9 sebagai konteks arsitektur, tidak mendalam), MLOps model deployment penuh (rujuk kembali ke `courses/ml-fundamentals` Module 11 draft bila nanti di-approve), dan riset pretraining LLM dari nol.

## Domain yang Dipakai

**Existing — `software-engineering`** (lihat `domains/software-engineering/DOMAIN.md`, Scope: "Backend/full-stack software engineering, system design, DevOps, dan bidang teknis terkait"). Course ini adalah pekerjaan seorang software/backend engineer yang membangun sistem (API, pipeline, storage, workflow engine) — bukan bidang subjek baru yang butuh Trusted Sources/Review Style/Assessment Form berbeda dari domain software-engineering yang sudah ada. Tidak ada `domains/scale-ai-data-infrastructure/DOMAIN.md` yang dibuat.

## Referensi Riset

Disintesis dari (bukan copy-paste satu sumber):

* [roadmap.sh/data-engineer](https://roadmap.sh/data-engineer) dan [roadmap.sh/ai-engineer](https://roadmap.sh/ai-engineer) — kerangka umum pipeline data & posisi AI data engineering di antara keduanya
* [Scale AI — Data Engine](https://scale.com/data-engine) — kerangka loop "collect → curate → annotate → train → evaluate → repeat" yang dipakai sebagai struktur besar Module 1 & 9; kombinasi pre-labeling model + human-in-the-loop review dipakai sebagai kerangka Module 3-4
* [Label Studio Documentation](https://labelstud.io/guide/get_started) dan [Argilla Documentation](https://docs.v1.argilla.io/) — referensi tooling & workflow konkret untuk Module 3 (multi-modal labeling: teks, RLHF, LLM eval, computer vision)
* [Snorkel — Programmatic Labeling / Weak Supervision](https://docs.v1.argilla.io/en/v1.8.0/tutorials/libraries/snorkel.html) — dipakai sebagai kerangka Module 6 (weak supervision, label functions, label model)
* Ouyang et al., *Training language models to follow instructions with human feedback* (OpenAI, InstructGPT paper) — kerangka 3-tahap SFT → Reward Model (comparison data) → PPO, dipakai sebagai struktur inti Module 5
* [Chip Huyen — "RLHF: Reinforcement Learning from Human Feedback"](https://huyenchip.com/2023/05/02/rlhf.html) — penjelasan applied yang menghubungkan teori RLHF ke praktik pengumpulan data preferensi, dipakai untuk memperkaya Module 5
* [OpenAI Evals (github.com/openai/evals)](https://github.com/openai/evals) — pola eval-as-code & registry benchmark, dipakai sebagai kerangka Module 7 (eval harness sebagai infrastruktur, bukan sekadar teknik)
* [Anthropic — "Demystifying evals for AI agents"](https://www.anthropic.com/engineering/demystifying-evals-for-ai-agents) dan [Anthropic — "Building Effective AI Agents"](https://www.anthropic.com/engineering/building-effective-agents) — prinsip automated eval & human-in-the-loop approval, dipakai melengkapi Module 4 & 7
* Andrew Ng — kampanye *Data-Centric AI* (error analysis → data augmentation/collection tertarget → konsistensi label) — dipakai sebagai prinsip inti Module 6 (data quality)
* `standards/API_STANDARDS.md`, `standards/DATABASE_STANDARDS.md`, `standards/SECURITY_STANDARDS.md`, `standards/OBSERVABILITY_STANDARDS.md`, `standards/MESSAGING_STANDARDS.md` (repo ini) — dipakai sebagai kerangka implementasi konkret di Module 8-9, konsisten dengan instruksi root `CLAUDE.md` soal Java/Spring Boot/PostgreSQL/Redis/Kafka/Docker/Kubernetes

---

# Learning Objectives

* [ ] Paham peta besar AI Data Infrastructure: "Data Engine" loop (collect → curate → annotate → train → evaluate → repeat) dan di mana peran software/backend engineer di dalamnya, dibedakan dari peran ML Engineer/Data Scientist yang mengonsumsi data tersebut
* [ ] Mampu merancang & mengimplementasikan pipeline ingestion data mentah (multi-sumber, multi-modal) untuk kebutuhan training/evaluasi model AI, lengkap dengan versioning dan lineage
* [ ] Mampu merancang sistem annotasi/labeling: taxonomy label, instruksi annotator, tooling (Label Studio/Argilla-style), dan mekanisme quality control (inter-annotator agreement, gold set, consensus)
* [ ] Mampu merekayasa workflow human-in-the-loop sebagai sistem software: task queue, routing ke annotator, active learning loop, review/escalation queue — bukan sekadar konsep desain proses
* [ ] Mampu membangun pipeline pengumpulan data RLHF (demonstration data untuk SFT, comparison/preference data untuk reward model) mengikuti kerangka InstructGPT, termasuk UI/API untuk perbandingan preferensi
* [ ] Mampu menerapkan data quality engineering: weak supervision/programmatic labeling, deteksi duplikat/near-duplicate, deteksi bias & data leakage, validasi skema data
* [ ] Mampu membangun infrastruktur evaluasi LLM sebagai platform: golden dataset versioning, eval harness sebagai service, benchmark registry, dashboard hasil eval
* [ ] Mampu mengekspos seluruh kapabilitas di atas sebagai API/service production (ingestion API, labeling API, dataset versioning API) yang terintegrasi dengan stack Spring Boot/PostgreSQL/Redis/Kafka repo ini
* [ ] Paham arsitektur platform data AI skala production: storage (object storage + metadata DB + vector store), orkestrasi pipeline, observability, keamanan & privasi data manusia (PII handling di data annotasi)
* [ ] Menyelesaikan proyek portofolio: platform data AI end-to-end (ingestion → annotation → HITL → eval) yang production-ready

---

# Course Modules

## Module 1 — Peta Besar AI Data Infrastructure & Peran Software Engineer di Dalamnya

Description: Sebelum membangun apa pun, pahami dulu kenapa "data" untuk AI adalah disiplin engineering tersendiri, bukan cuma file CSV — dan di mana posisi Anda sebagai software engineer di loop ini, dibedakan dari ML Engineer/Data Scientist.

Lessons:

* [ ] 1.1 Kenapa AI Data Infrastructure adalah disiplin sendiri: data sebagai bottleneck utama kualitas model (data-centric AI, Andrew Ng), bukan hanya algoritma
* [ ] 1.2 Data Engine loop (Scale AI): collect → curate → annotate → train → evaluate → repeat — dipetakan ke peran konkret (data engineer, annotation ops, ML engineer, evaluator)
* [ ] 1.3 Peta industri: Scale AI, Labelbox, Surge AI, Snorkel AI, Argilla/Hugging Face — apa yang masing-masing jual, dan kenapa perusahaan besar (OpenAI, Anthropic, Google) membangun sebagian infrastruktur ini sendiri (build vs buy)
* [ ] 1.4 Refresher singkat istilah LLM yang dipakai sepanjang course (token, embedding, fine-tuning, RLHF) — untuk yang belum menyelesaikan `courses/llm` Module 1-2; bukan pengajaran baru, hanya kalibrasi vocabulary

Mini Project: Buat diagram arsitektur "Data Engine" untuk 1 use case pilihan Anda (mis. moderasi konten, chatbot support, self-driving perception) — petakan tiap tahap loop ke komponen sistem yang akan dibangun di modul-modul berikutnya.

Status: Not Started

---

## Module 2 — Data Collection & Ingestion Pipelines

Description: Data untuk AI jarang datang dari satu sumber bersih. Modul ini membangun kemampuan merancang pipeline yang menarik data mentah multi-sumber secara reliable, dengan versioning dan lineage sejak hari pertama.

Lessons:

* [ ] 2.1 Sumber data untuk training/evaluasi AI: internal logs, web/API crawling, dataset publik, data sintetis (dan kapan masing-masing tepat/tidak tepat dipakai)
* [ ] 2.2 Desain schema data mentah: multi-modal (teks, gambar, audio) dengan metadata yang cukup untuk anotasi & audit
* [ ] 2.3 Dataset versioning & lineage: kenapa `git` saja tidak cukup, prinsip content-addressable storage untuk dataset (konsep, bukan implementasi DVC penuh — disinggung ulang di Module 9)
* [ ] 2.4 Deduplikasi & sampling strategis di tahap ingestion — kenapa membuang data buruk lebih murah di awal daripada setelah dilabel
* [ ] 2.5 Legal & privasi dasar saat mengumpulkan data (consent, PII scrubbing awal, lisensi dataset) — pengantar, diperdalam di Module 9

Mini Project: Bangun pipeline ingestion sederhana (Python/Java) yang menarik data dari minimal 2 sumber berbeda (mis. API publik + file dump), menormalkan ke satu schema, dan menyimpan dengan metadata versi.

Status: Not Started

---

## Module 3 — Annotation & Labeling Systems

Description: Inti dari "membuat data mentah menjadi data yang bisa dipakai model" — merancang taxonomy, instruksi, tooling, dan yang paling sering diremehkan: quality control terhadap manusia yang melabel.

Lessons:

* [ ] 3.1 Merancang taxonomy label & instruksi annotator yang tidak ambigu (kenapa instruksi buruk = data buruk, bahkan dengan annotator terbaik)
* [ ] 3.2 Lanskap tooling annotasi: Label Studio & Argilla (self-hosted/open-source) vs Scale AI/Labelbox (managed) — kriteria memilih
* [ ] 3.3 Membangun/mengonfigurasi interface labeling untuk tugas konkret: klasifikasi teks, named entity recognition, dan pengantar labeling multi-modal
* [ ] 3.4 Quality control: inter-annotator agreement (Cohen's/Fleiss' kappa), gold set/honeypot task, mekanisme consensus & tie-breaking
* [ ] 3.5 Mengelola annotator sebagai tenaga kerja: onboarding, kalibrasi, feedback loop kualitas, insentif vs kecepatan vs akurasi

Mini Project: Setup Label Studio (atau Argilla) untuk 1 task labeling nyata (mis. klasifikasi sentimen/toxicity), rekrut "annotator" (bisa diri sendiri + 1-2 orang), ukur inter-annotator agreement, dan revisi instruksi berdasarkan disagreement yang ditemukan.

Status: Not Started

---

## Module 4 — Human-in-the-Loop Workflow Engineering

Description: Di sini "human-in-the-loop" direkayasa sebagai sistem software — task queue, routing, active learning — bukan sekadar konsep desain proses seperti di `courses/genai-workflow-design`. Ini level backend engineering di balik platform seperti Scale AI.

Lessons:

* [ ] 4.1 Task queue & routing: bagaimana satu item data sampai ke annotator yang tepat (skill-based routing, load balancing, prioritas)
* [ ] 4.2 Active learning loop: model memilih sendiri data mana yang paling perlu dilabel manusia berikutnya (uncertainty sampling, kenapa ini menghemat biaya labeling drastis)
* [ ] 4.3 Review & escalation queue: multi-tier review (annotator → reviewer → expert), kapan item dieskalasi otomatis
* [ ] 4.4 Trade-off cost vs quality vs speed dalam desain workflow — dan bagaimana sistem (bukan kebijakan manual) yang menegakkan trade-off itu
* [ ] 4.5 Kapan pola ini berbeda dari human-in-the-loop di `courses/genai-workflow-design` Module 3: workflow bisnis vs workflow produksi data ML — sama istilah, beda level abstraksi & tujuan

Mini Project: Implementasikan task queue + routing sederhana (mis. dengan database + worker pool, atau Kafka topic per tier) yang mengirim item "butuh review" ke antrian eskalasi setelah N disagreement, lengkap dengan status tracking per item.

Status: Not Started

---

## Module 5 — RLHF & Preference Data Engineering

Description: Sisi data engineering dari RLHF — bagaimana demonstration data dan preference/comparison data yang dipakai melatih model seperti ChatGPT/Claude sebenarnya dikumpulkan, mengikuti kerangka 3-tahap InstructGPT.

Lessons:

* [ ] 5.1 Kerangka RLHF 3-tahap (InstructGPT): SFT (demonstration data) → Reward Model (comparison data) → RL/PPO — fokus pada data apa yang dibutuhkan tiap tahap, bukan algoritma RL-nya
* [ ] 5.2 Mengumpulkan demonstration data berkualitas untuk SFT: kriteria "jawaban ideal", kalibrasi antar-writer
* [ ] 5.3 Merancang UI/API perbandingan preferensi (comparison/ranking task: 2 respons mana yang lebih baik) — pola interface yang dipakai Scale AI/Surge AI/Anthropic labeler
* [ ] 5.4 Dari pairwise comparison ke dataset reward model: agregasi ranking, menangani disagreement antar-labeler pada task yang inherently subjektif
* [ ] 5.5 Alternatif modern (DPO) & dampaknya ke kebutuhan data — kenapa arsitektur data pipeline tetap mirip meski algoritma training berubah

Mini Project: Bangun API/UI sederhana untuk task perbandingan preferensi (2 output LLM ditampilkan berdampingan, reviewer memilih yang lebih baik + alasan singkat), simpan hasil sebagai dataset preferensi terstruktur siap dipakai reward model.

Status: Not Started

---

## Module 6 — Data Quality, Weak Supervision & Programmatic Labeling

Description: Data quality bukan aktivitas satu kali di awal — ini disiplin berkelanjutan. Modul ini mendalami cara mendeteksi & memperbaiki masalah kualitas data secara sistematis dan terprogram, termasuk melabel tanpa manusia sama sekali di sebagian kasus.

Lessons:

* [ ] 6.1 Prinsip Data-Centric AI (Andrew Ng): error analysis untuk menemukan *jenis* data yang bermasalah, lalu targeted collection/augmentation — bukan "kumpulkan lebih banyak data" secara membabi buta
* [ ] 6.2 Programmatic labeling & weak supervision (Snorkel): label function, label model, kapan dipakai menggantikan/melengkapi human labeling
* [ ] 6.3 Deteksi duplikat & near-duplicate pada dataset besar (hashing, embedding similarity) — kenapa data leakage antara train/eval sering berasal dari sini
* [ ] 6.4 Deteksi bias & representasi tidak seimbang dalam dataset berlabel, serta dampaknya ke model yang dilatih di atasnya
* [ ] 6.5 Validasi skema & continuous data quality checks — menjadikan quality check bagian dari pipeline (mirip semangat automated testing), bukan review manual sesekali

Mini Project: Bangun label function (weak supervision) untuk 1 task klasifikasi teks, bandingkan hasilnya dengan ground truth dari Module 3, plus jalankan deteksi near-duplicate pada dataset gabungan Module 2-3 untuk menemukan kebocoran data.

Status: Not Started

---

## Module 7 — LLM Evaluation & Eval Data Infrastructure

Description: `courses/llm` Module 8 mengajarkan *cara* mengevaluasi (metric, LLM-as-judge, human eval). Modul ini mengajarkan cara membangun *infrastruktur* yang menjalankan evaluasi itu berulang, terversi, dan bisa dipercaya — layer platform di atasnya.

Lessons:

* [ ] 7.1 Golden dataset: kurasi, versioning, dan menjaga agar tidak "bocor" ke data training (menyambung Module 6.3)
* [ ] 7.2 Eval harness sebagai service (pola OpenAI Evals): eval-as-code, registry benchmark, menjalankan eval otomatis pada tiap perubahan model/prompt
* [ ] 7.3 Human eval platform: mengelola panel evaluator manusia untuk task yang tidak bisa dinilai otomatis (menyambung tooling Module 3-4, bukan mengulang)
* [ ] 7.4 Menyimpan & menyajikan hasil eval: dashboard perbandingan model/versi dari waktu ke waktu, regression detection
* [ ] 7.5 Automated eval untuk agentic/multi-step system (rujuk `courses/agents/roadmap.md` untuk cara mengevaluasi agent itu sendiri; modul ini fokus pada infrastruktur penyimpanan & orkestrasi eval run-nya)

Mini Project: Bangun eval harness minimal yang menjalankan set golden dataset terhadap 1 model/prompt, menyimpan hasil skor terversi, dan menampilkan perbandingan antar-run dalam laporan sederhana.

Status: Not Started

---

## Module 8 — Data Infra sebagai API & Platform Engineering

Description: Modul paling "software engineer" di course ini — mengekspos seluruh kapabilitas Module 2-7 sebagai service/API production, terintegrasi dengan stack repo ini (Spring Boot/PostgreSQL/Redis/Kafka), bukan lagi script/notebook.

Lessons:

* [ ] 8.1 Desain API untuk platform data AI: ingestion API, labeling task API, dataset versioning API (kontrak, pagination, idempotency — mengikuti `standards/API_STANDARDS.md` & `standards/OPENAPI_STANDARDS.md`)
* [ ] 8.2 Skema database untuk metadata dataset, task annotasi, dan hasil eval (`standards/DATABASE_STANDARDS.md`, `standards/SQL_STANDARDS.md`) — desain yang mendukung versioning & audit trail
* [ ] 8.3 Event-driven pipeline: memakai Kafka untuk alur ingestion → annotation → training-ready secara asynchronous (`standards/MESSAGING_STANDARDS.md`)
* [ ] 8.4 Caching & rate limiting untuk API labeling yang dipakai ribuan annotator/worker bersamaan (`standards/CACHE_STANDARDS.md`)
* [ ] 8.5 Keamanan API data sensitif: autentikasi annotator, access control per dataset, audit log akses data (`standards/SECURITY_STANDARDS.md`)

Mini Project: Implementasikan minimal 1 service Spring Boot yang mengekspos labeling task API (create task, assign, submit label, review) di atas PostgreSQL, dengan event Kafka yang dipublish saat task selesai.

**Production Discussion: Spring Boot Integration** — modul ini *adalah* implementasi Spring Boot-nya, bukan diskusi terpisah seperti di `courses/llm` — konsisten dengan fokus "implementasi untuk software engineer" course ini.

Status: Not Started

---

## Module 9 — Production Data Platform Architecture

Description: Menyatukan semua komponen jadi arsitektur platform yang bisa beroperasi di skala production — storage, orkestrasi, observability, dan kepatuhan privasi data manusia.

Lessons:

* [ ] 9.1 Arsitektur storage: object storage (raw data) + metadata database (Module 8.2) + vector store (untuk similarity search/dedup Module 6.3) — kapan masing-masing dipakai
* [ ] 9.2 Orkestrasi pipeline data skala besar: overview Airflow/Kafka Streams untuk menjalankan loop Data Engine secara terjadwal/event-driven (konsep, tidak mendalam seperti course orkestrasi khusus)
* [ ] 9.3 Observability untuk data pipeline: tracing lineage data dari sumber sampai training set, alerting saat volume/kualitas anomali (`standards/OBSERVABILITY_STANDARDS.md`)
* [ ] 9.4 Keamanan & privasi data manusia: PII handling di data annotasi (mendalami Module 2.5), retention policy, hak penghapusan data (relevan bila menyentuh GDPR-like regulation)
* [ ] 9.5 Cost engineering: biaya labeling manusia vs weak supervision vs synthetic data, kapan trade-off tiap pendekatan masuk akal secara ekonomi

Mini Project: Tulis 1 dokumen arsitektur (architecture decision record) untuk platform data AI dari Module 1-8: diagram storage, alur data lineage, dan keputusan trade-off cost/quality yang diambil beserta alasannya.

**Production Discussion: Spring Boot Integration** — pola umum: service Spring Boot dari Module 8 sebagai control plane platform, dengan worker Python/Java terpisah untuk model-assisted pre-labeling, disatukan lewat Kafka — mirip pola `courses/llm/roadmap.md` Module 9.

Status: Not Started

---

## Module 10 — Menjadi AI Data Infrastructure Engineer yang Dipercaya

Description: Level di mana Anda bisa memimpin keputusan arsitektur platform data AI, bukan cuma mengimplementasikan potongan-potongannya.

Lessons:

* [ ] 10.1 Studi kasus: bagaimana Scale AI, Labelbox, OpenAI, dan Anthropic membangun data engine mereka masing-masing — pola yang sama, keputusan yang berbeda
* [ ] 10.2 Menyusun System Design Doc untuk platform data AI (trade-off arsitektur, estimasi biaya, rencana skalabilitas)
* [ ] 10.3 Membangun budaya data quality dalam tim (feedback loop annotator, review berkala taxonomy, menghindari "data debt")
* [ ] 10.4 Roadmap belajar lanjutan (opsional): kapan perlu mendalami big data engineering penuh (Spark), MLOps penuh, atau riset RL untuk RLHF — dan ke course/sumber mana merujuk

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Full AI Data Infrastructure Simulation** — bangun 1 platform data AI end-to-end yang menggabungkan seluruh course: (1) pipeline ingestion multi-sumber dengan versioning (Module 2), (2) sistem annotasi dengan taxonomy, tooling, dan quality control terukur (Module 3), (3) workflow human-in-the-loop dengan task queue, routing, dan active learning loop (Module 4), (4) pipeline pengumpulan preference data ala RLHF untuk minimal 1 use case (Module 5), (5) data quality checks otomatis termasuk dedup & bias check (Module 6), (6) eval harness yang menjalankan golden dataset dan menyimpan hasil terversi (Module 7), (7) seluruhnya diekspos sebagai API Spring Boot production-grade (Module 8), (8) 1 halaman "Data Platform Architecture Doc" (Module 9-10).

Acceptance Criteria:

* Pipeline ingestion → annotation → HITL → eval berjalan end-to-end untuk minimal 1 dataset nyata, bukan simulasi terpisah-pisah
* Inter-annotator agreement terukur dan didokumentasikan (bukan diasumsikan "pasti konsisten")
* Active learning loop terbukti mengurangi jumlah item yang perlu dilabel manusia dibanding random sampling (ada perbandingan angka)
* API annotasi & dataset berjalan sebagai service Spring Boot dengan autentikasi, audit log, dan event Kafka
* Eval harness menghasilkan skor terukur & terversi antar-run, bukan penilaian subjektif satu kali
* Data Platform Architecture Doc menjelaskan trade-off storage, cost, dan privasi secara eksplisit
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Kenapa AI Data Infrastructure adalah disiplin sendiri
