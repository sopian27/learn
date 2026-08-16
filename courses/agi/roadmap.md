# Course Roadmap

## Course Information

* Course Name: AGI (Artificial General Intelligence) — dari 0 sampai Expert
* Category: AI Research & Systems / Capstone Synthesis Course
* Difficulty: Level 4 (sudah applied AI engineer) → Level 5 (paham lanskap riset AGI: teori, arsitektur, evaluasi, alignment — cukup dalam untuk berdiskusi dengan riset terkini, bukan cuma memakai API)
* Estimated Duration: 12 modules, ~3-5 lessons per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: **Wajib menyelesaikan ketiganya** — `courses/ml-fundamentals/roadmap.md` (matematika ML, neural network dari nol, deep learning dasar), `courses/llm/roadmap.md` (Transformer, tokenization/embedding, prompting, RAG, fine-tuning), dan `courses/agents/roadmap.md` (agentic loop, tool use, planning strategy level-prompt, memory untuk LLM agent, MCP, multi-agent orchestration). Course ini **tidak** mengajarkan ulang materi itu — course ini adalah lapisan sintesis di atasnya (lihat "Catatan Scope").

---

# Catatan Scope (baca dulu sebelum approve)

## Posisi course ini

`ml-fundamentals` → `llm` → `agents` sudah membentuk "full path" applied AI engineering di repo ini (lihat `progress/roadmap.md`). Ketiganya mengajarkan **cara membangun sistem AI yang bekerja** (model ML, aplikasi LLM, agent production-grade). Course ini **bukan** lanjutan applied keempat — ini adalah **capstone/synthesis course** yang duduk di atas ketiganya, menjawab pertanyaan yang sengaja tidak dijawab tiga course itu: *apa itu "general intelligence" sebenarnya, bagaimana mengukurnya, arsitektur apa yang diusulkan untuk mencapainya, dan bagaimana membuatnya aman* — riset-heavy (paper DeepMind/OpenAI/Anthropic/Meta, bukan spekulasi sci-fi), bukan tutorial coding sekuensial.

## Peta No-Duplication (topik AGI vs. yang sudah diajarkan)

| Topik di brief AGI | Sudah diajarkan di mana? | Apa yang BARU di course ini |
|---|---|---|
| AI fundamentals | `ml-fundamentals` M1 (hierarki AI/ML/DL/LLM, use case industri) | Sejarah & filosofi AI: symbolic AI (GOFAI) vs connectionism, AI winters, "bitter lesson" — **tidak** diajarkan di `ml-fundamentals` sama sekali |
| Teori intelijensi | Tidak ada di ketiga course | **100% baru**: psychometric g/CHC theory, Turing Test, Chinese Room, Legg-Hutter universal intelligence (AIXI) |
| Learning paradigms | `ml-fundamentals` M5-M9 (supervised, unsupervised, neural net, deep learning) | **Baru**: self-supervised learning at scale, meta-learning, few-shot/continual learning, catastrophic forgetting — paradigma di luar supervised/unsupervised klasik |
| Reasoning | `llm` M3 (CoT prompting), `agents` M2/M4 (ReAct, Plan-and-Execute, ToT) — **level prompting/aplikasi** | **Baru**: System 1 vs System 2, reasoning model & test-time compute scaling (o1/o3-style), neurosymbolic reasoning, debat "apakah ini reasoning asli" |
| Planning | `agents` M4 — **level orkestrasi agent LLM** (strategi prompting) | **Baru**: classical AI planning (STRIPS, search, HTN), Monte Carlo Tree Search, model-based planning (AlphaZero/MuZero) — akar riset yang berbeda dari planning ala agent LLM |
| Memory systems | `agents` M5 — **level engineering** (context window, vector store, short/long-term untuk agent LLM) | **Baru**: teori memori kognitif (working/episodic/semantic dari cognitive science), cognitive architecture (SOAR, ACT-R) |
| Agentic architectures | `agents` seluruh course — **level software engineering production** | **Baru**: cognitive architecture riset (bukan framework produksi) dan bagaimana arsitektur modular LeCun (JEPA) berbeda dari agent LLM biasa |
| Multimodal AI | `llm` M5 (disinggung sekilas untuk RAG saja) | **Baru**: vision-language model, multimodal foundation model, embodied AI/robotics, grounding sebagai syarat general intelligence |
| World models | Tidak ada di ketiga course | **100% baru**: JEPA, Genie, V-JEPA, model-based RL, world model vs LLM-sebagai-world-model |
| Reinforcement learning | Tidak ada di ketiga course (ml-fundamentals hanya supervised/unsupervised) | **100% baru**: MDP, Q-learning, policy gradient, deep RL, AlphaGo/MuZero, RLHF/RLAIF sebagai mekanisme (bukan cuma disebut nama) |
| Alignment | `llm` M8 (1 bullet "alignment dasar, red-teaming ringan"), `agents` M11 (security: prompt injection, sandboxing) — **level defensive engineering** | **Baru & jauh lebih dalam**: outer/inner alignment, specification gaming, deceptive alignment, scalable oversight, mechanistic interpretability, Responsible Scaling Policy/ASL |
| Evaluation/benchmarking | `llm` M8, `agents` M10 — **level eval sistem LLM/agent spesifik** (groundedness, tool-call accuracy) | **Baru**: benchmark general intelligence (ARC-AGI, METR time horizon, GAIA), DeepMind "Levels of AGI", OpenAI "5 Levels", Goodhart's law pada benchmark |
| AGI architecture proposals/debates | Tidak ada di ketiga course | **100% baru**: scaling hypothesis vs modular/neurosymbolic, perbandingan taruhan riset OpenAI/DeepMind/Anthropic/Meta-AMI Labs, takeoff scenarios, timeline forecasting |

