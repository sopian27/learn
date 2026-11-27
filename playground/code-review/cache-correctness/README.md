# Lesson 4.2: Cache correctness

```bash
java CacheDemo.java
```

Yang dilihat (harga asli sudah jadi 200):

- Write path yang lupa evict, dan evict sebelum commit, sama-sama masih nampilin 100.
- Evict sesudah commit nampilin 200.
- 50 request kena miss bareng: 50 DB read tanpa single flight, 1 DB read dengan single flight.

DB dan cache di sini cuma `Map`, dan urutan "concurrent" untuk kasus stale ditulis berurutan supaya hasilnya selalu sama. Single flight-nya hanya berlaku di satu JVM.
