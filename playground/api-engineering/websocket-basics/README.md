# WebSocket Basics (Lesson 4.1)

Jalankan: `java WebSocketBasicsDemo.java` (Java 17+, tanpa library).

Yang diliat:
- Handshake: client kirim `Upgrade: websocket` + `Sec-WebSocket-Key`, server balas `101 Switching Protocols` + `Sec-WebSocket-Accept` (SHA-1 key + GUID, Base64).
- Server mengirim frame lebih dulu (push) tanpa request dari client.
- Frame client ke server `masked=true`, server ke client `masked=false`. Itu aturan RFC 6455.
- Client dan server bertukar frame di satu koneksi TCP yang sama (full-duplex).
- Demo sengaja cuma frame teks pendek (< 126 byte), tanpa ping/pong dan close frame.
