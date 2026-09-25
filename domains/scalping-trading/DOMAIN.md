# Scalping & Short-Term Trading — Domain Pack

> **Status: DRAFT, terdaftar Paused, menunggu approval user.** Course: `courses/scalper-mind/roadmap.md`.

## Domain Name

Scalping & Short-Term Trading — Membangun Disiplin, Sistem, dan Kemampuan Menguji Edge Sebelum Mempertaruhkan Uang

## Scope

Belajar trading jangka sangat pendek sebagai **disiplin pengambilan keputusan di bawah ketidakpastian**, bukan sebagai jalan cepat menghasilkan uang: apa itu scalping dan kenapa timeframe pendek tidak berarti lebih mudah (biaya, spread, slippage), probabilitas dan expectancy, membaca candlestick dan market structure, support/resistance, breakout, pullback, liquidity, membangun entry model dan stop loss, position sizing dan batas risiko harian, psikologi (fear, greed, FOMO, revenge trading, ego), membangun satu setup, backtesting dan forward testing yang jujur, journaling dan review, market regime, berita, eksekusi, dan operating system harian. Diakhiri "My Scalping Playbook" dan "My Scalper Code".

Basis awal: outline "Scalper Mind — Membangun Mental, Disiplin, dan Sistem Trading Jangka Pendek" buatan user (10 Part, 31 modul, Pre/During/Post-Market checklist, 30-Day Scalper Mind Challenge, Final Project My Scalping Playbook, Personal Scalper Code 10 butir). Urutan Part dipertahankan; 31 modul dilipat jadi 17 modul course (lihat "Pemetaan Outline ke Modul" di roadmap).

**Yang membuat domain ini berbeda dari domain lain:** tidak ada course lain di repo yang mengajarkan trading jangka pendek (grep `scalp|price action|candlestick` di `courses/` = 0 hasil pada 2026-09-25). Yang sudah ada hanya beririsan di lapisan tipis: `investing-stocks` (fundamental-first; Module 12 technical analysis hanya "pendukung", Module 9 bias investor, Module 10 position sizing), `cryptocurrency` (Module 10 trading/risk/psikologi ringkas), `anti-scam-risk-management` (Module 4 FOMO, Module 6 position sizing dan expected value). Domain ini mengisi celah *short-term execution* dan *sistem yang bisa diuji*, sambil merujuk (bukan mengulang) mekanisme psikologi umum.

## Posisi Bukti (diverifikasi lewat pencarian 2026-09-25; dibaca ulang dari sumber primer saat lesson ditulis)

Ini bagian paling penting dari domain pack. Course ini **tidak boleh** dibangun di atas asumsi "scalping itu menguntungkan kalau disiplin". Bukti yang ditemukan justru sebaliknya untuk trader ritel rata-rata, dan course dibangun jujur di atasnya:

