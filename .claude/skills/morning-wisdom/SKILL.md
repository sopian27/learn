---
name: morning-wisdom
description: "Use when user minta catatan quotes/refleksi pagi harian (mis. \"quotes hari ini\", \"morning wisdom\", \"bikinin catatan quotes\"). Menghasilkan satu file Markdown Obsidian per tanggal berisi refleksi mendalam dari sebuah quote/kisah/hikmah — bukan sekadar kumpulan quotes."
---

## Tujuan

Bukan mengumpulkan quotes. Tujuannya bikin user paham makna lebih dalam, melihat hidup dari sudut pandang lebih dewasa, dan jadi pribadi lebih baik. Jangan cuma bikin termotivasi — bikin melihat kehidupan dengan sudut pandang lebih luas.

## Lokasi & Aturan File

- Vault path: baca `reference_obsidian_vault` di memory untuk root vault (`C:\Users\eluon\OneDrive\Documents\Obsidian Vault`).
- Folder: `quotes/` di root vault.
- Filename: `YYYY-MM-DD.md`, pakai tanggal hari ini.
- Satu tanggal = satu file. Kalau file untuk tanggal itu sudah ada, UPDATE/MERGE isinya — jangan pernah overwrite mentah-mentah.
- Output harus Markdown Obsidian siap-simpan.
- Jangan tambahkan penjelasan di luar isi catatan (no commentary sebelum/sesudah note di chat).

## Template Wajib

```markdown
---
date: YYYY-MM-DD
type: morning-wisdom
tags:
  - quotes
  - morning-reflection
  - life-wisdom
---

# 🌅 Morning Wisdom — YYYY-MM-DD

> [Quote atau kalimat hikmah utama]

**Sumber:** [Tokoh / kisah / anonim / refleksi orisinal]

## 1. Apa yang Sebenarnya Ingin Disampaikan?
## 2. Point of View — Melihat dari Sudut Pandang yang Berbeda
## 3. Kisah Nyata atau Ilustrasi
## 4. Pelajaran yang Sering Kita Lewatkan
## 5. Cermin untuk Diri Saya
## 6. Pertanyaan Introspeksi
## 7. Satu Tindakan Kecil Hari Ini
## 8. Penutup
```

## Panduan Per Section

1. **Apa yang Sebenarnya Ingin Disampaikan?** — bahasa simpel, bukan terjemahan. Cakup: pesan utama, kenapa penting, kesalahpahaman umum.
2. **Point of View** — tawarkan sudut pandang bervariasi (orang yang sedang gagal, orang yang sudah melewati fase itu, orang bijak, perspektif Islam kalau relevan — sabar/syukur/tawakal/ikhlas/tanggung jawab). Jangan paksakan semua sudut pandang — pilih yang paling relevan buat quote hari itu.
3. **Kisah Nyata atau Ilustrasi** — kalau pakai kisah nyata, jangan karang detail. Kalau ragu, sebut eksplisit sebagai ilustrasi, bukan kisah spesifik nyata.
4. **Pelajaran yang Sering Kita Lewatkan** — 1-2 pelajaran yang tidak obvious (contoh pola: mengira masalahnya kegagalan padahal ego; mengira butuh motivasi padahal butuh keberanian mulai; mengejar sukses padahal mengejar pengakuan; mengira harus lebih kuat padahal harus menerima kenyataan).
5. **Cermin untuk Diri Saya** — kaitkan relevan ke hidup user (kandidat tema: terlalu bergantung penilaian orang lain, takut terlihat gagal, menurunkan ego, jadi lebih konsisten, belajar ikhlas dan sabar, menerima kenyataan, jadi lebih dewasa). Jangan asumsikan user mengalami semua tema ini — pilih yang relevan.
6. **Pertanyaan Introspeksi** — 2-3 pertanyaan yang benar-benar bikin mikir, BUKAN pertanyaan motivasi generik.
7. **Satu Tindakan Kecil Hari Ini** — satu tindakan konkret, simpel, realistis, terkait langsung ke pelajaran hari itu.
8. **Penutup** — satu kalimat pendek yang menenangkan tapi tetap membangkitkan kesadaran.

## Gaya & Batasan (wajib dipatuhi tiap kali)

- Bahasa Indonesia.
- Nada mentor bijak sekaligus teman yang peduli.
- Hangat, jujur, menyentuh. Boleh sedikit menohok kalau perlu bikin sadar.
- JANGAN lebay, JANGAN menggurui, JANGAN bikin user merasa kecil/inferior.
- Jangan selalu pakai framing sukses. Jangan selalu suruh kerja lebih keras.
- Kadang pelajarannya soal menerima kenyataan, berhenti mengejar pengakuan, atau belajar melepas — bukan selalu soal pencapaian.
- Ringkas — untuk dibaca & direnungkan di pagi hari, bukan esai panjang.
- Prinsip inti: jangan hanya bikin termotivasi, bikin melihat hidup dengan sudut pandang lebih luas.

## Alur Eksekusi

1. Tentukan tanggal hari ini.
2. Cek apakah `quotes/YYYY-MM-DD.md` sudah ada di vault.
3. Kalau belum ada folder `quotes/`, buat dulu.
4. Kalau file belum ada: buat baru sesuai template di atas.
5. Kalau file sudah ada: baca isinya dulu, lalu update/merge (jangan overwrite mentah) — misal user minta ganti quote atau nambah refleksi di hari yang sama.
6. Simpan file, balas user singkat (tanpa restate isi note di chat) — konfirmasi lokasi file saja.
