# Chess Mastery — Domain Pack

**Status: DRAFT — dibangun 2026-08-13 atas permintaan user ("Buat course catur dari 0 hingga expert: aturan dasar, opening, tactics, calculation, positional play, endgame, strategy, master games, tournament training, analisis engine, dan roadmap menuju level GM"), menunggu approval sebelum Lesson 1.**

## Domain Name

Chess Mastery (Catur dari 0 sampai Expert — Aturan Dasar, Opening, Tactics, Calculation, Positional Play, Endgame, Strategy, Master Games, Tournament Training, Analisis Engine, dan Roadmap Menuju Grandmaster)

## Scope

Membangun kekuatan catur praktis dari nol total (belum tahu cara bidak jalan) sampai level expert yang bisa bersaing di turnamen berperingkat, lewat jalur pembelajaran yang sama dipakai pemain kuat sungguhan: fondasi aturan & notasi, pengenalan pola taktik, prinsip opening (bukan hafalan buta), metode kalkulasi terstruktur (pohon analisis Kotov), evaluasi posisi lewat elemen/imbalance (Silman), endgame fundamental sampai lanjut (Dvoretsky), strategi/struktur pion & prophylaxis (Nimzowitsch, Flores Rios), studi partai master (Fischer, Chernev), latihan turnamen praktis (manajemen jam, psikologi kompetisi), analisis berbantuan engine yang disiplin (bukan jadi tongkat yang mematikan kemampuan berpikir sendiri), sampai peta jalan realistis menuju gelar FIDE (CM → FM → IM → GM).

Cakupan konkret: aturan resmi FIDE & notasi aljabar, pola mat dasar, pola taktik (fork/pin/skewer/discovered attack/kombinasi), prinsip opening & filosofi repertoar, kalkulasi & visualisasi (candidate moves, tree of analysis), elemen posisional (material, ruang, waktu/tempo, struktur pion, kualitas minor piece), endgame teoretis & praktis (king & pawn, rook endgame, minor piece endgame), studi partai master beranotasi, metode latihan spaced-repetition (Woodpecker Method), analisis pasca-partai dengan engine (Stockfish/NNUE), sistem rating (Elo/FIDE), dan jalur norma gelar FIDE.

Secara eksplisit TIDAK mencakup:

- **Membangun/memprogram engine catur sendiri** (algoritma minimax/alpha-beta, NNUE training, UCI protocol implementation) — itu proyek software engineering murni, arahkan ke domain `software-engineering` kalau user tertarik membangun engine sebagai proyek koding, bukan diajarkan sebagai bagian jalur peningkatan kekuatan bermain di domain ini.
- **Varian catur non-standar** (Chess960/Fischer Random, bughouse, three-check, dll.) di luar sebagai catatan singkat pengenalan — course ini fokus penuh ke catur standar (classical chess) karena itu yang dipakai jalur rating & gelar FIDE.
- **Psikologi kompetisi umum/mental toughness generik** (manajemen kecemasan, overthinking, regulasi emosi di luar konteks papan) — domain `emotional-resilience`/`intuition-training` untuk itu; domain ini hanya membahas sisi catur-spesifik (manajemen jam, rutinitas pra-turnamen, pemulihan dari blunder di tengah partai).
- **Mengajar/coaching orang lain bermain catur** secara formal (pedagogi, sertifikasi pelatih FIDE) — course ini untuk kekuatan bermain pribadi (playing strength), bukan menjadi pelatih.

## Trusted Sources / Research Priority

