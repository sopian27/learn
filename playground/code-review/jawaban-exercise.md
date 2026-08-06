Module 1 — Lesson 1: Tujuan Sebenarnya dari Code Review
1.Klasifikasikan tiap komentar (A, B, C) ke salah satu dari 4 pilar (atau tandai jika gagal keempatnya).
A:Consistency
B:gagal keempatnya
C:Defect Prevention

2.Untuk komentar yang menurut Anda lemah, tulis ulang versi yang lebih baik.
komentar yang lemah adalah B:LGTM, seharusnya :
Defect Prevention : ada validasi uuid itu exist atau tidak, sebelum action delete
Consistency & Standards : endpoint delete seharusnya menggunakan http DELETE bukan POST
Mentoring & Growth : path variable id harusnya di validasi tidak boleh kosong

Module 1 — Lesson 2: Anatomy of a Bad Review vs a Great Review
Klasifikasikan: ini pola buruk atau hebat? Kenapa?
ini pola buruk, karena tidak actionable, line ke berapa ?, flow apa ? apakah semua di pecah, tidak ada saran nya ?

Tulis ulang jadi versi yang spesifik dan actionable — anggap function-nya adalah method processOrder() sepanjang 80 baris yang mencampur validasi, kalkulasi harga, dan pengiriman notifikasi dalam satu method.
method processOrder() kalau misalkan ada penambahan fiture untuk hit endpoint external atau apapun feature baru itu, berarti code nya akan semakin panjang ya, bagaimana jika 3 bulan dari sekarang ada temuan issue di production ,apakah kita masih ingat, jika ini dihandover ke developer lain, apakah dia mengerti cara maintenance code ini. sebaiknya logic nya di pecah jadi processOrder adalah methode utama yang memiliki sub methode validasi(), kalkulasi() dan notifikasi() sehingga penambahan fiture di methode utama lebih mudah untuk dibaca dan dikembangkan.