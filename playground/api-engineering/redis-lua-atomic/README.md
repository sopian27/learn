# Atomic Limiter Demo (Lesson 2.2)

Run: `java AtomicLimiterDemo.java`

Lihat:
- `naive`: lolos jauh di atas 10 (di run gua semua 50 lolos). Cek `size` dan tulis `addLast` terpisah, jadi banyak thread lolos cek yang sama.
- `atomic`: selalu tepat 10. Cek + tulis dalam satu blok, sama idenya dengan Lua script di Redis.

Simulasi in-memory, bukan Redis asli. Script Lua aslinya ada di komentar `atomic()`.