- **Day trader ritel mayoritas rugi.** Chague, De-Losso & Giovannetti ("Day Trading for a Living?", working paper SSRN 2019): seluruh individu yang mulai day trading di pasar *equity futures* Brasil 2013-2015; **97% dari yang bertahan >300 hari merugi**, hanya 1,1% menghasilkan lebih dari upah minimum Brasil, 0,5% lebih dari gaji awal teller bank, dan tidak ditemukan bukti pembelajaran dari pengalaman. Batas: pasar dan negara spesifik (futures Brasil), populasi "yang bertahan" adalah seleksi, working paper (status publikasi dicek ulang), tidak otomatis berlaku untuk saham/forex/kripto Indonesia.
- **Taiwan.** Barber, Lee, Liu & Odean (beberapa makalah: *Do Individual Day Traders Make Money? Evidence from Taiwan*; *The Cross-Section of Speculator Skill: Evidence from Day Trading*, *Journal of Financial Markets* 2014; *Do Day Traders Rationally Learn About Their Ability?*): sebagian besar day trader merugi setelah biaya transaksi; ada bukti **persistensi** (sedikit trader memang konsisten untung) tetapi kecil; pembelajaran lambat dan mahal. Angka persis (mis. proporsi yang untung per periode enam bulan) dibaca ulang dari makalah, bukan dari ringkasan situs. Batas: satu bursa (Taiwan), periode 1992-2006.
- **CFD/forex ritel di Eropa.** ESMA (2018) memakai analisis regulator nasional yang menunjukkan **74-89% akun ritel CFD merugi**; dasar keputusan intervensi produk (pembatasan leverage, larangan opsi biner). Batas: data regulator, ditujukan pada CFD berleverage tinggi, bukan semua bentuk scalping.
- **Leverage dan overconfidence.** Barber, Huang, Ko & Odean, *Leveraging Overconfidence* (working paper SSRN 2019; status jurnal dicek ulang): investor margin lebih percaya diri, lebih banyak trading, seleksi sekuritas lebih buruk. Batas: data broker satu negara.
- **Analisis teknikal: bukti campuran, bukan nol, bukan pasti.** Park & Irwin (2007, *Journal of Economic Surveys* 21(4):786-826): studi awal menemukan strategi teknikal menguntungkan di FX dan futures, tidak di saham; studi modern lebih beragam dengan masalah data snooping. Lo, Mamaysky & Wang (2000, *Journal of Finance* 55(4):1705-1765): sebagian pola punya informasi tambahan pada sampel besar, tetapi profitabilitas bergantung biaya dan implementasi. **Candlestick:** Marshall, Young & Rose (2006, *Journal of Banking & Finance* 30:2303-2323) tidak menemukan return berlebih signifikan pada komponen DJIA 1992-2002; hasil di pasar lain campuran (mis. studi Bursa Thailand, Tharavanij dkk. 2017, dicek). Konsekuensi desain: "pola tanpa konteks bukan sinyal" (kalimat outline user) adalah posisi yang didukung, tetapi konteks juga **tidak menjamin edge**.
- **Kluster order stop-loss dan take-profit.** Osler (2003, *Journal of Finance* 58(5):1791-1819): dari buku order sebuah bank valas besar (9.655 order, tiga pasangan mata uang, Sept 1999-Apr 2000), take-profit menumpuk di angka bulat dan stop-loss menumpuk tepat di luar angka bulat; stop-loss cenderung memperkuat tren setelah level ditembus. Ini bukti *bahwa kluster order ada* di satu dealer dan periode. **Ini bukan bukti bahwa "smart money memburu stop trader ritel"** — klaim itu (sering dipakai konsep liquidity sweep/stop hunt dalam komunitas SMC/ICT) tidak punya dukungan riset yang kuverifikasi dan dilabel *heuristik populer, tidak tervalidasi*. Osler (2005, *Journal of International Money and Finance* 24:219-241) tentang price cascade dirujuk sebagai lanjutan.
- **Backtest overfitting.** Bailey, Borwein, López de Prado & Zhu (2014, *Notices of the AMS* 61(5):458-471): performa simulasi tinggi mudah diperoleh dengan mencoba sedikit konfigurasi; makin banyak konfigurasi dicoba, makin besar peluang overfit, dan overfit dapat berujung ekspektasi *negatif* di luar sampel. Konsekuensi: Module 12 mewajibkan hitungan jumlah konfigurasi yang dicoba, out-of-sample, dan walk-forward.
- **Psikologi keputusan.** Kahneman & Tversky (1979, *Econometrica* 47:263-291) prospect theory dan loss aversion; **klaim "kerugian terasa 2× lebih sakit" diperdebatkan**: Gal & Rucker (2018, *Journal of Consumer Psychology* 28:497-516) berargumen bukti umum tidak mendukung loss aversion universal, dibalas Mrkva dkk. (2020) yang mempertahankannya. Dilabel *teori berpengaruh dengan bukti diperdebatkan*. Odean (1998, *Journal of Finance* 53(5)): disposition effect (menahan yang rugi, menjual yang untung) pada 10.000 akun broker, tidak dibenarkan oleh kinerja berikutnya. Thaler & Johnson (1990, *Management Science* 36:643-660): house-money effect dan break-even effect (menaikkan risiko setelah untung, atau untuk "balik modal" setelah rugi) — dasar ilmiah pola revenge trading, tetapi berasal dari eksperimen/gambling, bukan trader profesional.
- **Fisiologi trader.** Coates & Herbert (2008, *PNAS* 105(16):6167-6172): pada trader laki-laki di trading floor London, testosteron pagi memprediksi profit hari itu; kortisol naik dengan varians hasil dan volatilitas pasar. Sampel kecil, satu lantai bursa, korelasional; tidak dipatok sebagai resep ("naikkan testosteron"). Dipakai hanya untuk menjelaskan kenapa stres dan risiko saling menguatkan.
- **Regulasi Indonesia.** Pengaturan dan pengawasan aset keuangan digital termasuk aset kripto serta derivatif keuangan dialihkan dari Bappebti ke OJK dan Bank Indonesia sejak 10 Januari 2025 (UU 4/2023 P2SK; POJK 27/2024; PBI 6/2024); OJK dan Bappebti menandai berakhirnya masa transisi pada Januari 2026. Satgas PASTI (OJK) rutin menghentikan entitas investasi ilegal, termasuk robot trading, platform perdagangan berjangka tanpa izin, dan kripto ilegal. Detail izin broker/pialang, batas leverage, dan pajak **dicek ulang dari OJK/BI/DJP sebelum diajarkan**; regulasi berubah.
- **Klaim yang sering dipakai dan tidak dipatok sebagai bukti:** "volume confirmation" di pasar spot forex (tidak ada volume terpusat; yang tersedia tick volume broker), "stop hunting oleh market maker" (tidak diverifikasi), "liquidity sweep" sebagai konsep SMC/ICT (tidak punya uji peer-reviewed yang kuverifikasi), "trader profesional selalu pakai R:R 1:2+" (tanpa sumber), "90% trader gagal" (angka viral yang sering tidak punya sumber primer; yang dipakai adalah angka dari studi di atas dengan batasnya masing-masing).

