# Locking Demo (Lesson 8.4)

Simulasi mini (tanpa Spring/DB) 50 pembeli rebutan stok 100 yang sama.

Jalankan: `java LockingDemo.java`

Yang dilihat:
1. Tanpa lock: stok akhir hampir pasti bukan 50 (lost update, update saling menimpa).
2. Optimistic: stok 50, tapi ada angka `retry` (tabrakan terdeteksi lalu diulang).
3. Pessimistic: stok 50, `retry=0`, tapi semua antre satu per satu.

Angka retry dan durasi beda tiap run. Ini model sederhana, bukan JPA/PostgreSQL asli.
