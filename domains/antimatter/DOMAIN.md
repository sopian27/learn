# Antimateri — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Antimateri (Antimatter) — fisika partikel dari Model Standard sampai antimateri: persamaan Dirac, antipartikel, anihilasi, produksi pasangan, produksi & penyimpanan antimateri di eksperimen nyata (CERN), gravitasi antimateri, asimetri materi-antimateri kosmologis, dan aplikasi nyata (kedokteran nuklir, material science).

## Scope

Fisika partikel sebagai jalur menuju pemahaman utuh soal antimateri — dari matematika yang memprediksinya sampai eksperimen nyata yang memproduksi dan mengukurnya hari ini:

- **Prasyarat cepat**: hasil relativitas khusus yang dipakai langsung (four-momentum, E²=(pc)²+(mc²)², natural units) — bridge, BUKAN derivasi penuh (itu milik domain [[domains/relativity/DOMAIN.md|relativity]]).
- **Fondasi mekanika kuantum secukupnya**: wave function, operator, spin — hanya sebanyak yang dibutuhkan untuk memahami persamaan Dirac & spinor, BUKAN kursus QM penuh (bandingkan dengan domain [[domains/quantum-computing-for-engineers/DOMAIN.md|quantum-computing-for-engineers]] yang membangun QM untuk tujuan berbeda — qubit sebagai unit informasi).
- **Model Standard partikel**: fermion (quark & lepton, 3 generasi), boson gauge (foton, gluon, W/Z, Higgs), muatan/warna/spin, empat interaksi fundamental (kuat/lemah/elektromagnetik/gravitasi) — peta besar sebelum masuk antimateri secara spesifik.
- **Persamaan Dirac & prediksi antimateri (1928)**: solusi energi negatif, interpretasi hole theory, kenapa persamaan ini "memaksa" keberadaan antipartikel secara matematis, bukan ditemukan lebih dulu secara eksperimental.
- **Penemuan eksperimental**: positron (Carl Anderson, 1932-1933, cloud chamber), antiproton (Segrè & Chamberlain, 1955, Bevatron) — sejarah & metode eksperimen aslinya.
- **Antipartikel di seluruh Model Standard**: charge conjugation (C), simetri CPT (pernyataan & implikasi, bukan pembuktian QFT penuh), antipartikel tiap jenis partikel (antiquark, antilepton, foton sebagai antipartikelnya sendiri).
- **Anihilasi & produksi pasangan**: kinematika relativistik anihilasi elektron-positron (kekekalan energi-momentum, kenapa minimal 2 foton), positronium (para- vs ortho-positronium, lifetime), pair production dari foton berenergi tinggi (syarat energi ambang, peran medan Coulomb inti).
- **Produksi & penyimpanan antimateri nyata**: fasilitas CERN Antiproton Decelerator (AD) & ELENA, cara antiproton diproduksi dari tumbukan proton energi tinggi, Penning trap & Penning-Malmberg trap, magnetic bottle, kenapa antimateri tidak bisa disimpan di wadah biasa (anihilasi instan dengan materi), eksperimen ALPHA (antihidrogen), AEgIS, BASE.
- **Gravitasi antimateri**: eksperimen ALPHA-g (Nature, 2023) yang mengonfirmasi antihidrogen jatuh ke bawah (bukan ke atas) di bawah gravitasi, kaitannya dengan weak equivalence principle.
- **Asimetri materi-antimateri & Big Bang**: masalah baryogenesis (kenapa alam semesta didominasi materi, bukan campuran setara materi-antimateri), tiga syarat Sakharov (pelanggaran baryon number, pelanggaran C & CP, departure from thermal equilibrium), eksperimen pelanggaran CP nyata (kaon netral — Cronin & Fitch 1964, B meson — BaBar/Belle, D meson — LHCb) — dinyatakan eksplisit sebagai riset terbuka, BUKAN masalah yang sudah terpecahkan penuh.
- **Aplikasi nyata**: PET scan (Positron Emission Tomography) di kedokteran nuklir, positron annihilation spectroscopy untuk deteksi cacat/vacancy material, dan pembahasan kuantitatif jujur soal batas propulsi/energi antimateri (biaya produksi riil vs klaim fiksi ilmiah).

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Quantum Field Theory penuh** (kalkulus diagram Feynman, renormalisasi, path integral) — domain ini memakai HASIL konseptual QFT secukupnya (mis. antipartikel sebagai solusi energi negatif, CPT theorem sebagai pernyataan), tidak menurunkannya dari formalisme QFT penuh. Itu di luar scope "software engineer belajar antimateri dari 0."
- **Derivasi penuh Relativitas Khusus/Umum** — sepenuhnya milik domain [[domains/relativity/DOMAIN.md|relativity]] yang sudah ada di repo ini (Paused, bisa dilanjutkan kapan saja). Domain ini hanya memakai hasil kinematika relativistik (four-momentum, E=mc²) secukupnya, dirujuk eksplisit sebagai prasyarat/paralel.
- **Fisika nuklir/reaktor** (fisi, fusi, fisika reaktor) — sepenuhnya milik domain [[domains/nuclear-technology/DOMAIN.md|nuclear-technology]] yang sudah ada di repo ini; domain ini TIDAK membahas reaktor/siklus bahan bakar nuklir sama sekali, meski sama-sama menyentuh fisika inti secukupnya untuk konteks berbeda (produksi antipartikel, bukan energi fisi/fusi).
- **Quantum computing** (qubit sebagai unit informasi, algoritma kuantum) — sepenuhnya milik domain [[domains/quantum-computing-for-engineers/DOMAIN.md|quantum-computing-for-engineers]]; QM yang dibangun di domain ini murni untuk memahami persamaan Dirac & antipartikel, tidak menyentuh sirkuit/algoritma kuantum.
- **Klaim propulsi/senjata/energi antimateri sebagai hal yang layak secara teknis hari ini** — antimateri PALING MAHAL yang pernah diproduksi manusia (skala nanogram/tahun di CERN, biaya diperkirakan triliunan dolar per gram); domain ini WAJIB menyatakan angka nyata ini setiap kali topik propulsi/senjata/energi antimateri muncul, dan menolak memberi kesan bahwa ini teknologi yang akan segera terwujud — lihat Domain-Specific Standards.
- **Kesetaraan gelar/riset PhD fisika partikel** — ini literasi teknis-mendalam + artefak yang bisa dibangun (derivasi + kalkulasi/simulasi), bukan pengganti riset PhD atau qualifying exam particle physics formal.

