# HATEOAS & Hypermedia

   # HATEOAS & hypermedia

   HATEOAS (Hypermedia As The Engine Of Application State) = response bawa daftar aksi/navigasi yang valid saat ini. Client ngikutin link, bukan bangun URL dan aturan sendiri.

   HATEOAS = response bawa link aksi valid sesuai state, aturan tinggal di server.

   jangan dilakukan:
   1. Mengira HATEOAS = nambahin self link di semua response. Link yang nggak pernah dipakai client cuma bikin noise.
   2. Mengirim link statis yang nggak tergantung state. Itu nggak ngasih nilai apa-apa.
   3. API internal dengan satu-dua client yang rilis bareng backend.

   Tes Feynman: server yang kasih tau aksi apa yang boleh sekarang, supaya client nggak perlu nebak dan nggak ketinggalan saat aturan berubah.

   Aturan Emas: Aturan yang bisa berubah simpan di satu tempat, jangan disalin ke semua pemakainya.
   
   Terapin: sebelum nulis if (status == ...) di client, tanya dulu "ini aturan siapa? kalau berubah, berapa tempat yang harus gue rilis ulang?"

   # Implementasi HATEOAS dengan Spring HATEOAS pada Order Management API (link `self`, `cancel`, `items` berdasarkan state order)

   Tes Feynman: server yang ngitung dan nunjukin langkah berikutnya yang valid, jadi alamat dan aturan nggak ditulis tangan di banyak tempat.

   Aturan Emas: Jangan tulis tangan sesuatu yang bisa dihitung oleh kode.
   
   Terapin: pas mau nulis string URL atau if (status == ...) di dua tempat, tanya "bisa nggak ini diturunin dari satu sumber?"




