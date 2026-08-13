# Course Roadmap

## Course Information

* Course Name: Career Reinvention & Active Job Search — dari CV 2017-2026 sampai Siap Eksekusi Peluang Berikutnya
* Category: Career Strategy / Personal Branding (non-teknis, konteks software engineer Java/Spring Boot dipakai untuk contoh) — melengkapi Master Goal "World-Class Backend Developer" (`progress/roadmap.md`), lapisan personal-marketing & distribusi dual-purpose (full-time DAN freelance), bukan pengganti `courses/credential-strategy` (lapisan kredensial) maupun `courses/engineering-culture` (lapisan craft/judgment)
* Difficulty: Level 2 (sudah kerja profesional 9 tahun, tapi CV/LinkedIn/portfolio belum direfresh, belum py sistem pencarian kerja aktif) → Level 4 (CV/LinkedIn/portfolio konsisten satu narasi, target company list + tracker berjalan, sudah eksekusi outreach nyata)
* Estimated Duration: 8 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`). Pace sedang ≈ 2-3 bulan.
* Prerequisites: Sudah kerja profesional dengan riwayat karier 2017-2026 (dikonfirmasi linear, tanpa career gap/pivot signifikan — lihat Catatan Scope). Tidak wajib menunggu course lain selesai — boleh paralel dengan course teknis manapun, tapi lebih efektif kalau sudah py minimal 1 artifact portfolio nyata (design doc/postmortem dari `courses/engineering-culture`, atau project kerja) untuk dijadikan bahan Module 5.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan permintaan "course memperbaiki karier: update CV dari 2017-2026, membangun LinkedIn profesional, personal branding, networking, portfolio, achievement, dan strategi aktif mencari peluang kerja" dan kalibrasi AskUserQuestion:

1. **Dual-purpose, bukan full-time murni maupun freelance murni.** CV, LinkedIn, portfolio, dan narasi dibangun supaya jalan untuk full-time DAN freelance client acquisition sekaligus — selaras Master Goal (`progress/roadmap.md`: freelance internasional utama, remote full-time opsi lanjutan). Modul job-search (6-7) tetap mencakup kanal keduanya (job board/recruiter untuk full-time, networking/referral yang juga relevan untuk klien freelance).
2. **Sengaja TIDAK mencakup sisi bisnis freelance murni** — profil Upwork/Toptal, proposal writing, client management, kontrak & pembayaran internasional, pricing/rating tetap **di luar scope**, mengisi gap "Freelance & International Career Readiness (sisa)" yang tercatat di `progress/roadmap.md` sebagai course terpisah nanti. Course ini murni: CV, LinkedIn, personal branding, portfolio, networking umum, dan sistem pencarian aktif — infrastruktur yang dipakai bersama oleh jalur full-time maupun freelance, bukan operasional bisnis freelance itu sendiri.
3. **"Achievement" = dokumentasi & kuantifikasi pencapaian yang SUDAH ADA** dari riwayat kerja 2017-2026 (pakai XYZ/STAR format), bukan strategi mengejar pencapaian baru (sertifikasi, open-source, speaking). Membangun pencapaian baru sudah dicakup `courses/credential-strategy` (sertifikasi/TOEFL) — course ini fokus menggali & mem-package apa yang sudah dikerjakan.
4. **Riwayat karier 2017-2026 diasumsikan linear** (dikonfirmasi user) — tidak ada career gap atau career pivot signifikan yang perlu ditangani secara khusus. Kalau asumsi ini berubah saat Module 1 audit berjalan, Module 1 bisa diperluas dengan lesson framing gap/pivot sebelum lanjut.
5. **Tidak mengulang** materi yang sudah ada: self-audit skill teknis tetap merujuk `courses/credential-strategy` Module 1 (bukan diulang), artifact "cara kerja senior" (design doc/postmortem) tetap dari `courses/engineering-culture` (course ini hanya mengemasnya jadi konten portfolio), dan materi teknis apapun (cloud/database/dst.) tetap di course masing-masing.

Referensi riset yang dipakai menyusun roadmap ini (Agustus 2026): panduan ATS resume 2026 — pergeseran ke skills-based screening, format one-column, CV yang di-tailor skor 40-60% lebih tinggi dari generic ([The Interview Guys](https://blog.theinterviewguys.com/ats-resume-optimization/), [Resume Optimizer Pro](https://resumeoptimizerpro.com/blog/ats-friendly-resume-tips), [JobWizard](https://jobwizard.ai/blog/how-to-optimize-your-resume-for-ats-systems-in-2026)); perubahan algoritma LinkedIn 2026 jadi mesin pencocokan berbasis LLM dengan AI agent first-pass sebelum recruiter, formula headline, skill-as-cluster (bukan keyword stuffing), dan pentingnya recommendation ([jobright.ai](https://jobright.ai/blog/ai-linkedin-profile-optimization/), [dev.to](https://dev.to/__be2942592/how-to-optimize-your-linkedin-profile-as-a-developer-in-2026-3e18), [JobSprout](https://www.jobsprout.ai/blog/linkedin-profile-guide)); data hidden job market — 70-85% posisi terisi lewat networking/referral, bukan job board ([resume.co](https://resume.co/blog/hidden-job-market), [CareerEnlightenment](https://careerenlightenment.com/networking-tactics-job-seekers-2026)); elemen personal branding portfolio developer — identitas visual konsisten, project narrative, maintenance berkala ([gola.supply](https://www.gola.supply/blog/developer-portfolio-websites), [Script and Tools](https://scriptandtools.com/building-personal-brand/)); XYZ Formula milik Laszlo Bock (mantan SVP People Operations Google) untuk menulis achievement terkuantifikasi — "Accomplished [X] as measured by [Y] by doing [Z]" ([Teal](https://www.tealhq.com/post/xyz-resume), [Resume.io](https://resume.io/blog/xyz-resume-format)); serta strategi target company list & application tracker — pencarian tertarget (20-50 perusahaan, 2-3 role) 3x lebih efektif menghasilkan interview daripada apply massal ([scale.jobs](https://scale.jobs/blog/targeted-job-search-complete-guide), [Enhancv](https://enhancv.com/blog/job-search-plan/)). Temuan inti yang membentuk struktur course: aset (CV/LinkedIn/portfolio) tanpa achievement terkuantifikasi lemah secara ATS maupun algoritma LinkedIn (Module 2 jadi fondasi wajib sebelum Module 3-4), dan aset sekuat apapun tidak berguna tanpa sistem eksekusi pencarian yang disiplin (Module 6-7 jadi penutup wajib sebelum capstone).

---

# Learning Objectives

* [ ] Mengaudit riwayat karier 2017-2026 jadi bahan baku mentah yang lengkap, dan mendefinisikan target dual-purpose (full-time + freelance) secara eksplisit
* [ ] Menggali dan mengkuantifikasi pencapaian nyata pakai XYZ Formula (Laszlo Bock/Google), termasuk teknik estimasi dampak saat data presisi tidak tersedia
* [ ] Menulis ulang CV yang lolos ATS 2026 (skills-based screening, tailoring) sekaligus enak dibaca manusia, dalam dua versi (ATS full-time & narrative freelance)
* [ ] Mengoptimasi profil LinkedIn sebagai aset pencarian aktif sesuai algoritma LLM-matching 2026 (headline, skill cluster, social proof)
* [ ] Membangun/memperbarui portfolio dan memastikan satu narasi personal brand konsisten lintas CV, LinkedIn, portfolio, dan GitHub
* [ ] Menjalankan strategi networking hidden job market (informational interview, weak-tie outreach) secara genuine, bukan transaksional
* [ ] Menyusun dan mengeksekusi sistem pencarian kerja aktif yang terukur (target company list, tracker, cadence outreach)
* [ ] Menyelesaikan capstone: Career Reinvention Portfolio dengan bukti eksekusi tahap awal nyata, siap dipakai untuk full-time maupun freelance

---

# Course Modules

## Module 1 — Career Audit & Target Definition

Description: Titik awal course ini — sebelum menulis apapun, harus dulu tahu jelas riwayat 9 tahun karier secara lengkap dan target yang mau dituju.

Lessons:

* [ ] Audit riwayat karier 2017-2026: timeline role/tanggung jawab/project/tech stack lengkap per tahun sebagai bahan baku mentah (belum diformat jadi CV)
* [ ] Dual-purpose target definition: role/level/industri untuk jalur full-time DAN tipe klien/proyek untuk jalur freelance — selaras Master Goal
* [ ] Menyusun positioning statement awal: 1 kalimat "siapa saya, expertise apa, value apa ke pemberi kerja/klien" (draft kasar, direvisi ulang di Module 5)
* [ ] Menghubungkan ke competency gap (merujuk self-audit skill di `courses/credential-strategy` Module 1, tidak mengulang) — apa yang sudah bisa dibuktikan vs belum lewat riwayat kerja
* [ ] Menyusun "Raw Material Inventory": daftar mentah role, tanggung jawab, project, stack per tahun 2017-2026, siap ditambang di Module 2

Mini Project: Career Timeline Audit — dokumen mentah riwayat 9 tahun (role, tanggung jawab, project, stack per tahun) + draft positioning statement + target role/klien dual-purpose.

Status: Not Started

---

## Module 2 — Achievement Mining & Quantification (XYZ/STAR)

Description: Mayoritas orang menulis daftar tugas, bukan pencapaian — modul ini menggali dampak nyata dari 9 tahun karier dan mengkuantifikasinya, jadi fondasi wajib sebelum CV/LinkedIn ditulis ulang.

Lessons:

* [ ] Kenapa "job duties" tidak menjual — perbedaan task-list vs achievement-list, dan efeknya ke tingkat callback interview
* [ ] XYZ Formula (Laszlo Bock/Google): "Accomplished [X] as measured by [Y] by doing [Z]" — cara membedah tiap project di Raw Material Inventory (Module 1) pakai formula ini
* [ ] Teknik estimasi dampak saat data presisi tidak tersedia (before/after proxy, indikator skala/scope, waktu yang dihemat) — jujur dan bisa dipertanggungjawabkan, bukan mengarang angka
* [ ] Memilah bobot achievement per era karier — 3-5 tahun terakhir dapat porsi lebih besar dibanding awal karier
* [ ] Menyusun Achievement Bank: 15-20 bullet XYZ mentah terkategorikan per role/era, belum dipilih mana yang masuk CV/LinkedIn

Mini Project: Achievement Bank — 15-20 bullet XYZ terkuantifikasi dari riwayat 2017-2026, dikategorikan per role/era.

Status: Not Started

---

## Module 3 — CV/Resume Modern Rewrite (ATS + Human)

Description: CV ditulis ulang total memakai Achievement Bank, dioptimasi lolos ATS 2026 sekaligus tetap enak dibaca manusia.

Lessons:

* [ ] ATS 2026: pergeseran ke skills-based screening (bukan cuma title-based), format one-column wajib, Skills section jadi bagian pertama yang di-parse
* [ ] Keyword tailoring per job description — kenapa CV yang ditailor skor 40-60% lebih tinggi daripada versi generic, dan cara melakukannya tanpa keyword stuffing
* [ ] Menyusun ulang bullet CV pakai Achievement Bank (Module 2): 3-5 bullet XYZ untuk role terbaru, 2-3 untuk role lebih lama
* [ ] Dual-version CV: versi ATS-optimized untuk apply full-time vs versi 1-halaman narrative untuk dibagikan ke network/klien freelance
* [ ] Kesalahan umum 2026 yang kini kena penalti (keyword stuffing, akronim tanpa kepanjangan pertama kali, template dua-kolom) — self-audit checklist sebelum submit

Mini Project: 2 versi CV baru (ATS full-time + narrative freelance) memakai Achievement Bank, ditailor ke minimal 1 job description nyata sebagai uji coba tailoring.

Status: Not Started

---

## Module 4 — LinkedIn Profile as Search Asset

Description: LinkedIn 2026 sudah jadi mesin pencocokan berbasis AI/LLM — profil harus dioptimasi sebagai aset pencarian aktif, bukan sekadar salinan CV yang statis.

Lessons:

* [ ] Perubahan algoritma LinkedIn 2026: AI agent melakukan first-pass sebelum recruiter membaca profil, penilaian holistik semantic relationship antar skill/title/industri
* [ ] Headline formula: [Target Role] | [2-3 Skill Inti] | [1 Proof Point] — memanfaatkan 220 karakter prime search real estate
* [ ] Skills sebagai cluster (bukan keyword stuffing) — kenapa skill tanpa konteks pendukung dibaca "shallow" oleh algoritma, cara menyusun 15-25 skill terpilih dan yang di-pin
* [ ] About & Featured section — menyambungkan ke positioning statement (Module 1) dan Achievement Bank (Module 2) jadi satu narasi
* [ ] Social proof: strategi meminta 2 recommendation solid (1 manager, 1 peer) — profil tanpa rekomendasi dibaca seperti CV tanpa referensi

Mini Project: Profil LinkedIn lengkap live (Headline, About, Skills cluster, Featured, minimal 1 recommendation diminta) selaras dengan CV Module 3.

Status: Not Started

---

## Module 5 — Portfolio & Konsistensi Personal Brand

Description: Menyatukan CV, LinkedIn, dan portfolio jadi satu identitas konsisten — bukan tiga kanal dengan cerita berbeda-beda.

Lessons:

* [ ] Elemen portfolio site personal branding: domain custom, bio ringkas, visual identity konsisten (merujuk, bukan mengulang, artifact design doc/postmortem dari `courses/engineering-culture` sebagai isi portfolio)
* [ ] Menulis project narrative (problem → approach → impact), bukan sekadar daftar tech stack atau tautan repo
* [ ] Audit konsistensi lintas kanal: CV, LinkedIn, portfolio, GitHub — memastikan satu positioning statement yang sama, bukan versi yang berbeda-beda
* [ ] Kesalahan umum yang harus dihindari: over-design tanpa substansi, tidak mobile-friendly, konten basi yang tidak pernah diperbarui
* [ ] Rencana maintenance: kapan portfolio direview ulang (tiap project baru selesai / tiap kuartal)

Mini Project: Portfolio site live (baru atau update signifikan dari yang sudah ada) + Consistency Audit Report (CV vs LinkedIn vs Portfolio vs GitHub, satu narasi yang sama).

Status: Not Started

---

## Module 6 — Networking & Hidden Job Market

Description: 70-85% pekerjaan terisi lewat networking/referral, bukan job board — modul ini membangun sistem networking yang genuinely bermanfaat, bukan transaksional.

Lessons:

* [ ] Data hidden job market: kenapa strategi "apply saja lewat job board" tidak cukup di 2026, dan bagaimana posisi sering terisi sebelum diposting
* [ ] Informational interview: cara reach out yang benar (belajar, bukan minta kerja), pertanyaan yang tepat, follow-up yang tidak mengganggu
* [ ] Weak-tie networking & strategic outreach ke recruiter — kenapa weak ties sering lebih efektif dari strong ties untuk membuka peluang baru
* [ ] Membangun kehadiran "dikenal sebelum posisi ada" — follow target company, engagement genuine (bukan spam komentar generik)
* [ ] Sistem networking berkelanjutan: nurture list, cadence follow-up, tanpa terasa transaksional

Mini Project: Networking Log — daftar 15-20 kontak target (kombinasi weak-tie/recruiter/target company), minimal 3 informational interview/outreach nyata dijalankan dan dicatat hasilnya.

Status: Not Started

---

## Module 7 — Sistem Pencarian Kerja Aktif

Description: Mengubah seluruh aset (CV, LinkedIn, portfolio, network) jadi sistem eksekusi pencarian yang terukur, bukan aktivitas acak tanpa arah.

Lessons:

* [ ] Target company list: 20-50 perusahaan/klien di 3-4 kategori — kenapa pencarian tertarget 3x lebih efektif menghasilkan interview daripada apply massal
* [ ] Application tracker: struktur tracker (company, role, status, tanggal apply, follow-up, kontak) dan disiplin memperbaruinya
* [ ] Cadence realistis: volume aplikasi tertarget (bukan asal banyak), kombinasi apply + referral + outreach langsung per minggu
* [ ] Template outreach & follow-up (recruiter, hiring manager, referral request) yang personal, bukan pesan massal generic
* [ ] Loop review mingguan: metric yang dilacak (response rate, interview rate), sinyal kapan strategi perlu direvisi

Mini Project: Target Company List (20-50 entri) + Application Tracker aktif + minimal 5 outreach/aplikasi tertarget nyata dijalankan dan dicatat di tracker.

Status: Not Started

---

## Module 8 — Capstone: Career Reinvention Portfolio

Description: Menyatukan seluruh 7 modul sebelumnya jadi satu sistem karier yang hidup dan sudah mulai dieksekusi — bukan sekadar dokumen rencana di atas kertas.

Lessons:

* [ ] Menyatukan seluruh output Module 1-7: positioning statement final, Achievement Bank, CV (2 versi), LinkedIn live, portfolio live, Networking Log, Target Company List + Tracker
* [ ] Konsistensi cross-check: memastikan satu narasi yang sama di semua kanal, tidak ada kontradiksi
* [ ] Eksekusi tahap awal nyata: minimal 5 aplikasi/outreach tertarget sudah berjalan (bukan cuma rencana), minimal 1 informational interview sudah terjadi
* [ ] Rencana review berkala: kapan seluruh sistem ini direvisi (tiap dapat feedback interview, tiap kuartal)
* [ ] Refleksi: bagaimana sistem ini dipakai berdampingan dengan `courses/credential-strategy` (kredensial) dan `courses/engineering-culture` (craft) untuk membentuk profil kompetensi lengkap ke pemberi kerja maupun klien freelance internasional

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Career Reinvention Portfolio** — sistem karier lengkap (CV dual-version + LinkedIn live + portfolio live + Achievement Bank + Networking Log + Target Company List/Tracker) dengan bukti eksekusi tahap awal nyata, siap dipakai untuk pencarian full-time maupun freelance.

Acceptance Criteria:

* [ ] Career Timeline Audit + positioning statement + target dual-purpose (Module 1) selesai
* [ ] Achievement Bank berisi 15-20 bullet XYZ terkuantifikasi (Module 2) selesai
* [ ] 2 versi CV (ATS full-time + narrative freelance), sudah ditailor ke minimal 1 job description nyata (Module 3)
* [ ] Profil LinkedIn lengkap live dengan minimal 1 recommendation (Module 4)
* [ ] Portfolio site live + Consistency Audit Report lintas kanal (Module 5)
* [ ] Networking Log dengan minimal 3 informational interview/outreach nyata (Module 6)
* [ ] Target Company List (20-50 entri) + Application Tracker aktif dengan minimal 5 outreach/aplikasi tertarget nyata (Module 7)
* [ ] Seluruh dokumen konsisten satu sama lain (narasi/positioning tidak saling bertentangan), disimpan ke `portofolio/`

Status: Not Started

---

# Overall Progress

Module Completion: 0/8

Overall Completion: 0%

Current Module: -

Current Lesson: -

Next Lesson: Menunggu approval roadmap ini dari user sebelum mulai Module 1, Lesson 1
