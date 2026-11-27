# Performance & Scalability Review

   # N+1 query, indexing, dan database access patterns

   fix N+1:
   1. DTO projection
   2. JOIN FETCH / @EntityGraph
   3. default_batch_fetch_size

   Red flag di diff:
   1. findAll() lalu filter di Java
   2. List tanpa LIMIT
   3. save() satu-satu di loop
   4. OFFSET halaman jauh
   5. IN (...) dari list tanpa batas
   6. HTTP call di dalam @Transactional

   jangan di lakukan:
   1. Nyimpulin aman karena test 3 row-nya cepet.
   2. Ngatasin N+1 dengan ganti semua relasi jadi EAGER.
   3. Minta index buat tiap kolom tanpa liat query-nya.

   Tes Feynman: jangan bolak-balik kalau bisa sekali jalan, dan jangan nyisir semua kalau bisa langsung ke tempatnya.

   Aturan Emas: Nilai kode pakai data sebesar nanti, bukan data sebesar test.

   Terapin: sebelum approve query baru, tulis satu kalimat "kalau row-nya 100.000, yang terjadi adalah ...".

   # Cache correctness (invalidation, stampede)

   Stale cache sering datang dari jalur tulis yang kelupaan dan evict sebelum commit.

   red flag di diff
   1. @Cacheable tanpa TTL di config //Kapan data ini bisa basi?
   2. Write path baru, gak ada evict //Cache mana yang ikut berubah?
   3. @CacheEvict bareng @Transactional //Evict-nya sebelum atau sesudah commit?
   4. Key cuma productId, hasil beda per tenant, user, atau locale //Bisa ketuker data orang lain?
   5. this.getPrice() dari class yang sama //Proxy dilewati, cache-nya jalan?
   6. Object entity JPA disimpan ke Redis //Serialisasi dan lazy field aman?

   Urutan evict itu penting. Kalau evict jalan sebelum commit, request lain bisa baca data lama dari DB lalu naruh lagi ke cache.

   Stampede: satu key mati, semua request lari ke DB.

   jangan di lakukan:
   1. Test cuma ngecek "panggilan kedua gak ke DB", gak ngecek "habis update, hasilnya baru".
   2. Nganggep sync = true beresin stampede se-cluster.
   3. Nambah cache sebelum tau query-nya emang masalah.

   Tes Feynman: salinan harus dibuang begitu aslinya berubah, dan jangan semua orang bikin salinan baru barengan.

   Aturan Emas: Setiap nyimpen salinan, putuskan dulu siapa yang bertugas membuangnya.

   Terapin: waktu review @Cacheable baru, minta penulis PR sebut semua jalur yang ngubah data itu.

   # Concurrency issues (race condition, deadlock, thread-safety)

   Race condition sering muncul dari "baca, putuskan, tulis" yang kepisah.

   Singleton bean jangan nyimpen state mutable; lock di JVM gak ngelindungin antar instance.
   
   Cegah deadlock dengan urutan lock konsisten dan transaksi pendek.

   fix :
      1. Conditional UPDATE :	Kondisi dicek langsung di SQL WHERE
      2. @Version / optimistic : Mendeteksi perubahan data sejak dibaca
      3. SELECT FOR UPDATE / pesimistic : Mengunci row selama transaksi
      4. Unique Constraint : Database menolak data duplikat

   fix thread safety :
      1. **Mutable field di `@Service`**
         - Masalah: Data dipakai bersama oleh semua request.
         - Solusi: Gunakan local variable jika memungkinkan.

      2. **`HashMap` sebagai shared field**
         - Masalah: `HashMap` tidak thread-safe.
         - Solusi: Gunakan `ConcurrentHashMap` atau concurrent collection lain.

      3. **`SimpleDateFormat` sebagai shared field**
         - Masalah: `SimpleDateFormat` tidak thread-safe.
         - Solusi: Gunakan `DateTimeFormatter` yang immutable dan thread-safe.

      4. **`get()` lalu `put()` di `ConcurrentHashMap`**
         - Masalah: Operasi gabungan tidak atomic.
         - Solusi: Gunakan `compute()` atau `merge()`.

      5. **`synchronized` untuk data database**
         - Masalah: Lock hanya berlaku dalam satu JVM, bukan lintas pod.
         - Solusi: Gunakan database locking.

      6. **`synchronized` + `@Transactional`**
         - Masalah: Lock bisa dilepas sebelum transaksi database commit.
         - Solusi: Jangan mengandalkan monitor untuk menjaga konsistensi database.

      7. **`ThreadLocal` di thread pool**
         - Masalah: Data bisa terbawa ke pekerjaan berikutnya karena thread digunakan ulang.
         - Solusi: Panggil `remove()` di blok `finally`.

      8. **`supplyAsync()` tanpa executor**
         - Masalah: Menggunakan common `ForkJoinPool`, sehingga pekerjaan blocking bisa mengganggu pekerjaan lain.
         - Solusi: Tentukan executor yang sesuai dengan jenis pekerjaan.

   fix deadlock :
      Deadlock bukan diselesaikan dengan mempercepat query saja. Pencegahan utamanya adalah memastikan semua transaksi mengambil lock dengan urutan yang konsisten.

   real world :
      Stripe API nyediain idempotency key supaya request yang di-retry gak bikin charge dobel. Startup biasanya cukup unique constraint dan conditional update. Sistem besar nambah idempotency store, antrean per key (misalnya Kafka partition per account), dan metric buat conflict serta deadlock.

   jangan di lakukan :
      1. Nganggep test yang lulus berarti bebas race condition.
      2. Nambah synchronized buat masalah yang sumbernya di database.
      3. Retry di dalam transaksi yang udah gagal.

   best case:
      PR yang nyentuh uang, stok, kuota, status order, scheduled job, consumer, atau state shared.
   
   Tes Feynman: jangan kasih jeda antara ngecek dan ngubah, dan kalau perlu dua barang, ambil dengan urutan yang sama.

   Aturan Emas: Review kode seolah ada orang lain yang menjalankannya di detik yang sama.

   Terapin: di tiap if yang diikuti penulisan data, tanya "kalau dua request masuk bareng, hasilnya apa?".

   # Resource leak (connection, thread, memory)

   Leak sering sembunyi di early return dan jalur exception.
   
   Executor, queue, dan collection berumur panjang butuh batas dan pemilik yang jelas.

   red flag:
      getConnection() manual → Connection Leak
         - getConnection() mengambil koneksi dari connection pool.
         - Jika terjadi exception sebelum close(), koneksi tidak dikembalikan ke pool.
         - Akibatnya connection pool bisa kehabisan koneksi.
         - Solusi: Gunakan try-with-resources agar koneksi otomatis dikembalikan, termasuk saat exception atau early return.
      
      Files.lines() → File Handle Leak
         - Files.lines() membuka file dan mengembalikan Stream.
         - Stream mempertahankan akses ke file sampai ditutup.
         - Jika tidak ditutup, file handle bisa menumpuk.
         - Solusi: Gunakan try-with-resources untuk memastikan Stream ditutup.

      Repository return Stream → Connection Tertahan
         - Stream dari repository bisa mempertahankan database connection selama data dikonsumsi.
         - Jika Stream tidak ditutup, connection bisa tertahan dan tidak kembali ke pool.
         - Stream harus dikonsumsi dalam lifecycle transaction yang sesuai.
         - Solusi: Konsumsi Stream dalam transaction dan tutup menggunakan try-with-resources
      
      HTTP Response Body → HTTP Resource Leak
         - HTTP response body berupa InputStream merupakan resource yang harus dikelola.
         - Jika tidak dikonsumsi atau ditutup, resource HTTP bisa tertahan.
         - Akibatnya connection pooling dan resource OS bisa terganggu.
         - Solusi: Konsumsi response body atau tutup menggunakan try-with-resources.

      HTTP Call dalam @Transactional → Connection Tertahan Terlalu Lama
         - HTTP call bisa membutuhkan waktu lama.
         - Selama transaction berlangsung, database connection bisa tertahan.
         - Jika banyak request terjadi bersamaan, connection pool bisa cepat penuh.
         - Solusi: Pisahkan HTTP call dari transaksi database dan buat transaksi sesingkat mungkin.

   fix thread issue :
      Executors.new... di dalam method
         - Setiap pemanggilan method bisa membuat executor baru.
         - Jika tidak di-shutdown, thread dan resource bisa menumpuk.
         - Menambah overhead pembuatan thread.
         - Solusi: Gunakan shared executor yang lifecycle-nya dikelola Spring Bean.

      newFixedThreadPool → Unbounded Queue
         - Jumlah worker thread dibatasi.
         - Task yang belum mendapat worker masuk ke queue.
         - Queue secara default tidak memiliki batas kapasitas.
         - Jika task masuk lebih cepat daripada task selesai, queue terus membesar dan berisiko OOM.
         - Solusi: Gunakan bounded queue dan rejection policy.

      newCachedThreadPool → Unbounded Threads
         - Thread dibuat sesuai kebutuhan.
         - Jumlah thread bisa terus bertambah saat traffic tinggi.
         - Berisiko menyebabkan CPU overload, memory pressure, dan context switching berlebihan.
         - Solusi: Gunakan thread pool dengan batas maksimal thread.
      
      future.get() atau HTTP Client tanpa Timeout
         - future.get() tanpa timeout menunggu task selesai tanpa batas waktu.
         - Jika task macet, thread pemanggil ikut tertahan.
         - HTTP request tanpa timeout bisa menggantung terlalu lama.
         - Jika terjadi bersamaan, thread pool bisa kehabisan worker.
         - Solusi: Gunakan timeout pada Future dan HTTP request. Tambahkan mekanisme pembatalan jika diperlukan.

   fix Memory leak di Java:
      static Map atau field singleton yang cuma put
         - Map terus menyimpan entry baru dan jarang atau tidak pernah menghapus entry lama.
         - Karena referensinya masih ada, GC tidak bisa membersihkan object tersebut.
         - Risiko: Penggunaan heap terus meningkat hingga OutOfMemoryError.
         - Solusi: Terapkan eviction, TTL, atau batas maksimum ukuran cache.

      ThreadLocal tanpa remove() di finally
         - Thread pool menggunakan ulang thread untuk request berikutnya.
         - Nilai dari request sebelumnya bisa tertinggal di thread.
         - Risiko: Memory leak atau data request sebelumnya terbawa ke request lain.
         - Solusi: Panggil remove() setelah selesai menggunakan ThreadLocal.

      Register listener atau callback
         - Listener yang sudah tidak dibutuhkan masih direferensikan oleh event publisher.
         - Akibatnya, object terkait tidak bisa dibersihkan GC.
         - Risiko: Object terus menumpuk di memory.
         - Solusi: Unregister listener ketika sudah tidak dibutuhkan.

      Key Map tanpa equals() / hashCode() yang benar
         - Map bisa menganggap dua object dengan isi sama sebagai key berbeda.
         - Akibatnya, entry yang secara logika seharusnya sama bisa terus bertambah.
         - Risiko: Memory terpakai untuk entry duplikat.
         - Solusi: Implementasikan equals() dan hashCode() secara konsisten.
   
   real world :
   HikariCP punya leakDetectionThreshold yang nge-log stack trace connection yang kelamaan dipinjam (default mati)

   jangan di lakukan:
   1. Nutup resource cuma di jalur sukses.
   2. Nganggep ada GC berarti gak mungkin leak.
   3. "Fix" dengan restart pod terjadwal tanpa nyari sumbernya.
   4. jangan minta try-with-resources buat object yang gak pegang resource (misalnya stream dari List), atau buat resource yang siklus hidupnya udah diurus container.

   Tes Feynman: apa pun yang lu pinjam harus pasti balik, juga waktu semuanya berantakan.

   Aturan Emas: Sebelum nyetujui kode yang membuka sesuatu, tunjuk dulu baris yang menutupnya.

   Terapin: waktu liat getConnection, new...Executor, atau static Map di diff, telusuri jalur errornya.



