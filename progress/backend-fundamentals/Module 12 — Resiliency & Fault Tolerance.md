# Resiliency & Fault Tolerance

  # Failure mode di sistem terdistribusi

  Timeout
  Batas waktu nunggu. Tanpa ini, "lambat" = "tunggu selamanya"

  Partial failure
  Sebagian komponen gagal, sisanya hidup. Lu nggak tahu pasti yang mana

  Cascading failure
  Kegagalan satu komponen menjalar karena resource (thread, koneksi) habis

  best case:
  1. Selalu set connect timeout dan read timeout. Default banyak library = tak terbatas.
  2. Timeout lu harus lebih kecil dari timeout si pemanggil lu (kalau nggak, dia nyerah duluan, lu masih kerja sia-sia).


  jangan di lakukan:
  1. Percaya default: banyak HTTP client nggak punya timeout sama sekali sampai lu set sendiri.
  2. Menganggap "dependency-nya cuma nice-to-have, jadi aman." Kalau lu manggilnya sinkron di thread yang sama, dia tetap bisa menjatuhkan lu.
  3. Cuma monitor CPU/memory. Thread pool exhaustion kelihatan sehat di dua metrik itu.
  4. Proses internal in-memory tanpa I/O.
  5. Batch job offline yang memang boleh jalan lama (tetap kasih batas atas, tapi besar).

  Aturan Emas: 
  Dependency yang lambat lebih berbahaya daripada yang mati, jadi jangan pernah nunggu tanpa batas.

  Terapin: 
  sebelum merge PR yang nambah call ke service lain, tanya "timeout-nya berapa, dan apa yang terjadi kalau habis?"  

  # Resilience4j: retry (backoff), circuit breaker, timeout, bulkhead

  Circuit Breaker adalah mekanisme untuk menghentikan sementara request ke dependency yang sering gagal agar kegagalan tidak merambat (cascading failure) ke service pemanggil.

  Analogi: MCB listrik yang memutus aliran ketika terjadi masalah.

  closed : Request diteruskan, kegagalan dipantau
  open : Request langsung ditolak, tidak memanggil dependency
  half_open : Mengizinkan sejumlah kecil request percobaan

  CLOSED
   |
   | Failure rate >= threshold
   v
 OPEN
   |
   | waitDurationInOpenState habis
   v
