# REST-specific Resilience & Observability

   # Idempotency key sebagai kontrak HTTP

   POST nggak idempotent. Idempotency key membuat retry aman lewat header yang di-generate klien dan dipakai ulang.

   Idempotent artinya: dikirim berkali-kali, efeknya sama dengan sekali. Di HTTP, GET, PUT, DELETE memang idempotent menurut spec.

   POST /orders
   Idempotency-Key: 7f3a...   <- UUID dari klien, sama di setiap retry

   1. Hitung fingerprint body.
   2. Claim key secara ATOMIK (insert dengan UNIQUE(key)).
   3. Key belum ada      -> proses, simpan status + body respons, balas.
   4. Key ada, DONE      -> replay: status + body yang tersimpan.
   5. Key ada, IN_PROGRESS -> 409 Conflict.
   6. Key ada, body beda -> 422 Unprocessable.

   jangan di lakukan:
   1. Server yang generate key. Percuma: retry-nya nggak membawa key yang sama. Klien yang harus generate.
   2. Klien bikin key baru di setiap retry, jadi server melihat request yang berbeda.
   3. SELECT dulu baru INSERT: dua retry paralel sama-sama lolos dan order dobel.
   4. Hanya menyimpan key tanpa hasil, atau menyimpan hasil tapi tanpa membandingkan body.
   5. Operasi yang efeknya memang boleh dobel dan murah.

   Tes Feynman: idempotency key itu nomor yang dibawa klien supaya server tahu "ini permintaan yang sama diulang", bukan permintaan baru.

   Aturan Emas: Kalau sebuah operasi boleh di-retry, rancang supaya retry-nya aman, bukan berharap retry-nya nggak terjadi.

   Terapin: sebelum menambah retry di klien atau consumer, tanya "kalau request ini sampai dua kali, apa yang rusak?" lalu tutup celahnya di server.

   # 202 Accepted + status endpoint

   202 Accepted artinya diterima tapi belum selesai; hasil diambil lewat resource status.

   best case:
   Operasi lambat atau nggak terduga durasinya: gateway pembayaran, generate laporan, export besar, proses batch.

   Tes Feynman: kalau kerjaannya lama, jawab dulu "diterima" plus nomor tiket, lalu biarkan klien cek hasilnya nanti, jangan suruh dia menunggu.

   Aturan Emas: Jangan suruh klien menunggu hal yang lama; kasih tiket dan biarkan dia menanyakan hasilnya.

   Terapin: sebelum menaikkan timeout buat endpoint yang lambat, tanya "apakah ini harusnya 202 + status endpoint?"

   # Asynchronous processing di balik API

   POST /payments/confirm -> validasi -> simpan job PENDING -> taruh di queue -> 202 + Location
                                                              |
                                           worker ambil pesan |
                                                              v
                       PENDING -> PROCESSING -> DONE | FAILED -> (webhook ke klien, atau klien polling)

   Setelah 202, worker mengerjakan job dari queue dengan state machine PENDING -> PROCESSING -> DONE/FAILED yang menolak transisi ilegal.

   Delivery biasanya at-least-once, jadi worker wajib idempotent dengan update state atomik.

   jangan di lakukan:
   1. Menganggap queue menjamin "tepat sekali", lalu worker nggak idempotent dan efek samping (tagihan, email) dobel.
   2. Webhook tanpa signature, atau signature dibandingkan dengan == biasa. Pakai perbandingan constant-time.
   3. Cek state lalu update sebagai dua langkah terpisah (race). Pakai UPDATE ... WHERE state = 'PENDING' dan lihat jumlah baris yang berubah.
   4. Job PROCESSING yang worker-nya mati nyangkut selamanya. Beri timeout atau lease supaya job bisa diambil ulang.
   5. Operasi cepat yang klien butuh hasilnya langsung.

   Tes Feynman: kerja yang dikerjakan di belakang layar bisa kebagian dua kali, jadi pekerjanya harus mengecek "sudah dikerjakan belum" dulu.

   Aturan Emas: Kalau pesan bisa datang dua kali, rancang pekerjanya supaya dua kali sama aman dengan satu kali.

   Terapin: sebelum menulis consumer atau worker, tanya "kalau pesan ini diproses dua kali, apa yang rusak?" dan tutup celahnya dengan cek state atomik.