**Kesimpulan**: Zero overlap konten. Course ini secara eksplisit **mengasumsikan** kemampuan applied (bisa pakai LLM, bangun RAG, bangun agent) sebagai given, dan membangun lapisan teori/riset/evaluasi/safety di atasnya.

## Referensi riset yang dipakai menyusun roadmap ini

* [DeepMind — "Levels of AGI for Operationalizing Progress on the Path to AGI"](https://arxiv.org/pdf/2311.02462) (Morris, Sohl-Dickstein, Legg, dkk., 2024) — kerangka level performance/generality/autonomy, dipakai sebagai struktur Module 10
* [OpenAI — Five Levels of AGI](https://www.forbes.com/sites/jodiecook/2024/07/16/openais-5-levels-of-super-ai-agi-to-outperform-human-capability/) (Chatbots → Reasoners → Agents → Innovators → Organizations, 2024) — dibandingkan dengan kerangka DeepMind di Module 10
* [ARC Prize / ARC-AGI](https://arcprize.org/arc-agi) (Chollet, dkk.) — ARC-AGI-1/2/3, dipakai sebagai kerangka utama Module 10; progres 2025-2026 (GPT-5.2 53% → 97.9% di ARC-AGI-2, ARC-AGI-3 merontokkan kembali semua model frontier) dipakai sebagai bukti bahwa benchmark yang "solved" cepat digantikan versi lebih sulit
* [METR — Measuring AI Ability to Complete Long Tasks](https://metr.org/blog/2025-03-19-measuring-ai-ability-to-complete-long-tasks/) & [Time Horizon 1.1](https://metr.org/blog/2026-1-29-time-horizon-1-1/) — metodologi "50% time horizon" dan tren doubling ~7 bulan (2019-2025), dipakai di Module 10
* [Anthropic — Core Views on AI Safety](https://www.anthropic.com/news/core-views-on-ai-safety) — kerangka skenario optimis/menengah/pesimis untuk alignment, dipakai sebagai struktur Module 11
* [Legg & Hutter — Universal Intelligence: A Definition of Machine Intelligence](https://arxiv.org/abs/0712.3329) — definisi formal intelligence berbasis algorithmic information theory, dipakai di Module 1
* [LeCun — A Path Towards Autonomous Machine Intelligence](https://openreview.net/pdf?id=BZ5a1r-kVsf) & perkembangan JEPA/V-JEPA/AMI Labs (LeCun keluar dari Meta akhir 2025, mendirikan AMI Labs 2026 dengan fokus world model) — dipakai sebagai studi kasus utama Module 6 & 9
* Perkembangan mechanistic interpretability 2025-2026 (Gemma Scope 2 DeepMind, penggunaan interpretability Anthropic pada pre-deployment safety assessment Claude) — dipakai di Module 11

---

# Learning Objectives

* [ ] Paham minimal 3 definisi formal intelligence (psychometric, Turing Test/behavioral, Legg-Hutter/algorithmic) dan bisa menjelaskan kenapa "AGI" tidak punya satu definisi yang disepakati semua orang
* [ ] Paham reinforcement learning dari fondasi (MDP, value function, policy gradient) sampai milestone deep RL (AlphaGo, MuZero) dan bagaimana RLHF/RLAIF menghubungkannya ke alignment LLM
* [ ] Paham learning paradigm di luar supervised/unsupervised: self-supervised learning at scale, meta-learning, continual learning, dan trade-off masing-masing
* [ ] Mampu membedakan reasoning "level prompting" (CoT/ReAct yang sudah dikuasai) dari reasoning model modern (test-time compute scaling) dan tahu bukti empiris batasan reasoning LLM saat ini (ARC-AGI)
* [ ] Paham classical AI planning (search, STRIPS, MCTS) dan model-based planning — akar riset planning yang berbeda dari orkestrasi agent LLM
* [ ] Paham world models (JEPA dan turunannya) sebagai paradigma alternatif terhadap "scale transformer lebih besar", termasuk taruhan riset LeCun/AMI Labs vs OpenAI/Anthropic
* [ ] Mampu membandingkan minimal 3 proposal arsitektur AGI (scaling hypothesis, modular/JEPA, neurosymbolic) dengan argumen for/against masing-masing, bukan memihak buta
* [ ] Mampu menjelaskan mekanisme alignment secara teknis (RLHF/RLAIF/Constitutional AI, scalable oversight, mechanistic interpretability) dan risiko spesifik (specification gaming, deceptive alignment) — bukan cuma "AI harus aman" level slogan
* [ ] Mampu mengevaluasi klaim "progress menuju AGI" secara kritis memakai benchmark nyata (ARC-AGI, METR time horizon) dan kerangka level (DeepMind, OpenAI) — termasuk mengenali Goodhart's law saat benchmark di-game
* [ ] Mampu menyusun argumen tertulis yang koheren dan berbasis bukti tentang "apa yang masih kurang menuju AGI dan bagaimana keamanannya" — capstone sintesis seluruh course

---

# Course Modules

## Module 1 — Peta Besar: Teori Intelijensi & Sejarah AI

Description: Sebelum bicara "AGI", harus jelas dulu apa itu "intelligence" dan "general" — course ini dimulai dari filosofi & sejarah yang sengaja dilewati tiga course sebelumnya (yang langsung masuk ke "cara pakai/bangun AI").

Lessons:

* [ ] Sejarah AI: symbolic AI/GOFAI vs connectionism, AI winters, kenapa "bitter lesson" (Rich Sutton) jadi argumen dominan era LLM
* [ ] Teori intelijensi psikometrik: general intelligence factor (g), CHC theory — asal-usul cara manusia mengukur "kecerdasan" sebelum era AI
* [ ] Turing Test & Chinese Room (Searle) — behavioral definition vs argumen "meniru ≠ memahami"
* [ ] Legg & Hutter — Universal Intelligence: definisi formal "kemampuan mencapai tujuan di berbagai environment" (dasar teoretis AIXI)
* [ ] Kenapa "AGI" tidak punya satu definisi disepakati — survei >70 definisi yang dipakai Legg & Hutter sebagai starting point

Mini Project: Tulis analisis komparatif — ambil 1 model frontier saat ini, evaluasi lewat 3 lensa berbeda (psikometrik, Turing Test, Legg-Hutter), tunjukkan kenapa kesimpulan "seberapa general" bisa berbeda tergantung definisi yang dipakai.

Status: Not Started

---

## Module 2 — Reinforcement Learning: Belajar dari Reward

Description: RL sama sekali tidak disentuh di `ml-fundamentals` (fokus supervised/unsupervised) maupun `llm`/`agents` (fokus prompting & orchestration). Modul ini membangun fondasi RL dari nol karena RL adalah tulang punggung RLHF (alignment, Module 11), AlphaGo/MuZero (planning, Module 5), dan sebagian arsitektur AGI yang diusulkan.

Lessons:

* [ ] Markov Decision Process (MDP): state, action, reward, policy, value function
* [ ] Model-free RL: Q-learning, policy gradient — intuisi & trade-off
* [ ] Deep RL: DQN, dan milestone AlphaGo/AlphaZero (self-play, tanpa data manusia)
* [ ] Exploration vs exploitation, reward shaping, kenapa RL terkenal sulit di-tuning
* [ ] Dari RL ke RLHF/RLAIF: bagaimana reward model manusia menggantikan reward environment — jembatan eksplisit ke Module 11 (Alignment)

Mini Project: Implementasi agent RL dari nol (Q-learning atau policy gradient sederhana, tanpa framework berat) pada environment grid-world/Gym sederhana — visualisasikan proses belajar kebijakan optimal.

Status: Not Started

---

## Module 3 — Learning Paradigms Melampaui Supervised/Unsupervised

Description: `ml-fundamentals` mengajarkan supervised & unsupervised learning klasik dengan baik, tapi berhenti di situ. Modul ini membuka paradigma belajar yang menjadi fondasi kemampuan "general" pada model modern.

Lessons:

* [ ] Self-supervised learning at scale: contrastive learning, masked prediction — kenapa ini cara LLM/vision model dipretrain (bukan supervised biasa)
* [ ] Meta-learning ("learning to learn"): bagaimana model belajar beradaptasi cepat ke task baru
* [ ] Few-shot & zero-shot learning — hubungannya dengan in-context learning yang sudah dipakai praktis di `llm` Module 3, tapi di sini dibedah teorinya
* [ ] Continual/lifelong learning & catastrophic forgetting — kenapa model yang belajar task baru cenderung "lupa" task lama, dan kenapa ini masalah besar untuk AGI (manusia tidak begitu)
* [ ] Transfer learning lintas domain — evidence generality vs overfitting ke distribusi training

Mini Project: Eksperimen empiris catastrophic forgetting — fine-tune model kecil pada task A, ukur degradasi performa task asal, lalu bandingkan dengan strategi mitigasi dasar (replay/regularization).

Status: Not Started

---

## Module 4 — Reasoning: dari Chain-of-Thought ke Test-Time Compute

Description: `llm` dan `agents` sudah mengajarkan CoT/ReAct/ToT sebagai **teknik prompting**. Modul ini naik satu level: membedah reasoning sebagai *kapabilitas kognitif* yang sedang diriset — reasoning model modern, test-time compute scaling, dan bukti empiris di mana reasoning LLM masih rapuh.

Lessons:

* [ ] System 1 vs System 2 thinking (Kahneman) diterapkan ke AI — kenapa "berpikir cepat" LLM standar beda dari reasoning model
* [ ] Reasoning model & test-time compute scaling (pola o1/o3): trade-off compute saat inference vs saat training
* [ ] Neurosymbolic reasoning: menggabungkan neural network dengan symbolic solver — kapan pendekatan hybrid unggul
* [ ] Self-consistency & verification: bagaimana model memeriksa jawabannya sendiri, dan batasannya
* [ ] Bukti empiris batas reasoning: ARC-AGI sebagai benchmark yang secara spesifik dirancang untuk resisten memorisasi/pattern-matching (preview Module 10)

Mini Project: Bandingkan reasoning model (extended thinking) vs model standar pada set puzzle bergaya ARC-AGI — analisis kualitatif jenis kegagalan (bukan cuma skor akhir).

Status: Not Started

---

## Module 5 — Planning: dari Classical AI ke Model-Based Planning

Description: `agents` Module 4 mengajarkan planning **level orkestrasi LLM** (Plan-and-Execute dkk). Modul ini kembali ke akar riset planning di AI — search klasik dan planning berbasis model dunia yang dipelajari, fondasi milestone seperti AlphaZero/MuZero.

Lessons:

* [ ] Classical planning: STRIPS, state-space search (A*, best-first search)
* [ ] Hierarchical Task Network (HTN) planning — memecah task besar jadi subtask terstruktur
* [ ] Monte Carlo Tree Search (MCTS) — algoritma di balik AlphaGo
* [ ] Model-based planning: MuZero — merencanakan dengan model dunia yang dipelajari, bukan aturan game yang diberikan manusia
* [ ] Perbandingan eksplisit: planning ala MCTS/MuZero vs planning ala agent LLM (`agents` M4) — beda filosofi dasarnya

Mini Project: Implementasi MCTS dari nol untuk game sederhana (mis. tic-tac-toe/connect-four), verifikasi ia menemukan strategi optimal.

Status: Not Started

---

## Module 6 — World Models

Description: Topik yang sama sekali tidak disentuh tiga course sebelumnya. World models adalah salah satu taruhan riset paling signifikan 2025-2026 (LeCun meninggalkan Meta untuk mendirikan AMI Labs khusus topik ini) sebagai alternatif terhadap "scale LLM lebih besar saja".

Lessons:

* [ ] Apa itu world model: sistem yang memprediksi *representasi* keadaan masa depan (bukan piksel/token), untuk mendukung planning & reasoning fisik
* [ ] Joint Embedding Predictive Architecture (JEPA) — arsitektur LeCun, V-JEPA/V-JEPA 2 (belajar dari video, sample-efficient robotic manipulation)
* [ ] World model generatif interaktif: Genie 2 (DeepMind) — dunia 3D interaktif dari 1 gambar
* [ ] World model vs LLM-sebagai-world-model: apakah next-token prediction pada teks sudah "cukup" untuk model dunia, atau perlu arsitektur terpisah (debat terbuka, disambung ke Module 9)
* [ ] Kenapa world model penting untuk sample efficiency & reasoning fisik yang LLM murni sering gagal (intuisi fisika, object permanence)

Mini Project: Bangun/latih dynamics model sederhana (predict next-state) untuk environment toy, gunakan untuk planning sederhana — rasakan langsung kenapa "belajar model dunia" berbeda dari "belajar kebijakan langsung".

Status: Not Started

---

## Module 7 — Memory & Cognitive Architectures

Description: `agents` Module 5 sudah mengajarkan memory **level engineering** untuk agent LLM (context window, vector store). Modul ini melihat memory dari sisi cognitive science dan cognitive architecture riset — bagaimana disiplin ilmu kognitif memodelkan memori manusia, dan bagaimana itu menginspirasi (atau berbeda dari) sistem AI.

Lessons:

* [ ] Model memori kognitif manusia: working memory (Baddeley), episodic vs semantic long-term memory
* [ ] Cognitive architecture klasik: SOAR dan ACT-R — bagaimana riset AI simbolik lama mencoba memodelkan kognisi manusia secara menyeluruh
* [ ] Perbandingan eksplisit: memory ala cognitive architecture vs memory ala agent LLM (`agents` M5) — tujuan berbeda (memodelkan kognisi vs menyelesaikan task)
* [ ] Integrasi memory + world model (Module 6) + planning (Module 5): bagaimana ketiganya harus bekerja bersama untuk perilaku "general", bukan berdiri sendiri-sendiri

Mini Project: Desain (diagram + implementasi parsial) sistem yang menggabungkan world model sederhana (Module 6) + planning (Module 5) + memory berjenjang (working vs long-term) — fokus pada *bagaimana ketiganya saling memberi informasi*, bukan kelengkapan implementasi.

Status: Not Started

---

## Module 8 — Multimodal AI & Embodied Intelligence

Description: `llm` hanya menyinggung multimodal sekilas untuk RAG. Modul ini membahas multimodal sebagai syarat general intelligence — kecerdasan yang hanya bisa memproses teks punya generality yang secara fundamental terbatas.

Lessons:

* [ ] Vision-language model: arsitektur, cara gambar & teks di-encode ke ruang representasi bersama
* [ ] Multimodal foundation model: unified tokenization lintas modalitas (teks, gambar, audio, video)
* [ ] Embodied AI & robotics learning: vision-language-action (VLA) model, sim-to-real transfer
* [ ] Grounding: kenapa "memahami" kata "berat" atau "panas" secara embodied berbeda dari memprediksi kata berikutnya dari teks
* [ ] Cross-modal reasoning: task yang butuh menggabungkan informasi dari >1 modalitas untuk dijawab benar

Mini Project: Bangun & evaluasi pipeline multimodal sederhana (vision-language model menyelesaikan task grounded, mis. "temukan objek X dan jelaskan mengapa ia tidak stabil") — analisis kegagalan grounding.

Status: Not Started

---

## Module 9 — AGI Architecture Proposals & Debates

Description: Modul sintesis arsitektur — membandingkan taruhan riset utama industri secara eksplisit, berbasis paper & posisi resmi lab, bukan opini media.

Lessons:

* [ ] Scaling hypothesis: "bitter lesson" diterapkan penuh — argumen bahwa compute + data + arsitektur sederhana (Transformer) akan terus menang melawan struktur buatan tangan
* [ ] Modular/hybrid architecture: proposal LeCun "A Path Towards Autonomous Machine Intelligence" — world model + planning + memory sebagai modul terpisah, bukan satu jaringan monolitik
* [ ] Neurosymbolic AI: menggabungkan neural network dengan symbolic reasoning eksplisit — argumen kenapa sebagian peneliti percaya ini diperlukan untuk reliability
* [ ] Perbandingan taruhan riset 2026: OpenAI/Anthropic (scaling + RL pada reasoning model) vs Meta/AMI Labs-LeCun (world model, menjauh dari LLM murni) vs DeepMind (portfolio: Gemini scaling + Genie world model + AlphaProof neurosymbolic-adjacent)
* [ ] Takeoff scenarios (fast vs slow) & timeline forecasting: metodologi (tren compute, tren METR time horizon) vs spekulasi tanpa dasar

Mini Project: Tulis "AGI architecture position paper" — bandingkan minimal 3 pendekatan (scaling, modular/JEPA, neurosymbolic) dengan bukti dari paper/publikasi resmi, ambil posisi beralasan (bukan netral kosong).

Status: Not Started

---

## Module 10 — Evaluation & Benchmarking General Intelligence

Description: `llm`/`agents` mengajarkan evaluasi **level sistem** (groundedness RAG, tool-call accuracy). Modul ini masuk ke level yang jauh lebih sulit: mengukur "seberapa general" sebuah sistem, dan kenapa ini salah satu masalah terbuka terbesar di bidang AI.

Lessons:

* [ ] ARC-AGI (1/2/3): dirancang khusus resisten memorisasi — kenapa ia jadi standar industri (Anthropic/DeepMind/OpenAI/xAI melaporkan skor ARC-AGI di model card mereka)
* [ ] METR time horizon benchmark: metodologi "50% time horizon", tren doubling ~7 bulan (2019-2025), dan apa artinya untuk otonomi agent jangka panjang
* [ ] DeepMind "Levels of AGI" (performance × generality × autonomy) vs OpenAI "5 Levels" (Chatbots → Reasoners → Agents → Innovators → Organizations) — dua kerangka operasional yang bersaing
* [ ] Goodhart's law pada benchmark AI: kenapa benchmark yang "solved" cepat digantikan versi lebih sulit (ARC-AGI-1 → 2 → 3), dan risiko overfit ke benchmark tanpa kemampuan general nyata
* [ ] Merancang mini-benchmark sendiri: prinsip benchmark yang baik (novel, tidak bisa dihafal, terukur objektif)

**Production Discussion: Spring Boot Integration** — pola umum mengintegrasikan eval harness (mis. ARC-AGI-style atau internal capability benchmark) ke pipeline CI/CD backend, supaya rilis model baru divalidasi otomatis sebelum dipakai fitur production, mengikuti semangat `standards/OBSERVABILITY_STANDARDS.md`.

Mini Project: Replikasi skala-kecil evaluasi bergaya ARC-AGI atau METR pada model yang tersedia — jalankan, kumpulkan skor, dan tulis analisis kritis (di mana ia gagal & kenapa), bukan cuma laporan angka.

Status: Not Started

---

## Module 11 — Alignment: Membuat AGI Aman & Terarah

Description: `llm` menyinggung "alignment dasar" dalam 1 bullet, `agents` fokus security defensif (prompt injection, sandboxing). Modul ini adalah pendalaman penuh mekanisme teknis alignment — bagian paling penting dari seluruh course jika sistem yang dibahas benar-benar makin general dan otonom.

Lessons:

* [ ] Outer alignment vs inner alignment — beda antara "reward function yang salah dirumuskan" dan "model belajar tujuan berbeda dari yang dimaksud meski reward-nya benar"
* [ ] Specification gaming & reward hacking — studi kasus nyata (RL agent yang "curang" mengejar reward tanpa mencapai tujuan sebenarnya)
* [ ] RLHF/RLAIF/Constitutional AI — mekanisme teknis (bukan cuma nama), menyambung ke fondasi RL Module 2
* [ ] Scalable oversight & weak-to-strong generalization — masalah "bagaimana manusia mengawasi sistem yang lebih pintar dari manusia"
* [ ] Deceptive alignment & mesa-optimization — konsep model yang tampak aligned saat dievaluasi tapi punya tujuan tersembunyi
* [ ] Mechanistic interpretability — membuka "kotak hitam" neural network di level fitur/sirkuit (Gemma Scope, penggunaan interpretability Anthropic pada pre-deployment safety check)
* [ ] Kerangka Anthropic "Core Views on AI Safety" (skenario optimis/menengah/pesimis) & Responsible Scaling Policy/AI Safety Levels (ASL) — bagaimana lab besar mengoperasionalkan ketidakpastian risiko

**Production Discussion: Spring Boot Integration** — bagaimana guardrail & monitoring safety (mis. output filtering, anomaly detection pada behavior model) biasanya diimplementasikan sebagai middleware/interceptor di backend Spring Boot yang membungkus panggilan LLM, menyambung ke pola `standards/SECURITY_STANDARDS.md`.

Mini Project: Red-team analysis — rancang skenario specification gaming/reward hacking pada setup RL toy (Module 2) ATAU LLM (prompt-based), tunjukkan model "menang" tanpa benar-benar mencapai tujuan yang dimaksud, lalu usulkan mitigasi konkret.

Status: Not Started

---

## Module 12 — Capstone: Menyusun Argumen AGI Anda Sendiri

Description: Modul sintesis penuh — menyatukan teori intelijensi (M1), RL (M2), learning paradigm (M3), reasoning (M4), planning (M5), world model (M6), memory (M7), multimodal (M8), debat arsitektur (M9), evaluasi (M10), dan alignment (M11) menjadi satu argumen teknis yang koheren dan bisa dipertahankan.

Lessons:

* [ ] Menyusun kerangka argumen: apa yang sudah dipecahkan, apa yang masih jadi masalah terbuka (open problem), berdasarkan bukti dari Module 1-11
* [ ] Menulis technical position paper yang defensible — standar academic writing ringan (klaim harus didukung sumber, counter-argument diakui, bukan cherry-picking)
* [ ] Presentasi & pertahanan argumen: simulasi Q&A kritis (skeptical peer review) atas argumen yang disusun

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **"State of AGI" Technical Report** — laporan riset tertulis (bukan aplikasi/coding project seperti course applied lain) yang mensintesis seluruh course menjadi argumen berbasis bukti, terdiri dari: (1) definisi intelligence yang dipakai penulis dan alasannya (Module 1), (2) analisis arsitektur — bandingkan minimal 3 proposal AGI (scaling, modular/JEPA, neurosymbolic — Module 9) dengan bukti dari paper/publikasi resmi, (3) analisis evaluasi — jalankan/replikasi minimal 1 benchmark general-intelligence nyata (ARC-AGI-style atau METR-style, Module 10) dan interpretasikan hasilnya secara kritis, (4) analisis alignment — identifikasi risiko spesifik (specification gaming/deceptive alignment) pada 1 sistem yang pernah dibangun di course `agents`/`llm`, plus mitigasi konkret (Module 11), (5) kesimpulan: posisi pribadi beralasan tentang "seberapa jauh jarak menuju AGI dan apa 3 hambatan terbesar" — bukan kesimpulan netral tanpa sikap.

Acceptance Criteria:

* Setiap klaim besar (terutama di bagian arsitektur & timeline) didukung sumber (paper/publikasi resmi lab), bukan opini tanpa rujukan
* Minimal 1 eksperimen nyata dijalankan (bukan cuma dirangkum dari literatur) — baik itu mini-benchmark evaluasi (M10) atau red-team alignment (M11)
* Argumen mengakui counter-argument yang valid terhadap posisi penulis sendiri (bukan cherry-picking bukti yang mendukung saja)
* Laporan bisa dipertahankan dalam sesi Q&A kritis (skeptical peer review, Module 12)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: Belum dimulai — menunggu prerequisite selesai (`ml-fundamentals`, `llm`, `agents`) DAN approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Sejarah AI: symbolic AI/GOFAI vs connectionism, AI winters, "bitter lesson"
