# API as Product Demo (Lesson 14.1)

Run: `java ApiAsProductDemo.java`

Lihat:
- 3 tim (orders, payments, catalog) punya gaya path, error, pagination, id field yang beda-beda.
- Linter konvensi tim ngitung pelanggaran per API. Hanya `orders` yang lolos.
- Konsumen harus nulis 3 adapter. Itu biaya inkonsistensi yang dibayar orang lain.

Simulasi data statis, bukan scan API beneran. Versi nyata: Spectral/OpenAPI linter di CI (Lesson 14.3).
