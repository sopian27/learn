# Course Roadmap

## Course Information

* Course Name: Microsoft Azure — dari 0 sampai Expert
* Category: Cloud Engineering / Backend Integration (Azure Track)
* Difficulty: Level 1 (paham cloud secara konsep umum, belum pernah deploy apa pun ke Azure) → Level 4 (Expert, setara kesiapan AZ-204: mampu deploy, provision via IaC, dan mengoperasikan sistem production di Azure secara mandiri tanpa panduan)
* Estimated Duration: 10 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Sudah terbiasa Java & Spring Boot (fokus utama repo ini) — course ini **tidak** mengulang dasar Java/Spring Boot, melainkan men-deploy stack yang sudah dikuasai (Spring Boot + PostgreSQL + Redis) ke Azure. Tidak wajib, tapi akan lebih cepat nyambung kalau sudah menyelesaikan `courses/vps` (konsep deployment single-server) dan/atau `courses/cloud-devops-foundations` (konsep IaaS/PaaS/SaaS, Terraform) — keduanya memberi kerangka konseptual yang dipakai ulang di sini, bukan diulang.

---

# Catatan Scope (baca dulu sebelum approve)

1. **Beda dari `courses/cloud-devops-foundations`.** Course itu adalah lapisan *konsep* provider-agnostic — IaaS/PaaS/SaaS, perbandingan AWS/Azure/GCP hanya sebatas konseptual (Modul 1.3), Terraform sebagai IaC provider-agnostic, dan SRE/observability sebagai disiplin umum. Course itu tidak pernah benar-benar membuka portal/CLI Azure. Course ini kebalikannya: 100% hands-on di **satu provider spesifik, Azure**, dari subscription pertama sampai aplikasi live dengan monitoring — memakai kerangka konsep dari `cloud-devops-foundations` sebagai fondasi (mis. IaaS/PaaS di Modul 1, prinsip IaC di Modul 8), tapi tidak mengulang penjelasan konsepnya.
2. **Sibling struktural dari `courses/aws-api-integration`, bukan duplikat.** Keduanya punya pola yang sama — course hands-on untuk *satu* cloud provider spesifik, dengan modul identity/auth dulu, lalu compute, storage, data, networking, IaC, CI/CD, observability, ditutup capstone project. Yang beda murni providernya: AWS course pakai AWS SDK for Java + IAM + Lambda + S3 + DynamoDB + SQS/SNS + API Gateway + CDK; course ini pakai Azure SDK/CLI + Microsoft Entra ID + App Service/Functions + Blob Storage + Azure Database for PostgreSQL + Bicep. Dua track paralel ini relevan karena klien freelance bisa memakai cloud provider mana saja — menguasai keduanya memperluas pasar, bukan mengulang pekerjaan yang sama.
3. **Beda dari `courses/kubernetes`.** Azure Kubernetes Service (AKS) hanya disinggung sebagai salah satu opsi compute di Modul 3 (kapan AKS masuk akal dibanding App Service/Functions/Container Apps) — orkestrasi container mendalam (Docker → K8s → OpenShift) tetap sepenuhnya di `courses/kubernetes`, tidak diulang di sini.
4. **Bicep (Modul 8) sengaja dikontraskan, bukan menggantikan, Terraform yang sudah diajarkan di `courses/cloud-devops-foundations` Modul 3-4.** Prinsip IaC (declarative vs imperative, state, workflow tim) tidak diulang — Modul 8 course ini fokus ke sintaks & tooling Azure-native (ARM template di baliknya, `az deployment`, integrasi first-party dengan Azure Resource Manager) dan kapan Bicep lebih masuk akal dibanding Terraform untuk workload Azure-only.
5. **AZ-204 (Developer Associate), bukan AZ-104 (Administrator Associate), jadi kerangka acuan sertifikasi.** AZ-104 berfokus pada operasional/administrasi infrastruktur (ops-focused) — di luar peran backend developer. AZ-204 (compute, storage, security/identity, monitoring, integrasi service Azure) jauh lebih relevan untuk peran ini, jadi dipakai sebagai kerangka skills outline course ini; AZ-900 dipakai untuk fondasi konsep di Modul 1.
6. **Track paralel/independen dari Master Goal**, sama seperti `courses/vps`, `courses/aws-api-integration`, dan `courses/cloud-devops-foundations` — bukan prasyarat wajib "World-Class Backend Developer", tapi sangat applied untuk deploy portofolio nyata ke cloud provider yang banyak dipakai perusahaan enterprise.

