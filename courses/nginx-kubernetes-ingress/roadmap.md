# Course Roadmap

## Course Information

* Course Name: NGINX & Kubernetes Ingress — dari 0 sampai Expert
* Domain: software-engineering
* Category: Networking / DevOps / Cloud Infrastructure
* Difficulty: Level 0 (dasar networking) → Level 4 (Expert, production-grade ingress architecture)
* Estimated Duration: 10 modul, 4-5 lesson/modul, ~25-30 menit/lesson. Pace santai (<5 jam/minggu) ≈ 20-24 minggu.
* Prerequisites: Docker dasar (image, container, run). Konsep Kubernetes dasar (Pod, Deployment, Service) — Modul 3 course ini menyediakan ringkasan review, tapi bukan pengganti fondasi K8s penuh. CLI Linux dasar.

---

# Catatan Scope (baca dulu sebelum mulai)

1. **Kenapa course terpisah, bukan bagian dari `courses/kubernetes`**: Course `kubernetes` yang sudah ada (`courses/kubernetes/roadmap.md`, Module 4.5) hanya menyentuh Ingress lewat satu bullet perbandingan "DeploymentConfig vs Deployment, Route vs Ingress" — cukup untuk paham konsep, tidak cukup untuk mahir konfigurasi NGINX, routing lanjut, TLS/cert-manager, security (WAF/rate limiting), observability, dan troubleshooting produksi. Course ini adalah pendalaman vertikal khusus layer NGINX + Ingress yang tidak dicakup di mana pun di repo ini.
2. **NGINX diajarkan berdiri sendiri dulu** (Modul 1-2) sebelum masuk konteks Kubernetes (Modul 3 dst.) — supaya paham *reverse proxy* dan konfigurasi NGINX murni (yang juga berguna di luar konteks K8s: VPS, bare-metal) sebelum melihatnya dibungkus jadi Ingress Controller.
3. **Ingress Controller yang dipakai sebagai referensi utama**: `ingress-nginx` (proyek resmi Kubernetes SIG-Network, `kubernetes.github.io/ingress-nginx`) — paling umum dipakai industri dan namanya langsung selaras dengan judul course. Alternatif (Traefik, HAProxy, cloud-native ALB Ingress Controller, F5 NGINX Ingress Controller versi komersial) dibahas ringkas di Modul 10 sebagai perbandingan, bukan dipraktikkan mendalam.
4. **Gateway API** (penerus resmi Ingress API di Kubernetes upstream) dibahas di Modul 10 sebagai overview & jalur migrasi — course ini tetap fokus ke Ingress karena itu yang paling banyak dipakai di production saat ini, tapi expert wajib tahu ke mana arah standar bergerak.
5. Semua mini project pakai aplikasi Spring Boot sebagai backend contoh, konsisten dengan root `CLAUDE.md` dan `standards/KUBERNETES_STANDARDS.md`.

