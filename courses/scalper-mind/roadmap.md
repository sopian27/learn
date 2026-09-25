# Course Roadmap

## Course Information

* Course Name: Scalper Mind — Membangun Mental, Disiplin, dan Sistem Trading Jangka Pendek
* Category: Trading Jangka Pendek sebagai Disiplin Keputusan (Domain: scalping-trading — lihat `domains/scalping-trading/DOMAIN.md`)
* Basis: outline "Scalper Mind" buatan user (10 Part, 31 modul, Pre/During/Post-Market checklist, 30-Day Scalper Mind Challenge, Final Project My Scalping Playbook, Personal Scalper Code 10 butir). Urutan Part dipertahankan; 31 modul dilipat jadi 17 modul course.
* Difficulty: Level 0 (tahu trading dari konten media sosial; menganggap timeframe pendek = cepat untung; entry karena "candle-nya bagus"; belum menghitung biaya, expectancy, atau ukuran posisi) → Level 3 (bisa menghitung expectancy dan losing streak, membaca chart dengan label bukti jujur, punya satu setup tertulis yang sudah diuji tanpa menipu diri, punya batas risiko harian yang ditaati, punya Trade Log dengan pola perilaku, dan punya Verdict tertulis kapan lanjut, ulangi, atau berhenti)
* Level: Beginner → Intermediate
* Estimated Duration: 17 modul, 3-4 lesson/modul (±58 lesson) + 30-Day Scalper Mind Challenge (paralel, demo saja) + Capstone. Pace mengikuti pengujian nyata, bukan dikebut.
* Prerequisites: Tidak ada prasyarat teknis. **Prasyarat keras non-teknis:** uang yang dipakai (bila kelak ada) aman hilang total; dana darurat dan kebutuhan keluarga tidak tersentuh (`personal-finance`). Kesediaan mencatat semua trade, termasuk yang melanggar aturan. Kemampuan dasar spreadsheet; pemrograman opsional (berguna untuk Module 12).

---

# Catatan Scope (menunggu konfirmasi saat approval)

* **Dibuat 2026-09-25 lewat AskUserQuestion:** user memilih lanjut dengan override guard 50% (Character Development pas 3/6 modul; `male-grooming` masih Module 1) dan **domain baru + cross-reference**. Nama domain `scalping-trading` adalah asumsi mentor (bukan pilihan eksplisit user).
* **Yang perlu dikatakan terang di depan:** hasil riset web (Chague dkk. Brasil: 97% day trader yang bertahan >300 hari merugi; Barber dkk. Taiwan: mayoritas rugi setelah biaya; ESMA: 74-89% akun CFD ritel rugi) menunjukkan bahwa **hipotesis awal yang wajar adalah "kamu belum punya edge"**. Outline user sudah memuat sikap sehat (survive first, tidak wajib uang nyata, bukan jaminan profit). Course ini memperkuatnya: **tujuan bukan menemukan profit, tetapi menguji klaim edge dengan jujur, mengelola risiko, dan berhenti bila bukti berkata berhenti.** Keputusan itu ditulis sebagai **Verdict** di Capstone. Bila tujuan user sebenarnya "cara cepat dapat uang", course ini akan mengecewakan; itu diucapkan sekarang, bukan di Module 15.
* **Yang benar-benar baru di repo:** seluruh course. `grep scalp|price action|candlestick` di `courses/` = 0 hasil. Irisan tipis: `investing-stocks` (Module 12 TA hanya pendukung; Module 9 bias; Module 10 position sizing; Module 13 disiplin jual), `cryptocurrency` (Module 10), `anti-scam-risk-management` (Module 4 FOMO, Module 6 position sizing/expected value). Semuanya dirujuk, tidak diulang.
* **Koreksi/penambahan atas outline (menunggu persetujuan user):**
  * **Module 1** ditambah lesson bukti retail day trading dan konteks regulasi Indonesia (outline hanya menyebut spread/fee/slippage).
  * **Butir 7 Personal Scalper Code** ("Jangan mengubah sistem hanya karena beberapa trade loss") dipertahankan tetapi **dipasangkan dengan kriteria statistik kapan sistem harus dihentikan atau direvisi** (Module 2 dan 16). Tanpa itu, butir ini bisa menjadi pembenaran sunk-cost.
  * **Module 9 (Breakout) dan 11 (Liquidity)** memuat konsep yang berasal dari komunitas SMC/ICT ("liquidity sweep", "stop hunt", "manipulasi"). Bukti yang ada (Osler 2003) hanya menunjukkan kluster order stop-loss/take-profit di satu dealer valas; klaim bahwa pihak tertentu sengaja memburu stop trader ritel dilabel *heuristik populer, tidak tervalidasi*. Kalimat outline "Tujuannya bukan menganggap semua pergerakan sebagai manipulasi" didukung dan dijadikan prinsip.
  * **"Volume confirmation" (Module 9, 13)** di pasar spot forex tidak punya volume terpusat (hanya tick volume broker); untuk saham dan futures volume nyata ada. Label per market.
  * **Module 16 (Execution)** memuat latensi dan jenis order; ditambah bahwa scalper ritel bersaing dengan pemain berlatensi rendah (label: kerangka mikrostruktur, sumber dicek).
