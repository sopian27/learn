# Course Roadmap

## Course Information

* Course Name: Google Cloud Platform (GCP) — dari 0 sampai Expert
* Category: Cloud Engineering / Backend Deployment (Provider-Specific: GCP)
* Difficulty: Level 1 (paham cloud di level konsep umum, belum pernah punya GCP project sendiri) → Level 4 (Expert, mampu mendesain & mengoperasikan sistem production di GCP setara Professional Cloud Developer)
* Estimated Duration: 9 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Familiar Java/Spring Boot (dari repo ini) dan sudah punya pengalaman deploy aplikasi (mis. dari `courses/vps` atau pengalaman kerja) — course ini **tidak** mengajarkan ulang Java/Spring Boot, melainkan memakainya sebagai aplikasi nyata yang di-deploy ke GCP. Familiar Docker dasar akan membantu di Modul 4 tapi tidak wajib. Tidak perlu menyelesaikan `courses/cloud-devops-foundations`, `courses/aws-api-integration`, atau `courses/kubernetes` lebih dulu — lihat "Catatan Scope" di bawah untuk hubungan dengan ketiganya.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini adalah course **hands-on spesifik-provider** untuk Google Cloud Platform, disusun dengan pola struktural yang sama seperti `courses/aws-api-integration` (AWS) dan course paralel untuk Microsoft Azure (`courses/azure`, sedang disusun terpisah) — tiga course "sibling" yang masing-masing dalam-dalam ke satu provider, bukan saling menggantikan.

1. **Beda dari `courses/cloud-devops-foundations`.** Course itu adalah lapisan *konsep* provider-agnostic: model layanan cloud (IaaS/PaaS/SaaS), building block generik (compute/storage/networking sebagai konsep), Terraform sebagai tool IaC secara umum (HCL, workflow `init/plan/apply`, state), budaya DevOps (CALMS, Three Ways), dan disiplin SRE (SLI/SLO, observability tiga pilar, incident management) — semuanya tanpa pernah login ke satu provider tertentu. Course ini kebalikannya: 100% hands-on di **console & CLI GCP yang sungguhan** — `gcloud` nyata, project GCP nyata, IAM role nyata, Cloud Run yang benar-benar men-deploy JAR Spring Boot dan bisa diakses lewat URL publik. Modul 7 course ini (Terraform di GCP) secara eksplisit **memakai ulang** pemahaman HCL/workflow dari `cloud-devops-foundations` Modul 3-4 dan menerapkannya ke provider `google`, bukan mengajarkan Terraform dari nol lagi.
2. **Sibling terhadap `courses/aws-api-integration`, bukan duplikatnya.** Course AWS itu fokus ke integrasi SDK Java (S3, Lambda, API Gateway, SQS/SNS, DynamoDB) dengan kredensial IAM AWS. Course ini memakai analog GCP untuk stack yang sama secara konseptual (Cloud Storage, Cloud Run/Cloud Functions, Pub/Sub, Firestore/Cloud SQL) tapi dengan model IAM, tooling (`gcloud`, Application Default Credentials), dan filosofi deployment GCP sendiri (Cloud Run sebagai compute-first-class, bukan Lambda-first). Modul & urutan sengaja dibuat sejajar secara struktural (fondasi → IAM/security → compute → storage → data → IaC → CI/CD → observability → expert) supaya pola belajar terasa familiar, tapi kontennya murni GCP.
3. **Beda dari `courses/kubernetes`.** GKE (Google Kubernetes Engine) hanya disinggung sekilas di Modul 3 sebagai salah satu opsi compute GCP dan kapan memilihnya dibanding Cloud Run — orkestrasi container mendalam (Docker → K8s → OpenShift) tetap sepenuhnya domain `courses/kubernetes`, tidak diulang di sini.
4. **Reuse stack yang sudah dikuasai, bukan mengajar ulang.** Modul 4 (Cloud Run) men-deploy aplikasi **Spring Boot** yang sudah familiar dari repo ini sebagai container, bukan mengajarkan Spring Boot dari nol. Modul 5 (data services) memakai **PostgreSQL** (via Cloud SQL) dan **Redis** (via Memorystore) — stack database yang sama persis dengan yang sudah dipakai di course lain, hanya dipindah ke layanan managed GCP.
5. **Track paralel/independen dari Master Goal**, sama seperti `courses/vps`, `courses/aws-api-integration`, dan `courses/cloud-devops-foundations` — bukan prasyarat untuk "World-Class Backend Developer", tapi sangat applied untuk deploy portofolio nyata (mis. hasil `courses/backend-fundamentals` atau `courses/system-design`) ke cloud provider production-grade yang bisa ditunjukkan ke klien freelance.

