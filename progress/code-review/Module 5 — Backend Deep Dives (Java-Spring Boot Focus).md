# Backend Deep Dives (Java/Spring Boot Focus)

   # Spring Boot anti-pattern (field injection, fat controller, transactional pitfalls)

   Constructor injection bikin dependency jujur dan gampang dites.
   
   Controller ngurus HTTP, aturan bisnis dan transaction ada di bawahnya.
   
   @Transactional bisa diam-diam gak berlaku: self-invocation, private, checked exception.

   fix :
   this.method() ke method @Transactional
      - Pemanggilan method dalam class yang sama melewati this, bukan Spring Proxy.
      - Akibatnya, anotasi @Transactional pada method yang dipanggil bisa tidak dijalankan.
      - Risiko: Transaction tidak aktif meskipun anotasinya ada.
      - Solusi: Pindahkan method transactional ke Spring Bean lain atau gunakan pendekatan proxy yang tepat.
   
   @Transactional di method private
      - Spring Proxy secara default tidak bisa meng-intercept pemanggilan method private.
      - Akibatnya, konfigurasi transaction pada method tersebut tidak berlaku.
      - Risiko: Mengira operasi berjalan dalam transaction, padahal tidak.
      - Solusi: Gunakan method public yang dipanggil melalui Spring Proxy.

   throws checked exception
      - Secara default, Spring melakukan rollback untuk RuntimeException dan Error.
      - Checked exception seperti IOException tidak otomatis memicu rollback.
      - Risiko: Operasi gagal, tetapi transaction tetap commit.
      - Solusi: Gunakan rollbackFor jika checked exception harus membatalkan transaction.

   catch yang menelan exception
      - Exception ditangkap, tetapi tidak dilempar kembali.
      - Spring menganggap method selesai normal.
      - Risiko: Transaction commit meskipun proses sebenarnya gagal.
      - Solusi: Lempar kembali exception atau tandai transaction sebagai rollback-only.
   
   HTTP call di dalam transaction
      - Transaction tetap aktif selama HTTP call berlangsung.
      - Selama menunggu respons HTTP, koneksi database bisa tetap dipinjam.
      - Risiko: Connection pool cepat habis, terutama jika HTTP lambat.
      - Solusi: Hindari HTTP call di dalam transaction. Pisahkan proses HTTP dan transaksi database jika memungkinkan.
   
   REQUIRES_NEW
      - Membuat transaction baru dan menangguhkan transaction sebelumnya.
      - Transaction baru membutuhkan koneksi database tambahan ketika transaction lama masih memegang koneksi.
      - Risiko: Connection pool kehabisan koneksi, bahkan bisa terjadi connection pool starvation.
      - Solusi: Perhitungkan ukuran pool dan jumlah concurrent request.

   jangan di lakukan:
   1. Nganggep ada @Transactional berarti pasti rollback.
   2. Ganti field injection ke @RequiredArgsConstructor tapi class-nya tetap punya 10 dependency.
   3. Komentar "ini anti-pattern" tanpa nyebut akibat konkretnya.

   Tes Feynman: annotation gak ngapa-ngapain sendiri, harus ada penjaga yang dilewatin.

   Aturan Emas: Jangan percaya annotation, telusuri siapa yang manggil dan apa yang terjadi pas gagal.

   Terapin: waktu liat @Transactional di diff, cari pemanggilnya dan semua exception yang bisa keluar.

   # Review migrasi database (Flyway), transaction boundary, locking

   Migrasi aman itu bertahap: expand, backfill, index, contract, dengan lock_timeout.

   Skema baru harus tetap cocok sama kode lama selama deploy.
   
   @Transactional bukan lock; pakai atomic update, @Version, atau FOR UPDATE.

   Migrasi lama diedit : Checksum mismatch. Kenapa bukan file baru?
   
   RENAME / DROP COLUMN : Kode versi lama masih baca?
   
   UPDATE tanpa batch : Berapa row, lock ditahan berapa lama?
   
   ADD COLUMN ... DEFAULT gen_random_uuid() : Volatile default, tabel ditulis ulang
   
   CREATE INDEX polos di tabel besar : Write diblok selama build
   
   CONCURRENTLY gagal : Sisa index INVALID udah dibersihin?

   jangan di lakukan:
   1. Nilai migrasi dari durasi di database lokal yang isinya 20 row.
   2. Naruh DDL, backfill, dan index dalam satu file.
   3. Pasang @Transactional lalu ngerasa race condition udah beres.

   Tes Feynman: ubah sedikit-sedikit, jangan tutup jalan lama sebelum jalan baru bisa dipakai.

   Aturan Emas: Sebelum approve perubahan data, tanya siapa yang lagi pakai data itu saat perubahannya terjadi.

   # API/contract review (REST convention, OpenAPI accuracy, backward compatibility)

   Perubahan breaking dinilai dari sisi client lama, bukan dari sisi server.

   Default aman: tambah dulu, deprecate, hapus belakangan.

   Tes Feynman: boleh nambah hal baru, tapi jangan pindahin yang udah dipakai orang tanpa kasih waktu.

   Aturan Emas: Sebelum ngubah sesuatu yang dipakai orang lain, cari tahu dulu siapa yang bergantung dan kasih mereka waktu pindah.

   Terapin: ada rename di DTO response, minta penulis PR sebutin konsumennya sebelum lu approve.


   # Distributed system concerns (idempotency, retry, timeout, Kafka consumer pitfalls)

   Timeout berarti hasilnya belum diketahui.
   
   Retry butuh batas, backoff, satu lapis, dan operasi yang idempotent.

   Timeout
      - Tiap panggilan keluar (HTTP, database, Redis) punya connect dan read timeout eksplisit. Jangan andelin default.
      - Timeout di dalam harus lebih pendek dari kesabaran pemanggilnya.
      - Jangan nahan transaction database sambil nunggu HTTP.

   retry
      Error apa yang diulang : Timeout, 502/503/504, 429. Bukan 400 atau saldo kurang
      Jeda : Exponential backoff plus jitter
      Batas : Jumlah percobaan dan total waktu
      Lapisan : Satu aja. Tiga lapis @ tiga percobaan = sampai 27 panggilan
      Operasinya : Aman diulang, atau punya idempotency key

   jangan di lakukan:
   1. Nganggap timeout sama dengan gagal.
   2. Generate idempotency key baru di tiap percobaan.
   
   Tes Feynman: diam bukan berarti gagal, jadi bikin pesan ulang aman diterima dua kali.

   Aturan Emas: Sebelum ngulang sesuatu, pastikan ngulangnya gak bikin kerusakan kedua.

   Terapin: liat @Retryable di PR, cari dulu idempotency-nya sebelum bahas jumlah percobaan.


