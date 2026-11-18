# Global Exception Handler Demo (Lesson 6.3)

Run (Java 17+, tanpa dependency):

```bash
java GlobalExceptionHandlerDemo.java
```

Yang diliat:

* Controller cuma melempar exception; tidak ada `try/catch` per endpoint. Pemetaan ke status ada di satu tabel `HANDLERS` (di Spring: `@ControllerAdvice`).
* `OrderGoneException` (turunan `OrderNotFoundException`) dapat 410, bukan 404: handler paling spesifik menang.
* Exception tak dikenal ("bug") jatuh ke fallback 500: pesan internal tidak keluar, cuma masuk log server.
