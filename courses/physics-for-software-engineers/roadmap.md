# Course Roadmap

## Course Information

* Course Name: Fisika Terapan untuk Software Engineer — dari Kinematika sampai Physics Engine, Ray Tracer & Robot Kinematics
* Category: Physics for Software Engineers (Domain: physics-for-software-engineers — lihat `domains/physics-for-software-engineers/DOMAIN.md`)
* Difficulty: Level 0 (bisa coding, tapi fisika SMA/awal kuliah sudah lama tidak dipakai — apalagi dipakai untuk kode) → Level 3 (mampu membangun DAN memvalidasi secara numerik komponen inti physics engine, ray tracer, atau skrip kinematika robotika sendiri)
* Estimated Duration: 11 modul (10 modul konten + 1 modul capstone), 4-5 lesson/modul, pace disesuaikan karena Applied Practice di domain ini SELALU berupa kode yang benar-benar dijalankan & divalidasi numerik (energy/momentum check, dsb) — bukan sekadar baca teori
* Prerequisites: Bisa coding di minimal satu bahasa (Python/JS/C++/dst) dengan dasar OOP dan array/vector; aljabar & trigonometri tingkat SMA. TIDAK butuh kalkulus lanjut di awal — turunan/integral secukupnya diperkenalkan saat dibutuhkan di Module 1 & 6. Domain ini murni mekanika klasik/Newtonian (v << c); pertanyaan yang menyentuh efek relativistik (mis. koreksi GPS) akan diarahkan eksplisit ke domain terpisah `relativity`, bukan dijawab di sini.

---

# Learning Objectives

