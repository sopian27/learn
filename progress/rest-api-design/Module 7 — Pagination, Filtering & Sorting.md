# Pagination, Filtering & Sorting

   # Offset-based pagination dengan Spring Data `Pageable`

   Tes Feynman: Offset itu "lompat ke posisi ke-N dari awal", jadi aman selama daftarnya tidak bergeser dan wajib dibatasi ukurannya.

   Aturan Emas: Setiap daftar yang bisa tumbuh harus punya batas ukuran dan urutan yang pasti.

   Terapin: Sebelum merge endpoint list, tanya "kalau tabel ini 3 juta baris, apa yang terjadi?" lalu pasang default size, batas, dan sort.

   # Cursor-based pagination

   cursor based pagination bisa digunakan untuk dataset besar (jutaan) atau tumbuh terus.

   Tes Feynman: Cursor pagination itu bilang "lanjut dari sini" pakai penanda, bukan "kasih gua halaman ke-sekian" pakai hitungan.

   Aturan Emas: Untuk data yang besar dan terus berubah, tunjuk "setelah yang terakhir gua lihat", jangan hitung "halaman ke berapa".

   Terapin: Pas review endpoint list yang tabelnya jutaan baris, tanya "kalau halaman ke-100.000 diminta, berapa lama?" sebelum approve Pageable polos.

   # Filtering dinamis

   Resep umum:
   1. Whitelist eksplisit. Daftar parameter yang boleh dipakai, bukan "semua kolom".
   2. Satu filter = satu aturan (enum untuk status, min panjang untuk keyword, rentang tanggal maksimal).
   3. Tolak yang tidak dikenal dengan 400, jangan diam-diam diabaikan. Kalau diabaikan, klien mengira filternya jalan padahal tidak.

   jangan di lakukan:
   1. Memetakan semua query param ke nama kolom entity secara refleksi.
   2. Mengabaikan param yang tidak dikenal tanpa kabar, sehingga klien salah mengira hasilnya sudah terfilter.
   3. Pakai LIKE '%kata%' di tabel besar tanpa pembatasan panjang minimal.
   4. Membiarkan filter pada kolom sensitif (catatan internal, hash password, flag fraud) karena "kan tidak ditampilkan".
   5. Satu atau dua filter tetap: derived query biasa (findByStatus) lebih simpel.
   6. Pencarian teks penuh yang berat: pakai search engine, bukan LIKE.

   Tes Feynman: Filter itu pintu yang kita pilih untuk dibuka, bukan akses langsung ke semua kolom.

   Aturan Emas: Yang boleh diakses klien harus didaftarkan dengan sadar, bukan terbuka secara default.

   Terapin: Pas review endpoint list, tanya "param mana yang boleh difilter, dan kolom sensitif mana yang pasti tidak?" sebelum approve.

   # Sorting dengan whitelist field & default sort yang aman

   resep implementasi:
   1. Whitelist field sort, dengan peta nama publik ke properti entity (created jadi createdAt), jadi nama kolom tidak bocor.
   2. Tolak yang lain dengan 400 dan pesan yang menyebut field yang boleh dipakai.
   3. Default sort eksplisit dan terindeks, misal createdAt DESC.
   4. Selalu tambah tie-breaker id, supaya urutan deterministik
   5. Batasi jumlah field sort

   jangan di lakukan:
   1. Mengandalkan Pageable bawaan Spring tanpa filter, jadi semua properti entity otomatis bisa di-sort.
   2. Tidak ada default sort, jadi urutan bergantung pada kebetulan fisik tabel dan berubah antar request.
   3. Lupa tie-breaker id, jadi pagination menghasilkan duplikat atau baris hilang.
   4. Memakai nama kolom DB langsung di URL, jadi rename kolom jadi breaking change.
   5. Endpoint dengan urutan tetap (misal feed cursor yang selalu id DESC): tidak perlu parameter sort sama sekali.
   6. Tabel kecil internal, walau whitelist tetap murah dan layak dipasang.

   Tes Feynman: Sorting itu cuma boleh di kolom yang sudah disiapkan urutannya, sisanya ditolak.

   Aturan Emas: Jangan jual kemampuan yang belum lu siapkan; tiap hal yang boleh diminta klien harus sudah lu pastikan murah dilayani.

   Terapin: Pas review endpoint list, tanya "field sort apa saja yang boleh, dan mana index-nya?" sebelum approve.





