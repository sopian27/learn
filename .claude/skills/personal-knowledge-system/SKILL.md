---
name: personal-knowledge-system
description: "Use when user minta satu pengetahuan/insight baru hari ini di luar topik software engineering biasa (mis. \"kasih pengetahuan hari ini\", \"jalankan personal knowledge system\", \"PKS hari ini\", \"knowledge harian\"). Pilih SATU kategori dari daftar tetap, pilih SATU topik curated di dalamnya, tulis satu file Markdown ke Knowledge/[Category]/ di vault Obsidian. BUKAN untuk puisi (pakai skill poetry-reading) atau quotes pagi (pakai skill morning-wisdom) — dua topik itu punya skill & folder sendiri. BUKAN JUGA untuk \"info tech\"/\"info tech terbaru\"/\"berita tech\" (pakai skill info-tech) — itu digest MULTI-ITEM berita/rilis teknologi hasil web search real-time ke folder info tech/, beda dari skill ini yang SATU esai konsep timeless per run."
---

## Tujuan

Bukan mesin penghasil informasi. Curator yang membantu user menemukan hal-hal yang layak diketahui.

Tiap kali dijalankan: pilih SATU pengetahuan terbaik hari itu — bukan sebanyak-banyaknya. Prioritaskan insight yang bikin reaksi "oh, gak pernah mikir gitu sebelumnya" — bukan topik populer atau yang paling gampang dijelaskan.

## Kategori (pilih tepat satu per run)

Poetry dan Morning Wisdom SENGAJA tidak masuk daftar ini — sudah punya skill dan folder sendiri (`poetry-reading` → `poetry/`, `morning-wisdom` → `quotes/`) dengan template lebih spesifik. Jangan duplikasi di sini.

1. **Information Technology** — backend engineering, software architecture, system design, distributed systems, database, Java/Spring, AI/LLM, cloud, Kubernetes, DevOps, security, networking, engineering practice, teknologi baru. Goal: perkuat pemahaman fundamental cara kerja sistem. Prioritaskan konsep/trade-off/arsitektur/failure mode/reasoning di atas tutorial.
2. **Psychology** — human behavior, cognitive bias, emosi, ego, attachment, social psychology, decision making, motivasi, habit, personality, relationship, self-perception. Goal: paham cara manusia berpikir/merasa/berperilaku. Sitasi riset/eksperimen kalau memungkinkan.
3. **Philosophy** — Stoicism, existentialism, etika, meaning of life, free will, happiness, death, suffering, virtue, human nature, knowledge. Goal: berpikir lebih dalam, mempertanyakan asumsi. Tunjukkan pertanyaan mendasar yang coba dijawab filosofi itu — jangan cuma resitasi teori.
4. **Spirituality** — hubungan manusia-Tuhan, akhlak, sabar, syukur, ikhlas, tawakal, taubat, kesederhanaan, kematian, makna hidup, penyucian diri. Kalau pakai perspektif Islam, WAJIB sumber kredibel, JANGAN PERNAH karang dalil/hadis. Jangan reduksi jadi konten motivasi semata.
5. **History & Biography** — History: peradaban, perang, revolusi, penemuan, perubahan sosial, keputusan politik, bangkit-jatuhnya bangsa, peristiwa dunia. Biography: ilmuwan, engineer, entrepreneur, pemimpin, seniman, filsuf, tokoh agama, orang biasa dengan perjalanan hidup luar biasa. Goal: belajar dari apa yang sudah terjadi dan memahami manusia lewat perjalanan hidupnya. WAJIB jelaskan: apa yang terjadi → kenapa terjadi → keputusan kunci → konsekuensi → pelajaran. Jangan cuma narasi kronologi.
6. **Business & Entrepreneurship** — business idea, business model, startup, entrepreneurship, customer behavior, marketing, distribution, product validation, kompetisi, ekonomi, business failure, emerging market. Goal: ajari melihat masalah sebagai peluang dan paham bagaimana bisnis menciptakan nilai dan menghasilkan uang. WAJIB ajarkan rantai: Problem → Customer → Pain → Solution → Value → Business Model → Distribution → Competition → Risk → Validation. User berlatar software engineering — jelaskan kapan teknologi jadi leverage asli, tapi JANGAN paksakan teknologi ke masalah yang sebenarnya tidak butuh itu.
7. **World & Society** — geopolitik, ekonomi, demografi, budaya, dampak teknologi, perubahan sosial, tren global, pendidikan, kerja, lingkungan, masyarakat, masa depan umat manusia. Goal: bantu paham dunia besar di luar kerjaan dan hidup pribadi user. Prioritaskan topik yang jelaskan kenapa dunia berubah dan bagaimana berbagai perubahan saling terhubung.

## Aturan Kurasi (wajib dipatuhi tiap run)

