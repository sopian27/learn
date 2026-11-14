# JWT Validation Demo (Lesson 11.2)

JWT HS256 mini tanpa library: bandingin verifier ceroboh (cuma baca isi token) dengan verifier ketat (algoritma, signature, issuer, audience, expiry).

## Jalanin

```bash
java JwtValidationDemo.java
```

## Output asli

```text
token sah                      ceroboh: LOLOS
                               bener  : OK  {"sub":"budi","role":"USER","iss":"auth.toko","aud":"order-api","exp":1900}
payload diubah (USER->ADMIN)   ceroboh: LOLOS
                               bener  : TOLAK signature salah
sudah expired                  ceroboh: LOLOS
                               bener  : TOLAK expired
alg=none tanpa signature       ceroboh: LOLOS
                               bener  : TOLAK algoritma bukan HS256
audience salah                 ceroboh: LOLOS
                               bener  : TOLAK audience salah
```

## Yang diliat

- JWT itu cuma Base64, bukan enkripsi: siapa pun bisa baca dan ngubah payload. Yang bikin aman cuma signature.
- Verifier ceroboh meloloskan semua kasus, termasuk USER yang ngaku ADMIN.
- Algoritma harus dikunci di sisi server, jangan percaya header `alg` dari token.

## Catatan

Simulasi: waktu `now` di-hardcode dan claim dicek pakai string match. Produksi pakai library (Spring Security Resource Server / Nimbus) yang parsing JSON betulan, dan secret datang dari env/secret manager.
