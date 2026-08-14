# Course Roadmap

## Course Information

* Course Name: Brain Mastery — Integrator Fokus, Deep Work, Kreativitas, Membaca & Metakognisi (dari 0 sampai Expert)
* Category: Brain Mastery — Cognitive Performance Integrator. Domain: **reuse** `learning-science` (lihat `domains/learning-science/DOMAIN.md`) — course ini TIDAK membuat domain baru. Alasan lengkap ada di "Catatan Scope" di bawah.
* Difficulty: Level 0 (fokus/atensi mudah buyar, deep work belum jadi kebiasaan sadar, "kreativitas" terasa bakat bawaan bukan skill, membaca linear tanpa strategi, tidak sadar kapan diri sendiri "merasa paham" padahal belum) → Level 3 (punya Personal Brain Mastery Operating System yang menggabungkan skill dari course ini dengan output 3 course lain yang sudah/sedang berjalan)
* Estimated Duration: 7 modul (lebih tipis dari course lain di repo ini secara sengaja), ~27 lesson total, pace mengikuti Applied Practice nyata (bukan dikebut)
* Prerequisites: Tidak ada prasyarat teknis. **Sangat direkomendasikan** sudah mengenal atau sedang menjalankan minimal satu dari `courses/memory-mastery`, `courses/learning-science`, `courses/critical-thinking` — course ini secara eksplisit dibangun DI ATAS ketiganya, bukan menggantikannya. Modul 1 akan memandu urutan yang masuk akal kalau ketiganya belum pernah disentuh.

---

# Catatan Scope (baca dulu sebelum approve — ini bagian paling penting)

## Kenapa course ini tipis, dan kenapa TIDAK jadi domain baru

Permintaan awal user ("Brain Mastery": fokus, memori, belajar cepat, critical thinking, problem solving, kreativitas, decision-making, membaca, deep work, metacognition, mental performance) di-cross-check dulu ke 3 domain/course yang SUDAH ada di repo ini sebelum satu baris modul pun ditulis: `domains/memory-mastery`, `domains/learning-science`, `domains/critical-thinking`. Hasilnya: **7 dari 11 sub-topik sudah tercover penuh atau sebagian besar** oleh ketiga course itu — dengan kualitas riset (evidence hierarchy Dunlosky, SDT Deci-Ryan, Kahneman, dst.) yang kalau ditulis ulang di sini pasti lebih dangkal karena harus dipadatkan ulang dari nol. Menulis ulang itu akan:

1. Melanggar semangat root `CLAUDE.md` ("Never optimize for finishing quickly. Optimize for deep understanding.") — duplikat dangkal bukan deep understanding.
2. Memecah Applied Practice — user akan punya 2 Anki deck / 2 Thinking Log terpisah untuk skill yang sama, membingungkan bukan membantu.
3. Berisiko drift — kalau course baru dan course lama sama-sama mengajarkan "active recall" dengan kata-kata beda, salah satu pasti jadi usang duluan.

**Keputusan: Opsi (b)** — course tipis sebagai index/integrator, isi HANYA untuk gap asli (bukan reteach), plus satu modul integrator metakognisi, plus capstone yang menjahit semuanya. **Bukan opsi (a)** (tidak buat course sama sekali) karena setelah dipetakan ternyata ADA gap asli yang cukup besar (fokus, deep work, kreativitas, membaca) yang tidak tercover di manapun — kalau tidak dibuatkan wadah, 4 sub-topik ini akan menggantung tanpa rumah.

**Kenapa reuse domain `learning-science`, bukan domain baru:** Empat gap asli (fokus/attention, deep work, kreativitas, membaca) semuanya adalah skill psikologis-kognitif terapan yang diukur lewat self-report + log penerapan nyata — persis pola Applied Practice/Review Style/Assessment Form `domains/learning-science/DOMAIN.md` (rating before-after, evidence-over-hype check, sustainability check), BUKAN pola evidence-hierarchy-retention-score `memory-mastery` atau pola Thinking-Decision-Log rigor `critical-thinking`. Tidak cukup kuat alasan untuk domain baru — `templates/DOMAIN_TEMPLATE.md` mensyaratkan scope yang jelas TIDAK overlap dengan domain existing, dan gap asli di sini secara natural adalah perluasan kecil dari `learning-science` (yang sudah bicara flow/attention-adjacent di Module 3, environment design di Module 9.4), bukan bidang yang sama sekali berbeda. Domain baru baru masuk akal kalau nanti ada topik yang benar-benar tidak nyambung ke satupun domain existing.

