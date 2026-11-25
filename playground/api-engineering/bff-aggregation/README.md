# BFF aggregation demo

Run: `java BffAggregationDemo.java`

Lihat:
- [1] tiga service berurutan ~300ms, dan di dunia nyata tiap panggilan itu round trip lewat jaringan HP.
- [2] BFF memanggil tiga service paralel: ~100ms, client cuma 1 request dan dapat bentuk `MobileHome` yang pas.
- [3] `promo-service` mati: promo jadi `-`, halaman home tetap jalan. BFF yang memutuskan data mana yang boleh gagal.

Batas: latency disimulasikan dengan `sleep`, tanpa timeout per panggilan, tanpa cache. BFF sungguhan wajib pasang timeout dan circuit breaker per downstream.
