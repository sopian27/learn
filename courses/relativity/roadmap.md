# Course Roadmap

## Course Information

* Course Name: Teori Relativitas — dari 0 sampai Level Pascasarjana Awal (Special & General Relativity)
* Category: Relativitas (Domain: relativity — lihat `domains/relativity/DOMAIN.md`)
* Difficulty: Level 0 (belum tahu apa-apa soal relativitas, modal awal cuma mekanika klasik & kalkulus dasar) → Level 4 (setara mahasiswa pascasarjana awal fisika teoretis: mampu menurunkan sendiri hasil-hasil kunci Relativitas Khusus & Umum dari prinsip dasar, dan membaca paper GR tingkat pengantar secara mandiri)
* Estimated Duration: 15 modul (14 modul konten + 1 modul Capstone), rata-rata 4 lesson/modul — pace disesuaikan karena tiap modul wajib menghasilkan satu derivasi tangan lengkap DAN satu artefak simulasi/kode (Derivation & Simulation Lab, per `domains/relativity/DOMAIN.md` bagian "Applied Practice Definition") sebelum dianggap selesai
* Prerequisites: Mekanika klasik/Newtonian tingkat dasar (diasumsikan sudah dikuasai atau di-refresh singkat di Module 1 — TIDAK diajarkan ulang dari nol, sesuai Domain Scope yang eksplisit mengecualikan mekanika klasik penuh), kalkulus (turunan, integral, deret Taylor), aljabar linear dasar (vektor, matriks, notasi indeks akan dibangun dari nol di Module 6), dan kemampuan coding secukupnya (bahasa bebas, memanfaatkan skill dari domain `software-engineering`) untuk artefak simulasi tiap modul. Quantum Mechanics/QFT di ruang lengkung dan astrofisika observasional teknis eksplisit di luar scope course ini.

---

# Learning Objectives

* [ ] Menjelaskan kenapa mekanika Newton & invariansi Galileo mulai retak berhadapan dengan elektrodinamika Maxwell, dan bagaimana eksperimen Michelson-Morley memotivasi postulat Einstein
* [ ] Menurunkan sendiri transformasi Lorentz dari dua postulat Einstein, dan menggunakannya untuk menjelaskan dilatasi waktu, kontraksi panjang, dan relativitas simultanitas — termasuk limit-check ke mekanika Newtonian saat v << c
* [ ] Membaca dan membangun diagram spacetime (Minkowski) untuk menyelesaikan paradoks kembar dan kasus relativitas lain secara visual-geometris, bukan hafalan rumus
* [ ] Menurunkan energi-momentum relativistik (4-momentum, E=mc²) dan menerapkannya ke tumbukan/peluruhan partikel
* [ ] Menguasai kalkulus tensor dan geometri diferensial (manifold, metrik, Christoffel symbols, tensor kurvatur Riemann) secukupnya sebagai alat untuk Relativitas Umum — bukan matematika murni abstrak
* [ ] Menurunkan persamaan medan Einstein dari prinsip ekivalensi dan geometri kelengkungan, serta menunjukkan limit Newtoniannya (persamaan Poisson)
* [ ] Menurunkan metrik Schwarzschild dan menggunakannya untuk presesi perihelion Merkurius, pembelokan cahaya gravitasional, dan struktur dasar lubang hitam
* [ ] Menjelaskan dasar matematis gelombang gravitasi dan pengantar kosmologi relativistik (metrik FLRW, persamaan Friedmann)
* [ ] Menghubungkan hasil teoretis ke bukti eksperimental nyata (GPS, LIGO/Virgo, ekspedisi Eddington 1919) dari minimal empat lensa praktisi berbeda (teoretis, eksperimental, insinyur terapan, komunikator sains)
* [ ] Menghasilkan Relativity Portfolio sebagai Capstone Project — derivasi penuh + simulasi numerik satu topik expert-level, disimpan ke `portofolio/`

---

# Course Modules

