# API Review Checklist Demo (Lesson 14.2)

Run: `java ApiReviewChecklistDemo.java`

Lihat:
- 3 endpoint dalam satu PR dicek pakai checklist dengan level MUST/SHOULD.
- `POST /v1/orders/create` kena banyak temuan MUST, `POST /v1/orders` yang sudah benar lolos.
- Perhatikan: SHOULD tidak memblok merge, MUST memblok.

Simulasi data statis, bukan baca OpenAPI beneran. Versi nyata: Spectral di CI (Lesson 14.3).
