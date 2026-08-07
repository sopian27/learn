# Investing & Stocks — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Investing & Stocks (Investasi & Saham)

## Scope

Investasi saham individual secara mendalam: cara kerja pasar saham, cara membaca laporan keuangan perusahaan, analisis fundamental (business quality & valuasi), konstruksi portofolio saham, psikologi/perilaku investor, dan mekanisme praktis transaksi saham — mencakup **Bursa Efek Indonesia (IDX/BEI)** sebagai basis utama (relevan untuk investor Indonesia) **plus** pasar global (AS/internasional) sebagai perluasan, sesuai hasil kalibrasi user. Pendekatan inti: **fundamental/value investing jangka panjang** (ala Graham-Buffett-Lynch), dengan technical analysis diperkenalkan hanya sebagai literasi pendukung di modul akhir — bukan strategi utama.

Secara eksplisit TIDAK mencakup (untuk menghindari overlap dengan domain/course lain di repo ini):

- **Perencanaan keuangan pribadi secara umum** (budgeting, dana darurat, asuransi, alokasi aset lintas kelas — saham vs obligasi vs properti vs deposito, keputusan rekening pensiun) — itu wilayah `domains/personal-finance/DOMAIN.md`. Domain ini adalah pendalaman khusus untuk *baris "investing" saja* di dalam Investment Policy Statement personal-finance: begitu keputusan "berapa persen ke saham" dibuat di sana, domain ini yang mengajarkan cara memilih, menilai, dan mengelola saham individual itu sendiri.
- **Cryptocurrency** — sudah punya domain sendiri, `domains/cryptocurrency/DOMAIN.md`. Tidak dibahas di sini sama sekali.
- **Trading aktif/technical analysis sebagai strategi utama** (scalping, day-trading, chart pattern sebagai basis keputusan entry-exit) — di luar cakupan utama sesuai kalibrasi user ("fundamental dulu, technical hanya literasi tambahan"). Modul technical analysis di course ini eksplisit dibingkai sebagai alat bantu timing, bukan pengganti thesis fundamental.
- **Analisis sekuritas non-saham** (obligasi korporat/pemerintah mendalam, reksadana, derivatif/options) — disinggung sebatas perbandingan konseptual saat relevan (mis. reksadana sebagai alternatif "malas riset sendiri"), tidak didalami sebagai instrumen sendiri.
- **Pajak & hukum di luar konteks transaksi saham** — hanya membahas pajak/regulasi yang spesifik terkait transaksi/kepemilikan saham (PPh final transaksi, pajak dividen, aturan OJK/BEI); pajak penghasilan umum atau legal-entity planning tetap wilayah `domains/personal-finance/DOMAIN.md`.

## Trusted Sources / Research Priority

1. **Benjamin Graham — "The Intelligent Investor"** dan **Graham & Dodd — "Security Analysis"** — kitab dasar value investing, sumber prinsip margin of safety yang jadi tulang punggung course ini.
2. **Warren Buffett — Berkshire Hathaway Shareholder Letters** (berkshirehathaway.com/letters/letters.html, gratis, sumber primer) — evolusi pemikiran value investing dari "cigar butt" Graham ke "wonderful business at fair price".
3. **Aswath Damodaran (NYU Stern)** — blog "Musings on Markets" (damodaran.blogspot.com) dan kuliah "Valuation" gratis di YouTube/website pribadinya — rigor akademik untuk metodologi valuasi (DCF, relative valuation), dipakai lintas modul valuasi.
4. **Peter Lynch — "One Up On Wall Street"** dan **"Beating the Street"** — pendekatan stock-picking praktis untuk investor ritel individual, dasar modul growth-at-reasonable-price.
5. **Howard Marks — Oaktree memos** (oaktreecapital.com/insights/memo) dan **"The Most Important Thing"** — pemahaman siklus pasar dan disiplin risiko.
6. **OJK** (ojk.go.id, termasuk program EduFin) dan **BEI/IDX** (idx.co.id) — sumber resmi regulasi pasar modal Indonesia, mekanisme perdagangan (lot, ARA/ARB, auto-reject), dan daftar sekuritas resmi.
7. **DJP** (pajak.go.id) — aturan PPh Final transaksi saham (0,1% dari nilai bruto penjualan, PP 41/1994) dan pajak dividen (PPh Final 10% per PP 9/2021 jo UU HPP, dengan pengecualian dividen yang diinvestasikan kembali).
8. **Laporan keuangan resmi emiten IDX** (idx.co.id, laporan tahunan & keuangan) — sumber primer untuk latihan membaca laporan keuangan riil, bukan ringkasan pihak ketiga.
9. **SEC EDGAR** (sec.gov/edgar) — sumber primer filing perusahaan AS (10-K, 10-Q) untuk modul saham global.
10. **Morgan Housel — "The Psychology of Money"** — lapisan perilaku/psikologi investor, dipakai di modul behavioral finance.

