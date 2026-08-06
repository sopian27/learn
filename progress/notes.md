# Notes

Catatan konsep ad-hoc yang tidak terikat satu course tertentu (kebutuhan kerja langsung, dsb).

---

## Git — Dual GitLab Workflow (Internal → External) — 2026-08-06

Konteks: kantor punya GitLab internal dan GitLab eksternal. Alur kerja: `main` → merge ke `pre-master` → squash commit `pre-master` ke `master` → push `master` ke GitLab eksternal.

Poin kunci yang diajarkan:

* `git merge --squash` tidak membuat merge commit dan tidak mencatat parent — akibatnya Git tidak tahu isi `pre-master` sudah pernah masuk ke `master`, sehingga squash berikutnya bisa memicu conflict yang sama berulang.
* Solusi standar: recreate branch `pre-master` dari `main` setiap selesai satu siklus squash-merge ke `master`, alih-alih menjaga `pre-master` tetap hidup lintas rilis.
* `--force-with-lease` harus jadi default dibanding `--force` polos saat push branch yang riwayatnya ditulis ulang (squash/rebase), karena menolak push kalau remote sudah berubah sejak fetch terakhir.
* Alasan bisnis di balik pola ini: kebersihan history untuk pihak eksternal, mencegah jejak data sensitif dari commit sementara ikut terkirim, dan `master` sebagai representasi rilis resmi (1 commit = 1 rilis/fitur).

Status: penjelasan konsep sudah diberikan + quiz diajukan (belum dijawab user). Tawaran sandbox latihan di `playground/` (2 repo bare simulasi internal/eksternal) belum dikonfirmasi user.

---

## Claude / AI Usage Mastery — dari 0 sampai Expert — 2026-08-06

Konteks: user sering pakai Claude tapi merasa boros token dan belum tahu cara kombinasikan dengan ChatGPT/tool AI lain. Diajarkan sebagai track terpisah (bukan course formal), bertahap per level.

Roadmap yang disepakati:
* Level 0 — Mental model: semua (system prompt, CLAUDE.md, riwayat chat, hasil tool call) numpuk di context window dan dihitung sebagai token, bukan cuma pesan user.
* Level 1 — Prompting fundamental: prompt spesifik (Goal/Context/Constraint/Format) di awal lebih murah daripada prompt umum + banyak iterasi klarifikasi.
* Level 2 — Token economy Claude Code: command `/context`, `/usage`, `/compact [fokus]`, `/clear`, `/model`, `/memory`; prompt caching (hidup 1 jam di subscription, ~10% biaya saat cache hit, hangus kalau ganti model/effort/MCP/`/compact`); subagent untuk isolasi context riset besar; CLAUDE.md sebagai memori terkompresi (jaga <200 baris, instruksi spesifik-tugas taruh di Skill bukan CLAUDE.md).
* Level 3 — Tooling lanjutan (skills, hooks, plan mode, MCP) — BELUM diajarkan.
* Level 4 — Kombinasi multi-AI (Claude vs ChatGPT vs Gemini, pembagian kerja per kekuatan model, cross-review) — baru preview singkat, BELUM didalami.
* Level 5 — Expert: playbook pribadi, automation, ukur ROI — BELUM diajarkan.

Status: Level 0-2 **selesai** — quiz + 3 exercise (`/context`, `/usage`, rewrite prompt) sudah dikerjakan user di `playground/mastering-claude/answer.md` dan direview 2026-08-06. Command reference tersimpan di [[cheatsheet]] `progress/cheatsheet.md`. Fakta teknis (command, cache behavior) diverifikasi lewat agent referensi resmi Claude Code, bukan dari ingatan model — supaya tidak mengajarkan detail yang basi/salah.

Temuan dari exercise (bukti nyata, bukan teori): `/usage` user menunjukkan "80% of usage was at >150k context" — pola pemakaian user sendiri cenderung sesi panjang tanpa `/compact`/`/clear`, persis pola boros yang diajarkan di Level 2. Actionable takeaway personal: biasakan `/compact` saat masih 1 task tapi mulai berat, `/clear` saat pindah task.

Catatan kekuatan/kelemahan user dari review: instinct prompting di atas rata-rata (menambahkan constraint "jelaskan kalau ragu" tanpa diajarkan eksplisit — kebiasaan senior engineer). Area yang perlu diasah: membedakan CONTEXT (latar belakang/alasan) vs TARGET (lokasi/scope kerja) — masih sering tertukar.

Next session: lanjut Level 3 (tooling: skills, hooks, plan mode, MCP) atau Level 4 (kombinasi multi-AI Claude+ChatGPT) sesuai pilihan user.
