# Concurrency & Multithreading
  # Model mental thread-safety
  
  race condition (masalah atomicity) : keliatan 1 proses padahal 3 proses baca, ubah, tulis.

  visibility (masalah memory) : perubahan oleh suatu thread di ketahui oleh thread yang lainnya.

  synchronized bukan solusi ajaib:
  1. bisa melindungi kalau semua akses pakai lock yang sama (check-than-act dalam satu lock)
  2. tidak melindungi antar instance di pods. Alternative : operasi atomic DB
  3. granularity : cakupan kode yang dilindungi oleh lock (besar vs kecil), kecil berarti satu thread memungkinkan berjalan bersama , besar berarti satu thread harus menunggu thread lain menyelesaikan operasinya.
  4. deadlock : dua atau lebih thread saling menunggu resource yang dikunci oleh thread lain.

  invariant adalah aturan yang harus selalu benar tentang data.

  jangan di lakukan :
  1. mengira volatile berarti thread safe
  2. men-synchronized kan setiap metode dengan berfikir semua pasti aman, padahal invariant melintasi beberapa method (check-then-act)

  Aturan Emas: 
  Thread-safe itu soal melindungi invariant, bukan soal menempelkan synchronized di method.

  # java.util.concurrent: ExecutorService, thread pool sizing, ConcurrentHashMap vs Collections.synchronizedMap

  1. jangan new thread di jalur request, pakai pool yang ukurannya di putuskan
  2. ukur dulu cpu bound atau i/o bound 
  3. queue berbatas + rejection policy yang disengaja, supaya overload terlihat cepat
  4. untuk map yang dibagi: conccurrentHashMap + operasi atomik (merge, compute), synchronizeMap hanya layak untuk beban kecil yang sederhana

  jangan di lakukan :
  1. asal copy newFixThreadPool tanpa sadar kalau queue tumbuh tanpa batas
  2. satu pool untuk semua jenis task, padahal harusnya dipisah antara cpu dan io
  3. mengganggap ketika udah pake concurrentHashMap otomatis thread safe, padahal get lalu put tetep race
  4. lupa shutdown pool, thread non daemon menahan jvm tidak bisa berhenti
  5. jangan tambah pool kalau bottleneck nya db
  6. jangan pakai cache in memory kalau datanya harus konsisten antar pod

  best case :
  1. kerja paralel yang bisa dipisah
  2. cache atau counter in memory yang diakses banyak request

  Aturan Emas:
  Pool bukan soal berapa banyak thread yang lu punya, tapi soal apa yang terjadi saat kerjaan datang lebih cepat dari yang bisa lu selesaikan.

  Terapin:
  Tiap bikin pool, tulis jawaban "kalau queue penuh, apa yang terjadi?" sebelum nulis angka ukurannya.

  # CompletableFuture: composing async, exception handling, vs reactive
  
  thenApply : transformasi hasil
  thenCompose : langkah berikutnya async return completablefuture
  thencombine : join dua future independence
  alloff : tunggu banyak future
  anyof : ambil yang tercepat

  - panggilan idependence dengan supplyasync + then combine
  - putuskan per layanan : wajib (error merambat) atau opsional (berikan fallback)
  - semua panggilan hilir diberikan timeout
  - rantai selalu berakhir di join 

  jangan di lakukan :
  1. supplyasync tanpa executor untuk task blocking (menguras pool)
  2. memakai thenapply saat langkah selanjutnya adalah result sync jadinya future bersarang
  3. lupa join() error hilang diam-diam, atau execption tidak di tangkap error di telan tanpa melihat cause()
  4. memanggil get() / join() di tengah rantai bukannya async malah balik jadi sync

  best case :
  1. satu req bisa beberapa panggilan dan bisa paralel
  2. ada layanan yang bisa fallback kalau gagal

  jangan di lakukan :
  1. panggilan saling bergantung, sequential lebih sederhana

  aturan emas:
  Kerja paralel itu mudah dibuat; yang bikin aman adalah memutuskan dari awal apa yang terjadi saat salah satu bagiannya gagal atau lambat.

  Terapin: 
  Sebelum fan-out, tulis satu baris per panggilan: "kalau ini gagal/lambat, checkout lanjut atau berhenti?"

  2.4 Virtual Threads (Java 25)
  2.5 Bug concurrency di production: deadlock, livelock, stale read, baca thread dump

