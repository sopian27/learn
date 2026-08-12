# Teknologi Roket — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Teknologi Roket (Rocket Technology) — propulsi, struktur, aerodinamika peluncuran, dan mekanika orbit dasar untuk kendaraan peluncur (launch vehicle), dari fondasi fisika sampai desain kendaraan peluncur tingkat literasi-terapan.

## Scope

Roket sebagai sistem rekayasa utuh, dari prinsip dasar sampai desain kendaraan peluncur nyata:

- **Sejarah & fondasi**: dari roket bubuk mesiu (Congreve) ke Goddard/Tsiolkovsky/von Braun/Korolev, era Space Race, sampai era komersial modern (SpaceX, Rocket Lab, Blue Origin, ISRO, dll) — dipakai untuk konteks, bukan trivia.
- **Persamaan roket Tsiolkovsky & dasar propulsi**: mass ratio, delta-v, specific impulse (Isp), thrust, exhaust velocity — fondasi kuantitatif semua modul berikutnya.
- **Jenis propulsi kimia**: solid, liquid (bipropellant/monopropellant), hybrid — siklus mesin (gas generator, staged combustion, expander cycle), perbandingan trade-off (kompleksitas vs performa vs re-usability).
- **Propulsi non-kimia tingkat literasi**: electric propulsion (ion/Hall-effect thruster), dan pengantar konsep nuclear thermal/nuclear electric propulsion — secukupnya untuk paham kenapa dipakai di misi tertentu (mis. satelit, deep space probe), bukan desain reaktor (itu domain [[domains/nuclear-technology/DOMAIN.md|nuclear-technology]]).
- **Struktur & material roket**: tangki propelan, rangka (airframe), thermal protection system (TPS) untuk re-entry, trade-off material (aluminium-lithium, komposit, baja stainless ala Starship) — level literasi desain, bukan structural engineering penuh.
- **Aerodinamika peluncuran**: drag, dynamic pressure (max-Q), stabilitas (center of pressure vs center of gravity), fin design dasar — cukup untuk memahami kenapa profil peluncuran didesain seperti itu.
- **Staging & desain misi**: single vs multi-stage, parallel staging (booster + core), delta-v budgeting untuk mencapai orbit, trade-off jumlah stage vs efisiensi.
- **Mekanika orbit dasar untuk insersi**: orbital elements dasar, kecepatan orbit/escape velocity, Hohmann transfer, gravity turn ascent profile — dibatasi pada apa yang dibutuhkan untuk desain misi peluncuran, TIDAK sampai desain trajectory antarplanet/deep-space navigation lanjutan.
- **Guidance, Navigation & Control (GNC) tingkat literasi**: konsep dasar closed-loop guidance untuk ascent (bukan desain sistem guidance senjata — lihat batasan di bawah), sensor (IMU, GPS), dan kenapa roket butuh active control selama peluncuran.
- **Industri & pemain modern**: perbandingan pendekatan NASA (heritage/rigor formal), SpaceX (iterative/rapid-prototyping, reusability), ISRO/Roscosmos/ESA — dipakai sebagai Practitioner Reference Frame.

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Desain sistem guidance rudal balistik/senjata militer (missile guidance/warhead integration)** — termasuk targeting system, re-entry vehicle untuk hulu ledak, atau optimasi trayektori untuk tujuan senjata. Domain ini murni kendaraan peluncur sipil/sains (lihat Domain-Specific Standards).
- **Detail dual-use terkontrol ekspor (ITAR/MTCR-controlled specifics)** — mis. spesifikasi teknis rinci propelan/motor roket tingkat senjata, guidance package militer — dibahas hanya sebatas KEBERADAAN kerangka regulasinya (apa itu ITAR/MTCR), bukan cara mengelilinginya.
- **Astrobiologi, kosmologi, dan trayektori antarplanet/deep-space lanjutan** — sepenuhnya milik domain [[domains/astronomy-and-life-in-the-universe/DOMAIN.md|astronomy-and-life-in-the-universe]] yang sudah ada di repo ini.
- **Fisika klasik umum non-roket & metode numerik generik** — fondasi kinematika/dinamika/integrasi numerik dipakai sebagai prasyarat dari domain [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]] yang sudah ada, tidak diajarkan ulang dari nol di sini.
- **Desain reaktor nuklir untuk nuclear thermal/electric propulsion** — kalau topik menyentuh area ini, diarahkan ke domain [[domains/nuclear-technology/DOMAIN.md|nuclear-technology]].
- **Sertifikasi rekayasa aerospace profesional** — bukan pengganti gelar Aerospace/Astronautical Engineering terakreditasi atau lisensi insinyur profesional.
- **Pembuatan roket amatir fisik tanpa pedoman keselamatan resmi** — lihat house rule keselamatan di bawah.

