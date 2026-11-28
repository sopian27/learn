# context-kernel

Versi kecil, untuk belajar, dari lapisan pengelola konteks untuk coding agent: bagian percakapan yang sudah selesai dilipat menjadi ringkasan, dan bisa dikembalikan persis seperti semula.

## Ini buat apa?

Coding agent (misalnya Claude Code) mengirim ulang riwayat percakapan ke model di setiap giliran. Makin panjang sesinya:

- makin banyak token yang dibayar,
- context window makin cepat penuh,
- model makin gampang terdistraksi oleh hal lama, seperti isi file yang sudah tidak relevan.

Solusi umumnya adalah meringkas bagian lama. Masalahnya, ringkasan membuang detail. Kalau ternyata detail itu dibutuhkan lagi (pesan error persisnya, isi file), dan aslinya sudah dihapus, detail itu hilang.

Kernel ini menunjukkan cara meringkas tanpa kehilangan apa pun:

```text
history (disimpan utuh) : m1  m2  m3  m4  m5
block b1 (stiker)       :     [---------]
view (dikirim ke model) : m1  [ringkasan b1]  m5
```

Setelah `restore("b1")`, view kembali berisi m1 sampai m5.

Contoh pemakaian:

```java
var state = ContextKernel.State.empty();
state = ContextKernel.append(state, "user", "cari bug di login", null);
state = ContextKernel.append(state, "assistant", "cek AuthService", null);
state = ContextKernel.append(state, "assistant", "ketemu: cek expiry salah", null);

var folded = ContextKernel.compress(state, "m00001", "m00002", "sudah telusuri login");
ContextKernel.view(folded);                                // [b1, m00003]
ContextKernel.view(ContextKernel.restore(folded, "b1"));   // [m00001, m00002, m00003]
```

## Tujuan

Ini bahan belajar, bukan library siap pakai. Yang dilatih:

1. **Overlay, bukan mutasi.** Data asli tidak pernah diubah, jadi pemulihan persis sama tanpa logika pemulihan yang rumit. Ide yang sama dipakai di event sourcing.
2. **Pure core.** Tidak ada I/O dan tidak memanggil model, sehingga bisa dites tanpa mock.
3. **Invariant.** Aturan yang harus selalu benar, ditolak di pintu masuk.

## Cara menjalankan test

Dari folder ini (Java 17+, tanpa dependency):

```bash
javac -d out *.java && java -cp out ContextKernelTest
```

## Desain

- `history` hanya bisa ditambah. `compress` tidak pernah mengubah atau menghapus message.
- Block adalah overlay (`from`, `to`, `summary`, `active`). `view` menerapkan block aktif di atas history.
- `restore` hanya menonaktifkan block.
- Semua operasi adalah pure function: menerima `State`, mengembalikan `State` baru.

Trade-off: `view` dan pencarian ref menelusuri list secara linear. Cukup untuk belajar; buat indeks kalau history mencapai ribuan message.

## Aturan yang ditegakkan

- Ref dan block id tidak pernah dipakai ulang.
- Rentang tidak boleh menimpa block yang aktif.
- Tool call dan tool result dilipat berdua atau tidak sama sekali.
- Ringkasan tidak boleh kosong.

Ringkasan ditulis oleh pemanggil; kernel tidak membuatnya sendiri.

## Batasan yang diketahui

- Tool call yang hasilnya **belum** masuk history masih bisa dilipat. Kalau hasilnya ditambahkan sesudah itu, pasangannya terpisah. Belum diperbaiki dan belum ada test-nya.
- Ringkasan masuk view dengan role `user`. Kalau yang diringkas berisi teks tidak terpercaya (isi web, file orang lain), instruksi di dalamnya bisa terbaca seperti perintah user.
- Belum dicoba ke API model asli.

## Belum ada

Persistence, wire codec, proxy, search, tingkatan ringkasan, hitung token, nudge, protected tool.

## Latihan

1. Tambahkan `search(state, query)` yang juga menemukan teks di dalam rentang terlipat dan mengembalikan block id. Tulis test-nya dulu.
2. Izinkan `compress` mencakup satu block utuh (tier T2). Setelah `restore` pada block luar, apa yang harus terlihat?
3. `restore` pada block yang sudah dipulihkan tidak melakukan apa-apa. Beri argumen setuju atau tidak kalau diganti melempar exception.
