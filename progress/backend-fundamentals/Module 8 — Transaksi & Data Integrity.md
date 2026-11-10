# Transaksi & Data Integrity

  # ACID secara mendalam dan konsekuensi praktisnya
  atomicity : semua langkah sukses atau gagal
  consistency : transaksi membawa data dari state valid ke state valid
  isolation : Transaksi paralel tidak saling lihat kondisi setengah jadi
  durability : Setelah COMMIT sukses, data bertahan walau crash

  jangan di lakukan :
  1. ngira pake db otomatis atomicity, padahal tiap state comit sendiri sendiri
  2. nyimpen aturan bisnis penting hanya di db, tanpa constraints db
  3. manggil sistem external di dalam transkaksi nganggep nya ikut ke roolback

  best case :
  case ngubah lebih dari satu baris/tabel dan harus utuh bersama (transfer, order + stok, payment + ledger).

  Aturan Emas: 
  Kalau dua langkah harus berhasil bersama, bilang ke sistem bahwa mereka satu kesatuan; jangan berharap keduanya kebetulan jalan.

  Terapin: 
  Waktu bikin fitur yang mengubah dua tabel sekaligus, tanya dulu "kalau mati di tengah, state-nya masih valid?" sebelum nulis baris pertama.
  
  # Isolation level (`READ_COMMITTED`, `REPEATABLE_READ`, `SERIALIZABLE`), dirty read, non-repeatable read, phantom read

  Dirty read: lu baca data yang belum di-commit transaksi lain (dan mungkin nanti di-rollback). Lu bertindak atas data yang tidak pernah "ada". NON-REPEATABLE READ (READ COMMITTED, default Postgres)

  Non-repeatable read: baris yang sama dibaca dua kali dalam satu transaksi, nilainya beda karena transaksi lain commit di antaranya. (concurrent update)

  Phantom read: query yang sama (misal WHERE balance > 0) dijalankan dua kali, jumlah baris yang cocok beda karena ada baris baru/hilang yang commit di antaranya. (insert)

  READ_UNCOMMITTED:
  Bisa membaca data yang belum di-COMMIT transaksi lain (Dirty Read).
  Risiko konsistensi data paling tinggi.

  READ_COMMITTED (Default PostgreSQL)
  Hanya membaca data yang sudah di-COMMIT.
  Setiap query mendapatkan snapshot terbaru.
  Non-Repeatable Read dan Phantom Read masih bisa terjadi.
  Cocok untuk kebutuhan transaksi umum.

  REPEATABLE_READ
  Pembacaan data tetap konsisten menggunakan snapshot yang sama selama transaksi.
  Mencegah Non-Repeatable Read dan Phantom Read di PostgreSQL.
  Concurrent update pada baris yang sama bisa menghasilkan serialization error (40001).
  Cocok ketika konsistensi pembacaan dalam satu transaksi sangat penting.

  SERIALIZABLE
  Isolation level paling ketat.
  Transaksi berjalan seolah-olah dieksekusi satu per satu secara berurutan.
  Mencegah Dirty Read, Non-Repeatable Read, dan Phantom Read.
  Bisa menghasilkan serialization error (40001), sehingga perlu mekanisme retry.
  Cocok untuk transaksi dengan aturan integritas data yang sangat ketat.

  Spring: @Transactional(isolation = Isolation.REPEATABLE_READ). Tanpa atribut itu, Spring pakai default DB-nya.

  jangan di lakukan:
  1. Mengira @Transactional otomatis berarti "terisolasi penuh". Defaultnya tergantung DB, dan biasanya READ_COMMITTED.
  2. Menaikkan ke SERIALIZABLE tanpa logika retry. Hasilnya error acak di production saat traffic naik.
  3. Menyalin pengetahuan MySQL ke Postgres (atau sebaliknya) tanpa cek default dan perilaku fantom-nya.

  Aturan Emas: 
  Jangan tanya "ini terisolasi atau tidak?"; tanya "anomali apa yang masih boleh terjadi di level ini, dan aman nggak buat logika gua?"

  Terapin: 
  Waktu review PR yang membaca data yang sama dua kali dalam satu @Transactional, tanya penulisnya level isolasinya apa dan apa yang terjadi kalau data berubah di antara dua baca. (balik lagi ke flow bisnis nya)












  # `@Transactional` propagation (`REQUIRED`, `REQUIRES_NEW`, `NESTED`, dll)
  # Optimistic vs pessimistic locking, `@Version`, `SELECT ... FOR UPDATE`
  # Pengantar distributed transaction dan intuisi Saga

