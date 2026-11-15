# Common Bottlenecks (Lesson 14.4)

Run: `java CommonBottlenecksDemo.java`

Lihat:
- Pool 4 vs 40: waktu kerja per request sama (~100 ms), tapi waktu antre melonjak di pool kecil. Query-nya nggak lambat, requestnya nunggu thread. CPU nganggur.
- Hit ratio turun dari 95% ke 50%: rata-rata latency naik berkali-kali lipat dan beban DB ikut naik.

Catatan: simulasi dengan sleep, bukan DB/cache beneran.