## Module 1 — Prasyarat & Motivasi: Kenapa Mekanika Newton Mulai Retak

Description: Sebelum masuk formalisme relativitas, modul ini merefresh singkat mekanika klasik & invariansi Galileo (bukan mengajarkannya dari nol — sudah diasumsikan dikuasai per Domain Scope), lalu menunjukkan kontradiksi nyata yang memaksa lahirnya Relativitas Khusus: elektrodinamika Maxwell yang tidak invarian Galileo, dan hasil nol eksperimen Michelson-Morley.

Lessons:

* [ ] 1.1 Refresh kilat: kerangka acuan inersial, transformasi Galileo, dan invariansi hukum Newton
* [ ] 1.2 Elektrodinamika Maxwell vs invariansi Galileo — kenapa kecepatan cahaya bermasalah kalau semua serba relatif ala Newton
* [ ] 1.3 Eksperimen Michelson-Morley: mencari eter yang tidak pernah ketemu, dan kenapa hasil nolnya begitu mengganggu
* [ ] 1.4 Alat matematika secukupnya: notasi vektor, matriks, dan pengantar index notation sebagai bekal ke tensor nanti

Mini Project (Derivation & Simulation Lab): Derivasi tangan transformasi kecepatan Galileo dan tunjukkan eksplisit kenapa itu berkontradiksi dengan c konstan; kode simulasi kecil interferometer Michelson-Morley yang membandingkan fringe shift yang diprediksi kalau eter ada vs hasil nol yang benar-benar diamati.

Status: Not started

---

## Module 2 — Dua Postulat Einstein & Transformasi Lorentz

Description: Modul inti pembuka Relativitas Khusus — dari dua postulat sederhana Einstein (1905), menurunkan transformasi Lorentz sendiri, bukan menghafalnya sebagai rumus jadi. Sign convention & notasi course disepakati eksplisit di sini sesuai Domain-Specific Standards.

Lessons:

* [ ] 2.1 Dua postulat Einstein (1905) dan konsekuensi konseptualnya (dibandingkan singkat ke paper asli "Zur Elektrodynamik bewegter Körper")
* [ ] 2.2 Menurunkan transformasi Lorentz langsung dari dua postulat (pendekatan Taylor & Wheeler / Susskind)
* [ ] 2.3 Velocity parameter (rapidity) dan komposisi kecepatan relativistik
* [ ] 2.4 Menyepakati notasi & sign convention yang dipakai course ini sejak awal (per house rule domain)

Mini Project (Derivation & Simulation Lab): Derivasi tangan lengkap transformasi Lorentz dari dua postulat, termasuk limit-check ke transformasi Galileo saat v << c; kode kalkulator transformasi Lorentz & komposisi kecepatan relativistik interaktif (input dua frame + kecepatan, output koordinat/kecepatan tertransformasi).

Status: Not started

---

## Module 3 — Dilatasi Waktu, Kontraksi Panjang, & Relativitas Simultanitas

Description: Tiga konsekuensi paling terkenal (dan paling sering disalahpahami) dari transformasi Lorentz, diturunkan dari nol lewat thought experiment klasik (light clock), bukan diberikan sebagai rumus ajaib — sekaligus tempat mengoreksi miskonsepsi populer eksplisit sesuai Domain-Specific Standards.

Lessons:

* [ ] 3.1 Dilatasi waktu: derivasi dari light clock thought experiment
* [ ] 3.2 Kontraksi panjang: derivasi, dan kenapa itu bukan ilusi optik semata
* [ ] 3.3 Relativitas simultanitas — kenapa "sekarang" tidak universal antar observer bergerak relatif
* [ ] 3.4 Mengoreksi miskonsepsi populer ("semua benda memendek jadi lebih berat", "semua serba relatif/subjektif") dan kenapa itu salah kaprah

Mini Project (Derivation & Simulation Lab): Derivasi tangan dilatasi waktu & kontraksi panjang dari light clock, dengan limit-check v << c; kode kalkulator dilatasi waktu/kontraksi panjang dengan tabel pembanding kecepatan sehari-hari (pesawat, roket) vs kecepatan relativistik signifikan.