1. **FIDE Handbook — Laws of Chess & International Title Regulations** (fide.com/handbook, edisi berlaku terbaru) — sumber resmi & otoritatif untuk aturan permainan (Module 1) dan sistem gelar/norma CM-FM-IM-GM serta rating Elo (Module 12). Prioritas tertinggi karena ini bukan opini, tapi regulasi resmi badan pengatur catur dunia.
2. **Jeremy Silman — *How to Reassess Your Chess* (edisi ke-4) & *The Amateur's Mind*** — kerangka "imbalances" (material, ruang, pion mayoritas, kualitas minor piece, kontrol file/petak kunci, inisiatif) untuk evaluasi posisi terstruktur; dianggap standar emas literatur positional play untuk pemain club-to-expert. Dasar utama Module 5 & 8.
3. **Alexander Kotov — *Think Like a Grandmaster*** — metode "tree of analysis" & candidate moves untuk kalkulasi terstruktur, klasik yang jadi dasar hampir semua kurikulum kalkulasi modern meski dikritik sebagian pelatih sebagai terlalu kaku untuk pemula — disilangkan ke sumber 4 (Dvoretsky) untuk keseimbangan. Dasar utama Module 4.
4. **Mark Dvoretsky & Artur Yusupov — *Dvoretsky's Endgame Manual*, seri *Yusupov's Build Up Your Chess*** — Dvoretsky dianggap salah satu pelatih catur paling dihormati di dunia (murid-muridnya termasuk banyak juara dunia/top GM); metodologi berpikir-kalkulasi-pemahaman (bukan hafalan) jadi rujukan endgame lanjut (Module 6, 10) dan kalkulasi (Module 4).
5. **Aron Nimzowitsch — *My System*** — teks klasik strategi: prophylaxis, overprotection, blockade, kontrol petak lemah. Dasar utama Module 8 (positional play lanjut/strategi).
6. **Mauricio Flores Rios — *Chess Structures: A Grandmaster Guide*** — referensi modern untuk struktur pion spesifik (Isolani, Carlsbad, Hedgehog, dll.) dengan rencana konkret per struktur, melengkapi teks klasik Nimzowitsch dengan pendekatan lebih sistematis/modern. Dasar tambahan Module 8.
7. **Axel Smith & Hans Tikkanen — *The Woodpecker Method* & *The Woodpecker Method 2***. Smith naik dari rating 2100 ke gelar GM sebagai pemain dewasa memakai metode ini; Tikkanen meraih 3 norma GM dengan metode yang sama — bukti empiris praktisi nyata, bukan klaim tanpa hasil. Metodologi spaced-repetition puzzle solving jadi dasar utama Module 2 & 7 (tactics training).
8. **Irving Chernev — *Logical Chess: Move by Move*; Bobby Fischer — *My 60 Memorable Games*; David Bronstein — *Zurich International Chess Tournament 1953*** — metodologi studi partai master beranotasi lengkap, tiap langkah dijelaskan alasannya (bukan cuma notasi mentah). Dasar utama Module 9.
9. **Fernand Gobet (cognitive scientist) — riset "template theory" & chunking pada pemain catur** (memperluas riset Chase & Simon 1973) — dasar ilmiah kenapa pattern recognition lewat repetisi/paparan posisi nyata efektif untuk pemula membangun basis pengetahuan sebelum kalkulasi mendalam; dipakai untuk menyeimbangkan pendekatan Kotov (murni kalkulasi) dengan kebutuhan pemula membangun "knowledge base" dulu. Dasar tambahan Module 2-3.
10. **Lichess.org & Chess.com** — platform latihan nyata: puzzle rating system (Applied Practice tactics), opening explorer berbasis database, analisis game dengan engine terintegrasi (Stockfish NNUE open-source), fitur studi/broadcast turnamen nyata. Dipakai sebagai alat, bukan sumber teori.
11. **Stockfish (open-source, UCI-compliant engine)** — engine analisis standar industri (kekuatan >3500 Elo, jauh di atas manusia terkuat) untuk Module 11 (analisis engine); dipasangkan wajib dengan house rule disiplin urutan pemakaian (lihat "Domain-Specific Standards").

Tidak pernah membangun satu lesson murni dari satu sumber populer tanpa menyilangkan ke sumber pelatih/riset yang lebih dalam (mis. video YouTube pemula selalu disilangkan ke Silman/Kotov/Dvoretsky sebelum jadi dasar konten inti).

## Applied Practice Definition

