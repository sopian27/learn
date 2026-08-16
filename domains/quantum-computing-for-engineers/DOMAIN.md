# Quantum Computing untuk Software Engineer — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Quantum Computing untuk Software Engineer (Quantum Computing for Engineers) — kuantum sebagai model komputasi baru: qubit, superposisi, entanglement, gerbang kuantum, sirkuit kuantum, algoritma kuantum (Deutsch-Jozsa, Grover, Shor), dan implementasi nyata lewat Qiskit (dan sekilas Cirq) di simulator maupun hardware kuantum sungguhan.

## Scope

Quantum computing sebagai model komputasi yang harus benar-benar dikode dan dijalankan — bukan mekanika kuantum untuk fisikawan. Setiap modul teori wajib berujung pada sirkuit kuantum nyata yang dijalankan dan diverifikasi:

- **Matematika fondasi**: bilangan kompleks, aljabar linear atas complex vector space (vektor, matriks, inner product, unitary operator, tensor product/Kronecker product), notasi Dirac (bra-ket), dan probabilitas kuantum (Born rule, postulat pengukuran) — diajarkan dari nol khusus untuk kebutuhan quantum computing, bukan kursus aljabar linear/probabilitas umum.
- **Qubit sebagai unit informasi**: superposisi, global vs relative phase, Bloch sphere, entanglement, no-cloning theorem — fondasi konseptual sebelum satu pun gerbang kuantum dibahas.
- **Gerbang kuantum & sirkuit**: single-qubit gates (Pauli X/Y/Z, Hadamard, S/T, rotasi Rx/Ry/Rz), multi-qubit gates (CNOT, CZ, SWAP, Toffoli), model sirkuit kuantum (input, gerbang, measurement, classical control), universalitas gate set — plus dua protokol pembuka yang membuktikan entanglement praktis: quantum teleportation & superdense coding.
- **Algoritma kuantum** pada level konseptual + matematis penuh (derivasi, bukan cuma cerita): Deutsch-Jozsa & Simon's algorithm (quantum parallelism, query complexity), Grover's search (amplitude amplification, speedup kuadratik), Quantum Fourier Transform & Shor's factoring algorithm (period finding, implikasi terhadap RSA).
- **Lanskap hardware kuantum**: modalitas (superconducting, trapped-ion, neutral atom, photonic), NISQ era, noise/decoherence (T1/T2, gate error rate), pengantar quantum error correction secukupnya, dan cara menjalankan sirkuit di hardware kuantum sungguhan (IBM Quantum cloud).
- **Algoritma near-term/variational secukupnya** (VQE, QAOA) dan gambaran ekosistem industri (IBM Quantum, Amazon Braket, Azure Quantum) — sekadar orientasi, bukan pendalaman.
- **Coding**: Python + Qiskit sebagai toolchain utama — membangun & menjalankan sirkuit kuantum sungguhan, mensimulasikan algoritma, submit job ke hardware kuantum nyata. Cirq disinggung sebagai pembanding ekosistem Google, bukan toolchain utama (rasional pemilihan ada di `courses/quantum-computing-for-engineers/roadmap.md`).

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Mekanika kuantum umum ala fisikawan** — persamaan Schrödinger untuk atom hidrogen, teori medan kuantum, quantum many-body physics penuh, dsb. Domain ini hanya mengambil postulat QM yang relevan untuk unit informasi (qubit): superposisi, entanglement, measurement — bukan kursus QM S1 Fisika penuh.
- **Physics engine, ray tracing, robotika, mekanika klasik** — itu wilayah domain [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]] yang justru secara eksplisit MENGECUALIKAN materi ini dari scope-nya sendiri: *"Mekanika kuantum / fisika komputasi kuantum — domain terpisah, tidak dibahas di sini sama sekali (baik quantum mechanics maupun quantum computing)"* (dikutip langsung dari `domains/physics-for-software-engineers/DOMAIN.md` bagian Scope). Karena pengecualian itu eksplisit, domain quantum-computing-for-engineers ini berdiri sendiri — lihat "Registration History" di bawah untuk rasional lengkap.
- **Kriptografi post-quantum secara mendalam** (desain algoritma tahan-kuantum seperti lattice-based crypto) — disinggung sekilas sebagai implikasi Shor's algorithm terhadap RSA/ECC (Module 10 course), bukan kursus kriptografi.
- **Desain & fabrikasi hardware kuantum sungguhan** (desain chip superconducting, laser trapping ion, kalibrasi fisik, dst) — level literasi "cara kerja & trade-off tiap modalitas dari sudut pandang pengguna cloud quantum computing," bukan pendidikan quantum hardware engineering.

## Trusted Sources / Research Priority

