# Course Roadmap

## Course Information

* Course Name: Quantum Theory for Software Engineers — dari 0 sampai Expert
* Category: Quantum Computing for Engineers (Domain: quantum-computing-for-engineers — lihat `domains/quantum-computing-for-engineers/DOMAIN.md`)
* Difficulty: Level 0 (bisa coding kuat, tapi belum pernah pakai bilangan kompleks/aljabar linear untuk fisika, apalagi quantum computing) → Level 4 (mampu menurunkan matematika di balik algoritma kuantum utama DAN mengimplementasikannya sendiri dari nol di Qiskit, di simulator maupun hardware kuantum nyata)
* Estimated Duration: 13 modul (12 modul konten + 1 modul capstone), 4 lesson/modul (52 lesson), pace disesuaikan karena Applied Practice di domain ini SELALU berupa sirkuit kuantum yang benar-benar dijalankan & diverifikasi (statevector, distribusi measurement, dsb) — bukan sekadar baca teori
* Prerequisites: Kemampuan programming kuat di Python (fungsi, OOP dasar, comfortable baca dokumentasi library) — Qiskit adalah library Python. Aljabar & trigonometri tingkat SMA membantu tapi TIDAK wajib menguasai bilangan kompleks/aljabar linear/probabilitas dulu — semuanya diperkenalkan dari nol khusus untuk kebutuhan quantum computing di Module 1-3. TIDAK butuh latar belakang fisika atau mekanika kuantum sebelumnya. Domain ini murni quantum computing (qubit sebagai unit informasi); pertanyaan yang menyentuh mekanika kuantum umum ala fisikawan (mis. solusi Schrödinger equation untuk atom hidrogen) akan dinyatakan eksplisit di luar scope, bukan dijawab di sini.

---

# Learning Objectives

* [ ] Menguasai bilangan kompleks dan aljabar linear atas complex vector space (vektor, matriks, inner product, unitary operator, tensor product, notasi Dirac/bra-ket) secukupnya untuk merepresentasikan quantum state
* [ ] Memahami probabilitas kuantum (Born rule, postulat pengukuran) sebagai jembatan dari amplitude kompleks ke hasil yang benar-benar terukur
* [ ] Menjelaskan qubit, superposisi, Bloch sphere, entanglement, dan no-cloning theorem dengan benar — termasuk miskonsepsi umum yang perlu diluruskan
* [ ] Menguasai gerbang kuantum single- dan multi-qubit (Pauli, Hadamard, phase, rotasi, CNOT, Toffoli) sebagai matriks unitary, dan membaca/menulis notasi sirkuit kuantum standar
* [ ] Mengimplementasikan protokol quantum teleportation dan superdense coding sebagai pembuktian praktis entanglement
* [ ] Menurunkan dan mengimplementasikan Deutsch-Jozsa & Simon's algorithm, memahami quantum parallelism dan query complexity
* [ ] Menurunkan dan mengimplementasikan Grover's search algorithm (amplitude amplification, speedup kuadratik) dengan analisis jumlah iterasi optimal
* [ ] Menurunkan dan mengimplementasikan Quantum Fourier Transform & Shor's factoring algorithm (period finding), memahami implikasinya terhadap RSA
* [ ] Memahami lanskap hardware kuantum saat ini (superconducting, trapped-ion, neutral atom, photonic), noise/decoherence, dan pengantar quantum error correction secukupnya
* [ ] Menjalankan sirkuit kuantum sungguhan di hardware kuantum nyata (IBM Quantum) dan membandingkan hasilnya secara kuantitatif terhadap simulator ideal
* [ ] Memiliki orientasi ekosistem quantum computing industri terkini (VQE, QAOA, cloud quantum computing) secukupnya untuk arah belajar lanjutan
* [ ] Menghasilkan Capstone Project (implementasi algoritma kuantum end-to-end di Qiskit, simulator + hardware nyata) dengan dokumentasi matematis dan trade-off desain

---

# Course Modules

## Module 1 — Bilangan Kompleks & Complex Vector Space untuk Quantum Computing

Description: Fondasi matematis paling dasar sebelum menyentuh satu pun qubit — bilangan kompleks dan complex vector space, karena quantum state hidup di ruang vektor kompleks, bukan real. Mengikuti tiga bab pertama Qiskit Textbook dan Bab 2 Nielsen & Chuang.

Lessons:

