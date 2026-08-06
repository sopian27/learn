# Course Roadmap

## Course Information

* Course Name: Large Language Models — dari 0 sampai Mahir (Applied/Product Engineer Track)
* Category: AI Engineering / Applied Machine Learning
* Difficulty: Level 1 (Pemula di ML/AI) → Level 4 (Expert Applied AI Engineer)
* Estimated Duration: 10 modules, ~2-5 lessons each (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Sudah terbiasa programming (termasuk Java/Spring Boot dari repo ini), **belum** pernah ML/AI — Python akan diajarkan dari dasar khusus untuk kebutuhan AI (bukan Python umum)

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban Anda:

1. **Level**: Programmer berpengalaman, baru di ML/AI → course dimulai dari Python-for-AI + intuisi cara kerja LLM, **bukan** dari nol pemrograman, dan matematika (aljabar linear, probabilitas) diajarkan secukupnya secara intuitif/visual, bukan pembuktian formal.
2. **Tujuan**: Applied/Product Engineer → fokus **memakai, mengintegrasikan, mengevaluasi, dan men-deploy** LLM (prompt engineering, RAG, agents, fine-tuning ringan/LoRA, evaluation, production). **Bukan** riset pretraining model dari nol, scaling laws, atau training infrastruktur skala besar (disinggung sekilas di Module 2 & 9 sebagai konteks, tidak dipraktikkan).
3. **Stack**: Python murni untuk seluruh materi LLM (PyTorch, Hugging Face, provider SDK seperti Anthropic/OpenAI, vector DB). Mengikuti standar industri & referensi resmi apa adanya. Karena `standards/` repo ini fokus Java/Spring Boot, setiap modul yang relevan menambahkan sub-bagian singkat **"Production Discussion: Spring Boot Integration"** — bukan implementasi paralel, hanya diskusi bagaimana service Python ini biasanya diekspos/dikonsumsi dari backend Java di dunia nyata (API gateway, message queue, dsb) — konsisten dengan instruksi root `CLAUDE.md` soal Spring Boot.

Referensi riset yang dipakai menyusun roadmap ini: [roadmap.sh/ai-engineer](https://roadmap.sh/ai-engineer), [Hugging Face LLM Course](https://huggingface.co/learn/llm-course/chapter1/1), buku *AI Engineering* — Chip Huyen (struktur Evaluation → Prompt Engineering → RAG & Agents → Fine-tuning → Dataset Engineering → Inference Optimization → Architecture), dan [promptingguide.ai](https://www.promptingguide.ai/).

---

# Learning Objectives

* [ ] Paham cara kerja LLM secara intuitif: tokenization, embedding, attention/Transformer, inference — cukup dalam untuk debugging & keputusan desain, tanpa perlu menurunkan matematika training dari nol
* [ ] Mahir prompt engineering tingkat produksi: few-shot, chain-of-thought, structured output, tool/function calling, defense terhadap prompt injection
* [ ] Mampu membangun sistem RAG production-grade: chunking, retrieval, reranking, evaluasi kualitas retrieval
* [ ] Mampu melakukan fine-tuning ringan (LoRA/QLoRA) untuk task spesifik dan tahu kapan fine-tune vs RAG vs prompt engineering adalah pilihan tepat
* [ ] Mampu membangun AI agents: tool use, planning, memory, multi-agent orchestration, dan menangani failure mode-nya
* [ ] Mampu mengevaluasi sistem LLM secara rigorous (metrics, LLM-as-judge, human eval) dan menerapkan guardrail keamanan/safety
* [ ] Mampu men-deploy & mengoperasikan sistem LLM di production: cost, latency, caching, observability, arsitektur end-to-end
* [ ] Mampu menyelesaikan proyek portofolio: aplikasi LLM end-to-end (RAG + Agent + Evaluation + Guardrail) yang production-ready

---

# Course Modules

## Module 1 — Python for AI & Intuisi Cara Kerja LLM

Description: Python secukupnya untuk AI (bukan Python umum — venv, pip, Jupyter, NumPy, requests async), lalu membangun mental model: LLM sebagai "mesin prediksi token berikutnya", bukan sihir. Neural network & Transformer diperkenalkan sebagai intuisi visual, bukan derivasi matematis.

Lessons:

* [ ] Setup environment AI (Python venv, Jupyter/VS Code, package manager `uv`/`pip`)
* [ ] NumPy essentials untuk AI (vector, matrix, dot product — hanya yang dipakai LLM)
* [ ] Apa itu LLM sebenarnya: statistical language model, next-token prediction, autoregressive generation
* [ ] Intuisi neural network & Transformer (tanpa backprop matematis — pakai visualisasi)

Mini Project: Panggil LLM API pertama (Anthropic Claude) dari Python script — kirim prompt, terima & parse response, handle error dasar.

Status: Not Started

---

## Module 2 — Tokenization, Embeddings & Arsitektur Transformer

Description: Membuka "kotak hitam" LLM secukupnya untuk applied engineer — cukup dalam untuk memahami kenapa model punya context window limit, kenapa biaya dihitung per token, kenapa embedding similarity bekerja.

Lessons:

* [ ] Tokenization (BPE/WordPiece) — kenapa "strawberry" susah dieja model
* [ ] Embeddings & vector space — cosine similarity, semantic search dasar
* [ ] Attention mechanism & arsitektur Transformer (conceptual walkthrough, bukan implementasi from scratch)
* [ ] Parameter model, context window, inference: temperature, top-p, top-k, sampling
* [ ] *(Konteks, tidak dipraktikkan)* Sekilas pretraining & scaling laws — kenapa GPT-4 beda dengan GPT-2, kenapa training from-scratch di luar scope applied engineer

Mini Project: Eksperimen tokenizer (hitung token, lihat split aneh) + visualisasi embedding similarity antar kalimat.

Status: Not Started

---

## Module 3 — Prompt Engineering Tingkat Produksi

Description: Skill dengan ROI tertinggi di applied AI engineering — menguasai ini sebelum masuk ke RAG/agent.

Lessons:

* [ ] Zero-shot, few-shot, chain-of-thought prompting
* [ ] System prompt design, structured output (JSON mode/schema-constrained output)
* [ ] Tool/function calling — cara model "memanggil" kode eksternal
* [ ] Prompt patterns lanjutan: ReAct, self-consistency, reflection
* [ ] Prompt injection & defense dasar (security mindset — nyambung ke `standards/SECURITY_STANDARDS.md`)

Mini Project: Bangun prompt library terstruktur (versioned, testable) untuk 1 use case nyata (mis. customer support classifier).

Status: Not Started

---

## Module 4 — Bekerja dengan LLM API & SDK di Production

Description: Detail teknis memakai provider API secara robust — bukan sekadar "hello world" call.

Lessons:

* [ ] Anthropic/OpenAI SDK: streaming, retries, timeout, structured output
* [ ] Cost & token management, rate limiting, prompt caching provider
* [ ] Model open-source & local inference (Hugging Face Inference, Ollama) — kapan pakai API vs self-host
* [ ] Error handling & observability dasar untuk LLM call (logging request/response, tracing)

Mini Project: Bangun CLI tool yang membungkus LLM API dengan streaming, retry, dan error handling proper (mengikuti semangat `standards/ERROR_HANDLING_STANDARDS.md`).

**Production Discussion: Spring Boot Integration** — bagaimana CLI/service Python ini biasanya dibungkus jadi internal API dan dipanggil dari service Spring Boot (REST client, timeout budget, circuit breaker via Resilience4j).

Status: Not Started

---

## Module 5 — Embeddings & Retrieval-Augmented Generation (RAG)

Description: Pola arsitektur paling umum untuk "menyambungkan" LLM ke data privat/terkini.

Lessons:

* [ ] Vector database (Chroma untuk belajar, lalu pgvector/Pinecone untuk production)
* [ ] Chunking strategies (fixed-size, semantic, recursive) & trade-off-nya
* [ ] Retrieval algorithms: dense, sparse (BM25), hybrid search, reranking
* [ ] RAG architecture end-to-end & evaluasi kualitas retrieval (precision/recall konteks)
* [ ] RAG beyond text — sekilas multimodal (image/table retrieval)

Mini Project: Bangun RAG chatbot dari dokumen sendiri (mis. isi `standards/` repo ini sebagai knowledge base).

**Production Discussion: Spring Boot Integration** — pgvector di PostgreSQL yang sama dengan `standards/DATABASE_STANDARDS.md`, kenapa banyak tim menaruh vector search di database yang sudah ada daripada menambah vector DB baru.

Status: Not Started

---

## Module 6 — Fine-tuning & Model Adaptation

Description: Kapan fine-tuning benar-benar diperlukan (setelah prompt engineering & RAG mentok), dan cara melakukannya secara efisien dengan resource terbatas.

Lessons:

* [ ] Decision framework: prompt engineering vs RAG vs fine-tuning — kapan masing-masing dipakai
* [ ] Supervised fine-tuning dasar, dataset engineering ringan (kualitas > kuantitas)
* [ ] LoRA & QLoRA — fine-tune efisien di GPU terbatas/consumer hardware
* [ ] Evaluasi hasil fine-tuning (before/after benchmark)

Mini Project: Fine-tune model kecil open-source (mis. Llama/Qwen kelas kecil) via Hugging Face + PEFT untuk task spesifik (klasifikasi/format output konsisten).

Status: Not Started

---

## Module 7 — AI Agents

Description: LLM yang bisa bertindak — memanggil tools, merencanakan multi-step task, dan menyimpan memory.

Lessons:

* [ ] Agent overview: loop plan → act → observe
* [ ] Tools & function calling di konteks agent (bukan single-call seperti Module 3)
* [ ] Planning strategies & memory (short-term vs long-term)
* [ ] Agent failure modes & cara mengevaluasinya (infinite loop, tool misuse, hallucinated tool args)
* [ ] Multi-agent orchestration dasar (kapan 1 agent cukup vs perlu banyak agent)

Mini Project: Bangun agent yang bisa pakai minimal 2 tools nyata (mis. web search + calculator/code execution) untuk menyelesaikan task multi-step.

Status: Not Started

---

## Module 8 — Evaluation, Safety & Observability

Description: Yang membedakan prototype dan sistem yang bisa dipercaya production — evaluasi rigorous dan guardrail keamanan.

Lessons:

* [ ] Cara mengevaluasi sistem LLM: metrics otomatis, LLM-as-judge, human evaluation
* [ ] Hallucination detection & mitigasi
* [ ] Bias, alignment dasar, red-teaming ringan
* [ ] Guardrails & content moderation (input/output filtering)
* [ ] Observability untuk LLM system (tracing call chain RAG/agent, mirip semangat `standards/OBSERVABILITY_STANDARDS.md`)

Mini Project: Bangun eval harness untuk RAG chatbot (Module 5) & agent (Module 7) yang sudah dibuat — ukur akurasi retrieval, groundedness jawaban, dan tool-call success rate.

Status: Not Started

---

## Module 9 — Production Architecture & Inference Optimization

Description: Mengoperasikan sistem LLM secara efisien dan reliable dalam skala production.

Lessons:

* [ ] Inference optimization: batching, caching (exact & semantic cache), quantization
* [ ] Cost vs latency vs quality trade-off — cara memilih model per use case (bukan selalu pakai model terbesar)
* [ ] Arsitektur sistem AI end-to-end: gateway, caching layer, feedback loop, fallback antar model
* [ ] Deployment: containerization (Docker), scaling, monitoring, rollback strategy

Mini Project: Desain (dan implementasikan sebagian) arsitektur production untuk sistem LLM dari Module 5-8, termasuk caching layer & fallback.

**Production Discussion: Spring Boot Integration** — pola umum: Spring Boot sebagai API gateway/BFF yang menangani auth, rate limiting, dan orchestrasi, sementara service Python menangani model inference — dengan Kafka (`standards/MESSAGING_STANDARDS.md`) untuk workload async/batch.

Status: Not Started

---

## Module 10 — Menjadi Applied AI Engineer yang Dipercaya

Description: Level di mana Anda bisa memimpin keputusan arsitektur AI, bukan cuma implementasi.

Lessons:

* [ ] Studi kasus: bagaimana Anthropic, OpenAI, Google, Stripe, GitHub (Copilot) mendesain sistem AI production mereka
* [ ] Menyusun AI system design doc (trade-off model, cost projection, risk assessment)
* [ ] Membangun evaluation & feedback loop culture dalam tim
* [ ] Roadmap belajar lanjutan (opsional) bila nanti tertarik ke arah ML/Research Engineer track (pretraining, RLHF, distributed training)

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Full Applied AI Engineer Simulation** — bangun 1 aplikasi LLM end-to-end yang menggabungkan seluruh course: (1) RAG chatbot di atas knowledge base nyata, (2) minimal 1 agent dengan tool use untuk task yang tidak bisa dijawab RAG saja, (3) guardrail keamanan (prompt injection defense + content moderation), (4) eval harness otomatis (retrieval quality + groundedness + tool success rate), (5) deployment dasar (Docker) dengan caching & cost tracking, (6) 1 halaman "AI System Design Doc" yang menjelaskan trade-off arsitektur.

Acceptance Criteria:

* RAG menjawab benar berdasarkan dokumen sumber (bukan hallucinated) untuk minimal 90% pertanyaan test set
* Agent berhasil menyelesaikan task multi-step dengan tool use tanpa infinite loop
* Guardrail menolak/menangani minimal 3 skenario prompt injection yang disiapkan
* Eval harness menghasilkan angka terukur, bukan penilaian subjektif
* Sistem berjalan via Docker dengan logging/tracing dasar
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Setup environment AI (Python venv, Jupyter/VS Code, package manager)
