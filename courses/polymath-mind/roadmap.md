# Course Roadmap

## Course Information

* Course Name: Polymath Mind — Belajar Luas dan Dalam ala Ibnu Sina dan Leonardo, dengan Label Bukti (dari 0 sampai Capstone)
* Category: Learning Strategy & Intellectual History — Integrator. Domain: **reuse** `learning-science` (lihat `domains/learning-science/DOMAIN.md`). Course ini TIDAK membuat domain baru dan TIDAK menambah baris di `ai-los/ACTIVE_DOMAIN.md`. Alasan ada di "Catatan Scope".
* Difficulty: Level 0 (percaya mitos "orang dulu lebih jenius", tidak tahu kapan harus dalam dan kapan harus luas, analogi lintas bidang dipakai tanpa diuji) sampai Level 3 (punya Polymath Learning Loop pribadi, peta T-shaped, dan kebiasaan menguji analogi)
* Estimated Duration: 6 modul (5 modul isi + 1 modul capstone), 24 lesson, sengaja tipis. Pace mengikuti Applied Practice nyata, bukan dikebut.
* Prerequisites: Tidak ada prasyarat teknis. Direkomendasikan sudah menyentuh `courses/brain-mastery` (fokus, deep work, kreativitas, membaca) dan `courses/learning-science` Module 4 dan 7. Course ini dibangun di atas keduanya dan tidak mengulang isinya.
* Status: **Draft, menunggu approval**. Belum Course Aktif, belum ada Lesson 1.

---

# Catatan Scope (baca dulu sebelum approve)

## Asal course ini

Outline asli dari user: "WHY WERE PEOPLE IN THE PAST SO BRILLIANT? — Rahasia di Balik Pikiran Polymath: Dari Ibnu Sina sampai Leonardo da Vinci". Outline itu punya 14 Part dan 21 lesson, ditambah Final Lesson "The Real Secret", Mental Toughness Challenge 7 hari, The Polymath Rule, dan Final Mantra. Outline datang tanpa instruksi. User memilih "Audit overlap dulu", lalu memilih opsi course terlipat standalone (sekitar 6 modul, reuse `learning-science`, label bukti) setelah audit.

Guard "Course Creation Discipline" (`CLAUDE.md`) aktif saat course ini didraft: "Character Development" pas di garis 50% (3/6 modul) dan `male-grooming` masih Module 1. Mentor menyebut ini sebelum audit dan menyatakan bahwa opsi extend atau tidak membuat course lebih ringan. User tetap memilih course terlipat. Keputusan user dihormati dan dicatat di `progress/roadmap.md`.

## Hasil audit overlap

Estimasi mentor: sekitar 65-70% isi outline sudah punya rumah di repo. Angka ini didasarkan pada daftar modul dan lesson di roadmap, bukan pada isi lesson yang sudah ditulis. Karena itu angkanya kasar.

| Bagian outline | Sudah ada di | Perlakuan di course ini |
|---|---|---|
| L4, L12 (belajar untuk paham, recall, jelaskan ulang) | `memory-mastery` Modul 2, 5, 6; `learning-science` Modul 7; `brain-mastery` Modul 5 | Dirujuk. Hanya dipakai sebagai komponen Polymath Learning Loop (Modul 5). |
| L11 (deep attention, tanpa notifikasi) | `brain-mastery` Modul 2-3 | Dirujuk. Ditambah satu lesson tentang batas realisme (Lesson 5.4). |
| L13-15 (beginner mind, "belum mengerti" itu state) | `learning-science` Modul 4 dan 7; `thinking-struggle-meaning` | Dirujuk. Ditambah satu lesson tentang kapan bingung produktif dan kapan buntu (Lesson 5.3). |
| L6, L7, L8 (Ibnu Sina, Al-Khwarizmi, Al-Farabi) | `islamic-golden-age` Modul 2, 3, 7 (metode Documented/Contested/Myth) | Biografi TIDAK diulang. Modul 2 hanya mengekstrak metode belajar yang bisa dipindahkan. |
| L9-10 (Leonardo, pertanyaan bagus, notebook) | `brain-mastery` Modul 4 sebagian; `art-appreciation` Modul 7 (hanya sisi karya seni) | Sisi metode notebook belum ada. Masuk Lesson 2.4 dan 5.2. |
| L5, L20 (hubungkan ilmu, analogi lintas bidang) | `critical-thinking` Modul 6 (latticework Munger) | Dirujuk. Gap yang diisi: cara menguji analogi (Modul 4). |
| L1-L3 (mitos "dulu lebih pintar", spesialisasi) | Belum ada | Modul 1 (gap asli). |
| L16-17, L21 (T-shaped, deep first wide second) | Belum ada | Modul 3 (gap asli). |
| L18-19 (Polymath Loop, One Question a Day) | Belum ada sebagai kerangka | Modul 4-5. |
| 7-Day Challenge, Polymath Rule, Final Mantra | Applied Practice `brain-mastery` dan `learning-science` sebagian | Modul 6 (capstone). |