1. **IBM Qiskit Textbook / IBM Quantum Learning** (https://quantum.cloud.ibm.com/learning) — kurikulum resmi & gratis dari tim yang membangun Qiskit sendiri, jadi tulang punggung modul gerbang/sirkuit/algoritma/coding, plus akses langsung ke hardware kuantum nyata lewat IBM Quantum cloud.
2. **Michael Nielsen & Isaac Chuang**, *Quantum Computation and Quantum Information* (Cambridge University Press) — buku standar akademik/industri ("the bible" quantum computing), dipakai sebagai kerangka rigor matematis untuk modul aljabar linear, postulat kuantum, dan derivasi algoritma.
3. **MIT 8.370x — Quantum Information Science** (edX/MIT OpenCourseWare, gratis, https://ocw.mit.edu/courses/8-370x-quantum-information-science-i-spring-2018/) — dipakai sebagai kerangka urutan modul algoritma (Deutsch-Jozsa, Simon's, Grover, Shor) dan protokol (teleportation, superdense coding), disilang-cek dengan Nielsen & Chuang.
4. **Dokumentasi & source code resmi Qiskit** (https://github.com/Qiskit, https://docs.quantum.ibm.com/) — rujukan implementasi nyata untuk setiap gerbang/algoritma yang dikode, dicek silang dengan derivasi matematis dari sumber 2-3.
5. **The Quantum Insider, Quantum Zeitgeist, dan laporan resmi vendor hardware** (IBM, Google Quantum AI, IonQ, Quantinuum, QuEra, PsiQuantum) — dipakai khusus untuk modul lanskap hardware; lanskap ini berubah cepat, jadi setiap klaim qubit count/fidelity WAJIB diberi tanggal & sumber eksplisit, tidak dianggap statis dari ingatan.
6. **Scott Aaronson**, *Quantum Computing Since Democritus* dan blog *Shtetl-Optimized* — dipakai untuk intuisi konseptual computer-science-native (kompleksitas komputasi, kenapa quantum speedup terjadi), pelengkap rigor Nielsen & Chuang yang lebih formal.
7. **Paper asli algoritma** (Deutsch 1985/1992, Grover 1996, Shor 1997 — arXiv.org) — dipakai untuk verifikasi klaim algoritma terhadap sumber primer saat dibutuhkan kedalaman ekstra, bukan bacaan wajib tiap lesson.

Klaim matematis (kompleksitas query, bukti speedup kuadratik/eksponensial) selalu disilangkan minimal ke Nielsen & Chuang ATAU MIT 8.370x, dan setiap implementasi kode diverifikasi benar-benar berjalan terhadap simulator Qiskit resmi (Aer) — tidak cukup hanya dari ingatan/asumsi tanpa verifikasi.

## Applied Practice Definition

Applied practice di domain ini SELALU berarti sirkuit kuantum yang benar-benar dibangun dan dijalankan (di simulator, dan pada titik tertentu di hardware kuantum nyata via IBM Quantum) — konsisten dengan `# Hands-on First` di `CLAUDE.md` root, dengan urutan wajib:

- **Derivasi matematis singkat dulu** (state vector, matrix multiplication gerbang, tensor product) SEBELUM dikode — supaya kode bukan cuma "manggil fungsi Qiskit" tanpa paham representasi matematis di baliknya.
- **Sirkuit kuantum per konsep** dibangun di Qiskit (`QuantumCircuit`), dijalankan di simulator (`Aer`/`StatevectorSimulator`), hasil (statevector/probabilitas measurement) dibandingkan eksplisit terhadap perhitungan tangan.
- **Implementasi algoritma end-to-end** (Deutsch-Jozsa, Grover, Shor versi kecil) sebagai sirkuit yang bisa dijalankan berulang & divalidasi secara statistik (mis. Grover menemukan item benar dengan probabilitas tinggi setelah jumlah iterasi optimal, bukan "kelihatan jalan sekali").
- **Minimal satu submission ke hardware kuantum nyata** (IBM Quantum free tier) untuk merasakan efek noise dunia nyata dibanding simulator ideal — bagian wajib modul hardware landscape, bukan opsional.

## Project Tiers

- **Mini Project** (per modul): satu sirkuit/skrip Qiskit kecil yang mendemonstrasikan konsep modul itu sendiri (mis. sirkuit Bell state, sirkuit Deutsch-Jozsa oracle 3-qubit).
- **Intermediate Project** (setelah beberapa modul terkait): Grover's algorithm penuh untuk search space kecil (3-4 qubit) dengan analisis jumlah iterasi optimal vs teori, DAN perbandingan noise simulator ideal vs hardware nyata untuk sirkuit yang sama.
- **Capstone / Portfolio Project** (akhir course): proyek Qiskit lengkap mengimplementasikan salah satu algoritma utama (Grover diskalakan lebih besar, ATAU Shor versi kecil seperti faktorisasi N=15/21, ATAU kombinasi 2+ algoritma sebagai showcase) dari nol — matematika, sirkuit, eksekusi simulator + hardware nyata, dan dokumentasi trade-off desain — disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak kode di domain ini juga dicek lewat lensa berikut:

- **Mathematical Correctness** — apakah representasi matematis (state vector, matrix, tensor product) benar dan konsisten dengan apa yang dikode, bukan angka yang kebetulan cocok.
- **Circuit Correctness** — apakah sirkuit yang dibangun benar-benar mengimplementasikan algoritma yang dimaksud secara struktural (oracle, diffusion operator, dst benar), bukan cuma "mirip contoh di textbook".
- **Quantum Advantage Awareness** — apakah learner bisa menjelaskan MEKANISME kenapa algoritma tsb lebih cepat dari klasik (interference, amplitude amplification, period finding), bukan cuma tahu hasil akhirnya lebih cepat.
- **Simulator vs Hardware Reality Check** — kesadaran eksplisit soal noise & decoherence pada hardware nyata vs idealisasi simulator (paralel dengan lensa "Numerical Stability" di domain [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]]).

## Assessment Form

Problem set matematis terarah (hitung tangan state vector setelah serangkaian gerbang, lalu verifikasi lewat kode — gaya Nielsen & Chuang end-of-chapter exercises), quiz konseptual algoritma (mis. "kenapa Grover butuh ~π√N/4 iterasi, bukan kurang/lebih"), dan review kode sirkuit (learner menjalankan sirkuitnya sendiri, membandingkan distribusi hasil measurement terhadap prediksi teori, menjelaskan penyebab deviasi bila ada — terutama saat sudah menyentuh hardware nyata).

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk mendekati topik yang sama:

- **Quantum algorithm researcher** (gaya Nielsen & Chuang, Scott Aaronson) — rigor matematis, complexity theory, bukti formal — lensa dominan untuk modul matematika fondasi & algoritma.
- **Quantum software engineer** (gaya tim Qiskit IBM) — fokus praktis: circuit design, transpilation, running on real backends, debugging noisy hasil — lensa dominan untuk modul coding & hardware.
- **Quantum hardware engineer** (gaya IBM/Google/IonQ/Quantinuum) — fokus fisik: coherence time, gate fidelity, trade-off qubit count vs kecepatan vs error rate — lensa untuk modul lanskap hardware.

Ketiga lensa ini sering menekankan hal berbeda untuk topik yang sama (mis. memilih algoritma untuk NISQ device: researcher mengutamakan kebenaran matematis penuh, software engineer mengutamakan seberapa dalam sirkuit bisa dijalankan sebelum noise mendominasi, hardware engineer mengutamakan modalitas mana yang paling stabil untuk sirkuit itu) — domain ini melatih learner mengenali trade-off tersebut secara sadar.

## Domain-Specific Standards

`domains/quantum-computing-for-engineers/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Tidak ada gerbang tanpa matriks.** Setiap gerbang kuantum yang dipakai harus diketahui representasi matriksnya dan diverifikasi unitary, bukan cuma nama/dipakai membabi buta dari dokumentasi Qiskit.
- **Klaim quantum advantage harus dijelaskan mekanismenya** (query complexity, interference, amplitude amplification, period finding) — bukan diklaim begitu saja tanpa derivasi.
- **Batas mekanika kuantum umum dinyatakan eksplisit.** Domain ini mengajarkan QM secukupnya untuk quantum computing (postulat state, evolusi unitary, pengukuran), bukan kursus QM Fisika S1 penuh — setiap kali pertanyaan menyentuh area fisika murni di luar itu (mis. solusi Schrödinger equation untuk atom hidrogen), mentor menyatakan eksplisit bahwa itu di luar scope domain ini.
- **Klaim lanskap hardware wajib bersumber & bertanggal.** Karena qubit count/fidelity vendor berubah cepat, setiap klaim di modul hardware landscape harus menyebut sumber & tanggal, bukan dianggap statis dari ingatan/training data.

## Domain Goal

Learner mampu memahami fondasi matematis quantum computing (aljabar linear atas complex vector space, probabilitas kuantum) dan menggunakannya untuk membangun, menjalankan, dan menganalisis sirkuit kuantum nyata di Qiskit — termasuk mengimplementasikan dari nol algoritma kuantum utama (Deutsch-Jozsa, Grover, Shor), memahami lanskap hardware kuantum saat ini, dan merasakan langsung efek noise pada hardware kuantum sungguhan — dibuktikan lewat capstone project di portofolio yang mengintegrasikan matematika + sirkuit + eksekusi nyata. Ini eksplisit BUKAN kredensial fisika kuantum formal — murni kemampuan applied engineering yang menghubungkan latar belakang software engineering learner dengan domain quantum computing.

## Registration History

Domain ini diregistrasi 2026-08-16 atas permintaan user untuk membangun course "Quantum Theory for Software Engineers — dari 0 sampai Expert". Sebelum membuat domain baru, `domains/physics-for-software-engineers/DOMAIN.md` (sudah terdaftar, Status: Paused) dibaca penuh untuk mengecek kemungkinan overlap — domain tersebut secara eksplisit MENGECUALIKAN "mekanika kuantum / fisika komputasi kuantum" dari scope-nya sendiri, dengan kalimat eksplisit: *"domain terpisah, tidak dibahas di sini sama sekali (baik quantum mechanics maupun quantum computing)"*. Karena pengecualian itu eksplisit dan disengaja oleh domain physics-for-software-engineers sendiri, domain quantum-computing-for-engineers ini dibuat BERDIRI SENDIRI — bukan sub-topik/module tambahan di bawah physics-for-software-engineers — sehingga tidak ada duplikasi konten sama sekali antara keduanya.
