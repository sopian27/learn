# Driving (Mengemudi Mobil) — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Mengemudi Mobil (Driving a Car)

## Scope

Belajar mengemudi mobil dari **titik nol absolut** (belum pernah pegang setir) sampai **level expert**: kontrol kendaraan tingkat lanjut, defensive driving, dan pemulihan dari situasi darurat (skid/selip), setara standar sekolah mengemudi lanjutan internasional. Mencakup: pengenalan kendaraan & kontrol dasar, gerak & parkir dasar, persiapan & kelulusan ujian SIM A (teori + praktik, konteks Indonesia), berkendara di kondisi nyata (jalan raya, malam, hujan), defensive driving & hazard perception, situasi darurat & pemulihan kontrol, perjalanan jarak jauh, sampai pengembangan menuju car control tingkat lanjut.

Secara eksplisit TIDAK mencakup (untuk menghindari overlap dengan domain/course lain di repo ini, dan karena keterbatasan AI mentor yang tidak bisa mengamati fisik langsung):

- **Mengemudi motor / SIM C** — kendaraan roda dua punya dinamika & kurikulum berbeda; jadi domain terpisah bila diminta.
- **Servis & perbaikan mekanik mobil** (ganti oli, servis mesin, diagnosa kerusakan) — itu wilayah domain montir/otomotif terpisah bila diminta; domain ini hanya mencakup pre-drive check dasar (cek ban, lampu, oli sebatas level pengemudi awam).
- **Balap kompetitif / lisensi rally & motorsport** — level "expert" di domain ini berhenti di car control tingkat lanjut & defensive driving ala sekolah performance driving, bukan jalur karier pembalap profesional.
- **Keputusan beli/sewa/kredit mobil** — itu wilayah domain `personal-finance` (`domains/personal-finance/DOMAIN.md`).
- **Supervisi fisik langsung saat praktik nyata** — AI mentor ini TIDAK BISA duduk di kursi penumpang dan mengamati kondisi mengemudi secara langsung. Setiap sesi praktik behind-the-wheel **wajib** didampingi instruktur berlisensi atau pengemudi dewasa berlisensi SIM A yang sah, sesuai hukum setempat (di Indonesia: hanya pemegang SIM yang boleh mengemudi di jalan umum — latihan pra-SIM harus di area tertutup/pribadi atau didampingi sesuai ketentuan Satpas setempat). Domain ini menyediakan teori, checklist, framework, dan review berbasis laporan tertulis learner — bukan pengawasan real-time.

## Trusted Sources / Research Priority

1. **Korlantas Polri** (`korlantas.polri.go.id`, `digitalkorlantas.polri.go.id`) — sumber resmi syarat, tahapan, dan materi ujian SIM A di Indonesia (ujian teori sistem AVIS, ujian praktik: uji pengereman/keseimbangan, U-turn, lintasan S, uji reaksi rem menghindar). Dipakai untuk Module 3 & 5.
2. **UU No. 22 Tahun 2009 tentang Lalu Lintas dan Angkutan Jalan (LLAJ)** dan **Perpol No. 2 Tahun 2023** — dasar hukum aturan lalu lintas, rambu, marka, dan syarat SIM. Lesson terkait selalu mencatat bahwa aturan bisa berubah — cross-check ke sumber resmi Korlantas untuk versi terbaru sebelum ujian sungguhan.
3. **IAM RoadSmart** (`iamroadsmart.com`, UK, quality-assured oleh DVSA) — standar "gold standard" untuk advanced/defensive driving berbasis sistem police car control, framework IPSGA (Information-Position-Speed-Gear-Acceleration). Dipakai sebagai reference frame tingkat lanjut di Module 7, karena Indonesia belum punya standar defensive-driving institusional setara.
4. **National Safety Council Defensive Driving Course** (AS) dan **Smith System** — framework defensive driving alternatif, dipakai untuk cross-check konsep hazard perception & following distance.
5. **Skip Barber Racing School** dan **Bob Bondurant School of High-Performance Driving** (AS) — kurikulum car-control-at-the-limit: braking module (ABS, emergency lane change), skid pad (pemulihan selip), autocross (racing line). Dipakai sebagai reference frame level "expert" di Module 8 & 10 — dicatat jelas bahwa praktik skid pad sungguhan butuh fasilitas terkontrol yang mungkin tidak tersedia lokal, sehingga lesson terkait menyediakan alternatif: teori + simulasi + rekomendasi kursus lokal setara bila ada.
6. **Buku manual pemilik kendaraan (owner's manual)** — dipakai generik (bukan brand spesifik) untuk materi instrumen dashboard, indikator peringatan, dan pre-drive check dasar.

Jangan pernah membangun lesson keselamatan (defensive driving, emergency maneuver, skid recovery) hanya dari satu sumber blog/YouTube tanpa cross-check ke minimal satu sumber institusional di atas, sesuai bar "Vetting a Source" di `standards/UNIVERSAL_STANDARDS.md`. Materi hukum/ujian SIM selalu diakhiri catatan "konfirmasi ke Satpas/Korlantas setempat untuk aturan terbaru", sama seperti pola disclaimer legal/pajak di `domains/personal-finance/DOMAIN.md` dan `domains/entrepreneurship/DOMAIN.md`.

## Applied Practice Definition

Setiap lesson menghasilkan **sesi praktik behind-the-wheel nyata** (atau, untuk sub-topik yang butuh fasilitas khusus seperti skid pad, sesi teori+simulasi terdokumentasi bila fasilitas tidak tersedia) yang dicatat sebagai **logbook**: tanggal, durasi, lokasi/kondisi, skill yang dilatih, dan **debrief tertulis** learner (apa yang terjadi, kesalahan yang disadari, rasa kontrol atas mobil). Karena AI tidak bisa mengamati fisik langsung, logbook + debrief inilah bahan Review — bukan observasi real-time. Sesi praktik WAJIB didampingi instruktur berlisensi atau pengemudi dewasa berlisensi SIM A yang sah sampai learner sendiri punya SIM, sesuai catatan hukum di bagian Scope.

## Project Tiers

- **Mini Project** (tiap modul): satu sesi praktik/drill terfokus pada skill modul tsb + logbook & debrief tertulis. Contoh: sesi parkir paralel, sesi drive jalan raya pertama, sesi pengereman darurat di area tertutup.
- **Intermediate Project** (tiap section besar): gabungan beberapa modul — mis. mock ujian praktik SIM A lengkap (Module 5), atau sesi drive gabungan tol+malam+hujan (Module 6).
- **Capstone / Portfolio Project** (akhir course): SIM A resmi didapat (bila belum punya) + logbook lengkap seluruh course + dokumentasi satu perjalanan jarak jauh nyata + self-assessment memakai tiga Practitioner Reference Frame (legal minimum vs advanced/defensive vs performance/car-control), disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths / Weaknesses / Actionable Improvements / Score 0-100), setiap review logbook/debrief sesi di domain ini juga mengecek — **berbasis laporan tertulis learner, bukan observasi langsung, jadi review memakai pertanyaan Socratic untuk menguji blind spot self-assessment**:

- **Safety Discipline** — sabuk pengaman, cek spion 3 titik, jarak aman, disiplin kecepatan — apakah disebutkan dilakukan atau terlewat?
- **Hazard Perception & Anticipation** — apakah learner menyadari bahaya lebih awal (ala IPSGA: Information sebelum Position/Speed/Gear/Acceleration), atau baru bereaksi setelah bahaya dekat?
- **Vehicle Control Smoothness** — modulasi gas/rem/kopling/setir halus atau kasar, berdasar deskripsi learner sendiri?
- **Legal Compliance** — sesuai UU LLAJ & rambu yang berlaku, atau ada pelanggaran yang perlu dikoreksi sebelum jadi kebiasaan?

Setiap beberapa modul, review secara eksplisit merekomendasikan sesi bersama instruktur bersertifikat untuk observasi langsung — terutama menjelang ujian praktik SIM (Module 5) dan menjelang latihan car-control tingkat lanjut/skid (Module 8), karena itu di luar kemampuan observasi AI.

## Assessment Form

Quiz rambu-rambu & aturan lalu lintas (meniru format ujian teori AVIS Korlantas) setelah bagian teori tiap lesson, ditambah pertanyaan skenario hazard perception ("apa yang kamu lakukan jika...") ala ujian defensive driving, plus self-reported skill checklist berkala untuk melacak kesiapan menuju tahap berikutnya (siap ujian teori → siap ujian praktik → siap jalan raya → siap kondisi lanjut → siap car-control lanjut).

## Practitioner Reference Frame

Bandingkan tiga bar berbeda untuk masalah mengemudi yang sama: **standar minimum legal Korlantas/Satpas** (cukup untuk lulus SIM A dan sah mengemudi), **advanced/defensive driver ala IAM RoadSmart** (police-system car control, framework IPSGA, membaca bahaya lebih awal — dianggap gold standard sipil di UK), dan **performance/car-control expert ala Skip Barber/Bob Bondurant** (kontrol mobil di batas traksi: pengereman darurat, pemulihan skid, racing line). Tiga tingkat kompetensi yang jelas berbeda untuk skill dasar yang sama — membantu learner tahu di tahap mana dia berada dan apa artinya "expert" di ujung roadmap course ini.

## Domain-Specific Standards

Belum ada — `standards/UNIVERSAL_STANDARDS.md` cukup untuk saat ini. Pertimbangkan `domains/driving/standards/` hanya jika muncul kebutuhan berulang untuk aturan rigor spesifik (mis. checklist minimum jam praktik terdampingi sebelum boleh lanjut ke modul jalan raya).

## Domain Goal

Akan disempurnakan bersama learner saat `courses/driving/roadmap.md` disetujui — sementara: mengemudi mobil dengan aman dan percaya diri di semua kondisi umum (kota, jalan raya, malam, hujan, jarak jauh), memegang SIM A yang sah, dan mencapai level kontrol kendaraan/defensive driving setara standar advanced-driver internasional (IAM RoadSmart), dengan kesadaran penuh bahwa praktik fisik nyata selalu butuh pendampingan pengemudi/instruktur berlisensi di luar sesi belajar bersama AI ini.