* **Pilihan market belum ditanyakan** (saham BEI, forex, emas, kripto, atau lainnya). Ini mengubah biaya, jam, leverage, dan regulasi, sehingga Module 1, 11, dan 16 ditulis netral-market dan diisi setelah user memilih di Module 11. Pilihan ini ditanyakan saat approval.
* **Rencana modal nyata belum ditanyakan.** Default course: demo/paper saja. Modal nyata hanya opsi pasca-Verdict (Capstone) dan keputusan user, bukan rekomendasi course.
* **Sumber diverifikasi ulang saat lesson ditulis.** Angka dari studi (persentase, jumlah sampel, periode), biaya transaksi, batas leverage, aturan BEI dan OJK dibaca ulang dari sumber primer. Roadmap menyebut nama dan garis besar, belum final.
* **Klaim yang ditandai eksplisit:** (a) 97% (Chague dkk.) hanya untuk yang bertahan >300 hari di equity futures Brasil 2013-2015, working paper; (b) loss aversion diperdebatkan (Gal & Rucker 2018 vs Mrkva dkk. 2020); (c) Coates & Herbert 2008 sampel kecil dan korelasional; (d) angka viral "90% trader gagal" tidak dipakai tanpa sumber primer; (e) candlestick: Marshall dkk. 2006 tidak menemukan return berlebih di DJIA, studi pasar lain campuran; (f) Lo dkk. 2000: ada informasi pola pada sampel besar, profitabilitas bergantung biaya; (g) backtest tinggi mudah dicapai lewat overfitting (Bailey dkk. 2014); (h) SMC/ICT (liquidity sweep, order block, fair value gap) tidak punya uji peer-reviewed yang kuverifikasi; (i) "R:R 1:2+ adalah standar profesional" tanpa sumber; (j) angka biaya BEI/broker/pajak dicek ulang dari sumber resmi.
* **Cross-reference (tidak diduplikasi):**
  * Module 2 ↔ `statistics-probability` 2, 6, 9-10 (probabilitas, LLN, uji hipotesis); `critical-thinking` Module 4 (expected value).
  * Module 6-8 ↔ `investing-stocks` 12 (TA sebagai pendukung), 10.2 (position sizing); `cryptocurrency` 10.
  * Module 9-10 ↔ `investing-stocks` 9, 13; `anti-scam-risk-management` 4, 6; `emosi` 5, 11; `emotional-resilience` 10; `stoicism`; `lowering-the-ego`; `human-approval`; `dopamine`; `courage-to-try` 3.
  * Module 11-13 ↔ `automation-scripting`, `data-analysis-visualization` (opsional: backtester).
  * Module 1, 11, 17 ↔ `anti-scam-risk-management` 3 (taksonomi scam: signal seller, robot trading, mentor/kelas berbayar, copy trading), `cryptocurrency` 4 (bila market kripto), `personal-finance` (uang aman hilang).
* **Pacing dan overstudy:** belajar trading bisa jadi cara menunda keputusan. Aturan course: satu modul boleh berhari-hari; Test Ledger dan Trade Log lebih penting daripada menambah materi; **tidak menambah setup baru sebelum yang pertama diuji**; hari terlewat dicatat sebagai terlewat; berhenti adalah hasil sah.
* **Bukan rekomendasi.** Tidak ada sinyal, arah harga, instrumen, broker, robot, atau kelas berbayar di course ini.

Asumsi di atas menunggu konfirmasi/koreksi user saat approval, sebelum Lesson 1 dimulai.

---

# Learning Objectives

* [ ] Menjelaskan scalping vs day trading vs swing trading, dan kenapa timeframe pendek tidak lebih mudah (biaya per trade terhadap target kecil, noise, kebutuhan eksekusi)
* [ ] Menjelaskan bukti tentang profitabilitas trader ritel jangka pendek (Brasil, Taiwan, ESMA) dengan batas generalisasinya
* [ ] Menghitung expectancy, memahami peran win rate dan R:R, dan menyimulasikan losing streak serta sample size yang diperlukan
* [ ] Membaca candlestick (OHLC, body, wick) sebagai catatan tarik-menarik buyer/seller, dan menilai pola dengan konteks serta label bukti
* [ ] Mengidentifikasi market structure, tren vs range, support/resistance sebagai area, breakout, pullback, dan kluster likuiditas tanpa mengklaim manipulasi
* [ ] Menyusun entry model tertulis: context, structure, level, trigger, stop, target — dan membedakan confirmation dari overconfirmation
* [ ] Menentukan stop loss sebagai titik invalidasi dan menghitung ukuran posisi dari risiko per trade
* [ ] Menetapkan batas risiko harian (loss per trade, loss harian, loss beruntun, jumlah trade) dan mematuhinya sebagai kill switch
* [ ] Mengenali fear, greed, FOMO, revenge trading, dan ego lewat pola perilaku sendiri, dan memutus loop dengan pause-review-reset
* [ ] Membangun satu setup di satu market dan satu sesi (mastery before variety)
* [ ] Melakukan backtest dan forward test yang jujur (biaya, out-of-sample, hitung konfigurasi yang dicoba) dan menyimpulkan "tidak ada bukti edge" bila itu hasilnya
* [ ] Menyimpan Trade Log lengkap dan meninjau mingguan untuk menemukan pola perilaku, bukan trade yang tampak bagus
* [ ] Mengenali market regime, dampak berita (spread melebar, slippage), dan memahami jenis order dan latensi
* [ ] Menjalankan checklist Pre-Market, During-Market, dan Post-Market, serta No-Trade Rules
* [ ] Menyelesaikan 30-Day Scalper Mind Challenge (demo) dan menyusun My Scalping Playbook, My Scalper Code, dan Verdict dari bukti sendiri

