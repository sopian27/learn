# Course Roadmap

## Course Information

* Course Name: Persiapan Masa Tua — Lima Celah yang Belum Terhitung, dengan Label Bukti (dari 0 sampai Capstone)
* Category: Life Planning & Healthy Aging — Integrator. Domain: **baru** `aging-preparation` (lihat `domains/aging-preparation/DOMAIN.md`), terdaftar Paused.
* Difficulty: Level 0 (menganggap masa tua = angka dana pensiun, belum tahu kondisi fisik dasar sendiri, belum punya gambaran perawatan jangka panjang, mengandalkan asumsi "makin tua makin bernilai") sampai Level 3 (punya baseline terukur di lima pilar, tahu klaim mana yang berbukti, dan punya jadwal tinjauan tahunan)
* Estimated Duration: 6 modul (5 modul isi + 1 modul capstone), 22 lesson, sengaja tipis. Pace mengikuti Applied Practice nyata, bukan dikebut.
* Prerequisites: Tidak ada prasyarat teknis. Direkomendasikan sudah menyentuh `courses/personal-finance` Module 9 dan 17 (angka pensiun dan skenario) karena course ini memakai hasilnya sebagai masukan dan tidak mengulangnya.
* Status: **Draft, menunggu approval**. Belum Course Aktif, belum ada Lesson 1.

---

# Catatan Scope (baca dulu sebelum approve)

## Asal course ini

Outline asli dari user: "PREPARING FOR OLD AGE — Mempersiapkan Masa Tua Sejak Usia 34 Tahun". Outline itu punya 15 lesson di 15 Part (lima pilar Health/Money/Relationships/Purpose/Capability, checklist usia 34, Five Questions, Life Balance Sheet, "The 30-Year Project"). Outline datang tanpa instruksi. User memilih "Audit overlap dulu", lalu memilih opsi A: course terlipat sekitar 6 modul setelah audit.

Guard "Course Creation Discipline" (`CLAUDE.md`) aktif saat course ini didraft: "Character Development" pas di garis 50% (3/6 modul) dan `male-grooming` masih Module 1. Mentor menyebut ini di awal dan menawarkan audit dulu tanpa menulis apa pun; setelah audit user memilih membuat course. Mentor menyebut bahwa opsi extend atau hanya menyimpan outline lebih ringan; keputusan user dihormati dan dicatat di `progress/roadmap.md`.

## Hasil audit overlap

Estimasi mentor: sekitar 75% isi outline sudah punya rumah di repo. Angka ini didasarkan pada daftar modul dan lesson di roadmap, bukan pada isi lesson yang sudah ditulis, jadi kasar.

| Bagian outline | Sudah ada di | Perlakuan di course ini |
|---|---|---|
| L4-6, Part XII-XV (active income ke aset, dana darurat, angka pensiun, roadmap usia 34→40→50→60+) | `personal-finance` Module 2, 3, 6-7, 9, 13-15, **17.1-17.5** | Dirujuk, tidak diulang. Angka dipakai sebagai masukan Capstone. |
| Life Balance Sheet (kekayaan finansial + fisik + intelektual + sosial + batin) | `personal-finance` 17.5 (Survival/Comfortable/Freedom/Legacy), `life-mastery` Stop 14 | Modul 6 memakai kerangka lima pilar sebagai daftar asesmen, bukan skor tunggal. |
| L2-3 (kesehatan: kekuatan, kardio, mobilitas, tidur, nutrisi) | `home-fitness` M2-5, 9; `mens-health` M7-9 | Dirujuk. Modul 2 hanya menambah sudut penuaan (sarkopenia, VO2, keseimbangan, jatuh, demensia, skrining). |
| L8 (kesehatan sebagai bagian perencanaan) | `personal-finance` 5.3, 9.2 | Dirujuk. Modul 3 menambah biaya dan siapa yang merawat. |
| L13 (identitas di luar pekerjaan) | `live-your-youth` M12, `masculine-development` M4 | Dirujuk. Modul 5 menambah bukti pensiun dan identitas yang berlawanan arah. |
| L14 (belajar kehilangan) | `emotional-resilience`, `stoicism`, `emosi`, `sandwich-generation` | Dirujuk. Modul 5 hanya menambah kehilangan yang spesifik untuk penuaan. |
| L15 (low fixed cost) | `personal-finance` 1.3/1.5, `slow-living`, `ichigo-ichie` | Dirujuk. |
| L7 (tempat tinggal di masa tua) | Belum ada | Modul 3 (gap asli). |
| L9-10 (karier yang bernilai setelah 40, career optionality) | Belum ada (`career-reinvention` = job search) | Modul 4 (gap asli). |
| L11-12 (relasi, investasi sosial) | `live-your-youth` M10 (pertemanan dewasa umum) | Modul 5 menambah bukti modal sosial di usia lanjut. |
| Kesehatan aging-specific | Belum ada | Modul 2 (gap asli). |
| Five Questions, Year 1 Checklist | Belum ada sebagai baseline terukur | Modul 1 dan 6. |

