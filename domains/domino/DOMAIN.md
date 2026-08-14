# Domino Mastery — Domain Pack

**Status: DRAFT — dibangun 2026-08-14 atas permintaan user ("buat course domino 0-expert: aturan, probabilitas, tracking kartu, membaca pola lawan, inferensi kartu tersembunyi, counting, strategi blocking, baiting, bluff, dan decision-making"), menunggu approval sebelum Lesson 1.**

## Domain Name

Domino Mastery (Domino dari 0 sampai Expert — Aturan, Probabilitas, Tracking Kartu, Membaca Pola Lawan, Inferensi Kartu Tersembunyi, Counting, Strategi Blocking, Baiting/Bluff, dan Decision-Making)

## Scope

Membangun kekuatan bermain domino praktis dari nol total (belum tahu aturan) sampai level expert yang mampu membaca papan, melacak kartu, membaca lawan, dan mengambil keputusan optimal secara konsisten di partai nyata: fondasi aturan & setup permainan, probabilitas dasar domino (kombinatorik kartu tersisa & distribusi), tracking kartu yang sudah keluar, membaca pola/kebiasaan lawan, inferensi kartu tersembunyi lawan dari histori permainan, counting lanjutan (expected value tiap langkah), strategi blocking (menutup angka), strategi baiting/bluff, decision-making di endgame, sampai mental game/psikologi lawan tingkat expert.

**Asumsi varian (eksplisit, dikonfirmasi ulang di `courses/domino/roadmap.md` bagian "Catatan Scope"):** karena user tidak menyebut varian spesifik, domain ini memakai **Block Gaple 4 pemain individual** — varian domino paling umum dimainkan casual di Indonesia — sebagai jalur utama: satu set domino standar double-six (28 keping, angka 0-6), dibagi habis 7 keping/pemain (tanpa boneyard/tumpukan tarik), menang dengan menghabiskan kartu duluan atau (kalau permainan buntu/blocked) total angka sisa kartu di tangan paling kecil. Varian lain (partnership 2v2, Draw Domino dengan boneyard untuk 2-3 pemain, set domino lebih besar seperti double-nine) disinggung sebagai catatan variasi di modul relevan, bukan jalur utama, supaya scope tidak melebar.

Cakupan konkret: aturan resmi/konvensi block gaple & setup permainan, notasi/cara mencatat papan (histori langkah & ujung terbuka), probabilitas dasar (jumlah kombinasi kartu, distribusi hypergeometric sederhana untuk memperkirakan peluang lawan memegang keping tertentu), sistem tracking kartu keluar (tally per angka 0-6), membaca pola/kebiasaan lawan (tempo, pola pilihan saat ada beberapa opsi sah, "tanda" tidak sengaja), inferensi deduktif kartu tersembunyi (dari langkah yang TIDAK dimainkan lawan di ujung terbuka tertentu), counting lanjutan & expected value per langkah, strategi blocking (mengendalikan angka mana yang terbuka/tertutup di papan), strategi baiting (memancing lawan membuka informasi lewat pilihan langkah), keterbatasan konsep "bluff" di domino dibanding kartu tertutup murni (dijelaskan jujur — lihat bagian di bawah), decision-making endgame (meminimalkan sisa poin, timing "keluar"), dan psikologi kompetitif domino-spesifik (manajemen tilt akibat sering diblokir, membaca "table image" lawan lintas ronde dalam satu seri partai).

Secara eksplisit TIDAK mencakup:

- **Varian trick-taking seperti Texas 42 atau Mexican Train secara mendalam** — mekanik & strategi trick-taking berbeda signifikan dari block/draw domino; disinggung sekilas sebagai perbandingan kalau relevan, bukan jalur utama course ini.
- **Judi/taruhan domino dan skema uang** — di luar scope sama sekali, tidak pernah disentuh domain ini dalam bentuk apa pun (konsisten dengan sikap domain `football-tactics` terhadap judi bola).
- **Membangun/memprogram engine/solver domino sendiri** (algoritma minimax, Monte Carlo untuk hidden-information game) — itu proyek software engineering murni, arahkan ke domain `software-engineering` kalau user tertarik membangun solver sebagai proyek koding, bukan diajarkan sebagai jalur peningkatan kekuatan bermain di domain ini.
- **Psikologi kompetisi umum/mental toughness generik** di luar konteks domino (manajemen kecemasan umum, regulasi emosi generik) — domain `emotional-resilience`/`intuition-training` untuk itu; domain ini hanya membahas sisi domino-spesifik (tilt akibat blokir berulang, rutinitas antar-ronde dalam satu seri).
- **Mengajar/coaching orang lain bermain domino secara formal** — course ini untuk kekuatan bermain pribadi (playing strength), bukan menjadi pelatih/instruktur bersertifikat.

