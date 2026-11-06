# JVM Flags Demo (Lesson 3.5)

Satu program kecil yang mencetak konfigurasi JVM yang benar-benar aktif: max heap, GC, jumlah CPU, dan flag dari command line. Jalankan beberapa kali dengan flag beda, bandingkan.

## Jalankan

```
java FlagsDemo.java
java -Xmx128m -Xms128m FlagsDemo.java
java -XX:+UseSerialGC -Xmx128m FlagsDemo.java
java -XX:+UseZGC -Xmx512m FlagsDemo.java
java -XX:ActiveProcessorCount=2 FlagsDemo.java
```

Java 17+ (`java File.java` menjalankan sebagai source-file mode; flag JVM tetap berlaku).

## Yang diperhatikan

- Tanpa flag: ukuran heap = ~1/4 RAM mesin, GC = G1 (kalau CPU >= 2 dan RAM >= 1792 MB), kalau tidak Serial. Itu "ergonomics".
- `-Xmx` mengubah `Max heap`; `-Xms` mengubah `Heap sekarang` di awal.
- Heap kecil: GC jalan lebih sering untuk 200 MB sampah yang sama.
- `ActiveProcessorCount` meniru container dengan CPU limit.
- Ini demo, bukan panduan tuning production.
