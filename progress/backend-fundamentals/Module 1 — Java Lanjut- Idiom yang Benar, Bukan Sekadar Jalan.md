# Java Lanjut: Idiom yang Benar, Bukan Sekadar Jalan
   # Generics Mendalam: Bounded Type, Wildcard, dan Type Erasure

    Posisi	Wildcard	Bisa baca?	Bisa tulis?
    Producer (sumber data)	? extends T	Ya, sebagai T	Tidak (kecuali null)
    Consumer (tujuan data)	? super T	Cuma sebagai Object	Ya, boleh nulis T

    Type erasure: setelah compile, info <T> dihapus. List<String> dan List<Integer> jadi List biasa di bytecode.

    jangan di lakukan :
    1. Pakai raw type (List tanpa <>): matiin semua pengecekan compiler, bug-nya pindah ke runtime.
    2. Pakai List<Object> dan ngira itu "terima apa aja"; padahal gak bisa nerima List<String>.
    3. @SuppressWarnings("unchecked") tanpa bukti cast itu aman. Itu bohong ke compiler.
    4. Jangan taruh wildcard di return type, itu maksa caller berurusan sama tipe capture.
    5. Jangan generic-in segalanya; class yang cuma satu tipe konkret gak butuh <T>.

    kapan dipakai :
    1. Parameter method yang cuma membaca koleksi: List<? extends X>.
    2. Parameter yang cuma menulis ke koleksi: List<? super X>.
    3. Butuh operasi tertentu di T (bandingkan, hitung): bounded <T extends Comparable<T>>.

    aturan emas
    pas code review atau nulis @SuppressWarnings / cast, tulis alasannya di komentar atau PR. Kalau gak bisa nulis alasannya, berarti belum paham, dan bug-nya cuma lagi nunggu pindah ke production.

   # Collections internals: ArrayList vs LinkedList vs HashMap vs TreeMap, mutable key, hashCode/equals
     
     Kontrak equals/hashCode:

     Dua object equals → hashCode WAJIB sama. Kebalikannya boleh (hash sama, equals beda = collision).

     hashCode dihitung dari field yang sama dengan equals, dan field itu gak boleh berubah selama object jadi key.

     alternatif

     HashMap O(1) tapi gak terurut dan butuh key dengan hash bagus. 
     TreeMap terurut tapi O(log n) dan butuh ordering yang konsisten. 
     LinkedHashMap jaga urutan insert (basis LRU cache).

     Real-world
     Cache in-memory dengan key object (tenant, user, produk) adalah sumber bug klasik. Lupa hashCode di entity JPA yang dipakai di Set juga sering: Hibernate bikin proxy dan id yang baru terisi setelah save, jadi hashCode berbasis id berubah di tengah jalan. Tim yang pakai Caffeine/Guava sebagai cache biasanya maksa key berupa record atau String supaya masalah ini mati dari desain.

     jangan di lakukan :
     1. Pakai object mutable (atau entity JPA yang id-nya baru terisi setelah save) sebagai key HashMap/elemen HashSet.
     2. Override equals tanpa hashCode, atau hashCode pakai field yang beda dari equals.

     Kapan dipakai
     1. ArrayList sebagai default list; HashMap untuk lookup by key; TreeMap kalau butuh urut atau range.
     2. record atau class dengan field final untuk key map/elemen set.
     
     Aturan Emas
     sebelum ngubah field yang dipakai buat identitas / key (email, kode, ID) atau ngubah kontrak API yang sudah dipakai tim lain, tanya dulu "siapa yang pegang nilai lama ini?" dan sepakati migrasinya, jangan diam-diam diubah.

   # Exception handling idiomatis
     
     Checked vs unchecked:

     Jenis	Contoh	Pakai kalau
     
     Checked (Exception)	IOException, SQLException	Caller bisa dan diharapkan pulih (retry, pakai fallback)
     
     Unchecked (RuntimeException)	IllegalArgumentException, custom business error	Bug programmer, atau kegagalan yang gak bisa dipulihkan di layer itu

     Kapan bikin custom exception: kalau nama dan datanya bantu caller mengambil keputusan (PaymentFailedException, DuplicateEmailException). Kalau cuma mengganti nama RuntimeException tanpa data atau tanpa perlakuan beda, gak usah.

     Real-world
     Insiden klasik "sukses palsu": job batch yang catch dan lanjut ke baris berikutnya sehingga ribuan transaksi gagal tanpa jejak. Tim yang matang biasanya punya satu @RestControllerAdvice yang jadi satu-satunya tempat log + mapping exception ke respons HTTP, jadi layer bawah tinggal throw dan gak perlu log sama sekali.

     jangan di lakukan :
     1. catch (Exception e) {} atau catch yang cuma e.printStackTrace(): error dibisukan, program lanjut seolah sukses.
     2. throw new RuntimeException(e.getMessage()): cause dan stack trace asli hilang.
     3. Log-and-throw di setiap layer, sehingga satu error tercatat berkali-kali dan nyamarin yang lain.

     Kapan dipakai : 
     1. Custom unchecked exception untuk business error yang butuh penanganan beda (PaymentFailedException → 502/retry).
     2. Checked exception hanya untuk kegagalan yang caller bisa pulih darinya.
     3. Satu handler terpusat (@RestControllerAdvice) untuk log dan mapping ke respons.

     Aturan Emas

     Kalau ada yang gagal, bilang. Jangan pura-pura sukses.

     pas lu gak bisa nyelesain sesuatu (task telat, bug belum ketemu, data meragukan), kabarin tim dengan konteks yang cukup buat ditindaklanjuti. Sama kayak cause di exception: kabar gagal tanpa penyebab itu setengah berguna.

   # Idiom Effective Java yang sering dilanggar
     1. Immutable by default. Field final, gak ada setter, collection masuk dan keluar di-copy (List.copyOf, bukan simpan referensi mentah). Object immutable aman dipakai bareng antar thread, aman jadi key HashMap (nyambung ke Lesson 1.2), dan gampang dinalar karena nilainya gak bisa berubah diam-diam. record memberi ini hampir gratis, tapi hati-hati: record cuma dangkal immutable. Field List di dalamnya tetap bisa dimutasi kalau lu gak copyOf.

    2. equals/hashCode/toString itu kontrak, bukan boilerplate.

    equals dan hashCode satu paket, dihitung dari field yang sama (detailnya di Lesson 1.2).
    toString adalah output ke dunia luar: masuk log, error message, tracing. Pilih field-nya dengan sadar. Jangan masukin password, token, nomor kartu, atau data pribadi. Catatan: toString() bawaan record nyetak semua field, jadi record yang bawa secret perlu override.

    Trade-offs / alternatif
    Immutability berharga: copy tiap kali berubah (misal "ubah satu field"). Untuk object kecil, biayanya gak kerasa; untuk koleksi besar yang sering diubah, pertimbangkan builder atau struktur persisten. Builder cocok kalau parameter banyak dan opsional. Telescoping constructor itu yang harus dihindari. Alternatif lain untuk argumen ketuker: bikin tipe sendiri (Currency sebagai enum, bukan String), jadi compiler yang jaga.

    Real-world
    Library standar sudah ngikutin idiom ini: List.of, Optional.of, LocalDate.of itu static factory; String dan BigDecimal immutable. Tim pembayaran biasanya punya Money immutable dengan factory karena duit gak boleh berubah diam-diam, dan punya aturan "DTO tidak boleh bawa secret di toString" supaya log aman.

    jangan di lakukan :
    1. Simpan referensi collection dari luar, atau ngembaliin collection internal lewat getter.
    2. toString() hasil generate IDE/Lombok @ToString yang ikut field sensitif
    3. Constructor dengan banyak parameter bertipe sama (String, String, String) tanpa validasi.

    Aturan Emas :
    sebelum ngasih akses (referensi, kredensial, file, dokumen) ke orang atau sistem lain, tanya "apa yang bisa mereka ubah atau lihat dari sini?" Kasih salinan atau versi yang cuma-baca, dan jangan taruh secret di tempat yang gampang kebaca.
     

   # Naming & code style
     real world
     style guide google dan spring lewat formatter + linter (checkstyle dan spotless).

     jangan di lakukan :
     1. param angka tanpa satuan
     2. satu konsep banyak ejaan
     3. method get tapi implementasi nya ada update db
     4. singkatan yang hanya dimengerti penulis
     5. debat cuman karena masalah style
     6. rename masal di proyek aktif tanpa kesepakatan
     
     Aturan Emas: 
     Kasih nama yang jujur dan seragam, supaya orang lain gak perlu nebak.

     Terapin: 
     tiap nulis nama (variabel, method, tabel, nama file, judul tiket), cek "apa satuannya, apa efeknya, dan apakah gue nyebut hal yang sama dengan kata yang sama di tempat lain?" Dua menit di sini hemat berjam-jam orang nebak nanti.