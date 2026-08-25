# Overall Learning Roadmap (Index)

Ini adalah index tingkat repo dari seluruh course yang sedang/akan dijalani. Detail kurikulum per-course ada di `courses/<nama-course>/roadmap.md`.

---

# Master Goal (ditetapkan 2026-08-06)

**World-Class Backend Developer — dipercaya untuk freelance internasional (Upwork/Toptal/dll), remote full-time jadi opsi lanjutan setelah itu.**

* Level saat ini (self-assessed user): Sudah kerja profesional Java/Spring Boot, tapi fondasi terasa rapuh — banyak konsep dipahami setengah-setengah (arsitektur, system design, production best practice), bukan level "belum pernah coding".
* Semua course di repo ini (yang sudah ada maupun yang akan dibuat) adalah bagian dari payung goal ini, bukan jalur-jalur terpisah yang tidak berhubungan.

## Gap Analysis

9 course yang sudah ada (`agents`, `aws-api-integration`, `blockchain`, `code-review`, `design-patterns`, `kubernetes`, `llm`, `ml-fundamentals`, `react`) semuanya **mengasumsikan** fondasi Java/Spring Boot/system design sudah solid sebagai prasyarat — tidak ada yang membangun ulang fondasi itu sendiri. Untuk goal "world-class" + "dipercaya freelance luar negeri", ada 3 gap yang belum punya course:

1. **Backend Engineering Fundamentals Rebuild** (belum ada course) — Java mendalam (concurrency, JVM, memory model), Spring Boot production-grade (observability, resiliency, security, testing pyramid, bukan cuma bikin REST API), database mendalam (indexing, transaction isolation, query planning). Ini yang langsung menjawab "fondasi terasa rapuh".
2. **System Design Mastery** — **sekarang punya course**: `courses/system-design/roadmap.md` (dibuat 2026-08-06, lihat "Course Baru" di bawah). Menentukan level kepercayaan klien freelance untuk proyek besar, juga modal interview kalau nanti ke jalur full-time.
3. **Freelance & International Career Readiness** (belum ada course) — non-technical: komunikasi teknis dalam Bahasa Inggris, membangun profil/portofolio Upwork/Toptal, menulis proposal, client management, kontrak & pembayaran internasional, reputasi & rating. `ai-los/CAREER_MODE.md` (baru diaktifkan) menyediakan kerangka mentoring untuk ini, tapi belum ada course terstruktur.
4. **Agile Leadership / Scrum Master** (baru diidentifikasi 2026-08-06, di luar gap analysis awal) — **sekarang punya course**: `courses/scrum-master/roadmap.md`. Bukan gap teknis, tapi gap "bisa memimpin tim/proyek" yang juga relevan untuk kepercayaan klien freelance skala tim.
5. **Engineering Culture & Senior Engineer Craft** (baru diidentifikasi 2026-08-07, atas permintaan user "belajar cara berpikir, standar, kebiasaan, dan engineering culture ala Google/Meta/Amazon/Netflix/dll") — **sekarang punya draft course**: `courses/engineering-culture/roadmap.md` (menunggu approval). Bukan gap teknis maupun kepemimpinan tim, tapi gap "judgment & komunikasi tertulis ala senior engineer" (design doc, code review culture, blameless postmortem, mentoring, pengaruh tanpa otoritas) — layer yang melengkapi kedalaman teknis Backend Fundamentals & System Design, bukan menggantikannya.
6. **Career Reinvention & Active Job Search** (baru diidentifikasi 2026-08-13, atas permintaan user "Buat course memperbaiki karier: update CV dari 2017-2026, membangun LinkedIn profesional, personal branding, networking, portfolio, achievement, dan strategi aktif mencari peluang kerja") — **sekarang punya draft course**: `courses/career-reinvention/roadmap.md` (menunggu approval). Bukan gap kredensial (sudah `courses/credential-strategy`) maupun craft (sudah `courses/engineering-culture`), tapi gap "kemasan & distribusi": CV, LinkedIn, personal branding, portfolio narrative, networking, dan sistem eksekusi pencarian aktif — dual-purpose untuk full-time DAN freelance. Sengaja tidak mencakup sisi bisnis freelance murni (Upwork/Toptal profile, proposal, kontrak, pricing) yang tetap jadi sisa gap #3 di atas.

## Proposed Sequencing (usulan, menunggu approval)

1. **Backend Engineering Fundamentals Rebuild** — prioritas #1, jadi fondasi yang menopang semua course lain.
2. **Software & System Architecture** (`courses/system-design`, mengisi slot System Design Mastery) — lanjutan natural setelah fondasi kuat, idealnya mulai setelah/paralel dengan Backend Fundamentals Module 4 (Spring Core).
3. **Scrum Master** (`courses/scrum-master`) — **paralel**, bukan menunggu giliran — course ini non-coding (lihat Catatan Scope di roadmap-nya), sengaja dirancang tidak bersaing energi dengan course teknis manapun. Bisa dicicil kapan saja berdampingan dengan course lain di atas.
4. **Freelance & International Career Readiness** — bisa mulai paralel begitu ada portofolio awal untuk ditampilkan.
5. Course existing (`ml-fundamentals` → `llm` → `agents`, `kubernetes`, `aws-api-integration`, dst.) tetap lanjut sesuai prioritas yang sudah berjalan — jadi spesialisasi yang memperkuat portofolio "world-class", bukan diganti.

