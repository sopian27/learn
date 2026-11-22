# Idempotency-Key Demo (Lesson 13.1)

Run: `java IdempotencyKeyDemo.java`

Lihat:
- Tanpa key: retry setelah timeout bikin 2 order.
- Dengan key: retry di-replay (201 + body sama), order tetap 1.
- Key sama + body beda: 422.
- Retry saat request pertama masih jalan: 409.

Simulasi in-memory. Produksi: tabel `idempotency_key` dengan `UNIQUE(key)` + TTL, dan claim key satu transaksi dengan insert order.
