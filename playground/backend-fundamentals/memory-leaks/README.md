# Memory Leak Demo (Lesson 3.3)

Tiga leak klasik versi mini: static collection, listener yang tidak di-unregister, `ThreadLocal` di thread pool. Tiap skenario ada versi bocor lalu versi fix, heap diukur setelah `System.gc()`.

## Jalankan

```
java -Xmx256m MemoryLeakDemo.java
```

Java 17+. Hasil di Java 17 (angka kira-kira, beda per mesin):

| Skenario | Bocor | Fix |
|---|---|---|
| static cache | ~25 MB | ~3 MB (bounded 100 entry) |
| listener | ~23 MB | ~3 MB (unregister) |
| ThreadLocal | ~39 MB | ~3 MB (`remove()` di `finally`) |

## Yang diperhatikan

- Heap setelah GC tetap tinggi di versi bocor: objek masih punya jalur dari GC root (static field, thread hidup), jadi GC tidak boleh ambil.
- ThreadLocal: request sudah selesai tapi thread pool masih hidup, value masih nempel di thread.
- Ini demo, bukan benchmark. `System.gc()` cuma hint, jangan dipakai di production.