**Belum diputuskan — menunggu approval user:** urutan mulai (Backend Fundamentals dulu vs urutan lain), apakah `ml-fundamentals` yang sedang in-progress di-pause selama Fundamentals Rebuild atau tetap jalan paralel santai, dan kapan tepatnya mulai `courses/scrum-master` (roadmap sudah didraft 2026-08-06, menunggu approval).

**Update 2026-08-07:** `courses/backend-fundamentals/roadmap.md` dan `courses/system-design/roadmap.md` **disetujui isinya** — keduanya siap dimulai kapan saja, hanya urutan/pacing-nya yang masih menunggu keputusan user. Master Goal tetap freelance internasional (dikonfirmasi ulang 2026-08-07, tidak bergeser ke full-time perusahaan global).

---

# Course Aktif

**Update 2026-08-07:** Course aktif dipersempit jadi 2 atas permintaan user, untuk hemat pemakaian token. Semua course lain di bawah ini (termasuk yang sebelumnya In Progress) dipindah ke "Course Paused" — bukan dihentikan, tinggal minta "lanjut course X" untuk resume kapan saja. Rasional lengkap tiap course tetap ada di `courses/<nama>/roadmap.md` masing-masing dan di git history file ini.

* **Mastering Claude — dari 0 sampai Expert** — `courses/mastering-claude/roadmap.md` — Status: **Selesai (2026-08-16)** — 8/8 module + Enterprise Project (Capstone) tuntas, skor 90/100. Artifact final: `portofolio/mastering-claude-playbook.md`. Track paralel/praktis, dimulai 2026-08-06.
* **Character Development — Rendah Hati, Etos Kerja, dan Rasa Hormat, dari 0 sampai Expert** — `courses/character-development/roadmap.md` — Domain: character-development — Status: In Progress (Module 1, Lesson 1.1) — di-approve 2026-08-07.
* **Backend Engineering Fundamentals Rebuild** — `courses/backend-fundamentals/roadmap.md` — Status: In Progress (Module 1, Lesson 1.5 selesai skor 88/100 — Module 1 lessons tuntas semua, lanjut Mini Project Module 1) — **resume 2026-08-19 atas permintaan eksplisit user**, override kebijakan cap 2-course-aktif 2026-08-07 (slot Mastering Claude sudah selesai jadi user memilih isi dengan course ini alih-alih menganggap kosong).

# Course Disetujui — Siap Dimulai (belum ada lesson dimulai)

Rasional lengkap tiap course (kalibrasi, sumber riset, pemisahan dari course lain) ada di masing-masing "Catatan Scope" di `courses/<nama>/roadmap.md` — tidak diduplikasi di sini.

* **Software & System Architecture** — `courses/system-design/roadmap.md` — Status: Approved 2026-08-07, Not Started.
* **Automation & Scripting — dari 0 sampai Expert** — `courses/automation-scripting/roadmap.md` — Domain: software-engineering — Status: Approved 2026-08-08, Not Started.
* **Life Mastery — Personal Operating System** — `courses/life-mastery/roadmap.md` — Domain: life-mastery — Status: Approved 2026-08-11, Not Started — orkestrator 14-stop lintas course lain, lihat roadmap-nya sendiri untuk peta lengkap.
* **Systems Security & Malware Analysis** — `courses/systems-security-malware-analysis/roadmap.md` — Domain: software-engineering — Status: Approved 2026-08-11, Not Started — bagian dari `life-mastery` Stop 8.
* **Astronomy & Life in the Universe** — `courses/astronomy-and-life-in-the-universe/roadmap.md` — Domain: astronomy-and-life-in-the-universe — Status: Approved 2026-08-11, Not Started — bagian dari `life-mastery` Stop 9.

# Course Paused

*Dipause 2026-08-06 (alasan lama):*

* **Docker, Kubernetes & OpenShift** — `courses/kubernetes/roadmap.md` — Status: Paused (Module 1, Lesson 1.1 belum selesai) — di-pause saat user memilih mulai jalur AI. Kebutuhan kerja aktif, resume kapan saja diminta.

*Dipause 2026-08-07 untuk hemat token (semua "In Progress" sebelumnya, resume kapan saja atas permintaan user):*

