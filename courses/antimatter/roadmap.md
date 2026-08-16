# Course Roadmap

## Course Information

* Course Name: Antimatter — dari 0 sampai Expert
* Category: Antimateri (Domain: antimatter — lihat `domains/antimatter/DOMAIN.md`)
* Difficulty: Level 0 (bisa coding kuat, tahu E=mc² secara populer, tapi belum pernah menurunkan kinematika relativistik atau menyentuh persamaan Dirac) → Level 4 (mampu menurunkan sendiri kenapa persamaan Dirac memprediksi antimateri, menghitung kinematika anihilasi/produksi pasangan dari nol, dan menjelaskan secara teknis-jujur bagaimana antimateri diproduksi/disimpan/diukur di eksperimen nyata)
* Estimated Duration: 14 modul (13 modul konten + 1 modul capstone), 4 lesson/modul (56 lesson), pace disesuaikan karena Applied Practice domain ini SELALU berujung derivasi/kalkulasi tervalidasi atau studi kasus berbasis sumber institusional — bukan sekadar baca teori
* Prerequisites: Kemampuan programming Python dasar-menengah (skrip kecil, comfortable baca dokumentasi library seperti NumPy). Aljabar & trigonometri tingkat SMA. TIDAK wajib menguasai relativitas khusus atau mekanika kuantum sebelumnya — hasil relativitas yang dipakai (four-momentum, E=mc²) di-bridge ringkas di Module 1, dan QM secukupnya untuk persamaan Dirac dibangun dari nol di Module 2. Domain ini murni fisika partikel/antimateri; pertanyaan yang menyentuh derivasi relativitas/QFT penuh, fisika nuklir/reaktor, atau quantum computing akan dinyatakan eksplisit di luar scope dan diarahkan ke domain lain di repo ini (lihat `domains/antimatter/DOMAIN.md`).

---

# Learning Objectives

* [ ] Menguasai hasil relativitas khusus yang dipakai langsung di fisika partikel (four-momentum, E²=(pc)²+(mc²)², natural units) tanpa perlu derivasi GR/SR penuh
* [ ] Memahami fondasi mekanika kuantum secukupnya (wave function, operator, spin) untuk membaca persamaan Dirac
* [ ] Menjelaskan peta besar Model Standard partikel: fermion (quark/lepton, 3 generasi), boson gauge, dan empat interaksi fundamental
* [ ] Menurunkan (secara konseptual-matematis) kenapa persamaan Dirac (1928) memprediksi solusi energi negatif, dan bagaimana ini mengarah ke prediksi antimateri lewat hole theory
* [ ] Menjelaskan sejarah & metode penemuan eksperimental positron (Anderson 1932-1933) dan antiproton (Segrè & Chamberlain 1955)
* [ ] Menguasai charge conjugation (C) dan simetri CPT sebagai kerangka antipartikel di seluruh Model Standard
* [ ] Menghitung kinematika relativistik anihilasi elektron-positron dan memahami positronium (para vs ortho, lifetime)
* [ ] Menghitung ambang energi produksi pasangan (pair production) untuk berbagai skenario, divalidasi terhadap nilai PDG
* [ ] Menjelaskan cara antiproton benar-benar diproduksi di fasilitas nyata (CERN Antiproton Decelerator/ELENA)
* [ ] Menjelaskan cara antimateri disimpan (Penning trap, magnetic bottle) dan cara antihidrogen dibuat/diukur (eksperimen ALPHA)
* [ ] Menjelaskan eksperimen gravitasi antimateri ALPHA-g (2023) dan implikasinya terhadap weak equivalence principle
* [ ] Menjelaskan masalah asimetri materi-antimateri (baryogenesis), tiga syarat Sakharov, dan bukti eksperimental pelanggaran CP — dengan status epistemis yang jujur (riset terbuka, bukan solved problem)
* [ ] Menjelaskan aplikasi nyata antimateri (PET scan, positron annihilation spectroscopy) dan menilai secara kuantitatif jujur klaim propulsi/energi antimateri
* [ ] Menghasilkan Capstone Project (Antiparticle Physics Notebook / CERN Antimatter Facility Report / Baryogenesis & CP Violation Research Paper) dengan derivasi, validasi data nyata, dan status epistemis eksplisit

