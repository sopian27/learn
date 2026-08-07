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

* **Mastering Claude — dari 0 sampai Expert** — `courses/mastering-claude/roadmap.md` — Status: In Progress (2/8 module selesai) — track paralel/praktis, dimulai 2026-08-06; Module 3-5 & 7-8 sudah diriset, menunggu approval user untuk mulai.
* **Character Development — Rendah Hati, Etos Kerja, dan Rasa Hormat, dari 0 sampai Expert** — `courses/character-development/roadmap.md` — Domain: character-development — Status: In Progress (Module 1, Lesson 1.1) — di-approve 2026-08-07.

# Course Disetujui — Siap Dimulai (belum ada lesson dimulai)

* **Software & System Architecture** — `courses/system-design/roadmap.md` — Status: Approved 2026-08-07, Not Started — mengisi slot "System Design Mastery" di Master Goal, 14 modul (arsitektur, DDD, monolith-vs-microservices, data-at-scale, resiliency, event-driven, system design interview). Idealnya mulai setelah/paralel Backend Fundamentals Module 4.

# Course Paused

*Dipause 2026-08-06 (alasan lama):*

* **Docker, Kubernetes & OpenShift** — `courses/kubernetes/roadmap.md` — Status: Paused (Module 1, Lesson 1.1 belum selesai) — di-pause saat user memilih mulai jalur AI. Kebutuhan kerja aktif, resume kapan saja diminta.

*Dipause 2026-08-07 untuk hemat token (semua "In Progress" sebelumnya, resume kapan saja atas permintaan user):*

* **Backend Engineering Fundamentals Rebuild** — `courses/backend-fundamentals/roadmap.md` — Module 1, Lesson 1.1 — fondasi #1 Master Goal "World-Class Backend Developer".
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

* React — `courses/react/roadmap.md`
* LLM — `courses/llm/roadmap.md` (lanjutan setelah ml-fundamentals selesai, jalur "full path")
* Agents — `courses/agents/roadmap.md` (lanjutan setelah llm selesai, jalur "full path")
* Blockchain — `courses/blockchain/roadmap.md`
* Code Review — `courses/code-review/roadmap.md`
* Design Patterns — `courses/design-patterns/roadmap.md`
* AWS API Integration (Java/Spring Boot) — `courses/aws-api-integration/roadmap.md` (draft baru 2026-08-06, menunggu approval — user sudah tahu AWS di level console, belum pernah integrasi API/SDK)
* **Generative AI & Workflow Design** — `courses/genai-workflow-design/roadmap.md` — roadmap **approved 2026-08-06**, belum dimulai. Course pertama dari 4 course baru yang diminta user 2026-08-06 ("ajarkan saya Generative AI & Workflow Design, Data Analysis & Visualization, Cybersecurity, Cloud Computing & DevOps, dari 0 sampai expert, course terpisah"), dikerjakan satu per satu sesuai urutan yang diminta. Layer *desain* (tool-agnostic): analisis proses, keputusan automate/augment/human-only, pemetaan BPMN as-is/to-be, human-in-the-loop, AI-step design, ROI, sampai mengemas jadi jasa consulting/freelance — sengaja tidak tumpang tindih dengan `courses/llm` (mekanisme LLM), `courses/agents` (implementasi agent), maupun `courses/n8n-automation` (implementasi tool-specific di n8n, dirujuk untuk prototyping). Tujuan: consulting/freelance service skill (kalibrasi user), track paralel/independen dari Master Goal, sama seperti personal-finance/n8n-automation/dst. 8 modul.
* **Data Analysis & Visualization** — `courses/data-analysis-visualization/roadmap.md` — roadmap **approved 2026-08-06**, belum dimulai. Course kedua dari 4 course baru (lihat entri di atas). Standalone Data Analyst/BI skill: SQL analytics (window function/CTE, terhubung ke `courses/backend-fundamentals` Module 7 tanpa mengulang), Pandas data wrangling mendalam, statistik terapan untuk bisnis, prinsip visualisasi (Storytelling with Data), Power BI + DAX, komunikasi insight, sampai persiapan interview Data Analyst/BI Analyst — sengaja lebih dalam & independen dari `courses/ml-fundamentals` Module 1 (yang hanya prasyarat ringan untuk ML). 9 modul.
* **Cybersecurity** — `courses/cybersecurity/roadmap.md` — roadmap **approved 2026-08-06**, belum dimulai. Course ketiga dari 4 course baru (lihat entri di atas). Angle AppSec/backend-engineer-focused (kalibrasi user, bukan generalist Security+ maupun offensive/pentesting OSCP): threat modeling STRIDE, OWASP Top 10:2025 (termasuk kategori baru Software Supply Chain Failures & Mishandling of Exceptional Conditions), auth/session security, authorization & OWASP API Security Top 10, injection & data sensitif, supply chain security (SLSA/SBOM), cloud/infra security, security code review (OWASP ASVS), incident response — dibangun di atas `standards/SECURITY_STANDARDS.md` (bukan mengulang) dan merujuk (bukan menduplikasi) implementasi di `courses/ci-cd`/`courses/aws-api-integration`/`courses/kubernetes`. 10 modul.
* **Cloud Computing & DevOps Foundations** — `courses/cloud-devops-foundations/roadmap.md` — roadmap **approved 2026-08-06**, belum dimulai. Course keempat/terakhir dari 4 course baru yang diminta user 2026-08-06 — semua 4 course kini sudah didraft & disetujui. Lapisan konseptual provider-agnostic di bawah `courses/aws-api-integration`/`courses/kubernetes`/`courses/ci-cd` (tidak mengubah/menduplikasi ketiganya): model layanan cloud IaaS/PaaS/SaaS, perbandingan konseptual AWS/Azure/GCP, IaC dengan **Terraform** (kontras sengaja dengan AWS CDK di aws-api-integration), budaya DevOps (CALMS, Three Ways), observability sebagai disiplin SRE umum (SLI/SLO/error budget Google SRE — payung di atas treatment spesifik-tool di kubernetes/ci-cd), incident management untuk insiden reliabilitas umum (payung di atas incident response spesifik-keamanan di `courses/cybersecurity`), cost & governance. 9 modul.

