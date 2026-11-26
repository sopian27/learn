# The Core Review Checklist

   # Correctness & edge case hunting

   Correctness artinya kode ngelakuin yang seharusnya, buat semua input yang mungkin datang, bukan cuma input yang kepikiran penulisnya.

   Cari dulu janji dan invariant-nya, baru baca implementasi.

   Kategori
   Kosong : null, string kosong, list kosong
   Batas : 0, 1, -1, max, pas di limit, limit + 1
   Angka	: pembagian gak habis, overflow, double buat uang
   Waktu	: tengah malam, akhir bulan, 29 Februari, timezone
   Data :duplikat, urutan acak, record gak ketemu
   Eksternal : timeout, respons kosong, retry setelah sukses
   Concurrency	: double click, cek-lalu-tulis (check-then-act)

   jangan di lakukan:
   1. Percaya test hijau, padahal test-nya cuma happy path.
   2. Review cuma baris yang berubah, gak liat siapa yang manggil dan bawa nilai apa.
   3. Komentar "handle edge case ya" tanpa nyebut input-nya. Penulis gak bisa ngapa-ngapain.

   best case:
   tiap PR yang ada logika: hitungan, filter, status transition, integrasi.

   Tes Feynman: kode itu bener kalau tetep ngasih hasil yang dijanjikan waktu inputnya aneh, bukan cuma waktu inputnya manis.

   Aturan Emas: Jangan tanya "ini jalan gak?", tanya "input apa yang bikin ini bohong?"

   Terapin: di PR berikutnya yang ada hitungan, coba nol, satu, dan angka yang gak habis dibagi sebelum approve.

   # Architecture & SOLID dalam konteks review (bukan cuma menulis, tapi menilai)

   Diff nunjukin yang berubah. Tugas reviewer juga nyari yang harusnya ikut berubah.
   
   SOLID itu daftar pertanyaan buat nemu risiko, bukan label buat ditempel.
   
   Kurang desain dan kelebihan desain sama-sama temuan.

   | Prinsip | Kepanjangan | Inti |
   |---|---|---|
   | **S – SRP** | Single Responsibility Principle | Satu class punya satu tanggung jawab utama. |
   | **O – OCP** | Open/Closed Principle | Kode terbuka untuk ekstensi, tertutup untuk modifikasi yang tidak perlu. |
   | **L – LSP** | Liskov Substitution Principle | Implementasi turunan harus bisa menggantikan induknya tanpa merusak perilaku. |
   | **I – ISP** | Interface Segregation Principle | Jangan memaksa class mengimplementasikan method yang tidak dibutuhkan. |
   | **D – DIP** | Dependency Inversion Principle | Logic bisnis bergantung pada abstraksi, bukan detail implementasi teknis. |

   kode yang bagus bukan kode yang paling banyak menerapkan pattern, tetapi kode yang paling mudah menghadapi perubahan requirement.

   real world:
   Panduan code review Google (eng-practices) naruh desain sebagai hal terpenting buat diliat di sebuah perubahan, dan sekaligus minta reviewer waspada sama over-engineering: selesaiin masalah yang emang ada sekarang, bukan yang dikira bakal datang. Dua sisi itu harus dipegang barengan. Di startup, switch plus default -> throw sering cukup. Di tim besar yang banyak orang nambah varian, jaminan dari compiler lebih berharga.

   jangan di lakukan:
   1. Komentar "melanggar SRP" tanpa bilang apa yang bakal rusak. Penulis cuma dapet label.
   2. Minta refactor besar di PR bugfix kecil.
   3. Cuma baca file yang berubah, jadi duplikasi keputusan gak pernah keliatan.

   Tes Feynman: bentuk kode yang bagus bikin perubahan berikutnya cukup dilakuin di satu tempat, atau teriak kalau ada yang kelewat.

   Aturan Emas: Jangan komentar nama prinsipnya, komentar apa yang rusak waktu kode ini diubah lagi.

   Terapin: tiap kali mau ngetik "melanggar SOLID", ganti jadi "kalau nambah X, bagian Y bakal kelewat".

   # Readability, naming, dan cognitive load

   Kode lebih sering dibaca daripada ditulis, jadi biaya bingung itu dibayar berulang.

   real world:
   Panduan code review Google (eng-practices) nyaranin: kalau reviewer gak ngerti sebuah kode, minta penulis bikin kodenya lebih jelas, bukan cuma jelasin di thread review. Penjelasan di thread gak kebawa ke orang yang baca kodenya tahun depan. Di startup, tiga orang bisa saling tanya langsung. Di perusahaan besar, penulisnya mungkin udah pindah tim, jadi kodenya harus bisa ngejelasin diri sendiri. Urusan format serahin ke formatter dan linter, biar tenaga reviewer buat makna.

   jangan di lakukan:
   1. Komentar "kurang readable" tanpa bilang di mana lu bingung.
   2. Maksa gaya pribadi sebagai aturan.
   3. Nerima komentar kode sebagai tambalan nama yang buruk, padahal nama bisa diganti.
   4. Ganti nama sambil ngubah logika di commit yang sama, jadi susah diverifikasi.

   best case:
   tiap PR yang nambah method publik, field, parameter, atau percabangan bertingkat.

   Tes Feynman: kode itu gampang dibaca kalau orang baru bisa nebak perilakunya dengan bener tanpa bongkar isinya.

   Aturan Emas: Jangan tanya "gua ngerti gak?", tanya "orang yang baru baca bakal salah nebak apa?"

   Terapin: tiap kali lu baca ulang satu baris dua kali waktu review, tulis kenapa.

   # Clean Code Principles

   dry : don't refeat yourself
      Jangan menulis aturan yang sama berulang kali.

   kiss : keep it simple stupid
      Pilih solusi paling sederhana yang bisa menyelesaikan masalah.

   YAGNI: (You Aren't Gonna Need It)
      Jangan membuat sesuatu sebelum benar-benar dibutuhkan.

      Jangan menambahkan fitur atau abstraksi hanya karena mungkin berguna di masa depan.

      YAGNI ada batasnya. Test, validasi input, dan cara rollback bukan "nanti aja". Keputusan yang mahal dibalik, kayak skema database atau kontrak API publik, layak dipikirin lebih jauh.

   SoC : (Separation of Concerns)
      Pisahkan kode berdasarkan tanggung jawabnya.

      Setiap bagian kode sebaiknya fokus pada urusannya masing-masing.
   
   jangan di lakukan:
   1. Komentar "DRY" tiap lihat dua blok mirip.
   2. Pakai prinsip sebagai stempel, tanpa bilang apa yang bakal rusak.
   3. Maksa refactor besar di PR bugfix kecil. Cukup bikin ticket.

   best case:
   nambah helper bersama, base class, config, flag, atau nyalin logika bisnis.

   Tes Feynman: satuin aturan yang emang satu, bukan baris yang kebetulan mirip.

   Aturan Emas: Sebelum gabungin dua hal yang mirip, tanya siapa yang berhak ngubah masing-masing.

   Terapin: tiap mau ngetik "duplikat nih" di review, cek dulu pemilik dua aturan itu.

   # Code Smells & Refactoring Techniques

   Code smell itu tanda di permukaan yang sering nunjuk ke masalah desain. Bukan bug, bukan vonis. Refactoring itu ngubah struktur tanpa ngubah perilaku.

   Long Method : Butuh komentar buat misahin bagian
   fix : Extract Method

   Long Parameter List : Lima argumen, urutannya rawan ketuker
   fix : Introduce Parameter Object

   Primitive Obsession : Uang, status, ID cuma String/long
   fix : Replace Primitive with Object

   Feature Envy : Method lebih sibuk sama data class lain
   fix : move method

   Repeated Switches : switch tipe yang sama di mana-mana
   fix : Enum atau polymorphism

   Shotgun Surgery : Satu fitur nyentuh banyak file
   fix : Kumpulin ke satu tempat

   jangan di lakukan:
   1. Nyebut nama smell tanpa dampak.
   2. "Refactor" yang sekalian ngubah perilaku diam-diam.
   3. Nahan PR hotfix demi bersih-bersih.

   Tes Feynman: rapiin susunan kode tanpa ngubah kelakuannya, biar perubahan berikutnya gak gampang kelewat.

   Aturan Emas: Kalau kerjaan bergantung sama ingatan orang, ubah susunannya, jangan cuma minta lebih teliti.

   Terapin: abis ada yang kelupaan, tanya "gimana caranya ini gak bisa dilupain?" sebelum bilang "lain kali hati-hati".

   # Method Design, Class Design, Cohesion & Coupling

   Method design
      Method adalah kontrak antara pembuat method dan pemanggilnya.

      Prinsipnya: method harus mudah digunakan dengan benar dan sulit digunakan dengan salah.
   
   Class design
      Class adalah tempat mengelompokkan data dan perilaku yang memiliki tanggung jawab berkaitan.

      Prinsipnya: satu class sebaiknya memiliki satu kelompok tanggung jawab yang jelas.
   
   Cohesion
      Cohesion mengukur seberapa erat hubungan antara method dan data dalam satu class.

      High Cohesion = isi class saling berkaitan.
   
   Coupling
      Coupling mengukur seberapa besar satu class bergantung pada class lainnya.

      Low Coupling = perubahan pada satu class tidak mudah merusak class lain.

   Target desain kode yang baik:
   - Good Method Design: method punya kontrak yang jelas.
   - Good Class Design: tanggung jawab class jelas.
   - High Cohesion: isi class saling berkaitan.
   - Low Coupling: ketergantungan antar-class minimal.

   Pecah class vs biarin. Class kecil-kecil gampang dites, tapi kalau kebanyakan, alurnya susah diikutin. Pecah kalau alasan berubahnya beda, bukan karena jumlah baris.

   real world:
   Di organisasi besar, coupling jadi urusan antar tim: satu tim gak bisa rilis tanpa nunggu tim lain, jadi batasnya biasanya dijaga lewat ownership modul dan tes arsitektur kayak ArchUnit.

   jangan di lakukan:
   1. Komentar "ini coupling-nya tinggi" tanpa nyebut perubahan apa yang jadi susah.
   2. Nuntut redesign total di PR kecil.
   3. Ngira banyak class otomatis desain bagus.

   best case:
   PR yang nambah method publik, nambah parameter ke method lama, atau nambah dependency ke class yang udah rame.

   Tes Feynman: bikin kode yang susah dipake salah, dan tiap bagiannya cuma perlu tau seperlunya.

   Aturan Emas: Bikin cara yang benar jadi cara yang paling gampang.

   Terapin: sebelum nyerahin method baru, tulis dulu baris pemanggilnya dan tanya apakah orang baru bisa nebak artinya.

   # Error handling, logging, observability

   Tiap catch di diff harus bisa jawab: abis ini apa? Cuma ada beberapa jawaban sah: 
   1. coba lagi, pake fallback yang emang disetujui bisnis, terjemahin jadi exception yang lebih bermakna (cause dibawa).
   2. lempar ke atas. "Diem aja" bukan salah satunya.

   Logging nyeritain satu kejadian. Observability itu kemampuan jawab "kenapa sistem begini?" dari luar, biasanya lewat tiga sinyal:

   metric : ada masalah gak, seberapa parah   -> alert
   trace  : masalahnya di hop mana            -> cari lokasi
   log    : persisnya kejadian apa            -> cari sebab

   Level juga bagian dari desain:
      ERROR buat yang butuh ditindak,
      WARN buat yang janggal tapi ketangani,
      INFO buat kejadian bisnis penting.
      Input user yang salah bukan ERROR.

   real world:
   Di startup, log terstruktur plus satu alert error rate udah jauh lebih baik daripada nol. Di perusahaan besar biasanya ada tracing (misalnya OpenTelemetry), dashboard, SLO, dan runbook per alert.

   jangan di lakukan:
   1. Minta "tambahin log" tanpa bilang pertanyaan apa yang mau dijawab log itu.
   2. Cuma ngetes happy path, jalur gagal gak pernah dijalanin.
   3. Nganggep ada try-catch berarti error udah ditangani.
   
   best case:
   PR yang nyentuh I/O: HTTP client, database, Kafka consumer, job terjadwal, webhook.

   Tes Feynman: kalau ada yang gagal, harus ada yang tau, dan tau di mana nyarinya.

   Aturan Emas: Gagal boleh, gagal diam-diam jangan.

   Terapin: tiap nulis atau nge-review catch, tanya "siapa yang bakal tau, dan dari mana?"

   # Menilai kecukupan testing (coverage vs meaningful tests)

   Coverage nunjukin yang belum dijalanin, bukan bukti yang dijalanin udah bener.
   
   Coverage ≠ kualitas test.
   - Coverage menjawab: baris kode mana yang dieksekusi?
   - Assertion menjawab: hasilnya benar atau salah?
   - Coverage tinggi belum menjamin bug terdeteksi.

   Prinsip utama code review:
   Kalau baris ini salah, test mana yang bakal merah?

   test name : Nama menjelaskan perilaku yang diuji.
   assertion : Memastikan hasil benar, bukan sekadar tidak null.
   input : Menguji normal case dan boundary case.
   failure path : Menguji kondisi gagal, timeout, dan exception.
   mock : Menguji perilaku, bukan sekadar urutan pemanggilan.
   bug fix : Ada regression test yang membuktikan bug tertangkap.
   flaky test : Hindari sleep(), waktu tidak terkontrol, dan shared state.

   Level test juga dinilai: dengan pyramid test

   jangan di lakukan:
   1. Komentar "tambahin test" tanpa nyebut skenario mana.
   2. Approve karena coverage ijo, file test-nya gak dibuka.
   3. Test yang nyalin rumus dari implementasi, jadi salahnya ikut disalin.

   Tes Feynman: test yang bagus itu yang teriak kalau kodenya salah, bukan yang cuma ngejalanin kodenya.

   Aturan Emas: Dilewatin belum tentu diperiksa.

   Terapin: sebelum approve, pilih satu baris penting di diff dan tanya "kalau ini salah, test mana yang merah?"