Belum dibaca isi lesson-nya: `tech-builder-mindset`, `digital-social-media-wisdom`, `computational-thinking`, `life-mastery`, `career-reinvention`. Kalau ada overlap tambahan di sana, akan ditandai saat Lesson di-generate.

## Kenapa reuse `learning-science`

Pola kerja course ini adalah Applied Practice, self-report, dan log penerapan nyata, sama dengan `brain-mastery`. Itu cocok dengan Review Style `domains/learning-science/DOMAIN.md`. Domain baru hanya masuk akal kalau ada topik yang tidak nyambung ke domain existing. Di sini semua modul menyambung ke `learning-science`, `critical-thinking`, atau `islamic-golden-age`.

## Sistem label bukti (wajib di setiap lesson)

Outline asli memuat banyak klaim yang enak dibaca tetapi belum tentu benar. Setiap klaim di lesson diberi salah satu label:

* **[Terdokumentasi]** — punya sumber yang bisa dicek (riset peer-reviewed, sejarawan, teks primer).
* **[Heuristik]** — aturan praktis yang berguna dan masuk akal, tetapi bukan hasil riset. Boleh dipakai, wajib diuji di pengalaman sendiri.
* **[Spekulasi]** — klaim populer atau cerita legenda yang tidak punya dukungan kuat. Ditampilkan hanya untuk dibongkar.

Klaim outline yang sudah ditandai sejak audit:

1. "Orang dulu lebih jenius": diuji sebagai survivorship bias (yang kita sebut polymath adalah segelintir orang yang bertahan dalam catatan sejarah). Ini argumen mentor, bukan hasil dari satu sumber.
2. "Membaca sedikit, mengunyah lama" ala polymath: tidak ada sumber historis yang diverifikasi. Label [Heuristik] paling tinggi. Cek dulu ke `memory-mastery` (spacing dan retrieval) sebelum dipakai.
3. "Tanpa notifikasi": bagian dari deep work ([Terdokumentasi] di `brain-mastery` Modul 2), tetapi romantisasi kondisi hidup polymath ini mengabaikan privilege (patronase, waktu luang).
4. Transfer analogi lintas bidang: riset menunjukkan transfer spontan itu sulit dan jarang terjadi otomatis (Gick dan Holyoak, 1980). Jadi analogi Lesson 4.3 adalah [Heuristik] yang harus diuji, bukan fakta tentang dunia.
5. Al-Khwarizmi dan "algoritma": kata "algorithm" berasal dari bentuk Latin dari namanya (Algoritmi). Ini bukan klaim bahwa ia merumuskan konsep algoritma modern. Rujuk metode Documented/Contested/Myth di `islamic-golden-age` Modul 1.
6. "Mental toughness adalah bertahan dalam kebingungan": [Heuristik] yang perlu dibedakan dari bertahan di jalan buntu. Lihat Lesson 5.3.

## Trusted Sources / Research Priority