* [ ] 1.1 Bilangan kompleks: representasi Cartesian & polar, konjugat, modulus — kenapa quantum computing butuh ini (amplitude bisa kompleks, bukan cuma real)
* [ ] 1.2 Complex vector space: vektor, basis, inner product (dot product versi kompleks), norm
* [ ] 1.3 Notasi Dirac (bra-ket): |0⟩ dan |1⟩, ket sebagai column vector, bra sebagai conjugate transpose
* [ ] 1.4 Python untuk aljabar linear kompleks: NumPy complex arrays, verifikasi perhitungan tangan lewat kode (belum Qiskit)

Mini Project: Skrip Python (NumPy) yang merepresentasikan vektor state |ψ⟩ = α|0⟩ + β|1⟩ untuk beberapa contoh α, β kompleks, menghitung norm-nya, dan memverifikasi kondisi normalisasi |α|²+|β|²=1.

Status: Not started

---

## Module 2 — Aljabar Linear Lanjutan: Matriks, Unitary Operators & Tensor Product

Description: Operasi pada quantum state adalah perkalian matriks unitary; sistem multi-qubit butuh tensor product. Modul ini menutup fondasi matematis sebelum masuk qubit sungguhan (Nielsen & Chuang Ch. 2, Qiskit Textbook Ch. 1).

Lessons:

* [ ] 2.1 Matriks sebagai operator linear — perkalian matrix-vector, eigenvalue/eigenvector secukupnya
* [ ] 2.2 Unitary matrix — definisi (U†U=I), kenapa evolusi quantum HARUS unitary (reversibilitas)
* [ ] 2.3 Tensor product (Kronecker product) — cara menggabungkan ruang vektor beberapa qubit
* [ ] 2.4 Dari matematika ke NumPy: memverifikasi unitary secara terprogram, menghitung tensor product beberapa vektor kecil dengan `numpy.kron`

Mini Project: Skrip Python yang memeriksa apakah suatu matriks unitary atau bukan (beberapa contoh, termasuk kandidat gerbang kuantum yang akan muncul di Module 5), dan menghitung tensor product dua vektor qubit secara manual lalu dibandingkan dengan hasil `numpy.kron`.

Status: Not started

---

## Module 3 — Probabilitas Kuantum: Born Rule & Postulat Pengukuran

Description: Menjembatani matematika murni ke fisika informasi: bagaimana amplitude kompleks berubah jadi probabilitas terukur lewat Born Rule, dan postulat pengukuran kuantum (Nielsen & Chuang Ch. 2.2, MIT 8.370.1x).

Lessons:

* [ ] 3.1 Probability amplitude vs probabilitas klasik — Born Rule (P = |amplitude|²)
* [ ] 3.2 Postulat pengukuran kuantum: collapse of the wavefunction, measurement basis
* [ ] 3.3 Expectation value & observable secukupnya (operator Hermitian sebagai observable)
* [ ] 3.4 Kenapa quantum computing "probabilistik tapi deterministik secara matematis" — meluruskan miskonsepsi umum ("qubit adalah bit yang bingung")

Mini Project: Simulasikan (NumPy, belum Qiskit) pengukuran berulang dari suatu state |ψ⟩ sebanyak 1000 "shots" memakai sampling sesuai distribusi Born Rule, lalu bandingkan distribusi hasil empiris terhadap prediksi teori |amplitude|².

Status: Not started

---

## Module 4 — Qubit: Superposisi dan Bloch Sphere

Description: Titik masuk resmi ke "unit informasi kuantum" — qubit sebagai generalisasi bit, superposisi, dan visualisasi Bloch sphere. Instalasi dan sirkuit pertama di Qiskit (Qiskit Textbook Ch. 1).

Lessons:

* [ ] 4.1 Dari bit klasik ke qubit — kenapa qubit BUKAN "bit yang lagi bingung 0 atau 1"
* [ ] 4.2 Superposisi & perbedaan global phase vs relative phase
* [ ] 4.3 Bloch sphere — representasi visual state qubit tunggal
* [ ] 4.4 Setup Qiskit (instalasi, akun IBM Quantum) & sirkuit qubit pertama: `QuantumCircuit`, `Statevector`, visualisasi Bloch sphere lewat kode

Mini Project: Sirkuit Qiskit satu-qubit yang menyiapkan beberapa state berbeda (|0⟩, |1⟩, |+⟩, |−⟩, state sudut arbitrer), memvisualisasikannya di Bloch sphere, dan membandingkan statevector hasil kode dengan perhitungan tangan dari Module 1-3.

Status: Not started

---

## Module 5 — Single-Qubit Gates & Sirkuit Kuantum Pertama

