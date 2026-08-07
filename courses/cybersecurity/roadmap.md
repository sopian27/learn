# Course Roadmap

## Course Information

* Course Name: Cybersecurity — dari 0 sampai Expert (Application Security / Backend Engineer Track)
* Category: Application Security / Secure Software Engineering
* Difficulty: Level 1 (paham `standards/SECURITY_STANDARDS.md` sebagai aturan kode, belum pernah berpikir seperti security practitioner) → Level 4 (Expert, mampu menjalankan security assessment penuh & memimpin keputusan security architecture)
* Estimated Duration: 10 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Familiar Java/Spring Boot & AWS di level dasar (dari repo ini). Tidak perlu pengalaman security sebelumnya di luar mengikuti `standards/SECURITY_STANDARDS.md` saat coding.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda (2026-08-06):

1. **Angle: AppSec, backend-engineer focused** — bukan track generalist ala Security+ (network/OS/crypto luas) maupun offensive security/pentesting penuh (OSCP-style). Course ini memperkuat kredibilitas freelance backend Anda langsung: OWASP Top 10, desain API/auth yang aman, keamanan dependency/supply chain, code review keamanan, keamanan cloud/IAM. Mindset penyerang (attacker mindset) tetap diajarkan secukupnya untuk *bertahan* dengan benar (threat modeling, memahami pola eksploitasi) — bukan untuk menjalankan pentest penuh.
2. **Tidak ada target sertifikasi spesifik** — konten didorong oleh kegunaan nyata untuk kerja backend production, bukan objektif ujian.
3. **Membangun di atas `standards/SECURITY_STANDARDS.md`, bukan mengulanginya.** Standard itu adalah aturan kode yang sudah berlaku otomatis di semua proyek repo ini (prinsip CIA, least privilege, secure-by-default, dll). Course ini adalah pemahaman *mendalam* di baliknya: kenapa aturan itu ada, bagaimana serangan sebenarnya bekerja, dan bagaimana melakukan assessment/review keamanan secara sistematis — bukan hanya mengikuti checklist.
4. **Terhubung dengan course lain tanpa mengulang implementasinya**: Modul 6 merujuk `courses/ci-cd` Modul 5 (secret/dependency scanning di pipeline) untuk kedalaman implementasi CI, Modul 7 merujuk `courses/aws-api-integration` Modul 2 (IAM/kredensial) dan `courses/kubernetes` Modul 6 (SCC/RBAC/NetworkPolicy) untuk kedalaman implementasi platform. Course ini memberi lensa *AppSec practitioner* yang menyatukan semuanya — bukan tutorial ulang tool-nya.

