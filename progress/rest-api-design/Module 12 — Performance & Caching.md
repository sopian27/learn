# Performance & Caching

   # Caching HTTP

   Cache-Control menentukan boleh tidaknya pakai salinan tanpa nanya; ETag/Last-Modified + If-None-Match/If-Modified-Since bikin revalidasi murah lewat 304 tanpa body.

   - `Cache-Control: max-age=60` : Response → Fresh 60 detik, klien tidak perlu request ulang ke server.
   - `Cache-Control: no-cache` : Response → Boleh disimpan, tetapi wajib revalidate dulu.
   - `Cache-Control: no-store` : Response → Jangan simpan sama sekali (data sensitif).
   - `Cache-Control: private` : Response → Hanya boleh disimpan oleh cache browser/app.
   - `Cache-Control: public` : Response → Boleh disimpan oleh browser, CDN, atau proxy.
   - `ETag: "abc"` : Response → Sidik jari isi response.
   - `Last-Modified: <tanggal>` : Response → Waktu terakhir resource berubah (presisi detik).
   - `If-None-Match: "abc"` : Request → Mengirim ETag untuk mengecek apakah resource masih sama.
   - `If-Modified-Since: <tanggal>` : Request → Mengecek perubahan resource berdasarkan waktu terakhir modifikasi.

   jangan di lakukan:
   1. Pasang max-age panjang di endpoint data user lalu heran kenapa data basi, atau lupa private sehingga data user A nyangkut di cache bersama (CDN/proxy).
   2. Ngira no-cache = "jangan cache". Itu no-store.
   3. Hitung ETag dari timestamp response yang selalu berubah (mis. ada field generatedAt), jadi nggak pernah ketemu 304.
   4. Data sensitif atau personal tanpa private/no-store.
   5. Data real-time yang harus selalu segar (saldo, stok kritis): pakai no-cache + ETag, bukan max-age.
   6. Endpoint POST; respons-nya jarang layak di-cache.

   Tes Feynman: HTTP caching itu cara klien memakai salinan lama dengan aman, entah dengan tidak bertanya sama sekali (max-age) atau dengan bertanya "masih sama?" sehingga server cukup jawab "sama" tanpa kirim ulang isinya (ETag + 304).

   Aturan Emas: Jangan kirim ulang apa yang klien sudah punya; cukup tanya "masih sama?".

   Terapin: sebelum nambah Redis di endpoint GET yang berat, cek dulu apakah ETag + Cache-Control sudah cukup menghemat payload-nya.

   # Application-level caching dengan Redis

   Cache-aside: cek Redis, MISS ke DB lalu simpan dengan TTL, saat tulis hapus key.
   
   Pilih cache response untuk hasil identik antar user, cache query result untuk data yang dipakai banyak endpoint.

   Aturan praktis: kalau responsnya identik untuk banyak user, cache response. Kalau datanya dipakai banyak endpoint dengan bentuk beda, cache hasil query. Data per-user wajib punya userId di key (cart:user:7), kalau nggak, user A bisa lihat keranjang user B.

   invalidasi teknik:
   1. TTL saja: paling sederhana. Data bisa basi sampai TTL habis
   2. Invalidate saat tulis (@CacheEvict / DEL key): segar, tapi lu harus ingat semua jalur tulis
   3. Write-through: tulis ke DB dan cache bersamaan
   4. Kombinasi (disarankan): invalidate saat tulis + TTL sebagai jaring pengaman.

   jangan di lakukan:
   1. Cache tanpa TTL dan tanpa invalidasi. Data basi selamanya sampai ada yang flush manual.
   2. Key tanpa identitas user atau parameter (products padahal responsnya tergantung ?page=2&sort=price), jadi user lihat data yang salah.
   3. Cache data yang murah di-query atau jarang dibaca. Lu menambah kompleksitas tanpa untung.
   4. Data yang harus selalu akurat (saldo, stok kritis, status pembayaran)
   5. Query yang sebenarnya lambat karena N+1 atau index hilang: perbaiki akarnya dulu, jangan ditutupi cache.
   6. Data yang jarang diminta ulang (hit rate rendah).

   Tes Feynman: cache itu salinan jawaban yang disimpan dekat supaya nggak nanya ke sumber asli terus, dan harus punya aturan kapan salinannya dibuang supaya nggak menyesatkan.

   Aturan Emas: Cache itu bohong yang disengaja; tentukan dulu berapa lama bohongnya boleh sebelum lu pasang.

   Terapin: sebelum nulis @Cacheable, tanya PM atau tim terkait, "data ini boleh basi berapa lama?", lalu jadikan jawabannya TTL.

   # Menghindari N+1 query di response API (lazy loading JPA yang tidak disadari)


   # Response compression & payload minimization

   Compression memadatkan data, minimization mengurangi data. Minimize dulu, baru compress.

   Aturan Emas: Jangan bungkus lebih rapi barang yang sebenarnya nggak perlu lu bawa.

   Terapin: sebelum nyalain gzip, tanya dulu "field mana di response ini yang nggak pernah dibaca klien?" dan buang itu.

   # Load testing dasar dengan k6

   Satu request nggak membuktikan apa-apa. Load test mensimulasikan banyak user bersamaan untuk mengukur latency, throughput, dan error rate.

   jangan di lakukan:
   1. Mengukur dengan satu request dan menyimpulkan "sudah cepat".
   2. Hanya membaca rata-rata, mengabaikan p95/p99.
   3. Tidak menyimpan baseline, jadi nggak ada pembanding "sebelum".
   4. Mengubah banyak hal sekaligus, lalu nggak tahu perubahan mana yang berpengaruh.
   5. Test tanpa sleep() atau dengan data dummy sangat kecil, hasilnya nggak mewakili production.
   6. Di production tanpa koordinasi.
   7. Untuk endpoint yang jarang dipanggil dan bukan jalur kritis, biayanya nggak sebanding.

   Tes Feynman: load test itu mengukur seberapa lambat pengalaman pengguna paling sial saat banyak orang datang bersamaan, dan membandingkannya dengan angka sebelum perubahan.

   Aturan Emas: Kalau lu nggak punya angka "sebelum", lu nggak punya hak bilang "sudah lebih baik".

   Terapin: sebelum ngoptimasi apa pun, jalankan dan simpan hasil baseline, lalu tulis angka before/after di deskripsi PR.