---

# Course Modules

## Module 1 — Prasyarat Cepat: Relativitas Khusus untuk Fisika Partikel

Description: Fisika partikel modern seluruhnya relativistik — modul ini membangun HANYA hasil yang langsung dipakai (four-momentum, invarian massa, E²=(pc)²+(mc²)², natural units ħ=c=1) tanpa mengulang derivasi penuh Relativitas Khusus (itu milik domain [[domains/relativity/DOMAIN.md|relativity]]). Mengikuti MIT OCW 8.701 minggu pengantar dan Griffiths Bab 1.

Lessons:

* [ ] 1.1 Kenapa fisika partikel butuh relativitas — energi ikat, produksi partikel baru, kecepatan mendekati c di akselerator
* [ ] 1.2 Four-momentum & invarian massa: E²=(pc)²+(mc²)² — turunan singkat dari transformasi Lorentz (hasil, bukan derivasi ulang)
* [ ] 1.3 Natural units (ħ=c=1) — kenapa fisika partikel memakainya, cara konversi ke satuan SI
* [ ] 1.4 Python: skrip kecil konversi antar satuan (GeV, natural units, SI) dan verifikasi E²=(pc)²+(mc²)² untuk beberapa partikel nyata (data massa dari PDG)

Mini Project: Skrip Python yang mengambil massa diam beberapa partikel dari tabel PDG (elektron, proton, muon), menghitung energi total pada beberapa momentum berbeda, dan memverifikasi identitas E²=(pc)²+(mc²)² secara numerik.

Status: Not started

---

## Module 2 — Fondasi Mekanika Kuantum Secukupnya untuk Persamaan Dirac

Description: Persamaan Dirac butuh bahasa QM dasar (wave function, operator, spin) — modul ini membangunnya secukupnya untuk Module 4, BUKAN kursus QM penuh (domain [[domains/quantum-computing-for-engineers/DOMAIN.md|quantum-computing-for-engineers]] membangun QM untuk tujuan berbeda). Mengikuti Griffiths Bab 1 & Feynman Lectures Vol. III untuk intuisi.

Lessons:

* [ ] 2.1 Wave function & persamaan Schrödinger non-relativistik — kenapa ini TIDAK cukup untuk partikel berkecepatan relativistik
* [ ] 2.2 Operator & observable secukupnya, notasi bra-ket ringkas
* [ ] 2.3 Spin sebagai derajat kebebasan intrinsik — spin-1/2 untuk elektron, kenapa ini penting untuk persamaan Dirac (spinor, bukan skalar)
* [ ] 2.4 Kenapa menyatukan QM + relativitas khusus itu sulit — masalah persamaan Klein-Gordon (densitas probabilitas negatif) sebagai motivasi kenapa Dirac butuh pendekatan berbeda

Mini Project: Tulisan pendek (1 halaman) yang menjelaskan dengan bahasa sendiri kenapa persamaan Schrödinger biasa gagal untuk elektron relativistik, dan kenapa masalah ini menuntun ke persamaan Dirac — dicek terhadap penjelasan Griffiths/Feynman.

Status: Not started

---

## Module 3 — Model Standard Partikel: Peta Besar

Description: Peta besar seluruh partikel fundamental yang diketahui sebelum masuk ke antimateri secara spesifik — fermion, boson gauge, dan empat interaksi fundamental. Mengikuti Griffiths Bab 1 dan halaman resmi CERN "The Standard Model."

Lessons:

* [ ] 3.1 Fermion: quark (up, down, charm, strange, top, bottom — 3 generasi) dan lepton (elektron, muon, tau + neutrino masing-masing)
* [ ] 3.2 Boson gauge: foton (elektromagnetik), gluon (kuat), W±/Z (lemah), Higgs boson (massa) — pembawa interaksi
* [ ] 3.3 Empat interaksi fundamental: kuat, lemah, elektromagnetik, gravitasi — kekuatan relatif, jangkauan, partikel yang terlibat
* [ ] 3.4 Muatan, warna (color charge), dan bilangan kuantum lain secukupnya untuk memahami kenapa partikel tertentu berinteraksi dengan cara tertentu

