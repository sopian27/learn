# Course Roadmap

## Course Information

* Course Name: AI Agents — dari 0 sampai Expert
* Category: AI Engineering / Agentic Systems
* Difficulty: Level 0 (Belum paham LLM sama sekali) → Level 4 (Expert Agentic AI Engineer)
* Estimated Duration: 12 modules, ~3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Programmer berpengalaman (Java/Spring Boot dari repo ini), **belum** pernah pakai LLM/AI sama sekali — Module 1 mengajarkan fondasi LLM & prompting secukupnya sebelum masuk materi agent-spesifik. Python akan dipakai sebagai bahasa utama (diajarkan secukupnya untuk kebutuhan AI, bukan Python umum, mengikuti pola `courses/llm/roadmap.md`).

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban Anda:

1. **Course berdiri sendiri, bukan lanjutan Module 7 `courses/llm/roadmap.md`.** Course LLM yang sudah ada hanya menyentuh agent selama 5 lesson (1 modul) sebagai bagian dari course LLM yang lebih luas. Karena target Anda "sampai expert", course ini dibuat terpisah dan jauh lebih dalam: arsitektur agent, planning/reasoning strategy, memory, Model Context Protocol (MCP), framework (LangGraph/CrewAI/Agent SDK), multi-agent orchestration, evaluation, security, sampai production. Kedua course independen — tidak perlu menyelesaikan course LLM dulu untuk memulai course ini, karena Module 1 course ini sudah mencakup fondasi LLM yang dibutuhkan.
2. **Dimulai dari fondasi LLM (Module 1)** karena Anda belum pernah pakai LLM/AI sama sekali — tokenization/embedding mendalam (yang ada di `courses/llm/roadmap.md` Module 1-2) **tidak** diulang di sini; Module 1 course ini hanya mengajarkan secukupnya untuk paham agent: apa itu LLM secara intuitif, prompting dasar, dan tool/function calling single-call (fondasi sebelum masuk ke *loop* agentic di Module 2).
3. **Stack: Python murni + diskusi integrasi Spring Boot**, konsisten dengan `courses/llm/roadmap.md` — mayoritas framework agent produksi (LangGraph, CrewAI, OpenAI Agents SDK, Claude Agent SDK) berbasis Python, jadi course mengikuti standar industri apa adanya. Setiap modul yang relevan mendapat sub-bagian **"Production Discussion: Spring Boot Integration"**, sesuai instruksi root `CLAUDE.md`.

Referensi riset yang dipakai menyusun roadmap ini (sintesis, bukan copy-paste):

