# Course Roadmap

## Course Information

* Course Name: Black Hole — dari 0 sampai Expert
* Category: Fisika Lubang Hitam (Domain: black-hole — lihat `domains/black-hole/DOMAIN.md`)
* Difficulty: Level 0 (paham black hole secara pop-sci, belum pernah menurunkan metrik Schwarzschild/Kerr sendiri atau menghitung suhu Hawking) → Level 4 (mampu menurunkan struktur kausal Schwarzschild & Kerr dari nol, menjelaskan derivasi semiklasik radiasi Hawking, menganalisis information paradox dengan status epistemis jujur, dan menghubungkan seluruh teori ke bukti observasional nyata EHT/LIGO secara kuantitatif)
* Estimated Duration: 14 modul (13 modul konten + 1 modul capstone), rata-rata 4 lesson/modul (~52 lesson), pace disesuaikan karena Applied Practice domain ini SELALU berujung derivasi tangan tervalidasi, skrip/kode simulasi, atau studi kasus berbasis sumber primer — bukan sekadar baca teori
* Prerequisites: Kemampuan programming Python dasar-menengah (skrip kecil, comfortable baca dokumentasi NumPy/Matplotlib). Aljabar linear & kalkulus multivariat tingkat awal kuliah. **SANGAT DISARANKAN** sudah menuntaskan atau berjalan paralel dengan `courses/relativity/roadmap.md` Module 1-11 (Special Relativity penuh sampai metrik Schwarzschild/pembelokan cahaya/pengantar Penrose diagram) — kalau belum, Module 2 course ini menyediakan bridge ringkas yang cukup untuk lanjut, tapi eksplisit BUKAN pengganti derivasi penuh course relativity (lihat Catatan Scope). TIDAK butuh quantum mechanics/QFT sebelumnya — secukupnya untuk radiasi Hawking dibangun dari nol di Module 8.

---

# Learning Objectives

* [ ] Menjelaskan sejarah ide "dark star" (Michell 1783, Laplace 1796) sebagai antisipasi black hole sebelum relativitas, dan menurunkan escape velocity Newtonian sebagai motivasi konseptual
* [ ] Memakai hasil SR/GR secukupnya (prinsip ekivalensi, metrik, geodesic, metrik Schwarzschild dasar) sebagai fondasi, TANPA mengulang derivasi penuh yang sudah dimiliki domain `relativity`
* [ ] Menurunkan struktur kausal Schwarzschild secara PENUH: koordinat Eddington-Finkelstein, maximal extension Kruskal-Szekeres, Penrose diagram lengkap, dan Einstein-Rosen bridge (non-traversable)
* [ ] Menghitung photon sphere, ISCO, dan gravitational redshift ekstrem di sekitar black hole non-rotasi dari geodesic Schwarzschild
* [ ] Menurunkan metrik Kerr dan menjelaskan ergosphere, frame-dragging (Lense-Thirring), dan Penrose process sebagai mekanisme ekstraksi energi rotasi
* [ ] Menguasai empat hukum black hole mechanics (Bardeen-Carter-Hawking 1973) dan area theorem, serta analogi formalnya ke termodinamika klasik
* [ ] Memahami fondasi QFT di curved spacetime secukupnya (particle creation, Unruh effect) untuk mengikuti derivasi radiasi Hawking
* [ ] Menurunkan (secara semiklasik) radiasi Hawking (Hawking 1974/1975), menghitung suhu Hawking dan estimasi waktu evaporasi untuk black hole stellar-mass vs primordial
* [ ] Menjelaskan information paradox secara teknis-jujur: unitaritas QM vs radiasi termal, no-hair theorem, firewall paradox (AMPS 2013), dan status riset terbuka per 2026 — tanpa overclaim satu posisi sebagai jawaban final
* [ ] Menjelaskan astrofisika akresi black hole nyata: accretion disk, Eddington luminosity, relativistic jets, quasar/AGN, dan X-ray binary sebagai cara black hole stellar-mass terdeteksi
* [ ] Menganalisis bukti observasional nyata secara kuantitatif: Event Horizon Telescope (M87*, Sgr A*) dan ringdown/no-hair theorem test dari merger black hole biner LIGO/Virgo
* [ ] Menjelaskan black hole cosmology: primordial black hole, status kandidat dark matter, dan status epistemis white hole (spekulatif, bukan solusi fisis teramati)
* [ ] Menghasilkan Capstone Project (Black Hole Geometry & Kerr Notebook / Hawking Radiation & Information Paradox Research Paper / Observational Black Hole Report) dengan derivasi, validasi data nyata, dan status epistemis eksplisit

---

# Course Modules

## Module 1 — Prasyarat: Gravitasi Newton, Escape Velocity, dan Sejarah "Dark Star"

