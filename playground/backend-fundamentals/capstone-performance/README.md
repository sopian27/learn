# Capstone Performance Demo (Lesson 15.3)

Jalankan: `java CapstonePerformanceDemo.java`

Yang dilihat:
- Satu bottleneck (N+1 query di endpoint ketersediaan) diukur sebelum dan sesudah diperbaiki, pakai skenario yang sama.
- Angka p95 dan jumlah query per request jadi bukti, bukan "kayaknya lebih cepat".

Catatan: latensi DB disimulasikan pakai `Thread.sleep(3)`. Angka p95 bisa beda tiap mesin, tapi sebelum harus jauh di atas sesudah.
