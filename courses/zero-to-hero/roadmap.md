# Course Roadmap

**Status: DRAFT — menunggu approval user. Belum terdaftar di `ai-los/ACTIVE_DOMAIN.md`.**

## Course Information

* Course Name: Zero to Hero — Peta Hidup 12-Pilar (Karakter, Belajar, Kesehatan, Komunikasi, Karier, Backend Engineering, Finansial, Relasi, Seni, Sejarah, Filsafat, Kebijaksanaan Hidup)
* Category: Life Mastery — orchestrator-of-orchestrators lintas domain (Domain: zero-to-hero — lihat `domains/zero-to-hero/DOMAIN.md`)
* Difficulty: Level 0 (belum punya sistem terkodifikasi di pilar mana pun) → Level 4 (punya Zero to Hero Master Life Portfolio: minimal satu pilar craft dikuasai "hero"-level T-shaped, 11 pilar lain minimal functional-literate, tanpa duplikasi kerja dari course manapun)
* Estimated Duration: 12 stop (campuran checkpoint ganda + native tipis + 1 capstone sintesis) — estimasi **jauh lebih lama dari perkiraan jam murni di bawah**, karena 8 dari 12 stop hanya SELESAI kalau course yang di-checkpoint (termasuk stop life-mastery yang ia checkpoint) benar-benar selesai, bukan sekadar dibaca. Realistis: **1.5-3 tahun** pada pace paralel wajar, mengikuti kebijakan cap 2-course-aktif (`progress/roadmap.md`).
* Prerequisites: Tidak ada prasyarat teknis keras untuk course ini SENDIRI. Tapi karena hampir semua stop adalah checkpoint, prasyarat SEBENARNYA ada di course yang dirujuk masing-masing (lihat tiap stop). **Sangat direkomendasikan** membaca "Catatan Scope" di bawah sebelum approval — course ini overlap besar dengan `courses/life-mastery/roadmap.md` yang sudah ada.

---

# Catatan Scope (WAJIB dibaca sebelum approval)

**(a) Overlap dengan `life-mastery` sudah diverifikasi, bukan diasumsikan.** Repo ini sudah punya `domains/life-mastery/DOMAIN.md` + `courses/life-mastery/roadmap.md` — course orchestrator 14-stop yang mencakup nyaris seluruh permintaan "Zero to Hero": karakter, penerimaan diri, kecerdasan emosional, komunikasi, power/strategi, financial intelligence, karier internasional software engineer, location-independent income, world travel, sampai capstone Personal Life Operating System 19-poin. Status `life-mastery` saat ini: **Paused** per `ai-los/ACTIVE_DOMAIN.md` — **BUKAN dihapus, bisa di-resume kapan saja**. Kedua file itu sudah dibaca penuh sebagai bagian riset course ini (bukan diasumsikan dari nama saja).

**(b) Yang genuinely BARU dari permintaan "Zero to Hero" ini, dan TIDAK dicakup `life-mastery` sebagai konten native:**

1. **Seni (Art)** — `life-mastery` tidak menyentuh apresiasi seni rupa sama sekali. Course dedicated `courses/art-appreciation/roadmap.md` sudah ada di repo (Paused) — course ini checkpoint ke situ, tidak menulis ulang.
2. **Sejarah (History)** — `life-mastery` tidak menyentuh sejarah dunia. Course dedicated `courses/world-history/roadmap.md` sudah ada (Paused) — checkpoint ke situ.
3. **Backend Engineering sebagai pilar eksplisit** — `life-mastery` Stop 10 ("International Software Engineer") menyinggung karier SWE internasional secara TANGENSIAL (visa/kredensial/freelance framing), TAPI tidak mengajarkan atau bahkan secara eksplisit menamai "backend engineering skill-building" sebagai pilarnya sendiri. Skill actual-nya sudah py course dedicated: `courses/backend-fundamentals/roadmap.md`, `courses/mastering-claude/roadmap.md`, `courses/system-design/roadmap.md` (domain `software-engineering`). Course ini menambahkan pilar eksplisit "Backend Engineering as a Life Skill" yang checkpoint LANGSUNG ke ketiga course itu — terpisah dari framing karier di life-mastery Stop 10.

