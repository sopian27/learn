# Course Roadmap

## Course Information

* Course Name: Domino Mastery — dari 0 sampai Expert
* Category: Domino — Aturan, Probabilitas, Tracking Kartu, Membaca Pola Lawan, Inferensi Kartu Tersembunyi, Counting, Strategi Blocking, Baiting/Bluff, dan Decision-Making (Domain: domino — lihat `domains/domino/DOMAIN.md`)
* Difficulty: Level 0 (belum tahu aturan sama sekali) → Level 4 (bermain seri partai nyata dengan tracking akurat, inferensi tangan lawan valid, keputusan berbasis expected value, dan sistem bermain pribadi teruji — setara "expert club/tournament player" per `domains/domino/DOMAIN.md` bagian Practitioner Reference Frame)
* Estimated Duration: 10 modul, 3-4 lesson/modul (± 34 lesson) — pace mengikuti volume partai nyata yang dimainkan & dianalisis (bukan dikebut), realistis 2-4 bulan dengan latihan rutin
* Prerequisites: Tidak ada prasyarat teknis. Butuh satu set domino fisik standar double-six (28 keping) atau aplikasi simulasi/lawan bot bila partai fisik tidak tersedia, minimal 3 partner main (untuk 4 pemain) atau kesediaan main online, dan kesediaan mencatat log partai nyata tiap modul (Game Log, bukan cuma teori) sesuai `domains/domino/DOMAIN.md` bagian "Applied Practice Definition".

---

# Catatan Scope (asumsi kalibrasi, dikonfirmasi saat approval)

* **Varian: Block Gaple, 4 pemain individual, kartu domino standar double-six (28 keping, angka 0-6).** User tidak menyebut varian spesifik — course ini mengasumsikan varian block/draw domino standar dengan fokus utama ke "gaple", varian paling populer dimainkan casual di Indonesia: 28 keping dibagi habis 7/pemain (TANPA boneyard/tumpukan tarik), menang dengan menghabiskan kartu duluan, atau kalau permainan buntu (blocked, tidak ada pemain bisa jalan), pemenang adalah yang total angka sisa kartu di tangannya paling kecil.
* **Bukan trick-taking (Texas 42, Mexican Train, dll.) dan bukan Draw Domino dengan boneyard** — kedua itu cuma disinggung sekilas sebagai catatan perbandingan di modul relevan, bukan jalur utama.
* **Tidak ada rulebook resmi tunggal untuk gaple** (beda dari catur yang punya FIDE) — aturan & scoring disintesis dari konvensi umum yang berlaku lintas daerah, house-rule yang berbeda (mis. bonus "gaple"/menang dengan double di tangan) ditandai eksplisit sebagai variasi di lesson terkait, bukan aturan mutlak. Konfirmasi aturan meja pribadi user tetap disarankan sebelum partai nyata.
* **Konsep "bluff" dibatasi realistis sesuai aturan domino** (lihat `domains/domino/DOMAIN.md` bagian "Domain-Specific Standards") — di block gaple pemain wajib main keping yang cocok kalau punya (tidak ada "pass" sukarela), jadi Module 8 fokus ke penyamaran isi tangan lewat pemilihan langkah & pemancingan informasi, bukan bluff ala poker yang mengaku punya kartu yang tidak dipegang.
* **Learner main partai nyata sejak awal** (fisik dengan keluarga/teman, atau simulasi/bot kalau tidak ada partner) — Game Log & Applied Practice butuh partai sungguhan sejak Module 1 selesai, bukan cuma skenario papan hipotetis.

Kelima asumsi di atas menunggu konfirmasi/koreksi user saat approval roadmap, sebelum Lesson 1 dimulai.

---

# Learning Objectives

* [ ] Memahami aturan block gaple, setup permainan, dan cara menang/skor secara benar (Module 1)
* [ ] Menghitung probabilitas dasar domino — jumlah kombinasi kartu tersisa & distribusi per angka (Module 2)
* [ ] Melacak kartu yang sudah keluar secara akurat & konsisten selama partai berlangsung (Module 3)
* [ ] Membaca pola & kebiasaan lawan lintas beberapa partai (Module 4)
* [ ] Menyimpulkan kemungkinan isi tangan tersembunyi lawan lewat inferensi deduktif dari histori permainan (Module 5)
* [ ] Menghitung expected value tiap kandidat langkah memakai probabilitas & tracking (Module 6)
* [ ] Menguasai strategi blocking — mengendalikan angka mana yang terbuka/tertutup di papan (Module 7)
* [ ] Menguasai strategi baiting sesuai batas realistis aturan domino (Module 8)
* [ ] Mengambil keputusan endgame yang meminimalkan risiko & sisa poin (Module 9)
* [ ] Memahami psikologi kompetitif domino-spesifik & etiket seri partai tingkat expert (Module 10)
* [ ] Menyusun dan menjalankan Personal Domino Strategy System dengan data menang-kalah terukur

