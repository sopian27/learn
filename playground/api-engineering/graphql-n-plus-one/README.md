# GraphQL N+1 (Lesson 3.3)

Jalankan: `java NPlusOneDemo.java` (Java 17+, tanpa library).

Yang diliat:
- Versi naif: 1 query daftar + 1 query per order = 51 query untuk 50 order.
- Versi batch: 1 query daftar + 1 query `IN (...)` = 2 query, hasil identik.
- Order tanpa item (o-3) tetap dapat list kosong di versi batch.
- Ubah `rangeClosed(1, 50)` ke angka lain: naif ikut naik, batch tetap 2.
