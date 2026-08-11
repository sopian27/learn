# Course Roadmap

## Course Information

* Course Name: Astronomy & Life in the Universe — dari 0 sampai Expert (untuk Software Engineer)
* Category: Astronomy & Astrobiology (Domain: astronomy-and-life-in-the-universe — lihat `domains/astronomy-and-life-in-the-universe/DOMAIN.md`)
* Difficulty: Level 0 (paham astronomi populer level umum, belum pernah menyimulasikan orbit atau menghitung Drake Equation sendiri) → Level 3 (mampu membangun orbit simulator tervalidasi data nyata, dan menyusun argumen Fermi Paradox yang epistemically disciplined)
* Estimated Duration: 9 modul, 3-4 lesson/modul
* Prerequisites: Familiar pemrograman dasar (loop, fungsi, array/vector) untuk Modul 4-5. Modul 6-8 (astrobiologi) tidak butuh coding, murni penalaran tertulis. Disarankan sudah/sedang menempuh `critical-thinking` untuk fondasi penalaran probabilistik di Modul 8, meski tidak wajib prasyarat ketat.

---

# Catatan Scope

Sibling dari `courses/physics-for-software-engineers` (mekanika klasik) dan `courses/relativity` (SR/GR) — course ini TIDAK mengulang keduanya. Mekanika orbital diajarkan hanya secukupnya untuk proyek simulator (Hukum Kepler diterapkan komputasional), bukan derivasi fisika klasik penuh. Efek relativistik (presesi Merkurius, dilatasi waktu GPS) di luar scope, diarahkan ke `relativity`.

Referensi riset utama: OpenStax *Astronomy 2e*, MIT OCW 8.282J, NASA Open APIs, JPL Horizons, NASA Exoplanet Archive, ESA Gaia Archive, REBOUND (Rein & Liu 2012), NASA Astrobiology Program, Sandberg/Drexler/Ord (2018) & Westby/Conselice (2020) untuk Drake Equation, SETI Institute — daftar lengkap dengan catatan otoritas di `domains/astronomy-and-life-in-the-universe/DOMAIN.md`.

---

# Learning Objectives

* [ ] Memahami Tata Surya, bintang, galaksi, dan kosmologi dasar pada level deskriptif-kuantitatif (OpenStax/MIT OCW)
* [ ] Menerapkan Hukum Kepler secara komputasional untuk mekanika orbital dua-benda
* [ ] Membangun & memvalidasi simulasi N-body sederhana memakai data posisi nyata (JPL Horizons), membandingkan minimal 2 metode integrasi numerik terhadap kekekalan energi
* [ ] Mampu menarik & mengolah dataset astronomi nyata (NASA API, Exoplanet Archive, Gaia) untuk visualisasi/analisis
* [ ] Memahami dasar astrobiologi: asal-usul kehidupan, extremophile, habitable zone, biosignature/technosignature
* [ ] Menghitung Drake Equation sendiri dengan minimal 2 skenario asumsi berbeda dan menjelaskan kenapa hasilnya bisa berbeda berorde magnitudo
* [ ] Menerapkan template epistemik (fact/inference/hypothesis/speculation) secara konsisten pada setiap klaim soal kehidupan di luar Bumi — tidak pernah overclaim
* [ ] Menyelesaikan dua capstone: Planetary-Orbit Simulator dan Fermi Paradox Research Paper

---

# Course Modules

## Module 1 — Tata Surya & Mekanika Orbital Dasar

Description: Fondasi deskriptif Bumi/Bulan/Matahari/Tata Surya, plus Hukum Kepler sebagai jembatan ke simulasi komputasional.

Lessons:

* [ ] 1.1 Bumi, Bulan, Matahari — skala, gerak, dan mengapa itu penting untuk kalibrasi model
* [ ] 1.2 Struktur Tata Surya & klasifikasi benda langit
* [ ] 1.3 Hukum Kepler (I-III) diturunkan secara komputasional dari hukum gravitasi Newton

Mini Project: Hitung & plot orbit satu planet memakai Hukum Kepler dari parameter orbital nyata (bukan angka karangan).

Status: Not Started

---

## Module 2 — Bintang, Evolusi Bintang, dan Objek Ekstrem

Description: Siklus hidup bintang sampai objek paling ekstrem di alam semesta.

Lessons:

* [ ] 2.1 Klasifikasi bintang, diagram Hertzsprung-Russell
* [ ] 2.2 Evolusi bintang & supernova
* [ ] 2.3 Neutron star & black hole — konsep dasar (tanpa masuk GR penuh, itu domain `relativity`)

Mini Project: Plot HR diagram dari data bintang nyata (dataset Gaia atau subset publik).

Status: Not Started

---

## Module 3 — Galaksi & Kosmologi Dasar

Description: Skala galaksi dan struktur besar alam semesta.

Lessons:

* [ ] 3.1 Struktur galaksi, Bimasakti
* [ ] 3.2 Kosmologi dasar: ekspansi alam semesta, hukum Hubble (level konseptual)
* [ ] 3.3 Exoplanet — metode deteksi (transit, radial velocity) dan NASA Exoplanet Archive

Mini Project: Query NASA Exoplanet Archive, analisis distribusi exoplanet terkonfirmasi berdasarkan satu parameter (ukuran/jarak/period).

Status: Not Started

---

## Module 4 — Astronomi sebagai Data Science

Description: Menjembatani astronomi ke keahlian software engineering: data besar, API nyata, distributed computing.

Lessons:

* [ ] 4.1 NASA Open APIs & JPL Horizons — menarik data posisi/ephemeris nyata secara programatik
* [ ] 4.2 ESA Gaia Archive — bekerja dengan dataset astrometri skala miliar baris
* [ ] 4.3 Studi kasus Rubin Observatory/LSST — kenapa astronomi butuh distributed computing (~20 TB/malam)

Mini Project: Script yang menarik data real-time dari salah satu NASA API (APOD/NeoWs/DONKI) dan memvisualisasikannya.

Status: Not Started

---

## Module 5 — Metode Integrasi Numerik untuk Simulasi Orbit

Description: Matematika sesungguhnya di balik simulasi orbit — modul penentu untuk capstone Planetary-Orbit Simulator.

Lessons:

* [ ] 5.1 Kenapa Euler naif gagal untuk simulasi jangka panjang (energy drift)
* [ ] 5.2 Symplectic integrator: leapfrog, semi-implicit Euler, perbandingan terhadap RK4
* [ ] 5.3 Validasi terhadap REBOUND (Rein & Liu) sebagai reference implementation produksi

Intermediate Project: **N-body simulator kecil** — minimal 2 integrator diimplementasikan untuk sistem yang sama memakai initial condition nyata dari JPL Horizons, energy drift dibandingkan secara terukur.

Status: Not Started

---

## Module 6 — Asal-Usul Kehidupan & Extremophile

Description: Fondasi biologi/astrobiologi sebelum masuk ke pertanyaan besar Fermi Paradox.

Lessons:

* [ ] 6.1 Riset abiogenesis (asal-usul kehidupan) — status sebagai open hypothesis space, bukan fakta settled
* [ ] 6.2 Extremophile & perluasan batas habitability yang diketahui
* [ ] 6.3 Habitable zone — model/inferensi dari fisika (termodinamika air cair) + data astronomi

Mini Project: Klasifikasikan 5 klaim astrobiologi (dari artikel berita/sumber populer) ke dalam Firm Fact / Model-Inference / Open Hypothesis / Speculation.

Status: Not Started

---

## Module 7 — Drake Equation & Fermi Paradox

Description: Modul inti epistemik course ini — melatih penalaran di bawah ketidakpastian mendalam lewat kasus nyata yang masih diperdebatkan.

Lessons:

* [ ] 7.1 Drake Equation — tiap parameter dan sumber ketidakpastiannya
* [ ] 7.2 Sandberg/Drexler/Ord (2018) vs Westby/Conselice (2020) — persamaan sama, kesimpulan berbeda orde magnitudo karena asumsi berbeda
* [ ] 7.3 Great Filter (Hanson) — kenapa sangat penting apakah filter itu di belakang atau di depan kita

Mini Project: Hitung Drake Equation sendiri dengan 2 skenario asumsi berbeda, tulis justifikasi tiap parameter.

Status: Not Started

---

## Module 8 — SETI, Technosignature, dan Kasus Studi K2-18b

Description: Bagaimana pencarian bukti sungguhan dilakukan hari ini, plus contoh kerja nyata soal klaim biosignature yang naik lalu dibantah.

Lessons:

* [ ] 8.1 SETI Institute & Allen Telescope Array — metodologi pencarian technosignature nyata (radio, optik), dan fakta: belum pernah ada deteksi terkonfirmasi
* [ ] 8.2 Kardashev Scale sebagai kerangka berpikir (bukan pengukuran nyata)
* [ ] 8.3 Kasus studi JWST K2-18b (2023-2025): klaim awal ~3σ → reanalisis independen → walkback NASA 2025 — dipraktikkan sebagai template "fact vs inference vs hypothesis vs speculation" secara penuh

Mini Project: Tulis timeline K2-18b (klaim → tingkat keyakinan → reanalisis → konsensus saat ini) sebagai latihan disiplin epistemik sebelum capstone.

Status: Not Started

---

## Module 9 — Capstone

Description: Menyatukan Modul 1-5 (simulasi) dan Modul 6-8 (astrobiologi/epistemik) jadi dua artefak final.

Lessons:

* [ ] 9.1 Finalisasi Planetary-Orbit Simulator: validasi numerik penuh + visualisasi
* [ ] 9.2 Finalisasi Fermi Paradox Research Paper: perhitungan Drake + tinjauan Great Filter/SETI + kesimpulan eksplisit berkualifikasi ketidakpastian
* [ ] 9.3 Review silang: pertahankan kesimpulan Fermi Paradox terhadap pertanyaan "bukti apa yang akan mengubah pendapatmu?"

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: Dua artefak wajib, disimpan ke `portofolio/`:

1. **Planetary-Orbit Simulator** — integrator numerik tervalidasi (energy drift terukur, dibandingkan ke REBOUND), initial condition dari data JPL Horizons nyata, visualisasi orbit.
2. **Fermi Paradox Research Paper** — perhitungan Drake Equation sendiri (minimal 2 skenario asumsi), tinjauan Great Filter & SETI, dan kesimpulan yang SELALU berkualifikasi ketidakpastian (tidak pernah "aliens exist/don't exist" sebagai simpulan tunggal).

Acceptance Criteria:

* Simulator menunjukkan bukti numerik eksplisit kekekalan energi/momentum sudut (bukan "terlihat benar" secara visual)
* Paper Fermi Paradox mengklasifikasikan SETIAP klaim besarnya secara eksplisit (Firm Fact/Inference/Hypothesis/Speculation) dan menjawab "bukti apa yang akan mengubah kesimpulan?"
* Kedua artefak disimpan ke `portofolio/`

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval user sebagai bagian dari roadmap `life-mastery` Phase 9-10

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Bumi, Bulan, Matahari
