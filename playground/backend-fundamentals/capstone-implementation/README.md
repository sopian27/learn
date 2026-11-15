# Capstone Implementation Demo (Lesson 15.2)

Jalankan: `java CapstoneImplementationDemo.java`

Yang dilihat:
- Satu "tahap" implementasi (locking) dibuktiin pakai test konkuren, bukan klaim.
- Versi naif (cek lalu tulis) bikin banyak user merasa berhasil; versi dengan constraint atomik menyisakan tepat 1.
- Tiap tahap meninggalkan satu baris catatan keputusan + bukti.

Catatan: `ConcurrentHashMap.putIfAbsent` mewakili `UNIQUE(court_id, start_at)` di database. Angka versi naif bisa beda tiap run (balapan thread), tapi hampir pasti lebih dari 1.
