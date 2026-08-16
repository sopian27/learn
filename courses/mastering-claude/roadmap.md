# Course Roadmap

## Course Information

* Course Name: Mastering Claude — dari 0 sampai Expert
* Category: AI Tooling / Productivity / Software Engineering Workflow
* Difficulty: Beginner → Expert (progresif per module)
* Estimated Duration: fleksibel, track paralel (bukan jalur utama) — belajar sambil dipakai kerja sehari-hari
* Prerequisites: Sudah aktif memakai Claude Code (terpenuhi). Tidak butuh background AI/ML.

---

# Learning Objectives

* [x] Memahami mental model token & context window, kenapa penggunaan Claude terasa boros
* [x] Menulis prompt efektif (Goal/Context/Constraint/Format) untuk mengurangi iterasi bolak-balik
* [x] Menguasai command & mekanisme efisiensi Claude Code (`/context`, `/usage`, `/compact`, `/clear`, prompt caching, subagent)
* [ ] Memahami arsitektur Claude Code mendalam: tools, hooks, plan mode, MCP servers, background agent
* [ ] Mampu membuat & menggunakan Skills dan Plugins custom
* [ ] Memahami Claude Cowork: kapan pakai Cowork vs Claude Code vs chat biasa
* [ ] Mampu mengombinasikan Claude dengan tool AI lain (ChatGPT, Gemini, dll) secara strategis, bukan asal pindah tool
* [ ] Memahami vibe coding secara utuh — definisi, kapan cocok/berbahaya, dan risiko nyatanya (security, technical debt) di production
* [ ] Menguasai spec-driven development & disiplin verifikasi sebagai penyeimbang vibe coding untuk kerja profesional
* [ ] Membangun playbook pribadi untuk automation & mengukur ROI penggunaan AI

---

# Course Modules

## Module 1 — Fondasi: Mental Model & Prompting

Description: Dasar token/context window (semua — system prompt, riwayat chat, hasil tool call — numpuk jadi context dan dihitung token), serta anatomi prompt efektif.

Lessons:

* [x] 1.1 Mental Model Token & Context Window
* [x] 1.2 Prompting Fundamental (Goal/Context/Constraint/Format)

Mini Project: Rewrite prompt "boros" pribadi jadi versi spesifik — selesai di `playground/mastering-claude/answer.md`.

Status: Selesai (2026-08-06)

---

## Module 2 — Token Economy Claude Code

Description: Command untuk kontrol context, mekanisme prompt caching (TTL 1 jam di subscription, invalidate saat ganti model/effort/MCP/`/compact`), subagent untuk isolasi context, CLAUDE.md sebagai memori terkompresi.

Lessons:

* [x] 2.1 Command & Cache Mechanics (`/context`, `/usage`, `/compact`, `/clear`, `/model`, `/memory`)
* [x] 2.2 Subagent & Strategi CLAUDE.md (<200 baris, instruksi spesifik-tugas → Skill)

Mini Project: Audit `/usage` sesi sendiri + identifikasi kebiasaan boros nyata (>150k context di 80% pemakaian) — selesai & direview.

Status: Selesai (2026-08-06)

---

## Module 3 — Claude Code Mendalam

Description: Arsitektur CLI Claude Code di luar sekadar chat — bagaimana tools, hooks, plan mode, MCP server, dan background agent bekerja & saling berhubungan.

Lessons:

* [x] 3.1 Tools & Permission Model (bagaimana tool call disetujui, sandboxing)
* [x] 3.2 Hooks — automasi event-driven (pre/post tool call)
* [x] 3.3 Plan Mode — Opus merencanakan, model lain eksekusi
* [x] 3.4 MCP Servers — integrasi tool eksternal, biaya context-nya
* [x] 3.5 Background Agents & Monitor — kerja paralel tanpa blokir sesi utama

Mini Project: Build 1 Fitur, Pakai 5 Mekanisme — CRUD Note API kecil di Spring Boot, sengaja memicu Plan Mode + Tools/Permission + Hooks + MCP + Background Agent dalam satu alur kerja. Spec lengkap: `courses/mastering-claude/module3-mini-project.md`. Log & bukti: `playground/mastering-claude/3-mini-project.md`.

Status: Selesai (2026-08-13)

---

## Module 4 — Skills & Plugins

Description: Skill sebagai folder instruksi dengan 3-level progressive loading (`SKILL.md`), cara membuat skill custom, Plugin sebagai unit distribusi yang membundel skill+agent+hook+MCP+command jadi satu paket.

Lessons:

* [x] 4.1 Anatomi Skill — `SKILL.md`, frontmatter, progressive disclosure
* [x] 4.2 Membuat Skill Custom — workflow authoring, testing di sesi baru
* [x] 4.3 Plugin — Bundling & Distribusi (marketplace, git repo, `.claude-plugin/plugin.json`)
* [x] 4.4 Best Practices & Keamanan (description harus spesifik, audit skill dari sumber tak dikenal)

Mini Project: Buat 1 skill custom untuk workflow pribadi (misal skill review PR ala kantor sendiri) — dipenuhi oleh skill `tdd-workflow` (dibangun 4.2) yang dibundle jadi plugin `tdd-toolkit` (dibangun 4.3), sengaja dihitung ulang sebagai mini project ini alih-alih bikin skill terpisah (keputusan user 2026-08-14). Log lengkap: `playground/mastering-claude/4.2.md`, `.fix.md`, `.fix1.md`, `4.3.md`, `4.4.md`.

Status: Selesai (2026-08-14)

---

## Module 5 — Claude Cowork

Description: Agent otonom yang bekerja langsung di file/aplikasi (bukan chat manual copy-paste) — GA April 2026, ekspansi web & mobile Juli 2026. Scheduled tasks, Dispatch (computer use), plugin marketplace di Cowork.

Lessons:

* [x] 5.1 Cowork Overview & Positioning (beda dari Claude Code & chat biasa)
* [x] 5.2 Scheduled & Unattended Tasks
* [x] 5.3 Computer Use / Dispatch
* [x] 5.4 Kapan Pakai Cowork vs Claude Code vs Chat Biasa — studi kasus

Mini Project: TBD

Status: Selesai (2026-08-15) — 5.1 selesai (2026-08-14), skor 100/100 setelah 2 putaran revisi. 5.2 selesai (2026-08-14), skor 100/100 setelah 1 putaran revisi (Exercise 2 diperdalam: mekanisme "user sebagai failure detector real-time" hilang di unattended, notification/retry/escalation gantikan peran itu). 5.3 selesai (2026-08-15), skor 100/100 setelah 1 putaran revisi (Exercise 3 diperdalam: alasan sandboxed VM relevan mitigasi prompt injection — containment/blast radius, bukan sekadar restate risiko). 5.4 selesai (2026-08-15), skor 100/100 setelah 1 putaran revisi (Quiz 1 & 2 sempat salah pasang axis — "butuh eksekusi vs ide" dan "supervision availability" — diperbaiki di revisi; 4 skenario Exercise benar semua). Log: `playground/mastering-claude/5.1.md`, `.fix.md`, `5.2.md`, `5.3.md`, `5.4.md`.

---

## Module 6 — Kombinasi Multi-AI

Description: Strategi pembagian kerja antara Claude dan tool AI lain (ChatGPT, Gemini, dll) berdasarkan kekuatan masing-masing, plus pola cross-model review.

Lessons:

* [x] 6.1 Kapan Pakai Model/Tool Apa (brainstorming vs eksekusi terstruktur)
* [x] 6.2 Cross-Model Review — menangkap blind spot satu model

Portfolio Project: TBD

Status: Selesai (2026-08-15) — 6.1 selesai (2026-08-15), skor 100/100 tanpa revisi (quiz + exercise benar semua, sumbu keputusan dipakai tepat). 6.2 selesai (2026-08-15), applied practice pakai kasus nyata (proses patching MBP): cross-model blind-spot review, lalu swap-test buat isolasi confound (prompt framing vs model identity), lalu self-adversarial-review sebagai kontrol lanjutan — hasil digeneralisasi jadi pattern "self-review dulu, cross-model second pass" di `progress/cheatsheet.md`. Log: `playground/mastering-claude/6.1.md`, `6.2.md`.

---

## Module 7 — Vibe Coding: Filosofi & Alur Kerja

Description: "Vibe coding" (istilah Andrej Karpathy, Feb 2025) — gaya coding di mana kamu menyatakan intent dalam bahasa natural dan membiarkan AI generate/iterate, "melupakan kode itu ada". Module ini membangun pemahaman dari nol: apa bedanya dengan AI-assisted coding biasa, kapan gaya ini cocok dipakai, dan kapan berbahaya. Ditambahkan 2026-08-06 atas permintaan user (awalnya diajukan sebagai course terpisah, digabung ke sini karena user sudah pakai Claude Code sebagai tool utama).

Lessons:

* [x] 7.1 Apa Itu Vibe Coding — definisi, asal istilah, spektrum "vibe coding" vs "AI-assisted engineering" vs coding manual
* [x] 7.2 Kapan Cocok, Kapan Tidak — matrix stakes vs reversibility (prototype/throwaway script vs production/codebase besar)
* [x] 7.3 Anatomi Sesi Vibe Coding yang Baik — describe intent → run → observe → iterate, loop feedback cepat pakai Claude Code

Mini Project: Vibe-code satu tool kecil pribadi (mis. CLI scraper/utility) secara "vibes only" (tanpa baca detail kode yang di-generate) — lalu refleksi tertulis: bagian mana yang rusak atau tidak dipahami setelahnya.

Status: Selesai (2026-08-16). Semua 3 lesson selesai. 7.1 selesai (2026-08-15), skor 100/100 tanpa revisi (quiz + exercise benar semua; Exercise 4 nangkep nuansa verifikasi struktural/compiler vs vibe coding yang tidak eksplisit di theory). 7.2 selesai (2026-08-15), skor 3/3 setelah 1x revisi (Exercise 3 percobaan pertama restate mekanisme tanpa pisahkan axis stakes vs reversibility, revisi berhasil eksplisit). 7.3 selesai (2026-08-15), skor 3/3 quiz + 4/4 exercise setelah beberapa putaran revisi (Q1 diperkuat sampai eksplisit "success signal ≠ correct behavior"; Q3 dua percobaan pertama recycle contoh CSS/SQL dari 7.2 tanpa nyentuh mekanisme auto-run/auto-accept yang ditanya, revisi final spesifik; Exercise 2 sempat salah label tahap — reasoning cocok Iterate tapi ditulis Observe, diperbaiki). Mini Project selesai (2026-08-16): CLI scraper produk→CSV, prompt 1-shot, 2 ronde vibes-patch buta (TLS reset → tambah User-Agent/timeout; lalu 0 produk karena selector ditebak nggak cocok struktur HTML asli — baru ketahuan pas refleksi, bukan real-time). Refleksi tertulis nangkep "program jalan tanpa error ≠ program benar". Tool ini jadi bahan mentah Mini Project Module 8. Log: `playground/mastering-claude/7.1.md`, `playground/mastering-claude/7.2.md`, `playground/mastering-claude/7.3.md`, `playground/mastering-claude/7-mini-project.md`.

---

## Module 8 — Dari Vibe Coding ke Rekayasa Bertanggung Jawab

Description: Penyeimbang level lanjut/expert. Data risiko nyata vibe coding di production (security flaw, technical debt, test coverage anjlok, credential leak), lalu disiplin penyeimbangnya: spec-driven development dan verifikasi sebelum klaim selesai. Ditutup dengan pergeseran istilah Karpathy di 2026 dari "vibe coding" ke "agentic engineering" — orkestrasi banyak agent, bukan satu obrolan linear (nyambung ke Module 3 yang membahas plan mode & background agent).

Lessons:

* [x] 8.1 Risiko Vibe Coding di Dunia Nyata — data security flaw (~45% AI-generated code berisiko per Veracode 2025), technical debt, test coverage turun, credential leak; studi kasus
* [x] 8.2 Spec-Driven Development sebagai Penyeimbang — alur define intent → remove ambiguity → plan with constraints → implement dengan AI → validate against spec
* [x] 8.3 Disiplin Verifikasi — review diff, minimum test coverage, security review sebelum merge (pakai skill `security-review` & `sonarqube` yang sudah tersedia)
* [x] 8.4 Agentic Engineering — pergeseran istilah Karpathy 2026: orkestrasi banyak agent/subagent (plan mode, background agent, MCP) dibanding satu sesi chat linear

Mini Project: Ambil tool dari Mini Project Module 7, refactor pakai alur spec-driven + verifikasi penuh — bandingkan hasil "vibes only" vs "disciplined" (kualitas, waktu, kepercayaan diri terhadap kode sendiri).

Status: Selesai (2026-08-16). 8.1 selesai (2026-08-15), skor 4/4 quiz + 4/4 exercise setelah 1x revisi (Exercise 4 awalnya salah label "security flaw", direvisi jadi pengakuan eksplisit skenario tidak cocok bersih ke 4 kategori — dipilih "test coverage & overconfidence" secara analogis). 8.2 selesai (2026-08-16), skor 4/4 quiz setelah 1x revisi (Q1 & Q3) + Exercise lengkap tanpa revisi (draft SDD utility "extract page title" — remove ambiguity 5 edge case, validate against spec dengan cara verifikasi konkret tiap klausa). 8.3 selesai (2026-08-16), skor 4/4 quiz + exercise lengkap tanpa revisi (3 gerbang verifikasi diterapkan ke tool extract-title dari Exercise 8.2 — review diff nemuin duplikasi fetch, test coverage nuntut bukti urutan validasi-sebelum-fetch, security review nemuin risiko tambahan resource exhaustion/DoS di luar SSRF yang dicontohkan). 8.4 selesai (2026-08-16), skor 4/4 quiz tanpa revisi + exercise lengkap setelah 1x revisi (poin 4 Gerbang 2 awalnya cuma "test dijalankan mekanisme independen", direvisi eksplisit kaitkan ke line vs behavioral coverage & target 92% dari 8.1/8.3 di titik handoff antar-agent).