* **Machine Learning & AI Fundamentals** — `courses/ml-fundamentals/roadmap.md` — Module 1 — jalur "full path" ml-fundamentals → llm → agents.
* **CI/CD Mastery — dari Permukaan ke Expert** — `courses/ci-cd/roadmap.md` — Module 1, Lesson 1.1 — tool utama GitLab CI.
* **Personal Finance — dari 0 sampai Punya Rencana Keuangan Lengkap** — `courses/personal-finance/roadmap.md` — Domain: personal-finance — Module 1, Lesson 1.1 — pilot domain non-SWE pertama untuk arsitektur universal AI-LOS.
* **n8n Automation — dari 0 sampai Bisa Bangun Asisten AI Pribadi ("Jarvis-lite")** — `courses/n8n-automation/roadmap.md` — Domain: n8n-automation — Module 1, Lesson 1.1.
* **Communication Skills — dari 0 sampai Lancar Berkomunikasi dengan Orang Baru** — `courses/communication-skills/roadmap.md` — Domain: communication-skills — Module 1, Lesson 1.1.
* **Critical Thinking & Problem Solving — dari 0 sampai Expert** — `courses/critical-thinking/roadmap.md` — Domain: critical-thinking — Module 1, Lesson 1.1.
* **World History & Historical Discernment — dari 0 sampai Expert** — `courses/world-history/roadmap.md` — Domain: world-history — Module 1, Lesson 1.1.
* **Gitar — dari 0 sampai Expert** — `courses/guitar/roadmap.md` — Domain: guitar — Module 1, Lesson 1.1.
* **Self-Love — dari 0 sampai Expert** — `courses/self-love/roadmap.md` — Domain: self-love — Module 1, Lesson 1.1.
* **Computational Thinking & Algoritma — dari 0 sampai Expert** — `courses/computational-thinking/roadmap.md` — Domain: software-engineering — Module 1, Lesson 1.1.
* **Film Criticism & Analysis — dari 0 sampai Expert** — `courses/film-criticism/roadmap.md` — Domain: film-criticism — Module 1, Lesson 1.1.
* **Bahasa Korea — dari 0 sampai Expert** — `courses/korean/roadmap.md` — Domain: korean — Module 1, Lesson 1.1.
* **Cryptocurrency — dari 0 sampai Expert** — `courses/cryptocurrency/roadmap.md` — Domain: cryptocurrency — Module 1, Lesson 1.1.

---

# Course Lain (belum dimulai / belum ada progres tercatat)

Rasional lengkap tiap course ada di "Catatan Scope" masing-masing `courses/<nama>/roadmap.md`.

* React — `courses/react/roadmap.md`
* LLM — `courses/llm/roadmap.md` (lanjutan setelah ml-fundamentals selesai, jalur "full path")
* Agents — `courses/agents/roadmap.md` (lanjutan setelah llm selesai, jalur "full path")
* Blockchain — `courses/blockchain/roadmap.md`
* Code Review — `courses/code-review/roadmap.md`
* Design Patterns — `courses/design-patterns/roadmap.md`
* AWS API Integration (Java/Spring Boot) — `courses/aws-api-integration/roadmap.md` — draft 2026-08-06, menunggu approval.
* **Generative AI & Workflow Design** — `courses/genai-workflow-design/roadmap.md` — Approved 2026-08-06, belum dimulai. 1/4 dari batch course 2026-08-06 (bersama 3 entri di bawah).
* **Data Analysis & Visualization** — `courses/data-analysis-visualization/roadmap.md` — Approved 2026-08-06, belum dimulai. 2/4 batch.
* **Cybersecurity** — `courses/cybersecurity/roadmap.md` — Approved 2026-08-06, belum dimulai. 3/4 batch.
* **Cloud Computing & DevOps Foundations** — `courses/cloud-devops-foundations/roadmap.md` — Approved 2026-08-06, belum dimulai. 4/4 batch.

# Course Baru (draft, menunggu approval)

Rasional lengkap tiap course (kalibrasi, pemisahan dari course lain, breakdown modul, sumber riset) ada di "Catatan Scope"/"Learning Objectives" masing-masing `courses/<nama>/roadmap.md` — tidak diduplikasi di sini. Domain baru yang disebut "belum terdaftar" statusnya tercatat di banner DRAFT domain file itu sendiri (`domains/<name>/DOMAIN.md`).