Status verifikasi jujur: pada sesi audit ini hanya **metadata dan ringkasan hasil pencarian** yang diperiksa, bukan isi buku atau paper. Semua sumber di bawah harus dibaca atau dicek langsung sebelum diajarkan sebagai fakta (pola "Vetting a Source" `standards/UNIVERSAL_STANDARDS.md`).

Sumber yang metadata-nya terverifikasi lewat pencarian:

1. **Peter Burke — *The Polymath: A Cultural History from Leonardo da Vinci to Susan Sontag* (Yale University Press, 2020).** Studi prosopografi 500 polymath selama 500 tahun. Ringkasan penerbit: kemunculan mereka sejalan dengan pertumbuhan pengetahuan (mesin cetak, penemuan Dunia Baru, Revolusi Ilmiah), dan percepatan pengetahuan belakangan mendorong spesialisasi. Sumber utama Modul 1. Isi bab belum dibaca.
2. **Robert Root-Bernstein dkk. (2008), "Arts foster scientific success", *Journal of Psychology of Science and Technology* 1(2), 51-63.** Peraih Nobel lebih sering punya hobi seni dan kerajinan dibanding anggota Royal Society, National Academy, dan Sigma Xi. Desainnya korelasional dari biografi dan obituari, jadi tidak membuktikan sebab-akibat. Dipakai di Modul 3 dengan label yang menyebut batas itu.
3. **Mary L. Gick dan Keith J. Holyoak (1980), "Analogical problem solving", *Cognitive Psychology* 12, 306-355.** Sumber utama Modul 4. Temuan inti dari ringkasan pencarian: tanpa petunjuk, hanya sekitar 30% peserta yang memakai analogi dari cerita yang berbeda permukaan, dan sekitar 80% setelah diberi hint. Angka persis wajib dicek ke paper.
4. **James R. Flynn (1987), "Massive IQ gains in 14 nations: What IQ tests really measure", *Psychological Bulletin* 101(2), 171-191.** Skor IQ naik sekitar 3 poin per dekade di banyak negara pada periode ukur. Sumber pendukung Lesson 1.1 (skor tes kognitif tidak konstan lintas generasi). Flynn sendiri kemudian merevisi tafsirnya. Batas ini harus disebut di lesson.
5. **Etimologi "algorithm"**: berasal dari nama Latin Al-Khwarizmi, "Algoritmi" (ringkasan sumber sekunder populer: NPR 2026, Open Culture, jurnal Olympiads in Informatics). Sumber sekunder saja. Rujukan utama tetap `islamic-golden-age` Modul 2.
6. **Martin Kemp — *Leonardo da Vinci: The Marvellous Works of Nature and Man*.** Rujukan sejarawan seni untuk Leonardo. Hanya catatan perpustakaan yang dicek. Catatan tambahan dari pencarian (sumber lemah, perlu dikonfirmasi): notebook Leonardo baru terbit akhir abad ke-19 dan tafsirnya lewat sedikit penyalin, jadi klaim "pengaruh ilmiah Leonardo" perlu dicek.

Sumber kandidat yang BELUM diverifikasi sama sekali (dari ingatan mentor, tidak boleh dipakai sebagai fakta sebelum dicek):

* David Epstein, *Range* (2019) — sintesis populer untuk keluasan versus spesialisasi.
* Dedre Gentner, structure-mapping theory (1983) — dasar tesis "analogi bagus memetakan relasi, bukan permukaan".
* Charlie Munger, latticework (populer, sudah dirujuk di `critical-thinking` Modul 6).
* Asal istilah "T-shaped" (kemungkinan istilah rekrutmen/desain, bukan konstruk riset). Ini dugaan mentor, belum dicek.
* Dean Keith Simonton, riset kreativitas dan kejeniusan.
* Teks primer Ibn Sina, Al-Khwarizmi, Al-Farabi. Diverifikasi lewat `islamic-golden-age`, bukan di course ini.