Praktik nyata terhadap posisi/partai sungguhan, bukan cuma membaca teori: **Puzzle/Tactics Training Log** (sesi puzzle solving terukur — akurasi & waktu, dipakai untuk siklus Woodpecker Method: kumpulan soal yang sama diulang makin cepat), **Calculation Worksheet** (posisi nyata dianalisis pakai pohon candidate moves Kotov secara tertulis sebelum dicocokkan ke solusi/engine), **Game Log** (partai learner sendiri — online rated atau OTB — dicatat & dianotasi manual dulu sebelum dibantu engine), **Opening Repertoire Notebook** (baris opening yang dipilih beserta rencana middlegame-nya, bukan cuma daftar langkah hafalan), **Endgame Technique Drill** (posisi endgame teoretis dimainkan sampai tuntas melawan engine/lawan, bukan cuma dibaca), dan **Engine Analysis Review** (partai dianalisis manual dulu, baru dibandingkan ke evaluasi engine — mengukur gap antara penilaian sendiri dan evaluasi objektif). Posisi/soal generik hanya jembatan sementara sebelum learner punya cukup partai sendiri untuk dianalisis.

## Project Tiers

- **Mini Project** (per modul): satu Puzzle Log/Calculation Worksheet/Game Log/Repertoire Notebook/Endgame Drill/Engine Review sesuai fokus modul.
- **Intermediate Project** (checkpoint setelah Module 1-6): **Personal Game Analysis Report** — anotasi lengkap 3-5 partai learner sendiri (opening, momen taktik, kalkulasi kritis, evaluasi posisional), ditulis SEBELUM konsultasi engine, lalu dibandingkan.
- **Capstone / Portfolio Project** (akhir course): **Personal Chess Improvement System** — sistem latihan pribadi terkodifikasi (rotasi puzzle Woodpecker, repertoar opening dengan rencana per struktur, checklist evaluasi posisional, protokol kalkulasi, program endgame, dan jadwal turnamen/rating tracking) yang sudah dijalankan nyata minimal 6-8 minggu dengan data rating/skor puzzle terukur sebelum-sesudah, plus peta jalan pribadi menuju milestone gelar FIDE berikutnya (CM/FM/IM/GM sesuai rating saat ini), disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak juga dicek lewat lima lensa:

- **Tactical Accuracy Check** — untuk Puzzle Log: akurasi & pola yang terlewat, apakah kesalahan berulang di jenis taktik tertentu (mis. selalu lewat discovered attack) yang butuh latihan terfokus.
- **Candidate-Move Discipline Check** — untuk Calculation Worksheet: apakah learner benar-benar memakai pohon analisis terstruktur (Kotov) — mendata semua candidate move dulu sebelum mendalami satu cabang — bukan asal menghitung satu jalur favorit lalu berhenti.
- **Imbalance Identification Check** — untuk evaluasi posisi: apakah elemen Silman (material/ruang/struktur pion/kualitas minor piece/kontrol petak/inisiatif) diidentifikasi lengkap & akurat, bukan cuma "posisi ini bagus/jelek" tanpa alasan konkret.
- **Opening Principle Adherence Check** — untuk Repertoire Notebook: apakah baris yang dipilih dipahami rencana middlegame-nya (bukan hafalan buta), dan prinsip dasar (kontrol pusat, keamanan raja, pengembangan) tidak dilanggar tanpa alasan konkret.
- **Engine-Deviation Check** — untuk Engine Analysis Review: apakah urutan wajib dipatuhi (analisis manual dulu, baru buka engine), dan gap antara evaluasi sendiri vs engine dipakai untuk belajar (bukan sekadar mengoreksi tanpa memahami kenapa).

## Assessment Form

Tes puzzle rating terukur (tactics, dengan batas waktu), kuis evaluasi posisi (mengidentifikasi imbalance dari posisi yang diberikan), latihan kalkulasi dengan batas waktu (memakai worksheet candidate moves), spaced-repetition review cycle (metode Woodpecker — set puzzle yang sama diulang, mengukur peningkatan kecepatan & akurasi), dan partai latihan terukur (online rated atau lawan sungguhan, hasil & performance rating dicatat).

## Practitioner Reference Frame