* [roadmap.sh/ai-agents](https://roadmap.sh/ai-agents) — struktur skill agentic AI dari roadmap.sh (2026 edition)
* [Anthropic — Building Effective AI Agents](https://www.anthropic.com/engineering/building-effective-agents) — pembeda fundamental *workflow* (predefined code path) vs *agent* (LLM yang mengarahkan proses & tool use-nya sendiri secara dinamis); dipakai sebagai kerangka Module 2
* [Anthropic — Writing Effective Tools for AI Agents](https://www.anthropic.com/engineering/writing-tools-for-agents) — prinsip desain tool schema; dipakai sebagai kerangka Module 3
* Perbandingan framework agent 2026 (LangGraph sebagai standar produksi untuk stateful/auditable workflow, CrewAI untuk prototyping multi-agent tercepat, OpenAI Agents SDK dengan pola *handoff*, Claude Agent SDK dengan integrasi MCP terdalam) — dipakai sebagai kerangka Module 8
* Model Context Protocol (MCP) sebagai standar emerging untuk tool/context integration antar agent dan sistem eksternal — dipakai sebagai kerangka Module 7 (relevan langsung karena lingkungan kerja Anda, Claude Code, memakai MCP)

---

# Learning Objectives

* [ ] Paham fondasi LLM & prompting secukupnya untuk mengerti bagaimana agent "berpikir" (tanpa perlu paham arsitektur Transformer mendalam)
* [ ] Paham perbedaan fundamental *workflow* vs *agent*, dan kapan masing-masing adalah pilihan yang tepat (bukan selalu pakai agent)
* [ ] Mahir merancang tool/function calling yang robust untuk agent: schema design, error handling, parallel tool calls
* [ ] Mahir menerapkan strategi planning & reasoning (ReAct, Plan-and-Execute, Reflection, Tree of Thoughts) sesuai kebutuhan task
* [ ] Mampu merancang sistem memory untuk agent (context management, short-term, long-term, vector-backed memory)
* [ ] Paham dan mampu membangun Model Context Protocol (MCP) server/client
* [ ] Mahir memakai framework agent produksi (LangGraph, CrewAI) dan tahu kapan build-from-scratch lebih tepat
* [ ] Mampu merancang sistem multi-agent (supervisor, handoff, swarm) beserta menangani failure mode koordinasinya
* [ ] Mampu mengevaluasi agent secara rigorous (trajectory evaluation, LLM-as-judge, tracing/observability)
* [ ] Paham ancaman keamanan spesifik agent (prompt injection via tool output, sandboxing, human-in-the-loop) dan cara mitigasinya
* [ ] Mampu men-deploy & mengoperasikan agent di production (cost, latency, guardrail, monitoring)
* [ ] Mampu menyelesaikan proyek portofolio: sistem multi-agent end-to-end yang production-ready

---

# Course Modules

## Module 1 — Fondasi: LLM, Prompting & Tool Calling Dasar

Description: Modul prasyarat karena Anda belum pernah pakai LLM/AI. Cukup dalam untuk mengerti bagaimana agent "berpikir" di Module 2, tanpa masuk ke detail training/arsitektur Transformer (itu domain `courses/llm/roadmap.md` kalau suatu saat ingin didalami).

Lessons:

* [ ] Setup environment AI (Python venv, package manager `uv`/`pip`, Anthropic SDK)
* [ ] Apa itu LLM secara intuitif: next-token prediction, kenapa ia bisa "terlihat" berpikir
* [ ] Prompting dasar: zero-shot, few-shot, system prompt, structured output
* [ ] Tool/function calling single-call: cara model memilih & memanggil fungsi eksternal (belum berupa loop)

Mini Project: Panggil Claude API dari Python dengan 1 tool (kalkulator) — kirim prompt, model memutuskan pakai tool atau tidak, parse & tampilkan hasil.

Status: Not Started

---

## Module 2 — Apa Itu Agent, Sebenarnya

Description: Kerangka mental paling penting di seluruh course, diambil dari framework Anthropic: *workflow* (LLM+tools diorkestrasi lewat jalur kode yang sudah ditentukan) vs *agent* (LLM yang mengarahkan proses & tool use-nya sendiri secara dinamis). Tanpa modul ini, mudah salah kaprah "agent" hanya berarti "pakai LLM dalam loop while".

Lessons:

* [ ] Workflow vs Agent: definisi, contoh konkret, kapan masing-masing tepat dipakai
* [ ] Agentic loop: perceive → plan → act → observe, dan kenapa loop ini yang membedakan agent dari single-call biasa
* [ ] ReAct pattern (Reasoning + Acting) — dibangun dari nol pakai raw Python, tanpa framework, supaya mekanismenya tidak jadi "kotak hitam"
* [ ] Trade-off agent: reliability vs fleksibilitas, cost vs kemampuan menangani task tak terduga

Mini Project: Bangun ReAct loop dari nol (tanpa framework apa pun) yang bisa memakai 1 tool untuk menyelesaikan task multi-step sederhana (mis. "cari tahu X lalu hitung Y").

Status: Not Started

---

## Module 3 — Tool Use & Function Calling Tingkat Lanjut

Description: Tool adalah "tangan dan mata" agent — kualitas tool yang buruk adalah penyebab #1 agent gagal di production. Berdasarkan prinsip desain tool Anthropic.

Lessons:

* [ ] Merancang tool schema yang baik (nama, deskripsi, parameter) — kenapa deskripsi tool sama pentingnya dengan prompt
* [ ] Error handling & retry pada tool call (tool gagal, timeout, hasil kosong)
* [ ] Parallel vs sequential tool calls, dan kapan masing-masing dipakai
* [ ] Menangani hallucinated tool arguments & validasi input sebelum eksekusi

Mini Project: Bangun toolkit 3-4 tools nyata (mis. web search, kalkulator, baca file, cek cuaca) dengan error handling yang proper, mengikuti semangat `standards/ERROR_HANDLING_STANDARDS.md`.

Status: Not Started

---

## Module 4 — Planning & Reasoning Strategies

Description: ReAct di Module 2 baru satu dari beberapa strategi. Modul ini membandingkan strategi planning agar Anda bisa memilih sesuai kebutuhan task, bukan selalu pakai satu pola.

Lessons:

* [ ] Recap ReAct + batasannya (long-horizon task, akumulasi error)
* [ ] Plan-and-Execute: rencana di-generate di awal, dieksekusi bertahap
* [ ] Reflection / self-critique loop: agent mengevaluasi hasil kerjanya sendiri
* [ ] Tree of Thoughts / multi-path reasoning untuk task dengan banyak kemungkinan solusi
* [ ] Memilih strategi berdasarkan trade-off cost vs quality vs latency

Mini Project: Selesaikan task multi-step yang sama dengan 2 strategi berbeda, ukur & bandingkan success rate serta cost (jumlah token/API call).

Status: Not Started

---

## Module 5 — Memory Systems untuk Agent

Description: Agent tanpa memory yang baik akan mengulang kesalahan yang sama atau kehabisan context window di task panjang.

Lessons:

* [ ] Context window management: compaction & summarization saat riwayat percakapan/tool call menumpuk
* [ ] Short-term memory (working memory dalam 1 sesi) vs long-term memory (antar sesi)
* [ ] Episodic memory vs semantic memory, dan kapan agent butuh yang mana
* [ ] Vector store untuk long-term memory (menyambung ke konsep RAG)
* [ ] Pola state management agent (mirip state machine, bukan sekadar riwayat chat)

Mini Project: Bangun agent dengan long-term memory yang mengingat preferensi user antar sesi (mis. asisten yang ingat gaya kerja Anda dari sesi sebelumnya).

Status: Not Started

---

## Module 6 — Agentic RAG

Description: RAG biasa mengambil dokumen sekali lalu jawab. Agentic RAG membuat agent *memutuskan sendiri* kapan perlu retrieve, apa yang dicari, dan kapan cukup.

Lessons:

* [ ] Kapan agent perlu retrieve vs cukup jawab dari pengetahuan/context yang sudah ada
* [ ] Iterative retrieval: retrieve → evaluasi hasil → retrieve ulang dengan query berbeda kalau belum cukup
* [ ] Query reformulation oleh agent (agent menulis ulang query pencarian sendiri)
* [ ] Agentic RAG vs RAG statis: kapan kompleksitas tambahan ini sepadan

Mini Project: Bangun agent yang memutuskan sendiri kapan perlu search dokumen (mis. isi `standards/` repo ini sebagai knowledge base) vs kapan cukup jawab langsung.

Status: Not Started

---

## Module 7 — Model Context Protocol (MCP)

Description: Standar emerging untuk menghubungkan agent ke tools/data eksternal secara terstandarisasi — relevan langsung karena lingkungan kerja Anda (Claude Code) memakai MCP untuk semua integrasi eksternalnya.

Lessons:

* [ ] Kenapa MCP muncul: masalah "N agent x M tool integration" yang coba diselesaikan
* [ ] Arsitektur MCP: client-server, primitives (resources, tools, prompts)
* [ ] Membangun MCP server sendiri (expose 1 kapabilitas custom, mis. wrapper ke internal API/database)
* [ ] Mengonsumsi MCP server dari agent/Claude

Mini Project: Bangun MCP server custom dan hubungkan ke Claude — verifikasi agent bisa memakai kapabilitas custom tersebut.

**Production Discussion: Spring Boot Integration** — pola umum expose service Spring Boot internal sebagai MCP server, supaya agent bisa memanggil API internal perusahaan secara terstandarisasi tanpa integrasi ad-hoc per agent.

Status: Not Started

---

## Module 8 — Agent Framework dalam Praktik

Description: Module 2-7 dibangun manual agar mekanismenya tidak jadi kotak hitam. Modul ini memperkenalkan framework produksi dan kapan pakai yang mana.

Lessons:

* [ ] Kapan build-from-scratch (Module 2) masih masuk akal vs kapan wajib pakai framework
* [ ] LangGraph — arsitektur graph-based, stateful, standar produksi untuk workflow agent yang auditable
* [ ] CrewAI — role-based multi-agent, prototyping tercepat
* [ ] OpenAI Agents SDK & Claude Agent SDK — pola *handoff*, integrasi MCP native
* [ ] Kriteria memilih framework: kontrol vs kecepatan development vs ekosistem

Mini Project: Reimplementasikan agent dari Module 3 memakai LangGraph, lalu bandingkan trade-off (baris kode, observability bawaan, kemudahan debug) dengan versi from-scratch.

Status: Not Started

---

## Module 9 — Multi-Agent Orchestration

Description: Kapan satu agent tidak cukup, dan bagaimana banyak agent bekerja sama tanpa saling menjegal.

Lessons:

* [ ] Kapan 1 agent cukup vs kapan perlu banyak agent (kompleksitas yang sepadan, bukan default)
* [ ] Pola supervisor/orchestrator: 1 agent koordinator mendelegasikan ke agent spesialis
* [ ] Pola handoff (ala OpenAI Agents SDK): agent transfer kontrol dengan membawa context
* [ ] Pola swarm/peer-to-peer: agent berkoordinasi tanpa hierarki tetap
* [ ] Failure mode koordinasi: deadlock, kerja duplikat, context hilang antar agent

Mini Project: Bangun sistem multi-agent (mis. researcher + writer + reviewer) untuk menyelesaikan task pembuatan konten end-to-end.

Status: Not Started

---

## Module 10 — Evaluation & Observability

Description: Yang membedakan demo dan sistem yang bisa dipercaya production — evaluasi bukan cuma output akhir, tapi seluruh trajectory keputusan agent.

Lessons:

* [ ] Trajectory evaluation: menilai *proses* keputusan agent, bukan cuma jawaban akhir
* [ ] LLM-as-judge untuk menilai kualitas reasoning & tool selection
* [ ] Tracing agent (call chain plan→act→observe), mengikuti semangat `standards/OBSERVABILITY_STANDARDS.md`
* [ ] Taksonomi failure mode: infinite loop, tool misuse, hallucinated args, context drift

Mini Project: Bangun eval harness untuk sistem multi-agent dari Module 9 — ukur task success rate, tool-call accuracy, dan jumlah langkah tak perlu.

Status: Not Started

---

## Module 11 — Agent Security & Safety

Description: Agent yang bisa bertindak (bukan cuma menjawab) membuka permukaan serangan baru yang tidak ada di chatbot biasa.

Lessons:

* [ ] Prompt injection via tool output (mis. hasil web search/scrape berisi instruksi tersembunyi)
* [ ] Sandboxing eksekusi tool (terutama code execution & file system access)
* [ ] Human-in-the-loop approval untuk aksi berisiko/ireversibel
* [ ] Permission scoping & least privilege untuk tool access, menyambung ke `standards/SECURITY_STANDARDS.md`
* [ ] Red-teaming agent sendiri: teknik dasar mencoba "membobol" agent Anda sendiri

Mini Project: Red-team salah satu agent yang sudah dibuat (coba prompt injection lewat tool result), lalu implementasikan mitigasinya.

Status: Not Started

---

## Module 12 — Production Deployment & Menjadi Agentic AI Engineer

Description: Level di mana Anda bisa memimpin keputusan arsitektur agent, bukan cuma implementasi — mengoperasikan agent secara efisien dan reliable di production.

Lessons:

* [ ] Cost & latency management di production (agent bisa memanggil LLM berkali-kali per task — biaya bisa meledak tanpa kontrol)
* [ ] Guardrails & kill-switch untuk agent yang berjalan tak terduga
* [ ] Deployment (Docker), monitoring, dan strategi rollback untuk sistem agent
* [ ] Studi kasus: bagaimana Claude Code, Devin, Cursor Agent, dan sistem agent internal Anthropic/OpenAI didesain
* [ ] Menyusun "Agent System Design Doc" (arsitektur, trade-off, risk assessment, cost projection)

**Production Discussion: Spring Boot Integration** — pola umum: Spring Boot sebagai orchestrator/API gateway yang menangani auth & rate limiting, task agent long-running dijalankan async lewat message queue (`standards/MESSAGING_STANDARDS.md`, Kafka), hasil di-poll atau di-push balik ke client.

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Full Agentic AI Engineer Simulation** — bangun 1 sistem multi-agent production-grade yang menggabungkan seluruh course: (1) minimal 2 agent terspesialisasi dengan pola orchestration/handoff (Module 9), (2) tool use robust dengan error handling (Module 3) termasuk minimal 1 tool via MCP server custom (Module 7), (3) strategi planning yang sesuai task (Module 4) dan long-term memory (Module 5), (4) agentic RAG di atas knowledge base nyata (Module 6), (5) eval harness otomatis untuk trajectory & tool-call accuracy (Module 10), (6) guardrail keamanan: sandboxing + human-in-the-loop untuk aksi berisiko + mitigasi prompt injection (Module 11), (7) deployment dasar (Docker) dengan cost & latency tracking (Module 12), (8) 1 halaman "Agent System Design Doc".

Acceptance Criteria:

* Sistem multi-agent menyelesaikan task end-to-end tanpa infinite loop atau kerja duplikat antar agent
* Tool call (termasuk lewat MCP) berhasil dengan error handling yang proper, bukan silent failure
* Eval harness menghasilkan angka terukur (success rate, tool-call accuracy, jumlah langkah) — bukan penilaian subjektif
* Minimal 1 aksi berisiko melalui human-in-the-loop approval sebelum dieksekusi
* Guardrail menolak/menangani minimal 2 skenario prompt injection yang disiapkan
* Sistem berjalan via Docker dengan cost & latency tracking dasar
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Setup environment AI (Python venv, package manager, Anthropic SDK)
