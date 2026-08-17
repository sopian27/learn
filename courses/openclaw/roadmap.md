# Course Roadmap

## Course Information

* Course Name: OpenClaw — dari 0 sampai Expert
* Category: AI Platform Engineering / Autonomous Personal Assistant (Domain: software-engineering)
* Difficulty: Level 3 (paham dasar agent/LLM dari pengalaman kerja) → Level 5 (mampu deploy, extend, dan mengoperasikan production instance OpenClaw sendiri: custom skill/plugin, multi-channel, multi-agent, hardened security, monitored deployment)
* Estimated Duration: 15 modul, ~4-6 lessons/modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Tidak wajib, tapi **sangat direkomendasikan** sudah menyelesaikan (atau paralel dengan) `courses/agents/roadmap.md` — course itu mengajarkan konsep agentic loop/tool use/MCP/multi-agent **secara framework-agnostic**, course ini mengajarkan cara satu platform produksi nyata (OpenClaw) mengimplementasikan konsep-konsep itu. Tanpa `agents`, istilah seperti "agent loop", "tool calling", "MCP" akan dijelaskan dari nol secukupnya di Module 3/6/11 supaya tetap bisa diikuti, tapi lebih dangkal dari course `agents`.

---

# Catatan Scope (baca dulu sebelum approve)

## Apa itu OpenClaw

