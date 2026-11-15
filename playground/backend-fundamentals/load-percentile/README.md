# Load Percentile Demo (Lesson 14.3)

Simulasi load test kecil: 50 user paralel, 3.000 request, 98% request ~20 ms, 2% ~800 ms.

Run: `java LoadPercentileDemo.java`

Yang diliat: `avg` kelihatan lumayan, `p50` dan `p95` kelihatan sehat, tapi `p99` dan `max` ngebuka ekor lambat yang dirasain sebagian user. Demo ini simulasi (sleep), bukan k6/Gatling beneran, dan nggak ngatasin coordinated omission.
