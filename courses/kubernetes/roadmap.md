# Course Roadmap

## Course Information

* Course Name: Docker, Kubernetes & OpenShift — Developer Track menuju CKAD & OpenShift Certified Specialist
* Category: DevOps / Cloud Infrastructure / Container Orchestration
* Difficulty: Level 0 (dasar) → Level 4 (Expert, siap sertifikasi & production)
* Estimated Duration: 8 modul, 4-6 lesson/modul, ~20-30 menit/lesson. Dengan pace santai (<5 jam/minggu) ≈ 20-26 minggu.
* Prerequisites: CLI Linux dasar, konsep networking dasar (IP, port, DNS), YAML dasar. Familiar dengan Java/Spring Boot (dipakai sebagai aplikasi contoh di setiap mini project, sesuai root `CLAUDE.md`).

---

# Catatan Scope (baca dulu sebelum mulai)

Berdasarkan jawaban kalibrasi Anda:

1. **Tujuan**: **Kebutuhan kerja sekarang** + **sertifikasi resmi**. Karena itu setiap modul K8s disusun mengikuti objektif resmi **CKAD (Certified Kubernetes Application Developer)** — bukan CKA, karena CKA lebih berat ke cluster administration (etcd backup, HA control plane, dsb) yang di luar scope "developer yang deploy sendiri". Modul 8 punya sesi khusus persiapan CKAD + overview Red Hat **EX280/DO280** (OpenShift Administration).
2. **Fokus Role**: **Developer yang deploy sendiri**. Topik cluster-admin murni (bootstrap control plane, etcd, multi-master HA) hanya dibahas sebagai teori/konsep di Modul 3.1 secukupnya untuk paham arsitektur & debugging — tidak hands-on operasional penuh. Semua mini project berbasis aplikasi Spring Boot nyata, konsisten dengan `standards/JAVA_STANDARDS.md` dan `standards/SPRING_BOOT_STANDARDS.md`.
3. **OpenShift**: **Sudah dipakai di kerjaan sekarang**. Karena itu Modul 4 (OpenShift Essentials) dimajukan — langsung setelah dasar Kubernetes (Modul 3), bukan di akhir course seperti pola default. Modul 6 (Security) juga mencakup SCC (Security Context Constraints) yang sangat sering jadi sumber error "image jalan di Minikube tapi ditolak di OpenShift".
4. **Pace**: **Santai, <5 jam/minggu**. Lesson dibuat singkat (20-30 menit), tiap modul 4-6 lesson saja, dan mini project dipecah kecil supaya bisa selesai dalam 1-2 sesi belajar.