House rule: klaim biografi polymath yang tidak punya dasar di `islamic-golden-age` atau sumber sejarawan diberi label [Spekulasi]. Tidak ada hack "jadi jenius dalam 30 hari".

---

# Learning Objectives

* [ ] Menguji klaim "orang dulu lebih jenius" memakai survivorship bias dan konteks historis (patronase, ukuran korpus pengetahuan), lalu menjelaskan kenapa spesialisasi punya untung dan rugi
* [ ] Mengekstrak metode belajar yang bisa dipindahkan dari kasus Ibnu Sina, Al-Khwarizmi, Al-Farabi, dan Leonardo tanpa mengulang biografi mereka
* [ ] Memetakan profil T-shaped pribadi sebagai software engineer dan memakai urutan "dalam dulu, luas kedua, hubungkan ketiga" sebagai heuristik yang diuji
* [ ] Menguji analogi lintas bidang: menandai apa yang dipetakan, di mana analogi pecah, dan kapan hanya kesamaan permukaan
* [ ] Menjalankan Polymath Learning Loop dan kebiasaan One Question a Day tanpa berubah jadi overstudy
* [ ] Menjalankan 7-Day Polymath Challenge dan menghasilkan Polymath Mind Playbook sebagai Capstone

---

# Course Modules

## Module 1 — Mitos "Dulu Lebih Pintar": Konteks, Bias Seleksi, dan Harga Spesialisasi

Description: Gap asli (outline L1-L3). Membongkar premis judul outline sebelum membangun apa pun di atasnya. Fokusnya kondisi hidup dan struktur pengetahuan, bukan kapasitas otak.

Lessons:

* [ ] 1.1 "Apakah orang dulu lebih pintar?" — apa yang bisa dan tidak bisa dijawab data (Flynn effect dan batas tafsirnya), plus survivorship bias sebagai argumen mentor
* [ ] 1.2 Kondisi, bukan otak: patronase, waktu luang elit, korpus pengetahuan yang belum terpecah, dan mesin cetak (Burke)
* [ ] 1.3 Spesialisasi sebagai pisau bermata dua: kedalaman dan produktivitas versus bahaya tunnel vision (label [Terdokumentasi] untuk tren historis Burke, [Heuristik] untuk sisanya)
* [ ] 1.4 Membaca klaim tentang tokoh besar: latihan memakai label [Terdokumentasi]/[Heuristik]/[Spekulasi], meminjam metode `islamic-golden-age` Modul 1

Mini Project: Ambil 5 klaim populer tentang polymath (dari outline atau media sosial), beri label bukti, dan tulis satu paragraf per klaim tentang apa yang perlu dicek untuk menaikkan atau menurunkan labelnya.

Status: Not started

---

## Module 2 — Metode dari Empat Tokoh: Ekstraksi, Bukan Biografi

Description: Outline L6-L10. Biografi Ibnu Sina, Al-Khwarizmi, dan Al-Farabi sudah punya rumah di `islamic-golden-age` Modul 2, 3, dan 7, jadi tidak diulang. Modul ini hanya bertanya: metode belajar apa dari tiap tokoh yang bisa dipakai learner sekarang, dan seberapa kuat dasar klaimnya.

Lessons:

* [ ] 2.1 Ibnu Sina: mengorganisasi pengetahuan luas jadi sistem (rujuk `islamic-golden-age` Modul 3; verifikasi klaim ke sumber di sana)
* [ ] 2.2 Al-Khwarizmi: prosedur langkah demi langkah dan pemecahan masalah, plus batas klaim "penemu algoritma" (rujuk `islamic-golden-age` Modul 2 dan `computational-thinking`)
* [ ] 2.3 Al-Farabi: klasifikasi ilmu, yaitu peta yang menunjukkan bagaimana cabang pengetahuan saling terkait (rujuk `islamic-golden-age` Modul 7)
* [ ] 2.4 Leonardo: observe, record, question, test lewat notebook, dan batas klaim "jenius yang tidak terbit" (rujuk `art-appreciation` Modul 7 hanya untuk sisi karya)

