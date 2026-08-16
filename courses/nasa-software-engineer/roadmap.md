# Course Roadmap

## Course Information

* Course Name: NASA Software Engineer — dari 0 sampai Expert
* Category: Aerospace / Mission-Critical Software Engineering (Domain: software-engineering) — career-adjacent specialization di atas fondasi backend yang sudah ada
* Difficulty: Level 0 (belum pernah menulis C/C++, belum pernah menyentuh embedded/RTOS, belum tahu apa itu safety-critical software) → Level 4 (Expert — mampu menulis kode C/C++ yang lolos audit Power of 10/MISRA, memahami arsitektur software embedded/RTOS untuk sistem mission-critical, menjalankan proses V&V setara NASA-STD-8739.8, dan membuat keputusan bahasa/arsitektur yang defensible untuk konteks aerospace)
* Estimated Duration: 14 modul (13 modul konten + 1 modul capstone), 3-5 lesson/modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Baseline Java/Spring Boot backend engineer — lihat `domains/software-engineering/DOMAIN.md` dan `courses/backend-fundamentals/roadmap.md` (asumsi: sudah bisa membangun REST API production-grade, paham database/testing/observability dasar). **Direkomendasikan** (bukan wajib, bisa paralel) sudah menyelesaikan atau sedang berjalan di `courses/computational-thinking/roadmap.md` (struktur data & algoritma — course ini TIDAK mengulang Big-O/struktur data dari nol) dan `courses/git-advanced/roadmap.md` (branching/CM mekanik — course ini fokus ke konfigurasi manajemen ala aerospace, bukan mekanik Git dasar). **Tidak** perlu pengalaman C/C++, embedded, atau aerospace sebelumnya — semuanya dibangun dari 0 di sini.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini dibuat berbarengan (paralel, background agent terpisah) dengan draft course **"Satellite Programming"** (`courses/satellite-programming/roadmap.md`) yang sedang disusun agent lain. Ada overlap topik yang disengaja (C/C++, embedded systems, RTOS, aerospace software, testing) — jadi pembagian batasnya harus eksplisit:

1. **Course ini ("NASA Software Engineer") = fondasi karier + breadth umum.** Ini adalah course "peta besar dan skill dasar" untuk siapapun yang ingin masuk ke software engineering aerospace/mission-critical sebagai spesialisasi karier — mencakup CS/bahasa pemrograman relevan (C/C++/Python, plus catatan posisi Java yang sudah dikuasai), Linux, konsep umum embedded/RTOS, robotika, konsep distributed systems dalam konteks aerospace, budaya rekayasa/standar/safety/testing NASA secara umum, dan survei jenis-jenis misi NASA. **Tidak** ada satu pun modul di sini yang mendalami satelit secara teknis.
2. **`courses/satellite-programming` (sibling) = deep-dive satellite-specific engineering.** Orbital mechanics, arsitektur satelit end-to-end, protokol telemetry/command spesifik, fault tolerance level flight software satelit, simulasi orbit, dan project CubeSat hands-on **BUKAN** bagian dari course ini — itu domain course sibling tersebut. Kalau setelah course ini Anda ingin masuk ke satelit secara spesifik, lanjut ke `courses/satellite-programming/roadmap.md`.
3. **Aturan konkret pembagian per topik yang overlap secara nama:**
   * **C/C++**: course ini mengajarkan bahasa dari 0 sampai level safety-critical umum (Power of 10, MISRA) — `satellite-programming` mengasumsikan C/C++ ini sudah dikuasai dan langsung pakai untuk flight software satelit spesifik.
   * **Embedded & RTOS**: course ini mengajarkan konsep umum (microcontroller, interrupt, scheduling, priority inversion, FreeRTOS dasar) yang berlaku ke SEMUA sistem embedded (rover, instrumen, robot, satelit) — `satellite-programming` mengaplikasikannya ke arsitektur flight software satelit spesifik (bus vs payload, mode management, dll).
   * **Aerospace software & testing**: course ini mengajarkan proses V&V, safety-critical testing (MC/DC, FMEA), dan budaya rekayasa NASA secara umum lintas jenis misi — `satellite-programming` mengaplikasikan proses yang sama ke pengujian subsistem satelit/CubeSat spesifik (thermal-vacuum test, telemetry validation, dll).
   * **Robotics**: course ini murni fondasi (kontrol loop, ROS2 dasar, sensor fusion dasar) relevan untuk rover/robotic arm — bukan bagian dari satelit sama sekali, tidak overlap dengan course sibling.
