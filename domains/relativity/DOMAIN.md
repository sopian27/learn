# Teori Relativitas — Domain Pack

## Domain Name

Teori Relativitas (Relativitas Khusus & Umum / Special & General Relativity)

## Scope

Fisika relativitas dari fondasi konseptual sampai level yang setara mahasiswa pascasarjana awal fisika teoretis, mencakup:

- **Prasyarat cepat**: kenapa mekanika Newton mulai retak (invariansi Galileo vs elektrodinamika Maxwell, eksperimen Michelson-Morley), aljabar linear & kalkulus vektor secukupnya untuk notasi tensor nanti.
- **Relativitas Khusus (Special Relativity)**: dua postulat Einstein, transformasi Lorentz, dilatasi waktu, kontraksi panjang, relativitas simultanitas, diagram spacetime (Minkowski), paradoks kembar, energi-momentum relativistik (E=mc², 4-momentum), elektrodinamika relativistik secukupnya untuk motivasi ke Relativitas Umum.
- **Matematika jembatan**: kalkulus tensor, geometri diferensial (manifold, metrik, Christoffel symbols, kurvatur Riemann) — diajarkan sebagai alat, bukan matematika murni abstrak.
- **Relativitas Umum (General Relativity)**: prinsip ekivalensi, persamaan medan Einstein, metrik Schwarzschild, precession perihelion Merkurius, pembelokan cahaya, lubang hitam (horizon peristiwa, singularitas, Penrose diagram secukupnya), gelombang gravitasi (dasar matematis di balik deteksi LIGO), pengantar kosmologi relativistik (metrik FLRW, ekspansi alam semesta).
- **Konteks eksperimental & aplikasi nyata**: bagaimana GPS mengoreksi dilatasi waktu relativistik & gravitasional, deteksi gelombang gravitasi LIGO/Virgo, bukti pembelokan cahaya (ekspedisi Eddington 1919), Global Positioning sebagai studi kasus "relativitas dipakai di produksi."

Secara eksplisit TIDAK mencakup:

- **Mekanika klasik/Newtonian secara penuh** (hukum Newton, mekanika Lagrangian/Hamiltonian non-relativistik dari nol) — diasumsikan sudah dikuasai atau di-refresh singkat sebagai prasyarat, bukan diajarkan ulang dari dasar mutlak.
- **Mekanika kuantum & Quantum Field Theory di ruang lengkung** — perpotongan relativitas dengan kuantum (mis. radiasi Hawking, quantum gravity, string theory) disebut sebagai "ke mana arah riset lanjut" di akhir course, tapi tidak diajarkan mendalam — itu di luar scope dan butuh domain terpisah di masa depan kalau dibutuhkan.
- **Astrofisika observasional teknis** (kalibrasi instrumen teleskop, reduksi data mentah) — dipakai secukupnya sebagai konteks bukti (mis. data LIGO, data Event Horizon Telescope), bukan diajarkan sebagai disiplin observasional tersendiri.
- **Filsafat waktu/ruang murni** (debat A-theory vs B-theory of time, dll) — disentuh sebagai catatan menarik saat relevan, bukan cabang filsafat tersendiri.

## Trusted Sources / Research Priority

1. **Karya asli Einstein** — "Zur Elektrodynamik bewegter Körper" (1905, fondasi Relativitas Khusus) dan "Die Feldgleichungen der Gravitation" (1915, persamaan medan Relativitas Umum). Prioritas tertinggi sebagai sumber primer historis — dipakai untuk memahami logika asli penemuan, bukan sebagai referensi pengajaran utama (notasi modern jauh lebih jernih).
2. **Edwin F. Taylor & John A. Wheeler**, *Spacetime Physics* — buku standar pengantar Relativitas Khusus paling dihormati secara pedagogis, dipakai sebagai tulang punggung modul Relativitas Khusus.
3. **Bernard Schutz**, *A First Course in General Relativity* — buku pengantar GR yang paling umum dipakai untuk transisi dari sarjana ke pascasarjana, menyeimbangkan rigor matematis dengan aksesibilitas. Tulang punggung modul Relativitas Umum tingkat menengah.
4. **Charles Misner, Kip Thorne, John Wheeler**, *Gravitation* (MTW) — referensi definitif/"kitab suci" GR untuk kedalaman level expert (lubang hitam, gelombang gravitasi, kosmologi lanjut); dipakai di modul-modul akhir.
5. **Sean Carroll**, *Spacetime and Geometry* (buku + lecture notes yang tersedia terbuka di situs pribadinya) — sintesis modern yang lebih mudah diakses daripada MTW untuk level lanjut, dipakai untuk cross-check notasi dan penjelasan konsep kurvatur.
6. **The Feynman Lectures on Physics, Vol. I** (Ch. 15-17, tersedia gratis di feynmanlectures.caltech.edu) — intuisi fisis tentang relativitas khusus dari salah satu pengajar fisika terbaik; dipakai di tahap awal ("dari 0") untuk membangun intuisi sebelum masuk formalisme berat.
7. **MIT OpenCourseWare** — 8.033 (Relativity) dan 8.962 (General Relativity) — materi kuliah universitas riil, lengkap dengan problem set & solusi, dipakai untuk struktur latihan soal terverifikasi.
8. **Leonard Susskind**, *The Theoretical Minimum: Special Relativity and Classical Field Theory* (buku + kuliah video Stanford Continuing Studies, gratis di YouTube) — dirancang khusus untuk pembelajar dewasa yang belajar mandiri dari nol, dipakai sebagai jembatan awal yang ramah sebelum ke Taylor & Wheeler/Schutz.
9. **LIGO Scientific Collaboration** (ligo.caltech.edu) dan paper deteksi gelombang gravitasi pertama (Abbott et al. 2016, *Physical Review Letters*) — dipakai sebagai bukti eksperimental modern & studi kasus aplikasi nyata GR.
10. **Khan Academy / PBS Space Time / Veritasium** — untuk orientasi awal yang sangat ramah pemula dan visualisasi intuitif; eksplisit bersifat SIMPLIFIKASI, tidak pernah jadi sumber tunggal untuk klaim kuantitatif atau derivasi — selalu disilangkan ke sumber 2-7.

Tidak pernah membangun satu lesson kuantitatif (rumus, derivasi, angka) hanya dari sumber 10 tanpa menyilangkan ke sumber akademik (2-8). Klaim yang bergantung pada notasi tensor selalu diverifikasi silang antara minimal dua buku (mis. Schutz vs Carroll) karena konvensi tanda (sign convention) berbeda antar penulis dan sering jadi sumber kebingungan pemula.

## Applied Practice Definition

**Derivation & Simulation Lab** — tiap modul diterapkan lewat dua jenis praktik yang saling melengkapi:

1. **Derivasi tangan** — menurunkan persamaan kunci modul itu sendiri dari postulat/prinsip dasar (bukan menghafal rumus jadi), diperiksa langkah demi langkah termasuk analisis dimensi dan limit-check (apakah rumus relativistik kembali ke rumus Newtonian saat v << c atau medan gravitasi lemah — kalau tidak, ada kesalahan).
2. **Simulasi/tooling kecil** (memanfaatkan skill software engineering yang sudah dipunya learner di domain [[domains/software-engineering/DOMAIN.md|software-engineering]]) — mis. kalkulator dilatasi waktu, plotter diagram spacetime Minkowski, simulator presesi perihelion, atau visualisasi light cone — dikode dengan bahasa pilihan learner, sehingga konsep abstrak jadi sesuatu yang bisa dijalankan dan dieksperimen ulang parameternya.

Setiap modul harus menghasilkan minimal satu derivasi tertulis lengkap DAN satu artefak simulasi/kode kecil sebelum dianggap selesai — teori tanpa derivasi tangan gampang jadi hafalan rumus semu, dan derivasi tanpa simulasi kurang membangun intuisi numerik.

## Project Tiers

- **Mini Project** (per modul): satu Derivation & Simulation Lab tunggal — derivasi lengkap dari satu hasil kunci modul (mis. transformasi Lorentz) plus program kecil yang mendemonstrasikannya secara interaktif/numerik.
- **Intermediate Project** (per beberapa modul): studi kasus terapan nyata yang menggabungkan beberapa konsep — mis. "Kenapa GPS Meleset Tanpa Koreksi Relativistik": derivasi dilatasi waktu kinematik + gravitasional untuk satelit GPS, dihitung angka riilnya (mikrodetik/hari), dan disimulasikan dampaknya kalau tidak dikoreksi.
- **Capstone / Portfolio Project** (akhir course): **Relativity Portfolio** — pilih satu topik expert-level (presesi perihelion Merkurius dari metrik Schwarzschild, pembelokan cahaya gravitasional, atau estimasi strain gelombang gravitasi ala LIGO untuk sistem biner sederhana), kerjakan derivasi penuh dari persamaan medan Einstein/geodesic sampai hasil akhir, plus simulasi/visualisasi yang menunjukkan hasilnya secara numerik — disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap review Derivation & Simulation Lab juga mengecek:

- **Derivational Rigor** — apakah tiap langkah derivasi punya justifikasi eksplisit (postulat/aturan mana yang dipakai), atau ada lompatan logika/aljabar yang disembunyikan?
- **Dimensional & Limit Consistency** — apakah satuan konsisten di tiap langkah, dan apakah hasil akhir kembali ke limit Newtonian yang benar saat parameter relativistik (v/c atau kelengkungan) dikecilkan menuju nol?
- **Conceptual vs Rote** — apakah learner bisa menjelaskan MENGAPA hasil itu masuk akal secara fisis (bukan cuma mengutak-atik aljabar sampai ketemu jawaban buku), termasuk lewat thought experiment/gedankenexperiment sendiri?
- **Simulation Fidelity** — apakah kode simulasi benar-benar mengimplementasikan fisika yang diturunkan (bukan angka yang di-hardcode supaya "terlihat benar"), dan apakah hasil numeriknya dibandingkan ke nilai referensi yang diketahui?

## Assessment Form

Quiz konsep singkat setelah bagian teori tiap lesson (skenario "apa yang diamati observer A vs observer B" untuk menguji pemahaman relativitas simultanitas/frame, bukan hafalan rumus), problem set numerik terarah gaya MIT OCW dengan kunci jawaban untuk self-check, dan sesi Socratic derivation — learner diminta menurunkan satu langkah kunci sebelum diberi tahu jawabannya, sesuai `# Challenge` di `CLAUDE.md` root ("Reveal the full solution only after I have attempted it").

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk mendekati topik yang sama:

- **Fisikawan teoretis akademik** (gaya Schutz/Carroll/MTW) — rigor matematis penuh, derivasi dari aksioma, nyaman dengan abstraksi tensor berlapis.
- **Fisikawan eksperimental** (gaya kolaborasi LIGO) — fokus pada "bagaimana ini diverifikasi/diukur secara nyata," toleransi error, desain eksperimen untuk mengisolasi efek relativistik yang sangat kecil.
- **Insinyur terapan** (gaya tim GPS/satelit) — relativitas sebagai koreksi teknik yang harus dihitung presisi dan diimplementasikan dalam sistem produksi nyata, bukan objek kontemplasi teoretis — titik temu alami dengan latar belakang software engineering learner.
- **Pengajar/komunikator sains** (gaya Feynman/Susskind/PBS Space Time) — cara membangun intuisi fisis sebelum formalisme, dipakai terutama di modul-modul awal ("dari 0").

## Domain-Specific Standards

`domains/relativity/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Notasi & sign convention selalu dinyatakan eksplisit di awal tiap modul GR** (mis. signature metrik (-,+,+,+) vs (+,-,-,-)) karena ini sumber kebingungan nomor satu pemula yang belajar dari banyak buku sekaligus — konsisten dengan bar "Vetting a Source" di `standards/UNIVERSAL_STANDARDS.md`.
- **Tidak pernah melompat dari 0 langsung ke tensor Riemann tanpa fondasi**: urutan Learning Loop (`ai-los/CORE_LOS.md`) tetap dipegang ketat di domain ini — Relativitas Khusus tuntas dulu sebagai fondasi konseptual (frame, invariansi, spacetime) sebelum notasi tensor penuh diperkenalkan untuk Relativitas Umum.
- **Klaim populer yang sering salah kaprah** (mis. "semua benda memendek jadi lebih berat," "relativitas berarti semua serba relatif/subjektif") dikoreksi eksplisit begitu muncul, dengan penjelasan mengapa salah kaprah itu terjadi — bukan dibiarkan lewat begitu saja demi kesederhanaan.

## Domain Goal

Akan disepakati final bersama learner saat `courses/relativity/roadmap.md` dibuat dan di-approve (domain ini didaftarkan Paused dulu, roadmap belum dibuat) — sementara: mampu memahami dan menurunkan sendiri hasil-hasil kunci Relativitas Khusus dan Umum dari prinsip dasar (bukan menghafal rumus), menghubungkannya ke bukti eksperimental nyata (GPS, LIGO, presesi Merkurius), dan mampu mengimplementasikan/mensimulasikan konsepnya secara numerik — dari titik nol (belum tahu apa-apa soal relativitas) sampai level yang setara mahasiswa pascasarjana awal fisika teoretis yang bisa membaca paper GR tingkat pengantar secara mandiri.
