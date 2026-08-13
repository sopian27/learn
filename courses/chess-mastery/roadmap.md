# Course Roadmap

## Course Information

* Course Name: Chess Mastery — dari 0 sampai Expert
* Category: Catur — Aturan Dasar, Opening, Tactics, Calculation, Positional Play, Endgame, Strategy, Master Games, Tournament Training, Analisis Engine, dan Roadmap Menuju Grandmaster (Domain: chess-mastery — lihat `domains/chess-mastery/DOMAIN.md`)
* Difficulty: Level 0 (belum tahu cara bidak jalan) → Level 5 (bermain turnamen berperingkat dengan repertoar dipahami, kalkulasi terstruktur, evaluasi posisional akurat, teknik endgame solid, dan sistem latihan pribadi yang teruji — cukup kuat untuk menargetkan norma Candidate Master/FIDE Master lewat lanjutan pelatihan personal)
* Estimated Duration: 12 modul, 4 lesson/modul (48 lesson) — pace mengikuti volume latihan puzzle & partai nyata (bukan dikebut), realistis 6-12 bulan untuk pemain rajin latihan harian
* Prerequisites: Tidak ada prasyarat teknis. Butuh akun Lichess.org atau Chess.com (gratis) untuk puzzle rating, opening explorer, dan analisis engine. Kesediaan mencatat log nyata tiap modul (Game Log dari partai sungguhan, bukan cuma teori) sesuai `domains/chess-mastery/DOMAIN.md` bagian "Applied Practice Definition". Papan catur fisik opsional tapi disarankan untuk Module 1-2.

---

# Catatan Scope (asumsi kalibrasi, dikonfirmasi saat approval)

* **Catur standar (classical chess), bukan varian.** Chess960/bughouse/dll. hanya disinggung sekilas kalau relevan — course ini fokus penuh ke catur standar karena itu yang dipakai jalur rating FIDE.
* **"Roadmap menuju GM" berarti peta jalan realistis, bukan janji hasil pasti.** Capstone menghasilkan sistem latihan pribadi + posisi rating saat ini + langkah konkret ke milestone gelar berikutnya (CM/FM/IM/GM) — bukan klaim "ikuti course ini dan pasti jadi GM". GM sungguhan butuh bertahun-tahun latihan intensif, pelatih personal, dan turnamen langsung di luar cakupan satu course ini.
* **Engine (Stockfish) dipakai disiplin: analisis SETELAH bermain, tidak pernah SAAT bermain partai resmi.** Ini house rule keras (lihat `domains/chess-mastery/DOMAIN.md` bagian "Domain-Specific Standards") — dikonfirmasi di sini karena krusial untuk integritas latihan & fair play.
* **Learner memainkan partai nyata sejak awal** (online rated di Lichess/Chess.com cukup, tidak wajib OTB) — Game Log & Applied Practice butuh partai sungguhan, bukan cuma posisi hipotetis, sejak Module 1 selesai.

Keempat asumsi di atas menunggu konfirmasi/koreksi user saat approval roadmap, sebelum Lesson 1 dimulai.

---

# Learning Objectives

* [ ] Memahami aturan resmi FIDE, notasi aljabar, dan pola mat dasar (Module 1)
* [ ] Mengenali pola taktik umum (fork/pin/skewer/discovered attack) dengan cepat & akurat lewat repetisi terukur (Module 2, 7)
* [ ] Memiliki repertoar opening yang dipahami rencananya, bukan dihafal buta (Module 3)
* [ ] Mengkalkulasi posisi kompleks lewat metode candidate moves & tree of analysis (Kotov) (Module 4)
* [ ] Mengevaluasi posisi lewat elemen/imbalance konkret (Silman) — material, ruang, struktur pion, kualitas minor piece, inisiatif (Module 5, 8)
* [ ] Menguasai teknik endgame fundamental (king & pawn, rook endgame dasar) sampai lanjut (minor piece endgame, teknik praktis) (Module 6, 10)
* [ ] Memahami strategi jangka panjang — struktur pion, prophylaxis, blockade (Nimzowitsch, Flores Rios) (Module 8)
* [ ] Mempelajari partai master secara mendalam lewat anotasi lengkap, bukan sekadar menonton (Module 9)
* [ ] Menganalisis partai sendiri secara disiplin — manual dulu, engine kedua (Module 11)
* [ ] Bermain turnamen dengan manajemen jam, rutinitas, dan psikologi kompetisi catur-spesifik yang matang (Module 12)
* [ ] Menyusun dan menjalankan Personal Chess Improvement System dengan peta jalan realistis menuju gelar FIDE berikutnya

