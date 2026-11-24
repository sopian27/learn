# GraphQL Fundamentals & Implementasi

   # Schema design

   Schema GraphQL adalah kontrak: Query untuk baca, Mutation untuk ubah, Subscription untuk stream, client memilih field sendiri.

   Prinsip schema-first: kontrak ditulis dulu, implementasi menyusul. Manfaatnya sama seperti contract-first di REST (lesson sebelumnya): client dan server kerja paralel, schema jadi dokumentasi yang selalu benar.

   Enak buat banyak client dengan kebutuhan beda (mobile, web, partner). Kurang enak buat API sederhana satu client: REST + caching HTTP lebih murah.

   jangan di lakukan:
   1. Schema jadi cermin tabel DB, bukan kebutuhan client, sehingga detail internal ikut bocor.
   2. Mutation generik updateX yang menerima semua field opsional; nggak jelas apa yang boleh diubah kapan.
   3. Semua field diberi ! "biar aman", lalu satu service down menjatuhkan seluruh query.
   4. API sederhana satu client, kebutuhan caching HTTP berat (CDN), upload file besar, tim belum siap soal N+1 dan query mahal.

   Tes Feynman: GraphQL schema adalah daftar menu yang menjanjikan apa yang boleh diminta, dan client memilih sendiri bagian yang dia mau.

   Aturan Emas: Tulis kontraknya dulu, baru kodenya.

   Terapin: sebelum bikin endpoint atau schema baru, kirim draft kontrak ke orang yang bakal memakainya dan minta komentar, baru mulai implementasi.

   # Resolver di Spring for GraphQL, menghubungkan ke service/repository yang sudah ada

   Resolver menyambungkan field schema ke kode: @QueryMapping, @MutationMapping, @SchemaMapping, dan @Argument menjodohkan lewat nama.

   integrasi service existing dengan graphql :
   GitHub dan Shopify menjalankan GraphQL sebagai lapisan di depan service internal yang sudah ada; resolver-nya tipis, logika tetap di service domain. Pola ini juga yang dipakai Netflix saat menaruh federated graph di atas banyak service.

   jangan di lakukan:
   1. Menaruh logika bisnis dan cek hak akses langsung di resolver, sehingga REST dan GraphQL berperilaku beda.
   2. Mengembalikan entity JPA langsung, lalu resolver relasi memicu lazy-loading tak terduga atau membocorkan field internal.
   3. Menulis resolver untuk setiap field, termasuk yang scalar, padahal cukup dibaca dari parent.

   Tes Feynman: resolver itu petugas loket yang menyambungkan permintaan ke bagian dalam yang sudah punya aturan, bukan tempat bikin aturan baru.

   Aturan Emas: Penyambung harus tipis, aturan hidup di satu tempat.

   Terapin: sebelum menulis if di resolver (atau controller apa pun), tanya "apakah aturan ini sudah ada di service?" dan taruh di sana kalau belum.

   # N+1 problem di GraphQL dan solusinya dengan DataLoader/batching

   N+1 di GraphQL muncul karena resolver relasi dipanggil per parent; 50 order jadi 51 query, dan tumbuh seiring ukuran list.

   Solusinya batching: @BatchMapping atau DataLoader mengumpulkan key lalu satu IN (...) per level, dengan loader per request dan hasil sesuai urutan key.

   jangan di lakukan:
   1. Mengira "resolver sudah tipis" berarti performanya aman. N+1 tetap muncul karena resolver dipanggil per parent.
   2. Test dengan 3 data dummy, jadi N+1 nggak pernah kelihatan sebelum production.
   3. Membagi loader antar request (singleton), jadi cache nyampur data antar user.

   Tes Feynman: N+1 itu bolak-balik ke gudang per pelanggan; batching itu menunda sebentar, mengumpulkan semua pesanan, lalu sekali jalan.

   Aturan Emas: Jangan bolak-balik untuk hal yang bisa dibawa sekali jalan.

   Terapin: kalau ada loop yang memanggil database atau service di dalamnya, tanya "bisa dikumpulin dulu lalu dipanggil sekali?"

   # Query complexity & depth limiting

   GraphQL mengizinkan client menulis query sedalam dan selebar apa pun; schema dengan siklus bisa dieksploitasi walau sudah di-batch.

   Pasang depth limit dan complexity limit (dihitung sebelum eksekusi), ditambah first maksimum, timeout, dan rate limit.

   Tes Feynman: depth limit membatasi seberapa tinggi tumpukannya, complexity limit membatasi seberapa banyak totalnya, dan keduanya dinilai sebelum eksekusi.

   Aturan Emas: Kalau orang lain bebas memilih seberapa banyak yang diminta, kasih batas sebelum mereka mulai.

   Terapin: di setiap API atau fitur yang menerima ukuran dari pengguna (page size, depth, jumlah item), tanya "angka maksimum yang masih aman berapa, dan siapa yang menegakkannya?



