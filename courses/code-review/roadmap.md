# Course Roadmap

## Course Information

* Course Name: Code Review Mastery for Backend Engineers
* Category: Software Engineering Practice / Quality Engineering
* Difficulty: Level 2 (Intermediate) → Level 4 (Expert)
* Estimated Duration: 9 modules, ~2-4 lessons each (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Pengalaman backend production (Java/Spring Boot diasumsikan sesuai stack repo — konfirmasi di bawah), sudah pernah submit & menerima PR review

---

# Learning Objectives

* [ ] Mampu mereview kode bukan sekadar "cari bug", tapi menilai correctness, arsitektur, security, performance, dan production-readiness sekaligus
* [ ] Mampu memberi feedback yang mendidik, bukan sekadar mengoreksi
* [ ] Mampu mereview di level desain/arsitektur (RFC/design doc), bukan cuma diff
* [ ] Mampu menyusun standar review untuk tim (checklist, SLA, tooling)
* [ ] Mampu mentoring engineer lain lewat proses review
* [ ] Dipercaya sebagai final/gatekeeping reviewer untuk kode production

---

# Course Modules

## Module 1 — Foundations & Review Mindset

Description: Kenapa code review penting (bukan cuma cari bug), budaya blameless, sync vs async review, biaya review yang buruk (rubber-stamping vs bikeshedding).

Lessons:

* [ ] Tujuan sebenarnya dari code review (defect prevention, knowledge sharing, konsistensi, mentoring)
* [ ] Anatomy of a bad review vs a great review
* [ ] Psychological safety & blameless culture dalam review

Mini Project: Analisis 1 PR review nyata (bisa dari open-source) — identifikasi mana feedback yang efektif, mana yang sekadar nitpick.

Status: Not Started

---

## Module 2 — The Core Review Checklist

Description: Kerangka evaluasi inti yang dipakai sepanjang course: Correctness, Architecture/SOLID, Readability, Error Handling, Testing, Documentation. Memetakan langsung ke `templates/CODE_REVIEW_TEMPLATE.md` dan `ai-los/REVIEW_MODE.md` yang sudah ada di repo ini.

Lessons:

* [ ] Correctness & edge case hunting
* [ ] Architecture & SOLID dalam konteks review (bukan cuma menulis, tapi menilai)
* [ ] Readability, naming, dan cognitive load
* [ ] Error handling, logging, observability
* [ ] Menilai kecukupan testing (coverage vs meaningful tests)

Mini Project: Review kode dengan cacat yang disengaja (disediakan bertahap: syntax-level → logic-level) memakai `templates/CODE_REVIEW_TEMPLATE.md`.

Status: Not Started

---

## Module 3 — Security-Focused Review

Description: OWASP Top 10 dari sudut pandang reviewer, bukan penulis kode.

Lessons:

* [ ] Injection, broken auth, sensitive data exposure dalam diff
* [ ] Input validation & sanitization checklist
* [ ] Secrets & credential handling
* [ ] Dependency/supply-chain awareness saat review

Mini Project: Security review terhadap PR yang mengandung beberapa celah OWASP tersembunyi.

Status: Not Started

---

## Module 4 — Performance & Scalability Review

Description: Mengenali red flag performa hanya dari membaca diff, tanpa menjalankan profiler.

Lessons:

* [ ] N+1 query, indexing, dan database access patterns
* [ ] Cache correctness (invalidation, stampede)
* [ ] Concurrency issues (race condition, deadlock, thread-safety)
* [ ] Resource leak (connection, thread, memory)

Mini Project: Review PR dengan bug performa tersembunyi (N+1 query + cache invalidation salah).

Status: Not Started

---

## Module 5 — Backend Deep Dives (Java/Spring Boot Focus)

Description: Area spesifik backend yang sering dilewatkan reviewer junior — disesuaikan stack repo ini (Java, Spring Boot, PostgreSQL, Kafka).

Lessons:

* [ ] Spring Boot anti-pattern (field injection, fat controller, transactional pitfalls)
* [ ] Review migrasi database (Flyway), transaction boundary, locking
* [ ] API/contract review (REST convention, OpenAPI accuracy, backward compatibility)
* [ ] Distributed system concerns (idempotency, retry, timeout, Kafka consumer pitfalls)

Mini Project: Review PR Spring Boot lengkap (controller + service + repository + migration) dengan multiple layered issues.

Status: Not Started

---

## Module 6 — Architecture & Design-Level Review (Expert Territory)

Description: Review sebelum kode ada — RFC/design doc — dan menilai trade-off, bukan implementasi.

Lessons:

* [ ] Cara mereview design doc/RFC
* [ ] Menilai trade-off arsitektur (bukan "benar/salah" tapi "cocok untuk konteks ini?")
* [ ] Cross-service/cross-team impact review
* [ ] Menimbang technical debt jangka panjang vs kecepatan delivery

Mini Project: Review 1 design doc (disediakan) dan tulis assessment trade-off-nya.

Status: Not Started

---

## Module 7 — Giving Effective Feedback

Description: Skill komunikasi yang membedakan reviewer intermediate dan expert.

Lessons:

* [ ] Severity labeling (blocking vs nit vs suggestion) — Conventional Comments
* [ ] Menulis feedback yang mendidik, bukan menghakimi
* [ ] Menangani disagreement/pushback secara diplomatis
* [ ] Mereview junior vs senior vs peer secara berbeda

Mini Project: Tulis ulang 5 contoh feedback review yang buruk menjadi versi yang efektif.

Status: Not Started

---

## Module 8 — Process, Tooling & Metrics

Description: Bagaimana review yang baik di-scale ke seluruh tim, bukan cuma personal skill.

Lessons:

* [ ] Disiplin ukuran/scope PR
* [ ] Static analysis sebagai first line of defense (SonarQube, Checkstyle/PMD/SpotBugs) — bisa praktik langsung pakai tool `sonarqube` yang tersedia di environment ini
* [ ] CI quality gate & review SLA
* [ ] Metrik review (turnaround time, defect escape rate)

Mini Project: Tulis dokumen "Code Review Guidelines" untuk tim fiktif (format mirip `standards/` di repo ini).

Status: Not Started

---

## Module 9 — Becoming the Expert Reviewer

Description: Level di mana Anda jadi trusted final reviewer dan mentor.

Lessons:

* [ ] Mentoring engineer lain lewat proses review
* [ ] Menjalankan review calibration session (menyamakan standar antar reviewer)
* [ ] Mendorong engineering standards di seluruh organisasi
* [ ] Studi kasus: budaya review di Google, Microsoft, Stripe, Netflix

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Full Expert Review Simulation** — (1) Tulis dokumen Code Review Guidelines lengkap untuk tim backend fiktif, mencakup checklist, severity levels, dan SLA. (2) Lakukan review penuh terhadap satu PR kompleks (disediakan, berlapis: ada masalah correctness + security + performance + arsitektur sekaligus) dan dokumentasikan hasilnya memakai `templates/CODE_REVIEW_TEMPLATE.md`. (3) Terima meta-review atas review Anda sendiri.

Acceptance Criteria:

* Guidelines mencakup minimal: checklist evaluasi, severity labeling, SLA, dan proses eskalasi disagreement
* Review PR menemukan seluruh cacat yang sengaja disisipkan (correctness, security, performance, arsitektur)
* Feedback yang ditulis memenuhi standar Module 7 (mendidik, bukan menghakimi)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: Lesson 1 — Tujuan sebenarnya dari code review (defect prevention, knowledge sharing, konsistensi, mentoring)
