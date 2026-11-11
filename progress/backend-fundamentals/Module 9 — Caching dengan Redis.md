# Caching dengan Redis
  # Kapan caching benar-benar dibutuhkan
  cache itu optimasi performa, bukan sumber kebenaran, dan sistem harus tetap benar tanpa cache

  layak dijadikan cache:
  1. Sering, key yang sama berulang (hit rate tinggi)
  2. Jarang (katalog, config, referensi)
  3. Query berat, API pihak ketiga yang lambat, komputasi mahal

  urutan yang benar:
  1. perbaiki sumbernya (index, query, N+1) 
  2. baru cache kalau masih perlu

  jangan di lakukan :
  1. Pasang cache sebelum mengukur, jadi tidak tahu hit rate atau apakah masalahnya memang di situ.
  2. Cache data yang berubah terus atau wajib konsisten, lalu user lihat data basi.
  3. Menutup query lambat dengan cache alih-alih memperbaiki index atau query-nya.

  Aturan Emas: 
  Cache itu utang konsistensi; ambil hanya kalau angka membuktikan kamu butuh kecepatannya.

  Terapin: 
  sebelum menulis @Cacheable, tulis dulu satu baris di PR: hit rate perkiraan, berapa lama basi masih aman, dan angka latency sebelum-sesudah.

  # Pola caching: cache-aside, write-through, write-behind
Redis dari Spring Boot: @Cacheable/@CacheEvict, RedisTemplate, TTL & eviction
Masalah klasik: cache stampede, stale data, invalidation
Redis sebagai lebih dari cache: rate limiting, distributed lock dasar

