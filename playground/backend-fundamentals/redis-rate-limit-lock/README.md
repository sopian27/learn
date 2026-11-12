# Redis Rate Limit & Lock Demo (Lesson 9.5)

Model mini `INCR + EXPIRE` (rate limit fixed window) dan `SET NX` dengan TTL (lock). Tanpa Redis asli, waktu dipalsukan.

Run:

```
java RateLimitLockDemo.java
```

Yang diperhatikan:
- Rate limit: 5 request, hanya 3 lolos.
- Perbatasan window: 3 di detik 9 + 3 di detik 10 = 6 lolos dalam 2 detik.
- Lock tanpa owner check: TTL habis, DEL buta menghapus lock milik orang lain.
- Dengan owner token: pelepasan lock milik orang lain ditolak.

Batasan: Redis asli butuh Lua script agar cek-owner-lalu-hapus atomik; lock tunggal tidak aman penuh saat failover. Bukan kode production.