* [ ] Memodelkan gerak (kinematika 1D/2D) dan gaya (dinamika, Hukum Newton) sebagai kode simulasi yang bisa divalidasi terhadap solusi analitik
* [ ] Memakai kekekalan energi & momentum sebagai alat debug simulasi, bukan cuma rumus hafalan (MIT 8.01)
* [ ] Menguasai mekanika rotasi (torsi, momen inersia, momentum sudut) secukupnya untuk rigid body & robotika
* [ ] Membandingkan integrator numerik (Euler, semi-implicit Euler, Verlet, RK4) secara terukur — stabilitas vs akurasi vs biaya komputasi — bukan memilih satu secara asal
* [ ] Membangun komponen rigid body dynamics dan collision detection/response dari nol mengikuti pola Millington & Ericson, cukup untuk memahami cara kerja Box2D/Bullet/PhysX
* [ ] Membangun ray tracer minimal (ray-object intersection, reflection/refraction, shading dasar) mengikuti kerangka MIT 6.837 & Real-Time Rendering
* [ ] Memiliki literasi elektromagnetisme/sensor (Ohm's law, accelerometer/gyroscope/LIDAR) dan kinematika robotika dasar secukupnya untuk menulis firmware/software yang menginterpretasi hardware dengan benar
* [ ] Menghasilkan Capstone Project (Physics Engine 2D / Ray Tracer / Robot Kinematics Simulator) yang production-aware, dengan validasi numerik eksplisit dan dokumentasi trade-off desain

---

# Course Modules

## Module 1 — Kinematika: Mendeskripsikan Gerak dalam Kode

Description: Fondasi paling dasar sebelum apa pun lain: bagaimana posisi, kecepatan, dan percepatan saling berhubungan, dan bagaimana itu diterjemahkan langsung jadi kode simulasi gerak — mengikuti struktur minggu-minggu awal MIT OCW 8.01SC (Kinematics).

Lessons:

* [ ] 1.1 Posisi, kecepatan, percepatan sebagai vektor — turunan & integral secukupnya (tanpa kalkulus formal berlebihan)
* [ ] 1.2 Gerak 1D: gerak lurus berubah beraturan, grafik x-t/v-t/a-t, dan makna geometrisnya
* [ ] 1.3 Gerak 2D: gerak proyektil — dekomposisi komponen x/y yang independen
* [ ] 1.4 Dari rumus kinematika ke kode: loop simulasi posisi step-by-step (belum pakai integrator numerik formal — itu Module 6)

Mini Project: Projectile motion simulator (gravitasi tanpa drag) — hitung posisi tiap timestep lewat loop kode. Validasi numerik: bandingkan posisi hasil simulasi terhadap solusi analitik closed-form x(t), y(t) di titik waktu yang sama; error harus tetap di bawah threshold kecil (mis. <1%) di sepanjang lintasan.

Status: Not started

---

## Module 2 — Dinamika: Hukum Newton, Gaya, dan Free Body Diagram

Description: Kenapa benda bergerak seperti itu — Tiga Hukum Newton dan disiplin free body diagram, sebelum masuk ke gaya-gaya yang lebih realistis (gesekan, drag, pegas) yang dibutuhkan simulasi nyata (MIT 8.01 Week 2, Bourg & Bywalec Ch. 3).

Lessons:

* [ ] 2.1 Tiga Hukum Newton & konsep gaya neto
* [ ] 2.2 Free Body Diagram — gravitasi, normal force, gaya gesek statis/kinetik
* [ ] 2.3 Gaya pegas (Hooke's Law) & gaya hambat/drag (linear vs quadratic drag)
* [ ] 2.4 Dari F=ma ke akselerasi: menyusun persamaan gerak untuk sistem dengan banyak gaya sekaligus

Mini Project: Projectile motion simulator v2 — tambahkan drag/hambatan udara ke simulator Module 1. Validasi numerik: buktikan lintasan lebih pendek dari kasus tanpa drag DAN energi mekanik total menurun monoton sepanjang simulasi (karena drag adalah gaya disipatif) — kontraskan eksplisit dengan Module 1 yang energinya kekal.

Status: Not started

---

## Module 3 — Kerja, Energi, dan Kekekalan Energi

Description: Energi sebagai alat verifikasi paling penting untuk simulasi fisika — modul ini membangun pemahaman work-energy theorem dan kekekalan energi, lalu langsung dipakai sebagai teknik debug (MIT 8.01 Week 7-8).

Lessons:

* [ ] 3.1 Work-Energy Theorem — kerja sebagai integral gaya sepanjang lintasan
* [ ] 3.2 Energi potensial (gravitasi, pegas) & gaya konservatif vs non-konservatif
* [ ] 3.3 Hukum Kekekalan Energi Mekanik — kapan berlaku, kapan tidak (sistem dengan disipasi)
* [ ] 3.4 Energi sebagai alat debug simulasi: kenapa energi total yang naik terus adalah tanda bug/instabilitas numerik, bukan "fisika aneh"

Mini Project: Simple pendulum tanpa gesekan, dengan log energi kinetik + potensial + total di tiap timestep. Validasi numerik: energi total harus tetap konstan (dalam toleransi kecil) sepanjang simulasi berjalan; deviasi signifikan wajib dijelaskan (limitasi integrator, bukan diabaikan).

Status: Not started

---

## Module 4 — Momentum, Impuls, dan Tumbukan

Description: Kekekalan momentum sebagai fondasi kedua untuk memvalidasi simulasi, plus dasar tumbukan yang jadi pintu masuk ke collision response nanti (MIT 8.01 Week 5 & 9, Bourg & Bywalec Ch. 5 "Collisions").

Lessons:

* [ ] 4.1 Momentum linear & impuls — hubungan dengan Hukum II Newton (F dt = dp)
* [ ] 4.2 Kekekalan momentum pada sistem tertutup & konsep pusat massa (center of mass)
* [ ] 4.3 Tumbukan elastis vs inelastis — coefficient of restitution
* [ ] 4.4 Dari teori ke kode: menghitung kecepatan pasca-tumbukan untuk kasus 1D lalu 2D

Mini Project: Simulator tumbukan 2 partikel — mulai 1D, lalu extend ke 2D (bola-bola). Validasi numerik: total momentum sistem sebelum & sesudah tumbukan harus sama (dalam toleransi numerik), dan energi kinetik total sesuai jenis tumbukan yang dipilih (kekal penuh untuk elastis, berkurang konsisten dengan restitution untuk inelastis).

Status: Not started

---

## Module 5 — Mekanika Rotasi: Torsi, Momen Inersia, dan Momentum Sudut

Description: Analog rotasi dari semua yang sudah dipelajari — torsi sebagai analog gaya, momen inersia sebagai analog massa — fondasi wajib sebelum rigid body dynamics (MIT 8.01 Week 10-12).

Lessons:

* [ ] 5.1 Torsi & momen inersia — analog rotasi dari F=ma
* [ ] 5.2 Kekekalan momentum sudut & gerak menggelinding (rolling motion)
* [ ] 5.3 Kenapa rotasi 3D butuh lebih dari sekadar sudut — pengantar singkat ke masalah representasi rotasi (didalami penuh di Module 7 lewat quaternion)
* [ ] 5.4 Aplikasi ke robotika: forward kinematics dasar untuk lengan robot 2 sendi (2D) — menghubungkan torsi/rotasi ke aplikasi nyata

Mini Project: (a) Physical pendulum (bandul fisis) berotasi terhadap sumbu — validasi numerik kekekalan momentum sudut & energi total. (b) Skrip forward kinematics lengan robot 2 sendi 2D — validasi: posisi end-effector hasil kode dicek terhadap perhitungan trigonometri manual untuk beberapa nilai sudut sendi.

Status: Not started

---

## Module 6 — Metode Integrasi Numerik untuk Simulasi

Description: Inti matematis di balik setiap game engine & physics engine sungguhan — bagaimana hukum gerak (ODE) diselesaikan langkah demi langkah secara numerik, dan kenapa metode yang dipilih menentukan apakah simulasi stabil atau meledak (MIT 6.837 "Particle Systems and ODE Solvers", Millington Ch. "The Particle Physics Engine").

Lessons:

* [ ] 6.1 Kenapa simulasi butuh integrator numerik — hukum gerak Newton sebagai ODE
* [ ] 6.2 Euler eksplisit — cara kerja, dan kenapa gampang tidak stabil (energy gain/divergensi)
* [ ] 6.3 Semi-implicit Euler (symplectic Euler) & Verlet integration — kenapa jadi pilihan default game engine (Box2D, dst)
* [ ] 6.4 Runge-Kutta orde-4 (RK4) — akurasi lebih tinggi, biaya komputasi per step lebih mahal
* [ ] 6.5 Trade-off stabilitas vs akurasi vs biaya komputasi — kapan pilih integrator yang mana (game real-time vs simulasi offline presisi tinggi)

Mini Project: Implementasikan keempat integrator (Euler eksplisit, semi-implicit Euler, Verlet, RK4) untuk sistem yang sama (mis. pendulum dari Module 3 atau orbit dua-benda sederhana). Validasi numerik: ukur & bandingkan energy drift dari waktu ke waktu untuk tiap integrator pada timestep yang sama — hasil berupa angka/grafik drift eksplisit, bukan kesimpulan "kelihatan stabil".

Status: Not started

---

## Module 7 — Rigid Body Dynamics

Description: Naik level dari partikel titik ke benda tegar (rigid body) yang punya orientasi dan bisa berotasi — mengikuti progresi Millington dari particle engine → mass-aggregate engine → rigid-body engine.

Lessons:

* [ ] 7.1 Dari particle ke rigid body — representasi state: posisi, orientasi (quaternion), kecepatan linear & sudut, tensor inersia
* [ ] 7.2 Hukum gerak untuk rigid body — persamaan linear & angular equations of motion
* [ ] 7.3 Progresi Millington: particle physics engine → mass-aggregate physics engine (partikel terhubung pegas/rod) → rigid-body physics engine
* [ ] 7.4 Mengintegrasikan state rigid body (posisi + orientasi) tiap timestep memakai integrator dari Module 6

Mini Project: Mass-aggregate physics engine mini (beberapa partikel terhubung spring/rod constraint), lalu extend jadi satu rigid body (kotak/balok) yang jatuh bebas & berotasi. Validasi numerik: kekekalan momentum sudut pada rigid body tanpa gaya eksternal, dan buktikan tensor inersia dipakai konsisten dari perhitungan (bukan angka yang di-hardcode).

Status: Not started

---

## Module 8 — Collision Detection & Collision Response

Description: Inti dari physics engine sungguhan — mendeteksi tabrakan secara efisien (broad-phase/narrow-phase) lalu meresponsnya secara fisis benar (impulse-based) — mengikuti Ericson (Real-Time Collision Detection) untuk deteksi dan Millington untuk resolusi.

Lessons:

* [ ] 8.1 Broad-phase vs narrow-phase collision detection — kenapa dipisah demi performa
* [ ] 8.2 Bounding volumes (AABB, bounding sphere) & basic primitive tests (Ericson Ch. 4-5)
* [ ] 8.3 Generating contacts — deteksi tabrakan lingkaran-lingkaran & AABB-AABB
* [ ] 8.4 Collision response berbasis impulse — restitution & friction dasar
* [ ] 8.5 Constraint solving secukupnya untuk memahami pola kerja Box2D/Bullet (bukan solver production penuh)

Mini Project (Intermediate Project — "Mini 2D Physics Engine"): Gabungkan Module 6 (integrator) + Module 7 (rigid body) + Module 8 (collision) jadi physics engine 2D kecil dengan beberapa lingkaran/kotak yang saling bertabrakan. Validasi numerik: total momentum sistem tertutup kekal antar tabrakan (dalam toleransi), tidak ada objek saling menembus (penetrasi) setelah collision response diterapkan, dan energi berkurang secara wajar untuk restitution < 1 (bukan meledak/NaN/divergen).

Status: Not started

---

## Module 9 — Optik & Ray Tracing untuk Computer Graphics

Description: Fisika cahaya secukupnya untuk membangun rendering — hukum pemantulan/pembiasan sampai ray tracer minimal — mengikuti kerangka lecture ray tracing MIT 6.837 (Matusik & Durand) dan Real-Time Rendering untuk shading.

Lessons:

* [ ] 9.1 Hukum pemantulan (reflection) & pembiasan (refraction) — Snell's Law
* [ ] 9.2 Model ray tracing dasar: ray-object intersection (ray-sphere, ray-plane)
* [ ] 9.3 Shading model dasar (local illumination) — Phong/Blinn-Phong secukupnya
* [ ] 9.4 Recursive ray tracing — refleksi & refraksi berulang, pengantar BRDF & light transport secukupnya (bukan transport theory penuh, konsisten batas domain)
* [ ] 9.5 Acceleration structures secukupnya — kenapa ray tracing naif lambat, dan gambaran umum solusinya

Mini Project (Intermediate Project — "Mini Ray Tracer"): Ray tracer minimal dengan beberapa primitif (sphere/plane), refleksi + refraksi sederhana, dan shading dasar. Validasi: render dibandingkan terhadap kasus uji dengan jawaban geometris yang diketahui (mis. sudut pantul relatif terhadap normal permukaan, posisi bayangan pada cermin datar ideal) dicek secara analitik — bukan cuma dinilai "kelihatan benar" secara visual.

Status: Not started

---

## Module 10 — Literasi Elektromagnetisme, Sensor, dan Kinematika Robotika untuk Hardware/IoT

Description: Literasi software-engineer-yang-berinteraksi-dengan-hardware — cukup untuk membaca datasheet sensor dan menulis firmware/software yang menginterpretasi sinyalnya dengan benar, plus kinematika robotika dasar sebagai aplikasi nyata dari Module 5. Secara eksplisit BUKAN kurikulum Electrical Engineering penuh (lihat `domains/physics-for-software-engineers/DOMAIN.md` bagian Scope & Domain-Specific Standards).

Lessons:

* [ ] 10.1 Hukum Ohm & rangkaian dasar (seri/paralel) — cukup untuk membaca datasheet sensor
* [ ] 10.2 Sensor umum robotika/IoT: accelerometer, gyroscope, LIDAR/ultrasonic — prinsip kerja & bentuk output sinyalnya
* [ ] 10.3 Interpretasi data sensor di software — noise, kalibrasi dasar, kenapa raw sensor data tidak boleh dipakai mentah-mentah
* [ ] 10.4 Forward & dasar inverse kinematics lengan robot sederhana (2-3 sendi) — extend Lesson 5.4

Mini Project: Skrip kinematika robotika — forward kinematics lengan robot 2-3 sendi plus inverse kinematics dasar (mis. metode geometris/analitik untuk 2 sendi), dipadukan dengan skrip pembacaan "data sensor" tersimulasi (sudut sendi + noise buatan). Validasi numerik: hasil forward kinematics dari sudut yang dihasilkan inverse kinematics harus kembali mendekati posisi target (round-trip error kecil terukur), dan pengaruh noise sensor terhadap akurasi posisi didemonstrasikan dengan angka, bukan cuma diklaim.

Status: Not started

---

## Module 11 — Capstone: Physics Engine, Ray Tracer, atau Robot Kinematics Simulator

Description: Mengintegrasikan seluruh artefak Module 1-10 jadi satu capstone project portofolio, mengikuti `domains/physics-for-software-engineers/DOMAIN.md` bagian "Project Tiers" — learner memilih SATU dari tiga jalur capstone yang tersedia.

Lessons:

* [ ] 11.1 Menentukan jalur capstone & scope: Physics Engine 2D lengkap / Ray Tracer dengan light transport dasar / Robot Kinematics Simulator (forward + dasar inverse kinematics)
* [ ] 11.2 Desain & implementasi artefak capstone, mengintegrasikan modul-modul yang relevan dengan jalur terpilih
* [ ] 11.3 Validasi numerik menyeluruh sesuai jalur — kekekalan energi/momentum & non-penetrasi (Physics Engine), akurasi geometris pantulan/bias (Ray Tracer), atau round-trip error forward-inverse kinematics (Robot Kinematics)
* [ ] 11.4 Dokumentasi tertulis keputusan desain fisika/numerik & trade-off yang diambil, disimpan ke `portofolio/`

Capstone Project: Pilih SATU — (A) **Physics Engine 2D kecil yang lengkap**: integrator terpilih (Module 6) + broad/narrow-phase collision detection (Module 8) + constraint/impulse resolution (Module 8) untuk beberapa bentuk (lingkaran/kotak), divalidasi lewat kekekalan momentum & non-penetrasi. (B) **Ray tracer dengan light transport dasar**: beberapa primitif, multiple bounce refleksi/refraksi, BRDF sederhana (Module 9), divalidasi lewat kasus uji geometris analitik. (C) **Simulator kinematika robotika**: forward + dasar inverse kinematics untuk lengan robot sederhana (Module 5 & 10), divalidasi lewat round-trip error posisi end-effector. Semua jalur disertai penjelasan tertulis singkat tentang keputusan desain fisika/numerik yang diambil dan trade-off-nya (mis. kenapa integrator X dipilih, kenapa BRDF disederhanakan, dst).

Status: Not started

---

# Capstone Project

Description: Lihat Module 11 di atas untuk rincian lengkap tiga jalur capstone (Physics Engine 2D / Ray Tracer / Robot Kinematics Simulator). Capstone course ini SATU dengan Capstone Project Module 11 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/physics-for-software-engineers/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` DITAMBAH keempat lensa Review Style domain ini dari `domains/physics-for-software-engineers/DOMAIN.md`: **Physical Correctness** (hukum fisika yang mendasari terpenuhi, mis. energi/momentum kekal pada sistem tertutup), **Numerical Stability** (integrator stabil untuk skenario yang disimulasikan, trade-off dijelaskan sadar bukan kebetulan), **Code-Physics Fidelity** (kode benar-benar mengimplementasikan fisika yang dipahami, bukan angka di-fudge supaya "terlihat benar"), dan **Real-Time/Production Awareness** (kesadaran budget waktu per frame, floating-point precision, stabilitas pada timestep variabel). Disimpan ke `portofolio/` disertai dokumentasi trade-off desain.

Status: Not started

---

# Overall Progress

Module Completion: 0/11

Overall Completion: 0%

Current Module: Module 1 — Kinematika: Mendeskripsikan Gerak dalam Kode (menunggu approval roadmap)

Current Lesson: 1.1 Posisi, kecepatan, percepatan sebagai vektor — turunan & integral secukupnya

Next Lesson: 1.1 Posisi, kecepatan, percepatan sebagai vektor — turunan & integral secukupnya (tanpa kalkulus formal berlebihan)
