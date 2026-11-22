# Architecture, API Governance & Capstone

   # API sebagai produk

   API itu produk, konsumen itu pelanggan, DX itu fitur.

   aturan praktis:
   1. Konsistensi > kesempurnaan lokal. Konvensi "cukup bagus" yang dipakai semua tim menang lawan desain terbaik yang cuma dipakai satu tim. Konsumen belajar sekali, bukan N kali.
   2. Biaya inkonsistensi dibayar konsumen, bukan producer
   3. Konsumen internal tetap pelanggan.
   4. Ukur DX: time-to-first-successful-call, jumlah pertanyaan di channel, jumlah tiket "ini maksudnya apa"

   jangan di lakukan:
   1. Ngukur API dari sisi producer ("udah sesuai REST") dan lupa nanya konsumen.
   2. Nganggep standar itu birokrasi, padahal itu biaya integrasi yang dipindahin dari ratusan konsumen ke satu tempat.
   3. Bikin standar tanpa enforcement. Dokumen 40 halaman yang nggak dicek otomatis bakal dilupain dalam sebulan.
   4. prototype buang dalam seminggu, atau API yang cuma dipakai satu service lu sendiri dan nggak keluar dari tim. Jangan over-govern.

   Tes Feynman: API yang bagus itu yang gampang dipakai orang lain dengan cara yang sama kayak API lain, bukan yang paling pintar desainnya.

   Aturan Emas: Ukur karya lu dari betapa gampang orang lain memakainya, bukan dari betapa bangga lu sama desainnya.

   Terapin: sebelum merge endpoint baru, tanya satu orang dari tim konsumen "coba call ini tanpa nanya gua", terus catat di mana dia mentok.

   # API Review Checklist (`standards/API_STANDARDS.md`)

   Review API itu review kontrak, bukan cuma kode: kontrak yang sudah dipakai klien mahal diubah.

   Yang bisa dicek mesin diserahin ke linter, manusia fokus ke judgment desain.

   Urutan review API yang masuk akal, dari yang paling mahal kalau salah:

   1. Kontrak: path, method, status code, bentuk error karena Sekali dipakai klien, mahal diubah
   2. Keamanan: authn/authz, object-level check, data sensitif karena Salah di sini = insiden, bukan bug
   3. Aman di-retry: idempotency, konkurensi, timeout karena Jaringan pasti gagal
   4. Skala: pagination, filter, batas ukuran karena Murah di awal, mahal setelah data besar
   5. Dokumentasi: contoh nyata, deprecation note karena Tanpa ini konsumen nanya terus
   6. Baru terakhir: gaya kode karena Bisa dibenerin kapan saja

   Aturan praktis:
   MUST = nggak boleh merge. SHOULD = catat, boleh nyusul. Dan satu lagi: tulis aturannya sekali di standar tim, jangan jadi opini reviewer.

   jangan di lakukan:
   1. Review kode tapi nggak review kontraknya, padahal kontrak yang susah diubah.
   2. Semua komentar dianggap sama berat, jadi PR ketahan gara-gara nitpick sementara masalah besar lewat.
   3. Checklist ada tapi cuma di kepala satu orang, jadi hasil review tergantung siapa yang kebagian.
   4. PR yang murni internal (refactor, log) tanpa dampak ke kontrak. Cukup review kode biasa.

   Tes Feynman: review API itu mengecek janji ke orang lain dulu, baru kerapian kode.

   Aturan Emas: Saat review, tanya dulu "apa yang susah diubah setelah orang lain memakainya?", baru urusin yang lain.

   Terapin: di PR berikutnya, tulis komentar soal kontrak (status code, bentuk error, field) lebih dulu dan kasih label MUST atau SHOULD di tiap komentar.

   # API governance di organisasi nyata

   Governance bertumpu pada linter di CI, lifecycle yang dijaga, dan otoritas yang jelas.

   Aturan praktis:
   1. Yang bisa dicek mesin jangan dicek manusia. Manusia dipakai buat hal yang butuh judgment.
   2. Tiap API punya owner tercatat (tim dan kontak).
   3. Sunset berdasar data trafik, bukan perasaan.
   4. Breaking change itu keputusan owner API plus konsumen terdampak

   jangan di lakukan:
   1. Bikin guideline 40 halaman tanpa linter, jadi nggak ada yang baca dan nggak ada yang menegakkan.
   2. API tanpa owner tercatat, sehingga nggak ada yang bisa approve perubahan atau berani mematikannya.
   3. Sunset karena "kayaknya udah nggak dipakai", bukan karena data trafik.

   Tes Feynman: governance itu aturan yang ditegakkan mesin, pemilik yang jelas, dan siklus hidup yang tertib, bukan dokumen yang cuma dibaca.

   Aturan Emas: Aturan yang nggak ditegakkan cuma saran, jadi serahin penegakan ke mesin dan kasih tiap hal satu pemilik yang jelas.

   Terapin: pilih satu aturan yang sering lu ingatkan manual di review, lalu pindahin jadi cek otomatis di CI minggu ini.

   # Menyatukan Module 1-13 jadi satu sistem koheren