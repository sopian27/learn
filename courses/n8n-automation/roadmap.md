# Course Roadmap

## Course Information

* Course Name: n8n Automation — dari 0 sampai Bisa Bangun Asisten AI Pribadi ("Jarvis-lite")
* Category: n8n Automation (Domain: n8n-automation — lihat `domains/n8n-automation/DOMAIN.md`)
* Difficulty: Level 0 (belum pernah pakai n8n / automation tool apapun) → Level 3 (mampu merancang, membangun, mengamankan, dan merawat sistem asisten AI pribadi berbasis n8n end-to-end)
* Estimated Duration: 10 modul, 3-4 lesson/modul, ~30-45 menit/lesson. Pace disesuaikan sambil jalan.
* Prerequisites: Tidak wajib bisa coding, tapi terbiasa membaca JSON/API response akan sangat membantu di Module 3 ke atas. Setup dipilih: **n8n Cloud** (free tier) — cukup daftar akun, tidak perlu instalasi. Untuk Module 6-8 (AI Agent), pilihan LLM provider (OpenAI/Anthropic/Ollama lokal) belum diputuskan — trade-off-nya akan dijelaskan dulu di Lesson 6.1-6.2 sebelum learner memilih.

---

# Learning Objectives

* [ ] Memahami mental model workflow automation dan membangun workflow n8n dari trigger sampai output yang benar-benar jalan (bukan cuma benar di canvas)
* [ ] Mengintegrasikan API eksternal dan app (Telegram/Slack/Google Sheets, dst) dengan credential yang aman dan error handling yang layak produksi
* [ ] Mentransformasi data secara andal memakai expressions, Code node, dan pola looping/batching
* [ ] Membangun AI Agent di n8n yang punya tools, memory, dan pola multi-agent dasar
* [ ] Menghubungkan asisten AI ke channel nyata (chat) dan menerapkan automation proaktif terjadwal
* [ ] Menghasilkan satu sistem Asisten AI Pribadi ("Jarvis-lite") sebagai Capstone Project yang reliable, aman, efisien, dan bisa dipertahankan setiap keputusan desainnya

---

# Course Modules

## Module 1 — n8n Fundamentals & Mental Model

Description: Fondasi sebelum menyentuh workflow sungguhan — apa itu automation platform, bagaimana n8n berbeda dari alternatif lain, dan cara kerja canvas/execution-nya.

Lessons:

* [ ] 1.1 Apa itu workflow automation, dan bagaimana n8n berbeda dari Zapier/Make (node-based, fair-code license, self-host vs cloud)
* [ ] 1.2 Instalasi & setup: n8n Cloud vs self-hosted (Docker) — memilih mana untuk belajar, dan trade-off-nya
* [ ] 1.3 Anatomy of a workflow: trigger, node, connection, execution, pinned data
* [ ] 1.4 UI tour: canvas, node panel, expressions editor, execution log

Mini Project: Workflow pertama — Manual Trigger → Set node → output — dijalankan dan datanya diinspeksi lewat execution log.

Status: Not started

---

## Module 2 — Triggers & Basic Data Flow

Description: Cara data masuk ke workflow dan bagaimana n8n merepresentasikan data secara internal, plus node inti untuk mengatur alur logika.

Lessons:

* [ ] 2.1 Jenis trigger: Manual, Schedule/Cron, Webhook, app-based trigger
* [ ] 2.2 Struktur data di n8n: items, JSON, binary data
* [ ] 2.3 Node inti: Set/Edit Fields, IF, Switch, Filter, Merge
* [ ] 2.4 Expressions dasar: `{{ }}`, `$json`, `$node`, cara mereferensikan node sebelumnya

Mini Project: Workflow bertrigger Webhook yang menerima data form, memvalidasi dengan IF, dan mengarahkan ke 2 cabang berbeda.

Status: Not started

---

## Module 3 — API & App Integrations

Description: Menghubungkan n8n ke dunia luar — HTTP request, credential, dan node aplikasi spesifik — dengan kebiasaan aman sejak awal.

Lessons:

* [ ] 3.1 HTTP Request node — method, auth header, query param, pagination
* [ ] 3.2 Credential management — API key, OAuth2, kenapa jangan pernah hardcode secret di node/expression
* [ ] 3.3 App-specific node (Google Sheets, Gmail, Telegram, Slack) vs HTTP Request generik — kapan pakai yang mana
* [ ] 3.4 Rate limiting, retry, dan error handling per-node (Continue on Fail, retry on fail)

Mini Project: Workflow yang mengambil data dari API publik (mis. weather/news API) lalu mengirim notifikasi ke Telegram/Slack pribadi, dengan error handling dasar.

Status: Not started

---

## Module 4 — Data Transformation & Logic

Description: Mengubah data mentah jadi bentuk yang berguna secara andal, termasuk kapan keluar dari node bawaan dan menulis kode sendiri.

Lessons:

* [ ] 4.1 Expressions lanjutan: JS expression, built-in method untuk string/array/date
* [ ] 4.2 Code node (JavaScript/Python) — kapan pakai Code node vs node bawaan
* [ ] 4.3 Loop & batch processing: Split In Batches / Loop Over Items
* [ ] 4.4 Aggregate, Sort, Remove Duplicates, dan reshaping data lain

Intermediate Project: Workflow ETL sederhana — ambil data dari API/Google Sheet, transform (filter + aggregate), tulis ke destinasi lain (Sheet/DB).

Status: Not started

---

## Module 5 — Error Handling, Reliability & Sub-workflows

Description: Membuat workflow yang bertahan saat sesuatu gagal, dan mulai berpikir modular seperti automation builder berpengalaman.

Lessons:

* [ ] 5.1 Error workflow — global error handler, notifikasi otomatis saat workflow gagal
* [ ] 5.2 Pola try/catch memakai error output tiap node
* [ ] 5.3 Sub-workflow (Execute Workflow node) — modularisasi dan reuse logic
* [ ] 5.4 Versioning, environment (staging vs production credential), naming convention & dokumentasi (sticky notes)

Mini Project: Menambahkan error handling menyeluruh + minimal satu sub-workflow modular ke salah satu workflow dari modul sebelumnya.

Status: Not started

---

## Module 6 — AI Fundamentals di n8n (LangChain Nodes)

Description: Titik masuk ke sisi AI dari n8n — memanggil LLM dari dalam workflow dan memaksa outputnya jadi data terstruktur yang bisa dipakai node lain.

Lessons:

* [ ] 6.1 Konsep dasar orkestrasi LLM di n8n — chat model node, prompt, memory (gambaran besar sebelum masuk detail)
* [ ] 6.2 Basic LLM Chain / Chat Model node — memanggil LLM (OpenAI/Anthropic/model lokal) dari workflow
* [ ] 6.3 Structured Output Parser — memaksa output LLM jadi JSON terstruktur yang bisa dikonsumsi node lain
* [ ] 6.4 RAG dasar: Vector Store node + embeddings — kapan benar-benar dibutuhkan vs cukup prompt biasa

Mini Project: Workflow yang menerima teks (mis. isi email/pesan) → diringkas LLM → hasil terstruktur dikirim ke tujuan (Sheet/Slack).

Status: Not started

---

## Module 7 — AI Agent & Tools

Description: Dari "memanggil LLM sekali" ke "agent yang bisa memilih tindakan sendiri" — inti dari asisten AI yang akan jadi Capstone.

Lessons:

* [ ] 7.1 AI Agent node — konsep agent yang memilih tool sendiri (Tools Agent)
* [ ] 7.2 Menghubungkan tools ke agent: HTTP Request as Tool, Workflow as Tool, built-in tools
* [ ] 7.3 Memory — Simple Memory vs external memory (Redis/Postgres) untuk agent yang stateful antar percakapan
* [ ] 7.4 Pola multi-agent: AI Agent Tool node — agent utama mendelegasikan ke sub-agent

Intermediate Project: Asisten AI berbasis chat dengan minimal 2 tools (mis. cek jadwal + kirim reminder) dan memory percakapan.

Status: Not started

---

