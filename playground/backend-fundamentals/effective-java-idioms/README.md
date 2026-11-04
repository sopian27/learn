# Effective Java idioms demo (Lesson 1.4)

Run: `java EffectiveJavaIdiomsDemo.java`

Lihat:
- `1b`: total order berubah karena list internal bocor. `2`: versi record + `List.copyOf` nolak `add()`.
- `3a` vs `3b`: `toString` yang bocorin `apiKey` vs yang milih field aman.
- `4a`: argumen constructor ketuker tapi compile lolos. `4b`/`4c`: static factory + validasi.