Description: Gerbang kuantum sebagai matriks unitary yang beraksi pada qubit — Pauli gates, Hadamard, phase gates — plus cara membaca/menulis notasi sirkuit kuantum standar (Qiskit Textbook Ch. 1-2, Nielsen & Chuang Ch. 4).

Lessons:

* [ ] 5.1 Pauli gates (X, Y, Z) — matriks & efeknya di Bloch sphere
* [ ] 5.2 Hadamard gate — gerbang superposisi paling penting, kenapa H² = I
* [ ] 5.3 Phase gates (S, T) & rotasi umum (Rx, Ry, Rz)
* [ ] 5.4 Notasi sirkuit kuantum standar & universalitas gate set — kenapa {H, T, CNOT} cukup untuk komputasi kuantum universal

Mini Project: Bangun sirkuit Qiskit yang mengaplikasikan kombinasi gate (mis. H → Z → H), buktikan lewat kode DAN perhitungan tangan bahwa hasilnya ekuivalen dengan gate tunggal lain (mis. HZH = X) — verifikasi identitas gerbang.

Status: Not started

---

## Module 6 — Multi-Qubit Systems & Entanglement

Description: Melampaui satu qubit — bagaimana ruang state tumbuh secara tensor product, gerbang dua-qubit (CNOT), dan fenomena paling "quantum" dari semuanya: entanglement (Nielsen & Chuang Ch. 1.3.6 & 2.3, Qiskit Textbook Ch. 2).

Lessons:

* [ ] 6.1 Ruang state multi-qubit sebagai tensor product — kenapa n qubit butuh 2^n amplitude
* [ ] 6.2 CNOT gate & gerbang multi-qubit lain (CZ, SWAP, Toffoli)
* [ ] 6.3 Entanglement — Bell states, kenapa state terentangle tidak bisa ditulis sebagai tensor product dua state terpisah
* [ ] 6.4 No-cloning theorem & implikasinya — kenapa quantum computing tidak bisa "copy-paste" state sembarangan

Mini Project: Sirkuit Qiskit yang membangun keempat Bell states (Φ+, Φ−, Ψ+, Ψ−), ukur berulang (banyak shots), dan buktikan korelasi hasil pengukuran dua qubit (bukan independen) lewat statistik histogram dibandingkan prediksi teori.

Status: Not started

---

## Module 7 — Quantum Measurement Lanjutan & Protokol Kuantum

Description: Menutup fondasi model sirkuit kuantum dengan dua protokol klasik yang membuktikan entanglement bukan cuma teori: quantum teleportation & superdense coding (Nielsen & Chuang Ch. 1.3.7, MIT 8.370.2x minggu awal).

Lessons:

* [ ] 7.1 Model sirkuit kuantum lengkap: input klasik/kuantum, gerbang, measurement, classical control (mid-circuit measurement)
* [ ] 7.2 Quantum teleportation — protokol lengkap, kenapa TIDAK melanggar no-cloning theorem atau faster-than-light communication
* [ ] 7.3 Superdense coding — mengirim 2 bit klasik lewat 1 qubit yang terentangle
* [ ] 7.4 Diskusi: kenapa dua protokol ini jadi "hello world" pembuktian entanglement praktis, bukan cuma trik matematis di atas kertas

Mini Project: Implementasikan protokol quantum teleportation penuh di Qiskit (3 qubit: state yang ditransfer + pasangan Bell + classical bits untuk koreksi), verifikasi state hasil di qubit tujuan sama dengan state asal untuk beberapa state uji berbeda.

Status: Not started

---

## Module 8 — Quantum Algorithms I: Deutsch-Jozsa & Simon's Algorithm

Description: Algoritma kuantum pertama dalam sejarah yang menunjukkan speedup nyata atas klasik — titik masuk resmi ke "kenapa quantum computing bisa lebih cepat" lewat quantum parallelism & interference (Nielsen & Chuang Ch. 1.4.3 & 3.2.1, MIT 8.370.2x, paper Deutsch 1985/1992).

Lessons:

* [ ] 8.1 Oracle & query complexity — cara mengukur "seberapa cepat" algoritma kuantum dibanding klasik
* [ ] 8.2 Deutsch's algorithm (kasus 1-qubit) — intuisi quantum parallelism & interference
* [ ] 8.3 Deutsch-Jozsa algorithm (generalisasi n-qubit) — derivasi matematis lengkap
* [ ] 8.4 Simon's algorithm — period finding sederhana, jembatan konseptual menuju Shor's algorithm (Module 10)

