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
  
  2.3 CompletableFuture: composing async, exception handling, vs reactive
  2.4 Virtual Threads (Java 25)
  2.5 Bug concurrency di production: deadlock, livelock, stale read, baca thread dump