Status: Not started

---

## Module 4 — Diagram Spacetime (Minkowski) & Paradoks Kembar

Description: Membangun kemampuan berpikir geometris-visual tentang spacetime lewat diagram Minkowski — alat yang dipakai di sisa course untuk menyelesaikan skenario relativistik tanpa aljabar berat, sekaligus membedah paradoks kembar sampai tuntas.

Lessons:

* [ ] 4.1 Diagram spacetime: sumbu, world line, light cone, invariant interval
* [ ] 4.2 Region-region spacetime (timelike, spacelike, lightlike) dan implikasinya untuk kausalitas
* [ ] 4.3 Paradoks kembar: kenapa bukan paradoks — analisis lewat diagram spacetime & proper time
* [ ] 4.4 Efek Doppler relativistik dan aberasi bintang (stellar aberration) sebagai aplikasi diagram spacetime

Mini Project (Derivation & Simulation Lab): Derivasi tangan resolusi paradoks kembar (asimetri lewat integral proper time sepanjang worldline masing-masing kembar, termasuk fase percepatan); kode plotter diagram Minkowski yang menggambar worldline, light cone, dan menghitung proper time sepanjang lintasan yang dipilih.

Status: Not started

---

## Module 5 — Momentum-Energi Relativistik & Penutup Relativitas Khusus

Description: Modul penutup fase Relativitas Khusus — 4-momentum, E=mc², dan elektrodinamika relativistik secukupnya sebagai jembatan motivasi ke Relativitas Umum, ditutup checkpoint review sebelum masuk math bridge.

Lessons:

* [ ] 5.1 4-momentum, energi relativistik, dan derivasi E=mc²
* [ ] 5.2 Tumbukan & peluruhan relativistik — kekekalan energi-momentum 4-vector
* [ ] 5.3 Elektrodinamika relativistik secukupnya: bagaimana medan listrik-magnet bertransformasi antar frame
* [ ] 5.4 Review & sintesis Relativitas Khusus — checkpoint konseptual sebelum masuk notasi tensor penuh (per Domain-Specific Standards: SR tuntas dulu sebagai fondasi)

Mini Project (Derivation & Simulation Lab): Derivasi tangan relasi energi-momentum relativistik E²=(pc)²+(mc²)² dan terapkan ke satu soal tumbukan/peluruhan partikel; kode kalkulator tumbukan relativistik yang memverifikasi numerik kekekalan 4-momentum sebelum/sesudah tumbukan.

Status: Not started

---

## Module 6 — Math Bridge I: Kalkulus Tensor

Description: Modul pertama dari dua modul "jembatan matematika" — membangun notasi indeks dan tensor sebagai alat (bukan matematika murni abstrak), dimulai dari spacetime datar Minkowski yang sudah dikenal dari Relativitas Khusus.

Lessons:

* [ ] 6.1 Notasi indeks, indeks kontravarian/kovarian, Einstein summation convention
* [ ] 6.2 Vektor & tensor di spacetime Minkowski, metrik sebagai objek fundamental
* [ ] 6.3 Aturan transformasi tensor dan kenapa hukum fisika harus ditulis sebagai persamaan tensor (covariance)
* [ ] 6.4 Signature metrik & sign convention ditinjau ulang secara eksplisit untuk notasi tensor (per house rule domain)

Mini Project (Derivation & Simulation Lab): Derivasi tangan bagaimana tensor metrik bertransformasi di bawah transformasi Lorentz, dan tulis ulang invariant interval Module 4 dalam notasi tensor; kode utility index-juggling kecil (raise/lower index, kontraksi) untuk 4-vector sederhana.

Status: Not started

---

## Module 7 — Math Bridge II: Geometri Diferensial (Manifold & Kurvatur)