Belum dibaca isi lesson-nya: `masculine-development`, `slow-living`, `happiness`, `aku-rumah-semua-orang`, `relationships`, `engineering-culture`, `the-new-ceo`. Kalau ada overlap tambahan di sana, akan ditandai saat Lesson di-generate.

## Kenapa domain baru

Semua modul menyambung ke topik yang tidak punya domain: kesehatan penuaan, perawatan jangka panjang, karier setelah 40, dan modal sosial usia lanjut tidak cocok dimasukkan ke `personal-finance` (sudah 19 modul dan mulai gemuk), `mens-health` (fokus reproduksi dan gaya hidup pria), atau `life-mastery` (orkestrator, bukan pemilik konten). Nama domain `aging-preparation` adalah asumsi mentor dan bisa diganti saat approval.

## Sistem label bukti (wajib di setiap lesson)

Outline asli memuat banyak klaim yang enak dibaca tetapi belum tentu benar atau tidak spesifik. Setiap klaim di lesson diberi salah satu label:

* **[Bukti kuat]** — meta-analisis, RCT berkeyakinan tinggi, atau data resmi yang bisa dicek.
* **[Bukti sedang]** — studi observasional besar atau bukti dengan batas yang disebutkan.
* **[Heuristik]** — aturan praktis yang masuk akal tetapi bukan hasil riset. Boleh dipakai, wajib diuji.
* **[Data luar Indonesia]** — angka dari negara lain, tidak boleh dianggap berlaku di Indonesia.
* **[Populer]** — klaim yang sering dikutip tetapi belum dibaca dari sumber primer, ditampilkan hanya untuk diuji.

Klaim outline yang sudah ditandai sejak audit:

1. "Semakin tua, semakin bernilai karena pengalaman": hipotesis, bukan fakta. Data ageism (survei, satu studi arXiv) menunjukkan risiko sebaliknya di tech. Diuji di Modul 4. [Heuristik]
2. Urutan "Pada usia 20-an tubuh terasa..., 30-an..., 40-an...": gambaran umum, bukan data. Tidak diajarkan sebagai fakta. [Heuristik]
3. "Kepuasan relasi di usia 50 memprediksi kesehatan di usia 80 lebih baik daripada kolesterol" (Harvard Study): klaim populer dari ringkasan Harvard Gazette dan media. Sampel: pria Boston abad ke-20. Wajib dibaca dari primer di Modul 5. [Populer]
4. "Kesepian sebanding merokok": tafsir penulis Holt-Lunstad 2010, bukan hasil eksperimen. Studi observasional. Tidak boleh dipakai sebagai alasan kausal. [Bukti sedang]
5. "Passive income" di Lesson 4 outline: sudah dibahas di `personal-finance` Module 14 dengan syarat; tidak diulang.
6. Usia pensiun 59/60 tahun dan rentang manfaat JP: dari blog dan portal berita, sumber lemah. Dicek ke BPJS Ketenagakerjaan. [Populer]
7. Angka perawatan jangka panjang (52%, 14%): dari AS. [Data luar Indonesia]
8. "Blue Zones": tidak dipakai sebagai bukti karena pencatatan usianya dikritik. Dipakai hanya untuk latihan membaca klaim di Modul 1.

## Trusted Sources / Research Priority

