# Insanity Thinking — Domain Pack

> **Status: DRAFT, terdaftar Paused, menunggu approval user.** Course: `courses/think-wild-act-sane/roadmap.md`.

## Domain Name

Insanity Thinking — Think Wild, Act Sane: berpikir divergen/generatif yang menantang asumsi dan pola otomatis, dipasangkan dengan eksperimen kecil yang disiplin dan bisa diukur (bukan gagasan liar tanpa rem).

## Scope

Domain ini mengajarkan **sisi generatif/divergen** dari berpikir berbeda — menghasilkan banyak ide (termasuk ide buruk) sebelum menilai, membongkar pola otomatis lewat perubahan lingkungan/input, menyambungkan pengetahuan lintas disiplin, memakai pertanyaan reframing ekstrem (10x, zero-based, subtraction) untuk keluar dari solusi tambal-sulam, memperlakukan kebingungan dan kegagalan sebagai data (bukan identitas), melatih kebiasaan mencatat masalah sehari-hari sebagai bahan proyek/eksperimen, dan menjalankan **loop eksperimen kecil berisiko-terukur** (Hypothesis → Experiment → Result → Analysis → Adjustment) lewat notebook "Insanity Lab" dan tantangan 30 hari. Semuanya dibingkai dalam satu pagar eksplisit — **Disciplined Insanity**: ide boleh ekstrem, eksekusi harus punya lapis validasi, kriteria berhenti, dan kesadaran realitas sebagai hakim, bukan ego.

Basis awal: dokumen "INSANITY — Berpikir Gila, Bertindak Waras" buatan/dikumpulkan user (25 Part bernomor + 30-Day Insanity Challenge + Insanity Lab notebook format + 10 Insane Questions + Final Framework 8 langkah). Domain ini **tidak memindahkan seluruh 25 Part apa adanya** — sekitar sepertiga isinya audit-overlap (lihat di bawah) ternyata sudah diajarkan mendalam di domain lain dengan kerangka teori yang lebih kuat; bagian itu **dirujuk, tidak diduplikasi**. Sisa yang benar-benar mengisi celah repo dilipat jadi 11 modul course (lihat "Pemetaan Outline ke Modul" di roadmap).

**Yang membuat domain ini berbeda dari domain lain:** `critical-thinking` sendiri secara eksplisit menyebut dirinya fokus **convergent thinking** (evaluasi/menilai klaim) dan menyisihkan **"kreativitas/ideation murni (brainstorming divergent, design thinking fase generatif)"** sebagai kandidat domain terpisah di masa depan (lihat `domains/critical-thinking/DOMAIN.md` bagian TIDAK mencakup). Domain ini **adalah** domain itu — sisi **divergent/generatif** yang menjadi pasangan `critical-thinking`, bukan penggantinya. Instruksi memakainya bersama: divergen dulu (domain ini) untuk menghasilkan opsi liar, lalu konvergen (`critical-thinking`) untuk menilai dan memutuskan.

## Audit Overlap (2026-09-28) — bagian outline yang TIDAK diajarkan ulang di sini

Grep dan pembacaan modul course terkait menunjukkan porsi signifikan outline sudah punya rumah dengan kerangka riset yang lebih dalam:

- **Five Whys, First Principles Thinking, "Reverse the Problem"** (outline Part I.3, Part IV, Part V) — **identik** dengan `courses/critical-thinking/roadmap.md` Module 3.4 (First Principles + 5 Whys + Root Cause Analysis) dan Module 6.1 (Inversion — "bagaimana caranya gagal?" untuk menemukan cara sukses; contoh API lambat di outline domain ini pun sama persis dengan pola inversion). **Tidak diulang.** Module 1 di sini hanya merujuk.
- **Steelmanning / Contrarian Test** (outline Part XVII) — sudah jadi `critical-thinking` Module 8.1 (Steelmanning vs strawmanning) secara eksplisit. **Tidak diulang.**
- **Personal Insanity / Challenge Your Identity / Become a Beginner Again / Courage to Look Stupid / Anti-Comfort Principle** (outline Part XI-XIII, XV) — ini adalah domain `courage-to-try` (exposure hierarchy, comfort/learning/panic zone, keberanian mencoba hal baru meski takut dinilai) dan `character-development` (ego, identitas sebagai pola vs fakta permanen). **Tidak diulang** — Module 8 di sini (Dark Side) hanya menyinggung ego sebagai *risiko dalam eksperimen liar*, bukan pendalaman psikologis identitas.
- **Productive Obsession / Deep Work** (outline Part XIV.27) — istilah dan konsepnya sudah eksplisit jadi bagian `domains/work-management/DOMAIN.md` (timeboxing, focus, deep work individu). **Tidak diulang.**
- **Fail Cheap sebagai mekanika bisnis** (validasi Lean Startup, MVP, pricing) — sudah `domains/entrepreneurship/DOMAIN.md` (Lean Startup validation, MVP building). Domain ini memakai "gagal murah" sebagai **metodologi eksperimen personal generik** (Module 5), bukan mekanika bisnis; contoh bisnis dirujuk ke `entrepreneurship`, tidak diulang.

Yang **tersisa dan genuinely mengisi celah** (grep 2026-09-28: tidak ditemukan modul setara di domain manapun): Crazy Idea Machine/quantity-before-quality brainstorming (`critical-thinking` eksplisit menolak topik ini), Pattern Interruption + Connection Journal lintas disiplin, Confusion Protocol (kebingungan sebagai model mental belum lengkap, beda dari `learning-science` yang bicara motivasi/flow/habit belajar, bukan diagnosis kebingungan konseptual), Experiment Loop + Insanity Lab notebook sebagai format generik (beda dari mekanika bisnis `entrepreneurship`), 10x Question/Zero-Based Thinking/Subtraction Principle sebagai trio reframing ekstrem, Problem Notebook/inventor mindset, dan **Disciplined Insanity** — kerangka tiga lapis (Imagination/Validation/Execution) + Insanity Filter 8 pertanyaan sebagai pagar sebelum bertindak ekstrem, yang menyatukan semuanya.

Secara eksplisit TIDAK mencakup (dirujuk, tidak diduplikasi):

- **Evaluasi argumen, bias kognitif, root cause, mental models, steelmanning** — [[critical-thinking]]. Domain ini divergen (menghasilkan), `critical-thinking` konvergen (menilai). Dipakai berurutan: domain ini dulu untuk membuka opsi, lalu `critical-thinking` untuk menyaring.
- **Keberanian mencoba hal baru, exposure ke rasa takut dinilai/gagal, comfort/learning/panic zone** — [[courage-to-try]]. Module 8 di sini hanya menyebut ego dan rasa takut sebagai *bias yang merusak eksperimen*, bukan psikologi keberanian itu sendiri.
- **Identitas, ego, kerendahan hati sebagai nilai personal lintas konteks** — [[character-development]].
- **Deep work, timeboxing, batas jam kerja** — [[work-management]].
- **Mekanika bisnis (Lean Canvas, MVP, pricing, legal/pajak)** — [[entrepreneurship]]. Module 5 di sini hanya metodologi eksperimen generik.
- **Motivasi, flow state, habit belajar, gamifikasi belajar** — [[learning-science]].
- **First-principles ala builder/founder teknis (Karpathy, dll.)** — [[tech-builder-mindset]]. Domain itu mempelajari POLA operasi tokoh nyata; domain ini mengajarkan METODE generik yang bisa dipakai siapa saja, tanpa studi tokoh.
- **Negosiasi, strategi antar-pihak, membaca lawan** — [[sun-tzu-strategy]], [[putin-code-strategic-thinking]], [[never-split-the-difference]]. Tidak ada elemen "lawan" di domain ini; eksperimen di sini murni personal/profesional, bukan situasi berhadapan dengan pihak lain.

## Trusted Sources / Research Priority

Kerangka yang dipakai punya akar riset/penulis nyata; setiap klaim dicek ulang (nama, tahun, argumen inti) sebelum lesson ditulis, bukan dikutip dari ingatan:

1. **Alex Osborn, *Applied Imagination* (1953)** — asal-usul brainstorming dan prinsip "kuantitas melahirkan kualitas" (deferral of judgment); dasar Module 2 (Crazy Idea Machine).
2. **Eric Ries, *The Lean Startup* (2011)** — loop **Build-Measure-Learn**, basis struktur Experiment Loop (Hypothesis → Experiment → Result → Analysis → Adjustment) di Module 5; versi bisnisnya dirujuk ke `entrepreneurship`, di sini dipakai generik.
3. **David Epstein, *Range: Why Generalists Triumph in a Specialized World* (2019)** — dasar riset untuk Module 3 (Polymath Effect, koneksi lintas disiplin); dibandingkan dengan kritik "deliberate practice" (Ericsson) yang lebih pro-spesialisasi, supaya tidak sepihak.
4. **Leidy Klotz, *Subtract: The Untapped Science of Less* (2021)**, berbasis riset "People systematically overlook subtractive changes" (Adams, Converse, Hales, Klotz — *Nature*, 2021) — dasar Module 6 bagian Subtraction Principle; verifikasi ulang detail temuan (jumlah partisipan, metodologi Lego-block experiment) sebelum lesson ditulis.
5. **Astro Teller / X, the moonshot factory (Google X)** — dasar populer "10x, bukan 10%" sebagai target ekstrem yang memaksa pendekatan berbeda; dicek sumber primer (talk/tulisan resmi X), bukan meme "10x engineer" budaya tech yang beda konteks.
6. **Carol Dweck, growth mindset** — dirujuk singkat untuk Module 4 (Confusion Protocol: "model mental belum lengkap" vs "saya bodoh"); pendalaman penuh tetap di `character-development`.
7. **Brian Tracy, "Zero-Based Thinking"** — istilah populer yang dipakai Module 6; dicek klaim asal-usulnya (buku mana persisnya) sebelum dijadikan rujukan bernama, karena istilah ini juga dipakai penulis self-help lain — bila tidak terverifikasi jelas, disajikan sebagai teknik generik tanpa atribusi tunggal.
8. **IDEO / Tom Kelley, *The Art of Innovation*** — budaya prototyping cepat dan "fail cheap" versi desain (bukan bisnis), untuk Module 5 dan Module 9 (Problem Notebook/inventor mindset); anekdot James Dyson (5.000+ prototipe) dicek ulang angka pastinya sebelum dipakai sebagai contoh, bukan diambil dari ingatan.

Tidak ada kutipan motivasi viral ("orang sukses berpikir begini") yang dipakai tanpa sumber tertulis yang bisa dirujuk — house rule sama seperti domain lain di repo ini yang menangani materi populer (lihat `sun-tzu-strategy` Ledger Kutipan sebagai preseden).

## Applied Practice Definition

**Insanity Lab Entry** — satu entri eksperimen nyata (skala kecil, risiko terukur) dengan format tetap: Experiment (apa yang dicoba), Why (alasan dan asumsi yang diuji), Hypothesis (dugaan terukur), Small Test (bagaimana menguji dengan risiko kecil — biaya, waktu, atau cakupan dibatasi eksplisit), Result (apa yang terjadi, termasuk hasil "gagal"), Lesson (apa yang dipelajari, dipisahkan dari "saya gagal sebagai orang"), Next (eksperimen lanjutan). Bukan latihan berpikir di atas kertas saja — setiap modul menuntut minimal satu entri nyata yang benar-benar dijalankan, bukan direncanakan.

## Project Tiers

- **Mini Project** (per modul): satu latihan konkret (mis. sesi brainstorming 20 ide buruk untuk satu masalah nyata, Connection Journal 7 hari, satu entri Confusion Protocol lengkap, satu Problem Notebook berisi 10 masalah yang diperhatikan minggu ini).
- **Intermediate Project**: **30-Day Insanity Challenge** (4 minggu: Break One Pattern, Build Something Small, Do the Uncomfortable dalam batas aman, Run a Real Experiment) — versi domain ini sengaja **tidak mengulang** Week 1 outline asli ("Question Everything" 5 Whys/hari) karena itu sudah `critical-thinking`; minggu itu diganti "Generate & Filter" (20 ide liar → saring 1 lewat Insanity Filter).
- **Capstone**: **Personal Insanity Lab** — kumpulan minimal 8 entri Insanity Lab lintas modul, disertai satu **Insanity Filter** lengkap (8 pertanyaan) untuk eksperimen paling berisiko yang dipilih, dan refleksi tertulis memakai Final Framework 8 langkah (Question → Challenge → Imagine → Experiment → Measure → Learn → Adapt → Repeat). Disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), tiap entri Insanity Lab dicek:

- **Kuantitas sebelum kualitas** — apakah ide liar benar-benar dihasilkan dulu (tidak loncat ke ide "aman" pertama)?
- **Risiko terukur** — apakah "small test" benar-benar kecil (waktu/uang/cakupan eksplisit dibatasi), bukan taruhan besar berkedok "eksperimen"?
- **Label sumber** — untuk klaim yang mengklaim berasal dari riset/tokoh tertentu (Osborn, Ries, Epstein, Klotz), apakah nama dan klaim intinya akurat, bukan tebakan?
- **Kegagalan sebagai data** — apakah hasil gagal dipisahkan dari identitas ("hipotesis saya salah" bukan "saya gagal")?
- **Insanity Filter dijalankan** — untuk eksperimen berisiko lebih tinggi, apakah 8 pertanyaan filter benar-benar dijawab sebelum bertindak, termasuk kriteria berhenti?
- **Batas Disciplined Insanity** — apakah ada tanda recklessness (mengabaikan risiko nyata), delusion (yakin tanpa bukti), atau obsession tanpa recovery? Ditandai sebagai Dark Side, bukan dirayakan sebagai keberanian.
- **Tidak menyerobot domain lain** — apakah eksperimen soal keberanian sosial/identitas dirujuk ke `courage-to-try`/`character-development`, bukan dijawab di sini dengan psikologi dangkal?

## Assessment Form

Studi kasus tertulis: satu sesi brainstorming nyata yang dinilai kuantitas dan keliaran idenya, satu Confusion Protocol lengkap, satu Experiment Loop dengan hasil (termasuk yang gagal), penerapan trio 10x/Zero-Based/Subtraction pada satu masalah nyata, dan Insanity Filter lengkap untuk satu keputusan nyata. Bukan quiz hafalan istilah.

## Practitioner Reference Frame

- **Peneliti kreativitas dan inovasi** (Osborn, IDEO/Tom Kelley, David Epstein) — lensa ideation dan cross-disciplinary.
- **Praktisi startup/lean methodology** (Eric Ries) — lensa experiment loop, dirujuk balik ke `entrepreneurship` untuk versi bisnis penuh.
- **Peneliti perilaku** (Leidy Klotz — subtraction neglect; Carol Dweck — growth mindset, dirujuk `character-development`) — lensa bias kognitif spesifik yang relevan ke reframing.
- **Konteks software engineer:** contoh domain ini memakai kasus backend/API/arsitektur (mis. reframing "10x lebih cepat" pada sebuah sistem, "subtraction" pada codebase/proses tim) sebagai salah satu konteks utama, tanpa menjadikannya satu-satunya.

## Domain-Specific Standards

- **Tidak ada eksperimen yang menyakiti orang lain atau diri sendiri.** "Do the Uncomfortable" dan "small test" wajib dalam batas aman — bukan risiko finansial besar, bukan tindakan yang melanggar hukum/etika, bukan eksperimen pada orang lain tanpa persetujuan.
- **Kriteria Berhenti wajib** untuk eksperimen berisiko menengah ke atas — ditulis sebelum eksperimen dimulai, bukan sesudah.
- **Kegagalan dipisahkan dari identitas** secara konsisten di seluruh course — bahasa "hipotesis salah", bukan "saya gagal".
- **Klaim berbau kesehatan mental tidak dipakai untuk mendiagnosis.** Istilah "insanity" murni metafora keberanian berpikir; bila refleksi learner menunjukkan tanda gangguan kecemasan/mood klinis, eskalasi ke profesional (house rule sama seperti `courage-to-try`, `character-development`), bukan dijawab dengan kerangka course ini.
- **Dark Side selalu dicek balik**: ego, confirmation bias, recklessness, obsession, delusion — setiap Insanity Lab entry berisiko tinggi wajib menjawab minimal satu pertanyaan Dark Side sebelum dieksekusi.
- **Tidak mengulang materi domain lain** yang sudah dirujuk di "Audit Overlap" — five whys/first principles/inversion/steelmanning/deep work/identitas/mekanika bisnis TIDAK ditulis ulang di lesson manapun di sini, hanya dirujuk.
- **Sumber dicek, bukan diingat** — nama peneliti/buku/tahun/klaim inti dibaca ulang dari referensi sebelum ditulis ke lesson (lihat "Trusted Sources").

