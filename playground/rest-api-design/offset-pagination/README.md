# offset-pagination (Lesson 7.1)
Jalankan: `java OffsetPaginationDemo.java`
Lihat: order id 8 muncul di page 0 dan page 1 karena ada order baru (id 11) masuk di antara dua request (page drift).
`size=1000` dipotong ke batas maksimum. Baris terakhir = self-check.