Status verifikasi jujur: pada sesi ini hanya **metadata dan ringkasan hasil pencarian** yang diperiksa, bukan isi paper atau buku. Semua sumber di bawah harus dibaca atau dicek langsung sebelum diajarkan sebagai fakta (pola "Vetting a Source" `standards/UNIVERSAL_STANDARDS.md`). Rincian ada di `domains/aging-preparation/DOMAIN.md`.

Sumber yang metadata-nya terverifikasi lewat pencarian:

1. **Holt-Lunstad, Smith & Layton (2010), "Social Relationships and Mortality Risk: A Meta-analytic Review", *PLoS Medicine* 7(7):e1000316.** 148 studi, 308.849 partisipan, OR 1,50. Observasional. Sumber utama Modul 5.
2. **Mandsager dkk. (2018), *JAMA Network Open* 1(6):e183605.** 122.007 orang, kebugaran kardiorespirasi dan mortalitas, tanpa batas atas manfaat yang teramati; populasi rujukan treadmill, retrospektif. Sumber Modul 2.
3. **Sherrington dkk. (2019), Cochrane CD012424.** 108 RCT, 23.407 partisipan, rerata 76 tahun; laju jatuh turun 23% (tinggi), keseimbangan/fungsional 24% (tinggi), multi-jenis 34% (sedang), tai chi 19% (rendah). Sumber Modul 2.
4. **Livingston dkk., Lancet Commission on dementia (2024).** 14 faktor risiko yang dapat dimodifikasi, sekitar 45% kasus. Estimasi populasi. Sumber Modul 2.
5. **ASPE/HHS (Favreault & Dey), risiko perawatan jangka panjang.** Data AS. Sumber Modul 3, dengan label [Data luar Indonesia].
6. **BPS, *Statistik Penduduk Lanjut Usia 2024*.** Sekitar 12% (29 juta) berusia 60+, proyeksi sekitar 20% pada 2045. Sumber Modul 1 dan 3; angka lain dicek ke publikasi.
7. **Meta-analisis pensiun dan depresi (*AJE* 2021; *Epidemiology and Psychiatric Sciences* 2021)** dan literatur pra-pensiun. Bukti bertentangan; dipakai di Modul 5.
8. **Studi arXiv 2510.22457 (diskriminasi dalam karier software)** dan survei ageism tech. Survei mandiri, bukan bukti perekrutan. Modul 4.

Sumber kandidat yang BELUM diverifikasi sama sekali (dari ingatan mentor, tidak boleh dipakai sebagai fakta sebelum dicek):

* Waldinger & Schulz, *The Good Life* (2023), publikasi primer Harvard Study.
* Pedoman aktivitas fisik WHO 2020; konsensus sarkopenia EWGSOP2/AWGS.
* Wang & Shi, model adaptasi pensiun berbasis sumber daya.
* Literatur age discrimination hiring (Neumark dkk.).
* Situs resmi BPJS Ketenagakerjaan/Kesehatan dan PP 45/2015 untuk aturan pensiun terkini.
* Data perawatan lansia di Indonesia (Kemenkes): belum ditemukan.

House rule: tidak ada nasihat medis, investasi, atau hukum spesifik; tidak ada angka dari luar negeri tanpa label; tidak ada janji "pasti sehat/tenang".

---

# Learning Objectives

* [ ] Menyusun baseline pribadi lima pilar (Health, Money, Relationships, Purpose, Capability) dengan angka atau fakta terukur, bukan perasaan, dan memisahkan bagian yang sudah dimiliki `personal-finance` sebagai rujukan
* [ ] Menjelaskan bukti kekuatan, kebugaran, keseimbangan, dan risiko demensia dengan label kekuatan bukti, lalu memilih satu kebiasaan kecil yang berbukti
* [ ] Menghitung gambaran realistis tempat tinggal dan perawatan jangka panjang termasuk peran keluarga, dengan menandai data luar Indonesia
* [ ] Menguji klaim "semakin tua semakin bernilai" terhadap data ageism dan lowongan nyata, lalu memetakan opsi karier
* [ ] Memetakan jaring sosial dan tujuan di luar pekerjaan dengan bukti yang jujur tentang batasnya, dan menyiapkan diri menghadapi kehilangan
* [ ] Menyelesaikan Old Age Blueprint dan tinjauan tahunan Five Questions sebagai Capstone

