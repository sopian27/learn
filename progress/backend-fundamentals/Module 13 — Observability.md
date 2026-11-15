# Observability

  # Structured logging + correlation ID

  log.info("user " + id) anti-pattern: nggak bisa di-query, nggak ada penghubung antar baris, dan rawan bocor data; ganti dengan log JSON berfield konsisten.

  Correlation ID di MDC menempel ke semua baris satu request dan diteruskan lewat header ke service lain, jadi seluruh jejak muncul dengan satu filter.

  jangan di lakukan:
  1. Konkatenasi string di log, lalu heran kenapa nggak bisa di-query.
  2. Bikin correlation ID tapi lupa MDC.clear(), jadi ID request lama nempel di request baru.
  3. Nge-log objek request penuh (termasuk password atau token) demi "biar lengkap".
  4. Jangan log di dalam loop panas per item; agregasi dulu atau pakai metrics.
  5. Script sekali jalan di lokal nggak butuh JSON; println cukup.

  Aturan Emas: 
  Tulis log buat orang yang jam 2 pagi harus mencari satu request di tengah ribuan, bukan buat diri sendiri yang barusan ngetik.

  Terapin: 
  sebelum merge, tanya "kalau baris log ini muncul sendirian, bisa nggak gua tau request siapa dan lagi ngapain?"

  Tes Feynman: 
  Tulis log sebagai data berfield dengan ID yang sama di tiap baris satu request, supaya mesin bisa mencari dan menghitungnya, bukan manusia menebak

  # Metrics: RED dan USE, Micrometer/Actuator

  Metrics jawab "bagaimana kondisi service secara keseluruhan": murah, cepat di-query, dan jadi dasar alert, beda dari log yang cerita per kejadian.
  
  RED, buat service yang dipanggil (API, consumer): 
  Rate (request per detik), 
  Errors (berapa yang gagal), 
  Duration (berapa lama, dilihat sebagai percentile, bukan rata-rata).

  USE, buat resource/infrastructure (thread pool, connection pool, CPU, queue): 
  Utilization (seberapa sibuk), 
  Saturation (berapa yang antre nunggu), 
  Errors (error dari resource itu).

  jangan di lakukan:
  1. Cuma lihat rata-rata response time, ekor lambatnya nggak pernah kelihatan.
  2. Tag metric berisi ID unik (userId, orderId, URL mentah dengan path variable), bikin cardinality meledak.
  3. Cuma ukur error, tapi nggak ukur saturation, jadi baru sadar setelah sistem sudah jatuh.
  4. Jangan bikin metric per-user atau per-order (pakai log).
  5. Script sekali jalan atau tool internal kecil nggak butuh Prometheus; log cukup.

  Tes Feynman: 
  Hitung seberapa sering sesuatu terjadi, seberapa sering gagal, dan seberapa lama yang paling lambat, supaya tahu service sehat tanpa membaca tiap catatan.

  Aturan Emas: 
  Jangan percaya rata-rata; lihat pelanggan yang paling apes dan antrean yang belum jadi error.

  Terapin: 
  sebelum bilang "performanya bagus" di review atau retro, tunjukkan p99 dan saturation, bukan cuma avg.

  # Distributed tracing dasar: trace ID / span ID

  Distributed tracing menjawab "di service mana request ini lambat" lewat satu trace ID dan pohon span (span ID + parent span).

  Tes Feynman: 
  Beri satu nomor yang ikut satu request ke mana pun dia pergi, supaya lu bisa lihat jalurnya dan titik yang paling lama menahan.

  jangan di lakukan:
  1. Tracing dipasang, tapi trace ID tidak ikut ke log, jadi trace dan log tidak bisa dihubungkan.
  2. Lupa propagasi di async task, thread pool, atau consumer Kafka, sehingga trace putus.
  3. Sampling 100% di production tanpa hitung biayanya.
  4. Mengandalkan trace tanpa metrics: trace menjelaskan satu request, bukan tren keseluruhan.
  5. Aplikasi tunggal kecil tanpa dependency antar service; log + metrics cukup.
  6. Jangan rekam semua trace tanpa sampling kalau trafik tinggi.

  Aturan Emas: 
  Satu request, satu nomor resi yang ikut sampai ujung; kalau nomornya putus di tengah, lu buta di situ.

  Terapin: 
  tiap nambah panggilan keluar, async task, atau consumer baru, cek dulu trace ID ikut terbawa.
 
  # Alerting yang bermakna, anti alert fatigue

  Alert fatigue terjadi saat terlalu banyak alert palsu: orang berhenti percaya, dan alert asli ikut terlewat.

  Alert di gejala yang dirasakan pengguna, dengan threshold plus durasi, dan tiap alert punya runbook; yang nggak urgent jadi ticket.

  jangan di lakukan:
  1. Alert di semua metric yang "kelihatan penting", tanpa tanya apakah butuh tindakan.
  2. Threshold tanpa durasi, jadi lonjakan sesaat langsung nge-page.
  3. Alert tanpa runbook, orang on-call tengah malam harus menebak harus ngapain.
  4. Alert yang sering palsu dibiarkan atau di-mute, bukan diperbaiki atau dihapus.
  5. Jangan page untuk penyebab yang belum tentu berdampak (CPU, memori) atau yang bisa nunggu jam kerja.
  6. Tool internal kecil atau job sekali jalan nggak perlu on-call; cukup notifikasi ke channel.

  Tes Feynman: 
  Bunyikan alarm hanya untuk masalah nyata yang butuh tindakan sekarang, supaya orang tetap percaya sama alarm.

  Aturan Emas: 
  Alarm yang terlalu sering bunyi sama saja dengan alarm yang rusak; page hanya kalau ada manusia yang harus bertindak sekarang.

  Terapin: 
  sebelum bikin alert baru, tulis dulu jawaban "orang on-call ngapain kalau ini bunyi?"; kalau nggak ada jawaban, jadikan dashboard.

  # Debugging insiden production dari log, metric, trace

  Debugging insiden itu urutan: metric (apa dan seberapa besar), trace (di service mana), log yang difilter trace ID (kenapa), tiap langkah mempersempit area cari.

  Mitigasi dulu (rollback, scale), akar masalah dan postmortem tanpa menyalahkan sesudahnya; cek perubahan terakhir sebagai tersangka pertama.

  jangan di lakukan:
  1. Buka semua dashboard dan log sekaligus tanpa urutan, lalu mengejar log yang paling berisik.
  2. Mengira service yang paling banyak error adalah penyebab; sering dia cuma korban dari service di bawahnya.
  3. Ngejar akar masalah sementara pelanggan masih terdampak.
  4. Mengubah banyak hal sekaligus saat mitigasi (restart + scale + rollback), jadi nggak tahu mana yang berhasil.
  5. Selesai insiden tanpa catatan: kejadian yang sama terulang bulan depan.
  6. Jangan mulai dari grep log kalau metric dan trace sudah bisa mempersempit dulu.
  7. Jangan diagnosis panjang kalau perubahan terakhir jelas penyebabnya dan rollback aman; rollback dulu.

  Tes Feynman: 
  Persempit masalah dari yang luas ke yang spesifik, selamatkan pengguna dulu, baru cari kenapa terjadi.

  Aturan Emas: 
  Saat ada masalah, persempit dari yang luas ke yang sempit dan selamatkan dulu orang yang terdampak sebelum mencari siapa atau apa penyebabnya.

  Terapin: 
  saat on-call, tulis dulu tiga baris di channel: apa dampaknya (metric), di mana (trace), dan langkah mitigasi yang dicoba; akar masalah menyusul.

