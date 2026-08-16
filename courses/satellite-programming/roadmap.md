# Course Roadmap

## Course Information

* Course Name: Satellite Programming — dari 0 sampai Expert
* Category: Aerospace / Embedded Systems Specialization (Domain: software-engineering — lihat `domains/software-engineering/DOMAIN.md`)
* Difficulty: Level 0 (backend engineer solid di Java/Spring Boot, tapi belum pernah menulis C/C++, belum pernah menyentuh embedded/RTOS, belum pernah menghitung orbital mechanics) → Level 4 (Expert — mampu merancang arsitektur flight software satelit lengkap, menurunkan & menghitung sendiri parameter orbit, membangun sistem telemetry/command end-to-end, dan menyelesaikan capstone CubeSat simulation project yang portfolio-ready)
* Estimated Duration: 14 modul (12 modul konten + 1 modul integrasi subsistem + 1 modul capstone), 4-5 lesson/modul (20-45 menit/lesson sesuai `ai-los/AI-LOS.md`). Karena domain ini applied-heavy (matematika orbital + kode C/C++ + simulasi), pace realistis ≈ 6-8 bulan pada 5-10 jam/minggu.
* Prerequisites: Programming solid (Java/Spring Boot dari `courses/backend-fundamentals/roadmap.md` sebagai baseline logika & disiplin engineering — bukan syntax spesifik yang dipakai di sini). **Tidak** perlu pengalaman C/C++ atau embedded sebelumnya — Module 2 membangun C/C++ dari nol secukupnya untuk flight software, dan Module 3 membangun konsep embedded/RTOS secukupnya untuk konteks satelit (lihat Catatan Scope poin 1 untuk kedalaman yang disengaja dangkal di dua modul ini). Aljabar vektor & trigonometri tingkat SMA cukup untuk Module 1 (orbital mechanics) — kalkulus dasar (turunan/integral) membantu tapi tidak wajib, karena hasil kalkulus yang dipakai (persamaan gerak, hukum Kepler) di-derive secara konseptual, bukan pembuktian penuh. **Bekerja sangat baik berdampingan dengan** `courses/nasa-software-engineer/roadmap.md` (kalau sudah ada) untuk fondasi CS/embedded/RTOS/safety-culture umum — lihat "Catatan Scope" di bawah untuk pembagian tanggung jawab eksplisit antara kedua course.

---

# Catatan Scope (baca dulu sebelum approve)

1. **Hubungan dengan `courses/nasa-software-engineer/roadmap.md`** (course sejenis yang sedang di-draft paralel, mungkin belum ada di filesystem saat roadmap ini dibuat — cross-reference ini forward-looking dan perlu disinkronkan ulang saat kedua roadmap sudah final). Course itu dirancang sebagai fondasi luas: CS fundamentals umum, C/C++/Python/Java, Linux, konsep embedded/RTOS generik, robotics, distributed systems, budaya/standar/safety/testing/Git ala aerospace software engineering, dan survei jenis-jenis misi NASA. **Course ini ("Satellite Programming") sengaja TIDAK mengulang fondasi itu** — course ini adalah spesialisasi teknis dalam-dalam ke SATU domain aplikasi: satelit/CubeSat. Pembagian eksplisit:
   * C/C++ (Module 2) di course ini **bukan** "belajar C/C++ dari nol secara umum" — ini C/C++ secukupnya untuk membaca/menulis flight software (memory-constrained, no dynamic allocation di hot path, fixed-point/embedded idiom). Kalau `nasa-software-engineer` sudah pernah diambil, Module 2 akan terasa seperti refresher applied, bukan materi baru.
   * Embedded/RTOS (Module 3) di course ini **bukan** teori RTOS generik (scheduling algorithm, context switch mendalam) — itu didorong ke `nasa-software-engineer`. Module 3 course ini fokus ke "kenapa satelit butuh RTOS" dan pola RTOS yang spesifik dipakai flight software (task priority untuk ADCS vs telemetry, watchdog untuk radiation-induced fault).
   * Testing/safety/Git culture ala aerospace **tidak** dibahas generik di sini — course ini mengasumsikan disiplin itu (atau merujuk balik ke `nasa-software-engineer`) dan langsung menerapkannya ke konteks CubeSat (mis. Module 11 testing adalah hardware-in-the-loop testing satelit, bukan unit testing C generik).
   * Yang **sepenuhnya milik course ini, tidak ada di course sebelah**: orbital mechanics (Module 1), arsitektur subsistem satelit power/ADCS/comms/payload (Module 4), flight software architecture pattern spesifik satelit (Module 5), sensor fusion untuk attitude determination (Module 6), protokol telemetry/command (TM/TC — Module 7-8), komunikasi ground station (Module 9), fault tolerance/redundancy design spesifik spacecraft (Module 10), tooling simulasi orbit/satelit (Module 12), dan capstone CubeSat (Module 14).
   * Jika `nasa-software-engineer` belum pernah diambil, Module 2-3 course ini tetap cukup untuk lanjut — keduanya punya "just enough" bridge, bukan wajib prasyarat keras. Prerequisites di atas mencerminkan ini eksplisit.