---

# Course Modules

## Module 1 — Fondasi: Bertemu Dirimu di Usia 64 dan Baseline Lima Pilar

Description: Gap asli (Lesson 1 outline, Five Questions, Year 1 Checklist). Membangun titik awal yang terukur sebelum membahas topik apa pun. Uang tidak diulang; diambil dari `personal-finance` Module 9 dan 17.

Lessons:

* [ ] 1.1 Membaca angka demografi: siapa lansia di Indonesia (BPS 2024, proyeksi 2045) dan apa artinya bagi rentang hidup 30 tahun ke depan. Label [Bukti kuat] untuk data BPS, [Heuristik] untuk proyeksi pribadi.
* [ ] 1.2 Lima pilar sebagai daftar periksa, bukan skor: apa yang sudah ada di repo (uang, kebugaran umum, identitas) dan apa yang belum (Modul 2-5). Life Balance Sheet dibaca sebagai kerangka reflektif, bukan ukuran baku.
* [ ] 1.3 Healthspan dan lifespan, dan cara membaca klaim longevity: latihan dengan klaim Blue Zones dan angka "hidup sampai 100" (label [Populer] dan [Bukti sedang])
* [ ] 1.4 Baseline Five Questions Year 1: lima pertanyaan akhir tahun dijawab dengan angka atau fakta (kekuatan, posisi finansial dari `personal-finance`, keterbawaan karier, kualitas relasi, identitas di luar pekerjaan)

Mini Project: Old Age Log entri pertama: lima jawaban Five Questions dengan satu angka atau fakta per jawaban dan satu hal yang belum kuketahui.

Status: Not started

---

## Module 2 — Healthspan yang Spesifik untuk Penuaan

Description: Gap asli. Kekuatan, kebugaran kardiorespirasi, keseimbangan, dan risiko demensia sebagai investasi jangka panjang. Latihan dasar dan nutrisi dirujuk ke `home-fitness` dan `mens-health`; modul ini hanya menambah bukti penuaan. Bukan nasihat medis.

Lessons:

* [ ] 2.1 Kebugaran kardiorespirasi dan kekuatan otot sebagai prediktor (Mandsager 2018; kekuatan genggam sebagai prediktor asosiatif). Batas: populasi rujukan treadmill, observasional. [Bukti sedang]
* [ ] 2.2 Sarkopenia dan latihan resistensi pada lansia: apa yang berbukti untuk lansia dan apa artinya bagi usia 34 (jangan menerapkan dosis lansia langsung). [Bukti sedang]
* [ ] 2.3 Keseimbangan dan risiko jatuh: bukti Cochrane 2019 (laju jatuh turun 23-34%) dan kenapa jatuh jadi titik balik kemandirian. Rumusan tujuan: kemandirian di usia 70+. [Bukti kuat untuk lansia]
* [ ] 2.4 Demensia dan 14 faktor risiko yang dapat dimodifikasi (Lancet 2024): mana yang bisa dikerjakan sekarang (aktivitas fisik, tekanan darah, pendengaran, isolasi sosial). "Berkaitan dengan" bukan "pasti dicegah". [Bukti sedang]

Mini Project: Asesmen fungsional sederhana yang aman (mis. bangkit dari kursi 5 kali, berdiri satu kaki, jalan 6 menit) dicatat sebagai baseline, satu kebiasaan kecil untuk 30 hari, dan satu catatan "kapan periksa ke dokter". Bukan diagnosis.

Status: Not started

---

## Module 3 — Rumah dan Perawatan Jangka Panjang

Description: Gap asli (Lesson 7 dan bagian Lesson 8 outline). Bukan investasi properti (`personal-finance` 8.4), tetapi pertanyaan "di mana dan dengan siapa aku tinggal, dan siapa yang merawat kalau aku tidak mandiri".

Lessons:

* [ ] 3.1 Aging in place: syarat rumah yang aman untuk lansia (tangga, kamar mandi, akses faskes, transportasi) dan kota mana yang cocok. Label [Heuristik] sampai ada sumber Indonesia.
* [ ] 3.2 Risiko perawatan jangka panjang: data ASPE (AS) sebagai kerangka berpikir, dilabel [Data luar Indonesia]. Kenapa tidak boleh menyalin angka AS, dan apa yang perlu dicari untuk versi Indonesia.
* [ ] 3.3 Siapa yang merawat: peran keluarga, biaya perawatan orang tua yang sudah terjadi sebagai data pribadi, dan memutus siklus sandwich (rujuk `sandwich-generation` M9)
* [ ] 3.4 Apa yang ditanggung dan tidak ditanggung sistem: BPJS Kesehatan, Jaminan Pensiun (usia pensiun dan syarat iuran dicek ke situs resmi), dan celahnya. Rujuk `personal-finance` 9.2 dan 5.3.

Mini Project: Daftar biaya dan waktu perawatan salah satu orang tua atau kerabat lansia dalam 12 bulan terakhir (atau skenario realistis berlabel), plus satu keputusan kecil (mis. audit rumah untuk aksesibilitas atau tanya kerabat soal rencana perawatan).

Status: Not started

---

## Module 4 — Karier yang Tetap Bernilai Setelah 40

Description: Gap asli (Lesson 9-10 outline). Menguji klaim "semakin tua semakin bernilai" terhadap data, lalu memetakan opsi karier. Bukan job search (`career-reinvention`) dan bukan kepemimpinan eksekutif (`the-new-ceo`).

Lessons:

* [ ] 4.1 Ageism di tech: apa yang dikatakan data (survei, studi arXiv 2510.22457) dan apa yang belum (tidak ada bukti perekrutan eksperimental yang ditemukan). [Bukti sedang / Populer]
* [ ] 4.2 Menguji klaim "pengalaman = nilai": apa yang membuat senior tetap dicari (arsitektur, komunikasi, keputusan) dan apa yang tidak. Uji dengan 10 lowongan senior/staff/architect nyata.
* [ ] 4.3 Opsi karier (Senior, Architect, Engineering Manager, Konsultan, Trainer, Independent) sebagai peta pilihan, bukan rencana: apa yang membuat masing-masing transferable. Rujuk `engineering-culture` dan `entrepreneurship`.
* [ ] 4.4 Skill yang tidak usang: batas antara skill teknologi (cepat berubah) dan skill fundamental (arsitektur, komunikasi, penilaian). Sambungan ke Master Goal backend Java/Spring dan freelance internasional.

Mini Project: Peta 10 lowongan dengan tabel "syarat pengalaman / skill non-teknis / batas usia tersirat" dan satu kesimpulan tertulis tentang klaim "semakin tua semakin bernilai" (didukung, ditolak, atau belum cukup bukti).

Status: Not started

---

## Module 5 — Relasi, Tujuan, dan Kehilangan di Usia Lanjut

Description: Gap asli (Lesson 11-14 outline, bagian yang belum tercakup). Pertemanan dewasa umum sudah di `live-your-youth` M10, identitas di luar kerja di M12, dan regulasi emosi di `emotional-resilience`; modul ini menambah bukti modal sosial dan kehilangan yang spesifik untuk penuaan.

Lessons:

* [ ] 5.1 Modal sosial dan kelangsungan hidup: Holt-Lunstad 2010 dan Harvard Study of Adult Development, dengan batasnya (observasional, sampel pria Boston, tafsir "setara merokok" milik penulis). Baca dari primer sebelum diajarkan. [Bukti sedang / Populer]
* [ ] 5.2 Memetakan jaring sosial pribadi: siapa yang bisa dihubungi saat butuh, kualitas versus jumlah, dan satu tindakan kecil untuk merawat satu relasi (rujuk `live-your-youth` M10 dan `relationships`)
* [ ] 5.3 Pensiun, identitas, dan tujuan: bukti bertentangan (pensiun tidak sukarela versus sukarela, pra-pensiun perencanaan); "siapa aku tanpa pekerjaan" sebagai pertanyaan reflektif, bukan temuan. [Bukti sedang]
* [ ] 5.4 Kehilangan yang spesifik untuk penuaan: tubuh berubah, orang tua dan teman meninggal, peran berubah. Acceptance/adaptation/meaning dari outline diperlakukan sebagai kerangka reflektif (rujuk `emotional-resilience`, `stoicism`, `emosi`). Bila kecemasan berat, rujuk profesional.

