# Rate Limit Algorithms Demo (Lesson 2.1)

Run: `java RateLimitAlgorithmsDemo.java`

Lihat:
- `fixed`: 10 request lolos dalam ~1 detik (5 di ujung jendela lama + 5 di awal jendela baru). Itu boundary burst, 2x limit.
- `slideL`: cuma 5 lolos, akurat tapi nyimpen timestamp tiap request.
- `tokenB` / `leakyB`: burst awal lolos sampai kapasitas, sisanya ditolak sampai bucket keisi/bocor.

Simulasi waktu (detik), bukan Redis. Versi Redis atomic ada di Lesson 2.2.
