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

## Proposed Sequencing (usulan, menunggu approval)

1. **Backend Engineering Fundamentals Rebuild** — prioritas #1, jadi fondasi yang menopang semua course lain.
2. **Software & System Architecture** (`courses/system-design`, mengisi slot System Design Mastery) — lanjutan natural setelah fondasi kuat, idealnya mulai setelah/paralel dengan Backend Fundamentals Module 4 (Spring Core).
3. **Scrum Master** (`courses/scrum-master`) — **paralel**, bukan menunggu giliran — course ini non-coding (lihat Catatan Scope di roadmap-nya), sengaja dirancang tidak bersaing energi dengan course teknis manapun. Bisa dicicil kapan saja berdampingan dengan course lain di atas.
4. **Freelance & International Career Readiness** — bisa mulai paralel begitu ada portofolio awal untuk ditampilkan.
5. Course existing (`ml-fundamentals` → `llm` → `agents`, `kubernetes`, `aws-api-integration`, dst.) tetap lanjut sesuai prioritas yang sudah berjalan — jadi spesialisasi yang memperkuat portofolio "world-class", bukan diganti.

**Belum diputuskan — menunggu approval user:** urutan mulai (Backend Fundamentals dulu vs urutan lain), apakah `ml-fundamentals` yang sedang in-progress di-pause selama Fundamentals Rebuild atau tetap jalan paralel santai, dan kapan tepatnya mulai `courses/system-design` & `courses/scrum-master` (roadmap keduanya sudah didraft 2026-08-06, menunggu approval roadmap sekaligus keputusan mulai).

---

# Course Aktif

* **Machine Learning & AI Fundamentals** — `courses/ml-fundamentals/roadmap.md` — Status: In Progress (Module 1) — di-approve & dimulai 2026-08-06
* **Mastering Claude — dari 0 sampai Expert** — `courses/mastering-claude/roadmap.md` — Status: In Progress (2/8 module selesai) — track paralel/praktis (bukan jalur utama), dimulai 2026-08-06 dari kebutuhan langsung user; Module 3-5 (Claude Code Mendalam, Skills & Plugins, Claude Cowork) baru diriset & disusun, menunggu approval user untuk mulai; Module 7-8 (Vibe Coding: filosofi, risiko, spec-driven development, agentic engineering) ditambahkan 2026-08-06 atas permintaan user ("belajar vibe coding dari 0 sampai expert"), digabung ke course ini alih-alih course terpisah, juga menunggu approval
* **CI/CD Mastery — dari Permukaan ke Expert** — `courses/ci-cd/roadmap.md` — Status: In Progress (Module 1, Lesson 1.1) — dimulai 2026-08-06 atas permintaan langsung user ("tahu CI/CD hanya kulitnya"); tool utama GitLab CI, konsep universal tetap diajarkan generik dulu; mengisi gap DevOps yang belum tercakup di Gap Analysis awal, beririsan dengan `courses/kubernetes` (deploy target) yang sedang paused

# Course Paused

* **Docker, Kubernetes & OpenShift** — `courses/kubernetes/roadmap.md` — Status: Paused (Module 1, Lesson 1.1 belum selesai) — di-pause 2026-08-06 saat user memilih mulai jalur AI. Kebutuhan kerja aktif, resume kapan saja diminta.

---

# Course Lain (belum dimulai / belum ada progres tercatat)

* React — `courses/react/roadmap.md`
* LLM — `courses/llm/roadmap.md` (lanjutan setelah ml-fundamentals selesai, jalur "full path")
* Agents — `courses/agents/roadmap.md` (lanjutan setelah llm selesai, jalur "full path")
* Blockchain — `courses/blockchain/roadmap.md`
* Code Review — `courses/code-review/roadmap.md`
* Design Patterns — `courses/design-patterns/roadmap.md`
* AWS API Integration (Java/Spring Boot) — `courses/aws-api-integration/roadmap.md` (draft baru 2026-08-06, menunggu approval — user sudah tahu AWS di level console, belum pernah integrasi API/SDK)
* **Backend Engineering Fundamentals Rebuild** — `courses/backend-fundamentals/roadmap.md` (draft baru 2026-08-06, menunggu approval — fondasi #1 Master Goal, 15 modul, kalibrasi: semua area rapuh merata, pace sedang 5-10 jam/minggu, ML/AI tetap jalan paralel santai. Module 7 "Advanced SQL & Reporting Query Patterns" ditambahkan 2026-08-06 atas permintaan user setelah melihat pola industri reporting API berbasis CTE/window function)

# Course Baru (draft 2026-08-06, menunggu approval)

* **Software & System Architecture** — `courses/system-design/roadmap.md` — mengisi slot "System Design Mastery" di Gap Analysis (bukan course terpisah lagi). Scope dikalibrasi ke Software/System Architecture single-system (bukan Enterprise/Solution Architecture penuh): layering, Clean/Hexagonal Architecture, DDD, monolith-vs-microservices, komunikasi antar-service, data-at-scale (sharding/CAP), resiliency, event-driven architecture, API/deployment/security architecture, ditutup system design interview practice + capstone. 14 modul. Status: Not Started, roadmap menunggu approval.
* **Scrum Master** — `courses/scrum-master/roadmap.md` — course baru terpisah (bukan digabung ke Software & System Architecture, sesuai kalibrasi user). Scrum Guide 2020, peran Scrum Master (servant leadership, 4 stance), fasilitasi event, backlog & estimasi, coaching & fasilitasi tim, metrik Agile, Kanban, scaling framework, Agile enterprise, tooling (Jira, pakai `templates/JIRA_TASK_TEMPLATE.md`), persiapan PSM I. 12 modul, non-coding — dirancang jalan paralel tanpa bersaing energi dengan course teknis. Status: Not Started, roadmap menunggu approval.
* **Personal Finance — dari 0 sampai Punya Rencana Keuangan Lengkap** — `courses/personal-finance/roadmap.md` — Domain: personal-finance — dibuat sebagai pilot domain non-SWE pertama, lihat `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md`. Status: Not Started, roadmap menunggu approval.

# Course Belum Dibuat (gap dari Master Goal)

* Freelance & International Career Readiness — belum ada folder `courses/`, lihat Gap Analysis di atas — bisa mulai paralel begitu ada portofolio awal

---

# Prioritas Saat Ini

1. **Backend Engineering Fundamentals Rebuild** (usulan prioritas baru #1, 2026-08-06, menunggu approval final) — fondasi untuk Master Goal "World-Class Backend Developer"
2. ML & AI Fundamentals → LLM → Agents (jalur belajar AI yang dipilih user 2026-08-05, di-approve & dimulai 2026-08-06) — status paralel/pause menunggu keputusan user
3. Docker, Kubernetes & OpenShift — paused, kebutuhan kerja aktif tetap ada + target sertifikasi CKAD/EX280, resume setelah AI path cukup jauh atau atas permintaan user
4. AWS API Integration — urutan belum ditentukan user (2026-08-06)
5. **Software & System Architecture** (`courses/system-design`) — menyusul setelah Backend Fundamentals Rebuild dimulai, mengisi slot System Design Mastery
6. **Scrum Master** (`courses/scrum-master`) — paralel, bisa mulai kapan saja begitu roadmap di-approve, tidak menunggu giliran course teknis
7. Freelance & International Career Readiness — menyusul setelah Backend Fundamentals Rebuild dimulai
