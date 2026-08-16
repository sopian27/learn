# Learning Progress

**Update 2026-08-07:** Course aktif dipersempit jadi 2 atas permintaan user untuk hemat pemakaian token. Detail course lain ada di bagian "Paused Courses" di bawah — resume kapan saja diminta.

## Active Courses

### 1. Mastering Claude — `courses/mastering-claude/roadmap.md`

Current Module: Module 8 — Dari Vibe Coding ke Rekayasa Bertanggung Jawab (In Progress)
Current Lesson: 8.4 Agentic Engineering (selesai) — semua lesson Module 8 selesai; next Mini Project Module 8
Course Completion: 7/8 module selesai (87.5%)

| Module | Status |
| ------ | ------ |
| 1. Fondasi: Mental Model & Prompting | Selesai (2026-08-06) |
| 2. Token Economy Claude Code | Selesai (2026-08-06) |
| 3. Claude Code Mendalam | Selesai (2026-08-13) — Mini Project: Note CRUD API pakai 5 mekanisme, bukti di `playground/mastering-claude/3-mini-project.md` |
| 4. Skills & Plugins | Selesai (2026-08-14) — 4.1-4.3: bikin skill `tdd-workflow` nyata + bundle jadi plugin lokal `tdd-toolkit@sopian-local`, install nyata via `/plugin marketplace add` + `/plugin install`, verifikasi baca dari plugin cache global. 4.4: audit keamanan `tdd-toolkit` (checklist description/body/hook/MCP/bundled script/source pinning/least privilege) — koreksi 1 putaran (exercise awal salah tandai 5 poin "aman karena unknown", diperbaiki jadi verified-per-file). Mini Project Module 4 dipenuhi oleh `tdd-toolkit` (keputusan user, tidak bikin skill terpisah). Log: `playground/mastering-claude/4.1.md`, `.fix.md`, `4.2.md`, `.fix.md`, `.fix1.md`, `4.3.md`, `4.4.md` |
| 5. Claude Cowork | Selesai (2026-08-15) — 5.1 skor 100/100 setelah 2 putaran revisi (Q3 reasoning diperbaiki dari salah framing "dev environment" jadi "VM isolasi + overhead orkestrasi subagent"). 5.2 skor 100/100 setelah 1 putaran revisi (Exercise 2 diperdalam ke mekanisme failure detector). 5.3 skor 100/100 setelah 1 putaran revisi (containment/blast radius). 5.4 skor 100/100 setelah 1 putaran revisi (Quiz 1-2 salah pasang axis, diperbaiki). Log: `playground/mastering-claude/5.1.md`, `.fix.md`, `5.2.md`, `5.3.md`, `5.4.md` |
| 6. Kombinasi Multi-AI | Selesai (2026-08-15) — 6.1 skor 100/100 tanpa revisi (quiz + exercise benar semua, sumbu keputusan tepat). 6.2 applied practice kasus nyata (proses patching MBP): cross-model blind-spot review → swap-test isolasi confound prompt-vs-model → self-adversarial-review kontrol → pattern digeneralisasi ke `progress/cheatsheet.md`. Log: `playground/mastering-claude/6.1.md`, `6.2.md` |
| 7. Vibe Coding: Filosofi & Alur Kerja | Selesai (2026-08-16). 7.1 skor 100/100 tanpa revisi; 7.2 skor 3/3 setelah 1x revisi (axis stakes vs reversibility); 7.3 skor 3/3 quiz + 4/4 exercise setelah revisi (Q1 diperkuat "success signal ≠ correct behavior"; Q3 dua percobaan pertama recycle contoh 7.2, revisi final spesifik ke auto-run/auto-accept; Exercise 2 sempat salah label tahap). Mini Project selesai (2026-08-16): CLI scraper produk→CSV dibangun vibes-only, 2 ronde vibes-patch buta (TLS reset lalu 0 produk karena selector ditebak), refleksi nangkep "program jalan tanpa error ≠ program benar". Log: `playground/mastering-claude/7-mini-project.md` |
| 8. Dari Vibe Coding ke Rekayasa Bertanggung Jawab | In Progress — 8.1 selesai (2026-08-15) skor 4/4 quiz + 4/4 exercise setelah 1x revisi (Exercise 4 diakui tak cocok bersih ke 4 kategori, dipilih analogis). 8.2 selesai (2026-08-16) skor 4/4 quiz setelah 1x revisi (Q1 & Q3) + Exercise lengkap tanpa revisi. 8.3 selesai (2026-08-16) skor 4/4 quiz + exercise lengkap tanpa revisi (3 gerbang verifikasi diterapkan ke tool extract-title dari 8.2). 8.4 selesai (2026-08-16) skor 4/4 quiz tanpa revisi + exercise setelah 1x revisi (line vs behavioral coverage). Next: Mini Project Module 8 (refactor `7-mini-project/scraper.py` pakai spec-driven + verifikasi penuh) |

