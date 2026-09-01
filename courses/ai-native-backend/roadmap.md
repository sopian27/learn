# Course Roadmap

## Course Information

* Course Name: AI-Native Backend Engineering — dari 0 sampai Expert
* Category: Software Engineering / AI Infrastructure & Platform Engineering
* Difficulty: Level 0 (belum pernah integrasi AI ke backend) → Level 4 (Expert AI-Native Backend/Platform Engineer)
* Estimated Duration: 11 modules + Enterprise Project, ~3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Java/Spring Boot production-grade (level `courses/backend-fundamentals/roadmap.md`), paham Docker/Kubernetes dasar (level `courses/kubernetes/roadmap.md` Module 1-3). **Tidak perlu** sudah menyelesaikan `courses/llm/roadmap.md` atau `courses/agents/roadmap.md` — Module 1-3 mengajarkan fondasi Spring AI secukupnya dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini dibuat atas permintaan langsung: "AI-Native Backend Engineering 0–expert: RedisTemplate JSON, Spring AI, AI Engineering, Kubernetes AI workload, Kubeflow, AI compliance, open table format/Iceberg, observability, security, dan vendor lock-in." Dibuat **sambil kedua Course Aktif** ("Backend Engineering Fundamentals Rebuild" Module 2/15, "Character Development" Module 1/6) **masih di bawah 50% — user eksplisit override guard CLAUDE.md § Course Creation Discipline saat ditanya konfirmasi (2026-09-01).**

**Kenapa course terpisah, bukan modul tambahan di course lain:**

1. **vs `courses/llm/roadmap.md` dan `courses/agents/roadmap.md`** — kedua course itu adalah jalur *Applied AI Engineer* berbasis **Python**: arsitektur LLM (tokenization, embeddings, transformer), prompt engineering, fine-tuning, agent framework (LangGraph/CrewAI), dari sisi *membangun model/agent itu sendiri*. Course ini sebaliknya adalah jalur **Java/Spring Boot backend engineer yang membungkus kapabilitas AI ke dalam sistem production** — perspektif platform/infrastruktur, bukan model. Tidak ada modul yang mengulang tokenization/fine-tuning/agent framework Python; Module 1-3 course ini hanya menyentuh LLM secukupnya lewat lensa Spring AI.
2. **vs `courses/kubernetes/roadmap.md`** — course itu CKAD-aligned, fondasi umum (Docker, Deployment, Service, scaling generik). Module 7 course ini **tidak mengulang** fondasi itu — fokus sempit ke pola *khusus AI workload*: Dynamic Resource Allocation untuk GPU, Kueue, KServe, KEDA autoscaling berbasis metrik LLM. Prasyarat eksplisit: selesai Module 1-3 course kubernetes dulu.
3. **vs `courses/observability/roadmap.md`** — course itu OpenTelemetry/Prometheus/Grafana/ELK generik untuk backend biasa. Module 9 course ini **tidak mengulang** tiga pilar observability generik — fokus sempit ke apa yang beda di AI: OTel GenAI semantic conventions (masih experimental), tracing token usage/cost, logging prompt-response, evaluation metrics.
4. **vs `courses/cybersecurity/roadmap.md`** — course itu OWASP Top 10 web klasik (injection, auth, session). Module 10 course ini **tidak mengulang** itu — fokus sempit ke OWASP Top 10 for LLM Applications (prompt injection, excessive agency, unbounded consumption) yang tidak ada di OWASP web klasik.
5. **vs `courses/ml-fundamentals/roadmap.md` Module 11 (MLOps in Practice)** — module itu fokus MLflow/DVC/continuous training dari sisi *data scientist workflow*. Module 8 course ini (Kubeflow & ML Platform Landscape) fokus dari sisi *backend/platform engineer yang mengoperasikan* platform tersebut di Kubernetes — sudut pandang beda, tidak diulang.

**Catatan kalibrasi konten (penting dibaca sebelum approve):** beberapa topik di course ini berubah cepat per riset Agustus-September 2026 — ditandai eksplisit di modul terkait, bukan disembunyikan:

