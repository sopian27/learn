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
  
  ### Cache-aside
  | **Baca** | App cek cache. Jika miss, baca DB lalu isi cache. |
  | **Tulis** | Tulis ke DB, kemudian hapus entri cache. |
  | **Kekuatan** | Sederhana, DB tetap menjadi sumber kebenaran, aplikasi tetap berjalan meskipun cache mati. |
  | **Harga yang dibayar** | Pembacaan pertama setelah update lebih lambat (cache miss); ada jendela kecil data basi. |

  ### Write-through
  | Aspek | Penjelasan |
  |---|---|
  | **Baca** | Selalu baca dari cache. |
  | **Tulis** | Tulis ke cache dan DB secara bersamaan. Sukses jika keduanya berhasil. |
  | **Kekuatan** | Cache dan DB relatif konsisten. |
  | **Harga yang dibayar** | Penulisan lebih lambat; cache bisa terisi data yang mungkin tidak pernah dibaca. |

  ### Write-behind
  | Aspek | Penjelasan |
  |---|---|
  | **Baca** | Baca dari cache. |
  | **Tulis** | Tulis ke cache terlebih dahulu, DB menyusul secara asynchronous. |
  | **Kekuatan** | Penulisan sangat cepat dan bisa digabung dalam batch. |
  | **Harga yang dibayar** | DB bisa tertinggal; crash sebelum flush berpotensi menyebabkan data hilang. |

  jangan di lakukan:
  1. Mengupdate nilai di cache setelah tulis DB (bukan menghapusnya), lalu kena race dan data basi bertahan.
  2. Memakai write-behind untuk data yang tidak boleh hilang tanpa antrean yang tahan crash.
  3. Mengira write-through "otomatis konsisten" padahal kalau salah satu tulisan gagal di tengah, tetap perlu penanganan error.
  4. Write-behind untuk saldo, stok, order, pembayaran.
  5. Write-through kalau mayoritas data yang ditulis jarang dibaca.
  6. Menimpa cache setelah tulis DB tanpa memikirkan urutan dua tulisan yang bersamaan.

  Aturan Emas:
  Satu data, satu sumber kebenaran; kalau cache ikut jadi penulis, lu harus tahu persis apa yang hilang saat cache atau antreannya mati.
  
  Terapin:
  di PR yang menyentuh cache, tulis satu baris pola yang dipakai dan apa dampaknya kalau cache mati pas tulis.

Redis dari Spring Boot: @Cacheable/@CacheEvict, RedisTemplate, TTL & eviction
Masalah klasik: cache stampede, stale data, invalidation
Redis sebagai lebih dari cache: rate limiting, distributed lock dasar

