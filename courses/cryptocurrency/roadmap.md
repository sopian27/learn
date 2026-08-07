# Course Roadmap

## Course Information

* Course Name: Cryptocurrency — dari 0 sampai Expert (Teknologi, Ekonomi, Keamanan, dan Analisis)
* Category: Cryptocurrency (Domain: cryptocurrency — lihat `domains/cryptocurrency/DOMAIN.md`)
* Difficulty: Level 0 (belum paham blockchain/crypto sama sekali) → Level 5 (mampu evaluasi proyek, kelola keamanan self-custody, eksekusi on-chain/DeFi, dan paham konteks makro-regulasi setara analis independen)
* Estimated Duration: 12 modul, 4 lesson/modul (48 lesson). Pace realistis 3-5 sesi aktif per minggu (baca + praktik hands-on wallet/on-chain, bukan cuma teori) — sesuai Applied Practice Definition di `domains/cryptocurrency/DOMAIN.md`.
* Prerequisites: Tidak ada pengetahuan crypto sebelumnya. Dibutuhkan HP/laptop untuk setup wallet nyata (Modul 3 dst.) dan koneksi internet untuk praktik on-chain. Tidak butuh kemampuan coding — course ini SISI PEMAKAI/ANALIS, bukan pengembang (kalau ingin menulis smart contract, lihat `courses/blockchain/roadmap.md` sebagai course terpisah/pelengkap). Dana sungguhan baru dipakai setelah Modul 3 (keamanan wallet) tuntas, dan hanya jumlah kecil yang "affordable to lose" — house rule domain, lihat `domains/cryptocurrency/DOMAIN.md` bagian Domain-Specific Standards.

---

# Learning Objectives

* [ ] Menjelaskan masalah yang dipecahkan Bitcoin (double-spend, trust tanpa perantara) dan mekanisme dasarnya (UTXO, proof-of-work, halving) dari whitepaper asli, bukan versi media yang disederhanakan
* [ ] Mengelola keamanan self-custody dengan benar: seed phrase, hardware wallet, dan pencegahan phishing/scam — dibuktikan lewat setup wallet nyata
* [ ] Memilih & memakai exchange (CEX/DEX) secara aman, termasuk paham kerangka regulasi Indonesia (Bappebti/OJK) dan kewajiban pajak dasar
* [ ] Memahami Ethereum, smart contract, dan token standar (ERC-20/ERC-721) dari sudut pandang pemakai — mampu membaca kontrak nyata di block explorer
* [ ] Melakukan analisis on-chain (baca transaksi, wallet tracking, forensik dasar) dan menyusun due diligence framework untuk mengevaluasi proyek crypto secara independen
* [ ] Berinteraksi dengan DeFi (AMM, lending, staking) di testnet dan menjelaskan risikonya (impermanent loss, smart contract risk, oracle manipulation)
* [ ] Menerapkan manajemen risiko (position sizing, diversifikasi, psikologi pasar) dalam menyusun investment thesis tanpa bergantung pada hype/influencer
* [ ] Memahami konteks makro-institusional (ETF, adopsi institusi, CBDC) dan regulasi global vs Indonesia
* [ ] Menghasilkan Personal Crypto Portfolio & Risk Management Plan sebagai Capstone Project — bukti kemampuan menyeluruh

---

# Course Modules

## Module 1 — Fondasi: Sejarah Uang, Masalah Bitcoin, dan Mental Model Blockchain

Description: Titik awal mutlak — sebelum menyentuh wallet atau exchange, pahami dulu masalah apa yang sebenarnya dipecahkan crypto dan kenapa desainnya seperti itu, langsung dari sumber primer (whitepaper Nakamoto), bukan dari hype media.

Lessons:

* [ ] 1.1 Sejarah singkat uang & properti "sound money" (commodity money → fiat → masalah inflasi/sentralisasi yang jadi motivasi Bitcoin)
* [ ] 1.2 Masalah double-spend & solusi Bitcoin — walkthrough poin-poin kunci whitepaper Nakamoto (2008)
* [ ] 1.3 Anatomi blockchain secara konsep (block, hash, chain, consensus) — level pemakai, disilangkan ke `courses/blockchain/roadmap.md` Module 1 untuk yang mau bangun sendiri dari kode
* [ ] 1.4 Mendisambiguasi istilah: cryptocurrency vs blockchain vs Web3 — kesalahpahaman paling umum bagi pemula