Referensi riset yang dipakai menyusun roadmap ini:
- [Associate Cloud Engineer Certification — Google Cloud](https://cloud.google.com/learn/certification/cloud-engineer) dan [exam guide resminya (PDF)](https://services.google.com/fh/files/misc/associate_cloud_engineer_exam_guide_english.pdf) — dipakai sebagai kerangka Modul 1-3 (project/IAM/regions-zones/compute options), scope "operasional GCP sehari-hari".
- [Professional Cloud Developer Certification — Google Cloud](https://cloud.google.com/learn/certification/cloud-developer) dan [exam guide resminya (PDF)](https://services.google.com/fh/files/misc/professional_cloud_developer_exam_guide_english.pdf) — track sertifikasi paling relevan untuk backend developer; dipakai sebagai kerangka level Expert (Modul 8-9): deploying applications, exchanging data, monitoring/troubleshooting, scaling.
- [Cloud Run documentation — Google Cloud](https://docs.cloud.google.com/run/docs) dan [Quickstart: Deploy a containerized application to Cloud Run](https://docs.cloud.google.com/run/docs/quickstarts/deploy-container) — kerangka Modul 4 (deploy Spring Boot container).
- [Connect a Spring Boot app to Cloud SQL — Google Codelabs](https://codelabs.developers.google.com/codelabs/cloud-spring-petclinic-cloudsql) dan [Spring Cloud GCP — Cloud SQL reference](https://googlecloudplatform.github.io/spring-cloud-gcp/3.2.1/reference/html/sql.html) — kerangka Modul 5 (Cloud SQL PostgreSQL dari Spring Boot).
- [Terraform on Google Cloud — overview](https://docs.cloud.google.com/docs/terraform/terraform-overview) dan [Getting Started with the Google Cloud provider — Terraform Registry](https://registry.terraform.io/providers/hashicorp/google/latest/docs/guides/getting_started) — kerangka Modul 7, eksplisit menyambung dari Terraform generik yang sudah diajarkan di `cloud-devops-foundations`.

---

# Learning Objectives

* [ ] Memahami struktur resource hierarchy GCP (Organization → Folder → Project) dan mahir navigasi Console + `gcloud` CLI
* [ ] Mahir IAM GCP: principal, role (basic/predefined/custom), policy binding, dan prinsip least privilege — termasuk Service Account & Application Default Credentials
* [ ] Mampu membandingkan opsi compute GCP (Compute Engine vs Cloud Run vs App Engine vs GKE) dan memilih yang tepat sesuai use case
* [ ] Mahir men-deploy aplikasi Spring Boot production sebagai container di **Cloud Run**, termasuk konfigurasi environment, scaling, dan traffic management
* [ ] Mampu mengintegrasikan **Cloud SQL for PostgreSQL** dan **Memorystore for Redis** dari aplikasi Spring Boot yang berjalan di Cloud Run
* [ ] Mahir **Cloud Storage** untuk object storage (bucket, IAM, signed URL, lifecycle policy)
* [ ] Memahami networking dasar GCP: VPC, subnet, firewall rule, Serverless VPC Access untuk menghubungkan Cloud Run ke resource privat
* [ ] Mampu men-*provision* infrastruktur GCP secara reproducible dengan **Terraform** (provider `google`), menyambungkan dari fondasi Terraform yang sudah dikuasai di `cloud-devops-foundations`
* [ ] Mampu membangun CI/CD pipeline yang men-deploy otomatis ke Cloud Run (Cloud Build dan/atau GitHub Actions)
* [ ] Mampu memakai Cloud Monitoring & Cloud Logging untuk observability dasar aplikasi production di GCP
* [ ] Menyelesaikan proyek portofolio: aplikasi Spring Boot live di Cloud Run, dengan Cloud SQL, CI/CD, dan monitoring — disimpan di `portofolio/`

---

# Course Modules

## Module 1 — Fondasi GCP: Project, Resource Hierarchy & Console/CLI

Description: Sebelum menyentuh service apa pun, pahami struktur dasar GCP dan cara berinteraksi dengannya — baik lewat Console maupun `gcloud` CLI. Ini scope inti Associate Cloud Engineer.

Lessons:

* [ ] 1.1 Resource hierarchy GCP: Organization → Folder → Project → Resource, dan kenapa struktur ini penting untuk billing & access control
* [ ] 1.2 Region & zone: cara memilih lokasi resource, latency, dan pertimbangan multi-region
* [ ] 1.3 Setup: buat GCP project, aktifkan billing (free tier/trial), install & konfigurasi `gcloud` CLI, `gcloud init`
* [ ] 1.4 Console vs `gcloud` CLI vs Cloud Shell — kapan pakai yang mana, dan kenapa CLI/scripting lebih disukai untuk repeatability

Mini Project: Buat 1 GCP project baru dari nol, konfigurasi `gcloud` CLI lokal, dan jalankan sederet perintah `gcloud` dasar (list project, list service account, enable API) untuk memverifikasi environment siap dipakai.

Status: Not Started

---

## Module 2 — IAM & Keamanan Dasar GCP

Description: Modul paling penting sebelum deploy apa pun — hampir semua masalah akses di GCP berakar dari IAM yang salah konfigurasi.

Lessons:

* [ ] 2.1 Model IAM GCP: principal (user/group/service account), role (basic: Owner/Editor/Viewer vs predefined vs custom), policy binding
* [ ] 2.2 Service Account: kapan dipakai, cara membuat, dan kenapa service account key file adalah anti-pattern yang harus dihindari di production
* [ ] 2.3 Application Default Credentials (ADC) — cara aplikasi (termasuk Spring Boot) mendapatkan kredensial GCP tanpa hardcode key
* [ ] 2.4 Prinsip least privilege: audit IAM binding, `gcloud projects get-iam-policy`, kapan pakai custom role

Mini Project: Buat service account dengan role minimal (bukan Owner) untuk keperluan deploy aplikasi, verifikasi ADC bekerja lewat `gcloud auth application-default login`, dan tulis catatan role apa saja yang dipakai dan alasannya.

Status: Not Started

---

## Module 3 — Peta Compute GCP: Memilih Layanan yang Tepat

Description: GCP menawarkan beberapa cara menjalankan kode — modul ini membangun kerangka keputusan sebelum masuk hands-on Cloud Run di Modul 4.

Lessons:

* [ ] 3.1 Compute Engine (VM): kapan masih relevan (full control, workload legacy) — sekilas, bukan hands-on mendalam
* [ ] 3.2 App Engine (PaaS lama GCP) vs Cloud Run (serverless container modern) — kenapa Cloud Run jadi pilihan course ini untuk Spring Boot
* [ ] 3.3 Cloud Functions vs Cloud Run — event-driven function kecil vs service container penuh
* [ ] 3.4 GKE (Google Kubernetes Engine) sekilas: kapan orkestrasi container penuh dibutuhkan (rujuk `courses/kubernetes` untuk hands-on mendalam, tidak diulang di sini)

Mini Project: Tulis decision matrix (tabel trade-off) untuk 3 skenario aplikasi berbeda — mana yang cocok Compute Engine, mana Cloud Run, mana GKE — lengkap dengan alasan.

Status: Not Started

---

## Module 4 — Deploy Spring Boot ke Cloud Run

Description: Inti hands-on course ini — men-deploy aplikasi Spring Boot yang sudah dikenal sebagai container serverless production-grade di Cloud Run.

Lessons:

* [ ] 4.1 Containerize Spring Boot: Dockerfile production-grade (multi-stage build, non-root user), kontrak `PORT` environment variable yang wajib dipatuhi Cloud Run
* [ ] 4.2 Deploy pertama: `gcloud run deploy` dari source atau dari image di Artifact Registry, memahami revision & traffic split
* [ ] 4.3 Konfigurasi production: environment variable, secret via Secret Manager, concurrency, memory/CPU limit, min/max instance (cold start trade-off)
* [ ] 4.4 Custom domain, HTTPS otomatis, dan IAM invoker (public vs authenticated-only service)

Mini Project: Deploy 1 aplikasi Spring Boot (boleh hasil dari course lain) ke Cloud Run, bisa diakses via URL publik, dengan environment variable production terpisah dari kode dan minimal 1 secret dikelola lewat Secret Manager.

Status: Not Started

---

## Module 5 — Data Services: Cloud SQL (PostgreSQL) & Memorystore (Redis)

Description: Menghubungkan Cloud Run ke database managed — reuse stack PostgreSQL/Redis yang sudah dikenal, kali ini sebagai layanan GCP.

Lessons:

* [ ] 5.1 Cloud SQL for PostgreSQL: provisioning instance, koneksi via Cloud SQL Auth Proxy/Unix socket dari Cloud Run, connection pooling
* [ ] 5.2 Migrasi skema & seed data ke Cloud SQL, integrasi konfigurasi Spring Boot (`application-prod.yml`, datasource URL format GCP)
* [ ] 5.3 Memorystore for Redis: provisioning instance, koneksi dari Cloud Run lewat Serverless VPC Access (Memorystore hanya bisa diakses dari dalam VPC)
* [ ] 5.4 Backup, high availability dasar, dan cost awareness untuk Cloud SQL/Memorystore (tier apa yang masuk akal untuk latihan vs production)

Mini Project: Aplikasi dari Modul 4 terhubung ke Cloud SQL PostgreSQL (persistence) dan Memorystore Redis (caching/session), keduanya diakses dengan aman (bukan public IP terbuka).

Status: Not Started

---

## Module 6 — Storage & Networking Dasar

Description: Dua building block yang hampir selalu dibutuhkan di sistem production: object storage dan jaringan privat.

Lessons:

* [ ] 6.1 Cloud Storage: bucket, storage class, IAM di level bucket/object, signed URL untuk akses sementara
* [ ] 6.2 Lifecycle policy Cloud Storage: auto-delete/auto-archive object lama untuk cost control
* [ ] 6.3 VPC dasar GCP: network, subnet, firewall rule (default-deny mindset)
* [ ] 6.4 Serverless VPC Access & Cloud NAT — cara Cloud Run (yang secara default tanpa VPC) bisa menjangkau resource privat (Memorystore, VM internal)

Mini Project: Fitur upload file di aplikasi Modul 4 memakai Cloud Storage (bukan disk lokal container yang ephemeral), dengan signed URL untuk download sementara.

Status: Not Started

---

## Module 7 — Infrastructure as Code: Terraform di GCP

Description: Menyambungkan Terraform yang sudah dikuasai di `courses/cloud-devops-foundations` ke provider `google` — bukan belajar Terraform dari nol.

Lessons:

* [ ] 7.1 Provider `google`/`google-beta`: autentikasi (ADC vs service account key), konfigurasi project/region default
* [ ] 7.2 Menulis resource Terraform untuk stack Modul 4-6: Cloud Run service, Cloud SQL instance, Cloud Storage bucket, VPC — reuse workflow `init/plan/apply` dari `cloud-devops-foundations` Modul 3
* [ ] 7.3 State management di GCP: remote state via Cloud Storage backend, locking
* [ ] 7.4 Module Terraform reusable untuk 2 environment (dev/staging) — reuse konsep module dari `cloud-devops-foundations` Modul 4, diterapkan ke resource GCP

Mini Project: Provision ulang seluruh stack Modul 4-6 (Cloud Run + Cloud SQL + Cloud Storage + VPC) sepenuhnya lewat Terraform, dari kode yang sama untuk 2 environment.

Status: Not Started

---

## Module 8 — CI/CD ke GCP

Description: Deployment yang reproducible dan otomatis, bukan `gcloud run deploy` manual dari laptop selamanya.

Lessons:

* [ ] 8.1 Cloud Build: `cloudbuild.yaml`, build → test → push image ke Artifact Registry → deploy ke Cloud Run
* [ ] 8.2 Trigger otomatis dari repository (Cloud Build trigger on push/PR) vs GitHub Actions dengan `gcloud` auth (Workload Identity Federation, bukan service account key di secrets)
* [ ] 8.3 Cloud Deploy sekilas: delivery pipeline multi-environment (dev → staging → prod) dengan approval gate
* [ ] 8.4 Strategi rollout aman: canary/gradual traffic split di Cloud Run, rollback cepat ke revision sebelumnya

Mini Project: Pipeline CI/CD (Cloud Build atau GitHub Actions) yang otomatis build, test, dan deploy aplikasi ke Cloud Run setiap push ke branch utama, dengan kemampuan rollback terbukti.

Status: Not Started

---

## Module 9 — Observability & Menjadi GCP Expert

Description: Modul penutup — operasional harian aplikasi production di GCP, plus kerangka menuju level Professional Cloud Developer.

Lessons:

* [ ] 9.1 Cloud Logging: structured logging dari Spring Boot, log-based metric, query dasar
* [ ] 9.2 Cloud Monitoring: dashboard, alerting policy (mis. error rate, latency, memory), uptime check
* [ ] 9.3 Cloud Trace & Error Reporting sekilas — distributed tracing dan agregasi error otomatis untuk aplikasi Cloud Run
* [ ] 9.4 Kerangka menuju Professional Cloud Developer: scope sertifikasi (deploying applications, exchanging data, monitoring/troubleshooting, scaling), dan kapan cukup vs kapan perlu mendalami lebih jauh

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Spring Boot Production Deployment di GCP** — 1 aplikasi Spring Boot nyata/realistis (boleh hasil dari course lain, mis. `courses/backend-fundamentals` atau `courses/system-design`) di-deploy penuh ke GCP: (1) project & IAM dengan service account least-privilege (Modul 1-2), (2) live di Cloud Run dengan custom domain & HTTPS (Modul 4), (3) persistence Cloud SQL PostgreSQL + caching Memorystore Redis, diakses privat lewat VPC (Modul 5-6), (4) seluruh infrastruktur ter-provision via Terraform, reproducible di 2 environment (Modul 7), (5) CI/CD pipeline otomatis dari push sampai deploy (Modul 8), (6) dashboard Cloud Monitoring + alerting policy aktif (Modul 9).

Acceptance Criteria:

* Aplikasi live dan bisa diakses lewat URL/domain sungguhan dengan HTTPS valid
* Tidak ada credential/secret hardcoded di kode — 100% via Secret Manager/ADC
* Database & cache tidak exposed ke public internet (diakses hanya lewat VPC/Cloud SQL Auth Proxy)
* Seluruh infrastruktur ter-provision via Terraform (bukan manual console) dan bisa di-`destroy` bersih
* Push ke branch utama otomatis trigger build, test, dan deploy tanpa intervensi manual
* Dashboard Cloud Monitoring menampilkan metrik nyata (request rate, error rate, latency) dan minimal 1 alerting policy aktif
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, termasuk dokumentasi arsitektur singkat

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: Roadmap draft — menunggu approval user, belum dimulai (mengikuti kebijakan hemat-token repo, tidak otomatis jadi course aktif)

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Resource hierarchy GCP: Organization → Folder → Project → Resource, dan kenapa struktur ini penting untuk billing & access control