---

# Course Modules

## Module 1 — Fondasi: Aturan & Setup Permainan

Description: Titik nol mutlak — komposisi set domino standar (28 keping, double-six), cara pembagian kartu (7/pemain, tanpa boneyard), penentuan pemain pertama, aturan mencocokkan ujung terbuka, dan kondisi menang (habis duluan) vs buntu/blocked (skor dari sisa poin terkecil). Sumber utama: Pagat.com (cross-check aturan Barat) disilangkan ke konvensi gaple Indonesia yang berlaku umum.

Lessons:

* [ ] 1.1 Anatomi set domino — 28 keping, angka 0-6, istilah "gaple" (keping [0-0]/double-blank) dan asal nama permainan
* [ ] 1.2 Setup permainan — pembagian 7 keping/pemain, penentuan pemain pertama, mencocokkan ujung terbuka papan
* [ ] 1.3 Kondisi menang & skor — habis kartu duluan vs permainan buntu (blocked), cara menghitung sisa poin
* [ ] 1.4 Variasi house-rule umum (bonus "gaple", bonus double) — dikenali sebagai variasi, bukan aturan mutlak

Mini Project: Game Log #1 — mainkan 3 partai penuh (fisik/simulasi), catat susunan papan, keping tiap pemain, dan hasil akhir tiap partai dengan notasi lengkap.

Status: Not started

---

## Module 2 — Probabilitas Dasar Domino

Description: Fondasi matematis untuk semua modul lanjutan — menghitung total kombinasi keping, distribusi angka 0-6 di 28 keping, dan probabilitas dasar (hypergeometric sederhana) memperkirakan peluang lawan memegang keping tertentu berdasarkan apa yang sudah terlihat.

Lessons:

* [ ] 2.1 Total kombinasi & distribusi keping — berapa kali tiap angka (0-6) muncul di 28 keping
* [ ] 2.2 Probabilitas dasar tanpa pengembalian (hypergeometric) — konsep inti dari Feller/Ross diterapkan ke domino
* [ ] 2.3 Menghitung peluang lawan memegang keping/angka tertentu dari keping yang sudah terlihat di tangan sendiri
* [ ] 2.4 Latihan hitung probabilitas dari state papan nyata (dari Game Log Module 1)

Mini Project: Probability Worksheet — hitung peluang lawan memegang keping tertentu untuk 5 titik keputusan berbeda dari partai nyata di Game Log Module 1.

Status: Not started

---

## Module 3 — Tracking Kartu yang Sudah Keluar

Description: Sistem tally manual — mencatat live selama partai berlangsung keping mana yang sudah keluar per angka (0-6), supaya probabilitas Module 2 bisa dihitung akurat secara real-time, bukan cuma pasca-partai.

Lessons:

* [ ] 3.1 Merancang sistem tally manual per angka (0-6) yang cepat dicatat saat partai berjalan
* [ ] 3.2 Menghubungkan tally dengan ujung papan terbuka — berapa keping tersisa yang bisa menutup tiap ujung
* [ ] 3.3 Latihan tracking live pada partai nyata — mencatat tanpa mengganggu tempo permainan
* [ ] 3.4 Verifikasi akurasi tracking pasca-partai (cocokkan tally vs Game Log lengkap)

Mini Project: Tile Tracking Sheet — lacak live minimal 3 partai nyata, verifikasi akurasi tally pasca-partai, catat kesalahan tracking yang ditemukan.

Status: Not started

---

## Module 4 — Membaca Pola & Kebiasaan Lawan

Description: Profil kebiasaan tiap lawan lintas beberapa partai — tempo bermain, pola pilihan saat punya beberapa opsi sah, kecenderungan menahan angka tertentu (indikasi tangan berat di angka itu).

Lessons:

* [ ] 4.1 Sinyal tempo — jeda lama sebelum jalan sebagai indikasi pilihan sulit/tangan terbatas
* [ ] 4.2 Pola pilihan saat ada beberapa langkah sah — apa yang biasanya diprioritaskan lawan tertentu
* [ ] 4.3 Mengenali kecenderungan menahan angka (indikasi tangan berat di angka tersebut)
* [ ] 4.4 Membangun profil lawan lintas beberapa partai (bukan cuma satu observasi)

