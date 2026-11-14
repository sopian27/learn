# OWASP Demo (Lesson 11.4)

Tiga celah OWASP yang sering muncul di Spring Boot, masing-masing bug lalu fix: injection, broken access control (IDOR), mass assignment.

## Jalanin

```bash
java OwaspDemo.java
```

## Output asli

```text
--- 1. Injection ---
bug : SELECT * FROM users WHERE name = 'x' OR '1'='1'
fix : SELECT * FROM users WHERE name = ?   params=[x' OR '1'='1]
--- 2. Broken access control (budi buka order milik yuni) ---
bug : Order[id=2, owner=yuni, total=999]
fix : null   (order budi sendiri: Order[id=1, owner=budi, total=100])
--- 3. Mass assignment (register dengan role=ADMIN) ---
bug : User[name=budi, role=ADMIN]
fix : User[name=budi, role=USER]
```

## Yang diliat

- Bug 1: input user ikut jadi bagian SQL, kondisi `OR '1'='1'` bikin semua baris lolos. Fix: query tetap, nilai dikirim terpisah sebagai parameter.
- Bug 2: user login bisa buka order siapa pun cuma dengan ganti id. Fix: cek kepemilikan di server tiap akses.
- Bug 3: client ikut ngirim `role=ADMIN` dan diterima. Fix: hanya field yang diizinkan yang di-bind, role ditentukan server.
- `main` melempar `AssertionError` kalau salah satu fix nggak bekerja.

## Catatan

Simulasi tanpa DB dan tanpa Spring. Di Spring Boot nyata: parameter binding lewat Spring Data JPA (`@Query` dengan `:param`) atau `PreparedStatement`, cek kepemilikan lewat `@PreAuthorize` atau filter query per user, dan DTO terpisah dari entity untuk request body.