### 2. Character Development — `courses/character-development/roadmap.md`

Current Module: Module 1 — Fondasi: Kerendahan Hati Sejati vs Ego & Self-Deprecation
Current Lesson: 1.1 Peta tiga kondisi — kesombongan vs kerendahan hati sehat vs self-deprecation toxic
Course Completion: 0/6 module (0%)

| Lesson | Status | Quiz | Exercise |
| ------ | ------ | ---- | -------- |
| 1.1 Peta tiga kondisi — kesombongan vs kerendahan hati sehat vs self-deprecation toxic | In Progress | - | - |

---

# Projects

| Project | Status |
| ------- | ------ |

---

# Weak Topics

*

---

# Strong Topics

*

---

# Review Schedule

Rule: every completed module gets a +3 hari (recall cepat) dan +14 hari (retensi) check — jawab 2-3 pertanyaan inti modul itu dari ingatan, tanpa buka lesson lama. Kalau gagal recall, tandai "Revision Needed" dan re-review sebelum lanjut modul baru.

| Module | Selesai | +3 hari | +14 hari | Status |
| ------ | ------- | ------- | -------- | ------ |
| Mastering Claude 1-2 (Fondasi, Token Economy) | 2026-08-06 | 2026-08-09 ✓ (lewat, belum di-log) | 2026-08-20 | Due +14 hari |
| Mastering Claude 3 (Claude Code Mendalam) | 2026-08-13 | 2026-08-16 | 2026-08-27 | Due +3 hari |
| Mastering Claude 4 (Skills & Plugins) | 2026-08-14 | 2026-08-17 | 2026-08-28 | Upcoming |

Revision Needed: -

---

# Notes

* 2026-08-07: Course aktif dipersempit jadi 2 (mastering-claude, character-development) atas permintaan user untuk hemat pemakaian token — semua course lain (termasuk yang sebelumnya "In Progress" seperti backend-fundamentals, ml-fundamentals, ci-cd, personal-finance, dst.) dipause, bukan dihentikan. Resume kapan saja diminta user.
* Mastering Claude: track paralel/praktis (bukan jalur utama), dimulai 2026-08-06 dari kebutuhan langsung user.
* Character Development: domain pack, di-approve & dimulai 2026-08-07. Applied Practice-nya butuh jurnal reflektif jujur + feedback nyata dari orang lain — pace mengikuti ritme praktik nyata, bukan dikebut.

---

# Paused Courses

*Dipause 2026-08-06 (alasan lama):*

* Docker, Kubernetes & OpenShift — `courses/kubernetes/roadmap.md`
  * Module 1 — Docker Fundamentals, Lesson 1.1 Container vs VM, arsitektur Docker Engine (In Progress, belum selesai)
  * Alasan pause: user memilih mulai jalur AI (ml-fundamentals) dulu, 2026-08-06. Kebutuhan kerja aktif — resume kapan saja user minta "lanjut Kubernetes".

*Dipause 2026-08-07 untuk hemat token (semua "In Progress" sebelumnya, resume kapan saja atas permintaan user — detail rasional lengkap ada di `courses/<nama>/roadmap.md` masing-masing dan git history file ini):*