## Trusted Sources / Research Priority

Catatan jujur di depan: domino (apalagi varian regional seperti gaple) **tidak punya badan pengatur tunggal yang diakui secara global** setara FIDE untuk catur — aturan gaple bervariasi antar keluarga/daerah di Indonesia, dan literatur strategi domino terkodifikasi jauh lebih tipis dibanding catur. Karena itu domain ini secara eksplisit menyilangkan beberapa sumber tiap kali membangun sebuah lesson, alih-alih mengklaim satu rujukan "resmi".

1. **Pagat.com (John McLeod) — ensiklopedia aturan permainan kartu & ubin (tile games) yang dikelola komunitas dan banyak dirujuk lintas budaya**, termasuk entri Dominoes (Block, Draw, Muggins/All Fives) dan variasi regional. Dipakai sebagai titik awal cross-check aturan dasar (Module 1), BUKAN sumber tunggal — selalu disilangkan ke konvensi gaple Indonesia yang berlaku umum karena Pagat lebih kuat di varian Barat.
2. **Konvensi gaple Indonesia yang berlaku umum** (disintesis dari berbagai sumber komunitas/rule-of-thumb yang konsisten lintas daerah, bukan satu dokumen resmi tunggal) — dasar utama Module 1 untuk aturan block gaple 4 pemain individual, scoring, dan istilah lokal ("gaple" merujuk ke keping [0-0]/double-blank yang secara historis memberi nama permainan ini). Ketidaksepakatan house-rule (mis. bonus "gaple"/double saat menang) ditandai eksplisit sebagai variasi, bukan aturan mutlak.
3. **William Feller — *An Introduction to Probability Theory and Its Applications*** dan **Sheldon Ross — *A First Course in Probability*** — teks probabilitas & kombinatorik standar (distribusi hypergeometric, counting tanpa pengembalian) yang jadi dasar matematis Module 2, 3, dan 6 untuk menghitung peluang keping tersisa/di tangan lawan.
4. **John von Neumann & Oskar Morgenstern — *Theory of Games and Economic Behavior*** dan **John Harsanyi — kerangka "games with incomplete information played by Bayesian players" (1967-68)** — fondasi teori game untuk permainan informasi tersembunyi (hidden hand), dasar konseptual Module 5 (inferensi) dan Module 8 (baiting/bluff): bagaimana memperbarui keyakinan (belief update) tentang tangan lawan dari aksi yang teramati.
5. **Bill Chen & Jerrod Ankenman — *The Mathematics of Poker*** — dipakai sebagai **analogi lintas-domain**, bukan sumber domino langsung: kerangka expected value & pengambilan keputusan di bawah informasi tersembunyi paling matang dan teruji ada di literatur poker, disilangkan hati-hati ke konteks domino di Module 6 dan 8 (dengan disclaimer eksplisit soal batas kemiripan — lihat "Domain-Specific Standards").
6. **Federasi/turnamen domino kompetitif regional** (mis. tradisi turnamen domino di Karibia/Amerika Latin — Kuba, Puerto Rico, Republik Dominika, di mana domino punya budaya kompetitif kuat dan terorganisir) — dipakai untuk Module 10 (psikologi & etiket kompetitif) dan Practitioner Reference Frame di bawah, sebagai kerangka "expert club/tournament player" karena tidak ada figur setara grandmaster catur yang dikenal luas di domino.

Tidak pernah membangun satu lesson murni dari satu sumber (khususnya sumber 1 dan 6 yang paling lemah otoritasnya untuk konteks gaple Indonesia spesifik) tanpa menyilangkan ke sumber lain di atas atau ke konvensi yang dikonfirmasi user langsung saat pengalaman bermain nyata.

## Applied Practice Definition