2. **Kenapa C/C++, bukan Java/Spring Boot** — meski stack utama repo ini Java. Flight software satelit nyata (cFS milik NASA, F Prime milik JPL, firmware ADCS/EPS/COMMS komersial) berjalan di atas C/C++ karena constraint keras: memory footprint kecil (ratusan KB-beberapa MB RAM), tidak ada garbage collector yang predictable untuk real-time deadline, dan toolchain cross-compile untuk mikrokontroler/prosesor radiation-hardened tidak punya JVM yang matang. ini bukan preferensi — ini kondisi keras industri aerospace embedded. Course ini **tidak meninggalkan** kebiasaan disiplin dari stack Java (mengikuti pola `courses/computer-vision` & `courses/rust` yang juga applied-first di bahasa non-Java): setiap modul C/C++ eksplisit membandingkan idiom dengan padanan Java (mis. "kenapa tidak ada `new` sembarangan di sini" vs GC Java) supaya transfer mental model terasa alami, bukan mulai dari nol kognitif.

3. **Kedalaman matematika orbital mechanics (Module 1) sengaja "cukup untuk insinyur", bukan "cukup untuk astrodinamikawan"**. Mengikuti pendekatan Vallado *Fundamentals of Astrodynamics and Applications* bab-bab awal: hukum Kepler, elemen orbit klasik, dan propagasi orbit dua-body diturunkan secara konseptual dengan validasi numerik (Python/kode kecil), bukan derivasi variational calculus penuh dari persamaan gerak n-body. Ini cukup untuk memahami kenapa satelit bergerak seperti itu dan untuk menulis kode ground station/mission planning dasar — derivasi orbital mechanics tingkat riset (perturbation theory penuh, orbit determination presisi tinggi) di luar scope, ditandai eksplisit sebagai "di luar cakupan" di Module 1.

4. **Capstone (Module 14) adalah simulasi CubeSat berbasis software, bukan hardware fisik**. Course ini tidak mengasumsikan akses ke hardware satelit sungguhan atau ground station radio nyata — capstone dibangun di atas simulator (mis. NASA cFS di lingkungan simulasi, atau custom simulator Module 12) dan protokol TM/TC yang diuji lewat loopback/simulasi, bukan uplink/downlink RF sungguhan. Ini konsisten dengan level "Level 0 backend engineer mengeksplorasi aerospace sebagai spesialisasi baru" — jalur hardware-in-the-loop sungguhan (radio nyata, satellite bus fisik) adalah extension di luar scope course ini, disebutkan sebagai kemungkinan lanjutan di Module 14 tapi tidak dibangun di sini.

Referensi riset yang dipakai menyusun roadmap ini (dikategorikan, riset draft — belum sitasi lengkap per lesson):

