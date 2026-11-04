# CompletableFuture demo (Lesson 2.3)

Run: `java CompletableFutureDemo.java`

Lihat:
- `1a` vs `1b`: 3 layanan @200 ms. Sekuensial ~600 ms, paralel (`thenCombine`) ~200 ms, yaitu layanan paling lambat.
- `2a`: `join()` melempar `CompletionException`; error aslinya ada di `getCause()`.
- `2b`: `exceptionally` memberi fallback supaya satu layanan gagal tidak menjatuhkan seluruh rantai.
- `2c`: future yang gagal tanpa `join`/handler tidak menghasilkan log apa pun, error hilang diam-diam.
- `3`: layanan hilir macet 3 detik, `orTimeout(300ms)` + `exceptionally` memotongnya jadi ~300 ms.

Angka waktu tergantung mesin.
