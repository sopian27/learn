# JWT Authentication Demo (Lesson 9.1)

Run: `java JwtAuthenticationDemo.java`

Look for: validator naive menerima token expired dan token `alg=none` (admin palsu). Validator strict menolak keduanya. Secret `"secret"` ketemu lewat tebakan kamus kecil, jadi penyerang bisa tanda tangan token sendiri. Di kerjaan jangan tulis validator sendiri, pakai Spring Security Resource Server.
