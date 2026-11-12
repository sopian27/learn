# Cache Patterns Demo (Lesson 9.2)

Satu operasi (update harga lalu baca lagi) lewat tiga pola: cache-aside, write-through, write-behind.

Run:

```
java CachePatternsDemo.java
```

Yang diperhatikan:
- cache-aside: setelah update, entri cache dibuang, baca berikutnya ambil nilai baru dari DB.
- write-through: cache dan DB sama setelah tulis.
- write-behind: DB ketinggalan sampai `flush()`; kalau "crash" sebelum flush, update hilang.

Batasan: model sederhana, tanpa TTL, eviction, concurrency, atau retry flush. Bukan kode production.
