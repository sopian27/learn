# Getting Started — Cara Menggunakan AI-LOS

Tutorial ini menjelaskan cara memakai sistem AI-LOS di repo ini, dengan contoh konkret: **belajar React**.

---

## 1. Apa yang Terjadi di Setiap Sesi

Setiap sesi baru, Claude membaca urutan ini secara otomatis (lihat root `CLAUDE.md`, "Session Start"):

1. `ai-los/ACTIVE_MODE.md` — mode mentor mana yang aktif
2. `ai-los/ACTIVE_DOMAIN.md` — domain (subject) mana yang aktif
3. `ai-los/CORE_LOS.md` — proses belajar domain-agnostik, plus domain aktifnya `domains/<name>/DOMAIN.md`
4. Folder lain (`standards/`, `templates/`, `courses/`, dll) dibaca on-demand saat relevan — bukan dipaksa tiap sesi

Anda tidak perlu melakukan apa pun untuk langkah ini — otomatis.

---

## 2. Mode Aktif Saat Ini

| Mode | Status | Fungsi |
|---|---|---|
| LEARNING_MODE | Primary | Mengajar konsep secara terstruktur |
| MENTOR_MODE | Secondary | Membimbing, bukan sekadar menjawab |
| REVIEW_MODE | Secondary | Review kode yang Anda submit |

**Cara ganti mode:** ketik `switch to <MODE>`, `enable <MODE>`, atau `disable <MODE>`.
Contoh: `switch to INTERVIEW_MODE` untuk simulasi interview, `enable ARCHITECT_MODE` untuk diskusi arsitektur.

Mode bisa digabung. Kombinasi umum ada di `ai-los/ACTIVE_MODE.md`.

---

## 3. Alur Belajar Topik Baru (Contoh: React)

### Langkah 1 — Nyatakan niat belajar
Ketik, misalnya:

```
Saya mau belajar React dari nol.
```

### Langkah 2 — Riset & Rancang Kurikulum (otomatis, bukan tugas Anda)
Sesuai `ai-los/COURSE_CREATION.md`, Claude TIDAK langsung mengajar. Urutannya:

- [ ] Riset topik dari banyak sumber (roadmap.sh, dokumentasi resmi, blog engineering)
- [ ] Susun roadmap
- [ ] Susun modul
- [ ] Tentukan prasyarat
- [ ] **Minta approval Anda** sebelum mulai Lesson 1

### Langkah 3 — Anda Approve / Revisi Roadmap
Anda bisa minta ubah urutan, tambah/hapus topik, atau atur level target (pemula/menengah/lanjut) sebelum lanjut.

### Langkah 4 — Lesson per Lesson
Setiap lesson mengikuti format tetap (`ai-los/CORE_LOS.md` § Universal Learning Loop):

```
Objective → Prerequisite → Theory → Diagram → Example
  → Quiz → Exercise → Mini Project → Summary → Next Lesson
```

Porsi: **20% teori, 80% praktik** — tiap lesson wajib ada coding, bukan cuma bacaan.

> Catatan: template lesson aslinya punya field "Java Example" / "Spring Example" (repo ini awalnya fokus backend). Untuk topik non-Java seperti React, field ini otomatis diganti jadi contoh yang relevan (mis. "Component Example", "Hook Example").

---

## 4. Ke Mana Hasilnya Disimpan

| Folder | Isi |
|---|---|
| `courses/react/` | Roadmap, modul, dan lesson React (format `templates/ROADMAP_TEMPLATE.md`) |
| `progress/` | `roadmap.md`, `progress.md`, `notes.md`, `cheatsheet.md`, dll — di-update berjalan |
| `playground/` | Eksperimen/latihan cepat per lesson, tidak perlu rapi |
| `portofolio/` | Proyek React kelas produksi di akhir course |
| `challenges/` | Latihan soal tambahan |

---

## 5. Shortcut Prompt yang Bisa Dipakai

- `Create Course` — mulai course baru
- `Review My Code` — minta review kode
- `Generate Project` — minta ide/spesifikasi mini project
- `Generate Jira` — dapat tiket kerja simulasi (Enterprise Mode)
- `Weekly Review` — rekap progres mingguan
- `Interview Mode` — simulasi interview
- `Mock Interview` — interview penuh dengan penilaian
- `Generate Cheatsheet` — ringkasan materi
- `/recommend` — cari & rangking course eksternal terbaik untuk skill berikutnya (lihat `ai-los/COURSE_RECOMMENDATION.md`)

---

## 6. Contoh Konkret: Mulai Sekarang

```
Saya mau belajar React dari nol. Level saya pemula di frontend
tapi sudah paham JavaScript dasar. Pakai TypeScript, target akhir
bisa bikin proyek portofolio.
```

Claude akan meriset, menyusun roadmap `courses/react/roadmap.md`, lalu menunggu approval Anda sebelum Lesson 1 dimulai.
