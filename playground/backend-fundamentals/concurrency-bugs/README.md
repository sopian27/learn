# Concurrency Bugs demo (Lesson 2.5)

Butuh Java 21+ (lesson ini Java 25). Run: `java ConcurrencyBugsDemo.java`

Lihat:
- `1`: dua transfer silang (A ke B, B ke A) macet. Dua-duanya masih hidup setelah 1 detik.
- `2`: `ThreadMXBean.findDeadlockedThreads()` menemukan dua thread yang saling tunggu. Ini informasi yang sama dengan blok "Found one Java-level deadlock" di `jstack`.
- `3`: fix, urutan lock konsisten. Keduanya selesai.
- `4`: livelock. Dua thread saling mengalah 1000 putaran, tidak ada yang selesai.

Tip: saat demo `1` sedang macet (JVM hidup beberapa detik), jalankan `jcmd <pid> Thread.print` dari terminal lain.

Livelock di demo dibuat serempak dengan `CyclicBarrier` supaya hasilnya pasti. Di production livelock biasanya tidak sepasti ini. Stale read tidak didemokan di sini (lihat lesson 2.1).
