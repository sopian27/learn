# Course Roadmap

## Course Information

* Course Name: String Theory for Software Engineers — dari 0 sampai Expert
* Category: String Theory (Domain: string-theory-for-engineers — lihat `domains/string-theory-for-engineers/DOMAIN.md`)
* Difficulty: Level 0 (bisa coding, nyaman dengan aljabar linear & kalkulus, TAPI belum pernah belajar quantum mechanics, general relativity, atau string theory secara formal) → Level 4 (setara mahasiswa awal pascasarjana yang sudah menuntaskan graduate string theory course pertama gaya MIT 8.251: mampu menurunkan spektrum massa string bosonic/superstring, menjelaskan compactification & T-duality/S-duality secara kuantitatif, memahami compactification Calabi-Yau & mirror symmetry secukupnya untuk membaca Zwiebach/Tong mandiri, dan mensimulasikan/memvisualisasikan konsep intinya lewat kode)
* Estimated Duration: 15 modul (14 modul konten + 1 modul Capstone), rata-rata 4-5 lesson/modul — pace disesuaikan karena tiap modul WAJIB menghasilkan satu Derivation & Simulation Lab (derivasi tangan lengkap DAN artefak simulasi/kode) per `domains/string-theory-for-engineers/DOMAIN.md` bagian "Applied Practice Definition", bukan sekadar baca teori
* Prerequisites: Bisa coding (Python direkomendasikan untuk NumPy/SciPy/SymPy/Matplotlib) dengan dasar aljabar linear (vektor, matriks, eigenvalue) dan kalkulus multivariat (turunan parsial, integral lintasan secukupnya). TIDAK butuh quantum mechanics atau general relativity sebelumnya — keduanya dibangun di dalam course ini sendiri: QM dari nol di Module 2-3, GR HANYA sebagai bridge singkat di Module 4 (bukan pengulangan penuh). Direkomendasikan kuat (opsional tapi sangat disarankan) sudah menuntaskan atau berjalan paralel dengan `courses/relativity/roadmap.md` Module 1-9 (Special Relativity penuh sampai geometri diferensial & metrik Schwarzschild) — kalau belum, Module 4 course ini menyediakan ringkasan minimal yang cukup untuk lanjut, tapi eksplisit BUKAN pengganti derivasi penuh course relativity. Geometri diferensial, complex analysis, dan teori grup/Lie algebra lanjutan dibangun just-in-time di Module 4 dan Module 13, bukan diasumsikan sudah dikuasai di awal.

---

# Learning Objectives

