# Course Roadmap

## Course Information

* Course Name: Robotics — dari 0 sampai Expert untuk Software Engineer
* Category: Robotics Engineering (Kinematics, Control, Embedded, ROS2, Perception, Autonomy)
* Difficulty: Level 0 (belum pernah menyentuh robotika/embedded/ROS sama sekali, rotation matrix/quaternion masih asing) → Level 4 (Expert — mampu merancang, mensimulasikan, DAN men-deploy sistem robot otonom end-to-end: kinematics → kontrol → persepsi → SLAM → path planning → hardware nyata)
* Estimated Duration: 14 modul + Capstone, ~4-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`). Dengan pace sedang (5-10 jam/minggu) ≈ 6-8 bulan — course terluas cakupannya di repo ini karena robotika secara inheren lintas-disiplin (matematika, kontrol, embedded, software, AI).
* Prerequisites: Programming solid (Java/Spring Boot dari root `CLAUDE.md`, tapi course ini praktik dengan C++/Python — lihat alasan di Catatan Scope poin 1). **Tidak** perlu pengalaman elektronika/robotika/kalkulus lanjut sebelumnya — Module 1 membangun fondasi matematika ruang 3D dari nol. **Direkomendasikan opsional** (bukan wajib): `courses/physics-for-software-engineers` Module 5/10/14 sebagai pemanasan kinematika 2D & PID, dan `courses/computer-vision` untuk kedalaman CV umum (lihat Catatan Scope poin 2-3 untuk batas cakupan yang jelas).

---

# Catatan Scope (baca dulu sebelum approve)

1. **Kenapa C++/Python, bukan Java/Spring Boot — meski stack utama repo ini Java.** Seluruh ekosistem robotika praktis (ROS2 client library `rclcpp`/`rclpy`, driver hardware, firmware mikrokontroler real-time, Gazebo/Isaac Sim Python API, PyTorch untuk RL) native C++ dan Python — ini kondisi industri, bukan preferensi: Boston Dynamics, Waymo, iRobot, dan seluruh ekosistem ROS berjalan di atas kombinasi C/C++ (real-time/embedded/driver) dan Python (orchestration/AI/prototyping). Course ini **tidak meninggalkan** stack utama repo — mengikuti pola `courses/computer-vision` dan `courses/llm`, Module 14 (Autonomous Robots — Integrasi Full-Stack) eksplisit membahas **"Production Discussion: Spring Boot Integration"**: robot sebagai node yang mengirim telemetry/menerima command lewat REST/MQTT ke backend Spring Boot (fleet management dashboard, monitoring), bukan logic robotika ditulis ulang di Java.

2. **Hubungan dengan `courses/physics-for-software-engineers`** (dicek penuh — course itu Not Started, Module 5.4/10/14 menyentuh forward/inverse kinematics lengan robot 2D 2-3 sendi, literasi sensor accelerometer/gyro/LIDAR, dan PID dasar untuk inverted pendulum/lengan robot, sebagai **satu bagian kecil** dari course fisika umum yang juga mencakup physics engine, ray tracer, dan simulasi fluida — capstone-nya bahkan menawarkan "Robot Kinematics Simulator" sebagai salah satu dari tiga jalur pilihan). Course Robotics ini adalah **spesialisasi vertikal penuh**, jauh melampaui: kinematika 3D penuh dengan screw theory & DH parameter (bukan cuma 2D), dinamika Newton-Euler/Lagrangian, ROS2 penuh, SLAM, path planning, embedded real hardware (bukan cuma "literasi datasheet"), computer vision terapan, AI/RL, simulasi lanjut, sampai integrasi full-stack autonomous system. `physics-for-software-engineers` tetap opsional sebagai pemanasan matematika/intuisi, **tidak wajib** — kalau sudah diambil, Module 1-3 course ini akan terasa familiar di bagian kinematika 2D tapi baru sepenuhnya di 3D/screw-theory dan seluruh modul setelahnya. Tidak ada modul yang di-drop dari course itu karena scope-nya memang berbeda total (dua dari tiga jalur capstone-nya — Physics Engine, Ray Tracer — tidak ada hubungannya dengan robotika).

3. **Hubungan dengan `courses/computer-vision`** (12 modul, sudah lengkap: classification, object detection, segmentation, tracking, OCR, Vision Transformer, VLM, deployment). Module 11 course Robotics ini **sengaja tidak mengulang** breadth itu — cakupannya sempit dan spesifik ke aplikasi robotika: kalibrasi kamera (intrinsic/extrinsic) untuk pemasangan sensor di robot, stereo/depth vision, visual servoing (closed-loop kontrol berbasis vision), fiducial marker (AprilTag/ArUco) untuk ground-truth localization, dan integrasi output CV ke perception pipeline SLAM/Nav2. `courses/computer-vision` jadi prasyarat opsional (direkomendasikan, bukan wajib) — kalau sudah diambil, Module 11 di sini terasa cepat karena tinggal aplikasi dari model yang sudah dikuasai, bukan konsep CV baru.

4. **Tidak ada overlap dengan `courses/ml-fundamentals` atau `courses/neural-network`** — dicek penuh, reinforcement learning belum pernah diajarkan di course manapun di repo ini (`neural-network` murni CNN matematis dari nol, `ml-fundamentals` tidak menyentuh RL). Module 12 course ini jadi pengantar RL pertama di repo, di-scope ketat untuk kontrol robotika (policy untuk locomotion/manipulation sederhana, sim-to-real), **bukan** RL umum (game-playing, LLM alignment/RLHF — itu di luar scope course ini).

5. **Kalibrasi "dari 0 sampai Expert untuk Software Engineer"**: asumsi programming solid sudah given (tidak ada modul "apa itu variabel"), tapi nol asumsi pengalaman robotika/elektronika — course dimulai dari representasi rotasi 3D yang untuk kebanyakan software engineer benar-benar baru. Course sengaja tidak berhenti di "robot jalan di simulator" — Module 13-14 eksplisit membahas hardware nyata, safety/fail-safe (e-stop, watchdog), observability robotika, dan deployment, mengikuti filosofi root `CLAUDE.md`: teori tanpa production consideration belum selesai.

Referensi riset yang dipakai menyusun roadmap ini: [Modern Robotics: Mechanics, Planning, and Control — Kevin M. Lynch & Frank C. Park](https://hades.mech.northwestern.edu/images/2/2e/MR-largefont-v2.pdf) (tersedia gratis, Northwestern) beserta [Coursera Specialization](https://www.coursera.org/specializations/modernrobotics)-nya untuk struktur kinematika/dinamika berbasis screw theory (Module 1-3), [ROS 2 Official Documentation](https://docs.ros.org/en/jazzy/) (distribusi Jazzy) untuk seluruh Module 6-7 & Module 10 (Nav2), [Probabilistic Robotics — Thrun, Burgard, Fox](https://mitpress.mit.edu/9780262201629/probabilistic-robotics/) sebagai rujukan standar Bayes filter/Kalman/particle filter/SLAM (Module 8-9), [Planning Algorithms — Steven LaValle](http://lavalle.pl/planning/) untuk sampling-based motion planning (RRT/PRM, Module 10), [PythonRobotics (Sakai et al., arXiv:1808.10703)](https://arxiv.org/pdf/1808.10703) sebagai koleksi implementasi referensi kinematics/SLAM/path-planning yang bisa langsung dijalankan untuk validasi, [ros2_control documentation](https://control.ros.org/) untuk aktuator/hardware interface (Module 5), dan [NVIDIA Isaac Sim ROS2 Navigation docs](https://docs.isaacsim.omniverse.nvidia.com/) beserta studi [Sim-to-Real Transfer for Mobile Robots (arXiv:2501.02902)](https://arxiv.org/abs/2501.02902) untuk Module 13 (simulasi lanjut & sim-to-real gap).

---

# Learning Objectives

* [ ] Menguasai representasi ruang 3D untuk robotika: rotation matrix, Euler angle, quaternion, homogeneous transform, coordinate frame — fondasi matematis yang dipakai di seluruh modul berikutnya
* [ ] Menghitung forward & inverse kinematics manipulator (DH parameter, screw theory/Product of Exponentials) dan kinematika mobile robot (differential drive, Ackermann, omnidirectional)
* [ ] Memahami dinamika dasar (Newton-Euler/Lagrangian secukupnya) dan menerapkan feedback control (PID, state-space) untuk menstabilkan sistem fisis secara real-time
* [ ] Membaca datasheet & mengintegrasikan sensor robotika nyata: encoder, IMU, LiDAR, kamera, ultrasonic/ToF, GPS/GNSS
* [ ] Memahami aktuator (motor DC/servo/stepper/BLDC, motor driver) dan memprogram mikrokontroler (ESP32/STM32) untuk kontrol real-time dengan RTOS dasar
* [ ] Mahir ROS2 fundamental: node, topic, service, action, parameter, package/workspace, DDS/QoS
* [ ] Mampu memodelkan robot (URDF/Xacro, TF2) dan mensimulasikannya di Gazebo/RViz2
* [ ] Menguasai state estimation & sensor fusion: Bayes filter, Kalman Filter/EKF/UKF, particle filter
* [ ] Mampu membangun sistem SLAM (occupancy grid, graph-based SLAM, loop closure) untuk pemetaan & lokalisasi simultan
* [ ] Mampu mengimplementasikan path & motion planning (A*/Dijkstra, RRT/RRT*/PRM) dan mengoperasikan stack navigasi Nav2 penuh
* [ ] Menerapkan computer vision terapan untuk robotika: kalibrasi kamera, visual servoing, fiducial marker, integrasi ke perception pipeline
* [ ] Memahami dasar reinforcement learning & imitation learning untuk kontrol robot, plus behavior tree untuk pengambilan keputusan
* [ ] Mahir simulasi lanjut & digital twin (Gazebo, NVIDIA Isaac Sim/MuJoCo) serta memahami sim-to-real gap dan domain randomization
* [ ] Mampu mengintegrasikan seluruh pilar menjadi sistem autonomous robot production-aware: safety/fail-safe, observability, deployment ke hardware nyata
* [ ] Menyelesaikan Capstone Project: robot mobile otonom simulasi end-to-end (persepsi → SLAM → planning → navigasi) yang portfolio-ready

---

# Course Modules

## Module 1 — Fondasi Matematika & Representasi Ruang 3D

Description: Sebelum bicara robot, kuasai dulu bahasa yang dipakai untuk mendeskripsikan posisi dan orientasinya di ruang 3D — fondasi yang untuk kebanyakan software engineer benar-benar baru, dan kesalahpahaman di sini menular ke semua modul berikutnya.

Lessons:

* [ ] 1.1 Vektor & matriks untuk robotika: recap secukupnya (dot/cross product, matrix multiplication) dengan aplikasi langsung ke posisi/orientasi
* [ ] 1.2 Representasi rotasi: rotation matrix (SO(3)), Euler angle (dan masalah gimbal lock), quaternion — kapan pakai yang mana
* [ ] 1.3 Homogeneous transformation (SE(3)): menggabungkan rotasi & translasi jadi satu operasi, transform chaining antar coordinate frame
* [ ] 1.4 Coordinate frame convention robotika: world frame, base frame, end-effector frame, sensor frame — kenapa disiplin frame ini krusial (sumber bug paling umum pemula)
* [ ] 1.5 Pengantar screw theory: twist & wrench secukupnya sebagai jembatan ke Module 2 (Modern Robotics Ch. 2-3)

Mini Project: Implementasi library kecil (Python) untuk konversi antar representasi rotasi (rotation matrix ↔ quaternion ↔ Euler) dan komposisi transform SE(3) berantai, divalidasi terhadap `scipy.spatial.transform` sebagai ground truth.

Status: Not Started

---

## Module 2 — Kinematika: Forward & Inverse

Description: Menjawab dua pertanyaan inti manipulator dan mobile robot — "kalau sendi/roda begini, di mana end-effector/robot berada?" (forward) dan sebaliknya (inverse) — pakai kerangka screw theory modern, bukan cuma tabel DH yang dihafal.

Lessons:

* [ ] 2.1 Forward kinematics manipulator: DH parameter klasik vs Product of Exponentials (screw theory) — kenapa PoE lebih robust terhadap kesalahan konvensi
* [ ] 2.2 Inverse kinematics: closed-form vs numerical (Newton-Raphson), workspace & reachability, multiple solutions
* [ ] 2.3 Jacobian manipulator: velocity kinematics, singularitas, manipulability
* [ ] 2.4 Kinematika mobile robot: differential drive, Ackermann steering (mobil), omnidirectional (mecanum/holonomic) — model matematis tiap jenis
* [ ] 2.5 Odometry: menghitung estimasi posisi dari kinematika roda + encoder, dan sumber error akumulatif (drift)

Mini Project: Implementasi forward + numerical inverse kinematics untuk lengan robot 3-DOF (Python, divalidasi round-trip forward→inverse→forward), plus simulasi odometry differential-drive robot yang menunjukkan drift akumulatif terhadap ground truth.

Status: Not Started

---

## Module 3 — Dinamika & Fondasi Sistem Kontrol

Description: Kinematika menjawab "di mana", dinamika & kontrol menjawab "bagaimana menggerakkannya dengan benar dan stabil" — pilar yang membedakan robot yang "bisa gerak" dari yang "gerak dengan presisi dan aman".

Lessons:

* [ ] 3.1 Dinamika secukupnya: Newton-Euler & Lagrangian formulation — intuisi, bukan derivasi penuh, cukup untuk paham dari mana torque requirement berasal
* [ ] 3.2 Open-loop vs closed-loop control: kenapa feedback wajib untuk sistem fisis nyata (disturbance, model error)
* [ ] 3.3 PID controller mendalam: tuning (Ziegler-Nichols secukupnya), windup, derivative kick, dan kapan PID tidak cukup
* [ ] 3.4 State-space representation & pengantar kontrol modern (state feedback secukupnya) — jembatan ke topik lanjut di luar scope course ini
* [ ] 3.5 Stabilitas sistem: pengantar intuitif (pole location, Bode plot secukupnya) — cukup untuk mendiagnosis kenapa sistem berosilasi/divergen

Mini Project: Tuning PID controller untuk menstabilkan simulasi inverted pendulum (atau posisi motor DC dengan disturbance), dengan dokumentasi before/after step response (overshoot, settling time, steady-state error).

Status: Not Started

---

## Module 4 — Sensor untuk Robotika

Description: Robot tanpa sensor yang benar hanya menebak — modul ini membangun kemampuan membaca datasheet dan memahami karakteristik noise/error tiap sensor sebelum data itu dipakai untuk estimasi state (Module 8) atau SLAM (Module 9).

Lessons:

* [ ] 4.1 Encoder (incremental & absolute): prinsip kerja, resolusi, kaitannya dengan odometry (Module 2.5)
* [ ] 4.2 IMU (accelerometer + gyroscope): apa yang sebenarnya diukur, bias & drift, kenapa IMU sendirian tidak cukup untuk posisi
* [ ] 4.3 LiDAR: prinsip time-of-flight, 2D vs 3D LiDAR, point cloud sebagai representasi data, noise characteristic
* [ ] 4.4 Kamera sebagai sensor robotika: RGB vs depth (stereo/structured light/ToF), field of view, frame rate trade-off
* [ ] 4.5 Sensor jarak lain (ultrasonic, IR) & GPS/GNSS: use case, akurasi, kapan masing-masing relevan (indoor vs outdoor)

Mini Project: Membaca & memvisualisasikan data sensor nyata (rosbag publik atau dataset LiDAR/IMU) — mengidentifikasi noise pattern IMU dan menghitung karakteristik error dasar (bias, variance) dari data mentah.

Status: Not Started

---

## Module 5 — Aktuator & Embedded Systems

Description: Bagaimana perintah software akhirnya jadi gerakan fisik — motor, driver, dan mikrokontroler yang menjalankan loop kontrol real-time, di luar dunia "request-response" yang biasa dikenal software engineer backend.

Lessons:

* [ ] 5.1 Jenis motor: DC brushed, servo, stepper, BLDC — karakteristik torque/speed dan kapan masing-masing dipilih
* [ ] 5.2 Motor driver: H-bridge untuk DC motor, ESC untuk BLDC — prinsip PWM untuk kontrol kecepatan/posisi
* [ ] 5.3 Mikrokontroler untuk robotika (ESP32/STM32): GPIO, PWM, komunikasi I2C/SPI/UART untuk interfacing sensor & aktuator
* [ ] 5.4 Pengantar RTOS (FreeRTOS): kenapa "real-time" beda dari "cepat", task scheduling, interrupt handling — konsep yang tidak ada padanannya di backend web
* [ ] 5.5 Interface hardware ke ROS2: micro-ROS atau serial bridge, `ros2_control` untuk abstraksi hardware interface

Mini Project: Program mikrokontroler (ESP32/STM32, atau simulator seperti Wokwi kalau hardware fisik tidak tersedia) untuk membaca satu sensor + mengontrol satu motor dengan closed-loop PID sederhana (Module 3), dikirim/diterima via serial.

Status: Not Started

---

## Module 6 — ROS2 Fundamentals

Description: Middleware standar industri robotika modern — bukan sekadar library, tapi arsitektur komunikasi terdistribusi yang jadi tulang punggung hampir semua modul praktik setelah ini.

Lessons:

* [ ] 6.1 Arsitektur ROS2: node, DDS sebagai middleware komunikasi, kenapa ROS2 (bukan ROS1) jadi standar sekarang (real-time, multi-robot, tanpa single point of failure)
* [ ] 6.2 Topic (publisher/subscriber) & message: komunikasi asynchronous, custom message type
* [ ] 6.3 Service & Action: request-response synchronous (service) vs long-running task dengan feedback (action) — kapan pakai yang mana
* [ ] 6.4 Parameter, package, workspace, dan build system `colcon` — struktur proyek ROS2 yang idiomatis
* [ ] 6.5 QoS (Quality of Service) profile: reliability, durability — kenapa ini krusial untuk sensor data yang lossy-tolerant vs command yang harus reliable

Mini Project: Bangun package ROS2 kecil dengan minimal 2 node yang berkomunikasi lewat topic custom (mis. publisher sensor simulasi + subscriber yang memproses & menampilkan data), plus satu service sederhana.

Status: Not Started

---

## Module 7 — Pemodelan Robot & Simulasi Dasar

Description: Sebelum mengontrol robot (nyata atau simulasi), robot itu harus dideskripsikan secara formal — struktur link/joint, dan bagaimana ROS2 melacak posisi tiap bagian robot relatif satu sama lain dari waktu ke waktu.

Lessons:

* [ ] 7.1 URDF (Unified Robot Description Format): mendeskripsikan link, joint, visual/collision geometry
* [ ] 7.2 Xacro: macro untuk URDF yang reusable — menghindari duplikasi definisi robot kompleks
* [ ] 7.3 TF2: transform tree, broadcasting & listening transform antar frame secara real-time (menyambung Module 1.4)
* [ ] 7.4 RViz2: visualisasi robot state, sensor data (LiDAR/point cloud), dan transform tree untuk debugging
* [ ] 7.5 Gazebo dasar: mensimulasikan robot dari URDF dengan physics engine, plugin sensor (LiDAR/kamera/IMU simulasi)

Mini Project: Deskripsikan robot mobile sederhana (differential drive) dalam URDF/Xacro lengkap dengan sensor LiDAR, jalankan di Gazebo, visualisasikan transform tree & sensor data-nya di RViz2.

Status: Not Started

---

## Module 8 — Estimasi State & Sensor Fusion

Description: Sensor tunggal selalu noisy atau tidak lengkap — modul ini membangun kemampuan menggabungkan beberapa sumber data noisy jadi satu estimasi state yang lebih dapat dipercaya, fondasi matematis wajib sebelum SLAM (Module 9).

Lessons:

* [ ] 8.1 Bayes filter sebagai kerangka umum state estimation — intuisi probabilistik di balik semua filter berikutnya
* [ ] 8.2 Kalman Filter: asumsi linier-Gaussian, predict-update cycle, kenapa ini optimal di bawah asumsinya
* [ ] 8.3 Extended Kalman Filter (EKF) & Unscented Kalman Filter (UKF): menangani sistem non-linier (kasus robot nyata)
* [ ] 8.4 Particle filter: state estimation non-parametrik untuk distribusi multi-modal, trade-off komputasi vs Kalman-based filter
* [ ] 8.5 Sensor fusion praktis dengan `robot_localization` (ROS2): menggabungkan odometry + IMU + GPS jadi satu estimasi pose yang stabil

Mini Project: Implementasi EKF dari nol (Python, tanpa library black-box) untuk fusion odometry + IMU pada simulasi differential-drive robot, dibandingkan akurasinya terhadap odometry saja (before/after drift).

Status: Not Started

---

## Module 9 — SLAM (Simultaneous Localization and Mapping)

Description: Masalah "ayam dan telur" klasik robotika mobile — robot butuh peta untuk melokalisasi diri, tapi butuh tahu posisinya untuk membangun peta yang akurat. Modul ini membongkar bagaimana SLAM menyelesaikan keduanya sekaligus.

Lessons:

* [ ] 9.1 Occupancy grid mapping: representasi peta probabilistik dari data LiDAR, log-odds update
* [ ] 9.2 EKF-SLAM: memperluas EKF (Module 8.3) untuk mengestimasi pose robot + posisi landmark secara simultan
* [ ] 9.3 Graph-based SLAM: pose graph, factor graph, pose graph optimization (least-squares) — pendekatan dominan SLAM modern
* [ ] 9.4 Loop closure: mendeteksi robot kembali ke lokasi yang pernah dikunjungi, dan kenapa ini krusial untuk mengoreksi akumulasi drift
* [ ] 9.5 SLAM praktis di ROS2: SLAM Toolbox atau Cartographer — menjalankan SLAM real-time di atas data LiDAR simulasi/rosbag

Mini Project: Jalankan SLAM Toolbox di Gazebo (robot dari Module 7) untuk memetakan environment simulasi dari nol, verifikasi loop closure bekerja dengan menggerakkan robot dalam loop tertutup dan membandingkan peta sebelum/sesudah koreksi.

Status: Not Started

---

## Module 10 — Path & Motion Planning

Description: Punya peta (Module 9) dan tahu posisi diri (Module 8) belum cukup — robot masih perlu memutuskan rute mana yang aman dan efisien menuju tujuan, lalu mengikutinya secara real-time sambil menghindari obstacle dinamis.

Lessons:

* [ ] 10.1 Graph search klasik: Dijkstra, A* — heuristic design, kapan masing-masing cocok untuk grid map
* [ ] 10.2 Sampling-based planning: RRT, RRT*, PRM (Probabilistic Roadmap) — kenapa pendekatan ini scale ke ruang konfigurasi dimensi tinggi (manipulator) tempat graph search klasik tidak praktis
* [ ] 10.3 Costmap: layering (static, obstacle, inflation) sebagai representasi lingkungan untuk planner
* [ ] 10.4 Trajectory & local planning: dari path (geometris) ke trajectory (dengan velocity/timing), local controller untuk obstacle avoidance real-time (DWA/TEB)
* [ ] 10.5 Nav2 stack penuh: behavior tree sebagai orkestrator (planner server, controller server, recovery behavior), konfigurasi end-to-end

Mini Project: Konfigurasi Nav2 penuh di atas peta hasil SLAM (Module 9), robot menavigasi otonom dari titik A ke B melewati obstacle statis, lalu obstacle dinamis ditambahkan untuk menguji local planner/recovery behavior.

Status: Not Started

---

## Module 11 — Computer Vision Terapan untuk Robotika

Description: Bukan pengulangan `courses/computer-vision` (lihat Catatan Scope poin 3) — modul ini sempit dan spesifik: bagaimana kamera dikalibrasi dan diintegrasikan sebagai sensor persepsi dalam loop kontrol robot, bukan breadth task CV umum.

Lessons:

* [ ] 11.1 Kalibrasi kamera: intrinsic (focal length, distortion) & extrinsic (posisi kamera relatif ke robot frame, menyambung Module 1.4) — kenapa kalibrasi yang salah merusak semua persepsi hilir
* [ ] 11.2 Stereo & depth vision: disparity map, point cloud dari depth camera, integrasi dengan LiDAR (sensor fusion persepsi)
* [ ] 11.3 Fiducial marker (AprilTag/ArUco): ground-truth localization untuk testing/kalibrasi, use case gudang/manufaktur
* [ ] 11.4 Visual servoing: closed-loop control berbasis feedback visual real-time (position-based vs image-based)
* [ ] 11.5 Integrasi output CV (deteksi objek dari model yang sudah ada/`courses/computer-vision`) ke perception pipeline ROS2 — object jadi obstacle di costmap atau target navigasi

Mini Project: Kalibrasi kamera simulasi di Gazebo (Module 7), deteksi & lokalisasi AprilTag, gunakan hasilnya sebagai target visual servoing sederhana (robot mendekati marker sampai jarak tertentu).

Status: Not Started

---

## Module 12 — AI & Learning untuk Robotika

Description: Melengkapi pendekatan klasik (kinematika/kontrol/planning berbasis model) dengan pendekatan berbasis data — kapan robot lebih baik belajar dari pengalaman/demonstrasi daripada diprogram secara eksplisit.

Lessons:

* [ ] 12.1 Reinforcement learning dasar untuk kontrol: MDP, reward shaping, policy — kenapa RL cocok untuk task yang sulit dimodelkan analitik (locomotion, manipulation kontak-kaya)
* [ ] 12.2 RL praktis untuk robot: training policy sederhana di simulasi (mis. dengan Gymnasium/Isaac Gym-style environment)
* [ ] 12.3 Imitation learning: belajar dari demonstrasi manusia sebagai alternatif/pelengkap RL, kapan lebih efisien
* [ ] 12.4 Behavior tree untuk decision making: struktur modular untuk task-level logic (menyambung Nav2 Module 10.5), dibanding finite state machine
* [ ] 12.5 Sim-to-real transfer: kenapa policy yang bagus di simulasi sering gagal di robot nyata, domain randomization sebagai mitigasi (jembatan ke Module 13)

Mini Project: Train policy RL sederhana (mis. balancing atau navigasi grid dasar) di simulasi, dokumentasikan reward curve dan bandingkan hasil terhadap baseline hand-coded controller dari Module 3.

Status: Not Started

---

## Module 13 — Simulasi Lanjut & Digital Twin

Description: Simulasi sebagai infrastruktur produksi robotika modern, bukan sekadar tempat belajar — modul ini masuk ke tooling yang dipakai industri untuk training RL skala besar dan validasi sebelum deployment ke hardware mahal/berbahaya.

Lessons:

* [ ] 13.1 Gazebo mendalam: physics engine (ODE/Bullet/DART), plugin sensor lanjut, world building untuk skenario testing kompleks
* [ ] 13.2 NVIDIA Isaac Sim: photorealistic rendering, GPU-accelerated physics, integrasi ROS2 — kapan ini lebih unggul dari Gazebo (training RL skala besar, sensor synthetic data)
* [ ] 13.3 MuJoCo sebagai alternatif untuk riset kontrol/RL: kecepatan simulasi, akurasi kontak — trade-off dibanding Gazebo/Isaac Sim
* [ ] 13.4 Domain randomization: randomize tekstur, massa, friction, noise sensor saat training untuk menutup sim-to-real gap (Module 12.5)
* [ ] 13.5 Digital twin sebagai konsep production: simulasi yang terus disinkronkan dengan robot nyata untuk monitoring/predictive maintenance

Mini Project: Ambil skenario navigasi dari Module 10 dan jalankan ulang di Isaac Sim atau MuJoCo (bukan Gazebo), bandingkan behavior/performa, dokumentasikan perbedaan fidelity simulasi yang teramati.

Status: Not Started

---

## Module 14 — Autonomous Robots: Integrasi Full-Stack & Production

Description: Modul penutup yang menyatukan sense → estimate → plan → act menjadi satu sistem, plus pertimbangan yang membedakan "demo yang jalan" dari "sistem yang aman dan bisa dipercaya di dunia nyata" — mengikuti filosofi root `CLAUDE.md` bahwa teori belum selesai tanpa production consideration.

Lessons:

* [ ] 14.1 Integrasi end-to-end: menyambung seluruh pipeline (sensor → state estimation → SLAM → planning → kontrol aktuator) jadi satu sistem autonomous yang koheren
* [ ] 14.2 Safety & fail-safe: e-stop (hardware & software), watchdog timer, behavior saat sensor gagal/hilang — kenapa ini bukan "nice to have" di robotika (berbeda dari software biasa, kegagalan berarti kerusakan fisik)
* [ ] 14.3 Observability robotika: logging rosbag untuk post-mortem, monitoring health node/topic, diagnostic aggregator ROS2
* [ ] 14.4 Multi-robot & fleet overview: namespace ROS2 untuk multi-robot, tantangan koordinasi (sekadar peta, bukan pendalaman)
* [ ] 14.5 **Production Discussion: Spring Boot Integration** — robot sebagai node yang mengirim telemetry & menerima command lewat REST/MQTT ke backend Spring Boot untuk fleet dashboard, mengikuti pola integrasi yang sudah dibahas `courses/computer-vision` Module 12 dan `courses/llm` Module 4/9 (tidak diulang detail arsitekturnya di sini)

Mini Project: Tambahkan safety layer (e-stop simulasi + watchdog) ke sistem navigasi Module 10, simulasikan kegagalan sensor (LiDAR mati mendadak) dan buktikan robot berhenti aman alih-alih melanjutkan dengan data stale.

Status: Not Started

---

# Capstone Project

Description: **Robot Mobile Otonom End-to-End (Simulasi)** — bangun satu sistem robot mobile yang mampu memetakan lingkungan baru, melokalisasi dirinya, merencanakan rute, dan menavigasi secara otonom untuk menyelesaikan satu task nyata, menggabungkan minimal 5 modul inti dari course ini (persepsi, state estimation, SLAM, path planning, integrasi/safety). Contoh task (atau task lain yang lebih personal):

1. **Warehouse patrol robot**: robot memetakan gudang simulasi (Gazebo), lalu berpatroli otonom melewati waypoint sambil mendeteksi obstacle baru dan AprilTag sebagai checkpoint (Module 11).
2. **Delivery robot indoor**: robot menerima target lokasi (mis. via topic/service ROS2), merencanakan rute dari peta SLAM, menavigasi menghindari obstacle dinamis (orang berjalan disimulasikan), berhenti aman kalau sensor gagal (Module 14).
3. **Exploration robot**: robot mengeksplorasi environment tak dikenal secara otonom (frontier exploration sederhana) sampai peta dianggap "cukup lengkap", lalu kembali ke titik awal.

Bangun end-to-end: (1) robot dimodelkan penuh dengan URDF/Xacro + sensor LiDAR & kamera (Module 7), (2) SLAM membangun peta dari nol (Module 9), (3) sensor fusion (EKF, Module 8) menjaga estimasi pose tetap stabil, (4) Nav2 menavigasi otonom dengan obstacle avoidance (Module 10), (5) safety layer mencegah robot melanjutkan aksi berbahaya saat sensor/plan gagal (Module 14), (6) seluruh sistem berjalan sebagai package ROS2 yang bisa dijalankan ulang (`ros2 launch`), bukan skrip ad-hoc.

Acceptance Criteria:

* Robot berhasil memetakan environment simulasi baru dari nol menggunakan SLAM (bukan peta yang sudah disiapkan sebelumnya)
* Sensor fusion (EKF/`robot_localization`) terbukti mengurangi drift dibanding odometry murni, didokumentasikan dengan perbandingan lintasan
* Navigasi otonom berhasil mencapai minimal 3 waypoint berbeda, termasuk menghindari minimal 1 obstacle dinamis yang tidak ada di peta awal
* Safety layer terbukti bekerja: skenario kegagalan sensor disimulasikan secara eksplisit dan robot berhenti aman (bukan crash/collision/hang)
* Seluruh sistem reproducible via satu `ros2 launch` file, dengan dokumentasi arsitektur (diagram node/topic) dan instruksi menjalankan ulang
* Hasil (kode, rosbag demo, dokumentasi) disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -
