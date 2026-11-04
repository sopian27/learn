# Thread-safety demo (Lesson 2.1)

Run: `java ThreadSafetyDemo.java`

Lihat:
- `1a` vs `1b`: `stok--` dari 8 thread x 100.000 hampir tidak pernah berakhir di 0 (lost update); `AtomicInteger` selalu 0.
- `2a` vs `2b`: flag tanpa `volatile` bisa bikin worker tidak pernah berhenti (nondeterministik, ulangi run); dengan `volatile` selalu berhenti.
- `3a` vs `3b`: semua method `synchronized`, tapi `if (balance >= 10)` lalu `withdraw` terpisah tetap bisa bikin saldo minus (jalankan beberapa kali). Satu lock untuk check + act menutupnya.
- `3c`: `ConcurrentHashMap.merge` atomik, `get` lalu `put` tidak.