Description: Modul kedua math bridge — dari spacetime datar ke bahasa yang dibutuhkan untuk spacetime melengkung: manifold, Christoffel symbols, parallel transport, dan tensor kurvatur Riemann. Ini fondasi matematis langsung sebelum masuk Relativitas Umum.

Lessons:

* [ ] 7.1 Manifold, koordinat kurvilinear, dan kenapa spacetime melengkung butuh bahasa baru
* [ ] 7.2 Christoffel symbols & turunan kovarian
* [ ] 7.3 Parallel transport & persamaan geodesic
* [ ] 7.4 Tensor kurvatur Riemann, tensor Ricci, dan skalar kurvatur — pengantar

Mini Project (Derivation & Simulation Lab): Derivasi tangan Christoffel symbols untuk metrik 2D sederhana yang melengkung (mis. permukaan bola); kode geodesic solver yang mengintegrasikan persamaan geodesic secara numerik pada metrik tersebut dan memvisualisasikan lintasannya.

Status: Not started

---

## Module 8 — Prinsip Ekivalensi & Fondasi Relativitas Umum

Description: Modul pembuka fase Relativitas Umum — dari prinsip ekivalensi (gravitasi tidak bisa dibedakan dari percepatan), dibangun intuisi kenapa massa-energi melengkungkan spacetime, sebelum masuk ke persamaan medan penuh di Module 9.

Lessons:

* [ ] 8.1 Prinsip ekivalensi lemah & kuat — gravitasi vs percepatan
* [ ] 8.2 Gravitational redshift diturunkan langsung dari prinsip ekivalensi (tanpa persamaan medan penuh)
* [ ] 8.3 Dari prinsip ekivalensi ke gagasan spacetime melengkung oleh massa-energi
* [ ] 8.4 Stress-energy tensor sebagai sumber kelengkungan — pengantar konseptual

Mini Project (Derivation & Simulation Lab): Derivasi tangan gravitational redshift dari thought experiment lift/elevator Einstein; kode kalkulator yang membandingkan dilatasi waktu kinematik (SR) vs gravitasional (prinsip ekivalensi) untuk skenario satelit — cikal bakal studi kasus GPS di Module 14.

Status: Not started

---

## Module 9 — Persamaan Medan Einstein

Description: Puncak matematis course ini — menyusun tensor Einstein dan menuliskan persamaan medan Einstein, memverifikasi limit Newtoniannya (persamaan Poisson), sesuai urutan MIT 8.962 (tensor → prinsip ekivalensi → kurvatur → field equation).

Lessons:

* [ ] 9.1 Menyusun tensor Einstein dari tensor Ricci & skalar kurvatur
* [ ] 9.2 Persamaan medan Einstein: bentuk penuh, makna fisis tiap suku
* [ ] 9.3 Limit Newtonian persamaan medan Einstein — harus kembali ke persamaan Poisson
* [ ] 9.4 Vacuum solutions vs solusi dengan sumber materi — pengantar sebelum Schwarzschild

Mini Project (Derivation & Simulation Lab): Derivasi tangan limit Newtonian persamaan medan Einstein (tunjukkan tereduksi ke persamaan Poisson untuk gravitasi lemah); kode pengecekan numerik/simbolik (memakai library CAS) yang memverifikasi komponen suatu metrik sederhana memenuhi persamaan medan vakum.

Status: Not started

---

## Module 10 — Metrik Schwarzschild & Presesi Perihelion Merkurius

Description: Solusi eksak pertama dan paling penting dari persamaan medan Einstein — diturunkan lalu dipakai untuk menjelaskan anomali presesi perihelion Merkurius yang sudah lama diketahui tapi tak terjelaskan Newton.

Lessons:

* [ ] 10.1 Menurunkan metrik Schwarzschild (solusi vakum simetri bola)
* [ ] 10.2 Orbit partikel di sekitar massa Schwarzschild — geodesic radial & orbital
* [ ] 10.3 Presesi perihelion Merkurius: derivasi koreksi relativistik terhadap orbit Newtonian
* [ ] 10.4 Membandingkan hasil derivasi ke data pengamatan riil presesi Merkurius

