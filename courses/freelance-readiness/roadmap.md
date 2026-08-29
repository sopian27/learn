# Course Roadmap

## Course Information

* Course Name: Freelance & International Career Readiness — dari Profil Kosong sampai Client Internasional Pertama
* Category: Career Strategy / Freelance Business Operations (non-teknis, konteks software engineer Java/Spring Boot dipakai untuk contoh) — mengisi sisa gap "Freelance & International Career Readiness" di Master Goal (`progress/roadmap.md`), bukan pengganti `courses/credential-strategy` (lapisan kredensial/sertifikasi) maupun `courses/career-reinvention` (lapisan kemasan CV/LinkedIn/portfolio/networking umum) — course ini murni operasional BISNIS freelance itu sendiri
* Difficulty: Level 1 (belum pernah punya profil Upwork/Toptal, belum pernah dapat client internasional) → Level 3 (profil live siap invite, proses aplikasi Toptal jalan kalau dipilih, kontrak & pembayaran internasional pertama siap dieksekusi)
* Estimated Duration: 8 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`). Pace sedang ≈ 2-3 bulan.
* Prerequisites: Idealnya sudah punya CV/LinkedIn/portfolio yang solid (`courses/career-reinvention`), tapi tidak wajib menunggu selesai — boleh paralel. Course ini tidak mengulang personal branding/CV, murni operasional: platform, proposal, harga, kontrak, pembayaran, client management, reputasi.

---

# Catatan Scope (baca dulu sebelum approve)

Dibuat 2026-08-29 mengikuti rekomendasi `/recommend` — sumber: Gap Analysis `progress/roadmap.md` poin #3, satu-satunya sisa gap Master Goal yang belum ada course sama sekali (draft maupun aktif). **Dibuat sambil kedua Course Aktif ("Character Development" Module 1/6, "Backend Engineering Fundamentals Rebuild" Module 2/15) masih di bawah 50% — user eksplisit override guard `CLAUDE.md` § Course Creation Discipline saat ditanya konfirmasi.**

1. **Pembagian gap eksplisit** (supaya tidak tumpang tindih dengan 2 course terkait yang sudah ada): sisi kredensial (sertifikasi/TOEFL/bukti kompetensi) ada di `courses/credential-strategy`; sisi kemasan & distribusi umum (CV, LinkedIn, personal branding, portfolio narrative, networking, job search dual-purpose) ada di `courses/career-reinvention`. Course ini murni **operasional bisnis freelance**: platform mana yang dipakai, cara profil ranking di platform itu, cara menulis proposal yang dibalas, cara menentukan harga, cara melindungi diri lewat kontrak, cara menerima pembayaran internasional tanpa rugi fee/pajak, cara mengelola client asing, dan cara membangun reputasi jangka panjang.
2. **Dua jalur platform dibedakan eksplisit** karena mekanismenya sangat berbeda: Upwork (marketplace terbuka, kompetisi proposal, ranking algoritma) vs Toptal (vetting ketat di depan, begitu lolos tidak perlu kompetisi proposal). Course ini mencakup keduanya supaya keputusan platform berdasarkan data, bukan tebakan.
3. **Konteks pajak Indonesia dimasukkan eksplisit** (Module 6) karena ini kebutuhan nyata yang sering diabaikan course freelance berbahasa Inggris — penghasilan dari client luar negeri tetap wajib dilaporkan & kena PPh di Indonesia meski client tidak memotong pajak di negaranya (prinsip *physical presence* dalam tax treaty).
4. **Tidak dari nol bahasa Inggris** — `ai-los/CAREER_MODE.md` sudah menyediakan kerangka mentoring komunikasi teknis dalam Bahasa Inggris secara ongoing; course ini asumsi bahasa Inggris tertulis sudah cukup untuk proposal/komunikasi client, fokus ke *apa* yang ditulis/dinegosiasikan, bukan *tata bahasanya*.

Referensi riset yang dipakai menyusun roadmap ini (Agustus 2026, semua verifikasi web fresh sesuai `ai-los/COURSE_CREATION.md`): mekanisme ranking profil Upwork 2026 — keyword relevance di title/overview/skill menyumbang 20-25% faktor ranking, profil yang aktif diperbarui mengalahkan profil statis meski review lebih sedikit ([GigRadar](https://gigradar.io/blog/upwork-profile-seo), [SnipeWork](https://snipework.com/blog/upwork-profile-optimization-2026)); proses vetting resmi Toptal — 4 tahap (screening komunikasi Bahasa Inggris 15-20 menit → tes teknis timed 90 menit via HackerRank/Codility → live technical interview 1-2 jam → test project berbayar 20-40 jam), total 3-8 minggu, acceptance rate di bawah 3% ([DDIY](https://ddiy.co/toptal-interview-screening-process/), [HoningJS](https://www.honingjs.com/blogs/toptal-interview-process-guide-and-review/)); perbandingan biaya pembayaran internasional — Wise pakai mid-market rate transparan (fee 0,4-1,16%) lebih murah untuk transfer langsung ke client, Payoneer (fee ~1% + markup kurs ~0,5-2%) lebih unggul untuk integrasi native ke Upwork/Fiverr, strategi gabungan (terima lewat Payoneer, pindahkan ke Wise untuk konversi) dipakai banyak freelancer berpengalaman ([xflowpay](https://www.xflowpay.com/blog/wise-vs-payoneer), [Nomad Labs](https://nomad-labs.com/payoneer-vs-wise-business-non-us-freelancer/)); elemen wajib kontrak freelance developer — scope of work eksplisit untuk cegah scope creep, klausul IP assignment (default hukum sering menguntungkan freelancer, harus ditegaskan tertulis), klausul NDA, termination clause ([Index.dev](https://www.index.dev/blog/freelance-software-developer-contract-template), [Mediabistro](https://www.mediabistro.com/go-freelance/what-to-know-about-ip-law-contracts-and-ndas-as-a-freelancer/)); syarat badge reputasi Upwork 2026 — Rising Talent (profil 100% lengkap + rating rata-rata ≥4.8 + earning $250/12 bulan), Top Rated (Job Success Score ≥90% selama 13 dari 16 minggu terakhir + earning $1.000/12 bulan + akun ≥90 hari), dievaluasi otomatis tiap 2 minggu tanpa perlu apply ([Upwork Help — Top Rated](https://support.upwork.com/hc/en-us/articles/211068468-How-to-become-Top-Rated-on-Upwork), [Upwork Help — Rising Talent](https://support.upwork.com/hc/en-us/articles/211063228-How-to-become-a-Rising-Talent-on-Upwork)); pergeseran model harga — value-based pricing bisa menghasilkan 42% lebih banyak per proyek dibanding hourly, tapi butuh prasyarat (10-20 proyek sejenis selesai supaya estimasi waktu akurat ±20%, positioning kuat, inbound client flow) sebelum layak dipakai — hourly tetap tepat untuk discovery call/audit teknis/scope belum jelas ([Plutio](https://www.plutio.com/freelancer-magazine/freelance-pricing-models-compared), [Upwork — Set Your Rate](https://www.upwork.com/resources/how-to-set-your-freelance-rate)); struktur proposal Upwork yang efektif — 200-300 kata, dua kalimat pertama menentukan dibaca lanjut atau tidak, personalisasi menaikkan reply rate hingga 30%, 3 portofolio spesifik-relevan lebih efektif dari daftar skill generik ([GigRadar — Cover Letter](https://gigradar.io/blog/the-upwork-cover-letter-modern-best-practices), [Upwork — Cover Letter Tips](https://www.upwork.com/resources/cover-letter-tips)); kewajiban pajak freelancer Indonesia dengan client luar negeri — penghasilan luar negeri tetap wajib dilaporkan (SPT 1770 Bagian A Kolom 4) & kena PPh meski client tidak memotong pajak di negaranya, opsi skema PPh Final 0,5% dari omzet untuk omzet tahunan di bawah Rp 4,8 miliar ([Ortax](https://ortax.org/pajak-freelance-online-atau-remote-working-luar-negeri), [Direktorat Jenderal Pajak](https://www.pajak.go.id/en/node/70103)).

---

# Learning Objectives

* [ ] Memilih platform freelance yang tepat (Upwork/Toptal/direct outreach) berdasarkan positioning & niche, bukan ikut-ikutan
* [ ] Membangun profil Upwork yang ranking tinggi secara algoritmik: headline, overview, keyword, foto, portofolio terkurasi
* [ ] Memahami & mempersiapkan diri untuk 4 tahap vetting Toptal (komunikasi, tes teknis, live interview, test project) kalau memilih jalur ini
* [ ] Menulis proposal/cover letter yang dibaca sampai selesai dan dibalas client, bukan cover letter generik yang di-copy-paste
* [ ] Menentukan model harga yang tepat sesuai tahap karier (hourly vs fixed vs value-based) dan tahu kapan waktunya naik level
* [ ] Menyusun kontrak yang melindungi diri sendiri: scope of work, IP assignment, NDA, termination clause, milestone & escrow
* [ ] Mengelola pembayaran internasional dengan fee & kurs paling efisien (Payoneer, Wise, kombinasi keduanya) dan tahu kewajiban pajak Indonesia atas penghasilan itu
* [ ] Mengelola komunikasi & ekspektasi client lintas zona waktu, termasuk menangani scope creep dan potensi sengketa
* [ ] Membangun reputasi jangka panjang: Job Success Score, badge Rising Talent/Top Rated, strategi minta review, jalan menuju retainer/agency
* [ ] Menyelesaikan capstone: profil live siap invite + minimal 5 proposal nyata terkirim + draft kontrak siap pakai + sistem pembayaran & pencatatan pajak berjalan

---

# Course Modules

## Module 1 — Landscape Platform & Positioning

Description: Sebelum membangun apa pun, tentukan dulu arena mana yang dimainkan. Modul ini memetakan mekanisme Upwork (marketplace terbuka, kompetisi proposal) vs Toptal (vetting ketat di depan) vs direct outreach/networking, lalu menentukan niche & ideal client profile berdasarkan skill Backend Fundamentals yang sedang dibangun paralel.

Lessons:

* [ ] 1.1 Peta platform freelance internasional: Upwork vs Toptal vs Freelancer.com vs direct outreach — mekanisme, kompetisi, dan siapa yang cocok pakai jalur mana
* [ ] 1.2 Niche selection: kenapa generalis "full-stack developer" kalah dari spesialis di pasar freelance, cara memilih niche dari skill yang sudah/sedang dikuasai
* [ ] 1.3 Ideal client profile: jenis client yang membayar layak vs red flag client dari awal (race-to-bottom pricing, scope tidak jelas, red flag di job post)
* [ ] 1.4 Menyusun rencana platform pribadi: pilih 1 platform utama untuk 3 bulan pertama, dengan kriteria keputusan eksplisit (bukan ikut tren)

Mini Project: Dokumen 1 halaman "Positioning Statement" — niche, ideal client profile, dan platform pilihan beserta alasan berbasis kriteria dari lesson 1.1-1.3.

Status: Belum dimulai

---

## Module 2 — Profil & Portofolio: Upwork SEO dan Jalur Vetting Toptal

Description: Profil adalah aset yang bekerja 24/7. Modul ini membedah faktor ranking algoritmik Upwork dan, kalau jalur Toptal dipilih, mempersiapkan 4 tahap vetting-nya secara konkret.

Lessons:

* [ ] 2.1 Anatomi profil Upwork yang ranking tinggi: headline (bobot terbesar per karakter), overview, keyword density title/overview/skill (20-25% faktor ranking)
* [ ] 2.2 Portofolio yang mengonversi: urutan highlighted item, foto profesional, kaitan portofolio dengan niche dari Module 1 — bukan sekadar upload semua project lama
* [ ] 2.3 Proses vetting Toptal tahap demi tahap: screening komunikasi Bahasa Inggris → tes teknis timed (HackerRank/Codility, ~90 menit) → live technical interview (1-2 jam) → test project berbayar (20-40 jam) — cara mempersiapkan tiap tahap
* [ ] 2.4 Kalibrasi realistis: kapan waktu yang tepat mendaftar Toptal (acceptance rate <3%, butuh kesiapan teknis solid) vs mulai dulu dari Upwork sambil menyiapkan diri

Mini Project: Draft lengkap profil Upwork (headline + overview + 3 portofolio item terkurasi) siap dipublikasikan, ATAU rencana persiapan 4 tahap Toptal dengan timeline konkret kalau itu jalur pilihan.

Status: Belum dimulai

---

## Module 3 — Proposal Writing & Client Outreach

Description: Profil bagus percuma kalau proposal tidak dibaca. Modul ini fokus ke struktur proposal yang benar-benar dibalas, berdasarkan data reply-rate nyata, bukan template generik.

Lessons:

* [ ] 3.1 Anatomi proposal yang dibalas: dua kalimat pembuka menentukan dibaca lanjut atau tidak, fokus ke masalah client bukan "menjual diri sendiri" di awal
* [ ] 3.2 Personalisasi vs template: cara membangun sistem proposal semi-personal (kerangka tetap, isi disesuaikan) tanpa menulis dari nol tiap kali — personalisasi menaikkan reply rate hingga 30%
* [ ] 3.3 Memilih & menyusun bukti kerja dalam proposal: 3 portofolio spesifik-relevan (satu project relevan, satu hasil terukur, satu proses kerja) — bukan daftar skill generik
* [ ] 3.4 Direct outreach di luar marketplace: cara pendekatan client potensial lewat LinkedIn/email tanpa terlihat spam (menyambung ke fondasi `courses/career-reinvention` kalau sudah berjalan)

Mini Project: Kirim minimal 5 proposal nyata (Upwork atau direct outreach) memakai kerangka dari lesson 3.1-3.3, catat reply rate sebagai baseline data.

Status: Belum dimulai

---

## Module 4 — Pricing Strategy & Rate Setting

Description: Salah menentukan harga adalah penyebab paling umum freelancer baru terjebak race-to-bottom. Modul ini membedah 3 model harga dan kapan masing-masing tepat dipakai.

Lessons:

* [ ] 4.1 Tiga model harga: hourly, fixed-price per project, value-based pricing — mekanisme dan insentif masing-masing (hourly membalik insentif: makin efisien makin sedikit dibayar)
* [ ] 4.2 Value-based pricing: kenapa bisa menghasilkan hingga 42% lebih banyak per proyek, tapi butuh prasyarat (10-20 proyek sejenis, positioning kuat, inbound client flow) — jangan dipakai prematur
* [ ] 4.3 Menentukan rate awal yang realistis untuk profil baru, dan jalur menaikkannya secara bertahap seiring reputasi terbangun (Module 8)
* [ ] 4.4 Negosiasi harga dengan client: cara merespons "budget kita segini" tanpa langsung diskon, cara membingkai value bukan waktu

Mini Project: Kalkulasi rate hourly awal berbasis biaya hidup + target income, plus 1 simulasi penawaran fixed-price untuk salah satu proposal dari Module 3.

Status: Belum dimulai

---

## Module 5 — Kontrak, Scope Protection & Kekayaan Intelektual

Description: Tanpa kontrak yang jelas, scope creep dan sengketa pembayaran adalah risiko nyata di client internasional. Modul ini membangun kontrak yang benar-benar melindungi, bukan formalitas kosong.

Lessons:

* [ ] 5.1 Scope of work yang eksplisit: cara mendefinisikan deliverable, deadline, dan batasan supaya scope creep bisa ditolak dengan basis kontrak, bukan basis "perasaan tidak enak"
* [ ] 5.2 Klausul IP assignment: kenapa default hukum sering menguntungkan freelancer sampai ditegaskan tertulis, kapan hak pakai portofolio tetap dipegang setelah IP dialihkan ke client
* [ ] 5.3 NDA, termination clause, dan milestone/escrow structure — cara memecah project besar jadi milestone berbayar bertahap untuk mengurangi risiko non-payment
* [ ] 5.4 Kontrak native platform (Upwork Escrow/Milestone) vs kontrak direct client — kapan wajib pakai kontrak tertulis terpisah di luar platform

Mini Project: Susun 1 template kontrak pribadi (scope of work + IP assignment + termination clause + milestone) siap dipakai untuk client pertama.

Status: Belum dimulai

---

## Module 6 — Pembayaran Internasional & Kewajiban Pajak Indonesia

Description: Fee dan kurs yang salah pilih bisa memakan margin signifikan, dan penghasilan luar negeri tetap kena kewajiban pajak Indonesia meski tidak dipotong di negara client. Modul ini menyusun sistem penerimaan pembayaran & pencatatan pajak yang benar sejak awal.

Lessons:

* [ ] 6.1 Payoneer vs Wise: perbandingan fee & kurs (Wise pakai mid-market rate ~0,4-1,16%, Payoneer ~1% + markup kurs 0,5-2%), kapan pakai yang mana, dan strategi kombinasi (terima via Payoneer untuk marketplace, pindahkan ke Wise untuk konversi murah)
* [ ] 6.2 Menerima pembayaran langsung dari direct client (di luar marketplace): invoicing internasional, metode transfer, dan risiko yang perlu diwaspadai (chargeback, client tidak bayar)
* [ ] 6.3 Kewajiban pajak penghasilan freelance dari client luar negeri: penghasilan tetap wajib dilaporkan di SPT 1770 meski client tidak memotong pajak (prinsip *physical presence*), opsi skema PPh Final 0,5% dari omzet untuk omzet di bawah Rp 4,8 miliar/tahun
* [ ] 6.4 Sistem pencatatan sederhana: memisahkan rekening bisnis vs pribadi, mencatat penghasilan per client untuk pelaporan pajak tahunan

Mini Project: Setup akun Payoneer dan/atau Wise (kalau belum ada), plus template pencatatan penghasilan bulanan untuk keperluan SPT.

Status: Belum dimulai

---

## Module 7 — Client Management & Komunikasi Lintas Zona Waktu

Description: Mendapat client hanya awal — mengelola hubungan kerja lintas budaya dan zona waktu tanpa salah paham adalah yang membuat client bertahan atau kabur di project kedua.

Lessons:

* [ ] 7.1 Menyusun ekspektasi di awal project: kick-off call/message yang menyelaraskan scope, jadwal update, dan channel komunikasi
* [ ] 7.2 Komunikasi asinkron yang efektif lintas zona waktu: cara menulis update status yang tidak butuh balasan real-time tapi tetap informatif
* [ ] 7.3 Menangani scope creep secara profesional: cara menolak/menegosiasikan permintaan di luar scope tanpa merusak hubungan, merujuk balik ke kontrak Module 5
* [ ] 7.4 Menangani konflik & potensi sengketa: eskalasi lewat platform (Upwork dispute resolution) vs negosiasi langsung, kapan waktunya menghentikan project

Mini Project: Simulasi tertulis — draft kick-off message + 1 skenario penolakan scope creep untuk project hipotetis dari proposal Module 3.

Status: Belum dimulai

---

## Module 8 — Reputasi, Badge System & Pertumbuhan Jangka Panjang

Description: Modul penutup — membangun reputasi yang membuat client datang sendiri (inbound), bukan terus-menerus mengejar lewat proposal, dan merancang jalur pertumbuhan setelah client pertama.

Lessons:

* [ ] 8.1 Job Success Score & badge Upwork: syarat Rising Talent (profil 100% + rating ≥4.8 + earning $250/12 bulan) dan Top Rated (JSS ≥90% selama 13/16 minggu + earning $1.000/12 bulan + akun ≥90 hari), dievaluasi otomatis tiap 2 minggu
* [ ] 8.2 Strategi meminta review tanpa terkesan memaksa, dan cara merespons review negatif secara profesional
* [ ] 8.3 Dari project pertama ke repeat client & retainer: cara membangun hubungan jangka panjang alih-alih terus mencari client baru dari nol
* [ ] 8.4 Jalur pertumbuhan lanjutan: kapan waktunya naik ke value-based pricing penuh (Module 4), membentuk agency kecil, atau fokus ke direct client tanpa marketplace fee

Mini Project / Capstone: Rencana pertumbuhan 6 bulan pasca-client-pertama, menyambungkan hasil Module 1-7 (positioning, profil, proposal, harga, kontrak, pembayaran, client management) jadi satu sistem operasional berjalan.

Status: Belum dimulai

---

# Enterprise Project (Capstone)

Description: Menyatukan seluruh course jadi operasi freelance yang benar-benar berjalan, bukan cuma teori — profil live, proposal nyata terkirim, kontrak siap pakai, dan sistem pembayaran/pajak berjalan.

Acceptance Criteria:

* Profil Upwork live & lengkap (atau progres konkret aplikasi Toptal tahap 1-2) sesuai standar Module 2
* Minimal 5 proposal nyata terkirim memakai kerangka Module 3, dengan data reply rate dicatat
* Rate card pribadi (hourly + opsi fixed-price) tersusun sesuai Module 4
* Template kontrak pribadi (scope of work + IP assignment + termination + milestone) siap pakai sesuai Module 5
* Akun Payoneer dan/atau Wise aktif, plus sistem pencatatan penghasilan untuk pajak sesuai Module 6
* Rencana pertumbuhan 6 bulan tertulis sesuai Module 8

Status: Belum dimulai

---

# Overall Progress

Module Completion: 0/8

Overall Completion: 0%

Current Module: -

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 (menunggu approval)