* Backend Engineering Fundamentals Rebuild — `courses/backend-fundamentals/roadmap.md` — Module 1, Lesson 1.1
* Machine Learning & AI Fundamentals — `courses/ml-fundamentals/roadmap.md` — Module 1, Lesson 1.1 (AI vs ML vs DL vs LLM)
* CI/CD Mastery — `courses/ci-cd/roadmap.md` — Module 1, Lesson 1.1
* Personal Finance — `courses/personal-finance/roadmap.md` — Module 1, Lesson 1.1
* n8n Automation — `courses/n8n-automation/roadmap.md` — Module 1, Lesson 1.1
* Communication Skills — `courses/communication-skills/roadmap.md` — Module 1, Lesson 1.1
* Critical Thinking & Problem Solving — `courses/critical-thinking/roadmap.md` — Module 1, Lesson 1.1. Domain Goal dikunci final 2026-08-09 di `domains/critical-thinking/DOMAIN.md` (Einstein/Musk/Buffett/Edison/Tesla sebagai referensi prinsip berpikir, bukan panutan utuh — lihat bagian "Tokoh Referensi" di domain pack) — status tetap Paused, resume kapan saja diminta.
* World History & Historical Discernment — `courses/world-history/roadmap.md` — Module 1, Lesson 1.1
* Gitar — `courses/guitar/roadmap.md` — Module 1, Lesson 1.1
* Self-Love — `courses/self-love/roadmap.md` — Module 1, Lesson 1.1
* Computational Thinking & Algoritma — `courses/computational-thinking/roadmap.md` — Module 1, Lesson 1.1
* Film Criticism & Analysis — `courses/film-criticism/roadmap.md` — Module 1, Lesson 1.1
* Bahasa Korea — `courses/korean/roadmap.md` — Module 1, Lesson 1.1
* Cryptocurrency — `courses/cryptocurrency/roadmap.md` — Module 1, Lesson 1.1

*Didaftarkan paused sejak awal 2026-08-08 (konsisten dengan narrowing 2026-08-07), roadmap dibangun lengkap atas permintaan user meski belum aktif:*

* Pornography Recovery — `courses/pornography-recovery/roadmap.md` — Module 1, Lesson 1.1. Domain pack: `domains/pornography-recovery/DOMAIN.md`. House rule anti-shame eksplisit + eskalasi wajib ke profesional untuk indikasi klinis — lihat domain pack sebelum resume.
* Media & Political Literacy — `courses/media-literacy/roadmap.md` — Module 1, Lesson 1.1. Domain pack: `domains/media-literacy/DOMAIN.md`. Analisis berita/pernyataan pemerintah/kebijakan/isu politik kritis & berbasis bukti, tanpa fanatik ke kelompok/ideologi mana pun. House rule paling ketat: mentor tidak pernah mengambil posisi politik — lihat domain pack sebelum resume.
* Vocal Coaching — `courses/vocal-coaching/roadmap.md` — Module 1, Lesson 1.1. Domain pack: `domains/vocal-coaching/DOMAIN.md`. Teknik vokal pop/CCM dari 0 untuk suara bass/baritone, sesi via instruksi verbal + self-report (bukan rekaman, mentor tidak bisa mendengar langsung). House rule paling ketat: hard boundary eskalasi kesehatan vokal (nyeri/serak berkepanjangan/kehilangan suara mendadak → wajib rujuk THT/laringolog) — lihat domain pack sebelum resume.
* Football Tactical Analysis — `courses/football-tactics/roadmap.md` — Module 1, Lesson 1.1. Domain pack: `domains/football-tactics/DOMAIN.md`. Membaca taktik sepak bola dari 0 (anchor liga Premier League), skill inti membedakan kesalahan pemain vs sistem dan taktik vs eksekusi (Module 6). Mode analisis memakai pertandingan terkenal yang sudah terdokumentasi luas, bukan nonton live bareng — lihat domain pack sebelum resume.
