# Generics & Type Erasure demo (Lesson 1.1)

Run: `java GenericsErasureDemo.java`

Lihat:
- Output `class sama? true`: erasure hapus `<String>` dan `<Integer>` saat runtime.
- `BUG erasure: ...`: `ClassCastException` meledak di caller, bukan di `toArrayBroken`.
- Baris yang di-comment (`totalRigid(orders)`, `amounts.add(1.0)`): buka comment, lihat compiler nolak.
