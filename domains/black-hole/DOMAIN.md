# Black Hole — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Black Hole (Fisika Lubang Hitam) — dari geometri Schwarzschild & Kerr sampai termodinamika black hole, radiasi Hawking, information paradox, astrofisika akresi, bukti observasional (EHT, LIGO), dan black hole cosmology.

## Scope

Fisika lubang hitam secara teknis-mendalam sebagai spesialisasi TERPISAH dari relativitas umum secara luas — domain ini mengasumsikan fondasi GR (metrik, geodesic, persamaan medan Einstein, derivasi dasar metrik Schwarzschild) sebagai PRASYARAT yang sudah dimiliki (via `domains/relativity/DOMAIN.md`) atau di-bridge singkat, lalu masuk jauh lebih dalam khusus ke fisika black hole itu sendiri:

- **Prasyarat cepat**: gravitasi Newton (escape velocity, konsep "dark star" Michell/Laplace 1783/1796 sebagai antisipasi historis black hole sebelum GR), bridge SR/GR secukupnya (prinsip ekivalensi, metrik, geodesic) — merujuk ke `domains/relativity/DOMAIN.md` untuk derivasi penuh, BUKAN mengulanginya.
- **Struktur kausal Schwarzschild secara penuh**: event horizon, singularitas, koordinat Schwarzschild vs Eddington-Finkelstein vs Kruskal-Szekeres, Penrose diagram LENGKAP (bukan cuma pengantar seperti di `relativity`), maximal extension & Einstein-Rosen bridge (wormhole non-traversable) — ini secara eksplisit LEBIH DALAM daripada cakupan Module 11 `courses/relativity/roadmap.md`.
- **Fenomenologi kuantitatif di sekitar black hole non-rotasi**: photon sphere, ISCO (innermost stable circular orbit), gravitational redshift ekstrem mendekati horizon.
- **Kerr black hole (rotasi)**: metrik Kerr, ergosphere, frame-dragging (Lense-Thirring), Penrose process & ekstraksi energi rotasi — TIDAK dibahas sama sekali di domain `relativity`.
- **Black hole mechanics & termodinamika**: area theorem (Hawking 1971), empat hukum black hole mechanics (Bardeen-Carter-Hawking 1973), entropi Bekenstein-Hawking, suhu Hawking, analogi formal ke termodinamika klasik.
- **Quantum field theory di curved spacetime secukupnya**: particle creation di spacetime melengkung, Unruh effect — HANYA sebanyak yang dibutuhkan untuk memahami derivasi radiasi Hawking, BUKAN QFT penuh (mirroring cara `domains/antimatter/DOMAIN.md` membangun QM secukupnya untuk persamaan Dirac).
- **Radiasi Hawking & evaporasi black hole**: derivasi semiklasik (Hawking 1974/1975), black hole sebagai objek termodinamika sungguhan, skala waktu evaporasi.
- **Information paradox**: unitaritas mekanika kuantum vs radiasi termal Hawking, no-hair theorem, black hole complementarity, firewall paradox (AMPS 2013), status riset per 2026 — dinyatakan eksplisit sebagai OPEN PROBLEM, bukan solved.
- **Astrofisika akresi & black hole nyata**: accretion disk, Eddington luminosity, relativistic jets, quasar/AGN, X-ray binary sebagai cara black hole stellar-mass terdeteksi secara tidak langsung.
- **Bukti observasional**: Event Horizon Telescope (M87*, Sgr A*), deteksi merger black hole biner LIGO/Virgo (bridge singkat ke `relativity` Module 12 untuk fisika gelombang gravitasi, fokus domain ini pada apa yang dipelajari SPESIFIK tentang black hole dari sinyal itu — ringdown, no-hair theorem test), gravitational lensing oleh black hole.
- **Black hole cosmology**: primordial black holes, status black hole sebagai kandidat dark matter, black hole dalam konteks FLRW/ekspansi alam semesta, status spekulatif white hole.

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Derivasi penuh Relativitas Khusus/Umum dari nol** (postulat Einstein, transformasi Lorentz, kalkulus tensor, persamaan medan Einstein, derivasi dasar Schwarzschild) — sepenuhnya milik domain [[domains/relativity/DOMAIN.md|relativity]] yang sudah ada di repo ini. Domain ini mengasumsikan itu sudah dikuasai atau di-bridge singkat di Module 2, TIDAK diturunkan ulang secara lengkap.
- **Astronomi deskriptif umum** (Tata Surya, klasifikasi bintang, evolusi bintang sebelum jadi black hole, kosmologi FLRW dasar) — sepenuhnya milik domain [[domains/astronomy-and-life-in-the-universe/DOMAIN.md|astronomy-and-life-in-the-universe]], yang menyebut black hole hanya di level konseptual (Module 2.3 course itu eksplisit menyatakan "tanpa masuk GR penuh, itu domain relativity"). Domain ini adalah pemenuhan mendalam dari black hole secara spesifik, bukan duplikasi astronomi umum.
- **Quantum Field Theory penuh & quantum gravity** (renormalisasi, path integral penuh, loop quantum gravity, string theory sebagai calon teori quantum gravity) — dipakai/disebut secukupnya sebagai konteks "ke mana arah riset lanjut" untuk information paradox, TIDAK didalami; QFT-in-curved-spacetime yang dibangun di Module 8 course ini HANYA sebanyak yang dibutuhkan untuk Hawking radiation, bukan formalisme QFT umum.
- **String theory / holographic principle / AdS-CFT secara teknis** — disebut sebagai salah satu arah riset information paradox (Maldacena 1997 sebagai rujukan nama), TIDAK diturunkan; kalau learner butuh kedalaman string theory sungguhan, diarahkan eksplisit ke `domains/string-theory-for-engineers/DOMAIN.md`.
- **Klaim black hole sebagai portal/time travel/wormhole traversable yang layak secara fisis** — Einstein-Rosen bridge dari metrik Schwarzschild BUKAN traversable (non-traversable, tertutup sebelum apa pun bisa melintas); domain ini WAJIB menyatakan ini eksplisit setiap kali topik wormhole/time travel muncul, tidak membiarkan kesan fiksi ilmiah dianggap fisika solid.
- **Kesetaraan gelar/riset PhD relativitas/astrofisika teoretis** — ini literasi teknis-mendalam + artefak yang bisa dibangun (derivasi + kalkulasi/simulasi), bukan pengganti riset PhD atau qualifying exam gravitational physics formal.