* **Spring AI** — versi stabil produksi saat ini 1.1.x; Spring AI 2.0 (Spring Boot 4/Framework 7) masih milestone (M6, Mei 2026), API MCP-nya bisa berubah sebelum GA. Course mengajarkan 1.x sebagai baseline, 2.0 dibahas sebagai "watch this space", bukan materi utama.
* **OpenTelemetry GenAI semantic conventions** — masih **experimental/Development status** (belum stable per Juli 2026), baru dipecah ke repo terpisah `semantic-conventions-genai` untuk iterasi lebih cepat. Modul Observability eksplisit mengajarkan ini sebagai "standar yang belum settle", bukan seolah-olah sudah baku.
* **EU AI Act timeline** — sudah bergeser sekali lewat amandemen Digital Omnibus (berlaku 27 Jul 2026): kewajiban transparansi (Pasal 50) berlaku 2 Agu 2026, tapi kewajiban tier high-risk ditunda ke Des 2027/Agu 2028 karena standar teknis harmonized belum siap. Modul Compliance mengajarkan *kerangka berpikir* (apa yang jadi tanggung jawab engineer: audit trail, dokumentasi model, provenance data) bukan menghafal tanggal yang masih bisa berubah lagi.

Referensi riset (WebSearch, dilakukan 2026-09-01, sintesis bukan copy-paste):

