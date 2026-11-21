# HTTP Caching Demo (Lesson 12.1)

Run: `java HttpCachingDemo.java`

Lihat:
- Tanpa conditional request: 3x GET = 3x body penuh.
- Dengan `ETag` + `If-None-Match`: GET pertama 200, sisanya 304 tanpa body (byte turun drastis).
- Data berubah: ETag lama tidak cocok, server balas 200 + ETag baru.
- `max-age`: selama fresh klien nol request, tapi bisa lihat data basi.

Simulasi in-memory; di Spring pakai `ShallowEtagHeaderFilter` atau `ResponseEntity.eTag(...)`.
