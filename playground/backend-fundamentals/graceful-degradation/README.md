# Graceful Degradation Demo (Lesson 12.4)

Companion demo untuk lesson 12.4. Simulasi in-memory tanpa dependency: dependency tambahan mati, halaman tetap tampil dengan data stale yang ditandai.

## Run

```
java GracefulDegradationDemo.java
```

## Yang diperhatikan

1. Tanpa fallback: `recommendation` mati, seluruh halaman error.
2. Dengan fallback: halaman tampil dengan rekomendasi dari cache, ditandai `degraded`, dan penggunaan fallback dihitung.
3. Harga (dependency inti) mati: tetap error jelas, tidak ada fallback ke harga stale.