* **NGINX & Kubernetes Ingress** — `courses/nginx-kubernetes-ingress/roadmap.md` (draft 2026-08-13) — Domain: software-engineering — Status: Not Started, menunggu approval.
* **Scrum Master** — `courses/scrum-master/roadmap.md` (draft 2026-08-06) — Status: Not Started, menunggu approval.
* **Engineering Culture & Senior Engineer Craft** — `courses/engineering-culture/roadmap.md` (draft 2026-08-07) — Status: Not Started, menunggu approval.
* **API Engineering** — `courses/api-engineering/roadmap.md` (draft 2026-08-07) — Status: Not Started, menunggu approval & kalibrasi (belum ada sesi tanya-jawab kalibrasi user).
* **Observability Engineering** — `courses/observability/roadmap.md` (draft 2026-08-07) — Status: Not Started, menunggu approval & kalibrasi.
* **Performance Engineering** — `courses/performance-engineering/roadmap.md` (draft 2026-08-07) — Status: Not Started, menunggu approval & kalibrasi.
* **Content Creation (Faceless, Multi-Platform)** — `courses/content-creation/roadmap.md` (draft 2026-08-09) — Domain: content-creation (approved, registered paused) — Status: Not Started, menunggu approval user (tidak otomatis jadi "Course Aktif" ketiga).
* **Affiliate Marketing** — `courses/affiliate-marketing/roadmap.md` (draft 2026-08-09) — Domain: affiliate-marketing (approved, registered paused) — Status: **Approved 2026-08-09**, Not Started — paused mengikuti cap 2-course-aktif.
* **Functional Programming** — `courses/functional-programming/roadmap.md` (draft 2026-08-07) — Status: Not Started, menunggu approval & kalibrasi.
* **Product & Business Knowledge for Engineers** — `courses/business-knowledge/roadmap.md` (draft 2026-08-07) — Status: Not Started, menunggu approval & kalibrasi.
* **Masa Depan Software Engineering (2027–2030)** — `courses/future-of-software-engineering/roadmap.md` (draft 2026-08-07) — Status: Not Started, menunggu approval & kalibrasi.
* **Wirausaha & Penghasilan Sampingan (Weekend-Only)** — `courses/entrepreneurship/roadmap.md` (draft 2026-08-07) — Domain: entrepreneurship (draft, belum terdaftar) — Status: Not Started, menunggu approval user (domain pack maupun course roadmap).
* **Investasi & Saham** — `courses/investing-stocks/roadmap.md` (draft 2026-08-07) — Domain: investing-stocks (draft, belum terdaftar) — Status: Not Started, menunggu approval user (domain pack maupun course roadmap).
* **Psychology** — `courses/psychology/roadmap.md` — Domain: psychology — Status: Approved 2026-08-08, Not Started — paused mengikuti cap 2-course-aktif.
* **Bahasa Inggris** — `courses/english/roadmap.md` (approved 2026-08-08) — Domain: english — Status: Approved 2026-08-08, Not Started — paused mengikuti cap 2-course-aktif.
* **VPS (Virtual Private Server)** — `courses/vps/roadmap.md` (draft 2026-08-08) — Domain: software-engineering — Status: Approved 2026-08-08, Not Started — paused mengikuti cap 2-course-aktif.

## Batch 2026-08-09 — 15 Topik Baru (riset paralel, semua draft menunggu approval)

Atas permintaan user "ajarkan saya course berikut dari 0 sampai expert" untuk 15 topik sekaligus (Data Scientist, Data Analyst, Node.js, Rust, Microsoft Azure, DevOps, GCP, MLOps, Microservice Architecture, Android Development — Expert, Prompt Engineering, Oracle Database Administrator, Teknologi Nuklir, Psychology of Money, Atomic Habits). Kalibrasi user: (1) draft semua dulu sebagai roadmap paused, tidak ada yang langsung jadi "Course Aktif" — konsisten kebijakan hemat-token 2026-08-07; (2) 5 topik yang beririsan course existing **diperluas, bukan dibuat course baru**; (3) Psychology of Money & Atomic Habits digabung ke domain existing, bukan domain baru. 8 topik didraft sebagai course baru lewat riset paralel (subagent per topik), 5 topik dipetakan ke course existing, 2 topik jadi modul tambahan di domain existing.

**8 course baru (draft, menunggu approval, belum ada yang di-registrasi ke `ai-los/ACTIVE_DOMAIN.md`).** Rasional lengkap tiap course ada di "Catatan Scope" masing-masing `courses/<nama>/roadmap.md`:

* **Data Scientist** — `courses/data-scientist/roadmap.md` — Domain: software-engineering.
* **Node.js** — `courses/nodejs/roadmap.md` — Domain: software-engineering.
* **Rust** — `courses/rust/roadmap.md` — Domain: software-engineering.
* **Microsoft Azure** — `courses/azure/roadmap.md` — Domain: software-engineering.
* **Google Cloud Platform (GCP)** — `courses/gcp/roadmap.md` — Domain: software-engineering.
* **Android Development** — `courses/android-development/roadmap.md` — Domain: software-engineering.
* **Oracle Database Administrator** — `courses/oracle-dba/roadmap.md` — Domain: software-engineering.
* **Teknologi Nuklir** — `courses/nuclear-technology/roadmap.md` — Domain: nuclear-technology (draft, belum terdaftar).

**5 topik dipetakan ke course existing (tidak ada course baru dibuat):**

