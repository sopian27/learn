---
name: course-next-recommendation
description: "Use when user asks what course to take next, minta rekomendasi course lanjutan, or otherwise wants guidance on sequencing among courses/roadmap in this repo."
---

## Tujuan

Tentukan course berikutnya paling tepat, berdasar: course selesai/aktif, prerequisite, dependency antar-course, urutan kesulitan, dan learning roadmap. Bukan sekadar kemiripan topik.

## Data Wajib Dibaca

1. `progress/roadmap.md` — index master semua course, status (Aktif/Paused/Disetujui/Draft), Master Goal, sequencing rationale. Baca full file (paging kalau kepotong).
2. Roadmap course kandidat kuat (`courses/<name>/roadmap.md`) — cek prerequisite, learning objectives, status modul.
3. Kalau course X (yang baru selesai/jadi trigger) tidak disebut user eksplisit, infer dari course yang baru saja selesai atau paling dekat progress-nya di `progress/roadmap.md`.

## 5 Langkah Wajib

1. Identifikasi skill yang sudah dikuasai dari course yang baru selesai/aktif.
2. Identifikasi prerequisite yang masih kurang untuk kandidat course berikutnya.
3. Analisis semua course tersedia di workspace (Aktif/Paused/Disetujui/Draft).
4. Kategorikan tiap course: Ready / Almost Ready / Not Ready.
5. Urutkan rekomendasi berdasar dependency dan value — bukan sekadar kemiripan topik.

## Prioritas Keputusan

Urutan: dependency → prerequisite → learning goal (Master Goal) → tingkat kesulitan → nilai praktis. Kalau beberapa course sama validnya, pilih SATU rekomendasi utama dan jelaskan trade-off alternatif — jangan list beberapa sebagai setara.

## Constraint Repo (cek selalu)

- Cap "Course Aktif" bersamaan: 2 course (kebijakan hemat token, lihat `progress/roadmap.md`).
- CLAUDE.md "Course Creation Discipline": jangan draft/riset course BARU kalau ada course aktif di bawah 50% modul selesai — kalau user minta juga, harus flag eksplisit dan minta konfirmasi dulu. Ini tidak berlaku untuk resume course yang sudah disetujui/didraft (bukan riset baru).

## Output Wajib (format tetap, dalam Bahasa Indonesia)

### 🎯 Recommended Next Course

Nama course, prioritas bintang (⭐ 1-5), Readiness (Ready/Almost Ready/Not Ready), bullet "Kenapa sekarang?", lalu dependency chain ASCII:

```text
Course Sebelumnya (selesai)
      ↓
Course Direkomendasikan
      ↓
Course Berikutnya (butuh prerequisite dari course ini)
```

### 🔀 Alternative Courses

Table: `Course | Priority | Bisa mulai? | Alasan`

### ⚠️ Jangan Ambil Dulu

Course yang menggoda tapi prematur, plus prerequisite yang masih kurang.

### 🗺️ Suggested Learning Path

ASCII flow diagram urutan lebih luas (lintas beberapa course ke depan).