Mini Project selesai (2026-08-16): refactor nyata (bukan hipotetis) `playground/mastering-claude/7-mini-project/scraper.py` (CLI scraper vibes-only Module 7) jadi versi disciplined di `playground/mastering-claude/8-mini-project/scraper.py`. Siklus SDD penuh: user tulis spec sendiri (define intent + remove ambiguity 10 edge case + plan with constraints, `8-mini-project.md`), AI implement sesuai spec dengan selector yang divalidasi terhadap struktur HTML nyata (books.toscrape.com, bukan tebakan) — ketemu bug encoding riil (mojibake "Â£" krn `requests.text` fallback ISO-8859-1 padahal body UTF-8, di-fix pakai `response.content` + auto-detect BeautifulSoup). 3 gerbang verifikasi dijalankan sungguhan: review diff (bug encoding), test coverage (14 test, 99% line coverage — exceeds target 92% dari 8.1/8.3), security review (skill `security-review` di-scope manual ke file baru krn diff branch penuh nggak relevan — 0 finding HIGH/MEDIUM, SSRF & DoS excluded sesuai exclusion rules skill karena tidak ada privilege boundary yang dilanggar untuk CLI personal). Perbandingan tertulis vibes-only vs disciplined di `8-mini-project.review.md`: inti pembelajaran — "vibe coding mengoptimalkan speed-to-code, disciplined AI-assisted engineering mengoptimalkan speed-to-verified-result". Log: `playground/mastering-claude/8.1.md`, `8.2.md`, `8.3.md`, `8.4.md`, `8-mini-project.md`, `8-mini-project.review.md`, kode di `8-mini-project/`.

---

# Enterprise Project

Description: Menyusun "playbook" pribadi penggunaan AI (kombinasi CLAUDE.md, skills, plugin, automation, disiplin vibe coding vs spec-driven dari Module 7-8) + mengukur ROI (waktu vs biaya token vs kualitas hasil).

Acceptance Criteria (dirancang & disetujui 2026-08-16, tier Capstone per `ai-los/CORE_LOS.md`):

* **AC1 — Playbook document**: `portofolio/mastering-claude-playbook.md` — dokumen keputusan pribadi: kapan pakai CLAUDE.md vs Skill, kapan bikin Plugin, kapan automation/hook, plus decision matrix vibe-coding vs spec-driven (stakes × reversibility, dari 7.2).
* **AC2 — Automation piece nyata**: extend plugin `tdd-toolkit` (Module 4) dengan 1 hook/mekanisme baru yang mendemonstrasikan rule di playbook.
* **AC3 — ROI case study**: formalkan perbandingan scraper vibes-only (Module 7) vs disciplined (Module 8) yang sudah ada jadi case study resmi — waktu, estimasi token/cost, defect count, test coverage, confidence.
* **AC4 — Review**: dokumen di-review pakai Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score, `ai-los/CORE_LOS.md`).
* **AC5 — Lokasi**: hasil akhir tersimpan di `portofolio/`, bukan `playground/`.

Status: Selesai (2026-08-16), skor 90/100 setelah 1x revisi (gate hook §3 awalnya understate celah "state gak terikat ke file", diperjelas eksplisit; tabel ROI §4 awalnya campur angka waktu self-reported presisi semu, dipindah jadi catatan naratif). Artifact: `portofolio/mastering-claude-playbook.md`. Automation: `playground/mastering-claude/plugins/tdd-toolkit` v1.0.0 → v1.1.0 (2 hook baru, diverifikasi 7 skenario nyata).

---

# Overall Progress

Module Completion: 8/8

Overall Completion: 100% — course selesai penuh termasuk Enterprise Project (Capstone), 2026-08-16.

Current Module: Semua selesai.

Current Lesson: Enterprise Project (selesai, 2026-08-16, skor 90/100)

Next: Tidak ada — course tuntas. Lihat `portofolio/mastering-claude-playbook.md` untuk artifact final.
