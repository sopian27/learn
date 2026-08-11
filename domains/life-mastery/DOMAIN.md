# Life Mastery — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Life Mastery (Personal Operating System — Power & Strategi, Kecerdasan Emosional & Makna, Proyek Rp1 Miliar, Location-Independent Income, dan World Travel)

## Scope

Domain ini adalah **orkestrator + pemilik konten yang belum punya rumah** dari kurikulum "Life Mastery" yang lebih besar (13 phase). Prinsipnya: domain ini TIDAK mengulang apa pun yang sudah dimiliki domain lain di repo — ia hanya mengajarkan langsung topik yang genuinely tidak punya rumah, dan menyusun (orchestrate) urutan lintas-domain lewat `courses/life-mastery/roadmap.md`.

Konten yang diajarkan LANGSUNG oleh domain ini:

- **Power, Influence & Organizational Strategy** — dinamika kekuasaan, status, pengaruh, insentif, politik organisasi, reputasi, negosiasi, kepemimpinan — dengan sikap anti-manipulasi eksplisit. Materi seed (48 Laws of Power) TIDAK diperlakukan sebagai ground truth, selalu dikritisi lewat riset psikologi sosial nyata.
- **Emotional Intelligence, Acceptance & Meaning** — ikhlas/melepaskan, kecerdasan emosional, optimisme realistis, limiting beliefs, purpose/makna hidup — mengisi celah yang TIDAK dicakup `character-development` (etos kerja/rasa hormat) maupun `self-love` (self-compassion/self-worth). Materi seed: Ikhlas dan Melepaskan, Emotional Intelligence (Goleman), The Power of Positive Thinking, The Mountain Is You, Think Like a Monk, The Four Agreements, Think and Grow Rich.
- **The Rp1 Miliar Project** — proyek skenario finansial yang mengonsumsi skill dari `personal-finance` (sudah selesai) + konteks karier software engineer, tapi bukan pemilik salah satu domain itu sendiri — proyek gabungan yang butuh rumah sendiri.
- **Location-Independent Income** — jalur pendapatan yang tidak terikat lokasi (remote SWE, freelancing, consulting, content creation, SaaS/digital product, AI-enabled services).
- **World Travel While Working Online** — logistik digital nomad: visa, pajak, budgeting, asuransi, keamanan, adaptasi budaya — konsumen langsung dari Location-Independent Income di atas.
- **Capstone: Personal Life Operating System** — sintesis SELURUH 13 phase (termasuk output dari domain lain) jadi satu dokumen hidup.

Konten yang HANYA di-orchestrate (menunjuk ke course yang sudah ada, TIDAK diajarkan ulang di sini):

- Disiplin & habit (Phase 2) → `character-development`
- Penerimaan diri, resiliensi, approval-seeking (irisan Phase 1 & 3) → `self-love`
- Komunikasi & sosialisasi (Phase 4) → `communication-skills`
- Financial Intelligence (Phase 6) → `personal-finance`
- International Software Engineer (Phase 11) → `software-engineering` (courses/system-design, courses/backend-fundamentals, + course "Freelance & International Career Readiness" yang masih perlu dibangun)
- Cybersecurity/malware (Phase 8) → course baru `courses/systems-security-malware-analysis/roadmap.md` di domain `software-engineering` (BUKAN di sini — lihat catatan penamaan di roadmap)
- Astronomy + Alien Life (Phase 9-10) → domain sibling baru `astronomy-and-life-in-the-universe`
- Evidence classification (A-E, Section 3 master prompt) → lapisan epistemik dari domain `critical-thinking`, dipakai bukan diajarkan ulang

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Etos kerja, disiplin, kerendahan hati, rasa hormat ke orang lain** — domain [[domains/character-development/DOMAIN.md|character-development]].
- **Self-compassion, self-worth independen dari validasi eksternal, boundary, body image** — domain [[domains/self-love/DOMAIN.md|self-love]].
- **Mekanika percakapan/komunikasi interpersonal** — domain [[domains/communication-skills/DOMAIN.md|communication-skills]].
- **Perencanaan keuangan pribadi umum** (cash flow, investasi, asuransi, pensiun) — domain [[domains/personal-finance/DOMAIN.md|personal-finance]]. Phase 7 (Rp1 Miliar Project) di domain ini MENGONSUMSI skill itu, tidak mengajarkannya ulang.
- **Framework berpikir kritis, bias kognitif, evidence classification generik** — domain [[domains/critical-thinking/DOMAIN.md|critical-thinking]]. Domain ini MEMAKAI skala A-E dari sana, tidak mendefinisikan ulang.
- **AppSec/backend security (OWASP, auth, injection, supply chain)** — course `courses/cybersecurity/roadmap.md` di domain `software-engineering` (sudah ada, approved 2026-08-06). **Malware/OS/network security generalist** — course baru `courses/systems-security-malware-analysis/roadmap.md`, domain `software-engineering`, bukan di sini.
- **Astronomi deskriptif, astrobiologi, Fermi Paradox** — domain sibling [[domains/astronomy-and-life-in-the-universe/DOMAIN.md|astronomy-and-life-in-the-universe]].
- **Terapi klinis** untuk depresi, trauma, gangguan kepribadian, atau kondisi yang butuh diagnosis profesional — di luar scope, wajib eskalasi (lihat "Domain-Specific Standards").
- **Nasihat hukum/pajak/imigrasi yang mengikat** — modul Location-Independent Income & World Travel mengajarkan KONSEP dan sumber resmi untuk dicek, bukan pengganti konsultan pajak/imigrasi berlisensi.

