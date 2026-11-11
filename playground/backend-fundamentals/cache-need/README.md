# Cache Need Demo (Lesson 9.1)

Tiga skenario baca dengan "DB" lambat (5 ms), cache-nya cuma `HashMap` (tanpa Redis).

Jalankan: `java CacheNeedDemo.java`

Yang dilihat:
1. A (data hot, stabil): DB turun dari 200 ke 10 panggilan, waktu jauh lebih kecil.
2. B (tiap key unik): hit = 0, waktu sama, cuma nambah entri di memori.
3. C (data berubah terus): cepat tapi `stale` tinggi, hasilnya salah.

Ini model kasar: tanpa TTL, tanpa eviction, tanpa concurrency. Itu dibahas di lesson 9.2-9.4.
