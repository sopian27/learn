### 1. Define Intent

**CLI scraper produk → CSV dianggap selesai jika:**

* CLI menerima **1 URL halaman katalog produk** dan optional path output CSV.
* Program berhasil mengambil halaman tersebut melalui HTTP/HTTPS.
* Program mengekstrak **judul dan harga produk** berdasarkan struktur HTML target yang benar-benar divalidasi, bukan selector tebakan.
* Setiap row CSV berisi `title` dan `price` yang berasal dari produk yang ditemukan.
* Jika halaman berhasil diakses tetapi **0 produk ditemukan**, program **tidak boleh menganggapnya sukses**: harus memberikan error/warning yang jelas dan exit dengan status non-zero.
* CSV hanya dianggap berhasil jika jumlah row > 0 dan file berhasil ditulis.
* CLI memberikan summary yang bisa diverifikasi, misalnya `Scraped 24 products → products.csv`.

**Definition of Done:** `valid URL → successful fetch → validated extraction → >0 products → valid CSV → truthful exit status/reporting`.

---

## 2. Remove Ambiguity

Minimal edge case yang harus ditangani:

1. **URL tidak valid / bukan HTTP(S)**
   Contoh: `dummyjson.com/products`, `ftp://...`, atau URL kosong → ditolak dengan pesan error yang jelas.

2. **HTTP error**
   `404`, `403`, `500`, redirect bermasalah → jangan menghasilkan CSV kosong lalu mengklaim sukses.

3. **TLS/network failure**
   Connection reset, timeout, DNS failure → tampilkan error yang jelas; gunakan retry terbatas untuk kegagalan network yang transient.

4. **Halaman berhasil diakses tetapi struktur HTML berubah**
   Selector tidak menemukan produk → **fail**, bukan menghasilkan CSV dengan header saja.

5. **0 produk ditemukan**
   Harus dianggap sebagai kegagalan extraction/validation, bukan successful scrape.

6. **Produk tidak memiliki title atau price**
   Tentukan perilaku eksplisit: skip item yang field wajibnya tidak lengkap dan laporkan jumlah item yang di-skip.

7. **Harga memiliki format aneh**
   Misalnya `$1,299.99`, `Rp 25.000`, atau whitespace berlebih. Untuk versi pertama, **simpan sebagai text apa adanya setelah trimming**, jangan mencoba melakukan currency conversion.

8. **Duplicate product**
   Jangan otomatis melakukan deduplication karena belum ada definisi unik produk yang reliable.

9. **Output file sudah ada**
   Default behavior harus jelas: overwrite file output, bukan append, agar hasil run tidak tercampur dengan run sebelumnya.

10. **Server membutuhkan User-Agent / rate limiting**
    Request harus memiliki User-Agent yang wajar dan scraping tidak boleh melakukan request berulang tanpa batas.

---

## 3. Plan with Constraints

### Task breakdown

**Task 1 — CLI & input validation**

* Terima positional `url`.
* Terima `-o/--output`.
* Validasi URL hanya `http://` atau `https://`.

**Task 2 — HTTP fetching**

* Gunakan `requests`.
* Set explicit timeout.
* Tambahkan User-Agent.
* Implementasikan retry terbatas untuk error network/transient.
* Jangan retry tanpa batas.

**Task 3 — HTML extraction**

* Validasi struktur HTML target terlebih dahulu.
* Gunakan selector yang sesuai dengan struktur halaman aktual.
* Extract `title` + `price`.
* Trim whitespace.
* Skip produk yang kehilangan field wajib.

**Task 4 — Result validation**

* Jika HTTP gagal → exit non-zero.
* Jika extraction menghasilkan `0 products` → exit non-zero.
* Jangan pernah melaporkan sukses ketika CSV hanya berisi header.
* Hitung jumlah extracted/skipped products.

**Task 5 — CSV output**

* Header wajib: `title,price`.
* UTF-8.
* Overwrite output file.
* Pastikan minimal satu data row sebelum menyatakan sukses.

**Task 6 — CLI reporting**
Contoh successful output:

```text
Scraped 24 products.
Skipped 2 incomplete products.
Saved to products.csv
```

Contoh failure:

```text
Error: page loaded successfully but no products were found.
```

### Technical constraints

* Python 3.
* `requests` untuk HTTP.
* `BeautifulSoup` untuk HTML parsing.
* `csv` standard library untuk output.
* Timeout HTTP **wajib**.
* Retry **terbatas**, bukan infinite retry.
* URL harus HTTP/HTTPS.
* Tidak boleh menggunakan browser automation/JavaScript execution.
* Tidak boleh menganggap `HTTP 200` sebagai bukti scraping berhasil.
* **`0 products` harus menghasilkan non-zero exit code.**
* Tidak melakukan request ke URL lain selain target yang diberikan.