* **CubeSat design specifications**: [CubeSat Design Specification (CDS) — Cal Poly SLO / CubeSat community standard](https://www.cubesat.org/) — spesifikasi bentuk/ukuran/interface standar CubeSat (1U/2U/3U, dll), dipakai sebagai kerangka Module 4 & 14.
* **Open-source flight software frameworks**: [NASA cFS (core Flight System)](https://cfs.gsfc.nasa.gov/) — arsitektur flight software modular yang benar-benar dipakai NASA, dipakai sebagai referensi arsitektur Module 5 & tooling Module 12; [F Prime (F') — JPL/NASA](https://fprime.jpl.nasa.gov/) — framework flight software component-based dari JPL, dipakai untuk CubeSat & instrument kecil, referensi utama pola arsitektur Module 5 dan capstone Module 14.
* **Orbital mechanics textbook**: David Vallado, *Fundamentals of Astrodynamics and Applications* — referensi utama Module 1, pendekatan "cukup untuk insinyur" seperti dijelaskan poin 3 di atas.
* **Komunitas amatir radio/CubeSat**: [AMSAT (Radio Amateur Satellite Corporation)](https://www.amsat.org/) — referensi praktis untuk komunikasi ground station amatir, telemetry beacon format, dan tracking satelit, dipakai di Module 9.
* **Sumber tambahan untuk konteks subsistem & fault tolerance**: dokumentasi teknis NASA/ESA tentang desain subsistem satelit (power/ADCS/thermal/comms) dan pola redundancy spacecraft (radiation-hardened design, triple modular redundancy) — dipakai di Module 4 & 10, dikumpulkan lebih rinci saat penulisan lesson penuh (bukan di tahap draft ini).

---

# Learning Objectives

* [ ] Menguasai orbital mechanics dasar: hukum Kepler, elemen orbit klasik (semi-major axis, eccentricity, inclination, dll), dan mampu menghitung/propagasi orbit dua-body secara numerik
* [ ] Menulis C/C++ secukupnya untuk flight software: memory-constrained programming, fixed-point arithmetic, menghindari dynamic allocation di hot path, idiom embedded yang aman
* [ ] Memahami kenapa satelit butuh RTOS dan pola RTOS spesifik flight software (task priority, watchdog, deterministic scheduling untuk subsistem kritis)
* [ ] Memahami arsitektur subsistem satelit lengkap: power (EPS), attitude determination & control (ADCS), komunikasi (COMMS), payload — dan bagaimana mereka berinteraksi lewat bus data on-board
* [ ] Mampu merancang arsitektur flight software spesifik satelit mengikuti pola cFS/F Prime: modular, message-passing antar subsistem, fault-isolated
* [ ] Memahami & mengimplementasikan sensor fusion dasar untuk attitude determination (gyroscope, magnetometer, sun sensor → estimasi orientasi satelit)
* [ ] Menguasai protokol telemetry (TM): format paket, prioritas data, downlink terjadwal vs event-driven
* [ ] Menguasai protokol command (TC): uplink command, validasi, command sequencing, safety interlock sebelum eksekusi command kritis
* [ ] Mampu membangun sistem komunikasi ground station dasar: link budget secukupnya, protokol AX.25/pola komunitas AMSAT, scheduling pass satelit
* [ ] Merancang fault tolerance & redundancy spesifik spacecraft: radiation-induced fault (single event upset), triple modular redundancy, safe mode/fail-safe design
* [ ] Menggunakan tooling simulasi satelit (orbit propagator, flight software simulator seperti cFS/F Prime di lingkungan simulasi) untuk validasi desain sebelum "terbang"
* [ ] Menguasai testing spesifik aerospace: hardware-in-the-loop (simulasi), fault injection testing, regression testing untuk flight software safety-critical
* [ ] Mengintegrasikan seluruh subsistem jadi satu flight software stack yang koheren
* [ ] Menyelesaikan Capstone Project: simulasi CubeSat end-to-end (orbit propagation + flight software + TM/TC + fault tolerance) yang portfolio-ready

---

# Course Modules

## Module 1 — Orbital Mechanics: Fondasi Matematis

Description: Sebelum menyentuh satu baris kode flight software, pahami dulu benda yang dikendalikan software itu benar-benar bergerak seperti apa di orbit. Modul ini membangun fondasi orbital mechanics "cukup untuk insinyur" (lihat Catatan Scope poin 3), mengikuti Vallado *Fundamentals of Astrodynamics and Applications* bab-bab awal.

Lessons:

* [ ] 1.1 Hukum Kepler (I-III) dan model dua-body — kenapa orbit berbentuk elips, bukan lingkaran sempurna
* [ ] 1.2 Elemen orbit klasik (Keplerian elements): semi-major axis, eccentricity, inclination, RAAN, argument of perigee, true anomaly — apa arti fisik masing-masing
* [ ] 1.3 Orbit tipe umum untuk satelit kecil: LEO (Low Earth Orbit), SSO (Sun-Synchronous Orbit) — kenapa CubeSat hampir selalu di LEO/SSO, trade-off ketinggian vs coverage vs radiation exposure
* [ ] 1.4 Propagasi orbit dasar & ground track: menghitung posisi satelit dari waktu ke waktu (two-body propagation sederhana), memprediksi kapan satelit "lewat" di atas ground station
* [ ] 1.5 Python: skrip kecil propagator orbit dua-body dari elemen Keplerian, plot ground track, validasi terhadap data TLE (Two-Line Element) satelit nyata publik

Mini Project: Skrip Python yang mengambil elemen orbit (TLE) satu CubeSat nyata yang publik (mis. dari Celestrak), menghitung posisi & ground track selama 24 jam, dan memprediksi jendela waktu kapan satelit itu terlihat dari lokasi ground station tertentu (elevation angle di atas horizon).

Status: Not started

---

## Module 2 — C/C++ untuk Flight Software

Description: **Bukan** pengantar C/C++ generik dari nol (lihat Catatan Scope poin 1) — modul ini membangun C/C++ secukupnya untuk membaca & menulis flight software: constraint memory, kenapa dynamic allocation dihindari di hot path, dan idiom embedded yang aman. Setiap konsep dijembatani eksplisit ke padanan mental model Java.

Lessons:

* [ ] 2.1 Dari Java ke C: perbedaan fundamental (manual memory management, tidak ada GC, pointer vs reference) — kenapa ini penting untuk sistem real-time embedded
* [ ] 2.2 Memory model C/C++: stack vs heap, kenapa flight software menghindari `malloc`/`new` di hot path (unpredictable timing, fragmentation risk di sistem yang harus jalan bertahun-tahun tanpa reboot)
* [ ] 2.3 Fixed-size buffer, static allocation, dan struct packing untuk representasi paket data (telemetry/command) — pola yang akan dipakai lagi di Module 7-8
* [ ] 2.4 Fixed-point arithmetic secukupnya — kenapa sebagian flight software menghindari floating-point di path kritis (determinisme, konsistensi across hardware), kapan floating-point masih aman dipakai
* [ ] 2.5 Idiom C/C++ embedded yang aman: RAII secukupnya di C++ untuk resource management tanpa exception, defensive coding (null check, bounds check) yang jadi kebiasaan default

Mini Project: Implementasi struct paket data telemetry sederhana di C (fixed-size, packed struct) dengan fungsi serialize/deserialize manual (tanpa library eksternal), diuji dengan beberapa skenario data termasuk edge case (buffer penuh, data corrupt) — dibandingkan eksplisit dengan bagaimana hal yang sama akan terlihat di Java (DTO + Jackson) untuk menegaskan kenapa pendekatan ini berbeda di dunia embedded.

Status: Not started

---

## Module 3 — Embedded Systems & RTOS untuk Konteks Satelit

Description: **Bukan** teori RTOS generik (scheduling algorithm mendalam, context switch internals — itu domain `nasa-software-engineer`, lihat Catatan Scope poin 1). Modul ini fokus sempit: kenapa satelit butuh RTOS, dan pola RTOS yang spesifik dipakai flight software.

Lessons:

* [ ] 3.1 Kenapa satelit butuh RTOS, bukan OS umum (Linux biasa) atau bare-metal loop sederhana — determinism, task priority untuk subsistem safety-critical (ADCS) vs subsistem best-effort (payload data)
* [ ] 3.2 Konsep task/thread priority di RTOS umum (FreeRTOS/RTEMS sebagai contoh nyata yang dipakai misi satelit kecil) secukupnya untuk paham trade-off desain, bukan implementasi RTOS dari nol
* [ ] 3.3 Watchdog timer — pola paling khas embedded aerospace: kenapa sistem harus "membuktikan diri masih hidup" secara berkala, apa yang terjadi kalau watchdog timeout (reset paksa)
* [ ] 3.4 Radiation-induced fault di lingkungan orbit: Single Event Upset (SEU) — kenapa RAM/prosesor di orbit bisa "salah bit" karena radiasi kosmik, dan kenapa ini mendorong desain software yang defensif (checksum, redundant computation) — jembatan konseptual ke Module 10
* [ ] 3.5 Pola task scheduling flight software nyata: task ADCS (frekuensi tinggi, prioritas tinggi) vs task telemetry downlink (frekuensi rendah, prioritas lebih rendah) vs task command processing (event-driven, prioritas sedang) — studi kasus dari dokumentasi cFS

Mini Project: Diagram/tabel desain task scheduling untuk CubeSat hipotetis (list task: ADCS control loop, telemetry beacon, command listener, payload data collection), tentukan priority & frekuensi tiap task dengan justifikasi eksplisit, plus tulisan pendek menjelaskan skenario watchdog timeout dan bagaimana sistem seharusnya recover.

Status: Not started

---

## Module 4 — Arsitektur Subsistem Satelit: Power, ADCS, Comms, Payload

Description: Peta besar "apa saja yang ada di dalam satelit" sebelum masuk ke flight software yang mengendalikannya — power system, attitude determination & control, komunikasi, dan payload, plus bagaimana mereka terhubung lewat bus data on-board. Mengikuti CubeSat Design Specification (CDS) dan dokumentasi teknis subsistem NASA/ESA.

Lessons:

* [ ] 4.1 Electrical Power System (EPS): solar panel, battery, power budget — kenapa power adalah constraint paling ketat di CubeSat kecil (1U-3U), power budgeting sebagai keputusan desain lintas-subsistem
* [ ] 4.2 Attitude Determination & Control System (ADCS): apa yang dikendalikan (orientasi satelit di 3 sumbu), sensor yang dipakai (gyroscope, magnetometer, sun sensor, star tracker) dan aktuator (reaction wheel, magnetorquer) — overview sebelum deep-dive sensor fusion di Module 6
* [ ] 4.3 Communication Subsystem (COMMS): radio uplink/downlink, band frekuensi umum CubeSat (UHF/VHF/S-band), antenna deployment — konteks untuk protokol TM/TC di Module 7-9
* [ ] 4.4 Payload: instrumen sains/kamera/eksperimen sebagai "alasan satelit itu ada" — bagaimana payload berinteraksi dengan subsistem lain (power budget, data storage, downlink bandwidth) tanpa mengganggu subsistem kritis
* [ ] 4.5 On-board data bus & interkoneksi subsistem: pola komunikasi antar-subsistem (I2C/SPI/CAN sebagai contoh nyata), kenapa isolasi antar-subsistem penting untuk fault containment (jembatan ke Module 5 & 10)

Mini Project: Dokumen desain subsistem (block diagram + tabel) untuk CubeSat 3U hipotetis — mendefinisikan subsistem apa saja yang ada, sensor/aktuator per subsistem, power budget kasar per subsistem, dan bagaimana mereka terhubung lewat data bus — mengikuti struktur dokumen CDS.

Status: Not started

---

## Module 5 — Flight Software Architecture Patterns

Description: Dari peta subsistem (Module 4) ke arsitektur software yang mengendalikannya — pola arsitektur flight software spesifik satelit: modular, message-passing antar-komponen, fault-isolated. Mengikuti arsitektur NASA cFS (core Flight System) dan F Prime (JPL) sebagai dua referensi framework nyata yang berbeda pendekatan.

Lessons:

* [ ] 5.1 Kenapa flight software TIDAK dirancang sebagai satu monolithic loop besar — kebutuhan modularitas untuk fault isolation (satu subsistem crash tidak boleh menjatuhkan seluruh satelit)
* [ ] 5.2 Pola arsitektur cFS: aplikasi sebagai modul independen, Software Bus (message passing) sebagai tulang punggung komunikasi antar-modul — dibandingkan dengan pola event-driven/message queue yang familiar dari backend (Kafka dari `standards/`, tapi versi jauh lebih ringan & deterministic)
* [ ] 5.3 Pola arsitektur F Prime: component-based, port-based connection (input/output port eksplisit antar-komponen), command/telemetry/event sebagai first-class concept di level framework
* [ ] 5.4 State machine sebagai pola sentral flight software: mode satelit (safe mode, nominal mode, payload mode) sebagai state machine eksplisit, transisi mode yang dijaga ketat (tidak sembarang command bisa mengubah mode)
* [ ] 5.5 Fault containment di level arsitektur: bagaimana desain modular Module 5.1-5.3 secara langsung mendukung fault tolerance yang akan dibahas mendalam di Module 10

Mini Project: Desain arsitektur flight software (diagram modul + message passing) untuk CubeSat dari Module 4, mengikuti pola cFS ATAU F Prime (pilih satu, jelaskan alasan pilihan) — termasuk state machine mode satelit (minimal: safe mode, nominal mode) dengan aturan transisi eksplisit.

Status: Not started

---

## Module 6 — Sensor Fusion untuk Attitude Determination

Description: Deep-dive ke ADCS (diperkenalkan di Module 4.2) — bagaimana data sensor yang noisy dan tidak lengkap (gyroscope, magnetometer, sun sensor) digabungkan jadi estimasi orientasi satelit yang reliable, fondasi sebelum satelit bisa "tahu ke mana ia menghadap".

Lessons:

* [ ] 6.1 Masalah attitude determination: kenapa satu sensor saja tidak cukup (gyroscope drift over time, magnetometer terganggu medan magnet lokal, sun sensor tidak bekerja saat eclipse)
* [ ] 6.2 Representasi orientasi: Euler angles vs quaternion — kenapa quaternion jadi standar de facto di ADCS (menghindari gimbal lock, komputasi lebih stabil)
* [ ] 6.3 Sensor fusion dasar: complementary filter sebagai pengantar sederhana sebelum Kalman filter
* [ ] 6.4 Kalman filter untuk attitude estimation (konsep & intuisi, bukan derivasi matematis penuh — cukup untuk paham kenapa ini standar industri) — overview Extended Kalman Filter (EKF) sebagai varian yang dipakai untuk sistem non-linear seperti attitude dynamics
* [ ] 6.5 Python: implementasi sensor fusion sederhana (complementary filter atau Kalman filter dasar) dengan data sensor sintetik (gyroscope + magnetometer noisy), validasi terhadap ground truth yang diketahui

Mini Project: Skrip Python yang mensimulasikan data sensor noisy (gyroscope drift + magnetometer noise) untuk satelit yang berputar dengan pola diketahui, lalu menerapkan complementary filter atau Kalman filter dasar untuk mengestimasi orientasi (quaternion), dibandingkan akurasinya terhadap ground truth simulasi.

Status: Not started

---

## Module 7 — Telemetry (TM): Format, Prioritas, dan Downlink

Description: Bagaimana satelit "berbicara" ke bumi — modul ini membangun sistem telemetry: format paket data, prioritas data yang mana dikirim duluan saat bandwidth terbatas, dan pola downlink terjadwal vs event-driven.

Lessons:

* [ ] 7.1 Apa itu telemetry di konteks satelit: housekeeping data (status subsistem: suhu, tegangan, mode) vs payload data (hasil instrumen/sains) — kenapa keduanya butuh perlakuan berbeda
* [ ] 7.2 Format paket telemetry: header (source, timestamp, sequence number), payload data, checksum — menyambung ke struct packing dari Module 2.3
* [ ] 7.3 Prioritas & bandwidth budget: satelit LEO hanya punya beberapa menit kontak per pass (dari Module 1.4) — kenapa data harus diprioritaskan (housekeeping kritis > payload data biasa), strategi buffering saat tidak ada kontak
* [ ] 7.4 Pola downlink: beacon terjadwal (broadcast periodik, tidak perlu request) vs data terjadwal on-demand saat pass — trade-off masing-masing
* [ ] 7.5 C: implementasi encoder paket telemetry (struct → byte stream siap kirim) dengan prioritization queue sederhana (housekeeping data selalu didahulukan dari payload data saat buffer penuh)

Mini Project: Implementasi C sistem telemetry sederhana: struct data housekeeping (suhu, tegangan baterai, mode) + struct data payload, dengan queue yang memprioritaskan housekeeping saat "bandwidth" (disimulasikan sebagai limit byte per "pass") terbatas — diuji dengan skenario buffer penuh membuktikan housekeeping tidak pernah ter-drop.

Status: Not started

---

## Module 8 — Command (TC): Uplink, Validasi, dan Safety Interlock

Description: Kebalikan dari Module 7 — bagaimana bumi "memerintah" satelit, dan kenapa ini jauh lebih dijaga ketat daripada telemetry (command yang salah bisa merusak/kehilangan satelit permanen).

Lessons:

* [ ] 8.1 Anatomi command: struktur paket command (opcode, parameter, authentication/checksum) — kenapa validasi command jauh lebih ketat daripada validasi telemetry
* [ ] 8.2 Command validation layer: cek checksum/authentication, cek command dikenali, cek parameter dalam rentang valid — pola defense-in-depth sebelum command benar-benar dieksekusi
* [ ] 8.3 Safety interlock untuk command kritis: command yang bisa mengubah mode satelit atau menyalakan aktuator butuh "double confirmation" atau precondition check (mis. tidak boleh fire thruster saat attitude belum diketahui pasti)
* [ ] 8.4 Command sequencing & scheduling: command yang dieksekusi langsung (real-time) vs command yang dijadwalkan dieksekusi nanti (time-tagged command) untuk operasi saat satelit di luar kontak ground station
* [ ] 8.5 C: implementasi command processor sederhana — parsing command dari byte stream, validasi berlapis (checksum → opcode dikenali → parameter valid → precondition check), reject command yang gagal validasi dengan log alasan

Mini Project: Implementasi C command processor yang menerima command mode-change (mis. "masuk safe mode" / "aktifkan payload"), menjalankan seluruh validasi berlapis dari Module 8.5, dan menolak command yang tidak valid (checksum salah, parameter di luar rentang, precondition tidak terpenuhi) dengan pesan diagnostik yang jelas — diuji dengan minimal 5 skenario command invalid berbeda.

Status: Not started

---

## Module 9 — Komunikasi Ground Station

Description: Sisi bumi dari komunikasi satelit — link budget secukupnya untuk paham kenapa komunikasi satelit kecil itu sulit, protokol yang dipakai komunitas amatir CubeSat, dan penjadwalan pass satelit. Mengikuti referensi praktis AMSAT.

Lessons:

* [ ] 9.1 Link budget secukupnya: kenapa sinyal satelit kecil (low power, antenna kecil) sulit diterima — konsep dasar path loss, kenapa ketinggian orbit (Module 1) langsung memengaruhi kualitas link
* [ ] 9.2 Protokol komunikasi amatir CubeSat: AX.25 sebagai protokol umum yang dipakai komunitas AMSAT, format frame dasar
* [ ] 9.3 Pass scheduling: menggabungkan propagasi orbit (Module 1.4) dengan jadwal ground station — menentukan kapan & berapa lama satelit "terlihat" untuk kontak, doppler shift secukupnya untuk paham kenapa frekuensi perlu dikoreksi saat pass
* [ ] 9.4 Ground station software: arsitektur dasar software ground station (tracking antenna mengikuti orbit, decode telemetry masuk, encode command keluar) — menyambung balik ke Module 7-8
* [ ] 9.5 Studi kasus komunitas: bagaimana operator amatir (AMSAT) benar-benar melacak & berkomunikasi dengan CubeSat menggunakan hardware murah — realita praktis di luar fasilitas institusional besar

Mini Project: Skrip Python yang menggabungkan propagator orbit dari Module 1 dengan simulasi pass scheduling: untuk satu ground station (lat/lon tertentu) dan satu satelit (TLE), hasilkan jadwal seluruh pass dalam 7 hari ke depan (waktu mulai, waktu maksimum elevasi, durasi, doppler shift perkiraan di awal/akhir pass).

Status: Not started

---

## Module 10 — Fault Tolerance & Redundancy Design

Description: Satelit tidak bisa "diperbaiki manual" begitu terbang — modul ini merancang sistem yang tahan gagal, spesifik untuk tantangan spacecraft: radiation-induced fault (diperkenalkan Module 3.4), kegagalan hardware, dan kebutuhan recovery otomatis tanpa intervensi manusia.

Lessons:

* [ ] 10.1 Kelas kegagalan spesifik spacecraft: Single Event Upset (SEU, bit flip karena radiasi), hardware degradation over time, kegagalan komunikasi berkepanjangan — kenapa ini berbeda dari kelas kegagalan software backend biasa
* [ ] 10.2 Redundancy pattern: Triple Modular Redundancy (TMR) — menjalankan komputasi kritis 3x dan voting, kapan ini worth cost-nya (power/compute budget yang ketat di CubeSat kecil)
* [ ] 10.3 Software-level fault detection: checksum/CRC untuk deteksi corruption memory, periodic self-test, watchdog (revisit Module 3.3) sebagai lapisan terakhir
* [ ] 10.4 Safe mode design: kondisi apa yang memicu transisi otomatis ke safe mode (dari state machine Module 5.4), apa yang satelit lakukan di safe mode (minimal power draw, hanya subsistem esensial aktif, menunggu command dari ground)
* [ ] 10.5 Graceful degradation: satelit yang kehilangan satu sensor/subsistem tetap bisa beroperasi dengan kapabilitas terbatas, bukan langsung total failure — desain "degraded mode" di antara nominal dan safe mode penuh

Mini Project: Perluas state machine mode satelit dari Module 5.4 dengan transisi fault-triggered eksplisit: definisikan kondisi fault apa yang memicu safe mode, tambahkan minimal 1 degraded mode (mis. "ADCS degraded — hanya magnetometer tersedia"), dan implementasikan checksum/CRC sederhana di C untuk salah satu struct data dari Module 7 sebagai contoh fault detection nyata.

Status: Not started

---

## Module 11 — Testing untuk Flight Software

Description: **Bukan** unit testing C generik (asumsi sudah dikuasai dari `nasa-software-engineer` atau pengalaman backend) — modul ini fokus ke testing yang spesifik safety-critical spacecraft software: hardware-in-the-loop simulasi, fault injection, dan regression testing dengan standar lebih ketat dari software biasa.

Lessons:

* [ ] 11.1 Kenapa testing flight software lebih ketat dari software backend biasa — biaya kegagalan (tidak bisa "hotfix" satelit yang sudah terbang, downtime = kehilangan data/misi), budaya testing ala aerospace secukupnya (pointer ke `nasa-software-engineer` untuk budaya penuh)
* [ ] 11.2 Hardware-in-the-loop testing (simulasi): menguji flight software melawan simulator subsistem (bukan hardware fisik asli, konsisten dengan Catatan Scope poin 4) — kenapa ini beda dari mocking biasa di backend testing
* [ ] 11.3 Fault injection testing: sengaja menyuntikkan skenario gagal (sensor mengirim data invalid, command corrupt, subsistem tidak merespons) untuk membuktikan fault tolerance dari Module 10 benar-benar bekerja
* [ ] 11.4 Regression testing untuk flight software: kenapa setiap perubahan kecil butuh regression test penuh sebelum "upload" ke satelit (constraint: tidak ada rollback mudah setelah command diterima)
* [ ] 11.5 Test coverage untuk safety-critical path: command processor (Module 8) dan fault detection (Module 10) sebagai area yang butuh coverage nyaris 100%, dibedakan dari area non-kritis yang boleh lebih longgar

Mini Project: Test suite (boleh pakai unit testing framework C ringan seperti Unity/CMock, atau harness sederhana buatan sendiri) untuk command processor Module 8.5 — mencakup seluruh happy path DAN fault injection (command corrupt, checksum salah, precondition gagal, parameter di luar batas) dengan target coverage eksplisit untuk validation layer.

Status: Not started

---

## Module 12 — Simulasi Satelit: Tooling & Integrasi

Description: Sebelum capstone, kuasai tooling simulasi yang menyatukan orbital mechanics (Module 1), flight software (Module 2-3, 5), dan subsistem (Module 4, 6-10) jadi satu lingkungan yang bisa "diterbangkan" secara virtual. Mengikuti dokumentasi NASA cFS dan F Prime untuk lingkungan simulasi.

Lessons:

* [ ] 12.1 Peran simulator dalam pengembangan flight software: kenapa hampir semua misi mengembangkan & menguji software di simulator dulu sebelum hardware asli tersedia ("software-first" development)
* [ ] 12.2 Setup lingkungan simulasi cFS ATAU F Prime (pilih salah satu, konsisten dengan pilihan arsitektur di Module 5) — menjalankan aplikasi flight software sederhana di simulator
* [ ] 12.3 Menghubungkan orbit propagator (Module 1) sebagai "environment model" — simulator flight software perlu tahu posisi/orientasi satelit untuk mensimulasikan sensor (mis. sun sensor butuh tahu apakah satelit sedang eclipse)
* [ ] 12.4 Simulasi subsistem sebagai mock/stub yang realistis: model power (baterai berkurang/terisi sesuai simulasi solar panel & eclipse dari Module 1), model sensor ADCS dengan noise (menyambung ke Module 6)
* [ ] 12.5 Menjalankan skenario simulasi end-to-end pendek: satelit di orbit, melewati satu pass ground station, mengirim telemetry, menerima 1 command — seluruh komponen dari modul-modul sebelumnya bekerja sama

Mini Project: Setup lingkungan simulasi cFS atau F Prime yang menjalankan minimal 2 aplikasi/komponen flight software buatan sendiri (mis. telemetry app dari Module 7 + command processor dari Module 8), terhubung ke environment model orbit sederhana dari Module 1, dijalankan untuk simulasi durasi 1 orbit penuh dengan log hasil (state mode satelit berubah sesuai kondisi, telemetry terkirim sesuai jadwal).

Status: Not started

---

## Module 13 — Integrasi Subsistem Penuh & Mission Operations

Description: Modul jembatan sebelum capstone — menyatukan seluruh subsistem (Module 4, 6, 10) dan flight software (Module 2-3, 5, 7-8, 11-12) jadi satu stack koheren, plus pengantar singkat ke sisi operasional misi (bagaimana tim ground control benar-benar mengoperasikan satelit sehari-hari).

Lessons:

* [ ] 13.1 Integrasi end-to-end: menyusun checklist integrasi (tiap subsistem punya interface yang jelas ke Software Bus/port dari Module 5, tiap fault mode dari Module 10 sudah punya handler)
* [ ] 13.2 Mission timeline & concept of operations (ConOps) secukupnya: fase misi (launch, deployment/commissioning, nominal operations, end-of-life) dan bagaimana flight software berperilaku berbeda di tiap fase
* [ ] 13.3 Ground segment operations secukupnya: bagaimana tim ground control menjadwalkan pass (Module 9), memutuskan command apa yang dikirim, memonitor kesehatan satelit dari telemetry (Module 7) — overview operasional, bukan mendalam
* [ ] 13.4 Anomaly response: skenario satelit "berperilaku aneh" (telemetry hilang, safe mode tidak terduga) — proses diagnosis dari data yang tersedia (log, telemetry historis) sebelum mengirim command korektif
* [ ] 13.5 Review arsitektur menyeluruh: code review penuh atas seluruh komponen yang sudah dibangun Module 1-12, mengikuti Review Style `domains/software-engineering/DOMAIN.md` ditambah lensa keandalan spacecraft-specific

Mini Project: Dokumen integrasi + checklist yang memetakan seluruh komponen dari Module 1-12 ke satu arsitektur sistem utuh (diagram + tabel interface), plus simulasi skenario anomaly response tertulis: diberi "gejala" (mis. telemetry housekeeping tiba-tiba berhenti selama 3 pass berturut-turut), diagnosis kemungkinan akar masalah dan command korektif yang akan dikirim.

Status: Not started

---

## Module 14 — Capstone: CubeSat Simulation Project

Description: Menyatukan seluruh 13 modul menjadi satu proyek CubeSat simulasi end-to-end yang portfolio-ready — bukti konkret kemampuan merancang & mengimplementasikan flight software satelit dari orbital mechanics sampai fault tolerance.

Lessons:

* [ ] 14.1 Perencanaan misi: definisikan misi CubeSat hipotetis (mis. Earth imaging sederhana, amateur radio relay, atau technology demonstration) — orbit yang dipilih, subsistem yang dibutuhkan, payload konsep
* [ ] 14.2 Implementasi flight software: arsitektur (Module 5) + command processor (Module 8) + telemetry system (Module 7) + fault handling & safe mode (Module 10), dibangun di atas simulator (Module 12)
* [ ] 14.3 Integrasi orbit & ground station: orbit propagator (Module 1) sebagai environment model, simulasi pass scheduling (Module 9) untuk jadwal kontak ground station simulasi
* [ ] 14.4 Fault injection & validation: jalankan test suite (Module 11) termasuk skenario fault injection yang membuktikan safe mode & redundancy bekerja sesuai desain
* [ ] 14.5 Dokumentasi: mission design document, arsitektur flight software, hasil simulasi (log 1+ orbit penuh dengan minimal 1 pass ground station & 1 skenario fault yang berhasil di-recover), submit ke `portofolio/`

Capstone Project: **CubeSat Simulation Project** — satu sistem simulasi CubeSat yang menggabungkan: (1) orbit propagator dari elemen Keplerian/TLE nyata (Module 1), (2) flight software C/C++ dengan arsitektur modular cFS/F Prime-style (Module 2-3, 5) dijalankan di simulator (Module 12), (3) sistem telemetry dengan prioritization (Module 7), (4) command processor dengan validasi berlapis & safety interlock (Module 8), (5) sensor fusion attitude determination sederhana (Module 6), (6) fault tolerance dengan minimal 1 redundancy pattern & safe mode otomatis (Module 10), (7) simulasi ground station pass scheduling (Module 9), (8) test suite dengan fault injection (Module 11). Skenario akhir: simulasikan minimal 1 orbit penuh, satelit mengirim telemetry terjadwal, menerima & memvalidasi minimal 1 command, dan mengalami minimal 1 fault yang terdeteksi & di-recover otomatis (transisi ke safe mode lalu kembali ke nominal setelah kondisi pulih).

Status: Not started

---

# Capstone Project

Description: Lihat Module 14 di atas untuk rincian lengkap CubeSat Simulation Project. Capstone course ini SATU dengan Capstone Project Module 14 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan pola Project Tiers `domains/software-engineering/DOMAIN.md` (Enterprise-grade Portfolio Project per course).

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` ditambah Review Style `domains/software-engineering/DOMAIN.md`, dengan lensa tambahan spesifik domain ini: **Orbital Correctness** (propagasi orbit tervalidasi terhadap data TLE nyata), **Memory & Resource Discipline** (flight software tidak melakukan dynamic allocation di hot path, sesuai idiom Module 2), **Fault Recovery Proof** (minimal 1 skenario fault injection terbukti ter-recover otomatis lewat log simulasi, bukan klaim tanpa bukti), dan **Command Safety** (command processor menolak seluruh skenario command invalid yang diuji di Module 8/11, tidak ada bypass validasi). Disimpan ke `portofolio/`.

Status: Not started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: Belum dimulai — Draft, menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Hukum Kepler (I-III) dan model dua-body