---

# Course Modules

## Module 1 — Fondasi: Aturan, Notasi, dan Mat Dasar

Description: Titik nol mutlak — cara bidak bergerak, aturan khusus (castling, en passant, promosi), notasi aljabar untuk membaca/menulis partai, dan pola mat paling dasar (king+queen vs king, king+rook vs king) yang jadi fondasi seluruh course. Sumber utama: FIDE Laws of Chess (rujukan resmi).

Lessons:

* [ ] 1.1 Papan, bidak, dan cara tiap bidak bergerak (termasuk aturan khusus: castling, en passant, promosi pion)
* [ ] 1.2 Notasi aljabar — membaca & menulis langkah, membaca partai dari database/buku
* [ ] 1.3 Nilai relatif bidak, cara menang/remis (checkmate, stalemate, draw by repetition/50-move rule) per FIDE Laws of Chess
* [ ] 1.4 Pola mat dasar — king+queen vs king, king+rook vs king, latihan sampai lancar

Mini Project: Checkmate Drill Log — selesaikan 10 posisi mat dasar (K+Q vs K, K+R vs K) sampai konsisten benar, plus mainkan 1 partai penuh pertama (online, bebas hasil) sambil mencatat notasi lengkap.

Status: Not started

---

## Module 2 — Tactics I: Pattern Recognition

Description: Pengenalan pola taktik inti yang jadi 80% keputusan kritis di level pemula-menengah — fork, pin, skewer, discovered attack, double attack. Berbasis riset chunking (Gobet, memperluas Chase & Simon) — pola dibangun lewat paparan berulang, bukan hafalan teori.

Lessons:

* [ ] 2.1 Fork & double attack — mengenali kesempatan menyerang dua target sekaligus
* [ ] 2.2 Pin & skewer — memahami bidak yang "terkunci" dan cara mengeksploitasinya
* [ ] 2.3 Discovered attack & discovered check — pola yang sering terlewat pemula
* [ ] 2.4 Sesi puzzle terukur pertama — baseline akurasi & waktu di Lichess Puzzles/Chess.com

Mini Project: Puzzle Log — selesaikan 50 puzzle rating campuran di Lichess/Chess.com, catat akurasi & pola taktik yang paling sering terlewat.

Status: Not started

---

## Module 3 — Opening Principles & Fundamentals

Description: Prinsip opening (kontrol pusat, keamanan raja, pengembangan cepat, jangan gerak bidak sama dua kali tanpa alasan) sebagai fondasi sebelum masuk teori baris spesifik. Repertoar dibangun dari pemahaman rencana, bukan hafalan (Silman, Nimzowitsch).

Lessons:

* [ ] 3.1 Tiga prinsip inti opening — kontrol pusat, keamanan raja (castling awal), pengembangan cepat
* [ ] 3.2 Kesalahan umum opening pemula — gerak queen terlalu awal, gerak bidak sama berulang, mengabaikan pengembangan
* [ ] 3.3 Memilih repertoar awal — satu opening untuk Putih, satu pertahanan vs 1.e4, satu vs 1.d4 (dipilih sesuai gaya, bukan "yang paling kuat")
* [ ] 3.4 Memahami rencana middlegame di balik baris yang dipilih (bukan cuma menghafal langkah)

Mini Project: Opening Repertoire Notebook v1 — dokumentasikan 3 baris pilihan (1 sebagai Putih, 2 sebagai Hitam) lengkap dengan alasan strategis & rencana middlegame-nya.

Status: Not started

---

## Module 4 — Calculation & Visualization

Description: Metode kalkulasi terstruktur ala Kotov — mendata semua candidate move dulu sebelum mendalami satu cabang, membangun "tree of analysis" secara disiplin. Dilengkapi latihan visualisasi papan tanpa memindah bidak fisik.

Lessons:

* [ ] 4.1 Metode candidate moves (Kotov) — kenapa mendata semua opsi dulu sebelum mendalami satu jalur
* [ ] 4.2 Tree of analysis — mencatat kalkulasi secara terstruktur di atas kertas sebelum verifikasi
* [ ] 4.3 Latihan visualisasi — membayangkan posisi beberapa langkah ke depan tanpa memindah bidak
* [ ] 4.4 Kalkulasi posisi taktis nyata dengan batas waktu, dibandingkan ke solusi

Mini Project: Calculation Worksheet — analisis 5 posisi taktis kompleks secara tertulis pakai metode candidate moves, cocokkan hasilnya ke solusi/engine setelah selesai (bukan sebelum).

Status: Not started

---

## Module 5 — Positional Play I: Imbalances & Elements

Description: Kerangka evaluasi posisi Silman — lima elemen imbalance (material, ruang, struktur pion, kualitas minor piece, kontrol file/petak kunci) yang dipakai untuk menjawab "posisi ini bagus untuk siapa, dan kenapa" secara konkret, bukan cuma perasaan.

Lessons:

* [ ] 5.1 Kerangka imbalances Silman — lima elemen inti evaluasi posisi
* [ ] 5.2 Ruang & kualitas minor piece — good bishop vs bad bishop, knight outpost
* [ ] 5.3 Struktur pion dasar — pion terisolasi (isolani), pion ganda, pion mundur (backward pawn)
* [ ] 5.4 Latihan evaluasi posisi nyata — identifikasi imbalance dari posisi partai master

Mini Project: Position Evaluation Log — evaluasi 5 posisi dari partai nyata (sendiri atau master) pakai kerangka lima imbalance Silman, bandingkan dengan evaluasi engine setelahnya.

Status: Not started

---

## Module 6 — Endgame I: Fundamental Endgames

Description: Endgame teoretis wajib dikuasai sebelum lanjut ke strategi lanjut — king & pawn endgame (opposition, key squares), rook endgame dasar (Lucena, Philidor). Endgame sering diabaikan pemula padahal paling sering menentukan hasil partai amatir.

Lessons:

* [ ] 6.1 King & pawn endgame — opposition, key squares, triangulation dasar
* [ ] 6.2 Rook endgame dasar — posisi Lucena (menang) dan Philidor (remis)
* [ ] 6.3 Prinsip umum endgame — aktivitas raja, rook di belakang passed pawn
* [ ] 6.4 Latihan endgame praktis melawan engine/lawan sampai posisi teoretis benar-benar dimenangkan/diremiskan

Mini Project: Endgame Technique Drill — mainkan 5 posisi endgame teoretis (K+P, Lucena, Philidor) sampai tuntas melawan engine, catat berapa kali berhasil vs gagal mengeksekusi teknik dengan benar.

Status: Not started

---

# Intermediate Project — Checkpoint Integrasi Module 1-6

Description: **Personal Game Analysis Report** — anotasi lengkap 3-5 partai learner sendiri (dari game online rated yang sudah dimainkan sejak Module 1-3), mencakup evaluasi opening (prinsip Module 3), momen taktik (Module 2), kalkulasi kritis (Module 4), dan evaluasi posisional (Module 5) — ditulis manual SEBELUM konsultasi engine, baru dibandingkan setelahnya, sebelum masuk ke lapisan strategi/master games/tournament di Module 7-12.

Status: Not started

---

## Module 7 — Tactics II: Combinations & Woodpecker Training

Description: Naik level dari pola taktik dasar ke kombinasi multi-langkah, plus penerapan penuh Woodpecker Method (Smith & Tikkanen) — set puzzle yang sama diulang berkali-kali dengan target makin cepat, terbukti secara empiris meningkatkan pattern recognition bawah sadar.

Lessons:

* [ ] 7.1 Kombinasi multi-langkah — menggabungkan pola dasar (fork+pin, discovered+skewer) jadi rangkaian
* [ ] 7.2 Sacrifice motif umum — greek gift, back rank, deflection, decoy
* [ ] 7.3 Metode Woodpecker — memilih set puzzle, siklus pengulangan, mengukur peningkatan kecepatan
* [ ] 7.4 Sesi Woodpecker penuh pertama — solve set puzzle, ulangi, catat waktu & akurasi tiap siklus

