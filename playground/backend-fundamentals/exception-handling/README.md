# Exception handling demo (Lesson 1.3)

Run: `java ExceptionHandlingDemo.java`

Lihat:
- `payBroken = PAID` padahal charge gagal: `catch (Exception e) {}` nelen error.
- `lost cause = ... cause = null`: wrap tanpa cause, penyebab asli hilang. Versi fixed nampilin `IOException` di cause.
- `try-with-res`: exception utama tetap yang keluar, error dari `close()` masuk `suppressed`.
