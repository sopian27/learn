# Course Roadmap

## Course Information

* Course Name: Studi Intelijen — dari 0 sampai Level Analis Junior (Intelligence Studies sebagai Disiplin Akademik)
* Category: Intelligence Studies (Domain: intelligence-studies — lihat `domains/intelligence-studies/DOMAIN.md`)
* Difficulty: Level 0 (belum tahu apa itu siklus intelijen, jenis-jenis INT, atau ACH) → Level 3 (mampu menghasilkan Intelligence Estimate Memo yang rigorous — source reliability grading, Analysis of Competing Hypotheses, bahasa probabilitas terkalibrasi ala Kent — pada studi kasus historis nyata, setara literasi analis intelijen junior atau mahasiswa pascasarjana security studies)
* Estimated Duration: 10 modul, 4 lesson/modul, pace disesuaikan karena Applied Practice tiap modul adalah menulis Intelligence Estimate Memo penuh (butuh waktu riset sumber, bukan sekadar menjawab quiz)
* Prerequisites: Tidak ada prasyarat teknis. Course ini murni akademik/analitik — bukan pelatihan operasional. Sesuai `domains/intelligence-studies/DOMAIN.md`, course ini TIDAK PERNAH mengajarkan tradecraft operasional yang bisa dipraktikkan pada target nyata (surveillance fisik, elicitation, identitas palsu, komunikasi rahasia), tidak pernah mensimulasikan/mengklaim informasi rahasia (classified), dan latihan OSINT hanya memakai subjek & kasus publik/historis — tidak pernah individu privat. Kesediaan membaca sumber primer/akademik dalam Bahasa Inggris (Lowenthal, Heuer, Zegart, dsb.) akan sangat membantu meskipun course disampaikan dalam Bahasa Indonesia.

---

# Learning Objectives

* [ ] Memahami sejarah & lembaga intelijen modern lintas negara (OSS→CIA, MI6, KGB→SVR/FSB, Mossad, BIN Indonesia) dan evolusinya dari era Perang Dingin ke era digital/cyber
* [ ] Menguasai siklus intelijen formal (planning & direction → collection → processing → analysis → dissemination → feedback) dan struktur komunitas intelijen sebagai kerangka kerja institusional
* [ ] Membedakan jenis-jenis pengumpulan intelijen (HUMINT, SIGINT, IMINT/GEOINT, MASINT, OSINT) — apa yang dikumpulkan tiap jenis, kekuatan dan keterbatasannya
* [ ] Memahami psikologi analis: bagaimana persepsi, memori, dan bias kognitif (mirror-imaging, groupthink, confirmation bias) mendistorsi penilaian intelijen (Heuer)
* [ ] Menguasai analytic tradecraft terstruktur: Analysis of Competing Hypotheses (ACH), Admiralty Code untuk source reliability grading, dan bahasa probabilitas terkalibrasi ala Sherman Kent
* [ ] Menganalisis studi kasus kegagalan & keberhasilan intelijen (Pearl Harbor, Cuban Missile Crisis, kejatuhan Uni Soviet, 9/11, Iraq WMD NIE) dari sisi struktural — di titik mana dalam siklus intelijen sesuatu salah/benar
* [ ] Memahami counterintelligence & covert action secara historis/konseptual (double agent, deception, kasus Mitrokhin/Ames, Stuxnet) — bukan sebagai instruksi operasional
* [ ] Memahami kerangka hukum, etika, dan pengawasan (oversight) yang membatasi badan intelijen — Church Committee/FISA, UK ISC, UU No. 17/2011 Indonesia — dan mengenali kasus penyalahgunaan wewenang (COINTELPRO) sebagai pelajaran, bukan model
* [ ] Mempraktikkan OSINT legal ala Bellingcat pada subjek & kasus publik/historis sebagai skill verifikasi klaim dari sumber terbuka
* [ ] Menghasilkan Intelligence Analyst Case Book sebagai Capstone Project — portofolio berisi minimal tiga Intelligence Estimate Memo lintas jenis skenario plus bab metodologi dan bab etika/oversight

---

# Course Modules

## Module 1 — Fondasi: Sejarah & Lembaga Intelijen Modern

Description: Sebelum masuk ke teknik analisis, bangun dulu peta besar: apa itu "intelijen" secara definisi formal, dari mana lembaga-lembaga intelijen modern lahir, dan bagaimana lanskapnya berubah dari Perang Dingin ke era digital. Modul ini grounded pada Lowenthal Ch.1-2 (*Intelligence: From Secrets to Policy*) dan Christopher Andrew (*The Secret World*) untuk kedalaman historis lintas negara.

Lessons:

* [ ] 1.1 Apa itu "intelijen"? Definisi formal, tujuan, dan kenapa negara punya badan intelijen (Lowenthal Ch.1)
* [ ] 1.2 Lahirnya komunitas intelijen modern: OSS→CIA (AS), MI6 (Inggris), KGB→SVR/FSB (Rusia/Soviet), Mossad (Israel), BIN (Indonesia)
* [ ] 1.3 Perang Dingin sebagai "zaman keemasan" spionase — pola, doktrin, dan kasus lintas negara (Andrew, *The Secret World*)
* [ ] 1.4 Dari Perang Dingin ke era digital: bagaimana cyber & big data mengubah cara kerja intelijen (Zegart, *Spies, Lies, and Algorithms*)

Mini Project: Intelligence Estimate Memo pertama — pilih satu skenario historis sederhana dari periode yang dipelajari, rumuskan pertanyaan intelijen, dan tulis memo pendek yang mencantumkan sumber yang dipakai (belum wajib pakai ACH penuh, fokus format dasar memo).

Status: Not started

---

## Module 2 — Siklus Intelijen & Struktur Komunitas Intelijen

Description: Siklus intelijen adalah kerangka kerja formal yang dipakai tiap badan intelijen sungguhan — modul ini membangun pemahaman institusional sebelum masuk ke teknik analitik. Grounded pada Lowenthal Ch.3-4.

Lessons:

* [ ] 2.1 Siklus intelijen formal: planning & direction → collection → processing → analysis → dissemination → feedback (Lowenthal Ch.4)
* [ ] 2.2 Struktur komunitas intelijen: siapa mengerjakan apa untuk siapa — IC AS sebagai model, dibandingkan struktur UK & Indonesia (Lowenthal Ch.3)
* [ ] 2.3 Intelligence-to-policy: garis batas antara menyampaikan fakta/analisis dan membuat kebijakan (Lowenthal, Betts)
* [ ] 2.4 Menelusuri satu pertanyaan intelijen melalui seluruh siklus, dari perumusan pertanyaan sampai feedback

Mini Project: Intelligence Estimate Memo yang secara eksplisit memetakan tiap bagian memo ke tahap siklus intelijen (dari mana pertanyaan berasal, sumber apa yang dikumpulkan, bagaimana diproses jadi kesimpulan).

Status: Not started

---

## Module 3 — Jenis-Jenis Pengumpulan Intelijen (The INTs)

Description: Tiap jenis "INT" punya kekuatan dan keterbatasan berbeda — analis yang baik tahu jenis bukti apa yang sedang ia pegang dan seberapa jauh ia bisa dipercaya. Grounded pada bab collection disciplines di Lowenthal.

Lessons:

* [ ] 3.1 HUMINT: pengumpulan dari sumber manusia — kekuatan, keterbatasan, dan risiko reliabilitas
* [ ] 3.2 SIGINT: signals intelligence — apa yang bisa dan tidak bisa diungkap dari sinyal
* [ ] 3.3 IMINT/GEOINT & MASINT: citra satelit, data geospasial, dan pengukuran teknis
* [ ] 3.4 OSINT: intelijen dari sumber terbuka — kekuatan unik di era digital dan hubungannya dengan INT lain

Mini Project: Intelligence Estimate Memo untuk satu skenario yang sengaja memakai minimal dua jenis INT berbeda sebagai sumber, dengan penilaian eksplisit kekuatan/keterbatasan tiap jenis sumber yang dipakai.

Status: Not started

---

## Module 4 — Psikologi Analis: Persepsi, Memori & Bias Kognitif

Description: Sebelum belajar teknik analitik, seorang analis harus paham kenapa otaknya sendiri adalah sumber kesalahan terbesar. Grounded pada Heuer Part I ("Our Mental Machinery" — *Psychology of Intelligence Analysis*, tersedia gratis via CIA CSI) dan bab "seven deadly biases" Zegart.

Lessons:

* [ ] 4.1 Thinking about thinking: kenapa mental model analis sendiri jadi sumber kesalahan (Heuer Ch.1)
* [ ] 4.2 Persepsi: kenapa kita tidak melihat apa yang sebenarnya ada di depan mata (Heuer Ch.2)
* [ ] 4.3 Memori & mental model: bagaimana pengalaman masa lalu mendistorsi penilaian baru (Heuer Ch.3)
* [ ] 4.4 Tujuh bias mematikan ala Zegart: mirror-imaging, groupthink, confirmation bias, dan lainnya dalam praktik nyata

Mini Project: Intelligence Estimate Memo yang menyertakan bagian "self-audit bias" eksplisit — mengidentifikasi bias kognitif mana yang paling berisiko memengaruhi penilaian penulis sendiri pada skenario yang dipilih.

Status: Not started

---

