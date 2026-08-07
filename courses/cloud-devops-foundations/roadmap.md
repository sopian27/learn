# Course Roadmap

## Course Information

* Course Name: Cloud Computing & DevOps Foundations — dari 0 sampai Expert
* Category: Cloud Architecture / DevOps Culture & Site Reliability Engineering
* Difficulty: Level 1 (paham cloud di level pemakaian, belum pernah berpikir provider-agnostic/SRE) → Level 4 (Expert, mampu memimpin keputusan arsitektur cloud & budaya DevOps)
* Estimated Duration: 9 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Familiar Java/Spring Boot & Docker dasar (dari repo ini). Tidak perlu menyelesaikan `courses/aws-api-integration`, `courses/kubernetes`, atau `courses/ci-cd` lebih dulu — course ini adalah lapisan konsep di *bawah* ketiganya, bukan lanjutan darinya.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda (2026-08-06):

1. **Lapisan konseptual provider-agnostic, bukan pengganti course hands-on yang sudah ada.** `courses/aws-api-integration` (AWS SDK for Java, hands-on 1 provider), `courses/kubernetes` (Docker/K8s/OpenShift, hands-on orkestrasi), `courses/ci-cd` (GitLab CI, hands-on pipeline) semuanya tetap berdiri sendiri dan tidak berubah. Course ini mengajarkan lapisan di bawahnya: cara berpikir tentang cloud computing & DevOps yang tidak terikat 1 tool/provider — supaya keputusan Anda di 3 course itu (dan di kerjaan nyata) punya fondasi konseptual, bukan sekadar ikut tutorial.
2. **IaC: Terraform**, dipilih justru karena berbeda dari AWS CDK yang sudah diajarkan di `courses/aws-api-integration` — Terraform provider-agnostic (HCL, state file, multi-cloud) sementara CDK terikat AWS. Modul 3 & 4 course ini secara eksplisit membandingkan keduanya, bukan mengajarkan CDK ulang.
3. **Observability & incident response: lapisan disiplin SRE umum**, bukan pengulangan. `courses/kubernetes` Modul 7 membahas observability spesifik-K8s (baca log/event `kubectl`/`oc`), `courses/ci-cd` Modul 8 membahas DORA metrics spesifik-pipeline, `courses/cybersecurity` Modul 9 membahas incident response spesifik-keamanan. Modul 6-7 course ini adalah payung konseptualnya: SLI/SLO/error budget (Google SRE), tiga pilar observability tool-agnostic, dan incident response/postmortem untuk insiden reliabilitas *apa pun* (bukan hanya security) — course lain tidak diubah, tetap dengan treatment spesifik masing-masing.
4. **Track paralel/independen dari Master Goal**, sama seperti 3 course baru lainnya (Generative AI & Workflow Design, Data Analysis & Visualization, Cybersecurity) — bukan prasyarat untuk "World-Class Backend Developer", meski tetap relevan dan saling memperkuat.

