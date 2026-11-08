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


  