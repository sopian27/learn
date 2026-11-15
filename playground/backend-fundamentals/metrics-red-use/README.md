# Metrics RED + USE Demo (Lesson 13.2)

Companion demo untuk lesson 13.2. Simulasi in-memory (tanpa Micrometer/Actuator): counter dan timer yang dihitung manual dari 100 request checkout, plus snapshot pool thread.

## Run

```
java MetricsRedUseDemo.java
```

## Yang diperhatikan

1. RED: Rate dan Errors cuma counter yang naik; Duration dicatat per request lalu diringkas jadi percentile.
2. Bandingin `avg` dengan `p99`: rata-rata kelihatan kecil, p99 membuka request lambat yang disembunyikan rata-rata.
3. USE: `Utilization` (thread sibuk) dan `Saturation` (antrean) menunjukkan resource penuh sebelum error muncul.
4. Hasil tiap run bisa beda sedikit (timing thread), polanya yang dilihat.