---

# Pemetaan Outline ke Modul

| Outline | Modul | Jenis |
|---|---|---|
| Module 1 (Apa Itu Scalping?) | 1 | **Native** + bukti retail day trading, regulasi Indonesia |
| Module 2 (Scalper Mindset), Module 3 (Trading Is a Probability Game) | 2 | **Native** — expectancy, losing streak, sample size |
| Module 4 (Candlestick Fundamentals), Module 5 (Candlestick Patterns) | 3 | **Native** dengan label bukti |
| Module 6 (Market Structure), Module 7 (Support & Resistance), Module 8 (Trend vs Range) | 4 | **Native** |
| Module 9 (Breakout), Module 10 (Pullback), Module 11 (Liquidity) | 5 | **Native** dengan koreksi SMC/ICT |
| Module 12 (Entry Model), Module 13 (Confirmation), Module 14 (Stop Loss) | 6 | **Native** |
| Module 15 (Position Sizing), Module 16 (Risk/Reward) | 7 | **Native** |
| Module 17 (Daily Risk Limit) | 8 | **Native** |
| Module 18 (Fear), Module 19 (Greed) | 9 | Ringkas + rujukan `emosi`, `emotional-resilience`, `investing-stocks` 9 |
| Module 20 (FOMO), Module 21 (Revenge Trading), Module 22 (Trading Ego) | 10 | Ringkas + rujukan `anti-scam-risk-management` 4, `lowering-the-ego` |
| Module 23 (Build Your Trading Setup), Module 24 (One Setup First) | 11 | **Native** |
| Module 25 (Backtesting) | 12 | **Native** — overfitting |
| Module 26 (Forward Testing) | 13 | **Native** |
| Module 27 (The Trading Journal), Module 28 (Review Your Trades) | 14 | **Native** |
| Module 29 (Market Regime), Module 30 (News & Volatility), Module 31 (Execution) | 15 | **Native** |
| Part X (Scalper Operating System) | 16 | **Native** — checklist dan No-Trade Rules |
| 30-Day Challenge | paralel | Native |
| Final Project + Personal Scalper Code | 17 | **Native** + Verdict |

---

# Course Modules

## Module 1 — Apa Itu Scalping? dan Apa Kata Bukti (Outline Module 1)

Description: Definisi scalping dibanding day trading dan swing trading, kenapa timeframe pendek bukan berarti lebih mudah, dan apa yang sebenarnya diketahui tentang hasil trader ritel jangka pendek. Ditulis netral-market; angka biaya diisi setelah user memilih market (Module 11).

Lessons:

* [ ] 1.1 Scalping, day trading, swing trading — holding period, frekuensi, timeframe; scalper mengambil pergerakan kecil dengan banyak transaksi; label bukti: definisi praktisi
* [ ] 1.2 Biaya yang memakan target kecil — spread, fee, slippage, volatilitas; **kenapa timeframe pendek tidak lebih mudah**: biaya per trade sebagai persentase terhadap target kecil, noise, kebutuhan eksekusi (pertanyaan utama outline); latihan hitung: target 5 poin dengan biaya 2 poin
* [ ] 1.3 Apa kata bukti — Chague, De-Losso & Giovannetti (Brasil, equity futures, 2013-2015), Barber, Lee, Liu & Odean (Taiwan), ESMA (CFD ritel 2018); batas: pasar spesifik, seleksi "yang bertahan", working paper; apa yang bisa dan tidak bisa disimpulkan untuk Indonesia; hipotesis awal course: "belum punya edge"
* [ ] 1.4 Peta regulasi dan risiko penipuan di Indonesia — peralihan pengawasan aset keuangan digital dan derivatif dari Bappebti ke OJK/BI (10 Januari 2025, masa transisi berakhir Januari 2026), Satgas PASTI, ciri signal seller/robot trading/kelas mentor palsu; rujukan `anti-scam-risk-management` 3-4; cara mengecek izin lewat kanal resmi; label: dibaca ulang dari OJK/BI, bukan dari iklan broker

Mini Project: Kartu Biaya — untuk satu instrumen fiktif atau satu market yang kau lirik, hitung biaya round-trip sebagai persentase target scalping 5, 10, dan 20 poin; tulis kesimpulan tiga kalimat "berapa kali harus benar hanya untuk impas".

Status: Not started

---

## Module 2 — Probabilitas, Expectancy, dan Sikap terhadap Kalah (Outline Module 2-3)

Description: Probability > Prediction. Trader tidak perlu selalu benar, tetapi butuh edge yang terukur dan cukup data untuk tahu apakah edge itu ada. Kerangka matematis dirujuk ke `statistics-probability`; di sini penerapan pada trading.

Lessons:

* [ ] 2.1 Probability > Prediction — kenapa win rate saja tidak bermakna; menerima loss, missed entry, losing streak, false breakout sebagai bagian dari distribusi hasil; label: kerangka praktisi
* [ ] 2.2 Expectancy — (win% × rata-rata menang) − (loss% × rata-rata kalah), dalam satuan R; contoh outline "win rate 40% tidak otomatis buruk bila average win > average loss"; **biaya masuk ke perhitungan**; win rate tinggi dengan R:R kecil bisa negatif
* [ ] 2.3 Losing streak dan risk of ruin — simulasi Monte Carlo sederhana (spreadsheet/Python): probabilitas rugi beruntun untuk win rate 40%/50%/60%; kenapa 5-8 kalah beruntun pada sistem sehat normal; rujukan `statistics-probability` 2, 6; label: matematika, bukan klaim tentang market
* [ ] 2.4 Sample size dan kapan sistem dinyatakan gagal — berapa trade cukup untuk membedakan edge dari keberuntungan (interval kepercayaan kasar, rujukan `statistics-probability` 8-9); **melengkapi butir 7 Scalper Code**: kriteria tertulis kapan "beberapa loss" wajar dan kapan sistem harus dihentikan atau direvisi

