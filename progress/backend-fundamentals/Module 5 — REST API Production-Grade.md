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

Validation layer yang benar dan error response konsisten
Dokumentasi API-first dengan OpenAPI
Idempotency & konvensi REST lanjut: PUT vs PATCH, ETag

