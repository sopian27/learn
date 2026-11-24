# SSE resume demo

Run: `java SseResumeDemo.java`

Lihat:
- Format event: `id`, `event`, `data`, diakhiri baris kosong.
- Setelah koneksi putus, reconnect dengan `Last-Event-ID: 2` cuma menerima id 3 dan 4.
- Bandingkan dengan `../stomp-pubsub`: di sana pesan yang lewat saat client absen hilang.

Batas: tanpa socket, tanpa `retry:`, tanpa heartbeat comment (`: ping`). Log event tumbuh tanpa batas (di produksi dipotong/TTL).