4. **Kalau kedua course didekati bersamaan**: urutan yang disarankan adalah course ini dulu (fondasi bahasa/embedded/RTOS/safety umum) baru `satellite-programming` (aplikasi spesifik ke satelit) — persis pola `ml-fundamentals` → `computer-vision` di repo ini.

Overlap dengan course lain yang SUDAH ADA di repo ini (dicek eksplisit sebelum course ini ditulis, tidak ada modul yang duplikat):

* **`courses/computational-thinking/roadmap.md`** (14 modul, CS fundamentals + struktur data & algoritma dari 0 di Java): course ini TIDAK mengulang Big-O, struktur data (tree/graph/heap), atau teknik algoritma umum — direkomendasikan sebagai prasyarat paralel. Modul 1 course ini hanya menyinggung ulang secukupnya konsep CS yang langsung relevan ke konteks aerospace (mis. kenapa determinism & bounded memory jadi prioritas berbeda dari backend biasa), bukan re-teaching penuh.
* **`courses/git-advanced/roadmap.md`** (10 modul, branching strategy tim & Git mekanik lanjut): course ini TIDAK mengulang mekanik Git (rebase, branching strategy tim, dll) — Modul 13 course ini ("Engineering Culture, Git Workflows & Process Discipline") mengasumsikan mekanik Git sudah dikuasai dan fokus ke lapisan DI ATASNYA yang spesifik aerospace: configuration management formal (baseline control, Change Control Board), traceability requirement→code→test, dan budaya code review level mission-critical.
* **`courses/system-design/roadmap.md`** (14 modul, arsitektur sistem/distributed systems umum): course ini TIDAK mengulang teori distributed systems umum (CAP theorem, konsensus, load balancing, dll) — Modul 11 course ini ("Distributed Systems & Ground-Space Communication") mengasumsikan fondasi itu (via `system-design`, opsional tapi direkomendasikan) dan fokus HANYA ke apa yang BEDA di konteks aerospace: high-latency/intermittent link, Delay/Disruption Tolerant Networking (DTN), arsitektur ground segment — bukan re-derivasi distributed systems dari nol.
* **`courses/kubernetes/roadmap.md`** dan **`courses/ci-cd/roadmap.md`**: keduanya DevOps/infrastructure umum, tidak overlap konten — disinggung sebagai konteks bahwa ground software/tooling aerospace modern kadang memakai stack yang sama (container, CI/CD), tapi tidak diajarkan ulang di sini.
* **`courses/systems-security-malware-analysis/roadmap.md`** (7 modul, OS/network/malware defensive literacy): tidak overlap — course itu tentang analisis malware & keamanan sistem umum, course ini tentang software correctness/safety untuk sistem mission-critical (domain berbeda: security vs safety-critical engineering). Tidak ada cross-reference yang diperlukan.

Referensi riset yang dipakai menyusun roadmap ini (kategori, riset penuh menyusul saat lesson ditulis — ini masih draft):