## Pemetaan 11 sub-topik user

| # | Sub-topik user | Status | Keterangan |
|---|---|---|---|
| 1 | **Fokus** (attention) | GAP → Modul 2 | Disinggung sekilas di `learning-science` Module 3 (flow: skill-challenge balance) & 9.4 (environment design), tapi attention/sustained-focus sebagai skill eksplisit (attention residue, distraction management berbasis riset) belum diajarkan di manapun. |
| 2 | **Memori** | Sudah tercover PENUH | `courses/memory-mastery` (10 modul: active recall, spacing, Anki, elaboration, Feynman, deliberate practice, tidur). Tidak diulang. |
| 3 | **Belajar cepat** | Sudah tercover (gabungan) | = retensi (`memory-mastery`) + konsistensi/motivasi (`learning-science`). Tidak ada "hack kecepatan" terpisah — klaim speed-learning/speed-reading tanpa dasar riset justru DITOLAK oleh house rule `memory-mastery` (lihat baris 8). |
| 4 | **Critical thinking** | Sudah tercover PENUH | `courses/critical-thinking` Module 1-2. |
| 5 | **Problem solving** | Sudah tercover PENUH | `courses/critical-thinking` Module 3 (root cause) & Module 7 (systems thinking). |
| 6 | **Kreativitas** | GAP → Modul 4 | **Diakui eksplisit sebagai gap oleh `domains/critical-thinking/DOMAIN.md` sendiri**: "Kreativitas/ideation murni... domain ini fokus pada evaluasi/convergent thinking... Kandidat domain terpisah `creative-thinking` di masa depan." Course ini mengisi sisi divergent itu, lalu menyambung balik ke critical-thinking untuk evaluasi (convergent). |
| 7 | **Decision-making** | Sudah tercover PENUH | `courses/critical-thinking` Module 4 (ketidakpastian/risiko) & Module 5 (bias/kalibrasi). |
| 8 | **Membaca** | GAP → Modul 5 | Tidak diajarkan di manapun. Hati-hati: `domains/memory-mastery/DOMAIN.md` house rule sudah menolak klaim speed-reading ekstrem — modul ini WAJIB konsisten, fokus ke active reading berbasis riset (bukan wpm tinggi). |
| 9 | **Deep work** | GAP → Modul 3 | Bersinggungan dengan `learning-science` Module 9 (energy management/environment design/anti-burnout) tapi belum ada sebagai skill eksplisit bernama (scheduling philosophy, shutdown ritual). Modul ini eksplisit menyambung balik ke Module 9 `learning-science`, tidak mengulang energy-management-nya dari nol. |
| 10 | **Metacognition** | Bukan gap murni → Modul 6 (integrator) | Sudah TERSEBAR sebagai bagian dari teknik lain: Feynman gap-detection = monitoring (`memory-mastery` 6.2), prediction tracking/kalibrasi = evaluating (`critical-thinking` 5.4), self-mapping pengalaman belajar = planning (`learning-science` 1.1). Modul 6 course ini TIDAK mengajarkan dari nol — menyatukan ketiganya jadi satu kerangka eksplisit "thinking about thinking" yang sebelumnya cuma tersirat. |
| 11 | **Mental performance** | DIKELUARKAN dari scope | Istilah ini terlalu luas (nutrisi, olahraga, suplemen/nootropics) dan condong ke wilayah kesehatan fisik yang di luar filosofi non-medis repo ini (lihat pola eskalasi-ke-profesional di semua domain terkait). Komponen yang relevan & sudah tervalidasi riset — tidur (`memory-mastery` Module 8) dan energy management (`learning-science` Module 9) — sudah ada. Kalau user nanti benar-benar ingin modul nutrisi/olahraga untuk kognisi, itu domain terpisah (mis. `cognitive-health`) di luar cakupan course ini. |