**(c) Untuk SETIAP pilar yang life-mastery SUDAH punya stop native/checkpoint sendiri, course ini checkpoint ke STOP life-mastery tersebut (checkpoint-ke-checkpoint), bukan menulis ulang, dan bukan checkpoint langsung ke course di baliknya** — supaya kalau `life-mastery` di-resume nanti, kedua roadmap tetap konsisten satu sama lain:

| Pilar | Ada stop native/checkpoint di `life-mastery`? | Ditangani lewat |
|---|---|---|
| Karakter | Ya — Stop 1 | Checkpoint ke Life-Mastery Stop 1 → `character-development` |
| Komunikasi | Ya — Stop 4 | Checkpoint ke Life-Mastery Stop 4 → `communication-skills` |
| Karier | Ya — Stop 10 | Checkpoint ke Life-Mastery Stop 10 → `system-design`/`backend-fundamentals` (framing karier internasional) |
| Finansial | Ya — Stop 6-7 | Checkpoint ke Life-Mastery Stop 6-7 → `personal-finance` + Rp1 Miliar Project |

**Verifikasi penting (jangan diasumsikan begitu saja):** setelah membaca `courses/life-mastery/roadmap.md` secara PENUH (bukan cuma judul), 4 pilar berikut **TIDAK punya stop native/checkpoint sendiri** di sana, meski sekilas terasa "pasti sudah dicakup":

| Pilar | Status di `life-mastery` | Ditangani lewat |
|---|---|---|
| Belajar (Learning How to Learn) | TIDAK ADA stop. Hanya menyebut `critical-thinking` sebagai lapisan epistemik — topik berbeda (skeptisisme/evidence, bukan psikologi belajar). | Checkpoint LANGSUNG ke `courses/learning-science/roadmap.md` |
| Kesehatan (Health) | TIDAK ADA stop. "Health/lifestyle principles" cuma muncul sebagai 1 dari 19 poin Capstone tanpa stop pembangun manapun — poin yatim. | Checkpoint LANGSUNG ke `domains/home-fitness/DOMAIN.md` (course roadmap-nya **belum dibangun** — lihat catatan di Stop 3) |
| Relasi (Relationships, romantis) | TIDAK ADA. Stop 2 life-mastery soal self-love/self-acceptance — beda topik dari relasi romantis, dan life-mastery tidak merujuk domain `relationships` sama sekali. | Checkpoint LANGSUNG ke `courses/relationships/roadmap.md` |
| Filsafat (Philosophy) | TIDAK ADA stop dedicated. Disentuh tangensial di Stop 3 (EI/Meaning: Adlerian, ACT) dan Stop 5 (Power: Machiavelli sebagai contoh historis) — bukan pilar filsafat berdiri sendiri. | Checkpoint LANGSUNG ke `courses/stoicism/roadmap.md` (masih **DRAFT**, belum diregister) |

Jadi dari 8 pilar yang "kelihatannya sudah dicakup life-mastery", hanya **4 benar-benar punya stop di sana** (Karakter, Komunikasi, Karier, Finansial). Course ini tetap route 4 pilar itu lewat life-mastery (checkpoint-ke-checkpoint), tapi 4 pilar lainnya (Belajar, Kesehatan, Relasi, Filsafat) checkpoint LANGSUNG ke course dedicated masing-masing, karena memaksakan lewat life-mastery hanya akan menciptakan pointer palsu ke stop yang tidak ada.

**(d) Rekomendasi eksplisit untuk keputusan approval user:** Mengingat overlap besar di poin (a)-(c) di atas, pertimbangkan alternatif yang lebih murah secara maintenance: **resume `courses/life-mastery/roadmap.md` yang sudah ada, lalu tambahkan 2 stop baru di sana (Seni, Sejarah)**, alih-alih menjalankan course orchestrator paralel kedua yang isinya >60% checkpoint-ke-checkpoint ke roadmap yang sama. Opsi itu: (i) tidak butuh domain pack baru (`zero-to-hero`) sama sekali, (ii) tidak berisiko dua roadmap orchestrator saling drift kalau salah satu diupdate, (iii) langsung memakai 14 stop `life-mastery` yang sudah lengkap + 2 stop baru = 16 stop, lebih sederhana dari mengelola 2 course sejajar. Pilar "Backend Engineering sebagai pilar eksplisit" pun bisa jadi Stop 15 tambahan di `life-mastery` dengan cara yang sama.

Meski begitu, **roadmap penuh di bawah tetap dibangun sesuai permintaan** (Step 3-5 briefing) — approval user menentukan apakah roadmap ini yang dijalankan, atau alternatif resume-life-mastery di atas yang dipilih.

---

# Bagaimana Roadmap Ini Bekerja

Mengikuti pola persis `courses/life-mastery/roadmap.md` (lihat "Bagaimana Roadmap Ini Bekerja" di sana):

* **Native Module (🔷)** — konten asli, dimiliki & diajarkan langsung oleh domain `zero-to-hero`. Di course ini SEMUA native module sengaja tipis (framing + refleksi integrasi), bukan silabus penuh — kedalaman selalu didelegasikan ke checkpoint.
* **Checkpoint (🔗)** — pointer terstruktur. Sebagian menunjuk ke STOP di `courses/life-mastery/roadmap.md` (checkpoint-ke-checkpoint), sebagian menunjuk LANGSUNG ke course dedicated lain — lihat tabel di "Catatan Scope" di atas untuk alasan tiap pilihan.

---

# Learning Objectives

* [ ] Memiliki karakter, komunikasi, karier, dan financial intelligence yang teruji nyata lewat jalur `life-mastery` (Stop 1, 4, 5, 6, 7)
* [ ] Mampu mendesain proses belajar sendiri supaya berkelanjutan & menyenangkan (Stop 2)
* [ ] Punya fondasi kesehatan fisik terstruktur tanpa gym (Stop 3)
* [ ] Punya minimal satu craft/skill teknis yang dikuasai dalam — disarankan Backend Engineering — sebagai "batang" T-shaped (Stop 6)
* [ ] Punya sistem relasi romantis sehat berbasis riset (Stop 8)
* [ ] Mampu melihat & menganalisis karya seni rupa secara sadar-teknik, dengan pilar Seni terintegrasi ke identitas hidup yang lebih besar (Stop 9)
* [ ] Memahami sejarah dunia lintas era/wilayah dengan metode historis yang rigorous, terintegrasi ke kebijaksanaan hidup (Stop 10)
* [ ] Memahami Stoicism sebagai filosofi utuh yang bisa diterapkan nyata (Stop 11)
* [ ] Menghasilkan **Zero to Hero Master Life Portfolio** — perluasan Personal Life Operating System `life-mastery` dengan 3 poin baru (Stop 12, Capstone)

---

# Course Modules

## Stop 1 🔗 — Karakter

Checkpoint ke: **Life-Mastery Stop 1** (`courses/life-mastery/roadmap.md`, checkpoint ke `character-development`).

Kenapa lewat life-mastery, bukan langsung ke character-development: supaya kalau `life-mastery` di-resume nanti, kedua roadmap tetap sinkron — status stop ini SAMA dengan status Stop 1 life-mastery, tidak ditrack dua kali secara independen.

Status: Not Started

---

## Stop 2 🔗 — Belajar (Learning How to Learn)

Checkpoint LANGSUNG ke: `courses/learning-science/roadmap.md` (`domains/learning-science/DOMAIN.md`).

Kenapa langsung, bukan lewat life-mastery: diverifikasi di "Catatan Scope" — life-mastery tidak punya stop untuk ini.

Bagaimana hasilnya masuk ke Capstone: Personal Joyful Learning System (capstone course tersebut) menjadi masukan cara MENJALANKAN seluruh 11 stop lain di roadmap ini secara berkelanjutan — cocok dikerjakan di awal atau paralel sejak Stop 1.

Status: Not Started

---

## Stop 3 🔗 — Kesehatan

Checkpoint LANGSUNG ke: `domains/home-fitness/DOMAIN.md`.

**Catatan status penting**: domain pack `home-fitness` sudah ada (DRAFT, belum diregister), tapi `courses/home-fitness/roadmap.md`-nya **belum dibangun**. Stop ini tidak bisa dijalankan sampai roadmap course tersebut dibuat lewat proses yang sama (`ai-los/COURSE_CREATION.md`) dan disetujui user — dicatat eksplisit di sini supaya tidak dikira sudah siap pakai.

Kenapa langsung, bukan lewat life-mastery: diverifikasi di "Catatan Scope" — "Health/lifestyle principles" di life-mastery cuma poin yatim di Capstone tanpa stop pembangun.

Status: Not Started (blocked — course roadmap tujuan belum ada)

---

## Stop 4 🔗 — Komunikasi

Checkpoint ke: **Life-Mastery Stop 4** (`courses/life-mastery/roadmap.md`, checkpoint ke `communication-skills`).

Bagaimana hasilnya masuk ke stop lain: prasyarat praktis untuk Stop 5 (Karier — negosiasi/power) dan Stop 8 (Relasi — mekanika percakapan generik dirujuk, tidak diulang, oleh `relationships`).

Status: Not Started

---

## Stop 5 🔗 — Karier

Checkpoint ke: **Life-Mastery Stop 10** (`courses/life-mastery/roadmap.md` — framing karier internasional software engineer: portofolio, proposal, client management, kredensial).

Beda dengan Stop 6 di bawah: stop ini soal STRATEGI karier & positioning (bagaimana dipercaya proyek internasional), bukan skill teknis backend itu sendiri — itu Stop 6.

Status: Not Started

---

## Stop 6 🔗 — Backend Engineering (Craft Mastery)

Checkpoint LANGSUNG ke: `courses/backend-fundamentals/roadmap.md`, `courses/mastering-claude/roadmap.md`, `courses/system-design/roadmap.md` (domain `software-engineering`).

Native framing tipis (bukan silabus — Java/Spring Boot/arsitektur diajarkan penuh di ketiga course di atas, TIDAK diulang di sini):

* [ ] 6.1 Kenapa craft yang dalam (bukan tersebar dangkal) adalah bagian dari "hero" — career capital (Cal Newport), dan kapasitas mencipta/menghasilkan sebagai fondasi pilar-pilar lain (mis. Finansial, Karier)
* [ ] 6.2 Refleksi integrasi: pilar mana yang jadi "batang" T-shaped milikmu — apakah Backend Engineering, atau pilar lain? Keputusan eksplisit, bukan default tanpa sadar

Mini Project: Refleksi integrasi 150-250 kata (lihat `domains/zero-to-hero/DOMAIN.md` bagian "Applied Practice Definition") + keputusan checkpoint: lanjut `backend-fundamentals` sekarang, atau prioritaskan pilar craft lain dengan alasan eksplisit.

Status: Not Started

---

## Stop 7 🔗 — Finansial

Checkpoint ke: **Life-Mastery Stop 6-7** (`courses/life-mastery/roadmap.md` — checkpoint ke `personal-finance`, lalu native "The Rp1 Miliar Project" yang mengonsumsi hasilnya).

Prasyarat keras (diwariskan dari life-mastery): Stop 6 (Financial Intelligence) di sana WAJIB selesai sebelum Rp1 Miliar Project dimulai.

Status: Not Started

---

## Stop 8 🔗 — Relasi

Checkpoint LANGSUNG ke: `courses/relationships/roadmap.md` (`domains/relationships/DOMAIN.md`).

Kenapa langsung, bukan lewat life-mastery: diverifikasi di "Catatan Scope" — life-mastery Stop 2 soal self-love/self-acceptance, bukan relasi romantis; life-mastery tidak merujuk domain `relationships` sama sekali.

Dependency lunak: `relationships` sendiri merujuk (tidak mengulang) `communication-skills` (Stop 4 di sini) dan `self-love`/`emotional-resilience` untuk sebagian modulnya — idealnya Stop 4 dikerjakan duluan atau paralel.

Status: Not Started

---

## Stop 9 🔷🔗 — Seni

Native framing tipis + Checkpoint ke: `courses/art-appreciation/roadmap.md` (`domains/art-appreciation/DOMAIN.md`) untuk kedalaman penuh (14 modul, metode Feldman, sejarah seni lintas periode, ikonografi, Capstone Personal Museum & Analysis Portfolio).

Native framing (BUKAN pengganti 14 modul di atas):

* [ ] 9.1 Kenapa "active looking" (bukan cuma "suka/tidak suka") bagian dari hidup yang utuh — Range (Epstein): breadth estetik memperkaya cara berpikir di pilar lain (mis. komposisi visual → komunikasi visual di Karier)
* [ ] 9.2 Refleksi integrasi: satu karya yang sudah pernah "mengena" secara personal — dianalisis sekilas (bukan penuh Feldman, itu ada di Module 1 art-appreciation), lalu diputuskan: lanjut deep-dive sekarang atau nanti

Mini Project: Refleksi integrasi 150-250 kata + keputusan checkpoint eksplisit ke `art-appreciation`.

Status: Not Started

---

## Stop 10 🔷🔗 — Sejarah

Native framing tipis + Checkpoint ke: `courses/world-history/roadmap.md` (`domains/world-history/DOMAIN.md`) untuk kedalaman penuh (11 modul, metode historis, historiografi, historical discernment terhadap konspirasi).

Native framing (BUKAN pengganti 11 modul di atas):

* [ ] 10.1 Kenapa historical literacy bagian dari kebijaksanaan hidup — pola berulang lintas era (bukan "sejarah berulang persis", tapi struktur sebab-akibat yang bisa dikenali) sebagai input pengambilan keputusan hidup
* [ ] 10.2 Refleksi integrasi: satu peristiwa sejarah yang paling relevan ke situasi hidup/karier saat ini — dianalisis sekilas, lalu diputuskan lanjut deep-dive sekarang atau nanti

Mini Project: Refleksi integrasi 150-250 kata + keputusan checkpoint eksplisit ke `world-history`.

Status: Not Started

---

## Stop 11 🔗 — Filsafat

Checkpoint LANGSUNG ke: `courses/stoicism/roadmap.md` (`domains/stoicism/DOMAIN.md`).

**Catatan status penting**: `stoicism` masih berstatus **DRAFT**, belum diregister ke `ai-los/ACTIVE_DOMAIN.md`. Stop ini menunggu approval `stoicism` secara independen sebelum bisa dijalankan — dicatat eksplisit supaya tidak dikira sudah aktif.

Kenapa langsung, bukan lewat life-mastery: diverifikasi di "Catatan Scope" — life-mastery hanya menyentuh filsafat tangensial (Adlerian di Stop 3, Machiavelli sebagai contoh historis di Stop 5), bukan pilar filsafat utuh.

Status: Not Started (blocked — course tujuan masih DRAFT)

---

## Stop 12 🔷 — Capstone: Kebijaksanaan Hidup (Zero to Hero Master Life Portfolio)

Native module `zero-to-hero`, PRASYARAT KERAS: Stop 1-11 selesai (yang berarti, secara transitif, Life-Mastery Stop 1/4/6/7/10 DAN Capstone Life-Mastery-nya sendiri sudah selesai untuk stop yang di-checkpoint lewat sana — lihat "Bagaimana Roadmap Ini Bekerja").

Description: MEMPERLUAS (bukan menulis ulang) Personal Life Operating System 19-poin milik `life-mastery` (lihat Stop 14 di `courses/life-mastery/roadmap.md`) dengan 3 poin baru:

20. **Aesthetic Sensibility & Art Literacy** (dari Stop 9 — mengutip Personal Museum & Analysis Portfolio `art-appreciation`)
21. **Historical & Civilizational Literacy** (dari Stop 10 — mengutip World History & Discernment Portfolio `world-history`)
22. **Craft Mastery** (dari Stop 6 — mengutip bukti nyata skill backend engineering dari `backend-fundamentals`/`mastering-claude`/`system-design`, sebagai wujud konkret kapasitas mencipta)

Lessons:

* [ ] 12.1 Audit jujur 11 stop sebelumnya — mana yang benar-benar selesai (bukan cuma dibaca), sesuai "Assessment Form" `domains/zero-to-hero/DOMAIN.md`
* [ ] 12.2 Mengutip (bukan menulis ulang) 19 poin Personal Life Operating System `life-mastery` sebagai fondasi poin 1-19
* [ ] 12.3 Menyusun poin 20-22 (Seni, Sejarah, Craft Mastery) dengan kutipan langsung ke artefak nyata dari Stop 9, 10, 6
* [ ] 12.4 Refleksi T-shaped final: pilar mana yang jadi "batang" hero-level, pilar mana yang cukup functional-literate, ditulis jujur (bukan mengklaim penguasaan merata di 22 poin)