Konsekuensi desain: tiap lesson berlabel bukti (*didukung riset* / *teori atau kerangka praktisi, bukan klaim empiris* / *heuristik populer, tidak tervalidasi*). Hipotesis awal course adalah **"kamu kemungkinan besar belum punya edge"**, dan tujuan course bukan menemukan profit tetapi (a) memahami risikonya dengan angka, (b) mampu menguji klaim edge sendiri dengan jujur, (c) bertahan hidup dan mengambil keputusan berdisiplin, (d) berhenti bila bukti bilang berhenti.

Secara eksplisit TIDAK mencakup (dirujuk, tidak diduplikasi):

- **Investasi fundamental, valuasi, laporan keuangan, portofolio jangka panjang** — [[investing-stocks]] Module 3-8, 10, 13. Domain ini hanya jangka sangat pendek; jembatan: apa yang hilang bila memilih scalping dibanding investasi.
- **Bias investor umum, siklus pasar, aturan investasi pribadi** — [[investing-stocks]] Module 9. Module 9-10 di sini hanya sudut *eksekusi intraday* dan merujuk.
- **Kripto: blockchain, exchange, regulasi kripto, tokenomics** — [[cryptocurrency]] Module 4 dan 10. Bila market yang dipilih kripto, prasyarat dirujuk ke sana.
- **Scam, robot trading palsu, signal seller, copy trading ilegal, manufactured urgency** — [[anti-scam-risk-management]] Module 3-4, 6; dirujuk di Module 1, 11, dan 16.
- **Manajemen uang pribadi, dana darurat, asuransi, "uang yang boleh dipertaruhkan"** — [[personal-finance]]. Prasyarat keras: modal trading adalah uang yang aman hilang total.
- **Dasar probabilitas dan statistika** — [[statistics-probability]] Module 2, 6, 9-10 (probabilitas, LLN, uji hipotesis). Module 2 dan 12 di sini hanya penerapan (expectancy, losing streak, sample size).
- **Ego, kebutuhan membuktikan diri, menerima salah** — [[lowering-the-ego]], [[human-approval]]; **regulasi emosi, jeda sebelum bereaksi** — [[emosi]], [[emotional-resilience]] Module 10; [[stoicism]] (dikotomi kendali). Module 9-10 di sini hanya konteks trading dan merujuk.
- **Perfeksionisme, keberanian mengambil keputusan tidak sempurna** — [[courage-to-try]] Module 3.
- **Dopamin dan perilaku mirip judi** — [[dopamine]]; **kecanduan/kompulsif** — bila perilaku trading mengganggu hidup, rujuk profesional (lihat Domain-Specific Standards).
- **Pemrograman backtester/otomasi** — [[automation-scripting]], [[data-analysis-visualization]]; opsional di Module 12.

## Trusted Sources / Research Priority

Diverifikasi lewat pencarian saat draft ini ditulis (2026-09-25); tiap klaim tetap dibaca ulang dari sumber primer saat lesson ditulis:

1. **Bukti profitabilitas trader ritel:** Chague, De-Losso & Giovannetti (SSRN 3423101); Barber, Lee, Liu & Odean (Taiwan, beberapa makalah); ESMA (siaran pers dan notice product intervention CFD/opsi biner, Maret dan Juni 2018); Barber, Huang, Ko & Odean (*Leveraging Overconfidence*). Kandidat: Barber & Odean (2000, "Trading Is Hazardous to Your Wealth", *Journal of Finance*, dicek).
2. **Analisis teknikal dan candlestick:** Park & Irwin (2007); Lo, Mamaysky & Wang (2000); Marshall, Young & Rose (2006); Tharavanij dkk. (2017). Kandidat: Brock, Lakonishok & LeBaron (1992); Marshall, Young & Cahan (2008, candlestick Jepang); Nison, *Japanese Candlestick Charting Techniques* (deskriptif/praktisi).
3. **Mikrostruktur dan order flow:** Osler (2003; 2005); kandidat: Harris, *Trading and Exchanges* (buku teks mikrostruktur, spread dan slippage), literatur BIS Triennial Survey untuk struktur pasar valas.
4. **Backtest dan validasi:** Bailey, Borwein, López de Prado & Zhu (2014); kandidat: López de Prado, *Advances in Financial Machine Learning* (deflated Sharpe ratio, walk-forward), Harvey, Liu & Zhu (2016, "...and the Cross-Section of Expected Returns").
5. **Psikologi keputusan:** Kahneman & Tversky (1979); Gal & Rucker (2018) dan balasan Mrkva dkk. (2020); Odean (1998); Thaler & Johnson (1990); Coates & Herbert (2008). Kandidat praktisi: Douglas, *Trading in the Zone* (populer, tidak berbasis riset terkontrol), Steenbarger, *The Psychology of Trading* (klinisi/praktisi).
6. **Manajemen risiko dan ukuran posisi:** kandidat Kelly (1956) dan turunannya, van Tharp (R-multiple; populer), literatur risk of ruin.
7. **Regulasi Indonesia:** siaran pers OJK/Bappebti/BI (peralihan 10 Januari 2025 dan penutupan masa transisi Januari 2026), POJK 27/2024, PBI 6/2024, Satgas PASTI/waspada investasi OJK; kandidat: aturan BEI (jam bursa, ARA/ARB, penyelesaian T+2, biaya) dan DJP (pajak transaksi) — **semuanya dibaca ulang dari sumber resmi**, angka biaya dan batas tidak dipatok dari ingatan.

House rule sumber: (a) klaim riset disebut penulis/tahun dan dibaca ulang dari sumber primer, bukan ringkasan situs atau ingatan; (b) bukti dari satu pasar/negara diberi label itu dan tidak digeneralisasi ke Indonesia; (c) teori (prospect theory, SMC/ICT) dilabel sesuai statusnya; (d) tidak ada persentase "mengesankan" tanpa sumber primer (termasuk "90% trader gagal"); (e) kritik terhadap trading jangka pendek disebut apa adanya; (f) tidak ada sinyal, rekomendasi instrumen, rekomendasi broker, atau prediksi arah harga; (g) aturan dan biaya dicek ke sumber resmi karena berubah.

## Applied Practice Definition

**Trade Log dan Test Ledger** — dua buku catatan, keduanya dari data nyata (demo atau historis), bukan hipotetis.

*Test Ledger* (Module 12-13): tiap trade backtest atau forward test dicatat dengan tanggal/jam, market, setup, entry, stop, target, hasil dalam R, biaya (spread/fee/slippage yang diasumsikan), dan **jumlah konfigurasi yang sudah dicoba** hingga trade itu dihitung.

*Trade Log* (Module 14 dan seterusnya): date, time, market, setup, entry, stop, target, risk, result, screenshot, alasan masuk, **emosi sebelum/saat/sesudah**, dan "apakah mengikuti aturan? (ya/tidak, mana yang dilanggar)". Kualitas entri dinilai dari kejujuran dan kelengkapan (termasuk trade yang melanggar aturan), bukan dari hasil untung/rugi.

## Project Tiers