Mini Project: Simulasikan 1.000 rangkaian 100 trade untuk dua sistem (win 40%/R 2:1 dan win 60%/R 0,7:1) dengan biaya; catat distribusi drawdown, losing streak terpanjang, dan proporsi rangkaian yang rugi setelah 100 trade.

Status: Not started

---

## Module 3 — Candlestick: Dari Dasar sampai Pola dengan Konteks (Outline Module 4-5)

Description: Membaca candle sebagai ringkasan satu periode tarik-menarik buyer dan seller, lalu menguji pola dengan jujur. "Pattern tanpa konteks bukan signal" didukung bukti; "konteks menjamin edge" tidak.

Lessons:

* [ ] 3.1 Anatomi satu candle — open, high, low, close, body, wick, bullish/bearish; apa yang terjadi antara buyer dan seller di dalamnya; batasan: candle bergantung timeframe dan feed data broker
* [ ] 3.2 Pola populer — doji, hammer, shooting star, engulfing, pin bar, inside bar, morning/evening star; definisi objektif (bisa dituliskan sebagai aturan) vs definisi "terlihat"; label: kerangka praktisi/Nison
* [ ] 3.3 Pola dan konteks — pola di tengah range vs di level kunci; blind study: tandai pola pada chart historis tanpa melihat hasilnya, lalu cek; apa yang berubah bila konteks ditambahkan
* [ ] 3.4 Apa kata bukti tentang candlestick — Marshall, Young & Rose (2006, DJIA) tidak menemukan return berlebih signifikan; studi pasar lain campuran; Lo, Mamaysky & Wang (2000); Park & Irwin (2007); implikasi: pola sebagai bahasa deskriptif, bukan mesin sinyal

Mini Project: Blind study 30 candle sinyal — tandai 30 pola dari chart historis (dengan hasil disembunyikan), catat prediksi dan alasan, lalu buka hasil dan hitung proporsi benar dengan dan tanpa filter konteks; tulis apakah selisihnya bermakna dengan n sekecil itu.

Status: Not started

---

## Module 4 — Market Structure, Support & Resistance, Trend vs Range (Outline Module 6-8)

Description: Membaca siapa yang lebih dominan (buyer/seller), area yang penting, dan kapan strategi tertentu cocok. Level sebagai area, bukan garis presisi.

Lessons:

* [ ] 4.1 Market structure — HH/HL/LH/LL, uptrend, downtrend, ranging; swing point yang objektif (aturan tertulis, bukan "terlihat"); label: kerangka praktisi
* [ ] 4.2 Support dan resistance sebagai area — horizontal level, previous high/low, rejection area, supply/demand sebagai istilah praktisi; area of interest, bukan garis; **Osler (2003)**: take-profit menumpuk di angka bulat, stop-loss tepat di luar angka bulat (satu bank valas, 9.655 order, 1999-2000) sebagai penjelasan parsial kenapa level bulat berperan; batas
* [ ] 4.3 Tren vs range vs transisi — pullback → continuation di tren, support → resistance di range, range → tren dan sebaliknya; kenapa tidak semua market cocok untuk satu strategi; cara objektif mengklasifikasi (mis. slope, ATR), rujukan `investing-stocks` 12 untuk sisi investasi
* [ ] 4.4 Konteks multi-timeframe secukupnya — timeframe lebih tinggi sebagai konteks; jebakan analisis berlebihan; batas: tidak ada bukti bahwa multi-timeframe menaikkan expectancy tanpa pengujian

Mini Project: Klasifikasi 20 potongan chart historis (tren/range/transisi) berdasarkan aturan tertulis; bandingkan dengan klasifikasi "feeling" sendiri; catat disagreement.

Status: Not started

---

## Module 5 — Breakout, Pullback, dan Liquidity (Outline Module 9-11)

Description: Tiga pola gerak harga yang paling sering dijadikan setup, dengan koreksi tentang konsep likuiditas ala komunitas SMC/ICT.

Lessons:

* [ ] 5.1 Breakout dan false breakout — retest, continuation, konfirmasi volume (label per market: volume nyata di saham dan futures, tick volume di forex spot); pertanyaan outline: breakout sungguhan atau mengambil likuiditas?; cara menguji tanpa memilih hasil setelah melihat
* [ ] 5.2 Impulse → pullback → continuation — pullback sehat, dalam, dan reversal; cara membedakan dengan aturan tertulis (kedalaman, struktur); batas: klasifikasi setelah kejadian selalu mudah, sebelum kejadian tidak
* [ ] 5.3 Liquidity — equal highs/lows, stop cluster, liquidity sweep, breakout trap; **apa yang didukung bukti** (kluster order ada; Osler 2003, 2005) **dan apa yang tidak** (bahwa pihak tertentu sengaja memburu stop trader ritel; SMC/ICT tidak punya uji peer-reviewed yang kuverifikasi); prinsip outline: tujuannya bukan menganggap semua gerak sebagai manipulasi
* [ ] 5.4 Menulis setup sebagai aturan yang bisa diuji — mengubah kalimat "false breakout di equal highs" jadi kondisi objektif; kenapa kalimat yang tidak bisa dituliskan sebagai aturan tidak bisa di-backtest

