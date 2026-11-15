# Structured Logging Demo (Lesson 13.1)

Companion demo untuk lesson 13.1. Simulasi in-memory (tanpa Logback/SLF4J): log kalimat bebas vs log JSON dengan correlation ID, dari dua request yang jalan bersamaan.

## Run

```
java StructuredLoggingDemo.java
```

## Yang diperhatikan

1. Log biasa dari dua request tercampur dan nggak ada penanda milik siapa; lu harus nebak dari isi kalimat.
2. Log JSON punya field `correlationId`; satu filter langsung nampilin semua jejak satu request.
3. Karena field-nya terstruktur, "berapa payment gagal?" jadi query, bukan baca log satu-satu.
4. `correlationId.remove()` di `finally`: tanpa itu, thread pool membawa ID request lama ke request berikutnya.