Description: Sebelum masuk relativitas, modul ini membangun intuisi Newtonian tentang objek yang begitu masif/padat sehingga cahaya tidak bisa lolos — ide yang secara historis muncul JAUH sebelum Einstein lewat John Michell (1783) dan Pierre-Simon Laplace (1796). Mengikuti Thorne *Black Holes and Time Warps* Bab 3 untuk narasi historis.

Lessons:

* [ ] 1.1 Escape velocity Newtonian — derivasi dari kekekalan energi, dan kenapa ia tidak bergantung arah lontar
* [ ] 1.2 John Michell (1783) & Pierre-Simon Laplace (1796): "dark star" — benda yang escape velocity-nya melebihi kecepatan cahaya, dihitung murni Newtonian (tanpa GR)
* [ ] 1.3 Kenapa argumen Newtonian "dark star" secara konsep benar tapi secara fisis tidak lengkap — cahaya sebagai partikel bermassa (asumsi Newtonian) vs cahaya sebagai fenomena relativistik
* [ ] 1.4 Python: kalkulator escape velocity untuk berbagai massa & radius, mencari radius "dark star" Newtonian untuk massa Matahari — dibandingkan ke radius Schwarzschild yang benar (preview ke Module 3)

Mini Project: Skrip Python yang menghitung escape velocity untuk beberapa benda astronomi nyata (Bumi, Matahari, neutron star tipikal) dan mencari radius kritis "dark star" Newtonian untuk massa Matahari, dibandingkan numerik terhadap radius Schwarzschild sesungguhnya (2GM/c²) sebagai jembatan ke Module 3.

Status: Not started

---

## Module 2 — Bridge Cepat: Special & General Relativity Secukupnya

Description: Modul bridge, BUKAN pengulangan — merangkas HANYA hasil SR/GR yang dipakai langsung di sisa course (prinsip ekivalensi, metrik, geodesic, notasi tensor dasar, derivasi ringkas metrik Schwarzschild). Derivasi penuh eksplisit di luar scope, dirujuk ke `courses/relativity/roadmap.md` Module 1-10. Mengikuti Carroll *Spacetime and Geometry* Bab 1-5 (ringkasan).

Lessons:

* [ ] 2.1 Prinsip ekivalensi & spacetime melengkung — ringkasan konseptual (bukan derivasi penuh, lihat `domains/relativity/DOMAIN.md`)
* [ ] 2.2 Notasi tensor & metrik secukupnya (index notation, Einstein summation, signature metrik) untuk dibaca di Module 3-5
* [ ] 2.3 Geodesic & persamaan medan Einstein — hasil (BUKAN derivasi) yang dipakai sebagai titik berangkat solusi Schwarzschild/Kerr
* [ ] 2.4 Rekap kilat metrik Schwarzschild sebagai solusi vakum simetri bola — hasil akhir dikutip dari `courses/relativity/roadmap.md` Module 10, course ini melanjutkan PERSIS dari titik ini

Mini Project (Derivation & Simulation Lab): Ringkasan tertulis (1 halaman) peta ketergantungan "apa yang dipakai dari relativity, apa yang dibangun baru di course ini" — plus verifikasi numerik/simbolik (SymPy) bahwa metrik Schwarzschild yang dikutip memenuhi persamaan medan vakum (reuse pola verifikasi dari `courses/relativity/roadmap.md` Module 9 kalau sudah dikerjakan).

Status: Not started

---

## Module 3 — Struktur Kausal Schwarzschild Secara Penuh: Horizon, Singularitas, dan Penrose Diagram

Description: Titik masuk inti course — di mana `courses/relativity/roadmap.md` Module 11 berhenti di "pengantar Penrose diagram secukupnya", modul ini melanjutkan JAUH lebih dalam: koordinat Eddington-Finkelstein, maximal extension Kruskal-Szekeres, Penrose diagram penuh, dan Einstein-Rosen bridge. Mengikuti Carroll Bab 5-7 dan MIT OCW 8.962.

Lessons:

* [ ] 3.1 Kenapa koordinat Schwarzschild "rusak" di r=2GM/c² — singularitas koordinat (removable) vs singularitas fisis sejati di r=0, dibedakan lewat skalar kurvatur (Kretschmann scalar)
* [ ] 3.2 Koordinat Eddington-Finkelstein (ingoing/outgoing) — menghilangkan singularitas koordinat di horizon, menunjukkan horizon sebagai permukaan kausal satu-arah
* [ ] 3.3 Maximal extension Kruskal-Szekeres — spacetime Schwarzschild "penuh" mencakup empat region (black hole, white hole, dua alam semesta paralel)
* [ ] 3.4 Penrose diagram Schwarzschild lengkap & Einstein-Rosen bridge — kenapa "jembatan" ke alam semesta paralel ini BUKAN traversable wormhole (house rule domain, dinyatakan eksplisit)

