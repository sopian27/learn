# Course Roadmap

## Course Information

* Course Name: Masa Depan Software Engineering (2027–2030) — AI-Native SWE, Platform Engineering, FinOps, MLOps
* Category: Frontier/Emerging Technology Awareness — lapisan paling depan di atas seluruh fondasi teknis lain di repo ini (`backend-fundamentals`, `system-design`, `cloud-devops-foundations`, `cybersecurity`, `ml-fundamentals`/`llm`/`agents`), bukan pengganti satu pun dari course tersebut
* Difficulty: Level 3 (sudah punya fondasi backend/arsitektur/DevOps solid) → Level 4 (bisa menilai secara kritis mana tren yang layak diadopsi sekarang, mana yang masih terlalu dini — dan menjelaskan alasannya dengan bukti, bukan ikut-ikutan hype)
* Estimated Duration: 9 modul, 2-3 lesson/modul (lebih ringkas dari course teknis lain karena sifatnya survei kritis, bukan hands-on mendalam), ~30 menit/lesson. Perkiraan ≈ 2-3 bulan pace santai.
* Prerequisites: `courses/cloud-devops-foundations`, `courses/system-design`, dan minimal salah satu dari `courses/llm`/`courses/agents` — course ini terus-menerus merujuk balik ke fondasi di course-course itu, bukan mengajarkannya ulang.

---

# Catatan Scope (draft — menunggu approval & kalibrasi user)

Draft ini dibuat atas permintaan eksplisit user (2026-08-07) untuk mengisi gap "Masa Depan (2027–2030)" dari daftar besar topik. **Belum ada sesi kalibrasi** — dan course ini punya sifat yang berbeda dari semua course lain di repo, perlu dibaca dengan hati-hati sebelum approve:

1. **Ini course yang sifatnya paling cepat basi.** Berbeda dari `backend-fundamentals` (JVM/Spring stabil bertahun-tahun) atau `system-design` (prinsip arsitektur stabil puluhan tahun), konten course ini **harus ditinjau ulang secara berkala** (disarankan setiap 6 bulan) karena lanskap AI-native engineering & platform engineering bergerak cepat. Referensi riset di bawah adalah kondisi pengetahuan sampai cutoff model ini (Januari 2026) — beberapa klaim mungkin sudah berubah saat course ini benar-benar dijalankan.
2. **House rule eksplisit — setiap modul wajib diakhiri verdict "Adopt / Trial / Assess / Hold"** (format [ThoughtWorks Technology Radar](https://www.thoughtworks.com/radar)) dengan bukti yang mendasarinya (adopsi nyata di industri, bukan marketing vendor). Ini mengoperasionalkan skill "bedakan hype dari nyata" sebagai kebiasaan berulang, bukan penilaian satu kali. Selaras dengan `domains/critical-thinking/DOMAIN.md` (evidence-based reasoning) — course ini secara eksplisit meminjam standar bukti dari domain itu.
3. **Bukan capstone coding seperti course teknis lain.** Karena sifatnya penilaian tren (bukan skill hands-on tunggal yang bisa "dikuasai"), capstone-nya adalah **Technology Radar Assessment** tertulis, bukan proyek kode. Ini keputusan desain yang mungkin perlu didiskusikan lagi saat approval — kalau user lebih suka setiap modul yang "Adopt" dicoba hands-on singkat, roadmap perlu direvisi menambah mini-hands-on per topik yang applicable (misal: Platform Engineering/Backstage, MLOps tooling).
4. **Cross-reference intensif** — course ini sengaja tidak mengulang mekanisme yang sudah diajarkan mendalam di course lain (Multi-Agent → `courses/agents`, Platform observability → `courses/observability` draft, Cloud-native → `courses/cloud-devops-foundations`/`courses/kubernetes`, MLOps dasar ML → `courses/ml-fundamentals`/`courses/llm`, FinOps biaya dasar → draft `courses/business-knowledge` Module 5) — modul di sini fokus ke *apa yang baru/berubah* di atas fondasi itu.

Referensi riset (untuk disusun jadi roadmap final saat approval — daftar ini sendiri perlu diverifikasi ulang saat course benar-benar mulai, karena sifatnya frontier):

* [CNCF Platform Engineering whitepaper](https://tag-app-delivery.cncf.io/whitepapers/platform-engineering/) — Module 5
* [ThoughtWorks Technology Radar](https://www.thoughtworks.com/radar) — metodologi Adopt/Trial/Assess/Hold, dipakai sebagai format penilaian di **semua** modul
* [FinOps Foundation](https://www.finops.org/framework/) — Module 7
* [Backstage (CNCF)](https://backstage.io/) — referensi implementasi IDP, Module 5
* Gartner Hype Cycle methodology — dipakai sebagai *lensa* untuk mengajarkan skeptisisme terhadap klaim vendor, bukan sebagai sumber kebenaran tunggal
* Engineering blog Anthropic/OpenAI/Google (disilangkan, bukan sumber tunggal) — klaim AI-native SWE Module 1
* [CNCF Cloud Native Landscape](https://landscape.cncf.io/) — Module 6

---

# Learning Objectives

* [ ] Membedakan praktik AI-native software engineering yang sudah benar-benar diadopsi luas vs yang masih di level demo/marketing
* [ ] Memahami pola orkestrasi multi-agent untuk workflow engineering (di atas fondasi `courses/agents`)
* [ ] Menilai kapabilitas & keterbatasan nyata AI code review dan AI-generated testing saat ini
* [ ] Memahami arah autonomous CI/CD & self-healing pipeline di atas fondasi `courses/ci-cd`
* [ ] Menjelaskan Platform Engineering dan Internal Developer Platform (IDP) sebagai disiplin, termasuk konsep "golden path"
* [ ] Memahami evolusi arsitektur cloud-native (serverless, edge) di luar default K8s-centric
* [ ] Menerapkan kerangka FinOps dasar sebagai kesadaran biaya level organisasi
* [ ] Memahami kebutuhan MLOps saat engineer backend mulai memiliki sistem terintegrasi AI/ML di production
* [ ] Menilai maturitas edge computing & confidential computing — kapan genuinely relevan vs masih prematur
* [ ] Menyelesaikan capstone: Technology Radar Assessment pribadi untuk konteks karir/freelance sendiri

---

# Course Modules

## Module 1 — AI-Native Software Engineering: Apa yang Sungguh Nyata di 2026

Description: Titik awal wajib — memisahkan klaim "AI mengubah segalanya" dari adopsi yang benar-benar terjadi di tim engineering sungguhan.

Lessons:

* [ ] 1.1 Survei praktik AI-assisted development yang sudah mainstream (code completion, agentic coding assistant) vs yang masih eksperimental
* [ ] 1.2 Data adopsi nyata (survei developer, laporan industri) vs narasi vendor — cara membaca klaim secara kritis
* [ ] 1.3 Verdict Adopt/Trial/Assess/Hold untuk 3-4 praktik AI-native spesifik, dengan bukti

Mini Project: Pilih 3 klaim "AI-native engineering" yang sering terdengar, cari bukti adopsi nyata untuk masing-masing (bukan marketing vendor), beri verdict Adopt/Trial/Assess/Hold dengan justifikasi.

Status: Not Started

---

## Module 2 — Multi-Agent Systems dalam Workflow Engineering

Description: Melanjutkan fondasi `courses/agents` (cara membangun 1 agent) ke pola orkestrasi banyak agent untuk tugas engineering.

Lessons:

* [ ] 2.1 Pola orkestrasi multi-agent: orchestrator-worker, peer-to-peer, hierarchical — cross-ref arsitektur dari `courses/agents`
* [ ] 2.2 Use case nyata multi-agent untuk engineering: code migration besar, investigasi insiden paralel, review multi-perspektif
* [ ] 2.3 Keterbatasan nyata: koordinasi overhead, cost, kegagalan yang sulit didiagnosis di sistem multi-agent

Mini Project: Verdict Adopt/Trial/Assess/Hold untuk pola multi-agent di 2 use case engineering spesifik yang relevan dengan pekerjaan Anda.

Status: Not Started

---

## Module 3 — AI Code Review & AI Testing

Description: Menilai jujur kapabilitas tool AI code review/testing saat ini — bukan menolak maupun memuja secara membabi buta.

Lessons:

* [ ] 3.1 State of the art AI code review: apa yang sudah reliable (linting-level, pattern-based) vs yang masih sering salah (judgment arsitektural)
* [ ] 3.2 AI-generated test suite: kekuatan (coverage cepat) dan kelemahan nyata (test yang "hijau" tapi tidak menguji apa pun bermakna)
* [ ] 3.3 Tempat AI code review/testing yang tepat dalam workflow tim (cross-ref `courses/code-review`) — augmentasi, bukan pengganti reviewer manusia

Mini Project: Jalankan 1 AI code review tool pada kode nyata Anda, evaluasi jujur: berapa persen temuan yang benar-benar berguna vs noise, verdict Adopt/Trial/Assess/Hold untuk konteks kerja Anda.

Status: Not Started

---

## Module 4 — Autonomous CI/CD & Self-Healing Pipeline

Description: Apa yang muncul di atas fondasi CI/CD konvensional (`courses/ci-cd`) — bukan menggantikannya.

Lessons:

* [ ] 4.1 Auto-remediation pipeline: kapan sistem benar-benar aman diberi otonomi memperbaiki kegagalan sendiri, kapan berbahaya
* [ ] 4.2 AI-assisted pipeline debugging: mempercepat root cause analysis kegagalan build/deploy
* [ ] 4.3 Batas otonomi yang masuk akal — mengapa "fully autonomous deploy to prod tanpa manusia" masih (atau mungkin selalu) berisiko tinggi untuk sebagian besar sistem

Mini Project: Verdict Adopt/Trial/Assess/Hold untuk auto-remediation di pipeline CI/CD Anda sendiri (dari `courses/ci-cd` atau kerjaan), dengan batas otonomi yang Anda anggap wajar dan alasannya.

Status: Not Started

---

## Module 5 — Platform Engineering & Internal Developer Platform (IDP)

Description: Disiplin yang mengubah "developer experience" jadi produk internal — salah satu tren paling nyata (bukan hype) dari daftar course ini.

Lessons:

* [ ] 5.1 Definisi CNCF Platform Engineering: golden path, self-service infrastructure, developer experience sebagai produk
* [ ] 5.2 Backstage sebagai referensi implementasi IDP: service catalog, software template, TechDocs
* [ ] 5.3 Kapan organisasi butuh platform team formal vs kapan itu overkill (tim kecil, startup awal)

Mini Project: Rancang (di atas kertas, tidak wajib implementasi penuh) 1 "golden path" untuk skenario yang relevan dengan Anda (misal: cara tim baru deploy service pertama mereka), verdict Adopt/Trial/Assess/Hold untuk konteks freelance/tim kecil Anda.

Status: Not Started

---

## Module 6 — Evolusi Arsitektur Cloud-Native

Description: Melihat ke depan dari default K8s-centric yang sudah dibangun di `courses/cloud-devops-foundations`/`courses/kubernetes`.

Lessons:

* [ ] 6.1 Serverless lanjutan: kapan sudah matang untuk beban kerja production serius, kapan masih terbatas (cold start, vendor lock-in)
* [ ] 6.2 WebAssembly (WASM) di edge/server: janji dan status kematangan nyata saat ini
* [ ] 6.3 Tren konsolidasi vs fragmentasi tooling cloud-native (CNCF landscape) — cara membaca lanskap tanpa tenggelam di ratusan tool

Mini Project: Verdict Adopt/Trial/Assess/Hold untuk serverless dan WASM masing-masing, untuk 1 skenario nyata dari portofolio Anda.

Status: Not Started

---

## Module 7 — FinOps untuk Engineer

Description: Melengkapi kesadaran biaya dasar (draft `courses/business-knowledge` Module 5) dengan disiplin formal FinOps di skala cloud.

Lessons:

* [ ] 7.1 FinOps Foundation framework: Inform → Optimize → Operate, dan kenapa ini kolaborasi engineering-finance-bisnis
* [ ] 7.2 Cost sebagai first-class engineering concern: cost allocation, tagging, showback/chargeback
* [ ] 7.3 Praktik FinOps nyata: rightsizing, reserved capacity vs on-demand, cost anomaly detection

Mini Project: Verdict Adopt/Trial/Assess/Hold untuk penerapan FinOps formal di konteks freelance/klien kecil Anda — kapan ini sepadan effort-nya, kapan cukup kesadaran biaya dasar saja.

Status: Not Started

---

## Module 8 — MLOps untuk Sistem Terintegrasi AI

Description: Saat engineer backend mulai memiliki sistem yang mengandung komponen AI/ML production — melengkapi `courses/ml-fundamentals`/`courses/llm` dengan sisi operasionalnya.

Lessons:

* [ ] 8.1 Model versioning & reproducibility — kenapa ini berbeda dari versioning kode biasa
* [ ] 8.2 Drift monitoring: mendeteksi saat model/prompt yang tadinya bekerja mulai menurun performanya di production
* [ ] 8.3 Eval pipeline untuk sistem berbasis LLM (cross-ref `courses/llm`) — menguji kualitas output AI secara berkelanjutan, bukan sekali saat launch

Mini Project: Verdict Adopt/Trial/Assess/Hold untuk MLOps formal di konteks proyek AI Anda saat ini (dari `courses/llm`/`courses/agents`/`courses/n8n-automation`) — apakah kompleksitas MLOps penuh sepadan di skala proyek itu.

Status: Not Started

---

## Module 9 — Edge Computing & Confidential Computing

Description: Dua tren yang sering disebut bersamaan tapi menyelesaikan masalah berbeda — modul penutup yang melatih kembali skill "pisahkan hype dari nyata" pada topik paling belum matang di course ini.

Lessons:

* [ ] 9.1 Edge computing: masalah nyata yang diselesaikan (latency, bandwidth, data residency) vs kapan cloud terpusat tetap lebih sederhana & cukup
* [ ] 9.2 Confidential computing: masalah nyata yang diselesaikan (trusted execution environment, data-in-use protection) vs maturitas tooling saat ini
* [ ] 9.3 Sintesis akhir: kapan mengejar tren frontier justru kontra-produktif dibanding fokus ke fondasi (kaitkan balik ke Master Goal freelance di `progress/roadmap.md`)

Mini Project: Verdict Adopt/Trial/Assess/Hold untuk edge computing dan confidential computing, masing-masing dengan 1 skenario konkret di mana itu benar-benar relevan (atau kesimpulan jujur "belum relevan untuk konteks saya sekarang").

Status: Not Started

---

# Capstone Project

Description: **Technology Radar Assessment Pribadi** — mengikuti format [ThoughtWorks Technology Radar](https://www.thoughtworks.com/radar) (kuadran Adopt/Trial/Assess/Hold), pilih 3 topik dari Module 1-9 yang paling relevan dengan konteks karir freelance Anda saat ini, tulis penilaian lengkap untuk masing-masing: apa itu, bukti maturitas/adopsi nyata (bukan marketing), rekomendasi verdict, dan alasan kenapa relevan/tidak relevan untuk Anda secara spesifik (bukan generik).

Acceptance Criteria:

* 3 topik dipilih dengan justifikasi kenapa itu yang paling relevan (bukan asal pilih)
* Setiap topik punya bukti maturitas/adopsi yang bisa ditelusuri sumbernya (bukan klaim tanpa rujukan)
* Verdict Adopt/Trial/Assess/Hold masing-masing punya alasan spesifik ke konteks freelance/portofolio Anda, bukan kesimpulan umum yang bisa dicopy-paste siapa saja
* Dokumen mengakui eksplisit keterbatasan: ini penilaian per tanggal tertentu, bukan kebenaran permanen — mencontohkan sikap kritis yang diajarkan sepanjang course
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, dengan catatan tanggal penulisan (karena akan butuh ditinjau ulang)

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Survei praktik AI-assisted development
