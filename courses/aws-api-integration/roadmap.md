# Course Roadmap

## Course Information

* Course Name: AWS API Integration — dari 0 sampai Expert (Java/Spring Boot Track)
* Category: Cloud Engineering / Backend Integration
* Difficulty: Level 1 (paham AWS di level console/konsep, belum pernah integrasi via API/SDK) → Level 4 (Staff-level: bisa desain arsitektur integrasi AWS production-grade)
* Estimated Duration: 10 modules, ~3-5 lessons per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Sudah terbiasa Java & Spring Boot (sesuai fokus utama repo ini). Tidak perlu pengalaman AWS API/SDK sebelumnya — pengalaman AWS Anda selama ini di level console/general knowledge akan dipakai sebagai fondasi, bukan diulang dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban Anda:

1. **Provider**: AWS saja (bukan Alibaba Cloud maupun perbandingan multi-cloud). Kalau nanti Anda butuh Alibaba Cloud, itu jadi course terpisah — banyak konsep (IAM/RAM, SDK pattern, retry/signing) akan transfer langsung karena polanya mirip.
2. **Stack**: Java + Spring Boot, konsisten dengan `CLAUDE.md`. Pakai **AWS SDK for Java v2** (bukan v1 yang sudah maintenance mode) sepanjang course, diintegrasikan sebagai Spring bean/service layer — bukan sekadar script CLI.
3. **Urutan modul**: "Broad foundation" — auth/IAM dulu (karena semua API call ke AWS butuh ini), lalu storage (S3) → compute/serverless (Lambda) → API Gateway → messaging (SQS/SNS) → database (DynamoDB) → resiliency/observability/cost → IaC & deployment → arsitektur level staff. Ini urutan yang paling natural karena tiap modul memakai kredensial & pola error-handling dari modul sebelumnya.

