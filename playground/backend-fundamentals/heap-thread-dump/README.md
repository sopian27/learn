# Heap Dump & Thread Dump Demo (Lesson 3.4)

Satu proses dengan dua masalah: `ORDER_CACHE` penuh (leak) dan dua thread `payment-worker-*` yang deadlock. Demo ambil thread dump dan heap dump dari dalam proses, lalu nunggu 60 detik supaya lu bisa pakai `jcmd` dari terminal lain.

## Jalankan

```
java -Xmx256m DumpDemo.java
```

Java 17+. Output pertama: dua thread `BLOCKED`, masing-masing nunggu lock yang dipegang lawannya. File `.hprof` (~60 MB) ditulis ke folder temp dan dihapus saat demo selesai normal (kalau lu Ctrl+C, hapus manual).

## Yang diperhatikan

- Thread dump: cari `BLOCKED` + `waiting to lock`, dan teks `Found one Java-level deadlock`.
- Heap dump: buka di VisualVM atau Eclipse MAT, cari `byte[]` / `Order` dengan retained size terbesar, lalu telusuri "path to GC roots" ke `DumpDemo.ORDER_CACHE`.
- `GC.class_histogram` cukup buat tebakan cepat tanpa file besar.
- Ini demo, bukan prosedur production. Heap dump mem-pause JVM dan ukurannya sebesar heap.
