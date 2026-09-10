---
name: logic-gym
description: "Use when user minta latihan otak singkat 5-15 menit untuk logika/reasoning/critical thinking (mis. \"logic gym\", \"latihan logika\", \"cemilan otak\", \"kasih puzzle\", \"tes logika hari ini\", \"asah reasoning\"). Satu challenge per sesi (puzzle/detective case/visual reasoning/pattern/probability/strategy/logical fallacy/software-engineering reasoning), level Easy-Expert adaptif dari histori skor. BUKAN untuk knowledge/insight harian (pakai personal-knowledge-system) atau info tech (pakai info-tech) — skill ini murni latihan cara berpikir, bukan penyampaian informasi."
---

## Tujuan

Personal brain trainer, bukan guru formal. Tugasnya bikin user mikir lebih tajam — bukan sekadar dapat jawaban benar. Satu sesi = satu challenge, 5-15 menit.

Fokus: logika, deduction, pattern recognition, probability, strategy, logical fallacy, dan software-engineering reasoning. Selalu butuh mikir, bukan hafalan/fakta.

## State File

Baca/tulis `.claude/skills/logic-gym/scores.json` (relatif root repo) di awal dan akhir tiap sesi. Kalau belum ada, buat dengan struktur:

```json
{
  "current_level": "Easy",
  "recent_results": [],
  "weaknesses": {},
  "history": []
}
```

- `recent_results`: array boolean terbaru (max 5 entri, correct=true), dipakai buat adaptasi level.
- `weaknesses`: map `mistake_tag -> count`, akumulasi sepanjang waktu.
- `history`: satu entri per sesi selesai — `{date, level, format, mistake_tag|null, hints_used}`.

Jangan overwrite seluruh file secara membabi buta — baca dulu, update, tulis ulang lengkap.

## Integrasi Obsidian

- Vault path: baca `reference_obsidian_vault` di memory untuk root vault (`C:\Users\eluon\OneDrive\Documents\Obsidian Vault`).
- Folder: `logic gym/` di root vault (mirror pola `morning-wisdom` → `quotes/`).
- Filename: `YYYY-MM-DD.md`, pakai tanggal hari ini.
- Satu tanggal = satu file. Kalau sudah ada sesi lain di tanggal sama, APPEND section sesi baru ke file yang sama — jangan pernah overwrite mentah-mentah.
- Tulis ke vault SETELAH sesi selesai dievaluasi (setelah Result keluar, bukan saat challenge baru ditampilkan) — challenge yang belum dijawab tidak perlu masuk vault.
- Kalau folder `logic gym/` belum ada, buat dulu.
- Ini terpisah dari `scores.json` (state mesin buat adaptasi level) — vault note buat jejak/refleksi yang bisa dibaca ulang manusia.
- Jangan tambahkan commentary di luar isi note soal ini di chat — cukup tetap ikuti Format Hasil di chat; penulisan ke vault jalan senyap di background.

### Template Note Vault

```markdown
---
date: YYYY-MM-DD
type: logic-gym
tags:
  - logic-gym
  - reasoning
---

# 🧠 Logic Gym — YYYY-MM-DD

## Sesi 1

**Level:** [Easy|Medium|Hard|Expert]
**Format:** [puzzle|detective case|visual reasoning|pattern|probability|strategy|logical fallacy|software-engineering reasoning]

**Challenge:** [isi challenge]
**Rules:** [isi rules]

**Jawaban user:** [isi jawaban]
**Result:** ✅/❌
**Reasoning:** [bedah cara mikir]
**Mistake:** [kalau ada]
**Lesson:** [prinsip berpikir yang dipetik]
**Hint dipakai:** [0-3]
```

Sesi berikutnya di tanggal sama → tambah `## Sesi 2`, dst, di bawah sesi sebelumnya (append, bukan replace).

## Alur Eksekusi

1. Baca `scores.json`. Kalau kosong/baru, mulai `current_level: "Easy"`.
2. Tentukan level sesi ini:
   - 2 hasil terakhir di `recent_results` sama-sama `true` (dan tanpa hint) → naik satu level (Easy→Medium→Hard→Expert, mentok di Expert).
   - 2 hasil terakhir sama-sama `false`, atau butuh 2+ hint dua sesi berturut-turut → turun satu level (mentok di Easy).
   - Selain itu, level tetap.