## Trusted Sources / Research Priority

Skala evidence dipakai konsisten di seluruh domain ini (Section 3 master prompt, selaras dengan pendekatan `critical-thinking`): **A** = evidence-backed · **B** = plausible/useful framework · **C** = philosophical/subjective · **D** = controversial/unsupported · **E** = potentially misleading.

### Power, Influence & Organizational Strategy

1. **Dacher Keltner, "The Power Paradox" (2016) + UC Berkeley Greater Good Science Center** — riset eksperimental terkontrol soal bagaimana kekuasaan diberikan (lewat perilaku prososial) dan terkikis (lewat "empathy deficit"). Tier **A** untuk temuan empathy-deficit; klaim preskriptif buku lebih ke **B**.
2. **Robert Cialdini, "Influence: The Psychology of Persuasion"** — enam/tujuh prinsip pengaruh (reciprocity, commitment, social proof, authority, liking, scarcity, unity), hasil dekade eksperimen terkontrol. Tier **A**, sekaligus jadi kurikulum pertahanan (setiap prinsip yang dipakai untuk compliance juga dipakai untuk manipulasi).
3. **Paulhus & Williams, "The Dark Triad of Personality" (2002)** — paper peer-reviewed fondasional (narcissism/Machiavellianism/psychopathy). Tier **A**, dasar untuk mengenali pola power-seeking "gelap" secara klinis, bukan anekdot.
4. **O'Boyle et al., meta-analisis Dark Triad vs kinerja kerja** — Tier **A** (meta-analitik). Temuan kunci: Machiavellianism berkorelasi dengan counterproductive work behavior & turnover, TIDAK memprediksi kinerja lebih baik — langsung membantah janji implisit 48 Laws bahwa taktik kejam terbayar jangka panjang.
5. **Kacmar & Ferris, Perceptions of Organizational Politics Scale (POPS), 1991-1992** — Tier **A**, instrumen tervalidasi untuk memetakan politik kantor nyata, dipakai untuk latihan "power map" tanpa mengandalkan folk wisdom.
6. **Cheng, Tracy, Henrich et al., "Two Ways to the Top" (2013)** — Tier **A**, riset lapangan longitudinal (kohort MBA nyata) membuktikan dominance (rasa takut) dan prestige (kompetensi/kemurahan hati) adalah dua jalur status yang independen — dipakai untuk menilai mana "law" yang dominance-coded (rapuh) vs prestige-coded (tahan lama).
7. **Robert Axelrod, "The Evolution of Cooperation" (1984)** — Tier **A**, turnamen iterated prisoner's dilemma membuktikan strategi tit-for-tat (kooperatif tapi tidak bisa dieksploitasi) mengalahkan strategi murni eksploitatif dalam repeated game — bukti pembanding utama untuk law yang berbasis pengkhianatan permanen.
8. **Roger Fisher, William Ury & Bruce Patton, "Getting to Yes" (Harvard Program on Negotiation)** — Tier **B/A**, principled negotiation (pisahkan orang dari masalah, fokus interest bukan posisi) sebagai penawar etis terhadap framing win/lose 48 Laws.
9. **Jennifer Freyd, konstruk DARVO (Deny, Attack, Reverse Victim and Offender)** — Tier **A**, dipakai khusus untuk tahap "Defense" wajib di modul ini: pola yang bisa dikenali saat seseorang membalik posisi korban dalam konflik kekuasaan.
10. **Niccolò Machiavelli (The Prince), Sun Tzu (The Art of War), Robert Greene (48 Laws of Power)** — Tier **C untuk dua yang pertama** (teks filosofis/historis, bukan riset — bernilai untuk tahap "Historical Example" tapi klaim perilakunya harus diverifikasi ke sumber 1-9). **Tier D/E untuk 48 Laws of Power** — Greene adalah kurator historis/storyteller, bukan ilmuwan perilaku; buku ini tidak falsifiable (anekdot dipilih untuk cocok dengan tesis, tanpa kasus gagal, tanpa engagement ke literatur empiris). Setiap "law" WAJIB diperlakukan sebagai hipotesis yang dijalankan lewat pipeline di bawah, tidak pernah sebagai kebenaran awal.

