# Error Handling & Problem Details

   # Kenapa stack trace tidak boleh bocor ke klien

   Stack trace dan pesan exception mentah membocorkan host, versi library, dan struktur internal; gratis buat penyerang, tidak berguna buat konsumen.

   Aturan jari: 4xx = klien salah, jelaskan cara memperbaikinya. 5xx = kita yang salah, minta maaf secukupnya, simpan detailnya buat kita sendiri.

   jangan di lakukan:
   1. Membiarkan default error handler jalan tanpa mengecek isinya di environment production (cek lewat curl, bukan asumsi).
   2. Mengembalikan e.getMessage() ke klien "biar jelas". Pesan exception bukan kontrak.
   3. Menyembunyikan trace di response tapi lupa menulisnya ke log, jadi saat error terjadi tidak ada yang bisa dilacak.
   4. tidak ada alasan membocorkan trace di environment non-lokal. Di mesin lokal boleh, tapi jangan jadikan itu perilaku yang dibawa ke deploy.

   Tes Feynman: Ceritakan detail kegagalan ke log kita sendiri, dan ke pengguna cukup kasih tahu apa yang bisa mereka lakukan plus satu nomor tiket.

   Aturan Emas: Detail error itu milik kita, bukan milik penerima response.

   Terapin: Sebelum merge handler error, curl endpoint-nya dengan input rusak dan baca body response seolah lu penyerang; kalau ada satu hal internal, perbaiki dulu.

   # RFC 9457 Problem Details for HTTP APIs

   Tes Feynman: Error API lu harus punya satu bentuk yang sama dan dikenal semua orang, supaya penerima tidak perlu belajar format baru tiap kali.

   Aturan Emas: Format error itu kontrak, bukan kebetulan.
   
   Terapin: Waktu bikin endpoint baru, jangan mengarang JSON error; pakai Problem Details dan tentukan dulu type-nya sebelum nulis handler.

   # Global exception handling

   @RestControllerAdvice + @ExceptionHandler memusatkan pemetaan exception ke ProblemDetail, jadi controller cukup melempar exception.

   @RestControllerAdvice = @ControllerAdvice + @ResponseBody. Kelas ini berlaku untuk semua controller (bisa dibatasi dengan basePackages atau assignableTypes).

   @ExceptionHandler menyatakan exception mana yang ditangani method itu.

   Exception domain (OrderNotFoundException) sebaiknya tidak tahu apa-apa soal HTTP. Layer service melempar kejadian bisnis; advice yang menerjemahkan ke status. Dengan begitu service tetap bisa dipakai dari Kafka consumer atau job, bukan cuma dari REST.

   Fallback Exception.class wajib ada. Isinya: log lengkap di server, balasan generik ke klien.

   Urutan: handler spesifik tidak perlu diurutkan manual, Spring yang memilih. Yang perlu dijaga adalah jangan ada dua handler untuk exception yang sama di advice yang sama.

   jangan di lakukan:
   1. Handler Exception.class yang mengisi detail dengan e.getMessage()
   2. Semua exception bisnis dipetakan ke status code yang sama. Klien tidak bisa memebedakan input salah, state bentrok, dll
   3. Menelan exception tanpa log di handler 500. Klien dapat balasan rapi, tim tidak pernah tahu ada bug.
   4. Exception domain membawa HttpStatus, sehingga service ikut terikat ke web layer.
   5. untuk satu kasus yang benar-benar unik di satu endpoint, handler lokal (@ExceptionHandler di dalam controller itu) lebih jujur daripada memaksa masuk advice global.

   Tes Feynman: Controller dan service cukup melaporkan apa yang salah; satu penanggung jawab di depan yang menentukan apa yang dilihat klien.

   Aturan Emas: Satu masalah, satu penerjemah.
   
   Terapin: Waktu lu nemu dua tempat yang menerjemahkan error yang sama, gabungkan ke satu tempat sebelum nambah kasus baru.

   # Error contract sesuai `standards/API_STANDARDS.md`

   traceId adalah jembatan antara klien dan log. Ambil dari trace yang sudah ada (header traceparent atau MDC dari tracing), jangan bikin ID baru di handler kalau sudah ada. Taruh di body dan log di sisi server dengan nilai yang sama.

   details opsional. Isi untuk validasi (daftar field), kosongkan untuk 404 atau 500. Jangan pernah isi dengan stack trace atau pesan exception.

   timestamp dalam UTC, supaya bisa dicocokkan lintas service dan zona waktu.

   jangan di lakukan:
   1. traceId ada di response tapi tidak pernah masuk log, jadi tidak ada yang bisa dicari.
   2. code berubah-ubah antar versi, sehingga klien yang meng-switch rusak diam-diam.
   3. details berisi pesan exception mentah atau nama kelas internal. Kebocoran 6.1 lewat pintu baru.
   4. Field kontrak terisi di sebagian handler dan kosong di handler lain, jadi klien harus cek null di mana-mana.
   5. timestamp pakai zona waktu server tanpa penanda zona.
   6. API internal sementara (prototipe, script sekali pakai) boleh lebih ringan, tapi tetap isi status dan traceId karena murah.

   Tes Feynman: Setiap error harus membawa nomor resi (traceId) dan identitas jelas (code), supaya penerimanya bisa bertindak dan timnya bisa melacak tanpa membocorkan isi dapur.

   Aturan Emas: Error yang tidak bisa dilacak itu sama dengan error yang tidak pernah dilaporkan.

   Terapin: Sebelum merilis endpoint, pastikan satu nilai di response error bisa langsung dicari di log.

   # Membedakan error klien (4xx, actionable) vs error server (5xx, harus di-log & dialert, bukan cuma ditampilkan ke user)

   Garis 4xx vs 5xx: siapa yang bisa memperbaiki. 4xx = klien, kasih petunjuk spesifik; 5xx = server, pesan generik + `traceId`.

   jangan di lakukan:
   1. 5xx yang membawa pesan exception mentah ke klien.
   2. Alert dimatikan setelah "terlalu berisik", lalu 5xx sungguhan tidak ketahuan.
   3. 500 dipakai untuk kegagalan validasi atau resource tidak ada, karena handler fallback menangkap semuanya.
   4. 400 tanpa petunjuk ("Bad request"), jadi klien menebak.
   
   Tes Feynman: Kalau pengirim bisa memperbaikinya, kasih petunjuk dan tidak usah alarm; kalau hanya kita yang bisa, minta maaf secukupnya ke pengirim dan bunyikan alarm ke tim.

    Aturan Emas: Alarm yang berbunyi untuk semua hal akhirnya tidak didengar untuk hal yang penting.

   Terapin: Sebelum memasang alert, tulis dulu siapa yang bisa memperbaiki kejadian itu, lalu alert hanya yang jawabannya "kita".