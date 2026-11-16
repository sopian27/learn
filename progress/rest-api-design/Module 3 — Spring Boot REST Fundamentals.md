# Spring Boot REST Fundamentals

   # `@RestController`, `@RequestMapping`, path variable vs query parameter

   @RestController = @Controller + @ResponseBody, @RequestMapping di class memberi prefix bersama.

   Path variable untuk identitas (wajib, 404 kalau nggak ada), query param untuk filter (opsional, list kosong tetap 200).

   Tes Feynman: identitas resource masuk path dan wajib ada, sedangkan filter masuk query dan boleh kosong.

   Aturan Emas: Kalau ragu, tanya: "kalau bagian ini dihapus, alamatnya masih sama?" Kalau iya, itu saringan, bukan alamat.

   Terapin: tiap nambah parameter ke endpoint baru, tulis dulu 404 atau list kosong yang diharapkan sebelum memilih path atau query.

   # Request/response body binding

   @RequestBody = body mentah → objek lewat HttpMessageConverter. Gagal → 400 sebelum controller jalan.

   ResponseEntity = status + header + body dalam satu objek, buat kasus di luar 200 biasa.

   POST buat resource: 201 + Location. DELETE sukses: 204. GET nggak ketemu: 404.

   Tes Feynman: @RequestBody mengubah isi request jadi objek, dan ResponseEntity memberi tahu klien hasilnya dengan status dan alamat yang tepat, bukan cuma isi.

   Aturan Emas: Respons itu janji ke klien: status dan header harus cukup supaya klien nggak perlu menebak dari body.

   # Content negotiation

   Tes Feynman: Content-Type menyebut format yang lu kirim, Accept menyebut format yang lu mau terima, dan server menolak kalau nggak ada yang cocok.

   Aturan Emas: Label format itu bagian dari kontrak, jadi nyatakan eksplisit dan jangan biarkan klien menebak.

   Terapin: tiap bikin endpoint baru, tulis consumes dan produces-nya dulu, lalu tulis test untuk satu 415 dan satu 406.

   consumes memfilter Content-Type request, produces memfilter Accept dan menentukan Content-Type respons.

   # Layered architecture untuk REST

   jangan di lakukan:
   1. Menaruh aturan bisnis di controller, lalu menyalinnya ke job atau consumer, sehingga satu aturan punya beberapa versi.
   2. Service yang menerima atau mengembalikan HttpServletRequest atau ResponseEntity. Itu membuat service nggak bisa dipakai di luar HTTP.
   3. Repository berisi aturan bisnis (misal query yang menyembunyikan diskon), sehingga logika tersebar di tempat yang nggak terduga.
   4. Membuat layer "demi rapi" padahal isinya cuma meneruskan panggilan tanpa nilai tambah, lalu bingung kenapa file bertambah tapi kode nggak lebih jelas.
   5. membuat service kosong untuk endpoint CRUD yang benar-benar nggak punya aturan, kalau tim sudah sepakat soal itu. Jangan juga menambah layer baru (mapper, facade, manager) tanpa masalah nyata yang dipecahkan.

   Tes Feynman: controller mengurus cara orang meminta, service mengurus apa aturannya, repository mengurus di mana datanya disimpan

   Aturan Emas: Satu aturan harus punya satu rumah, dan rumah itu jangan tergantung pada cara orang mengetuk pintu.

   Terapin: tiap menulis if yang menyangkut aturan bisnis, tanya "kalau ada pemanggil lain selain HTTP, apakah aturan ini ikut?" Kalau nggak ikut, pindahkan ke service.


