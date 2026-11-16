# REST sebagai Architectural Style

   # Enam constraint REST Fielding (client-server, stateless, cacheable, uniform interface, layered system, code-on-demand)

   REST = gaya arsitektur dengan enam constraint Fielding, bukan "JSON lewat HTTP".
   
   Lima wajib: client-server, stateless, cacheable, uniform interface, layered system. Code-on-demand opsional.

   1. Client-Server     UI dan data dipisah, ngobrol lewat satu antarmuka   -> tim UI dan backend bisa jalan sendiri-sendiri
   2. Stateless         tiap request bawa semua konteks (Lesson 1.5)         -> scale horizontal, restart aman
   3. Cacheable         response bilang boleh/nggaknya di-cache               -> kurangi beban server dan latensi
   4. Uniform Interface resource + representasi + method standar + hypermedia -> semua client ngerti pola yang sama
   5. Layered System    client nggak tau lagi ngobrol sama server asli/proxy   -> gateway, CDN, WAF bisa disisipkan
   6. Code-on-Demand    server boleh ngirim kode (mis. JS) buat dijalankan     -> OPSIONAL

   jangan di lakukan:
   1. Ngira REST = JSON over HTTP. Itu cuma kulitnya.
   2. Menyebut API "RESTful" padahal nyimpen session di memori server (melanggar stateless).
   3. Mengira code-on-demand wajib, lalu bingung nyari "kode apa yang harus dikirim server".

   Tes Feynman: REST itu seperangkat aturan yang ditaati bersama supaya sistem gampang membesar dan berubah, bukan sekadar API yang kebetulan pakai JSON.

   Aturan Emas: Sebelum nyebut sesuatu "RESTful", sebutin constraint mana yang dipenuhi dan mana yang sengaja dilewatin.

   Terapin: di review API, ganti "ini sudah REST belum?" jadi "dari enam constraint, yang mana yang kita langgar dan kenapa?"

   # Richardson Maturity Model (Level 0-3)

   RMM punya empat level: 0 satu endpoint, 1 resource, 2 method + status code, 3 hypermedia.

   Tes Feynman: Richardson Maturity Model ngukur seberapa banyak API memakai fitur HTTP (alamat per resource, kata kerja, kode hasil, dan link) supaya client bisa paham tanpa baca isi kepala developer-nya.

   Aturan Emas: Jangan bilang "sudah REST", sebutin level-nya dan apa yang sengaja dilewatin.

   # Resource modeling

   tiga aturan yang bisa dipakai untuk modeling:
   1. Resource = nomina / kata benda, bukan aksi. Aksi diekspresikan oleh method (POST, DELETE)
   2. Nested kalau child nggak bisa hidup tanpa parent. Item order kehilangan makna kalau ordernya hilang, jadi /orders/{id}/items.
   3. Referensi, jangan menanam. Item nyimpen productId (atau link /products/10), bukan menanam seluruh objek product.

   jangan di lakukan:
   1. URI berisi verb (/getOrders, /deleteItem), lalu method HTTP jadi nggak bermakna.
   2. Nesting terlalu dalam, id parent harus selalu dibawa padahal child sudah punya id unik.
   3. Menanam objek lengkap (product utuh di tiap item) sehingga data duplikat dan cepat basi.
   4. Mengekspos tabel database apa adanya, padahal resource itu konsep domain, bukan struktur tabel.

   Tes Feynman: resource modeling itu memilih benda-benda domain sebagai alamat tetap, menaruh anak di dalam induk hanya kalau nggak bisa hidup sendiri, dan menghubungkan sisanya lewat referensi.

   Aturan Emas: Desain URI dari benda yang ada di domain, bukan dari aksi yang ada di kepala lu.

   Terapin: sebelum nulis endpoint, tulis dulu daftar nomina dan tanya tiap satu "bisa hidup tanpa induknya?".

   # URI naming sesuai `standards/API_STANDARDS.md`

   uri naming standards:
   Lowercase
   path case-sensitive, dan manusia gampang salah ketik huruf besar. Satu bentuk saja berarti nggak ada 404 misterius.

   hyphen kalau perlu pemisah
   terbaca sebagai pemisah kata di URL, dan gampang dibaca di log. Garis bawah sering ketutup garis bawah link.

   Resource plural
   /users adalah koleksi, /users/5 anggota koleksi. Satu bentuk untuk keduanya, jadi nggak perlu mikir "ini /user atau /users".

   tanpa verb
   verb sudah dibawa HTTP method (Lesson 1.3 dan 2.3). Verb di URI bikin method jadi dekorasi.

   jangan di lakukan:
   1. Mencampur gaya antar endpoint (/order-items dan /payment_transactions di API yang sama).
   2. Menaruh verb di URL (/getOrders, /deleteItem) lalu method HTTP jadi nggak bermakna.
   3. Singular di sebagian, plural di sebagian.
   4. Menggunakan huruf besar karena "tampil lebih rapi" di dokumentasi.
   5. Menaruh nilai di nama resource (/orders-2026) padahal itu filter.
   6. jangan memaksa nama plural pada hal yang memang bukan koleksi (/health). Catat sebagai pengecualian.

   Tes Feynman: aturan URI naming ada supaya orang yang belum pernah melihat endpoint bisa menebak alamatnya dengan benar.

   Aturan Emas: Konsistensi lebih berharga daripada selera: pilih aturan sekali, lalu ikuti di mana-mana.

   Terapin: sebelum nambah endpoint, jalankan linter atau cek checklist di atas, jangan andalkan ingatan.

   # RPC-style vs REST-style
   
   Tes Feynman: pakai REST-style untuk mengubah data, pakai endpoint aksi kalau perubahan itu punya aturan bisnis yang harus dijaga server.

   Aturan Emas: Taruh aturan di tempat yang nggak bisa dilewati, bukan di ingatan orang yang memakainya.




