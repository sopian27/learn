# Rate Limiting Lanjut

   # Perbandingan algoritma

   Fixed Window
      - Cara kerja: Counter per jendela waktu tetap.
      - Memori / Client: 1 angka (counter).
      - Akurasi: Rendah, berpotensi terjadi burst hingga 2x limit di batas jendela.
      - Burst: Ya, terutama di batas jendela waktu.
      - murah dan toleran salah

   Sliding Window Log
      - Cara kerja: Menyimpan timestamp setiap request yang masuk.
      - Memori / Client: O(limit).
      - Akurasi: Tepat karena setiap request dihitung berdasarkan timestamp aktual.
      - Burst: Tidak.
      - akurat dan jumlah client kecil
   
   Sliding Window Counter
      - Cara kerja: Menggunakan counter jendela waktu saat ini dan sebelumnya, kemudian membobot counter sebelumnya secara proporsional.
      - Memori / Client: 2 angka (counter).
      - Akurasi: Hampir tepat (aproksimasi).
      - Burst: Kecil.
      - Default publik skala besar

   Token Bucket
      - Cara kerja: Bucket diisi ulang dengan token secara konstan. Setiap request membutuhkan 1 token.
      - Memori / Client: 2 angka (jumlah token dan waktu pengisian terakhir).
      - Akurasi: Tepat untuk mengontrol rata-rata request rate.
      - Burst: Ya, hingga kapasitas maksimum bucket.
      - Stripe dan banyak API cloud pakai varian ini
   
   Leaky Bucket
      - Cara kerja: Request masuk ke antrean dan diproses dengan kecepatan konstan (bocor secara teratur).
      - Memori / Client: 2 angka (secara konseptual, jika menggunakan meter tanpa antrean eksplisit).
      - Akurasi: Tepat untuk mengatur laju output.
      - Burst: Tidak, karena output dipaksa berjalan secara rata.
      - traffic shaping, mirip yang dipakai NGINX limit_req

   jangan di lakukan:
   1. Pilih fixed window "karena paling gampang", lalu kaget boundary burst 2x limit nembus ke database.
   2. Nganggap token bucket = leaky bucket. Token bucket mengizinkan burst, leaky bucket meratakannya.
   3. Bahas algoritma tanpa bahas atomicity. Semua di atas masih bocor kalau dua request baca counter bersamaan.
   
   Tes Feynman: Algoritma rate limit itu cuma beda cara ngitung "dalam periode tertentu", dan cara ngitung itu yang nentuin siapa bisa nembus limit.

   Aturan Emas: Sebelum percaya sebuah angka, tanya dulu cara ngukurnya.

   Terapin: pas ada yang bilang "limit kita 100 per menit", tanya "diukur pakai apa, dan apa yang terjadi di batas menitnya?".
   
   # Sliding window dengan Redis + Lua script (atomic)

   Sliding window yang benar di atas kertas tetap bocor kalau cek dan tulis adalah perintah Redis terpisah (race condition).

   Lua script lewat EVAL bikin cek + tulis jadi satu unit atomic dalam satu round trip.
   
   Jaga script pendek, pakai TIME dari Redis, beri TTL, dan satu slot di Cluster.

   jangan di lakukan:
   1. Nulis limiter dari 3 panggilan Redis terpisah dan ngira "Redis kan atomic". Satu perintah atomic, urutan beberapa perintah nggak.
   2. Ambil timestamp dari jam aplikasi di banyak instance. Clock skew bikin entry dibuang terlalu cepat atau terlalu lambat.
   3. Lupa PEXPIRE, key client yang berhenti nembak numpuk selamanya di memori.

   Tes Feynman: Kalau keputusan lu bergantung pada hasil baca, baca dan tulisnya harus jadi satu gerakan yang nggak bisa disela.

   Aturan Emas: Benar di satu thread belum tentu benar di keramaian.

   Terapin: tiap nulis logika "baca lalu putuskan lalu tulis" di data bersama, tanya "kalau dua request lakuin ini bersamaan, siapa yang menang?" dan tulis test paralelnya.

   # Per-client quota & tier (free vs paid)

   Satu limit global bikin satu client rakus menghabiskan jatah semua orang, termasuk client berbayar.

   Dua hal yang sering ketuker:

   Rate limit: batas laju jangka pendek (per detik/menit), tujuannya melindungi sistem dari burst.

   Quota: jatah total per periode panjang (per hari/bulan), tujuannya kontrol pemakaian dan basis penagihan.

   skema per client minimal ada : clientId, tier(free/paid), counter, response ditolak

   Aturan praktis: gateway buat melindungi sistem, aplikasi buat menegakkan bisnis. Pola yang umum: dua lapis, gateway pasang batas kasar yang longgar, aplikasi pasang batas tier yang presisi.

   jangan di lakukan:
   1. Satu limit global buat semua client. Satu client rusak ngerugiin semua orang, termasuk yang bayar.
   2. Limit tier di-hardcode di kode. Ganti harga paket = deploy ulang.
   3. Pakai IP sebagai identitas. Kantor satu NAT kena limit bareng, atau client sengaja ganti IP buat menghindar.
   4. Nolak tanpa Retry-After, jadi client retry brutal dan makin parah.

   Tes Feynman: Limit harus dipasang per client sesuai paketnya, bukan satu angka buat semua orang.

   Aturan Emas: Jatah yang dibagi rata tanpa nama pemilik akan dihabiskan oleh yang paling rakus.

   Terapin: tiap bikin resource bersama (limit, pool, antrean), tanya "siapa yang bisa ngabisin ini buat semua orang?" lalu kasih jatah per pemakai.