Mini Project: Opponent Pattern Log — profil kebiasaan minimal 2 lawan tetap, dikumpulkan dari minimal 4 partai per lawan.

Status: Not started

---

## Module 5 — Inferensi Kartu Tersembunyi Lawan

Description: Inferensi deduktif — menyimpulkan keping yang TIDAK dipegang lawan dari kesempatan main yang dilewatkan di ujung tertentu, membangun "peta tangan" perkiraan tiap lawan seiring partai berjalan. Berbasis kerangka belief-update dari teori game informasi tersembunyi (von Neumann-Morgenstern, Harsanyi).

Lessons:

* [ ] 5.1 Logika deduktif dasar — kalau lawan tidak main di ujung yang tersedia, lawan tidak pegang angka itu
* [ ] 5.2 Membangun peta tangan perkiraan lawan yang diperbarui tiap langkah (belief update)
* [ ] 5.3 Menggabungkan inferensi dengan tracking (Module 3) & pola lawan (Module 4) jadi satu gambaran utuh
* [ ] 5.4 Latihan inferensi pada partai nyata — bandingkan peta tangan perkiraan vs tangan asli lawan setelah partai selesai

Mini Project: Inference Worksheet — bangun peta tangan perkiraan untuk 3 partai nyata, cocokkan akurasinya dengan tangan asli lawan setelah partai selesai.

Status: Not started

---

# Intermediate Project — Checkpoint Integrasi Module 1-5

Description: **Full Match Analysis Report** — analisis lengkap 3-5 partai nyata learner sendiri (dari Game Log yang sudah dikumpulkan sejak Module 1), menggabungkan tracking kartu (Module 3), profil pola tiap lawan (Module 4), dan inferensi tangan tersembunyi (Module 5) — ditulis manual SEBELUM dicocokkan ke hasil akhir partai, mengevaluasi seberapa akurat inferensi yang dibuat selama partai berlangsung, sebelum masuk ke lapisan counting lanjutan/blocking/baiting/endgame/psikologi di Module 6-10.

Status: Not started

---

## Module 6 — Counting Lanjutan: Expected Value Tiap Langkah

Description: Menggabungkan probabilitas (Module 2), tracking (Module 3), dan inferensi (Module 5) jadi satu kerangka pengambilan keputusan kuantitatif — menghitung/mengestimasi nilai (expected value) tiap kandidat langkah di titik keputusan kritis, diadaptasi hati-hati dari kerangka EV literatur poker (Chen & Ankenman) ke konteks domino.

Lessons:

* [ ] 6.1 Konsep expected value diadaptasi ke domino — apa yang dimaksimalkan (peluang menang vs minimasi sisa poin)
* [ ] 6.2 Menghitung EV kandidat langkah dari data probabilitas & tracking yang sudah dikumpulkan
* [ ] 6.3 Kapan EV jangka pendek (menang langkah ini) vs EV jangka panjang (posisi lebih baik nanti) berbeda arah
* [ ] 6.4 Latihan EV pada titik keputusan kritis dari partai nyata (Intermediate Project)

Mini Project: Expected Value Decision Log — hitung EV untuk 5 titik keputusan kritis dari partai nyata, catat langkah yang dipilih dan alasannya.

Status: Not started

---

## Module 7 — Strategi Blocking

Description: Mengendalikan angka mana yang terbuka/tertutup di papan secara sengaja — menahan keping "pembunuh" (killer tile) untuk memblokir lawan, terutama efektif di fase akhir partai saat opsi lawan menyempit.

Lessons:

* [ ] 7.1 Konsep "killer tile" — keping yang menutup total peluang lawan main di satu ujung
* [ ] 7.2 Kapan menahan vs kapan langsung memainkan keping pembunuh
* [ ] 7.3 Membaca kapan papan sudah cukup sempit untuk blocking efektif (menghubungkan ke tracking Module 3)
* [ ] 7.4 Latihan skenario blocking dari state papan nyata

Mini Project: Blocking Drill — identifikasi & eksekusi minimal 3 peluang blocking nyata dari partai yang dimainkan, evaluasi hasilnya.

Status: Not started

---

## Module 8 — Strategi Baiting & Batas Realistis Bluff