Mini Project: Implementasikan Deutsch-Jozsa algorithm di Qiskit untuk oracle balanced vs constant (bangun oracle-nya sendiri sebagai sirkuit), jalankan di simulator, buktikan hasil measurement membedakan kedua kasus HANYA DENGAN SATU query — kontraskan eksplisit dengan klasik yang butuh sampai 2^(n-1)+1 query.

Status: Not started

---

## Module 9 — Quantum Algorithms II: Grover's Search Algorithm

Description: Algoritma pencarian kuantum paling terkenal — amplitude amplification memberikan speedup kuadratik untuk unstructured search, dengan derivasi geometris yang elegan (Nielsen & Chuang Ch. 6, paper Grover 1996, Qiskit Textbook Ch. 3).

Lessons:

* [ ] 9.1 Unstructured search problem & batas klasik O(N)
* [ ] 9.2 Oracle & diffusion operator — dua komponen inti Grover's algorithm
* [ ] 9.3 Amplitude amplification — derivasi geometris (rotasi di ruang 2D span target vs non-target)
* [ ] 9.4 Jumlah iterasi optimal (~π√N/4) & apa yang terjadi saat overshoot (probabilitas sukses justru turun)

Mini Project (Intermediate Project): Implementasikan Grover's algorithm penuh di Qiskit untuk search space 3-4 qubit (8-16 item), dengan oracle custom menandai 1 atau 2 item target. Validasi: jalankan dengan jumlah iterasi optimal vs sub-optimal vs berlebihan, plot probabilitas menemukan target sebagai fungsi jumlah iterasi, bandingkan terhadap kurva teori π√N/4 secara eksplisit.

Status: Not started

---

## Module 10 — Quantum Algorithms III: Quantum Fourier Transform & Shor's Algorithm

Description: Algoritma yang mengguncang kriptografi modern — QFT sebagai building block, lalu Shor's factoring algorithm secara konseptual + matematis (period finding), termasuk implikasinya terhadap RSA (Nielsen & Chuang Ch. 5, paper Shor 1997, MIT 8.370.2x).

Lessons:

* [ ] 10.1 Quantum Fourier Transform (QFT) — definisi, sirkuit, perbandingan dengan classical DFT/FFT
* [ ] 10.2 Phase estimation — building block penting sebelum Shor's algorithm
* [ ] 10.3 Shor's algorithm: reduksi masalah factoring ke period finding, kenapa QFT jadi kuncinya
* [ ] 10.4 Implikasi terhadap RSA/kriptografi klasik & pengantar singkat post-quantum cryptography (kesadaran saja, bukan pendalaman — lihat batas scope di `domains/quantum-computing-for-engineers/DOMAIN.md`)

Mini Project: Implementasikan QFT sebagai sirkuit Qiskit (verifikasi terhadap classical DFT via NumPy), lalu implementasikan Shor's algorithm versi kecil (mis. faktorisasi N=15 atau N=21) di simulator, verifikasi faktor yang ditemukan benar secara matematis.

Status: Not started

---

## Module 11 — Lanskap Hardware Kuantum & Era NISQ

Description: Dari "algoritma di atas kertas" ke kenyataan hardware kuantum hari ini — modalitas hardware, noise, decoherence, dan kenapa quantum computer saat ini disebut NISQ (Noisy Intermediate-Scale Quantum), plus eksekusi sirkuit di hardware kuantum sungguhan (IBM Quantum). Klaim spesifikasi vendor di modul ini mengikuti house rule "wajib bersumber & bertanggal" dari `domains/quantum-computing-for-engineers/DOMAIN.md`.

Lessons:

* [ ] 11.1 Modalitas hardware kuantum: superconducting (IBM/Google), trapped-ion (IonQ/Quantinuum), neutral atom (QuEra/Pasqal/Atom Computing), photonic (PsiQuantum/Xanadu) — cara kerja & trade-off qubit count vs fidelity vs kecepatan gate
* [ ] 11.2 Noise & decoherence — T1/T2 time, gate error rate, kenapa hardware nyata berbeda dari simulator ideal
* [ ] 11.3 Pengantar quantum error correction secukupnya — logical vs physical qubit, kenapa fault-tolerant quantum computing masih riset aktif
* [ ] 11.4 Menjalankan sirkuit di hardware kuantum sungguhan — IBM Quantum cloud, transpilation, antrian job, interpretasi hasil yang noisy

Mini Project: Jalankan salah satu sirkuit dari modul sebelumnya (mis. Bell state Module 6 atau Deutsch-Jozsa Module 8) di simulator DAN di hardware kuantum nyata (IBM Quantum free tier), bandingkan histogram hasil — dokumentasikan gap akibat noise secara eksplisit dengan angka, bukan cuma "hasilnya beda".

