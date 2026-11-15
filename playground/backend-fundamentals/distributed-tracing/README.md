# Distributed Tracing Demo (Lesson 13.3)

Companion demo untuk lesson 13.3. Simulasi in-memory (tanpa OpenTelemetry/Micrometer Tracing): 3 service dalam satu proses, `trace-id` dan `parent-span-id` dikirim lewat Map yang berperan sebagai header.

## Run

```
java TracingDemo.java
```

## Yang diperhatikan

1. Tanpa tracing: tiap service cuma melaporkan durasinya sendiri, tidak kelihatan siapa memanggil siapa.
2. Dengan tracing: semua span berbagi satu `trace-id`, dan `parent-span-id` menyusun pohon panggilan.
3. Span daun paling lambat langsung menunjuk biang masalah (payment-service).
4. Kalau satu service lupa meneruskan header, pohonnya putus di situ.
