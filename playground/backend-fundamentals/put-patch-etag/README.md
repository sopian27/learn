# PUT vs PATCH + ETag Demo (Lesson 5.5)

Jalankan: `java PutPatchEtagDemo.java` (port 8093)

Yang diliat, 3 skenario edit produk yang sama oleh dua orang:
1. `PUT` tanpa `If-Match`: edit kedua menimpa harga edit pertama (lost update, diam-diam).
2. `PUT` + `If-Match`: edit kedua dapat 412, harus `GET` ulang dulu.
3. `PATCH`: cuma field yang dikirim berubah, jadi dua edit di field beda nggak saling timpa.

Server in-memory satu produk, regex ala kadarnya. Di Spring Boot: `ETag` dari kolom `@Version` JPA atau hash body, cek `If-Match` di controller/filter.
