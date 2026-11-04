# Virtual Threads demo (Lesson 2.4)

Butuh Java 21+ (lesson ini Java 25). Run: `java VirtualThreadsDemo.java`

Lihat:
- `1a` vs `1b`: 2000 request I/O-bound @200 ms. Pool 100 platform thread ~4000 ms (antre), virtual thread ~200 ms.
- `2`: 100.000 task nunggu 1 detik bareng, selesai dalam hitungan detik. Jumlah thread bukan lagi batas.
- `3`: virtual thread tetap `Thread` biasa (`isVirtual()` true, daemon, tanpa nama).

Demo pakai `Thread.sleep` sebagai pengganti I/O blocking. Task CPU-bound tidak dapat untung dari virtual thread. Angka waktu tergantung mesin.