## Module 8 — Channels & Real-World Interfaces

Description: Membuat asisten yang dibangun di Module 6-7 bisa benar-benar diajak bicara lewat channel sehari-hari, bukan cuma dites di canvas.

Lessons:

* [ ] 8.1 Chat trigger / webhook untuk WhatsApp, Telegram, atau Slack sebagai antarmuka asisten
* [ ] 8.2 (Opsional) voice-to-text/text-to-voice lewat API eksternal, bila learner ingin interaksi suara
* [ ] 8.3 Automation proaktif terjadwal (mis. ringkasan harian otomatis tanpa diminta)
* [ ] 8.4 Menghubungkan beberapa layanan pribadi (calendar, email, task manager) sebagai "sensor" asisten

Mini Project: Menghubungkan satu channel (Telegram/WhatsApp) sebagai antarmuka chat ke AI Agent dari Module 7.

Status: Not started

---

## Module 9 — Security, Deployment & Maintenance

Description: Memastikan sistem yang sudah dibangun aman, terpantau, dan sadar biaya sebelum dianggap selesai — mengikuti `domains/n8n-automation/DOMAIN.md` bagian "Review Style".

Lessons:

* [ ] 9.1 Credential security best practice — least privilege, rotasi secret, jangan expose webhook tanpa autentikasi
* [ ] 9.2 Checklist produksi self-hosted (bila self-host): backup, environment variable, gambaran dasar queue mode
* [ ] 9.3 Monitoring & logging — execution history, alerting saat gagal
* [ ] 9.4 Biaya & skalabilitas: n8n Cloud tier vs self-host, kesadaran biaya token LLM

Mini Project: Audit keamanan & reliability terhadap seluruh workflow yang sudah dibuat sejauh ini, memakai checklist credential, error handling, dan monitoring.

Status: Not started

---

## Module 10 — Capstone: Sistem Asisten AI Pribadi ("Jarvis-lite")

Description: Integrasi seluruh modul menjadi satu sistem asisten AI pribadi yang koheren, mengikuti `domains/n8n-automation/DOMAIN.md` bagian "Project Tiers" untuk definisi Capstone/Portfolio Project di domain ini.

Lessons:

* [ ] 10.1 Desain arsitektur: peta sub-workflow (trigger, agent core, tools, channel, memory, error handler)
* [ ] 10.2 Build & integrasi seluruh komponen dari Module 1-9 jadi satu sistem yang saling terhubung
* [ ] 10.3 Self-review memakai kriteria `domains/n8n-automation/DOMAIN.md` bagian "Review Style" (Reliability, Security, Efficiency, Maintainability) sebelum diajukan review
* [ ] 10.4 Revisi berdasarkan Actionable Improvements, lalu dokumentasi, export workflow JSON, dan penyimpanan ke `portofolio/`

Capstone Project: Satu sistem Asisten AI Pribadi berbasis n8n — bisa menerima perintah lewat chat (Telegram/WhatsApp), memakai AI Agent dengan minimal 2-3 tools nyata, punya memory percakapan, error handling menyeluruh, dan minimal 1 automation proaktif terjadwal — didokumentasikan (workflow JSON + README arsitektur) dan disimpan ke `portofolio/`. Review memakai `domains/n8n-automation/DOMAIN.md` bagian "Review Style" di atas Universal Review Rubric (`ai-los/CORE_LOS.md`), plus Assessment Form-nya dan sesi tanya-jawab mempertahankan setiap keputusan desain.

Status: Not started

---

# Capstone Project

Description: Lihat Module 10 di atas untuk rincian lengkap. Capstone course ini SATU dengan Capstone Project Module 10 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/n8n-automation/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi keempat kriteria domain (Reliability, Security, Efficiency, Maintainability) plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, dan memenuhi bar "Portfolio-Worthy" (Complete, Reviewed, Presentable, Original) di `standards/UNIVERSAL_STANDARDS.md`.

Status: Not started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: 1.1 Apa itu workflow automation, dan bagaimana n8n berbeda dari Zapier/Make (node-based, fair-code license, self-host vs cloud)
