# Security Filter Chain Demo (Lesson 11.1)

Versi mini filter chain ala Spring Security: Authentication filter lalu Authorization filter, dirangkai pakai Chain of Responsibility.

## Jalanin

```bash
java FilterChainDemo.java
```

## Output asli

```text
anonim -> /admin                  : 401 belum login / token salah
budi (USER) -> /admin             : 403 login sih, tapi bukan admin
yuni (ADMIN) -> /admin            : 200 data rahasia untuk yuni
--- bug: lupa authorization ---
budi (USER) -> /admin             : 200 data rahasia untuk budi
--- bug: urutan kebalik ---
yuni (ADMIN) -> /admin            : 403 login sih, tapi bukan admin
```

## Yang diliat

- 401 = authentication gagal (nggak tahu lu siapa), 403 = authorization gagal (tahu lu siapa, tapi nggak boleh).
- Lupa filter authorization = user biasa tembus /admin (broken access control).
- Urutan kebalik = admin sah ikut ditolak, karena identitas belum ke-set saat izin dicek.

## Catatan

Simulasi: token dicek dari Map di memori. Spring Security asli punya `SecurityFilterChain`, `SecurityContextHolder`, `AuthenticationManager`, dan banyak filter lain (CSRF, CORS, session, dll).