1. Pilih SATU kategori untuk hari itu.
2. Pilih SATU topik spesifik dan bernilai di kategori itu.
3. Jelaskan dengan cara mudah dipahami tapi tetap dalam.
4. JANGAN ulang topik yang sudah pernah dibahas kecuali ada alasan kuat (cek dulu file yang sudah ada di `Knowledge/`).
5. Prioritaskan insight yang menghasilkan reaksi "oh, gak pernah mikir gitu sebelumnya" — bukan yang paling populer/gampang.
6. JANGAN kasih fakta telanjang — selalu jelaskan KENAPA fakta itu penting.
7. Kalau pakai info yang perlu diverifikasi, pakai sumber kredibel.
8. Pisahkan jelas fakta, interpretasi, dan opini — jangan campur aduk sebagai satu "kebenaran".

Kriteria pemilihan topik: kombinasi **Interesting × Useful × Surprising × Depth**. Tujuannya bikin user lebih pintar, mikir lebih kritis, paham manusia lebih baik, bantu karier, bantu paham bisnis, bantu paham dunia, lebih bijak, dan lihat hal dari sudut pandang baru. Jangan takut pilih topik yang tidak berhubungan langsung dengan kehidupan sehari-hari user — tujuannya memperluas cakrawala, bukan cuma memperdalam yang sudah disukai.

Kategori dipilih berdasar penilaian kurasi tiap run (bukan rotasi ketat terjadwal) — jadi dari luar urutannya bisa kelihatan tidak merata; itu wajar.

## Lokasi & Aturan File

- Vault path: baca `reference_obsidian_vault` di memory untuk root vault (`C:\Users\eluon\OneDrive\Documents\Obsidian Vault`).
- Folder: `Knowledge/[Category]/` di root vault — nama folder persis nama kategori di atas (mis. `Knowledge/History & Biography/`). Folder dibuat on-demand saat kategori itu pertama kali dipakai, jangan pre-scaffold semua folder di awal.
- Filename: `YYYY-MM-DD - [Topic].md`, pakai tanggal hari ini.
- Satu run = satu file baru. Jangan overwrite file tanggal yang sudah ada — kalau user run lagi di tanggal sama, tanya dulu mau tambah note kedua atau skip.
- Sebelum memilih topik, cek isi `Knowledge/` (semua subfolder) untuk hindari pengulangan topik.
- Output harus Markdown Obsidian siap-simpan.
- Jangan tambahkan penjelasan/rekap isi note di chat (no commentary sebelum/sesudah note) — cukup konfirmasi lokasi file.

## Template Wajib

```markdown
---
date: YYYY-MM-DD
category: [Category]
topic: [Topic]
tags:
  - knowledge
  - [category-slug]
---

# [Topic]

## The Idea

[Jelaskan ide utama secara singkat]

## Why It Matters

[Kenapa pengetahuan ini penting]

## Deep Dive

[Jelaskan secara mendalam tetapi mudah dipahami]

## Point of View

[Berikan perspektif atau cara pandang berbeda jika relevan — pisahkan fakta/interpretasi/opini]

## Example

[Contoh konkret]

## What Most People Miss

[Satu insight yang sering tidak disadari]

## Key Takeaways

- [Insight 1]
- [Insight 2]
- [Insight 3]

## Question to Think About

[Berikan satu pertanyaan yang membuat user berpikir]

## Sources

- [Sumber jika diperlukan]
```

## Gaya & Batasan

- Bahasa Indonesia untuk narasi, istilah teknis boleh tetap bahasa Inggris kalau memang istilah baku.
- Nada: curator/mentor yang mengajar, bukan generator info.
- Jangan pilih topik hanya karena populer.
- Untuk kategori Spirituality: jangan pernah karang dalil/hadis; kalau tidak yakin sumbernya, sebut eksplisit sebagai penjelasan umum, bukan kutipan langsung.
- Untuk kategori History & Biography: jangan karang detail biografis yang tidak yakin kebenarannya.

## Alur Eksekusi

1. Baca `reference_obsidian_vault` di memory untuk path vault.
2. Scan folder `Knowledge/` di vault (semua subfolder) untuk lihat topik yang sudah pernah dibahas.
3. Pilih satu kategori (dari 7 kategori di atas) berdasar kurasi hari itu.
4. Pilih satu topik spesifik dalam kategori itu, pastikan belum pernah dibahas (kecuali alasan kuat untuk mengulang).
5. Tentukan tanggal hari ini.
6. Kalau folder `Knowledge/[Category]/` belum ada, buat dulu.
7. Tulis file `Knowledge/[Category]/YYYY-MM-DD - [Topic].md` sesuai template di atas, patuhi 8 aturan kurasi.
8. Simpan file, balas user singkat — sebutkan kategori + topik yang dipilih dan lokasi file, tanpa restate isi note di chat.
