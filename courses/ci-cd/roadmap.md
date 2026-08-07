# Course Roadmap

## Course Information

* Course Name: CI/CD Mastery — dari Permukaan ke Expert
* Category: DevOps / Software Delivery
* Difficulty: Level 0 (dasar) → Level 4 (Expert, mampu mendesain pipeline dari nol untuk sistem production)
* Estimated Duration: 9 modul, 3-5 lesson/modul, ~30-40 menit/lesson. Pace belum ditentukan eksplisit — disesuaikan sambil jalan (lihat catatan di `progress/progress.md`).
* Prerequisites: Git dasar, Linux CLI dasar, Docker dasar (beririsan dengan `courses/kubernetes` Modul 1 yang sedang paused). Familiar Java/Spring Boot (dipakai sebagai aplikasi contoh di semua mini project, sesuai root `CLAUDE.md`).

---

# Catatan Scope (baca dulu sebelum mulai)

Berdasarkan jawaban kalibrasi Anda (2026-08-06):

1. **Tool utama**: **GitLab CI** — semua contoh hands-on (`.gitlab-ci.yml`, runner, variables, environments) pakai GitLab CI. Konsep universal (stage, artifact, cache, secret, deployment strategy) tetap dijelaskan generik dulu sebelum masuk sintaks GitLab CI, supaya transferable ke GitHub Actions/Jenkins/dll bila suatu saat perlu. Modul 9.2 khusus membandingkan GitLab CI vs GitHub Actions vs Jenkins.
2. **Level awal**: **Semuanya masih permukaan** — bisa jalanin pipeline yang sudah ada, tapi belum bisa desain dari nol. Karena itu course dimulai dari mental model paling dasar (CI vs CD vs CD) sebelum masuk sintaks tool, dan setiap modul menjelaskan "kenapa" sebelum "bagaimana", sesuai `standards/CI_CD_STANDARDS.md` bagian "Learning Expectations".
3. **Format**: Course terstruktur penuh dengan roadmap + progress tracking, konsisten dengan course lain di repo ini.
4. **Acuan standar**: Semua materi & review pipeline mengikuti `standards/CI_CD_STANDARDS.md` (dokumen ini prioritas tertinggi bila ada konflik). Contoh aplikasi selalu Spring Boot + Postgres/Redis/Kafka sesuai stack di root `CLAUDE.md`, dan berintegrasi dengan `courses/kubernetes` (deploy target) serta `standards/FLYWAY_STANDARDS.md` (migration dalam pipeline).

