# Alerting Demo (Lesson 13.4)

Companion demo untuk lesson 13.4. Simulasi 60 menit error rate per menit (data buatan), dua aturan alert dijalankan di data yang sama. Tanpa Prometheus/Alertmanager.

## Run

```
java AlertingDemo.java
```

## Yang diperhatikan

1. Aturan A (satu menit lewat threshold) nge-page untuk tiap lonjakan sesaat.
2. Aturan B (threshold bertahan 5 menit) cuma nge-page untuk insiden beneran.
3. Harga aturan B: telat beberapa menit. Itu trade-off yang sengaja diambil.
4. Ubah angka threshold/durasi di kode dan lihat jumlah page berubah.