**Aturan cross-check untuk pilar ini**: klaim empiris spesifik (mis. "kekuasaan mengurangi empati", "Machiavellianism memprediksi X") harus tertelusur ke sumber 1-9 di atas. Klaim yang hanya bersumber dari Greene/Machiavelli/Sun Tzu/blog praktisi WAJIB dilabeli eksplisit sebagai framework/filosofis (C) dan selalu dipasangkan dengan tahap Defense — bahkan saat disajikan sebagai "Ethical Application".

### Emotional Intelligence, Acceptance & Meaning

1. **Mayer, Salovey & Caruso, "Emotional Intelligence: New Ability or Eclectic Traits?" (2008, American Psychological Association)** — Tier **A**, model empat cabang (persepsi/penggunaan/pemahaman/manajemen emosi) dari pencipta asli konstruk EI (mendahului popularisasi Goleman 1995). Dipakai sebagai anchor "apa itu EI sungguhan", dibedakan tegas dari kuis EQ populer.
2. **Kritik validitas konstruk EI** (mis. Locke, "Why Emotional Intelligence Is an Invalid Concept", *Journal of Organizational Behavior*, 2005) — self-report "trait EI" sering overlap dengan Big Five (terutama extraversion/neuroticism), dan status EI sebagai konstruk tunggal masih diperdebatkan. WAJIB disertakan tiap kali EI diajarkan sebagai sains yang settled.
3. **Daniel Goleman, "Emotional Intelligence" (1995)** — Tier **B**, buku yang mempopulerkan EI; penulis jurnalis sains bukan periset asli, beberapa klaim ("EI lebih penting dari IQ") melampaui bukti primer.
4. **Martin Seligman, riset Learned Optimism (University of Pennsylvania)** — Tier **A/B**, riset explanatory-style bertahun-tahun terhubung ke pencapaian/kesehatan mental; sebagian ekstrapolasi populer ("optimisme menyembuhkan segalanya") overreach.
5. **Gabriele Oettingen, riset Mental Contrasting/WOOP (NYU/Hamburg), ~25 tahun studi terkontrol** — Tier **A**, temuan kritis: fantasi positif murni tentang masa depan yang sudah tercapai justru MENURUNKAN usaha & pencapaian dibanding kontrol — bukti langsung batas "positive thinking" murni. WOOP (Wish-Outcome-Obstacle-Plan) terbukti lebih efektif.
6. **Kritik Albert Ellis (pendiri REBT, cikal bakal CBT) terhadap Norman Vincent Peale, "The Power of Positive Thinking" (1952)** — Ellis secara eksplisit menyebut teknik Peale menyerupai autosugesti hipnotis dan "dangerous, distorted, and unrealistic". Tier **D/E** untuk Peale sebagai sains psikologi — WAJIB dilabeli sebagai buku yang dikritik langsung oleh tokoh sentral psikologi klinis modern, bukan klasik self-help yang netral.
7. **Alfred Adler, Individual Psychology (teori primer)** — Tier **B/C**, mazhab klinis historis nyata (masih ada jurnal/organisasi profesi aktif), lebih empiris dari Freud/Jung tapi mendahului standar RCT modern.
8. **Kishimi & Koga, "The Courage to Be Disliked" (2013)** — Tier **B/C**, popularisasi dialog Sokratik dari Adlerian psychology yang menyederhanakan klaim Adler (mis. "trauma tidak ada, hanya makna yang kau berikan" disajikan sebagai absolut) — berguna untuk aksesibilitas, bukan pengganti teks primer.
9. **David Nasaw (biografer utama Andrew Carnegie) soal klaim wawancara Napoleon Hill × Carnegie di "Think and Grow Rich" (1937)** — Nasaw menyatakan "no evidence of any sort that Carnegie and Hill ever met" dan tidak ada catatan pendukung untuk subjek wawancara lain yang diklaim Hill (Roosevelt, Rockefeller, Edison). Tier **D/E** untuk framing faktual/biografis buku ini — WAJIB dilabeli sebagai masalah kredibilitas terdokumentasi. Teknik individual yang disentuhnya (goal visualization, persistence) punya bukti terpisah (lihat sumber 5) — pisahkan teknik dari narasi palsu buku.
10. **Steven Hayes, Acceptance and Commitment Therapy (ACT); meta-analisis A-Tjak et al. 2015, Öst 2014** — Tier **A**, effect size ~0.42, diakui APA Division 12 untuk nyeri kronis (strong support) dan depresi/OCD/kecemasan (modest/probably efficacious). Ini adalah **sepupu evidence-backed dari "ikhlas"** — dipakai sebagai jembatan empiris, TAPI ikhlas sendiri (konsep teologis, muatan penyerahan diri pada kehendak Tuhan) tetap dinilai **C** — ACT analog struktural, bukan pembuktian klaim keagamaan.
11. **Jay Shetty, "Think Like a Monk" (2020)** dan **Don Miguel Ruiz, "The Four Agreements" (1997)** — Tier **C**, sintesis personal-brand/ajaran spiritual Toltec; berguna sebagai kerangka nilai/praktik, tidak mengklaim jadi riset, klaim psikologis spesifik di dalamnya harus dicek ke sumber primer.