Bandingkan beberapa tradisi pelatihan untuk isu yang sama — "bagaimana pemain kuat sungguhan membangun kekuatannya": tradisi Soviet klasik (Dvoretsky-Yusupov — metodologi berpikir mendalam, kalkulasi presisi, pemahaman posisi lewat prinsip) vs tradisi modern berbantuan data/engine (pelatihan era AlphaZero/Stockfish — evaluasi objektif, gaya dinamis yang lebih berani secara materi) vs metode empiris berbasis repetisi terukur (Axel Smith/Woodpecker — bukti nyata seorang pemain dewasa naik ke gelar GM lewat latihan taktik terstruktur, bukan bakat bawaan). Juga bandingkan gaya positional klasik (Nimzowitsch/Karpov — prophylaxis, kontrol jangka panjang) vs gaya dinamis modern (Kasparov/Carlsan — inisiatif, ketidakseimbangan aktif) sebagai dua filosofi sah yang sama-sama didukung hasil turnamen nyata, bukan satu "cara benar" tunggal.

## Domain-Specific Standards

`domains/chess-mastery/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Engine dipakai setelah analisis mandiri, bukan sebelum.** Mengikuti prinsip pelatihan Dvoretsky/Yusupov: konsultasi engine sebelum mencoba menganalisis sendiri secara sistematis merusak kemampuan kalkulasi & evaluasi independen (jadi "engine crutch"). Urutan wajib: analisis manual tertulis → baru buka engine untuk verifikasi/koreksi.
- **Larangan tegas memakai bantuan engine/analisis eksternal saat bermain partai resmi (rated online atau OTB).** Ini pelanggaran fair play serius di seluruh dunia catur (dasar dari FIDE Anti-Cheating Regulations & kebijakan chess.com/lichess) — domain ini tidak pernah mengajarkan atau menoleransi cara "curang" untuk menang, baik online maupun over-the-board. Engine hanya untuk analisis PASCA-partai atau latihan solo, tidak pernah dipakai real-time saat kompetisi berjalan.
- **Opening tidak boleh dihafal buta tanpa paham rencana.** Konsisten dengan Silman/Nimzowitsch: setiap baris di Repertoire Notebook wajib disertai alasan strategis (kenapa langkah ini, rencana middlegame apa yang mengikutinya) — hafalan langkah tanpa pemahaman ditandai sebagai belum lengkap.
- **Rating & gelar adalah milestone, bukan identitas.** Progres diukur dari data terukur (akurasi puzzle, hasil Calculation Worksheet, performance rating partai), bukan cuma perasaan "sudah jago" — dan kekalahan/blunder dievaluasi dari kualitas proses berpikir saat itu (bukan cuma hasil akhir partai), sejalan dengan prinsip menghindari resulting bias yang juga dipakai domain `intuition-training`.
- **Bukan pengganti pelatih catur berlisensi untuk jalur kompetitif serius.** Kalau learner menargetkan norma FIDE nyata (FM/IM/GM), course ini memberi fondasi & sistem latihan solid, tapi disclaimer eksplisit: pelatih personal (apalagi bersertifikat FIDE) dan turnamen langsung sangat direkomendasikan di level itu — course ini tidak menggantikan keduanya.

## Domain Goal

Learner memiliki kekuatan catur praktis yang teruji nyata — memahami aturan & notasi sepenuhnya, mengenali pola taktik umum dengan cepat & akurat, punya repertoar opening yang dipahami (bukan dihafal), mampu mengkalkulasi posisi kompleks lewat metode candidate moves terstruktur, mengevaluasi posisi lewat elemen/imbalance yang konkret, menguasai teknik endgame fundamental sampai lanjut, memahami strategi & struktur pion jangka panjang, sudah mempelajari partai master secara mendalam (bukan sekadar menonton), mampu bermain turnamen dengan manajemen jam & rutinitas yang matang, menganalisis partainya sendiri secara disiplin (manual dulu, engine kedua), dan punya peta jalan pribadi realistis menuju gelar FIDE berikutnya — dibuktikan lewat data rating/puzzle terukur dan sistem latihan pribadi yang benar-benar berjalan, bukan cuma rasa percaya diri.

## Registration History

Registered 2026-08-13, domain pack + course roadmap dibangun atas permintaan user ("Buat course catur dari 0 hingga expert: aturan dasar, opening, tactics, calculation, positional play, endgame, strategy, master games, tournament training, analisis engine, dan roadmap menuju level GM"). Roadmap approved 2026-08-13, tetap Status: Paused mengikuti kebijakan cap 2-course-aktif — lanjut kapan saja atas permintaan "lanjut course Chess Mastery".