* **Data Analyst** → sudah 100% tercakup `courses/data-analysis-visualization/roadmap.md` (approved 2026-08-06, belum dimulai) — tidak perlu perubahan apa pun, course itu memang jalur karier Data Analyst/BI.
* **DevOps** → sudah tercakup gabungan `courses/cloud-devops-foundations` (budaya DevOps/CALMS, SRE, IaC) + `courses/ci-cd` (pipeline) + `courses/vps` (operasional server) + `courses/kubernetes` (orkestrasi) — keempatnya bersama-sama SUDAH menjadi jalur "DevOps" lengkap, tidak ada course baru dibuat.
* **Microservice Architecture** → sudah tercakup dalam gabungan `courses/system-design` Module 6/7/10 (keputusan monolith vs microservices, komunikasi antar-service/saga, event-driven Kafka) + `courses/api-engineering` (implementasi multi-protokol) — tidak ada course baru dibuat.
* **Prompt Engineering** → sudah tercakup mendalam di `courses/llm/roadmap.md` Module 3 ("Prompt Engineering Tingkat Produksi": few-shot/CoT/structured output/tool calling/ReAct/prompt injection defense) plus `courses/agents` Module 1/3 dan `courses/mastering-claude` Module 1 — tidak ada course baru dibuat.
* **Machine Learning Operations (MLOps)** → satu-satunya dari 5 topik ini yang kontennya masih tipis (sebelumnya cuma 1 bullet "overview, tidak dipraktikkan mendalam" di `ml-fundamentals` Module 10) — **diperluas nyata**: Module 11 baru "MLOps in Practice" ditambahkan ke `courses/ml-fundamentals/roadmap.md` (proposed extension, belum dihitung progress), mencakup MLflow tracking/registry, DVC data versioning, Continuous Training, model monitoring/drift detection, deployment pattern (shadow/canary). Riset: Google Cloud MLOps whitepaper resmi, MLflow & DVC docs resmi.

**2 topik jadi modul tambahan di domain existing** (rasional lengkap ada di masing-masing modul "Proposed Extension" di file course-nya):

* **Psychology of Money** → Module 1.5 baru di `courses/personal-finance/roadmap.md`.
* **Atomic Habits** → Module 7 baru (append) di `courses/character-development/roadmap.md`.

Semua 8 course baru + 3 proposed extension module di atas **menunggu approval user** sebelum ada yang mulai Lesson 1, sesuai `ai-los/COURSE_CREATION.md`. Tidak ada yang otomatis jadi "Course Aktif" ketiga — tetap 2 sesuai kebijakan hemat-token.

Rasional lengkap, sumber riset, house rule, dan breakdown modul tiap entri berikut ada di "Catatan Scope"/"Learning Objectives" masing-masing `courses/<nama>/roadmap.md` (dan `domains/<nama>/DOMAIN.md` untuk domain baru) — tidak diduplikasi di sini.

## 2026-08-12 — Belajar yang Menyenangkan (Learning Science)

* **Belajar yang Menyenangkan** — `courses/learning-science/roadmap.md` — Domain: learning-science (registered paused). Status: Draft, menunggu approval.
* **Ingatan Jangka Panjang (Memory Mastery)** — `courses/memory-mastery/roadmap.md` — Domain: memory-mastery (registered paused). Status: Draft, menunggu approval.

## 2026-08-12 — Researcher (Research Methods)

* **Researcher** — `courses/research-methods/roadmap.md` — Domain: research-methods (registered paused). Status: Draft, menunggu approval.

## 2026-08-12 — Storytelling

* **Storytelling** — `courses/storytelling/roadmap.md` — Domain: storytelling (registered paused). Status: Draft, menunggu approval.

## 2026-08-12 — Notulen & Meeting Intelligence

* **Notulen & Meeting Intelligence** — `courses/notulen-mastery/roadmap.md` — Domain: notulen-mastery (registered paused). Status: Draft, menunggu approval.

## 2026-08-12 — REST API Design (Spring Boot)

* **REST API Design (Spring Boot)** — `courses/rest-api-design/roadmap.md` — Domain: software-engineering. Status: Draft, menunggu approval.

## 2026-08-12 — Emotional Resilience

* **Emotional Resilience** — `courses/emotional-resilience/roadmap.md` — Domain: emotional-resilience (registered paused). Status: Draft, menunggu approval.

## 2026-08-12 — Intuition Training

* **Intuition Training & Calibrated Judgment** — `courses/intuition-training/roadmap.md` — Domain: intuition-training (registered paused). Status: Draft, menunggu approval.

## 2026-08-12 — Cinta Sehat (Relationships Extended)

* **Relationships & Cinta Sehat** — `courses/relationships/roadmap.md` (diperluas 2026-08-12 dari course lama "Relationships & Marriage Readiness") — Domain: relationships (registered paused). Status: Draft hasil extend, menunggu approval.

## 2026-08-14 — Middle-earth Legendarium

* **Middle-earth Legendarium** — `courses/middle-earth/roadmap.md` — Domain: middle-earth (registered paused). Status: Approved 2026-08-14, Not Started.