**Ringkasan:** 4 gap asli (fokus, deep work, kreativitas, membaca) dapat modul baru. Metakognisi dapat 1 modul integrator (bukan reteach). Sisanya murni index/cross-reference. Course ditutup 1 modul orientasi (Modul 1) dan 1 capstone (Modul 7).

## Trusted Sources / Research Priority (khusus untuk 4 gap asli + integrator metakognisi)

Sumber untuk memori/motivasi/critical-thinking TIDAK diulang di sini — lihat Trusted Sources di domain masing-masing. Sumber baru yang dipakai HANYA untuk Modul 2-6 course ini:

1. **Gloria Mark (UC Irvine) — riset cost-of-interruption & attention residue, peer-reviewed** — dasar ilmiah Modul 2 (fokus): kenapa gangguan kecil merusak kualitas kerja jauh lebih besar dari durasi gangguannya sendiri.
2. **Sophie Leroy — "Why is it so hard to do my work?" (2009), attention residue, peer-reviewed** — dasar ilmiah kenapa berpindah tugas menyisakan "residu" perhatian yang menurunkan performa tugas berikutnya.
3. **Amishi Jha (University of Miami) — riset mindfulness-based attention training, peer-reviewed** — dasar ilmiah latihan sustained attention yang bisa dilatih, bukan trait tetap.
4. **Cal Newport — *Deep Work* (2016)** — sintesis populer (bukan peer-reviewed murni) untuk kerangka Modul 3; wajib disilangkan ke sumber 1-3 di atas sebelum diajarkan sebagai fakta, mengikuti pola "Vetting a Source" `standards/UNIVERSAL_STANDARDS.md`.
5. **J.P. Guilford — riset divergent thinking, peer-reviewed** — dasar ilmiah pembeda divergent vs convergent thinking untuk Modul 4 (kreativitas).
6. **Teresa Amabile (Harvard Business School) — Componential Theory of Creativity, peer-reviewed** — dasar ilmiah bahwa kreativitas = domain expertise + creative-thinking skill + motivasi intrinsik (menyambung ke SDT `learning-science`), bukan bakat bawaan semata.
7. **Mihaly Csikszentmihalyi — *Creativity: Flow and the Psychology of Discovery* (1996)** — sudah jadi Trusted Source `domains/learning-science/DOMAIN.md` untuk riset Flow-nya; di sini dirujuk ulang khusus sisi kreativitasnya, tidak diulang dari nol.
8. **Keith Rayner (UC San Diego) — riset eye-tracking & reading, peer-reviewed** — sumber UTAMA Modul 5 (membaca): dasar ilmiah yang membantah klaim speed-reading ekstrem (batas fisiologis fiksasi mata), konsisten dengan house rule penolakan speed-reading di `domains/memory-mastery/DOMAIN.md`.
9. **Francis P. Robinson — metode SQ3R (Survey-Question-Read-Recite-Review)** — kerangka klasik active reading, teruji puluhan tahun di pendidikan.
10. **John Flavell — riset metakognisi (planning/monitoring/evaluating), peer-reviewed, fondasi bidang metacognition** — sumber UTAMA Modul 6 (metakognisi): kerangka yang dipakai untuk menyatukan ulang Feynman gap-detection, prediction calibration, dan self-mapping dari 3 course lain.

**House rule khusus course ini (mewarisi & menegaskan ulang house rule `memory-mastery`):** klaim speed-reading ekstrem, "unlock kreativitas instan", atau "hack fokus" viral tanpa dasar riset peer-reviewed DITOLAK. Setiap teknik di Modul 2-6 wajib disilangkan ke sumber di atas.

---

# Learning Objectives