Mini Project (Derivation & Simulation Lab): Derivasi tangan transformasi koordinat Schwarzschild → Eddington-Finkelstein → Kruskal-Szekeres, tunjukkan eksplisit bagaimana singularitas koordinat hilang; kode plotter Penrose diagram Schwarzschild (empat region berlabel) plus kalkulator Kretschmann scalar yang membuktikan r=0 adalah singularitas fisis sejati sementara r=2GM/c² bukan.

Status: Not started

---

## Module 4 — Fenomenologi Kuantitatif di Sekitar Black Hole Non-Rotasi

Description: Dari geometri abstrak ke angka yang bisa dihitung — photon sphere, ISCO, dan gravitational redshift ekstrem, semuanya diturunkan dari geodesic Schwarzschild. Mengikuti Carroll Bab 5 dan MIT OCW 8.962.

Lessons:

* [ ] 4.1 Geodesic partikel masif & foton di metrik Schwarzschild — persamaan orbit efektif (effective potential)
* [ ] 4.2 Photon sphere (r=1.5 r_s) — derivasi kondisi orbit foton sirkular tidak stabil, kaitannya langsung ke "bayangan" black hole yang difoto EHT (preview eksplisit ke Module 12)
* [ ] 4.3 ISCO (innermost stable circular orbit, r=3 r_s=6GM/c²) — derivasi dari effective potential, kenapa ini batas dalam accretion disk stabil (preview eksplisit ke Module 11)
* [ ] 4.4 Gravitational redshift ekstrem mendekati horizon — limit z→∞ saat r→r_s, dan makna fisisnya untuk observer jauh

Mini Project (Derivation & Simulation Lab): Derivasi tangan lokasi photon sphere & ISCO dari effective potential geodesic Schwarzschild; kode kalkulator yang menghitung photon sphere, ISCO, dan redshift untuk berbagai jarak radial, divalidasi terhadap nilai r=1.5 r_s dan r=3 r_s yang diketahui.

Status: Not started

---

## Module 5 — Kerr Black Hole: Rotasi, Ergosphere, dan Frame-Dragging

Description: Black hole nyata di alam semesta HAMPIR SELALU berotasi — modul ini membangun metrik Kerr (R.P. Kerr, 1963) dan konsekuensinya yang tidak punya analog di Schwarzschild: ergosphere dan frame-dragging. Topik ini TIDAK dibahas sama sekali di `courses/relativity/roadmap.md`. Mengikuti Carroll Bab 6 dan paper asli Kerr (1963).

Lessons:

* [ ] 5.1 Metrik Kerr dalam koordinat Boyer-Lindquist — struktur & parameter (massa M, spin angular momentum a), limit-check kembali ke Schwarzschild saat a→0
* [ ] 5.2 Ergosphere — region di luar event horizon di mana tidak ada observer yang bisa diam relatif terhadap infinity, akibat frame-dragging ekstrem
* [ ] 5.3 Frame-dragging (efek Lense-Thirring) — spacetime "diseret" berputar oleh massa berotasi, dari efek lemah di sekitar Bumi (gyroscope precession, dikonfirmasi Gravity Probe B) sampai ekstrem di ergosphere Kerr
* [ ] 5.4 Horizon ganda Kerr (outer & inner horizon) dan batas ekstremalitas (a=GM/c — kapan black hole "berputar semaksimal mungkin")

Mini Project (Derivation & Simulation Lab): Derivasi tangan lokasi ergosphere & event horizon Kerr dari metrik Boyer-Lindquist, tunjukkan limit a→0 kembali ke Schwarzschild; kode plotter penampang ergosphere & horizon Kerr untuk beberapa nilai spin a, memvisualisasikan bagaimana ergosphere "menggembung" seiring spin bertambah.

Status: Not started

---

## Module 6 — Penrose Process dan Empat Hukum Black Hole Mechanics

Description: Dua hasil besar yang lahir dari geometri Kerr & Schwarzschild — mekanisme ekstraksi energi rotasi (Penrose process, 1969) dan empat hukum black hole mechanics (Bardeen-Carter-Hawking, 1973) yang secara formal mirip hukum termodinamika, menjadi jembatan langsung ke Module 7. Mengikuti Wald *General Relativity* Bab 12 dan paper primer Bardeen-Carter-Hawking (1973).

Lessons:

