# String Theory untuk Software Engineer — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

String Theory untuk Software Engineer (String Theory for Software Engineers) — quantum mechanics dasar, general relativity secukupnya (sebagai jembatan, bukan pengulangan), dinamika string relativistik, kuantisasi, extra dimensions, compactification (Calabi-Yau), D-branes, dualitas (T-duality, S-duality, mirror symmetry), dan matematika pendukungnya (geometri diferensial, complex manifold, teori grup) — semuanya dijembatani ke kode: simulasi & visualisasi yang bisa dijalankan dan diperiksa.

## Scope

Domain ini mengajarkan string theory secara teknis-nyata (bukan pop-sci) sampai level cukup untuk membaca Zwiebach dan lecture notes David Tong secara mandiri, dan memahami paper pengantar seperti Candelas-de la Ossa-Green-Parkes (1991) tentang mirror symmetry. Cakupan:

- **Fondasi Quantum Mechanics**: state vector, operator, prinsip ketidakpastian, entanglement dasar, osilator harmonik kuantum (fondasi WAJIB untuk kuantisasi string nanti — tidak ada domain lain di repo ini yang mengajarkan QM, jadi domain ini yang membangunnya dari nol).
- **General Relativity secukupnya (bridge, bukan pengulangan)**: prinsip ekivalensi, metrik, kelengkungan, geodesic — HANYA untuk memahami klaim "graviton = mode osilasi string tertutup" dan target-space geometry di compactification. Derivasi penuh persamaan medan Einstein, metrik Schwarzschild, gelombang gravitasi, dll TIDAK diulang di sini — itu domain [[domains/relativity/DOMAIN.md|relativity]] yang sudah ada, dirujuk sebagai prasyarat/paralel.
- **Dinamika string klasik & kuantisasi**: aksi Nambu-Goto & Polyakov, worldsheet vs target space, open vs closed string, light-cone gauge, spektrum massa, dimensi kritis (D=26 bosonic, D=10 superstring), masalah tachyon.
- **Extra dimensions & compactification**: ide Kaluza-Klein, compactification pada lingkaran, KK tower, kenapa dimensi ekstra harus kecil/compact.
- **Dualitas**: T-duality (winding vs momentum mode, R ↔ α'/R), S-duality (strong-weak coupling), web dualitas lima teori superstring, M-theory sebagai payung 11 dimensi.
- **D-branes**: syarat batas Dirichlet, brane sebagai objek dinamis, medan gauge pada brane, tegangan brane.
- **Compactification Calabi-Yau & mirror symmetry**: manifold kompleks, Kähler geometry, holonomy, syarat Ricci-flat untuk N=1 SUSY di 4D, moduli, mirror pair, mirror symmetry sebagai kasus khusus T-duality (SYZ conjecture).
- **Matematika pendukung, diajarkan just-in-time** (bukan silabus matematika murni terpisah di awal): geometri diferensial (manifold, metrik, koneksi — dibangun ulang singkat dari math bridge domain relativity), complex analysis secukupnya untuk complex manifold, teori grup/Lie algebra secukupnya untuk gauge symmetry (E8×E8, SO(32)).
- **Komputasi/simulasi**, sebagai wujud nyata "hands-on" untuk software engineer: simulasi osilasi string relativistik (mode Fourier di worldsheet), visualisasi compactification & spektrum KK, demonstrasi numerik T-duality (spektrum R vs α'/R match), visualisasi proyeksi Calabi-Yau (gaya Hanson/quintic) — Python (NumPy/SciPy/SymPy/Matplotlib) sebagai tool praktis.

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Derivasi penuh General Relativity** (persamaan medan Einstein, metrik Schwarzschild, gelombang gravitasi, kosmologi) — sepenuhnya milik domain [[domains/relativity/DOMAIN.md|relativity]] yang sudah ada di repo ini (Paused, bisa dilanjutkan kapan saja). Domain ini hanya memakai HASIL GR secukupnya sebagai bahasa target-space, dirujuk eksplisit sebagai prasyarat/paralel, bukan diturunkan ulang.
- **Mekanika klasik/Newtonian dari nol** — domain [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]] yang sudah ada di repo ini mencakup itu (physics engine, ray tracing, robotika); domain string theory ini TIDAK bersinggungan dengan scope itu sama sekali (physics-for-software-engineers eksplisit mengecualikan relativitas DAN quantum mechanics — dua hal yang justru jadi fondasi wajib domain ini, jadi kedua domain sengaja dipisah, bukan digabung).
- **Quantum Field Theory penuh & AdS/CFT** — disebut sebagai "ke mana arah lanjut" di penutup course (MIT 8.821/McGreevy), tapi tidak didalami; itu butuh domain terpisah di masa depan kalau dibutuhkan.
- **Bukti matematis penuh teorema eksistensi Calabi-Yau (Calabi conjecture, dibuktikan Yau 1977)** — dipakai/dikutip hasilnya (Ricci-flat compact Kähler manifold ada & unik dalam kelas Kähler tertentu), TIDAK diturunkan dari nol — itu differential geometry murni tingkat riset, di luar scope "software engineer belajar string theory."
- **Klaim string theory sebagai fakta fisis terverifikasi** — string theory PER 2026 belum punya konfirmasi eksperimental langsung (beda dengan GR yang sudah dikonfirmasi GPS/LIGO/Eddington). Domain ini WAJIB membedakan eksplisit hasil matematis yang solid (mis. konsistensi D=26/D=10, T-duality) dari klaim fisis spekulatif (mis. "string theory adalah the theory of everything", landscape/multiverse) — lihat Domain-Specific Standards.
- **Kesetaraan gelar/riset PhD string theory** — ini literasi teknis-mendalam + artefak yang bisa dibangun (derivasi + simulasi), bukan pengganti riset PhD atau qualifying exam string theory formal.

## Trusted Sources / Research Priority

1. **Barton Zwiebach**, *A First Course in String Theory* (2nd ed., Cambridge University Press) — tulang punggung utama domain ini; satu-satunya textbook string theory yang secara eksplisit dirancang untuk pembelajar undergraduate (bukan langsung graduate-level Polchinski), dipakai MIT 8.251 sebagai textbook utama.
2. **MIT OpenCourseWare 8.251 — String Theory for Undergraduates** (Zwiebach, https://ocw.mit.edu/courses/8-251-string-theory-for-undergraduates-spring-2007/) — silabus & problem set resmi yang jadi acuan urutan modul Module 6-10 domain ini (Lorentz/light-cone → compactification → dinamika klasik → kuantisasi → superstring/D-brane); prasyarat resminya sendiri (Relativity, Statistical Physics, Quantum Physics II) mengonfirmasi kenapa domain ini butuh modul QM & GR-bridge duluan.
3. **MIT OpenCourseWare 8.821 — String Theory / String Theory and Holographic Duality** (McGreevy, https://ocw.mit.edu/courses/8-821-string-theory-fall-2008/) — level graduate (AdS/CFT), dipakai sebagai rujukan "ke mana arah lanjut" di penutup course, eksplisit di luar scope inti domain ini.
4. **David Tong**, Lecture Notes on String Theory (Cambridge, gratis di https://www.damtp.cam.ac.uk/user/tong/string.html) — cross-check utama untuk compactification, T-duality, D-branes, dan conformal field theory secukupnya, dengan notasi & penjelasan yang sering lebih eksplisit daripada Zwiebach untuk topik lanjut.
5. **Joseph Polchinski**, *String Theory* Vol. 1 & 2 (Cambridge Monographs on Mathematical Physics) — referensi definitif/"kitab suci" graduate-level, dipakai untuk memverifikasi klaim lanjut (branes, dualitas, superstring) yang di textbook pengantar kadang disederhanakan.
6. **Katrin Becker, Melanie Becker, John H. Schwarz**, *String Theory and M-Theory: A Modern Introduction* (Cambridge) — narasi fisis yang kuat untuk branes, compactification, dan web dualitas, pelengkap Polchinski yang lebih formal.
7. **Leonard Susskind & Art Friedman**, *Quantum Mechanics: The Theoretical Minimum* — tulang punggung Module 2-3 (fondasi QM), dirancang khusus untuk pembelajar dewasa mandiri dengan modal kalkulus/aljabar linear, konsisten dengan cara domain [[domains/relativity/DOMAIN.md|relativity]] memakai seri Theoretical Minimum Susskind untuk Special Relativity.
8. **Domain & course [[domains/relativity/DOMAIN.md|relativity]]** (`courses/relativity/roadmap.md`) — TIDAK diajarkan ulang, tapi jadi rujukan eksplisit setiap kali domain ini butuh hasil GR (prinsip ekivalensi, metrik, geodesic) di Module 4; learner diarahkan ke course itu untuk derivasi penuh.
9. **Philip Candelas, Xenia de la Ossa, Paul Green, Linda Parkes** (1991), "A Pair of Calabi-Yau Manifolds as an Exactly Soluble Superconformal Theory," *Nuclear Physics B359* — paper pendiri mirror symmetry, sumber primer Module 14.
10. **Brian Greene**, "String Theory on Calabi-Yau Manifolds" (TASI lecture notes, gratis di arXiv hep-th/9702155) — jembatan yang lebih mudah diakses ke matematika compactification/mirror symmetry sebelum ke paper primer di atas.
11. **Shing-Tung Yau**'s existence theorem (Calabi conjecture, dibuktikan 1977) — hasilnya DIKUTIP (Ricci-flat compact Kähler manifold ada dalam kelas Kähler tertentu), tidak diturunkan; latar belakang geometri manifold/Kähler secukupnya disilangkan ke primer seperti Mikio Nakahara, *Geometry, Topology and Physics*.
12. **Howard Georgi**, *Lie Algebras in Particle Physics* — rujukan teori grup/Lie algebra yang accessible untuk potongan gauge symmetry (E8×E8, SO(32)) yang disentuh di Module 10 & 12.
13. **Andrew J. Hanson**, "A Construction for Computer Visualization of Certain Complex Curves" (*Notices of the AMS*) dan implementasi komunitas seperti analyticphysics.com "Visualizing Calabi-Yau Manifolds" — rujukan teknik visualisasi komputasi untuk mini project/capstone Module 13-15.
14. **Dokumentasi resmi NumPy, SciPy, SymPy, Matplotlib** — tool stack praktis untuk setiap artefak simulasi/visualisasi di domain ini, konsisten dengan cara domain [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]] & [[domains/relativity/DOMAIN.md|relativity]] memakai kode sebagai wahana Applied Practice.

Klaim kuantitatif/derivasi (mis. dimensi kritis D=26, spektrum T-duality, syarat Ricci-flat) selalu disilangkan minimal ke Zwiebach (2) DAN satu sumber lanjut (Tong/Polchinski/Becker-Becker-Schwarz, 4-6) — tidak cukup dari satu textbook pengantar saja, apalagi dari sumber populer tanpa derivasi. Klaim status epistemis string theory (terverifikasi vs spekulatif) selalu dinyatakan eksplisit per house rule di bawah, tidak dibiarkan ambigu seolah setara dengan hasil GR yang sudah dikonfirmasi eksperimen.

## Applied Practice Definition

**Derivation & Simulation Lab** — mengikuti pola domain [[domains/relativity/DOMAIN.md|relativity]] (bukan pola code-only [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]]), karena string theory di level ini condong ke pemahaman matematis-konseptual yang HARUS diverifikasi lewat derivasi tangan, sekaligus dibuat konkret lewat kode:

1. **Derivasi tangan** — menurunkan sendiri hasil kunci modul (mis. dimensi kritis D=26 dari kanselasi anomali Weyl secukupnya, atau spektrum massa T-duality) dari prinsip dasar, termasuk limit-check eksplisit (mis. apakah spektrum string kembali ke teori medan titik/point-particle saat α'→0 atau saat mode osilasi tinggi diabaikan).
2. **Simulasi/visualisasi kecil** (memanfaatkan skill software engineering dari domain [[domains/software-engineering/DOMAIN.md|software-engineering]]) — mis. simulator osilasi string relativistik (dekomposisi mode Fourier di worldsheet), plotter spektrum KK tower, kalkulator T-duality yang membuktikan numerik spektrum R match dengan spektrum α'/R, atau visualisasi proyeksi Calabi-Yau — dikode dalam Python (NumPy/SciPy/SymPy/Matplotlib), sehingga objek abstrak jadi sesuatu yang bisa dijalankan, diperiksa, dan dieksperimen ulang parameternya.

Setiap modul WAJIB menghasilkan minimal satu derivasi tertulis lengkap DAN satu artefak simulasi/kode kecil sebelum dianggap selesai, sesuai `# Hands-on First` di `CLAUDE.md` root — derivasi tanpa kode gampang jadi manipulasi simbol tanpa intuisi numerik, kode tanpa derivasi gampang jadi angka yang di-fudge supaya "terlihat benar."

## Project Tiers

- **Mini Project** (per modul): satu Derivation & Simulation Lab tunggal — derivasi lengkap satu hasil kunci modul plus skrip kecil yang mendemonstrasikannya secara numerik/visual.
- **Intermediate Project** (setelah bagian dualitas & branes, Module 9-12): **"T-Duality & Brane Spectrum Matcher"** — verifikasi numerik bahwa spektrum massa string (KK + winding) pada radius R sama persis dengan spektrum pada radius α'/R untuk string terbuka dan tertutup, plus visualisasi D-brane sederhana yang menunjukkan endpoint string terkunci pada brane.
- **Capstone / Portfolio Project** (akhir course): pilih SATU dari tiga jalur — **(A) String Worldsheet Dynamics Simulator** (simulasi & animasi osilasi string terbuka/tertutup dari solusi mode Fourier, divalidasi terhadap solusi analitik), **(B) Compactification & Duality Visualizer** (visualisasi spektrum KK tower pada compactification lingkaran/torus + demonstrasi numerik penuh T-duality R↔α'/R), atau **(C) Calabi-Yau Cross-Section Visualizer** (visualisasi proyeksi 2D/3D dari quintic Calabi-Yau threefold gaya Hanson, dengan penjelasan tertulis kaitannya ke mirror pair & Hodge numbers) — disimpan ke `portofolio/`, disertai penjelasan tertulis desain matematis/numerik dan status epistemis hasil (mana yang matematika solid, mana yang interpretasi fisis).

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap Derivation & Simulation Lab di domain ini juga dicek lewat lensa berikut:

- **Derivational Rigor** — apakah tiap langkah derivasi punya justifikasi eksplisit (prinsip/aturan mana yang dipakai), tanpa lompatan aljabar tersembunyi? (sama seperti domain relativity)
- **Dimensional & Limit Consistency** — apakah hasil akhir kembali ke limit yang benar (mis. spektrum string → teori medan titik saat α'→0, atau string non-relativistik klasik saat mode kuantum diabaikan)?
- **Conceptual vs Rote** — apakah learner bisa menjelaskan MENGAPA hasil itu masuk akal (mis. kenapa T-duality masuk akal secara fisis sebagai "string bisa membungkus dimensi kompak, titik tidak bisa"), bukan cuma mengutak-atik aljabar sampai ketemu jawaban buku?
- **Simulation Fidelity** — apakah kode simulasi benar-benar mengimplementasikan fisika/matematika yang diturunkan (bukan angka di-hardcode supaya "terlihat benar"), dan hasil numeriknya dibandingkan ke nilai referensi analitik yang diketahui?
- **Epistemic Honesty** (KHAS domain ini, tidak ada di relativity/physics-for-se) — apakah artefak/penjelasan membedakan secara eksplisit klaim matematis yang solid & self-consistent (mis. D=26, T-duality) dari klaim fisis yang masih spekulatif/belum terverifikasi eksperimen (mis. string theory sebagai teori realitas, landscape/multiverse, pilihan compactification tertentu = alam semesta kita)? Kegagalan membedakan ini adalah cacat serius, bukan detail kecil.

## Assessment Form

Concept-check singkat setelah tiap bagian teori (skenario "apa yang terjadi pada spektrum massa kalau radius compactification diperbesar/diperkecil" untuk menguji pemahaman, bukan hafalan rumus), problem set numerik terarah gaya MIT 8.251 dengan kunci jawaban untuk self-check, sesi Socratic derivation (learner diminta menurunkan satu langkah kunci — mis. syarat mass-shell — sebelum diberi tahu jawabannya, sesuai `# Challenge` di `CLAUDE.md` root), dan review kode simulasi (learner menjalankan sendiri, membandingkan output numerik ke solusi analitik, menjelaskan deviasi bila ada).

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk mendekati topik yang sama:

- **Fisikawan teoretis akademik** (gaya Polchinski/Becker-Becker-Schwarz/Tong) — rigor matematis penuh, nyaman dengan abstraksi worldsheet CFT & geometri compactification berlapis — lensa dominan untuk modul dinamika string, kuantisasi, dan compactification.
- **Pengajar/komunikator untuk pembelajar mandiri** (gaya Zwiebach/Susskind) — membangun intuisi & notasi accessible sebelum formalisme penuh, dipakai terutama di modul-modul awal ("dari 0") dan seluruh modul QM.
- **Matematikawan geometer** (gaya Yau/Nakahara) — rigor definisi & teorema untuk Calabi-Yau/Kähler geometry, dipakai untuk memastikan Module 13-14 tidak menghafal istilah tanpa makna geometris yang benar.
- **Kritikus/filsuf sains** (gaya Peter Woit "Not Even Wrong", Lee Smolin "The Trouble with Physics") — lensa WAJIB di penutup course untuk menjaga kejujuran epistemis: string theory belum terverifikasi eksperimen, kritik falsifiability & landscape problem harus disebutkan secara jujur, bukan disembunyikan demi antusiasme mengajarkan topik yang menarik.

Keempat lensa ini sering menekankan hal berbeda pada topik yang sama (mis. Calabi-Yau compactification: fisikawan teoretis excited soal implikasi fenomenologi, matematikawan geometer peduli rigor eksistensi/keunikan, kritikus sains bertanya "tapi bagaimana ini diuji?") — domain ini melatih learner mengenali ketegangan itu secara sadar, bukan menganggap satu lensa sebagai satu-satunya kebenaran.

## Domain-Specific Standards

`domains/string-theory-for-engineers/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Tidak ada teori tanpa derivasi tangan DAN kode.** Konsisten dengan Applied Practice Definition di atas — modul yang tidak berakhir dengan derivasi tertulis lengkap PLUS artefak kode yang bisa dijalankan/divalidasi dianggap belum selesai.
- **Batas GR dinyatakan eksplisit, dan diarahkan ke domain relativity, bukan diulang.** Setiap kali topik butuh hasil GR di luar bridge minimal Module 4 (mis. derivasi penuh metrik Schwarzschild, gelombang gravitasi), mentor menyatakan eksplisit itu di luar scope domain ini dan mengarahkan ke domain [[domains/relativity/DOMAIN.md|relativity]] — mirroring house rule serupa di `domains/physics-for-software-engineers/DOMAIN.md` soal batas relativitas.
- **Kejujuran epistemis WAJIB, bukan opsional.** String theory PER 2026 adalah kerangka matematis yang self-consistent dan kandidat unifikasi kuat, TAPI belum punya konfirmasi eksperimental langsung — beda kategori dari GR (dikonfirmasi GPS/LIGO/Eddington) yang diajarkan di domain relativity. Mentor WAJIB menyatakan status ini eksplisit setiap kali membahas klaim fisis (bukan matematis) dari string theory, dan merujuk kritik ilmiah (Woit, Smolin) secukupnya di penutup course — never present string theory as settled physics.
- **Validasi numerik wajib untuk tiap simulasi.** Setiap artefak kode yang dianggap "selesai" harus punya bukti eksplisit kesesuaiannya dengan hasil analitik yang diturunkan (mis. spektrum massa dari kode vs dari derivasi tangan) — bukan cuma "terlihat benar" secara visual, sesuai lensa Review Style "Simulation Fidelity" di atas.
- **Matematika dibangun just-in-time, bukan silabus matematika murni terpisah.** Geometri diferensial, complex analysis, dan teori grup diperkenalkan tepat saat dibutuhkan modul yang bersangkutan (Module 4 untuk dasar tensor/manifold dari GR bridge, Module 13 untuk complex manifold/Kähler geometry, Module 10/12 untuk Lie algebra gauge symmetry) — bukan satu modul matematika murni raksasa di awal yang memutus motivasi fisis.

## Domain Goal

Learner mampu mengikuti alur penuh dari fondasi QM & GR-bridge → dinamika string klasik & kuantisasi → extra dimensions/compactification → D-branes & dualitas (T-duality, S-duality) → Calabi-Yau compactification & mirror symmetry — cukup untuk membaca Zwiebach dan lecture notes David Tong secara mandiri, dan memahami (bukan mereproduksi penuh) paper pengantar seperti Candelas-de la Ossa-Green-Parkes (1991) — dibuktikan lewat capstone project di portofolio yang mengintegrasikan derivasi tangan + simulasi/visualisasi kode numerik. Ini eksplisit BUKAN kredensial PhD string theory maupun klaim bahwa string theory adalah fakta fisis terverifikasi — murni kemampuan memahami & membangun artefak dari kerangka matematis string theory secara teknis-jujur, memakai latar belakang software engineering learner sebagai jembatan.

## Registration History

Domain ini didraft 2026-08-16 atas permintaan user membangun course "String Theory for Software Engineers — dari 0 sampai Expert". Domain baru (bukan ekstensi domain existing) diputuskan setelah membaca eksplisit `domains/physics-for-software-engineers/DOMAIN.md` (Scope-nya secara eksplisit MENGECUALIKAN relativitas DAN quantum mechanics — dua prasyarat wajib string theory) dan `domains/relativity/DOMAIN.md` (Scope-nya secara eksplisit MENGECUALIKAN string theory, dengan kalimat: "itu di luar scope dan butuh domain terpisah di masa depan kalau dibutuhkan"). Karena tidak ada satu pun domain existing yang cocok tanpa melanggar batas scope eksplisit yang sudah didokumentasikan sendiri oleh kedua domain itu, domain baru `string-theory-for-engineers` dibuat — bukan duplikasi, melainkan pengisian gap yang sudah diantisipasi oleh domain relativity. Detail lengkap keputusan ini didokumentasikan di `courses/string-theory-for-engineers/roadmap.md` bagian "Catatan Scope". Registered paused mengikuti kebijakan hemat-token 2026-08-07 begitu didaftarkan; roadmap dibangun bersamaan atas permintaan user, mengikuti pola yang sama dengan `domains/physics-for-software-engineers/DOMAIN.md`.
