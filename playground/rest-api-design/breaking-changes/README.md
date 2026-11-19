# Breaking Change Demo (Lesson 8.1)

Run: `java BreakingChangeDemo.java`

Look for: consumer v1 (kode tidak berubah) tetap jalan waktu server cuma menambah field. Rename, hapus field, ganti tipe, dan enum value baru bikin consumer meledak (`NullPointerException`, `ClassCastException`, `IllegalStateException`). Di sisi request, field wajib baru bikin klien lama kena `400`.