Referensi riset yang dipakai menyusun roadmap ini: [roadmap.sh/aws](https://roadmap.sh/aws) (jalur Cloud Developer — SDK, API Gateway, Lambda, DynamoDB), dokumentasi resmi [AWS SDK for Java 2.x Developer Guide](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/) (credential provider chain, IAM role di EC2/ECS, supply credentials in code), AWS Well-Architected Framework, serta pola integrasi Spring Boot + AWS SDK v2 yang umum dipakai di industri (bean-based client configuration, IAM role di atas hardcoded access key).

---

# Learning Objectives

* [ ] Paham model keamanan AWS API secara mendalam: IAM (user/role/policy), request signing (SigV4), credential provider chain, dan kenapa hardcoded access key adalah anti-pattern
* [ ] Mahir integrasi **Amazon S3** dari Spring Boot: upload/download, presigned URL, multipart upload, streaming file besar
* [ ] Mahir membangun & mengintegrasikan **AWS Lambda** (Java) dari sistem Spring Boot, termasuk event-driven trigger (S3 event, dll)
* [ ] Mampu mengekspos API via **API Gateway** dengan auth yang tepat (IAM auth, Lambda authorizer, Cognito) dan menghubungkannya ke backend Spring Boot
* [ ] Mahir integrasi messaging **SQS & SNS**: producer/consumer, fan-out, dead-letter queue, idempotency — dan tahu kapan pakai ini vs Kafka (`standards/MESSAGING_STANDARDS.md`)
* [ ] Mahir integrasi **DynamoDB** dari Spring Boot pakai Enhanced Client, termasuk intuisi single-table design
* [ ] Mampu menerapkan resiliency (retry/circuit breaker), observability (CloudWatch, X-Ray), dan cost awareness pada setiap panggilan API AWS
* [ ] Mampu men-deploy sistem terintegrasi AWS pakai Infrastructure as Code (AWS CDK — Java) dan pipeline CI/CD dasar
* [ ] Mampu menyusun AWS System Design Doc dan menjelaskan trade-off arsitektur seperti Staff Engineer, merujuk AWS Well-Architected Framework
* [ ] Menyelesaikan portfolio project: backend platform Spring Boot yang terintegrasi penuh dengan AWS (auth, storage, compute, messaging, database, observability, IaC)

---

# Course Modules

## Module 1 — AWS Fundamentals untuk API Integration

Description: Menjembatani pengetahuan AWS level-console Anda ke level API/SDK. Fokus ke model mental "setiap aksi di AWS — termasuk yang biasa Anda klik di console — sebenarnya adalah API call yang di-sign dan diautentikasi", plus setup project Java yang benar.

Lessons:

* [ ] Review cepat: region/AZ, resource hierarchy AWS (akun → IAM → resource) — dari sudut pandang API, bukan console
* [ ] AWS API model: REST, endpoint per service/region, request/response shape (JSON), rate limit & throttling dasar
* [ ] AWS SDK for Java v2: setup project (Maven/Gradle), struktur module SDK (per-service jar), perbedaan dengan SDK v1
* [ ] AWS CLI sebagai alat bantu debugging (bukan tujuan utama) — verifikasi kredensial & request sebelum coding

Mini Project: CLI Java kecil (bukan Spring Boot dulu) yang memanggil STS `GetCallerIdentity` via SDK — panggilan API AWS pertama Anda, untuk memverifikasi environment & kredensial jalan dengan benar sebelum masuk ke konsep IAM yang lebih dalam di Module 2.

Status: Not Started

---

## Module 2 — IAM, Request Signing & Kredensial di Production

Description: Modul paling penting di seluruh course — hampir semua bug integrasi AWS pemula adalah masalah IAM/kredensial. Modul ini membangun fondasi security mindset sebelum menyentuh service lain.

Lessons:

* [ ] IAM deep dive: user vs role, identity-based vs resource-based policy, prinsip least privilege
* [ ] Signature Version 4 (SigV4) — cara kerja request signing di balik layar SDK (kenapa request AWS tidak bisa "asal curl")
* [ ] Credential provider chain di SDK v2: environment variable → profile → IAM role (EC2/ECS/Lambda) — dan kenapa IAM role instance/task selalu dipilih di production, bukan access key
* [ ] STS `AssumeRole` & kredensial sementara — pola cross-account/cross-service access
* [ ] Secrets Manager & Parameter Store: menyimpan config sensitif, integrasi ke Spring Boot `Environment`/`PropertySource`

Mini Project: Spring Boot service dengan bean `StsClient`/`SecretsManagerClient` yang start-up dengan IAM role (bukan access key hardcoded), fetch 1 secret dari Secrets Manager, dan expose endpoint `/whoami` yang menampilkan identity dari `GetCallerIdentity` — praktik langsung `standards/SECURITY_STANDARDS.md` & `standards/CONFIGURATION_STANDARDS.md`.

Status: Not Started

---

## Module 3 — Storage Integration: Amazon S3

Description: Service AWS yang paling sering diintegrasikan pertama kali di dunia nyata. Fokus ke pola production, bukan sekadar upload/download sederhana.

Lessons:

* [ ] Konsep S3: bucket, object key, storage class, versioning — dari sudut pandang API call yang relevan
* [ ] Operasi dasar SDK: `PutObject`, `GetObject`, `ListObjectsV2`, `DeleteObject` — streaming vs load-to-memory
* [ ] Presigned URL — upload/download langsung dari client tanpa lewat backend (pola umum untuk file besar)
* [ ] Multipart upload untuk file besar & retry per-part
* [ ] Error handling & retry S3 call mengikuti `standards/ERROR_HANDLING_STANDARDS.md` dan `standards/RESILIENCY_STANDARDS.md`

Mini Project: File storage microservice di Spring Boot — endpoint upload (streaming, bukan load semua ke memory), list, download, dan generate presigned URL untuk share sementara.

Status: Not Started

---

## Module 4 — Compute & Serverless: AWS Lambda

Description: Masuk ke compute serverless — cara AWS menjalankan kode Anda tanpa server yang dikelola sendiri, dan bagaimana ini nyambung ke sistem Spring Boot yang sudah ada.

Lessons:

* [ ] Model eksekusi Lambda: cold start, memory-to-CPU ratio, timeout, concurrency
* [ ] Menulis Lambda handler di Java, packaging (fat jar/zip), deploy dasar (console lalu CLI)
* [ ] Event source: S3 event notification, direct invoke via SDK dari Spring Boot (`LambdaClient`)
* [ ] Kapan pakai Lambda vs kapan tetap pakai Spring Boot service biasa — trade-off cold start, cost, statefulness

Mini Project: Lambda (Java) yang ter-trigger otomatis saat file baru masuk ke bucket S3 (dari Module 3), memprosesnya (mis. resize/validate), lalu Spring Boot admin service memonitor hasil prosesnya via SDK invoke/CloudWatch Logs.

Status: Not Started

---

## Module 5 — API Gateway & Mengekspos API

Description: Cara AWS mengekspos Lambda (atau backend lain) sebagai REST API publik dengan auth, throttling, dan kontrol akses yang proper.

Lessons:

* [ ] REST API vs HTTP API di API Gateway — kapan pakai yang mana
* [ ] Resource, method, integration (Lambda proxy vs custom integration), request/response mapping
* [ ] Auth di API Gateway: IAM auth (SigV4), Lambda authorizer custom, Cognito user pool
* [ ] Throttling, usage plan, API key — kontrol rate limit di level gateway

Mini Project: Expose Lambda dari Module 4 lewat API Gateway dengan Lambda authorizer custom, lalu buat Spring Boot client (RestClient) yang memanggilnya dengan retry & circuit breaker (Resilience4j) — menyambungkan ke `standards/RESILIENCY_STANDARDS.md`.

Status: Not Started

---

## Module 6 — Messaging Integration: SQS & SNS

Description: Pola komunikasi asinkron AWS — penting untuk sistem event-driven dan decoupling antar service.

Lessons:

* [ ] SQS: standard vs FIFO queue, visibility timeout, long polling, dead-letter queue (DLQ)
* [ ] SNS: pub/sub, fan-out ke banyak SQS, message filtering
* [ ] Producer/consumer pattern di Spring Boot: idempotency key, poison message handling, graceful shutdown consumer
* [ ] Perbandingan SQS/SNS vs Kafka (`standards/MESSAGING_STANDARDS.md`) — kapan pilih managed queue AWS vs self-managed Kafka

Mini Project: Sistem event-driven mini: Spring Boot publish event ke SNS → fan-out ke 2 SQS queue → 2 consumer service independen memproses pesan dengan DLQ untuk pesan gagal.

Status: Not Started

---

## Module 7 — Database Integration: DynamoDB

Description: Database NoSQL managed AWS — pola akses & desain data yang berbeda signifikan dari relational DB yang biasa Anda pakai.

Lessons:

* [ ] Konsep DynamoDB: partition key, sort key, GSI/LSI, capacity mode (on-demand vs provisioned)
* [ ] Intuisi single-table design — kenapa DynamoDB "melawan" insting normalisasi SQL Anda
* [ ] SDK integration: Enhanced Client, CRUD, `Query` vs `Scan` (dan kenapa `Scan` sering jadi anti-pattern), pagination
* [ ] Repository pattern di Spring Boot yang membungkus DynamoDB Enhanced Client, konsisten dengan `standards/DATABASE_STANDARDS.md`

Mini Project: CRUD service Spring Boot berbasis DynamoDB single-table design untuk domain nyata (mis. order tracking), termasuk query akses pattern yang efisien (bukan `Scan`).

Status: Not Started

---

## Module 8 — Resiliency, Observability & Cost Awareness

Description: Yang membedakan integrasi AWS "jalan di demo" vs "jalan di production" — semua modul sebelumnya sekarang dilengkapi lapisan production-grade.

Lessons:

* [ ] Retry/backoff & circuit breaker (Resilience4j) yang membungkus setiap AWS SDK call
* [ ] CloudWatch: structured logging, custom metrics, alarms
* [ ] AWS X-Ray: distributed tracing lintas Spring Boot → Lambda → DynamoDB/S3
* [ ] Cost visibility: tagging strategy, Cost Explorer, budget alert — kenapa cost awareness bagian dari tanggung jawab engineer, bukan cuma finance

Mini Project: Tambahkan CloudWatch structured logging + X-Ray tracing + Resilience4j circuit breaker ke service S3 (Module 3) & DynamoDB (Module 7), lalu set budget alarm untuk resource yang dipakai course ini.

Status: Not Started

---

## Module 9 — Infrastructure as Code & Deployment

Description: Cara mem-provision & men-deploy semua yang sudah dibangun secara reproducible, bukan klik manual di console selamanya.

Lessons:

* [ ] IaC landscape: CloudFormation vs Terraform vs AWS CDK — kenapa CDK (Java) jadi pilihan course ini (native ke stack Anda)
* [ ] AWS CDK (Java): mendefinisikan S3 bucket, DynamoDB table, IAM role sebagai kode
* [ ] Deploy Spring Boot ke AWS: ECS Fargate vs Elastic Beanstalk vs EC2 — trade-off operational overhead
* [ ] CI/CD dasar untuk deploy AWS (`standards/CI_CD_STANDARDS.md`), container registry (ECR), Docker (`standards/DOCKER_STANDARDS.md`)

Mini Project: Provision S3 bucket + DynamoDB table + IAM role via AWS CDK (Java), lalu deploy service Spring Boot dari Module 7-8 sebagai ECS Fargate task lewat pipeline CI/CD dasar.

Status: Not Started

---

## Module 10 — Menjadi AWS Integration Engineer Level Staff

Description: Level di mana Anda bisa memimpin keputusan arsitektur integrasi AWS, bukan cuma mengimplementasikan service satu-satu.

Lessons:

* [ ] AWS Well-Architected Framework (6 pillar) diterapkan ke sistem yang sudah dibangun sepanjang course ini
* [ ] Studi kasus: bagaimana Netflix, Stripe, Airbnb-kelas perusahaan mendesain integrasi AWS skala besar (multi-region, disaster recovery, event-driven at scale)
* [ ] Menyusun AWS System Design Doc: trade-off, cost projection, failure mode, blast radius
* [ ] Interview prep: pertanyaan system design & API integration AWS yang umum ditanyakan (Solutions Architect / Backend Cloud Engineer track)

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Full AWS-Integrated Backend Platform** — satu platform Spring Boot yang menggabungkan seluruh course: (1) auth via IAM role + Secrets Manager, (2) file storage via S3 (upload/presigned URL), (3) proses async via Lambda ter-trigger event S3, (4) API publik lewat API Gateway dengan authorizer, (5) event-driven workflow via SNS/SQS dengan DLQ, (6) persistence via DynamoDB single-table design, (7) observability penuh (CloudWatch + X-Ray) & circuit breaker, (8) provisioning via AWS CDK, deploy ke ECS Fargate lewat CI/CD, (9) 1 halaman "AWS System Design Doc".

Acceptance Criteria:

* Tidak ada credential hardcoded di mana pun — 100% via IAM role/Secrets Manager
* Semua AWS SDK call dibungkus retry/circuit breaker dan ter-trace di X-Ray end-to-end
* Event-driven workflow (SNS/SQS) menangani pesan gagal via DLQ tanpa kehilangan data
* Seluruh infrastruktur ter-provision via CDK (bukan manual console) dan bisa di-`cdk destroy` bersih
* Budget alarm aktif dan cost per komponen bisa dijelaskan
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, lengkap dengan System Design Doc

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Review cepat region/AZ & resource hierarchy AWS dari sudut pandang API