- **Mini Project** (per modul): satu latihan konkret dengan data historis atau demo (mis. hitung expectancy dari 30 trade fiktif dan simulasikan losing streak, tandai 20 candlestick di chart historis tanpa melihat hasilnya lalu cek, hitung ukuran posisi untuk lima stop berbeda, backtest satu aturan sederhana 50 trade dengan biaya).
- **Intermediate Project**: **30-Day Scalper Mind Challenge** (Minggu 1 Market Reading, Minggu 2 Price Action, Minggu 3 System, Minggu 4 Psychology), **hanya demo/paper**, berjalan paralel, tidak terkunci kalender; hari terlewat dicatat sebagai terlewat.
- **Capstone**: **My Scalping Playbook** (10 bagian: My Market, Timeframe, Setup, Entry Rules, Invalidation, Risk Rules, Exit Rules, No-Trade Rules, Psychology Rules, Review System) + **My Scalper Code** versi sendiri + **Verdict** yang jujur: apakah bukti Test Ledger dan Trade Log mendukung lanjut ke modal kecil, ulangi pengujian, atau berhenti. Disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), tiap entri dan playbook dicek:

- **Bukti vs kesan** — klaim "setup ini bagus" harus punya angka (jumlah trade, expectancy, drawdown, biaya); kesan dari beberapa trade bagus tidak dihitung.
- **Biaya diperhitungkan** — hasil tanpa spread/fee/slippage otomatis ditandai belum valid.
- **Kejujuran terhadap pelanggaran** — trade yang melanggar aturan dicatat; log yang tampak selalu patuh dicurigai.
- **Tidak ada retro-fitting** — aturan tidak ditulis setelah melihat hasil; jumlah konfigurasi yang dicoba diungkap.
- **Risiko sebelum keuntungan** — playbook yang menjelaskan target profit sebelum batas kerugian ditolak.
- **Kriteria berhenti tertulis** — ada angka atau kondisi kapan sistem dinyatakan gagal (bukan hanya kapan dianggap berhasil).

## Assessment Form

Latihan hitung dan simulasi (expectancy, losing streak, ukuran posisi, biaya vs target), latihan baca chart historis tanpa melihat hasil (blind study), kasus ambigu (kapan "jangan ubah sistem karena beberapa loss" benar, dan kapan itu sunk-cost), backtest sederhana dengan catatan overfitting, dan refleksi tertulis singkat setelah simulasi hari buruk. Bukan quiz hafalan istilah candlestick.

## Practitioner Reference Frame

- **Peneliti perilaku dan pasar** (Barber, Odean, Chague dkk., Osler, Kahneman & Tversky) — lensa bukti tentang siapa yang untung, kenapa, dan kapan psikologi merusak keputusan.
- **Peneliti backtest** (Bailey, López de Prado) — lensa kejujuran pengujian.
- **Praktisi/penulis populer** (Nison, Douglas, van Tharp, Brooks) — dibaca sebagai kerangka dan bahasa, dengan label heuristik populer; bukan bukti.
- **Konteks software engineer:** kemampuan pemrograman dipakai untuk membangun backtester dan menghindari overfitting; kebiasaan engineering (logging, post-mortem, kill switch, alerting) dipetakan ke Trade Log, review mingguan, dan Daily Risk Limit. Rujukan sistem: rate limit, circuit breaker, dan blameless post-mortem sebagai analogi risk limit dan review.

## Domain-Specific Standards