Mini Project: Tabel perbandingan tertulis properti uang fiat vs Bitcoin (durability, portability, divisibility, scarcity, censorship-resistance) + ringkasan 1 paragraf menjelaskan masalah double-spend dengan bahasa sendiri.

Status: Not started

---

## Module 2 — Bitcoin Mendalam: Ekonomi & Mekanisme

Description: Masuk lebih dalam ke mekanisme dan kebijakan moneter Bitcoin — cukup dalam untuk paham trade-off desainnya secara analitis, tanpa perlu menulis kode mining sendiri.

Lessons:

* [ ] 2.1 UTXO model, mining, proof-of-work, difficulty adjustment — cara kerja & kenapa didesain begitu
* [ ] 2.2 Kebijakan moneter Bitcoin: batas 21 juta, siklus halving, stock-to-flow model (dan kritik akademik terhadapnya)
* [ ] 2.3 Metrik keamanan & desentralisasi jaringan (hash rate, jumlah node, konsentrasi mining pool) — dibaca dari dashboard nyata
* [ ] 2.4 Lightning Network & sejarah "block size wars" — debat skalabilitas Bitcoin

Mini Project: Analisis tertulis dari data real-time di mempool.space/dashboard on-chain gratis — interpretasikan hash rate, mempool size, dan fee saat ini, jelaskan apa artinya untuk kondisi jaringan.

Status: Not started

---

## Module 3 — Wallet, Private Key, dan Keamanan Self-Custody

Description: Modul paling krusial di seluruh course — house rule domain melarang menyentuh dana sungguhan sebelum modul ini tuntas. Fondasi keamanan yang menopang semua praktik hands-on setelahnya.

Lessons:

* [ ] 3.1 Public/private key, seed phrase (BIP-39), hierarchical deterministic wallet (BIP-32/44) — konsep dan kenapa seed phrase adalah satu-satunya sumber kebenaran
* [ ] 3.2 Hot wallet vs cold/hardware wallet, custodial vs non-custodial — trade-off keamanan vs kenyamanan
* [ ] 3.3 Praktik: setup wallet non-custodial nyata dengan backup seed phrase yang benar (offline, tidak difoto/diketik online), kirim transaksi pertama (testnet atau jumlah sangat kecil)
* [ ] 3.4 Ancaman keamanan umum: phishing, seed phrase scam, malware clipboard-swap, fake app/extension — checklist pencegahan konkret

Mini Project: Wallet Setup & Security Audit Log — dokumentasi setup wallet nyata (screenshot proses tanpa expose seed phrase) + checklist self-audit keamanan yang sudah dijalankan.

Status: Not started

---

## Module 4 — Exchange, Regulasi Indonesia, dan Kepatuhan Dasar

Description: Sekarang wallet aman, saatnya paham cara masuk-keluar dari fiat ke crypto dengan aman, termasuk kerangka hukum yang berlaku di Indonesia.

Lessons:

* [ ] 4.1 CEX vs DEX, order book, market/limit order, slippage
* [ ] 4.2 Risiko custodial — studi kasus keruntuhan FTX, kenapa "not your keys not your coins" bukan sekadar slogan
* [ ] 4.3 Regulasi crypto Indonesia: transisi kewenangan Bappebti → OJK (2025), bursa kripto berizin, kewajiban pajak dasar (PPh/PPN) — ditandai eksplisit "informasional, bukan nasihat hukum/pajak"
* [ ] 4.4 KYC/AML dan cara memilih exchange berizin yang aman

Mini Project: Perbandingan tertulis 2-3 exchange (minimal 1 CEX berizin Indonesia, 1 DEX) dari sisi keamanan, biaya, dan likuiditas.

Status: Not started

---

## Module 5 — Ethereum, Smart Contract, dan "World Computer"

Description: Transisi dari Bitcoin (uang programmable minimal) ke Ethereum (platform programmable penuh) — murni dari sudut pandang pemakai/pembaca, bukan penulis kode.

Lessons:

* [ ] 5.1 Kenapa Ethereum lahir, EVM, gas sebagai mekanisme anti-spam, account model vs UTXO
* [ ] 5.2 Smart contract secara konsep: apa yang bisa/tidak bisa dilakukan — disilangkan ke `courses/blockchain/roadmap.md` Module 8-9 untuk yang mau menulis Solidity sungguhan
* [ ] 5.3 Token standar dari sisi pengguna (ERC-20 fungible, ERC-721 NFT) — cara membaca kontrak nyata di Etherscan tanpa menulis kode
* [ ] 5.4 The Merge & Proof-of-Stake Ethereum, staking (solo vs pooled vs liquid staking)

Mini Project: Baca 1 smart contract nyata terverifikasi di Etherscan, tulis penjelasan fungsi-fungsi utamanya dan apa yang terjadi tiap kali dipanggil.

Status: Not started

---

## Module 6 — On-Chain Analysis: Membaca Blockchain Seperti Analis

Description: Skill inti seorang analis crypto — mengekstrak insight nyata dari data on-chain publik, bukan cuma percaya klaim orang lain.

Lessons:

* [ ] 6.1 Block explorer mendalam (Etherscan/mempool.space): membaca transaksi, gas, event log
* [ ] 6.2 Wallet tracking & whale-watching tools (Nansen, Arkham, Dune) — use case dan keterbatasannya
* [ ] 6.3 Metrik on-chain untuk evaluasi: active addresses, TVL, exchange netflow, realized cap
* [ ] 6.4 Forensik dasar: mengikuti jejak dana lewat beberapa hop transaksi menggunakan studi kasus hack/scam nyata

Mini Project: Forensik On-Chain — diberi satu transaction hash dari kasus hack/scam nyata yang terdokumentasi publik (mis. dari Rekt.news), telusuri alur dananya via block explorer dan tulis laporan singkat.

Status: Not started

---

## Module 7 — Tokenomics & Due Diligence Framework

Description: Modul yang membedakan investor yang menganalisis dari yang sekadar ikut hype — kerangka due diligence yang dipakai berulang di modul-modul setelahnya.

Lessons:

* [ ] 7.1 Anatomi tokenomics: supply/emission, vesting schedule, distribusi awal (fair launch vs VC-backed)
* [ ] 7.2 Utility token vs security token vs governance token — implikasi regulasi & cara token menangkap value
* [ ] 7.3 Framework due diligence institusional: tim, teknologi, tokenomics, komunitas, status audit
* [ ] 7.4 Red flags rug pull & pola scam umum (pump-and-dump, tim anonim + tanpa audit + janji yield tidak realistis)

Intermediate Project: Laporan due diligence lengkap untuk satu proyek crypto nyata, memakai framework 7.3 dan checklist red flags 7.4 secara eksplisit.

Status: Not started

---

## Module 8 — DeFi: AMM, Lending, dan Yield

Description: Masuk ke "money legos" DeFi — dipraktikkan langsung di testnet supaya risikonya dipahami lewat pengalaman, bukan cuma teori.

Lessons:

* [ ] 8.1 Automated Market Maker (AMM): constant product formula, liquidity pool, impermanent loss
* [ ] 8.2 Protokol lending/borrowing (model Aave/Compound): collateralization, liquidation, interest rate model
* [ ] 8.3 Yield farming & staking — cara membedakan yield yang sustainable dari yang Ponzi-esque
* [ ] 8.4 Risiko DeFi: smart contract risk, oracle manipulation, flash loan attack (konsep + studi kasus nyata)

Mini Project: Praktik swap & provide liquidity di DEX testnet, hitung dan jelaskan potensi impermanent loss dari posisi yang diambil.

Status: Not started

---

## Module 9 — Stablecoin, NFT, dan Aset Digital Lain

Description: Melengkapi lanskap aset crypto di luar Bitcoin/Ethereum murni — dengan penekanan pada mekanisme dan risiko kegagalan, bukan hype.

Lessons:

* [ ] 9.1 Stablecoin: model fiat-collateralized vs crypto-collateralized vs algorithmic — studi kasus kegagalan Terra/UST
* [ ] 9.2 NFT: use case nyata di luar spekulasi (ticketing, identitas digital, gaming, tokenisasi real-world asset)
* [ ] 9.3 Real World Asset (RWA) tokenization & CBDC — tren institusional terbaru
* [ ] 9.4 Lanskap multi-chain: Layer 2 (Arbitrum/Optimism/Base) vs alt-L1 (Solana dll) — kapan pakai yang mana

