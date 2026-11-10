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

  Propagation menjawab satu hal: method yang dipanggil dalam transaksi itu gabung atau pisah. Default REQUIRED = gabung.

  REQUIRED (default spring transactional):
  Jika sudah ada transaksi, gunakan transaksi tersebut.
  Jika belum ada, buat transaksi baru.
  Jika method gagal dan exception memicu rollback, transaksi bersama bisa ikut rollback.
  Contoh: OrderService memanggil PaymentService. Keduanya menggunakan satu transaksi.

  REQUIRES_NEW :
  Selalu membuat transaksi baru.
  Transaksi luar ditangguhkan sementara.
  Transaksi baru memiliki commit/rollback sendiri.
  Contoh: Menyimpan audit log yang harus tetap tersimpan meskipun transaksi utama gagal.

  NESTED : 
  Membuat nested transaction menggunakan Savepoint.
  Jika gagal, bisa rollback sampai Savepoint tanpa membatalkan seluruh transaksi luar.
  Jika transaksi luar rollback, perubahan nested ikut rollback.
  Contoh: Proses import data dengan beberapa bagian yang boleh gagal secara terpisah.
  
  SUPPORTS : 
  Jika ada transaksi, ikut transaksi tersebut.
  Jika tidak ada, berjalan tanpa transaksi baru.
  Contoh: Method pembacaan data yang bisa dipanggil dari dalam maupun luar transaksi.
  
  MANDATORY :
  Wajib dipanggil dari transaksi yang sudah ada.
  Jika tidak ada transaksi, Spring melempar exception.
  Contoh: Method internal yang hanya boleh dijalankan sebagai bagian dari transaksi bisnis utama. 

  NOT SUPPORTED:
  Selalu berjalan tanpa transaksi.
  Jika ada transaksi luar, transaksi tersebut ditangguhkan sementara.
  Contoh: Proses tertentu yang tidak perlu ikut transaksi database utama.

  jangan di lakukan:
  1. Mengira @Transactional di dua method otomatis dua transaksi. Default-nya bergabung jadi satu.
  2. Memakai REQUIRES_NEW "biar aman" di mana-mana, lalu pool koneksi habis saat traffic naik.
  3. try/catch di sekitar method REQUIRED lalu lanjut, padahal transaksi sudah rollback-only.
  4. REQUIRES_NEW di dalam loop besar atau jalur panas: tiap iterasi meminjam koneksi lagi.
  5. REQUIRES_NEW yang menyentuh baris yang sama dengan transaksi luar.

  Aturan Emas: 
  Tiap kali satu method memanggil method transaksional lain, tanya: "kalau yang dalam gagal, yang luar harus ikut gagal atau tidak?"; jawabannya menentukan propagation, bukan kebiasaan.

  Terapin: 
  Waktu review PR yang menaruh @Transactional atau try/catch di method yang dipanggil dari method transaksional lain, minta penulisnya menjelaskan nasib kedua bagian kalau salah satunya gagal.

  # Optimistic vs pessimistic locking, `@Version`, `SELECT ... FOR UPDATE`
  # Pengantar distributed transaction dan intuisi Saga

