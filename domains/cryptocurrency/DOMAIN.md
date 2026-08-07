# Cryptocurrency — Domain Pack

## Domain Name

Cryptocurrency — dari 0 sampai Expert (Teknologi, Ekonomi, Keamanan, dan Analisis, Bukan Sekadar Trading)

## Scope

Memahami dan memakai cryptocurrency secara mendalam dari sisi **pengguna, investor, dan analis** — bukan dari sisi pengembang protokol. Mencakup: sejarah uang & masalah yang dipecahkan Bitcoin, mekanisme blockchain secara konsep (proof-of-work/proof-of-stake, UTXO vs account model), keamanan self-custody (private key, seed phrase, hardware wallet), exchange (CEX/DEX) & regulasi (termasuk Bappebti/OJK Indonesia), Ethereum & smart contract dari sudut pandang pemakai (bukan penulis kode), on-chain analysis (membaca block explorer, wallet tracking, forensik dasar), tokenomics & due diligence framework, DeFi (AMM, lending, staking, yield), stablecoin & NFT, trading & manajemen risiko, sampai konteks makro/institusional (ETF, CBDC, regulasi global).

Secara eksplisit TIDAK mencakup:

- **Menulis smart contract (Solidity) atau membangun blockchain dari nol** — itu sudah punya course sendiri di `courses/blockchain/roadmap.md` (domain `software-engineering`, fokus rekayasa: implementasi Java blockchain toy → Solidity/EVM → dApp). Domain ini murni sisi pemakaian/analisis; kalau suatu modul perlu baca smart contract, itu dilakukan lewat block explorer (Etherscan), bukan menulis kode. Kedua course saling melengkapi, tidak tumpang tindih — pelajar boleh ambil keduanya.
- **Nasihat keuangan personal spesifik ("beli koin X sekarang")** — domain ini mengajarkan kerangka evaluasi (due diligence, manajemen risiko) supaya learner bisa memutuskan sendiri, bukan memberi rekomendasi beli/jual. Selaras dengan batasan serupa di `domains/personal-finance/DOMAIN.md`.
- **Trading derivatif/leverage/margin sebagai skill inti** — course menyinggung risikonya (Modul 10) tapi tidak mengajarkan strategi leverage trading sebagai kompetensi yang dilatih; ini area berisiko tinggi yang di luar tujuan "expert dari sisi paham & aman", bukan "expert trader berisiko tinggi".
- **Kepatuhan pajak/hukum penuh secara legal-formal** — konten regulasi & pajak tetap konseptual dan menandai eksplisit "konsultasikan ke profesional pajak/hukum berlisensi di Indonesia", sama seperti batasan tax/estate di `domains/personal-finance/DOMAIN.md`.

## Trusted Sources / Research Priority

