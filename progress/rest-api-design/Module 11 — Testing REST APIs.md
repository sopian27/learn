# Testing REST APIs

   # Unit test service layer (mock repository)

   Unit test service itu menguji aturan bisnis, dengan semua dependency (repository, client eksternal) diganti objek palsu.

   layak di uji
   1. aturan bisnis dan cabang if
   2. execption yang dilempar untuk kondisi salah
   3. apa yang disimpan (hasil), dan yang tidak disimpan
   4. Interaksi penting: event terkirim, pembayaran tidak dipanggil dua kali

   jangan di lakukan:
   1. Mengejar angka coverage: test yang cuma verify(save) menaikkan coverage tapi nggak menjaga aturan apa pun.
   2. Mock semua termasuk objek value (DTO, record): test jadi rumit dan nggak menguji apa-apa.
   3. Nggak ada test jalur gagal: happy path doang, padahal bug nyata sering di cabang penolakan.
   4. jangan unit-test-mock untuk membuktikan query atau mapping DB; service yang cuma meneruskan ke repository tanpa logika (test-nya cuma cermin).

   Tes Feynman: unit test service memastikan aturan bisnis ditegakkan, dengan dependency palsu, dengan menanyakan "apa hasilnya?" bukan "siapa yang dipanggil?".

   Aturan Emas: Test yang nggak bisa gagal waktu kodenya salah itu bukan test, itu dekorasi.

   Terapin: sebelum merge, sengaja rusakin satu aturan bisnis di kode dan pastikan minimal satu test jadi merah.

   # Slice test `@WebMvcTest`

   Slice test menguji kontrak HTTP: request masuk, response keluar.

   layak di uji:
   1. Status code per skenario (201, 400, 404, 409)
   2. Validation: @Valid, pesan error, format Problem Details
   3. Mapping exception ke status lewat @RestControllerAdvice
   4. Header (Location, Content-Type), bentuk JSON, serialisasi

   @WebMvcTest untuk controller
   @DataJpaTest untuk repository 
   @SpringBootTest seperlunya

   jangan di lakukan:
   1. Pakai @SpringBootTest untuk semua test controller: lambat, dan kegagalan susah dilacak ke layer mana.
   2. Cuma ngetes happy path 200/201: padahal bug nyata ada di 400, 404, dan format error.
   3. Assert mentah ke string JSON panjang: rapuh. Pakai jsonPath untuk field yang jadi kontrak.
   4. jangan pakai slice untuk membuktikan aturan bisnis atau query DB; controller tanpa logika dan tanpa validasi nggak butuh test sendiri (cukup ditangkap test kontrak).

   Tes Feynman: slice test memastikan pintu depan API menolak, menerima, dan menjawab dengan benar, tanpa menyalakan seluruh sistem.

   Aturan Emas: Kalau input salah sampai ke logika bisnis, pintunya yang bocor, bukan logikanya.

   Terapin: tiap endpoint baru, tulis test 400 dengan verifyNoInteractions(service) sebelum merge.

   # Integration test dengan Testcontainers

   Testcontainers menjalankan DB sungguhan di container, jadi constraint, index, dan dialect production ikut teruji.

   layak di uji:
   1. Query custom, native SQL, JPQL kompleks
   2. Constraint, index, hasil Flyway migration
   3. Perilaku transaksi, locking, rollback
   4. Mapping tipe spesifik DB (`jsonb`, enum, array)

   jangan di lakukan:
   1. Pakai H2 buat "sekadar cepat", lalu schema digenerate dari entity: constraint dan index production nggak pernah teruji.

   Tes Feynman: integration test Testcontainers menguji kode lu di database yang sama persis dengan production, supaya bug yang cuma muncul di database asli ketahuan sebelum deploy.

   Aturan Emas: Test yang bohong soal lingkungan itu lebih berbahaya daripada nggak ada test.
   
   Terapin: sebelum percaya test hijau, tanya "ini jalan di mesin yang sama dengan production nggak?" dan kalau beda, catat bug apa yang mungkin lolos.

   # Menguji skenario yang sering dilewatkan

   Coverage tinggi bukan bukti aman: happy path nggak menyentuh validasi, auth, dan batas data.

   jangan di lakukan:
   1. Ngukur kualitas test dari coverage, bukan dari skenario gagal yang tercakup.
   2. Cuma tes 401 tanpa tes 403/404: user login yang jahat tetap bisa baca data orang lain.
   3. Nggak tes batas pagination: halaman terakhir dan halaman kosong paling sering salah satu per satu (off-by-one).
   4. jangan duplikasi skenario yang sama di tiga layer. Satu skenario cukup di layer paling murah yang bisa membuktikannya.

   Tes Feynman: test yang bagus bukan yang membuktikan program jalan saat semuanya benar, tapi yang membuktikan program tetap aman dan jelas saat ada yang salah.

   Aturan Emas: Orang baik nggak butuh aturan, aturan dibuat untuk saat ada yang salah.
   
   Terapin: tiap menulis fitur, tulis dulu satu skenario "bagaimana kalau ini disalahgunakan atau salah isi", baru happy path.

   # Contract testing konsep lanjutan (Pact)

   Pact: consumer menulis kontrak seperlunya, provider memverifikasinya di pipeline sebelum deploy.

   jangan di lakukan:
   1. Kontrak berisi seluruh respons, termasuk field yang nggak dipakai: provider jadi nggak bisa berubah tanpa alarm palsu. Tulis hanya yang benar-benar dipakai.
   2. Pakai matcher nilai persis ("PAID") padahal yang penting tipenya: kontrak jadi rapuh. Pakai matcher tipe.
   3. Verifikasi provider cuma jalan di laptop, nggak di pipeline: kontrak ada tapi nggak pernah menghentikan apa pun.
   4. satu tim, satu repo, deploy bersamaan (test integrasi biasa cukup), atau API publik dengan consumer tak dikenal (pakai versioning dan deprecation, Module 10).

   Tes Feynman: contract testing memastikan penyedia API tidak diam-diam mengubah hal yang dipakai pemakainya, dengan memeriksa daftar kebutuhan pemakai sebelum perubahan dirilis.

   Aturan Emas: Jangan ubah sesuatu yang dipakai orang lain tanpa tahu siapa pemakainya dan apa yang mereka pakai.
   
   Terapin: sebelum rename atau hapus field API, cek kontrak consumer atau tanya tim pemakainya, bukan cuma andalkan test sendiri.





