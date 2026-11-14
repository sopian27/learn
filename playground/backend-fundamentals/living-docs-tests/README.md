# Test sebagai Dokumentasi Hidup Demo (Lesson 10.5)

Demo kecil: satu bug yang sama, dua gaya test.

## Jalanin

```bash
java LivingDocsTestsDemo.java
```

## Output asli

```text
GAGAL  test1
       -> expected true but was false
GAGAL  member_gold_dapat_diskon_10_persen
       -> tier=gold, harga=100000: expected 90000 tapi dapat 100000
```

## Yang diliat

- `test1` gagal tapi lu nggak tahu apa yang rusak, harus buka kode.
- Test kedua: nama sudah nyebut aturan bisnisnya, pesan gagal nyebut input, expected, dan actual.
- Struktur Given-When-Then bikin test bisa dibaca seperti spesifikasi.

## Catatan

Ini simulasi tanpa JUnit: assert ditulis manual dan runner-nya `jalanin(...)`. Di JUnit 5 padanannya `@DisplayName` atau nama method deskriptif, plus `assertEquals(expected, actual, "pesan")`. Sintaks itu ditulis dari ingatan, tidak dijalankan di demo ini.
