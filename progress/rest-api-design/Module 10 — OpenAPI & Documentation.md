# OpenAPI & Documentation

   # OpenAPI Specification

   OpenAPI = satu file terstruktur yang menjelaskan API: info, paths, components.

   jangan di lakukan:
   1. Nulis spec manual lalu nggak pernah disinkronkan dengan kode. Dokumen jadi basi persis seperti wiki.
   2. Jangan jadikan spec satu-satunya bukti: tetap cek perilaku nyata.

   Tes Feynman: OpenAPI adalah daftar endpoint (paths) plus kamus bentuk data yang dipakai ulang (components), dan lu baca daftarnya dulu baru kamusnya.

   Aturan Emas: Baca kontraknya dulu sebelum nulis satu baris kode integrasi.

   Terapin: sebelum nanya "endpoint-nya apa?" ke rekan, buka openapi.yaml atau Swagger UI dulu, baru tanya hal yang spec nggak jawab.

   # `springdoc-openapi`

   springdoc membaca controller, DTO, dan constraint, lalu menghasilkan spec di /v3/api-docs dan Swagger UI.

   jangan di lakukan:
   1. Cuma anotasi response sukses, error (401, 404, 422) dibiarkan hilang dari spec.
   2. Swagger UI terbuka di production tanpa proteksi, jadi peta lengkap API (termasuk endpoint internal) terbuka ke siapa saja. Matikan atau amankan di prod, dan tandai @Hidden untuk yang internal.
   3. Anotasi @Operation ditulis dengan deskripsi kosong atau copy-paste ("Get data"), jadi dokumen ada tapi nggak menolong.

   Tes Feynman: springdoc menulis dokumen dari kode yang kelihatan, jadi hal yang nggak kelihatan di kode (terutama error) harus lu labeli sendiri.

   Aturan Emas: Dokumen yang ditulis terpisah dari kode pasti berbohong suatu hari, jadi taruh dokumen di tempat yang sama dengan perilakunya.

   Terapin: tiap kali lu ubah apa yang endpoint balikin (termasuk error baru), ubah anotasinya di PR yang sama, bukan nanti.

   # Contract-first vs code-first

   Code-first menjadikan kode sumber kebenaran, contract-first menjadikan spec sumber kebenaran yang direview dulu.

   jangan di lakukan:
   1. Memilih contract-first tapi spec dan kode tidak diverifikasi otomatis, jadi tetap basi, cuma lebih melelahkan.
   2. Memilih code-first tapi spec hasil generate tidak pernah di-diff di CI, jadi rename field menyelinap ke consumer.
   3. Menganggap ini pilihan agama. Satu organisasi boleh memakai keduanya: contract-first untuk API publik dan antar-tim, code-first untuk service internal satu tim.

   Tes Feynman: contract-first berarti kesepakatan ditulis dan disetujui dulu lalu kode diperiksa terhadapnya, sedangkan code-first berarti kesepakatan ditebak dari kode yang sudah jadi.

   Aturan Emas: Sebelum mengubah sesuatu yang dipakai orang lain, ubah dulu kesepakatannya, baru kodenya.

   Terapin: sebelum rename atau hapus field di response, buka PR untuk perubahan spec dan tag consumer-nya, jangan langsung refactor.


   # Dokumentasi lengkap sesuai `standards/OPENAPI_STANDARDS.md`

   Akurat belum cukup: dokumentasi harus lengkap (semua response, skema error, contoh, auth, rate limit, versi) supaya consumer mandiri.

   Tes Feynman: dokumentasi yang bagus menjawab bukan cuma "apa yang terjadi kalau berhasil" tapi juga "apa yang terjadi kalau gagal dan gimana menanganinya".

   Aturan Emas: Dokumen yang bagus menjawab pertanyaan sebelum orang sempat menanyakannya, terutama pertanyaan "kalau gagal gimana?"

   Terapin: sebelum merge endpoint baru, baca spec-nya sebagai orang yang belum pernah lihat kode, lalu cek apakah error, contoh, auth, dan rate limit sudah terjawab.






