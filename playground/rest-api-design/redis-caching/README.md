# Redis Caching Demo (Lesson 12.2)

Run: `java RedisCachingDemo.java`

Lihat:
- Cache-aside: 3x baca, DB cuma kena 1x (MISS lalu HIT).
- Update tanpa invalidasi: klien dapat data basi sampai TTL habis.
- Update + hapus key: baca berikutnya langsung segar.
- TTL sebagai jaring pengaman kalau invalidasi terlewat.

Simulasi in-memory; di Spring pakai `@Cacheable` / `@CacheEvict` atau `RedisTemplate`.
