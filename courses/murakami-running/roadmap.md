# Course Roadmap

**Status: DRAFT — menunggu approval user. Domain `running-philosophy` belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` (daftar setelah approval).**

## Course Information

* Course Name: What I Talk About When I Talk About Running — Disiplin, Kesendirian, Batas Tubuh, dan Seni Terus Berjalan (dari 0 sampai Expert)
* Category: Running Philosophy (Domain: running-philosophy — lihat `domains/running-philosophy/DOMAIN.md`). Course berbasis buku (close reading) + praktik fisik ringan yang nyata.
* Difficulty: Level 0 (berhenti begitu motivasi hilang, butuh audiens/hasil untuk bertahan, menghindari kesendirian, menilai satu hari buruk sebagai vonis identitas, membandingkan diri terus-menerus, belum punya horizon di atas beberapa minggu) → Level 3 (punya praktik fisik/kerja yang jalan tanpa tepuk tangan, bisa membedakan rasa sakit yang harus dipahami dari yang harus dihentikan, bisa membaca klaim "pengalaman pribadi" vs "bukti", dan punya Personal Long-Run Philosophy yang teruji 30 hari nyata)
* Estimated Duration: 13 modul (12 konten + 1 capstone), 4 lesson/modul (52 lesson). Pace mengikuti praktik nyata — beberapa modul (3, 4, 8, 13) BUTUH minimal 1-4 minggu log riil dan tidak bisa dikebut.
* Prerequisites: Tidak ada prasyarat teknis. **Tidak perlu bisa berlari** — jalan kaki, berenang, bersepeda, atau aktivitas repetitif lain sah sebagai padanan "running" sepanjang course. Wajib: baca buku primernya (*What I Talk About When I Talk About Running*, Murakami; terj. Inggris Philip Gabriel, Knopf 2008; edisi Indonesia bila ada), dan bila ada kondisi kesehatan (jantung, sendi, hamil, cedera, riwayat gangguan makan) konsultasi dokter sebelum memulai aktivitas fisik baru (Module 4 & 13).

---

# Catatan Scope (WAJIB dibaca sebelum approval)

Course ini didraft 2026-09-24 atas permintaan langsung user (12 modul + 30-Day Running & Life Challenge + Final Project "My Long Run", ditempel penuh). Dikerjakan sambil Course Aktif "Character Development" pas di garis 50% (3/6 modul) — user eksplisit konfirmasi override guard via `AskUserQuestion`, dan meminta audit overlap sebelum draft.

**Struktur 12 modul dari user dipertahankan apa adanya.** Yang berubah hanya isi tiap modul: bagian yang sudah dalam di domain lain diganti **cross-reference** (house rule sama seperti `quiet-growth`), sehingga modul hanya mengajarkan apa yang belum ada di repo. Overlap-audit (grep lintas `courses/` + baca modul kandidat) menemukan:

| Modul | Topik | Status | Yang ditulis di sini vs dirujuk |
|---|---|---|---|
| 1 | Mengapa berlari | **Native** | Close reading "void", running sebagai exercise + metaphor; tujuan tanpa target eksternal. Cross-ref `quiet-growth` M1 (locus of evaluation) hanya sebagai peta, tidak diulang. |
| 2 | Discipline over motivation | **Cross-ref berat** | Native tipis: rutinitas Murakami (primer), "motivasi ≠ syarat mulai". Teori grit/habit stacking/identity habits → `character-development` M3 & M7; jadwal bangun/morning routine → `daily-momentum` M3. |
| 3 | Solitude | **Native sebagian** | Native: solitude sebagai praktik yang dilatih + bukti (Wilson 2014, Nguyen/Ryan/Deci 2018). Definisi solitude vs loneliness & tanda isolasi → `live-your-youth` M13. |
| 4 | Pain & limits | **Native** | Pain vs suffering, overtraining/RED-S, menerima limit — dengan batas medis. Metodologi latihan/injury prevention teknis → `home-fitness`; klinis → `mens-health`. |
| 5 | Running as a mirror | **Native sebagian** | Native: aktivitas fisik sebagai lab observasi diri (pola menyerah, panik performa, kejar hasil). Emosi/reaktivitas → `emotional-resilience`; ego → `lowering-the-ego`. |
| 6 | The Long Game | **Native (gap)** | Long-distance thinking, horizon 10 tahun, kesabaran hasil lambat. Compounding finansial → `personal-finance`/`warren-buffett`; grit → `character-development` M3. |
| 7 | Aging & changing body | **Native (gap murni)** | Tidak ada di repo. Target berubah seiring usia, perbandingan dengan diri masa lalu. |
| 8 | Failure & bad days | **Cross-ref berat** | Native: "bad session ≠ bad identity" lewat Lally 2010 + praktik log hari buruk. Self-compassion → `self-love`; "tidak peduli" → `not-caring`. |
| 9 | Running & creativity | **Native (gap murni)** | Klaim Murakami (Tier C) vs bukti (Oppezzo & Schwartz; meta-analisis) — latihan literasi bukti. |
| 10 | Professional mindset | **Native sebagian** | Talent+focus+endurance, career endurance, burnout. Deep work → `work-management`/`brain-mastery`; craft engineer → `engineering-culture`; kemasan karier → `career-reinvention`. |
| 11 | Competition with yourself | **Cross-ref berat** | Native: dashboard indikator pribadi (6 indikator) + jebakan aplikasi tracking. Comparison sebagai pola karakter → `character-development` M8. |
| 12 | What do you talk about | **Native (sintesis + kritik)** | Sintesis 9 pertanyaan besar + kritik terhadap buku (survivor bias, privilese, individualisme). |
| 13 | Capstone | **Native** | 30-Day Challenge + "My Long Run" + Personal Long-Run Philosophy. |

**Tiga hal yang genuinely tidak ada di repo (native inti course):** (1) aging & tubuh yang berubah, (2) literasi klaim pengalaman-vs-bukti untuk olahraga/kreativitas, (3) solitude sebagai praktik + batas tubuh/rasa sakit sebagai keputusan (bukan program latihan).

**Prinsip keselamatan (mengikat seluruh course):** Ini BUKAN course olahraga dan BUKAN nasihat medis. Tidak ada target jarak/pace yang mengalahkan keselamatan. Rasa sakit tajam/menetap, nyeri dada, pusing/sesak tidak wajar, atau gejala overtraining = berhenti dan konsultasi profesional. "Tidak semua rasa sakit harus dilawan, sebagian harus dipahami" diajarkan sebagai pemahaman, bukan sebagai izin memaksa tubuh.

**Batas verifikasi (jujur):** Saat draft ini dibuat, teks buku primer TIDAK dibaca; struktur bab dan kutipan verbatim baru dipastikan sebagian lewat sumber sekunder (lihat catatan verifikasi di domain pack). Pernyataan dari brief user seperti epitaph Murakami, nomor bab spesifik, dan detail race tertentu WAJIB dicek dari buku sebelum dipakai di lesson mana pun. Beberapa sumber ilmiah (Wilson 2014, Nguyen/Ryan/Deci 2018, Tanaka & Seals 2008, Lally 2010, Meeusen 2013) disebut dari pengetahuan umum dan wajib diverifikasi saat lesson dibuat.

**Alasan kalibrasi:** Course ini sengaja kecil per-modul (4 lesson) agar bisa selesai berdampingan dengan course aktif lain. Karena 5 dari 12 modul cross-ref berat/sebagian, lesson di modul itu ringan dan fokus pada bagian Murakami-spesifik.

---

# Learning Objectives

* [ ] Membaca memoar Murakami secara dekat dan membedakan apa yang ia katakan (primer) dari ringkasan/quote populer
* [ ] Menjelaskan "running as void" dan kenapa aktivitas tak bertujuan eksternal bisa bermakna
* [ ] Membedakan motivasi vs disiplin dan menjalankan praktik saat sedang tidak mood, memakai sistem dari `character-development`/`daily-momentum` tanpa mengulangnya
* [ ] Melatih solitude sebagai keterampilan (30 menit tanpa smartphone/musik) dan membedakannya dari isolasi
* [ ] Membedakan rasa sakit yang harus dipahami vs dihentikan, mengenali tanda overtraining, dan tahu kapan eskalasi ke profesional
* [ ] Memakai aktivitas fisik sebagai cermin observasi diri (menyerah, panik performa, mengejar hasil, membandingkan)
* [ ] Berpikir jangka panjang (10 tahun, bukan 10 hari) dan menyusun Long-Game Map
* [ ] Merevisi target dan cara membandingkan diri seiring usia tanpa berhenti berkembang
* [ ] Memakai prinsip "bad session ≠ bad identity" pada hari buruk nyata
* [ ] Memisahkan pengalaman pribadi (Murakami) dari klaim berbukti tentang olahraga & kreativitas
* [ ] Menerapkan talent+focus+endurance pada karier software engineer tanpa burnout
* [ ] Membangun indikator kemajuan pribadi (distance, consistency, recovery, skill, discipline, health) dan mengurangi ketergantungan pada perbandingan
* [ ] Mengkritisi buku: survivor bias, privilese, individualisme — bukan menjadikannya kitab suci
* [ ] Menjalankan 30-Day Running & Life Challenge nyata dan menulis Personal Long-Run Philosophy (5-10 prinsip)

---

# Course Modules

## Module 1 — Mengapa Kita Berlari?

Description: Pintu masuk ke buku dan ke pertanyaan "kalau tidak ada yang melihat, apakah saya masih mau melakukannya?". Native: close reading tema "void" dan "exercise + metaphor". Locus of evaluation hanya sebagai peta cross-ref ke `quiet-growth`.

Lessons:

* [ ] 1.1 Membaca buku dan penulisnya — konteks (2007, mulai berlari 1982 setelah menjual bar jazz, ditulis dari catatan menjelang NYC Marathon 2005) dan cara membaca primer vs ringkasan
* [ ] 1.2 "I run in order to acquire a void" — close reading gagasan kekosongan mental sebagai tujuan, bukan hasil
* [ ] 1.3 Aktivitas fisik sebagai perjalanan pribadi vs olahraga; kenapa tidak semua aktivitas butuh tujuan eksternal (cross-ref `quiet-growth` M1)
* [ ] 1.4 Menguji pertanyaan "kalau tidak ada yang melihat?" pada praktik nyata learner + kritik: apakah "tanpa tujuan" itu sendiri sebuah privilese?

Mini Project: Pilih satu aktivitas fisik ringan yang aman (jalan/lari ringan), jalankan 3 sesi tanpa tracking/tanpa dibagikan, tulis apa yang berubah dibanding saat ada audiens/angka.

Status: Not Started

---

## Module 2 — Discipline Over Motivation

Description: Cross-ref berat. Modul ini tipis dan hanya memakai sisi Murakami: rutinitas primer dan "mulai tanpa menunggu mood". Teori grit/habit → `character-development` M3 & M7; morning routine → `daily-momentum` M3.

Lessons:

* [ ] 2.1 Motivasi datang-pergi vs disiplin sebagai keputusan — pemetaan ke `character-development` M3 (jangan mengulang teorinya)
* [ ] 2.2 Rutinitas Murakami dari sumber primer (buku + Paris Review 2004) — apa yang bisa ditiru, apa yang konteks pribadi/privilese
* [ ] 2.3 Menjalankan sesuatu ketika tidak mood: aturan "sesi minimal" dan delayed gratification (Mischel dengan catatan replikasi Watts 2018)
* [ ] 2.4 Menghubungkan ke belajar, kerja, membaca, olahraga, ibadah — satu praktik, satu hari buruk, satu perbaikan kecil

Mini Project: Rancang "sesi minimal" untuk satu praktik nyata dan jalankan 7 hari, termasuk minimal dua hari saat tidak mood; catat apa yang membuatnya jalan/berhenti.

Status: Not Started

---

## Module 3 — The Solitude of Running

Description: Solitude sebagai keterampilan yang dilatih. Definisi solitude vs loneliness dan tanda isolasi → `live-your-youth` M13. Modul ini menambahkan praktik, bukti, dan batas.

Lessons:

* [ ] 3.1 Kesendirian vs kesepian vs isolasi — peta singkat + cross-ref `live-your-youth` M13
* [ ] 3.2 Riset solitude: Wilson et al. 2014 (tidak nyaman dengan pikiran sendiri, dengan catatan batas metodologi) dan Nguyen/Ryan/Deci 2018 (solitude sebagai regulasi afektif)
* [ ] 3.3 Percakapan dengan diri sendiri dan ruang berpikir — praktik 30 menit tanpa smartphone/musik/distraksi, hanya berjalan dan memperhatikan pikiran
* [ ] 3.4 Menikmati sendirian tanpa merasa kosong + kapan solitude bergeser jadi menghindar/isolasi, dan kapan menjangkau orang

Mini Project: 3 sesi 30 menit jalan tanpa smartphone/musik; log pikiran yang muncul, rasa tidak nyaman, dan apa yang berubah pada sesi ke-3.

Status: Not Started

---

## Module 4 — Pain & Limits

Description: Rasa sakit vs penderitaan dan batas tubuh. Modul dengan **batas keselamatan paling ketat**: tidak mengglorifikasi cedera, tidak menyuruh melawan nyeri tajam.

Lessons:

* [ ] 4.1 "Pain is inevitable, suffering is optional" — close reading (verifikasi bab) dan pembacaan kritis: apa yang dimaksud, apa yang tidak
* [ ] 4.2 Discomfort vs nyeri berbahaya, fatigue, mental resistance — membedakan yang harus dipahami vs dihentikan; kapan cek dokter
* [ ] 4.3 Pushing vs overtraining: tanda overtraining syndrome (Meeusen 2013) dan RED-S (IOC 2018), recovery sebagai bagian praktik; cross-ref `home-fitness` M4/M9 untuk teknis
* [ ] 4.4 Menerima keterbatasan tubuh sendiri (kondisi kronis, disabilitas, cedera) — padanan aktivitas alternatif; koreksi "no pain no gain" (Tier D/E)

Mini Project: Buat "Pain & Limits Protocol" pribadi: 3 tanda berhenti, 3 tanda boleh lanjut pelan, dan nomor/rencana konsultasi profesional; uji dengan satu skenario tertulis (bukan dengan memaksa tubuh).

Status: Not Started

---

## Module 5 — Running as a Mirror

Description: Aktivitas fisik sebagai laboratorium kecil untuk melihat karakter sendiri. Reaktivitas emosi → `emotional-resilience`; ego → `lowering-the-ego`.

Lessons:

* [ ] 5.1 Observasi diri saat aktivitas: mudah menyerah? terlalu mengejar hasil? panik saat performa turun? — kerangka pengamatan tanpa menghakimi
* [ ] 5.2 Self-comparison di tengah aktivitas: pola membandingkan, dari mana asalnya (cross-ref `character-development` M8)
* [ ] 5.3 Menikmati proses vs mengejar hasil (process vs outcome orientation; cross-ref `quiet-growth` M4)
* [ ] 5.4 Dari observasi ke perubahan kecil: menerjemahkan pola yang terlihat saat berlari ke praktik di kerja dan hubungan

Mini Project: Mirror Log 1 minggu — 3 sesi, catat satu pola karakter yang muncul per sesi + satu eksperimen perubahan kecil.

Status: Not Started

---

## Module 6 — The Long Game

Description: Long-distance thinking. Native (gap). Compounding finansial → `personal-finance`/`warren-buffett`; grit → `character-development` M3.

Lessons:

* [ ] 6.1 Long-distance thinking: hasil lambat, tidak semua hasil datang cepat — kenapa otak kita bias ke jangka pendek
* [ ] 6.2 Skill compounding dan deliberate practice (Ericsson 1993) — di mana kerangka ini berlaku, di mana ia dilebih-lebihkan
* [ ] 6.3 Menghubungkan ke karier, keuangan, hubungan, kesehatan, membaca, pendidikan — pilih 3 area sendiri
* [ ] 6.4 "Apa yang ingin saya bangun selama 10 tahun, bukan 10 hari?" — menyusun Long-Game Map awal

Mini Project: Long-Game Map v1 — 3 area, target 10 tahun, satu kebiasaan mingguan per area, dan satu hal yang sengaja TIDAK dikejar.

Status: Not Started

---

## Module 7 — Aging & Changing Body

Description: Gap murni di repo. Tubuh dan kemampuan berubah, target ikut berubah. Bukan course anti-aging dan bukan saran medis.

Lessons:

* [ ] 7.1 Murakami dan usia dalam buku (verifikasi teks): tubuh berubah, waktu tempuh melambat, tetap berlari
* [ ] 7.2 Pola umum penurunan performa endurans dengan usia (Tanaka & Seals 2008) — apa yang normal, apa yang bisa dilatih, apa yang tidak
* [ ] 7.3 Membandingkan diri dengan diri masa lalu: kapan sehat, kapan menjerat; merevisi target tanpa berhenti berkembang
* [ ] 7.4 Menerima bahwa kita tidak selalu bisa jadi versi fisik terbaik seumur hidup — "Bagaimana saya ingin menua?"

Mini Project: Surat ke diri 10 dan 20 tahun lagi — tubuh, kapasitas, dan target yang direvisi ramah usia; plus satu target tahun ini yang diukur bukan lawan diri usia 20.

Status: Not Started

---

## Module 8 — Failure & Bad Days

Description: Cross-ref berat. Native: "bad session ≠ bad identity" lewat bukti kebiasaan dan praktik log hari buruk. Self-compassion → `self-love`; ketidakpedulian sehat → `not-caring`.

Lessons:

* [ ] 8.1 Anatomi hari buruk: tubuh berat, pace buruk, motivasi hilang, target gagal, performa turun — apa yang sebenarnya terjadi
* [ ] 8.2 Satu hari buruk tidak menentukan perjalanan — Lally et al. 2010 (melewatkan satu kesempatan tidak merusak pembentukan kebiasaan)
* [ ] 8.3 Bad session ≠ bad identity: memisahkan performa dari nilai diri (cross-ref `self-love`, `not-caring`)
* [ ] 8.4 Protokol hari buruk: apa yang dilakukan saat target gagal, kapan berhenti total, kapan lanjut ringan

Mini Project: Bad Day Protocol — tulis sebelum hari buruk datang, lalu pakai di hari buruk nyata pertama; evaluasi apakah protokolnya menahan spiral.

Status: Not Started

---

## Module 9 — Running & Creativity

Description: Native (gap murni). Melatih literasi bukti: pengalaman Murakami (Tier C) vs klaim ilmiah yang butuh bukti (Tier A).

Lessons:

* [ ] 9.1 Murakami: running dan proses menulis — apa yang ia klaim, dalam bentuk apa (pengalaman, bukan bukti)
* [ ] 9.2 Repetitive movement, ruang mental, observasi, ide saat tubuh bergerak — mekanisme yang masuk akal vs yang terbukti
* [ ] 9.3 Membaca bukti: Oppezzo & Schwartz 2014 (divergent bukan convergent, sampel mahasiswa) dan meta-analisis aerobic exercise & kognisi (hasil beragam menurut populasi)
* [ ] 9.4 "Apakah tubuh aktif membantu pikiran bekerja lebih baik?" — jawaban jujur berlevel-tier + eksperimen n=1 sederhana untuk diri sendiri

Mini Project: Eksperimen n=1 selama 2 minggu (aktivitas fisik ringan vs tidak sebelum satu tugas kreatif/kerja); ukur, lalu tulis kesimpulan dengan label Tier dan batasannya.

Status: Not Started

---

## Module 10 — The Professional Mindset

Description: Talent + routine + persistence + solitude + time. Deep work → `work-management`/`brain-mastery`; craft → `engineering-culture`; karier → `career-reinvention`.

Lessons:

* [ ] 10.1 Talent, focus, endurance — close reading tesis Murakami: focus dan endurance bisa dilatih; apa batas klaim ini
* [ ] 10.2 Coding bukan sprint setiap hari: karier sebagai long-distance running — deep work dan skill compounding (cross-ref)
* [ ] 10.3 Technical debt sebagai analogi utang kebugaran; career endurance dan burnout (Maslach, ICD-11 sebagai fenomena okupasional)
* [ ] 10.4 Menyusun rencana endurance karier: ritme kerja berkelanjutan, recovery, dan tanda peringatan burnout

Mini Project: Career Endurance Plan — ritme kerja mingguan yang berkelanjutan, 3 tanda burnout pribadi, dan satu tindakan pencegahan yang dijalankan 2 minggu.

Status: Not Started

---

## Module 11 — Competition With Yourself

Description: Cross-ref berat. Native: dashboard indikator pribadi dan jebakan aplikasi tracking. Comparison sebagai pola karakter → `character-development` M8.

Lessons:

* [ ] 11.1 "Apakah saya lebih baik daripada saya yang kemarin?" — kenapa ini bukan pembebasan otomatis dari perbandingan (cross-ref M8)
* [ ] 11.2 Enam indikator pribadi: distance, consistency, recovery, skill, discipline, health — definisi yang tidak menjebak
* [ ] 11.3 Jebakan tracking/leaderboard/aplikasi dan sosial media: kapan data membantu, kapan jadi perbandingan terselubung
* [ ] 11.4 Tujuan bukan menang, tapi lebih baik sesuai kemampuan dan keadaan — merancang dashboard pribadi

Mini Project: Personal Progress Dashboard 4 minggu (6 indikator), dengan aturan "tidak ada perbandingan dengan orang lain" dan evaluasi mingguan.

Status: Not Started

---

## Module 12 — What Do You Talk About When You Talk About Running?

Description: Sintesis dan kritik. Menjawab pertanyaan besar (apa yang membuat saya terus berjalan, apa yang saya kejar, apa yang bersedia saya korbankan, apa batas saya) sekaligus mengkritisi buku.

Lessons:

* [ ] 12.1 Sintesis: 9 pertanyaan besar dalam brief, dijawab dengan bahan Module 1-11
* [ ] 12.2 Membaca kritis: survivor bias, privilese waktu/kesehatan, individualisme, gender/usia; siapa yang tidak bisa mengikuti jalan ini
* [ ] 12.3 Padanan "running" bagi orang lain: disabilitas, kondisi kronis, pekerjaan shift, keluarga — apa yang tetap berlaku dari prinsipnya
* [ ] 12.4 Membangun draf pertama prinsip pribadi (belum final) sebelum Capstone

Mini Project: Esai kritik 800-1200 kata: apa yang saya ambil dari Murakami, apa yang saya tolak, dan kenapa.

Status: Not Started

---

## Module 13 — Capstone: 30-Day Running & Life Challenge + My Long Run

Description: Praktik nyata 30 hari dan Final Project. Tidak harus jadi pelari serius; jangan mengejar jarak/pace dengan mengorbankan keselamatan.

Lessons:

* [ ] 13.1 Merancang 30-Day Challenge (Minggu 1 Start, 2 Consistency, 3 Reflection, 4 Endurance) sesuai kemampuan, dengan batas keselamatan
* [ ] 13.2 Menjalankan 30 hari dan Long-Run Journal (Apa yang saya rasakan? pikirkan? pelajari?), termasuk hari buruk nyata
* [ ] 13.3 Final Project "My Long Run": jawab 10 pertanyaan (tujuan jangka panjang, yang ingin dibangun, konsistensi, pengorbanan, batas, hari buruk, kegagalan, menua, saat tidak ada yang melihat, yang membuat terus berlari)
* [ ] 13.4 Personal Long-Run Philosophy: 5-10 prinsip pribadi (disiplin, konsistensi, kesehatan, pekerjaan, kehidupan), direview dengan Universal Review Rubric + lensa domain

Mini Project: Capstone itu sendiri (30 hari + Final Project + Philosophy).

Status: Not Started

---

# Capstone Project

Personal Long-Run Philosophy (5-10 prinsip) yang bersumber dari 30 hari praktik nyata dan Long-Run Journal, dinilai dengan Universal Review Rubric plus lima lensa domain (Primary-Source Fidelity, Experience-vs-Evidence, Safety, Anti-Cult, Cross-Reference Integrity).

---

# Resources

Lihat `domains/running-philosophy/DOMAIN.md` bagian "Trusted Sources / Research Priority" untuk daftar lengkap. Sumber utama: Murakami, *What I Talk About When I Talk About Running*; Paris Review Art of Fiction No. 182; Oppezzo & Schwartz 2014; WHO/ACSM guidelines; Meeusen 2013 & IOC RED-S 2018; Wilson 2014; Nguyen/Ryan/Deci 2018; Tanaka & Seals 2008; Lally 2010. Status verifikasi tiap sumber ada di domain pack.

# Projects

* Mini Project per modul (lihat tiap Module di atas) — semua berbasis praktik nyata yang aman.
* Intermediate: Long-Game Map (Module 6-7, 10).
* Capstone: 30-Day Challenge + My Long Run + Personal Long-Run Philosophy (Module 13).

# Interview

Akan diisi setelah course dimulai — skenario keputusan (motivasi hilang + nyeri ringan, hari buruk beruntun, target gagal), pertanyaan klaim-vs-bukti, dan pertanyaan kritik terhadap buku.

# Final Exam

Capstone (Module 13.4) berfungsi sebagai final exam.

---

# Overall Progress

Module Completion: 0/13

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap ini dan domain pack `domains/running-philosophy/DOMAIN.md`

Current Lesson: —

Next Lesson: 1.1 Membaca buku dan penulisnya (setelah roadmap disetujui dan learner sudah memegang buku primernya)
