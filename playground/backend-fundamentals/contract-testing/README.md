# Contract Testing Demo (Lesson 10.4)

Demo kecil buat lihat ide contract testing tanpa library.

## Jalanin

```bash
java ContractTestingDemo.java
```

## Output asli

```text
provider v1 (awal)                : HIJAU
provider v2 (rename fullName)     : MERAH (field 'fullName' hilang)
provider v3 (tambah avatarUrl)    : HIJAU
```

## Yang diliat

- Kontrak ditulis dari sisi consumer: cuma field yang dia pakai (`id`, `fullName`, `email`).
- Rename field = merah, walau test unit provider sendiri tetap hijau.
- Nambah field baru = tetap hijau (perubahan aditif aman).

## Catatan

Ini simulasi sederhana: kontrak ditulis manual dan response dibuat di memori. Tool asli (Pact, Spring Cloud Contract) menyimpan kontrak sebagai file, memverifikasi lewat HTTP asli, dan bisa dibagi antar repo/tim. Library itu tidak dipasang di demo ini.
