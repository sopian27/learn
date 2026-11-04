# Naming & code style demo (Lesson 1.5)

Run: `java NamingStyleDemo.java`

Lihat:
- `1a` vs `1b`: `timeout = 30` dibaca milidetik padahal maksudnya detik; satuan di tipe (`Duration`) menutup celah itu.
- `2a` vs `2b`: satu konsep (`userId`) ditulis 4 cara, grep cuma nemu sebagian.
- `3a` vs `3b`/`3c`: `getUser` yang diam-diam nulis vs `find`/`create` yang jujur.