- **Bukan nasihat keuangan, bukan sinyal.** Tidak ada rekomendasi instrumen, arah harga, broker, robot, atau kelas berbayar. Course ini edukasi (catatan di outline user dipertahankan) dan tidak menjanjikan profit.
- **Default: demo/paper.** Tidak ada latihan yang mewajibkan uang nyata. Bila user memilih modal nyata setelah Forward Testing, hanya uang yang aman hilang total (dana darurat, cicilan, dan kebutuhan keluarga tidak tersentuh) dan hanya sesudah Verdict di Capstone; keputusan itu milik user, dan course tidak menyarankannya.
- **Leverage dan derivatif dijelaskan dengan bukti kerugian, bukan dipromosikan.** Course tidak mengajarkan leverage tinggi sebagai cara "mempercepat"; contoh ukuran posisi memakai kerugian per trade sebagai persentase modal.
- **Tidak melarang, tidak mengiming-imingi.** Hipotesis awal jujur: mayoritas trader ritel merugi. Course tidak memakai bukti itu untuk menakut-nakuti dan tidak menyembunyikannya demi motivasi.
- **Legalitas dan izin dicek.** Broker/platform harus terdaftar dan diawasi otoritas Indonesia sesuai instrumennya (OJK/BI); pengecekan lewat kanal resmi, bukan iklan. Modul tentang scam dirujuk ke [[anti-scam-risk-management]]. Aturan pajak dan biaya dicek dari sumber resmi.
- **Bukan alat menghakimi diri.** Rugi beruntun bukan bukti karakter buruk; pelanggaran aturan dicatat sebagai data, bukan alasan menghukum diri.
- **Kesehatan mental dan perilaku kompulsif.** Bila trading terasa tak terkendali (mengejar kerugian dengan uang yang bukan milik sendiri, berbohong soal trading, tidak bisa berhenti walau berniat), berhenti dan rujuk profesional/layanan bantuan judi kompulsif; course ini bukan terapi.
- **"Jangan ubah sistem hanya karena beberapa loss" tidak boleh jadi sunk-cost.** Setiap butir Scalper Code harus dipasangkan dengan kriteria statistik kapan bukti cukup untuk *menghentikan atau merevisi* sistem (dirumuskan di Module 2 dan 16).
- **Jangan overstudy.** Belajar trading bisa menjadi cara menunda keputusan atau mengisi rasa "produktif" tanpa menguji apa pun. Course ini berat di pengujian dan pencatatan, ringan di teori baru; berhenti dan istirahat lebih dianjurkan daripada menambah setup.
- **Sumber dicek, bukan diingat.** Angka biaya, batas leverage, aturan bursa, dan angka studi selalu dibaca ulang dari sumber sebelum ditulis ke lesson.

## Domain Goal

Learner dapat menjelaskan dengan angka kenapa trading jangka pendek sulit, menghitung expectancy dan ukuran posisi, membaca chart dengan label bukti yang jujur, membangun satu setup dengan aturan entry/stop/target tertulis, mengujinya (backtest dan forward test) tanpa menipu diri sendiri, mengelola risiko harian, mengenali dan menahan pola fear/greed/FOMO/revenge/ego, mencatat dan meninjau tiap trade, dan menetapkan secara tertulis kapan ia harus berhenti. Dibuktikan lewat Test Ledger, Trade Log, 30-Day Challenge, dan My Scalping Playbook dengan Verdict. Prinsip utama: *survive first, improve second, scale last — dan bila bukti berkata tidak ada edge, berhenti adalah hasil yang sah, bukan kegagalan.*

## Registration History

Dibuat 2026-09-25 atas permintaan langsung user (paste outline "Scalper Mind — Membangun Mental, Disiplin, dan Sistem Trading Jangka Pendek" tanpa instruksi tambahan). Ditanya lewat AskUserQuestion: (1) guard "Course Aktif di bawah 50%" — Character Development pas 3/6 modul dan `male-grooming` masih Module 1 — user memilih **lanjut, override guard**; (2) scope domain — user memilih **domain baru + cross-reference** (rekomendasi mentor) dibanding extend `investing-stocks` atau audit overlap penuh dulu. Nama domain `scalping-trading` adalah **asumsi mentor yang tidak ditanyakan eksplisit**. Overlap dicek dengan grep `scalp|price action|candlestick` lintas `courses/` (0 hasil) dan membaca daftar modul dan lesson `investing-stocks` (Module 9, 10, 12, 13), `cryptocurrency` (Module 10), `anti-scam-risk-management` (Module 4, 6), `statistics-probability` (daftar modul), dan `emotional-resilience` (daftar modul); `slow-living` dan course lain 2026-09-25 tidak relevan. Hasil: **tidak ada course yang mengajarkan trading jangka pendek**; irisan hanya pada psikologi umum dan position sizing dasar, yang dirujuk. Riset web menemukan bukti kuat bahwa mayoritas day trader ritel merugi (Chague dkk., Barber dkk., ESMA) dan bahwa klaim populer (stop hunting, liquidity sweep, volume di forex, "90% trader gagal") tidak punya dukungan primer yang kuverifikasi, sehingga course dibangun dengan label bukti eksplisit, hipotesis awal "belum punya edge", dan Verdict yang mengizinkan hasil "berhenti". Tidak diaudit: apakah user berencana memakai uang nyata, market yang ingin dipilih (saham BEI, forex, emas, kripto), dan modal yang tersedia — ditanyakan saat approval, karena mengubah Module 1, 11, dan 16. Course **Draft, menunggu approval**; belum jadi Course Aktif.
