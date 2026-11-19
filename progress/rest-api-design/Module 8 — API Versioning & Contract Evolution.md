# API Versioning & Contract Evolution

   # Breaking vs non-breaking change

   Breaking change diukur dari sisi klien lama: kalau kode klien yang sudah benar jadi gagal tanpa diubah, itu breaking.

   Response boleh bertambah, nggak boleh berkurang atau berubah; request boleh melonggar, nggak boleh mengetat.

   Dua jebakan yang sering lolos:
   1. Perubahan semantik. Bentuk JSON sama, tapi artinya beda.
   2. Tambah nilai enum. Secara spec kelihatan aman, tapi klien yang switch tanpa default pecah.

   jangan di lakukan:
   1. Menganggap "test lulus" sama dengan "kontrak aman". Test server nggak tahu kode klien lu.
   2. Menganggap field baru selalu aman, padahal kalau field itu wajib di request, klien lama langsung 400.
   3. Mengubah arti field tanpa mengubah bentuknya, jadi nggak ada yang sadar ini breaking.

   Tes Feynman: perubahan itu breaking kalau klien lama yang nggak diubah jadi rusak.

   Aturan Emas: Sebelum mengubah kontrak, tanya "klien yang nggak gua kenal dan nggak bisa gua paksa update, masih jalan nggak?"

   Terapin: tiap review PR yang menyentuh DTO atau response, tulis satu komentar jawaban pertanyaan itu sebelum approve.

   # Strategi versioning

   URI paling mudah dipakai, header dan Accept paling bersih secara teori tapi paling gampang salah di praktik. Hampir semua tim pilih satu dan konsisten.

   Dua aturan yang berlaku di semua strategi:
   1. Default tanpa versi = versi lama yang stabil, bukan yang terbaru. Klien yang nggak menyebut versi tidak boleh tiba-tiba berubah perilakunya.
   2. Versi hanya naik untuk breaking change

   jangan di lakukan:
   1. Memilih header, lalu membiarkan default ke versi terbaru. Klien yang header-nya hilang langsung pindah versi tanpa sadar.
   2. Mencampur strategi (sebagian endpoint di URI, sebagian di header), jadi klien harus menghafal aturan per endpoint.
   3. Menaikkan versi untuk perubahan non-breaking, jadi versi menumpuk dan tidak ada yang berani mematikannya.

   Tes Feynman: versi itu cara klien memilih kontrak yang dia mau, dan kalau dia nggak memilih, dia tetap dapat yang lama.

   Aturan Emas: Pilih satu cara versioning, tulis, dan jadikan "tanpa versi" berarti "versi lama yang stabil".

   Terapin: waktu review endpoint baru, tanya "kalau klien nggak kirim versi, dia dapat apa?" sebelum approve.

   # Deprecation policy

   Deprecation itu tiga fase: aktif, deprecated dengan header Deprecation, Sunset, dan Link, lalu mati dengan 410 Gone.

   jangan di lakukan:
   1. Mematikan versi tanpa pengumuman ke semua pemilik integrasi, hanya ke satu channel internal.
   2. Mengandalkan header saja dan menganggap klien pasti membacanya.
   3. Mematikan dengan 404 atau 500, jadi klien tidak tahu itu disengaja dan harus pindah ke mana.
   4. Menentukan tanggal sunset tanpa melihat siapa yang masih memakai versi lama.

   Tes Feynman: mematikan versi itu proses memberi tahu, memberi waktu, lalu menutup dengan petunjuk jalan, bukan sekadar mematikan.

   Aturan Emas: Jangan matikan apa pun sebelum semua yang memakainya diberi tahu dan diberi waktu cukup untuk pindah.

   Terapin: sebelum menetapkan tanggal sunset, buka log dan tulis daftar siapa yang masih memanggil versi lama.

   # Consumer-driven contract testing (konsep)

   Test provider membuktikan keyakinan provider; breaking change butuh bukti dari sisi consumer.

   Consumer menulis kontrak minimal (hanya yang dia baca), provider memverifikasinya di CI sebelum rilis.

   Tes Feynman: yang tahu apa yang dipakai adalah pemakainya, jadi pemakai yang menulis kontrak dan penyedia yang membuktikannya.

   Aturan Emas: Jangan menilai perubahan dengan keyakinan sendiri; minta bukti dari orang yang memakainya.

   Terapin: sebelum mengubah response yang dipakai tim lain, tanya mereka "field mana yang beneran lu baca?" dan minta jawabannya ditulis sebagai test.