## 2026-08-09 — Football Tactical Analysis

* **Football Tactical Analysis** — `courses/football-tactics/roadmap.md` — Domain: football-tactics (registered paused). Status: Approved 2026-08-09, Not Started.

## 2026-08-12 — Strategi Kredensial & Bukti Kompetensi Profesional

* **Strategi Kredensial & Bukti Kompetensi Profesional** — `courses/credential-strategy/roadmap.md` — Domain: software-engineering. Mengisi sebagian gap "Freelance & International Career Readiness". Status: Draft, menunggu approval.

## 2026-08-13 — Realistic Manifestation

* **Realistic Manifestation** — `courses/realistic-manifestation/roadmap.md` — Domain: realistic-manifestation (registered paused). Status: Approved 2026-08-13, Not Started.

## 2026-08-13 — Chess Mastery

* **Chess Mastery** — `courses/chess-mastery/roadmap.md` — Domain: chess-mastery (registered paused). Status: Approved 2026-08-13, Not Started.

## 2026-08-13 — Career Reinvention & Active Job Search

* **Career Reinvention & Active Job Search** — `courses/career-reinvention/roadmap.md` — Domain: software-engineering. Bukan gap kredensial (`credential-strategy`) maupun craft (`engineering-culture`) — lapisan kemasan & distribusi (CV/LinkedIn/portfolio/networking). Status: Draft, menunggu approval.

## 2026-08-13 — Apresiasi Puisi Indonesia & Dunia

* **Apresiasi Puisi Indonesia & Dunia** — `courses/poetry-appreciation/roadmap.md` — Domain: poetry-appreciation (registered paused). Status: Draft, menunggu approval.

## 2026-08-13 — Perluasan Character Development: Validasi, Status, Spotlight Effect, Ketenangan Batin

* **Character Development (perluasan)** — Module 8 & 9 baru (append, tidak me-renumber Module 1 yang sedang In Progress) ditambahkan ke `courses/character-development/roadmap.md`; `domains/character-development/DOMAIN.md` diperbarui (Scope + Trusted Sources + house rule). Status: Draft, menunggu approval — tidak mengganggu progress Module 1 yang berjalan.

## 2026-08-13 — Sejarah Westeros

* **Sejarah Westeros** — `courses/westeros-history/roadmap.md` — Domain: westeros-history (registered paused). Status: Draft, menunggu approval.

## 2026-08-14 — Harry Potter & Dunia Sihir

* **Harry Potter & Dunia Sihir** — `courses/harry-potter/roadmap.md` — Domain: harry-potter (registered paused). Status: Approved 2026-08-14, Not Started.

## 2026-08-14 — Anti-Scam & Risk Management

* **Anti-Scam & Risk Management** — `courses/anti-scam-risk-management/roadmap.md` — Domain: anti-scam-risk-management (registered paused). Status: Approved 2026-08-14, Not Started.

## 2026-08-14 — Warm Charisma

* **Warm Charisma** — `courses/warm-charisma/roadmap.md` — Domain: warm-charisma (draft, belum terdaftar). Status: Draft, menunggu approval.

## 2026-08-16 — Bulk 10-Course Draft Request (background agent, catatan: dibuat sambil Course Aktif "Character Development" masih di bawah 50% Module 1/6 — user eksplisit minta lanjut, override guard `ai-los/COURSE_CREATION.md`)

Semua 10 di-drafting paralel via background agent (riset + roadmap + domain pack kalau perlu), tidak ada yang mulai Lesson 1, tidak ada yang ditulis ke Obsidian. Rasional lengkap kalibrasi/sumber riset/pemisahan dari course lain ada di masing-masing "Catatan Scope" di `courses/<nama>/roadmap.md`.

