# Test Pyramid vs Ice-Cream Cone (Lesson 10.1)

Simulasi: 100 test di kedua suite, beda cuma proporsi layer.

Run: `java TestPyramidDemo.java`

Yang diliat:
- Waktu 1x run CI: pyramid ~1.8 menit vs cone ~11.8 menit.
- Peluang build merah murni karena flake (tanpa bug asli): pyramid ~21% vs cone ~77%.

Angka durasi dan flake rate itu asumsi, bukan hasil ukur. Ubah di `main` buat cocokin project lu.