Mini Project: Peta 5 orang jaring sosial (siapa, seberapa sering kontak, kualitas), satu tindakan kecil 30 hari, dan satu paragraf jujur tentang "identitas di luar pekerjaan" beserta apa yang belum kuketahui.

Status: Not started

---

## Module 6 — Capstone: Old Age Blueprint dan Tinjauan Tahunan

Description: Menyatukan baseline dari modul 1-5 ke satu dokumen, dengan angka finansial dari `personal-finance` sebagai masukan, dan menyusun tinjauan tahunan Five Questions.

Lessons:

* [ ] 6.1 Merancang Blueprint: struktur lima pilar dengan baseline, satu keputusan kecil per pilar untuk 30 hari, dan asumsi yang belum teruji
* [ ] 6.2 Menjalankan 30 hari dan mencatat Old Age Log: apa yang berjalan, apa yang gagal, apa yang berubah
* [ ] 6.3 Membaca ulang klaim yang dipakai: audit label bukti (kuat/sedang/heuristik/luar Indonesia/populer) di seluruh Blueprint dan menurunkan label bila perlu
* [ ] 6.4 Self-review memakai Review Style `domains/aging-preparation/DOMAIN.md` dan menetapkan jadwal tinjauan tahunan Five Questions (bukan sekali jadi)

Capstone Project: **Old Age Blueprint** — dokumen dan Old Age Log yang berisi baseline lima pilar, minimal 5 klaim berlabel bukti (1 diturunkan labelnya), 5 keputusan kecil (satu per pilar) dengan hasil 30 hari, daftar asumsi yang belum teruji, dan cross-reference eksplisit ke minimal dua course lain (`personal-finance` dan salah satu dari `home-fitness`/`sandwich-generation`/`live-your-youth`). Disimpan ke `portofolio/`.

Status: Not started

---

# Capstone Project

Description: Lihat Module 6. Capstone SATU dengan Module 6, tanpa proyek tambahan. Intermediate Project: **Five Questions Year 1** di Module 1 dijadikan baseline yang dibandingkan lagi di Module 6.

Acceptance Criteria: Memenuhi kriteria Review Style `domains/aging-preparation/DOMAIN.md` plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, dan bar "Portfolio-Worthy" (Complete, Reviewed, Presentable, Original) di `standards/UNIVERSAL_STANDARDS.md`. Setiap klaim berlabel bukti. Data luar Indonesia dilabel. Ada cross-reference ke minimal dua course lain.

Status: Not started

---

# Catatan Pacing dan Kecocokan

* Kecocokan Master Goal (backend world-class, freelance internasional): **sedang**. Yang paling relevan Modul 4 (karier setelah 40) dan bagian uang yang dirujuk ke `personal-finance`. Modul lain adalah persiapan hidup, bukan skill teknis.
* Ada peringatan overstudy (lihat memory `feedback_subscription_sunk_cost_overstudy`): course yang berisi banyak bukti dan angka rawan dipakai untuk menunda tindakan. Setiap modul punya Mini Project kecil dengan satu keputusan 30 hari dan tanda berhenti.
* Ada peringatan kecemasan: topik penuaan dan kematian dapat memicu kecemasan. Domain menetapkan berhenti dan rujuk profesional bila persiapan berubah menjadi ketakutan.
* Backlog: repo sudah punya 60+ course draft yang belum dimulai. Course ini menambah satu draft dan tidak menjadi Course Aktif ketiga.
* Belum ditanyakan ke user: apakah 22 lesson terlalu banyak, apakah Modul 4 perlu diperluas untuk Master Goal, dan apakah nama domain `aging-preparation` cocok.

---

# Overall Progress

Module Completion: 0/6

Overall Completion: 0%

Current Module: Belum dimulai. Menunggu approval roadmap (termasuk keputusan domain baru `aging-preparation` dan label bukti).

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — "Membaca angka demografi"
