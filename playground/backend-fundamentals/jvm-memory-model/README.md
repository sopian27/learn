# JVM Memory Model Demo (Lesson 3.1)

Run: `java JvmMemoryModelDemo.java` (Java 17+).

Yang dicari di output:
- `[2a]` vs `[2b]`: object hidup selama ada strong reference, eligible GC begitu terakhir lepas.
- `[3]`: object lokal eligible begitu method return.
- `[4]`: variabel lokal null tapi object tetap hidup karena static `CACHE` (GC root). Ini bentuk paling sederhana dari memory leak.
- `[6]`: StackOverflowError beda dari OutOfMemoryError: yang habis stack thread, bukan heap.

Catatan: `System.gc()` cuma hint. Jangan dipanggil di production code.