Mini Project: Pilih satu pola dari lesson 5.1-5.3 dan tulis dalam 6 baris aturan objektif (kondisi, trigger, stop, target, pembatalan); jangan diuji dulu.

Status: Not started

---

## Module 6 — Entry Model, Confirmation, dan Stop Loss (Outline Module 12-14)

Description: Entry bukan karena "candle-nya bagus". Rantai keputusan tertulis dari konteks sampai take profit, dan stop loss sebagai titik invalidasi ide.

Lessons:

* [ ] 6.1 Entry model — Context → Market Structure → Key Level → Trigger → Entry → Stop Loss → Take Profit; checklist satu halaman; setiap kotak harus bisa dijawab ya/tidak
* [ ] 6.2 Confirmation vs overconfirmation — rejection, engulfing, structure break, retest, volume, momentum; terlalu banyak konfirmasi membuat entry terlambat dan R:R memburuk; **menghitung biaya menunggu konfirmasi** (R yang hilang) dan bahayanya: menambah konfirmasi setelah melihat hasil = overfitting (rujukan Module 12)
* [ ] 6.3 Stop loss sebagai invalidasi — "harga maksimum yang bersedia dibayar untuk membuktikan ide salah"; technical stop, volatility-based stop (mis. ATR), invalidation point; stop terlalu dekat vs terlalu jauh; moving stop dan trailing sebagai keputusan yang diuji, bukan dicoba-coba saat trade berjalan
* [ ] 6.4 Stop hunting — apa yang bisa dan tidak bisa disimpulkan; spread melebar dan slippage di stop sebagai penjelasan yang lebih sederhana; cara menaruh stop dengan memperhitungkan kluster di angka bulat (Osler 2003) tanpa menyebutnya "manipulasi"

Mini Project: Ambil aturan dari Module 5.4 dan lengkapi rantai entry model; gambar 10 contoh historis dan tandai di mana stop sebenarnya valid (invalidasi) vs sekadar jarak nyaman.

Status: Not started

---

## Module 7 — Position Sizing dan Risk/Reward (Outline Module 15-16)

Description: Skill terpenting: ketika salah, akun tetap hidup. Position sizing dan R:R sebagai hubungan, bukan target terpisah.

Lessons:

* [ ] 7.1 Risk per trade → stop distance → position size — rumus dasar, dalam persentase modal; contoh dengan berbagai stop; kenapa ukuran mengikuti stop, bukan sebaliknya; rujukan `investing-stocks` 10.2 dan `anti-scam-risk-management` 6.1
* [ ] 7.2 Berapa risiko per trade? — kenapa persentase kecil (kisaran yang dipakai praktisi; sumber dicek), efek majemuk kalah beruntun, risk of ruin dari Module 2.3 terhadap ukuran risiko; **leverage bukan strategi**: leverage mengubah ukuran posisi dan margin, bukan risiko per trade bila dihitung benar; ESMA sebagai konteks
* [ ] 7.3 Risk/Reward — 1:1, 1:2, 1:3, asymmetric payoff; R:R vs probabilitas (target lebih jauh = peluang mencapai lebih kecil); jangan mengejar R:R besar membabi buta; hubungan dengan expectancy dan biaya
* [ ] 7.4 Kelly dan alternatifnya secukupnya — kenapa Kelly penuh terlalu agresif dan bergantung estimasi edge yang tidak stabil; fixed fractional sebagai default; label: teori (Kelly 1956), bukan resep

Mini Project: Tabel ukuran posisi — untuk modal fiktif 10 juta, hitung ukuran posisi untuk risiko 0,5%, 1%, 2% dan lima stop berbeda; lalu simulasi 10 loss beruntun pada masing-masing dan catat sisa modal.

Status: Not started

---

## Module 8 — Daily Risk Limit: Kill Switch Pribadi (Outline Module 17)

Description: Aturan yang ditulis sebelum pasar dibuka, karena setelah rugi otak tidak bisa dipercaya. Analogi engineering: circuit breaker dan rate limit.

Lessons:

* [ ] 8.1 Empat batas — maximum loss per trade, maximum daily loss, maximum consecutive losses, maximum number of trades; contoh outline: "3 loss beruntun → berhenti hari itu" (bukan karena pasar pasti buruk, tetapi mencegah revenge trading); menentukan angka dari expectancy dan simulasi Module 2.3
* [ ] 8.2 Kenapa batas harus dipasang sebelum trade — Thaler & Johnson (1990): break-even effect dan house-money effect mengubah risiko setelah rugi/untung; label: eksperimen dan gambling, bukan trader profesional; pre-commitment (rujukan `anti-scam-risk-management` 4.4)
* [ ] 8.3 Mekanisme yang tidak bergantung kemauan — hard stop di platform, batas deposit, kunci akun, orang ketiga; analogi kill switch, circuit breaker; apa yang dilakukan saat batas tercapai (pause → review → reset)
* [ ] 8.4 Kapan batas boleh diubah — hanya di luar jam trading, berdasarkan data, dengan catatan; bukan setelah hari buruk

Mini Project: Tulis Daily Risk Limit satu halaman dengan angka dan mekanisme penegakannya; jalankan simulasi tiga hari buruk (data fiktif) dan tulis keputusan yang akan diambil di setiap batas.