1. **Standar & handbook resmi NASA**: [NASA-STD-8739.8B — Software Assurance and Software Safety Standard](https://standards.nasa.gov/standard/nasa/nasa-std-87398), [NASA Software Engineering Handbook (NASA-HDBK-2203)](https://swehb.nasa.gov/), [NPR 7150.2 — NASA Software Engineering Requirements](https://nodis3.gsfc.nasa.gov/), [NASA Systems Engineering Handbook (NASA/SP-2016-6105)](https://www.nasa.gov/reference/systems-engineering-handbook/) — dipakai sebagai kerangka Modul 1, 5, 12, 13.
2. **JPL safety-critical coding**: Gerard J. Holzmann, ["The Power of 10 — Rules for Developing Safety-Critical Code"](https://spinroot.com/gerard/pdf/P10.pdf) (IEEE Computer, 2006), JPL Institutional Coding Standard for the C Programming Language — inti Modul 5. [MISRA C:2012](https://misra.org.uk/) / MISRA C++ guidelines sebagai pelengkap industri.
3. **Embedded systems & RTOS**: dokumentasi resmi [FreeRTOS](https://www.freertos.org/Documentation/RTOS_book.html), kerangka pedagogis ala UT Austin "Embedded Systems" (Coursera/edX), Liu & Layland (1973) untuk rate-monotonic scheduling, studi kasus [Mars Pathfinder priority inversion](https://www.cs.unc.edu/~anderson/teach/comp790/papers/mars_pathfinder_long_version.html) (Glenn Reeves/JPL) — dipakai untuk Modul 8-9.
4. **CS fundamentals & algoritma**: pointer ke `courses/computational-thinking/roadmap.md` (tidak diduplikasi, lihat Catatan Scope).
5. **Linux & sistem**: *The Linux Programming Interface* (Michael Kerrisk), dokumentasi POSIX resmi, *Pro Git* (git-scm.com) sebagai referensi mekanik dasar (bukan Modul 13 yang levelnya di atasnya) — dipakai untuk Modul 2.
6. **C/C++**: *The C Programming Language* (Kernighan & Ritchie), *A Tour of C++* (Bjarne Stroustrup), [cppreference.com](https://en.cppreference.com/) — dipakai untuk Modul 3-4.
7. **Robotika**: dokumentasi resmi [ROS2](https://docs.ros.org/), *Probabilistic Robotics* (Thrun, Burgard, Fox) sebagai referensi konsep (bukan wajib dibaca penuh) — dipakai untuk Modul 10.
8. **Ground-space communication**: overview standar [CCSDS (Consultative Committee for Space Data Systems)](https://public.ccsds.org/) sebagai gambaran umum (protokol spesifik didalami di `satellite-programming`) — dipakai untuk Modul 11.
9. **Studi kasus kegagalan lintas industri**: Mars Climate Orbiter Mishap Investigation Report (1999), Ariane 5 Flight 501 Inquiry Board Report (1996), Nancy Leveson — *Medical Devices: The Therac-25* — dipakai di Modul 12-13 sebagai pelajaran proses, bukan sekadar cerita.

---

# Learning Objectives

* [ ] Memetakan lanskap software engineering NASA/aerospace: jenis misi (crewed, robotic, science, satelit), pusat riset (JPL, GSFC, JSC, MSFC), kelas software (Class A-E per NPR 7150.2), dan jalur karier (civil servant, JPL/Caltech, kontraktor utama)
* [ ] Menguasai Linux & toolchain cross-compilation untuk target embedded/aerospace (shell scripting, Make/CMake, cross-compiler, debugging tooling)
* [ ] Menulis C dari 0 dengan mindset safety-critical sejak awal (manual memory management, undefined behavior awareness)
* [ ] Menguasai C++ modern secukupnya untuk konteks embedded/aerospace (RAII, subset yang aman untuk flight-context — tanpa exception/RTTI di banyak konteks)
* [ ] Menguasai & mampu mengaudit kode terhadap JPL "Power of 10" Rules dan MISRA C/C++, menggunakan static analysis tooling nyata
* [ ] Memakai Python secara tepat untuk tooling ground segment & analisis data aerospace (bukan flight software) — dibedakan eksplisit dari kapan C/C++ wajib dipakai
* [ ] Membuat keputusan bahasa pemrograman (C/C++/Python/Java) yang defensible untuk skenario aerospace nyata, berbasis kekuatan Java yang sudah dikuasai dari baseline backend
* [ ] Memahami fondasi embedded systems: microcontroller, memory-mapped I/O, interrupt, bootloader, debugging JTAG
* [ ] Memahami & mempraktikkan konsep RTOS: real-time scheduling (rate monotonic/EDF), priority inversion (studi kasus Mars Pathfinder) dan solusinya, dasar FreeRTOS
* [ ] Memahami fondasi software robotika: control loop (PID), ROS2 dasar, sensor fusion dasar, arsitektur autonomy stack (perception-planning-control)
* [ ] Memahami apa yang BERBEDA dari distributed systems umum saat konteksnya aerospace: high-latency/intermittent link, Delay/Disruption Tolerant Networking, arsitektur ground segment
* [ ] Menjalankan proses Verification & Validation setara standar NASA: MC/DC coverage, requirements traceability, FMEA, fault tree analysis dasar
* [ ] Menerapkan configuration management & budaya rekayasa level mission-critical: baseline control, Change Control Board, traceability requirement→code→test, code review disiplin tinggi
* [ ] Menyelesaikan Capstone Project: sistem software mission-critical (embedded/robotics) yang menerapkan Power of 10/MISRA, proses V&V, dan dokumentasi traceability penuh

---

# Course Modules

## Module 1 — Lanskap Software Engineering NASA & Aerospace

Description: Peta besar sebelum masuk teknis — jenis-jenis misi NASA (crewed spaceflight, robotic exploration, earth/space science, satelit — dengan catatan eksplisit bahwa satelit didalami di course sibling), pusat riset utama (JPL, GSFC, JSC, MSFC, Ames), klasifikasi software menurut NPR 7150.2 (Class A safety-critical sampai Class E), dan jalur karier nyata (civil servant NASA, JPL/Caltech, kontraktor seperti Lockheed Martin/Boeing/Northrop, New Space seperti SpaceX/Blue Origin/Rocket Lab).

Lessons:

* [ ] 1.1 Survei jenis misi NASA & bagaimana software engineering berperan berbeda di tiap jenis (crewed vs robotic vs science vs satelit)
* [ ] 1.2 Klasifikasi software NPR 7150.2 (Class A-E) — kenapa rigor proses berbeda drastis antar kelas, dan kenapa ini menentukan segalanya di course ini
* [ ] 1.3 Pusat riset & organisasi: JPL vs NASA center lain vs kontraktor utama vs New Space — beda budaya rekayasa & proses
* [ ] 1.4 Jalur karier realistis dari baseline backend Java/Spring Boot Anda — skill gap apa yang course ini isi, dan apa yang masih perlu dicari di luar course ini (mis. clearance, kualifikasi khusus AS)

Mini Project: Peta tertulis (1-2 halaman) yang memetakan minimal 3 jenis misi NASA berbeda ke kelas software NPR 7150.2-nya, plus draft rencana pribadi jalur spesialisasi mana yang ingin dikejar dari course ini (embedded/RTOS, robotics, ground software, atau gabungan).

Status: Not started

---

## Module 2 — Linux & Toolchain Fundamentals untuk Aerospace

Description: Linux mendominasi ground software, flight software (lewat RTOS/embedded Linux), dan seluruh toolchain aerospace — modul ini membangun kemampuan CLI, shell scripting, dan cross-compilation yang jadi prasyarat semua modul teknis berikutnya.

Lessons:

* [ ] 2.1 Linux CLI mendalam untuk konteks engineering: filesystem, permission, process management, shell scripting (Bash) untuk otomasi build/test
* [ ] 2.2 Build system: Make dan CMake dari 0 — kenapa keduanya jadi standar de facto proyek C/C++ aerospace
* [ ] 2.3 Cross-compilation: kenapa target embedded butuh toolchain berbeda dari host, konsep cross-compiler (`arm-none-eabi-gcc` dsb), sysroot
* [ ] 2.4 Debugging tooling dasar: GDB, kenapa debugging embedded (remote/JTAG) berbeda dari debugging aplikasi backend biasa (pointer ke Modul 8 untuk JTAG penuh)

Mini Project: Setup toolchain cross-compilation lengkap (native dulu, lalu target ARM via QEMU atau board nyata kalau tersedia) — build & jalankan program C sederhana, didokumentasikan sebagai runbook reproducible.

Status: Not started

---

## Module 3 — C Programming dari 0, dengan Mindset Safety-Critical

Description: C tetap jadi bahasa dominan flight software karena kontrol penuh atas memory & predictability. Modul ini mengajarkan C dari 0 TAPI dengan kebiasaan aman (bounds checking, tanpa dynamic allocation setelah init) ditanamkan sejak lesson pertama — bukan "belajar C dulu, baru belajar aman belakangan".

Lessons:

* [ ] 3.1 Sintaks dasar C: tipe data, kontrol alur, fungsi — dengan catatan eksplisit tiap konstruk yang jadi sumber bug klasik (bandingkan sekilas dengan Java yang sudah dikuasai: tidak ada GC, tidak ada bounds checking otomatis)
* [ ] 3.2 Pointer & memory management manual: stack vs heap, `malloc`/`free`, kenapa memory leak & dangling pointer jadi kelas bug paling berbahaya di sistem embedded (tidak ada GC yang "menyelamatkan")
* [ ] 3.3 Struct, array, string handling C-style — dan kenapa off-by-one/buffer overflow di C punya konsekuensi lebih fatal daripada di Java
* [ ] 3.4 Undefined behavior: apa itu, kenapa compiler boleh melakukan apa saja saat UB terjadi, contoh UB paling umum (integer overflow signed, dereferencing null, dll)

Mini Project: Program C kecil (mis. parser data sensor sederhana) ditulis dari 0, lalu diaudit dengan static analyzer (`cppcheck`) untuk menemukan bug memory/UB yang mungkin belum disadari — perbaiki semua temuan.

Status: Not started

---

## Module 4 — C++ untuk Sistem Aerospace

Description: C++ dipakai luas di flight software modern (mis. banyak subsistem JPL/Mars rover) untuk abstraksi lebih tinggi tanpa kehilangan kontrol C — tapi dengan subset yang sengaja dibatasi (banyak proyek flight melarang exception/RTTI/dynamic allocation runtime). Modul ini mengajarkan C++ modern DAN kenapa subset itu dibatasi.

Lessons:

* [ ] 4.1 C++ modern dasar: class, RAII (Resource Acquisition Is Initialization) — kenapa RAII adalah jawaban C++ untuk masalah manual memory management Modul 3
* [ ] 4.2 Template dasar secukupnya untuk membaca kode embedded modern (tanpa metaprogramming mendalam — di luar scope Level Expert course ini)
* [ ] 4.3 Kenapa banyak proyek flight software melarang exception, RTTI, dan dynamic allocation setelah inisialisasi — trade-off predictability vs ekspresivitas bahasa
* [ ] 4.4 Pengantar MISRA C++ — apa itu, kenapa ada, bagaimana bentuknya sebagai checklist yang bisa diaudit otomatis (jembatan ke Modul 5)

Mini Project: Refactor mini project C (Modul 3) ke C++ modern dengan RAII, sambil sengaja MENGHINDARI fitur yang dilarang subset flight (exception, dynamic allocation setelah init) — didokumentasikan alasan tiap keputusan desain.

Status: Not started

---

## Module 5 — JPL "Power of 10" Rules & Standar Coding Safety-Critical NASA

Description: Titik masuk resmi ke rekayasa safety-critical — sepuluh aturan Gerard Holzmann/JPL yang jadi acuan industri untuk kode yang harus benar 100% (tidak ada "hotfix di production" di luar angkasa). Mengikuti paper asli Holzmann (2006) dan NASA-STD-8739.8.

Lessons:

* [ ] 5.1 Rule 1-3: kontrol alur sederhana (tanpa `goto`/rekursi tak terbatas), batas atas loop yang fixed, tanpa dynamic memory allocation setelah inisialisasi — rasional tiap aturan (analisis statis harus bisa membuktikan program berhenti & bounded)
* [ ] 5.2 Rule 4-6: fungsi pendek (≤60 baris), density assertion minimum, data scope sekecil mungkin — rasional dari sisi review-ability manusia, bukan cuma tooling
* [ ] 5.3 Rule 7-10: cek return value tiap fungsi, batasi preprocessor, batasi pointer (tanpa pointer-to-pointer, tanpa function pointer kompleks), compile dengan semua warning aktif sebagai error — rasional dari sisi memastikan tooling bisa memverifikasi
* [ ] 5.4 NASA-STD-8739.8 & static analysis tooling nyata: overview standar, praktik dengan tool nyata (mis. `cppcheck`/Clang Static Analyzer sebagai pengganti open-source untuk Coverity/Polyspace komersial)

Mini Project: Audit penuh mini project C++ dari Modul 4 terhadap seluruh 10 Rules + subset MISRA relevan, pakai static analyzer, perbaiki semua pelanggaran, dan tulis laporan singkat kenapa tiap pelanggaran berbahaya dalam konteks flight software.

Status: Not started

---

## Module 6 — Python untuk Tooling Ground Segment & Analisis Data Aerospace

Description: Python TIDAK dipakai untuk flight software (non-deterministic, GC, terlalu lambat/berat untuk real-time embedded) — tapi mendominasi tooling ground segment, automation, dan analisis data di aerospace. Modul ini fokus applied, bukan pengantar Python dari 0 syntax (asumsi sudah bisa baca kode dari baseline Java).

Lessons:

* [ ] 6.1 Python untuk aerospace tooling: kenapa dipakai luas di ground segment (JPL, mission ops) tapi TIDAK PERNAH untuk flight software — kontras eksplisit dengan Modul 3-5
* [ ] 6.2 Data processing dengan NumPy/Pandas untuk data aerospace tipikal (log, data sensor, data telemetry sederhana — protokol telemetry spesifik didalami di `satellite-programming`)
* [ ] 6.3 Automation & scripting: parsing log misi, otomasi test/build (menyambung ke Modul 2), integrasi dengan toolchain C/C++
* [ ] 6.4 Visualisasi data operasional sederhana (Matplotlib) untuk mendukung analisis anomali/telemetry

Mini Project: Tool Python yang mem-parsing dataset log/telemetry sampel (data sintetis atau publik), mendeteksi anomali sederhana (mis. nilai sensor di luar rentang), dan menghasilkan laporan/visualisasi ringkas.

Status: Not started

---

## Module 7 — Posisi Java & Keputusan Bahasa dalam Rekayasa Aerospace

Description: Modul singkat tapi penting — memakai kekuatan Java/Spring Boot yang SUDAH dikuasai (bukan mengajarkan Java dari 0) untuk membangun penilaian: di mana Java relevan di ekosistem aerospace (tooling mission ops, beberapa aplikasi ground seperti NASA WorldWind), dan di mana secara eksplisit TIDAK relevan (flight software — GC pause tidak predictable, footprint memory terlalu besar untuk banyak target embedded).

Lessons:

* [ ] 7.1 Di mana Java benar-benar dipakai di aerospace hari ini: ground software, mission operations tooling, dashboard/visualisasi (mis. NASA WorldWind) — dan kenapa domain ini cocok untuk GC/JVM
* [ ] 7.2 Kenapa Java (hampir) tidak pernah dipakai untuk flight software: non-determinism GC, footprint runtime, real-time guarantee yang tidak bisa dijamin JVM standar
* [ ] 7.3 Kerangka keputusan bahasa: matriks C/C++ vs Python vs Java berdasarkan constraint (real-time? memory terbatas? safety-critical? tooling/ground?) — sintesis Modul 3-7
* [ ] 7.4 Polyglot engineering judgment sebagai skill senior: bagaimana tim aerospace nyata benar-benar mencampur bahasa ini dalam satu misi (flight software C/C++, ground tooling Python/Java)

Mini Project: Tulisan keputusan (decision matrix + justifikasi tertulis) untuk 5 skenario software aerospace berbeda (mis. flight controller motor, dashboard mission ops, parser data telemetry, sistem command uplink, tool analisis anomali pasca-misi) — pilih bahasa yang tepat untuk tiap skenario dengan alasan teknis eksplisit.

Status: Not started

---

## Module 8 — Fondasi Embedded Systems

Description: Titik masuk ke dunia hardware-dekat — bagaimana software benar-benar berjalan di atas microcontroller tanpa OS penuh (atau dengan RTOS tipis), fondasi yang dibutuhkan SEBELUM RTOS (Modul 9) dan robotika (Modul 10). Konsep umum, tidak spesifik satelit.

Lessons:

* [ ] 8.1 Microcontroller vs microprocessor vs "komputer biasa" — kenapa target embedded aerospace punya constraint drastis berbeda (memory kilobytes, tanpa OS penuh, radiation-hardened di sistem nyata)
* [ ] 8.2 Memory-mapped I/O & register-level programming: cara software "bicara" langsung ke hardware tanpa driver OS
* [ ] 8.3 Interrupt: apa itu, ISR (Interrupt Service Routine), kenapa desain interrupt yang salah adalah sumber bug embedded paling umum
* [ ] 8.4 Bootloader & proses startup: apa yang terjadi sebelum `main()` berjalan, flashing firmware, debugging via JTAG/SWD

Mini Project: Program embedded (di board nyata seperti Arduino/STM32, atau emulator QEMU kalau board tidak tersedia) yang membaca sensor lewat interrupt (bukan polling) dan mengontrol output — didokumentasikan termasuk skema memory-mapped register yang dipakai.

Status: Not started

---

## Module 9 — Real-Time Operating Systems (RTOS)

Description: Kebanyakan flight software modern berjalan di atas RTOS, bukan bare-metal murni atau Linux penuh — modul ini membangun model mental real-time scheduling dan mempelajari studi kasus kegagalan paling terkenal di sejarah software NASA: priority inversion Mars Pathfinder (1997).

Lessons:

* [ ] 9.1 Apa itu "real-time": hard real-time vs soft real-time, kenapa "cepat rata-rata" tidak cukup — deadline yang harus selalu terpenuhi
* [ ] 9.2 Real-time scheduling: rate monotonic scheduling & Earliest Deadline First (EDF) — dasar teori Liu & Layland (1973) secukupnya untuk paham jaminan schedulability
* [ ] 9.3 Studi kasus Mars Pathfinder (1997): priority inversion yang menyebabkan sistem reset berulang di Mars, bagaimana tim JPL mendiagnosis dari jarak jutaan kilometer, dan solusi priority inheritance
* [ ] 9.4 Praktik dengan FreeRTOS: task, priority, mutex dengan priority inheritance, queue antar-task

Mini Project: Program FreeRTOS (di emulator atau board) yang SENGAJA mereplikasi kondisi priority inversion ala Mars Pathfinder (task prioritas rendah memegang resource yang dibutuhkan task prioritas tinggi, diblokir task prioritas menengah), dibuktikan bug-nya secara terukur, lalu diperbaiki dengan priority inheritance mutex — dibandingkan hasilnya before/after.

Status: Not started

---

## Module 10 — Fondasi Software Robotika

Description: Rover Mars, robotic arm, dan sistem otonom lain butuh lapisan software berbeda dari embedded murni — modul ini membangun fondasi kontrol & framework robotika modern, relevan untuk eksplorasi robotic (bukan satelit).

Lessons:

* [ ] 10.1 Control loop dasar: PID controller — cara kerja, tuning dasar, kenapa ini fondasi hampir semua kontrol motor/aktuator robotik
* [ ] 10.2 ROS2 (Robot Operating System 2) dasar: node, topic, service, kenapa arsitektur publish-subscribe cocok untuk sistem robotik modular
* [ ] 10.3 Sensor fusion dasar: menggabungkan data multi-sensor (mis. IMU + encoder) untuk estimasi state yang lebih akurat — pengantar konsep (Kalman filter secukupnya untuk intuisi, bukan derivasi penuh)
* [ ] 10.4 Autonomy stack overview: perception → planning → control sebagai kerangka besar sistem otonom (mis. rover menghindari rintangan) — peta konseptual, bukan implementasi penuh tiap lapisan

Mini Project: Node ROS2 sederhana yang menjalankan PID control loop pada simulasi (mis. menjaga posisi/kecepatan target sebuah aktuator simulasi), didokumentasikan termasuk proses tuning PID dan grafik respons sistem.

Status: Not started

---

## Module 11 — Distributed Systems & Ground-Space Communication (Konteks Aerospace)

Description: Modul ini SENGAJA tidak mengulang teori distributed systems umum (`courses/system-design` sudah mencakupnya) — fokus HANYA ke apa yang berbeda drastis saat distributed system-nya melibatkan link ke luar angkasa: latency tinggi (menit ke Mars), link yang bisa terputus (occlusion, jadwal ground station), dan bagaimana arsitektur ground segment menangani ini.

Lessons:

* [ ] 11.1 Kenapa asumsi distributed systems biasa (`system-design`) gagal di konteks space: latency dalam menit-jam (bukan milidetik), link intermittent (bukan "eventually consistent network" tapi benar-benar terputus terjadwal)
* [ ] 11.2 Delay/Disruption Tolerant Networking (DTN): konsep store-and-forward untuk link yang predictably terputus, kenapa TCP/IP biasa tidak cocok
* [ ] 11.3 Overview CCSDS (Consultative Committee for Space Data Systems): standar internasional komunikasi ruang angkasa secukupnya untuk paham lanskap (protokol spesifik & implementasi didalami di `satellite-programming`)
* [ ] 11.4 Arsitektur ground segment secara konseptual: ground station, mission control, command/telemetry pipeline tingkat tinggi — peta besar, bukan implementasi protokol

Mini Project: Design document (bukan implementasi penuh) untuk arsitektur komunikasi ground-space suatu misi hipotetis (mis. rover Mars) — menjelaskan bagaimana DTN & jadwal ground station diakomodasi dalam desain, dibandingkan eksplisit dengan bagaimana desain itu akan berbeda kalau memakai asumsi distributed system biasa (dan kenapa itu akan gagal).

Status: Not started

---

## Module 12 — Verification, Validation & Safety-Critical Testing

Description: Testing di aerospace bukan "tulis beberapa unit test" — ini proses formal yang harus membuktikan software benar SEBELUM diluncurkan (tidak ada patch setelah peluncuran untuk banyak sistem). Modul ini membangun proses V&V setara standar NASA, melengkapi (bukan mengulang) `standards/TESTING_STANDARDS.md` yang levelnya backend biasa.

Lessons:

* [ ] 12.1 Proses V&V (Verification & Validation): beda "apakah dibangun dengan benar" (verification) vs "apakah membangun yang benar" (validation), requirements traceability dari requirement → desain → kode → test
* [ ] 12.2 MC/DC coverage (Modified Condition/Decision Coverage): kenapa code coverage biasa (line/branch) tidak cukup untuk software Class A/B, cara mengukur & mencapai MC/DC
* [ ] 12.3 Fault Tree Analysis (FTA) & Failure Modes and Effects Analysis (FMEA): teknik sistematis memetakan cara sistem bisa gagal SEBELUM gagal beneran
* [ ] 12.4 Studi kasus kegagalan lintas industri: Mars Climate Orbiter (1999, unit mismatch), Ariane 5 Flight 501 (1996, integer overflow reuse code), Therac-25 (race condition medical device) — pelajaran proses, bukan sekadar cerita

Mini Project: Ambil fungsi safety-critical dari mini project Modul 5 atau 9, tulis test suite yang mencapai MC/DC coverage penuh, plus FMEA sederhana (tabel: failure mode → efek → severity → mitigasi) untuk komponen yang sama.

Status: Not started

---

## Module 13 — Engineering Culture, Configuration Management & Process Discipline

Description: Modul penutup non-capstone — lapisan proses & budaya yang membuat rekayasa NASA-grade berbeda dari engineering biasa, DI ATAS mekanik Git (`courses/git-advanced` sebagai prasyarat opsional): configuration management formal, Change Control Board, dan disiplin dokumentasi/review yang tidak bisa dikompromikan.

Lessons:

* [ ] 13.1 Configuration management formal: baseline control, versioning requirement/desain/kode secara sinkron — beda dari sekadar `git tag` biasa
* [ ] 13.2 Change Control Board (CCB): bagaimana perubahan ke sistem safety-critical yang sudah baseline harus melalui review formal multi-pihak, bukan sekadar approve PR
* [ ] 13.3 Traceability matrix requirement → kode → test sebagai artefak wajib, bukan dokumentasi opsional — kenapa auditor harus bisa menelusuri setiap baris kode balik ke requirement asalnya
* [ ] 13.4 Budaya code review level mission-critical: sintesis dari studi kasus Modul 12 — bagaimana proses review yang lebih lambat & lebih ketat sebenarnya adalah fitur, bukan inefisiensi, dalam konteks yang tidak bisa "hotfix di production"

Mini Project: Simulasikan proses Change Control Board untuk sebuah perubahan kode yang diusulkan pada modul safety-critical dari Modul 5/9/12 — buat dokumen: change request, dampak analysis, traceability update, dan "notulen" keputusan CCB (approve/reject/revisi) dengan alasan eksplisit.

Status: Not started

---

## Module 14 — Capstone: NASA-Grade Mission Software Portfolio Project

Description: Mengintegrasikan seluruh Module 1-13 menjadi satu portfolio project yang membuktikan kemampuan menulis & memproses software mission-critical end-to-end — dari bahasa, embedded/RTOS, sampai V&V dan dokumentasi proses.

Lessons:

* [ ] 14.1 Menentukan scope capstone: pilih SATU domain aplikasi (embedded controller safety-critical, subsistem robotik/rover, atau tooling ground segment) — eksplisit BUKAN satelit (itu capstone `satellite-programming`)
* [ ] 14.2 Implementasi inti sesuai Power of 10/MISRA (Modul 5) dan, kalau embedded/RTOS, prinsip Modul 8-9
* [ ] 14.3 Proses V&V penuh: test suite dengan MC/DC coverage, FMEA, traceability matrix requirement→kode→test (Modul 12-13)
* [ ] 14.4 Dokumentasi final ala CCB-ready: README production-grade, traceability matrix, dan "Engineering Decisions" (kenapa pilih bahasa/arsitektur X), submit ke `portofolio/`

Capstone Project: Bangun satu sistem software mission-critical skala kecil-menengah — contoh pilihan: (A) **Embedded Safety-Critical Controller**: kontroler C/C++ untuk aktuator/sensor simulasi (mis. kontrol suhu/tekanan) yang lolos audit Power of 10 penuh, jalan di atas FreeRTOS, dengan MC/DC test suite. (B) **Robotic Subsystem**: node ROS2 dengan PID control loop + sensor fusion dasar untuk skenario navigasi sederhana, didokumentasikan dengan FMEA. (C) **Ground Tooling**: sistem Python untuk parsing/analisis telemetry + dashboard sederhana, dengan requirement traceability penuh ke tiap fitur. Semua jalur WAJIB: audit Power of 10/MISRA (untuk jalur C/C++), MC/DC test coverage untuk komponen inti, FMEA, dan traceability matrix requirement→kode→test.

Status: Not started

---

# Capstone Project

Description: Lihat Module 14 di atas untuk rincian lengkap tiga jalur capstone (Embedded Safety-Critical Controller / Robotic Subsystem / Ground Tooling). Capstone course ini SATU dengan Capstone Project Module 14 — tidak ada proyek terpisah tambahan di luar modul.

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` DITAMBAH Review Style domain software-engineering (`domains/software-engineering/DOMAIN.md`) DITAMBAH lensa khusus safety-critical: **Power of 10/MISRA Compliance** (untuk jalur C/C++, wajib lolos audit static analyzer), **V&V Rigor** (MC/DC coverage tercapai untuk komponen inti, FMEA lengkap), **Traceability** (setiap requirement bisa ditelusuri ke kode dan test-nya), dan **Process Discipline** (dokumentasi Engineering Decisions eksplisit, bukan sekadar kode jalan). Disimpan ke `portofolio/`.

Status: Not started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Survei jenis misi NASA & bagaimana software engineering berperan berbeda di tiap jenis (crewed vs robotic vs science vs satelit)
