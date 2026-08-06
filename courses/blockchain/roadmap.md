# Course Roadmap

## Course Information

* Course Name: Blockchain — dari 0 sampai Expert (Fundamentals-First, lalu Solidity/Ethereum)
* Category: Blockchain Engineering / Web3 (pelengkap dari backend Java/Spring Boot)
* Difficulty: Level 0 (belum pernah blockchain sama sekali) → Level 4 (paham mendalam + mampu bangun & audit dApp production)
* Estimated Duration: 13 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Java (OOP, collections) sudah dikuasai — dipakai untuk membangun blockchain dari nol di Module 2-6. Tidak ada prasyarat blockchain/kriptografi — semua diajarkan dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban Anda:

1. **Tujuan**: Anda memilih **paham konsep + proyek portofolio**, bukan spesifik kejar lowongan Web3. Karena itu roadmap ini tetap menuntaskan Solidity + keamanan smart contract (supaya proyek portofolio Anda kredibel), tapi tidak ada modul khusus "interview prep Web3" atau spesialisasi DeFi/audit profesional yang biasanya ada di track karier murni.
2. **Pendekatan fondasi**: Anda memilih **bangun blockchain toy dari nol pakai Java dulu**, baru masuk Solidity/Ethereum. Karena itu Module 2-6 murni Java — tidak ada Solidity sama sekali — fokus membangun pemahaman *mengapa* blockchain bekerja (hashing, chaining, consensus, P2P, transaction model) dengan tangan Anda sendiri, sebelum memakai tool/framework Web3 yang menyembunyikan detail ini. Ini sama pola-nya dengan course React Anda: TypeScript dari nol sebelum framework.
3. **Ekosistem target**: Anda memilih **Ethereum/EVM (Solidity)**. Karena itu Module 8 ke atas fokus penuh ke EVM & Solidity, tanpa modul perbandingan multi-chain (Solana, Move, dll) — kalau nanti Anda mau eksplorasi chain lain, itu jadi course terpisah setelah course ini selesai.
4. **Bitcoin vs Ethereum**: Bitcoin tetap dapat 1 modul (Module 7) sebagai studi kasus UTXO model & motivasi historis, karena banyak konsep Ethereum (termasuk yang Anda bangun sendiri di Module 2-6) hanya masuk akal kalau dibandingkan dengan desain Bitcoin. Tapi ini bukan modul praktik Bitcoin scripting — cukup dalam untuk paham trade-off desainnya.

