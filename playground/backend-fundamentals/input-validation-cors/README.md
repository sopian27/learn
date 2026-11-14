# Input Validation & CORS Demo (Lesson 11.5)

Tiga lapisan pertahanan: validasi input (allowlist), escape output, dan CORS origin allowlist. Masing-masing bug lalu fix.

## Jalanin

```bash
java InputCorsDemo.java
```

## Output asli

```text
--- 1. Validasi username ---
budi_01  bug=true  fix=true
ab  bug=true  fix=false
budi; DROP TABLE users  bug=true  fix=false
aaaaaaaaaaaaaaaaaaaaaa...  bug=true  fix=false
--- 2. Escape output ---
bug : <p><script>alert(1)</script></p>
fix : <p>&lt;script&gt;alert(1)&lt;/script&gt;</p>
--- 3. CORS origin ---
https://app.example.com  bug=true  fix=true
https://app.example.com.evil.io  bug=true  fix=false
https://evil.io  bug=false  fix=false
```

## Yang diliat

- Validasi: terima hanya yang jelas benar (huruf kecil, angka, underscore, 3-20 karakter). Input aneh ditolak, bukan "dibersihkan".
- Escape: `<script>` jadi teks biasa di HTML. Ini lapisan terakhir, dipasang saat output.
- CORS: cek `contains("example.com")` meloloskan `app.example.com.evil.io`. Fix: cocokkan persis dengan daftar origin.
- `main` melempar `AssertionError` kalau salah satu fix nggak bekerja.

## Catatan

Simulasi tanpa Spring. Di Spring Boot nyata: Bean Validation (`@Valid`, `@Size`, `@Pattern`) di DTO, template engine yang auto-escape (mis. Thymeleaf), dan `CorsConfigurationSource` dengan `setAllowedOrigins(List.of("https://app.example.com"))`. Validasi bukan pengganti parameter binding (11.4).