Status: Not started

---

## Module 9 — Fear dan Greed di Meja Trading (Outline Module 18-19)

Description: Modul ringkas. Emosi dipahami sebagai sinyal tentang ukuran risiko dan kejelasan aturan. Mekanisme umum emosi dirujuk; sudut di sini: konteks trading.

Lessons:

* [ ] 9.1 Fear — posisi terlalu besar, stop terlalu dekat, tidak percaya sistem, takut kehilangan profit; **solusi utama outline: kurangi risk sampai keputusan kembali rasional**; rujukan `emosi` 5, 11 dan `emotional-resilience` 10 untuk regulasi; batas: takut bukan selalu sinyal ukuran salah
* [ ] 9.2 Greed — overtrading, oversized position, mengejar candle, memperlebar target, tidak mengambil profit sesuai sistem; "Enough is a position too"; Odean (1998) disposition effect (menjual yang untung terlalu cepat, menahan yang rugi terlalu lama); label: riset akun broker, bukan hukum
* [ ] 9.3 Fisiologi stres trader secukupnya — Coates & Herbert (2008): kortisol naik dengan varians hasil dan volatilitas, testosteron pagi dan profit hari itu; sampel kecil dan korelasional; kegunaan: mengakui bahwa tubuh ikut, tanpa resep hormon; batas: bukan terapi
* [ ] 9.4 Prosedur kecil — timer istirahat, napas, jalan kaki; bukan ritual magis; menulis "apa yang kurasa" sebelum tombol ditekan; kapan berhenti total untuk hari itu

Mini Project: Pemetaan pemicu — dari data demo/hari nyata, catat tiga momen di mana rasa takut atau serakah muncul, apa yang dilakukan, dan aturan yang seharusnya berlaku.

Status: Not started

---

## Module 10 — FOMO, Revenge Trading, dan Ego (Outline Module 20-22)

Description: Tiga pola yang paling sering merusak akun. Ringkas dan merujuk; sudut trading: loop dan pemutusnya.

Lessons:

* [ ] 10.1 FOMO — harga naik tanpa kita; tidak ada kewajiban mengambil setiap pergerakan; **missed trade ≠ loss**; rujukan `anti-scam-risk-management` 4 (manufactured urgency: countdown, signal grup, "sedang naik!"); mencatat trade yang sengaja dilewati
* [ ] 10.2 Revenge trading — loop Loss → Emotion → Bigger position → More loss; pemutus Loss → Pause → Review → Reset; Thaler & Johnson (1990) break-even effect sebagai dasar; **kill switch Module 8** sebagai pertahanan; tanda peringatan pribadi
* [ ] 10.3 Trading ego — "saya harus membuktikan analisis saya benar"; market tidak peduli; pertanyaan profesional: apa yang membuat analisis invalid?; rujukan `lowering-the-ego`, `human-approval`; kaitan dengan menggeser stop, menambah posisi rugi, menolak menerima loss
* [ ] 10.4 Ketika trading terasa kompulsif — tanda peringatan (mengejar kerugian, berbohong soal trading, tidak bisa berhenti, memakai uang yang bukan milik sendiri); rujukan profesional/layanan bantuan judi kompulsif; label: kesehatan mental, di luar lingkup course; **course boleh dihentikan**

Mini Project: Tulis protokol tiga langkah pribadi untuk FOMO, satu untuk revenge trading, satu untuk ego (tindakan konkret, bukan slogan); uji pada dua skenario simulasi.

Status: Not started

---

## Module 11 — Build Your Setup: Satu Setup Dulu (Outline Module 23-24)

Description: Memilih market, sesi, timeframe, dan satu-dua setup. Mastery before variety. Market dipilih di sini dan mengisi biaya, jam, dan regulasi dari Module 1.

Lessons:

* [ ] 11.1 Memilih market — saham BEI, forex, emas, kripto, atau lainnya; perbedaan biaya, jam, likuiditas, leverage, regulasi, dan risiko; pilih **satu**; label: perbandingan dibaca ulang dari sumber resmi (BEI/OJK/BI/broker berizin); rujukan `cryptocurrency` 4 bila kripto
* [ ] 11.2 Sesi dan timeframe — kapan pasar paling likuid, kapan berita sering keluar; kenapa satu sesi; jam trading yang cocok dengan kehidupan nyata (kerja, ibadah, tidur); jangan trading saat lelah
* [ ] 11.3 Menulis setup lengkap — Market, Session, Timeframe, Context, Setup, Trigger, Invalidation, Target (delapan kotak outline); 1-2 setup dari Module 5; 1 risk model dari Module 7-8
* [ ] 11.4 Mastery before variety — kenapa 20 strategi mengaburkan sampel; aturan "tidak menambah setup sebelum yang pertama lulus Module 13"; jebakan mengganti setup setiap kali rugi

Mini Project: Setup Sheet satu halaman berisi delapan kotak outline plus risk model dan daftar kondisi tidak trading; tanpa satu pun angka profit target harian.

Status: Not started

---

## Module 12 — Backtesting yang Jujur (Outline Module 25)

Description: Menguji setup dengan data historis tanpa menipu diri sendiri. Modul ini berat di kejujuran statistik; pemrograman opsional.

Lessons:

* [ ] 12.1 Apa yang diuji dan apa yang dicatat — total trades, win rate, average win, average loss, expectancy, maximum drawdown, losing streak, kualitas setup (outline); **biaya dimasukkan**: spread, fee, slippage asumsi; Test Ledger sebagai buku catatan
* [ ] 12.2 Bias dalam backtest — look-ahead, survivorship, cherry-picking chart, "terlihat jelas di belakang"; kenapa manual backtest rentan bias
* [ ] 12.3 Overfitting dan data snooping — Bailey, Borwein, López de Prado & Zhu (2014): makin banyak konfigurasi dicoba, makin mudah backtest tampak bagus dan makin buruk di luar sampel; **wajib mencatat jumlah konfigurasi yang dicoba**; in-sample vs out-of-sample, walk-forward; rujukan Park & Irwin (2007) tentang data snooping
* [ ] 12.4 Membaca hasil dengan skeptis — berapa trade cukup; interval kepercayaan kasar (rujukan `statistics-probability` 8-9); apa artinya "tidak ada bukti edge"; (opsional) membuat backtester kecil dengan Python/pandas atau spreadsheet; rujukan `automation-scripting`, `data-analysis-visualization`

Mini Project: Backtest 50-100 trade satu setup dengan biaya, satu set parameter tetap yang ditetapkan sebelum melihat hasil; laporan satu halaman: metrik outline, jumlah konfigurasi, dan satu kalimat "apa yang tidak dibuktikan oleh tes ini".

Status: Not started

---

## Module 13 — Forward Testing (Outline Module 26)

Description: Jembatan dari data historis ke waktu nyata, bertahap. Default: demo/paper. Modal nyata bukan bagian course kecuali user memutuskan sendiri setelah Verdict.

Lessons:

* [ ] 13.1 Kenapa backtest tidak cukup — perbedaan eksekusi nyata: slippage, requote, psikologi; forward test sebagai out-of-sample nyata
* [ ] 13.2 Paper/demo yang serius — aturan sama dengan rencana, catat semua, biaya realistis; batas demo: tidak ada tekanan uang nyata; hasil demo tidak menjamin hasil nyata
* [ ] 13.3 Tahap: demo → modal kecil → ukuran normal — jembatan dari outline; **kriteria maju ke tahap berikut tertulis sebelum dimulai** (jumlah trade, expectancy, drawdown maksimum, kepatuhan aturan); kriteria mundur/berhenti; keputusan modal nyata milik user, di luar rekomendasi course
* [ ] 13.4 Membandingkan backtest dan forward — degradasi performa yang wajar vs tanda overfitting; apa yang dilakukan bila forward buruk (jangan mengganti setup di tengah pengujian; catat dan tetapkan aturan revisi)

Mini Project: Rencana Forward Test satu halaman: durasi/jumlah trade, kriteria lulus/gagal, aturan revisi, dan batas modal (bila kelak nyata).

Status: Not started

---

## Module 14 — Trading Journal dan Review Mingguan (Outline Module 27-28)

Description: Journal lebih penting dari feeling. Tujuan: menemukan pola perilaku, bukan trade yang terlihat bagus.

Lessons:

* [ ] 14.1 Trade Log — date, time, market, setup, entry, stop, target, risk, result (R), screenshot, alasan masuk, emosi sebelum/saat/sesudah, dan "apakah mengikuti aturan? aturan mana yang dilanggar"; rekam trade yang dilewati
* [ ] 14.2 Review mingguan — What worked? What failed? What mistake repeated? Which setup has the best evidence? When do I trade badly?; menghitung: kepatuhan aturan, expectancy aktual vs rencana, biaya
* [ ] 14.3 Pola perilaku — jam buruk, hari buruk, setelah loss besar, setelah streak untung (house-money effect); analogi engineering: post-mortem tanpa menyalahkan; **data, bukan kesan**
* [ ] 14.4 Kapan hasil review berarti mengubah sistem — aturan revisi tertulis, satu perubahan per siklus, hitung ulang sampel; tidak mengubah sistem karena beberapa loss (butir 7) tetapi berhenti bila kriteria Module 2.4 tercapai

Mini Project: Sepuluh entri Trade Log dari demo (atau simulasi replay), plus satu review mingguan lengkap dengan satu pola perilaku dan satu aturan koreksi.

Status: Not started

---

## Module 15 — Market Regime, Berita, dan Eksekusi (Outline Module 29-31)

Description: Setup tidak bekerja di semua kondisi. Berita dan eksekusi menentukan biaya nyata.

Lessons:

* [ ] 15.1 Market regime — volatilitas tinggi/rendah, tren, range, news-driven; cara objektif mengukur (ATR, lebar range); satu strategi tidak harus bekerja pada semua kondisi; **menguji setup per regime** dan menulis kondisi tidak trading
* [ ] 15.2 Berita dan volatilitas — spread melebar, slippage, fake breakout, reversal cepat; kalender ekonomi (sumber dicek); prinsip outline: *Sometimes the best trade is no trade*
* [ ] 15.3 Jenis order dan eksekusi — market, limit, stop, stop-limit; spread, slippage, likuiditas, latensi; kapan tiap order cocok; **tradeoff**: limit tidak terisi vs market membayar spread; label: kerangka mikrostruktur (Harris; dicek)
* [ ] 15.4 Scalper ritel di pasar berlatensi rendah — pemain dengan infrastruktur lebih cepat; apa yang realistis bagi ritel (kecepatan bukan keunggulan ritel); implikasi pada pilihan setup; label: kerangka, dicek dari sumber mikrostruktur sebelum ditulis

Mini Project: Uji satu setup Module 12 pada dua regime dan pada jendela berita vs non-berita; laporan: kapan tidak trading.