Praktik nyata terhadap partai domino sungguhan (dengan keluarga/teman, atau simulasi/lawan bot bila partai fisik tidak tersedia), bukan cuma membaca teori: **Game Log** (tiap partai dicatat — susunan papan, keping yang dimainkan tiap pemain, siapa menang/blocked, dipakai sebagai bahan analisis di modul-modul berikutnya), **Probability Worksheet** (menghitung peluang lawan memegang keping tertentu dari keping yang sudah terlihat/keluar), **Tile Tracking Sheet** (tally manual 0-6 diperbarui live selama partai berlangsung), **Opponent Pattern Log** (catatan kebiasaan/tempo lawan lintas beberapa partai), **Inference Worksheet** (merekonstruksi kemungkinan isi tangan lawan dari histori langkah yang TIDAK dimainkan), **Expected Value Decision Log** (menghitung nilai EV tiap kandidat langkah di titik keputusan kritis), **Blocking/Baiting Drill** (skenario papan nyata dianalisis untuk opsi menutup angka atau memancing lawan), dan **Endgame Decision Journal** (keputusan di fase akhir partai dievaluasi pasca-partai). Skenario papan generik hanya jembatan sementara sebelum learner punya cukup partai nyata sendiri untuk dianalisis.

## Project Tiers

- **Mini Project** (per modul): satu Game Log/Probability Worksheet/Tile Tracking Sheet/Pattern Log/Inference Worksheet/EV Decision Log/Blocking-Baiting Drill/Endgame Journal sesuai fokus modul.
- **Intermediate Project** (checkpoint setelah Module 1-5 — aturan, probabilitas, tracking, pola lawan, inferensi): **Full Match Analysis Report** — analisis lengkap 3-5 partai nyata learner sendiri, menggabungkan tracking kartu, profil pola tiap lawan, dan inferensi tangan tersembunyi, ditulis manual sebelum dicocokkan ke hasil akhir partai (apakah inferensi yang dibuat selama partai terbukti benar).
- **Capstone / Portfolio Project** (akhir course): **Personal Domino Strategy System** — sistem bermain pribadi terkodifikasi (metode tracking manual, kerangka EV per langkah, playbook blocking & baiting, protokol inferensi, dan catatan psikologi lawan) yang sudah diuji nyata lewat satu seri partai (mis. first-to-N-poin) dengan data menang-kalah/rata-rata sisa poin terukur sebelum-sesudah, disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak juga dicek lewat empat lensa:

- **Counting Accuracy Check** — untuk Tile Tracking Sheet: apakah tally keping keluar per angka akurat & konsisten dengan histori papan sungguhan, bukan perkiraan kasar.
- **Inference Validity Check** — untuk Inference Worksheet: apakah kesimpulan "lawan tidak memegang angka X" benar-benar didukung logika deduktif yang valid (lawan punya kesempatan main di ujung itu tapi tidak melakukannya), bukan tebakan tanpa dasar.
- **EV Reasoning Check** — untuk Expected Value Decision Log: apakah perhitungan/estimasi nilai tiap kandidat langkah masuk akal secara matematis (memakai probabilitas dari tracking, bukan cuma "feeling langkah ini bagus").
- **Blocking/Baiting Soundness Check** — untuk Blocking/Baiting Drill: apakah langkah menutup angka atau memancing lawan punya tujuan taktis konkret yang bisa dijelaskan (angka apa yang mau ditutup, informasi apa yang mau digali), bukan kebetulan.

## Assessment Form

Kuis probabilitas terukur (menghitung peluang lawan memegang keping tertentu dari state papan yang diberikan), drill tracking kecepatan (tally keping keluar dari histori langkah tertulis, dibatasi waktu), skenario decision-making (diberi state papan & tangan, pilih langkah terbaik dan justifikasi pakai EV/blocking/inferensi), dan tes inferensi buta (diberi hanya histori langkah yang dimainkan/dilewati, rekonstruksi kemungkinan isi tangan lawan).

## Practitioner Reference Frame

Domino tidak punya figur setara grandmaster catur yang dikenal luas secara global — tidak ada "Kasparov-nya domino". Karena itu domain ini eksplisit memakai kerangka **"expert club/tournament player"** umum sebagai pembanding, disintesis dari dua tradisi yang punya budaya kompetitif domino kuat dan terdokumentasi:

- **Tradisi turnamen Karibia/Amerika Latin** (Kuba, Puerto Rico, Republik Dominika) — domino di sana mendekati status olahraga sosial nasional dengan liga & turnamen terorganisir; jadi rujukan untuk etiket kompetitif, manajemen seri partai panjang, dan disiplin mencatat skor (Module 10).
- **Pemain club/rumahan berpengalaman (Indonesia, konteks gaple)** — tidak terorganisir secara formal, tapi punya heuristik turun-temurun yang konsisten (mis. kapan menahan keping "pembunuh" untuk blocking, kapan buang keping berat lebih dulu) — dipakai sebagai pembanding gaya casual-tapi-tajam vs gaya turnamen lebih formal di atas.

Kedua tradisi dibandingkan, bukan diklaim salah satu "cara benar tunggal" — konsisten dengan pendekatan `domains/chess-mastery/DOMAIN.md` yang membandingkan tradisi Soviet vs modern engine-assisted.

## Domain-Specific Standards

`domains/domino/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Tracking & inferensi wajib dilatih manual (mental/kertas) dulu sebelum dibantu tool/app apa pun.** Sejalan dengan house rule `domains/chess-mastery/DOMAIN.md` soal engine: kalau ada app pencatat otomatis dipakai sejak awal, skill counting & inferensi mandiri tidak pernah benar-benar terbentuk. Tool/app boleh dipakai untuk verifikasi SETELAH tracking manual selesai, bukan menggantikannya.
- **Konsep "bluff" di domino dibatasi jujur, tidak disamakan mentah-mentah dengan poker.** Di block gaple, pemain WAJIB memainkan keping yang cocok kalau punya (tidak ada opsi "pass" sukarela seperti fold di poker) — jadi ruang bluff jauh lebih sempit: yang dimaksud "baiting/bluff" di domain ini adalah (a) memilih di antara beberapa langkah sah yang sama-sama valid untuk menyamarkan kekuatan/isi tangan, dan (b) memancing lawan membuka informasi lewat urutan langkah — BUKAN mengaku punya keping yang tidak dipegang, karena itu mustahil secara aturan. Module 8 menjelaskan batas ini secara eksplisit di awal supaya ekspektasi learner realistis.
- **Tidak ada toleransi kecurangan di partai nyata** — menandai keping, mengintip tangan lawan, atau kolusi sinyal ilegal (di luar komunikasi sah yang diperbolehkan aturan varian partnership, kalau dipakai) tidak pernah diajarkan atau ditoleransi, konsisten dengan semangat fair-play `domains/chess-mastery/DOMAIN.md` soal larangan bantuan engine saat partai resmi.
- **Variasi house-rule ditandai eksplisit, tidak diklaim universal.** Karena gaple tidak punya rulebook resmi tunggal, setiap kali sebuah aturan/skoring disebut, lesson wajib menandai apakah itu konvensi umum atau variasi lokal yang bisa berbeda di meja lain — learner didorong mengonfirmasi aturan meja sebelum partai dimulai.

## Domain Goal

Learner memiliki kekuatan bermain domino/gaple praktis yang teruji nyata — memahami aturan & setup sepenuhnya, mampu menghitung probabilitas dasar keping tersisa, melacak kartu yang sudah keluar secara akurat, membaca pola/kebiasaan lawan, menyimpulkan isi tangan lawan lewat inferensi deduktif yang valid, menghitung expected value tiap langkah, menguasai strategi blocking dan baiting sesuai batas realistis aturan domino, mengambil keputusan endgame yang meminimalkan risiko, dan memahami psikologi kompetitif domino-spesifik — dibuktikan lewat data menang-kalah/rata-rata sisa poin terukur dari seri partai nyata dan sistem bermain pribadi yang benar-benar dijalankan, bukan cuma rasa percaya diri.

## Registration History

Registered 2026-08-14, domain pack + course roadmap dibangun sebagai DRAFT atas permintaan user ("buat course domino dari 0 sampai expert, cakupan: aturan, probabilitas, tracking kartu, membaca pola lawan, inferensi kartu tersembunyi, counting, strategi blocking, baiting, bluff, dan decision-making"). Varian domino tidak disebut spesifik oleh user — domain ini secara eksplisit mengasumsikan **Block Gaple 4 pemain individual** (varian populer Indonesia) sebagai jalur utama, ditandai di bagian "Scope" di atas dan dikonfirmasi ulang di `courses/domino/roadmap.md` bagian "Catatan Scope". Menunggu approval user sebelum Lesson 1 dan sebelum ditambahkan ke `ai-los/ACTIVE_DOMAIN.md`.
