# DTO Design & Data Mapping

   # DTO vs Entity

   Entity langsung jadi response menyebabkan over-exposure (field internal bocor) dan coupling (refactor DB mengubah kontrak API).

   DTO adalah allow-list: field baru nggak otomatis keluar, dan perubahan skema cukup diredam di mapper.

   jangan di lakukan:
   1. Merasa aman karena field sensitif "nggak ditampilkan di UI": API tetap bisa di-curl, UI bukan batas keamanan.
   2. Membuat DTO yang isinya salinan 1:1 entity. Itu cuma memindahkan masalah: coupling tetap ada. DTO harus dirancang dari kebutuhan consumer.
   3. Memakai entity sebagai @RequestBody, sehingga client bisa mengisi field yang nggak seharusnya (mass assignment, mis. role atau id).

   Tes Feynman: entity adalah catatan internal tempat lu menyimpan data, DTO adalah menu yang sengaja lu tunjukkan ke orang luar.

   Aturan Emas: Jangan tunjukkan isi dapur ke tamu, tunjukkan menu.

   Terapin: sebelum merge PR yang menyentuh controller, cek tipe return-nya. Kalau ketemu @Entity di sana, minta diganti DTO.

   # Request DTO vs Response 
   
   Input dan output punya allow-list yang berbeda, jadi butuh tipe yang berbeda (CreateXRequest vs XResponse).

   jangan di lakukan:
   1. Satu UserDto untuk create, update, dan response, lalu menambal dengan if (dto.getRole() != null) dto.setRole(null) di service. Itu deny-list dengan tangan.
   2. Memakai id dari request body untuk PUT /users/{id}. Sumber kebenaran id itu path, bukan body.
   3. Request DTO mewarisi (extends) response DTO "biar hemat". Perubahan di output diam-diam mengubah input.

   Tes Feynman: yang boleh lu isi dan yang boleh lu lihat itu dua daftar berbeda, jadi simpan di dua kertas berbeda.

   Aturan Emas: Pisahkan apa yang boleh masuk dari apa yang boleh keluar.

   Terapin: waktu review PR endpoint tulis, tanyakan "kalau client menambah field role di body, apa yang terjadi?" Kalau jawabannya "tergantung," pisahkan DTO-nya.

   # Mapping

   Mapper manual memberi kontrol penuh tapi rawan lupa field; mapper otomatis hemat boilerplate tapi rawan menyalin terlalu banyak.

   jangan di lakukan:
   1. Memakai MapStruct tanpa unmappedTargetPolicy = ERROR, lalu kaget kenapa field baru tetap null.
   2. Menganggap mapper otomatis melindungi dari kebocoran. Field di DTO dengan nama yang sama akan ikut tersalin.
   3. Menaruh logika bisnis berat (query, panggilan service) di dalam mapper. Mapper harus murni: input objek, output objek.
   4.  project sangat kecil, atau mapping yang isinya aturan bisnis. Jangan memaksakan satu cara untuk semuanya: campur boleh (MapStruct untuk yang sederhana, manual untuk yang rumit).

   Tes Feynman: mapper cuma memindahkan isi dari satu bentuk ke bentuk lain, jadi pastikan ia berteriak kalau ada yang tertinggal, dan jangan jadikan ia penentu apa yang boleh keluar.

   Aturan Emas: Jangan biarkan kegagalan mapping diam, bikin ia gagal keras saat build.

   Terapin: waktu menambah field di DTO, ubah dulu setting mapper ke mode error, lalu lihat apakah build lu menangkap field yang belum terpetakan.

   # Over-fetching & under-fetching

   Over-fetching (terlalu banyak) dan under-fetching (terlalu sedikit) punya akar yang sama: DTO dirancang dari bentuk tabel, bukan dari kebutuhan consumer.

   Obatnya DTO per use case (XListItem, XDetail), dengan ringkasan relasi yang layar butuhkan, dan query join di server untuk menghindari N+1.

   jangan di lakukan:
   1. Membuat DTO dengan cara "kolom tabel semua masuk, nanti client yang pilih". Itu mengulang entity dengan nama lain.
   2. Mengatasi under-fetching dengan menambah semua relasi ke satu DTO untuk semua endpoint. Sekarang list ikut berat dan mungkin memicu N+1 di server.
   3. Menyelesaikan masalah dengan menambah endpoint baru tiap kali ada layar, tanpa bertanya apakah dua layar bisa berbagi satu bentuk.
   4. Mengukur "cukup cepat" di jaringan kantor, bukan di jaringan seluler.
   5. jangan memecah DTO sebelum ada bukti. Kalau cuma satu consumer dan bentuk list sama dengan detail, satu DTO cukup.

   Tes Feynman: isi response ditentukan oleh apa yang pembacanya butuhkan, bukan oleh bagaimana datanya disimpan.

   Aturan Emas: Rancang bentuk datanya dari kebutuhan yang membaca, bukan dari tempat ia disimpan.

   Terapin: sebelum menulis DTO, tanyakan ke consumer "layar lu menampilkan apa?", lalu mulai dari daftar itu, bukan dari daftar kolom.


