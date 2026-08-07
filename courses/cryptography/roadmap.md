# Course Roadmap

## Course Information

* Course Name: Kriptografi — dari 0 sampai Expert (Applied Cryptography untuk Backend Engineer)
* Category: Applied Cryptography / Secure Software Engineering
* Difficulty: Level 1 (belum pernah berpikir soal *bagaimana* crypto bekerja, hanya tahu "pakai AES" / "pakai HTTPS") → Level 4 (Expert, mampu memilih algoritma & mode yang tepat, mendesain key management, melakukan review kode kriptografi, dan menilai kesiapan migrasi Post-Quantum)
* Estimated Duration: 10 modul + capstone, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Familiar Java/Spring Boot dasar (dari repo ini). Tidak perlu latar belakang matematika lanjutan — konsep number theory dijelaskan secukupnya untuk intuisi, bukan pembuktian formal.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda (2026-08-07):

1. **Angle: Applied Engineering, bukan Academic/Rigorous.** Course ini mengajarkan cara kerja crypto secara benar dan cara memakainya dengan benar di sistem nyata (AES/RSA/ECC/hashing, Java JCA/JCE, TLS, key management, JWT/PKI, menghindari pitfall umum). Matematika (number theory, discrete log, lattice) dijelaskan secara intuitif untuk membangun pemahaman *why*, bukan security proof/reduction formal ala Boneh-Shoup. Ini paling nyambung ke goal repo ini: World-Class Backend Developer, Java/Spring Boot.
2. **Offensive/breaking crypto disertakan (cryptopals-style).** Di beberapa modul awal ada latihan membobol crypto yang lemah/salah implementasi (ECB mode, padding oracle, nonce reuse, weak RNG) — cara belajar yang dipakai praktisi crypto sungguhan untuk memahami *kenapa* aturan defensive itu ada, bukan sekadar menghafalnya.
3. **Post-Quantum Cryptography (PQC) dapat modul penuh (Modul 9).** NIST sudah memfinalkan FIPS 203/204/205 (ML-KEM, ML-DSA, SLH-DSA) pada Agustus 2024 dan sudah mulai dipakai di hybrid TLS production — relevan untuk level "expert" dan future-proofing skill.
4. **Membangun di atas `standards/SECURITY_STANDARDS.md`, bukan mengulanginya.** Standard itu hanya berisi aturan checklist singkat soal encryption (baris 283-291: "Encrypt sensitive data", "Prefer modern TLS versions"). Course ini adalah pemahaman *mendalam* di baliknya: bagaimana AES/RSA/ECC benar-benar bekerja, bagaimana memilih mode/algoritma yang tepat, dan bagaimana key management yang benar dirancang.
5. **Terhubung dengan course lain tanpa mengulang implementasinya**: Modul 8 merujuk `courses/aws-api-integration` untuk implementasi KMS/Secrets Manager, Modul 10 merujuk `courses/cybersecurity` Modul 3.2 (JWT pitfalls) dan Modul 5.3 (data sensitif) untuk sisi *application security* dari topik yang sama, serta `courses/blockchain` untuk primitif crypto yang dipakai di konteks blockchain (hash, digital signature, Merkle tree). Course ini memberi lensa *cryptography practitioner* yang mendasari semuanya — bukan tutorial ulang.