Mini Project: Diagram/tabel buatan sendiri (boleh teks terstruktur) yang memetakan seluruh partikel Model Standard beserta massa (dari PDG), muatan, dan interaksi yang dialaminya — dicek kelengkapan & akurasi terhadap PDG.

Status: Not started

---

## Module 4 — Persamaan Dirac & Prediksi Antimateri (1928)

Description: Titik masuk resmi ke antimateri — bagaimana P.A.M. Dirac (1928) mencoba menyatukan QM dan relativitas khusus untuk elektron, dan bagaimana persamaannya "memaksa" munculnya solusi energi negatif yang akhirnya diinterpretasikan sebagai antipartikel. Mengikuti Griffiths Bab 7, Halzen & Martin Bab 2, dan paper asli Dirac (1928).

Lessons:

* [ ] 4.1 Motivasi persamaan Dirac: mencari persamaan relativistik orde-satu (bukan orde-dua seperti Klein-Gordon) untuk elektron
* [ ] 4.2 Struktur persamaan Dirac & munculnya spinor 4-komponen — kenapa elektron relativistik butuh 4 komponen, bukan 1
* [ ] 4.3 Solusi energi negatif — masalah yang muncul, dan interpretasi hole theory Dirac: "lautan" elektron energi negatif yang terisi penuh, lubang di dalamnya berperilaku seperti partikel bermuatan positif
* [ ] 4.4 Dari hole theory ke antipartikel: kenapa interpretasi modern (Feynman-Stückelberg, antipartikel sebagai partikel bergerak mundur dalam waktu) menggantikan hole theory, tapi kesimpulannya sama — antimateri harus ada

Mini Project: Derivasi tertulis (boleh tangan, difoto/scan, atau LaTeX) yang menelusuri alur logika dari motivasi persamaan Dirac sampai kesimpulan "harus ada partikel dengan massa sama tapi muatan berlawanan" — dengan penjelasan sendiri (bukan salinan buku) kenapa setiap langkah diperlukan.

Status: Not started

---

## Module 5 — Penemuan Eksperimental: Positron & Antiproton

Description: Dari prediksi matematis ke bukti eksperimental — sejarah dan metode penemuan positron (Anderson, 1932-1933) dan antiproton (Segrè & Chamberlain, 1955), termasuk kenapa metode eksperimen itu meyakinkan. Mengikuti paper asli Anderson (1933) dan sumber sejarah CERN/Nobel Prize.

Lessons:

* [ ] 5.1 Cloud chamber & sinar kosmik — metode eksperimen Carl Anderson, kenapa medan magnet + jejak melengkung bisa membedakan muatan partikel
* [ ] 5.2 Penemuan positron (1932-1933): bagaimana Anderson membedakan jejak positron dari elektron/proton, dan kenapa hasilnya awalnya diragukan sebelum diterima luas
* [ ] 5.3 Antiproton (1955): kenapa dibutuhkan akselerator (Bevatron) untuk memproduksinya — ambang energi yang jauh lebih tinggi dari positron, metode Segrè & Chamberlain membedakan antiproton dari partikel latar belakang
* [ ] 5.4 Pola umum: kenapa antipartikel bermassa lebih besar (antiproton) jauh lebih sulit diproduksi/dideteksi daripada antipartikel bermassa kecil (positron) — jembatan konseptual ke Module 8-9

Mini Project: Ringkasan tertulis (studi kasus) yang membandingkan metode eksperimen Anderson (1932-33) vs Segrè-Chamberlain (1955) — persamaan prinsip dasar (deteksi lewat jejak di medan magnet) dan perbedaan tantangan teknis (energi, latar belakang noise) — berbasis paper primer & sumber institusional.

Status: Not started

---

## Module 6 — Antipartikel di Seluruh Model Standard: Charge Conjugation & CPT

Description: Generalisasi dari "elektron punya positron" ke "setiap partikel Model Standard punya antipartikel" — charge conjugation (C) sebagai operasi simetri, dan simetri CPT sebagai pernyataan mendalam tentang struktur hukum fisika. Mengikuti Halzen & Martin Bab 2 dan Griffiths Bab 4.

Lessons:

* [ ] 6.1 Charge conjugation (C): definisi operasi, kenapa ia mengubah partikel jadi antipartikelnya (muatan, bilangan lepton/baryon berbalik tanda, massa/spin sama)
* [ ] 6.2 Antipartikel untuk tiap jenis partikel Model Standard: antiquark, antilepton (positron, antimuon, dst), antineutrino — dan kasus khusus foton (antipartikelnya diri sendiri)
* [ ] 6.3 Simetri CPT (Charge-Parity-Time): pernyataan teorema CPT (tanpa bukti QFT penuh — itu di luar scope), implikasinya (antipartikel WAJIB punya massa & lifetime identik dengan partikelnya)
* [ ] 6.4 Pelanggaran C dan P secara individual (interaksi lemah) vs CPT yang belum pernah teramati dilanggar — jembatan konseptual ke Module 12 (pelanggaran CP)

Mini Project: Tabel buatan sendiri yang memetakan setiap partikel di Module 3 ke antipartikelnya (simbol, muatan, bilangan kuantum yang berbalik tanda), plus tulisan pendek menjelaskan kenapa foton adalah pengecualian — divalidasi terhadap PDG.

Status: Not started

---

## Module 7 — Anihilasi Partikel-Antipartikel: Kinematika & Positronium

Description: Apa yang terjadi secara kuantitatif saat partikel bertemu antipartikelnya — kinematika relativistik anihilasi elektron-positron, dan positronium sebagai "atom" sementara elektron-positron sebelum beranihilasi. Mengikuti Griffiths Bab 6 dan MIT OCW 8.701.

Lessons:

* [ ] 7.1 Kenapa anihilasi elektron-positron minimal menghasilkan 2 foton (bukan 1) — derivasi dari kekekalan energi-momentum di kerangka pusat massa
* [ ] 7.2 Energi & arah foton hasil anihilasi (kasus diam vs bergerak) — kalkulasi kinematika lengkap
* [ ] 7.3 Positronium: "atom" elektron-positron terikat sementara, para-positronium (spin singlet, 2 foton, lifetime ~0.125 ns) vs ortho-positronium (spin triplet, 3 foton, lifetime ~142 ns) — kenapa perbedaan spin mengubah jumlah foton & lifetime drastis
* [ ] 7.4 Python: kalkulator kinematika anihilasi (energi/momentum foton hasil untuk berbagai energi elektron-positron awal), validasi terhadap kasus diam (masing-masing foton 511 keV)

Mini Project: Skrip Python yang menghitung energi & arah foton hasil anihilasi elektron-positron untuk beberapa skenario energi awal berbeda (termasuk kasus diam sebagai validasi terhadap nilai 511 keV yang dikenal), plus tulisan pendek membandingkan lifetime para- vs ortho-positronium terhadap nilai PDG/literatur.

Status: Not started

---

## Module 8 — Produksi Pasangan (Pair Production): Fisika & Ambang Energi

Description: Kebalikan dari anihilasi — bagaimana foton berenergi tinggi bisa "berubah" jadi pasangan partikel-antipartikel, dan kenapa ini butuh syarat energi ambang serta medan Coulomb inti. Mengikuti Griffiths Bab 6 dan MIT OCW 8.701.

Lessons:

* [ ] 8.1 Kenapa foton tunggal di ruang hampa TIDAK bisa berubah jadi pasangan elektron-positron — argumen kekekalan energi-momentum yang melarangnya
* [ ] 8.2 Peran medan Coulomb inti — bagaimana kehadiran inti atom memungkinkan pair production dengan menyerap sebagian momentum
* [ ] 8.3 Ambang energi pair production: derivasi syarat energi minimum foton (2×m_e c² ditambah energi recoil inti)
* [ ] 8.4 Pair production untuk pasangan lebih berat (mis. proton-antiproton) di akselerator — kenapa ambang energinya jauh lebih tinggi, jembatan ke Module 9

Mini Project (Intermediate Project — "Kalkulator Kinematika Antimateri"): Skrip Python yang menghitung ambang energi pair production untuk berbagai pasangan partikel-antipartikel (elektron-positron, proton-antiproton) dan berbagai skenario (foton + inti diam, tumbukan partikel energi tinggi), divalidasi terhadap nilai referensi PDG/textbook — menggabungkan kalkulator anihilasi Module 7 jadi satu tool kinematika antimateri.

Status: Not started

---

## Module 9 — Produksi Antiproton Nyata: CERN Antiproton Decelerator & ELENA