* [ ] 6.1 Penrose process: bagaimana partikel yang masuk ergosphere dan terbelah bisa mengekstrak energi rotasi black hole, derivasi syarat energi negatif di ergosphere
* [ ] 6.2 Area theorem (Hawking 1971): luas horizon black hole tidak pernah berkurang secara klasik — derivasi konseptual & implikasinya
* [ ] 6.3 Empat hukum black hole mechanics (Bardeen-Carter-Hawking 1973): hukum ke-0 (surface gravity konstan di horizon), ke-1 (kekekalan energi termodinamika-like), ke-2 (area theorem sebagai analog entropi), ke-3 (unreachability ekstremalitas)
* [ ] 6.4 Analogi formal ke termodinamika klasik — di titik ini MASIH analogi matematis, BELUM diklaim fisis (jembatan eksplisit ke Module 7 di mana Hawking/Bekenstein membuatnya jadi fisis sungguhan)

Mini Project (Derivation & Simulation Lab): Derivasi tangan syarat energi negatif Penrose process dan batas atas energi yang bisa diekstrak (~29% massa-energi untuk black hole ekstremal); kode kalkulator Penrose process yang menghitung energi maksimum terekstraksi untuk berbagai nilai spin a, divalidasi terhadap batas 29% yang diketahui.

Status: Not started

---

## Module 7 — Termodinamika Black Hole: Entropi Bekenstein-Hawking dan Suhu

Description: Titik balik konseptual course — analogi formal Module 6 ternyata FISIS SUNGGUHAN: black hole punya entropi & suhu nyata. Mengikuti paper primer Bekenstein (1973) dan Wald Bab 12.

Lessons:

* [ ] 7.1 Argumen Bekenstein (1973): kenapa black hole HARUS punya entropi (mencegah pelanggaran hukum kedua termodinamika saat materi jatuh ke black hole)
* [ ] 7.2 Entropi Bekenstein-Hawking: S = (k_B c³ A)/(4Għ) — luas horizon sebagai ukuran entropi, implikasi radikal "holografik" (entropi sebanding luas, bukan volume) — disebut sebagai preview arah riset lanjut, BUKAN didalami (holographic principle penuh di luar scope)
* [ ] 7.3 Surface gravity κ sebagai analog suhu — hukum ke-0 black hole mechanics (Module 6.3) sekarang punya makna fisis: black hole punya suhu seragam di horizon
* [ ] 7.4 Kenapa "black hole punya suhu" tetap paradoks SELAMA belum ada mekanisme kuantum radiasi — motivasi eksplisit menuju Module 8-9

Mini Project (Derivation & Simulation Lab): Derivasi tangan argumen Bekenstein untuk generalized second law; kode kalkulator entropi Bekenstein-Hawking untuk black hole dengan berbagai massa (stellar-mass sampai supermassive Sgr A*/M87*), dibandingkan terhadap entropi termodinamika biasa skala serupa untuk menunjukkan betapa besarnya angka ini.

Status: Not started

---

## Module 8 — Bridge QFT di Curved Spacetime: Particle Creation dan Unruh Effect

Description: Modul bridge kedua course ini — membangun HANYA konsep QFT-in-curved-spacetime yang dibutuhkan untuk memahami radiasi Hawking (Module 9), BUKAN QFT penuh (tidak ada domain lain di repo ini yang mengajarkan ini, mirip pola `domains/antimatter/DOMAIN.md` membangun QM secukupnya untuk Dirac equation). Mengikuti Wald Bab 14 dan Susskind & Lindesay Bab 1-2.

Lessons:

* [ ] 8.1 Kenapa konsep "partikel" bergantung observer di spacetime melengkung/frame yang dipercepat — motivasi intuitif sebelum formalisme
* [ ] 8.2 Unruh effect: observer yang dipercepat konstan di ruang hampa Minkowski "melihat" radiasi termal — derivasi konseptual (bukan QFT formal penuh) dan kaitannya langsung ke radiasi Hawking
* [ ] 8.3 Particle creation di spacetime melengkung secukupnya — vacuum state berbeda antar region spacetime (mis. sebelum vs sesudah horizon terbentuk)
* [ ] 8.4 Jembatan eksplisit dari Unruh effect (accelerated observer di flat space) ke radiasi Hawking (observer diam jauh dari black hole) — analogi formal yang akan dipakai langsung di Module 9

Mini Project (Derivation & Simulation Lab): Tulisan terstruktur (1-2 halaman) yang menjelaskan dengan bahasa sendiri kenapa Unruh effect terjadi dan bagaimana argumennya di-generalize ke radiasi Hawking — dicek terhadap penjelasan Susskind & Lindesay/Wald; kode kalkulator suhu Unruh (T = ħa/2πck_B) untuk berbagai percepatan, sebagai pemanasan sebelum kalkulator suhu Hawking Module 9.

Status: Not started

---

## Module 9 — Radiasi Hawking: Derivasi Semiklasik dan Evaporasi Black Hole

Description: Hasil paling terkenal (dan paling disalahpahami) dari perpotongan GR dan QM — derivasi semiklasik Hawking (1974/1975) bahwa black hole memancarkan radiasi termal dan akhirnya menguap. Mengikuti paper primer Hawking (1975) dan Susskind & Lindesay Bab 3-4.