Dikonfirmasi dari repo resmi [`github.com/openclaw/openclaw`](https://github.com/openclaw/openclaw) (dicek langsung 2026-08-18, bukan asumsi nama): **"OpenClaw is a personal AI assistant that runs on your devices and meets you in the channels you already use."** Open-source (dikelola OpenClaw Foundation, non-profit), arsitektur intinya:

* **Gateway** — control plane lokal yang mengelola session, tools, events, dan koneksi channel; diakses lewat Control UI (web), CLI, dan TUI.
* **Channels** — integrasi ke WhatsApp, Telegram, Slack, Discord, Google Chat, Signal, iMessage, dll — asisten "datang" ke tempat user sudah biasa chat, bukan aplikasi terpisah.
* **Model & Provider support** — hosted (Anthropic, OpenAI, Google, dll — 68 provider terdaftar di `docs/providers/`) maupun local model (Ollama, llama.cpp, LM Studio, vLLM).
* **Tools, Skills, Plugins** — extensibility berlapis: tools (kapabilitas langsung: exec, browser, code execution), skills (kapabilitas terpaket, bisa ditulis sendiri), plugins (dibangun lewat plugin SDK, didistribusikan lewat ClawHub).
* **Companion apps/nodes** — voice, Canvas, kamera, screen, aksi device-lokal di platform yang didukung.
* Filosofi keamanan eksplisit: pesan masuk dianggap **untrusted input**, channel yang bisa DM butuh pairing approval by default, tool jalan di host kecuali sandboxing dikonfigurasi — ada dokumentasi threat model dan sandboxing khusus (`docs/security/`).

Course ini dibangun langsung dari struktur dokumentasi resmi repo (`docs/gateway/`, `docs/tools/`, `docs/plugins/`, `docs/providers/`, `docs/concepts/`, `docs/security/`, `docs/reference/`) — nama file dokumentasi asli dicantumkan di tiap lesson supaya saat course dijalankan, materi bisa langsung dicek/dibaca dari sumber asli, bukan dihafal dari roadmap ini.

## Kenapa bukan duplikasi `courses/agents` atau `courses/n8n-automation`

| Course lain | Level pembahasan | Bedanya dengan course ini |
|---|---|---|
| `courses/agents/roadmap.md` (M2, M5, M7, M9, M11, M12: apa itu agent, memory, MCP, multi-agent, security, deployment) | **Konseptual, framework-agnostic** — mengajarkan cara *membangun* agent loop/tool use/MCP dari API mentah, tidak terikat satu produk | Course ini **product-specific**: bagaimana *satu platform produksi nyata* (OpenClaw) mengimplementasikan konsep itu — config file asli, CLI asli, dokumentasi asli, keputusan desain asli (mis. kenapa Gateway jalan lokal bukan cloud, kenapa channel butuh pairing approval). Tidak mengulang teori agentic loop dari nol; mengasumsikan atau mengajarkan seperlunya lalu langsung ke implementasi nyata. |
| `courses/n8n-automation/roadmap.md` ("Jarvis-lite" via workflow automation) | Tool automation visual/no-code (n8n nodes + LangChain nodes) | Stack teknologi sama sekali berbeda (n8n workflow builder vs OpenClaw Gateway platform). Tidak overlap konten — hanya sama-sama bermuara ke "personal AI assistant", pendekatannya berbeda total. |
| `courses/mastering-claude/roadmap.md` | Cara memakai Claude (API/Code/produk Anthropic) sebagai model/tool | OpenClaw memakai Claude sebagai salah satu dari puluhan model provider yang didukung (`docs/providers/anthropic.md`), bukan topik course ini — course ini tentang platform yang membungkus model, bukan model itu sendiri. |
| `courses/vps/roadmap.md` | VPS umum (provisioning, hardening, general server ops) | Module 14 course ini memakai VPS sebagai salah satu target deployment (`docs/gateway/index.md` → `vps.md`), tapi fokus pada *cara OpenClaw spesifik* di-deploy (Docker Compose, Fly.io, Render, remote gateway) — bukan VPS ops umum. |

**Kesimpulan:** zero overlap konten praktis. Course ini adalah lapisan "operate & extend platform produksi nyata" yang melengkapi (bukan menggantikan) fondasi konseptual `agents`.

## Peta 15 Modul vs 14 Topik yang Diminta User

User minta: architecture, Gateway, agents, models, channels, tools, skills, memory, sessions, automation, MCP, multi-agent, security, deployment, monitoring, + membangun autonomous AI assistant. Course ini memetakan tiap topik ke modul dedicated (Module 1 menggabungkan "architecture" sebagai peta besar di awal, Module 15 sebagai capstone "membangun autonomous AI assistant"):

Module 1 Architecture → Module 2 Gateway → Module 3 Agents → Module 4 Models → Module 5 Channels → Module 6 Tools → Module 7 Skills & Plugins → Module 8 Memory → Module 9 Sessions → Module 10 Automation → Module 11 MCP → Module 12 Multi-Agent → Module 13 Security → Module 14 Deployment & Monitoring → Module 15 Capstone.

---

# Learning Objectives

* [ ] Paham arsitektur penuh OpenClaw (Gateway sebagai control plane lokal, Channels, Model providers, Tools/Skills/Plugins, Companion apps/nodes) dan bisa menjelaskan kenapa desainnya "local-first" bukan cloud-first
* [ ] Mampu install, konfigurasi, dan mengoperasikan Gateway sendiri (CLI, TUI, Control UI, protocol, authentication, permission modes)
* [ ] Paham agent runtime OpenClaw (agent loop, agent bindings, agent workspace, system prompt/"soul") dan bedanya dengan agent loop generik yang sudah dipelajari di `agents`
* [ ] Mampu mengonfigurasi & mengganti model provider (hosted maupun local), memahami model failover, prompt caching, dan cost/token tracking
* [ ] Mampu menghubungkan minimal 2 channel messaging berbeda (mis. Telegram + Slack/Discord) dan paham message lifecycle end-to-end
* [ ] Mampu menggunakan dan mengonfigurasi tools bawaan (exec, browser control, code execution) berikut approval policy-nya
* [ ] Mampu menulis skill/plugin kustom sendiri (mengikuti `docs/tools/creating-skills.md` dan `docs/plugins/building-plugins.md`) dan paham distribusi lewat ClawHub
* [ ] Paham arsitektur memory OpenClaw (active memory, memory search, opsi backend memory built-in/Honcho/QMD/LanceDB) dan trade-off masing-masing
* [ ] Paham manajemen session (session state, pruning, search, context engine/compaction) dan kenapa ini krusial untuk asisten yang jalan lama/terus-menerus
* [ ] Mampu mengonfigurasi automation (standing intents, queue, hooks, self-learning) supaya asisten bisa proaktif, bukan cuma reaktif
* [ ] Mampu mengintegrasikan MCP server eksternal ke OpenClaw dan paham posisinya di antara tools/plugins/skills
* [ ] Mampu mengonfigurasi multi-agent (subagents, swarm, delegate architecture) dan multi-user pada satu instance Gateway
* [ ] Paham model keamanan OpenClaw secara mendalam (threat model, sandboxing, permission modes, secrets, pairing) — mampu menjelaskan kenapa "treat inbound messages as untrusted input" jadi prinsip inti
* [ ] Mampu deploy OpenClaw ke produksi (Docker/VPS/Fly.io/Render/remote gateway) dan memasang monitoring (OpenTelemetry, Prometheus, health/heartbeat, logging, audit)
* [ ] Menghasilkan satu autonomous personal AI assistant OpenClaw yang berjalan nyata (Capstone) — multi-channel, punya skill kustom, memory persisten, aman, dan termonitor

---

# Course Modules

## Module 1 — Peta Besar: Filosofi & Arsitektur OpenClaw

Description: Sebelum masuk detail tiap komponen, pahami dulu kenapa OpenClaw didesain seperti ini — "personal AI assistant yang jalan di device Anda, menemui Anda di channel yang sudah dipakai" (README resmi) — dan bagaimana lima komponen inti (Gateway, Channels, Models, Extensibility, Companion apps) saling terhubung.

Lessons:

* [ ] Apa itu OpenClaw: local-first vs cloud-first personal assistant, siapa OpenClaw Foundation, posisi `VISION.md` project ini
* [ ] Lima komponen arsitektur inti: Gateway (control plane), Channels, Model/Provider, Extensibility (Tools/Skills/Plugins), Companion apps/nodes — baca `docs/reference/architecture.md` dan `docs/reference/architecture-internals.md`
* [ ] Instalasi dari 0: `openclaw onboard --install-daemon`, verifikasi lewat `openclaw gateway status` dan `openclaw dashboard` (ikuti Quick Start resmi untuk OS yang dipakai)
* [ ] Tur workspace: struktur repo (`packages/`, `apps/`, `extensions/`, `skills/`, `config/`, `deploy/`) — mana yang dipakai user biasa vs kontributor
* [ ] Perbandingan eksplisit dengan agent generik yang sudah dipelajari di `agents`: apa yang OpenClaw "putuskan" untuk Anda (opinionated defaults) vs apa yang tetap harus dikonfigurasi sendiri

Mini Project: Install OpenClaw di mesin sendiri, selesaikan onboarding penuh, screenshot/dokumentasikan Control UI + hasil `openclaw gateway status` yang sehat.

Status: Not Started

---

## Module 2 — Gateway: Control Plane

Description: Gateway adalah jantung OpenClaw — mengelola session, tools, events, dan koneksi channel secara lokal. Modul ini masuk ke operasional Gateway sendiri sebelum menyentuh komponen lain.

Lessons:

* [ ] Gateway protocol & clients: bagaimana CLI/TUI/Control UI/aplikasi eksternal berbicara ke Gateway (`docs/gateway/protocol.md`, `docs/gateway/clients.md`, `docs/gateway/bridge-protocol.md`)
* [ ] Configuration: `docs/gateway/configuration.md`, `configuration-reference.md`, `configuration-examples.md` — struktur config utama, `config-agents.md`, `config-channels.md`, `config-tools.md`
* [ ] Authentication & permission modes: `docs/gateway/authentication.md`, `permission-modes.md`, `operator-scopes.md` — siapa boleh apa
* [ ] Discovery & networking: `discovery.md`, `bonjour.md`, `network-model.md`, `stable-https-url.md`, `tailscale.md` — cara Gateway ditemukan di jaringan lokal vs diekspos aman
* [ ] Diagnostics dasar: `doctor.md`, `diagnostics.md`, `health.md`, `troubleshooting.md` — cara mendiagnosis Gateway yang bermasalah

Mini Project: Konfigurasi ulang Gateway dari default — ubah minimal 3 setting (permission mode, auth, satu integrasi discovery) — dokumentasikan sebelum/sesudah dan alasan tiap perubahan.

Status: Not Started

---

## Module 3 — Agents & Agent Runtime

Description: Bagaimana OpenClaw sendiri mengimplementasikan "agent" — agent loop, runtime architecture, workspace, dan konsep unik OpenClaw seperti "soul" (system prompt/persona). Kalau sudah paham agent loop generik dari `agents` Module 2, modul ini tinggal memetakan konsep itu ke implementasi nyata OpenClaw.

Lessons:

* [ ] Agent loop & agent runtime architecture OpenClaw: `docs/concepts/agent-loop.md`, `agent-runtime-architecture.md`, `agent-runtimes.md`, `openclaw-agent-runtime.md`
* [ ] Agent bindings & agent workspace: `agent-bindings.md`, `agent-workspace.md` — bagaimana satu agent terikat ke channel/session/tools tertentu
* [ ] System prompt & "soul": `system-prompt.md`, `soul.md` — cara mendefinisikan persona dan perilaku default asisten
* [ ] User model & presence: `user-model.md`, `presence.md`, `typing-indicators.md` — bagaimana agent "tahu" siapa lawan bicaranya dan menampilkan status realtime
* [ ] Delegate architecture (preview Module 12): `delegate-architecture.md` — kapan satu agent mendelegasikan ke agent lain

Mini Project: Kustomisasi "soul"/system prompt asisten sendiri (persona, batasan, gaya bicara) dan verifikasi perubahan perilaku lewat percakapan uji nyata.

Status: Not Started

---

## Module 4 — Models & Providers

Description: OpenClaw mendukung puluhan model provider (hosted maupun local) — modul ini tentang cara memilih, mengonfigurasi, dan menjaga reliabilitas akses model.

Lessons:

* [ ] Model & provider fundamentals: `docs/providers/models.md`, `docs/providers/index.md` (68 provider terdaftar: Anthropic, OpenAI, Google, Bedrock, Mistral, dll)
* [ ] Local model providers: `docs/gateway/local-models.md`, `local-model-services.md`, `docs/plugins/llama-cpp.md`, provider `ollama.md`/`vllm.md`/`lmstudio.md` — trade-off privasi/biaya vs kapabilitas
* [ ] Model failover & reliability: `docs/concepts/model-failover.md` — apa yang terjadi saat provider utama down/rate-limited
* [ ] Cost & token management: `docs/reference/api-usage-costs.md`, `prompt-caching.md`, `token-use.md`, `docs/concepts/usage-tracking.md`
* [ ] Studi kasus: bandingkan minimal 3 provider (1 hosted premium, 1 hosted murah/open-weight via OpenRouter, 1 local) untuk task yang sama — latensi, biaya, kualitas

Mini Project: Konfigurasi minimal 2 model provider (1 hosted, 1 local) dengan failover chain, buktikan asisten tetap jalan saat provider utama disimulasikan gagal.

Status: Not Started

---

## Module 5 — Channels: Bertemu User di Tempatnya

Description: Channel adalah cara OpenClaw "meets you in the channels you already use" — WhatsApp, Telegram, Slack, Discord, Google Chat, Signal, iMessage, dll. Modul ini tentang menghubungkan dan mengelola channel nyata.

Lessons:

* [ ] Channel docking & konfigurasi: `docs/concepts/channel-docking.md`, `docs/gateway/config-channels.md`
* [ ] Message lifecycle: `docs/concepts/messages.md`, `message-lifecycle-refactor.md`, `markdown-formatting.md` — perjalanan 1 pesan dari channel masuk sampai balasan keluar
* [ ] Multi-user pada satu channel: `docs/concepts/multi-user.md` — bagaimana Gateway membedakan banyak user di 1 grup/channel
* [ ] Pairing & DM security preview (detail penuh di Module 13): `docs/gateway/pairing.md` — kenapa channel yang bisa DM butuh approval eksplisit by default
* [ ] Rich output & presentasi: `docs/plugins/message-presentation.md`, `docs/reference/rich-output-protocol.md`, reactions (`docs/tools/reactions.md`)

Mini Project: Hubungkan minimal 2 channel berbeda (mis. Telegram + Discord) ke satu Gateway, verifikasi asisten bisa dipakai lewat keduanya dengan konteks yang benar (tidak tercampur antar channel/user).

Status: Not Started

---

## Module 6 — Tools: Kapabilitas Langsung

Description: Tools adalah kapabilitas paling dasar yang bisa dipanggil agent — eksekusi shell, browser control, code execution — berikut lapisan approval-nya. Ini level "agent benar-benar menyentuh sistem", jadi modul dengan implikasi keamanan langsung.

Lessons:

* [ ] Tool fundamentals & tool search: `docs/tools/index.md`, `tool-search.md`, `capability-cookbook.md`
* [ ] Exec & approvals: `exec.md`, `exec-approvals.md`, `exec-approvals-advanced.md`, `elevated.md` — kapan tool boleh jalan otomatis vs butuh persetujuan manusia
* [ ] Browser control: `browser.md`, `browser-control.md`, `chrome-extension.md` — agent yang bisa mengoperasikan browser sungguhan
* [ ] Code execution & code mode: `code-execution.md`, `code-mode.md`, `apply-patch.md`, `diffs.md`
* [ ] Web & search tools: `web.md`, `web-fetch.md`, plus perbandingan multi-provider search (`brave-search.md`, `exa-search.md`, `tavily.md`, `perplexity-search.md`, dll — cukup 1-2 dicoba, bukan semua)

Mini Project: Buat 1 task nyata yang butuh chaining tool (mis. browser cari info → exec olah data → tulis hasil ke file), jalankan dengan approval policy yang eksplisit didefinisikan, dokumentasikan approval prompt yang muncul.

Status: Not Started

---

## Module 7 — Skills, Plugins & ClawHub

Description: Lapisan ekstensibilitas OpenClaw: tools (bawaan) → skills (kapabilitas terpaket, sering user-authored) → plugins (dibangun via plugin SDK, formal, didistribusikan lewat ClawHub). Modul ini sampai bisa menulis dan mempublikasikan ekstensi sendiri.

Lessons:

* [ ] Skills fundamentals: `docs/tools/skills.md`, `skills-config.md`, `slash-commands.md`
* [ ] Menulis skill sendiri: `docs/tools/creating-skills.md`, `skill-workshop.md` — praktik langsung tulis 1 skill dari nol
* [ ] Plugin architecture & SDK: `docs/plugins/architecture.md`, `architecture-internals.md`, `sdk-overview.md`, `sdk-setup.md`, `manifest.md`
* [ ] Building & distributing plugin: `docs/plugins/building-plugins.md`, `building-extensions.md`, `bundles.md`, `docs/tools/clawhub.md` — cara publikasi ke ClawHub
* [ ] Governance ekstensi: `docs/plugins/plugin-permission-requests.md`, `manage-plugins.md`, `plugin-inventory.md`, `dependency-resolution.md` — kenapa ekstensi pihak ketiga butuh review izin

Mini Project: Tulis 1 skill kustom fungsional (task nyata yang belum ada tool bawaannya) mengikuti `creating-skills.md`, uji end-to-end, dan (opsional) siapkan manifest untuk publikasi ClawHub.

Status: Not Started

---

## Module 8 — Memory

Description: Bagaimana OpenClaw mengingat percakapan/fakta lintas waktu — beda dari memory level engineering generik di `agents` M5, di sini ada beberapa backend memory nyata yang bisa dipilih dan dikonfigurasi.

Lessons:

* [ ] Memory architecture & active memory: `docs/concepts/memory-architecture.md`, `active-memory.md`, `memory.md`
* [ ] Memory search & context engine: `memory-search.md`, `context-engine.md`, `context.md`, `compaction.md` — bagaimana memory lama "dipadatkan" tanpa hilang makna penting
* [ ] Backend memory built-in vs eksternal: `memory-builtin.md` vs `memory-honcho.md`, `memory-qmd.md`, `docs/plugins/memory-lancedb.md`, `memory-wiki.md` — trade-off tiap backend (embedded vs service eksternal vs graph/wiki-style)
* [ ] "Dreaming": `docs/concepts/dreaming.md` — proses konsolidasi memory OpenClaw yang berjalan di background (analogi konsolidasi memori manusia saat tidur)
* [ ] Konfigurasi memory: `docs/reference/memory-config.md` — memilih & mengatur backend sesuai kebutuhan privasi/skala

Mini Project: Konfigurasi 1 backend memory non-default (mis. LanceDB atau Honcho), buktikan asisten bisa mengingat fakta spesifik dari percakapan beberapa hari sebelumnya lintas restart Gateway.

Status: Not Started

---

## Module 9 — Sessions

Description: Session adalah unit percakapan yang dikelola Gateway — modul ini tentang siklus hidup session, terutama untuk asisten yang dipakai terus-menerus dalam jangka panjang (bukan sekali chat lalu selesai).

Lessons:

* [ ] Session fundamentals & main session: `docs/concepts/session.md`, `main-session.md`, `session-state.md`
* [ ] Session pruning & search: `session-pruning.md`, `session-search.md`, `session-attachment.md` — mengelola session yang membengkak seiring waktu
* [ ] Session tool & session management di gateway: `docs/tools/session-tool.md`, `docs/reference/session-management-compaction.md`
* [ ] Managed worktrees: `docs/concepts/managed-worktrees.md` — isolasi konteks kerja per task/project
* [ ] Queue & steering di level session: `docs/concepts/queue.md`, `queue-steering.md` — bagaimana banyak pesan masuk diantre & diarahkan tanpa tabrakan konteks

Mini Project: Simulasikan 1 asisten yang dipakai lintas beberapa hari (banyak session), terapkan pruning/compaction, verifikasi asisten tetap koheren tanpa context window meledak.

Status: Not Started

---

## Module 10 — Automation

Description: Dari reaktif (jawab kalau ditanya) ke proaktif — standing intents, hooks, self-learning. Ini yang membuat OpenClaw layak disebut "autonomous", bukan sekadar chatbot.

Lessons:

* [ ] Standing intents: `docs/concepts/standing-intents.md` — instruksi persisten yang dijalankan agent tanpa diminta ulang tiap saat
* [ ] Hooks: `docs/plugins/hooks.md` — event-driven automation (mis. reaksi otomatis terhadap event tertentu)
* [ ] Self-learning: `docs/tools/self-learning.md` — bagaimana asisten menyesuaikan perilaku dari feedback berulang
* [ ] Progress drafts & goal tracking: `docs/concepts/progress-drafts.md`, `docs/tools/goal.md` — automation untuk task jangka panjang
* [ ] Retry & reliability automation: `docs/concepts/retry.md`, `loop-detection.md` — mencegah automation yang justru terjebak loop rusak

Mini Project: Buat 1 standing intent nyata (mis. ringkasan harian otomatis, monitoring sederhana) yang jalan tanpa trigger manual, jalankan minimal beberapa siklus, evaluasi hasilnya.

Status: Not Started

---

## Module 11 — MCP (Model Context Protocol) & Integrasi Eksternal

Description: MCP di `agents` Module 7 diajarkan sebagai protokol generik. Modul ini tentang bagaimana OpenClaw secara konkret mengonsumsi (dan berpotensi menyajikan) MCP server, dan posisinya relatif terhadap tools/plugins/skills OpenClaw sendiri.

Lessons:

* [ ] MCP di OpenClaw: `docs/tools/mcp.md` — cara menghubungkan MCP server eksternal, kapan pakai MCP vs plugin native
* [ ] Tool plugins & provider plugins: `docs/plugins/tool-plugins.md`, `sdk-provider-plugins.md` — cara OpenClaw membungkus kapabilitas eksternal selain via MCP
* [ ] Webhooks sebagai integrasi eksternal lain: `docs/plugins/webhooks.md` — perbandingan pola push (webhook) vs pull (MCP)
* [ ] Admin & RPC API: `docs/reference/admin-http-rpc.md`, `docs/reference/rpc.md`, `docs/gateway/tools-invoke-http-api.md` — cara sistem eksternal memanggil OpenClaw, bukan sebaliknya
* [ ] Studi kasus: pasang 1 MCP server pihak ketiga nyata, bandingkan effort-nya vs menulis plugin native untuk kapabilitas yang sama

Mini Project: Integrasikan minimal 1 MCP server eksternal ke instance OpenClaw sendiri, buktikan agent bisa memanggil tool dari server tersebut dalam percakapan nyata.

Status: Not Started

---

## Module 12 — Multi-Agent Orchestration

Description: Satu Gateway bisa menjalankan lebih dari satu agent yang saling berkoordinasi — subagents, swarm, delegate architecture, sampai parallel specialist lanes untuk task yang butuh spesialisasi berbeda-beda.

Lessons:

* [ ] Multi-agent fundamentals di OpenClaw: `docs/concepts/multi-agent.md`, `docs/tools/subagents.md`
* [ ] Swarm pattern: `docs/tools/swarm.md` — banyak agent identik bekerja paralel pada task yang sama
* [ ] Delegate architecture & parallel specialist lanes: `docs/concepts/delegate-architecture.md`, `parallel-specialist-lanes.md` — 1 agent orchestrator mendelegasikan ke agent spesialis berbeda
* [ ] Agent-to-agent messaging: `docs/tools/agent-send.md` — mekanisme komunikasi antar-agent
* [ ] Multi-agent sandbox tools & permission: `docs/tools/multi-agent-sandbox-tools.md` — isolasi keamanan saat banyak agent jalan bersamaan (jembatan ke Module 13)

Mini Project: Rancang & jalankan setup 2+ agent yang berkolaborasi pada 1 task kompleks (mis. 1 agent riset + 1 agent eksekusi) memakai pola delegate atau swarm, dokumentasikan pembagian tanggung jawabnya.

Status: Not Started

---

## Module 13 — Security

Description: Prinsip inti OpenClaw: pesan masuk = untrusted input. Modul ini pendalaman penuh model keamanan — dari threat model formal sampai sandboxing operasional — krusial karena asisten ini punya akses eksekusi nyata ke device Anda.

Lessons:

* [ ] Threat model: `docs/security/THREAT-MODEL-ATLAS.md`, `CONTRIBUTING-THREAT-MODEL.md` — kategori ancaman yang secara eksplisit dipikirkan tim OpenClaw
* [ ] Sandboxing & tool policy: `docs/gateway/sandboxing.md`, `sandbox-vs-tool-policy-vs-elevated.md` — tiga lapis kontrol eksekusi tool
* [ ] Secrets management: `docs/gateway/secrets.md`, `secrets-plan-contract.md`, `docs/reference/secretref-credential-surface.md`, `secret-placeholder-conventions.md`
* [ ] Pairing, audit, & network exposure: `docs/gateway/pairing.md`, `audit.md`, `trusted-proxy-auth.md`, `docs/security/network-proxy.md`
* [ ] Incident response & formal verification: `docs/security/incident-response.md`, `formal-verification.md` — apa yang dilakukan tim OpenClaw kalau ada kerentanan ditemukan

Mini Project: Lakukan security review terhadap instance OpenClaw sendiri (Module 1-12) — audit permission mode, sandboxing tool exec (Module 6), dan exposure channel (Module 5) — tulis temuan + mitigasi mengikuti pola `standards/SECURITY_STANDARDS.md`.

Status: Not Started

---

## Module 14 — Deployment & Monitoring

Description: Dari "jalan di laptop sendiri" ke "jalan 24/7 sebagai layanan produksi" — deployment target, remote gateway, dan observability penuh supaya tahu kapan asisten bermasalah sebelum user (Anda sendiri) sadar.

Lessons:

* [ ] Deployment targets: Docker (`docker-compose.yml`, `Dockerfile`), VPS (`docs/gateway/index.md` → deploy guide, cross-reference `courses/vps`), Fly.io (`fly.toml`), Render (`render.yaml`) — kapan pakai yang mana
* [ ] Remote gateway & multi-tenant hosting: `docs/gateway/remote.md`, `remote-gateway-readme.md`, `multi-tenant-hosting.md`, `multiple-gateways.md`, `cloud-workers.md`
* [ ] Restart & recovery: `docs/gateway/restart-recovery.md`, `background-process.md`, `gateway-lock.md` — memastikan asisten pulih sendiri dari crash
* [ ] Monitoring: `docs/gateway/opentelemetry.md`, `prometheus.md`, `health.md`, `heartbeat.md`, `logging.md` — instrumentasi standar observability (cross-reference `standards/OBSERVABILITY_STANDARDS.md`)
* [ ] Operasional harian: `docs/gateway/diagnostics.md`, `docs/concepts/usage-tracking.md` — dashboard yang dicek rutin untuk asisten yang jalan produksi

**Production Discussion: Spring Boot Integration** — pola OpenTelemetry/Prometheus di `docs/gateway/opentelemetry.md`/`prometheus.md` memakai standar instrumentasi yang sama dengan yang biasa dipasang di service Spring Boot produksi (Micrometer → Prometheus, distributed tracing) — bandingkan langsung dengan `standards/OBSERVABILITY_STANDARDS.md` di repo ini.

Mini Project: Deploy instance OpenClaw sendiri ke target non-laptop (VPS/Fly.io/Render/Docker), pasang minimal 1 jalur monitoring (health check atau Prometheus/OTel), buktikan bisa mendeteksi downtime tanpa mengecek manual.

Status: Not Started

---

## Module 15 — Capstone: Membangun Autonomous Personal AI Assistant

Description: Modul sintesis penuh — menyatukan Gateway (M2), Agents (M3), Models (M4), Channels (M5), Tools (M6), Skills/Plugins (M7), Memory (M8), Sessions (M9), Automation (M10), MCP (M11), Multi-Agent (M12), Security (M13), Deployment/Monitoring (M14) menjadi satu asisten yang benar-benar dipakai sehari-hari.

Lessons:

* [ ] Merancang spesifikasi asisten pribadi: task nyata apa yang mau diotomasi, channel mana yang relevan, batas otonomi yang diizinkan
* [ ] Menyusun arsitektur keputusan: model provider, backend memory, kebijakan sandbox/approval — dengan alasan eksplisit tiap pilihan (bukan default tanpa pertimbangan)
* [ ] Implementasi bertahap: gateway → channel → model → 1 skill kustom → memory → automation → (opsional) multi-agent/MCP
* [ ] Hardening sebelum "live": ulangi security review Module 13 khusus untuk setup capstone, pasang monitoring Module 14
* [ ] Retrospektif: apa yang bekerja, apa yang gagal, apa yang akan diubah kalau mulai dari nol — kebiasaan post-mortem ala Senior Engineer

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Personal Autonomous Assistant — Production Deployment**. Bangun dan jalankan instance OpenClaw pribadi yang deployed nyata (bukan cuma jalan di localhost), terdiri dari: (1) minimal 2 channel messaging terhubung (Module 5), (2) minimal 1 skill/plugin kustom yang menyelesaikan task nyata milik Anda sendiri (Module 7), (3) memory persisten yang terbukti bertahan lintas restart (Module 8) dan minimal 1 automation/standing intent yang jalan tanpa trigger manual (Module 10), (4) security review tertulis (permission mode, sandboxing, secrets — Module 13), (5) deployed ke target non-laptop dengan monitoring aktif (Module 14).

Acceptance Criteria:

* Asisten bisa diakses dan dipakai nyata lewat ≥2 channel berbeda secara bersamaan tanpa konflik konteks antar-user
* Minimal 1 skill/plugin kustom terdokumentasi (bukan hanya pakai skill bawaan) dan terbukti berjalan end-to-end
* Memory & session bertahan lintas restart Gateway (dibuktikan, bukan diasumsikan)
* Security review tertulis mencakup threat model dasar OpenClaw sendiri (pesan masuk = untrusted input) dan mitigasi konkret yang diterapkan di instance ini
* Deployment berjalan di luar mesin development (VPS/Docker/Fly.io/Render) dengan minimal 1 jalur monitoring yang bisa mendeteksi downtime
* Retrospektif tertulis (apa yang bekerja/gagal/akan diubah) disimpan bersama hasil di `portofolio/`

Status: Not Started

---

# Overall Progress

Module Completion: 0/15

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Apa itu OpenClaw: local-first vs cloud-first personal assistant