# Course Baru (draft, menunggu approval)

* **Scrum Master** — `courses/scrum-master/roadmap.md` (draft 2026-08-06) — course terpisah non-coding. Scrum Guide 2020, peran Scrum Master (servant leadership, 4 stance), fasilitasi event, backlog & estimasi, coaching & fasilitasi tim, metrik Agile, Kanban, scaling framework, Agile enterprise, tooling (Jira, pakai `templates/JIRA_TASK_TEMPLATE.md`), persiapan PSM I. 12 modul — dirancang jalan paralel tanpa bersaing energi dengan course teknis. Status: Not Started, roadmap menunggu approval.
* **Engineering Culture & Senior Engineer Craft** — `courses/engineering-culture/roadmap.md` (draft 2026-08-07, atas permintaan user "belajar cara berpikir, standar, kebiasaan, engineering culture ala Google/Meta/Amazon/Netflix/dll, jadi Senior Software Engineer") — melengkapi (bukan mengganti) `courses/backend-fundamentals` & `courses/system-design` yang sudah disetujui. 9 modul: engineering judgment/decision-making, design doc & RFC, code review culture, incident response & blameless postmortem, technical debt & prioritas, feedback/mentoring/pengaruh tanpa otoritas, bekerja dengan ambiguitas & estimasi jujur, studi kasus budaya Netflix/Amazon/Google/Stripe/Shopify/Cloudflare, capstone portofolio "cara kerja" untuk klien freelance. Sengaja tidak membahas leveling/promo ladder korporat karena target tetap freelance internasional (dikonfirmasi 2026-08-07), bukan full-time. Status: Not Started, roadmap menunggu approval.
* **API Engineering** — `courses/api-engineering/roadmap.md` (draft 2026-08-07) — salah satu dari 6 gap yang teridentifikasi saat user meminta daftar besar 18-kategori topik ("SE Fundamentals sampai Masa Depan 2027-2030") dipetakan ke course yang sudah ada; hampir semua topik sudah tercakup, 6 ini murni yang belum. Lapisan implementasi hands-on multi-protokol (REST lanjut, gRPC, GraphQL, WebSocket, rate limiting, API Gateway) di bawah `courses/system-design` (level keputusan) dan lanjutan `courses/backend-fundamentals` Module 5. 8 modul, capstone: satu domain diekspos lewat REST+gRPC+WebSocket di belakang gateway. Status: Not Started, roadmap draft menunggu approval & kalibrasi (belum ada sesi tanya-jawab kalibrasi user, asumsi tercatat di "Catatan Scope" file roadmap-nya).
* **Observability Engineering** — `courses/observability/roadmap.md` (draft 2026-08-07) — bagian dari 6 gap yang sama. Lapisan tool-specific hands-on (Prometheus, Grafana, ELK/Loki, Jaeger/Tempo, OpenTelemetry, perbandingan Splunk) di bawah lapisan konsep SRE (SLI/SLO/error budget) yang sudah ada di `courses/cloud-devops-foundations`. 9 modul, capstone: instrumentasi sistem multi-service end-to-end + SLO burn-rate alert + runbook. Status: Not Started, roadmap draft menunggu approval & kalibrasi.
* **Performance Engineering** — `courses/performance-engineering/roadmap.md` (draft 2026-08-07) — bagian dari 6 gap yang sama. Lebih dalam dari `courses/backend-fundamentals` Module 3 (yang hanya cukup baca heap/thread dump dasar) — metodologi USE/RED, JMH microbenchmarking, CPU/memory/thread profiling, load & stress testing (k6), capacity planning. 8 modul, capstone: siklus performance engineering penuh dengan bukti before/after. Status: Not Started, roadmap draft menunggu approval & kalibrasi.
* **Functional Programming** — `courses/functional-programming/roadmap.md` (draft 2026-08-07) — bagian dari 6 gap yang sama. Java-first (asumsi belum dikonfirmasi user): pure function, immutability, higher-order function, Stream API mendalam, Optional/Either error handling, pengenalan pola monad/functor secukupnya, cicipan Kotlin untuk perspektif. 7 modul, capstone: refactor service imperative jadi functional style. Status: Not Started, roadmap draft menunggu approval & kalibrasi.
* **Product & Business Knowledge for Engineers** — `courses/business-knowledge/roadmap.md` (draft 2026-08-07) — bagian dari 6 gap yang sama. Judgment/komunikasi bisnis untuk engineer (product thinking, RICE/ICE, metrik/North Star, A/B testing applied, cost-consciousness, SLA sebagai alat negosiasi, komunikasi trade-off ke non-teknis) — beda dari `courses/data-analysis-visualization` (tooling analis) dan `courses/cloud-devops-foundations` (mekanisme SLO). 7 modul, capstone: business brief satu fitur untuk calon klien freelance. Status: Not Started, roadmap draft menunggu approval & kalibrasi.
* **Masa Depan Software Engineering (2027–2030)** — `courses/future-of-software-engineering/roadmap.md` (draft 2026-08-07) — bagian dari 6 gap yang sama, course paling spekulatif di repo ini (AI-native SWE, multi-agent, AI code review/testing, autonomous CI/CD, Platform Engineering/IDP, cloud-native lanjutan, FinOps, MLOps, edge & confidential computing). House rule eksplisit: setiap modul wajib diakhiri verdict Adopt/Trial/Assess/Hold (format ThoughtWorks Technology Radar) dengan bukti, bukan ikut tren; kontennya sendiri ditandai perlu ditinjau ulang berkala karena sifatnya cepat basi. Capstone bukan proyek kode seperti course lain, tapi Technology Radar Assessment tertulis. 9 modul. Status: Not Started, roadmap draft menunggu approval & kalibrasi.

# Course Belum Dibuat (gap dari Master Goal)

* Freelance & International Career Readiness — belum ada folder `courses/`, lihat Gap Analysis di atas — bisa mulai paralel begitu ada portofolio awal

---

# Prioritas Saat Ini

**Update 2026-08-07 (hemat token):** hanya 2 course aktif sekarang — lihat "Course Aktif" di atas.

1. **Mastering Claude** (`courses/mastering-claude`) — lanjutkan Module 3, menunggu approval user untuk mulai.
2. **Character Development** (`courses/character-development`) — lanjutkan Module 1, Lesson 1.1.

Semua prioritas lain (Backend Fundamentals, ML/LLM/Agents, Kubernetes, System Design, Scrum Master, Engineering Culture, Freelance Readiness, dll.) dipause bersama course-nya masing-masing di atas — resume kapan saja atas permintaan user, urutan lama tetap tercatat di git history file ini.
