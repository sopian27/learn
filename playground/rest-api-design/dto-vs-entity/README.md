# DTO vs Entity Demo (Lesson 4.1)

Run: `java DtoVsEntityDemo.java`

Demo ini tanpa Spring/Jackson; serializer mini berbasis reflection meniru Jackson (semua field ikut keluar).

Yang diliat:

* Entity langsung di-return -> `passwordHash` dan `internalNote` ikut bocor (over-exposure).
* Kolom `fullName` di-rename jadi `displayName` -> JSON entity berubah, client lama pecah (coupling).
* Lewat DTO `UserResponse` -> JSON sebelum dan sesudah refactor identik, cuma mapper yang diubah.