Referensi: `standards/CI_CD_STANDARDS.md`, [GitLab CI/CD Documentation](https://docs.gitlab.com/ee/ci/), [DORA Metrics (DevOps Research and Assessment)](https://dora.dev/).

---

# Learning Objectives

* [ ] Membedakan secara presisi Continuous Integration, Continuous Delivery, dan Continuous Deployment — bukan sekadar hafal istilah
* [ ] Memahami filosofi & anatomi pipeline: source checkout → build → test → package → deploy, dan alasan tiap stage ada
* [ ] Menguasai GitLab CI: `.gitlab-ci.yml`, runner & executor, variables/secrets, artifact vs cache, rules/workflow
* [ ] Mampu membangun pipeline build & test otomatis untuk aplikasi Java/Spring Boot (unit test, integration test dengan Testcontainers, static analysis)
* [ ] Menguasai artifact & container pipeline: immutable artifact, multi-stage Docker build, image tagging, container scanning
* [ ] Menguasai security dalam pipeline: secret scanning, dependency scanning, SAST, secrets management yang benar
* [ ] Memahami dan mampu memilih deployment strategy (rolling, blue-green, canary, feature flag) sesuai konteks risiko
* [ ] Mampu deploy ke Kubernetes/OpenShift dari GitLab CI, memahami trade-off push-based vs pull-based GitOps
* [ ] Menguasai observability pipeline: DORA metrics, notification, reliability (retry, fail-fast, idempotency)
* [ ] Mampu mendesain pipeline end-to-end dari nol untuk sistem microservices dan menjelaskan setiap trade-off (siap review/interview Senior)

---

# Course Modules

## Module 1 — CI/CD Fundamentals & Mental Model

Description: Fondasi konsep sebelum sentuh tool apapun — supaya semua modul berikutnya punya kerangka berpikir yang benar, bukan hafalan sintaks.

Lessons:

* [ ] 1.1 CI vs Continuous Delivery vs Continuous Deployment — definisi presisi & kesalahan umum
* [ ] 1.2 Masalah yang dipecahkan CI/CD — dunia sebelum vs sesudah (integration hell, manual deploy, "works on my machine")
* [ ] 1.3 Anatomi pipeline: stage vs job, urutan standar (checkout → build → test → package → deploy)
* [ ] 1.4 Branching strategy: Git Flow vs GitHub Flow vs Trunk-Based — pengaruhnya ke desain pipeline

Mini Project: Gambar (diagram) anatomi pipeline untuk 1 aplikasi Spring Boot sederhana, dari commit sampai production, lengkap dengan branching strategy yang dipilih + alasan.

Status: Not started

---

## Module 2 — GitLab CI Dasar (Hands-on Tool)

Description: Masuk ke sintaks & mekanisme GitLab CI sebagai tool utama course ini.

Lessons:

* [ ] 2.1 Anatomi `.gitlab-ci.yml`: stages, jobs, script, image
* [ ] 2.2 Runner & executor (docker, shell) — shared vs project-specific
* [ ] 2.3 Variables & secrets (project/group/instance level, protected, masked)
* [ ] 2.4 Artifact vs cache — beda konsep dan kapan pakai yang mana
* [ ] 2.5 `rules`, `workflow`, `only`/`except` — kontrol kapan job jalan

Mini Project: Pipeline GitLab CI pertama untuk aplikasi Spring Boot — checkout, build, run unit test.

Status: Not started

---

## Module 3 — Build & Test Automation

Description: Stage build dan test yang benar untuk aplikasi Java/Spring Boot di CI, bukan cuma `mvn test` polos.

Lessons:

* [ ] 3.1 Build stage: Maven/Gradle di GitLab CI, dependency caching yang efektif
* [ ] 3.2 Unit test otomatis + test report (JUnit XML) & coverage report
* [ ] 3.3 Integration test dengan Testcontainers di CI (Docker-in-Docker, `services:`)
* [ ] 3.4 Static analysis & linting (Checkstyle, SonarQube integration ke pipeline)

Mini Project: Pipeline dengan unit + integration test (Testcontainers Postgres) + coverage report yang tampil di GitLab MR.

Status: Not started

---

## Module 4 — Artifact & Container Pipeline

Description: Dari kode teruji menjadi artifact yang siap deploy — dan prinsip "build once, deploy many".

Lessons:

* [ ] 4.1 Immutable artifact — kenapa tidak boleh rebuild setelah test lulus
* [ ] 4.2 Container build stage: multi-stage Dockerfile di CI, strategi tagging image (commit SHA vs semver)
* [ ] 4.3 Push ke registry (GitLab Container Registry)
* [ ] 4.4 Container scanning (Trivy/Grype) sebagai gate pipeline

Mini Project: Pipeline lengkap build → test → package image → scan → push registry, dengan tag berbasis commit SHA.

Status: Not started

---

## Module 5 — Security dalam Pipeline

Description: Keamanan bukan tempelan di akhir — harus jadi gate otomatis di pipeline.

Lessons:

* [ ] 5.1 Secret scanning & dependency scanning
* [ ] 5.2 SAST/DAST overview (khususnya fitur bawaan GitLab)
* [ ] 5.3 Secrets management: CI/CD variables vs Vault vs cloud secret manager — kapan yang mana
* [ ] 5.4 Least privilege pipeline: protected branch, protected variable, deploy token

Mini Project: Audit & perbaiki pipeline Modul 4 yang sengaja punya kebocoran secret & tanpa scanning.

Status: Not started

---

## Module 6 — Deployment Strategies

Description: Cara deploy yang aman untuk production, bukan sekadar `kubectl apply` dan berdoa.

Lessons:

* [ ] 6.1 Environment di GitLab CI (`environment:`, deployment tracking)
* [ ] 6.2 Rolling update vs Blue-Green vs Canary — implementasi & trade-off risiko/kompleksitas
* [ ] 6.3 Feature flag overview — decouple deploy dari release
* [ ] 6.4 Rollback strategy — manual vs otomatis, kapan masing-masing tepat

Mini Project: Implementasi salah satu strategi (blue-green atau canary) untuk aplikasi contoh + skenario rollback yang diuji.

Status: Not started

---

## Module 7 — Deploy ke Kubernetes/OpenShift dari GitLab CI

Description: Menyambungkan pipeline ke target deploy nyata (beririsan dengan `courses/kubernetes`).

Lessons:

* [ ] 7.1 Deploy Helm chart dari job GitLab CI
* [ ] 7.2 GitOps: push-based (kubectl/helm dari CI) vs pull-based (ArgoCD) — trade-off
* [ ] 7.3 Approval gate: manual job & protected environment untuk production

Mini Project: Pipeline deploy otomatis ke staging, manual-approval ke production, dengan environment terpisah di GitLab.

Status: Not started

---

## Module 8 — Observability & Reliability Pipeline

Description: Mengukur dan menjaga kesehatan pipeline itu sendiri sebagai sistem produksi.

Lessons:

* [ ] 8.1 DORA metrics: deployment frequency, lead time, MTTR, change failure rate
* [ ] 8.2 Pipeline observability: durasi stage, success/failure rate, notifikasi (Slack/email)
* [ ] 8.3 Reliability pipeline: retry transient failure, fail-fast, idempotent job design
* [ ] 8.4 Koordinasi database migration (Flyway) dalam pipeline — backward compatibility & urutan deploy

Mini Project: Dashboard/report sederhana DORA metrics dari histori pipeline + notifikasi Slack untuk failure.

Status: Not started

---

## Module 9 — Menuju Expert: Arsitektur & Trade-off

Description: Topik yang membedakan level medior dari expert — desain, bukan cuma eksekusi.

Lessons:

* [ ] 9.1 Multi-project/parent-child pipeline, strategi CI untuk monorepo vs polyrepo
* [ ] 9.2 Perbandingan GitLab CI vs GitHub Actions vs Jenkins — kapan pilih apa
* [ ] 9.3 Optimasi performa & biaya pipeline (cache, parallel job, DAG needs)
* [ ] 9.4 Studi kasus: desain pipeline end-to-end dari nol untuk sistem microservices

Portfolio Project: Pipeline CI/CD lengkap untuk sistem microservices Spring Boot (order/payment/notification service ala `courses/kubernetes` Enterprise Project) — build, test (unit+integration Testcontainers), security scan, containerize, deploy blue-green ke Kubernetes/OpenShift via GitLab CI, dengan DORA metrics & rollback teruji. Disimpan di `portofolio/`.

Status: Not started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: 1.1 CI vs Continuous Delivery vs Continuous Deployment