* [Spring AI Reference](https://docs.spring.io/spring-ai/reference/index.html), [Spring AI Upgrade Notes](https://docs.spring.io/spring-ai/reference/upgrade-notes.html), [spring-ai GitHub](https://github.com/spring-projects/spring-ai)
* [Spring Data Redis docs](https://spring.io/projects/spring-data-redis/), [Redis Vector Search 2026 Guide](https://redis.io/blog/vector-search-database-news-2026-guide/), [RedisVL LLM Cache docs](https://docs.redisvl.com/en/latest/api/cache.html)
* [CNCF — Understanding Dynamic Resource Allocation in Kubernetes](https://www.cncf.io/blog/2026/07/01/understanding-dynamic-resource-allocation-in-kubernetes/), [KServe KEDA Autoscaler docs](https://kserve.github.io/website/docs/model-serving/predictive-inference/autoscaling/keda-autoscaler)
* [Kubeflow vs Ray (2026)](https://www.modern-datatools.com/compare/kubeflow-vs-ray), [Kubeflow vs KServe vs Ray](https://quantumencoding.io/blog/kubeflow-kserve-ray-choosing-right-ml-platform-kubernetes)
* [EU AI Act Implementation Timeline](https://artificialintelligenceact.eu/implementation-timeline/), [NIST AI RMF Core Functions](https://airc.nist.gov/airmf-resources/airmf/5-sec-core/)
* [Apache Iceberg vs Delta Lake vs Hudi (2026)](https://risingwave.com/blog/apache-iceberg-vs-delta-lake-vs-hudi-2026/), [Dremio — Iceberg vs Delta](https://www.dremio.com/blog/apache-iceberg-vs-delta-lake/)
* [State of OpenTelemetry GenAI Semantic Conventions, Jul 2026](https://john-hodge.com/blog/opentelemetry-genai-semantic-conventions/), [Top LLM Observability Platforms 2026](https://www.marktechpost.com/2026/08/09/top-llm-observability-and-evaluation-platforms-in-2026-langfuse-langsmith-braintrust-arize-and-more-compared/)
* [OWASP Top 10 for LLM Applications 2025](https://genai.owasp.org/resource/owasp-top-10-for-llm-applications-2025/)
* [Braintrust — Avoiding LLM Provider Lock-in 2026](https://www.braintrust.dev/articles/avoid-llm-provider-lock-in-2026), [LiteLLM overview](https://starlog.is/articles/ai-agents/berriai-litellm/)

---

# Learning Objectives

* [ ] Paham arsitektur AI-native backend: pola sinkron vs streaming, gateway pattern, di mana Spring Boot berperan di stack AI production
* [ ] Mahir Spring AI: `ChatClient`, provider abstraction (OpenAI/Anthropic/Bedrock/Ollama), Advisor framework, tool calling, structured output
* [ ] Mampu membangun RAG production-grade dengan Spring AI VectorStore dan mengekspos/mengonsumsi MCP server dari Java
* [ ] Mahir memakai Redis untuk kebutuhan AI: JSON serialization (`RedisTemplate`), response cache, semantic caching, embedding cache, agent memory
* [ ] Paham open table format (Iceberg vs Delta Lake vs Hudi) dan relevansinya sebagai lapisan data untuk feature store/training data reproducibility
* [ ] Mampu merancang strategi anti vendor lock-in: gateway abstraction (LiteLLM-style) vs abstraksi in-process (Spring AI), fallback multi-provider
* [ ] Paham pola AI workload di Kubernetes: Dynamic Resource Allocation (DRA) untuk GPU, Kueue untuk quota/fair-share, KServe untuk model serving, KEDA untuk autoscaling berbasis metrik LLM
* [ ] Paham lanskap platform ML (Kubeflow, Ray, Argo Workflows, MLflow) cukup untuk memilih dan mengintegrasikan, bukan membangun dari nol
* [ ] Mampu membangun observability AI: tracing token usage/cost, logging prompt-response aman, evaluation metrics, memakai OTel GenAI conventions dengan sadar keterbatasannya
* [ ] Paham OWASP Top 10 for LLM Applications dan mitigasi yang jadi tanggung jawab backend engineer (output handling, excessive agency, unbounded consumption, prompt injection)
* [ ] Paham kewajiban praktis compliance AI (EU AI Act, NIST AI RMF) yang jadi requirement engineering: audit trail, dokumentasi model, data provenance
* [ ] Mampu menyelesaikan proyek portofolio: AI-native backend service production-grade yang menggabungkan seluruh course

---

# Course Modules

## Module 1 — AI-Native Backend: Lanskap & Pola Arsitektur

Description: Modul orientasi — apa bedanya "backend yang memanggil AI API" dengan "AI-native backend", dan di mana Java/Spring Boot berperan di stack yang didominasi Python.

Lessons:

* [ ] Anatomi AI-native backend: request sinkron vs streaming (SSE/WebSocket untuk token streaming), long-running task (queue + polling) untuk task agentic
* [ ] Pola gateway/BFF (Backend-for-Frontend) untuk AI: kenapa jarang panggil provider API langsung dari client
* [ ] Peta ekosistem: kapan Java/Spring cocok (orchestration, integrasi sistem enterprise, keandalan) vs kapan Python masih dominan (training, eksperimen model)
* [ ] Overview 10 modul berikutnya sebagai satu sistem yang saling terhubung

Mini Project: Diagram arsitektur AI-native backend untuk 1 use case nyata (mis. customer support assistant) — request flow, komponen, titik integrasi dengan modul-modul berikutnya.

Status: Not Started

---

## Module 2 — Spring AI Fundamentals

Description: Fondasi Spring AI sebagai abstraksi idiomatis Spring di atas SDK provider — nilai utamanya provider-swapping lewat config/DI, bukan platform MLOps penuh.

Lessons:

* [ ] Setup Spring AI (starter dependency, konfigurasi provider), `ChatClient` sebagai entry point fluent
* [ ] Provider abstraction: swap OpenAI ↔ Anthropic ↔ Ollama ↔ Bedrock lewat konfigurasi, bukan ubah kode
* [ ] Prompt template & structured output (binding response ke Java record/POJO)
* [ ] Tool/function calling dari Spring: anotasi `@Tool`, error handling saat tool gagal

Mini Project: REST endpoint Spring Boot yang memanggil LLM dengan 1 tool custom, response di-bind ke Java record, provider bisa di-switch lewat `application.yml` tanpa ubah kode.

Status: Not Started

---

## Module 3 — Advisors, RAG & MCP dengan Spring AI

Description: Advisor framework adalah pola interceptor Spring AI untuk RAG/memory/logging — modul ini + MCP (kini first-class di Spring AI core) adalah bagian paling "backend-native" dari AI engineering.

Lessons:

* [ ] Advisor framework: `QuestionAnswerAdvisor`, `MessageChatMemoryAdvisor`, `SimpleLoggerAdvisor` — pola interceptor untuk compose RAG/memory/logging
* [ ] `VectorStore` abstraction & RAG production-grade (ingest, chunking, retrieval, provider vector store: PGVector/Redis/Qdrant)
* [ ] MCP di Spring AI: anotasi `@McpTool`/`@McpToolParam` — expose service Spring Boot sebagai MCP server, konsumsi MCP server eksternal dari Java
* [ ] Chat memory: short-term (per-sesi) vs long-term, penyimpanan lewat Advisor

Mini Project: RAG endpoint di atas knowledge base nyata (mis. isi `standards/` repo ini) + MCP server custom yang expose 1 service internal, dikonsumsi lewat Claude/agent eksternal.

**Production Discussion:** kapan Advisor chain jadi terlalu kompleks untuk di-debug — trade-off composability vs observability.

Status: Not Started

---

## Module 4 — Redis untuk AI Workloads

Description: Redis bukan cuma cache generik — untuk AI ada pola spesifik: semantic cache, embedding cache, agent memory. Modul ini eksplisit membahas `RedisTemplate` JSON serialization sesuai permintaan.

Lessons:

* [ ] `RedisTemplate` + JSON: `Jackson2JsonRedisSerializer` vs `GenericJackson2JsonRedisSerializer`, trade-off ukuran cache vs kemudahan deserialisasi
* [ ] Lettuce vs Jedis sebagai client — kenapa Lettuce default di Spring Boot
* [ ] Response cache exact-match vs semantic caching (reuse berdasar kemiripan embedding, bukan exact key) — konsep, walau tooling matang (RedisVL/LangCache) masih Python-sentris
* [ ] Redis sebagai vector store (Redis 8 vector search) untuk RAG, dan sebagai agent memory store (short/long-term)

Mini Project: Cache layer LLM response pakai `RedisTemplate` + JSON serialization, plus eksperimen semantic cache sederhana (cosine similarity check sebelum panggil LLM lagi) — ukur cache hit rate & penghematan cost.

Status: Not Started

---

## Module 5 — Open Table Format & Lapisan Data AI

Description: Iceberg dkk memecahkan masalah standardisasi tabel di atas object storage — relevan untuk backend engineer sebagai lapisan data di bawah feature store/training data, bukan sekadar topik data engineering.

Lessons:

* [ ] Masalah yang dipecahkan open table format: ACID di atas object storage, tanpa lock-in ke satu query engine
* [ ] Konsep inti Iceberg: schema evolution, hidden/partition evolution, time travel/snapshot
* [ ] Perbandingan Iceberg vs Delta Lake vs Hudi: multi-engine neutrality vs ekosistem Databricks vs kekuatan streaming CDC/upsert Hudi
* [ ] Relevansi ke AI: reproducibility training data lewat time travel, point-in-time correctness untuk feature store

Mini Project: Simulasi sederhana time travel/schema evolution pakai Iceberg (lokal, mis. via Spark/DuckDB) di atas dataset kecil — tunjukkan query ke snapshot lama setelah skema berubah.

Status: Not Started

---

## Module 6 — Menghindari Vendor Lock-in

Description: Ketergantungan ke satu provider LLM adalah risiko arsitektur nyata — modul ini membandingkan dua level abstraksi: gateway (infra, lintas bahasa) vs in-process (Spring AI, dalam JVM).

Lessons:

* [ ] Pola AI gateway: proxy antara app dan banyak provider (LiteLLM sebagai standar de facto polyglot) — load balancing, retry, fallback, spend tracking
* [ ] Abstraksi in-process: provider-swap Spring AI (Module 2) sebagai setara LiteLLM di level JVM — kapan pakai yang mana
* [ ] OpenAI-compatible API format sebagai lowest-common-denominator (DeepSeek, Mistral, Groq, Together AI ikut format ini)
* [ ] Strategi migrasi: evaluasi A/B provider lama vs baru sebelum full cutover, fallback provider per use case

Mini Project: Tambahkan fallback provider ke endpoint Module 2 (kalau provider utama gagal/timeout, otomatis switch ke provider kedua) + logging keputusan fallback.

**Production Discussion:** kapan gateway terpisah (infra-level) lebih tepat daripada abstraksi in-app — mis. saat ada layanan non-Java yang juga perlu akses AI.

Status: Not Started

---

## Module 7 — Kubernetes untuk AI Workload

Description: Prasyarat: Module 1-3 `courses/kubernetes/roadmap.md`. Modul ini sempit ke pola khusus AI — bukan mengulang fondasi Kubernetes umum.

Lessons:

* [ ] GPU scheduling: device plugin & nodeSelector/taint klasik, lalu Dynamic Resource Allocation (DRA, GA di K8s 1.34) sebagai model baru — MIG partition, NVLink
* [ ] Kueue: quota & fair-share scheduling untuk job GPU batch (utilisasi 25-35% → 60-85% versi klaim vendor, dibahas kritis)
* [ ] KServe: `InferenceService` CRD, model serving (canary, multi-model, transformer), scale-to-zero
* [ ] Autoscaling AI-specific: KEDA (event/metric-driven, mis. queue depth atau metrik LLM custom) vs HPA (resource-based) — KServe terintegrasi KEDA

Mini Project: Manifest `InferenceService` KServe (bisa disimulasikan/dry-run kalau tidak ada cluster GPU) + konfigurasi KEDA ScaledObject berdasar metrik custom (mis. request queue depth).

Status: Not Started

---

## Module 8 — Kubeflow & Lanskap Platform ML

Description: Kubeflow dibahas sebagai satu opsi dalam lanskap (bukan default tunggal) — fokus cukup untuk mengambil keputusan arsitektur platform, bukan menguasai instalasi/operasional mendalam.

Lessons:

* [ ] Arsitektur Kubeflow: Pipelines (di atas Argo Workflows), Trainer v2/`TrainJob` API (pengganti Training Operator v1 yang legacy)
* [ ] Hubungan Kubeflow dengan KServe (Module 7) — KServe kini independen/CNCF-graduated, bukan lagi subproject eksklusif Kubeflow
* [ ] Alternatif: Ray/KubeRay (ergonomi Python lebih baik untuk distributed training) dan MLflow (jadi model registry de facto, sering dikawinkan ke Kubeflow Pipelines)
* [ ] Kriteria memilih: ekosistem luas (Kubeflow) vs ergonomi (Ray) vs kebutuhan spesifik (registry-only via MLflow)

Mini Project: Decision matrix tertulis — untuk 3 skenario tim berbeda (startup kecil, enterprise dengan tim data science besar, tim backend murni yang cuma serving model), rekomendasikan kombinasi platform + alasan trade-off.

Status: Not Started

---

## Module 9 — AI Observability

Description: Prasyarat idealnya paham 3 pilar observability (level `courses/observability/roadmap.md`). Modul ini sempit ke apa yang beda soal AI, termasuk mengajarkan secara eksplisit bahwa standarnya belum settle.

Lessons:

* [ ] Apa yang beda dari APM biasa: token usage & cost per call, prompt-response logging (isu keamanan/PII), evaluation metrics, cost attribution per model/provider
* [ ] OpenTelemetry GenAI semantic conventions — status **experimental**, kenapa dipecah ke repo terpisah, implikasi buat keputusan "adopsi sekarang vs tunggu stable"
* [ ] Tooling: Langfuse (self-hosted, framework-agnostic via OTel trace), LangSmith (OTLP endpoint), Arize Phoenix (OpenInference convention) — kapan pakai yang mana
* [ ] Trace agentic call chain (plan→act→observe) mengikuti semangat `standards/OBSERVABILITY_STANDARDS.md`, sensor untuk PII sebelum logging

Mini Project: Instrumentasi endpoint Module 3 (RAG) dengan tracing token usage + cost per request, plus redaksi PII sebelum prompt/response di-log.

Status: Not Started

---

## Module 10 — Keamanan AI/LLM

Description: OWASP Top 10 for LLM Applications mencakup risiko yang tidak ada di OWASP web klasik — modul ini fokus ke mitigasi yang jadi tanggung jawab langsung backend engineer.

Lessons:

* [ ] OWASP Top 10 LLM 2025: overview 10 risiko (Prompt Injection, Sensitive Info Disclosure, Supply Chain, Data/Model Poisoning, Improper Output Handling, Excessive Agency, System Prompt Leakage, Vector/Embedding Weakness, Misinformation, Unbounded Consumption)
* [ ] Prompt injection langsung vs tidak langsung (via RAG/tool output) — kenapa "jangan percaya konten yang di-retrieve sebagai instruksi" adalah aturan inti
* [ ] Mitigasi yang dimiliki backend engineer: validasi/encoding output sebelum eksekusi tool call, least-privilege scoping permission agent, rate/cost limiting (Unbounded Consumption)
* [ ] System prompt leakage & vector/embedding weakness — kenapa vector store butuh access control juga

Mini Project: Red-team endpoint RAG dari Module 3 dengan payload prompt injection tidak langsung (sisipkan instruksi di dokumen yang di-retrieve), lalu implementasikan mitigasinya.

Status: Not Started

---

## Module 11 — Compliance & Governance AI untuk Engineer

Description: Bukan kelas hukum — modul ini menerjemahkan EU AI Act dan NIST AI RMF ke requirement engineering konkret: apa yang harus dibangun, bukan dihafal.

Lessons:

* [ ] EU AI Act dari sudut pandang engineer: kewajiban transparansi (Pasal 50, berlaku 2 Agu 2026) — labeling konten AI-generated, disclosure chatbot; catatan eksplisit bahwa kewajiban high-risk tier masih bergeser (Omnibus)
* [ ] NIST AI RMF: 4 fungsi inti (Govern, Map, Measure, Manage) sebagai kerangka iteratif, bukan checklist linear
* [ ] Requirement engineering konkret: audit trail (log siapa/kapan/prompt-response apa), dokumentasi model/versi yang dipakai, data provenance untuk sumber RAG/training
* [ ] Kapan compliance jadi keputusan arsitektur (mis. wajib on-prem/private model karena data sensitif) vs cukup dokumentasi

Mini Project: Tulis "AI Compliance Checklist" 1 halaman untuk sistem dari Module 3/10 — audit trail apa yang sudah ada, dokumentasi model apa yang kurang, data provenance RAG dari mana.

Status: Not Started

---

# Enterprise Project

Description: **AI-Native Backend Service — Simulasi Production Penuh.** Bangun 1 layanan Spring Boot yang menggabungkan seluruh course: (1) Spring AI `ChatClient` + Advisor + RAG di atas VectorStore nyata (Module 2-3), (2) MCP server custom yang expose 1 kapabilitas internal (Module 3), (3) Redis untuk response/semantic cache dengan `RedisTemplate` JSON (Module 4), (4) strategi fallback multi-provider anti vendor lock-in (Module 6), (5) manifest deployment K8s dengan pola AI workload — KServe/KEDA (Module 7), (6) tracing token usage/cost + redaksi PII di observability (Module 9), (7) mitigasi minimal 2 risiko OWASP LLM Top 10 (Module 10), (8) 1 halaman AI Compliance Checklist (Module 11).

Acceptance Criteria:

* Layanan berjalan end-to-end: request → RAG retrieval → tool/MCP call → response, dengan cache hit terukur
* Fallback provider teruji (matikan provider utama, verifikasi otomatis switch)
* Manifest K8s valid (dry-run diterima) dengan pola autoscaling AI-specific, bukan HPA generik biasa
* Trace menunjukkan token usage & cost per request, prompt/response sensitif sudah diredaksi sebelum log
* Minimal 2 skenario prompt injection (langsung & tidak langsung) tertangani dengan mitigasi terverifikasi
* AI Compliance Checklist selesai dan realistis untuk sistem yang dibangun
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/11

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Anatomi AI-native backend: request sinkron vs streaming, long-running task
