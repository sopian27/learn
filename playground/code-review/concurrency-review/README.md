# Lesson 4.3: Concurrency issues

```bash
java ConcurrencyDemo.java
```

Yang dilihat:

- Stok 1, check-then-act: 2 barang kejual. Atomic update: 1.
- Urutan lock berlawanan: 0 transfer selesai. Urutan sama: 2.

Barrier dipakai supaya tabrakannya kejadian di setiap run. Di production urutannya acak, jadi bug begini bisa lama gak muncul. Deadlock-nya dibikin pakai `tryLock` timeout biar demo gak menggantung. Semua ini lock di satu JVM, bukan lock database.