Referensi struktur: [NGINX Documentation](https://nginx.org/en/docs/), [Kubernetes Ingress Docs](https://kubernetes.io/docs/concepts/services-networking/ingress/), [ingress-nginx Documentation](https://kubernetes.github.io/ingress-nginx/), [cert-manager Documentation](https://cert-manager.io/docs/), [Kubernetes Gateway API](https://gateway-api.sigs.k8s.io/), `standards/KUBERNETES_STANDARDS.md` dan `standards/DOCKER_STANDARDS.md` di repo ini.

---

# Learning Objectives

* [ ] Memahami networking dasar yang relevan (L3/L4/L7, DNS) dan konsep reverse proxy vs forward proxy vs load balancer
* [ ] Menguasai konfigurasi NGINX murni: `http`/`server`/`location` block, routing, upstream, load balancing algorithm
* [ ] Memahami model networking Kubernetes secukupnya (Pod network, Service, kube-proxy, CoreDNS) sebagai fondasi Ingress
* [ ] Menguasai Ingress resource: rules, path, pathType, IngressClass, dan konsep Ingress Controller
* [ ] Mahir deploy & konfigurasi `ingress-nginx` termasuk annotation lanjut, custom config, canary, session affinity
* [ ] Menguasai TLS end-to-end: termination/passthrough/re-encryption, TLS Secret, `cert-manager` + Let's Encrypt (HTTP01/DNS01), mTLS
* [ ] Menguasai security layer ingress: rate limiting, WAF (ModSecurity + OWASP CRS), IP allow/deny, auth di edge, NetworkPolicy
* [ ] Menguasai observability ingress: log format, metrics Prometheus/Grafana, tracing, alerting berbasis SLO
* [ ] Mahir troubleshooting produksi: 502/503/504, Ingress tidak routing, isu cert/DNS, load testing & tuning
* [ ] Memahami arsitektur produksi lanjut: multi-controller, progressive delivery, Gateway API, perbandingan alternatif controller

---

# Course Modules

## Module 1 — Reverse Proxy & Networking Fundamentals

Description: Fondasi networking yang wajib dikuasai sebelum menyentuh NGINX — supaya konfigurasi berikutnya dipahami, bukan dihafal.

Lessons:

* [ ] 1.1 Model L3/L4/L7 secukupnya untuk paham posisi reverse proxy, DNS resolution dasar
* [ ] 1.2 Reverse proxy vs forward proxy vs load balancer — kapan pakai yang mana
* [ ] 1.3 Arsitektur NGINX: master/worker process, event-driven model (kenapa NGINX ringan di high concurrency)
* [ ] 1.4 Install & jalankan NGINX via Docker, reverse proxy pertama ke satu backend

Mini Project: Reverse proxy NGINX di depan satu Spring Boot app, jalan via Docker Compose.

Status: Not started

---

## Module 2 — NGINX Core Configuration

Description: Konfigurasi NGINX murni (di luar Kubernetes dulu) — skill yang tetap relevan untuk VPS/bare-metal.

Lessons:

* [ ] 2.1 Struktur `nginx.conf`: directive, context (`http`, `server`, `location`)
* [ ] 2.2 Location matching: prefix, exact, regex, urutan prioritas
* [ ] 2.3 `upstream` block & load balancing algorithm (round robin, least_conn, ip_hash)
* [ ] 2.4 Header manipulation, rewrite, redirect
* [ ] 2.5 Static file serving, caching header, gzip compression

Mini Project: NGINX di depan 2 instance Spring Boot (load balanced) + static frontend, dengan caching & gzip aktif.

Status: Not started

---

## Module 3 — Kubernetes Networking Bridge

Description: Ringkasan model networking Kubernetes yang jadi prasyarat langsung untuk paham kenapa Ingress dibutuhkan. Bukan pengulangan penuh `courses/kubernetes` — cukup untuk konteks course ini.

Lessons:

* [ ] 3.1 Pod networking model & CNI overview (setiap Pod dapat IP sendiri)
* [ ] 3.2 Service: ClusterIP, NodePort, LoadBalancer — dan kube-proxy mode (iptables/IPVS)
* [ ] 3.3 CoreDNS & Endpoints/EndpointSlices — bagaimana Service menemukan Pod
* [ ] 3.4 Batasan Service untuk trafik HTTP (satu LoadBalancer per Service = mahal & tidak bisa routing L7) → motivasi Ingress

Mini Project: Deploy 2 Spring Boot app berbeda ke Minikube/Kind, expose masing-masing lewat Service, rasakan langsung keterbatasannya sebelum Ingress diperkenalkan.

Status: Not started

---

## Module 4 — Kubernetes Ingress Basics

Description: Objek inti API Ingress dan konsep Controller — bagian yang paling sering disalahpahami (resource vs controller).

Lessons:

* [ ] 4.1 Anatomi Ingress resource: `rules`, `paths`, `pathType` (Exact/Prefix/ImplementationSpecific)
* [ ] 4.2 IngressClass & konsep Ingress Controller (Ingress resource tidak berbuat apa-apa tanpa controller)
* [ ] 4.3 Install `ingress-nginx` controller via Helm
* [ ] 4.4 Host-based routing & path-based routing, default backend

Mini Project: Routing 2 Spring Boot app lewat satu Ingress: `api.local/orders` dan `api.local/payments`.

Status: Not started

---

## Module 5 — Ingress-NGINX Deep Dive

Description: Fitur lanjut spesifik `ingress-nginx` yang dipakai sehari-hari di production.

Lessons:

* [ ] 5.1 Annotation penting: `rewrite-target`, `ssl-redirect`, `proxy-body-size`, `proxy-read-timeout`, dll.
* [ ] 5.2 Custom NGINX config lewat ConfigMap & server-snippet
* [ ] 5.3 Canary release via annotation (`canary`, `canary-weight`, `canary-by-header`)
* [ ] 5.4 Session affinity / sticky session berbasis cookie

Mini Project: Canary rollout 10% trafik ke versi baru Spring Boot app, diverifikasi lewat log.

Status: Not started

---

## Module 6 — TLS & Certificate Management

Description: HTTPS end-to-end — dari konsep sampai otomasi penuh dengan `cert-manager`.

Lessons:

* [ ] 6.1 TLS termination vs TLS passthrough vs re-encryption — kapan pakai yang mana
* [ ] 6.2 Kubernetes TLS Secret manual & referensinya di Ingress
* [ ] 6.3 `cert-manager`: ClusterIssuer, Let's Encrypt ACME (HTTP01 & DNS01 challenge), auto-renewal
* [ ] 6.4 mTLS antara Ingress dan backend (zero-trust dasar)

Mini Project: HTTPS otomatis lewat `cert-manager` + Let's Encrypt staging untuk app dari Modul 4.

Status: Not started

---

## Module 7 — Security di Layer Ingress

Description: Ingress sebagai edge/perimeter pertama — titik paling kritis untuk hardening.

Lessons:

* [ ] 7.1 Rate limiting & connection limiting (`limit-rps`, `limit-connections`)
* [ ] 7.2 WAF: ModSecurity + OWASP Core Rule Set di `ingress-nginx`
* [ ] 7.3 IP allow/deny list, basic auth, `oauth2-proxy` di depan Ingress
* [ ] 7.4 NetworkPolicy untuk membatasi akses masuk/keluar Ingress Controller

Mini Project: Amankan app dari Modul 6 dengan rate limiting + WAF + IP allowlist, diverifikasi dengan percobaan serangan sederhana (load test & payload XSS/SQLi dasar).

Status: Not started

---

## Module 8 — Observability Ingress

Description: Ingress adalah titik terbaik untuk observability trafik masuk — metrik dan log di sini paling berharga untuk SLO.

Lessons:

* [ ] 8.1 Format access/error log NGINX, structured logging (JSON log format)
* [ ] 8.2 Prometheus metrics dari `ingress-nginx-controller` + dashboard Grafana
* [ ] 8.3 Distributed tracing di layer Ingress (OpenTelemetry)
* [ ] 8.4 Alerting berbasis SLO (5xx rate, latency, error budget burn-rate)

Mini Project: Dashboard Grafana + alert untuk error rate & latency Ingress dari app Modul 7, sesuai `standards/OBSERVABILITY_STANDARDS.md`.

Status: Not started

---

## Module 9 — Troubleshooting Produksi

Description: Skill yang paling sering dipakai harian ketika Ingress "tiba-tiba" bermasalah.

Lessons:

* [ ] 9.1 Diagnosa 502/503/504 secara sistematis (controller log vs backend log vs upstream timeout)
* [ ] 9.2 Ingress tidak routing sama sekali — urutan pengecekan (`kubectl describe ingress`, IngressClass mismatch, controller events)
* [ ] 9.3 Isu cert (expired, wrong SAN) dan isu DNS (propagation, CNAME salah)
* [ ] 9.4 Load testing kapasitas Ingress (k6/hey) & tuning (`worker_connections`, keepalive, `proxy_buffering`)

Mini Project: Studi kasus — diberi Ingress + cluster yang sengaja rusak (multi-skenario), diagnosa dan perbaiki root cause satu per satu.

Status: Not started

---

## Module 10 — Arsitektur Produksi & Menuju Expert

Description: Topik yang membedakan level medior dari expert — arsitektur skala besar dan arah masa depan API Ingress.

Lessons:

* [ ] 10.1 Multiple Ingress Controller & IngressClass per tim/tenant (multi-tenancy)
* [ ] 10.2 Progressive delivery skala produksi: Argo Rollouts + `ingress-nginx` (canary otomatis berbasis metrik)
* [ ] 10.3 Gateway API — penerus resmi Ingress (`Gateway`, `HTTPRoute`) & jalur migrasi bertahap
* [ ] 10.4 Perbandingan `ingress-nginx` vs Traefik vs HAProxy Ingress vs cloud-native (AWS ALB Ingress Controller) — trade-off nyata
* [ ] 10.5 Arsitektur global/multi-cluster load balancing (overview)

Portfolio Project: Sistem multi-service (order/payment/notification, Spring Boot) di belakang `ingress-nginx` dengan host+path routing, TLS otomatis (`cert-manager`), rate limiting + WAF, canary release via Argo Rollouts, observability penuh (metrics+log+tracing+alert), dan dokumentasi troubleshooting runbook.

Status: Not started

---

# Enterprise Project

Description: Deploy platform e-commerce mini (order service, payment service, notification service, semua Spring Boot) ke Kubernetes dengan satu `ingress-nginx` sebagai edge tunggal — host-based routing per environment (staging/prod), path-based routing per service, TLS otomatis via `cert-manager`, WAF + rate limiting aktif, canary release progresif via Argo Rollouts, dan observability stack lengkap (Prometheus + Grafana + tracing), sesuai `standards/KUBERNETES_STANDARDS.md`, `standards/OBSERVABILITY_STANDARDS.md`, dan `standards/SECURITY_STANDARDS.md`.

Acceptance Criteria:

* [ ] Semua service ter-routing benar lewat satu Ingress (host + path based)
* [ ] HTTPS otomatis dan auto-renewal via `cert-manager`, tanpa cert manual
* [ ] Rate limiting & WAF aktif dan teruji lewat percobaan serangan dasar
* [ ] Canary release teruji dengan rollback otomatis berbasis metrik error rate
* [ ] Observability lengkap: metrics, log terstruktur, tracing, alert SLO
* [ ] Runbook troubleshooting untuk minimal 3 skenario kegagalan umum (502/503/504, cert expired, routing salah)
* [ ] Mampu menjelaskan setiap keputusan desain & trade-off (siap interview Senior/Staff)
* [ ] Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: 1.1 Model L3/L4/L7 secukupnya untuk paham posisi reverse proxy, DNS resolution dasar
