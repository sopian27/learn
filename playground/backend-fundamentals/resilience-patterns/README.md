# Resilience Patterns Demo (Lesson 12.2)

Companion demo untuk lesson 12.2. Versi mini retry, circuit breaker, dan bulkhead tanpa dependency, supaya mekanismenya kelihatan. Di production pakai Resilience4j.

## Run

```
java ResiliencePatternsDemo.java
```

## Yang diperhatikan

1. Retry: 1 request jadi 3 call ke gateway yang down. Kalikan dengan jumlah request.
2. Circuit breaker: setelah 3 gagal, state OPEN dan request berikutnya ditolak tanpa nyentuh gateway. Setelah jeda, HALF_OPEN lalu CLOSED saat gateway pulih.
3. Bulkhead: dari 5 request bareng, hanya 2 yang jalan, sisanya ditolak cepat.