## Domain Goal

Learner dapat menghasilkan banyak opsi liar sebelum menilai (bukan loncat ke solusi pertama), membongkar pola otomatis lewat pattern interruption dan koneksi lintas disiplin, mendiagnosis kebingungan sebagai model mental yang belum lengkap, menjalankan loop eksperimen kecil-berisiko-terukur dan memperlakukan hasil gagal sebagai data, memakai trio reframing ekstrem (10x/zero-based/subtraction) untuk keluar dari solusi tambal-sulam, mencatat masalah sehari-hari sebagai bahan proyek, dan menjalankan Disciplined Insanity — ide seliar mungkin, eksekusi seaman mungkin, realitas tetap jadi hakim. Prinsip utama (dari penutup outline user): *cukup gila untuk mempertanyakan dunia, cukup waras untuk menguji pikirannya terhadap kenyataan.*

## Registration History

Dibuat 2026-09-28 atas permintaan langsung user (paste dokumen "INSANITY — Berpikir Gila, Bertindak Waras" tanpa instruksi tambahan). Mentor bertanya lewat AskUserQuestion apa yang mau dilakukan dengan isinya (domain/course baru, simpan ke vault, atau diskusi chat) — user memilih **domain/course baru**. Guard "Course Aktif di bawah 50%" dicek: Character Development berada tepat di 3/6 modul (50%, bukan di bawah), Mastering Claude 100% selesai — guard tidak terpicu secara literal, tapi konsisten dengan preseden domain lain di repo ini (`sun-tzu-strategy`, `when-they-pull-away`, `never-split-the-difference` — semuanya di titik 50% yang sama), catatan ini ditulis eksplisit sebagai transparansi, tanpa mengulang AskUserQuestion kedua karena user sudah menjawab langsung permintaan pembuatan domain baru pada pertanyaan sebelumnya.

Audit overlap dijalankan atas inisiatif mentor (pola dokumen — 25 Part generik tentang cara berpikir — punya risiko tinggi tumpang tindih dengan `critical-thinking`, `courage-to-try`, `character-development`, `work-management`, `entrepreneurship`, `learning-science`, `tech-builder-mindset`). Hasil: **sepertiga outline (five whys, first principles, reverse-the-problem/inversion, steelmanning, personal identity/courage, deep work, mekanika bisnis)** sudah diajarkan mendalam di domain lain dengan riset lebih kuat — **tidak diduplikasi**. Sisanya (ideation kuantitas, pattern interruption, polymath connection, confusion protocol, experiment loop generik, trio reframing ekstrem, problem notebook, Disciplined Insanity/Insanity Filter) genuinely mengisi celah yang `critical-thinking` sendiri **secara eksplisit** menyisihkan sebagai kandidat domain terpisah di masa depan ("kreativitas/ideation murni... kandidat domain terpisah `creative-thinking`"). Nama domain `insanity-thinking` dan nama course `think-wild-act-sane` dipilih mentor untuk mempertahankan "rasa"/branding dokumen asli (pola sama seperti `sun-tzu-strategy`/`the-power-of-sun-tzu` mempertahankan nama sumber), bukan nama generik "creative-thinking" yang disebut `critical-thinking` — **belum ditanyakan eksplisit ke user**, dikonfirmasi saat approval. **Belum diaudit:** apakah user punya buku/sumber spesifik untuk dokumen INSANITY ini (tidak ada penulis/judul buku disebutkan di paste), dan apakah capstone sebaiknya mengambil satu masalah teknis (backend/karier) tertentu sesuai Master Goal — ditanyakan saat approval.