## Module 5 — Analytic Tradecraft: Structured Analytic Techniques, ACH & Bahasa Probabilitas

Description: Ini jantung Applied Practice domain ini — teknik konkret untuk menimbang hipotesis alternatif dan menyampaikan kesimpulan dengan bahasa probabilitas yang konsisten. Grounded pada Heuer Part II ("Tools for Thinking") dan konsep words of estimative probability Sherman Kent (dirujuk di Lowenthal).

Lessons:

* [ ] 5.1 Kenapa penilaian intuitif saja tidak cukup — strategi analitik menghadapi informasi tidak lengkap (Heuer Ch.4-5)
* [ ] 5.2 Analysis of Competing Hypotheses (ACH): membangun matriks hipotesis vs bukti langkah demi langkah
* [ ] 5.3 Sherman Kent's words of estimative probability — mengkalibrasi bahasa "kemungkinan besar/kecil" secara konsisten
* [ ] 5.4 Admiralty Code: menilai reliabilitas sumber & kredibilitas informasi sebelum dipakai dalam analisis

Mini Project: Intelligence Estimate Memo penuh pertama yang wajib memakai ACH (matriks hipotesis-bukti lengkap), Admiralty Code untuk tiap sumber, dan bahasa probabilitas terkalibrasi di kesimpulan — format yang akan dipakai konsisten di modul-modul berikutnya.

Status: Not started

---

## Module 6 — Studi Kasus: Kegagalan & Keberhasilan Intelijen

Description: Menerapkan seluruh teknik dari Module 1-5 pada studi kasus nyata yang paling banyak dikaji dalam literatur intelligence studies — dianalisis dari sisi *di titik mana dalam siklus intelijen* sesuatu salah/benar, bukan sekadar hasil akhirnya. Grounded pada Betts (*Enemies of Intelligence*), Zegart, dan Lowenthal. Mini Project modul ini adalah **Intermediate Project** sesuai `domains/intelligence-studies/DOMAIN.md` bagian "Project Tiers".

Lessons:

* [ ] 6.1 Pearl Harbor (1941): kegagalan di titik mana dalam siklus intelijen — collection, analysis, atau dissemination?
* [ ] 6.2 Cuban Missile Crisis (1962): kasus keberhasilan analisis intelijen di bawah tekanan waktu
* [ ] 6.3 Kejatuhan Uni Soviet: kenapa mayoritas analis meleset memprediksinya
* [ ] 6.4 9/11 dan Iraq WMD NIE (2002): kegagalan struktural di era modern, dibandingkan dengan laporan resmi (9/11 Commission)

Intermediate Project: Intelligence Failure Post-Mortem — analisis terstruktur satu kegagalan intelijen terkenal (pilih salah satu dari Pearl Harbor, 9/11, atau Iraq WMD NIE), mengidentifikasi secara eksplisit di titik mana dalam siklus intelijen kegagalan itu terjadi, dibandingkan dengan penjelasan resmi yang sudah dideklasifikasi.

Status: Not started

---

## Module 7 — Counterintelligence & Covert Action: Sejarah dan Kasus

Description: Counterintelligence dan covert action dibahas murni sebagai sejarah/konsep — apa itu, kenapa dipakai, contoh kasus yang sudah dideklasifikasi — tidak pernah sebagai instruksi operasional, sesuai batas eksplisit di `domains/intelligence-studies/DOMAIN.md`. Grounded pada Andrew (*The Sword and the Shield*, bersama Mitrokhin) dan Zegart.

Lessons:

* [ ] 7.1 Apa itu counterintelligence — konsep double agent & deception operations secara historis (bukan operasional)
* [ ] 7.2 Arsip Mitrokhin & kasus Aldrich Ames — bagaimana penetrasi & pengkhianatan terungkap
* [ ] 7.3 Covert action sebagai alat kebijakan: definisi, spektrum, dan batasnya (Zegart, Lowenthal)
* [ ] 7.4 Stuxnet sebagai studi kasus cyber covert action di level kebijakan/sejarah (bukan level teknis membangun tool-nya)

Mini Project: Intelligence Estimate Memo untuk satu kasus counterintelligence/covert action historis (mis. deteksi kemungkinan double agent dari pola bukti yang tersedia saat itu), memakai ACH untuk menimbang penjelasan alternatif.

Status: Not started

---

## Module 8 — Etika, Hukum & Pengawasan (Oversight)

Description: Modul wajib, bukan opsional, sesuai `domains/intelligence-studies/DOMAIN.md` bagian "Domain-Specific Standards" — belajar cara kerja intelijen tanpa belajar batas hukumnya bukan pilihan yang tersedia di domain ini. Grounded pada Loch Johnson (*Oxford Handbook of National Security Intelligence*), Betts, dan UU No. 17/2011.