Referensi riset yang dipakai menyusun roadmap ini: perbandingan model layanan cloud IaaS/PaaS/SaaS & AWS/Azure/GCP (sintesis referensi industri standar), [Terraform — HashiCorp Developer documentation](https://developer.hashicorp.com/terraform) (HCL, core workflow init/plan/apply/destroy, state management), [Google SRE Book & Workbook](https://sre.google/workbook/index/) (SLI/SLO/error budget, dipakai sebagai kerangka Modul 6), *The DevOps Handbook*/*The Phoenix Project* — kerangka CALMS & "The Three Ways" (Flow, Feedback, Continual Learning), dipakai sebagai kerangka Modul 5, konsisten dengan DORA metrics yang sudah dipakai di `courses/ci-cd`.

---

# Learning Objectives

* [ ] Memahami model layanan cloud (IaaS/PaaS/SaaS) dan bisa membandingkan AWS/Azure/GCP secara konseptual untuk mengambil keputusan arsitektur
* [ ] Mampu mendesain building block cloud provider-agnostic: compute (VM/container/serverless), storage (object/block/file), networking dasar
* [ ] Mahir Terraform: HCL, core workflow, state management, module reusable, workflow tim (PR-based IaC)
* [ ] Paham DevOps sebagai budaya (CALMS, The Three Ways) — bukan sekadar sekumpulan tool
* [ ] Mampu menerapkan observability sebagai disiplin SRE: tiga pilar (log/metric/trace), SLI/SLO/error budget
* [ ] Mampu menjalankan incident management untuk insiden reliabilitas: siklus respons, blameless postmortem
* [ ] Memahami cost management & governance cloud dasar: tagging, budget, rightsizing, policy as code
* [ ] Mampu menyusun architecture decision doc yang menjelaskan trade-off ke stakeholder teknis maupun non-teknis
* [ ] Menyelesaikan proyek portofolio: fondasi cloud/DevOps penuh untuk 1 sistem, dari keputusan arsitektur sampai provisioning & reliability

---

# Course Modules

## Module 1 — Fondasi Cloud Computing & Lanskap Provider

Description: Sebelum masuk 1 provider mana pun, pahami dulu peta besar model layanan cloud dan trade-off memilih provider.

Lessons:

* [ ] 1.1 Model layanan cloud: IaaS, PaaS, SaaS — batas tanggung jawab provider vs Anda
* [ ] 1.2 Ekonomi cloud: capex vs opex, kapan on-prem masih masuk akal
* [ ] 1.3 Lanskap multi-cloud: perbandingan konseptual AWS vs Azure vs GCP (compute/storage/networking setara)
* [ ] 1.4 Trade-off arsitektur cloud: vendor lock-in, kapan multi-cloud make sense vs kapan jadi kesalahan

Mini Project: Tulis tabel perbandingan provider + memo rekomendasi singkat untuk skenario klien hipotetis yang harus memilih cloud provider.

Status: Not Started

---

## Module 2 — Building Block Cloud Inti (Provider-Agnostic)

Description: Konsep inti yang berlaku di provider mana pun — dasar untuk mendesain arsitektur sebelum menyentuh Terraform.

Lessons:

* [ ] 2.1 Model compute: VM vs container vs serverless — kerangka keputusan
* [ ] 2.2 Model storage: object vs block vs file storage — trade-off pemakaian
* [ ] 2.3 Dasar networking: konsep VPC, load balancing, CDN (rujuk `courses/cybersecurity` Modul 7 untuk lensa keamanannya, tidak diulang di sini)

Mini Project: Rancang diagram arsitektur konseptual untuk 1 aplikasi web sederhana, pilih compute/storage/network primitive yang tepat beserta alasannya.

Status: Not Started

---

## Module 3 — Infrastructure as Code dengan Terraform

Description: Tool IaC provider-agnostic — kontras sengaja dengan AWS CDK yang sudah diajarkan di `courses/aws-api-integration`.

Lessons:

* [ ] 3.1 Prinsip IaC: declarative vs imperative, kenapa IaC mengalahkan ClickOps manual
* [ ] 3.2 Core workflow Terraform: `init`, `plan`, `apply`, `destroy`, sintaks HCL, provider
* [ ] 3.3 State management: remote state, locking, drift detection
* [ ] 3.4 Terraform vs CDK vs Pulumi — kapan masing-masing tepat dipilih

Mini Project: Provision 1 stack kecil multi-resource (mis. network + compute + storage) memakai Terraform di akun cloud nyata/free-tier.

Status: Not Started

---

## Module 4 — Terraform dalam Praktik: Module & Workflow Tim

Description: Dari Terraform yang jalan di laptop sendiri ke Terraform yang aman dipakai tim.

Lessons:

* [ ] 4.1 Terraform module — IaC yang reusable & composable
* [ ] 4.2 Mengelola environment (dev/staging/prod) via workspace atau struktur direktori
* [ ] 4.3 Workflow tim: perubahan IaC berbasis PR, review output `plan`, remote state backend & locking untuk mencegah konflik

Mini Project: Refactor stack Modul 3 jadi Terraform module yang reusable, parametrikan untuk 2 environment berbeda.

Status: Not Started

---

## Module 5 — Budaya & Praktik DevOps

Description: DevOps yang sering disalahpahami sebagai "sekumpulan tool" — modul ini meluruskan ke akarnya: budaya.

Lessons:

* [ ] 5.1 Apa itu DevOps sebenarnya: kerangka CALMS (Culture, Automation, Lean, Measurement, Sharing)
* [ ] 5.2 The Three Ways (DevOps Handbook): Flow, Feedback, Continual Learning
* [ ] 5.3 Merobohkan silo: kolaborasi Dev/Ops/Security (DevSecOps), rujuk `courses/cybersecurity` untuk sisi keamanannya

Mini Project: Tulis asesmen singkat "budaya DevOps saat ini vs target" untuk tim hipotetis (atau tim nyata tempat Anda bekerja).

Status: Not Started

---

## Module 6 — Observability sebagai Disiplin SRE

Description: Payung konseptual di atas observability spesifik-tool yang sudah ada di `courses/kubernetes` dan `courses/ci-cd`.

Lessons:

* [ ] 6.1 Tiga pilar observability: log, metric, trace — prinsip tool-agnostic
* [ ] 6.2 SLI/SLO/error budget (Google SRE) — cara mendefinisikan dan memakainya untuk keputusan trade-off rilis vs reliabilitas
* [ ] 6.3 Praktik on-call: filosofi alerting (alert pada simptom, bukan penyebab), eskalasi, menghindari alert fatigue

Mini Project: Definisikan SLI/SLO + error budget policy untuk 1 layanan nyata/contoh, usulkan aturan alerting yang selaras dengannya.

Status: Not Started

---

## Module 7 — Incident Management & Budaya Reliabilitas

Description: Payung konseptual di atas incident response spesifik-keamanan yang sudah ada di `courses/cybersecurity` Modul 9 — di sini untuk insiden reliabilitas apa pun.

Lessons:

* [ ] 7.1 Siklus incident response untuk insiden reliabilitas (deteksi → mitigasi → resolusi → tindak lanjut)
* [ ] 7.2 Blameless postmortem — cara menjalankannya, struktur dokumen postmortem yang baik
* [ ] 7.3 Trade-off reliabilitas: pengurangan toil, ROI otomasi, kapan harus bilang "tidak" ke fitur demi reliabilitas

Mini Project: Tulis dokumen blameless postmortem untuk insiden hipotetis, lengkap dengan timeline, root cause, dan action item.

Status: Not Started

---

## Module 8 — Cost, Governance & Trade-off Arsitektur

Description: Keputusan cloud/DevOps yang baik selalu punya sisi biaya dan tata kelola, bukan cuma teknis.

Lessons:

* [ ] 8.1 Manajemen biaya cloud: tagging, budget, rightsizing, reserved vs on-demand vs spot
* [ ] 8.2 Dasar governance: overview policy as code, pertimbangan compliance (secukupnya, bukan GRC penuh)
* [ ] 8.3 Kerangka menyajikan trade-off arsitektur ke stakeholder teknis maupun non-teknis

Mini Project: Susun rekomendasi optimasi biaya + governance untuk skenario cloud spend yang diberikan.

Status: Not Started

---

## Module 9 — Menjadi Engineer yang Berpikir Cloud/DevOps

Description: Level penutup — menyatukan seluruh course jadi cara berpikir, plus melihat ke mana arah bidang ini bergerak.

Lessons:

* [ ] 9.1 Studi kasus: bagaimana Netflix/Spotify/Airbnb menyusun tim platform/SRE
* [ ] 9.2 Overview tren platform engineering (internal developer platform) — ke mana bidang ini bergerak
* [ ] 9.3 Angle karier: bagaimana fondasi ini memperkuat kredibilitas freelance/senior lintas `aws-api-integration`/`kubernetes`/`ci-cd`

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Full Cloud/DevOps Foundations Deliverable** — pilih 1 sistem nyata/realistis, jalankan: (1) architecture decision doc (pilihan provider, compute/storage/network primitive, Modul 1-2), (2) provisioning via Terraform module di 2 environment (Modul 3-4), (3) SLI/SLO/error budget + aturan alerting (Modul 6), (4) asesmen budaya DevOps + template blameless postmortem untuk tim (Modul 5, 7), (5) rekomendasi cost & governance (Modul 8).

Acceptance Criteria:

* Architecture decision doc menjelaskan trade-off secara eksplisit, bukan cuma daftar pilihan tanpa alasan
* Terraform module berhasil di-`apply` ke 2 environment berbeda dari kode yang sama (parametrized, bukan duplikat)
* SLI/SLO terukur dari data nyata (bukan angka asal), error budget policy jelas kapan dipakai untuk menahan rilis fitur
* Postmortem template lengkap (timeline, root cause, action item) dan bisa dipakai ulang untuk insiden lain
* Rekomendasi cost/governance disertai angka/estimasi, bukan saran generik
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Model layanan cloud: IaaS, PaaS, SaaS