Referensi riset yang dipakai menyusun roadmap ini: [roadmap.sh/blockchain](https://roadmap.sh/blockchain) (struktur umum jalur blockchain developer: fundamentals → cryptography → consensus → Solidity/EVM → tooling → security), [ethereum.org/developers](https://ethereum.org/developers/) (dokumentasi resmi konsep Ethereum, akun, gas, EVM), dan [docs.soliditylang.org](https://docs.soliditylang.org/) (dokumentasi resmi bahasa Solidity).

---

# Learning Objectives

* [ ] Memahami mental model blockchain (ledger terdistribusi, immutability lewat hash chaining) bukan cuma menghafal istilah buzzword
* [ ] Mahir dasar kriptografi yang relevan: hash function (SHA-256), Merkle tree, digital signature (ECDSA), public/private key
* [ ] Mampu membangun blockchain sederhana dari nol pakai Java: block, chain, proof-of-work mining, validasi tamper-detection
* [ ] Memahami mekanisme consensus (PoW, PoS, BFT) dan trade-off-nya, termasuk masalah seperti 51% attack dan fork resolution
* [ ] Memahami cara node blockchain saling sinkron lewat jaringan P2P (dibangun sendiri versi sederhananya)
* [ ] Memahami dua model transaksi utama — UTXO (Bitcoin) vs Account-based (Ethereum) — dan kenapa desainnya berbeda
* [ ] Memahami arsitektur Ethereum & EVM: account, gas, nonce, transaction lifecycle
* [ ] Mahir Solidity dari nol sampai menulis smart contract production-grade (ERC-20, ERC-721) dengan OpenZeppelin
* [ ] Memahami kerentanan keamanan smart contract paling umum (reentrancy, integer overflow, access control) dan cara mencegahnya
* [ ] Mampu membangun, menguji (Hardhat/Foundry), dan men-deploy dApp ke testnet, lengkap dengan koneksi wallet (MetaMask)
* [ ] Menyelesaikan proyek portofolio: dApp full-stack yang terverifikasi di block explorer, siap ditunjukkan sebagai bukti kemampuan

---

# Course Modules

## Module 1 — Blockchain Fundamentals & Mental Model

Description: Titik awal mutlak dari nol. Sebelum menyentuh kode, Anda harus paham masalah apa yang diselesaikan blockchain dan kenapa desainnya seperti itu — supaya modul-modul praktik sesudahnya punya konteks yang jelas.

Lessons:

* [ ] Apa itu blockchain sebenarnya: distributed ledger, kenapa "terdesentralisasi" itu penting (dan kapan tidak penting)
* [ ] Trust tanpa perantara: masalah double-spending dan bagaimana blockchain menyelesaikannya secara konseptual
* [ ] Anatomi block & chain secara high-level (header, hash, previous hash, transaksi) — belum coding, cukup diagram
* [ ] Blockchain publik vs privat/permissioned, dan use case di luar cryptocurrency (supply chain, identity, dll) — sekilas, supaya tidak salah kira blockchain = crypto saja

Mini Project: Diagram tangan (atau tool diagram) yang menjelaskan alur "Alice kirim 5 koin ke Bob" dari mulai transaksi dibuat sampai masuk block — tanpa kode, murni untuk validasi pemahaman konsep.

Status: Not Started

---

## Module 2 — Kriptografi Dasar untuk Blockchain

Description: Tiga primitif kriptografi yang jadi fondasi segala sesuatu di blockchain: hash function, Merkle tree, digital signature. Semua dipraktikkan langsung di Java.

Lessons:

* [ ] Hash function (SHA-256): properti kunci (deterministic, avalanche effect, one-way, collision resistance) — praktik hashing di Java (`MessageDigest`)
* [ ] Merkle tree: kenapa dipakai untuk merangkum banyak transaksi jadi satu hash, cara build & verify di Java
* [ ] Public-key cryptography: asymmetric encryption, digital signature (ECDSA), cara kerja sign & verify — praktik pakai Java `KeyPairGenerator`/`Signature`
* [ ] Menghubungkan ketiganya: bagaimana hash + Merkle tree + signature membentuk fondasi integrity & authenticity di blockchain

Mini Project: Class Java `CryptoUtils` yang bisa hashing, generate key pair, sign pesan, dan verify signature — akan dipakai ulang di Module 3-6.

Status: Not Started

---

## Module 3 — Bangun Blockchain Sederhana dari Nol (Java)

Description: Inti dari pendekatan fundamentals-first yang Anda pilih. Anda menulis sendiri struktur block dan chain, lalu merasakan langsung kenapa mengubah 1 transaksi lama bisa merusak seluruh chain.

Lessons:

* [ ] Struktur `Block`: index, timestamp, data, previousHash, hash — implementasi Java memakai `CryptoUtils` dari Module 2
* [ ] Membentuk `Blockchain` (list of blocks) & method `addBlock`
* [ ] Validasi integrity: method `isChainValid()` yang mendeteksi tampering
* [ ] Kenapa hash saja tidak cukup untuk mencegah spam block — pengantar ke proof-of-work (jembatan ke Module 4)

Mini Project: Blockchain Java berjalan di memory (belum P2P, belum consensus sungguhan) yang bisa menambah block, lalu didemokan: ubah data 1 block lama secara sengaja, tunjukkan `isChainValid()` mendeteksinya.

Status: Not Started

---

## Module 4 — Consensus Mechanism

Description: Bagaimana banyak node yang tidak saling percaya bisa sepakat pada satu versi chain yang sama. Proof-of-work diimplementasikan langsung; PoS dan BFT dipelajari secara konseptual dan dibandingkan.

Lessons:

* [ ] Proof-of-Work mendalam: mining, difficulty target, nonce — implementasi mining sederhana di `Block` dari Module 3
* [ ] Proof-of-Stake: cara kerja, kenapa Ethereum bermigrasi dari PoW ke PoS (The Merge), trade-off energi vs keamanan
* [ ] Byzantine Fault Tolerance & consensus lain sekilas (PBFT, Nakamoto consensus) — cukup untuk paham istilah yang sering muncul
* [ ] Serangan & masalah consensus: 51% attack, fork (soft fork vs hard fork), longest chain rule

Mini Project: Tambahkan proof-of-work mining ke blockchain Module 3 (dengan difficulty yang bisa diatur), lalu ukur & catat waktu mining di beberapa level difficulty berbeda.

Status: Not Started

---

## Module 5 — Jaringan P2P Sederhana

Description: Blockchain nyata bukan satu program yang berjalan sendirian — ini jaringan node yang saling sinkron. Modul ini membangun versi sangat sederhana dari itu, cukup untuk memahami mekanismenya.

Lessons:

* [ ] Konsep jaringan P2P vs client-server, kenapa blockchain butuh P2P
* [ ] Membangun komunikasi antar-node sederhana di Java (socket atau REST endpoint minimal) untuk broadcast block baru
* [ ] Resolusi konflik antar-node: longest chain rule dalam praktik — node menerima chain mana ketika ada dua versi berbeda
* [ ] Keterbatasan implementasi sendiri vs jaringan produksi (network partition, latency, Sybil attack) — didiskusikan, tidak perlu diimplementasikan penuh

Mini Project: Jalankan 2-3 instance node Java blockchain Anda secara lokal (port berbeda), broadcast block baru dari satu node, dan tunjukkan node lain ikut sinkron dan menyelesaikan konflik chain.

Status: Not Started

---

## Module 6 — Wallet, Transaksi & Model UTXO vs Account

Description: Sejauh ini "data" di block Anda masih sembarang string. Modul ini mengganti itu dengan transaksi sungguhan yang ditandatangani, dan mengenalkan dua model transaksi utama di industri.

Lessons:

* [ ] Struktur transaksi: sender, receiver, amount, signature — menandatangani transaksi dengan `CryptoUtils` dari Module 2
* [ ] Wallet sederhana: key pair sebagai identitas, address dari public key
* [ ] UTXO model (Bitcoin): cara kerja, kenapa didesain begitu, kelebihan/kekurangannya
* [ ] Account-based model (Ethereum): cara kerja, beda dengan UTXO, kenapa Ethereum memilih ini (relevan untuk smart contract di Module 8+)

Mini Project: Tambahkan transaksi bertanda tangan (bukan string sembarang) ke blockchain Java Anda, dengan validasi signature sebelum transaksi diterima ke block — proyek Java Anda sekarang punya wallet, transaksi, mining, dan P2P sinkron: blockchain toy yang lengkap secara konsep.

Status: Not Started

---

## Module 7 — Studi Kasus: Bitcoin

Description: Modul singkat untuk menempatkan apa yang sudah Anda bangun ke dalam konteks blockchain nyata pertama di dunia, sebelum beralih ke Ethereum yang jadi fokus utama course ini.

Lessons:

* [ ] Bitcoin whitepaper (Satoshi Nakamoto) — walkthrough poin-poin kunci, dihubungkan ke apa yang sudah dibangun di Module 3-6
* [ ] Bitcoin scripting overview (Script bukan Turing-complete) — kenapa ini jadi motivasi Ethereum menambahkan smart contract
* [ ] Mempool, transaction fee, block reward & halving — konsep ekonomi dasar sebuah blockchain

Mini Project: Tidak ada coding baru — tulis ringkasan 1 halaman (di `progress/notes.md`) yang memetakan setiap komponen blockchain toy Anda (Module 3-6) ke komponen setara di Bitcoin sungguhan.

Status: Not Started

---

## Module 8 — Ethereum & EVM Fundamentals

Description: Transisi dari "blockchain yang Anda bangun sendiri" ke Ethereum sungguhan. Modul ini murni konsep & eksplorasi tooling, belum menulis Solidity.

Lessons:

* [ ] Kenapa Ethereum lahir: keterbatasan Bitcoin scripting, visi "world computer"
* [ ] Ethereum Virtual Machine (EVM): apa itu, bytecode, gas sebagai mekanisme anti-spam/DoS
* [ ] Account types (EOA vs Contract Account), nonce, transaction lifecycle di Ethereum
* [ ] Ethereum network: mainnet, testnet (Sepolia), local network (Hardhat/Anvil) — setup wallet (MetaMask) & dapatkan testnet ETH dari faucet

Mini Project: Setup MetaMask, hubungkan ke testnet Sepolia, kirim 1 transaksi testnet, dan telusuri transaksinya di block explorer (Etherscan) — memetakan field yang terlihat di explorer ke konsep dari modul ini.

Status: Not Started

---

## Module 9 — Solidity Fundamentals (dari 0)

Description: Bahasa pemrograman smart contract pertama Anda, dimulai dari nol. Anda sudah punya fondasi kuat dari Module 1-8, jadi modul ini bisa fokus penuh ke syntax & mental model kontrak.

Lessons:

* [ ] Setup Remix IDE, struktur file `.sol`, compiler & pragma version
* [ ] Tipe data, state variable vs local variable, visibility (`public`, `private`, `internal`, `external`)
* [ ] Function: modifier, `view`/`pure`, parameter, return value
* [ ] Constructor, `msg.sender`, `msg.value`, cara kontrak menerima & mengirim Ether
* [ ] Deploy kontrak pertama ke local network (Hardhat/Anvil) dari Remix atau CLI

Mini Project: Kontrak `SimpleStorage` atau `SimpleBank` (deposit/withdraw Ether) — deploy ke local network, interaksi lewat Remix.

Status: Not Started

---

## Module 10 — Solidity Intermediate & Standar Token

Description: Struktur data & pola yang dipakai di hampir semua smart contract production, lalu mengimplementasikan standar token paling populer.

Lessons:

* [ ] Struct, array, mapping — kombinasi umum untuk merepresentasikan data on-chain
* [ ] Events & logging, custom error (`revert`, `require`, `assert` — bedanya & kapan pakai yang mana)
* [ ] Inheritance & interface di Solidity, `abstract contract`
* [ ] Standar ERC-20 (fungible token): baca spesifikasinya, implementasikan dari nol (tanpa library dulu, supaya paham detailnya)
* [ ] OpenZeppelin: kenapa industri pakai library ini, migrasi implementasi ERC-20 Anda ke versi OpenZeppelin

Mini Project: Deploy token ERC-20 Anda sendiri (nama & simbol custom) ke testnet Sepolia, verifikasi source code di Etherscan.

Status: Not Started

---

## Module 11 — Solidity Advanced & Keamanan Smart Contract

Description: Yang membedakan "bisa menulis Solidity" dengan "bisa dipercaya menulis Solidity". Modul ini krusial supaya proyek portofolio Anda kredibel, bukan cuma jalan di happy path.

Lessons:

* [ ] Reentrancy attack: cara kerja (studi kasus The DAO hack), pola Checks-Effects-Interactions, `ReentrancyGuard`
* [ ] Integer overflow/underflow (konteks Solidity 0.8+ built-in check), access control yang salah (`onlyOwner`, role-based access dengan OpenZeppelin `AccessControl`)
* [ ] Gas optimization: storage vs memory vs calldata, packing struct, kesalahan umum yang boros gas
* [ ] Standar ERC-721 (NFT): spesifikasi, implementasi pakai OpenZeppelin
* [ ] Upgradeability overview (proxy pattern) — konsep & risikonya, tidak perlu diimplementasikan penuh

Mini Project: Audit checklist sendiri — ambil 1 kontrak yang sengaja punya bug (reentrancy atau access control), temukan & perbaiki bug-nya, dokumentasikan prosesnya.

Status: Not Started

---

## Module 12 — Tooling & Pengembangan dApp

Description: Alur kerja profesional untuk menulis, menguji, dan men-deploy smart contract — bukan cuma klik-klik di Remix.

Lessons:

* [ ] Hardhat atau Foundry: setup project, struktur folder, compile & deploy script
* [ ] Testing smart contract: unit test kontrak (JavaScript/TypeScript dengan Hardhat, atau Solidity test dengan Foundry)
* [ ] `ethers.js`: berinteraksi dengan kontrak dari kode (read/write, listen ke events)
* [ ] Menghubungkan dApp ke frontend: koneksi wallet (MetaMask) via `ethers.js`, kirim transaksi dari UI sederhana

Mini Project: Bungkus kontrak ERC-20/ERC-721 dari Module 10-11 dengan test suite (Hardhat/Foundry) yang hijau, plus halaman web sederhana yang bisa connect wallet dan memanggil fungsi kontrak.

Status: Not Started

---

## Module 13 — Portofolio & Menjadi Expert

Description: Modul penutup — menyatukan semua yang sudah dipelajari jadi 1 proyek portofolio yang bisa ditunjukkan, plus peta jalan supaya pemahaman Anda terus update setelah course ini selesai.

Lessons:

* [ ] Checklist deploy production-grade ke testnet: verifikasi Etherscan, dokumentasi kontrak (NatSpec comments)
* [ ] Overview ekosistem lanjutan: Layer 2 (Arbitrum, Optimism, Base) — kenapa ada, bagaimana bedanya dengan L1
* [ ] Overview DeFi & pola umum (DEX, lending, staking) sebagai wawasan lanjutan — konsep saja, tidak diimplementasikan
* [ ] Cara tetap up-to-date: sumber bacaan (ethereum.org, Solidity changelog), audit report sebagai bahan belajar (contoh kasus hack terkenal)

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **dApp Portofolio End-to-End** — proyek yang menggabungkan seluruh course: (1) smart contract ERC-20 atau ERC-721 yang ditulis sendiri dengan pola aman (Checks-Effects-Interactions, access control OpenZeppelin), (2) test suite lengkap (Hardhat/Foundry), (3) deploy ke testnet Sepolia dengan source code terverifikasi di Etherscan, (4) frontend sederhana yang connect wallet (MetaMask) dan berinteraksi dengan kontrak via `ethers.js`, (5) dokumentasi yang menjelaskan arsitektur & keputusan desain (termasuk kaitan konsepnya ke blockchain toy Java dari Module 3-6, sebagai bukti Anda paham fondasinya, bukan cuma copy tutorial).

Acceptance Criteria:

* Smart contract di-deploy ke Sepolia testnet dan terverifikasi di Etherscan
* Test suite berjalan hijau, mencakup minimal 1 test yang membuktikan kontrak tahan terhadap reentrancy atau access-control bypass
* Frontend berhasil connect wallet dan melakukan minimal 1 write transaction (misal mint token/NFT) yang terlihat di block explorer
* README proyek menjelaskan alur end-to-end dan menghubungkan balik ke konsep fundamental (hashing, consensus, transaction model) yang dipelajari di Module 1-7
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/13

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Apa itu blockchain sebenarnya (distributed ledger & kenapa desentralisasi penting)