Lessons:

* [ ] 8.1 Church Committee & lahirnya FISA — kenapa oversight legislatif dibutuhkan (AS)
* [ ] 8.2 UK Intelligence and Security Committee — model pengawasan parlementer Inggris
* [ ] 8.3 UU No. 17 Tahun 2011 tentang Intelijen Negara — kerangka hukum & pengawasan versi Indonesia, disandingkan dengan model AS/UK
* [ ] 8.4 COINTELPRO & penyadapan domestik ilegal — pelajaran dari penyalahgunaan wewenang, bukan model yang ditiru

Mini Project: Intelligence Estimate Memo yang menyertakan bagian "oversight check" eksplisit — menilai apakah rekomendasi/kesimpulan memo tetap berada di sisi fakta & kemungkinan (intelligence) dan tidak melewati garis ke rekomendasi tindakan politik (policy).

Status: Not started

---

## Module 9 — OSINT Praktis: Verifikasi Sumber Terbuka ala Bellingcat

Description: OSINT sebagai skill yang bisa dipraktikkan legal — hanya pada subjek & kasus publik/historis, dengan batas yang sama persis seperti dipakai domain conspiracy-theories untuk Bellingcat. Grounded pada metodologi publik Bellingcat.

Lessons:

* [ ] 9.1 Metodologi verifikasi terbuka: geolocation, chronolocation, dan cross-referencing sumber publik
* [ ] 9.2 Studi kasus publik Bellingcat: bagaimana satu klaim diverifikasi langkah demi langkah dari data terbuka
* [ ] 9.3 Batas etis OSINT: kenapa domain ini hanya memakai subjek & kasus publik/historis, tidak pernah individu privat — bahkan atas permintaan sendiri
* [ ] 9.4 Praktik OSINT pada satu studi kasus historis/publik yang metodologi verifikasinya sudah terdokumentasi

Mini Project: Intelligence Estimate Memo berbasis OSINT murni untuk satu klaim publik/historis, mencantumkan langkah verifikasi (geolocation/chronolocation/cross-reference) sebagai bagian dari penilaian reliabilitas sumber.

Status: Not started

---

## Module 10 — Capstone: Intelligence Analyst Case Book

Description: Mengintegrasikan seluruh artefak Module 1-9 jadi satu portofolio koheren, mengikuti `domains/intelligence-studies/DOMAIN.md` bagian "Project Tiers" untuk definisi Capstone di domain ini.

Lessons:

* [ ] 10.1 Menyusun ulang minimal tiga Intelligence Estimate Memo lintas jenis skenario (satu HUMINT-heavy, satu SIGINT/OSINT-heavy, satu kegagalan historis yang di-post-mortem dari Module 6)
* [ ] 10.2 Menulis bab metodologi: merangkum teknik analitik yang dipakai (ACH, Admiralty Code, words of estimative probability)
* [ ] 10.3 Menulis bab etika/oversight: batas hukum & moral kerja intelijen, merujuk kerangka Module 8
* [ ] 10.4 Self-review memakai lima kriteria Review Style domain ini, lalu finalisasi Case Book

Capstone Project: Intelligence Analyst Case Book — portofolio berisi minimal tiga Intelligence Estimate Memo lintas jenis skenario (Module 1-9), satu bab metodologi yang merangkum teknik analitik, dan satu bab etika/oversight yang membahas batas hukum & moral kerja intelijen — disimpan ke `portofolio/`. Review memakai `domains/intelligence-studies/DOMAIN.md` bagian "Review Style" (Source Reliability Grading, Analytic Rigor, Calibrated Confidence Language, Key Assumptions Check, Actionability without Policy Overreach) di atas Universal Review Rubric (`ai-los/CORE_LOS.md`).

Status: Not started

---

# Capstone Project

Description: Lihat Module 10 di atas untuk rincian lengkap. Capstone course ini SATU dengan Capstone Project Module 10 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/intelligence-studies/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi kelima kriteria Review Style domain (Source Reliability Grading, Analytic Rigor, Calibrated Confidence Language, Key Assumptions Check, Actionability without Policy Overreach) plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, dan seluruh materi Case Book harus tetap dalam batas domain (tidak ada tradecraft operasional, tidak ada simulasi informasi rahasia, OSINT hanya subjek publik/historis).

Status: Not started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Module 1 — Fondasi: Sejarah & Lembaga Intelijen Modern (menunggu approval roadmap)

Current Lesson: 1.1 Apa itu "intelijen"? Definisi formal, tujuan, dan kenapa negara punya badan intelijen

Next Lesson: 1.1 Apa itu "intelijen"? Definisi formal, tujuan, dan kenapa negara punya badan intelijen (Lowenthal Ch.1)