Referensi riset yang dipakai menyusun roadmap ini: [A Graduate Course in Applied Cryptography — Dan Boneh & Victor Shoup](https://toc.cryptobook.us/) (gratis, dipakai sebagai referensi konseptual meski course ini tidak mengikuti gaya proof-nya secara penuh), *Cryptography Engineering: Design Principles and Practical Applications* — Ferguson, Schneier, Kohno (basis pendekatan applied-engineering), [NIST FIPS 203 (ML-KEM)](https://csrc.nist.gov/pubs/fips/203/final), [FIPS 204 (ML-DSA)](https://csrc.nist.gov/pubs/fips/204/final), [FIPS 205 (SLH-DSA)](https://csrc.nist.gov/pubs/fips/205/final) — difinalkan Agustus 2024, [RFC 8446 (TLS 1.3)](https://www.rfc-editor.org/rfc/rfc8446), [OWASP Cryptographic Storage Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Cryptographic_Storage_Cheat_Sheet.html), [cryptopals.com](https://www.cryptopals.com/) (sumber latihan offensive/breaking crypto), Java Cryptography Architecture (JCA) Reference Guide (dokumentasi resmi Oracle), `standards/SECURITY_STANDARDS.md` di repo ini sebagai baseline aturan yang sudah berlaku.

---

# Learning Objectives

* [ ] Paham model ancaman kriptografi (Kerckhoffs's principle, threat model penyerang) dan kenapa "security through obscurity" gagal
* [ ] Menguasai symmetric encryption: cara kerja AES, mode operasi (ECB/CBC/CTR/GCM) dan kapan tiap mode aman/berbahaya, mampu mengeksploitasi ECB & padding oracle untuk membuktikan pemahaman
* [ ] Menguasai hash function & MAC: SHA-2/SHA-3, HMAC, length extension attack, password hashing yang benar (bcrypt/argon2) vs kesalahan umum
* [ ] Paham public-key cryptography: RSA, Diffie-Hellman, ECC/ECDH — cara kerja, kapan dipakai, pitfall umum (nonce reuse, small exponent, weak randomness)
* [ ] Mahir digital signature & PKI: RSA-PSS, ECDSA/EdDSA, X.509 certificate chain, trust model CA, menghindari kesalahan signing JWT/JWS
* [ ] Mampu implementasi crypto yang benar di Java (JCA/JCE): Cipher, KeyGenerator, MessageDigest, SecureRandom, KeyStore, kapan perlu Bouncy Castle
* [ ] Paham TLS 1.3 secara mendalam: handshake, perfect forward secrecy, mTLS, cara HTTPS membangun trust end-to-end
* [ ] Mampu mendesain key management yang benar: KDF, envelope encryption, integrasi KMS, key rotation
* [ ] Paham Post-Quantum Cryptography: ancaman quantum computing terhadap crypto klasik, ML-KEM/ML-DSA/SLH-DSA, strategi migrasi hybrid
* [ ] Mampu menganalisis kegagalan crypto dunia nyata (Sony PS3, Heartbleed, DUAL_EC_DRBG) dan melakukan code review kriptografi sistematis
* [ ] Menyelesaikan proyek portofolio: sistem penyimpanan/pengiriman data yang aman secara kriptografis, dari desain sampai code review

---

# Course Modules

## Module 1 — Fondasi & Model Ancaman Kriptografi

Description: Sebelum masuk ke algoritma, harus paham cara berpikir yang benar tentang keamanan kriptografi — kerangka ini dipakai di seluruh modul berikutnya.

Lessons:

* [ ] 1.1 Apa itu kriptografi: confidentiality, integrity, authenticity, non-repudiation — dan kenapa ke-4nya sering tertukar
* [ ] 1.2 Kerckhoffs's principle: kenapa keamanan tidak boleh bergantung pada kerahasiaan algoritma, hanya kunci
* [ ] 1.3 Cipher klasik (Caesar, Vigenère, XOR cipher) — dipecahkan sendiri untuk membangun intuisi kenapa cipher naif gagal
* [ ] 1.4 Symmetric vs asymmetric crypto, dan kapan repo/produksi memakai yang mana

Mini Project: Pecahkan cryptopals Set 1 (hex/base64 conversion, single-byte XOR cipher, frequency analysis) — membangun intuisi "crypto lemah bisa dipecahkan secara sistematis".

Status: Not Started

---

## Module 2 — Symmetric Encryption & Mode Operasi

Description: AES adalah cipher paling dipakai di dunia, tapi cipher yang benar bisa jadi tidak aman kalau mode operasinya salah — ini kesalahan implementasi paling umum.

Lessons:

* [ ] 2.1 Cara kerja AES secara konseptual (substitution-permutation network, key schedule) — cukup dalam untuk paham *properties*-nya, tidak perlu reimplement dari nol
* [ ] 2.2 Mode operasi: ECB (kenapa berbahaya — "ECB penguin"), CBC (IV, padding oracle), CTR (nonce reuse = fatal), GCM (AEAD, authenticated encryption)
* [ ] 2.3 Kapan pakai AEAD (GCM/ChaCha20-Poly1305) vs mode lama, dan kenapa "encrypt-then-MAC" > "MAC-then-encrypt"

Mini Project: Cryptopals Set 2-3 — deteksi ECB mode dari ciphertext, eksploitasi padding oracle attack untuk decrypt tanpa tahu key, forge nonce-reused CTR ciphertext.

Status: Not Started

---

## Module 3 — Hash Function, MAC & Password Storage

Description: Hash function dipakai di mana-mana tapi paling sering disalahgunakan — terutama untuk password.

Lessons:

* [ ] 3.1 Properti hash function yang aman (preimage/second-preimage/collision resistance), SHA-2 vs SHA-3, kenapa MD5/SHA-1 sudah broken
* [ ] 3.2 HMAC: kenapa `hash(key + message)` naif rentan length extension attack, dan bagaimana HMAC memperbaikinya
* [ ] 3.3 Password hashing yang benar: bcrypt/scrypt/argon2 vs kesalahan umum (plain SHA-256, tanpa salt, cost factor terlalu rendah)

Mini Project: Demonstrasikan length extension attack terhadap `SHA256(secret || message)` naif, lalu perbaiki dengan HMAC — buktikan attack tidak lagi berhasil.

Status: Not Started

---

## Module 4 — Public-Key Cryptography Fundamentals

Description: Ini yang memungkinkan dua pihak yang belum pernah bertemu untuk berkomunikasi aman — dasar dari HTTPS, SSH, dan hampir semua secure messaging.

Lessons:

* [ ] 4.1 RSA: cara kerja (modular exponentiation, kenapa faktorisasi sulit), textbook RSA yang naif dan kenapa harus pakai padding (OAEP)
* [ ] 4.2 Diffie-Hellman key exchange: bagaimana dua pihak sepakat kunci rahasia lewat kanal publik, discrete logarithm problem
* [ ] 4.3 Elliptic Curve Cryptography (ECC): kenapa ECC makin dominan (key lebih pendek, performa lebih baik), ECDH untuk key exchange
* [ ] 4.4 Hybrid encryption (envelope): kenapa data besar tidak pernah dienkripsi langsung dengan RSA/ECC — dienkripsi AES lalu AES key-nya yang dienkripsi asymmetric

Mini Project: Implementasi hybrid encryption sederhana (RSA/ECC untuk key wrapping + AES-GCM untuk payload) di Java, lalu identifikasi & perbaiki 1 kesalahan umum (mis. textbook RSA tanpa padding).

Status: Not Started

---

## Module 5 — Digital Signature & PKI

Description: Signature membuktikan siapa yang mengirim data dan bahwa data tidak diubah — fondasi trust di internet lewat sistem sertifikat.

Lessons:

* [ ] 5.1 Digital signature: RSA-PSS, ECDSA, EdDSA (Ed25519) — cara kerja dan perbedaannya
* [ ] 5.2 Kasus nyata ECDSA nonce reuse (Sony PlayStation 3 — private key ter-recover karena nonce statis) sebagai studi kasus kenapa randomness penting
* [ ] 5.3 X.509 certificate, certificate chain, root/intermediate CA, bagaimana browser memvalidasi trust
* [ ] 5.4 JWT/JWS signing yang benar (`alg` confusion, kapan pakai RS256 vs HS256) — terhubung ke `courses/cybersecurity` Modul 3.2 dari sisi *application* pitfall-nya

Mini Project: Reproduksi (secara konseptual/kalkulasi, bukan membobol sistem nyata) kelemahan ECDSA saat nonce dipakai ulang, lalu verifikasi certificate chain sungguhan (`openssl s_client`) untuk domain nyata.

Status: Not Started

---

## Module 6 — Applied Cryptography di Java (JCA/JCE)

Description: Semua teori sebelumnya sekarang dipraktikkan lewat API yang akan Anda pakai di production Spring Boot.

Lessons:

* [ ] 6.1 Java Cryptography Architecture (JCA): `Cipher`, `KeyGenerator`, `MessageDigest`, `Signature`, provider model
* [ ] 6.2 `SecureRandom` yang benar (kenapa `Random` biasa fatal untuk crypto), `KeyStore` untuk menyimpan key/cert
* [ ] 6.3 Kapan butuh Bouncy Castle (algoritma tidak tersedia di JDK default, mis. beberapa curve ECC atau PQC awal), integrasi dengan Spring Security crypto utilities (`PasswordEncoder`, dll)

Mini Project: Implementasi library kecil di Java: encrypt/decrypt file dengan AES-GCM (JCA murni, tanpa library eksternal), sign/verify dengan ECDSA, semuanya pakai `SecureRandom` dan key management yang benar.

Status: Not Started

---

## Module 7 — TLS & Protokol Aman dalam Praktik

Description: HTTPS adalah aplikasi kriptografi paling sering dipakai tapi paling jarang dipahami mendalam oleh backend engineer.

Lessons:

* [ ] 7.1 TLS 1.3 handshake step-by-step (RFC 8446): key exchange, cipher suite negotiation, kenapa TLS 1.3 lebih cepat & aman dari 1.2
* [ ] 7.2 Perfect Forward Secrecy: kenapa penting, bagaimana ephemeral key exchange mencapainya
* [ ] 7.3 Mutual TLS (mTLS): kapan dipakai (service-to-service auth), cara kerja certificate-based client authentication
* [ ] 7.4 Certificate pinning, kapan berguna dan kapan jadi anti-pattern (operational risk)

Mini Project: Setup mTLS antara dua service Spring Boot lokal (client cert + server cert self-signed), verifikasi handshake gagal saat client cert tidak valid.

Status: Not Started

---

## Module 8 — Key Management & Secrets di Skala Production

Description: Algoritma yang benar tidak berguna kalau key-nya bocor atau dikelola sembarangan — ini yang membedakan sistem production dari contoh tutorial.

Lessons:

* [ ] 8.1 Key Derivation Function (KDF): HKDF, PBKDF2 — menurunkan banyak key dari satu master secret dengan aman
* [ ] 8.2 Envelope encryption: data key vs key-encryption-key (KEK), pola yang dipakai semua cloud KMS
* [ ] 8.3 Integrasi KMS (AWS KMS/HashiCorp Vault) — rujuk `courses/aws-api-integration` untuk kedalaman implementasi IAM/kredensial
* [ ] 8.4 Key rotation, key revocation, dan kenapa "key yang tidak pernah di-rotate" adalah risiko tersembunyi

Mini Project: Desain (dan implementasi sebagian) skema envelope encryption untuk data sensitif di 1 service — data key per-record, KEK disimpan di KMS/Vault (atau simulasi lokal jika tidak ada akses cloud).

Status: Not Started

---

## Module 9 — Post-Quantum Cryptography (PQC)

Description: Quantum computer yang cukup besar akan mematahkan RSA/ECC — NIST sudah memfinalkan standar penggantinya, dan migrasi sudah mulai berjalan di production.

Lessons:

* [ ] 9.1 Kenapa quantum computing mengancam crypto klasik (Shor's algorithm memecahkan faktorisasi & discrete log — dijelaskan secara konseptual, bukan quantum computing mendalam), konsep "harvest now, decrypt later"
* [ ] 9.2 Standar NIST PQC yang final (Agustus 2024): ML-KEM (FIPS 203, key encapsulation), ML-DSA (FIPS 204, signature), SLH-DSA (FIPS 205, hash-based signature) — kapan pakai yang mana
* [ ] 9.3 Strategi migrasi: hybrid deployment (klasik + PQC berbarengan, seperti yang sudah dipakai di TLS production saat ini), timeline realistis untuk sistem backend biasa

Mini Project: Riset & tulis assessment singkat: sistem mana di proyek Anda (atau proyek contoh) yang paling berisiko dari "harvest now, decrypt later", dan rencana migrasi hybrid PQC untuk itu.

Status: Not Started

---

## Module 10 — Kegagalan Crypto Dunia Nyata & Menjadi Crypto-Minded Senior Engineer

Description: Level di mana kriptografi jadi bagian dari cara Anda mendesain & mereview sistem, bukan cuma "pakai library dan berharap aman".

Lessons:

* [ ] 10.1 Studi kasus kegagalan nyata: Heartbleed (buffer over-read di OpenSSL), DUAL_EC_DRBG (backdoored RNG), WEP (IV terlalu pendek), Adobe 2013 (ECB mode bocorkan pola password) — akar penyebab tiap kasus
* [ ] 10.2 Bagaimana Signal/WhatsApp (Signal Protocol, double ratchet), Stripe (idempotency + signing webhook), Google (BoringSSL, key rotation skala besar) mendesain sistem crypto production-grade
* [ ] 10.3 Checklist code review kriptografi sistematis (algoritma tepat? mode tepat? randomness aman? key management jelas? tidak roll-your-own-crypto?)
* [ ] 10.4 Angle karier: pertanyaan interview kriptografi yang umum, bagaimana skill ini memperkuat kredibilitas senior/staff backend engineer — terhubung ke `courses/cybersecurity` (application security) dan `courses/blockchain` (crypto primitives di konteks blockchain) sebagai lensa yang saling melengkapi

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Secure Data Vault & Messaging Service** — bangun 1 service Spring Boot kecil yang menyimpan & mengirim data sensitif dengan kriptografi yang benar end-to-end, lalu lakukan security/crypto review terhadapnya: (1) desain threat model ringkas khusus crypto (Modul 1), (2) enkripsi data dengan AES-GCM, mode & IV yang benar (Modul 2), (3) hash & simpan credential dengan argon2/bcrypt (Modul 3), (4) key exchange/hybrid encryption untuk data yang dikirim antar service (Modul 4), (5) signing & verifikasi integritas pesan dengan ECDSA/EdDSA (Modul 5), (6) semuanya diimplementasi murni dengan JCA/JCE, `SecureRandom` yang benar (Modul 6), (7) service-to-service pakai mTLS (Modul 7), (8) key management pakai envelope encryption + KMS/Vault (Modul 8), (9) tulis PQC readiness assessment untuk service ini (Modul 9), (10) lakukan code review kriptografi formal terhadap implementasi sendiri memakai checklist Modul 10 — temukan & perbaiki minimal 1 kesalahan yang sengaja/tidak sengaja disisipkan.

Acceptance Criteria:

* Tidak ada penggunaan ECB mode, textbook RSA tanpa padding, atau `Random`/`Math.random()` untuk keperluan crypto di manapun
* Password/credential di-hash dengan argon2/bcrypt, bukan hash biasa
* Ada mekanisme key management eksplisit (bukan hardcoded key di kode) — didokumentasikan cara rotasinya
* mTLS terbukti bekerja: koneksi tanpa client cert valid ditolak
* PQC readiness assessment mengidentifikasi minimal 1 aset data yang berisiko "harvest now, decrypt later" dengan rencana mitigasi konkret
* Laporan code review kriptografi mengikuti checklist Modul 10.3, menemukan & memperbaiki minimal 1 kesalahan nyata
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Apa itu kriptografi: confidentiality, integrity, authenticity, non-repudiation
