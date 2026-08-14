# Token-Efficient Learning — Cara Belajar Maksimal, Token Hemat

Panduan ini melengkapi `ai-los/GETTING_STARTED.md`. GETTING_STARTED menjelaskan *cara pakai* AI-LOS. Dokumen ini menjelaskan *cara pakai secara hemat token* tanpa mengorbankan kedalaman belajar — karena `CLAUDE.md` secara eksplisit melarang jawaban dangkal ("Never optimize for finishing quickly. Optimize for deep understanding."), jadi penghematan token di sini TIDAK datang dari memotong kedalaman materi, tapi dari menghilangkan pemborosan di sekitarnya: konteks yang dibaca ulang, scope yang tidak jelas, dan sesi yang menggelembung.

---

## 1. Kenapa Ini Perlu Dokumen Sendiri

Situasi nyata di repo ini per 2026-08-07:

- **15+ course aktif paralel** (`courses/backend-fundamentals`, `ml-fundamentals`, `mastering-claude`, `ci-cd`, `personal-finance`, `n8n-automation`, `communication-skills`, `critical-thinking`, `character-development`, `world-history`, `guitar`, `self-love`, `computational-thinking`, `film-criticism`, `korean`, dst).
- `progress/roadmap.md` adalah **index wajib baca** di banyak alur kerja, dan setiap course baru menambah 1 paragraf panjang ke file itu — artinya file ini bertumbuh tanpa batas atas.
- Setiap sesi baru otomatis membaca `CLAUDE.md` → `ai-los/ACTIVE_MODE.md` → `ai-los/ACTIVE_DOMAIN.md` → domain/course terkait. Makin banyak course terdaftar, makin besar kemungkinan konteks yang tidak relevan ikut terbawa kalau prompt Anda tidak spesifik.

Karena itu, pemborosan token terbesar di AI-LOS bukan dari "penjelasan yang mendalam" (itu memang tujuannya), tapi dari:

1. Konteks besar dibaca ulang padahal tidak relevan ke pertanyaan saat ini.
2. Prompt yang scope-nya kabur, memicu eksplorasi bolak-balik.
3. Sesi yang terus dipakai lintas topik/course yang tidak berhubungan.
4. Riwayat panjang yang seharusnya sudah terkompres jadi file (`notes.md`, `cheatsheet.md`) tapi belum.

---

## 2. Prinsip Dasar

| Prinsip | Maksudnya |
|---|---|
| **Kedalaman tetap, konteks dipangkas** | Jangan minta jawaban lebih dangkal. Pangkas apa yang perlu dibaca untuk sampai ke jawaban itu. |
| **File progress = memori eksternal** | Jangan re-explain background di chat. Pastikan `progress/*.md` dan `courses/<nama>/roadmap.md` sudah mewakili state Anda, lalu rujuk saja. |
| **1 course = 1 fokus per sesi** | Jangan campur 3 course berbeda dalam satu thread panjang. |
| **Scope kecil per prompt** | Ini sudah jadi aturan CLAUDE.md sendiri ("Never generate huge lessons") — manfaatkan, jangan minta banyak sekaligus. |

---

## 3. Strategi Level Sesi (Session-Level)

### 3.1 Satu sesi, satu course/topik
Jangan lanjutkan sesi yang sedang membahas `courses/guitar` untuk tiba-tiba tanya soal `courses/backend-fundamentals`. Riwayat course gitar tetap "menempel" di context window sepanjang sesi itu meski tidak relevan lagi. Buka percakapan baru saat pindah course.

**Kapan lanjut sesi lama vs mulai baru:**

- Lanjut sesi lama → masih dalam lesson/topik yang sama, butuh riwayat diskusi sebelumnya.
- Mulai sesi baru → pindah course, pindah mode besar (misal dari LEARNING_MODE ke INTERVIEW_MODE penuh), atau sesi lama sudah sangat panjang (banyak lesson selesai) dan konteks lama sudah tidak dibutuhkan lagi karena sudah tersimpan di file.

### 3.2 Percayakan state ke file, bukan ke riwayat chat
Sebelum mulai sesi baru untuk sebuah course, pastikan `courses/<nama>/roadmap.md` (progress per-course) sudah update. Kalau sudah, Anda tidak perlu buka riwayat chat lama sama sekali — cukup bilang:

```
Lanjut courses/backend-fundamentals, lesson berikutnya.
```

Claude akan baca roadmap course itu untuk tahu di lesson mana Anda berhenti, tanpa perlu Anda ketik ulang histori.

---

## 4. Strategi Level Prompt

### 4.1 Pakai shortcut prompt yang sudah baku
Alih-alih menjelaskan panjang lebar apa yang Anda mau, gunakan shortcut yang sudah didefinisikan sistem — ini secara langsung mengarahkan Claude ke satu proses yang sudah jelas, tanpa perlu klarifikasi bolak-balik:

`Create Course` · `Review My Code` · `Generate Project` · `Generate Jira` · `Weekly Review` · `Interview Mode` · `Mock Interview` · `Generate Cheatsheet` · `Generate Flash Card` · `Generate Quiz`

### 4.2 Pakai framework prompt Anda sendiri (sudah ada di `progress/notes-me mastering claude.md`)
Anda sudah punya template ini dari course Mastering Claude — pakai konsisten, karena tiap field-nya langsung memangkas ambiguitas (= memangkas ronde klarifikasi = memangkas token):