Jangan pernah membangun lesson dari blog/artikel aplikasi sekuritas (mis. konten marketing broker) sebagai satu-satunya sumber — selalu cross-check ke minimal satu sumber 1-9 di atas, mengikuti bar "Vetting a Source" di `standards/UNIVERSAL_STANDARDS.md`. Konten marketing broker boleh dipakai untuk info praktis non-kontroversial (mis. cara buka RDN), tapi tidak untuk klaim investasi apapun.

## Applied Practice Definition

Setiap lesson menghasilkan satu artifact nyata berbasis data perusahaan riil — bukan angka contoh yang dikarang. Contoh: membaca & meringkas laporan keuangan riil satu emiten IDX, membangun model valuasi DCF sederhana untuk satu saham riil, menulis investment thesis lengkap untuk satu saham (IDX atau global), membangun & melacak portofolio (nyata dengan modal kecil jika learner siap, atau paper portfolio bila belum). Prioritaskan modal riil kecil begitu learner nyaman (course ini mengasumsikan modal awal terbatas seperti investor pemula pada umumnya), dengan paper portfolio sebagai fallback yang jelas ditandai sampai learner siap.

## Project Tiers

- **Mini Project** (tiap modul): satu artifact tunggal — mis. satu analisis rasio keuangan 1 emiten, satu perhitungan valuasi DCF/relative valuation, satu ringkasan qualitative moat analysis.
- **Intermediate Project** (tiap section besar): gabungan beberapa modul — mis. satu investment thesis lengkap (business quality + valuasi + risiko) untuk 1 saham siap-beli, atau konstruksi mini-portofolio 5-10 saham dengan rasional alokasi tiap posisi.
- **Capstone / Portfolio Project** (akhir course): satu portofolio saham yang benar-benar dilacak (nyata atau paper, minimal beberapa bulan), dengan investment thesis terdokumentasi untuk tiap holding, aturan buy/sell yang eksplisit, dan review performa berkala — disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths / Weaknesses / Actionable Improvements / Score 0-100), setiap review thesis/portofolio saham di domain ini juga mengecek:

- **Margin of Safety** — apakah ada selisih jelas antara estimasi nilai intrinsik dan harga pasar, atau harga dibayar mepet/lebih mahal dari estimasi nilai wajar?
- **Business Quality** — apakah alasan beli berbasis kualitas bisnis (moat, manajemen, kesehatan finansial), bukan sekadar "murah" atau ikut tren/rumor?
- **Circle of Competence** — apakah learner benar-benar paham model bisnis perusahaan yang dianalisis, atau menganalisis sektor yang tidak dipahami?
- **Risk Discipline** — apakah position sizing dan diversifikasi masuk akal, dan apakah skenario downside (thesis salah) sudah dipikirkan sebelum beli, bukan sesudah rugi?

## Assessment Form

Concept-check singkat setelah bagian teori tiap lesson, ditambah **"investment committee pitch"** — learner mempresentasikan dan mempertahankan satu stock pick di depan panel skeptis (tanya-jawab menantang asumsi valuasi & thesis) sebelum posisi tersebut boleh masuk portofolio, memaksa rigor sebelum eksekusi nyata.

## Practitioner Reference Frame

Bandingkan beberapa mazhab pendekatan untuk masalah stock-picking yang sama: **Benjamin Graham/deep-value** (margin of safety ketat, kuantitatif, beli "cigar butt" murah), **Warren Buffett & Charlie Munger** (evolusi ke "wonderful business at fair price", kualitatif-kuantitatif seimbang), **Peter Lynch/growth-at-reasonable-price** (pendekatan retail-friendly, "invest in what you know"), dan **Boglehead/indexing skeptic** (argumen jujur kenapa stock-picking individual sering kalah dari index fund — counterpoint yang disengaja dimasukkan supaya learner sadar trade-off, bukan buta terhadap kritik terhadap stock-picking). Untuk konteks IDX, tambahkan perspektif **analis riset sekuritas lokal** (bagaimana broker Indonesia meng-cover saham IDX secara institusional) sebagai referensi kelima yang lebih lokal.

## Domain-Specific Standards

Belum ada — `standards/UNIVERSAL_STANDARDS.md` cukup untuk saat ini. Tambahkan `domains/investing-stocks/standards/` hanya jika muncul kebutuhan berulang untuk aturan rigor spesifik domain ini (mis. standar minimum margin of safety sebelum boleh masuk portofolio nyata).

## Domain Goal

Akan disempurnakan bersama learner saat `courses/investing-stocks/roadmap.md` disetujui — sementara: mampu meriset, menilai (valuasi), dan mengambil keputusan beli/tahan/jual saham individual (IDX maupun global) secara mandiri memakai proses fundamental yang terdokumentasi dan bisa diulang — bukan berdasar tips, rumor, atau FOMO.
