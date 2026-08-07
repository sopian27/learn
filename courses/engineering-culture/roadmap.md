# Course Roadmap

## Course Information

* Course Name: Engineering Culture & Senior Engineer Craft — Cara Berpikir, Menulis, dan Memimpin Seperti Engineer Kelas Dunia
* Category: Engineering Culture & Craft (lintas-teknologi, konteks Java/Spring Boot tetap dipakai untuk contoh) — melengkapi Master Goal "World-Class Backend Developer" (`progress/roadmap.md`), bukan menggantikan `courses/backend-fundamentals` maupun `courses/system-design`
* Difficulty: Level 2 (sudah kerja profesional, tapi belum pernah sadar mempraktikkan "cara kerja" senior engineer) → Level 4 (bisa menulis design doc, memimpin code review, menjalankan postmortem, dan menunjukkan senioritas ke klien tanpa gelar/title formal)
* Estimated Duration: 9 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`). Dengan pace sedang ≈ 3-4 bulan.
* Prerequisites: Sudah bisa membangun service dasar (CRUD API, git). **Disarankan** dijalankan paralel/setelah `courses/backend-fundamentals` Module 1-2 dimulai, supaya ada kode/artefak nyata untuk dipakai latihan (design doc, code review, postmortem) — tapi tidak wajib menunggu course itu selesai, karena banyak mini project di sini bisa memakai kode dari pekerjaan Anda sendiri.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan permintaan Anda ("belajar cara berpikir, standar, kebiasaan, dan engineering culture ala Google/Meta/Amazon/Netflix/Uber/Airbnb/Stripe/Shopify/Cloudflare") dan jawaban kalibrasi 2026-08-07:

1. **Kenapa course terpisah, bukan disisipkan**: `courses/backend-fundamentals` (15 modul, disetujui) dan `courses/system-design` (14 modul, disetujui) sudah berat secara teknis — menambahkan materi "cara berpikir/budaya" ke sana akan mengaburkan fokus keduanya. Course ini murni tentang *judgment*, komunikasi tertulis, dan kebiasaan kerja — layer yang berbeda dari kedalaman teknis, jadi sengaja dipisah supaya masing-masing course tetap fokus.
2. **Target tetap freelance internasional** (dikonfirmasi 2026-08-07, Master Goal `progress/roadmap.md` tidak berubah) — "kelas dunia" di sini berarti **standar kerja**, bukan tempat kerja. Karena itu course ini sengaja **tidak** membahas hal yang spesifik ke tangga karier korporat (performance review cycle, promo packet, leveling ladder internal) — itu tidak relevan untuk freelancer independen. Fokusnya digeser ke hal yang benar-benar bisa dipakai untuk membangun kepercayaan klien: menulis jelas, membuat keputusan yang bisa dipertanggungjawabkan, dan menangani kesalahan secara profesional.
3. **Beda dengan course "Freelance & International Career Readiness"** (belum dibuat, lihat gap di `progress/roadmap.md`): course itu nanti akan fokus ke sisi *bisnis* freelance (proposal, kontrak, pricing, client acquisition, rating). Course ini fokus ke *craft* teknis-komunikasi internal (bagaimana seorang senior engineer berpikir & menulis) yang jadi bahan baku kepercayaan tersebut — dua course ini saling melengkapi, tidak tumpang tindih.
4. **Tidak mengulang** materi yang sudah ada: ADR & ownership arsitektur ada di `courses/system-design` Module 1 (course ini menyambung dengan Module 2 — Design Doc, superset dari ADR), Resiliency/Observability teknis ada di `courses/backend-fundamentals` Module 12-13 (course ini fokus ke *budaya* insiden & postmortem, bukan implementasi Resilience4j-nya), dan proses Scrum/estimasi tim penuh ada di `courses/scrum-master` (course ini hanya menyentuh prinsip komunikasi estimasi, bukan framework Agile lengkap).

Referensi riset yang dipakai menyusun roadmap ini: *Staff Engineer: Leadership Beyond the Management Track* (Will Larson) & *The Staff Engineer's Path* (Tanya Reilly) untuk kerangka judgment & pengaruh tanpa otoritas, *Working Backwards* (Colin Bryar & Bill Carr) untuk budaya keputusan & narrative writing ala Amazon (6-pager, PR/FAQ, Type 1/2 decisions), [Google Engineering Practices](https://google.github.io/eng-practices/) untuk budaya code review, *Site Reliability Engineering* & *The Site Reliability Workbook* (Google) untuk budaya blameless postmortem & on-call, *Radical Candor* (Kim Scott) untuk budaya feedback, *Debugging Teams* / *Team Geek* (Brian Fitzpatrick & Ben Collins-Sussman) untuk prinsip Humility-Respect-Trust, Netflix Culture Memo ("Freedom & Responsibility", Reed Hastings & Patty McCord), Google re:Work "Project Aristotle" untuk psychological safety, serta sintesis engineering blog Stripe/Shopify/Cloudflare/Uber/Airbnb untuk studi kasus perusahaan mid-to-large-scale. Juga menyambung ke `standards/GIT_CONVENTIONS.md`, `standards/ENGINEERING_STANDARDS.md`, dan `standards/README_TEMPLATE.md` yang sudah ada di repo ini sebagai kontrak yang dipraktikkan langsung.

---

# Learning Objectives

* [ ] Mengambil & mendokumentasikan keputusan teknis dengan sadar (reversible vs irreversible), bukan asal pilih yang paling familiar
* [ ] Menulis design doc/RFC ala Google/Amazon yang bisa dipertahankan di depan reviewer maupun klien non-teknis
* [ ] Memberi dan menerima code review secara konstruktif — membedakan must-fix, nit, dan pertanyaan, tanpa jadi defensif
* [ ] Menjalankan incident response & menulis blameless postmortem yang benar-benar menghasilkan perbaikan, bukan mencari kambing hitam
* [ ] Mengenali dan mengomunikasikan technical debt ke non-engineer dengan framing biaya/risiko, bukan jargon
* [ ] Memberi feedback dengan Radical Candor, mentoring tanpa jadi manajer, dan memengaruhi keputusan tanpa otoritas formal
* [ ] Bekerja dengan requirement ambigu, memberi estimasi jujur (bukan titik palsu-presisi), dan menyampaikan bad news lebih awal
* [ ] Mensintesis budaya engineering Netflix/Amazon/Google/Stripe/Shopify/Cloudflare menjadi prinsip kerja pribadi yang konkret, bukan sekadar hafalan istilah
* [ ] Menyelesaikan capstone: portofolio "cara kerja" (design doc, review log, postmortem, debt report, prinsip pribadi) dari satu studi kasus ujung-ke-ujung, siap ditunjukkan ke klien freelance internasional sebagai bukti senioritas

---

# Course Modules

## Module 1 — Engineering Judgment & Decision-Making

Description: Titik awal course ini — sebelum bisa menulis atau mengomunikasikan keputusan dengan baik, harus dulu tahu bagaimana *mengambil* keputusan teknis secara sadar, bukan reflex "yang paling familiar" atau "yang lagi tren".

Lessons:

* [ ] Reversible vs irreversible decisions (Bezos Type 1/Type 2): kapan boleh cepat & murah untuk dicoba, kapan wajib pelan dan hati-hati
* [ ] Trade-off thinking di bawah ketidakpastian: cara membandingkan opsi tanpa data lengkap, tanpa terjebak analysis paralysis
* [ ] Dua sisi mata uang yang sama: over-engineering ("resume-driven development") vs premature optimization — keduanya akar masalah yang sama (memutuskan tanpa bukti)
* [ ] Cara senior engineer bilang "tidak" secara profesional — menolak scope creep/permintaan buruk tanpa menutup hubungan kerja
* [ ] Studi kasus keputusan teknis nyata dari engineering blog (mis. kenapa Basecamp/DHH memilih monolith, kenapa Stripe memilih stack tertentu) — bagaimana alasan itu ditulis dan dipertahankan

Mini Project: Ambil 1 keputusan teknis nyata dari pekerjaan/course lain Anda, klasifikasikan sebagai Type 1 atau Type 2, lalu tulis trade-off analysis singkat dengan minimal 2 opsi alternatif yang ditolak dan alasan konkret penolakannya.

Status: Not Started

---

## Module 2 — Menulis Design Doc & RFC

Description: Keterampilan tertulis yang paling langsung membedakan senior dari junior engineer — kemampuan menjelaskan *kenapa* sebelum menulis kode, dengan cara yang bisa dibaca baik oleh engineer lain maupun klien.

Lessons:

* [ ] Anatomi design doc yang baik (gaya Google): problem statement, goals/non-goals, alternatives considered, rollout plan
* [ ] Amazon 6-pager & PR/FAQ (*Working Backwards*): menulis narrative naratif sebagai alat berpikir, kenapa ini mengalahkan bullet slide
* [ ] Kapan design doc benar-benar dibutuhkan vs kapan overkill — tidak semua perubahan butuh dokumen 5 halaman
* [ ] Audience-aware writing: versi untuk sesama engineer vs versi untuk klien/stakeholder non-teknis (bahasa, tingkat detail, framing risiko)
* [ ] Hubungan design doc dengan ADR (`courses/system-design` Module 1) — design doc untuk *mengusulkan* sebelum keputusan, ADR untuk *mencatat* setelah keputusan diambil

Mini Project: Tulis 1 design doc lengkap (format Google-style) untuk fitur/perubahan nyata dari proyek Anda sendiri (boleh dari `courses/backend-fundamentals`/`courses/system-design` atau pekerjaan), lalu minta review (REVIEW_MODE) seolah dari tech lead.

Status: Not Started

---

## Module 3 — Code Review sebagai Budaya, Bukan Gerbang

Description: Code review yang sehat adalah salah satu sinyal budaya engineering paling jelas — modul ini membangun kebiasaan memberi dan menerima review dengan cara yang membangun, sesuai `standards/GIT_CONVENTIONS.md` & `standards/ENGINEERING_STANDARDS.md`.

Lessons:

* [ ] Prinsip [Google Engineering Practices](https://google.github.io/eng-practices/): apa yang wajib di-block (correctness, security) vs apa yang cuma saran (style preference)
* [ ] Memberi feedback yang jelas: memisahkan komentar must-fix, nit, dan pertanyaan — supaya penulis tahu mana yang wajib diubah
* [ ] Menerima code review tanpa defensif — memisahkan kritik terhadap kode dari kritik terhadap diri sendiri
* [ ] Kecepatan review sebagai budaya tim: kenapa review yang lambat adalah salah satu biaya tersembunyi terbesar dalam engineering
* [ ] Review sebagai alat mentoring: menjelaskan "kenapa" di balik saran, bukan cuma menyuruh "ubah ini"

Mini Project: Lakukan code review penuh terhadap 1 PR/service nyata (punya sendiri atau contoh yang diberikan), tulis feedback dengan kategori must-fix/nit/pertanyaan, lalu simulasikan menerima review balik dan menanggapi 1 kritik yang tidak Anda setujui secara profesional.

Status: Not Started

---

## Module 4 — Incident Response & Blameless Postmortem

Description: Bagaimana engineer kelas dunia menangani kegagalan production — bukan cuma teknis (itu sudah ada di `standards/RESILIENCY_STANDARDS.md`/`standards/OBSERVABILITY_STANDARDS.md`), tapi budaya di sekitarnya yang menentukan apakah tim benar-benar belajar dari insiden.

Lessons:

* [ ] Anatomi insiden production: deteksi → mitigasi → resolusi → postmortem, dan kenapa urutan ini tidak boleh dibalik (mitigasi dulu, root cause belakangan)
* [ ] Budaya blameless (Google SRE): kenapa mencari "siapa yang salah" merusak kemampuan tim untuk jujur soal apa yang sebenarnya terjadi
* [ ] Menulis postmortem yang baik: timeline faktual, root cause analysis (5 Whys), action item yang benar-benar spesifik dan bisa ditindaklanjuti
* [ ] On-call mentality: kesiapan mental saat dipanggil di luar jam kerja, pentingnya runbook, dan kapan harus eskalasi alih-alih coba sendirian
* [ ] Studi kasus postmortem publik nyata (mis. status page/blog post Cloudflare, GitHub, AWS) — apa yang membuat postmortem itu dipercaya pembacanya

Mini Project: Simulasikan 1 insiden (bisa dari bug nyata yang pernah Anda alami di pekerjaan), tulis postmortem lengkap format blameless dengan timeline, root cause 5 Whys, dan minimal 3 action item konkret.

Status: Not Started

---

## Module 5 — Technical Debt & Prioritas

Description: "Kode jelek" dan "technical debt" sering disamakan padahal beda — modul ini membangun kemampuan mengenali debt secara sadar dan mengomunikasikannya ke orang yang tidak paham kode sama sekali.

Lessons:

* [ ] Mengenali technical debt secara sadar: debt yang disengaja (dengan justifikasi) vs debt kecelakaan — kuadran Martin Fowler
* [ ] Boy Scout Rule vs "jangan boil the ocean" — kapan refactor kecil sambil jalan cukup, kapan butuh rencana besar terpisah
* [ ] Mengomunikasikan debt ke non-engineer (klien/stakeholder) dengan framing biaya & risiko bisnis, bukan istilah "kode kotor"
* [ ] Cost of delay & prioritization framework sederhana (impact vs effort) untuk memutuskan apa yang dikerjakan lebih dulu
* [ ] Cara bilang "kita perlu waktu untuk membereskan ini dulu" ke klien tanpa terdengar tidak kompeten

Mini Project: Audit 1 codebase (punya sendiri) untuk technical debt, kategorikan dengan kuadran Fowler, lalu tulis 1 halaman "debt report" yang bisa dikirim ke klien fiktif lengkap dengan rekomendasi prioritas.

Status: Not Started

---

## Module 6 — Feedback, Mentoring & Pengaruh Tanpa Otoritas

Description: Bagaimana engineer senior bekerja dengan orang lain — memberi feedback yang jujur tanpa merusak hubungan, dan memengaruhi keputusan tanpa harus punya jabatan formal.

Lessons:

* [ ] Radical Candor (Kim Scott): peduli secara personal + menantang secara langsung — kenapa dua-duanya wajib ada bersamaan, bukan salah satu
* [ ] Triad Humility-Respect-Trust (*Team Geek*) sebagai fondasi kerja sama teknis yang sehat
* [ ] Mentoring rekan/junior tanpa jadi manajer: mengajar lewat pertanyaan yang mengarahkan, bukan langsung memberi jawaban
* [ ] Influence without authority: cara meyakinkan tim/klien mengikuti rekomendasi teknis tanpa wewenang formal untuk memerintah
* [ ] Disagree and commit: cara berdebat teknis dengan keras di ruang diskusi, lalu tetap solid menjalankan keputusan yang diambil tim

Mini Project: Tulis 1 skenario feedback nyata yang pernah/akan Anda berikan (ke rekan, junior, atau klien), susun dalam format Radical Candor, lalu roleplay tertulis reaksi lawan bicara dan respons Anda terhadapnya.

Status: Not Started

---

## Module 7 — Bekerja dengan Ambiguitas & Estimasi yang Jujur

Description: Requirement yang 100% jelas nyaris tidak pernah terjadi di dunia nyata — modul ini membangun kebiasaan bertanya, mengestimasi jujur, dan menyampaikan kabar buruk lebih awal, tanpa mengulang framework Scrum penuh dari `courses/scrum-master`.

Lessons:

* [ ] Cara bertanya yang tepat untuk mengurangi ambiguitas requirement, tanpa menunggu spec yang sempurna (yang tidak akan pernah datang)
* [ ] Estimasi jujur: cone of uncertainty, kenapa estimasi titik ("3 hari") berbahaya, komunikasi rentang & confidence level
* [ ] Mengelola scope creep dari klien secara profesional — prinsip komunikasinya saja, framework proses lengkap ada di `courses/scrum-master`
* [ ] Kapan "good enough" cukup vs kapan benar-benar butuh sempurna — kalibrasi berdasarkan risiko & biaya kegagalan, bukan perfeksionisme default
* [ ] Menyampaikan bad news (proyek telat, bug besar ditemukan) ke klien sedini mungkin, bukan menunggu sampai deadline

Mini Project: Ambil 1 requirement ambigu (nyata atau simulasi), tulis daftar pertanyaan klarifikasi + estimasi berbentuk rentang dengan asumsi eksplisit, lalu simulasikan tertulis menyampaikan "proyek ini akan telat" ke klien fiktif secara profesional.

Status: Not Started

---

## Module 8 — Studi Kasus Engineering Culture Kelas Dunia

Description: Menyintesis budaya dari beberapa perusahaan teknologi terkemuka — bukan untuk ditiru mentah-mentah, tapi untuk diambil prinsip yang benar-benar cocok dengan cara kerja Anda sebagai freelancer independen.

Lessons:

* [ ] Netflix "Freedom & Responsibility": budaya otonomi tinggi, dan trade-off-nya dengan akuntabilitas yang juga tinggi
* [ ] Amazon Leadership Principles yang relevan untuk engineer individu (Ownership, Bias for Action, Dive Deep) — disaring dari yang sifatnya manajerial
* [ ] Google: psychological safety (Project Aristotle) sebagai fondasi budaya blameless yang sudah dipelajari di Module 4
* [ ] Stripe/Shopify/Cloudflare: sintesis engineering blog — bagaimana perusahaan mid-to-large-scale membangun budaya tanpa skala Google/Amazon
* [ ] Sintesis pribadi: menyusun 5-7 prinsip kerja pribadi, diambil sadar dari studi kasus di atas — bukan copy-paste budaya orang lain

Mini Project: Tulis "Engineering Principles" pribadi (1 halaman) yang akan Anda pakai sebagai standar kerja sendiri, disertai justifikasi dari studi kasus mana tiap prinsip diambil dan bagaimana ini akan terlihat dalam pekerjaan sehari-hari.

Status: Not Started

---

## Module 9 — Capstone: Mempraktikkan Senior Engineer Craft di Konteks Freelance

Description: Menyatukan seluruh 8 modul menjadi satu studi kasus ujung-ke-ujung — bukti konkret bahwa Anda bisa berpikir, menulis, dan bekerja setara senior engineer kelas dunia, dikemas untuk konteks freelance internasional.

Lessons:

* [ ] Menggabungkan design doc (Module 2), code review (Module 3), postmortem (Module 4), dan debt report (Module 5) menjadi satu portofolio "cara kerja", bukan cuma kode
* [ ] Menyusun 1 studi kasus lengkap: requirement ambigu (Module 7) → design doc → implementasi (menyambung ke `courses/backend-fundamentals`/`courses/system-design`) → code review → simulasi insiden → postmortem
* [ ] Presentasi tertulis ke klien fiktif: menjelaskan keputusan teknis, trade-off, dan cara Anda menangani kesalahan — bahasa yang membangun kepercayaan, bukan menutupi
* [ ] Self-review akhir: cross-check semua artefak (design doc, review log, postmortem, debt report, prinsip pribadi) untuk konsistensi satu sama lain
* [ ] Refleksi: bagaimana artefak-artefak ini akan dipakai sebagai bukti senioritas di proposal Upwork/Toptal — disambungkan ke course "Freelance & International Career Readiness" yang akan datang

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Engineering Craft Portfolio** — kumpulan artefak nyata dari satu studi kasus ujung-ke-ujung yang membuktikan Anda berpikir dan bekerja setara senior engineer kelas dunia: (1) design doc lengkap untuk 1 fitur/perubahan nyata, (2) code review log (feedback given & received) untuk 1 PR nyata, (3) postmortem blameless untuk 1 insiden simulasi/nyata, (4) technical debt report untuk 1 codebase, (5) dokumen "Engineering Principles" pribadi.

Acceptance Criteria:

* [ ] Design doc mengikuti struktur Module 2 (problem statement, goals/non-goals, alternatives considered, rollout plan) dan sudah melalui 1 siklus review
* [ ] Code review log memisahkan feedback must-fix/nit/pertanyaan dengan jelas, plus 1 contoh tanggapan profesional atas kritik yang tidak disetujui
* [ ] Postmortem berformat blameless: timeline faktual, root cause 5 Whys, minimal 3 action item konkret
* [ ] Debt report memakai kuadran Fowler dan ditulis dengan bahasa yang bisa dipahami klien non-teknis
* [ ] Dokumen Engineering Principles berisi 5-7 prinsip dengan justifikasi eksplisit dari studi kasus Module 8
* [ ] Seluruh artefak konsisten satu sama lain (studi kasus yang sama, keputusan yang tidak saling bertentangan tanpa penjelasan)
* [ ] Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, siap ditunjukkan ke klien freelance internasional sebagai bukti cara kerja senior

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: -

Current Lesson: -

Next Lesson: Menunggu approval roadmap ini dari user sebelum mulai Module 1, Lesson 1
