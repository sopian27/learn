# Circular Dependency Demo (Lesson 4.5)

Dua bean yang saling minta lewat constructor tidak bisa dibuat. Ditulis tanpa Spring, pakai "container" mini yang mencatat bean yang sedang dibuat.

## Jalankan

```
java CircularDependencyDemo.java
```

## Yang diperhatikan

- Versi pertama gagal dengan `BeanCurrentlyInCreation: orderService`. Ini mirip `BeanCurrentlyInCreationException` di Spring.
- Versi kedua jalan karena arah dependency jadi satu arah: dua service sama-sama bergantung ke `PaymentStatusReader`.
- Demo tidak menunjukkan setter injection atau `@Lazy`; early reference dan lazy proxy Spring tidak disimulasikan.
- Ini simulasi, bukan Spring sebenarnya.