Referensi riset yang dipakai menyusun roadmap ini (semua Microsoft Learn resmi, prioritas sesuai instruksi riset):

* [Study guide for Exam AZ-900: Microsoft Azure Fundamentals](https://learn.microsoft.com/en-us/credentials/certifications/resources/study-guides/az-900) — kerangka Modul 1 (model layanan cloud, resource hierarchy, region)
* [Study guide for Exam AZ-204: Developing Solutions for Microsoft Azure](https://learn.microsoft.com/en-us/credentials/certifications/resources/study-guides/az-204) — kerangka skills outline course secara keseluruhan (compute 25-30%, storage 15-20%, security/identity 15-20%, monitoring 10-15%, integrasi service 20-25%)
* [Azure Architecture Center](https://learn.microsoft.com/en-us/azure/architecture/) dan [Azure Well-Architected Framework](https://learn.microsoft.com/en-us/azure/well-architected/) — dipakai di Modul 10 untuk trade-off arsitektur level Expert
* [Choose an Azure compute service — decision tree](https://learn.microsoft.com/en-us/azure/architecture/guide/technology-choices/compute-decision-tree) — kerangka Modul 3 (App Service vs Functions vs Container Apps vs AKS)
* [Deploy a Spring Boot Web App to Linux on Azure App Service](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/deploy-spring-boot-java-app-on-linux) — kerangka hands-on Modul 4
* [Microsoft Entra RBAC documentation](https://learn.microsoft.com/en-us/entra/identity/role-based-access-control/) — kerangka Modul 2
* [What is Bicep? — Azure Resource Manager](https://learn.microsoft.com/en-us/azure/azure-resource-manager/bicep/overview) — kerangka Modul 8
* [Deploy to Azure App Service by using GitHub Actions](https://learn.microsoft.com/en-us/azure/app-service/deploy-github-actions) — kerangka Modul 9
* [Application Insights overview — Azure Monitor](https://learn.microsoft.com/en-us/azure/azure-monitor/app/app-insights-overview) — kerangka Modul 10

---

# Learning Objectives

* [ ] Memahami model layanan Azure secara konkret: subscription, resource group, region, resource hierarchy, serta Azure Portal/CLI/Cloud Shell (fondasi AZ-900)
* [ ] Mahir identity & access di Azure: Microsoft Entra ID (user, group, app registration), Azure RBAC, managed identity — dan kenapa managed identity mengalahkan hardcoded credential
* [ ] Mampu memilih platform compute yang tepat (App Service vs Azure Functions vs Container Apps, AKS secukupnya) berdasarkan trade-off, bukan asal ikut tutorial
* [ ] Mahir men-deploy aplikasi Spring Boot production ke **Azure App Service** (Linux, JAR deployment, deployment slot, konfigurasi environment)
* [ ] Mampu mengintegrasikan **Azure Database for PostgreSQL Flexible Server** dan **Azure Cache for Redis** dari Spring Boot, reuse stack yang sudah dikuasai
* [ ] Mahir **Azure Blob Storage** untuk kebutuhan file storage aplikasi (container, access tier, SAS token, upload/download dari Spring Boot)
* [ ] Memahami networking dasar Azure: VNet, subnet, Network Security Group (NSG), private endpoint — cukup untuk mengamankan App Service & database
* [ ] Mahir Infrastructure as Code Azure-native dengan **Bicep**: sintaks, parameter/module, `az deployment`, dan kapan dipilih dibanding Terraform
* [ ] Mampu membangun pipeline CI/CD ke Azure (GitHub Actions) dan memantau aplikasi production dengan **Azure Monitor / Application Insights**
* [ ] Menyelesaikan proyek portofolio: aplikasi Spring Boot live di Azure App Service dengan database, CI/CD, dan monitoring penuh

---

# Course Modules

## Module 1 — Fondasi Azure & Model Layanan Cloud

Description: Sebelum menyentuh service apa pun, pahami dulu bagaimana Azure menyusun dunia (subscription → resource group → resource) dan cara berinteraksi dengannya — lingkup AZ-900.

Lessons:

* [ ] 1.1 Resource hierarchy Azure: Management Group → Subscription → Resource Group → Resource, dan kenapa penataan ini penting untuk billing & access control
* [ ] 1.2 Region, Availability Zone, dan pertimbangan memilih lokasi resource (latency, kepatuhan data, harga)
* [ ] 1.3 Model layanan IaaS/PaaS/SaaS pada konteks Azure secara singkat (rujuk `courses/cloud-devops-foundations` Modul 1-2 untuk perbandingan konsep AWS/Azure/GCP mendalam, tidak diulang di sini)
* [ ] 1.4 Tooling: Azure Portal, Azure CLI (`az`), Azure Cloud Shell, dan kapan pakai yang mana

Mini Project: Buat 1 subscription (free tier/pay-as-you-go) dan 2 resource group untuk 2 environment (dev/prod), buat 1 resource sederhana (mis. Storage Account) lewat Portal lalu lewat `az cli`, bandingkan hasilnya.

Status: Not Started

---

## Module 2 — Identity & Access Management: Microsoft Entra ID & RBAC

Description: Modul paling krusial — hampir semua kesalahan konfigurasi Azure pemula berakar dari identity/access yang tidak tepat, sama seperti IAM di AWS.

Lessons:

* [ ] 2.1 Microsoft Entra ID (dulu Azure AD): tenant, user, group, app registration — model mental identity Azure
* [ ] 2.2 Azure RBAC: role assignment (Owner/Contributor/Reader/custom role), scope (management group/subscription/resource group/resource), principle of least privilege
* [ ] 2.3 Service principal vs Managed Identity — kenapa Managed Identity (system-assigned/user-assigned) adalah pilihan production, bukan credential yang di-hardcode
* [ ] 2.4 Azure Key Vault: menyimpan secret/connection string, integrasi ke aplikasi (referensi ke Modul 4)

Mini Project: Buat app registration + custom RBAC role dengan scope terbatas (bukan Contributor penuh) untuk 1 resource group, lalu buat 1 Key Vault dan simpan 1 secret contoh, akses via Azure CLI memakai identity tersebut (bukan akun pribadi).

Status: Not Started

---

## Module 3 — Compute Options di Azure: Memilih Platform yang Tepat

Description: Sebelum deploy, pahami dulu peta besar opsi compute Azure dan kapan masing-masing tepat dipilih — supaya Modul 4 dan seterusnya adalah pilihan sadar, bukan default.

Lessons:

* [ ] 3.1 Azure App Service: PaaS untuk web app, model App Service Plan, Linux vs Windows worker
* [ ] 3.2 Azure Functions: serverless, trigger (HTTP/Timer/Queue), consumption vs premium plan
* [ ] 3.3 Azure Container Apps: microservices serverless berbasis container, kapan lebih cocok dibanding App Service
* [ ] 3.4 Azure Kubernetes Service (AKS) — hanya sekilas kapan orkestrasi penuh dibutuhkan (rujuk `courses/kubernetes` untuk pendalaman, tidak diulang di sini)

Mini Project: Tulis decision memo singkat (setengah halaman) yang membandingkan App Service vs Functions vs Container Apps untuk 3 skenario aplikasi berbeda (monolith Spring Boot, event-driven image processor, sistem microservices kecil).

Status: Not Started

---

## Module 4 — Deploy Spring Boot ke Azure App Service (Hands-on)

Description: Menyatukan Modul 1-3 untuk men-deploy aplikasi Spring Boot nyata ke Azure App Service dengan cara production-grade.

Lessons:

* [ ] 4.1 Azure App Service untuk Java: App Service Plan (tier/sizing), runtime stack Java/Linux, deploy via `az webapp` / Maven plugin
* [ ] 4.2 Konfigurasi environment: App Settings vs Connection Strings, memisahkan secret dari kode (integrasi Key Vault dari Modul 2)
* [ ] 4.3 Deployment slot: staging slot, slot swap untuk zero-downtime deployment
* [ ] 4.4 Startup command, health check endpoint (Spring Boot Actuator), dan diagnostik dasar App Service (Log Stream, Kudu)

Mini Project: Deploy aplikasi Spring Boot sederhana ke App Service Linux dengan deployment slot staging→production, health check aktif via Actuator, dan environment config lewat App Settings (bukan hardcoded).

Status: Not Started

---

## Module 5 — Data Services: Azure Database for PostgreSQL & Azure Cache for Redis

Description: Menghubungkan aplikasi ke database managed Azure — reuse penuh stack PostgreSQL/Redis yang sudah dikuasai dari course lain di repo ini.

Lessons:

* [ ] 5.1 Azure Database for PostgreSQL Flexible Server: provisioning, compute tier, firewall rule/VNet integration, backup otomatis
* [ ] 5.2 Koneksi Spring Boot ke Azure PostgreSQL: connection string, SSL enforcement, connection pooling (HikariCP) yang aman untuk PaaS database
* [ ] 5.3 Azure Cache for Redis: tier (Basic/Standard/Premium), provisioning, koneksi dari Spring Boot (Spring Data Redis)
* [ ] 5.4 Migrasi skema database di Azure (Flyway, konsisten dengan konvensi repo ini) dan strategi backup/restore dasar

Mini Project: Aplikasi dari Modul 4 terhubung ke Azure Database for PostgreSQL (dengan Flyway migration) dan Azure Cache for Redis untuk session/cache, koneksi terenkripsi (SSL), credential lewat Key Vault.

Status: Not Started

---

## Module 6 — Azure Blob Storage

Description: Layanan storage yang paling sering dipakai aplikasi backend untuk file — pola production, bukan sekadar upload sederhana.

Lessons:

* [ ] 6.1 Konsep Blob Storage: Storage Account, container, blob types (block/append/page), access tier (Hot/Cool/Archive)
* [ ] 6.2 SDK Azure Storage untuk Java: upload/download/list/delete blob dari Spring Boot service
* [ ] 6.3 SAS token (Shared Access Signature): akses terbatas waktu/permission langsung dari client tanpa lewat backend
* [ ] 6.4 Lifecycle management policy dan pertimbangan biaya penyimpanan jangka panjang

Mini Project: File storage service di Spring Boot — endpoint upload (streaming), list, download, dan generate SAS URL untuk share sementara, mirip pola presigned URL S3 tapi versi Azure.

Status: Not Started

---

## Module 7 — Networking Dasar: VNet, NSG & Private Endpoint

Description: Mengamankan komunikasi antar resource Azure — cukup untuk operasional production yang aman, bukan networking engineer penuh.

Lessons:

* [ ] 7.1 Virtual Network (VNet) & subnet: model mental jaringan privat Azure
* [ ] 7.2 Network Security Group (NSG): allow/deny rule berbasis port & source, default-deny untuk resource sensitif
* [ ] 7.3 VNet Integration untuk App Service: membuat App Service bisa mengakses resource privat (database) tanpa lewat internet publik
* [ ] 7.4 Private Endpoint untuk Azure Database for PostgreSQL/Redis: menutup akses publik, koneksi hanya lewat jaringan privat

Mini Project: Konfigurasi App Service dari Modul 4 dengan VNet Integration, database dari Modul 5 diakses lewat Private Endpoint (firewall publik dimatikan), NSG membatasi akses hanya dari subnet App Service.

Status: Not Started

---

## Module 8 — Infrastructure as Code dengan Bicep

Description: Provisioning semua yang sudah dibangun manual di Modul 1-7 secara reproducible lewat kode, native Azure — dikontraskan sengaja dengan Terraform yang sudah dipelajari di `courses/cloud-devops-foundations`.

Lessons:

* [ ] 8.1 Apa itu Bicep: DSL declarative yang transpile ke ARM template, kenapa lebih ringkas dibanding JSON ARM murni
* [ ] 8.2 Sintaks inti: resource, parameter, variable, output; deploy dengan `az deployment group create`
* [ ] 8.3 Module Bicep untuk komponen reusable (App Service + Plan, PostgreSQL, Storage Account sebagai 1 module)
* [ ] 8.4 Bicep vs Terraform — kapan Bicep lebih masuk akal (Azure-only, first-party support cepat untuk resource baru) vs kapan Terraform tetap dipilih (multi-cloud, sudah standar tim); tidak mengulang workflow tim/state management yang sudah dibahas `cloud-devops-foundations` Modul 4

Mini Project: Tulis Bicep template yang men-provision ulang stack Modul 4-7 (App Service Plan + App Service + PostgreSQL Flexible Server + Storage Account + VNet/NSG dasar) dari nol lewat `az deployment`, bukan klik manual di Portal.

Status: Not Started

---

## Module 9 — CI/CD ke Azure dengan GitHub Actions

Description: Dari deploy manual ke pipeline otomatis — build, test, dan deploy setiap push tervalidasi sebelum mendarat di production.

Lessons:

* [ ] 9.1 GitHub Actions workflow dasar untuk Java/Maven: build, run test, package JAR
* [ ] 9.2 Autentikasi ke Azure dari GitHub Actions: OpenID Connect (federated credential) sebagai pilihan yang direkomendasikan Microsoft, dibanding publish profile/service principal secret
* [ ] 9.3 Deploy job ke App Service dari workflow, termasuk deploy ke staging slot lalu slot swap otomatis setelah smoke test lulus
* [ ] 9.4 Deploy infrastruktur (Bicep dari Modul 8) sebagai bagian dari pipeline yang sama — infra dan aplikasi versioned bersama

Mini Project: Pipeline GitHub Actions end-to-end: push ke `main` → build & test → deploy Bicep (jika ada perubahan infra) → deploy JAR ke staging slot → smoke test health check → slot swap ke production.

Status: Not Started

---

## Module 10 — Monitoring & Trade-off Arsitektur Level Expert

Description: Modul penutup — memastikan aplikasi yang sudah live bisa dipercaya dan dioperasikan, plus cara berpikir trade-off arsitektur level Expert/Staff di Azure.

Lessons:

* [ ] 10.1 Azure Monitor: konsep metric, log, alert rule pada resource Azure (App Service, PostgreSQL, Storage)
* [ ] 10.2 Application Insights: instrumentasi Spring Boot (OpenTelemetry), distributed tracing, dependency tracking, dashboard performa
* [ ] 10.3 Alert & action group: notifikasi saat threshold dilanggar (response time, error rate, resource habis)
* [ ] 10.4 Azure Well-Architected Framework (5 pilar) diterapkan ke sistem yang sudah dibangun sepanjang course — trade-off cost/reliability/security untuk keputusan arsitektur level Expert

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Full Azure-Deployed Spring Boot Platform** — satu aplikasi Spring Boot nyata/realistis (boleh hasil dari course lain, mis. `courses/backend-fundamentals` atau `courses/system-design`) yang di-deploy penuh ke Azure: (1) identity & access lewat Microsoft Entra ID + Managed Identity + Key Vault (Modul 2), (2) live di Azure App Service dengan deployment slot (Modul 4), (3) terhubung ke Azure Database for PostgreSQL + Azure Cache for Redis lewat Private Endpoint (Modul 5, 7), (4) file storage lewat Azure Blob Storage (Modul 6), (5) seluruh infrastruktur ter-provision via Bicep (Modul 8), (6) CI/CD penuh via GitHub Actions dengan OIDC (Modul 9), (7) monitoring production via Azure Monitor + Application Insights (Modul 10).

Acceptance Criteria:

* Tidak ada credential/connection string hardcoded di mana pun — 100% via Managed Identity/Key Vault
* Aplikasi live dan bisa diakses lewat App Service (custom domain jika memungkinkan), health check via Actuator hijau
* Database & Redis hanya bisa diakses lewat jaringan privat (Private Endpoint), tidak ada akses publik terbuka
* Seluruh infrastruktur ter-provision via Bicep (bukan manual Portal) dan bisa di-deploy ulang dari nol ke resource group baru
* Pipeline CI/CD berhasil deploy otomatis dari push ke `main` sampai slot swap ke production, termasuk smoke test sebelum swap
* Application Insights menampilkan trace/dependency real dari trafik aplikasi, minimal 1 alert rule aktif (mis. error rate/response time)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, termasuk dokumentasi arsitektur singkat (diagram + trade-off yang diambil)

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Roadmap draft — menunggu approval user, belum dimulai (mengikuti kebijakan hemat-token repo, tidak otomatis jadi course aktif)

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Resource hierarchy Azure: Management Group → Subscription → Resource Group → Resource
