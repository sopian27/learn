# Astronomy & Life in the Universe — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Astronomy & Life in the Universe untuk Software Engineer (Astronomi Deskriptif + Astrobiologi/SETI/Fermi Paradox, dijembatani lewat simulasi & data science)

## Scope

Astronomi deskriptif/observasional (Bumi, Bulan, Matahari, Tata Surya, bintang, galaksi, black hole, neutron star, supernova, kosmologi dasar, exoplanet) DAN astrobiologi (asal-usul kehidupan, extremophile, habitable zone, biosignature, technosignature, Drake Equation, Fermi Paradox, Great Filter, SETI, Kardashev Scale, penalaran Bayesian di bawah ketidakpastian mendalam) — keduanya dijembatani eksplisit ke software engineering lewat simulasi, numerical computing, data processing, distributed computing, visualisasi, dan ML.

Domain ini adalah **sibling** dari [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]] (mekanika klasik untuk game/graphics/robotika) dan [[domains/relativity/DOMAIN.md|relativity]] (Relativitas Khusus/Umum) — TIDAK mengulang scope keduanya:

- **Mekanika orbital dasar/Hukum Kepler secukupnya untuk simulasi** dibahas di domain ini (karena jadi fondasi proyek orbit simulator), tapi derivasi mekanika klasik penuh (kinematika/dinamika/rotasi rigid body) tetap milik `physics-for-software-engineers` — domain ini merujuk ke sana untuk fondasi, tidak mengajarkan ulang dari nol.
- **Efek relativistik** (dilatasi waktu GPS, presesi orbit Merkurius, dll) sepenuhnya di luar domain ini — diarahkan ke `relativity`.
- **Astrofisika teoretis tingkat riset** (stellar structure penuh, general relativity terapan ke kosmologi) di luar scope — domain ini level "Astro 101 + data science", bukan gelar astrofisika.

Secara eksplisit domain ini TIDAK mencakup:

- **Kredensial astrofisika/astronomi formal** — ini literasi terapan + artefak yang bisa dibangun, bukan pengganti gelar astronomi terakreditasi.
- **Klaim eksistensi/non-eksistensi alien** — domain ini secara eksplisit MENOLAK memberi kesimpulan pasti soal "apakah alien ada" (lihat house rule di bawah); tujuannya melatih cara bernalar di bawah ketidakpastian mendalam, bukan menjawab pertanyaannya.
- **UFO/UAP sebagai fenomena paranormal atau teori konspirasi** — di luar scope; bila muncul, diarahkan ke pendekatan ilmiah SETI/technosignature yang legitimate, bukan spekulasi tanpa dasar (lihat juga domain `conspiracy-theories` untuk penanganan klaim konspirasi secara umum bila relevan).

## Trusted Sources / Research Priority

### Modul A: Astronomy for Software Engineers

