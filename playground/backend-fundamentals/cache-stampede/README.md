# Cache Stampede Demo (Lesson 9.4)

20 request bersamaan menemukan entri cache kadaluarsa. Dibandingkan: tanpa proteksi vs lock per key (single-flight), plus TTL seragam vs TTL dengan jitter.

Run:

```
java CacheStampedeDemo.java
```

Yang diperhatikan:
- Tanpa proteksi: DB dipanggil sebanyak jumlah request (stampede).
- Single-flight: DB dipanggil 1x; yang lain menunggu lalu baca cache.
- TTL sama: semua key kadaluarsa serentak. Jitter menyebarkan waktunya.

Batasan: lock `synchronized` hanya berlaku dalam satu JVM; di banyak instance perlu distributed lock atau early refresh. Bukan kode production.