Lessons:

* [ ] 9.1 Argumen semiklasik Hawking: pasangan partikel-antipartikel virtual di dekat horizon, satu jatuh ke black hole, satu lolos sebagai radiasi nyata — penjelasan konseptual DAN batasannya (ini gambaran populer yang disederhanakan, Hawking asli memakai Bogoliubov transformation)
* [ ] 9.2 Suhu Hawking: T_H = ħc³/(8πGMk_B) — derivasi dari surface gravity (Module 7.3) via argumen Unruh (Module 8.4), dan kenapa suhu BERBANDING TERBALIK dengan massa (black hole kecil lebih panas)
* [ ] 9.3 Black hole sebagai objek termodinamika sungguhan: radiasi Hawking menutup lingkaran Module 6-7, empat hukum black hole mechanics sekarang punya makna fisis PENUH (bukan cuma analogi)
* [ ] 9.4 Evaporasi black hole & skala waktu — derivasi estimasi waktu evaporasi (∝ M³), kenapa black hole stellar-mass/supermassive butuh waktu jauh lebih lama dari umur alam semesta untuk menguap signifikan, sementara black hole primordial kecil bisa relevan hari ini (jembatan ke Module 13)

Mini Project (Derivation & Simulation Lab): Derivasi tangan suhu Hawking dari surface gravity via argumen Unruh; kode kalkulator suhu Hawking & estimasi waktu evaporasi untuk berbagai massa black hole (dari massa Planck sampai supermassive), divalidasi terhadap nilai referensi (mis. suhu Hawking black hole massa Matahari ≈ 60 nanokelvin) dari textbook/paper.

Status: Not started

---

## Module 10 — Information Paradox: Unitaritas, No-Hair Theorem, dan Firewall

Description: Konsekuensi paling mengganggu radiasi Hawking — kalau radiasi benar-benar termal murni, informasi yang jatuh ke black hole tampak hilang selamanya, melanggar unitaritas mekanika kuantum. Modul ini WAJIB menyatakan status riset terbuka secara eksplisit, mengikuti Susskind & Lindesay dan paper primer AMPS (2013).

Lessons:

* [ ] 10.1 No-hair theorem: black hole klasik sepenuhnya dikarakterisasi hanya oleh massa, spin, dan muatan (M, J, Q) — implikasinya untuk "informasi" yang jatuh ke dalam
* [ ] 10.2 Information paradox: kalau radiasi Hawking benar-benar termal murni (Module 9.1), informasi awal tampak hilang saat black hole menguap habis — kenapa ini bertentangan langsung dengan unitaritas QM
* [ ] 10.3 Black hole complementarity (Susskind) — proposal bahwa tidak ada kontradiksi selama tidak ada observer tunggal yang bisa melihat kedua sisi (informasi di dalam DAN radiasi keluar) sekaligus
* [ ] 10.4 Firewall paradox (AMPS 2013): argumen bahwa complementarity sendiri punya masalah konsistensi, memunculkan proposal "firewall" di horizon — status riset per 2026 (belum ada konsensus tunggal; disebut singkat: soft hair, island formula/holografi sebagai arah lain, TANPA didalami — di luar scope)

Mini Project (menuju Capstone jalur B): Tulisan analisis (2-3 halaman) yang memetakan posisi-posisi utama dalam information paradox (thermal murni/informasi hilang vs unitaritas/informasi tersimpan, complementarity, firewall) dengan status epistemis eksplisit untuk tiap posisi (solid/riset terbuka/spekulatif) — dicek terhadap Susskind & Lindesay dan paper AMPS (2013), TIDAK menyimpulkan satu posisi sebagai "jawaban benar".

Status: Not started

---

## Module 11 — Accretion Disk dan Astrofisika Black Hole Nyata

Description: Dari fisika teoretis ke black hole yang benar-benar ada di langit — bagaimana black hole terdeteksi lewat materi yang jatuh ke dalamnya (accretion), bukan lewat black hole itu sendiri (yang memang tidak memancarkan cahaya). Mengikuti sumber NASA/Chandra X-ray Observatory dan textbook astrofisika standar.

Lessons:

* [ ] 11.1 Accretion disk: materi yang jatuh ke black hole kehilangan momentum sudut lewat viskositas, memanas ekstrem, memancarkan radiasi (seringnya X-ray) — batas dalam disk di ISCO (koneksi langsung ke Module 4.3)
* [ ] 11.2 Eddington luminosity: batas atas teoretis luminositas dari tekanan radiasi vs gravitasi, dan kenapa ini membatasi laju pertumbuhan black hole
* [ ] 11.3 Relativistic jets & quasar/AGN (Active Galactic Nuclei) — bagaimana black hole supermassive di pusat galaksi bisa memproduksi jet partikel berkecepatan mendekati c
* [ ] 11.4 X-ray binary: pasangan bintang biasa + black hole stellar-mass, cara black hole PERTAMA kali diidentifikasi secara tidak langsung lewat pengukuran massa companion (studi kasus Cygnus X-1)

