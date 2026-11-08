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
  # `@Transactional` propagation (`REQUIRED`, `REQUIRES_NEW`, `NESTED`, dll)
  # Optimistic vs pessimistic locking, `@Version`, `SELECT ... FOR UPDATE`
  # Pengantar distributed transaction dan intuisi Saga

