# API Security

   # Authentication di Spring Security

   JWT = header.payload.signature; payload terbaca semua orang, signature yang menjamin keaslian.

   Signature valid : Bukti token dibuat server lu
   Algoritma dikunci (mis. cuma HS256/RS256) : Cegah alg=none dan algorithm confusion
   exp ada dan belum lewat : Token bocor nggak berlaku selamanya
   iss dan aud cocok : Token dari sistem lain nggak boleh nyasar masuk
   Secret kuat (min 32 byte acak) : Secret lemah = token bisa dipalsukan offline

   jangan di lakukan:
   1. Secret lemah atau hardcoded ("secret", "changeme") yang ikut ke Git.
   2. Validator buatan sendiri yang lupa cek exp, iss, atau percaya alg dari header
   3. Token berumur berhari-hari tanpa mekanisme revoke, atau menyimpan data sensitif di payload.

   Tes Feynman: JWT itu tiket bertanda tangan yang valid hanya kalau tanda tangannya kuat, belum kedaluwarsa, dan cara tanda tangannya dikunci oleh server, bukan oleh tiketnya.

   Aturan Emas: Jangan percaya klaim apa pun dari luar sebelum lu verifikasi sendiri, termasuk klaim tentang cara memverifikasinya.

   Terapin: waktu review PR auth, tanya "token ini dicek signature, exp, dan alg-nya di mana?" sebelum nanya hal lain.

   # Authorization

   Authentication jawab "lu siapa", authorization jawab "lu boleh apa"; BOLA (Broken Object Level Authorization) terjadi kalau cuma yang pertama dicek.

   userId selalu diambil dari token, nggak pernah dari body/URL/query param.

   jangan di lakukan:
   1. Merasa aman karena endpoint sudah .authenticated(); authentication bukan authorization.
   2. Percaya userId/ownerId dari body atau path, bukan dari token.
   3. Cek authorization di GET /orders/{id} tapi lupa di PUT, DELETE, dan endpoint list/export.

   Tes Feynman: Server harus cek bahwa data yang diminta memang milik si peminta, karena nomor yang dia tulis di request cuma alamat, bukan izin.

   Aturan Emas: Identifier dari klien itu alamat, bukan izin.

   Terapin: waktu review endpoint {id}, cari baris yang nyambungin id itu ke user dari token; kalau nggak ketemu, itu bug

   # Mass assignment protection

   Mass assignment terjadi waktu input di-bind ke entity, sehingga klien bisa ngisi role, balance, atau ownerId lewat body.

   jangan di lakukan:
   1. Pakai entity JPA langsung sebagai @RequestBody, atau sebagai response.
   2. Mengandalkan frontend: "form-nya nggak ngirim field itu." Penyerang nggak pakai form lu.
   3. Menyalin DTO ke entity pakai reflection/copy otomatis tanpa daftar field yang eksplisit.
   4. nggak ada alasan pakai entity sebagai input. Untuk body yang benar-benar bebas bentuk (mis. metadata), tetap bungkus dengan tipe yang batasnya jelas.

   Tes Feynman: Server harus menentukan field mana yang boleh diisi klien, bukan membiarkan klien menentukan sendiri lewat apa pun yang dia kirim.

   Aturan Emas: Server yang tentukan kolomnya, klien cuma ngisi.

   Terapin: waktu review @RequestBody, lihat tipenya; kalau itu entity atau ada field yang nggak boleh disentuh klien, minta DTO terpisah.

   # Rate limiting dasar untuk API

   Rate limiting melindungi kapasitas dari klien yang terlalu agresif, entah bug atau sengaja (OWASP API4:2023).

   jangan di lakukan:
   1. Cuma limit di frontend atau percaya klien bakal "sopan". Penyerang nggak lewat frontend lu.
   2. Satu limit global untuk semua endpoint
   3. jangan limit health check atau traffic internal antar-service yang sudah punya mekanisme lain, karena bisa bikin cascading failure sendiri.

   Tes Feynman: Rate limit membatasi seberapa cepat satu pihak boleh memakai layanan, supaya pihak lain tetap kebagian.

   Aturan Emas: Request yang sah pun bisa merusak kalau jumlahnya nggak dibatasi.

   Terapin: waktu bikin endpoint baru, tanya "kalau klien nembak 1000 kali per detik, apa yang jebol duluan?" dan pasang limit sebelum rilis.

   # OWASP API Security Top 10 (2023) checklist penuh

   OWASP API Top 10 (2023) adalah checklist 10 risiko API paling umum;

   jangan di lakukan:
   1. Menganggap versi lama "sudah mati" padahal masih menerima request.
   2. Cek keamanan cuma di akhir proyek, bukan di tiap rilis.
   
   Tes Feynman: Keamanan API itu soal mengecek semua lubang umum secara rutin, bukan cuma memperkuat satu pintu.

   Aturan Emas: Yang nggak lu daftar, nggak akan lu amankan.
   
   Terapin: sebelum rilis, bandingkan daftar endpoint yang hidup dengan dokumentasi, dan tutup semua selisihnya.