* **Kesehatan Intim Pria — dari 0 sampai Expert** — `courses/mens-health/roadmap.md` — Domain: mens-health (registered paused) — Status: Draft 2026-08-16, menunggu approval — 12 modul, framing klinis/edukatif, Module 11 dedicated "Red Flags — Kapan Harus ke Dokter".
* **Dopamine — dari 0 sampai Expert** — `courses/dopamine/roadmap.md` — Domain: dopamine (registered paused) — Status: Draft 2026-08-16, menunggu approval — 14 modul + 2 Intermediate Project checkpoint, neuroscience reward system s/d digital habits.
* **Director's Signature — dari 0 sampai Expert** — `courses/directors-signature/roadmap.md` — Domain: film-criticism (registered paused, course kedua di domain ini) — Status: Draft 2026-08-16, menunggu approval — 12 modul, auteur theory + case study 8 sutradara, tidak overlap dengan course film-criticism existing (craft/directing di luar scope).
* **Statistika & Probabilitas — dari 0 sampai Expert** — `courses/statistics-probability/roadmap.md` — Domain: software-engineering — Status: Draft 2026-08-16, menunggu approval — 12 modul, fondasi matematika (proof/derivasi) di bawah `ml-fundamentals` Module 4 dan `data-analysis-visualization` Module 4.
* **World-Class Tech Builder — dari 0 sampai Expert** — `courses/tech-builder-mindset/roadmap.md` — Domain: tech-builder-mindset (registered paused) — Status: Draft 2026-08-16, menunggu approval — 10 modul + capstone, studi pola pikir Zuckerberg/Alexandr Wang/Karpathy/dll, dipisahkan eksplisit dari `engineering-culture` dan `career-reinvention`.
* **AGI (Artificial General Intelligence) — dari 0 sampai Expert** — `courses/agi/roadmap.md` — Domain: software-engineering — Prerequisites: `ml-fundamentals` + `llm` + `agents` — Status: Draft 2026-08-16, menunggu approval — 12 modul + Enterprise Project, capstone/synthesis di atas jalur "full path" applied AI, zero overlap (ada peta no-duplication di Catatan Scope).
* **Computer Vision — dari 0 sampai Expert** — `courses/computer-vision/roadmap.md` — Domain: software-engineering — Status: Draft 2026-08-16, menunggu approval — 12 modul, classical CV sampai vision transformer/multimodal/deployment.
* **String Theory for Software Engineers — dari 0 sampai Expert** — `courses/string-theory-for-engineers/roadmap.md` — Domain: string-theory-for-engineers (registered paused) — Status: Draft 2026-08-16, menunggu approval — 15 modul, QM dibangun dari nol, GR jadi bridge rujuk ke `courses/relativity`, tidak overlap `physics-for-software-engineers` (yang eksplisit exclude relativitas & QM).
* **Quantum Theory for Software Engineers — dari 0 sampai Expert** — `courses/quantum-computing-for-engineers/roadmap.md` — Domain: quantum-computing-for-engineers (registered paused) — Status: Draft 2026-08-16, menunggu approval — 13 modul, linear algebra kompleks s/d Shor/Grover, toolchain Python+Qiskit, domain terpisah dari `physics-for-software-engineers` (yang eksplisit exclude quantum).
* **World Traveler — dari 0 sampai Expert** — `courses/world-traveler/roadmap.md` — Domain: world-traveler (registered paused) — Status: Draft 2026-08-16, menunggu approval — 11 modul (framework + 9 region × 2 negara + capstone simulasi trip), dipisahkan eksplisit dari `world-history` (historical discernment vs cultural/practical fluency).

## 2026-08-20 — Robotics (permintaan langsung, override guard Course Creation Discipline)

* **Robotics — dari 0 sampai Expert untuk Software Engineer** — `courses/robotics/roadmap.md` — Domain: software-engineering — Status: Draft 2026-08-20, menunggu approval — 14 modul + Capstone: fondasi matematika ruang 3D → kinematika (manipulator + mobile robot) → dinamika/kontrol PID → sensor → aktuator/embedded (ESP32/STM32, RTOS) → ROS2 fundamentals → pemodelan/simulasi (URDF/Gazebo) → sensor fusion (EKF/particle filter) → SLAM → path planning (Nav2) → computer vision terapan → RL/imitation learning → simulasi lanjut (Isaac Sim/MuJoCo) → integrasi full-stack autonomous robot (safety/observability/production). **Dibuat sambil kedua Course Aktif ("Character Development" Module 1/Lesson 1.1, "Backend Fundamentals" Module 1/Lesson 1.3) masih di bawah 50% — user eksplisit override guard `ai-los/COURSE_CREATION.md` saat ditanya konfirmasi.** Batas cakupan eksplisit vs course existing (tidak ada duplikasi modul) ada di `courses/robotics/roadmap.md` bagian "Catatan Scope": vs `courses/physics-for-software-engineers` (Not Started — course itu hanya 2D robot kinematics dasar + PID intro sebagai 1 dari banyak topik fisika, course ini spesialisasi vertikal 3D penuh), vs `courses/computer-vision` (Module 11 di sini sempit ke aplikasi robotika saja — kalibrasi kamera/visual servoing/fiducial marker, tidak mengulang breadth classification/detection/segmentation), dan RL (Module 12) jadi pengantar RL pertama di repo ini (tidak ada di `ml-fundamentals`/`neural-network`).

## 2026-08-20 — Virtual Journey Indonesia (permintaan langsung, override guard Course Creation Discipline)