* [ ] Memetakan 11 area cognitive performance ke course yang tepat di repo ini, dan tahu urutan belajar lintas 4 course yang masuk akal untuk diri sendiri
* [ ] Menjelaskan attention residue dan menerapkan teknik sustained-attention training berbasis riset (bukan sekadar "matikan notifikasi")
* [ ] Merancang dan menjalankan deep work block terjadwal dengan shutdown ritual, disambungkan ke energy management `learning-science` supaya tidak berujung burnout
* [ ] Membedakan divergent dari convergent thinking, menerapkan teknik ideation berbasis riset, lalu menyambungkannya ke framework evaluasi `critical-thinking`
* [ ] Menerapkan active reading (SQ3R) berbasis riset, sekaligus menolak klaim speed-reading ekstrem yang dibantah riset eye-tracking
* [ ] Menyatukan Feynman gap-detection, prediction calibration, dan self-mapping dari 3 course lain jadi satu kerangka metakognisi eksplisit (planning-monitoring-evaluating)
* [ ] Menghasilkan Personal Brain Mastery Operating System sebagai Capstone Project yang menjahit skill baru course ini dengan output 3 course lain

---

# Course Modules

## Module 1 — Orientasi: Peta Brain Mastery & Cara Memakai Course Ini

Description: Modul index murni — TIDAK mengajarkan skill baru. Tujuannya memetakan ulang permintaan awal ke course yang sudah ada, supaya learner tidak salah mulai (mis. belajar "cara fokus" di sini padahal belum pernah sentuh dasar motivasi/habit di `learning-science`).

Lessons:

* [ ] 1.1 Kenapa course ini TIDAK mengulang `memory-mastery`, `learning-science`, `critical-thinking` — ringkasan tabel pemetaan dari "Catatan Scope" di atas
* [ ] 1.2 Urutan belajar yang direkomendasikan lintas 4 course, tergantung area mana yang paling lemah (pakai hasil self-assessment 1.3)
* [ ] 1.3 Brain Mastery Scorecard — self-rating 1-10 untuk 11 area asli, dipakai untuk personalisasi urutan

Mini Project: Isi Brain Mastery Scorecard pribadi (rating 1-10 tiap area) + rencana urutan belajar tertulis lintas 4 course (termasuk kapan mengaktifkan/melanjutkan `memory-mastery`, `learning-science`, `critical-thinking` yang statusnya masih Paused).

Status: Not started

---

## Module 2 — Fokus & Attention Training

Description: Gap asli #1. Attention sebagai skill yang bisa dilatih — dibedakan dari flow (`learning-science` Module 3, soal keseimbangan skill-challenge) dan dari deep work scheduling (Module 3 course ini, soal blok waktu terjadwal).

Lessons:

* [ ] 2.1 Attention sebagai sumber daya terbatas — attention residue (Leroy) & cost-of-interruption (Gloria Mark)
* [ ] 2.2 Sustained attention training berbasis riset — mindfulness-based attention training (Amishi Jha)
* [ ] 2.3 Digital distraction management berbasis riset (friction design, bukan sekadar niat "matikan notifikasi")
* [ ] 2.4 Kapan lemahnya fokus perlu eskalasi profesional — mirror pola eskalasi ADHD/executive dysfunction di `domains/learning-science/DOMAIN.md`, course ini tidak mendiagnosis

Mini Project: Attention log 1 minggu (catat interupsi & sumbernya), terapkan 1 teknik friction-design, ukur perubahan panjang sesi fokus tanpa distraksi sebelum-sesudah.

Status: Not started

---

## Module 3 — Deep Work sebagai Skill Eksplisit

Description: Gap asli #2. Kerangka Cal Newport, disilangkan ke riset attention residue Modul 2, dan disambungkan eksplisit ke energy management `learning-science` Module 9 supaya jadwal deep work tidak berujung burnout.

Lessons:

* [ ] 3.1 Deep work vs shallow work — kenapa kapasitas fokus tanpa distraksi makin langka & makin bernilai
* [ ] 3.2 Deep work scheduling philosophies (monastic/bimodal/rhythmic/journalistic) — memilih yang realistis untuk ritme hidup sendiri
* [ ] 3.3 Shutdown ritual & batas waktu kerja — mencegah shallow work menggerus jam deep work
* [ ] 3.4 Menyambung ke `learning-science` Module 9 (energy management/anti-burnout) — deep work schedule wajib dicek lawan sinyal burnout, tidak ditambahkan begitu saja di atas jadwal yang sudah padat

Mini Project: Jadwalkan 1 deep work block (60-90 menit tanpa distraksi) untuk materi dari course aktif lain, jalankan 3x dalam seminggu dengan shutdown ritual, catat kualitas fokus dibanding sesi kerja biasa.

Status: Not started

---

## Module 4 — Kreativitas: Divergent Thinking & Ideation

Description: Gap asli #3, diakui eksplisit oleh `domains/critical-thinking/DOMAIN.md` sendiri sebagai di luar cakupannya. Modul ini mengisi sisi divergent (generate ide), lalu WAJIB menyambung balik ke `critical-thinking` untuk sisi convergent (evaluasi ide) — course ini tidak mengajarkan evaluasi argumen dari nol.

Lessons:

* [ ] 4.1 Divergent vs convergent thinking (Guilford) — kenapa dua mode ini butuh kondisi mental berbeda dan tidak efektif dilakukan bersamaan
* [ ] 4.2 Componential Theory of Creativity (Amabile) — domain expertise + creative-thinking skill + motivasi intrinsik (menyambung ke SDT `learning-science`)
* [ ] 4.3 Teknik ideation berbasis riset: brainstorming terstruktur, SCAMPER, analogical thinking — disilangkan ke sumber 5-7, bukan sekadar hack populer
* [ ] 4.4 Alur lengkap generate-lalu-evaluate: divergent session dulu (modul ini) → convergent evaluation pakai Elements of Thought/bias check (rujuk `courses/critical-thinking` Module 2 & 5)

Mini Project: 1 sesi brainstorming terstruktur (minimal 20 ide mentah) untuk 1 masalah nyata, lalu evaluasi 3 ide terbaik memakai framework dari `courses/critical-thinking` Module 2.

Status: Not started

---

## Module 5 — Membaca Efektif Berbasis Riset

Description: Gap asli #4. Secara eksplisit menolak klaim speed-reading ekstrem (konsisten dengan house rule `domains/memory-mastery/DOMAIN.md`), fokus ke active reading & comprehension strategy yang terbukti riset.

Lessons:

* [ ] 5.1 Kenapa speed-reading ekstrem (klaim >1000 wpm dengan retensi utuh) dibantah riset eye-tracking (Rayner) — batas fisiologis fiksasi mata
* [ ] 5.2 Active reading: metode SQ3R (Survey-Question-Read-Recite-Review) dan annotation yang efektif
* [ ] 5.3 Menyesuaikan kecepatan baca dengan tujuan — skimming untuk overview vs close reading untuk retensi, bukan satu kecepatan untuk semua teks
* [ ] 5.4 Menyambungkan hasil bacaan ke active recall & elaboration — rujuk `courses/memory-mastery` Module 2 & 5, supaya yang dibaca benar-benar nempel, bukan berhenti di "sudah dibaca"

Mini Project: Baca 1 bab/artikel teknis nyata dengan SQ3R, lalu retrieval self-test — bandingkan skor retensi dengan cara baca biasa (linear reading tanpa strategi) pada teks sejenis.

Status: Not started

---

## Module 6 — Metacognition: Integrasi Monitoring & Kalibrasi

Description: BUKAN skill berdiri sendiri dari nol — modul integrator yang menyatukan potongan metakognisi yang sudah tersebar di 3 course lain jadi satu kerangka eksplisit "thinking about thinking" (Flavell: planning-monitoring-evaluating).

Lessons:

* [ ] 6.1 Apa itu metakognisi (Flavell) — planning, monitoring, evaluating sebagai 3 fase, dan kenapa ini "meta-skill dari meta-skill"
* [ ] 6.2 Memetakan ulang teknik yang sudah dipelajari: Feynman gap-detection (`memory-mastery` 6.2) = monitoring; prediction tracking (`critical-thinking` 5.4) = evaluating/kalibrasi; self-mapping pengalaman belajar (`learning-science` 1.1) = planning
* [ ] 6.3 Illusion of competence lintas domain — kenapa "merasa paham/yakin" sering tidak berkorelasi dengan benar-benar paham/benar (menyatukan fluency illusion `memory-mastery` 1.3 & overconfidence `critical-thinking` 5.1)
* [ ] 6.4 Menyusun 1 metacognitive checklist pribadi, dipakai sebelum/saat/sesudah sesi belajar ATAU sesi mengambil keputusan

Mini Project: Terapkan metacognitive checklist ke 1 sesi belajar (materi course lain) DAN 1 keputusan nyata, catat momen "saya kira paham/yakin" yang ternyata meleset saat dicek.

Status: Not started

---

## Module 7 — Capstone: Personal Brain Mastery Operating System

Description: Menjahit 5 skill asli course ini (fokus, deep work, kreativitas, membaca, metakognisi) dengan output 3 course lain (Personal Long-Term Memory System / Personal Joyful Learning System / Personal Decision & Problem-Solving Playbook) — kalau sudah/sedang dijalankan — jadi satu sistem operasi mental pribadi.

Lessons:

* [ ] 7.1 Menyusun kerangka Personal Brain Mastery OS — kombinasi teknik Modul 2-6, plus rujukan eksplisit ke capstone 3 course lain (kalau sudah ada; kalau belum, cukup dirujuk sebagai "akan disambungkan nanti")
* [ ] 7.2 Merancang 1 minggu percobaan yang menggabungkan attention/deep work block + reading strategy + 1 creativity session + metacognitive checklist harian
* [ ] 7.3 Menjalankan & mencatat data (panjang sesi fokus, skor retensi bacaan, jumlah ide sesi kreativitas, temuan checklist metakognitif)
* [ ] 7.4 Self-review memakai kriteria `domains/learning-science/DOMAIN.md` bagian "Review Style" (Intrinsic vs Extrinsic Balance, Evidence Over Productivity-Hack Hype, Genuine Enjoyment vs Forced Positivity, Sustainability) sebelum finalisasi

Capstone Project: **Personal Brain Mastery Operating System** — dokumen + log yang menggabungkan 5 skill asli course ini dan cross-reference eksplisit ke sistem dari `memory-mastery`/`learning-science`/`critical-thinking` (kalau sudah dijalankan), dijalankan nyata minimal 1 minggu penuh, disimpan ke `portofolio/`. Review memakai `domains/learning-science/DOMAIN.md` bagian "Review Style" di atas Universal Review Rubric (`ai-los/CORE_LOS.md`).

Status: Not started

---

# Capstone Project

Description: Lihat Module 7 di atas untuk rincian lengkap. Capstone course ini SATU dengan Capstone Project Module 7 — tidak ada proyek terpisah tambahan di luar modul, mengikuti pola 3 course non-software lain di repo ini (`memory-mastery`, `learning-science`, `critical-thinking`).

Acceptance Criteria: Memenuhi 4 kriteria Review Style `domains/learning-science/DOMAIN.md` (Intrinsic vs Extrinsic Balance, Evidence Over Productivity-Hack Hype, Genuine Enjoyment vs Forced Positivity, Sustainability) plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, dan memenuhi bar "Portfolio-Worthy" (Complete, Reviewed, Presentable, Original) di `standards/UNIVERSAL_STANDARDS.md`. Wajib menunjukkan cross-reference eksplisit ke minimal satu dari 3 course lain (bukan berdiri sendiri seolah course ini independen), konsisten dengan seluruh premis "Catatan Scope" di atas.

Status: Not started

---

# Overall Progress

Module Completion: 0/7

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap (termasuk approval keputusan opsi (b) dan reuse domain `learning-science` di "Catatan Scope")

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Kenapa course ini TIDAK mengulang `memory-mastery`, `learning-science`, `critical-thinking`