Mini Project (Derivation & Simulation Lab): Derivasi tangan formula presesi perihelion dari persamaan geodesic Schwarzschild; kode integrator orbit numerik yang membandingkan orbit Newtonian murni vs orbit terkoreksi Schwarzschild, memvisualisasikan presesinya.

Status: Not started

---

## Module 11 — Pembelokan Cahaya & Lubang Hitam

Description: Dua konsekuensi Schwarzschild paling ikonik — pembelokan cahaya gravitasional (bukti pertama GR lewat ekspedisi Eddington 1919) dan struktur dasar lubang hitam.

Lessons:

* [ ] 11.1 Lintasan cahaya (null geodesic) di metrik Schwarzschild & derivasi sudut pembelokan
* [ ] 11.2 Ekspedisi Eddington 1919 — bagaimana prediksi ini diverifikasi secara historis
* [ ] 11.3 Event horizon, singularitas, dan koordinat Schwarzschild vs koordinat lain (Eddington-Finkelstein secukupnya)
* [ ] 11.4 Pengantar Penrose diagram secukupnya untuk memvisualisasikan struktur kausal lubang hitam

Mini Project (Derivation & Simulation Lab): Derivasi tangan sudut pembelokan cahaya di dekat massa masif dari null geodesic Schwarzschild; kode ray-tracer null-geodesic sederhana yang memvisualisasikan pembelokan cahaya, dibandingkan ke nilai hasil ekspedisi Eddington 1919.

Status: Not started

---

## Module 12 — Gelombang Gravitasi

Description: Dari persamaan medan dalam limit lemah (linearized gravity) sampai dasar matematis di balik deteksi LIGO/Virgo — ditutup studi kasus paper deteksi pertama gelombang gravitasi.

Lessons:

* [ ] 12.1 Linearized gravity: persamaan medan dalam limit medan lemah
* [ ] 12.2 Persamaan gelombang gravitasi & solusi radiasi dari sumber dinamis
* [ ] 12.3 Strain gelombang gravitasi & prinsip dasar bagaimana LIGO/Virgo mendeteksinya
* [ ] 12.4 Studi kasus: paper deteksi pertama Abbott et al. 2016 (GW150914)

Mini Project (Derivation & Simulation Lab): Derivasi tangan persamaan gelombang linear dari persamaan medan Einstein dalam limit lemah; kode estimator waveform strain sederhana untuk inspiral sistem biner, dibandingkan kualitatif ke bentuk sinyal GW150914.

Status: Not started

---

## Module 13 — Pengantar Kosmologi Relativistik

Description: Modul terakhir materi GR inti — metrik FLRW dan persamaan Friedmann sebagai pengantar kosmologi relativistik, ditutup dengan penegasan eksplisit batas scope course (quantum gravity, radiasi Hawking disebut tapi tidak didalami).

Lessons:

* [ ] 13.1 Metrik FLRW (Friedmann-Lemaître-Robertson-Walker) & asumsi homogenitas-isotropi alam semesta
* [ ] 13.2 Menurunkan persamaan Friedmann dari persamaan medan Einstein
* [ ] 13.3 Ekspansi alam semesta, hukum Hubble, dan skenario kosmologis (flat/open/closed)
* [ ] 13.4 Ke mana arah riset lanjut (quantum gravity, radiasi Hawking, string theory) — disebut sebagai batas scope, tidak didalami (per Domain Scope)

Mini Project (Derivation & Simulation Lab): Derivasi tangan persamaan Friedmann dari persamaan medan Einstein diterapkan ke metrik FLRW; kode integrator numerik kecil yang memplot faktor skala a(t) untuk beberapa pilihan parameter kosmologis (model toy matter-dominated vs dark-energy-dominated).

Status: Not started

---

## Module 14 — Konteks Eksperimental & Aplikasi Nyata: GPS, LIGO, dan Bukti Relativitas