### The Rp1 Miliar Project & Location-Independent Income

1. **Nicholas Bloom (Stanford) / WFH Research — Survey of Working Arrangements and Attitudes (SWAA), berjalan sejak Mei 2020** — Tier **A**, satu-satunya riset akademik ekonomi tenaga kerja yang genuinely rigorous di pilar ini (bukan laporan vendor). Dipakai sebagai anchor klaim "seberapa bernilai kerja remote/fleksibel bagi pekerja" (setara ~10% kenaikan gaji dalam sejumlah estimasi).
2. **Portal visa resmi pemerintah**: Portugal (`vistos.mne.gov.pt`, `aima.gov.pt`), Spanyol (`exteriores.gob.es`), Estonia (`politsei.ee`/PPA) — Tier **A/primer**, tapi SELALU ditandai perlu verifikasi ulang di setiap course build (angka ambang penghasilan biasanya diindeks ke upah minimum lokal dan direvisi tahunan).
3. **OECD, "Should OECD countries develop new Digital Nomad Visas?" (2022)** — Tier **A/official**, kerangka kebijakan lintas-negara dan basis tie-breaker pajak (OECD Model Tax Convention Art. 4: permanent home → center of vital interests → habitual abode → nationality).
4. **Upwork Freelance Forward / Future Workforce Index (2025), Deel "State of Global Hiring Report" (2025), Remote.com Global Workforce Trends (2025)** — Tier **B (data vendor besar, sampel platform sendiri, bukan sampel populasi acak)** — dipakai untuk gambaran realistis penghasilan freelance/remote, SELALU disertai catatan bias sampling.
5. **Prinsip residensi pajak umum** (183-day rule sebagai heuristik + tie-breaker OECD, dicek silang ke KPMG/PwC country tax notes) — konsep **durable** yang aman diajarkan tanpa verifikasi ulang tahunan: "visa digital nomad ≠ otomatis bebas pajak" adalah kesalahan paling umum & mahal yang harus ditekankan, termasuk catatan khusus **citizenship-based taxation AS** (WN AS tetap kena pajak worldwide income terlepas dari residensi).
6. **Nomad List (nomads.com)** — Tier **crowdsourced, bukan sumber resmi** — hanya dipakai sebagai sinyal arah kasar dengan disclaimer eksplisit, disilangkan ke Numbeo atau statistik nasional resmi sebelum dipakai sebagai angka otoritatif.

