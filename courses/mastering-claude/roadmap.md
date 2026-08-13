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

* [ ] 5.1 Cowork Overview & Positioning (beda dari Claude Code & chat biasa)
* [ ] 5.2 Scheduled & Unattended Tasks
* [ ] 5.3 Computer Use / Dispatch
* [ ] 5.4 Kapan Pakai Cowork vs Claude Code vs Chat Biasa — studi kasus

Mini Project: TBD

Status: Belum dimulai

---

## Module 6 — Kombinasi Multi-AI

Description: Strategi pembagian kerja antara Claude dan tool AI lain (ChatGPT, Gemini, dll) berdasarkan kekuatan masing-masing, plus pola cross-model review.

Lessons:

* [ ] 6.1 Kapan Pakai Model/Tool Apa (brainstorming vs eksekusi terstruktur)
* [ ] 6.2 Cross-Model Review — menangkap blind spot satu model

Portfolio Project: TBD

Status: Belum dimulai (preview singkat sudah diberikan 2026-08-06)

---

## Module 7 — Vibe Coding: Filosofi & Alur Kerja

Description: "Vibe coding" (istilah Andrej Karpathy, Feb 2025) — gaya coding di mana kamu menyatakan intent dalam bahasa natural dan membiarkan AI generate/iterate, "melupakan kode itu ada". Module ini membangun pemahaman dari nol: apa bedanya dengan AI-assisted coding biasa, kapan gaya ini cocok dipakai, dan kapan berbahaya. Ditambahkan 2026-08-06 atas permintaan user (awalnya diajukan sebagai course terpisah, digabung ke sini karena user sudah pakai Claude Code sebagai tool utama).

Lessons:

* [ ] 7.1 Apa Itu Vibe Coding — definisi, asal istilah, spektrum "vibe coding" vs "AI-assisted engineering" vs coding manual
* [ ] 7.2 Kapan Cocok, Kapan Tidak — matrix stakes vs reversibility (prototype/throwaway script vs production/codebase besar)
* [ ] 7.3 Anatomi Sesi Vibe Coding yang Baik — describe intent → run → observe → iterate, loop feedback cepat pakai Claude Code

Mini Project: Vibe-code satu tool kecil pribadi (mis. CLI scraper/utility) secara "vibes only" (tanpa baca detail kode yang di-generate) — lalu refleksi tertulis: bagian mana yang rusak atau tidak dipahami setelahnya.

Status: Belum dimulai (draft baru 2026-08-06, menunggu approval user)

---

## Module 8 — Dari Vibe Coding ke Rekayasa Bertanggung Jawab

Description: Penyeimbang level lanjut/expert. Data risiko nyata vibe coding di production (security flaw, technical debt, test coverage anjlok, credential leak), lalu disiplin penyeimbangnya: spec-driven development dan verifikasi sebelum klaim selesai. Ditutup dengan pergeseran istilah Karpathy di 2026 dari "vibe coding" ke "agentic engineering" — orkestrasi banyak agent, bukan satu obrolan linear (nyambung ke Module 3 yang membahas plan mode & background agent).

Lessons:

* [ ] 8.1 Risiko Vibe Coding di Dunia Nyata — data security flaw (~45% AI-generated code berisiko per Veracode 2025), technical debt, test coverage turun, credential leak; studi kasus
* [ ] 8.2 Spec-Driven Development sebagai Penyeimbang — alur define intent → remove ambiguity → plan with constraints → implement dengan AI → validate against spec
* [ ] 8.3 Disiplin Verifikasi — review diff, minimum test coverage, security review sebelum merge (pakai skill `security-review` & `sonarqube` yang sudah tersedia)
* [ ] 8.4 Agentic Engineering — pergeseran istilah Karpathy 2026: orkestrasi banyak agent/subagent (plan mode, background agent, MCP) dibanding satu sesi chat linear

Mini Project: Ambil tool dari Mini Project Module 7, refactor pakai alur spec-driven + verifikasi penuh — bandingkan hasil "vibes only" vs "disciplined" (kualitas, waktu, kepercayaan diri terhadap kode sendiri).

Status: Belum dimulai (draft baru 2026-08-06, menunggu approval user)

---

# Enterprise Project

Description: Menyusun "playbook" pribadi penggunaan AI (kombinasi CLAUDE.md, skills, plugin, automation, disiplin vibe coding vs spec-driven dari Module 7-8) + mengukur ROI (waktu vs biaya token vs kualitas hasil).

Acceptance Criteria: TBD — dirancang setelah Module 1-8 selesai.

Status: Belum dimulai

---

# Overall Progress

Module Completion: 4/8

Overall Completion: 50%

Current Module: Module 5 — Claude Cowork (belum dimulai)

Current Lesson: -

Next Lesson: 5.1 Cowork Overview & Positioning (beda dari Claude Code & chat biasa)
