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

  # Redis dari Spring Boot: @Cacheable/@CacheEvict, RedisTemplate, TTL & eviction
  

  @Cacheable = cache-aside dalam proxy; 
  @CacheEvict buang entri; 
  @CachePut menimpa dan rawan race.

  @Cacheable : 
  Cek cache dulu. Jika cache hit, langsung kembalikan hasil cache. Jika cache miss, jalankan method dan simpan hasilnya ke cache.
  
  @Cacheable(value = "users", key = "#id") — Ketika getUser(10) dipanggil pertama kali, data diambil dari DB dan disimpan ke cache. Pemanggilan berikutnya menggunakan cache tanpa query DB.

  @CacheEvict : 
  Menghapus entri cache. Biasanya digunakan ketika ada perubahan atau penghapusan data agar cache tidak menyimpan data lama.

  @CacheEvict(value = "users", key = "#id") — Ketika updateUser(10) berhasil, cache user dengan ID 10 dihapus. Pemanggilan getUser(10) berikutnya mengambil data terbaru dari DB.

  @CachePut:
  Selalu menjalankan method, kemudian menyimpan atau memperbarui hasil method ke cache tanpa mengecek cache terlebih dahulu.

  CachePut(value = "users", key = "#result.id") — Ketika updateUser(10) dipanggil, data diperbarui di DB dan hasil terbarunya langsung dimasukkan ke cache.

  @Cacheable untuk method baca murni dengan key jelas dan data yang boleh sedikit basi.
  RedisTemplate untuk struktur data khusus, counter atomik, atau TTL dinamis.

  Banyak perusahaan memakai dua lapis: Caffeine di memori aplikasi untuk data panas, Redis sebagai lapis bersama. Rata-rata layanan kecil sampai menengah cukup dengan Redis saja, TTL jelas, dan fallback ke DB saat Redis mati.

  jangan di lakukan:
  1. Mengira @Cacheable otomatis punya TTL. Spring Redis cache default-nya tanpa kedaluwarsa.
  2. Memanggil method ber-@Cacheable dari kelas yang sama (self-invocation) atau menaruhnya di method private.
  3. Lupa @CacheEvict di jalur update, sehingga data basi bertahan.
  4. Memakai JDK serialization default, atau men-cache objek besar tanpa pikir ukuran.
  5. Redis tanpa maxmemory, atau pakai noeviction untuk cache, sehingga saat penuh tulisan gagal.
  6. Mencampur Spring Cache dan RedisTemplate di jalur data yang sama tanpa alasan.
  7. Men-cache data sensitif (token, password, data pribadi) tanpa enkripsi dan justifikasi.

  Aturan Emas: 
  Anotasi cuma memasang kabel; TTL, evict, dan batas memori yang menentukan cache itu aman atau jadi bom waktu.

  Terapin: 
  sebelum merge, tiap @Cacheable baru harus punya jawaban tertulis untuk "kapan entri ini kedaluwarsa dan siapa yang membuangnya".

Masalah klasik: cache stampede, stale data, invalidation
Redis sebagai lebih dari cache: rate limiting, distributed lock dasar