Referensi riset yang dipakai menyusun roadmap ini: [OWASP Top 10:2025](https://owasp.org/www-project-top-ten/) (termasuk kategori baru Software Supply Chain Failures & Mishandling of Exceptional Conditions), [OWASP API Security Top 10](https://owasp.org/API-Security/) (BOLA, broken authentication, broken object property-level authorization), [OWASP Application Security Verification Standard (ASVS)](https://owasp.org/www-project-application-security-verification-standard/) (dipakai sebagai kerangka checklist Modul 8), [SLSA Framework](https://slsa.dev/spec/v1.0/about) (Google — supply chain integrity, dipakai sebagai kerangka Modul 6), `standards/SECURITY_STANDARDS.md` di repo ini sebagai fondasi prinsip yang sudah berlaku.

---

# Learning Objectives

* [ ] Mampu melakukan threat modeling (STRIDE) untuk sistem nyata dan mengidentifikasi trust boundary yang berisiko
* [ ] Menguasai OWASP Top 10:2025 secara mendalam — bisa mengenali, mengeksploitasi (untuk verifikasi), dan memperbaiki tiap kategori di konteks Spring Boot
* [ ] Mahir mengamankan authentication & session management (password storage, MFA, JWT/OAuth2 pitfalls) dan mempertahankan dari account takeover
* [ ] Mampu mendiagnosis dan memperbaiki broken access control (IDOR/BOLA, privilege escalation) termasuk OWASP API Security Top 10
* [ ] Mahir mencegah kelas serangan injection (SQLi, command injection, SSRF, deserialization) dan menangani data sensitif dengan benar
* [ ] Menguasai keamanan dependency & supply chain: SBOM, dependency scanning, prinsip SLSA
* [ ] Paham keamanan cloud/infrastruktur cukup dalam untuk melakukan review (IAM, network exposure, container/K8s security)
* [ ] Mampu melakukan security code review sistematis memakai OWASP ASVS dan menulis test case keamanan
* [ ] Paham dasar incident response & security operations: deteksi, respons, postmortem, disclosure
* [ ] Menyelesaikan proyek portofolio: security assessment & hardening penuh untuk 1 sistem nyata

---

# Course Modules

## Module 1 — Security Mindset & Threat Modeling

Description: Sebelum mencari kerentanan spesifik, harus bisa berpikir sistematis tentang di mana sistem bisa diserang — kerangka ini dipakai di seluruh modul berikutnya.

Lessons:

* [ ] 1.1 CIA triad, defense in depth, least privilege, zero trust — dari prinsip di `standards/SECURITY_STANDARDS.md` ke penerapan nyata
* [ ] 1.2 Threat modeling dengan STRIDE (Spoofing, Tampering, Repudiation, Information Disclosure, DoS, Elevation of Privilege)
* [ ] 1.3 Trust boundary & data flow diagram — memetakan di mana data melewati batas kepercayaan
* [ ] 1.4 Risk assessment dasar: likelihood x impact, intuisi CVSS scoring

Mini Project: Threat model 1 sistem nyata (mis. aplikasi Spring Boot Anda sendiri) memakai STRIDE, hasilkan dokumen threat model dengan trust boundary yang teridentifikasi.

Status: Not Started

---

## Module 2 — OWASP Top 10:2025 Mendalam

Description: Sepuluh risiko paling kritikal versi terbaru, masing-masing dengan contoh eksploitasi dan perbaikan konkret di konteks Spring Boot.

Lessons:

* [ ] 2.1 Broken Access Control (termasuk SSRF yang kini tergabung) — risiko #1 dan yang paling sering ditemukan
* [ ] 2.2 Injection & Security Misconfiguration — kesalahan konfigurasi default yang sering luput
* [ ] 2.3 Software Supply Chain Failures (kategori baru 2025) & Mishandling of Exceptional Conditions (kategori baru 2025) — koneksi ke `standards/ERROR_HANDLING_STANDARDS.md`
* [ ] 2.4 Sisa kategori Top 10 secara ringkas & prioritisasi mana yang paling relevan untuk backend API

Mini Project: Diberikan aplikasi Spring Boot yang sengaja rentan (dibangun sendiri atau contoh), temukan dan perbaiki kerentanan yang dipetakan ke kategori OWASP Top 10.

Status: Not Started

---

## Module 3 — Keamanan Authentication & Session

Description: Titik kegagalan paling umum di sistem production — implementasi auth yang "terlihat benar" tapi penuh celah.

Lessons:

* [ ] 3.1 Password storage yang benar (bcrypt/argon2), MFA, session management
* [ ] 3.2 JWT pitfalls: algorithm confusion (`alg: none`), secret lemah, tanpa expiry/rotation
* [ ] 3.3 OAuth2/OIDC flow deep dive & kesalahan konfigurasi umum
* [ ] 3.4 Pola serangan account takeover (credential stuffing, brute force) & pertahanannya (rate limiting, lockout)

Mini Project: Audit & perkeras implementasi auth (JWT/OAuth2) terhadap checklist pitfall umum — temukan minimal 3 celah dan perbaiki.

Status: Not Started

---

## Module 4 — Authorization & Access Control

Description: Broken Access Control adalah risiko #1 OWASP — modul ini masuk jauh lebih dalam dari Modul 2.

Lessons:

* [ ] 4.1 Pola broken access control: IDOR, privilege escalation, missing function-level access control
* [ ] 4.2 Desain RBAC vs ABAC, jebakan authorization di sistem multi-tenant
* [ ] 4.3 OWASP API Security Top 10: BOLA, broken object property-level authorization, unrestricted resource consumption

Mini Project: Perbaiki kerentanan IDOR/BOLA di API multi-tenant contoh, tambahkan test authorization yang membuktikan perbaikan bekerja.

Status: Not Started

---

## Module 5 — Input Validation, Injection & Data Sensitif

Description: Kelas kerentanan tertua tapi masih paling sering muncul, plus cara menangani data sensitif dengan benar.

Lessons:

* [ ] 5.1 Kelas injection: SQLi (termasuk jebakan raw query di balik ORM), command injection, SSRF, deserialization attack
* [ ] 5.2 Output encoding & XSS — relevan juga untuk backend API-first yang datanya dikonsumsi frontend
* [ ] 5.3 Menangani data sensitif: enkripsi at-rest/in-transit, PII handling, secure logging (tidak pernah log secret)

Mini Project: Temukan & perbaiki kerentanan injection di kode contoh, tambahkan lapisan validasi input yang proper.

Status: Not Started

---

## Module 6 — Keamanan Dependency & Supply Chain

Description: Kategori risiko yang tumbuh cepat (kini masuk OWASP Top 10:2025) — kode Anda mungkin aman, tapi dependency-nya belum tentu.

Lessons:

* [ ] 6.1 Kerangka SLSA (Google): source threat, build threat, dependency threat
* [ ] 6.2 Software Bill of Materials (SBOM) & dependency scanning (OWASP Dependency-Check/Snyk), risiko transitive dependency
* [ ] 6.3 Supply chain hardening di CI/CD: signed commit, provenance, hosted build runner — rujuk `courses/ci-cd` Modul 5 untuk implementasi pipeline

Mini Project: Setup dependency scanning + generate SBOM untuk 1 proyek nyata, triage dan perbaiki temuan yang ditemukan.

Status: Not Started

---

## Module 7 — Keamanan Cloud & Infrastruktur

Description: AppSec tidak berhenti di kode — konfigurasi cloud/infrastruktur yang salah bisa membatalkan semua kerja keras di modul sebelumnya.

Lessons:

* [ ] 7.1 IAM security deep-dive: least privilege policy, secret management — rujuk `courses/aws-api-integration` Modul 2 untuk implementasi
* [ ] 7.2 Dasar keamanan network untuk backend engineer: VPC/security group, TLS everywhere, firewall rule (secukupnya untuk paham & review, bukan network engineering penuh)
* [ ] 7.3 Dasar keamanan container/K8s — rujuk `courses/kubernetes` Modul 6 (SCC/RBAC/NetworkPolicy) untuk implementasi

Mini Project: Lakukan security review terhadap 1 deployment cloud yang ada (IAM policy, exposure network, secret) — hasilkan temuan + perbaikan.

Status: Not Started

---

## Module 8 — Security Code Review & Testing

Description: Skill yang membedakan engineer yang "tahu OWASP Top 10" dari yang benar-benar bisa mengaudit kode orang lain secara sistematis.

Lessons:

* [ ] 8.1 Metodologi manual security code review berbasis checklist OWASP ASVS
* [ ] 8.2 SAST/DAST dalam praktik — dari lensa AppSec practitioner (bukan lensa pipeline-builder seperti di `courses/ci-cd` Modul 5)
* [ ] 8.3 Menulis security test case: negative test, authorization test

Mini Project: Lakukan security code review penuh terhadap 1 PR/codebase nyata memakai checklist OWASP ASVS, tulis laporan temuan.

Status: Not Started

---

## Module 9 — Incident Response & Security Operations Dasar

Description: Kerentanan pasti pernah lolos — yang membedakan tim matang adalah cara meresponsnya.

Lessons:

* [ ] 9.1 Logging/monitoring untuk keamanan: mendeteksi anomali, bukan sekadar observability biasa
* [ ] 9.2 Dasar incident response: langkah saat kerentanan/breach ditemukan, proses disclosure, postmortem
* [ ] 9.3 Security dalam SDLC: shift-left, model security champion, threat modeling sebagai bagian design review

Mini Project: Tulis runbook incident response untuk skenario hipotetis (mis. API key bocor ke repository publik).

Status: Not Started

---

## Module 10 — Menjadi Security-Minded Senior/Staff Engineer

Description: Level di mana keamanan jadi bagian dari cara Anda mendesain sistem, bukan checklist tambahan di akhir.

Lessons:

* [ ] 10.1 Studi kasus: bagaimana Stripe/GitHub/Google menjalankan AppSec (security review gate, bug bounty program)
* [ ] 10.2 Membangun budaya security review dalam tim
* [ ] 10.3 Angle karier: bagaimana skill AppSec memperkuat kredibilitas freelance/senior, pertanyaan interview security yang umum

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Full Security Assessment & Hardening** — pilih 1 aplikasi Spring Boot nyata (proyek Anda sendiri atau proyek dari course lain di repo ini), jalankan assessment penuh: (1) threat model STRIDE (Modul 1), (2) audit OWASP Top 10 + perbaikan (Modul 2), (3) audit & pengerasan authentication/authorization (Modul 3-4), (4) audit injection & data sensitif (Modul 5), (5) dependency/supply chain scan + SBOM (Modul 6), (6) review keamanan cloud/infrastruktur (Modul 7), (7) laporan security code review formal berbasis ASVS (Modul 8), (8) incident response runbook (Modul 9).

Acceptance Criteria:

* Threat model mengidentifikasi minimal 5 trust boundary/skenario ancaman nyata, bukan generik
* Minimal 1 kerentanan nyata ditemukan & diperbaiki di tiap kategori: access control, injection, auth
* SBOM ter-generate dan temuan dependency scanning ditriase dengan keputusan jelas (fix/accept risk/mitigate)
* Laporan security code review mengikuti struktur OWASP ASVS, bukan daftar temuan acak
* Runbook incident response bisa diikuti orang lain tanpa penjelasan tambahan
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Belum dimulai — roadmap approved 2026-08-06, menunggu mulai

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — CIA triad, defense in depth, least privilege, zero trust