1. **Bitcoin whitepaper (Satoshi Nakamoto, 2008)** dan **Ethereum whitepaper/yellowpaper (Vitalik Buterin/Gavin Wood)** — sumber primer asli, dipakai untuk memahami desain dari akarnya, bukan versi yang sudah disederhanakan/diputarbalikkan media.
2. **"Bitcoin and Cryptocurrency Technologies" (Narayanan, Bonneau, Felten, Miller, Goldfeder — Princeton, gratis di bitcoinbook.cs.princeton.edu)** — textbook akademik paling rigorous yang menggabungkan sisi teknis, ekonomi, dan keamanan sekaligus; prioritas tertinggi untuk kedalaman konseptual karena ditulis peneliti keamanan komputer, bukan pelaku pasar dengan insentif komersial.
3. **"Mastering Bitcoin" dan "Mastering Ethereum" (Andreas M. Antonopoulos, O'Reilly, gratis di GitHub)** — referensi teknis paling dipakai luas di industri, ditulis praktisi/edukator dengan rekam jejak panjang dan independen dari kepentingan satu proyek/exchange tertentu.
4. **ethereum.org/developers** dan **bitcoin.org/en/developer-documentation** — dokumentasi resmi tiap proyek untuk fakta level protokol (gas, EVM, UTXO, dll) yang harus akurat dan up-to-date.
5. **Bappebti (Badan Pengawas Perdagangan Berjangka Komoditi)** dan **OJK (Otoritas Jasa Keuangan)** — otoritas regulasi resmi Indonesia untuk aset kripto (Bappebti mengatur sampai akhir 2024, kewenangan berpindah ke OJK mulai Januari 2025 per UU P2SK); wajib dipakai untuk setiap materi legalitas, bursa berizin, dan pajak di Indonesia — tidak boleh diganti opini komunitas/influencer.
6. **BIS (Bank for International Settlements)** dan **CFA Institute Digital Assets curriculum** — perspektif institusional/bank sentral yang sengaja jadi penyeimbang terhadap bias pro-crypto sumber-sumber industri; dipakai terutama di Modul 9 & 11 (stablecoin, CBDC, makro).
7. **Trail of Bits, OpenZeppelin, dan Rekt.news** — riset keamanan smart contract & database post-mortem hack/rug-pull terkemuka, dipakai di Modul 6-8 untuk studi kasus keamanan nyata, bukan cerita anekdotal.
8. **Etherscan, mempool.space, DeFiLlama, CoinGecko** — sumber data on-chain/market real-time untuk Applied Practice (bukan otoritas kurikulum, tapi alat wajib untuk latihan analisis nyata).

Bias eksplisit yang harus disadari: mayoritas konten populer di industri crypto (media crypto-native, influencer, tim proyek) punya insentif komersial untuk hype — setiap klaim ekonomi/keamanan yang berpotensi dipengaruhi insentif ini WAJIB disilangkan minimal ke sumber 2, 3, atau 6 sebelum diajarkan sebagai fakta, konsisten dengan bar "Vetting a Source" di `standards/UNIVERSAL_STANDARDS.md`. Tidak pernah membangun satu lesson hanya dari satu sumber, terutama bukan dari satu video YouTube atau satu thread X/Twitter.

## Applied Practice Definition

**Crypto Practitioner Log** — setiap modul menghasilkan artifact nyata yang bisa direview, bukan cuma "sudah paham teorinya": setup wallet nyata dengan seed phrase backup yang benar, transaksi on-chain nyata (testnet atau mainnet dengan jumlah kecil yang sanggup hilang total), laporan analisis on-chain dari data real (Etherscan/mempool.space), laporan due diligence tertulis atas proyek crypto nyata, dan interaksi DeFi nyata di testnet (swap, provide liquidity, staking). Sesuai house rule keamanan di bawah, latihan yang melibatkan dana sungguhan baru dilakukan SETELAH Modul 3 (keamanan wallet) selesai, dan hanya dengan jumlah yang eksplisit "affordable to lose". Konsisten dengan Hands-on First di `ai-los/CORE_LOS.md`.

## Project Tiers

- **Mini Project** (per modul): satu artifact terfokus ke satu skill modul itu — mis. laporan analisis on-chain metrics Bitcoin, atau setup wallet + audit keamanan.
- **Intermediate Project** (per beberapa modul): gabungan skill dari beberapa modul — mis. laporan due diligence lengkap satu proyek crypto (tokenomics + tim + audit + on-chain metrics).
- **Capstone / Portfolio Project** (akhir course): **Personal Crypto Portfolio & Risk Management Plan** — dokumen lengkap berisi (1) alokasi portofolio berbasis due diligence framework Modul 7, (2) setup keamanan final (hardware wallet, rencana backup seed phrase, incident response plan), (3) checklist kepatuhan Indonesia (bursa berizin, pencatatan pajak), (4) minimal 2 laporan due diligence proyek nyata — disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap review Crypto Practitioner Log juga mengecek:

- **Security Rigor** — apakah private key/seed phrase dikelola benar (tidak ada single point of failure, tidak pernah difoto/diketik di tempat online), apakah ada kesadaran phishing/scam pattern sebelum interaksi dengan dApp/exchange baru.
- **Analytical Rigor / Source Quality** — apakah klaim didukung data on-chain/sumber primer (bukan sekadar kutip influencer/marketing proyek), konsisten dengan bar Vetting a Source di atas.
- **Risk Awareness** — apakah position sizing dan skenario downside (termasuk "aset bisa ke nol") dipertimbangkan secara eksplisit, bukan diasumsikan optimis.
- **Regulatory/Compliance Awareness** — apakah exchange/produk yang dipakai berizin Bappebti/OJK, dan apakah ada kesadaran kewajiban pajak (bukan wajib sudah lapor pajak, tapi wajib sudah sadar kewajibannya).

## Assessment Form

Kuis konsep protokol/mekanisme setelah tiap modul, latihan skenario "spot the scam/rug pull" (diberi deskripsi proyek fiktif, identifikasi red flags), tantangan forensik on-chain (diberi satu transaction hash nyata, jelaskan apa yang terjadi lewat block explorer), dan mock due-diligence defense (learner mempresentasikan investment thesis-nya, lalu ditantang/di-steelman-kan mengikuti pendekatan `domains/critical-thinking/DOMAIN.md`).

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk topik yang sama:

- **Institusi TradFi yang masuk crypto (BlackRock spot Bitcoin ETF, Fidelity Digital Assets)** vs **dana crypto-native (a16z crypto, Paradigm)** — postur risiko/regulasi yang berbeda untuk aset yang sama.
- **Filosofi Bitcoin maximalist** (sound money, self-custody, "not your keys not your coins") vs **filosofi platform smart contract Ethereum** ("world computer", programmability) — dua worldview first-principles yang berbeda tentang apa yang seharusnya jadi tujuan utama crypto.
- **Peneliti keamanan/akademik** (Trail of Bits, Cornell IC3, penulis Princeton textbook) vs **lensa trading/influencer retail** — rigor berbasis bukti vs hype berbasis engagement.
- **Regulator/bank sentral** (BIS, OJK) — framing risiko sistemik & perlindungan konsumen, dikontraskan dengan framing "desentralisasi/kebebasan finansial" ala crypto-native, supaya learner paham kedua sisi bukan cuma satu narasi.

## Domain-Specific Standards

`domains/cryptocurrency/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit (di atas `standards/UNIVERSAL_STANDARDS.md`):

- **Keamanan wallet WAJIB diajarkan sebelum dana sungguhan disentuh** — Modul 3 (private key, seed phrase, hardware wallet, ancaman phishing) harus tuntas sebelum learner diminta connect wallet ke exchange/dApp mana pun dengan dana nyata, mirip pola "ergonomi dulu sebelum volume latihan tinggi" di `domains/guitar/DOMAIN.md`.
- **Tidak pernah merekomendasikan pembelian aset spesifik** — mentor mengajarkan kerangka evaluasi (due diligence, manajemen risiko), keputusan beli/jual selalu ada di tangan learner sendiri, ditandai eksplisit tiap kali topik ini muncul.
- **Setiap proyek/protokol baru yang dipelajari wajib dicek lewat due diligence checklist minimum** (tim doxxed/pseudonim + status audit + data TVL/volume dari sumber independen + jadwal vesting tokenomics) sebelum ada diskusi "apakah ini layak dipakai" — tidak pernah diajarkan lewat hype/marketing semata.
- **Latihan dengan dana sungguhan dibatasi eksplisit** ke jumlah yang learner sendiri nyatakan "affordable to lose" — tidak pernah diarahkan ke leverage/margin sebagai latihan pemula.
- **Semua konten regulasi/pajak ditandai eksplisit "informasional, bukan nasihat hukum/pajak"**, dengan pointer ke profesional pajak/hukum berlisensi Indonesia untuk keputusan nyata — konsisten dengan batasan serupa di `domains/personal-finance/DOMAIN.md`.
- **Klaim ekonomi/keamanan yang rawan bias komersial** (mis. proyeksi harga, klaim "yield aman tinggi", klaim keamanan dari tim proyek sendiri) selalu disilangkan ke sumber 2, 3, atau 6 di atas sebelum diajarkan sebagai fakta.

## Domain Goal

Dari tidak paham apa itu blockchain/crypto sama sekali, menjadi mampu: mengevaluasi desain teknis & tokenomics sebuah proyek secara independen, mengelola keamanan self-custody dengan benar, mengeksekusi transaksi on-chain/interaksi DeFi dengan aman, memahami konteks makro & regulasi (termasuk kerangka Bappebti/OJK Indonesia), dan membuat keputusan berbasis manajemen risiko yang sadar — dibuktikan lewat Personal Crypto Portfolio & Risk Management Plan yang nyata, bukan sekadar teori atau ikut-ikutan hype.
