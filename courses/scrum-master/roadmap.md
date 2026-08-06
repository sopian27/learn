# Course Roadmap

## Course Information

* Course Name: Scrum Master — dari 0 sampai Expert (Agile Leadership & Team Facilitation)
* Category: Agile Project Management / Scrum Master — course baru, terpisah dari `courses/system-design` per keputusan kalibrasi Anda (2026-08-06)
* Difficulty: Level 0 (belum pernah menjalankan Scrum secara sadar, hanya "ikut ritual" di tim) → Level 4 (bisa memfasilitasi tim Scrum penuh secara mandiri, coaching tim yang disfungsional, dan siap ujian PSM I/II)
* Estimated Duration: 12 modul, 3-5 lesson per modul (20-45 menit/lesson). Perkiraan ≈ 3-5 bulan dengan pace santai (course ini non-coding, jadi bisa dicicil paralel dengan course teknis lain tanpa berebut energi yang sama).
* Prerequisites: Tidak ada prasyarat teknis. Prasyarat yang membantu: pernah bekerja di tim software (Anda sudah punya ini sebagai Java/Spring Boot developer) — pengalaman sebagai anggota tim Scrum akan sering dipakai sebagai bahan refleksi ("bagaimana tim Anda menjalankan Daily Scrum selama ini, dan apa yang sebenarnya salah").

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda (2026-08-06):

1. **Course terpisah**: Sesuai pilihan Anda, ini course mandiri dengan roadmap dan progress tracking sendiri — bukan digabung ke `courses/system-design`. Alasan repo: dua topik ini punya skill tree yang sangat berbeda (satu teknis-arsitektural, satu people/process) sehingga pace dan gaya belajarnya juga akan berbeda.
2. **Posisi terhadap Master Goal**: `progress/roadmap.md` (`World-Class Backend Developer — freelance internasional`) sebelumnya tidak punya slot eksplisit untuk Scrum Master — course ini melengkapi sisi "bisa memimpin tim/proyek", bukan cuma "bisa coding sendirian". Ini relevan langsung untuk freelance: klien internasional (terutama di Upwork/Toptal skala tim) sering menghargai kontraktor yang paham cara kerja Agile klien, bahkan kalau peran formalnya bukan Scrum Master.
3. **Non-coding, jalur paralel**: Course ini dirancang **tidak bersaing energi** dengan course coding berat (`backend-fundamentals`, `system-design`) — cocok dijalankan berdampingan, misalnya 1 lesson Scrum Master setelah sesi ngoding berat, atau di hari yang lebih santai.
4. **Bukan sekadar hafalan Scrum Guide**: setiap event/artifact Scrum diajarkan dengan skenario tim yang realistis (termasuk skenario disfungsional), karena tantangan nyata Scrum Master bukan "tahu definisinya" tapi "tahu harus berbuat apa saat kenyataan tidak sesuai buku".