Status: Not started

---

## Module 16 — Scalper Operating System: Checklist dan No-Trade Rules (Outline Part X)

Description: Mengubah semua modul menjadi rutinitas harian yang bisa dijalankan dan diaudit.

Lessons:

* [ ] 16.1 Pre-Market — kondisi pasar, berita penting, key levels, sesi, risiko maksimum, setup yang dicari; checklist satu halaman; tidak menambah setup baru
* [ ] 16.2 During Market — apakah setup valid, apakah risk sesuai, apakah FOMO, apakah revenge, apakah kondisi berubah; aturan "tidak ada trade bila salah satu kotak tidak bisa dijawab"
* [ ] 16.3 Post-Market — berapa trade, berapa loss, apakah mengikuti sistem, kesalahan, perbaikan; entri Trade Log; menutup layar
* [ ] 16.4 No-Trade Rules dan kriteria berhenti — kapan tidak boleh trading (lelah, marah, berita besar, di luar sesi, setelah batas harian); **kapan menghentikan seluruh sistem** (kriteria statistik Module 2.4/14.4) dan apa yang dilakukan setelah itu

Mini Project: Checklist tiga bagian plus No-Trade Rules (sepuluh baris); jalankan tiga hari demo dan catat berapa kali checklist dilanggar.

Status: Not started

---

## Module 17 — Capstone: My Scalping Playbook + Verdict (Final Project)

Description: Mengubah Test Ledger, Trade Log, dan Challenge menjadi Playbook, Scalper Code, dan Verdict yang jujur.

Lessons:

* [ ] 17.1 Kompilasi bukti — Test Ledger, Trade Log, Challenge; angka: total trade, expectancy, drawdown, kepatuhan aturan, biaya; satu klaim populer yang kuubah setelah dicek (mis. konfirmasi lebih banyak, R:R besar, stop hunting)
* [ ] 17.2 My Scalping Playbook — sepuluh bagian outline: My Market, Timeframe, Setup, Entry Rules, Invalidation, Risk Rules, Exit Rules, No-Trade Rules, Psychology Rules, Review System — masing-masing berdasarkan bukti sendiri
* [ ] 17.3 My Scalper Code — versi sendiri dari sepuluh butir outline; tandai butir yang kuubah; **pasangkan butir 7 dengan kriteria berhenti**; tulis kalimat penutup outline dalam kata sendiri
* [ ] 17.4 Verdict — Lanjut (kriteria tercapai, modal kecil hanya bila user memilih), Ulangi pengujian (bukti belum cukup), atau Berhenti (tidak ada bukti edge atau biaya melebihi); hasil "Berhenti" adalah hasil sah; simpan ke `portofolio/`

Capstone Project: **My Scalping Playbook** + **My Scalper Code** + **Verdict**, disimpan ke `portofolio/`.

Status: Not started

---

# 30-Day Scalper Mind Challenge (paralel, demo saja)

Setiap malam satu entri Trade Log atau latihan blind study (format di `domains/scalping-trading/DOMAIN.md`). **Tidak wajib uang nyata.** Tidak boleh memakai uang nyata untuk Challenge.

| Minggu | Hari | Fokus | Setelah modul |
|---|---|---|---|
| 1 | 1-7 | Market Reading — candlestick, market structure, blind study | Module 1-4 |
| 2 | 8-14 | Price Action — support/resistance, breakout, pullback, liquidity | Module 5-7 |
| 3 | 15-21 | System — satu setup, backtest, Test Ledger | Module 8, 11-13 |
| 4 | 22-30 | Psychology — disiplin, FOMO, fear, greed, revenge, journaling | Module 9-10, 14-16 |

Aturan tambahan: hari terlewat dicatat sebagai terlewat; tidak menambah setup baru di minggu 4; trade yang melanggar aturan tetap dicatat; bila Challenge memicu perilaku kompulsif (mengejar kerugian demo untuk "menang", tidak bisa berhenti), hentikan dan lihat Module 10.4; kewajiban kerja dan keluarga tidak dikorbankan.

---

# Capstone Project

Description: **My Scalping Playbook** (sepuluh bagian), **My Scalper Code** (versi sendiri), dan **Verdict** (Lanjut/Ulangi/Berhenti) — lihat Module 17, berbasis Test Ledger, Trade Log, dan 30-Day Challenge nyata.

Acceptance Criteria:

* Test Ledger minimal 50-100 trade (backtest) dan Trade Log minimal 30 trade forward (demo) tersedia sebagai bukti
* Setiap butir Playbook dan Scalper Code punya kejadian atau angka sebagai dasar, bukan kalimat indah
* Hasil dilaporkan **dengan biaya**, dengan jumlah konfigurasi yang dicoba diungkap
* Terdapat catatan jujur: trade yang melanggar aturan, hari buruk, dan minimal dua klaim populer yang kuubah atau kubuang setelah dicek
* Ada **kriteria berhenti** tertulis dan Verdict yang konsisten dengan data (bukan dengan keinginan)
* Review Style dipenuhi: Bukti vs Kesan, Biaya Diperhitungkan, Kejujuran terhadap Pelanggaran, Tidak Ada Retro-Fitting, Risiko sebelum Keuntungan, Kriteria Berhenti Tertulis
* Disimpan ke `portofolio/`

Status: Not started

---

# Overall Progress

Module Completion: 0/17

Overall Completion: 0%

Current Module: —

Current Lesson: —

Next Lesson: 1.1 (menunggu approval user)
