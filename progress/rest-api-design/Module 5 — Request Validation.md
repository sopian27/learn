# Request Validation

   # Jakarta Bean Validation dasar

   apa yang dicek :
   @NotNull
   bukan null, cocok untuk semua
   "" dan "   " tetap lolos

   @NotEmpty
   bukan null dan panjang > 0
   String, Collection, Map
   "   " tetap lolos

   @NotBlank
   bukan null dan ada karakter non-spasi
   String

   @Size(min,max)
   panjang string / ukuran collection
   String, Collection

   @Min / @Max / @Positive
   rentang angka
   angka

   @Pattern(regexp)
   cocok regex
   string

   @email
   format email longgar
   string

   jangan di lakukan:
   1. Menempel @NotNull di semua String lalu menganggap selesai. Pakai @NotBlank untuk teks wajib.
   2. Lupa @Valid di @RequestBody. Anotasinya ada, validasinya nggak jalan, dan nggak ada error apa pun.
   3. Mengira @Email/@Size otomatis bikin field wajib. Mereka lolos untuk null.
   4. Pesan default yang generik. Tulis message yang menyebut field dan aturan.
   5. jangan memaksa aturan bisnis yang butuh data lain (stok cukup, kode unik) ke dalam anotasi bawaan. Itu bagian 5.2. Dan jangan validasi ulang hal yang sudah dijamin tipe (mis. field LocalDate sudah gagal parse sendiri).

   Tes Feynman: Validasi itu satpam yang menanyakan pertanyaan tepat untuk tiap jenis data dan menolak dengan alasan yang jelas.

   Aturan Emas: Jangan cuma tanya "ada isinya nggak?", tanya "isinya masuk akal nggak?", dan beri tahu kenapa kalau nggak.

   Terapin: Pas review DTO baru, ganti tiap @NotNull di String dengan pertanyaan "teks ini boleh kosong atau spasi doang?".

   # Custom validator untuk business rule yang tidak bisa diekspresikan anotasi bawaan

   Anotasi bawaan nggak cukup untuk aturan seperti check digit atau perbandingan antar field. Jawabannya constraint custom: field-level atau class-level.

   tiga aturan jari :
   1. null dianggap valid di validator lu sendiri, sama seperti constraint bawaan. Wajib atau tidaknya field itu urusan @NotNull. Satu constraint, satu tanggung jawab.

   2. Validator murni dan cepat: tanpa I/O, tanpa efek samping. Dia bisa dipanggil berkali-kali.

   3. Tempel error ke field yang benar di class-level, supaya consumer tahu apa yang diperbaiki.

   jangan di lakukan:
   1. validator nya gak public dan tanpa constructor
   2. validator me return false untuk null, jadi field opsional jadi wajib
   3. request io di dalam validator
   4. rule yang butuh state di luar request data

   best case:
   1. aturan yang murni di hitung dari satu request, dipakai lebih dari satu DTO.

   Tes Feynman: Constraint custom itu aturan yang cukup dijawab dengan melihat formulir saja; yang butuh bertanya ke tempat lain bukan tugas validasi.

   Aturan Emas: Validasi menjawab "formulirnya masuk akal nggak?", bukan "dunia luar mengizinkan nggak?".

   Terapin: Sebelum bikin constraint baru, tanya "bisa dijawab cuma dari isi request?". Kalau butuh DB, pindahkan ke service.

   # Validation groups
   
    Validation groups membiarkan satu DTO punya aturan berbeda untuk create dan update; pilih group lewat @Validated(OnCreate.class).

    jangan di lakukan:
    1. Menyimpan id di body update padahal sudah ada di path, lalu tidak mencek keduanya sama.
    2. operasi yang bentuknya memang berbeda jauh, atau group sudah lebih dari 2-3. Pakai DTO terpisah.

    Aturan Emas: Satu bentuk data boleh punya banyak aturan, asal setiap aturan jelas kapan berlakunya.

    Terapin: Sebelum menambah groups, tulis di komentar PR "constraint ini jalan di operasi apa"; kalau jawabannya tidak jelas, pecah DTO-nya.

   # Nested object validation & validasi collection (`@Valid` pada list item order)

   Tiga aturan jari:

   1. Lapis dua, dua jenis anotasi. Constraint di list (@NotEmpty, @Size) mengecek wadahnya. @Valid pada tipe elemen mengecek isi item. Order butuh keduanya.
   2. Batasi ukuran list. Tanpa @Size(max), satu request bisa membawa 100.000 item dan memaksa validator (dan service) bekerja keras.

   jangan di lakukan:
   1. Menaruh constraint di OrderItem tapi lupa @Valid di Order.items, sehingga validasi tidak pernah jalan (jebakan di cerita).
   2. Mengira @NotEmpty pada list ikut mengecek isi item. Itu hanya mengecek list tidak kosong.
   3. Tidak membatasi @Size(max) pada list yang datang dari klien.
   4. aturan yang butuh data luar atau lintas item (stok cukup, sku ganda). Taruh di service atau custom validator level list, bukan memaksa lewat anotasi per item.

   Tes Feynman: Validasi hanya turun ke dalam object atau list kalau lu menandainya dengan @Valid.

   Aturan Emas: Validasi yang tidak ditandai tidak berjalan, dan kegagalannya tidak bunyi.
   
   Terapin: Tiap menulis DTO bersarang, tulis satu tes dengan data rusak di level terdalam; kalau tes itu lolos, berarti @Valid ada yang hilang.




