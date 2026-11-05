# GC Generational Demo (Lesson 3.2)

Alokasi 2 juta objek `Order` (1KB), hampir semuanya mati muda, ±100 bertahan. Lalu print collector aktif dan jumlah/waktu GC.

## Jalankan

```
java -Xmx256m GcGenerationalDemo.java
java -Xmx256m -XX:+UseParallelGC GcGenerationalDemo.java
java -Xmx256m -XX:+UseSerialGC GcGenerationalDemo.java
java -Xmx256m -XX:+UseZGC GcGenerationalDemo.java
```

## Yang diperhatikan

- Nama collector di bagian atas berubah sesuai flag (G1 default di Java 9+).
- G1/Parallel/Serial: hampir semua kerja ada di "Young" (Scavenge/Copy), Old Generation `jumlah=0`. Itu hipotesis generasional bekerja.
- Bandingkan total waktu vs pause: Parallel biasanya paling cepat selesai (throughput), ZGC pause per-event paling kecil (latency) tapi ada overhead.
- Angka bervariasi per mesin. Ini demo, bukan benchmark.
- Di Java 17, ZGC belum generasional. Di Java 21+ pakai `-XX:+UseZGC` (generasional jadi default di 23+).
