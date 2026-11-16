# HTTP Anatomy Demo (Lesson 1.1)

Run: `java HttpAnatomyDemo.java` (JDK 11+; tanpa dependency)

Yang diliat:
- Request cuma teks: start line, header, baris kosong, (body).
- Response: status line, header, baris kosong, body JSON.
- `\r\n` ditampilin eksplisit supaya kelihatan format kawatnya.
- Tidak ada library REST: "REST" itu cara pakai teks ini, bukan protokol lain.
