# Fisika Terapan untuk Software Engineer — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Fisika Terapan untuk Software Engineer (Physics for Software Engineers) — mekanika klasik/Newtonian, metode numerik simulasi, rigid body dynamics, dan secukupnya optik/elektromagnetisme yang dipakai langsung untuk membangun software: physics engine, particle simulator, ray tracer, dan skrip kinematika robotika.

## Scope

Fisika sebagai alat kerja seorang software engineer — "fisika YANG diterapkan lewat kode," bukan fisika untuk fisikawan. Setiap modul harus terhubung eksplisit ke sesuatu yang benar-benar dibangun software engineer:

- **Kinematika & dinamika dasar**: posisi/kecepatan/percepatan, gaya (force), tiga Hukum Newton, free body diagram — fondasi untuk semua simulasi gerak.
- **Kerja, energi, dan momentum**: work-energy theorem, kekekalan energi, kekekalan momentum linear — dipakai untuk validasi/debugging simulasi (mis. "kenapa energi total simulasi saya naik terus, padahal harusnya kekal?").
- **Mekanika rotasi & torsi**: momen inersia, torsi, momentum sudut, kekekalan momentum sudut — fondasi rotasi rigid body dalam game/robot.
- **Metode integrasi numerik untuk simulasi**: Euler eksplisit, semi-implicit Euler (symplectic Euler), Verlet integration, Runge-Kutta orde-4 (RK4) — matematika sesungguhnya di balik game engine & physics engine, termasuk trade-off stabilitas numerik vs akurasi vs biaya komputasi per metode.
- **Rigid body dynamics & collision detection/response**: representasi rigid body (posisi, orientasi/quaternion, kecepatan linear & sudut), broad-phase vs narrow-phase collision detection, collision response (impulse-based), constraint solving secukupnya untuk memahami cara kerja Box2D/Bullet/PhysX — inti dari physics engine sungguhan.
- **Optik & gelombang untuk computer graphics**: hukum pemantulan (reflection) dan pembiasan (refraction/Snell's law), dasar-dasar ray tracing (ray-object intersection, shading model dasar), pengantar light transport untuk rendering fotorealistik (BRDF secukupnya, bukan transport theory penuh) — TIDAK mencakup optik kuantum.
- **Literasi elektromagnetisme/rangkaian untuk sensor & hardware**: hukum Ohm, rangkaian dasar (seri/paralel), konsep sensor umum (accelerometer, gyroscope, LIDAR/ultrasonic) secukupnya untuk membaca datasheet dan menulis firmware/software yang menginterpretasi data sensor dengan benar — pada level literasi software engineer yang berinteraksi dengan hardware/IoT/robotika, bukan level insinyur elektro.
- **Osilasi & gelombang**: gerak harmonik sederhana (SHM), damping/resonansi, gelombang mekanik, sampling/aliasing — fondasi untuk audio synthesis, signal processing, dan animasi berbasis spring/damper.
- **Fluida**: statika fluida (buoyancy), persamaan Bernoulli, viskositas/bilangan Reynolds, metode simulasi partikel fluida (SPH) — dipakai untuk simulasi cairan/gas dalam game/graphics engineering.
- **Termodinamika**: Hukum I & II Termodinamika, perpindahan kalor, heat equation — dipakai untuk simulasi termal (mis. heat diffusion di grid/particle system) dan pemahaman batasan fisis sistem tertutup.
- **Kontrol (feedback control)**: feedback loop, kontroler PID, tuning, studi kasus seperti inverted pendulum — fondasi untuk software kontrol robotika/embedded dan simulasi sistem fisis yang self-correcting.

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Relativitas Khusus & Umum (Special/General Relativity)** — sepenuhnya dimiliki domain [[domains/relativity/DOMAIN.md|relativity]] yang sudah ada di repo ini (saat ini terdaftar Paused, bisa dilanjutkan kapan saja). Domain ini murni mekanika klasik/Newtonian (v << c, tanpa efek relativistik) — kalau learner butuh koreksi relativistik (mis. kenapa GPS perlu koreksi dilatasi waktu), itu diarahkan ke domain relativity, bukan dibahas di sini.
- **Mekanika kuantum / fisika komputasi kuantum** — domain terpisah, tidak dibahas di sini sama sekali (baik quantum mechanics maupun quantum computing).
- **Kurikulum teknik elektro/desain rangkaian penuh** — hanya sebanyak yang dibutuhkan untuk menalar sensor/hardware/IoT/robotika dari sisi software engineer (baca datasheet, interpretasi sinyal, rangkaian dasar); bukan pengganti pendidikan Electrical Engineering (desain PCB, analisis rangkaian AC/DC lanjut, elektronika daya, dst).
- **Kesetaraan gelar/lisensi fisika formal** — ini literasi terapan + artefak yang bisa dibangun (buildable artifacts), bukan pengganti gelar fisika/teknik terakreditasi atau sertifikasi profesi insinyur.

Domain ini beririsan erat dengan domain software-engineering di repo ini (secara sengaja) — dikonsumsi lewat coding aktif (bukan cuma teks/soal konseptual seperti nuclear-technology), konsisten dengan Applied Practice Definition di bawah.

## Trusted Sources / Research Priority

1. **MIT OpenCourseWare 8.01SC — Classical Mechanics** (https://ocw.mit.edu/courses/8-01sc-classical-mechanics-fall-2016/) — materi kuliah resmi MIT untuk mekanika Newtonian (kinematika, dinamika, energi, momentum, rotasi), dipakai sebagai tulang punggung modul-modul mekanika dasar, lengkap dengan problem set & solusi terverifikasi.
2. **MIT OpenCourseWare 6.837 — Computer Graphics** (https://ocw.mit.edu/courses/6-837-computer-graphics-fall-2012/, oleh Wojciech Matusik & Frédo Durand) — materi kuliah resmi untuk ray tracing, graphics pipeline, dan dasar rendering; dipakai sebagai kerangka modul optik/graphics. Sumber setara lain yang bisa dicek silang: kursus rendering gaya Stanford CS248.
3. **David H. Eberly / Ian Millington**, *Game Physics Engine Development* (Ian Millington, 2nd ed., CRC Press) — buku standar industri yang membedah cara kerja physics engine game dari nol (particle physics, rigid body, collision detection/response, constraint solving) dengan kode nyata sebagai referensi utama modul rigid body dynamics.
4. **David M. Bourg & Bryan Bywalec**, *Physics for Game Developers* (2nd ed., O'Reilly) — buku terapan yang menjembatani fisika klasik ke kode game langsung (kinematika, dinamika, tabrakan, ledakan, rigid body) — dipakai sebagai referensi gaya "fisika untuk dikoding," pelengkap Millington.
5. **Christer Ericson**, *Real-Time Collision Detection* (Morgan Kaufmann/CRC Press) — referensi definitif industri untuk algoritma collision detection (bounding volume, broad-phase/narrow-phase, spatial partitioning) — dipakai di modul collision detection/response secara spesifik.
6. **Tomas Akenine-Möller, Eric Haines, Naty Hoffman, et al.**, *Real-Time Rendering* (4th ed.; situs resmi https://www.realtimerendering.com/) — referensi definitif computer graphics real-time (shading, light transport, ray tracing) — dipakai untuk modul optik/rendering di atas fondasi MIT 6.837.
7. **The Feynman Lectures on Physics, Vol. I** (gratis di https://www.feynmanlectures.caltech.edu/) — dipakai untuk membangun intuisi fisis di awal tiap topik mekanika/optik sebelum masuk formalisme/kode, konsisten dengan cara domain [[domains/relativity/DOMAIN.md|relativity]] memakainya.
8. **Dokumentasi & source code resmi physics engine produksi** — Box2D (https://box2d.org/, dokumentasi + source resmi) dan Bullet Physics (https://github.com/bulletphysics/bullet3, plus https://pybullet.org/ untuk binding Python) — dipakai sebagai rujukan "bagaimana expert sungguhan membangun ini," terutama untuk cross-check implementasi rigid body dan collision response terhadap textbook di atas.
9. **Sumber sekunder kredibel untuk literasi sensor/elektronika** (mis. datasheet resmi produsen sensor, dokumentasi platform robotika seperti ROS) — dipakai secukupnya untuk modul sensor/hardware, selalu proporsional dengan scope literasi (bukan EE mendalam) yang dinyatakan di atas.

Klaim matematis/numerik (mis. perbandingan stabilitas Euler vs RK4, rumus collision response) selalu disilangkan minimal ke satu textbook (3-6) DAN dicek terhadap perilaku implementasi nyata di source Box2D/Bullet (8) — tidak cukup hanya dari satu sumber atau dari ingatan/asumsi tanpa verifikasi.

## Applied Practice Definition

Applied practice di domain ini SELALU berarti kode yang benar-benar berjalan dan bisa diperiksa hasil numeriknya — bukan cuma derivasi tertulis (beda dengan domain [[domains/relativity/DOMAIN.md|relativity]] yang menyeimbangkan derivasi tangan + kode; di sini bobotnya condong ke artefak kode karena tujuannya memang applied engineering):

- **Simulasi kecil per konsep** — mis. simulator proyektil dengan gravitasi (kinematika), pendulum sederhana (energi/rotasi), atau particle system dasar (integrasi numerik) — dikode dalam bahasa pilihan learner, dengan validasi numerik eksplisit (mis. cek kekekalan energi/momentum sepanjang simulasi berjalan, bukan cuma "terlihat masuk akal secara visual").
- **Perbandingan metode integrasi** — mengimplementasikan integrator yang sama (Euler, semi-implicit Euler, Verlet, RK4) untuk sistem yang sama, lalu membandingkan stabilitas & akurasi numeriknya secara terukur (mis. energy drift dari waktu ke waktu) — bukan cuma tahu definisinya secara teori.
- **Modul physics engine mini** — komponen collision detection dan/atau collision response (mis. deteksi tabrakan lingkaran-lingkaran atau AABB, lalu impulse response) dibangun dari nol mengikuti pola Millington/Ericson, dites terhadap skenario nyata.
- **Artefak grafis/optik** — ray tracer minimal (ray-sphere/ray-plane intersection + shading dasar) atau demonstrasi refraksi/pemantulan sederhana.
- **Skrip kinematika robotika** — mis. forward kinematics lengan robot sederhana (2-3 sendi), menghubungkan konsep rotasi/torsi ke aplikasi robotika nyata.

Setiap modul teori WAJIB berakhir di salah satu bentuk kode di atas sebelum dianggap selesai, sesuai `# Hands-on First` di `CLAUDE.md` root.

## Project Tiers

- **Mini Project** (per modul): satu simulasi/skrip kecil yang mendemonstrasikan konsep modul itu sendiri (mis. proyektil dengan gravitasi & drag, atau ray-sphere intersection tunggal), dengan validasi numerik eksplisit.
- **Intermediate Project** (setelah beberapa modul terkait): artefak gabungan, mis. **"Mini 2D Physics Engine"** (menggabungkan integrasi numerik + rigid body + collision detection/response untuk beberapa objek sederhana seperti lingkaran/kotak) setelah modul rigid body & collision, atau **"Mini Ray Tracer"** (beberapa primitif + refleksi + refraksi + shading dasar) setelah modul optik/graphics.
- **Capstone / Portfolio Project** (akhir course): pilih salah satu artefak tingkat lanjut yang menunjukkan integrasi penuh — **physics engine 2D kecil yang lengkap** (integrator terpilih + broad/narrow-phase collision + constraint/impulse resolution untuk beberapa bentuk) ATAU **ray tracer dengan light transport dasar** (multiple bounce, BRDF sederhana) ATAU **simulator kinematika robotika** (forward + dasar inverse kinematics untuk lengan robot sederhana) — disimpan ke `portofolio/`, disertai penjelasan tertulis singkat tentang keputusan desain fisika/numerik yang diambil dan trade-off-nya.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak kode di domain ini juga dicek lewat lensa berikut:

- **Physical Correctness** — apakah hasil simulasi konsisten dengan hukum fisika yang mendasarinya (mis. energi/momentum kekal dalam sistem tertutup tanpa gaya eksternal, kecuali memang didesain untuk disipasi)?
- **Numerical Stability** — apakah integrator yang dipilih stabil untuk skenario yang disimulasikan (tidak meledak/divergen), dan apakah trade-off stabilitas vs akurasi vs biaya komputasi dari pilihan integrator dijelaskan secara sadar, bukan kebetulan?
- **Code-Physics Fidelity** — apakah kode benar-benar mengimplementasikan fisika yang dipahami/diturunkan (bukan angka yang di-hardcode/di-fudge supaya "terlihat benar secara visual") — sama seperti lensa "Simulation Fidelity" di domain [[domains/relativity/DOMAIN.md|relativity]].
- **Real-Time/Production Awareness** — mengingat domain ini condong ke game/graphics/robotika, apakah ada kesadaran soal batasan dunia nyata (budget waktu per frame, floating-point precision, kestabilan pada timestep variabel) — bukan cuma solusi matematis yang benar tapi terlalu lambat/tidak stabil untuk dipakai real-time?

## Assessment Form

Concept-check singkat setelah tiap bagian teori (skenario "apa yang terjadi pada energi/momentum sistem ini" untuk menguji pemahaman, bukan hafalan rumus), problem set numerik terarah gaya MIT OCW 8.01 dengan kunci jawaban untuk self-check, dan review kode simulasi (learner menjalankan simulasinya sendiri, membandingkan output numerik terhadap ekspektasi teoretis, dan menjelaskan penyebab deviasi bila ada) sebagai bentuk assessment applied yang khas domain ini.

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk mendekati topik yang sama:

- **Game physics engine engineer** (gaya penulis Box2D/Bullet/PhysX, Millington, Ericson) — fokus pada performa real-time, stabilitas numerik di bawah timestep tidak ideal, dan trade-off akurasi vs kecepatan — lensa dominan untuk modul integrasi numerik & rigid body/collision.
- **Rendering/graphics engineer** (gaya penulis Real-Time Rendering, MIT 6.837) — fokus pada fidelity visual, light transport, dan batasan hardware GPU real-time — lensa dominan untuk modul optik/graphics.
- **Robotics software engineer** (gaya komunitas ROS) — fokus pada kinematika yang harus benar-benar mengendalikan hardware fisik nyata (bukan simulasi murni), toleransi terhadap noise sensor & aktuator — lensa untuk modul kinematika robotika & sensor.
- **Fisikawan/pengajar akademik** (gaya MIT 8.01, Feynman) — rigor derivasi dari hukum dasar, verifikasi lewat limit-check & dimensional analysis — dipakai untuk memastikan fondasi tiap modul benar sebelum dikode, titik temu dengan cara domain [[domains/relativity/DOMAIN.md|relativity]] memvalidasi hasil.

Keempat lensa ini sering menekankan hal berbeda untuk topik yang sama (mis. integrasi numerik: game engineer mengutamakan symplectic Euler karena murah & stabil untuk game loop, sementara fisikawan/numerical analyst mungkin mengutamakan RK4 untuk akurasi) — domain ini melatih learner mengenali trade-off tersebut secara sadar, bukan menganggap satu pilihan sebagai satu-satunya jawaban benar.

## Domain-Specific Standards

`domains/physics-for-software-engineers/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Tidak ada teori tanpa kode.** Konsisten dengan Applied Practice Definition di atas — modul teori yang tidak berakhir dengan artefak kode yang bisa dijalankan dan divalidasi secara numerik dianggap belum selesai.
- **Batas relativitas dinyatakan eksplisit.** Karena domain ini murni klasik/Newtonian, setiap kali topik menyentuh area di mana efek relativistik jadi relevan (kecepatan sangat tinggi, medan gravitasi sangat kuat, presisi GPS), mentor menyatakan eksplisit bahwa itu di luar scope domain ini dan mengarahkan ke domain [[domains/relativity/DOMAIN.md|relativity]] — bukan menjawabnya sekilas dengan pendekatan yang salah/tidak lengkap.
- **Validasi numerik wajib, bukan opsional.** Setiap simulasi yang dianggap "selesai" harus punya bukti eksplisit kesesuaiannya dengan hukum fisika (mis. plot/angka energi sepanjang waktu, perbandingan terhadap solusi analitik jika ada) — bukan cuma "terlihat benar" secara visual, sesuai lensa Review Style "Physical Correctness" & "Code-Physics Fidelity" di atas.
- **Literasi, bukan pengganti kredensial EE/fisika.** Setiap kali lesson menyentuh area yang butuh kredensial nyata (mis. "bagaimana mendesain rangkaian daya untuk hardware produksi sungguhan", "bagaimana sertifikasi insinyur robotika profesional"), mentor menyatakan eksplisit batas ini dan mengarahkan ke jalur pendidikan/sertifikasi resmi yang sesungguhnya — konsisten dengan house rule serupa di `domains/nuclear-technology/DOMAIN.md`.

## Domain Goal

Learner mampu membaca dan membangun sendiri komponen inti dari physics engine, particle simulator, ray tracer sederhana, atau skrip kinematika robotika — dengan pemahaman yang benar tentang fisika klasik yang mendasarinya (kinematika, dinamika, energi/momentum, rotasi) DAN metode numerik yang tepat untuk mensimulasikannya secara stabil (Euler/semi-implicit Euler/Verlet/RK4) — dibuktikan lewat capstone project di portofolio yang mengintegrasikan fisika + numerik + kode produksi-sadar (production-aware). Ini eksplisit BUKAN kredensial fisika/teknik formal — murni kemampuan applied engineering yang menghubungkan latar belakang software engineering learner dengan domain physics engine, computer graphics, dan robotika.

## Registration History

Domain ini registered 2026-08-09, domain pack approved via permintaan user langsung membangun course roadmap-nya. Registered paused mengikuti kebijakan hemat-token 2026-08-07, namun course roadmap tetap dibangun saat itu juga atas permintaan user — lihat `courses/physics-for-software-engineers/roadmap.md`.
