# Response Compression & Payload Minimization (Lesson 12.4)

Jalankan: `java CompressionDemo.java`

Yang diamati:
1. JSON besar & berulang: gzip hemat sangat banyak.
2. Payload kecil: hemat sedikit, CPU tetap kepakai (di server biasanya ada ambang minimum).
3. Data yang sudah terkompres: gzip nyaris tidak menolong.
4. Buang field yang tidak dipakai klien: raw mengecil duluan, gzip menyusul.

Di Spring Boot: `server.compression.enabled=true`, `server.compression.min-response-size=1024`,
`server.compression.mime-types=application/json`. Demo ini hanya mengukur ukuran dengan `GZIPOutputStream`, bukan server sungguhan.