## Trusted Sources / Research Priority

1. **Sean Carroll**, *Spacetime and Geometry: An Introduction to General Relativity* (buku + lecture notes gr-qc/9712019 tersedia gratis) — bab black hole (Schwarzschild causal structure, Kruskal-Szekeres, Penrose diagram, black hole thermodynamics) jadi tulang punggung utama Module 3, 6, 7 domain ini; sama seperti dipakai `domains/relativity/DOMAIN.md` untuk cross-check notasi.
2. **MIT OpenCourseWare 8.962 — General Relativity** (https://ocw.mit.edu/courses/8-962-general-relativity-spring-2020/) — problem set & lecture notes resmi untuk Schwarzschild/Kerr geometry dan black hole mechanics, dipakai sebagai kerangka urutan Module 3-6.
3. **Robert Wald**, *General Relativity* (University of Chicago Press) — referensi rigor lebih tinggi untuk black hole mechanics (area theorem, four laws) dan bab QFT in curved spacetime — tulang punggung Module 6 & 8.
4. **Kip Thorne**, *Black Holes and Time Warps: Einstein's Outrageous Legacy* — narasi historis-teknis yang ditulis fisikawan yang benar-benar mengerjakan GR (bukan pop-sci murni), dipakai untuk konteks sejarah (Michell/Laplace, Schwarzschild 1916, Oppenheimer-Snyder, Kerr 1963) dan intuisi fisis di modul-modul awal.
5. **S.W. Hawking** (1975), "Particle Creation by Black Holes," *Communications in Mathematical Physics* 43, 199–220 — sumber primer derivasi radiasi Hawking, dasar kuantitatif Module 9.
6. **J.M. Bardeen, B. Carter, S.W. Hawking** (1973), "The Four Laws of Black Hole Mechanics," *Communications in Mathematical Physics* 31, 161–170 — sumber primer empat hukum black hole mechanics, dasar Module 6.
7. **J.D. Bekenstein** (1973), "Black Holes and Entropy," *Physical Review D* 7, 2333 — sumber primer entropi black hole, dasar Module 7.
8. **R.P. Kerr** (1963), "Gravitational Field of a Spinning Mass as an Example of Algebraically Special Metrics," *Physical Review Letters* 11, 237 — sumber primer historis metrik Kerr, dasar Module 5.
9. **Leonard Susskind & James Lindesay**, *An Introduction to Black Holes, Information and the String Theory Revolution* (World Scientific) — buku yang secara eksplisit ditulis untuk pembelajar dengan latar belakang fisika terbatas menuju information paradox, cocok untuk kalibrasi audiens domain ini — tulang punggung Module 10.
10. **A. Almheiri, D. Marolf, J. Polchinski, J. Sully** (2013), "Black Holes: Complementarity or Firewalls?," *Journal of High Energy Physics* — sumber primer firewall paradox, dipakai Module 10 sebagai contoh riset terbuka nyata.
11. **John Preskill**, Caltech lecture notes "Black Holes and Information" (tersedia gratis di situs pribadi/course Ph219) — pengantar teknis information paradox yang accessible, cross-check untuk Module 10.
12. **Event Horizon Telescope Collaboration** (2019), "First M87 Event Horizon Telescope Results," *The Astrophysical Journal Letters* 875, L1, dan (2022) hasil Sgr A* — sumber primer bukti observasional bayangan black hole, dasar Module 12.
13. **LIGO Scientific Collaboration** (Abbott et al. 2016), "Observation of Gravitational Waves from a Binary Black Hole Merger," *Physical Review Letters* 116, 061102 — sumber primer sudah dipakai `domains/relativity/DOMAIN.md`; domain ini merujuknya khusus untuk bagian ringdown/no-hair theorem test, bukan mengulang seluruh fisika gelombang gravitasi.
14. **NASA/Chandra X-ray Observatory & ESA** halaman edukasi resmi (chandra.harvard.edu, esa.int) — sumber institusional untuk accretion disk, X-ray binary, quasar/AGN di Module 11.
15. **R. Penrose** (1965), "Gravitational Collapse and Space-Time Singularities," *Physical Review Letters* 14, 57 — sumber primer teorema singularitas, HASIL dikutip (bukan dibuktikan penuh — bukti topological penuh di luar scope).

Klaim kuantitatif (massa/spin black hole hasil observasi, suhu/entropi Hawking, skala waktu evaporasi) selalu disilangkan minimal ke satu sumber primer (5-13) DAN satu textbook (1-3) — tidak cukup dari satu sumber atau dari ingatan/asumsi tanpa verifikasi. Status information paradox selalu dinyatakan eksplisit sebagai riset terbuka per house rule di bawah.

## Applied Practice Definition

**Derivation & Simulation Lab**, pola sama persis dengan `domains/relativity/DOMAIN.md` dan `domains/antimatter/DOMAIN.md` (konsisten karena domain ini adalah spesialisasi langsung dari relativity):

1. **Derivasi tangan** — menurunkan/menuliskan sendiri langkah kunci tiap modul (mis. photon sphere dari kondisi null geodesic circular, empat hukum black hole mechanics dari area theorem, argumen semiklasik radiasi Hawking) dari prinsip dasar, termasuk limit-check (mis. Kerr metric harus kembali ke Schwarzschild saat spin a→0) dan dimensional analysis.
2. **Skrip/kode kecil (Python direkomendasikan)** yang memvalidasi derivasi secara numerik dan/atau memvisualisasikan (mis. plotter Penrose diagram, kalkulator ISCO/photon sphere untuk berbagai massa & spin, estimator suhu Hawking & waktu evaporasi untuk black hole stellar-mass vs primordial, integrator geodesic di sekitar Kerr untuk memvisualisasikan frame-dragging).
3. **Studi kasus tertulis berbasis sumber primer/institusional** untuk bagian observasional/astrofisika (EHT, LIGO ringdown, accretion disk) — mengikuti pola Applied Practice `domains/astronomy-and-life-in-the-universe/DOMAIN.md` dan `domains/antimatter/DOMAIN.md` untuk topik yang lebih bersifat sintesis data nyata daripada derivasi murni.

Setiap modul WAJIB menghasilkan minimal satu dari ketiga bentuk di atas sebelum dianggap selesai, sesuai `# Hands-on First` di `CLAUDE.md` root.

## Project Tiers

- **Mini Project** (per modul): satu Derivation & Simulation Lab tunggal — derivasi/kalkulasi lengkap satu hasil kunci modul plus skrip kecil yang memvalidasi/memvisualisasikannya, ATAU satu studi kasus tertulis untuk modul observasional/astrofisika.
- **Intermediate Project** (setelah Module 6-7, black hole mechanics & termodinamika): **"Black Hole Property Calculator"** — skrip Python yang menghitung seluruh properti kunci black hole (radius Schwarzschild, ISCO, photon sphere, ergosphere untuk Kerr, entropi Bekenstein-Hawking, suhu Hawking, waktu evaporasi) dari input massa (dan spin, opsional) tunggal, divalidasi terhadap nilai referensi textbook/paper untuk kasus Sagittarius A* dan M87*.
- **Capstone / Portfolio Project** (akhir course): pilih SATU dari tiga jalur — **(A) Black Hole Geometry & Kerr Notebook** (derivasi penuh struktur kausal Schwarzschild + Penrose diagram, plus derivasi Kerr/ergosphere/Penrose process, dengan visualisasi kode tervalidasi), **(B) Hawking Radiation & Information Paradox Research Paper** (derivasi semiklasik radiasi Hawking dari nol + analisis tertulis mendalam information paradox dengan status epistemis eksplisit tiap posisi/proposal riset), atau **(C) Observational Black Hole Report** (studi kasus teknis-mendalam EHT M87*/Sgr A* + LIGO ringdown/no-hair test + accretion disk astrofisika, berbasis sumber primer) — disimpan ke `portofolio/`, disertai penjelasan tertulis status epistemis eksplisit tiap klaim besar.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak di domain ini juga dicek lewat lensa berikut (identik dengan `domains/relativity/DOMAIN.md`, ditambah satu lensa khas domain ini):

- **Derivational Rigor** — apakah tiap langkah derivasi punya justifikasi eksplisit (prinsip/hukum mana yang dipakai), tanpa lompatan aljabar tersembunyi?
- **Dimensional & Limit Consistency** — apakah satuan konsisten, dan apakah hasil kembali ke limit yang benar (mis. Kerr → Schwarzschild saat a→0, radiasi Hawking → nol saat ħ→0 secara formal)?
- **Conceptual vs Rote** — apakah learner bisa menjelaskan MENGAPA hasil itu masuk akal secara fisis, bukan cuma mengutak-atik aljabar sampai ketemu jawaban buku?
- **Simulation Fidelity** — apakah kode benar-benar mengimplementasikan fisika yang diturunkan (bukan angka hardcode), hasil numerik dibandingkan ke nilai referensi yang diketahui (mis. massa/spin M87* dari paper EHT asli)?
- **Epistemic Discipline** (KHAS domain ini, terutama untuk information paradox & wormhole) — apakah artefak membedakan secara eksplisit klaim matematis solid (mis. keberadaan event horizon, empat hukum black hole mechanics, radiasi Hawking sebagai prediksi teoretis solid) dari klaim yang masih riset terbuka (mis. resolusi information paradox, firewall vs complementarity) atau spekulasi tidak didukung fisika solid (mis. wormhole traversable, black hole sebagai portal)? Mirroring house rule serupa di `domains/antimatter/DOMAIN.md` dan `domains/string-theory-for-engineers/DOMAIN.md`.

## Assessment Form

Concept-check singkat setelah tiap bagian teori (skenario "apa yang terjadi pada observer yang jatuh ke black hole vs observer jauh" untuk menguji pemahaman struktur kausal, bukan hafalan rumus), problem set numerik terarah gaya MIT OCW 8.962 dengan kunci jawaban untuk self-check, sesi Socratic derivation (learner diminta menurunkan satu langkah kunci — mis. kenapa ISCO ada di r=6M untuk Schwarzschild — sebelum diberi tahu jawabannya, sesuai `# Challenge` di `CLAUDE.md` root), dan review studi kasus tertulis untuk modul observasional (dicek terhadap sumber primer EHT/LIGO yang dirujuk).

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk mendekati topik yang sama:

- **Fisikawan teoretis GR akademik** (gaya Carroll/Wald/MTW) — rigor matematis derivasi dari prinsip dasar (struktur kausal, black hole mechanics), nyaman dengan formalisme tensor — lensa dominan Module 3-7.
- **Fisikawan teoretis quantum gravity/high-energy** (gaya Hawking/Susskind/Preskill/Maldacena) — fokus pada perpotongan GR dan QM, information paradox sebagai masalah fundamental fisika teoretis — lensa dominan Module 8-10.
- **Astrofisikawan observasional** (gaya kolaborasi EHT/LIGO) — fokus pada "bagaimana ini benar-benar diukur," presisi instrumentasi, interpretasi data mentah jadi klaim fisis — lensa dominan Module 11-12.
- **Kosmolog** (gaya penelitian primordial black hole/dark matter candidate) — fokus implikasi black hole untuk sejarah & komposisi alam semesta — lensa dominan Module 13.
- **Pengajar/komunikator sains** (gaya Thorne/Feynman) — cara membangun intuisi fisis sebelum formalisme, dipakai terutama modul-modul awal ("dari 0").

## Domain-Specific Standards

`domains/black-hole/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Tidak ada teori tanpa derivasi/kalkulasi/studi kasus tervalidasi.** Konsisten dengan Applied Practice Definition di atas — modul yang tidak berakhir dengan salah satu dari tiga bentuk artefak dianggap belum selesai.
- **Batas GR dasar dinyatakan eksplisit, diarahkan ke domain relativity, bukan diulang.** Setiap kali topik butuh derivasi GR penuh dari nol (postulat, tensor, persamaan medan Einstein) di luar bridge Module 2, mentor menyatakan eksplisit itu di luar scope domain ini dan mengarahkan ke `domains/relativity/DOMAIN.md` — mirroring house rule `domains/string-theory-for-engineers/DOMAIN.md` & `domains/antimatter/DOMAIN.md`.
- **Wormhole/time travel selalu dikoreksi eksplisit.** Einstein-Rosen bridge BUKAN traversable — setiap kali topik wormhole/time travel via black hole muncul, mentor WAJIB menyatakan status fisisnya (non-traversable dalam solusi vakum standar, traversable wormhole butuh exotic matter yang belum pernah diamati) secara eksplisit, tidak dibiarkan berkesan seperti fiksi ilmiah adalah fisika solid.
- **Information paradox dinyatakan sebagai riset terbuka, bukan solved problem.** Per 2026, tidak ada konsensus tunggal (firewall vs complementarity vs soft hair vs island formula/holography) — mentor tidak pernah menyajikan satu posisi sebagai "jawaban final," konsisten dengan lensa Review Style "Epistemic Discipline" di atas.
- **Sumber data observasional wajib primary-source-checked.** Setiap angka properti black hole nyata (massa Sgr A*/M87*, spin, parameter merger LIGO) yang dipakai dalam lesson/problem set wajib bisa ditelusuri ke paper primer EHT/LIGO Collaboration (Trusted Sources 12-13), tidak cukup dari artikel populer tanpa rujukan.

## Domain Goal

Learner mampu memahami dan menurunkan sendiri geometri & mekanika black hole (Schwarzschild penuh — struktur kausal, Kruskal-Szekeres, Penrose diagram; Kerr — ergosphere, frame-dragging, Penrose process; empat hukum black hole mechanics) dari prinsip GR yang sudah dikuasai (via domain `relativity`), memahami radiasi Hawking sebagai hasil semiklasik solid sekaligus information paradox sebagai masalah riset terbuka yang jujur secara epistemis, dan mampu menghubungkan seluruh teori itu ke bukti observasional nyata (EHT, LIGO) dan astrofisika akresi black hole sungguhan — dibuktikan lewat capstone project di portofolio yang mengintegrasikan derivasi/kalkulasi + validasi kode/data nyata + sintesis tertulis berbasis sumber primer. Ini eksplisit BUKAN kredensial gravitational physics/astrofisika formal — murni kemampuan memahami & membangun artefak dari fisika black hole secara teknis-jujur, memakai latar belakang software engineering learner sebagai jembatan.

## Registration History

Domain ini didraft 2026-08-17 sebagai background agent task, atas permintaan langsung user untuk course "Black Hole — dari 0 sampai Expert" mencakup gravitasi Newton, relativitas khusus & umum, spacetime, event horizon, Schwarzschild radius, singularity, Kerr black hole, accretion disk, Hawking radiation, information paradox, dan black hole cosmology — untuk audiens Java/Spring Boot backend engineer belajar sebagai minat sains sampingan (bukan mahasiswa fisika), mirip semangat `courses/string-theory-for-engineers` dan `courses/quantum-computing-for-engineers`.

Sebelum membuat domain baru, domain fisika existing dicek eksplisit untuk overlap: `domains/relativity/DOMAIN.md` sudah mencakup derivasi dasar metrik Schwarzschild, event horizon, singularitas, dan pengantar Penrose diagram (Module 11 course-nya) — TAPI secara eksplisit hanya level "pengantar", TIDAK mencakup Kerr, black hole thermodynamics, Hawking radiation, atau information paradox sama sekali (topik-topik itu justru disebut eksplisit di `domains/relativity/DOMAIN.md` bagian Scope sebagai "arah riset lanjut... butuh domain terpisah di masa depan kalau dibutuhkan" — kutipan yang sama persis dipakai `domains/string-theory-for-engineers/DOMAIN.md` untuk justifikasi dirinya sendiri). `domains/astronomy-and-life-in-the-universe/DOMAIN.md` menyebut black hole hanya di level konseptual deskriptif (Module 2.3: "neutron star & black hole — konsep dasar, tanpa masuk GR penuh, itu domain relativity") — tidak menyentuh fisika teknis sama sekali. `domains/string-theory-for-engineers/DOMAIN.md` membangun QM+GR untuk tujuan berbeda (kuantisasi string), menyebut black hole hanya lewat referensi tidak langsung (D-brane, holografi disebut sepintas di M-theory), tidak membangun black hole physics sebagai topik utuh.

Kesimpulan: tidak ada domain existing yang mencakup black hole secara mendalam tanpa melanggar batas scope yang sudah didokumentasikan sendiri oleh masing-masing domain — domain baru `black-hole` dibuat sebagai SPESIALISASI LANGSUNG dari `relativity` (bukan duplikasi; hubungannya prasyarat, persis pola `domains/string-theory-for-engineers/DOMAIN.md` terhadap `domains/relativity/DOMAIN.md`), mengisi eksplisit celah yang sudah diantisipasi domain `relativity` sendiri. Dibuat sambil Course Aktif "Character Development" masih di bawah 50% Module 1/Lesson 1.1 — user eksplisit konfirmasi override guard `ai-los/COURSE_CREATION.md` untuk task ini. Registered paused mengikuti kebijakan hemat-token 2026-08-07; roadmap dibangun bersamaan atas permintaan user, mengikuti pola yang sama dengan domain fisika lain di repo ini (`antimatter`, `string-theory-for-engineers`, `quantum-computing-for-engineers`).