## Trusted Sources / Research Priority

1. **George P. Sutton & Oscar Biblarz, *Rocket Propulsion Elements* (9th ed., Wiley)** — textbook standar industri & akademik untuk propulsi roket di seluruh dunia (dipakai NASA, hampir semua program aerospace engineering S1). Tulang punggung modul propulsi kimia & persamaan dasar.
2. **NASA Glenn Research Center — Beginner's Guide to Rockets** (https://www.grc.nasa.gov/www/k-12/rocket/index.html) dan **NASA NTRS (NASA Technical Reports Server)** (https://ntrs.nasa.gov/) — sumber resmi NASA untuk konsep dasar sampai laporan teknis misi nyata, gratis & terverifikasi.
3. **MIT OpenCourseWare 16.512 — Rocket Propulsion** (https://ocw.mit.edu/courses/16-512-rocket-propulsion-fall-2005/) — materi kuliah resmi MIT Aeronautics & Astronautics, dipakai sebagai kerangka struktur modul propulsi & problem set terverifikasi.
4. **Howard D. Curtis, *Orbital Mechanics for Engineering Students* (4th ed., Butterworth-Heinemann)** — textbook standar untuk mekanika orbit terapan, dipakai untuk modul orbital insertion/Hohmann transfer/delta-v budgeting.
5. **Philip Hill & Carl Peterson, *Mechanics and Thermodynamics of Propulsion* (2nd ed., Addison-Wesley)** — pelengkap Sutton & Biblarz untuk sisi termodinamika mesin roket & jet, standar kuliah aerospace.
6. **Dokumentasi & press kit teknis resmi operator modern** — SpaceX (https://www.spacex.com/, khususnya press kit & webcast teknis Falcon 9/Starship), ESA (esa.int), ISRO (isro.gov.in), Roscosmos — dipakai sebagai rujukan "bagaimana industri modern sungguhan mendesain & menerbangkan roket," selalu disilangkan ke textbook untuk klaim fisika/teknik.
7. **Robert Braeunig, Rocket & Space Technology** (braeunig.us/space) — sumber independen yang luas dikutip komunitas amatir/hobbyist serius untuk kalkulasi propulsi & mekanika orbit; dipakai HANYA sebagai cross-check tambahan, tidak pernah jadi rujukan utama tunggal.
8. **National Association of Rocketry (NAR)** (nar.org) dan **Tripoli Rocketry Association** — rujukan wajib untuk safety code roket model/high-power bila learner ingin bereksperimen fisik nyata.

Klaim kuantitatif (mis. Isp berbagai propelan, delta-v budget misi nyata, trade-off siklus mesin) selalu disilangkan minimal ke satu textbook (1, 4, atau 5) DAN dicek terhadap data misi nyata dari sumber 2 atau 6 — tidak cukup hanya dari sumber independen (7) atau ingatan/asumsi tanpa verifikasi.

## Applied Practice Definition

Konsisten dengan `# Hands-on First` di `CLAUDE.md` root, dan menyambung kemampuan software engineering learner yang sudah ada (mirip pola [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]]):

- **Problem set kuantitatif per konsep**: hitungan delta-v (Tsiolkovsky), Isp/thrust, mass ratio, staging trade-off — mengikuti gaya soal Sutton & Biblarz / MIT OCW 16.512, dengan kunci jawaban untuk self-check.
- **Delta-v budget nyata**: menyusun delta-v budget untuk misi konkret (mis. Bumi ke LEO, LEO ke GTO) dan membandingkan terhadap data misi nyata (Falcon 9, dll) dari sumber 2/6.
- **Simulasi kode ascent/trajectory sederhana**: skrip 1D/2D yang mensimulasikan ascent roket (gravity, drag, thrust berubah terhadap waktu, mass depletion) memakai integrator numerik (menyambung ke fondasi [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]]), divalidasi terhadap solusi analitik/data nyata.
- **Desain kendaraan peluncur mini**: menyusun spesifikasi konseptual kendaraan multi-stage (pilihan propelan, jumlah stage, mass budget) untuk mencapai target delta-v tertentu, dengan justifikasi trade-off tertulis.
- **Post-mortem studi kasus kegagalan**: analisis tertulis satu kegagalan peluncuran nyata (mis. Challenger, Amos-6, kegagalan awal Starship) yang membedah root cause teknis & pelajaran safety/engineering-nya, bersumber dari laporan investigasi resmi.