* **Virtual Journey Indonesia — dari 0 sampai Expert** — `courses/virtual-journey-indonesia/roadmap.md` — Domain: virtual-journey-indonesia (registered paused, domain baru) — Status: Draft 2026-08-20, menunggu approval — 9 modul + Capstone (25 lesson konten + 4 lesson capstone + 3 Intermediate Project checkpoint): Module 1 framework 3-lapis (Hard Facts/Soft Culture/Practical & Kehidupan Lokal) dikalibrasi ke DKI Jakarta → Module 2-8 menjelajahi SELURUH 38 provinsi resmi Indonesia (termasuk hasil pemekaran Papua 2022) dikelompokkan per 7 region (Sumatera 10, Jawa 6, Kalimantan 5, Sulawesi 6, Bali & Nusa Tenggara 3, Maluku 2, Papua 6 — total 38 terverifikasi) dengan batch 2-3 provinsi per lesson plus satu provinsi "sorotan" mendalam tiap lesson → 3 Intermediate Project checkpoint (Sumatera & Jawa; Kalimantan & Sulawesi; Bali-Nusa Tenggara, Maluku, & Papua) → Module 9 Capstone "Nusantara Explorer Portfolio" (Comparative Cultural Map lintas minimal 5 provinsi & 4 region + itinerary pendidikan-budaya + refleksi personal). Module 8 (Papua) membawa catatan sensitivitas eksplisit: netral secara etnografi/budaya/alam, menghindari posisi politik soal status/konflik Papua, data demografis dari sumber resmi (BPS/Kemendikbudristek). **Dibuat sambil kedua Course Aktif ("Character Development" Module 1/Lesson 1.1, "Backend Engineering Fundamentals Rebuild" Module 1/Lesson 1.3) masih di bawah 50% — user eksplisit override guard `ai-los/COURSE_CREATION.md` saat ditanya konfirmasi.** Domain baru (bukan overlap dengan `world-traveler` atau `world-history`) — rasional pemisahan lengkap ada di `courses/virtual-journey-indonesia/roadmap.md` bagian "Catatan Scope": vs `world-traveler` (representative sampling lintas ~195 negara dunia, course ini sebaliknya BOUNDED eksklusif 38 provinsi Indonesia dengan kedalaman jauh lebih tinggi per unit, wajib ekshaustif bukan sampel), vs `world-history` (historical discernment kronologis-akademik lintas era dunia, course ini praktis-eksperiensial per-wilayah Indonesia dengan sejarah hanya secukupnya sebagai konteks budaya).

## 2026-08-16 — Antimatter (permintaan langsung, override guard Course Creation Discipline)

* **Antimatter — dari 0 sampai Expert** — `courses/antimatter/roadmap.md` — Domain: antimatter (registered paused, domain baru) — Status: Draft 2026-08-16, menunggu approval — 14 modul (13 konten + capstone): relativitas/QM secukupnya → Model Standard → persamaan Dirac & prediksi antimateri → penemuan positron/antiproton → charge conjugation/CPT → anihilasi & positronium → produksi pasangan → fasilitas CERN AD/ELENA → penyimpanan (Penning trap, ALPHA) → gravitasi antimateri (ALPHA-g 2023) → baryogenesis & pelanggaran CP (riset terbuka) → aplikasi nyata (PET scan) vs klaim propulsi spekulatif. **Dibuat sambil Course Aktif "Character Development" masih di bawah 50% Module 1/Lesson 1.1 — user eksplisit override guard `ai-los/COURSE_CREATION.md` saat ditanya konfirmasi.** Domain baru (bukan overlap dengan `nuclear-technology`, `relativity`, `astronomy-and-life-in-the-universe`, atau `quantum-computing-for-engineers` — lihat rasional lengkap di `domains/antimatter/DOMAIN.md` bagian "Registration History").

# Course Belum Dibuat (gap dari Master Goal)

* Freelance & International Career Readiness (sisa gap, dipersempit) — profil Upwork/Toptal, proposal writing, client management, kontrak & pembayaran internasional, reputasi/rating — belum ada folder `courses/`. Sisi kredensial-nya (sertifikasi/TOEFL/portfolio) sudah dipecah jadi `courses/credential-strategy/roadmap.md` (entri 2026-08-12), sisi kemasan/distribusi umum (CV/LinkedIn/personal branding/portfolio/networking/job search) sudah dipecah jadi `courses/career-reinvention/roadmap.md` (entri 2026-08-13 di atas) — sisa murni operasional bisnis freelance (Upwork/Toptal profile, proposal, kontrak, pricing) — bisa mulai paralel begitu ada portofolio awal

---

# Prioritas Saat Ini

**Update 2026-08-07 (hemat token):** hanya 2 course aktif sekarang — lihat "Course Aktif" di atas.

1. **Mastering Claude** (`courses/mastering-claude`) — **Selesai 2026-08-16** (8/8 module + Enterprise Project). Slot "course aktif" ke-2 kosong — resume course paused atau course baru kapan saja diminta.
2. **Character Development** (`courses/character-development`) — lanjutkan Module 1, Lesson 1.1.

Semua prioritas lain (Backend Fundamentals, ML/LLM/Agents, Kubernetes, System Design, Scrum Master, Engineering Culture, Freelance Readiness, dll.) dipause bersama course-nya masing-masing di atas — resume kapan saja atas permintaan user, urutan lama tetap tercatat di git history file ini.
