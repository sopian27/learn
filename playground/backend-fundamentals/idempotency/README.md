# Idempotency Demo (Lesson 12.3)

Companion demo untuk lesson 12.3. Versi mini idempotency key dan consumer dedup tanpa dependency. Di production key disimpan di DB dengan unique constraint, bukan di memory.

## Run

```
java IdempotencyDemo.java
```

## Yang diperhatikan

1. Retry tanpa idempotency: response hilang, client retry, uang ditarik 2x.
2. Idempotency key: dua request dengan key sama, uang ditarik 1x dan hasilnya sama.
3. Consumer dedup: pesan yang sama datang 3x, efeknya (email) cuma 1x.