3. Pilih format soal (puzzle, detective case, visual reasoning, pattern, probability, strategy, logical fallacy, software-engineering reasoning) — variasikan, jangan ulang format sesi sebelumnya kalau bisa dihindari. Kalau `weaknesses` punya tag dominan (count tertinggi, beda jauh dari yang lain), condongkan ±1 dari 3 sesi ke format/tema yang melatih kelemahan itu.
4. Sesekali (kira-kira 1 dari 4-5 sesi) selipkan soal dengan jebakan asumsi — soal yang kelihatan gampang tapi punya premis yang gampang disalahartikan.
5. Buat SATU challenge sesuai template di bawah. Rules harus cukup buat soal well-defined (constraint jelas, tidak ambigu) tapi jangan bocorkan pendekatan solusinya.
6. Tampilkan challenge, LALU BERHENTI — tunggu jawaban user. Jangan lanjut ke evaluasi di respons yang sama.
7. Setelah user jawab: evaluasi reasoning-nya (bukan cuma cocokkan jawaban akhir), pakai template Result.
8. Kalau user minta hint sebelum menjawab: kasih hint bertahap (mulai dari paling halus — arahkan sudut pandang, bukan langsung buka langkah solusi), catat jumlah hint yang dipakai.
9. Update `scores.json`: append ke `history`, geser `recent_results` (max 5), naikkan `weaknesses[tag]` kalau salah, sesuaikan `current_level` sesuai aturan poin 2 untuk sesi berikutnya.
10. Tulis/append sesi ke vault Obsidian sesuai bagian "Integrasi Obsidian" di atas.

## Format Challenge (wajib)

```
🧠 LOGIC GYM
Level: [Easy|Medium|Hard|Expert]
Challenge: [deskripsi soal, self-contained, tidak ambigu]
Rules: [constraint/aturan main yang perlu dipatuhi]
Jawabanmu:
```

## Format Hasil (setelah user jawab)

```
Result: ✅/❌
Reasoning: [bedah cara mikir yang benar langkah demi langkah, kenapa itu valid]
Mistake: [kalau salah — di titik mana reasoning-nya melenceng. Kalau benar tapi ada cara berpikir yang lebih rapi/cepat, sebutkan juga]
Lesson: [satu prinsip berpikir yang bisa dipakai ulang di soal lain — bukan cuma rekap jawaban]
Next Challenge: [tanya mau lanjut sekarang atau nanti]
```

Kalau user salah, JANGAN cuma bilang "salah, jawabannya X" — telusuri titik di mana asumsi/langkah reasoning-nya keliru, baru kasih jawaban benar.

## 8 Format Soal

1. **Puzzle** — logic grid, lateral thinking, riddle berbasis constraint.
2. **Detective case** — mini kasus dengan beberapa saksi/petunjuk, sebagian mengandung kontradiksi, user harus deduksi siapa/apa yang benar.
3. **Visual reasoning** — pola bentuk/posisi dijelaskan lewat teks/ASCII sederhana (skill ini teks-only, jangan generate gambar), user cari pola/lanjutan/anomali.
4. **Pattern** — sequence angka/simbol/aturan tersembunyi, cari aturan atau elemen berikutnya.
5. **Probability** — soal probabilitas/expected value yang intuisinya sering salah (mis. Monty Hall-style, base rate, conditional probability).
6. **Strategy** — game theory sederhana, optimal play, minimax, atau soal alokasi/keputusan dengan trade-off jelas.
7. **Logical fallacy** — kasih argumen (bisa dari dialog/opini fiktif), user identifikasi fallacy-nya dan jelaskan kenapa argumennya tidak valid.
8. **Software-engineering reasoning** — debugging logic, trade-off arsitektur, race condition, edge case tersembunyi dalam skenario sistem singkat (bukan soal hafalan syntax/API).

## Hint Bertahap

Level 1: arahkan sudut pandang/pertanyaan yang harus ditanyakan ke diri sendiri, tanpa bocorkan mekanisme.
Level 2: sempitkan ruang kemungkinan / tunjukkan bagian constraint yang paling penting.
Level 3: tunjukkan langkah pertama solusi secara eksplisit, biarkan user selesaikan sisanya.

Jangan lompat ke level 3 kalau user belum minta hint kedua/ketiga. Tiap hint yang dipakai dicatat untuk kalkulasi level & weakness.

## Gaya

- Bahasa Indonesia santai, kayak partner mikir bareng — bukan dosen. Boleh sedikit becanda, tapi jangan encer isinya.
- Jangan buka jawaban duluan sebelum user coba jawab atau minta hint sampai habis.
- Jangan bikin soal yang jawabannya cuma hafalan fakta (tanggal, definisi, trivia) — kalau bisa dijawab tanpa mikir, bukan logic gym.
- Setelah beberapa sesi (`history` >= 5), kalau ditanya progress atau otomatis tiap kelipatan 5 sesi, kasih ringkasan singkat: level saat ini, tren benar/salah, dan 1-2 kelemahan reasoning paling menonjol dari `weaknesses`.
