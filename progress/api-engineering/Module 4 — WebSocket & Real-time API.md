# WebSocket & Real-time API

   # WebSocket protocol dasar

   WebSocket mulai dari HTTP Upgrade (101 Switching Protocols), lalu koneksi TCP yang sama dipakai untuk frame dua arah.

   jangan di lakukan:
   1. Pakai WebSocket untuk data yang cuma butuh sekali ambil. Tanpa push, REST lebih simpel dan bisa di-cache.
   2. Nggak menangani reconnect dan heartbeat, jadi client diam-diam berhenti menerima update tanpa ada yang sadar.
   3. Mengira WebSocket otomatis aman. Origin, auth, dan validasi pesan tetap harus dicek di handshake dan di tiap pesan.
   
   best case:
   server harus mengirim data duluan dengan latensi rendah (notifikasi status, chat, live price, tracking), dan frekuensi pesannya tinggi atau kedua arah sama-sama aktif.

   Tes Feynman: WebSocket mengubah satu koneksi HTTP jadi saluran dua arah yang terus terbuka, sehingga server bisa bicara duluan tanpa ditanya.

   Aturan Emas: Kalau hampir semua jawaban yang lu terima isinya "belum ada yang baru", jangan nanya lebih sering, minta dikabari.

   Terapin: sebelum menaikkan frekuensi polling atau cek manual, tanya "bisa nggak pihak yang punya info ngabarin duluan?" Itu berlaku untuk API, dan untuk koordinasi dengan tim.

   WebSocket bersifat stateful karena koneksi melekat pada pod yang menerima koneksi tersebut.

   Masalah:
   - Client A terhubung ke Pod 1.
   - Client B terhubung ke Pod 2.
   - Pod 1 tidak otomatis mengetahui koneksi Client B.
   Solusi: External Message Broker (Redis/Kafka)

   Client A ── Pod 1 ──┐
                     ├── Redis Pub/Sub
   Client B ── Pod 2 ──┘

   Cara kerja:
   1. Setiap pod menyimpan koneksi WebSocket secara lokal.
   2. Setiap pod subscribe ke message broker.
   3. Pod mengirim pesan melalui broker.
   4. Pod tujuan meneruskan pesan ke client yang terhubung dengannya.

   Ketika scaling:
   - Pod baru menerima koneksi baru.
   - Koneksi lama tetap berada di pod asal.
   - Jika pod mati, client harus reconnect ke pod lain.

   Catatan penting:
   - Connection state → Local memory masing-masing pod.
   - Shared communication → Redis/Kafka.
   - Business data → Database.

   Intinya: Jangan bergantung pada memory satu pod untuk komunikasi antar-instance. Gunakan message broker agar WebSocket bisa horizontal scaling.

   # STOMP over WebSocket dengan Spring

   STOMP adalah protokol pesan berbasis teks di atas WebSocket: SUBSCRIBE, SEND, MESSAGE, DISCONNECT, dengan routing lewat destination.

   jangan di lakukan:
   1. Subscribe tanpa otorisasi: topic per order tanpa cek kepemilikan membuat data bocor ke siapa saja yang bisa nebak id.
   2. Mengira /topic menyimpan pesan. Itu fan-out sesaat, bukan antrean.
   3. Menyimpan state di memori satu instance lalu bingung saat scale-out.
   4. Lupa heartbeat, jadi koneksi mati diam-diam di balik NAT dan baru ketahuan setelah user komplain.

   best case:
   banyak jenis pesan dan banyak topic, banyak client yang subscribe ke subset data berbeda, dan lu mau routing serta subscribe/unsubscribe tanpa bikin protokol sendiri. Cocok dengan ekosistem Spring.

   Tes Feynman: STOMP memberi WebSocket aturan "grup": client masuk ke sebuah topic, server kirim ke topic itu, dan broker yang membagikan ke semua anggota.

   Aturan Emas: Jangan bikin protokolmu sendiri untuk masalah yang sudah punya standar, kecuali lu siap merawat semua bug-nya.

   Terapin: sebelum menulis format pesan, routing, atau retry buatan sendiri, cek dulu apakah standar yang ada (STOMP, SSE, gRPC) sudah menjawabnya.

   - WebSocket → menyediakan koneksi real-time.
   - STOMP → mengatur pengiriman pesan melalui /topic, /queue, /app.
   
   Intinya: WebSocket adalah jalur komunikasinya, STOMP adalah protokol messaging yang berjalan di atasnya. STOMP tidak wajib digunakan bersama WebSocket.

   # Server-Sent Events (SSE) sebagai alternatif lebih ringan

   SSE adalah response HTTP yang terus terbuka (text/event-stream), satu arah dari server ke client, hanya teks.

   jangan di lakukan:
   1. Pakai WebSocket atau STOMP untuk feed satu arah, lalu merawat reconnect dan protokol yang sebenarnya nggak perlu.
   2. Lupa heartbeat. Proxy menutup koneksi sepi, jadi kirim komentar : ping berkala.
   3. Nggak memberi id pada event, sehingga reconnect kehilangan data tanpa ada yang tahu.

   best case:
   server ngabarin, client cuma mendengar. Status order, notifikasi, progress job, live feed, stream hasil LLM. Terutama kalau lu ingin jalan di atas HTTP biasa dan reconnect gratis.

   Tes Feynman: kalau cuma server yang perlu bicara, SSE cukup, dan ia sudah membawa cara nyambung ulang yang rapi.

   Aturan Emas: Pilih alat sekecil kebutuhan, bukan sebesar kemampuan yang kebetulan lu kuasai.

   Terapin: sebelum memasang WebSocket, tulis satu kalimat "siapa yang perlu bicara ke siapa". Kalau jawabannya cuma satu arah, mulai dari SSE.

   # Scaling WebSocket

   WebSocket/SSE itu stateful: koneksi hidup di satu instance, jadi scale-out butuh fan-out lewat broker eksternal (mis. Redis pub/sub atau broker relay).

   Sticky session membantu handshake dan reconnect, tapi bukan pengganti broker, dan membuat beban nggak merata.

   jangan di lakukan:
   1. Mengira simple broker Spring otomatis jalan lintas instance. Ia hanya in-memory per instance, dan masalahnya baru muncul saat scale-out.
   2. Mengandalkan sticky session sebagai satu-satunya solusi. Saat instance mati, client pindah dan state lokal hilang.
   3. Memakai Redis pub/sub seolah antrean yang tahan: pesan saat instance putus nggak bisa diambil ulang.

   Tes Feynman: tiap server cuma kenal koneksinya sendiri, jadi pesan harus lewat saluran bersama yang didengar semua server.

   Aturan Emas: Kalau satu hal cuma hidup di satu tempat, jangan berasumsi yang lain tahu.

   Terapin: waktu menambah instance, tanya "state apa yang cuma ada di memori satu server?" sebelum menaikkan jumlah replika.