**Aturan staleness untuk pilar ini**: SEMUA angka spesifik (ambang penghasilan visa, daftar negara yang menawarkan digital nomad visa saat ini [~48-51 negara per sumber agregator 2026], rata-rata penghasilan freelance) WAJIB ditandai "verified as of [tanggal]" dan diverifikasi ulang setiap kali modul di-refresh — TIDAK PERNAH di-hardcode dari riset ini tanpa tanggal. Konsep yang timeless (mekanisme residensi pajak, bedanya visa vs status pajak, bentuk umum distribusi penghasilan freelance yang high-variance) aman diajarkan tanpa verifikasi ulang.

Tidak pernah membangun satu lesson di pilar mana pun hanya dari sumber Tier C/D/E (Greene, Peale, Hill, Nomad List) tanpa menyilangkan ke sumber Tier A/B di atas, sesuai bar "Vetting a Source" di `standards/UNIVERSAL_STANDARDS.md`.

## Applied Practice Definition

Karena domain ini multi-topik, Applied Practice punya bentuk berbeda per pilar — tapi semua WAJIB pakai data/situasi nyata milik learner, bukan hipotetis murni:

- **Power & Strategy**: exercise "map real power structure vs org chart", "influence-principle audit" mingguan (mengklasifikasi setiap penggunaan prinsip Cialdini sebagai legitimate vs manipulatif), dan deliverable inti — menjalankan satu "law" penuh lewat pipeline Principle → Historical Example → Psychology → Modern Workplace Example → Ethical Application → Abuse Case → Defense.
- **Emotional Intelligence, Acceptance & Meaning**: jurnal reflektif terstruktur (bukan freeform) yang menerapkan satu teknik (mis. WOOP, ACT defusion, cognitive reappraisal) ke situasi nyata yang sedang dihadapi learner minggu itu, dengan hasil/observasi ditulis sebelum dan sesudah.
- **Rp1 Miliar Project & Location-Independent Income**: model finansial nyata (bukan angka contoh orang lain) memakai pendapatan/pengeluaran learner sendiri, dengan minimal 3 skenario karier dibandingkan side-by-side dan asumsi eksplisit (growth rate, saving rate, return, inflasi) yang bisa dipertanggungjawabkan.
- **World Travel**: rencana budget & logistik nyata untuk satu window waktu tertentu (bukan "someday"), dicek terhadap sumber resmi terbaru untuk negara yang dipilih.

## Project Tiers

- **Mini Project** (per modul): satu deliverable per pilar sesuai Applied Practice Definition di atas (mis. satu power map, satu entry jurnal WOOP, satu skenario finansial tunggal).
- **Intermediate Project** (per beberapa modul): gabungan lintas modul dalam satu pilar — mis. "Power & Defense Playbook" (5 law yang sudah dijalankan penuh lewat pipeline) atau "Rp1 Miliar Roadmap draft" (3-5 skenario dibandingkan).
- **Capstone / Portfolio Project** (akhir course, sekaligus akhir SELURUH life-mastery roadmap): **Personal Life Operating System** — dokumen tunggal yang menyintesis output dari domain ini DAN seluruh domain yang di-orchestrate (character-development, self-love, communication-skills, personal-finance, critical-thinking, software-engineering career track, systems-security-malware-analysis, astronomy-and-life-in-the-universe) — lihat struktur 19-poin di `courses/life-mastery/roadmap.md`. Disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak juga dicek lewat lensa berikut:

- **Evidence Discipline** — apakah setiap klaim faktual/psikologis diberi label A-E secara eksplisit dan tertelusur ke sumber di atas, atau diterima mentah dari buku populer?
- **Anti-Manipulation Check** (khusus Power & Strategy) — apakah taktik yang dipelajari lolos tes "kalau pihak lain tahu persis apa yang saya lakukan dan kenapa, apakah dia tetap merasa dihormati?" Setiap deliverable WAJIB menyertakan tahap Defense, tidak boleh berhenti di Ethical Application saja.
- **Realism Check** (khusus Rp1 Miliar Project & Location-Independent Income) — apakah asumsi finansial/karier realistis (growth rate, saving rate, timeline) atau motivational-assumption yang tidak teruji? Tidak ada janji "pasti kaya/pasti bisa keliling dunia".
- **Toxic Positivity / Overclaiming Check** (khusus EI/Meaning) — apakah refleksi mengakui kesulitan nyata (bukan menyangkalnya), dan apakah klaim buku self-help dipisahkan tegas dari bukti psikologis riil?
- **Currency Check** (khusus Location-Independent Income & World Travel) — apakah angka/aturan (visa, pajak, biaya hidup) diberi tanggal verifikasi, atau di-hardcode tanpa disclaimer staleness?

## Assessment Form