Referensi struktur: [Kubernetes.io Documentation](https://kubernetes.io/docs/), [CKAD Curriculum (CNCF)](https://github.com/cncf/curriculum), [Red Hat OpenShift Documentation](https://docs.openshift.com/), `standards/KUBERNETES_STANDARDS.md` dan `standards/DOCKER_STANDARDS.md` di repo ini.

---

# Learning Objectives

* [ ] Memahami containerization vs virtualization dan kapan memakainya
* [ ] Menguasai Docker: image, container, networking, volume, multi-stage build, image security
* [ ] Menguasai Docker Compose untuk local multi-container development
* [ ] Memahami arsitektur Kubernetes secukupnya untuk debugging (control plane, node, kubelet)
* [ ] Menguasai core K8s objects sesuai CKAD: Pod, Deployment, Service, ConfigMap, Secret, Ingress
* [ ] Menguasai OpenShift: Route, BuildConfig/S2I, ImageStream, Project, DeploymentConfig, SCC
* [ ] Menguasai storage K8s: PV, PVC, StorageClass, StatefulSet dasar
* [ ] Menguasai probe, resource request/limit, Jobs/CronJobs, Helm dasar
* [ ] Menguasai security: RBAC, NetworkPolicy, SecurityContext, SCC OpenShift
* [ ] Mahir membaca logs/events dan debugging masalah production (CrashLoopBackOff, OOMKilled, ImagePullBackOff)
* [ ] Menguasai HPA, konsep Operator/CRD, dan GitOps (ArgoCD/OpenShift Pipelines)
* [ ] Siap ujian CKAD dan familiar dengan objektif Red Hat EX280/DO280
* [ ] Mampu mendesain & mendeploy aplikasi Spring Boot production-grade ke OpenShift di tempat kerja

---

# Course Modules

## Module 1 — Docker Fundamentals

Description: Containerization dari nol — image, container, Dockerfile, layer caching, networking, volume, keamanan dasar.

Lessons:

* [ ] 1.1 Container vs VM, arsitektur Docker Engine
* [ ] 1.2 Docker CLI dasar & lifecycle container (run, ps, logs, exec, stop, rm)
* [ ] 1.3 Dockerfile & image layer, build cache
* [ ] 1.4 Docker networking (bridge, host, custom network) & volume (data persistence)
* [ ] 1.5 Multi-stage build, image size optimization & keamanan dasar (non-root user, scanning)

Mini Project: Dockerize aplikasi Spring Boot + Postgres, image kecil dan aman.

Status: In Progress — Lesson 1.1

---

## Module 2 — Docker Compose & Registry

Description: Multi-container di local dan alur kerja registry yang dipakai sehari-hari di kerjaan.

Lessons:

* [ ] 2.1 Docker Compose dasar (services, networks, volumes)
* [ ] 2.2 Environment/secrets, health check & restart policy di Compose
* [ ] 2.3 Registry: tagging strategy, push/pull, private registry

Mini Project: Stack Spring Boot + Postgres + Redis + Kafka pakai Compose, siap migrasi ke K8s.

Status: Not started

---

## Module 3 — Kubernetes Fundamentals (CKAD-aligned)

Description: Arsitektur K8s secukupnya + objek inti yang jadi fondasi CKAD.

Lessons:

* [ ] 3.1 Arsitektur cluster ringkas (control plane, node) — untuk paham & debugging, bukan operasional
* [ ] 3.2 kubectl & cara berpikir declarative (apply, get, describe, explain)
* [ ] 3.3 Pod — unit terkecil, multi-container pod, init container
* [ ] 3.4 Deployment & ReplicaSet — rolling update, rollback
* [ ] 3.5 Service — ClusterIP, NodePort, LoadBalancer, DNS
* [ ] 3.6 ConfigMap, Secret, Namespace & label/selector

Mini Project: Deploy Spring Boot app ke Minikube/Kind dengan Deployment + Service + ConfigMap.

Status: Not started

---

## Module 4 — OpenShift Essentials

Description: Apa yang beda dari vanilla K8s dan langsung relevan untuk kerjaan Anda sekarang.

Lessons:

* [ ] 4.1 OpenShift vs vanilla Kubernetes — filosofi opinionated platform
* [ ] 4.2 oc CLI & Project (namespace dengan quota bawaan)
* [ ] 4.3 BuildConfig & Source-to-Image (S2I) — build image langsung dari source
* [ ] 4.4 ImageStream & internal registry
* [ ] 4.5 DeploymentConfig vs Deployment, Route vs Ingress

Mini Project: Build & deploy langsung dari Git repo pakai S2I, expose via Route.

Status: Not started

---

## Module 5 — Kubernetes Intermediate (CKAD lanjutan)

Description: Topik CKAD yang membuat deployment tahan banting di production.

Lessons:

* [ ] 5.1 Probe: liveness, readiness, startup
* [ ] 5.2 Resource request/limit & QoS class
* [ ] 5.3 Storage: PersistentVolume, PersistentVolumeClaim, StorageClass, StatefulSet dasar
* [ ] 5.4 Jobs & CronJobs
* [ ] 5.5 Helm dasar — templating & packaging

Mini Project: Helm chart sendiri untuk Spring Boot app dengan probe & resource limit yang benar.

Status: Not started

---

## Module 6 — Security & Config Management

Description: Yang paling sering bikin "jalan di laptop, ditolak di OpenShift" — dan yang dicek CKAD/EX280.

Lessons:

* [ ] 6.1 RBAC — Role, RoleBinding, ServiceAccount
* [ ] 6.2 SecurityContext & OpenShift SCC (kenapa banyak image ditolak OpenShift)
* [ ] 6.3 NetworkPolicy dasar
* [ ] 6.4 Secret management production (hindari hardcode, overview external secret/vault)

Mini Project: Perbaiki Dockerfile & manifest yang ditolak SCC OpenShift jadi compliant.

Status: Not started

---

## Module 7 — Observability & Troubleshooting

Description: Skill yang paling sering dipakai harian di kerjaan — baca log, diagnosa, perbaiki.

Lessons:

* [ ] 7.1 Membaca logs & events (`kubectl/oc describe`, `logs -f`)
* [ ] 7.2 Debugging CrashLoopBackOff, OOMKilled, ImagePullBackOff, Pending pod
* [ ] 7.3 Metrics dasar (Prometheus/Grafana) & structured logging
* [ ] 7.4 Rolling update gagal & strategi rollback di production

Mini Project: Studi kasus — diberi manifest/pod rusak, diagnosa dan perbaiki root cause-nya.

Status: Not started

---

## Module 8 — Scaling, GitOps & Menuju Expert

Description: Topik yang membedakan level medior dari expert, plus persiapan sertifikasi.

Lessons:

* [ ] 8.1 Horizontal Pod Autoscaler (HPA) & overview VPA
* [ ] 8.2 Konsep CRD & Operator pattern (reconcile loop) — cukup untuk paham OperatorHub OpenShift
* [ ] 8.3 GitOps dengan ArgoCD & OpenShift Pipelines (Tekton) overview
* [ ] 8.4 Persiapan ujian: CKAD exam tips & overview objektif Red Hat EX280/DO280

Portfolio Project: Setup HPA teruji dengan load test + pipeline deploy otomatis (GitOps) untuk app dari Modul 4.

Status: Not started

---

# Enterprise Project

Description: Deploy sistem microservices Spring Boot (order service, payment service, notification service) dengan Kafka sebagai event bus, Postgres per service, Redis cache — ke **OpenShift** (merepresentasikan skenario kerjaan Anda sekarang), lengkap dengan BuildConfig/S2I, Route, SCC yang benar, RBAC least-privilege, HPA, observability, dan GitOps deployment, sesuai `standards/KUBERNETES_STANDARDS.md` & `standards/DOCKER_STANDARDS.md`.

Acceptance Criteria:

* [ ] Semua service ter-containerize dengan image aman (non-root, minimal base, scanned)
* [ ] Build via S2I atau pipeline CI/CD, deploy otomatis via GitOps (ArgoCD/OpenShift Pipelines)
* [ ] HPA aktif dan teruji di bawah load test
* [ ] RBAC, NetworkPolicy, SCC dikonfigurasi sesuai least privilege
* [ ] Observability lengkap: metrics, logs, alerting
* [ ] Mampu menjelaskan setiap keputusan desain & trade-off (siap interview Senior/Staff)
* [ ] Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not started

---

# Overall Progress

Module Completion: 0/8

Overall Completion: 0%

Current Module: Module 1 — Docker Fundamentals

Current Lesson: 1.1 Container vs VM, arsitektur Docker Engine

Next Lesson: 1.2 Docker CLI dasar & lifecycle container
