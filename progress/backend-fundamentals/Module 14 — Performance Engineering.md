# Performance Engineering

   # Mindset performance engineering

   Measure before optimizing. Never optimize based on assumptions.

   Tentukan tujuan terukur (SLO / budget)
   -> Ukur baseline (reproducible)
   -> Cari bottleneck (profiling/metrics)
   -> Ubah SATU hal
   -> Ukur lagi, bandingkan
   -> Dokumentasi keputusan

   jangan di lakukan:
   1. Optimasi berdasarkan "kayaknya ini lambat" tanpa profiling.
   2. Bandingin before/after di kondisi beda (laptop vs staging, data kecil vs data besar).
   3. Ngejar rata-rata, padahal user ngerasain ekor (p95/p99, dibahas di 14.3).
   4. Jangan bikin riset performa untuk kode yang jarang jalan dan nggak ada keluhan.
   5. Jangan ngorbanin correctness atau keterbacaan demi gain yang nggak terukur.

   Tes Feynman: 
   Sebelum bikin sesuatu lebih cepat, cari tahu dulu bagian mana yang sebenarnya lambat.

   Aturan Emas: 
   Jangan percaya intuisi soal lambat; percaya angka.

   Terapin: 
   Pas ada yang bilang "ini pasti lambat gara-gara X", tanya sopan "udah kelihatan di profiling/metrics belum?" sebelum ada yang mulai ngoding.

   # Profiling aplikasi Java

   Tes Feynman: 
   Profiler itu foto berulang-ulang ke program, dan yang paling sering muncul di foto adalah yang bikin lambat.

   Aturan Emas: 
   Jangan cari bottleneck dengan membaca; cari dengan melihat di mana waktu benar-benar habis.

   Terapin: 
   Pas ada tiket "service lambat", ambil profil 30 detik dulu dan tempel di tiket sebelum diskusi solusi.

   # Load testing dasar (mis. k6/Gatling)

   Percentile : 
   Urutin semua latency dari kecil ke besar. 
   p50 = nilai di tengah (kebanyakan user). 
   p95 = 95% request lebih cepat dari ini. 
   p99 = 99% lebih cepat dari ini. 
   Makin tinggi percentile, makin ngeliat "ekor" yang lambat.

   alur load test:
   1. Tentukan skenario nyata (endpoint, campuran request, data mirip produksi)
   2. Baseline: beban normal dulu -> catat throughput + p50/p95/p99 + error rate
   3. Naikkan beban bertahap (ramp-up) -> cari titik di mana p95/p99 mulai melonjak
   4. Ubah SATU hal -> ulang test dengan skenario yang SAMA -> bandingin

   jangan di lakukan:
   1. Ngelaporin rata-rata sebagai "latency kita", tanpa p95/p99.
   2. Load test tanpa warmup atau cuma 10 detik, jadi ngukur JIT dingin, bukan kondisi stabil.
   3. Ngetes endpoint yang gampang (cache hit semua) lalu klaim "kuat 5.000 req/s".
   4. Jangan load test langsung ke produksi atau ke dependency pihak ketiga tanpa izin.
   5. Jangan load test endpoint yang belum jalan benar secara fungsional; error cepat bikin angka latency kelihatan bagus palsu.

   Tes Feynman: 
   Rata-rata nutupin hari apes, jadi ukur juga yang paling lambat dari 100 kali.

   Aturan Emas: 
   Jangan laporidn latency dengan satu angka; laporin distribusinya, terutama ekornya.

   Terapin: 
   Pas ada yang bilang "rata-rata 50 ms, aman", tanya "p95 dan p99-nya berapa, dan di beban berapa?"

   # Bottleneck umum backend

      # slow query:
      
         Gejala: Latency tinggi, CPU aplikasi relatif rendah, CPU/IO database tinggi.

         Penyebab: Query tidak optimal, index kurang tepat, terlalu banyak query dalam satu request.

         Pengecekan:

         Slow Query Log.

         EXPLAIN ANALYZE.

         Jumlah query per request.

         Solusi:

         Optimasi SQL.

         Tambahkan index yang tepat.

         Gunakan Fetch Join atau Batch Fetching untuk mengatasi N+1.

      # Thread Pool Starvation:

         Gejala: Request mengantre, latency meningkat, CPU rendah, banyak thread WAITING atau BLOCKED.

         Penyebab: Thread habis karena operasi blocking, pool terlalu kecil, atau proses terlalu lama.

         Pengecekan:

         Thread Dump.

         Metrik thread pool (active, queued).

         Connection Pool.

         Solusi:

         Evaluasi ukuran thread pool.

         Perbaiki blocking operation.

         Optimasi proses yang memakan waktu lama.
      
      # connection pool exhausting
         
         Gejala: Request menunggu koneksi database, muncul connection timeout.

         Penyebab: Koneksi tidak dikembalikan, query lambat, atau pool terlalu kecil.

         Pengecekan:

         HikariCP (active, idle, pending).

         Connection timeout.

         Connection leak detection.

         Solusi:

         Pastikan koneksi dikembalikan setelah digunakan.

         Optimasi query.

      # cache miss

         Gejala: Latency meningkat saat cache dingin, hit ratio turun, beban database meningkat.

         Penyebab: Cache tidak tersedia, TTL habis bersamaan, atau cache key tidak konsisten.

         Pengecekan:

         Cache Hit Ratio.

         Cache Miss Rate.

         Eviction.

         TTL.

         Solusi:

         Atur TTL.

         Gunakan Cache Warming.

         Perbaiki strategi cache key dan invalidation.

      # cpu saturation

         Gejala: CPU mendekati 100%, throughput stagnan, latency meningkat.

         Penyebab: Perhitungan berat, loop tidak efisien, atau serialization berlebihan.

         Pengecekan:

         CPU Usage.

         Thread Dump.

         CPU Profiling.

         Solusi:

         Optimasi algoritma.

         Kurangi proses CPU-intensive.

         Profiling kode untuk menemukan bagian yang berat.

      # memory prasure / GC

         Gejala: Latency mengalami spike, GC sering berjalan, penggunaan heap tinggi.

         Penyebab: Memory leak, terlalu banyak object, atau alokasi object berlebihan.

         Pengecekan:

         Heap Usage.

         GC Log.

         GC Pause.

         Heap Dump.

         Solusi:

         Perbaiki memory leak.

         Kurangi alokasi object yang tidak perlu.

         Evaluasi konfigurasi heap.

      # external api slow

         Gejala: Latency aplikasi meningkat mengikuti lambatnya service lain.

         Penyebab: Dependency lambat, timeout terlalu besar, atau koneksi tidak optimal.

         Pengecekan:

         HTTP Client Metrics.

         Request Timeout.

         Distributed Tracing.

         Solusi:

         Atur timeout.

         Gunakan retry secara terbatas.

         Terapkan Circuit Breaker.

         Gunakan Bulkhead untuk isolasi resource.

      # network bottleneck

         Gejala: Latency tidak konsisten, request timeout, atau koneksi terputus.

         Penyebab: Network congestion, DNS lambat, atau bandwidth terbatas.

         Pengecekan:

         Network Latency.

         Packet Loss.

         DNS Lookup.

         Bandwidth Usage.

         Solusi:

         Evaluasi kondisi jaringan.

         Optimasi komunikasi antarservice.

         Gunakan connection pooling.

   CPU app tinggi?  -> profiling CPU (14.2)
   CPU rendah, lambat? -> ada yang ditunggu:
      thread dump -> banyak WAITING di pool koneksi? -> starvation / query lambat
      hit ratio turun? -> cache miss
      slow query log -> query mana, kenapa? -> index / N+1

   jangan di lakukan:
   1. Nambah server tanpa tahu apa yang ditunggu.
   2. Naikin pool size atau maxPoolSize ke angka besar tanpa lihat kapasitas DB.
   3. Nyimpulin "cache kita kurang" tanpa ngecek hit ratio dan pola key.
   4. Jangan ngoprek pool size atau cache kalau belum ada bukti gejalanya; mulai dari ukur dulu
   5. Jangan anggap tiga keluarga ini lengkap; masih ada GC, network, lock contention, dan dependency eksternal.

   Tes Feynman: 
   Bottleneck itu titik tersempit tempat antrean numpuk, jadi cari di mana yang antre, bukan siapa yang kelihatan sibuk.

   Aturan Emas: 
   Kalau lambat tapi CPU adem, jangan tambah mesin; cari apa yang sedang ditunggu.

   Terapin: 
   Pas ada yang minta "tambah pod", tanya dulu "thread-nya lagi nunggu apa?" dan tempel thread dump di tiket.

   # Capacity planning secukupnya

   Capacity planning secukupnya: ukur kapasitas satu unit, perkirakan puncak, bagi, lalu tambah headroom dan N+1.

   alur:
   1. Load test 1 unit -> cari lutut (rps sebelum p99 melonjak)
   2. Estimasi puncak: metrik produksi x faktor pertumbuhan/event
   3. Unit = puncak / (lutut x target utilisasi)
   4. +1 (N+1), cek batas DB/dependency
   5. Pantau di produksi, koreksi angka tiap kuartal / sebelum event besar

   jangan di lakukan:
   1. Ngitung kapasitas dari beban rata-rata, bukan puncak.
   2. Ngejalanin sistem di utilisasi tinggi dan menganggap sisa persen itu aman.
   3. Skala pod tanpa ngecek batas DB atau dependency di belakangnya.
   4. Layanan kecil dengan traffic stabil: load test sekali plus alert utilisasi sudah cukup, nggak perlu model rumit.
   5. Jangan percaya angka tanpa load test; "double aja" itu nggak bisa dipertanggungjawabkan.

   Tes Feynman: 
   Hitung dulu satu unit sanggup berapa dan puncaknya berapa, lalu sisain ruang napas, jangan dipepet sampai penuh.

   Aturan Emas: 
   Jangan jalanin sistem sampai penuh; hitung kapasitas dari puncak, sisain ruang napas.

   Terapin: 
   Pas ada yang bilang "tambahin aja 2x biar aman", tanya "kapasitas satu pod berapa dan puncaknya berapa?" lalu hitung bareng.


