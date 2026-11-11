# Saga Demo (Lesson 8.5)

Simulasi mini (tanpa Spring/DB/broker) order -> bayar -> kurangi stok, tiap service punya "DB" sendiri. Langkah stok sengaja gagal.

Jalankan: `java SagaDemo.java`

Yang dilihat:
1. Tanpa saga: saldo user tetap terpotong 40, order nyangkut `PENDING`.
2. Dengan saga: langkah sukses dibatalkan terbalik (refund, order `BATAL`), saldo kembali 100.

Ini model sederhana: kompensasi di sini tidak pernah gagal dan tidak ada crash di tengah. Saga asli butuh state tersimpan dan retry.
