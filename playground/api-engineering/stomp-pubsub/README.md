# STOMP Pub/Sub Demo

Companion Lesson 4.2. Broker STOMP mini di memori (tanpa socket, tanpa Spring) untuk lihat bentuk frame dan pub/sub topic.

Run: `java StompPubSubDemo.java`

Perhatikan:
- Frame STOMP itu teks: command, header, baris kosong, body, lalu `^@` (null byte).
- Hanya subscriber topic yang sama menerima pesan (fan-out).
- Setelah `DISCONNECT`, session dibuang dari broker. Tanpa itu, subscription numpuk (leak).
- Publish ke topic tanpa subscriber tidak error, pesannya hilang. Simple broker tidak menyimpan pesan.

Batas: bukan implementasi STOMP penuh (tanpa CONNECT/CONNECTED, ACK, heartbeat, receipt).