Concept-check singkat setelah bagian teori tiap lesson (termasuk uji klasifikasi evidence A-E terhadap klaim baru yang belum pernah dibahas), self-audit memakai lensa Review Style di atas sebelum submit Applied Practice, dan — khusus pilar Power & Strategy — evaluasi skenario (diberi satu "law" baru yang belum diajarkan, learner menjalankan pipeline lengkap secara mandiri sebagai uji kompetensi).

## Practitioner Reference Frame

Bandingkan lensa berbeda per pilar untuk masalah yang sama:

- **Power & Strategy**: psikolog sosial akademik (Keltner/Cialdini — riset terkontrol) vs penulis strategi klasik (Machiavelli/Sun Tzu — filosofi preskriptif) vs praktisi negosiasi institusional (Harvard PON) — dikontraskan eksplisit dengan storyteller non-empiris (Greene).
- **EI/Meaning**: periset ability-model EI (Mayer/Salovey) vs psikolog klinis (Ellis/Hayes) vs penulis popularisasi (Goleman/Shetty/Ruiz/Hill) — perbedaan rigor ditekankan eksplisit tiap kali.
- **Rp1 Miliar/Location-Independent**: fee-only planner/CFP-style (dari `personal-finance`) vs komunitas FIRE (savings-rate agresif) vs data ekonom tenaga kerja akademik (Bloom/WFH Research) vs laporan vendor platform (Upwork/Deel) — trade-off cost/rigor/personalisasi ditekankan.

## Domain-Specific Standards

`domains/life-mastery/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Tidak ada taktik manipulatif diajarkan sebagai tujuan.** Konsisten dengan house rule `communication-skills`: setiap teknik power/influence yang diajarkan harus lolos tes "kalau pihak lain tahu persis apa yang saya lakukan dan kenapa, apakah dia tetap merasa dihormati?" — teknik yang gagal tes ini hanya diajarkan sebagai Abuse Case + Defense, tidak pernah sebagai rekomendasi.
- **Tidak ada janji hasil pasti.** "Pasti kaya", "pasti sukses", "pasti bisa keliling dunia" ditolak eksplisit di seluruh Rp1 Miliar Project & Location-Independent Income/World Travel — selalu skenario dengan asumsi eksplisit dan rentang hasil, bukan janji tunggal.
- **Evidence classification wajib, bukan opsional.** Setiap konsep dari buku seed (48 Laws, Think and Grow Rich, Power of Positive Thinking, dll) WAJIB diberi label A-E sebelum dipakai dalam lesson, mengikuti Trusted Sources di atas — tidak pernah diperlakukan sebagai kebenaran karena populer.
- **Staleness eksplisit untuk fakta terkini.** Modul Location-Independent Income & World Travel WAJIB menyertakan tanggal verifikasi untuk setiap angka visa/pajak/biaya hidup, dan mengarahkan learner ke sumber resmi untuk re-cek sebelum bertindak, mengikuti disiplin `standards/UNIVERSAL_STANDARDS.md`.
- **Eskalasi ke profesional**: refleksi EI/Meaning yang menunjukkan indikasi depresi klinis, trauma aktif, atau ide self-harm diarahkan ke psikolog/psikiater berlisensi (pola sama dengan `character-development`/`self-love`); keputusan finansial/pajak/imigrasi besar diarahkan ke profesional berlisensi (planner/akuntan pajak/konsultan imigrasi) sebelum dieksekusi nyata — course ini literasi, bukan pengganti nasihat profesional yang mengikat.

## Domain Goal

Learner memiliki: (1) pemahaman power/pengaruh yang evidence-aware dan anti-manipulatif, teruji lewat pipeline Principle→Defense yang bisa dijalankan mandiri pada situasi baru; (2) sistem regulasi emosi & makna yang membedakan tegas antara teknik ber-evidence (ACT, WOOP, cognitive reappraisal) dan framework filosofis/populer (ikhlas, Four Agreements, Think Like a Monk) tanpa mencampur keduanya sebagai fakta setara; (3) roadmap finansial Rp1 Miliar yang realistis dengan skenario karier terbandingkan; (4) pemahaman jalur location-independent income yang applicable dan world-travel plan yang hanya direkomendasikan saat fondasi finansial cukup kuat — seluruhnya disintesis jadi Personal Life Operating System di `portofolio/` sebagai capstone SELURUH kurikulum life-mastery, bukan hanya domain ini sendiri.
