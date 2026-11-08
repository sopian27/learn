# Advanced SQL & Reporting Query Patterns
  # CTE: Non-Recursive untuk Query Terbaca, Recursive untuk Data Hierarkis
  query besar tanpa CTE itu mimpi buruk, susah dibaca, bersarang,dengan CTE query besar lebih mudah dibaca dan di maintain.

  CTE bisa dipakai CTE setelahnya, dan CTE yang sama bisa dipakai berulang ulang.

  bisa untuk non recursive dan recursive juga.

  namun CTE tidak otomatis jadi lebih cepat.

  best case:
  1. query reporting bertahap
  2. data hierarki
  3. hasil yang sama antara dipakai berulang kali

  jangan dilakukan :
  1. mengganggap CTE itu optimasi, CTE soal keterbacaan
  2. recursive tanpa pengaman siklik
  3. `UNION` padahal maksudnya `UNION ALL`.** `UNION` dedup tiap iterasi, lebih lambat. Pakai `UNION ALL` kecuali butuh dedup.
  4. query sederhana pake CTE
  5. hierarki besar yang dibaca terus menerus

  aturan emas :
  reporting sebaiknya di kerjakan di query 80%, logic ada disana, gunakan CTE, jangan di java.

  Kasih nama ke setiap tahap query, karena yang lu tulis hari ini bakal dibaca orang lain minggu depan.

  Terapin: 
  waktu review atau nulis query reporting, kalau lu harus baca subquery dari kurung terdalam, pecah jadi CTE bernama dulu sebelum ngasih komentar lain.

  # Window Function: Running Total, Top-N per Grup, Perbandingan Periode

  - `ROW_NUMBER`: selalu unik, yang kembar diurutkan sembarang.
  - `RANK`: kembar dapat nomor sama, nomor berikutnya **loncat** (1, 2, 2, 4).
  - `DENSE_RANK`: kembar dapat nomor sama, **tanpa loncat** (1, 2, 2, 3).  

  window function : satu query baris utuh

  jangan di lakukan :
  1. window function di where. Error. Bungkus pakai CTE atau subquery, filter di luar.
  2. **`ROW_NUMBER` padahal seri harus adil.** Salah satu produk kena "kalah" acak. Pakai `RANK` atau `DENSE_RANK`.
  3. **Lupa `ORDER BY` eksplisit di `OVER`.** Hasil bergantung urutan fisik baris, jadi nggak deterministik. Tambah tie-breaker (misal `id`) kalau butuh hasil stabil.
  4. table besar tanpa index, cek dulu dengan explain analyze
  5. data kecil yang disimpan di satu tempat


  Aturan Emas: 
  Kalau ada nilai kembar, putuskan aturan serinya dulu sebelum nulis query ranking, karena angka yang bisa berubah tanpa data berubah itu bug.

  Terapin: 
  sebelum nulis `ROW_NUMBER`, tanya "kalau dua baris sama nilainya, siapa yang berhak tampil?", lalu pilih `RANK`/`DENSE_RANK` atau tambah tie-breaker.

  # Fungsi kondisional & agregasi kondisional: `CASE WHEN` di `SUM`/`COUNT`, `COALESCE`/`NULLIF`

  `COALESCE`: ganti `NULL` dengan nilai pengganti
  `NULLIF`: bikin `NULL` biar nggak meledak
  GREATEST = jangan kurang dari batas minimum.
  LEAST = jangan lebih dari batas maksimum.

  `GREATEST`/`LEAST` **mengabaikan** `NULL`

  jangan di lakukan:
  1. **`CASE` tanpa `ELSE` di dalam `SUM`.** Hasil bisa `NULL`, bukan `0`. Tambah `ELSE 0` atau bungkus `COALESCE`.
  2. **Bagi tanpa `NULLIF`.** Satu pembagi nol langsung `division by zero`. Pakai `NULLIF(pembagi, 0)`
  3. **`COALESCE` asal-asalan di `AVG`.** Mengganti `NULL` jadi 0 ngubah arti rata-rata. Tanya dulu populasi mana yang dimaksud.
  4. Logic bisnis write-path yang butuh validasi: taruh di Service, bukan di `CASE WHEN`.
  5. Jangan pakai `COALESCE` buat nutupin bug data; kalau `NULL` itu tidak seharusnya ada, benerin sumbernya.

  Aturan Emas: 
  Putuskan dulu apa arti "tidak ada data" di laporan, karena `NULL`, `0`, dan kosong itu tiga jawaban berbeda untuk orang yang baca.

  Terapin: 
  sebelum selesai nulis query reporting, cari tiap `SUM`, pembagian, dan `AVG`, lalu tanya "kalau datanya kosong, angka ini harus tampil apa?"

  # Query reporting dinamis yang aman (filter dari parameter API, tanpa SQL injection)

  Nilai = parameter, jangan pernah ditempel

  Filter opsional dalam satu query statis

  Yang nggak bisa jadi parameter: whitelist

  Validasi sekali, di satu tempat

  jangan di lakukan :
  1. **Merasa aman karena pakai `PreparedStatement`, tapi nilainya tetap di-concat.** Binding cuma melindungi kalau nilainya lewat `?` atau `:nama`.
  2. **Meng-bind nama kolom.** `ORDER BY :kolom` dianggap string konstan, hasilnya sort nggak jalan. Pakai whitelist.
  3. **Validasi cuma di frontend.** Request bisa dikirim langsung. Validasi di server, sekali, di satu tempat.
  4. Menerima potongan SQL dari klien (misal `where=...`). Tolak desainnya.
  5. Optional-NULL untuk puluhan filter di tabel besar: plan jadi generik. Pakai builder.
  6. Escape manual (`replace("'", "''")`) sebagai pengganti binding.

  Aturan Emas:
  Perlakukan semua input dari luar sebagai data, bukan perintah, dan jangan pernah biarkan keduanya ketemu dalam satu string.

  Terapin:
  tiap kali lu nulis `+` atau `String.format` di dekat kata `SELECT`, `WHERE`, atau `ORDER BY`, berhenti dan ganti dengan parameter atau whitelist.
  
  5. Performance query reporting: `EXPLAIN ANALYZE`, materialized view


  