Referensi riset yang dipakai menyusun roadmap ini: *The 2020 Scrum Guide* (Ken Schwaber & Jeff Sutherland, [scrum.org](https://www.scrum.org)) sebagai sumber primer & satu-satunya otoritas definisi Scrum, [Scrum.org Scrum Master Learning Path](https://www.scrum.org/pathway/scrum-master) & panduan resmi persiapan [PSM I](https://www.scrum.org/resources/blog/how-pass-psm-i-assessment-updated-scrum-guide-2020)/PSM II untuk struktur sertifikasi, *Agile Manifesto* & 12 Principles ([agilemanifesto.org](https://agilemanifesto.org)) untuk fondasi filosofis, *Coaching Agile Teams* (Lyssa Adkins) untuk pilar coaching/fasilitasi, *Agile Estimating and Planning* & *Succeeding with Agile* (Mike Cohn) untuk pilar backlog/estimasi, *The Five Dysfunctions of a Team* (Patrick Lencioni) untuk pilar tim disfungsional, *Kanban* (David J. Anderson) untuk pilar flow-based agile, serta lanskap scaling framework (SAFe, LeSS, Nexus, Scrum@Scale) untuk pilar enterprise. Template `templates/JIRA_TASK_TEMPLATE.md` yang sudah ada di repo ini dipakai langsung di Module 11 (Tooling).

---

# Learning Objectives

* [ ] Memahami Agile Manifesto & 12 Principles secara mendalam — bukan hafalan, tapi bisa menjelaskan *kenapa* tiap prinsip ada dan kapan Agile justru bukan pilihan tepat
* [ ] Menguasai Scrum Guide 2020 secara menyeluruh: 3 akuntabilitas (Scrum Master, Product Owner, Developers), 5 event, 3 artifact + 3 commitment
* [ ] Memahami peran Scrum Master secara benar: servant leader, 4 stance (Facilitator, Teacher, Mentor, Coach) — dan bedanya secara eksplisit dengan Project Manager
* [ ] Mampu memfasilitasi kelima event Scrum secara efektif, termasuk mengenali & memperbaiki anti-pattern di tiap event
* [ ] Menulis & mengelola Product Backlog: user story dengan kriteria INVEST, teknik splitting, estimasi (Planning Poker, story point, velocity)
* [ ] Menguasai skill coaching & fasilitasi: powerful questions, active listening, resolusi konflik, mengenali & menangani tim yang disfungsional (5 Dysfunctions)
* [ ] Menggunakan metrik Agile secara benar (velocity, burndown/burnup, cumulative flow, cycle time) untuk mendorong perbaikan berkelanjutan — bukan untuk mengejar angka
* [ ] Memahami Kanban sebagai alternatif/pelengkap Scrum: WIP limit, pull system, kapan flow-based lebih tepat daripada sprint-based
* [ ] Memahami lanskap scaling framework (SAFe, LeSS, Nexus, Scrum@Scale) secukupnya untuk tahu kapan (dan kapan tidak) dibutuhkan
* [ ] Mampu bekerja dengan stakeholder/manajemen dalam konteks transformasi Agile, mengenali anti-pattern enterprise ("Agile theater", "Zombie Scrum")
* [ ] Menjalankan siklus sprint penuh di tooling nyata (Jira atau setara) dari backlog sampai retrospective
* [ ] Siap ujian PSM I (dan paham jalur ke PSM II/III), siap wawancara untuk peran Scrum Master/Agile lead

---

# Course Modules

## Module 1 — Fondasi Agile

Description: Titik awal — memahami *kenapa* Agile lahir sebelum masuk ke *bagaimana* Scrum bekerja. Tanpa modul ini, Scrum mudah jadi "ritual kosong" yang dijalankan tanpa paham tujuannya.

Lessons:

* [ ] Sejarah singkat: dari Waterfall ke Agile Manifesto (2001) — masalah nyata yang coba diselesaikan, bukan sekadar tren
* [ ] Agile Manifesto: 4 nilai inti, dan kesalahpahaman paling umum tentang masing-masing nilai
* [ ] 12 Principles di balik manifesto — dan pemetaan tiap prinsip ke praktik nyata di tim software
* [ ] Peta keluarga metodologi Agile: Scrum, Extreme Programming (XP), Kanban, Lean — persamaan & perbedaan filosofisnya
* [ ] Kapan Agile *bukan* pilihan tepat: proyek dengan requirement benar-benar tetap & regulasi ketat (kontraktor pemerintah, aerospace) — supaya tidak jadi "Agile untuk semua hal"

Mini Project: Refleksi tertulis — bandingkan cara tim Anda saat ini bekerja dengan 4 nilai Agile Manifesto, identifikasi 3 titik gap konkret (bukan teoretis).

Status: Not Started

---

## Module 2 — Scrum Framework Fundamentals (Scrum Guide 2020)

Description: Membaca dan memahami Scrum Guide resmi secara mendalam — sumber tunggal kebenaran untuk seluruh course ini, karena banyak kesalahpahaman Scrum berasal dari "kata orang", bukan dari guide aslinya.

Lessons:

* [ ] Scrum Values: Commitment, Focus, Openness, Respect, Courage — kenapa ini bukan poster dinding, tapi fondasi perilaku tim
* [ ] 3 Akuntabilitas: Scrum Master, Product Owner, Developers — kenapa versi 2020 menghapus istilah "Team" berjenjang dan apa maknanya
* [ ] 3 Artifact: Product Backlog, Sprint Backlog, Increment — dan 3 Commitment yang menyertainya: Product Goal, Sprint Goal, Definition of Done
* [ ] 5 Event: Sprint sebagai "container" untuk Sprint Planning, Daily Scrum, Sprint Review, Sprint Retrospective
* [ ] Perubahan kunci Scrum Guide 2020 vs versi sebelumnya (2017) — untuk yang sudah pernah dengar Scrum versi lama dari sumber usang

Mini Project: Baca Scrum Guide resmi (13 halaman) end-to-end, lalu buat cheat sheet 1 halaman versi Anda sendiri (bukan salin-tempel) yang memetakan tiap elemen ke pengalaman tim Anda saat ini.

Status: Not Started

---

## Module 3 — Peran Scrum Master Secara Mendalam

Description: Modul inti course ini — apa sebenarnya pekerjaan seorang Scrum Master, kenapa sering disalahpahami sebagai "Project Manager versi Agile", dan bagaimana menjalankan peran servant-leadership yang sebenarnya.

Lessons:

* [ ] Servant Leadership: melayani tim, Product Owner, dan organisasi — bukan mengatur/mendelegasikan tugas
* [ ] 4 Stance Scrum Master (per Scrum.org): Facilitator, Teacher, Mentor, Coach — kapan tiap stance dipakai
* [ ] Scrum Master vs Project Manager: perbedaan tanggung jawab eksplisit (siapa yang menentukan *apa* yang dikerjakan vs siapa yang memfasilitasi *bagaimana* tim bekerja)
* [ ] Menghilangkan impediment: cara mengenali impediment nyata (bukan cuma keluhan), eskalasi yang tepat saat impediment di luar kendali tim
* [ ] Melindungi tim: dari gangguan eksternal, dari komitmen berlebihan (over-commitment), tanpa membuat tim terisolasi dari stakeholder

Mini Project: Tulis 3 skenario nyata (atau hipotetis dari pengalaman tim Anda) di mana peran Scrum Master dan Project Manager akan mengambil tindakan berbeda — jelaskan tindakan Scrum Master dan alasannya.

Status: Not Started

---

## Module 4 — Memfasilitasi Event Scrum

Description: Dari teori Module 2 ke praktik nyata memfasilitasi — termasuk mengenali dan memperbaiki anti-pattern yang hampir pasti sudah pernah Anda lihat di tim manapun.

Lessons:

* [ ] Sprint Planning: menentukan Sprint Goal dulu (bukan asal ambil item backlog), teknik capacity planning yang realistis
* [ ] Daily Scrum: tujuan sebenarnya (inspect progress ke Sprint Goal), kenapa "status report bergiliran" adalah anti-pattern paling umum
* [ ] Sprint Review: bukan sekadar demo — cara melibatkan stakeholder untuk inspect Increment dan menyesuaikan Product Backlog
* [ ] Sprint Retrospective: format-format praktis (Start-Stop-Continue, 4Ls, Sailboat, Mad-Sad-Glad) dan kapan tiap format cocok
* [ ] Backlog Refinement: event "tidak resmi" tapi krusial — kapan dan seberapa sering dilakukan, siapa yang wajib hadir

Mini Project: Pilih 1 event Scrum yang menurut Anda paling buruk dijalankan di tim Anda saat ini — rancang ulang agenda & fasilitasi event tersebut lengkap dengan anti-pattern spesifik yang ingin diperbaiki.

Status: Not Started

---

## Module 5 — Product Backlog & Estimasi

Description: Bekerja sama dengan Product Owner (bukan menggantikannya) untuk menjaga backlog tetap sehat — keterampilan yang membuat Sprint Planning berjalan mulus atau berantakan.

Lessons:

* [ ] User Story: format "As a... I want... So that...", kriteria INVEST (Independent, Negotiable, Valuable, Estimable, Small, Testable)
* [ ] Story Splitting: teknik memecah story besar (epic) jadi story kecil yang tetap valuable — pola SPIDR (Spike, Path, Interface, Data, Rules)
* [ ] Estimasi: Planning Poker & story point (estimasi relatif, bukan jam), T-shirt sizing untuk estimasi kasar, pengantar #NoEstimates sebagai perspektif alternatif
* [ ] Velocity: cara menghitung dan menggunakannya untuk forecasting — kenapa membandingkan velocity antar-tim adalah anti-pattern
* [ ] Definition of Ready vs Definition of Done: perbedaan tujuannya, dan risiko membuat DoR terlalu kaku (jadi mini-waterfall)

Mini Project: Ambil 1 fitur nyata (dari kerjaan Anda atau proyek portofolio course lain) — pecah jadi epic → user story yang memenuhi INVEST, lalu lakukan estimasi Planning Poker (simulasi solo dengan penjelasan tiap angka).

Status: Not Started

---

## Module 6 — Coaching & Fasilitasi Tim

Description: Modul "soft skill" yang paling menentukan efektivitas Scrum Master di dunia nyata — teori Module 3-4 tidak berguna kalau tidak bisa difasilitasi dengan baik ke manusia sungguhan.

Lessons:

* [ ] Coaching vs Mentoring vs Teaching vs Facilitating: 4 mode bantuan yang berbeda (per Scrum.org), kapan memilih yang mana
* [ ] Powerful Questions & Active Listening: teknik bertanya yang membuat tim menemukan jawaban sendiri, bukan didikte
* [ ] Resolusi konflik dalam tim: model Thomas-Kilmann (competing, collaborating, compromising, avoiding, accommodating) — kapan tiap gaya tepat
* [ ] 5 Dysfunctions of a Team (Lencioni): absence of trust, fear of conflict, lack of commitment, avoidance of accountability, inattention to results — cara mengenali gejalanya
* [ ] Menangani tim disfungsional nyata: silent team di Daily Scrum, satu anggota mendominasi, tim yang "setuju" tapi diam-diam tidak sepakat

Mini Project: Diberi 3 skenario tim disfungsional (silent team, dominator, hidden disagreement) — tulis rencana intervensi konkret untuk tiap skenario menggunakan teknik dari lesson di atas.

Status: Not Started

---

## Module 7 — Metrik Agile & Continuous Improvement

Description: Mengubah retrospective dari sekadar "curhat mingguan" jadi keputusan berbasis data — sekaligus menghindari jebakan klasik "metrik jadi target, bukan alat ukur" (Goodhart's Law).

Lessons:

* [ ] Velocity vs Throughput: kapan tiap metrik lebih relevan, kenapa velocity gampang dimanipulasi ("velocity gaming")
* [ ] Burndown Chart vs Burnup Chart: cara membaca tren, kapan Sprint Goal berisiko tidak tercapai dari tengah sprint
* [ ] Cumulative Flow Diagram (CFD): mendeteksi bottleneck di alur kerja tim secara visual
* [ ] Cycle Time & Lead Time: metrik flow-based (jembatan ke Module 8/Kanban) — lebih jujur daripada velocity untuk prediktabilitas
* [ ] Team health metrics: happiness index, survei tim sederhana — mengukur hal yang tidak terlihat di burndown chart

Mini Project: Diberi data sprint fiktif (velocity 5 sprint terakhir, CFD) — identifikasi 1 masalah tersembunyi yang tidak terlihat kalau hanya melihat velocity saja, lalu rancang agenda retrospective yang menyasar masalah itu.

Status: Not Started

---

## Module 8 — Kanban & Flow-Based Agile

Description: Alternatif (atau pelengkap) Scrum yang sering diremehkan — penting dipahami karena tidak semua tim/konteks kerja cocok dengan ritme sprint tetap.

Lessons:

* [ ] Prinsip Kanban: mulai dari cara kerja saat ini (start where you are), perubahan evolusioner, visualisasi alur kerja
* [ ] WIP Limit & Pull System: kenapa membatasi kerja paralel justru mempercepat penyelesaian (Little's Law secara intuitif)
* [ ] Kanban Board yang benar vs "papan Trello asal-asalan": kolom yang merepresentasikan alur kerja nyata, bukan sekadar To Do/Doing/Done
* [ ] Kanban vs Scrum vs Scrumban: kapan flow-based (tanpa sprint tetap) lebih cocok daripada time-boxed (mis. tim support/maintenance vs tim produk)
* [ ] Service Level Expectation (SLE) sebagai pengganti estimasi tradisional di Kanban murni

Mini Project: Untuk 1 tim/konteks kerja yang Anda kenal (bisa tim support, bisa hipotetis), tentukan apakah Scrum, Kanban, atau Scrumban paling cocok — dengan justifikasi berbasis karakteristik kerja tim tersebut (predictable vs unpredictable, prioritas berubah cepat atau tidak).

Status: Not Started

---

## Module 9 — Scaling Agile

Description: Begitu satu tim Scrum tidak cukup (produk besar, banyak tim saling bergantung) — lanskap framework scaling secukupnya untuk tahu kapan (dan kapan tidak) dibutuhkan.

Lessons:

* [ ] Kenapa scaling itu sulit: dependency antar-tim, shared component, integrasi rilis — masalah yang tidak muncul di satu tim Scrum biasa
* [ ] Scrum of Scrums & Nexus: pendekatan scaling paling minimal, cocok untuk 3-9 tim
* [ ] LeSS (Large-Scale Scrum): filosofi "lebih banyak Scrum, bukan lebih rumit" — perbandingan dengan pendekatan lain
* [ ] SAFe (Scaled Agile Framework): overview level tinggi (ART, PI Planning) — kenapa ini paling populer di enterprise sekaligus paling dikritik ("terlalu Waterfall dengan baju Agile")
* [ ] Spotify Model sebagai studi kasus (bukan framework resmi): squad/tribe/chapter/guild, dan kenapa Spotify sendiri sudah tidak memakainya persis seperti itu lagi

Mini Project: Diberi skenario 5 tim yang mengerjakan 1 produk dengan banyak dependency lintas tim — rekomendasikan pendekatan scaling (atau rekomendasikan *tidak* scaling formal, cukup koordinasi ringan) dengan justifikasi.

Status: Not Started

---

## Module 10 — Agile di Level Enterprise

Description: Realita yang jarang dibahas buku Scrum — bekerja dengan manajemen, stakeholder, dan organisasi yang belum (atau tidak sepenuhnya) Agile.

Lessons:

* [ ] Bekerja dengan stakeholder & manajemen: melindungi tim tanpa mengisolasi tim dari informasi bisnis penting
* [ ] Tantangan transformasi Agile: kenapa banyak transformasi gagal (top-down mandate tanpa perubahan budaya), change management dasar
* [ ] Tim distributed/remote: adaptasi event Scrum untuk tim lintas zona waktu — relevan langsung untuk konteks freelance internasional Anda
* [ ] Anti-pattern enterprise: "Agile theater" (ritual tanpa substansi), "Zombie Scrum" (Scrum berjalan tapi tidak menghasilkan value), "Water-Scrum-Fall"
* [ ] Peran Scrum Master sebagai agen perubahan organisasi (bukan cuma fasilitator satu tim) — batas realistis pengaruh yang bisa dicapai

Mini Project: Tulis analisis singkat: identifikasi 2 tanda "Zombie Scrum" atau "Agile theater" yang paling mungkin muncul di tim/organisasi Anda saat ini, dan 1 langkah konkret yang realistis untuk seorang Scrum Master (bukan CEO) untuk mulai memperbaikinya.

Status: Not Started

---

## Module 11 — Tooling dalam Praktik

Description: Menjalankan semua teori di atas dengan tool nyata — memakai `templates/JIRA_TASK_TEMPLATE.md` yang sudah ada di repo ini sebagai jembatan langsung ke praktik.

Lessons:

* [ ] Jira untuk Scrum: setup board, backlog, sprint — pemetaan konsep Scrum Guide ke fitur Jira secara eksplisit
* [ ] Menulis tiket kerja yang baik memakai `templates/JIRA_TASK_TEMPLATE.md` — menyambungkan format user story/task ke template yang sudah ada di repo ini
* [ ] Tool lain sekilas: Azure DevOps, Linear, physical/digital Kanban board — kapan tool sederhana lebih baik daripada Jira yang berat
* [ ] Report & dashboard di tool: burndown otomatis, velocity chart — dan jebakan mempercayai angka tanpa konteks
* [ ] Menjalankan 1 siklus sprint penuh di tooling: backlog refinement → sprint planning → daily tracking → review → retrospective, end-to-end

Mini Project: Setup 1 board (Jira atau tool gratis setara) untuk sebuah proyek fiktif/nyata, isi backlog dengan minimal 10 item memakai `templates/JIRA_TASK_TEMPLATE.md`, jalankan simulasi 1 sprint penuh dari planning sampai retrospective.

Status: Not Started

---

## Module 12 — Persiapan Sertifikasi & Capstone

Description: Modul penutup — mengonsolidasi seluruh course jadi kesiapan ujian dan wawancara nyata, plus 1 simulasi penuh sebagai bukti kompetensi.

Lessons:

* [ ] Format & struktur ujian PSM I: jenis soal, area yang paling sering jadi jebakan (istilah lama vs Scrum Guide 2020), strategi belajar
* [ ] Latihan soal PSM I bergaya skenario (bukan hafalan definisi) — minimal 20 soal simulasi dengan pembahasan
* [ ] Jalur lanjutan: PSM II (fokus servant leadership & fasilitasi tingkat lanjut) dan PSM III — kapan layak dikejar
* [ ] Pertanyaan wawancara Scrum Master/Agile Lead: pertanyaan situasional ("ceritakan saat Anda menangani tim disfungsional") dan cara menjawab dengan STAR method
* [ ] Review capstone: simulasikan diri Anda sebagai Scrum Master baru di tim yang Anda kenal — presentasikan rencana 30-60-90 hari pertama

Portfolio Project: Simulasikan 1 siklus sprint penuh (menggunakan board Module 11) sebagai Scrum Master untuk sebuah tim/proyek — hasilkan artefak nyata: Sprint Goal, hasil Planning Poker, catatan Daily Scrum 5 hari (bisa disimulasikan), catatan Sprint Review, dan hasil Retrospective dengan action item konkret. Disimpan sebagai bukti kompetensi portofolio.

Status: Not Started

---

# Enterprise Project

Description: Simulasi penuh siklus Scrum Master di Module 12 — bukan proyek koding, tapi bukti kompetensi praktis: mampu merancang, memfasilitasi, dan mendokumentasikan satu siklus sprint end-to-end dengan artefak yang bisa ditunjukkan ke klien/interviewer.

Acceptance Criteria:

* [ ] Sprint Goal yang jelas dan Product Backlog yang memenuhi kriteria INVEST
* [ ] Hasil estimasi (Planning Poker) dengan justifikasi
* [ ] Catatan fasilitasi kelima event Scrum, termasuk minimal 1 anti-pattern yang diidentifikasi dan diperbaiki
* [ ] Hasil Retrospective dengan action item konkret dan terukur
* [ ] Rencana 30-60-90 hari sebagai Scrum Master baru di tim nyata/hipotetis

Status: Not Started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: -

Current Lesson: -

Next Lesson: Menunggu approval roadmap ini dari user sebelum mulai Module 1, Lesson 1