## Trusted Sources / Research Priority

1. **Particle Data Group (PDG)** — *Review of Particle Physics* (https://pdg.lbl.gov/) — sumber data partikel paling otoritatif di dunia (massa, lifetime, cabang peluruhan, konstanta fisika), diperbarui tahunan oleh kolaborasi internasional. Setiap angka kuantitatif tentang properti partikel di domain ini WAJIB bisa ditelusuri ke PDG.
2. **CERN resmi** (https://home.cern/, khususnya halaman Antimatter Factory https://home.cern/science/experiments/antimatter-factory dan fasilitas AD/ELENA) — sumber institusional untuk fasilitas produksi/penyimpanan antimateri nyata, dan halaman eksperimen ALPHA, AEgIS, BASE.
3. **David Griffiths**, *Introduction to Elementary Particles* (2nd ed., Wiley-VCH) — textbook standar undergraduate fisika partikel, mencakup persamaan Dirac, antipartikel, dan Model Standard dengan cara yang paling accessible untuk pembelajar mandiri — tulang punggung utama Module 3-8 domain ini.
4. **Francis Halzen & Alan Martin**, *Quarks and Leptons: An Introductory Course in Modern Particle Physics* (Wiley) — rigor lebih tinggi dari Griffiths, dipakai untuk cross-check derivasi & notasi, terutama simetri C/CPT.
5. **Donald Perkins**, *Particle Astrophysics* (2nd ed., Oxford University Press) — menjembatani fisika partikel dengan kosmologi, tulang punggung modul asimetri materi-antimateri/baryogenesis.
6. **P.A.M. Dirac** (1928), "The Quantum Theory of the Electron," *Proceedings of the Royal Society A*, Vol. 117 — sumber primer historis persamaan Dirac; dipakai untuk memahami logika asli prediksi antimateri, bukan sebagai referensi pengajaran utama (notasi modern jauh lebih jernih, lihat sumber 3-4).
7. **Carl D. Anderson** (1933), "The Positive Electron," *Physical Review*, Vol. 43 — sumber primer penemuan eksperimental positron.
8. **A.D. Sakharov** (1967), "Violation of CP Invariance, C Asymmetry, and Baryon Asymmetry of the Universe," *JETP Letters* — sumber primer tiga syarat Sakharov untuk baryogenesis.
9. **ALPHA Collaboration** (2023), "Observation of the effect of gravity on the motion of antimatter," *Nature* 621, 716–722 — sumber primer hasil eksperimen gravitasi antimateri terbaru, dipakai sebagai studi kasus utama Module 11.
10. **J.H. Christenson, J.W. Cronin, V.L. Fitch, R. Turlay** (1964), "Evidence for the 2π Decay of the K₂⁰ Meson," *Physical Review Letters* 13, 138 — sumber primer penemuan pelanggaran CP pertama, dasar kuantitatif Module 12.
11. **MIT OpenCourseWare 8.701 — Introduction to Nuclear and Particle Physics** (https://ocw.mit.edu/courses/8-701-introduction-to-nuclear-and-particle-physics-fall-2020/) — silabus & problem set resmi MIT, dipakai sebagai kerangka urutan modul dan latihan numerik terverifikasi.
12. **The Feynman Lectures on Physics** (gratis di https://www.feynmanlectures.caltech.edu/) — dipakai untuk membangun intuisi fisis di awal topik-topik berat (Dirac equation, antipartikel) sebelum masuk formalisme, konsisten dengan cara domain [[domains/relativity/DOMAIN.md|relativity]] memakainya.
13. **Sumber institusional untuk aplikasi nyata**: Society of Nuclear Medicine and Molecular Imaging (snmmi.org) untuk PET scan, dan jurnal *Journal of Physics: Condensed Matter*/artikel review positron annihilation spectroscopy untuk aplikasi material science — dipakai secukupnya di Module 13, selalu disilangkan ke sumber institusional, bukan artikel populer tanpa rujukan.

Klaim kuantitatif (massa/lifetime partikel, ambang energi pair production, hasil eksperimen CP violation) selalu disilangkan minimal ke PDG (1) DAN satu textbook (3-5) — tidak cukup dari satu sumber atau dari ingatan/asumsi tanpa verifikasi. Klaim status epistemis baryogenesis (terpecahkan vs riset terbuka) dan kelayakan propulsi/energi antimateri selalu dinyatakan eksplisit per house rule di bawah, tidak dibiarkan ambigu.

## Applied Practice Definition

**Derivation & Calculation Lab** — mengikuti pola domain [[domains/relativity/DOMAIN.md|relativity]] (derivasi tangan + artefak kode), dikombinasikan dengan pola literasi/case-study [[domains/nuclear-technology/DOMAIN.md|nuclear-technology]] untuk bagian teknologi/aplikasi (fasilitas produksi, penyimpanan, kedokteran nuklir) yang lebih berupa sintesis sumber institusional daripada derivasi murni:

1. **Derivasi/kalkulasi tangan** — menurunkan atau menghitung sendiri hasil kunci modul (mis. ambang energi pair production, kinematika anihilasi 2-foton, jumlah syarat Sakharov dan mengapa masing-masing perlu) dari prinsip dasar, termasuk limit-check & dimensional analysis.
2. **Skrip kecil (Python)** yang memvalidasi kalkulasi tangan secara numerik dan/atau memvisualisasikan data nyata dari PDG (mis. kalkulator ambang energi pair production untuk berbagai target, plot lifetime positronium para vs ortho, replikasi angka asimetri CP dari data BaBar/Belle/LHCb yang dipublikasikan) — memanfaatkan skill software engineering yang sudah dipunya learner di domain [[domains/software-engineering/DOMAIN.md|software-engineering]].
3. **Studi kasus tertulis** untuk bagian teknologi/aplikasi (fasilitas AD/ELENA, Penning trap, PET scan) — ringkasan analitis berbasis sumber institusional (CERN/PDG/jurnal medis), mengikuti pola Applied Practice domain [[domains/nuclear-technology/DOMAIN.md|nuclear-technology]], karena bagian ini soal cara kerja fasilitas nyata, bukan sesuatu yang bisa diderivasi dari prinsip dasar.

Setiap modul WAJIB menghasilkan minimal satu dari ketiga bentuk di atas (derivasi/kalkulasi tangan ATAU skrip kecil ATAU studi kasus tertulis, sesuai sifat topiknya) sebelum dianggap selesai, sesuai `# Hands-on First` di `CLAUDE.md` root.

## Project Tiers

- **Mini Project** (per modul): satu Derivation & Calculation Lab tunggal — kalkulasi/derivasi lengkap satu hasil kunci modul plus skrip kecil yang memvalidasinya, ATAU satu studi kasus tertulis untuk modul yang bersifat teknologi/aplikasi.
- **Intermediate Project** (setelah Module 7-8, kinematika anihilasi & produksi pasangan): **"Kalkulator Kinematika Antimateri"** — skrip Python yang menghitung ambang energi pair production untuk berbagai skenario (foton di medan Coulomb, tumbukan partikel energi tinggi) dan kinematika anihilasi elektron-positron (energi/momentum foton hasil), divalidasi terhadap nilai referensi PDG/textbook.
- **Capstone / Portfolio Project** (akhir course): pilih SATU dari tiga jalur — **(A) Antiparticle Physics Notebook** (derivasi penuh persamaan Dirac sampai prediksi antipartikel + kalkulator kinematika anihilasi/produksi pasangan tervalidasi), **(B) CERN Antimatter Facility Report** (studi kasus teknis-mendalam produksi & penyimpanan antiproton di AD/ELENA + eksperimen ALPHA/ALPHA-g, berbasis sumber institusional CERN & paper primer), atau **(C) Baryogenesis & CP Violation Research Paper** (analisis tertulis tiga syarat Sakharov + replikasi numerik data asimetri CP dari eksperimen nyata + pernyataan eksplisit status riset terbuka) — disimpan ke `portofolio/`, disertai penjelasan tertulis status epistemis tiap klaim besar (solid vs riset terbuka vs spekulatif).

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak di domain ini juga dicek lewat lensa berikut:

- **Derivational Rigor** — apakah tiap langkah derivasi/kalkulasi punya justifikasi eksplisit (prinsip/hukum kekekalan mana yang dipakai), tanpa lompatan aljabar tersembunyi? (sama seperti domain relativity)
- **Dimensional & Limit Consistency** — apakah satuan konsisten, dan apakah hasil kembali ke limit yang benar (mis. ambang energi pair production kembali ke rumus non-relativistik saat energi jauh di atas ambang, atau kinematika anihilasi konsisten dengan kekekalan energi-momentum penuh)?
- **Real-Data Grounding** — apakah angka yang dipakai/dihasilkan cocok dengan nilai referensi PDG/paper primer, bukan angka karangan yang "terlihat masuk akal"?
- **Epistemic Discipline** (KHAS domain ini untuk topik baryogenesis/propulsi antimateri) — apakah artefak membedakan secara eksplisit klaim yang solid & terverifikasi eksperimen (mis. keberadaan antipartikel, hasil ALPHA-g, pelanggaran CP terukur) dari klaim yang masih riset terbuka (mis. mekanisme baryogenesis penuh) atau spekulasi tidak realistis (mis. propulsi antimateri jadi layak dalam dekade mendekat)? Kegagalan membedakan ini adalah cacat serius, mirroring house rule serupa di [[domains/string-theory-for-engineers/DOMAIN.md|string-theory-for-engineers]] dan [[domains/astronomy-and-life-in-the-universe/DOMAIN.md|astronomy-and-life-in-the-universe]].

## Assessment Form

Concept-check singkat setelah tiap bagian teori (skenario "apa yang terjadi pada energi/momentum sistem ini saat elektron-positron beranihilasi" untuk menguji pemahaman, bukan hafalan rumus), problem set numerik terarah gaya MIT OCW 8.701 dengan kunci jawaban untuk self-check (kalkulasi ambang energi, lifetime, dsb dengan nilai PDG asli), sesi Socratic derivation (learner diminta menurunkan satu langkah kunci — mis. syarat kekekalan energi-momentum minimal 2 foton saat anihilasi — sebelum diberi tahu jawabannya, sesuai `# Challenge` di `CLAUDE.md` root), dan review studi kasus tertulis (untuk modul teknologi/aplikasi, dicek terhadap sumber institusional yang dirujuk).

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk mendekati topik yang sama:

- **Fisikawan teoretis akademik** (gaya Griffiths/Halzen & Martin) — rigor matematis derivasi dari prinsip dasar (persamaan Dirac, simetri C/CPT), nyaman dengan formalisme — lensa dominan untuk modul Dirac equation & Model Standard.
- **Fisikawan eksperimental akselerator** (gaya kolaborasi ALPHA/AEgIS/BASE di CERN) — fokus pada "bagaimana ini benar-benar diproduksi/disimpan/diukur," presisi instrumentasi, tantangan rekayasa (vacuum, medan magnet, cryogenics) — lensa dominan untuk modul produksi & penyimpanan antimateri.
- **Kosmolog/fisikawan partikel-astropartikel** (gaya Perkins, kolaborasi BaBar/Belle/LHCb) — fokus pada implikasi kosmologis (kenapa alam semesta asimetris), pelanggaran CP sebagai bukti eksperimental — lensa dominan untuk modul baryogenesis.
- **Praktisi kedokteran nuklir/material science** (gaya SNMMI, peneliti positron annihilation spectroscopy) — antimateri sebagai alat diagnostik/pengukuran nyata yang dipakai sehari-hari, bukan objek kontemplasi teoretis — lensa untuk modul aplikasi.
- **Pengajar/komunikator sains** (gaya Feynman) — cara membangun intuisi fisis sebelum formalisme, dipakai terutama di modul-modul awal ("dari 0").

Kelima lensa ini sering menekankan hal berbeda untuk topik yang sama (mis. antihidrogen: fisikawan teoretis peduli konsistensi CPT, eksperimentalis peduli cara menjebaknya cukup lama untuk diukur, kosmolog bertanya apa implikasinya untuk asimetri alam semesta) — domain ini melatih learner mengenali trade-off/penekanan tersebut secara sadar.

## Domain-Specific Standards

`domains/antimatter/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Tidak ada teori tanpa kalkulasi/derivasi/studi kasus tervalidasi.** Konsisten dengan Applied Practice Definition di atas — modul yang tidak berakhir dengan salah satu dari tiga bentuk artefak (derivasi tangan, skrip validasi, studi kasus tertulis) dianggap belum selesai.
- **Batas relativitas & QFT dinyatakan eksplisit, diarahkan ke domain relativity, bukan diulang.** Setiap kali topik butuh derivasi relativitas penuh atau formalisme QFT (diagram Feynman, renormalisasi) di luar hasil yang dipakai langsung, mentor menyatakan eksplisit itu di luar scope domain ini — mirroring house rule serupa di `domains/string-theory-for-engineers/DOMAIN.md` dan `domains/physics-for-software-engineers/DOMAIN.md`.
- **Batas fisika nuklir dinyatakan eksplisit, diarahkan ke domain nuclear-technology.** Domain ini TIDAK membahas fisi/fusi/fisika reaktor sama sekali — kalau pertanyaan menyentuh area itu, mentor mengarahkan eksplisit ke `domains/nuclear-technology/DOMAIN.md`.
- **Angka realistis wajib untuk klaim propulsi/senjata/energi antimateri.** Setiap kali topik menyentuh potensi antimateri sebagai sumber energi/propulsi/senjata, mentor WAJIB menyertakan angka nyata (skala produksi CERN saat ini: nanogram/tahun; estimasi biaya: triliunan dolar per gram, per NASA/CERN) dan menyatakan eksplisit ini jauh dari kelayakan teknis — tidak pernah membiarkan kesan "teknologi masa depan yang dekat" tanpa konteks kuantitatif, mengikuti semangat house rule "No Uncritical Boosterism" di `domains/nuclear-technology/DOMAIN.md`.
- **Baryogenesis dinyatakan sebagai riset terbuka, bukan solved problem.** Tiga syarat Sakharov adalah kondisi NECESSARY yang didukung teori, tapi mekanisme lengkap yang menjelaskan skala asimetri materi-antimateri yang teramati MASIH topik riset aktif per 2026 — mentor tidak pernah menyajikan ini sebagai "sudah terjawab", konsisten dengan lensa Review Style "Epistemic Discipline" di atas.
- **Sumber data partikel wajib PDG-cross-checked.** Setiap angka properti partikel (massa, lifetime, branching ratio) yang dipakai dalam lesson/problem set wajib bisa ditelusuri ke Particle Data Group (Trusted Sources 1), tidak cukup dari ingatan/textbook lama yang mungkin sudah diperbarui.

## Domain Goal

Learner mampu memahami dan menurunkan sendiri hasil-hasil kunci fisika antimateri dari prinsip dasar (persamaan Dirac dan kenapa ia memprediksi antipartikel, kinematika anihilasi & produksi pasangan) — TIDAK menghafal rumus — sekaligus paham bagaimana antimateri benar-benar diproduksi, disimpan, dan diukur di eksperimen nyata (CERN AD/ELENA, ALPHA/ALPHA-g), memahami masalah asimetri materi-antimateri kosmologis (syarat Sakharov, bukti pelanggaran CP) sebagai riset terbuka yang jujur secara epistemis, dan mengenal aplikasi nyata antimateri hari ini (PET scan, positron annihilation spectroscopy) DIBANDING klaim spekulatif (propulsi/senjata) yang dinilai secara kuantitatif jujur — dibuktikan lewat capstone project di portofolio yang mengintegrasikan derivasi/kalkulasi + validasi kode/data nyata + sintesis tertulis berbasis sumber institusional. Ini eksplisit BUKAN kredensial fisika partikel formal — murni kemampuan memahami & membangun artefak dari fisika antimateri secara teknis-jujur, memakai latar belakang software engineering learner sebagai jembatan.

## Registration History

Domain ini didraft 2026-08-16 atas permintaan langsung user: "Buat course Antimatter 0–expert: fisika partikel, Standard Model, antipartikel, positron, antiproton, annihilation, produksi, penyimpanan, antimatter gravity, Big Bang, asymmetry, dan aplikasi." Sebelum membuat domain baru, domain fisika existing dicek untuk overlap: `domains/nuclear-technology/DOMAIN.md` (fisika nuklir/reaktor, bukan fisika partikel/antimateri), `domains/relativity/DOMAIN.md` (relativitas murni, secara eksplisit mengarahkan topik di luar scope-nya ke domain terpisah), `domains/astronomy-and-life-in-the-universe/DOMAIN.md` (kosmologi deskriptif/astrobiologi, tidak menyentuh fisika partikel/baryogenesis secara teknis), dan `domains/quantum-computing-for-engineers/DOMAIN.md` (QM untuk komputasi kuantum, bukan fisika partikel). Tidak ada satu pun yang cocok tanpa melanggar batas scope eksplisit yang sudah didokumentasikan sendiri oleh masing-masing domain, sehingga domain baru `antimatter` dibuat — bukan duplikasi. Dibuat sambil Course Aktif "Character Development" masih di bawah 50% Module 1/Lesson 1.1 — user eksplisit konfirmasi override guard `ai-los/COURSE_CREATION.md` saat ditanya. Registered paused mengikuti kebijakan hemat-token 2026-08-07; roadmap dibangun bersamaan atas permintaan user, mengikuti pola yang sama dengan domain fisika lain di repo ini.