Description: Dari fisika ke rekayasa nyata — bagaimana antiproton benar-benar diproduksi dalam jumlah terukur di fasilitas CERN hari ini, bukan cuma di eksperimen sejarah 1955. Mengikuti halaman resmi CERN Antimatter Factory dan dokumentasi fasilitas AD/ELENA.

Lessons:

* [ ] 9.1 Proses produksi: tumbukan proton berenergi tinggi (dari PS, Proton Synchrotron) ke target logam, menghasilkan antiproton di antara banyak partikel lain
* [ ] 9.2 Antiproton Decelerator (AD): kenapa antiproton hasil tumbukan berenergi terlalu tinggi & perlu "diperlambat" (deceleration), bukan dipercepat, sebelum bisa dipakai eksperimen
* [ ] 9.3 ELENA (Extra Low ENergy Antiproton ring): perkembangan lanjutan AD, memperlambat antiproton ke energi jauh lebih rendah untuk efisiensi eksperimen
* [ ] 9.4 Skala nyata: berapa banyak antiproton yang benar-benar diproduksi per tahun di CERN, dan kenapa ini penting untuk memahami batas realistis aplikasi antimateri (jembatan ke Module 13)

Mini Project: Studi kasus tertulis (1-2 halaman) yang menjelaskan alur penuh produksi antiproton di CERN dari tumbukan proton sampai antiproton siap dipakai eksperimen (PS → AD → ELENA), dengan angka skala produksi nyata dari sumber CERN — mengikuti pola Applied Practice `domains/nuclear-technology/DOMAIN.md`.

Status: Not started

---

## Module 10 — Penyimpanan Antimateri: Penning Trap, Magnetic Bottle & Antihidrogen (ALPHA)

Description: Antimateri tidak bisa disimpan di wadah biasa (anihilasi instan dengan dinding materi) — modul ini membahas cara nyata menjebak antipartikel bermuatan dan atom antihidrogen netral. Mengikuti dokumentasi eksperimen ALPHA & BASE di CERN.

Lessons:

* [ ] 10.1 Kenapa antimateri tidak bisa disimpan di wadah biasa — anihilasi instan saat bersentuhan dengan materi
* [ ] 10.2 Penning trap & Penning-Malmberg trap: menjebak partikel bermuatan (antiproton, positron) memakai kombinasi medan listrik & magnet, tanpa dinding fisik
* [ ] 10.3 Membuat antihidrogen: menggabungkan antiproton + positron jadi atom antihidrogen netral (eksperimen ALPHA) — kenapa atom netral butuh pendekatan berbeda (magnetic bottle/magnetic minimum trap), bukan Penning trap murni
* [ ] 10.4 Berapa lama antimateri bisa disimpan hari ini — rekor lifetime penyimpanan antihidrogen ALPHA, dan kenapa ini penting untuk eksperimen presisi (jembatan ke Module 11)

Mini Project: Studi kasus tertulis yang menjelaskan perbedaan Penning trap (partikel bermuatan) vs magnetic bottle (atom netral antihidrogen), dan kenapa masing-masing dibutuhkan pada tahap berbeda dari proses "antiproton lepas → antihidrogen terjebak" di eksperimen ALPHA — berbasis dokumentasi CERN/paper ALPHA.

Status: Not started

---

## Module 11 — Gravitasi Antimateri: Eksperimen ALPHA-g

Description: Pertanyaan fundamental — apakah antimateri jatuh ke bawah seperti materi, atau ke atas? Modul ini membahas eksperimen ALPHA-g (Nature, 2023) yang akhirnya menjawabnya secara langsung. Sumber primer: ALPHA Collaboration (2023), *Nature* 621.

Lessons:

* [ ] 11.1 Kenapa pertanyaan ini penting secara fundamental — weak equivalence principle (semua massa jatuh dengan percepatan sama di medan gravitasi yang sama), dan kenapa antimateri secara historis belum pernah diuji langsung sampai baru-baru ini
* [ ] 11.2 Desain eksperimen ALPHA-g: bagaimana antihidrogen netral (dari Module 10) dijatuhkan secara terkontrol dalam vertical trap, dan bagaimana arah jatuhnya diukur secara presisi
* [ ] 11.3 Hasil eksperimen (2023): antihidrogen jatuh ke BAWAH (bukan ke atas), konsisten dengan weak equivalence principle dalam batas presisi eksperimen saat ini
* [ ] 11.4 Implikasi & batas: apa yang sudah dikonfirmasi vs apa yang masih perlu presisi lebih tinggi untuk diuji (mis. penyimpangan kecil dari prediksi GR) — status epistemis eksplisit, bukan "masalah selesai total"

