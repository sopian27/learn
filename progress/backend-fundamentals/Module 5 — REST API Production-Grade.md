# REST API Production-Grade
  # Desain resource & kontrak REST yang konsisten (lesson ini)

  jangan di lakukan :
  1. Selalu return 200 dan taruh error di body, bikin retry, caching, dan monitoring buta.
  2. Bikin endpoint baru untuk tiap filter (/getPaidOrders, /getNewOrders) padahal cukup ?status=.
  3. List endpoint tanpa pagination atau tanpa cap size, satu request bisa tumbangin server.

  Aturan Emas: 
  Kontrak API itu janji ke orang yang nggak bisa lu tanya; bikin dia bisa ditebak, bukan kreatif.

  Terapin: 
  sebelum nambah endpoint, cari dulu endpoint serupa di service lu dan tiru polanya, termasuk status code dan bentuk error.

  # Versioning strategy (URI vs header) dan kapan breaking change butuh versi baru

  jangan di lakukan :
  1. Nganggep rename field itu "refactor kecil". Buat klien yang udah rilis, itu breaking change.
  2. Naikin versi tiap ada perubahan, sampai ada v7 yang semuanya masih hidup dan nggak ada yang berani matiin.
  3. Matiin versi lama tanpa deprecation notice dan tanpa ngecek siapa yang masih manggil.

  Aturan Emas: 
  Server itu milik lu, tapi bentuk response milik klien yang udah terlanjur bergantung; tambah boleh, ubah harus pakai versi.

  Terapin: 
  pas review PR yang nyentuh DTO response, tanya satu hal: "klien lama yang belum update bakal ngapain?"

  # Validation layer yang benar dan error response konsisten
  dua lapis validasi:
  1. format / struktur : DTO + @valid
  2. aturan bisnis : service /domain

  jangan di lakukan :
  1. Jangan pernah kirim stack trace atau pesan exception mentah. Detailnya masuk log, klien cuma dapat traceId buat dilaporin ke support.
  2. Validasi lempar exception biasa dan nggak ada handler global, jadinya 500 + stack trace.
  3. jangan pindahin aturan bisnis yang butuh DB ke annotation. Jangan validasi ulang hal yang sama di tiga lapis (controller, service, repository) tanpa alasan.

  Aturan Emas: 
  Pesan error itu antarmuka buat orang yang lagi salah; tulis supaya dia tahu apa yang harus dibenerin, dan jangan pernah kasih peta rumah lu.

  Terapin: 
  pas review PR, cari throw di jalur input dan tanya "klien bakal liat status dan body apa?"; kalau jawabannya stack trace, tolak.

  # Dokumentasi API-first dengan OpenAPI
  Aturan Emas: 
  Dokumentasi yang nggak dijaga mesin cuma janji; jadiin kontrak itu yang menggugat kode, bukan sebaliknya.

  Terapin: 
  pas bikin atau review endpoint, tanya "kalau kode ini beda dari spec, apa yang bakal merah, dan di mana?"

  # Idempotency & konvensi REST lanjut: PUT vs PATCH, ETag