Mini Project: Woodpecker Cycle Log — pilih 20-30 puzzle sesuai level, selesaikan 1x untuk baseline, ulangi minimal 2 siklus lagi dalam minggu yang sama, catat kurva peningkatan waktu & akurasi.

Status: Not started

---

## Module 8 — Positional Play II: Pawn Structures & Prophylaxis

Description: Memperdalam Module 5 ke strategi jangka panjang — prophylaxis & overprotection (Nimzowitsch), plus struktur pion spesifik dengan rencana konkret per struktur (Flores Rios): Isolani, Carlsbad, Hedgehog.

Lessons:

* [ ] 8.1 Prophylaxis & overprotection (Nimzowitsch, *My System*) — mencegah rencana lawan sebelum menyerang
* [ ] 8.2 Blockade & kontrol petak lemah — konsep klasik yang masih relevan di level modern
* [ ] 8.3 Struktur pion spesifik — Isolani, Carlsbad, Hedgehog (Flores Rios) dengan rencana khas tiap struktur
* [ ] 8.4 Menghubungkan struktur pion opening pilihan (Module 3) ke rencana middlegame-nya

Mini Project: Structural Plan Notebook — pilih satu struktur pion yang sering muncul di repertoar pribadi (dari Module 3), dokumentasikan rencana standar untuk kedua sisi berdasarkan Flores Rios/Nimzowitsch.

Status: Not started

---

## Module 9 — Master Games Study

Description: Studi partai master beranotasi lengkap — bukan menonton pasif, tapi menebak langkah sebelum melihat jawaban, lalu memahami alasan tiap keputusan (Chernev, Fischer, Bronstein). Metode ini menghubungkan semua skill sebelumnya (taktik, kalkulasi, evaluasi posisi, struktur) dalam konteks partai nyata.

Lessons:

* [ ] 9.1 Metode studi partai — menebak langkah (guess-the-move) sebelum melihat jawaban & anotasi
* [ ] 9.2 Studi partai klasik terpandu — *Logical Chess: Move by Move* (Chernev), fokus prinsip dasar dalam aksi
* [ ] 9.3 Studi partai master lanjut — *My 60 Memorable Games* (Fischer) atau *Zurich 1953* (Bronstein), fokus rencana strategis
* [ ] 9.4 Menghubungkan pelajaran dari partai master ke repertoar & gaya bermain pribadi

Mini Project: Master Game Study Log — studi 3 partai master lengkap pakai metode guess-the-move, catat langkah yang tertebak vs meleset, dan pelajaran konkret yang relevan ke repertoar sendiri.

Status: Not started

---

## Module 10 — Endgame II: Advanced Practical Endgames

Description: Lanjutan Module 6 ke endgame yang lebih kompleks — minor piece endgame (bishop vs knight, opposite-colored bishops), rook endgame lanjut, dan teknik praktis menghadapi posisi endgame di bawah tekanan waktu turnamen nyata.

Lessons:

* [ ] 10.1 Minor piece endgame — bishop vs knight, same-colored vs opposite-colored bishop endgame
* [ ] 10.2 Rook endgame lanjut — posisi kompleks di luar Lucena/Philidor, teknik cutting off the king
* [ ] 10.3 Endgame dengan material tidak seimbang — teknik praktis (bukan cuma teori) untuk posisi realistis
* [ ] 10.4 Latihan endgame di bawah tekanan waktu — simulasi kondisi turnamen (clock nyata)

Mini Project: Advanced Endgame Drill — mainkan 5 posisi endgame lanjut (minor piece, rook endgame kompleks) melawan engine dengan clock aktif, catat hasil & kesalahan teknik.

Status: Not started

---

## Module 11 — Engine-Assisted Analysis & Modern Training

Description: Cara memakai Stockfish/engine secara disiplin — SETELAH analisis manual, bukan sebelum. Mengukur gap antara evaluasi sendiri dan evaluasi objektif engine untuk belajar, bukan sekadar dikoreksi. House rule anti-cheat ditegaskan ulang di modul ini.

Lessons:

* [ ] 11.1 Prinsip disiplin urutan: analisis manual dulu, engine kedua — kenapa urutan terbalik merusak kalkulasi mandiri
* [ ] 11.2 Membaca output engine dengan benar — evaluasi centipawn, kedalaman (depth), garis alternatif — bukan cuma angka "bagus/jelek"
* [ ] 11.3 Memakai engine untuk prep opening & post-mortem partai sendiri
* [ ] 11.4 Fair play & etika engine — batas tegas kapan engine boleh/tidak boleh dipakai (house rule domain ini)

Mini Project: Engine Analysis Review — ambil 2 partai dari Game Log (Module 3, 6, atau Intermediate Project), analisis ulang manual dulu, baru buka Stockfish, catat gap evaluasi sendiri vs engine dan pelajaran konkretnya.

Status: Not started

---

## Module 12 — Tournament Training, Rating Systems & Roadmap to GM

Description: Modul penutup & Capstone — manajemen jam turnamen, rutinitas pra-partai, psikologi pemulihan dari blunder di tengah partai (catur-spesifik, bukan regulasi emosi umum), sistem rating Elo/FIDE, jalur norma gelar (CM-FM-IM-GM), dan integrasi semua skill jadi satu sistem latihan pribadi berkelanjutan.

Lessons:

* [ ] 12.1 Manajemen jam & clock management — alokasi waktu per fase partai, kesalahan umum time trouble
* [ ] 12.2 Rutinitas pra-turnamen & pemulihan dari blunder di tengah partai
* [ ] 12.3 Sistem rating Elo/FIDE & jalur norma gelar (CM 2200, FM 2300, IM/GM 2400/2500 + norma) — per FIDE Handbook
* [ ] 12.4 Menyusun Personal Chess Improvement System — integrasi puzzle rotation, repertoire, evaluasi posisional, kalkulasi, endgame, dan target rating

Capstone Project: **Personal Chess Improvement System** — sistem latihan pribadi terkodifikasi (rotasi puzzle Woodpecker, repertoar opening dengan rencana per struktur, checklist evaluasi posisional Silman, protokol kalkulasi Kotov, program endgame, dan jadwal turnamen/rating tracking), dijalankan nyata minimal 6-8 minggu dengan data rating/skor puzzle terukur sebelum-sesudah, plus peta jalan pribadi konkret menuju milestone gelar FIDE berikutnya sesuai rating saat ini, disimpan ke `portofolio/`.

Status: Not started

---

# Resources

Lihat `domains/chess-mastery/DOMAIN.md` bagian "Trusted Sources / Research Priority" untuk daftar lengkap dan urutan prioritas: FIDE Handbook (Laws of Chess & Title Regulations), Jeremy Silman (*How to Reassess Your Chess*, *The Amateur's Mind*), Alexander Kotov (*Think Like a Grandmaster*), Mark Dvoretsky & Artur Yusupov (*Dvoretsky's Endgame Manual*, seri *Build Up Your Chess*), Aron Nimzowitsch (*My System*), Mauricio Flores Rios (*Chess Structures*), Axel Smith & Hans Tikkanen (*The Woodpecker Method* 1 & 2), Irving Chernev (*Logical Chess: Move by Move*), Bobby Fischer (*My 60 Memorable Games*), David Bronstein (*Zurich 1953*), Fernand Gobet (riset chunking/template theory), Lichess.org & Chess.com (platform latihan), Stockfish (engine analisis).

# Projects

12 Mini Project (satu per modul) + 1 Intermediate Project (Personal Game Analysis Report, checkpoint Module 1-6) + 1 Capstone Project (Personal Chess Improvement System, disimpan ke `portofolio/`). Rincian tiap project ada di modul masing-masing di atas.

# Interview

Ditambahkan saat course mendekati Capstone: simulasi "diberi posisi kompleks di bawah batas waktu" — menguji kalkulasi candidate moves, evaluasi imbalance, dan identifikasi rencana strategis, konsisten dengan `domains/chess-mastery/DOMAIN.md` bagian "Assessment Form".

# Final Exam

Capstone Project (Module 12.4) berfungsi sebagai final exam — dinilai memakai Universal Review Rubric plus lima kriteria Review Style domain ini (Tactical Accuracy Check, Candidate-Move Discipline Check, Imbalance Identification Check, Opening Principle Adherence Check, Engine-Deviation Check).