1. **OpenStax *Astronomy 2e*** (gratis, peer-reviewed, https://openstax.org/books/astronomy-2e/) — diterbitkan Rice University, dipakai di banyak kelas "Astro 101" universitas AS sungguhan. Tulang punggung konten deskriptif Tata Surya sampai kosmologi. Cross-check: Bennett et al., *The Cosmic Perspective* (textbook komersial durable) saat OpenStax kurang mendalam di satu topik.
2. **MIT OpenCourseWare 8.282J — Introduction to Astronomy** (Prof. Saul Rappaport, https://ocw.mit.edu/courses/8-282j-introduction-to-astronomy-spring-2006/) — materi kuliah resmi MIT, memberi kedalaman kuantitatif di atas fondasi deskriptif OpenStax.
3. **NASA Science / NASA Open APIs** (https://api.nasa.gov/, https://science.nasa.gov/) — sumber federal resmi untuk fakta Tata Surya & data misi; entry point data nyata (APOD, NeoWs, DONKI, EPIC, Mars Rover Photos).
4. **JPL Horizons System** (https://ssd.jpl.nasa.gov/horizons/) — layanan efemeris resmi JPL Solar System Dynamics Group, sumber posisi/kecepatan nyata (state vector) planet/asteroid/komet/pesawat luar angkasa. Ini backbone data nyata untuk proyek orbit simulator — learner menarik initial condition sungguhan, bukan angka karangan.
5. **NASA Exoplanet Archive** (Caltech/IPAC, https://exoplanetarchive.ipac.caltech.edu/) dan **ESA Gaia Archive** (https://gea.esac.esa.int/archive/) — arsip resmi exoplanet terkonfirmasi (Kepler/K2/TESS) dan data astrometri ~1.8 miliar bintang; dipakai untuk topik exoplanet Modul A sekaligus grounding parameter Drake Equation di Modul B, dan sebagai dataset nyata untuk latihan visualisasi (mis. HR diagram dari data Gaia asli).
6. **Rein & Liu, "REBOUND: An open-source multi-purpose N-body code" (2012), *Astronomy & Astrophysics*, arXiv:1110.4876** — integrator N-body open-source standar di riset astrofisika nyata (leapfrog, WHFast, IAS15). Dipakai ganda: (a) bacaan kenapa integrasi Euler naif gagal untuk simulasi orbit, (b) codebase nyata untuk validasi hasil simulator buatan learner sendiri.
7. **Rubin Observatory / LSST Data Management** (https://www.lsst.org/about/dm) — dokumentasi resmi NSF-DOE soal pipeline data (~20 TB/malam, ~500 PB seumur hidup) — studi kasus konkret "kenapa astronomi butuh distributed computing", bukan klaim big-data generik.

**Aturan cross-check** (selaras pola `physics-for-software-engineers`): klaim numerik (stabilitas integrator, bentuk Hukum Kepler ketiga, ekspektasi kekekalan energi) selalu dicek ke sumber 1-2 (textbook/OCW) DAN divalidasi terhadap perilaku nyata REBOUND (6) atau data JPL Horizons (4) — tidak pernah dari ingatan/sumber tunggal.

### Modul B: Are We Alone? — Astrobiology, SETI, dan Fermi Paradox

1. **NASA Astrobiology Program** (https://science.nasa.gov/astrobiology/program-overview/) — program institusional resmi NASA; dipakai sebagai sumber otoritatif untuk pertanyaan riset astrobiologi mainstream saat ini — bukan untuk klaim temuan, karena ini program riset, bukan pernyataan kesimpulan.
2. **Literatur extremophile peer-reviewed** (mis. PMC6476344, "Living at the Extremes") — fakta kokoh: extremophile memperluas batas known habitability kehidupan-Bumi; TIDAK dengan sendirinya menyiratkan kehidupan di tempat lain — lompatan inferensi ini WAJIB ditandai eksplisit setiap kali dibuat.
3. **Sandberg, Drexler & Ord, "Dissolving the Fermi Paradox" (2018), arXiv:1806.02404** — reanalisis rigorous (Future of Humanity Institute) yang mengganti estimasi titik parameter Drake dengan distribusi probabilitas + Monte Carlo, menemukan probabilitas signifikan kita sendirian di alam semesta teramati.
4. **Westby & Conselice, "The Astrobiological Copernican... Limits" (2020), *ApJ* 896:58, arXiv:2004.03968** — peer-reviewed, memakai data pembentukan bintang galaksi nyata + asumsi "Copernican" eksplisit, mengestimasi ~36 peradaban aktif berkomunikasi di Bimasakti pada skenario "Strong". **Kedua paper ini WAJIB diajarkan berdampingan** — persamaan sama, kesimpulan berbeda drastis murni karena asumsi ketidakpastian berbeda — ini bukti hidup terbaik bahwa Drake Equation adalah kerangka mengorganisir ketidaktahuan, bukan mesin prediksi.
5. **Robin Hanson, "The Great Filter" (1998) + follow-up 2021** — sumber asal reframing "Great Filter"; ekonom GMU, bukan astrobiolog profesional — kerangka individu yang rigorous tapi bukan peer-reviewed jurnal astronomi, disilangkan ke sumber 3-4.
6. **Nick Bostrom, "Existential Risks" (2002) + karya anthropic reasoning** — sumber penghubung eksplisit ke penalaran Bayesian/anthropic di bawah ketidakpastian mendalam — jembatan langsung ke domain `critical-thinking`.
7. **SETI Institute** (https://www.seti.org/) — organisasi riset nonprofit nyata (berdiri 1984, bukan fringe), mengoperasikan Allen Telescope Array. Fakta yang WAJIB ditekankan: SETI belum pernah mendeteksi sinyal ekstraterestrial terkonfirmasi — ini fakta, bukan opini editorial.
8. **Kasus studi JWST K2-18b (2023-2025)** — Madhusudhan et al. melaporkan sinyal DMS tentatif (~3σ) 2025, lalu reanalisis independen (arXiv:2505.13407, arXiv:2508.05961) menemukan ~87.5% retrieval TIDAK mendukung deteksi tersebut, dan studi NASA Juli 2025 tidak menemukan bukti DMS konklusif. **Dipakai sebagai contoh kerja wajib** untuk melatih disiplin "klaim awal → tingkat keyakinan → reanalisis independen → konsensus saat ini". Catatan: satu hasil pencarian berjudul "First Detection of Exoplanetary Cannabinoids... K2-18b" (~Maret 2026) kemungkinan besar paper April Fools' — TIDAK PERNAH dikutip sebagai sains nyata, hanya (bila disebut sama sekali) sebagai contoh peringatan "jangan percaya judul/listing arXiv tanpa cek venue & isi".

**Klasifikasi status epistemik wajib** untuk setiap klaim besar Modul B: Firm Fact (mis. kehidupan ada di Bumi, tahan extremophile) vs Model/Inference (habitable zone, dari fisika+data astronomi kokoh) vs Open Hypothesis (mekanisme abiogenesis) vs Speculation (lokasi Great Filter) — tidak pernah dicampur sebagai setara.

## Applied Practice Definition

Applied practice SELALU kode yang berjalan dan/atau tulisan argumentatif yang bisa diverifikasi — konsisten dengan pola `physics-for-software-engineers` (bobot kode) dikombinasikan pola `critical-thinking` (bobot penalaran tertulis eksplisit):

- **Modul A**: simulasi kecil per konsep (mis. simulasi dua-benda dengan data JPL Horizons asli, perbandingan integrator Euler vs symplectic pada energi yang sama), validasi numerik eksplisit (kekekalan energi/momentum sudut), dan visualisasi dari dataset nyata (mis. HR diagram dari Gaia).
- **Modul B**: setiap klaim besar (habitable zone, Drake Equation, Great Filter) WAJIB dijalankan lewat template "Apa yang kita tahu? Apa yang kita infer? Apa yang tidak kita tahu? Bukti apa yang akan mengubah kesimpulan?" secara tertulis eksplisit, plus perhitungan Drake Equation sendiri dengan MINIMAL dua skenario asumsi berbeda (mis. mereplikasi kontras Sandberg/Drexler/Ord vs Westby/Conselice) untuk membuktikan pemahaman bahwa hasil bergantung penuh pada asumsi.

## Project Tiers

- **Mini Project** (per modul): satu simulasi/skrip kecil (Modul A) atau satu analisis epistemik tertulis dari satu klaim (Modul B).
- **Intermediate Project**: **N-body simulator kecil** memakai data JPL Horizons nyata dengan minimal 2 integrator dibandingkan (energy drift terukur) setelah Modul A selesai; **analisis Drake Equation komparatif** (replikasi 2 skenario asumsi berbeda + justifikasi tertulis) setelah Modul B selesai.
- **Capstone / Portfolio Project** (akhir course): pilih salah satu atau gabungan — **Planetary-Orbit Simulator** lengkap (integrator terpilih tervalidasi + visualisasi + data JPL Horizons nyata) DAN **Fermi Paradox Research Paper** (perhitungan Drake Equation sendiri + tinjauan Great Filter/SETI + kesimpulan eksplisit berbasis template epistemik di atas, TIDAK pernah "aliens exist/don't exist" sebagai simpulan tunggal) — disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100):

- **Physical & Numerical Correctness** (Modul A) — sama seperti lensa `physics-for-software-engineers`: apakah simulasi konsisten dengan hukum fisika yang mendasarinya (energi/momentum sudut kekal kecuali memang didesipasi)?
- **Real-Data Fidelity** (Modul A) — apakah simulasi divalidasi terhadap data JPL Horizons/REBOUND nyata, atau hanya angka karangan yang "terlihat masuk akal"?
- **Epistemic Discipline** (Modul B, WAJIB, paling ketat di domain ini) — apakah setiap klaim diberi label Firm Fact/Inference/Hypothesis/Speculation secara eksplisit? Klaim tunggal "aliens ada" atau "aliens tidak ada" tanpa kualifikasi ketidakpastian otomatis GAGAL review ini, tidak peduli seberapa baik argumen lainnya.
- **Assumption Sensitivity** (Modul B) — apakah learner menunjukkan paham bahwa mengubah asumsi (bukan data baru) bisa mengubah kesimpulan Drake Equation berorde magnitudo (seperti kontras Sandberg/Drexler/Ord vs Westby/Conselice)?

## Assessment Form

Concept-check singkat setelah teori (termasuk uji klasifikasi epistemik terhadap klaim baru yang belum dibahas), problem set numerik terarah gaya MIT OCW dengan kunci jawaban untuk Modul A, dan review tertulis (learner mempertahankan kesimpulan Modul B-nya terhadap pertanyaan "bukti apa yang akan mengubah pendapatmu?" — kegagalan menjawab ini secara konkret dianggap belum menguasai modul).

## Practitioner Reference Frame

- **Modul A**: astronom akademik (gaya MIT OCW/OpenStax — rigor deskriptif & kuantitatif) vs research software engineer astronomi (gaya tim REBOUND/Rubin Observatory — fokus performa & skala data) vs data scientist (gaya kursus "Data-Driven Astronomy" — ML/statistik terapan ke data astronomi nyata).
- **Modul B**: astrobiolog institusional (NASA Astrobiology — riset hati-hati, tidak berspekulasi di luar data) vs peneliti existential-risk/anthropic reasoning (Bostrom/Hanson/Sandberg — kerangka bernalar formal di bawah ketidakpastian ekstrem) vs SETI Institute (metodologi deteksi teknosignatur nyata, dikontraskan tegas dengan karikatur budaya pop soal "pencarian alien").

## Domain-Specific Standards

`domains/astronomy-and-life-in-the-universe/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit — yang PALING KETAT di seluruh repo untuk topik ini:

- **Tidak pernah menyatakan alien ada atau tidak ada.** Setiap lesson/deliverable Modul B WAJIB menyatakan eksplisit: apa yang diketahui (fact), apa yang diinferensi, apa yang tidak diketahui, dan bukti apa yang akan mengubah kesimpulan — pelanggaran aturan ini adalah kegagalan otomatis di Review Style "Epistemic Discipline" di atas, terlepas dari kualitas argumen lain.
- **Batas relativitas & mekanika klasik dinyatakan eksplisit**, mengikuti pola house rule `physics-for-software-engineers`: begitu topik menyentuh area relativistik atau mekanika klasik mendalam, arahkan ke domain terkait, jangan dijawab sekilas dengan pendekatan tidak lengkap.
- **Validasi numerik wajib untuk Modul A** — setiap simulasi yang dianggap selesai harus punya bukti eksplisit kesesuaian dengan hukum fisika (plot/angka energi, perbandingan ke solusi analitik/REBOUND), bukan cuma "terlihat benar secara visual".
- **Literasi, bukan kredensial formal** — sama seperti `physics-for-software-engineers`, domain ini tidak menggantikan gelar astronomi/astrofisika akreditasi.

## Domain Goal

Learner mampu membangun sendiri komponen inti orbit simulator (integrator numerik tervalidasi terhadap data nyata JPL Horizons/REBOUND) DAN bernalar secara Bayesian/epistemically-disciplined tentang pertanyaan kehidupan di alam semesta — dibuktikan lewat dua capstone (Planetary-Orbit Simulator + Fermi Paradox Research Paper) yang secara eksplisit tidak pernah overclaim kesimpulan di luar bukti yang tersedia. Ini eksplisit BUKAN kredensial astronomi formal — kemampuan applied engineering + reasoning-under-uncertainty yang menghubungkan latar belakang software engineering learner dengan domain astronomi dan astrobiologi.

## Registration History

Registered 2026-08-11, domain pack + course roadmap approved. Dibangun sebagai bagian roadmap `life-mastery` (Stop 9).
