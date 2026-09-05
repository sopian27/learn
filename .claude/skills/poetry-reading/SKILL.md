---
name: poetry-reading
description: "Use when user minta catatan puisi harian (mis. \"puisi hari ini\", \"bikinin catatan puisi\", \"poetry reading\"). Menghasilkan satu file Markdown Obsidian per tanggal berisi puisi + bedah makna + berbagai sudut pandang — course membaca dan memahami puisi, BUKAN morning reflection/quotes motivasi/jurnal self-development."
---

## Tujuan

Course membaca dan memahami puisi (literary interpretation), bukan pengembangan diri.

Ajarkan: menikmati keindahan puisi, memahami makna di balik kata, mengenali metafora/simbol, memahami emosi yang disampaikan penyair, melihat satu puisi dari berbagai sudut pandang, memahami konteks budaya/zaman, membedakan makna literal vs interpretasi.

JANGAN memaksakan tiap puisi jadi pelajaran hidup/motivasi. Kalau puisi bicara cinta → jelaskan cinta. Kalau bicara kematian → jelaskan kematian. Kalau bicara alam → jelaskan alam. Kalau bicara kesunyian → jelaskan kesunyian. Biarkan puisi berbicara lewat dirinya sendiri.

## Pemilihan Puisi

Penyair mana saja yang relevan dan berkualitas — jangan terpaku satu penyair (Gibran boleh salah satu sumber, bukan satu-satunya).

Rentang: Indonesia (Sapardi, Chairil Anwar, Rendra, dll), Timur Tengah (Rumi, Gibran), Eropa, Amerika (Neruda, Dickinson, Frost), Asia, klasik maupun modern.

Sesekali pilih karya kurang populer tapi punya kedalaman makna — jangan selalu yang paling terkenal.

## Lokasi & Aturan File

- Vault path: baca `reference_obsidian_vault` di memory untuk root vault (`C:\Users\eluon\OneDrive\Documents\Obsidian Vault`).
- Folder: `poetry/` di root vault.
- Filename: `YYYY-MM-DD.md`, pakai tanggal hari ini.
- Satu tanggal = satu file. Kalau file untuk tanggal itu sudah ada, UPDATE/MERGE isinya — jangan overwrite mentah, jangan buat banyak file dalam satu kali generate.
- Output harus Markdown Obsidian siap-simpan.
- Jangan tambahkan penjelasan di luar isi catatan (no commentary sebelum/sesudah note di chat).

## Template Wajib

```markdown
---
date: YYYY-MM-DD
type: poetry
author: [Nama Penyair]
title: [Judul Puisi]
tags:
  - poetry
  - literary-interpretation
  - [nama-penyair]
---

# [Judul Puisi]

**Penyair:** [Nama]
**Tahun:** [Jika diketahui]
**Karya:** [Buku / kumpulan puisi, jika relevan]

> [Kutipan pendek dari puisi, jika hak cipta memungkinkan]

## 1. Tentang Puisi Ini
## 2. Makna Puisi
## 3. Bedah Bahasa dan Simbol
## 4. Point of View
## 5. Interpretasi yang Mungkin
## 6. Mengapa Puisi Ini Menarik?
## 7. Pertanyaan untuk Pembaca
## 8. Penutup
```

## Panduan Per Section

1. **Tentang Puisi Ini** — siapa penyairnya, konteks karya, tema utama, singkat.
2. **Makna Puisi** — apa yang terjadi dalam puisi, apa yang dirasakan, apa yang ingin disampaikan, apa yang membuatnya menarik. Bahasa mudah dipahami tapi tetap mendalam.
3. **Bedah Bahasa dan Simbol** — metafora/simbol/pilihan kata penting. Kenapa penyair pilih kata itu, apa yang berubah kalau diganti.
4. **Point of View** — beberapa cara membaca (literal, emosional, filosofis, psikologis, budaya/sejarah). Jangan paksakan semua sudut pandang, pilih yang relevan buat puisi itu.
5. **Interpretasi yang Mungkin** — 2-3 kemungkinan interpretasi kalau puisinya terbuka ditafsirkan. Tegaskan ini kemungkinan pembacaan, bukan satu-satunya kebenaran.
6. **Mengapa Puisi Ini Menarik?** — nilai sastranya: keindahan bahasa, kedalaman emosi, simbol kuat, cara pandang penyair, kontras/paradoks, kesederhanaan yang menyimpan makna.
7. **Pertanyaan untuk Pembaca** — 2-3 pertanyaan yang bantu baca puisi lebih dalam (bukan pertanyaan motivasi/introspeksi diri).
8. **Penutup** — satu kalimat yang merangkum keindahan/inti puisi ini.

## Gaya & Batasan (wajib dipatuhi tiap kali)

- Bahasa Indonesia.
- Seperti guru sastra yang bijaksana tapi mudah dipahami — mendalam, tenang, tidak terburu-buru.
- Jangan terlalu akademis; jangan terlalu sederhana sampai kehilangan makna.
- JANGAN ubah puisi jadi motivasi. JANGAN selalu hubungkan ke kehidupan pribadi user. JANGAN selalu kasih tindakan/nasihat.
- Fokus pada puisi, bahasa, makna, interpretasi — bukan diri user.
- Prinsip inti: jangan cuma bikin user membaca puisi — ajari cara melihat dunia lewat puisi.

## Alur Eksekusi

1. Tentukan tanggal hari ini.
2. Cek apakah `poetry/YYYY-MM-DD.md` sudah ada di vault.
3. Kalau belum ada folder `poetry/`, buat dulu.
4. Kalau file belum ada: pilih satu puisi (variasikan penyair antar tanggal, cek catatan sebelumnya kalau perlu supaya tidak mengulang penyair/puisi yang sama terus), buat baru sesuai template di atas.
5. Kalau file sudah ada: baca isinya dulu, lalu update/merge (jangan overwrite mentah) — misal user minta ganti puisi atau nambah bedah di hari yang sama.
6. Simpan file, balas user singkat (tanpa restate isi note di chat) — konfirmasi lokasi file saja.