Description: Modul integrasi terapan — menghubungkan seluruh teori Module 1-13 ke bukti eksperimental nyata dan aplikasi produksi, ditinjau dari keempat Practitioner Reference Frame domain ini. Berisi Intermediate Project domain sesuai `domains/relativity/DOMAIN.md` bagian "Project Tiers".

Lessons:

* [ ] 14.1 Kenapa GPS meleset tanpa koreksi relativistik — dilatasi waktu kinematik + gravitasional dihitung angka riil
* [ ] 14.2 LIGO/Virgo sebagai studi kasus insinyur eksperimental — mengisolasi & mengukur efek relativistik super kecil
* [ ] 14.3 Ekspedisi Eddington 1919 & bukti pembelokan cahaya — ditinjau ulang dari lensa metodologi eksperimental
* [ ] 14.4 Membandingkan empat Practitioner Reference Frame domain ini (teoretis, eksperimental, insinyur terapan, komunikator sains) pada satu kasus nyata yang sama

Mini Project (Intermediate Project — per Domain "Project Tiers"): "Kenapa GPS Meleset Tanpa Koreksi Relativistik" — derivasi lengkap dilatasi waktu kinematik + gravitasional untuk satelit GPS, dihitung angka riilnya (mikrodetik/hari), dan disimulasikan dampak akumulatifnya kalau tidak dikoreksi.

Status: Not started

---

## Module 15 — Capstone: Relativity Portfolio

Description: Mengintegrasikan seluruh fondasi Module 1-14 ke satu Capstone Project expert-level, mengikuti `domains/relativity/DOMAIN.md` bagian "Project Tiers" untuk definisi Capstone di domain ini.

Lessons:

* [ ] 15.1 Memilih satu topik expert-level (presesi perihelion Merkurius, pembelokan cahaya gravitasional, atau estimasi strain gelombang gravitasi sistem biner sederhana)
* [ ] 15.2 Derivasi penuh dari persamaan medan Einstein/geodesic sampai hasil akhir topik pilihan
* [ ] 15.3 Simulasi/visualisasi numerik yang menunjukkan hasil secara kuantitatif, dibandingkan ke nilai referensi yang diketahui
* [ ] 15.4 Self-review memakai empat lensa Review Style domain (Derivational Rigor, Dimensional & Limit Consistency, Conceptual vs Rote, Simulation Fidelity) di atas Universal Review Rubric

Capstone Project: Relativity Portfolio — pilih satu topik expert-level (presesi perihelion Merkurius dari metrik Schwarzschild, pembelokan cahaya gravitasional, atau estimasi strain gelombang gravitasi ala LIGO untuk sistem biner sederhana), kerjakan derivasi penuh dari persamaan medan Einstein/geodesic sampai hasil akhir, plus simulasi/visualisasi yang menunjukkan hasilnya secara numerik — disimpan ke `portofolio/`.

Status: Not started

---

# Capstone Project

Description: Lihat Module 15 di atas untuk rincian lengkap. Capstone course ini SATU dengan Capstone Project Module 15 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/relativity/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi keempat lensa domain (Derivational Rigor, Dimensional & Limit Consistency, Conceptual vs Rote, Simulation Fidelity) plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, derivasi tangan lengkap dari prinsip dasar (bukan rumus dihafal) dengan limit-check Newtonian yang benar, dan simulasi/kode yang benar-benar mengimplementasikan fisika yang diturunkan (bukan angka hardcode) — hasil numerik dibandingkan ke nilai referensi yang diketahui.

Status: Not started

---

# Overall Progress

Module Completion: 0/15

Overall Completion: 0%

Current Module: Module 1 — Prasyarat & Motivasi: Kenapa Mekanika Newton Mulai Retak (menunggu approval roadmap)

Current Lesson: 1.1 Refresh kilat: kerangka acuan inersial, transformasi Galileo, dan invariansi hukum Newton

Next Lesson: 1.1 Refresh kilat: kerangka acuan inersial, transformasi Galileo, dan invariansi hukum Newton