Mini Project: Tabel 4 baris (tokoh, metode yang diekstrak, dasar klaim, label bukti, cara mencoba di pembelajaran engineering pribadi). Satu metode dipilih untuk dicoba 3 hari.

Status: Not started

---

## Module 3 — Keluasan versus Kedalaman: T-Shaped untuk Software Engineer

Description: Gap asli (outline L16-L17, L21). Memetakan T pribadi dan menguji aturan "dalam dulu, luas kedua, hubungkan ketiga" sebagai heuristik, bukan hukum. Menyesuaikan dengan Master Goal (backend Java/Spring, kepercayaan freelance internasional).

Lessons:

* [ ] 3.1 Apa itu T-shaped dan dari mana istilahnya (label jujur: heuristik rekrutmen/desain, bukan konstruk riset) — verifikasi asal istilah sebelum diajarkan
* [ ] 3.2 Memetakan T pribadi: batang (backend Java/Spring, database, system design) dan palang (bidang yang memperkuat batang, mis. komunikasi, produk, keamanan, matematika terapan)
* [ ] 3.3 Dalam dulu, luas kedua, hubungkan ketiga: kapan cukup dalam untuk mulai melebar, dan tanda salah urutan. Bukti pendukung terbatas: Root-Bernstein (korelasional), Epstein (belum diverifikasi)
* [ ] 3.4 Jebakan jack-of-all-trades dan tutorial hopping — menyambung ke peringatan overstudy dan `courses/career-reinvention` (belum dibaca isinya)

Mini Project: Peta T pribadi satu halaman. Batang: 3 topik dengan level saat ini. Palang: maksimum 2 bidang dengan alasan tertulis. Sertakan tanda berhenti (kapan menambah bidang ketiga dilarang).

Status: Not started

---

## Module 4 — Koneksi Lintas Bidang: Analogi yang Berguna dan yang Menipu

Description: Gap asli (outline L5, L19, L20). Tesis: menghubungkan bidang itu berharga tetapi transfer analogi sulit dan sering salah. Modul ini mengajarkan cara menguji analogi, bukan koleksi analogi menarik. Munger dan latticework dirujuk ke `critical-thinking` Modul 6.

Lessons:

* [ ] 4.1 Kenapa analogi tidak muncul sendiri: tiga langkah noticing, mapping, applying (Gick dan Holyoak 1980)
* [ ] 4.2 Uji analogi: apa yang dipetakan (relasi atau permukaan), di mana analogi pecah, dan apa prediksi yang bisa dites (structure-mapping Gentner: belum diverifikasi)
* [ ] 4.3 Connection Exercise: sistem terdistribusi ke hidup (redundancy dan dana darurat, fault tolerance dan ketahanan emosi, graceful degradation), dengan kolom wajib "titik pecah analogi" dan label [Heuristik]
* [ ] 4.4 One Question a Day: satu pertanyaan per hari sebagai kebiasaan ringan, dengan batas waktu supaya tidak jadi overstudy (rujuk `brain-mastery` Modul 4 dan `critical-thinking`)

Mini Project: 5 analogi lintas bidang dengan tabel "yang dipetakan / yang tidak cocok / prediksi yang bisa dites". Minimal 1 analogi harus ditolak.

Status: Not started

---

## Module 5 — Polymath Learning Loop dan Kondisi Belajarnya

Description: Menyatukan komponen yang sudah ada di course lain jadi satu loop pribadi (outline L11-L15, L18), plus dua tambahan yang belum ada: metode notebook dan batas realisme untuk deep attention dan "bertahan dalam bingung".

Lessons:

* [ ] 5.1 Polymath Learning Loop: Observe, Question, Learn, Connect, Test, Teach. Peta tiap langkah ke teknik yang sudah diajarkan (`memory-mastery` recall/Feynman, `learning-science` desirable difficulty, `brain-mastery` reading). Label [Heuristik].
* [ ] 5.2 Notebook pribadi ala Leonardo, satu notebook dengan aturan ringan (mengamati, mencatat pertanyaan, mengetes), bukan sistem manajemen pengetahuan raksasa
* [ ] 5.3 Beginner mind dan bingung produktif versus buntu: "belum mengerti" adalah state, bukan identitas. Kapan bertahan, kapan minta bantuan (rujuk `learning-science` Modul 4 dan 7, `thinking-struggle-meaning`; klaim mental toughness diberi label bukti)
* [ ] 5.4 Fokus dalam dan realisme: romantisasi "tanpa notifikasi" versus kondisi hidup nyata (rujuk `brain-mastery` Modul 2-3)

Mini Project: Jalankan 1 siklus loop pada satu topik engineering kecil (3-4 hari). Catat di notebook: apa yang diamati, pertanyaan, tes, dan satu kesalahan yang ditemukan.

Status: Not started

---

## Module 6 — Capstone: 7-Day Polymath Challenge dan Polymath Mind Playbook

Description: Menjalankan outline Mental Toughness Challenge 7 hari (deep work, curiosity, cross connection, beginner, discomfort, teach, synthesis) dan menutup dengan Polymath Rule serta Final Mantra sebagai bahan refleksi, bukan dogma.

Lessons:

* [ ] 6.1 Merancang 7 hari: satu tema per hari, beban maksimum per hari, tanda berhenti bila kelelahan (peringatan overstudy dan burnout)
* [ ] 6.2 Menjalankan dan mencatat log 7 hari (notebook, One Question a Day, satu analogi diuji, satu sesi mengajar)
* [ ] 6.3 The Polymath Rule (ensiklopedia, pemikir, pencipta) dan "The Real Secret" outline: uji dengan output nyata dari log, beri label bukti
* [ ] 6.4 Self-review memakai Review Style `domains/learning-science/DOMAIN.md` (Intrinsic vs Extrinsic Balance, Evidence Over Productivity-Hack Hype, Genuine Enjoyment vs Forced Positivity, Sustainability). Final Mantra dievaluasi, bukan diterima begitu saja.

Capstone Project: **Polymath Mind Playbook** — dokumen + log 7 hari yang berisi peta T-shaped, Polymath Learning Loop pribadi, minimal 3 analogi teruji (1 ditolak), dan cross-reference eksplisit ke minimal satu course lain (`brain-mastery`, `learning-science`, atau `islamic-golden-age`). Disimpan ke `portofolio/`.

Status: Not started

---

# Capstone Project

Description: Lihat Module 6. Capstone SATU dengan Module 6, tanpa proyek tambahan, mengikuti pola `brain-mastery`.

Acceptance Criteria: Memenuhi 4 kriteria Review Style `domains/learning-science/DOMAIN.md` plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, dan bar "Portfolio-Worthy" (Complete, Reviewed, Presentable, Original) di `standards/UNIVERSAL_STANDARDS.md`. Setiap klaim tentang tokoh sejarah punya label bukti. Ada cross-reference ke minimal satu course lain.

Status: Not started

---

# Catatan Pacing dan Kecocokan

* Kecocokan Master Goal (backend world-class, freelance internasional): **rendah sampai sedang**. Yang paling relevan adalah Modul 3 (T-shaped untuk engineer). Modul lain bersifat pengayaan.
* Ada peringatan pacing dan overstudy (lihat memory `feedback_subscription_sunk_cost_overstudy`): course tentang cara belajar rawan dipakai untuk menunda belajar yang sebenarnya. Setiap modul punya Mini Project kecil dan tanda berhenti.
* Belum ditanyakan ke user: apakah 24 lesson terlalu banyak, dan apakah kasus latihan T-shaped sebaiknya memakai peta karier nyata (backend Java/Spring) atau bidang lain.

---

# Overall Progress

Module Completion: 0/6

Overall Completion: 0%

Current Module: Belum dimulai. Menunggu approval roadmap (termasuk keputusan reuse domain `learning-science` dan label bukti).

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — "Apakah orang dulu lebih pintar?"
