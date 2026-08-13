# Interview Questions Log

Kumpulan pertanyaan interview-style dari tiap lesson, buat spaced review. Update tiap lesson yang punya sesi interview questions.

---

## Mastering Claude — Module 4.1 — Anatomi Skill (2026-08-13)

1. **Kenapa description skill signifikan lebih penting dari isi body-nya buat reliability sistem?**
   Description = sinyal utama skill discovery (Level 1, selalu di context). Kegagalan di sini bersifat silent — skill gak pernah dipanggil, gak ada error/warning, cuma keliatan sebagai "Claude gak pake skill itu". Body yang jelek masih ketauan pas di-invoke (gampang debug); description yang jelek bahkan gak pernah nyampe ke body.

2. **Trade-off: skill vs subagent vs slash command — kapan masing-masing dipilih?**
   - Skill: knowledge yang di-load ke context main thread, dipilih probabilistik lewat kecocokan description.
   - Subagent: context terisolasi, main thread cuma liat laporan akhir — dipakai buat delegasi task besar/independen DAN proteksi context utama dari hasil verbose.
   - Slash command: explicit action dipanggil langsung user by name — trigger deterministik, gak lewat keputusan model sama sekali.

3. **Staff-level: gimana desain description biar gak collide/overlap sama skill lain yang mirip topik?**
   - Scope boundary eksplisit (contoh nyata: skill `claude-api` di Claude Code punya klausa SKIP eksplisit kalau provider lain disebut).
   - Keyword/entity spesifik, bukan kata umum (contoh: "Use when writing JUnit tests for Spring Boot services using Mockito" > "helps with testing").
   - Cek manual collision: bandingkan description skill baru terhadap skill lain yang topiknya deket sebelum finalize.

Jawaban asli attempt pertama sempat kurang tajam (subagent dijelasin cuma soal "task besar" tanpa nyebut isolasi context; Q3 jawab "trigger spesifik dan significant" tanpa teknik konkret) — revisi kedua nangkep axis pembeda yang bener. Detail lengkap attempt 1 vs 2: `playground/mastering-claude/4.1.md` vs `4.1.fix.md`.

---