Mini Project: Ringkasan tertulis hasil eksperimen ALPHA-g berbasis paper primer *Nature* 621 — menjelaskan desain eksperimen, hasil kuantitatif (percepatan gravitasi terukur untuk antihidrogen dibanding materi biasa), dan batas presisi yang masih ada, dengan label status epistemis eksplisit (confirmed vs masih diteliti lebih presisi).

Status: Not started

---

## Module 12 — Asimetri Materi-Antimateri & Baryogenesis: Syarat Sakharov dan Pelanggaran CP

Description: Pertanyaan kosmologis besar — kalau Big Bang seharusnya memproduksi materi dan antimateri dalam jumlah setara, kenapa alam semesta yang teramati didominasi materi? Modul ini WAJIB menyatakan status riset terbuka secara eksplisit, mengikuti Perkins *Particle Astrophysics* dan paper primer Sakharov (1967) & Christenson-Cronin-Fitch-Turlay (1964).

Lessons:

* [ ] 12.1 Masalah asimetri: kalau hukum fisika (hampir) simetris antara materi-antimateri, kenapa alam semesta yang teramati nyaris tidak punya antimateri primordial?
* [ ] 12.2 Tiga syarat Sakharov (1967): pelanggaran baryon number, pelanggaran C & CP, departure from thermal equilibrium — kenapa ketiganya secara logis DIPERLUKAN (necessary condition), bukan otomatis cukup (sufficient) untuk menjelaskan skala asimetri yang teramati
* [ ] 12.3 Bukti eksperimental pelanggaran CP: penemuan asli kaon netral (Christenson-Cronin-Fitch-Turlay, 1964), dan pengukuran modern di B meson (BaBar/Belle) & D meson (LHCb)
* [ ] 12.4 Status riset per 2026: pelanggaran CP yang teramati di Model Standard TIDAK cukup besar untuk menjelaskan skala asimetri yang diamati — kenapa ini masih masalah terbuka, arah riset lanjut (leptogenesis, CP violation di sektor neutrino)

Mini Project (menuju Capstone jalur C): Skrip Python yang mereplikasi perhitungan asimetri CP sederhana dari data yang dipublikasikan (mis. rate pelanggaran CP kaon atau B meson dari PDG/paper), plus tulisan analisis (1-2 halaman) tiga syarat Sakharov dengan status epistemis eksplisit untuk tiap klaim (solid/terbuka/spekulatif).

Status: Not started

---

## Module 13 — Aplikasi Nyata: PET Scan, Positron Annihilation Spectroscopy, dan Realita Propulsi Antimateri

Description: Menutup materi non-capstone dengan aplikasi antimateri yang benar-benar dipakai hari ini, dikontraskan tegas secara kuantitatif dengan klaim propulsi/energi antimateri yang masih jauh dari layak secara teknis. Mengikuti sumber SNMMI (kedokteran nuklir) dan angka produksi CERN dari Module 9.

Lessons:

* [ ] 13.1 PET scan (Positron Emission Tomography): prinsip kerja — isotop pemancar positron, anihilasi menghasilkan 2 foton 511 keV terdeteksi coincidence, rekonstruksi citra — koneksi langsung ke kinematika anihilasi Module 7
* [ ] 13.2 Positron annihilation spectroscopy: memakai positron untuk mendeteksi cacat/vacancy pada material (semikonduktor, logam) — prinsip kerja secukupnya
* [ ] 13.3 Realita kuantitatif propulsi/energi antimateri: berapa antiproton yang pernah diproduksi manusia (skala nanogram/tahun dari Module 9), estimasi biaya produksi per gram, perbandingan dengan energi yang dibutuhkan misi propulsi — kenapa ini jauh dari layak hari ini
* [ ] 13.4 Sintesis: peta lengkap "apa yang sudah nyata dipakai" (PET scan, spectroscopy) vs "apa yang masih riset dasar" (produksi/penyimpanan skala besar) vs "apa yang masih fiksi ilmiah" (propulsi bertenaga antimateri) — status epistemis eksplisit untuk tiap kategori