HALF_OPEN
   |
   +---- Percobaan berhasil ---> CLOSED
   |
   +---- Percobaan gagal ------> OPEN

   Penjelasan:

    CLOSED: Dependency dianggap sehat. Request tetap diteruskan.

    OPEN: Dependency dianggap bermasalah. Request ditolak sementara tanpa memanggil dependency.

    HALF_OPEN: Dependency diuji dengan sejumlah kecil request.

    Berhasil sesuai aturan konfigurasi → CLOSED.

    Gagal sesuai aturan konfigurasi → OPEN kembali.

    jangan di lakukan :
    1. Retry tanpa backoff dan jitter: semua client mengulang di detik yang sama, dependency makin tertekan.
    2. Memasang retry di setiap lapisan (service A, B, dan C masing-masing retry 3x) sehingga satu request jadi 27 call di dasar.
    3. Memasang circuit breaker tapi nggak memantau state-nya. Breaker OPEN diam-diam berarti fitur mati tanpa ada yang tahu.
    4. Retry pada operasi tulis non-idempotent.
    5. Retry di call yang sudah ada retry di lapisan bawahnya (library client, message broker).
    6. Dependency in-process atau call yang gagalnya selalu deterministik (validasi).

    Aturan Emas: 
    Retry itu obat yang jadi racun kalau dosisnya nggak dibatasi, jadi selalu pasangkan dengan backoff, batas, dan circuit breaker.

    Terapin: 
    pas review PR yang nambah retry, tanya "kalau dependency-nya down 20 menit, berapa call yang kita kirim ke dia per detik?"
  
  # Idempotency di sisi consumer/producer

  Idempotent artinya: operasi yang dijalankan 1 kali atau N kali menghasilkan efek akhir yang sama.

  GET, DELETE, PUT : idempotence secara desain

  Producer :
  Bikin Idempotency-Key sekali per aksi, kirim ulang key yang sama saat retry
  Key dibuat di awal aksi, bukan di tiap percobaan

  Consumer :
  Simpan key atau message_id yang sudah diproses, cek sebelum eksekusi
  Cek dan simpan harus atomik (unique constraint, bukan if exists lalu insert)

  alur:
  terima request/pesan + key
  -> insert key (unique)  --bentrok--> kembalikan hasil lama
  -> sukses insert -> eksekusi -> simpan hasil

  jangan di lakukan:
  1. Bikin key baru di setiap retry. Hasilnya server menganggap itu request baru, jadi idempotency-nya nol.
  2. Cek duplikat pakai SELECT lalu INSERT tanpa unique constraint. Dua retry bersamaan sama-sama lolos.
  3. Operasi baca, atau operasi yang sudah idempotent secara alami (PUT replace, SET ke nilai tetap).
  4. Efek yang memang aman kalau dobel dan murah, misalnya menulis log atau refresh cache.

  best case:
  1. Operasi tulis yang bisa di-retry dan punya efek nyata: pembayaran, pembuatan order, kirim notifikasi, transfer.

  Aturan Emas: 
  Retry cuma aman buat operasi yang aman diulang, jadi sebelum nambah retry, pastiin operasinya idempotent.

  Terapin: 
  di review PR yang nambah retry ke endpoint tulis, tanya "kalau request ini sampai dua kali, apa yang terjadi pada uang atau datanya?

  # Graceful degradation & fallback strategy

  Graceful degradation: waktu satu bagian sistem gagal, sistem tetap melayani fungsi inti dengan kualitas turun, bukan mati total.

  jangan di lakukan:
  1. Pasang circuit breaker tapi nggak nulis fallback, jadi breaker open berarti HTTP 500 yang lebih cepat.
  2. Fallback ke data stale untuk hal sensitif (harga, saldo, stok) tanpa batas umur dan tanpa tanda. (lebih baik error jujur)
  3. Fallback yang diam-diam menelan error, jadi tim baru tahu setelah customer komplain karena nggak ada metrik atau alert.
  4. Fallback yang manggil dependency lain yang sama rapuhnya.
  5. Operasi finansial atau yang butuh data akurat saat itu juga (harga akhir, saldo, ketersediaan stok kritis): gagal jelas lebih aman.
  6. Dependency yang memang wajib ada supaya fitur masuk akal (misalnya otorisasi): jangan "diloloskan" saat gagal.

  Aturan Emas: 
  Tentukan dari awal bagian mana yang boleh hilang dan bagian mana yang harus gagal jelas, jadi waktu sesuatu mati, customer dapat versi yang lebih sedikit, bukan versi yang menyesatkan.

  Terapin: 
  di setiap panggilan eksternal baru, tulis satu baris "kalau ini mati, user lihat apa?" sebelum PR di-merge.

  # Health check & readiness/liveness probe

  Health check adalah mekanisme untuk mengetahui kondisi aplikasi agar sistem seperti Kubernetes bisa menentukan tindakan yang tepat terhadap aplikasi.

  Startup Probe
  Apakah aplikasi sudah selesai melakukan proses startup?
  - Membaca konfigurasi.
  - Membuat Spring Bean.
  - Menjalankan dependency injection.
  - Membuat koneksi database.
  - Menjalankan proses inisialisasi lainnya.

  Startup Probe memberikan waktu kepada aplikasi untuk menyelesaikan proses startup tanpa dianggap gagal oleh Liveness Probe.

  Liveness Probe
  Apakah aplikasi masih berjalan atau mengalami kondisi macet?
  - Deadlock menyebabkan thread saling menunggu.
  - Aplikasi mengalami kondisi internal yang membuat request tidak bisa diproses.
  - Proses aplikasi mengalami hang.
  
  Liveness Probe membantu Kubernetes mendeteksi kondisi tersebut.

  Readiness Probe
  Apakah aplikasi siap menerima traffic?
  - Aplikasi baru selesai startup.
  - Database utama belum bisa diakses.
  - Koneksi ke dependency inti sedang bermasalah.
  - Aplikasi sedang melakukan proses pemulihan.
  
  Readiness Probe membantu Kubernetes menentukan apakah pod boleh menerima traffic.

  jangan di lakukan:
  1. Satu endpoint /health dipakai untuk liveness dan readiness sekaligus, lengkap dengan cek DB dan semua dependency.
  2. Tidak ada readiness, jadi pod baru dan pod yang lagi warm-up langsung dapat traffic.
  3. Probe tanpa timeout yang jelas, atau terlalu agresif (periode pendek, threshold 1), jadi lonjakan kecil sudah memicu restart.
  4. Jangan taruh pengecekan dependency di liveness.
  5. Script atau batch job sekali jalan nggak butuh readiness; cukup exit code.

  Tes Feynman: 
  Liveness nanya "perlu di-restart nggak?", readiness nanya "boleh dikasih traffic nggak?", dan mencampur keduanya bikin masalah kecil jadi besar.

  Aturan Emas: 
  Pisahkan "perlu diganti" dari "belum siap dipakai", karena menghukum yang sedang sibuk dengan restart cuma memperparah keadaan.

  Terapin: 
  sebelum merge konfigurasi probe baru, tanya "kalau database lambat dua menit, probe ini bakal ngebunuh pod atau cuma menahan traffic?"