Status: Not started

---

## Module 12 — Algoritma Near-Term & Ekosistem Quantum Computing Industri

Description: Menutup materi non-capstone dengan gambaran ekosistem industri terkini — algoritma variational (VQE, QAOA) yang didesain untuk hardware NISQ yang noisy, dan orientasi lanskap industri quantum computing secukupnya untuk arah belajar/karier lanjutan.

Lessons:

* [ ] 12.1 Variational Quantum Eigensolver (VQE) — konsep hybrid classical-quantum secukupnya (bukan pendalaman kimia kuantum)
* [ ] 12.2 Quantum Approximate Optimization Algorithm (QAOA) — konsep secukupnya untuk problem optimisasi kombinatorial
* [ ] 12.3 Ekosistem industri: cloud quantum computing (IBM Quantum, Amazon Braket, Azure Quantum), use case realistis vs hype
* [ ] 12.4 Orientasi lanjutan: dari sini ke mana — riset akademik (Nielsen & Chuang lanjut), quantum software engineering, atau quantum algorithm research

Mini Project: Implementasikan QAOA sederhana di Qiskit untuk problem optimisasi kecil (mis. Max-Cut pada graph 4-5 node), bandingkan hasil terhadap solusi brute-force klasik.

Status: Not started

---

## Module 13 — Capstone: Quantum Algorithm Project End-to-End

Description: Mengintegrasikan seluruh Module 1-12 jadi satu capstone Qiskit project penuh — dari matematika sampai eksekusi hardware nyata, mengikuti `domains/quantum-computing-for-engineers/DOMAIN.md` bagian "Project Tiers".

Lessons:

* [ ] 13.1 Menentukan jalur capstone & scope: Grover's algorithm diskalakan lebih besar / Shor's algorithm untuk N lebih besar / kombinasi 2+ algoritma sebagai showcase
* [ ] 13.2 Desain & implementasi sirkuit lengkap, termasuk oracle/circuit construction dari nol
* [ ] 13.3 Eksekusi & validasi: simulator ideal vs hardware nyata, analisis noise, statistik hasil
* [ ] 13.4 Dokumentasi tertulis: matematika di balik algoritma, keputusan desain sirkuit, trade-off simulator vs hardware — submit ke `portofolio/`

Capstone Project: Pilih SATU — (A) **Grover's algorithm untuk search space lebih besar** (5+ qubit) dengan oracle custom kompleks. (B) **Shor's algorithm untuk faktorisasi N yang lebih besar** dari mini project Module 10. (C) **Kombinasi 2+ algoritma** (mis. Deutsch-Jozsa + Grover) sebagai "quantum algorithm showcase" dengan benchmark noise simulator vs hardware nyata untuk semuanya. Semua jalur wajib validasi numerik/statistik eksplisit (bukan "kelihatan jalan sekali"), plus dokumentasi tertulis trade-off desain yang diambil.

Status: Not started

---

# Capstone Project

Description: Lihat Module 13 di atas untuk rincian lengkap tiga jalur capstone (Grover diskalakan / Shor N lebih besar / kombinasi algoritma). Capstone course ini SATU dengan Capstone Project Module 13 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/quantum-computing-for-engineers/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` DITAMBAH keempat lensa Review Style domain ini dari `domains/quantum-computing-for-engineers/DOMAIN.md`: **Mathematical Correctness** (state vector/matrix/tensor product benar dan konsisten dengan kode), **Circuit Correctness** (sirkuit benar-benar mengimplementasikan algoritma yang dimaksud secara struktural), **Quantum Advantage Awareness** (bisa menjelaskan mekanisme speedup, bukan cuma hasil akhirnya), dan **Simulator vs Hardware Reality Check** (kesadaran eksplisit noise/decoherence pada hardware nyata vs simulator ideal, dibuktikan dengan angka). Disimpan ke `portofolio/` disertai dokumentasi trade-off desain.

Status: Not started

---

# Overall Progress

Module Completion: 0/13

Overall Completion: 0%

Current Module: Module 1 — Bilangan Kompleks & Complex Vector Space untuk Quantum Computing (menunggu approval roadmap)

Current Lesson: 1.1 Bilangan kompleks: representasi Cartesian & polar, konjugat, modulus

Next Lesson: 1.1 Bilangan kompleks: representasi Cartesian & polar, konjugat, modulus — kenapa quantum computing butuh ini (amplitude bisa kompleks, bukan cuma real)