```
GOAL       : apa yang mau dicapai, konkret
CONTEXT    : info yang relevan saja (bukan seluruh riwayat proyek)
CONSTRAINT : batasan (format, panjang, tech stack, yang TIDAK boleh)
FORMAT     : bentuk output yang diinginkan
TARGET     : tempat kejadian perkara
```

Contoh boros vs hemat:

| Boros | Hemat |
|---|---|
| "Aku pengen belajar Redis, tolong ajarin semuanya, aku juga masih agak bingung sama konsep cache secara umum, terus nanti kalau bisa dikaitkan juga sama Spring Boot punyaku yang lama..." | `GOAL: paham Redis cache-aside di Spring Boot. CONTEXT: sudah paham konsep cache umum, course courses/backend-fundamentals module Redis. TARGET: lesson berikutnya sesuai roadmap course itu.` |

### 4.3 Nyatakan scope eksplisit
- "Lanjut **1 lesson** saja" — bukan "ajarin modul ini sampai selesai".
- "Cukup **cheatsheet**, aku sudah paham teorinya" — bukan minta lesson lengkap ulang.
- "**Ringkas** saja, aku cuma butuh refresh" vs "**Mendalam**, ini konsep baru buatku" — beri sinyal level kedalaman secara eksplisit tiap prompt kalau berbeda dari default.

### 4.4 Trigger Adaptive Learning secara sadar
`ai-los/CORE_LOS.md` § Adaptive Learning sudah mendukung ini: *"If I already understand something, skip unnecessary repetition."* Tapi ini hanya jalan kalau Anda bilang. Contoh:

```
Skip bagian dasar array/linked list, aku sudah familiar. Fokus ke bagian B-Tree saja.
```

Ini memangkas output besar yang sebenarnya tidak Anda butuhkan.

### 4.5 Ganti mode dengan perintah pendek, bukan paragraf
`ai-los/ACTIVE_MODE.md` mendukung switch mode 1 baris:

```
switch to REVIEW_MODE
enable ARCHITECT_MODE
```

Ini lebih murah daripada menjelaskan ulang "tolong sekarang mode-nya jadi kayak reviewer yang galak evaluasi kodenya ya...".

---

## 5. Strategi Khusus: Mengelola 15+ Course Paralel

Ini spesifik untuk situasi Anda sekarang di repo ini.

- **Jangan buka course baru sebelum yang lama jelas statusnya** (in progress / paused eksplisit). Tiap course baru = 1 paragraf permanen tambahan di `progress/roadmap.md` yang ikut terbaca di banyak alur.
- **Pause eksplisit course yang sedang tidak dikerjakan** (seperti `courses/kubernetes` yang sudah berstatus Paused). Course paused tetap tercatat tapi Anda bisa bilang "abaikan course yang paused" saat minta ringkasan progres.
- **Gunakan `Weekly Review`** secara rutin untuk mengompres progres minggu berjalan jadi satu ringkasan di `progress/progress.md`, alih-alih nanti perlu scroll riwayat chat lama untuk rekonstruksi apa yang sudah terjadi.
- **Pertimbangkan meringkas entry course lama di `progress/roadmap.md`.** Saat ini tiap course dapat 1 paragraf sangat panjang (rationale lengkap, house rules, dst). Setelah course itu berjalan stabil, entry index-nya bisa dipendekkan jadi 1-2 baris ("status + link ke roadmap course-nya"), karena detail lengkap sudah ada permanen di `courses/<nama>/roadmap.md` sendiri — index tidak perlu duplikasi itu.

---

## 6. Strategi Level File

- **`Generate Cheatsheet` setelah tiap module selesai.** Cheatsheet padat jadi pengganti murah untuk "baca ulang lesson panjang" saat Anda butuh recall cepat nanti.
- **Update `progress/notes.md` untuk insight, bukan transkrip.** Catat kesimpulan/aha-moment, bukan menyalin ulang penjelasan Claude kata per kata — file yang ringkas lebih murah dibaca ulang di sesi berikutnya.
- **Satu file `journal.md` per entry singkat**, bukan naratif panjang — cukup untuk jejak "apa yang dikerjakan kapan", karena ini yang dipakai `ai-los/CAREER_MODE.md`/review mingguan untuk rekap, bukan buku harian.

---

## 7. Checklist Sebelum Kirim Prompt

- [ ] Course/topik yang saya maksud sudah jelas disebut namanya?
- [ ] Saya sudah pakai sesi yang tepat (bukan nyambung dari topik lain yang tidak relevan)?
- [ ] Scope prompt saya 1 hal spesifik (1 lesson / 1 review / 1 cheatsheet), bukan "ajari semua"?
- [ ] Saya sudah bilang kalau ada bagian yang mau di-skip karena sudah paham?
- [ ] Saya sudah pilih mode yang tepat (LEARNING/REVIEW/ARCHITECT/dst) lewat perintah singkat, bukan deskripsi panjang?

---

## 8. Yang TIDAK Perlu Dikorbankan

Supaya tidak salah arah — hal-hal ini **jangan** dipangkas demi hemat token, karena itu bertentangan langsung dengan filosofi `CLAUDE.md`:

- Penjelasan **why, how, trade-off, alternatif** per konsep baru.
- Porsi **80% praktik** per lesson (`ai-los/CORE_LOS.md` § Hands-on First).
- **Review mendalam** (Strengths/Weaknesses/Actionable Improvements/Score) setiap submit kode/artefak.
- **Riset multi-sumber** sebelum roadmap course baru dibuat.

Hemat token berarti hemat di *jalan menuju* jawaban itu, bukan di jawabannya sendiri.
