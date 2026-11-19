# Rate Limiting Demo (Lesson 9.4)

Run: `java RateLimitingDemo.java`

Lihat:
- Tanpa limit: 100 request lolos semua.
- Token bucket (burst 5, refill 1/detik): 5 lolos, 95 ditolak dengan `Retry-After`.
- Client lain (yuni) punya bucket sendiri, tidak ikut kena.
- Setelah menunggu `Retry-After`, request lolos lagi.

Simulasi in-memory; di produksi state bucket ada di Redis (lihat lesson).