Mini Project: Studi kasus tertulis (1-2 halaman) yang menjelaskan rantai bukti tidak langsung identifikasi black hole di sistem biner X-ray (Cygnus X-1: kurva kecepatan radial companion → estimasi massa → terlalu masif untuk neutron star → kandidat black hole), berbasis sumber NASA/Chandra & literatur astrofisika standar.

Status: Not started

---

## Module 12 — Bukti Observasional: Event Horizon Telescope dan LIGO/Virgo

Description: Puncak empiris course ini — dua terobosan observasional independen (2019 & 2016) yang mengonfirmasi prediksi teoretis Module 1-9 secara langsung. Sumber primer: EHT Collaboration (2019, 2022) dan LIGO Scientific Collaboration (Abbott et al. 2016).

Lessons:

* [ ] 12.1 Event Horizon Telescope: prinsip Very Long Baseline Interferometry (VLBI) menggabungkan teleskop radio seluruh dunia jadi "teleskop virtual" seukuran Bumi
* [ ] 12.2 Hasil M87* (2019) & Sagittarius A* (2022): "bayangan" black hole yang difoto adalah photon sphere (Module 4.2), bukan event horizon itu sendiri — perbedaan ini WAJIB dijelaskan eksplisit (miskonsepsi populer umum)
* [ ] 12.3 LIGO/Virgo ringdown: setelah merger black hole biner, black hole hasil "berdenging" (ringdown) dengan frekuensi karakteristik yang ditentukan HANYA oleh massa & spin final — quasi-normal modes sebagai test langsung no-hair theorem (Module 10.1)
* [ ] 12.4 Sintesis: bagaimana dua metode observasi total berbeda (pencitraan langsung EHT vs gelombang gravitasi LIGO) saling menguatkan prediksi GR untuk black hole secara independen

Mini Project (Intermediate Project — "Black Hole Property Calculator", per `domains/black-hole/DOMAIN.md` Project Tiers): Skrip Python yang menghitung seluruh properti kunci black hole (radius Schwarzschild, photon sphere, ISCO, entropi Bekenstein-Hawking, suhu Hawking) dari input massa & spin, lalu divalidasi terhadap nilai massa/spin aktual Sgr A* dan M87* dari paper primer EHT (2019, 2022).

Status: Not started

---

## Module 13 — Black Hole Cosmology: Primordial Black Hole, Dark Matter, dan White Hole

Description: Modul penutup materi non-capstone — black hole dalam konteks kosmologis: kandidat pembentukan awal alam semesta, status sebagai kandidat dark matter, dan penegasan status epistemis white hole yang sering disalahpahami sebagai fisika solid.

Lessons:

* [ ] 13.1 Primordial black hole: hipotesis pembentukan black hole dari fluktuasi densitas ekstrem di alam semesta sangat awal (bukan dari keruntuhan bintang) — mekanisme & rentang massa yang mungkin
* [ ] 13.2 Primordial black hole sebagai kandidat dark matter — status riset per 2026: constraint observasional (microlensing, radiasi Hawking untuk PBH kecil) yang sudah mengeliminasi sebagian besar rentang massa, TAPI belum sepenuhnya dieliminasi — dinyatakan eksplisit sebagai hipotesis terbuka, bukan solved/rejected
* [ ] 13.3 White hole: solusi matematis valid dari persamaan medan Einstein (region lain Kruskal-Szekeres, Module 3.3) TAPI tidak pernah teramati dan diyakini tidak stabil terhadap gangguan kecil — status epistemis eksplisit: solusi matematis solid, keberadaan fisis SANGAT spekulatif/ditolak mayoritas fisikawan
* [ ] 13.4 Sintesis penutup: peta lengkap "apa yang solid" (event horizon, Kerr, radiasi Hawking sebagai prediksi teoretis, bukti EHT/LIGO) vs "riset terbuka" (information paradox, PBH sebagai dark matter) vs "spekulatif/ditolak" (white hole fisis, wormhole traversable, black hole sebagai portal) — status epistemis eksplisit untuk tiap kategori

Mini Project: Tulisan sintesis (2 halaman) yang mengklasifikasikan sepuluh klaim besar course ini (dipilih sendiri oleh learner dari Module 1-13) ke dalam tiga kategori epistemis (Solid/Riset Terbuka/Spekulatif-Ditolak) dengan justifikasi tertulis tiap klasifikasi, mengikuti pola Review Style "Epistemic Discipline" `domains/black-hole/DOMAIN.md`.