## Project Tiers

- **Mini Project** (per modul): satu problem set kuantitatif (delta-v/Isp/mass ratio) ATAU satu skrip simulasi kecil (mis. burn tunggal tanpa gravity turn).
- **Intermediate Project** (setelah beberapa modul terkait): **"Multi-Stage Launch Vehicle Sizing Tool"** — skrip/spreadsheet yang menghitung delta-v budget & mass breakdown untuk kendaraan 2-3 stage dengan pilihan propelan berbeda, dibandingkan terhadap kendaraan nyata.
- **Capstone / Portfolio Project** (akhir course): **desain kendaraan peluncur konseptual lengkap** — pemilihan propulsi (kimia/jenis siklus), staging & delta-v budget, profil ascent (termasuk gravity turn), DAN simulasi trajectory kode yang memvalidasi desain mencapai target orbit — disimpan ke `portofolio/`, disertai penjelasan tertulis trade-off desain (kenapa memilih siklus mesin/jumlah stage tertentu) dibandingkan pendekatan kendaraan nyata (mis. Falcon 9 atau Electron).

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak di domain ini juga dicek lewat lensa berikut:

- **Physical/Engineering Correctness** — apakah kalkulasi delta-v/Isp/mass ratio konsisten dengan persamaan Tsiolkovsky & data propelan nyata, bukan angka yang di-fudge supaya "terlihat masuk akal"?
- **Numerical Validity (untuk simulasi kode)** — apakah simulasi ascent tervalidasi terhadap solusi analitik atau data misi nyata, bukan cuma "grafiknya terlihat seperti roket naik"?
- **Design Trade-off Awareness** — apakah pilihan desain (jenis propelan, jumlah stage, siklus mesin) dijustifikasi dengan trade-off eksplisit (performa vs kompleksitas vs biaya vs re-usability), bukan pilihan sembarang?
- **Safety & Failure-Mode Awareness** — apakah analisis kegagalan/desain menunjukkan kesadaran mode kegagalan nyata (max-Q, structural failure, propellant handling), konsisten dengan house rule keselamatan di bawah?

## Assessment Form

Concept-check singkat setelah tiap bagian teori, problem set kuantitatif terarah gaya Sutton & Biblarz/MIT OCW 16.512 dengan kunci jawaban untuk self-check, dan review kode simulasi (learner menjalankan simulasi ascent-nya sendiri, membandingkan delta-v/altitude hasil terhadap ekspektasi teoretis, menjelaskan deviasi bila ada) sebagai bentuk assessment applied.

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk topik yang sama:

- **NASA/heritage aerospace engineer** (gaya Sutton & Biblarz, MIT 16.512, budaya pra-penerbangan NASA) — rigor formal, margin keselamatan besar, dokumentasi ekstensif — lensa dominan untuk fondasi propulsi & GNC.
- **SpaceX/New Space engineer** (iterative design, "test early, fail fast," reusability sebagai prioritas desain sejak awal) — lensa pembanding untuk staging & trade-off desain modern (mis. kenapa Starship pakai stainless steel, kenapa booster didesain untuk didaratkan).
- **Insinyur Soviet/Rusia klasik** (gaya Korolev/biro desain RD) — rigor engineering pragmatis dengan constraint berbeda (era Cold War, akses material), pembanding historis penting untuk konteks Space Race.
- **Regulator/safety body** (NAR/Tripoli untuk model rocketry, FAA untuk peluncuran komersial) — framing keselamatan & kepatuhan sebagai prioritas, dipakai khusus saat topik menyentuh eksperimen fisik nyata.

Lensa-lensa ini sering menekankan hal berbeda untuk topik yang sama (mis. pemilihan material tangki: heritage engineer mengutamakan margin keselamatan terverifikasi lama, New Space engineer mengutamakan iterasi cepat & biaya) — domain ini melatih learner mengenali trade-off tersebut secara sadar.

## Domain-Specific Standards

`domains/rocket-technology/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Literasi & desain konseptual, bukan lisensi rekayasa aerospace profesional.** Tidak pernah menjadi pengganti pendidikan Aerospace/Astronautical Engineering terakreditasi atau sertifikasi insinyur profesional. Setiap kali lesson menyentuh area yang butuh kredensial nyata (mis. "bagaimana sertifikasi untuk merancang roket berawak sungguhan"), mentor menyatakan eksplisit batas ini.
- **Tidak pernah membahas desain sistem senjata/rudal.** Mentor tidak pernah memberikan detail desain guidance rudal balistik, integrasi hulu ledak, re-entry vehicle untuk senjata, atau informasi dual-use sensitif terkontrol-ekspor (ITAR/MTCR) lainnya. Jika diminta (langsung atau menyamar sebagai "penasaran ilmiah"), mentor menolak eksplisit dan mengarahkan kembali ke literasi kendaraan peluncur sipil/sains — konsisten dengan house rule serupa di [[domains/nuclear-technology/DOMAIN.md|nuclear-technology]].
- **Keselamatan propelan energetik ditangani serius.** Mentor tidak pernah memberi instruksi pembuatan/penanganan propelan roket amatir tanpa disclaimer keselamatan eksplisit. Bila learner ingin bereksperimen fisik nyata (model rocketry), mentor mengarahkan ke safety code resmi NAR/Tripoli, bukan instruksi self-improvised.
- **Sumber institusional/akademik di atas sumber informal untuk klaim teknis.** Setiap klaim kuantitatif (Isp, delta-v, trade-off desain) wajib bisa ditelusuri ke Trusted Sources 1-6 — sumber independen (7) hanya sebagai pelengkap cross-check, tidak pernah jadi dasar tunggal.
- **Tidak ada teori tanpa validasi kuantitatif.** Setiap modul propulsi/staging/trajectory yang dianggap "selesai" harus berakhir di problem set terhitung atau simulasi kode yang divalidasi angka nyata — bukan cuma penjelasan konseptual, konsisten dengan pola [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]].

## Domain Goal

Learner mampu menjelaskan & menghitung sendiri fondasi kuantitatif propulsi roket (persamaan Tsiolkovsky, Isp, thrust, mass ratio), memahami trade-off jenis propulsi & material struktur, menyusun delta-v budget untuk desain misi peluncuran nyata, dan membangun simulasi ascent/trajectory sederhana yang tervalidasi — dibuktikan lewat capstone desain kendaraan peluncur konseptual di portofolio yang mengintegrasikan propulsi + staging + simulasi kode + kesadaran trade-off desain dibandingkan kendaraan nyata (Falcon 9, Electron, dll). Ini eksplisit BUKAN kredensial rekayasa aerospace formal, dan eksplisit BUKAN literasi sistem senjata/rudal — murni kemampuan applied engineering & literasi sains untuk kendaraan peluncur sipil, menyambung ke fondasi fisika/numerik yang sudah dipunya learner dari [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]].
