# Measure First Demo (Lesson 14.1)

Run: `java MeasureFirstDemo.java`

Yang diliat: timer per tahap (query / format / serialize) nunjukin tahap mana yang dominan.
Optimasi `String +=` jadi `StringBuilder` kerasa pintar, tapi p50/p95 total hampir nggak gerak
karena bottleneck-nya query. Ukur dulu, baru pilih target.
Demo, bukan benchmark presisi (tanpa JMH, I/O disimulasikan pakai sleep).
