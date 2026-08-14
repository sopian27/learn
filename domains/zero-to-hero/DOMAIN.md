# Zero to Hero — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Zero to Hero (Master Orchestrator Lintas-Domain — Karakter, Belajar, Kesehatan, Komunikasi, Karier, Backend Engineering, Finansial, Relasi, Seni, Sejarah, Filsafat, dan Kebijaksanaan Hidup)

## Scope

Domain ini adalah **orchestrator-of-orchestrators** — thin layer yang MENYUSUN urutan lintas-domain paling luas di repo ini (12 pilar: karakter, belajar, kesehatan, komunikasi, karier, backend engineering, finansial, relasi, seni, sejarah, filsafat, kebijaksanaan hidup), sebagian besar dengan **checkpoint ganda**: menunjuk ke stop yang sudah ada di `courses/life-mastery/roadmap.md` (yang sendiri sudah jadi orchestrator ke domain lain), dan hanya untuk pilar yang genuinely tidak dicakup life-mastery, checkpoint langsung ke domain berdiri-sendiri (`learning-science`, `home-fitness`, `relationships`, `stoicism`). Domain ini TIDAK mengulang satu baris pun konten dari `life-mastery`, `character-development`, `communication-skills`, `personal-finance`, `learning-science`, `home-fitness`, `relationships`, `stoicism`, `art-appreciation`, `world-history`, `backend-fundamentals`, `mastering-claude`, atau `system-design` — lihat "Catatan Scope" di `courses/zero-to-hero/roadmap.md` untuk perbandingan lengkap dengan `life-mastery` dan rekomendasi eksplisit ke user soal mana yang sebaiknya dipilih.

Konten yang diajarkan LANGSUNG (native) oleh domain ini — SEMUANYA sengaja dangkal/framing-only, kedalaman penuh selalu di course yang di-checkpoint:

- **Seni sebagai pilar hidup** — framing singkat "kenapa apresiasi seni bagian dari hidup yang utuh" + latihan integrasi ringan, sebelum mengarahkan ke deep-dive penuh di `art-appreciation`.
- **Sejarah sebagai pilar hidup** — framing singkat "kenapa historical literacy bagian dari kebijaksanaan hidup" + latihan integrasi ringan, sebelum mengarahkan ke deep-dive penuh di `world-history`.
- **Backend Engineering sebagai pilar hidup** (BUKAN silabus Java/Spring Boot) — framing kenapa satu kemampuan craft yang dalam (bukan tersebar dangkal di semua hal) adalah bagian dari "hero", career capital (Cal Newport), dan kapasitas mencipta/menghasilkan — sebelum mengarahkan penuh ke `backend-fundamentals`/`mastering-claude`/`system-design` untuk skill actual.
- **Kebijaksanaan Hidup / Capstone Sintesis** — dokumen tunggal yang MEMPERLUAS (bukan menulis ulang) Personal Life Operating System 19-poin milik `life-mastery` dengan 3 poin baru (Seni, Sejarah, Craft Mastery/Backend Engineering).

Konten yang HANYA di-orchestrate (checkpoint), TIDAK diajarkan ulang di sini:

- Karakter, Komunikasi, Karier (International SWE framing), Financial Intelligence + Rp1 Miliar Project → lewat stop yang sudah ada di `courses/life-mastery/roadmap.md` (Stop 1, 4, 10, 6-7), yang sendiri menunjuk ke `character-development`, `communication-skills`, `system-design`/`backend-fundamentals`, `personal-finance`.
- Belajar cara belajar → LANGSUNG ke `courses/learning-science/roadmap.md` (life-mastery TIDAK punya stop native/checkpoint untuk ini — hanya menyebut "critical-thinking" sebagai lapisan epistemik, beda topik).
- Kesehatan fisik → LANGSUNG ke `domains/home-fitness/DOMAIN.md` (life-mastery TIDAK punya stop untuk ini sama sekali — "Health/lifestyle principles" hanya muncul sebagai satu poin yatim di Capstone 19-poin life-mastery tanpa stop yang membangunnya; course roadmap `home-fitness` sendiri belum dibangun, lihat catatan di roadmap).
- Relasi romantis → LANGSUNG ke `courses/relationships/roadmap.md` (life-mastery Stop 2 soal self-love/self-acceptance, BUKAN relasi romantis — beda topik, life-mastery tidak merujuk domain `relationships` sama sekali).
- Filsafat → LANGSUNG ke `courses/stoicism/roadmap.md` (life-mastery menyentuh filsafat hanya tangensial di Stop 3 EI/Meaning dan Stop 5 Power & Strategy — bukan pilar filsafat berdiri sendiri; `stoicism` sendiri masih berstatus DRAFT, belum diregister).
- Backend engineering skill actual (Java/Spring Boot/arsitektur) → `courses/backend-fundamentals/roadmap.md`, `courses/mastering-claude/roadmap.md`, `courses/system-design/roadmap.md` (domain `software-engineering`) — pilar ini BUKAN Stop 10 life-mastery (yang berbicara soal karier internasional), tapi pilar terpisah tentang skill craft itu sendiri.

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta menulis ulang kontennya di sini:

- **Detail konten penuh tiap pilar** — course ini bukan pengganti course aslinya, hanya peta + integrasi. Kalau user ingin masuk dalam ke satu pilar, jawab dari domain pack aslinya (mis. `domains/personal-finance/DOMAIN.md`), bukan dari sini.
- **Duplikasi arsitektur `life-mastery`** — domain ini secara sadar dibangun SETELAH `life-mastery` sudah ada dan mengikuti pola arsitektur yang sama persis (Native Module 🔷 / Checkpoint 🔗), bukan pola independen baru.

## Trusted Sources / Research Priority

Domain ini TIDAK mengklaim otoritas riset baru per pilar — untuk setiap pilar yang di-checkpoint, otoritas riset sepenuhnya diwariskan dari domain pack aslinya (lihat daftar path di atas). Trusted Sources di bawah HANYA berlaku untuk lapisan native tipis (framing Seni/Sejarah/Backend-as-life-skill, dan sintesis Capstone):

1. **David Epstein, *Range: Why Generalists Triumph in a Specialized World* (2019)** — sintesis riset (termasuk studi Barry Schwartz/Robert Sternberg soal "sampling period" sebelum spesialisasi) bahwa breadth lintas-domain justru memperkuat, bukan mengorbankan, kedalaman spesialis. Tier **B** (sintesis jurnalistik dari riset, bukan riset primer tunggal) — dipakai sebagai justifikasi KENAPA course seluas ini bernilai, dikontraskan eksplisit dengan risiko "jack of all trades, master of none" di "Practitioner Reference Frame" di bawah.
2. **Cal Newport, *So Good They Can't Ignore You* (2012)** — "career capital theory": skill langka & berharga (bukan passion semata) sebagai basis karier bermakna. Tier **B**, dipakai khusus untuk framing pilar Backend Engineering-as-life-skill — kenapa satu craft yang benar-benar dalam (bukan tersebar) adalah bagian dari "hero", bukan sekadar mata pencaharian.
3. **`domains/life-mastery/DOMAIN.md`, seluruh bagian "Trusted Sources / Research Priority"** — diwariskan penuh untuk 4 pilar yang di-checkpoint LEWAT life-mastery (Karakter, Komunikasi, Karier, Finansial), tidak diduplikasi di sini.
4. **`domains/art-appreciation/DOMAIN.md`, `domains/world-history/DOMAIN.md`** — diwariskan penuh untuk kedalaman pilar Seni & Sejarah; native framing di sini hanya memakai butir #1 (Epstein) sebagai justifikasi integratif, bukan sumber seni/sejarah baru.
5. **"Wheel of Life"** (alat coaching populer, asal-usul tidak tunggal/tidak peer-reviewed) — Tier **C, alat pengorganisasian saja**, dipakai HANYA sebagai kerangka visual 12-pilar di Capstone, bukan diklaim sebagai temuan psikologi tervalidasi.

Tidak pernah membangun klaim substantif baru di satu pilar mana pun tanpa merujuk ke domain pack aslinya — pelanggaran ini persis pola yang dikritik di house rule "Evidence Discipline" `domains/life-mastery/DOMAIN.md`, dan berlaku sama ketatnya di sini.

## Applied Practice Definition

Karena ini orchestrator-of-orchestrators, Applied Practice berbeda bentuk tergantung jenis stop:

- **Stop Checkpoint (8 dari 12 pilar)**: Applied Practice SEPENUHNYA diwariskan dari course yang di-checkpoint — tidak ada artefak baru dibuat di level domain ini.
- **Stop Native tipis (Seni, Sejarah, Backend Engineering)**: satu refleksi pendek (150-250 kata) "Kenapa pilar ini bagian dari hidup saya yang utuh" + keputusan eksplisit: lanjut deep-dive sekarang lewat checkpoint, atau tunda dengan alasan prioritas jujur (bukan penghindaran).
- **Capstone (Kebijaksanaan Hidup)**: dokumen sintesis yang secara eksplisit MENGUTIP (bukan menulis ulang) artefak nyata dari setiap stop — termasuk mengutip langsung Personal Life Operating System `life-mastery` sebagai basis, ditambah 3 poin baru (Seni, Sejarah, Craft Mastery).

## Project Tiers

- **Mini Project**: untuk checkpoint, ikuti Mini Project course asli. Untuk 3 stop native tipis, refleksi integrasi 150-250 kata di atas.
- **Intermediate Project**: tidak ada Intermediate Project baru dibuat DI SINI — semua Intermediate Project lintas-modul sudah ada di dalam course yang di-checkpoint (mis. Power & Defense Playbook di life-mastery, Intermediate Project 1-2 di art-appreciation).
- **Capstone / Portfolio Project**: **Zero to Hero Master Life Portfolio** — perluasan eksplisit dari Personal Life Operating System `life-mastery` (19 poin) dengan 3 poin baru: (20) Aesthetic Sensibility & Art Literacy, (21) Historical & Civilizational Literacy, (22) Craft Mastery (Backend Engineering sebagai wujud kemampuan mencipta). Disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100):

- **Non-Duplication Check** — apakah artefak/refleksi MENGUTIP artefak asli dari course yang di-checkpoint (path eksplisit), atau diam-diam menulis ulang kontennya dari nol di sini? Yang kedua ditolak.
- **Integration Check** (khusus 3 stop native tipis) — apakah refleksi benar-benar menghubungkan pilar itu ke sistem hidup yang lebih besar, atau cuma trivia terisolasi ("saya suka Monet") tanpa keterkaitan?
- **Breadth-vs-Depth Honesty Check** (khusus Capstone) — apakah Capstone jujur soal pilar mana yang benar-benar sudah "hero-level" (checkpoint course-nya selesai & Capstone-nya sendiri jadi) vs mana yang masih "zero/awal", bukan mengklaim penguasaan di semua 12 pilar sekaligus secara tidak realistis.

## Assessment Form

Self-audit dengan tiga lensa Review Style di atas sebelum tiap stop dianggap selesai, ditambah — khusus sebelum mulai Capstone — audit checklist eksplisit: dari 11 stop sebelumnya, mana yang course/domain yang di-checkpoint BENAR-BENAR sudah selesai (bukan cuma "dibaca sekilas")? Capstone menolak dimulai kalau checklist ini belum jujur diisi.

## Practitioner Reference Frame

- **Generalist ideal (Epstein, "Range")** vs **spesialis sempit-dalam** — dikontraskan eksplisit: course ini TIDAK merekomendasikan kedalaman setara di 12 pilar (tidak realistis dan tidak perlu), melainkan pola **T-shaped**: satu-dua pilar deep (biasanya Backend Engineering + satu pilar personal lain) sebagai "batang" karier/skill, sisanya cukup literate/functional sebagai "palang" — konsisten dengan Cal Newport (career capital) di atas.
- **Renaissance-person ideal historis** (Leonardo da Vinci, polymath) sering dipakai sebagai inspirasi budaya populer untuk course seluas ini — WAJIB dikontraskan dengan realitas: polymath sejarah bekerja dalam kondisi pra-spesialisasi ilmu modern yang sangat berbeda; dipakai sebagai narasi motivasi (Tier **C**), bukan model literal yang harus ditiru presis.
- **`life-mastery` sebagai leluhur arsitektural langsung** — pola Native Module/Checkpoint domain ini SEPENUHNYA mewarisi pola `domains/life-mastery/DOMAIN.md`, bukan didesain independen; setiap perbandingan "bagaimana orchestrator lintas-domain seharusnya bekerja di repo ini" WAJIB dirujuk ke situ dulu.

## Domain-Specific Standards

`domains/zero-to-hero/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **No Silent Duplication.** Tidak ada lesson/modul di sini boleh menulis ulang konten substantif yang sudah dimiliki domain lain. Kalau ditemukan gap baru di masa depan, gap itu didaftarkan sebagai kandidat pilar/native module baru DENGAN riset Trusted Sources sendiri — tidak ditambal diam-diam sebagai teks inline.
- **Cite, Don't Rewrite di Capstone.** Setiap poin di Zero to Hero Master Life Portfolio yang berasal dari course lain WAJIB mengutip path artefak asli secara eksplisit — pola sama persis dengan "Acceptance Criteria" `courses/life-mastery/roadmap.md`.
- **Pacing realistis.** Course ini BUKAN dimaksudkan berjalan penuh paralel dengan course lain — kebijakan cap 2-course-aktif (`progress/roadmap.md`) tetap berlaku. Sebagian besar "pekerjaan" course ini terjadi DI DALAM course yang di-checkpoint, bukan di sesi khusus course ini sendiri.
- **Transparansi status checkpoint yang belum lengkap.** Untuk pilar Kesehatan (checkpoint ke `home-fitness`) dan Filsafat (checkpoint ke `stoicism`), course/domain tujuannya sendiri belum lengkap terdaftar (course roadmap `home-fitness` belum dibangun; `stoicism` masih DRAFT) — mentor WAJIB menyebutkan status ini eksplisit tiap kali stop tersebut disentuh, bukan berpura-pura sudah siap dipakai.

## Domain Goal

Akan disepakati final bersama learner saat `courses/zero-to-hero/roadmap.md` di-approve — sementara: learner memiliki peta hidup 12-pilar yang terintegrasi (bukan 12 course terpisah tak berhubungan), dengan minimal satu pilar craft (disarankan Backend Engineering) dikuasai dalam ("hero"-level, T-shaped), pilar-pilar lain minimal functional-literate, dan satu Zero to Hero Master Life Portfolio yang jujur soal status nyata tiap pilar — tanpa duplikasi kerja dari course/domain manapun yang sudah ada di repo ini.

## Registration History

Diminta 2026-08-14 sebagai bagian dari batch course yang dibangun lewat background agent, permintaan awal: course "Zero to Hero" level 0-expert mencakup karakter, belajar, kesehatan, komunikasi, karier, backend engineering, finansial, relasi, seni, sejarah, filsafat, dan kebijaksanaan hidup.

**Catatan overlap eksplisit (WAJIB dibaca sebelum approval):** domain ini overlap substansial dengan `domains/life-mastery/DOMAIN.md` (Status: Paused, BUKAN dihapus) yang sudah mencakup ~8 dari 12 pilar di atas lewat arsitektur Native Module/Checkpoint yang sama persis. Satu-satunya konten genuinely baru dibanding `life-mastery` adalah: (1) **Seni** — checkpoint ke `art-appreciation` yang sudah ada, (2) **Sejarah** — checkpoint ke `world-history` yang sudah ada, dan (3) **Backend Engineering sebagai pilar hidup eksplisit** — checkpoint ke `backend-fundamentals`/`mastering-claude`/`system-design` yang sudah ada, berbeda dari framing karier internasional di life-mastery Stop 10. Lihat "Catatan Scope" di bagian atas `courses/zero-to-hero/roadmap.md` untuk perbandingan lengkap dan rekomendasi eksplisit ke user: mempertimbangkan resume `life-mastery` + menambah 2 stop baru (Seni, Sejarah) alih-alih menjalankan course orchestrator paralel kedua.
