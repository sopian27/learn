# Redis Spring Cache Demo (Lesson 9.3)

Model mini dari `@Cacheable`, `@CacheEvict`, TTL, dan eviction LRU. Tanpa Spring dan tanpa Redis asli.

Run:

```
java RedisSpringCacheDemo.java
```

Yang diperhatikan:
- `@Cacheable`: baca kedua tidak memanggil DB.
- TTL: setelah waktu lewat, entri dianggap tidak ada dan diambil ulang.
- `@CacheEvict`: setelah evict, baca berikutnya miss lagi.
- Eviction: kalau cache penuh, entri paling lama tidak dipakai dibuang.

Batasan: waktu dipalsukan, tidak ada serialisasi, jaringan, atau policy Redis asli (`allkeys-lru` dkk). Bukan kode production.