Mini Project: Analisis tertulis satu stablecoin (mekanisme & risiko depeg-nya) + satu use case NFT non-spekulatif.

Status: Not started

---

## Module 10 — Trading, Manajemen Risiko, dan Psikologi Pasar

Description: Dari "paham teknologi" ke "bisa mengambil keputusan yang disiplin" — inti dari perbedaan investor dan penjudi di pasar yang sangat volatile ini.

Lessons:

* [ ] 10.1 Analisis fundamental vs teknikal — kapan masing-masing relevan untuk aset volatile
* [ ] 10.2 Position sizing, alokasi portofolio, dollar-cost averaging vs lump sum
* [ ] 10.3 Psikologi pasar: FOMO, herd behavior, siklus hype-crash — disilangkan ke bias kognitif di `domains/critical-thinking/DOMAIN.md`
* [ ] 10.4 Manajemen risiko praktis: stop-loss, diversifikasi, "never invest more than you can afford to lose" sebagai prinsip operasional, bukan slogan

Mini Project: Investment thesis tertulis untuk satu aset (murni penalaran learner sendiri, mentor tidak memberi rekomendasi beli/jual sesuai house rule domain) + rencana manajemen risiko eksplisit.

Status: Not started

---

## Module 11 — Makro, Institusi, dan Masa Depan Crypto

Description: Menempatkan crypto dalam konteks lebih besar — bagaimana kebijakan makro, institusi TradFi, dan regulator global membentuk arah industri ini.

Lessons:

* [ ] 11.1 Korelasi crypto dengan makro (suku bunga, dolar AS, sentimen risk-on/risk-off)
* [ ] 11.2 Adopsi institusional: spot Bitcoin/Ethereum ETF, treasury korporat, custody institusional
* [ ] 11.3 Regulasi global (SEC AS, MiCA Eropa) dibandingkan kerangka Indonesia (Bappebti/OJK) — tren konvergensi
* [ ] 11.4 CBDC & masa depan uang digital — perspektif bank sentral (BIS) vs perspektif crypto-native

Mini Project: Ringkasan tertulis satu halaman tentang dampak satu kebijakan makro/regulasi nyata terhadap harga atau adopsi crypto, didukung data.

Status: Not started

---

## Module 12 — Capstone: Personal Crypto Portfolio & Risk Management Plan

Description: Mengintegrasikan seluruh Modul 1-11 jadi satu dokumen portofolio nyata yang menunjukkan kemampuan menyeluruh, sesuai `domains/cryptocurrency/DOMAIN.md` bagian "Project Tiers".

Lessons:

* [ ] 12.1 Menyusun alokasi portofolio berbasis due diligence framework Modul 7
* [ ] 12.2 Finalisasi setup keamanan (hardware wallet, rencana backup seed phrase, incident response plan untuk skenario hack/phishing/lost keys)
* [ ] 12.3 Menyusun checklist kepatuhan Indonesia (exchange berizin, kesadaran pencatatan pajak)
* [ ] 12.4 Finalisasi & presentasi portofolio lengkap, disimpan ke `portofolio/`

Capstone Project: Personal Crypto Portfolio & Risk Management Plan — dokumen berisi (1) alokasi portofolio dengan rationale due diligence, (2) setup keamanan final, (3) checklist kepatuhan Indonesia, (4) minimal 2 laporan due diligence proyek nyata dari Modul 7. Review memakai `domains/cryptocurrency/DOMAIN.md` bagian "Review Style" (Security Rigor, Analytical Rigor/Source Quality, Risk Awareness, Regulatory/Compliance Awareness) di atas Universal Review Rubric (`ai-los/CORE_LOS.md`).

Status: Not started

---

# Capstone Project

Description: Lihat Module 12 di atas untuk rincian lengkap. Capstone course ini SATU dengan Capstone Project Module 12 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/cryptocurrency/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi keempat kriteria domain (Security Rigor, Analytical Rigor/Source Quality, Risk Awareness, Regulatory/Compliance Awareness) plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, dan portofolio menunjukkan learner mampu bernalar independen (bukan menyalin rekomendasi orang lain) sesuai `domains/cryptocurrency/DOMAIN.md` bagian Domain Goal.

Status: Not started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: 1.1 Sejarah singkat uang & properti "sound money"