Status: Not started

---

## Module 14 — Capstone: Black Hole Research Portfolio

Description: Mengintegrasikan seluruh Module 1-13 jadi satu capstone project penuh, mengikuti `domains/black-hole/DOMAIN.md` bagian "Project Tiers".

Lessons:

* [ ] 14.1 Menentukan jalur capstone & scope: (A) Black Hole Geometry & Kerr Notebook, (B) Hawking Radiation & Information Paradox Research Paper, atau (C) Observational Black Hole Report
* [ ] 14.2 Riset & pengumpulan sumber primer/institusional sesuai jalur yang dipilih (paper primer Hawking/Bekenstein/Kerr/AMPS, data EHT/LIGO)
* [ ] 14.3 Penyusunan derivasi/kalkulasi/analisis inti sesuai jalur, termasuk validasi kode/data nyata
* [ ] 14.4 Dokumentasi tertulis final: status epistemis eksplisit tiap klaim besar (solid/riset terbuka/spekulatif), submit ke `portofolio/`

Capstone Project: Pilih SATU — **(A) Black Hole Geometry & Kerr Notebook**: derivasi penuh struktur kausal Schwarzschild (Module 3) + Kerr/ergosphere/Penrose process (Module 5-6), dengan visualisasi kode tervalidasi (Penrose diagram, ergosphere plot), didokumentasikan sebagai notebook teknis. **(B) Hawking Radiation & Information Paradox Research Paper**: derivasi semiklasik radiasi Hawking dari nol (Module 8-9) + analisis tertulis mendalam information paradox (Module 10) dengan status epistemis eksplisit tiap posisi/proposal riset. **(C) Observational Black Hole Report**: studi kasus teknis-mendalam EHT M87*/Sgr A* + LIGO ringdown/no-hair test (Module 12) + astrofisika akresi (Module 11), berbasis sumber primer EHT/LIGO Collaboration. Semua jalur wajib validasi terhadap data/nilai referensi paper primer, plus dokumentasi status epistemis eksplisit untuk tiap klaim besar.

Status: Not started

---

# Capstone Project