Capstone Project: **Zero to Hero Master Life Portfolio** — dokumen tunggal 22-poin, disimpan ke `portofolio/`, menjadi bukti T-shaped mastery lintas 12 pilar tanpa duplikasi kerja dari course manapun yang sudah ada di repo ini.

Status: Not Started

---

# Capstone Project (ringkasan)

Description: Lihat Stop 12 di atas untuk rincian lengkap. **Zero to Hero Master Life Portfolio** — perluasan eksplisit Personal Life Operating System `life-mastery` (19 poin) + 3 poin baru (Seni, Sejarah, Craft Mastery) = 22 poin total.

Acceptance Criteria:

* Poin 1-19 MENGUTIP LANGSUNG artefak Personal Life Operating System `life-mastery`, tidak ditulis ulang dari nol
* Poin 20-22 mengutip artefak nyata Stop 9 (`art-appreciation`), Stop 10 (`world-history`), Stop 6 (`backend-fundamentals`/`mastering-claude`/`system-design`)
* Tidak ada klaim penguasaan merata di semua 22 poin — refleksi T-shaped final (12.4) wajib jujur soal pilar mana yang dalam vs functional-literate
* Disimpan ke `portofolio/`

Status: Not Started

---

# Resources

Untuk 4 pilar yang checkpoint lewat Life-Mastery (Karakter, Komunikasi, Karier, Finansial): lihat `domains/life-mastery/DOMAIN.md` bagian "Trusted Sources / Research Priority". Untuk Belajar: `domains/learning-science/DOMAIN.md`. Untuk Kesehatan: `domains/home-fitness/DOMAIN.md`. Untuk Relasi: `domains/relationships/DOMAIN.md`. Untuk Seni: `domains/art-appreciation/DOMAIN.md`. Untuk Sejarah: `domains/world-history/DOMAIN.md`. Untuk Filsafat: `domains/stoicism/DOMAIN.md`. Untuk Backend Engineering: `domains/software-engineering/DOMAIN.md` + `courses/backend-fundamentals/roadmap.md`/`courses/mastering-claude/roadmap.md`/`courses/system-design/roadmap.md`. Untuk lapisan native tipis domain ini sendiri (framing Seni/Sejarah/Backend/Capstone): lihat `domains/zero-to-hero/DOMAIN.md` bagian "Trusted Sources / Research Priority" (David Epstein, *Range*; Cal Newport, *So Good They Can't Ignore You*).

# Projects

12 Mini Project (satu per stop — untuk stop checkpoint, ikuti Mini Project course asli; untuk 3 stop native tipis, refleksi integrasi 150-250 kata) + tidak ada Intermediate Project baru di level course ini (semua sudah ada di dalam course yang di-checkpoint) + 1 Capstone Project (Zero to Hero Master Life Portfolio, 22 poin, disimpan ke `portofolio/`).

# Interview

Tidak ada interview terpisah di level course ini — evaluasi kompetensi tiap pilar mengikuti Assessment Form course yang di-checkpoint masing-masing (lihat "Resources" di atas). Menjelang Capstone, satu audit checklist eksplisit dijalankan (Lesson 12.1) sebagai gerbang sebelum sintesis dimulai.

# Final Exam

Capstone Project (Stop 12) berfungsi sebagai final exam — dinilai memakai Universal Review Rubric (`ai-los/CORE_LOS.md`) plus tiga lensa Review Style `domains/zero-to-hero/DOMAIN.md` (Non-Duplication Check, Integration Check, Breadth-vs-Depth Honesty Check).

---

# Overall Progress

Module Completion: 0/12 stop

Overall Completion: 0%

Current Module: **Belum dimulai — roadmap ini DRAFT, menunggu approval user.** Lihat "Catatan Scope" di atas sebelum approval — pertimbangkan alternatif resume `life-mastery` + 2 stop baru.

Current Lesson: -

Next Lesson: Setelah approval — mulai Stop 1 (checkpoint ke Life-Mastery Stop 1) dan/atau Stop 2 (Belajar, native learning-science checkpoint) sesuai prioritas yang dipilih user
