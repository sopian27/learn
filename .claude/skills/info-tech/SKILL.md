---
name: info-tech
description: "Use when user minta info/berita teknologi TERBARU untuk sync ke Obsidian (trigger: \"info tech\", \"info tech terbaru\", \"berita tech\", \"/info-tech\"). Digest 3-5 item berita/update teknologi real (rilis, insiden, arsitektur, tool/framework) hasil web search hari ini, sync langsung ke vault, TANPA dump konten penuh di chat — cuma konfirmasi singkat. BEDA dari skill personal-knowledge-system: itu SATU topik edukasi mendalam lintas 7 kategori hidup (termasuk IT sebagai salah satu kategori, gaya esai kurator). Skill ini: MULTI-ITEM, gaya berita/rilis terkini, hasil pencarian real-time — bukan esai konsep timeless."
---

## Tujuan

Digest harian berita/update teknologi TERKINI — bukan esai konsep mendalam (itu jatah `personal-knowledge-system`). Fokus: hal yang benar-benar baru terjadi (rilis versi, CVE/insiden, keputusan arsitektur perusahaan, tool/framework baru, perubahan industri) yang relevan buat backend/software engineer.

Bukan mesin re-post RSS. Curator — pilih yang layak, bukan sebanyak-banyaknya.

## Prioritas Topik

Urutan minat (tinggi ke rendah), user backend engineer fokus Java/Spring:

1. Backend/server-side language & framework (Java, Spring, Kotlin, Go, Node, dll) — rilis versi, breaking change, deprecation.
2. System design / distributed systems / database / caching (Postgres, Redis, Kafka, dll) — insiden nyata, arsitektur baru dipublikasikan perusahaan (postmortem, engineering blog Netflix/Uber/Stripe/dll).
3. Cloud & DevOps (Kubernetes, Docker, AWS/GCP/Azure) — fitur baru, insiden outage besar, best practice baru.
4. AI/LLM dari sisi engineering (bukan hype produk) — API baru, model baru yang relevan dibangun di atasnya, teknik baru (RAG, agent, dll).
5. Security — CVE signifikan, breach besar, kelas kerentanan baru.

Skip: gadget consumer, rumor, opini tanpa substansi, listicle "10 tools you must know".

## Aturan Kurasi

1. Web search dulu (query tanggal hari ini / beberapa hari terakhir) — JANGAN karang berita dari ingatan/training data, harus hasil search real.
2. Pilih 3-5 item terbaik hari itu. Kalau tidak ada 3 item bernilai, kurang dari itu OK — jangan paksa isi filler.
3. Tiap item: sebutkan fakta inti + KENAPA penting buat engineer (dampak praktis/arsitektur), bukan cuma judul rewrite.
4. Sertakan sumber (link) tiap item — wajib bisa ditelusuri balik, jangan halusinasi sumber.
5. Cek file tanggal hari ini di vault dulu — kalau sudah ada, MERGE (tambah item baru yang belum ada), jangan overwrite/duplikat item lama.
6. Jangan ulang item yang sudah ditulis di file tanggal-tanggal sebelumnya kecuali ada perkembangan baru (mis. insiden yang sama tapi update status).

## Lokasi & Aturan File

- Vault root: baca `reference_obsidian_vault` di memory (`C:\Users\eluon\OneDrive\Documents\Obsidian Vault`).
- Folder: `info tech/` di root vault.
- Filename: `YYYY-MM-DD.md` (tanggal hari ini, bukan judul topik — beda dari personal-knowledge-system).
- Satu run = satu file per tanggal, isi bisa berupa banyak item (append kalau run ulang hari sama).
- Output harus Markdown Obsidian siap-simpan.
- JANGAN tampilkan isi/rekap konten item di chat. Balasan chat cuma: jumlah item + judul singkat tiap item (title-only list) + path file.

## Template File

```markdown
---
date: YYYY-MM-DD
tags:
  - tech-news
---

# Info Tech - YYYY-MM-DD

## [Judul Item 1]

- Source: [link]
- Apa yang terjadi: [fakta inti, singkat]
- Kenapa penting: [dampak praktis/arsitektur buat engineer]

## [Judul Item 2]

- Source: [link]
- Apa yang terjadi: ...
- Kenapa penting: ...
```

(Ulangi section `## [Judul]` per item, 3-5 item.)

## Alur Eksekusi

1. Baca `reference_obsidian_vault` di memory untuk path vault.
2. Cek `info tech/YYYY-MM-DD.md` (tanggal hari ini) — kalau sudah ada, baca dulu isinya (untuk merge, hindari duplikat).
3. Web search berita/update teknologi terkini (query spesifik per kategori prioritas di atas, bukan query generik "tech news").
4. Kurasi 3-5 item terbaik sesuai Aturan Kurasi.
5. Tulis/update `info tech/YYYY-MM-DD.md` sesuai template (buat folder `info tech/` kalau belum ada).
6. Balas user singkat: jumlah item + list judul + path file. Tidak ada rekap isi/konten di chat.