* [ ] Menguasai fondasi quantum mechanics (state vector, operator, prinsip ketidakpastian, osilator harmonik kuantum, entanglement dasar) secukupnya untuk mengikuti kuantisasi string (Susskind & Friedman, *Theoretical Minimum*)
* [ ] Memakai hasil general relativity secukupnya (prinsip ekivalensi, metrik, geodesic) sebagai bahasa target-space, TANPA mengulang derivasi penuh yang sudah dimiliki domain `relativity`
* [ ] Menurunkan sendiri aksi Nambu-Goto & Polyakov untuk string relativistik, dan persamaan gerak/syarat batasnya untuk open & closed string
* [ ] Mengkuantisasi string bosonic di light-cone gauge, menurunkan dimensi kritis D=26, dan menjelaskan kenapa muncul tachyon (masalah yang dipecahkan superstring nanti)
* [ ] Menjelaskan Kaluza-Klein compactification & extra dimensions, termasuk KK tower dan kenapa dimensi ekstra harus kecil/compact
* [ ] Menurunkan & mendemonstrasikan T-duality secara kuantitatif (winding vs momentum mode, R ↔ α'/R) untuk string tertutup dan terbuka
* [ ] Menjelaskan kenapa bosonic string gagal (tachyon, tanpa fermion) dan bagaimana superstring/SUSY/GSO projection memperbaikinya, sampai ke lima teori superstring konsisten di D=10
* [ ] Menjelaskan D-branes sebagai objek dinamis (syarat batas Dirichlet, medan gauge pada worldvolume brane) dan perannya dalam duality web
* [ ] Menjelaskan S-duality, web dualitas lima teori superstring, dan M-theory sebagai payung 11 dimensi yang menyatukannya
* [ ] Menguasai matematika compactification Calabi-Yau secukupnya (complex manifold, Kähler geometry, syarat Ricci-flat) dan mirror symmetry (mirror pair, SYZ conjecture sebagai T-duality) untuk membaca Candelas et al. (1991) secara konseptual
* [ ] Membedakan secara eksplisit klaim matematis string theory yang solid (mis. D=26, T-duality) dari klaim fisis yang masih spekulatif (mis. landscape, string theory sebagai "the" theory of everything) — Epistemic Honesty per `domains/string-theory-for-engineers/DOMAIN.md`
* [ ] Menghasilkan Capstone Project (String Worldsheet Simulator / Compactification & Duality Visualizer / Calabi-Yau Cross-Section Visualizer) yang menggabungkan derivasi tangan + simulasi numerik tervalidasi

---

# Course Modules

## Module 1 — Orientasi: Peta Besar, Prasyarat, dan Toolkit

Description: Sebelum masuk formalisme apa pun, pahami dulu kenapa string theory butuh QM DAN GR sekaligus, apa masalah unifikasi yang coba dipecahkannya (graviton di teori medan titik/point-particle QFT tidak renormalizable), sejarah singkatnya (dual resonance model → first superstring revolution), dan setup toolkit komputasi (Python: NumPy, SciPy, SymPy, Matplotlib) yang dipakai sepanjang course.

Lessons:

* [ ] 1.1 Peta besar: kenapa fisika butuh "teori segalanya" — batas Model Standar & GR klasik, masalah graviton di point-particle QFT
* [ ] 1.2 Sejarah singkat: dual resonance model (Veneziano 1968) → penemuan ulang sebagai teori string → first superstring revolution (1984)
* [ ] 1.3 Peta prasyarat course ini: QM (Module 2-3, dari nol), GR-bridge (Module 4, ringkas, dirujuk ke domain `relativity` untuk kedalaman), matematika just-in-time (Module 4 & 13)
* [ ] 1.4 Setup toolkit: Python + NumPy/SciPy/SymPy/Matplotlib — uji coba plot & simbolik sederhana sebagai smoke test sebelum dipakai serius di modul berikutnya

Mini Project: Notebook orientasi — ringkasan tertulis "peta ketergantungan" course ini (topik apa butuh topik apa) plus skrip Python smoke-test (plot fungsi sederhana + manipulasi simbolik SymPy dasar) yang memverifikasi toolkit siap dipakai.

Status: Not started

---

## Module 2 — Fondasi Quantum Mechanics I: State, Operator, dan Ketidakpastian

Description: Modul pertama dari dua modul QM — dibangun dari nol karena TIDAK ada domain lain di repo ini yang mengajarkan quantum mechanics. Mengikuti struktur awal Susskind & Friedman, *Quantum Mechanics: The Theoretical Minimum* (Bab 1-5): sistem & eksperimen, ruang state, operator, dan prinsip ketidakpastian.

Lessons:

* [ ] 2.1 Kenapa mekanika klasik gagal di skala kecil — eksperimen pemikiran spin/polarisasi sebagai motivasi (Susskind Ch. 1-2)
* [ ] 2.2 Ruang state kuantum: ket/bra notation, superposisi, ruang Hilbert secukupnya
* [ ] 2.3 Operator, observable, eigenvalue/eigenvector, dan makna pengukuran kuantum
* [ ] 2.4 Prinsip ketidakpastian Heisenberg — derivasi dari komutator, bukan cuma dihafal sebagai slogan "tidak bisa tahu posisi & momentum sekaligus"

Mini Project (Derivation & Simulation Lab): Derivasi tangan ketidakpastian Heisenberg dari relasi komutator [x,p]; kode simulasi kecil yang memvisualisasikan superposisi dua state basis (mis. spin-1/2) dan probabilitas pengukurannya dari amplitudo, dibandingkan ke prediksi analitik.

Status: Not started

---

## Module 3 — Fondasi Quantum Mechanics II: Osilator Harmonik dan Operator Creation/Annihilation

Description: Modul QM kedua, fokus pada satu topik yang jadi fondasi WAJIB untuk kuantisasi string nanti (Module 7): osilator harmonik kuantum lewat operator creation/annihilation. Mengikuti Susskind & Friedman Ch. 9-10, plus pengantar path integral secukupnya (Feynman) sebagai jembatan konseptual ke Polyakov path integral.

Lessons:

* [ ] 3.1 Partikel & gelombang — dualitas, fungsi gelombang, persamaan Schrödinger secukupnya
* [ ] 3.2 Osilator harmonik kuantum: solusi lewat operator creation (a†) & annihilation (a), bukan lewat persamaan diferensial langsung
* [ ] 3.3 Spektrum energi osilator harmonik & interpretasi "kuanta" sebagai eksitasi — pola yang akan berulang persis di spektrum massa string (preview eksplisit ke Module 7)
* [ ] 3.4 Pengantar path integral Feynman secukupnya — intuisi "jumlahkan semua lintasan" sebagai jembatan ke Polyakov path integral (bukan formalisme QFT penuh)

Mini Project (Derivation & Simulation Lab): Derivasi tangan spektrum energi osilator harmonik kuantum lewat aljabar operator [a,a†]=1; kode yang membangun matriks operator a/a† pada basis terpotong (truncated Fock space) dan memverifikasi numerik spektrum energi E_n = ℏω(n+1/2) sesuai hasil derivasi.

Status: Not started

---

## Module 4 — General Relativity Bridge: Secukupnya untuk Target Space

Description: Modul bridge, BUKAN pengulangan — memberi ringkasan minimal GR (prinsip ekivalensi, metrik, kelengkungan, geodesic, notasi tensor dasar) yang cukup untuk memahami target-space geometry di string theory nanti. Derivasi penuh persamaan medan Einstein dkk. eksplisit di luar scope, dirujuk ke `courses/relativity/roadmap.md`.

Lessons:

* [ ] 4.1 Prinsip ekivalensi & gagasan spacetime melengkung — ringkasan konseptual (bukan derivasi penuh, lihat `domains/relativity/DOMAIN.md`)
* [ ] 4.2 Metrik sebagai objek fundamental, notasi indeks & tensor dasar secukupnya (index notation, Einstein summation) untuk dibaca di Module 6-7
* [ ] 4.3 Geodesic secukupnya — kenapa string bergerak di target space melengkung mengikuti prinsip serupa geodesic partikel titik
* [ ] 4.4 Peta eksplisit: kapan course ini butuh GR lebih dalam (mis. metrik Schwarzschild, gelombang gravitasi) → diarahkan ke `courses/relativity/roadmap.md`, bukan dijawab di sini

Mini Project (Derivation & Simulation Lab): Derivasi tangan ringkas geodesic equation dari prinsip variational (aksi panjang lintasan) untuk metrik 2D sederhana; kode integrator geodesic numerik kecil pada metrik tersebut (boleh reuse pola dari `courses/relativity/roadmap.md` Module 7 kalau sudah dikerjakan) untuk memvisualisasikan lintasan pada permukaan melengkung sederhana (mis. bola).

Status: Not started

---

## Module 5 — Dinamika Klasik String Relativistik: Aksi Nambu-Goto & Polyakov

Description: Modul pertama string theory "sungguhan" — string sebagai objek 1-dimensi yang menyapu worldsheet 2D di target space, aksi yang meminimalkan luas worldsheet (Nambu-Goto) dan bentuk setaranya yang lebih mudah dikuantisasi (Polyakov). Mengikuti Zwiebach Ch. 6, Tong Ch. 1.

Lessons:

* [ ] 5.1 Dari partikel relativistik ke string: reparametrization invariance, worldline vs worldsheet
* [ ] 5.2 Aksi Nambu-Goto — string meminimalkan luas worldsheet, analog aksi panjang lintasan partikel relativistik
* [ ] 5.3 Aksi Polyakov — setara klasik dengan Nambu-Goto tapi tanpa akar kuadrat, lebih mudah dikuantisasi; peran worldsheet metric sebagai medan tambahan
* [ ] 5.4 Persamaan gerak string & syarat batas — open string (Neumann/Dirichlet preview) vs closed string (periodik)

Mini Project (Derivation & Simulation Lab): Derivasi tangan persamaan gerak string dari variasi aksi Polyakov (persamaan gelombang 2D di worldsheet); kode solver persamaan gelombang 1D pada worldsheet (finite difference sederhana) untuk string terbuka dengan syarat batas tetap, dibandingkan ke solusi mode analitik.

Status: Not started

---

## Module 6 — Solusi Mode Klasik: String Terbuka & Tertutup

Description: Memecahkan persamaan gerak string secara eksplisit lewat dekomposisi mode Fourier — fondasi langsung untuk kuantisasi Module 7, dan objek yang akan disimulasikan langsung di Capstone jalur (A). Mengikuti Zwiebach Ch. 6-7.

Lessons:

* [ ] 6.1 Static gauge & parametrisasi worldsheet — menyederhanakan persamaan gerak jadi persamaan gelombang standar
* [ ] 6.2 Solusi mode string terbuka: syarat batas Neumann, ekspansi mode osilasi transversal
* [ ] 6.3 Solusi mode string tertutup: syarat periodik, mode kiri vs kanan (left-movers & right-movers)
* [ ] 6.4 Energi, momentum, dan momentum sudut string klasik dari solusi mode — mempersiapkan spektrum massa kuantum di Module 7

Mini Project (Derivation & Simulation Lab): Derivasi tangan solusi mode Fourier string terbuka & tertutup dari persamaan gelombang + syarat batas masing-masing; kode simulator & animator osilasi string (transversal, beberapa mode pertama) untuk kedua jenis, divalidasi terhadap solusi analitik pada beberapa titik waktu sampel.

Status: Not started

---

## Module 7 — Kuantisasi String & Dimensi Kritis D=26

Description: Puncak matematis paruh pertama course — mengkuantisasi mode osilasi string (memakai operator creation/annihilation yang sudah dikuasai dari Module 3) di light-cone gauge, menurunkan spektrum massa, dan sampai pada hasil paling terkenal & paling sering disalahpahami: dimensi kritis D=26 untuk bosonic string, serta masalah tachyon yang menyertainya. Mengikuti Zwiebach Ch. 12, Tong Ch. 2.

Lessons:

* [ ] 7.1 Light-cone gauge — menghilangkan derajat kebebasan tidak fisis, menyisakan mode osilasi transversal murni
* [ ] 7.2 Kuantisasi mode osilasi: promosi koefisien Fourier jadi operator creation/annihilation, persis pola Module 3 tapi untuk tak-hingga banyak osilator (satu per mode)
* [ ] 7.3 Normal ordering, konstanta zero-point, dan derivasi dimensi kritis D=26 dari syarat konsistensi (Lorentz invariance di level kuantum)
* [ ] 7.4 Spektrum massa string bosonic & masalah tachyon (state dengan m²<0) — kenapa ini jadi motivasi utama superstring di Module 10

Mini Project (Derivation & Simulation Lab): Derivasi tangan formula spektrum massa string terbuka/tertutup dari operator bilangan level (Σ N_n) plus normal-ordering constant, tunjukkan eksplisit dari mana D=26 muncul; kode kalkulator spektrum massa yang menghitung & menampilkan level massa pertama untuk string terbuka & tertutup, menandai state tachyonic secara eksplisit.

Status: Not started

---

## Module 8 — Extra Dimensions & Kaluza-Klein Compactification

Description: Kalau spacetime string theory butuh 26 (atau nanti 10) dimensi, ke mana dimensi ekstra itu "pergi"? Modul ini membangun ide Kaluza-Klein compactification dari nol — dimensi ekstra yang digulung sangat kecil sehingga tidak teramati di skala energi rendah. Mengikuti Tong Ch. 8 bagian awal, Zwiebach bagian compactification.

Lessons:

* [ ] 8.1 Ide asli Kaluza-Klein: menyatukan elektromagnetisme dengan gravitasi lewat dimensi ke-5 yang digulung jadi lingkaran
* [ ] 8.2 Compactification pada lingkaran radius R — momentum kuantisasi di dimensi kompak (p = n/R)
* [ ] 8.3 Kaluza-Klein tower — spektrum massa tak-hingga banyak state 4D dari satu medan 5D, dan kenapa mode berat bisa diabaikan di energi rendah (E << 1/R)
* [ ] 8.4 Menerapkan compactification ke string: dimensi ekstra digulung, spektrum string mendapat kontribusi momentum KK tambahan

Mini Project (Derivation & Simulation Lab): Derivasi tangan spektrum massa KK tower dari kuantisasi momentum pada lingkaran; kode plotter spektrum KK tower untuk beberapa nilai radius R, menunjukkan eksplisit bagaimana spektrum merapat (mendekati kontinu) saat R membesar dan menjauh (mode berat) saat R mengecil.

Status: Not started

---

## Module 9 — T-Duality: Winding, Momentum, dan R ↔ α'/R

Description: Hasil paling khas string theory yang TIDAK punya analog di teori medan titik — string yang bisa membungkus (winding) dimensi kompak punya mode tambahan yang membuat fisika pada radius kecil R identik dengan fisika pada radius besar α'/R. Mengikuti Tong Ch. 8 (T-duality), Zwiebach.

Lessons:

* [ ] 9.1 Winding mode — string tertutup bisa membungkus dimensi kompak n kali, sesuatu yang mustahil untuk partikel titik
* [ ] 9.2 Spektrum massa string tertutup pada dimensi kompak: kontribusi momentum (KK) DAN winding sekaligus
* [ ] 9.3 T-duality: simetri spektrum di bawah R → α'/R bersamaan dengan pertukaran momentum ↔ winding — derivasi eksplisit kenapa spektrum benar-benar identik
* [ ] 9.4 T-duality untuk string terbuka — D-brane sebagai konsekuensi (preview eksplisit ke Module 11)

Mini Project (Derivation & Simulation Lab): Derivasi tangan invariansi spektrum massa string tertutup di bawah transformasi T-duality (R → α'/R, n ↔ w); kode yang menghitung & membandingkan numerik spektrum massa penuh pada radius R vs radius α'/R untuk beberapa nilai n,w — pembuktian numerik eksplisit bahwa kedua spektrum identik.

Status: Not started

---

## Module 10 — Superstring, Supersimetri, dan Lima Teori Konsisten

Description: Bosonic string (Module 5-9) punya dua masalah serius: tachyon dan tidak ada fermion (padahal materi nyata terdiri fermion). Modul ini memperkenalkan supersimetri pada worldsheet, GSO projection yang membuang tachyon, dan hasil bahwa hanya D=10 yang konsisten — melahirkan lima teori superstring berbeda. Mengikuti Zwiebach bagian superstring, Becker-Becker-Schwarz Ch. 4-5.

Lessons:

* [ ] 10.1 Kenapa bosonic string tidak cukup: tachyon & absennya fermion — motivasi supersimetri pada worldsheet
* [ ] 10.2 Superstring & GSO projection — bagaimana tachyon dibuang & fermion muncul secara konsisten
* [ ] 10.3 Dimensi kritis superstring D=10, dan sekilas kenapa berbeda dari D=26 bosonic
* [ ] 10.4 Lima teori superstring konsisten (Type I, Type IIA, Type IIB, Heterotic SO(32), Heterotic E8×E8) — gambaran umum perbedaannya, pengantar teori grup/Lie algebra secukupnya untuk memahami nama SO(32)/E8×E8 (Georgi, secukupnya)

Mini Project (Derivation & Simulation Lab): Derivasi tangan ringkas bagaimana GSO projection menghilangkan tachyon dari spektrum (level argumen, bukan superspace formalism penuh); kode tabel pembanding lima teori superstring (dimensi kritis, tipe string, grup gauge/simetri) sebagai referensi terstruktur, divalidasi silang ke Zwiebach & Becker-Becker-Schwarz.

Status: Not started

---

## Module 11 — D-Branes: Objek Dinamis, Bukan Sekadar Syarat Batas

Description: T-duality untuk string terbuka (Module 9.4) memaksa munculnya D-branes — bidang hyperplane tempat endpoint string terbuka terikat. Modul ini membangun D-brane sebagai objek fisis dinamis sungguhan (bukan trik matematis), lengkap dengan medan gauge yang hidup di worldvolume-nya. Mengikuti Zwiebach bagian D-brane, Polchinski Vol. 1 Ch. 8.

Lessons:

* [ ] 11.1 Syarat batas Dirichlet vs Neumann — dari mana D-brane muncul secara matematis dari T-duality string terbuka
* [ ] 11.2 D-brane sebagai objek dinamis: tegangan (brane tension), dimensi (Dp-brane), dan kenapa ia bukan sekadar "dinding" pasif
* [ ] 11.3 Medan gauge pada worldvolume D-brane — string terbuka dengan kedua ujung di brane sama menghasilkan medan gauge U(1) (atau U(N) untuk N brane bertumpuk)
* [ ] 11.4 D-brane dalam duality web — peran D-brane menghubungkan berbagai teori superstring (preview eksplisit ke Module 12)

Mini Project (Derivation & Simulation Lab): Derivasi tangan syarat batas Dirichlet dari T-duality pada koordinat string terbuka; kode visualisasi sederhana endpoint string terbuka yang terkunci bergerak sepanjang D-brane (2D: brane sebagai garis, string sebagai kurva dengan endpoint dibatasi pada garis itu).

Status: Not started

---

# Intermediate Project — T-Duality & Brane Spectrum Matcher (Module 9-11)

Description: Menggabungkan Module 9 (T-duality), 10 (superstring), dan 11 (D-branes) jadi satu artefak terintegrasi, per `domains/string-theory-for-engineers/DOMAIN.md` bagian "Project Tiers": kode yang memverifikasi numerik bahwa spektrum massa string (KK + winding) pada radius R sama persis dengan spektrum pada radius α'/R untuk string terbuka DAN tertutup, plus visualisasi D-brane yang menunjukkan endpoint string terkunci secara konsisten dengan hasil T-duality Module 9.4/11.1. Disertai penjelasan tertulis singkat bagaimana ketiga modul ini saling mengunci secara logis (T-duality → D-brane bukan kebetulan, tapi konsekuensi matematis langsung).

Status: Not started

---

## Module 12 — S-Duality, Web Dualitas, dan M-Theory

Description: T-duality (Module 9) menghubungkan teori pada radius berbeda; S-duality menghubungkan teori pada kekuatan kopling berbeda (strong-weak duality) — bersama, kelima teori superstring (Module 10) ternyata semuanya terhubung dalam satu web dualitas, dan disatukan lebih jauh oleh M-theory 11 dimensi. Mengikuti Becker-Becker-Schwarz Ch. 8, Zwiebach bagian penutup.

Lessons:

* [ ] 12.1 S-duality: strong-weak coupling duality — kenapa teori pada kopling kuat bisa setara secara eksak dengan teori lain pada kopling lemah
* [ ] 12.2 Web dualitas lengkap: bagaimana T-duality & S-duality saling menghubungkan Type I, IIA, IIB, Heterotic SO(32), Heterotic E8×E8
* [ ] 12.3 M-theory: petunjuk 11 dimensi (limit kopling kuat Type IIA), dan gagasan bahwa lima teori superstring adalah "sudut pandang" berbeda dari satu teori yang lebih dalam
* [ ] 12.4 Status M-theory secara jujur — dipahami lewat dualitas & limit-limitnya, TAPI formulasi fundamentalnya sendiri masih terbuka (per Epistemic Honesty, `domains/string-theory-for-engineers/DOMAIN.md`)

Mini Project (Derivation & Simulation Lab): Derivasi tangan (level argumen, bukan superspace penuh) kenapa S-duality Type IIB memetakan spektrum D-brane ke fundamental string dan sebaliknya; kode/diagram (boleh grafik NetworkX/Matplotlib) yang memetakan web dualitas lima teori superstring + M-theory sebagai graph bertautan, beserta jenis dualitas tiap edge (T atau S).

Status: Not started

---

## Module 13 — Math Bridge: Complex Manifold, Kähler Geometry, dan Syarat Ricci-Flat

Description: Modul jembatan matematika kedua course ini (setelah Module 4) — membangun bahasa geometri yang dibutuhkan untuk compactification realistis: complex manifold, Kähler geometry, holonomy, dan kenapa syarat Ricci-flat (bukan sembarang manifold kompak) yang dibutuhkan supaya compactification 6D mempertahankan N=1 supersimetri di 4D. Mengikuti Zwiebach bagian Calabi-Yau, Nakahara untuk latar belakang geometri.

Lessons:

* [ ] 13.1 Complex manifold secukupnya — koordinat kompleks, kenapa 6 dimensi ekstra realistis diperlakukan sebagai 3 dimensi kompleks
* [ ] 13.2 Kähler geometry & holonomy — pengantar konseptual (bukan klasifikasi holonomy penuh), fokus pada kenapa holonomy membatasi supersimetri yang bertahan
* [ ] 13.3 Syarat Ricci-flat & kenapa itu prasyarat compactification menjaga N=1 SUSY di 4D — sambungan ke persamaan Einstein di Module 4 (Ricci tensor = 0 sebagai vacuum solution)
* [ ] 13.4 Teorema eksistensi Yau (Calabi conjecture) — hasilnya DIKUTIP secara eksplisit (bukan diturunkan; bukti penuh di luar scope, per `domains/string-theory-for-engineers/DOMAIN.md`)

Mini Project (Derivation & Simulation Lab): Derivasi tangan (level konseptual-kuantitatif, bukan bukti eksistensi) kenapa syarat Ricci-flat pada manifold compactification setara dengan vacuum Einstein equation dari Module 4; kode SymPy yang menghitung tensor Ricci untuk satu metrik Kähler 2D sederhana (mis. Fubini-Study pada CP¹) dan memverifikasi apakah nol atau tidak, sebagai latihan konkret sebelum masuk Calabi-Yau penuh di Module 14.

Status: Not started

---

## Module 14 — Calabi-Yau Compactification & Mirror Symmetry

Description: Menyatukan Module 8 (compactification), 9 (T-duality), dan 13 (math bridge) ke kasus paling realistis dalam string phenomenology: compactification pada Calabi-Yau threefold, dan hasil paling menakjubkan yang muncul darinya — mirror symmetry, di mana dua Calabi-Yau berbeda menghasilkan fisika 4D yang identik. Mengikuti Candelas-de la Ossa-Green-Parkes (1991), Brian Greene TASI lectures, Zwiebach.

Lessons:

* [ ] 14.1 Calabi-Yau threefold sebagai pilihan compactification 6D — Hodge numbers (h^{1,1}, h^{2,1}) sebagai data yang menentukan fisika 4D (jumlah moduli, generasi fermion)
* [ ] 14.2 Moduli space compactification — Kähler moduli & complex structure moduli, dan kenapa keduanya perlu distabilkan untuk fisika realistis
* [ ] 14.3 Mirror symmetry: mirror pair Calabi-Yau dengan Hodge diamond tercermin, dan klaim intinya (Candelas et al. 1991) bahwa CFT string pada keduanya identik
* [ ] 14.4 Mirror symmetry sebagai kasus khusus T-duality (SYZ conjecture, Strominger-Yau-Zaslow) — menyambungkan kembali eksplisit ke Module 9

Mini Project (Derivation & Simulation Lab): Derivasi tangan/ringkasan terstruktur bagaimana Hodge numbers mirror pair saling bertukar (h^{1,1} ↔ h^{2,1}), dengan kutipan eksplisit hasil Candelas et al. (1991); kode visualisasi proyeksi 2D/3D dari permukaan Fermat quintic sederhana (gaya Hanson/analyticphysics.com) sebagai pengantar visual sebelum Capstone jalur (C).

Status: Not started

---

## Module 15 — Capstone: Simulasi/Visualisasi String Theory

Description: Mengintegrasikan seluruh derivasi & artefak Module 1-14 jadi satu Capstone Project portofolio, mengikuti `domains/string-theory-for-engineers/DOMAIN.md` bagian "Project Tiers" — learner memilih SATU dari tiga jalur.

Lessons:

* [ ] 15.1 Menentukan jalur capstone & scope: String Worldsheet Dynamics Simulator / Compactification & Duality Visualizer / Calabi-Yau Cross-Section Visualizer
* [ ] 15.2 Desain & implementasi artefak capstone, mengintegrasikan modul-modul relevan dengan jalur terpilih
* [ ] 15.3 Validasi numerik menyeluruh sesuai jalur — solusi mode analitik (Worldsheet Simulator), invariansi spektrum T-duality (Duality Visualizer), atau konsistensi topologis Hodge numbers/Euler characteristic yang dinyatakan (Calabi-Yau Visualizer)
* [ ] 15.4 Dokumentasi tertulis: keputusan desain matematis/numerik, trade-off yang diambil, DAN status epistemis hasil (mana matematika solid, mana interpretasi fisis) — disimpan ke `portofolio/`

Capstone Project: Pilih SATU — **(A) String Worldsheet Dynamics Simulator**: simulasi & animasi osilasi string terbuka/tertutup dari solusi mode Fourier (Module 6), divalidasi terhadap solusi analitik pada beberapa titik waktu. **(B) Compactification & Duality Visualizer**: visualisasi spektrum KK tower pada compactification lingkaran/torus (Module 8) + demonstrasi numerik penuh T-duality R↔α'/R (Module 9) untuk string terbuka & tertutup. **(C) Calabi-Yau Cross-Section Visualizer**: visualisasi proyeksi 2D/3D quintic Calabi-Yau threefold (Module 14) dengan penjelasan tertulis kaitannya ke mirror pair & Hodge numbers. Semua jalur disertai penjelasan tertulis desain matematis/numerik, trade-off, dan pemisahan eksplisit klaim matematis solid vs interpretasi fisis (per Epistemic Honesty, `domains/string-theory-for-engineers/DOMAIN.md`).

Status: Not started

---

# Capstone Project

Description: Lihat Module 15 di atas untuk rincian lengkap tiga jalur capstone (String Worldsheet Simulator / Compactification & Duality Visualizer / Calabi-Yau Cross-Section Visualizer). Capstone course ini SATU dengan Capstone Project Module 15 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/string-theory-for-engineers/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` DITAMBAH kelima lensa Review Style domain ini dari `domains/string-theory-for-engineers/DOMAIN.md`: **Derivational Rigor** (tiap langkah derivasi berjustifikasi eksplisit), **Dimensional & Limit Consistency** (hasil kembali ke limit yang benar, mis. α'→0), **Conceptual vs Rote** (bisa menjelaskan MENGAPA, bukan cuma aljabar), **Simulation Fidelity** (kode benar-benar mengimplementasikan matematika yang diturunkan, hasil numerik dibandingkan ke referensi analitik), dan **Epistemic Honesty** (klaim matematis solid dipisahkan eksplisit dari klaim fisis spekulatif). Disimpan ke `portofolio/` disertai dokumentasi trade-off desain.

Status: Not started

---

# Overall Progress

Module Completion: 0/15

Overall Completion: 0%

Current Module: Module 1 — Orientasi: Peta Besar, Prasyarat, dan Toolkit (menunggu approval roadmap)

Current Lesson: 1.1 Peta besar: kenapa fisika butuh "teori segalanya" — batas Model Standar & GR klasik, masalah graviton di point-particle QFT

Next Lesson: 1.1 Peta besar: kenapa fisika butuh "teori segalanya" — batas Model Standar & GR klasik, masalah graviton di point-particle QFT

---

# Catatan Scope

**Keputusan domain: domain BARU (`string-theory-for-engineers`), bukan ekstensi domain existing.** Task awal meminta eksplisit mengevaluasi apakah course ini sebaiknya masuk domain `physics-for-software-engineers` yang sudah ada. Setelah membaca penuh `domains/physics-for-software-engineers/DOMAIN.md`, jawabannya TIDAK — domain itu secara eksplisit menyatakan di bagian Scope: "Secara eksplisit domain ini TIDAK mencakup... Relativitas Khusus & Umum... Mekanika kuantum / fisika komputasi kuantum." Kedua hal itu justru prasyarat WAJIB string theory (Module 2-4 course ini). Memaksakan course ini ke domain itu akan melanggar batas scope yang sudah didokumentasikan sendiri oleh domain tersebut — bukan tumpang tindih yang sehat, tapi pelanggaran domain contract.

**Overlap dengan domain `relativity`: dicek eksplisit, TIDAK ada duplikasi — hubungannya prasyarat, bukan tumpang tindih.** `domains/relativity/DOMAIN.md` sudah ada dan sudah mencakup General Relativity secara mendalam (Module 1-15 di `courses/relativity/roadmap.md`, dari transformasi Lorentz sampai gelombang gravitasi & kosmologi). Bahkan lebih spesifik lagi: domain `relativity` sendiri, di bagian Scope-nya, SUDAH mengantisipasi kebutuhan course ini — kalimatnya persis: "perpotongan relativitas dengan kuantum (mis. radiasi Hawking, quantum gravity, string theory) disebut sebagai 'ke mana arah riset lanjut' di akhir course, tapi tidak diajarkan mendalam — itu di luar scope dan **butuh domain terpisah di masa depan kalau dibutuhkan**." Course ini adalah pemenuhan eksplisit dari antisipasi itu, bukan duplikasi tak disengaja. Konsekuensi desain konkret: Module 4 course ini HANYA bridge GR minimal (prinsip ekivalensi, metrik, geodesic — cukup untuk target-space geometry), dan setiap kali topik butuh GR lebih dalam (metrik Schwarzschild, gelombang gravitasi, kosmologi), learner diarahkan eksplisit ke `courses/relativity/roadmap.md` alih-alih diajarkan ulang di sini — house rule ini ditulis eksplisit di `domains/string-theory-for-engineers/DOMAIN.md` bagian Domain-Specific Standards, mirroring pola yang sama dipakai `domains/physics-for-software-engineers/DOMAIN.md` untuk membatasi diri terhadap topik relativistik.

**Quantum mechanics: gap yang belum ditutup domain manapun di repo ini.** Tidak ada domain existing (dicek lewat `ai-los/ACTIVE_DOMAIN.md` daftar registered domains) yang mengajarkan quantum mechanics. Karena string theory butuh QM sebagai fondasi kuantisasi (Module 7 course ini secara langsung memakai pola operator creation/annihilation dari Module 3), dan tidak ada domain lain yang bisa dirujuk sebagai prasyarat (berbeda dengan GR yang punya domain `relativity`), Module 2-3 course ini membangun QM dari nol — cakupannya dikalibrasi secukupnya untuk string theory (mengikuti *Theoretical Minimum* Susskind & Friedman), bukan course QM lengkap gaya jurusan fisika (tidak mencakup mekanika statistik kuantum, banyak-partikel/second quantization penuh, atau aplikasi condensed matter).

**Kenapa domain baru, bukan cukup jadi course tambahan di domain `physics-for-software-engineers` dengan pengecualian ditulis ulang:** Mengubah scope domain existing untuk mengakomodasi course ini akan bertentangan langsung dengan pernyataan eksplisit domain itu sendiri (lihat kutipan di atas) dan berisiko men-drift domain yang sudah solid (murni klasik/Newtonian, applied-engineering-heavy) jadi kabur. Domain baru menjaga kedua domain existing tetap murni sesuai kontraknya masing-masing, sekaligus konsisten dengan pola registrasi domain baru yang sudah dipakai repo ini (lihat `ai-los/ACTIVE_DOMAIN.md` bagian "Registering a New Domain").

Rincian lengkap keputusan ini (termasuk perbandingan sumber & sitasi) ada di `domains/string-theory-for-engineers/DOMAIN.md`, khususnya bagian Scope dan Registration History.
