# Course Roadmap

**Status: DRAFT — menunggu approval user (belum masuk `progress/roadmap.md` "Course Aktif"/"Course Disetujui", sesuai `ai-los/COURSE_CREATION.md`: jangan langsung mulai Lesson 1 sebelum di-approve).**

## Course Information

* Course Name: World-Class Tech Builder — dari 0 sampai Expert
* Category: Tech Builder Mindset (Domain: tech-builder-mindset — lihat `domains/tech-builder-mindset/DOMAIN.md`, draft)
* Difficulty: Level 1 (sudah kerja sebagai software engineer profesional, tapi belum pernah sadar mempelajari & menguji pola operasi builder/founder kelas dunia) → Level 4 (bisa mengartikulasikan DAN menjalankan Personal Builder Operating System sendiri — leverage sadar, execution velocity tinggi, first-principles reasoning, product taste, network capital nyata — masing-masing sudah diuji lewat eksperimen nyata, bukan cuma dihafal)
* Estimated Duration: 10 modul, 4-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`). Pace sedang ≈ 3-4 bulan.
* Prerequisites: Sudah kerja profesional sebagai software engineer (tidak wajib level senior). **Wajib** py minimal 1 proyek/kode/karier nyata yang sedang berjalan (dari pekerjaan, course lain, atau side project) — course ini secara struktural bergantung pada proyek nyata itu untuk bagian "Applied to Me" tiap modul; tanpa itu, course ini cuma jadi bacaan biografi pasif, yang eksplisit BUKAN tujuannya (lihat `domains/tech-builder-mindset/DOMAIN.md` bagian "Applied Practice Definition").

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan permintaan Anda mempelajari mindset/pola operasi builder teknis elite (Mark Zuckerberg, Alexandr Wang, Andrej Karpathy, plus Jensen Huang & John Carmack untuk range) — cakupan: technical depth sebagai moat, first-principles thinking, product thinking, leverage (personal/code/media/capital), building velocity/execution, networking/relationship capital, leadership at scale, dan entrepreneurship instinct:

1. **Kenapa BUKAN duplikat `courses/engineering-culture`**: course itu (9 modul, disetujui) mengajarkan CRAFT & JUDGMENT senior engineer yang sudah settled dalam sebuah tim — cara menulis design doc/RFC, budaya code review, incident response & blameless postmortem, technical debt communication, feedback/mentoring, estimasi jujur. Course ini tidak menyentuh satupun dari itu. Course ini murni tentang bagaimana builder/founder yang membangun sesuatu dari titik nol dengan taruhan besar BERPIKIR dan BERGERAK — technical depth sebagai senjata kompetitif, first-principles reasoning, leverage, kecepatan eksekusi, dan insting founder. Dua course ini saling melengkapi (technical depth Module 2 course ini bisa jadi modal untuk design doc yang lebih tajam di `engineering-culture` Module 2), tapi bukan versi "lanjutan" satu sama lain — layer yang berbeda sepenuhnya.
2. **Kenapa BUKAN duplikat `courses/career-reinvention`**: course itu (8 modul, disetujui) murni tentang KEMASAN & DISTRIBUSI karier — CV, LinkedIn, personal branding, portfolio, networking untuk mencari kerja/klien, application tracker. Course ini tidak pernah membahas format CV atau algoritma LinkedIn. Module 7 course ini ("Networking & Relationship Capital") secara eksplisit BUKAN "cara networking untuk cari kerja" (itu `career-reinvention` Module 6) — melainkan bagaimana builder membangun jaringan kepercayaan/co-founder/early-believer yang menggerakkan sesuatu yang besar (mis. bagaimana Zuckerberg terhubung ke Peter Thiel & Silicon Valley awal, bagaimana Wang membangun kepercayaan investor sejak usia 19). Hasil dari course ini (prinsip operasi, proyek nyata dari Applied-to-Me) BISA jadi bahan konten portfolio di `career-reinvention`, tapi course ini sendiri tidak membahas kemasan/distribusi.
3. **Kenapa domain baru (`tech-builder-mindset`), bukan diperluas ke domain existing**: bukan `software-engineering` karena bukan skill-building teknis linear (bukan belajar Java/system design/craft) — ini studi pola/mindset lewat sintesis essay-interview-biografi, metodenya lebih dekat ke `domains/middle-earth` (sintesis sumber primer/sekunder berlapis) daripada ke domain teknis. Bukan `character-development` karena domain itu tentang karakter/etos PERSONAL lintas-konteks (kerendahan hati, ketenangan batin, manajemen ego) — course ini sempit ke pola OPERASI teknis-bisnis builder, bukan psikologi diri. Bukan `entrepreneurship` (draft) karena domain itu mekanika OPERASIONAL bisnis sampingan weekend (Lean Canvas, legal/pajak UMKM, MVP) — course ini mindset builder skala besar, bukan langkah taktis mendirikan bisnis kecil. Rincian lengkap pemisahan ada di `domains/tech-builder-mindset/DOMAIN.md` bagian "Scope".
4. **Prinsip anti-hero-worship**: setiap modul wajib memasangkan pola praktisi dengan catatan eksplisit "apa yang pola ini asumsikan" (modal, timing pasar, privilege, skala tim) — supaya course ini tidak jadi bacaan motivasi kosong yang mengabaikan survivorship bias. Lihat `domains/tech-builder-mindset/DOMAIN.md` bagian "Domain-Specific Standards".
5. **Tidak ada endorsement moral penuh terhadap tiap figur** — figur yang dipelajari adalah manusia nyata dengan keputusan bisnis/etis yang kontroversial secara publik (mis. kebijakan konten Meta, kontroversi kontrak Scale AI). Course ini mengekstrak pola operasi teknis-bisnis yang bisa dipertanggungjawabkan, bukan menilai atau membenarkan keseluruhan karakter/keputusan moral mereka.

Referensi riset yang dipakai menyusun roadmap ini (Agustus 2026): Naval Ravikant, *"How to Get Rich (without getting lucky)"* ([nav.al/rich](https://nav.al/rich)) untuk kerangka leverage; Paul Graham, *"Relentlessly Resourceful"* (2009), *"How to Do Great Work"* (2023), dan *"Founder Mode"* (2024) via [paulgraham.com](https://paulgraham.com); Andrej Karpathy, *"Software 2.0"* (2017) dan *"A Recipe for Training Neural Networks"* (2019) di [karpathy.ai](https://karpathy.ai), plus seri *"Neural Networks: Zero to Hero"*; John Carmack, arsip *.plan files*, *Lex Fridman Podcast #309* (2022, transkrip di [lexfridman.com/john-carmack](https://lexfridman.com/john-carmack)), dan biografi *Masters of Doom* oleh David Kushner (2003); Jensen Huang, biografi *The Nvidia Way* oleh Tae Kim (2025), interview Stratechery (Ben Thompson) dan 60 Minutes (2026); Alexandr Wang, interview [Y Combinator Startup Library](https://www.ycombinator.com/library/MV-alexandr-wang-building-scale-ai-transforming-work-with-agents-competing-with-china), a16z *"Frontier Data Foundries"*, TIME (2026), dan Accel *"Spotlight On"* podcast; Mark Zuckerberg, surat IPO S-1 *"The Hacker Way"* (2012, [teks lengkap via CNN Money](https://money.cnn.com/2012/02/01/technology/zuckerberg_ipo_letter/index.htm)) dan biografi *The Facebook Effect* oleh David Kirkpatrick (2010); serta [Snopes fact-check soal "move fast and break things"](https://www.snopes.com/fact-check/move-fast-break-things-facebook-motto/) sebagai model verifikasi kutipan viral. Rincian lengkap & house rule riset ada di `domains/tech-builder-mindset/DOMAIN.md` bagian "Trusted Sources / Research Priority".

---

# Learning Objectives

* [ ] Mempelajari pola/keputusan praktisi lewat sumber primer terverifikasi (essay/transkrip/biografi), bukan kutipan viral yang tidak terlacak asalnya
* [ ] Menjelaskan technical depth sebagai moat kompetitif (Karpathy "from-scratch principle", Carmack) dan menerapkannya lewat 1 first-principles teardown nyata pada proyek/kode sendiri
* [ ] Mempraktikkan first-principles thinking untuk membongkar asumsi teknis/bisnis yang selama ini diterima begitu saja
* [ ] Mengasah product thinking & taste ala Zuckerberg — mengambil 1 keputusan produk nyata dengan reasoning yang bisa dipertanggungjawabkan, bukan tebakan
* [ ] Memetakan leverage pribadi (labor/capital/code/media, kerangka Naval Ravikant) dan menyusun rencana sadar menggeser diri ke leverage berbiaya-marjinal-rendah
* [ ] Menjalankan 1 sprint eksekusi bergaya "wartime intensity" (Wang) atau "Move Fast" (Zuckerberg) dengan hasil nyata dicatat, bukan rencana di atas kertas
* [ ] Membangun network/relationship capital genuine ala builder awal (bukan "cara networking cari kerja") lewat minimal 1 outreach nyata
* [ ] Menganalisis pola leadership at scale (Huang — speed culture, flat org, mission over ego) dan menerapkan 1 prinsip ke konteks kepemimpinan/pengaruh learner sendiri (formal atau informal)
* [ ] Mengartikulasikan founder instinct/insting entrepreneurial (resourcefulness ala Paul Graham, timing, taruhan besar) tanpa harus benar-benar mendirikan perusahaan untuk mempraktikkannya
* [ ] Menyelesaikan capstone: **Personal Builder Operating System** — 8 prinsip operasi pribadi, tiap prinsip dijangkar ke pola praktisi terverifikasi DAN dibuktikan lewat eksperimen "Applied to Me" nyata, plus rencana eksekusi 90 hari

---

# Course Modules

## Module 1 — Fondasi: Cara Mempelajari Builder Tanpa Hero Worship

Description: Sebelum masuk konten, pahami dulu metode course ini — kenapa "belajar dari orang sukses" gampang berubah jadi bacaan motivasi kosong kalau tidak disiplin soal verifikasi sumber dan survivorship bias, dan bagaimana metode "Builder Field Note" course ini menghindarinya.

Lessons:

* [ ] 1.1 Kenapa "studi builder kelas dunia" berbeda dari motivational content — disiplin verifikasi sumber primer vs kutipan viral yang salah konteks (studi kasus: [mitos "move fast and break things"](https://www.snopes.com/fact-check/move-fast-break-things-facebook-motto/))
* [ ] 1.2 Survivorship bias & "apa yang pola ini asumsikan" — kenapa pola yang berhasil untuk seorang founder dengan modal/timing/privilege tertentu tidak otomatis portable, dan cara tetap mengekstrak nilai darinya
* [ ] 1.3 Pattern-not-personality: membedakan pola operasi yang bisa dipindahkan (heuristik keputusan, kebiasaan kerja) dari cult of personality (gaya bicara, jam kerja ekstrem tanpa konteks, gaya hidup)
* [ ] 1.4 Metode "Builder Field Note": struktur Pattern + Applied to Me, dan kenapa bagian kedua wajib pakai proyek/karier nyata Anda, bukan skenario hipotetis
* [ ] 1.5 Praktik: pilih 1 proyek/kode/karier nyata yang akan jadi "lab" Applied-to-Me sepanjang course ini, tulis 1 paragraf konteksnya

Mini Project: Builder Field Note pertama — analisis 1 kutipan/klaim populer tentang salah satu dari 5 praktisi inti course, verifikasi ke sumber primer (terverifikasi atau ditandai "tidak terverifikasi"), plus deklarasi proyek "lab" pribadi untuk Applied-to-Me sepanjang course.

Status: Not started

---

## Module 2 — Technical Depth sebagai Moat

Description: Kenapa beberapa builder tetap relevan lintas dekade meski teknologi berubah total — karena mereka membangun pemahaman yang cukup dalam untuk tidak bergantung pada abstraksi orang lain. Modul ini masuk ke praktik konkret Karpathy dan Carmack.

Lessons:

* [ ] 2.1 Karpathy's "from-scratch principle" — kenapa dia membangun micrograd, char-rnn, nanoGPT dari nol alih-alih cuma memakai library, dan bagaimana itu jadi dasar debugging intuisi yang tidak bisa didapat dari pemakaian API saja (sumber: [karpathy.ai](https://karpathy.ai), *"A Recipe for Training Neural Networks"*)
* [ ] 2.2 Carmack's shipping-era depth — bagaimana penguasaan low-level (rendering, memory, performance) di era Doom/Quake jadi keunggulan kompetitif nyata id Software, dijangkar ke *Masters of Doom* dan *.plan files*
* [ ] 2.3 Technical depth sebagai moat vs technical depth sebagai vanity — kapan mendalami sesuatu benar-benar jadi keunggulan kompetitif, kapan cuma jadi rabbit hole yang tidak menghasilkan apa-apa
* [ ] 2.4 Depth dengan komunikasi jelas: kenapa Karpathy juga dikenal karena mengajar (Zero to Hero) — depth yang tidak bisa dijelaskan sederhana sering tanda belum benar-benar dikuasai
* [ ] 2.5 Praktik: identifikasi 1 layer abstraksi yang selama ini Anda pakai tanpa benar-benar paham cara kerjanya di proyek nyata Anda

Mini Project: Builder Field Note — lakukan 1 "from-scratch teardown" kecil pada 1 komponen/library yang Anda pakai tanpa dipahami dalamnya di proyek nyata (mis. reimplementasi versi mini dari satu fungsi library), catat apa yang ditemukan yang sebelumnya tersembunyi di balik abstraksi.

Status: Not started

---

## Module 3 — First-Principles Thinking

Description: Bagaimana builder membongkar asumsi yang diterima begitu saja dan membangun ulang dari fakta dasar — bukan sekadar analogi ke apa yang sudah ada.

Lessons:

* [ ] 3.1 First-principles vs reasoning by analogy — definisi konkret dan kenapa kebanyakan keputusan teknis/bisnis default ke analogi ("kita pakai X karena kompetitor pakai X")
* [ ] 3.2 Karpathy's debugging discipline sebagai first-principles dalam praktik sehari-hari: "neural net training fails silently" — hipotesis konkret, verifikasi eksperimen, tambah kompleksitas bertahap (*"A Recipe for Training Neural Networks"*)
* [ ] 3.3 Jensen Huang & reasoning dari ekonomi dasar chip — bagaimana taruhan NVIDIA pada GPU general-purpose computing (CUDA) dibangun dari analisis dasar biaya-komputasi, bukan mengikuti roadmap industri yang ada (*The Nvidia Way*)
* [ ] 3.4 Perangkap first-principles: overconfidence membongkar ulang sesuatu yang sebenarnya sudah punya alasan bagus (chesterton's fence) — kapan first-principles thinking tepat, kapan justru buang waktu
* [ ] 3.5 Praktik: pilih 1 asumsi teknis/proses yang dipakai begitu saja di proyek Anda ("kita selalu lakukan X"), bongkar ke fakta dasarnya

Mini Project: Builder Field Note — 1 first-principles teardown nyata terhadap satu asumsi teknis atau keputusan arsitektur di proyek Anda sendiri: tulis asumsi awal, fakta dasar yang ditemukan, dan apakah asumsi itu ternyata masih valid atau perlu diubah.

Status: Not started

---

## Module 4 — Product Thinking & Taste

Description: Kemampuan merasakan apa yang akan dipakai orang sebelum data membuktikannya — salah satu skill paling langka dan paling sulit diajarkan lewat teori saja.

Lessons:

* [ ] 4.1 Zuckerberg's product obsession — dari "The Facebook" versi awal sampai iterasi News Feed yang kontroversial saat diluncurkan tapi terbukti benar (*The Facebook Effect*, David Kirkpatrick)
* [ ] 4.2 Paul Graham soal "making yourself a big target for luck" & taste — bagaimana eksposur luas (baca, ngobrol dengan orang beragam, coba hal baru) membangun intuisi produk, bukan bakat bawaan semata (*"How to Do Great Work"*)
* [ ] 4.3 Data vs taste: kapan sebuah keputusan produk butuh data/eksperimen dulu, kapan justru harus diputuskan dari rasa/keyakinan sebelum data tersedia (produk baru belum py user untuk menghasilkan data)
* [ ] 4.4 "Dogfooding" & feedback loop cepat sebagai cara melatih taste secara sistematis, bukan menunggu bakat datang
* [ ] 4.5 Praktik: ambil 1 keputusan produk/fitur nyata di proyek Anda, tulis reasoning eksplisit (bukan cuma "menurut saya")

Mini Project: Builder Field Note — 1 keputusan produk nyata untuk proyek Anda (fitur baru/perubahan UX/prioritas roadmap), lengkap dengan reasoning taste-based DAN evidence yang ada, plus prediksi eksplisit yang bisa dicek benar/salahnya nanti.

Status: Not started

---

## Module 5 — Leverage: Personal, Code, Media, Capital

Description: Kerangka leverage Naval Ravikant sebagai lensa untuk memahami kenapa builder yang sama-sama kerja keras bisa menghasilkan dampak yang jauh berbeda — dan bagaimana Wang dan Zuckerberg mempraktikkannya secara berbeda.

Lessons:

* [ ] 5.1 Kerangka 4 leverage Naval Ravikant: labor, capital, code, media — permissioned vs permissionless leverage, dan kenapa code/media adalah leverage paling dapat diakses builder individu hari ini (*"How to Get Rich (without getting lucky)"*)
* [ ] 5.2 Zuckerberg & code sebagai leverage — bagaimana 1 platform (Facebook) menskalakan dampak 1 tim kecil ke miliaran pengguna tanpa linear menambah orang
* [ ] 5.3 Wang & leverage bertahap — bagaimana Scale AI dimulai dari leverage labor (data labeling manusia) lalu sengaja bergeser ke leverage code/produk seiring waktu (sumber: Y Combinator Startup Library, a16z)
* [ ] 5.4 Leverage capital: kapan mengambil modal eksternal benar-benar mengalikan dampak vs kapan justru mengunci Anda ke ekspektasi orang lain
* [ ] 5.5 Praktik: petakan leverage Anda saat ini (dominan labor/capital/code/media?) dan identifikasi 1 langkah konkret menggeser ke leverage berbiaya-marjinal-lebih-rendah

Mini Project: Builder Field Note — peta leverage pribadi (assessment jujur posisi Anda di 4 kategori) + 1 langkah konkret yang benar-benar mulai dijalankan minggu ini untuk menggeser leverage, dengan hasil awal dicatat.

Status: Not started

---

## Module 6 — Building Velocity & Execution

Description: Kecepatan bukan sekadar bekerja lebih lama — ini tentang menghilangkan bottleneck keputusan dan membangun budaya iterasi cepat. Modul ini membedah "Move Fast" Zuckerberg, "wartime intensity" Wang, dan disiplin shipping Carmack.

Lessons:

* [ ] 6.1 "The Hacker Way" & "Move Fast" — konteks asli surat IPO 2012 Zuckerberg, dan kenapa "move fast and break things" sering disalahpahami tanpa konteks aslinya (surat lengkap via [CNN Money](https://money.cnn.com/2012/02/01/technology/zuckerberg_ipo_letter/index.htm), verifikasi via [Snopes](https://www.snopes.com/fact-check/move-fast-break-things-facebook-motto/))
* [ ] 6.2 Wang & "wartime intensity" — going "ten miles" bukan satu, dan bedanya dengan sekadar kerja lembur tanpa arah (sumber: interview YC/a16z/TIME)
* [ ] 6.3 Carmack's shipping discipline — kerja fokus terukur (bukan cuma jam panjang), dan disiplin "kualitas turun setelah 12 jam" sebagai batas sadar, bukan glorifikasi burnout (*Lex Fridman Podcast #309*)
* [ ] 6.4 Bottleneck keputusan sebagai musuh nyata velocity — kenapa kecepatan sering ditentukan oleh seberapa cepat keputusan diambil, bukan seberapa cepat kode ditulis (menyambung ke Module 8, budaya keputusan Huang)
* [ ] 6.5 Praktik: rancang & jalankan 1 sprint eksekusi pendek (2-5 hari) dengan 1 target konkret di proyek nyata Anda, dengan aturan "done is better than perfect" secara eksplisit

Mini Project: Builder Field Note — 1 sprint eksekusi nyata (bukan simulasi) di proyek Anda, dengan target sebelum-sesudah yang jelas, dan refleksi jujur apa yang mempercepat vs yang jadi bottleneck.

Status: Not started

---

## Module 7 — Networking & Relationship Capital

Description: Bagaimana builder membangun jaringan kepercayaan yang benar-benar menggerakkan sesuatu besar — early believer, co-founder, investor, mentor — bukan "cara networking cari kerja" (itu domain lain), tapi bagaimana relationship capital jadi bagian dari mesin membangun.

Lessons:

* [ ] 7.1 "Surface area for luck" ala Paul Graham — bagaimana eksposur ke orang & ide beragam meningkatkan peluang koneksi bernilai tinggi, prinsip yang sama dengan Module 4 tapi diterapkan ke relasi
* [ ] 7.2 Zuckerberg & jaringan Silicon Valley awal — bagaimana koneksi awal (termasuk Peter Thiel sebagai investor pertama) membentuk lintasan Facebook, dan pola "siapa yang percaya duluan sebelum ada bukti" (*The Facebook Effect*)
* [ ] 7.3 Wang & kepercayaan investor di usia muda — bagaimana kredibilitas dibangun lewat eksekusi nyata yang terlihat, bukan sekadar pitching (sumber: Index Ventures, Accel)
* [ ] 7.4 Relationship capital yang genuine vs transaksional — kenapa jaringan yang dibangun murni untuk "apa yang bisa saya dapat" cenderung rapuh saat dibutuhkan
* [ ] 7.5 Praktik: identifikasi 1 hubungan/koneksi nyata (mentor, calon kolaborator, komunitas teknis) yang relevan untuk proyek "lab" Anda, dan lakukan 1 outreach nyata

Mini Project: Builder Field Note — 1 outreach nyata (pesan/pertemuan) ke satu orang yang relevan untuk proyek "lab" Anda, dicatat: apa yang ditawarkan (bukan diminta) di pesan itu, dan hasil/responnya.

Status: Not started

---

## Module 8 — Leadership at Scale

Description: Bagaimana builder yang tadinya individual contributor teknis berubah jadi pemimpin ribuan orang tanpa kehilangan kecepatan atau kejernihan keputusan — studi utama pada Jensen Huang.

Lessons:

* [ ] 8.1 Flat org & "60 orang yang tahu apa yang menggerakkan perusahaan" — filosofi organisasi Huang yang sengaja menghindari hierarki berlapis untuk mempertahankan kecepatan keputusan (*The Nvidia Way*)
* [ ] 8.2 "How fast can you do it, and why aren't you doing it that fast?" — budaya menantang bottleneck secara terbuka, bukan sekadar menuntut kerja keras
* [ ] 8.3 Transparansi radikal Huang — komunikasi strategi ke semua orang sekaligus untuk menghindari silo, dibandingkan dengan pola komunikasi command-and-control tradisional
* [ ] 8.4 Ketahanan lintas siklus — bagaimana NVIDIA (dan Huang) bertahan dari beberapa siklus boom-bust industri chip, dan apa yang dipertahankan (mission, budaya) saat strategi produk berubah total
* [ ] 8.5 Praktik: terapkan 1 prinsip leadership Huang (transparansi/menantang bottleneck/flat communication) ke konteks kepemimpinan/pengaruh Anda sendiri, sekecil apa pun skalanya (tim, komunitas, proyek open-source)

Mini Project: Builder Field Note — 1 penerapan nyata prinsip leadership Huang dalam konteks Anda (memimpin proyek kecil, tim, atau komunitas), dengan hasil/reaksi orang lain dicatat jujur.

Status: Not started

---

## Module 9 — Founder Instinct & Entrepreneurship

Description: Insting yang membuat seseorang melihat peluang dan berani bertaruh besar sebelum semua orang lain melihatnya — bukan mekanika mendirikan perusahaan (itu domain lain), tapi cara berpikir di baliknya.

Lessons:

* [ ] 9.1 Paul Graham's "Relentlessly Resourceful" — definisi konkret founder yang baik: bukan menunggu jalan mulus, tapi terus mencoba jalan baru saat jalan lama buntu
* [ ] 9.2 "Founder Mode" vs "Manager Mode" (Paul Graham, 2024) — kenapa builder tertentu sengaja tetap terlibat detail meski perusahaan sudah besar, dan kapan itu benar-benar diperlukan vs kapan jadi micromanagement
* [ ] 9.3 Timing & taruhan besar — bagaimana Wang mendirikan Scale AI di usia 19 saat melihat gap nyata (data labeling untuk ML) yang orang lain belum serius garap, dan bagaimana Huang bertaruh pada GPU general-purpose computing bertahun-tahun sebelum AI boom membuktikannya benar
* [ ] 9.4 Resourcefulness dalam constraint kecil — insting founder tidak butuh mendirikan startup untuk dipraktikkan; bisa diuji dalam skala proyek/inisiatif kecil di posisi kerja mana pun
* [ ] 9.5 Praktik: identifikasi 1 gap/peluang nyata yang Anda lihat (di tempat kerja, komunitas, atau domain minat Anda) yang belum digarap serius orang lain, tulis kenapa

Mini Project: Builder Field Note — 1 analisis gap/peluang nyata yang Anda identifikasi sendiri, plus 1 langkah kecil resourceful nyata yang sudah dicoba untuk memvalidasinya (bukan sekadar ide di atas kertas).

Status: Not started

---

## Module 10 — Capstone: Personal Builder Operating System

Description: Menyatukan seluruh 9 modul jadi satu sistem operasi pribadi yang benar-benar sudah diuji lewat eksperimen nyata sepanjang course — bukan kompilasi teori tentang orang lain.

Lessons:

* [ ] 10.1 Mengumpulkan seluruh 9 Builder Field Note (Module 1-9) dan mengidentifikasi pola yang paling konsisten terbukti berguna di proyek "lab" Anda sendiri
* [ ] 10.2 Menyusun 8 prinsip operasi pribadi (satu per tema: technical depth, first-principles, product thinking, leverage, velocity, network capital, leadership, founder instinct), tiap prinsip dengan justifikasi eksplisit dari praktisi mana ia diambil dan bukti Applied-to-Me nyata
* [ ] 10.3 Self-critique "akan bertahan tidak prinsip ini kena benturan konstrain saya" — uji tiap prinsip terhadap keterbatasan nyata (waktu, modal, posisi) alih-alih menulisnya sebagai aspirasi kosong
* [ ] 10.4 Menyusun rencana eksekusi 90 hari: 1 aksi konkret dan terukur per prinsip, dengan cara mengukur apakah itu benar-benar dijalankan
* [ ] 10.5 Roleplay tertulis "staff engineer skeptis" — pertahankan salah satu prinsip Anda terhadap tantangan tertulis, buktikan pemahaman dalam bukan kutipan permukaan

Capstone Project: **Personal Builder Operating System** — dokumen 1-2 halaman berisi 8 prinsip operasi pribadi (technical depth, first-principles, product thinking, leverage, velocity, network capital, leadership, founder instinct), masing-masing dijangkar ke pola praktisi terverifikasi (Zuckerberg/Wang/Karpathy/Huang/Carmack/Naval/Paul Graham) DAN dibuktikan dengan minimal 1 eksperimen "Applied to Me" nyata dari modul terkait, plus rencana eksekusi 90 hari yang konkret dan terukur. Review memakai `domains/tech-builder-mindset/DOMAIN.md` bagian "Review Style" (Source Grounding, Pattern-not-Personality, Applied Reality Check, Context-Awareness/Survivorship Bias Check) di atas Universal Review Rubric (`ai-los/CORE_LOS.md`).

Status: Not started

---

# Capstone Project

Description: Lihat Module 10 di atas untuk rincian lengkap. Capstone course ini SATU dengan Capstone Project Module 10 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/tech-builder-mindset/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi empat kriteria domain (Source Grounding, Pattern-not-Personality, Applied Reality Check, Context-Awareness/Survivorship Bias Check) plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, 8 prinsip operasi pribadi lengkap dengan justifikasi sumber DAN bukti Applied-to-Me nyata, dan rencana eksekusi 90 hari yang konkret dan terukur — disimpan ke `portofolio/`.

Status: Not started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: -

Current Lesson: -

Next Lesson: Menunggu approval roadmap ini dari user sebelum mulai Module 1, Lesson 1.1