Description: Memancing lawan membuka informasi lewat pilihan langkah, dan menyamarkan isi tangan sendiri saat ada beberapa opsi sah — dijelaskan dengan batas realistis aturan domino (wajib main kalau bisa, tidak ada bluff ala poker) di awal modul supaya ekspektasi tepat.

Lessons:

* [ ] 8.1 Kenapa "bluff" domino berbeda dari poker — batas aturan wajib-main dijelaskan eksplisit
* [ ] 8.2 Baiting lewat pemilihan langkah di antara opsi sah yang setara
* [ ] 8.3 Menyamarkan kekuatan tangan lewat urutan & tempo main
* [ ] 8.4 Latihan baiting pada partai nyata — evaluasi apakah lawan benar-benar terpancing

Mini Project: Baiting Log — rancang & eksekusi minimal 3 langkah baiting di partai nyata, catat reaksi lawan dan apakah informasi yang didapat akurat.

Status: Not started

---

## Module 9 — Decision-Making di Endgame

Description: Fase akhir partai — meminimalkan sisa poin kalau permainan berpotensi buntu, timing kapan "keluar" (habiskan kartu) vs menahan untuk posisi lebih aman, menggabungkan semua skill sebelumnya (tracking, inferensi, EV, blocking) di titik-titik keputusan paling kritis.

Lessons:

* [ ] 9.1 Mengenali tanda-tanda partai menuju buntu (blocked) lebih awal
* [ ] 9.2 Meminimalkan sisa poin di tangan saat partai berpotensi buntu
* [ ] 9.3 Timing "keluar" — kapan agresif menghabiskan kartu vs kapan bermain aman
* [ ] 9.4 Latihan endgame pada partai nyata dengan tekanan skor seri (bukan partai tunggal)

Mini Project: Endgame Decision Journal — catat & evaluasi keputusan endgame dari 5 partai nyata, bandingkan hasil aktual vs keputusan optimal secara retrospektif.

Status: Not started

---

## Module 10 — Mental Game & Psikologi Lawan Tingkat Expert

Description: Modul penutup & Capstone — psikologi kompetitif domino-spesifik (manajemen tilt akibat blokir berulang), membaca "table image" lawan lintas ronde dalam satu seri partai, etiket kompetitif dari tradisi turnamen Karibia/Amerika Latin, dan integrasi semua skill jadi satu sistem bermain pribadi berkelanjutan.

Lessons:

* [ ] 10.1 Tilt domino-spesifik — mengelola frustrasi akibat diblokir/kalah beruntun tanpa merusak kualitas keputusan
* [ ] 10.2 Table image — bagaimana pola bermain sendiri lintas ronde memengaruhi cara lawan membaca balik
* [ ] 10.3 Etiket & disiplin kompetitif seri partai (per tradisi turnamen Karibia/Amerika Latin, Module Practitioner Reference Frame)
* [ ] 10.4 Menyusun Personal Domino Strategy System — integrasi tracking, EV, blocking, baiting, dan psikologi jadi satu sistem

Capstone Project: **Personal Domino Strategy System** — sistem bermain pribadi terkodifikasi (metode tracking manual, kerangka EV per langkah, playbook blocking & baiting, protokol inferensi, catatan psikologi lawan), diuji nyata lewat satu seri partai (mis. first-to-N-poin) dengan data menang-kalah/rata-rata sisa poin terukur sebelum-sesudah, disimpan ke `portofolio/`.

Status: Not started

---

# Enterprise Project

Description: **Domino Club Analytics Kit** — simulasikan skenario "melatih grup bermain domino" (keluarga/komunitas): kumpulkan data Game Log dari minimal 2 pemain lain selain diri sendiri, bangun ringkasan analitik lintas pemain (rata-rata sisa poin, akurasi inferensi, frekuensi blocking berhasil) yang dipakai untuk memberi rekomendasi perbaikan konkret ke tiap pemain — mensimulasikan bagaimana seorang "analyst" domino berkomunikasi temuan taktis ke orang lain, bukan cuma ke diri sendiri.

Acceptance Criteria: Minimal 2 pemain selain diri sendiri dianalisis dari data partai nyata; setiap rekomendasi didukung bukti konkret dari log (bukan kesan umum), konsisten dengan `standards/UNIVERSAL_STANDARDS.md`; ringkasan ditulis supaya bisa dipahami pemain yang direview (bukan jargon internal semata).

Status: Not started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Belum dimulai (menunggu approval roadmap)

Current Lesson: —

Next Lesson: 1.1 (setelah approval)