Description: Lihat Module 14 di atas untuk rincian lengkap tiga jalur capstone (Black Hole Geometry & Kerr Notebook / Hawking Radiation & Information Paradox Research Paper / Observational Black Hole Report). Capstone course ini SATU dengan Capstone Project Module 14 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/black-hole/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` DITAMBAH kelima lensa Review Style domain ini dari `domains/black-hole/DOMAIN.md`: **Derivational Rigor** (tiap langkah derivasi punya justifikasi eksplisit), **Dimensional & Limit Consistency** (satuan konsisten, hasil kembali ke limit yang benar — mis. Kerr→Schwarzschild saat a→0), **Conceptual vs Rote** (bisa menjelaskan MENGAPA, bukan cuma aljabar), **Simulation Fidelity** (kode benar-benar mengimplementasikan fisika yang diturunkan, hasil numerik dibandingkan ke nilai referensi paper primer), dan **Epistemic Discipline** (klaim solid vs riset terbuka vs spekulatif dibedakan secara eksplisit, khususnya untuk information paradox dan klaim wormhole/white hole). Disimpan ke `portofolio/` disertai dokumentasi status epistemis eksplisit.

Status: Not started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: Module 1 — Prasyarat: Gravitasi Newton, Escape Velocity, dan Sejarah "Dark Star" (menunggu approval roadmap)

Current Lesson: 1.1 Escape velocity Newtonian — derivasi dari kekekalan energi, dan kenapa ia tidak bergantung arah lontar

Next Lesson: 1.1 Escape velocity Newtonian — derivasi dari kekekalan energi, dan kenapa ia tidak bergantung arah lontar

---

# Catatan Scope

**Keputusan domain: domain BARU (`black-hole`), sebagai spesialisasi LANGSUNG dari domain `relativity` — bukan duplikasi, bukan perluasan domain existing.** Task ini secara eksplisit meminta pengecekan overlap terhadap tiga domain: `domains/relativity/DOMAIN.md`, `domains/astronomy-and-life-in-the-universe/DOMAIN.md`, dan `domains/string-theory-for-engineers/DOMAIN.md`. Ketiganya dibaca penuh sebelum desain course ini dimulai.

**Overlap dengan `relativity`: dicek eksplisit, TIDAK ada duplikasi — hubungannya prasyarat/kelanjutan, bukan tumpang tindih.** `courses/relativity/roadmap.md` Module 10-11 SUDAH menurunkan metrik Schwarzschild dan membahas event horizon, singularitas, serta "pengantar Penrose diagram secukupnya" — course ini secara sengaja TIDAK mengulang derivasi metrik Schwarzschild dari persamaan medan Einstein (itu tetap murni milik `relativity` Module 9-10, dirujuk sebagai prasyarat di Module 2 course ini). Sebagai gantinya, Module 3 course ini melanjutkan PERSIS dari titik `relativity` Module 11 berhenti dan membangun struktur kausal PENUH (Kruskal-Szekeres, Penrose diagram lengkap, bukan cuma "pengantar secukupnya") — sebuah kedalaman yang secara eksplisit di luar scope `relativity` (course itu langsung lanjut ke gelombang gravitasi & kosmologi FLRW di Module 12-13, tidak pernah kembali memperdalam black hole). Lebih penting lagi: `domains/relativity/DOMAIN.md` bagian Scope secara eksplisit menyatakan "perpotongan relativitas dengan kuantum (mis. radiasi Hawking, quantum gravity, string theory) disebut sebagai 'ke mana arah riset lanjut'... tapi tidak diajarkan mendalam — itu di luar scope dan **butuh domain terpisah di masa depan kalau dibutuhkan**." Course ini adalah pemenuhan eksplisit dari antisipasi itu — kutipan yang sama persis yang menjadi dasar justifikasi `domains/string-theory-for-engineers/DOMAIN.md` register sebagai domain terpisah. Konsekuensi desain konkret: Kerr black hole (Module 5-6), black hole thermodynamics (Module 7), Hawking radiation (Module 8-9), dan information paradox (Module 10) — SELURUHNYA topik yang TIDAK disentuh sama sekali oleh `relativity` — menjadi bobot mayoritas course ini (7 dari 13 modul konten), sementara hanya Module 2-4 (bridge + struktur kausal + fenomenologi non-rotasi) yang bersinggungan wilayah dengan `relativity`, dan bahkan di situ pun didesain sebagai kelanjutan-lebih-dalam, bukan pengulangan.

**Overlap dengan `astronomy-and-life-in-the-universe`: dicek eksplisit, TIDAK ada duplikasi — level kedalaman berbeda drastis.** `courses/astronomy-and-life-in-the-universe/roadmap.md` Module 2.3 menyebut "Neutron star & black hole — konsep dasar (tanpa masuk GR penuh, itu domain `relativity`)" — murni deskriptif-populer, tidak ada satu pun derivasi matematis. Course ini adalah kebalikannya: seluruhnya derivasi teknis-kuantitatif. Satu-satunya area yang berdekatan adalah Module 11 course ini (accretion disk, X-ray binary, quasar/AGN) yang sedikit bersinggungan tema dengan astronomi deskriptif — tapi didesain eksplisit sebagai APLIKASI KUANTITATIF dari fisika Module 1-9 (ISCO menentukan batas dalam disk, Eddington luminosity dari fisika radiasi), bukan pengantar deskriptif ulang.

**Overlap dengan `string-theory-for-engineers`: dicek eksplisit, tidak ada duplikasi teknis, hanya referensi sepintas satu arah.** Course string theory menyebut black hole/D-brane/holografi hanya sepintas di Module 12 (M-theory) sebagai konteks web dualitas — tidak pernah membangun black hole physics sebagai topik utuh. Course ini, sebaliknya, menyebut string theory/holographic principle hanya sepintas di Module 7.2 (entropi Bekenstein-Hawking sebagai "preview holografik") dan Module 10.4 (island formula/holografi sebagai salah satu arah riset information paradox) — keduanya eksplisit dinyatakan DI LUAR SCOPE dan diarahkan ke `domains/string-theory-for-engineers/DOMAIN.md` kalau learner ingin mendalami. Hubungannya murah-referensi dua arah, bukan tumpang tindih konten.

**Kenapa domain baru, bukan cukup jadi Module tambahan di `relativity`:** Mengubah `courses/relativity/roadmap.md` yang sudah didesain selesai (15 modul, capstone sendiri, Module Completion 0/15 tapi struktur sudah final) untuk menambahkan 13 modul black hole akan membengkakkan course itu jadi ~28 modul dan mengaburkan capstone tunggalnya (Relativity Portfolio, memilih SATU topik expert-level). Domain baru menjaga `relativity` tetap sebagai fondasi GR yang solid & selesai sebagai unit, sekaligus membiarkan black hole jadi spesialisasi mendalam dengan capstone-nya sendiri — konsisten dengan pola yang sudah dipakai `domains/string-theory-for-engineers/DOMAIN.md` dan `domains/antimatter/DOMAIN.md`, keduanya juga "spesialisasi lanjutan" yang mem-bridge dari `relativity` alih-alih menambah modul ke dalamnya.

Rincian lengkap keputusan ini (termasuk perbandingan sumber & sitasi lengkap) ada di `domains/black-hole/DOMAIN.md`, khususnya bagian Scope dan Registration History.
