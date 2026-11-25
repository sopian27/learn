# API Gateway, Edge Concerns & Capstone

   # Peran API Gateway

   Gateway memusatkan routing, auth di edge, dan transformasi request/response supaya nggak diduplikasi di tiap service.

   tugas inti gateway:
   1. routing : Pilih service tujuan dari path/host/header
   2. auth di edge : Verifikasi identitas sekali di depan
   3. transformasi : Ubah request/response di tengah

   jangan di lakukan:
   1. Service internal bisa diakses langsung dari luar, jadi gateway cuma pajangan. Pintu depan dikunci, pintu belakang dibiarkan terbuka.
   2. Percaya header identitas dari client tanpa dibuang/ditimpa dulu di gateway.
   3. Menaruh logika bisnis di gateway sampai gateway jadi bottleneck tim.

   Tes Feynman: Gateway itu satu pintu depan yang ngecek identitas dan nunjukin arah, supaya tiap service nggak perlu jadi satpam sendiri.

   Aturan Emas: Satu pintu depan nggak berguna kalau pintu belakangnya masih terbuka.

   Terapin: sebelum bilang "sudah aman di gateway", coba panggil service langsung dari luar jaringan gateway dan pastikan gagal.

   # Setup gateway (Kong atau Spring Cloud Gateway) di depan service REST

   Spring Cloud Gateway cocok buat tim Java yang butuh kustomisasi, Kong cocok kalau mau plugin siap pakai dan konfigurasi deklaratif.

   jangan di lakukan:
   1. Lupa StripPrefix, jadi service 404 padahal route-nya "sudah benar".
   2. Tanpa timeout per route: satu service lambat menghabiskan semua koneksi gateway dan menyeret service lain.
   3. Mengubah route langsung di production tanpa review dan tanpa cara rollback.

   Tes Feynman: Setup gateway itu menulis daftar "alamat ini ke service mana, berapa lama ditunggu", bukan membangun mesin baru.

   Aturan Emas: Route tanpa timeout adalah antrean yang menunggu satu service lambat menjatuhkan semuanya.

   Terapin: waktu nambah route baru di gateway, isi timeout-nya di PR yang sama, jangan "nanti".

   # Backend-for-Frontend (BFF)

   BFF menggabungkan panggilan ke banyak service jadi satu kontrak yang pas untuk satu jenis client, dan bisa degradasi kalau satu service gagal.

   Gateway ngurus hal lintas-client (auth, rate limit), BFF ngurus bentuk data per client. Alternatifnya GraphQL atau API umum dengan expand.
   
   Jaga BFF tetap tipis: paralel, ada timeout, tanpa aturan bisnis, dimiliki tim frontend-nya.

   jangan di lakukan:
   1. Menaruh aturan bisnis (hitung diskon, validasi order) di BFF. BFF hanya menyusun dan menyesuaikan data, aturannya tetap di service.
   2. Memanggil downstream berurutan padahal bisa paralel, atau tanpa timeout sehingga satu service lambat menahan seluruh layar.
   3. Satu BFF untuk semua client, akhirnya sama saja dengan API umum yang kembung.

   best case:
   satu layar perlu data dari banyak service, atau kebutuhan mobile, web, dan partner jauh berbeda.

   Tes Feynman: BFF itu satu perantara yang ngumpulin data dari banyak service dan menyajikannya pas untuk satu jenis client.

   Aturan Emas: Bentuk data mengikuti kebutuhan layar, bukan kebiasaan tabel di backend.

   Terapin: waktu client minta "tambah field lagi", tanya dulu layar mana yang butuh, lalu putuskan apakah itu cukup di BFF-nya.

   # Auth & rate limiting di gateway vs di service

   Gateway cocok untuk authentication, scope kasar, dan rate limit umum. Service wajib untuk authorization halus (pemilik data) dan batas endpoint mahal.
   
   Anggap gateway bisa dilewati: cek halus di service jadi lapisan terakhir, terutama untuk akses data lewat ID.

   jangan di lakukan:
   1. Menyerahkan semua auth ke gateway lalu menghapus cek di service ("sudah dicek di depan").
   2. Satu rate limit global untuk semua endpoint, jadi endpoint mahal tetap bisa menjatuhkan sistem sementara endpoint murah ikut dibatasi.
   3. Rate limit di memori satu instance gateway padahal ada banyak instance, sehingga batasnya ikut kelipatan jumlah instance. Pakai store bersama seperti Redis.

   Tes Feynman: Gateway cek "siapa kamu dan seberapa sering datang", service cek "apakah ini punyamu dan seberapa berat permintaanmu".

   Aturan Emas: Gateway menjawab "boleh masuk gedung", service menjawab "boleh buka pintu ini".

   Terapin: tiap bikin endpoint yang menerima ID, tulis dulu test "user lain akses ID ini harus ditolak" sebelum menganggapnya aman.

   # Capstone