Mini Project: Studi kasus tertulis yang membandingkan tiga hal secara eksplisit dengan angka: (1) skala antimateri yang dipakai per prosedur PET scan (sangat kecil, isotop meluruh cepat), (2) skala antiproton yang pernah diproduksi CERN per tahun, (3) skala antimateri yang secara teoretis dibutuhkan untuk propulsi bermakna — menunjukkan kesenjangan orde magnitudo secara eksplisit, bukan cuma pernyataan kualitatif "masih jauh".

Status: Not started

---

## Module 14 — Capstone: Antimatter Research Portfolio

Description: Mengintegrasikan seluruh Module 1-13 jadi satu capstone project penuh, mengikuti `domains/antimatter/DOMAIN.md` bagian "Project Tiers".

Lessons:

* [ ] 14.1 Menentukan jalur capstone & scope: (A) Antiparticle Physics Notebook, (B) CERN Antimatter Facility Report, atau (C) Baryogenesis & CP Violation Research Paper
* [ ] 14.2 Riset & pengumpulan sumber primer/institusional sesuai jalur yang dipilih (PDG, paper primer, dokumentasi CERN)
* [ ] 14.3 Penyusunan derivasi/kalkulasi/analisis inti sesuai jalur, termasuk validasi kode/data nyata
* [ ] 14.4 Dokumentasi tertulis final: status epistemis eksplisit tiap klaim besar (solid/terbuka/spekulatif), submit ke `portofolio/`

Capstone Project: Pilih SATU — **(A) Antiparticle Physics Notebook**: derivasi penuh alur logika persamaan Dirac sampai prediksi antipartikel (Module 4) + kalkulator kinematika anihilasi/produksi pasangan tervalidasi terhadap PDG (Module 7-8), didokumentasikan sebagai notebook teknis. **(B) CERN Antimatter Facility Report**: studi kasus teknis-mendalam produksi & penyimpanan antiproton di AD/ELENA (Module 9) plus eksperimen ALPHA/ALPHA-g (Module 10-11), berbasis sumber institusional CERN & paper primer *Nature* 621. **(C) Baryogenesis & CP Violation Research Paper**: analisis tertulis tiga syarat Sakharov + replikasi numerik data asimetri CP dari eksperimen nyata (Module 12) + pernyataan eksplisit status riset terbuka, dengan sintesis aplikasi nyata vs spekulatif (Module 13). Semua jalur wajib validasi terhadap data PDG/paper primer, plus dokumentasi status epistemis eksplisit untuk tiap klaim besar.

Status: Not started

---

# Capstone Project

Description: Lihat Module 14 di atas untuk rincian lengkap tiga jalur capstone (Antiparticle Physics Notebook / CERN Antimatter Facility Report / Baryogenesis & CP Violation Research Paper). Capstone course ini SATU dengan Capstone Project Module 14 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/antimatter/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` DITAMBAH keempat lensa Review Style domain ini dari `domains/antimatter/DOMAIN.md`: **Derivational Rigor** (tiap langkah derivasi/kalkulasi punya justifikasi eksplisit), **Dimensional & Limit Consistency** (satuan konsisten, hasil kembali ke limit yang benar), **Real-Data Grounding** (angka cocok dengan nilai referensi PDG/paper primer), dan **Epistemic Discipline** (klaim solid vs riset terbuka vs spekulatif dibedakan secara eksplisit, khususnya untuk baryogenesis dan klaim propulsi/energi antimateri). Disimpan ke `portofolio/` disertai dokumentasi status epistemis eksplisit.

Status: Not started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: Module 1 — Prasyarat Cepat: Relativitas Khusus untuk Fisika Partikel (menunggu approval roadmap)

Current Lesson: 1.1 Kenapa fisika partikel butuh relativitas — energi ikat, produksi partikel baru, kecepatan mendekati c di akselerator

Next Lesson: 1.1 Kenapa fisika partikel butuh relativitas — energi ikat, produksi partikel baru, kecepatan mendekati c di akselerator
