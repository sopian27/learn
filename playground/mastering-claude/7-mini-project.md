# Mini Project Module 7 — Vibe-Code "Vibes Only"

Tool: CLI scraper judul + harga produk e-commerce → CSV. Kode: `playground/mastering-claude/7-mini-project/scraper.py`.

## Sesi Vibe Coding (vibes only — kode tidak dibaca)

**Prompt awal (intent, natural language):**
> "Bikin CLI scraper sederhana yang bisa mengambil judul dan harga produk dari sebuah halaman e-commerce, lalu menyimpan hasilnya ke file CSV. Buat supaya mudah dipakai dari terminal dan cukup robust untuk halaman yang punya banyak produk."

Generate 1-shot, tanpa klarifikasi balik (disiplin vibes-only dari sisi AI juga — langsung generate, bukan tanya requirement dulu).

**Iterasi 1 — run, observe:**
- Gejala: `ConnectionResetError: [WinError 10054]` — gagal di tahap TLS/SSL, belum sampai scraping.
- Vibes-patch (tebakan cepat, tanpa diagnosis mendalam): tambah header `User-Agent` + `timeout`.

**Iterasi 2 — run, observe:**
- Gejala: request sukses, tapi CSV cuma header, 0 produk.
- Root cause (baru ketahuan lewat refleksi, bukan saat itu): target (`dummyjson.com`) adalah JSON API test site, bukan halaman HTML toko dengan class `.product-title`/`.price` — selector yang ditebak AI nggak pernah cocok.
- Diputuskan stop iterate, lanjut ke refleksi (2 titik kegagalan tanpa baca kode sudah cukup bahan).

## Refleksi Tertulis

**1. Bagian yang rusak:**
Request ke website sudah berhasil, tetapi scraper tidak menemukan elemen produk yang cocok dengan selector yang digunakan, sehingga `products` kosong dan CSV hanya berisi header. Masalahnya ada di ekstraksi/parsing data, bukan koneksi.

**2. Bagian yang tidak dipahami:**
Karena tidak membaca kode, tidak tahu selector apa yang dipakai, bagaimana struktur HTML target sebenarnya, dan kenapa selector tersebut tidak menemukan produk. Hanya bisa melihat gejala dari output: request sukses, tapi hasil ekstraksi 0 products.

**3. Kepercayaan ke tool:**
Belum yakin dipakai produksi. Ini contoh jelas bahwa program bisa run tanpa error tetapi tetap gagal memenuhi requirement. Kalau hanya percaya ke tool tanpa membaca kode dan memverifikasi output, bisa saja dianggap "berhasil" padahal CSV kosong. Untuk produksi, tetap perlu validate against spec + verify hasil, bukan sekadar memastikan program tidak crash.

## Catatan Mentor

Refleksi poin 3 langsung nangkep nuansa inti Module 7-8: **"program jalan tanpa error" ≠ "program benar"** (success signal ≠ correct behavior, gaung dari 7.3 & 8.1). Dua kegagalan yang muncul (TLS reset lalu selector kosong) juga pas jadi bahan konkret buat Mini Project Module 8 — bukan flaw yang dikarang, tapi flaw asli hasil sesi vibes-only ini:
- Fragile/guessed CSS selector (tidak divalidasi terhadap struktur HTML nyata).
- Tidak ada validasi hasil (0 produk tetap dianggap "sukses", exit code 0, CSV tetap ditulis).
- Silent failure lain yang berpotensi ada tapi belum diverifikasi (perlu digali pas security/verification review Module 8): no rate limiting, no retry/backoff, no URL validation (SSRF-ish risk kalau input datang dari user lain).

Status: Selesai (2026-08-16).
