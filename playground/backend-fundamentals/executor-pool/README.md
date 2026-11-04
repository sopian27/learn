# Executor pool demo (Lesson 2.2)

Run: `java ExecutorPoolDemo.java`

Lihat:
- `1a` vs `1b`: 40 task I/O-bound (100 ms). Pool 4 thread ~1000 ms, pool 40 thread ~100 ms. Task I/O-bound butuh pool lebih besar dari jumlah core.
- `2a` vs `2b`: `newFixedThreadPool` punya antrean tak terbatas, task numpuk tanpa error (memori naik diam-diam). Queue dibatasi menolak task berlebih (`RejectedExecutionException`) sebagai backpressure.
- `3a` vs `3b`: `synchronizedMap` dengan `get` lalu `put` kehilangan update; `ConcurrentHashMap.merge` selalu 80000.
- `3c`: iterasi sambil mengubah `synchronizedMap` melempar `ConcurrentModificationException`; `ConcurrentHashMap` tidak (isi akhir bisa beda tiap run, iterasinya weakly consistent).
