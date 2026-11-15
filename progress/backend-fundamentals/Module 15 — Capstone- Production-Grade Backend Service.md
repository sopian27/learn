# Capstone: Production-Grade Backend Service

   # Perencanaan

   Tulis kontrak API dan skema inti (dengan constraint) sebelum ngoding, lalu catat Engineering Decisions.

   # Implementasi bertahap

   Implementasi capstone dipecah per pilar: satu tahap, satu commit, satu bukti, satu baris keputusan.

   Tes Feynman: Pasang satu bagian, buktikan bagian itu kuat, baru lanjut ke bagian berikutnya.

   Aturan Emas: Kode tanpa bukti itu cuma klaim.

   # Performance pass

   Performance pass punya siklus tetap: baseline, cari bottleneck dengan bukti, perbaiki satu, ukur ulang.

   jangan di lakukan:
   1. Optimasi berdasarkan tebakan ("pasti Redis"), tanpa ngukur.
   2. Mengubah banyak hal sekaligus, jadi nggak tahu perbaikan mana yang berhasil.
   3. Ngukur before dan after dengan skenario berbeda, jadi perbandingannya nggak adil.
   4. Cuma ngelaporin rata-rata. Rata-rata nyembunyiin ekor lambat, p95 dan p99 yang dirasain user.
   5. Optimasi dini di kode yang belum terbukti jadi masalah.
   

   Tes Feynman: Ukur dulu, ubah satu hal terbesar, ukur lagi, baru percaya.

   Aturan Emas: Jangan optimasi yang belum lu ukur.

   # Dokumentasi

   README
   "Bisa gua jalanin?"
   Quick start dari nol, arsitektur singkat, contoh request, cara jalanin test

   OpenAPI
   "Kontraknya apa?"
   Spec endpoint, skema error, contoh response; ikut dicek terhadap kode

   Engineering Decisions
   "Kenapa begini?"
   Per keputusan: pilihan, alternatif ditolak, bukti

   jangan di lakukan:
   1. README yang cuma judul dan stack, tanpa langkah menjalankan.
   2. Menulis keputusan tanpa alternatif yang ditolak, jadi kelihatan seperti kebetulan.
   3. Angka performa tanpa skenario atau tanpa pembanding, sehingga nggak bisa diverifikasi.
   4. Nggak pernah menjalankan README sendiri dari mesin atau folder bersih.
   5. Prototype buang-buang: cukup catatan singkat, jangan tiga lapis.
   6. Dokumen yang menduplikasi isi kode baris per baris; itu cepat basi dan nggak menambah informasi.

   Tes Feynman: Tulis supaya orang lain bisa menjalankannya sendiri dan tahu kenapa dibuat begitu.

   Aturan Emas: Kalau orang lain nggak bisa menjalankannya dari dokumen, itu belum selesai.

   Terapin: Sebelum bilang proyek selesai, jalankan README lu sendiri di folder bersih dan catat tiap kali lu harus nebak.

   # Code review penuh (REVIEW_MODE) atas keseluruhan capstone sebelum dianggap selesai

   Tes Feynman: Selesai itu bukan perasaan, tapi daftar kriteria yang semuanya terbukti.

   Aturan Emas: Jangan bilang selesai sebelum ada yang mengecek dengan daftar, bukan dengan perasaan.

   Terapin: Sebelum menyatakan selesai, tulis kriterianya, tunjuk bukti tiap baris, dan minta satu orang atau satu hari jeda untuk mengecek ulang.
