# API Versioning Demo (Lesson 5.2)

Jalankan: `java ApiVersioningDemo.java` (port 8090)

Yang diliat:
- `/v1/orders/1`: field `currency` ditambah, klien lama tetap jalan. Ada header `Deprecation`, `Sunset`, `Link` sebagai sinyal migrasi.
- `/broken/orders/1`: `amount` berubah dari angka jadi objek di URL yang sama, klien lama crash.
- `/v2/orders/1`: bentuk baru hidup di versi baru, v1 tetap melayani klien lama.

Klien lama itu sengaja naif (parse manual), mewakili app mobile yang sudah rilis dan nggak bisa dipaksa update.
