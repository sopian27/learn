# Learning Progress

**Update 2026-08-07:** Course aktif dipersempit jadi 2 atas permintaan user untuk hemat pemakaian token. Detail course lain ada di bagian "Paused Courses" di bawah — resume kapan saja diminta.

## Active Courses

### 1. Mastering Claude — `courses/mastering-claude/roadmap.md`

Current Module: Course tuntas — semua 8 module + Enterprise Project (Capstone) selesai 2026-08-16.
Current Lesson: Tidak ada, course selesai. Artifact final: `portofolio/mastering-claude-playbook.md`.
Course Completion: 8/8 module + Enterprise Project selesai (100%)

| Module | Status |
| ------ | ------ |
| 1. Fondasi: Mental Model & Prompting | Selesai (2026-08-06) |
| 2. Token Economy Claude Code | Selesai (2026-08-06) |
| 3. Claude Code Mendalam | Selesai (2026-08-13) — Mini Project: Note CRUD API pakai 5 mekanisme, bukti di `playground/mastering-claude/3-mini-project.md` |
| 4. Skills & Plugins | Selesai (2026-08-14) — 4.1-4.3: bikin skill `tdd-workflow` nyata + bundle jadi plugin lokal `tdd-toolkit@sopian-local`, install nyata via `/plugin marketplace add` + `/plugin install`, verifikasi baca dari plugin cache global. 4.4: audit keamanan `tdd-toolkit` (checklist description/body/hook/MCP/bundled script/source pinning/least privilege) — koreksi 1 putaran (exercise awal salah tandai 5 poin "aman karena unknown", diperbaiki jadi verified-per-file). Mini Project Module 4 dipenuhi oleh `tdd-toolkit` (keputusan user, tidak bikin skill terpisah). Log: `playground/mastering-claude/4.1.md`, `.fix.md`, `4.2.md`, `.fix.md`, `.fix1.md`, `4.3.md`, `4.4.md` |
| 5. Claude Cowork | Selesai (2026-08-15) — 5.1 skor 100/100 setelah 2 putaran revisi (Q3 reasoning diperbaiki dari salah framing "dev environment" jadi "VM isolasi + overhead orkestrasi subagent"). 5.2 skor 100/100 setelah 1 putaran revisi (Exercise 2 diperdalam ke mekanisme failure detector). 5.3 skor 100/100 setelah 1 putaran revisi (containment/blast radius). 5.4 skor 100/100 setelah 1 putaran revisi (Quiz 1-2 salah pasang axis, diperbaiki). Log: `playground/mastering-claude/5.1.md`, `.fix.md`, `5.2.md`, `5.3.md`, `5.4.md` |
| 6. Kombinasi Multi-AI | Selesai (2026-08-15) — 6.1 skor 100/100 tanpa revisi (quiz + exercise benar semua, sumbu keputusan tepat). 6.2 applied practice kasus nyata (proses patching MBP): cross-model blind-spot review → swap-test isolasi confound prompt-vs-model → self-adversarial-review kontrol → pattern digeneralisasi ke `progress/cheatsheet.md`. Log: `playground/mastering-claude/6.1.md`, `6.2.md` |
| 7. Vibe Coding: Filosofi & Alur Kerja | Selesai (2026-08-16). 7.1 skor 100/100 tanpa revisi; 7.2 skor 3/3 setelah 1x revisi (axis stakes vs reversibility); 7.3 skor 3/3 quiz + 4/4 exercise setelah revisi (Q1 diperkuat "success signal ≠ correct behavior"; Q3 dua percobaan pertama recycle contoh 7.2, revisi final spesifik ke auto-run/auto-accept; Exercise 2 sempat salah label tahap). Mini Project selesai (2026-08-16): CLI scraper produk→CSV dibangun vibes-only, 2 ronde vibes-patch buta (TLS reset lalu 0 produk karena selector ditebak), refleksi nangkep "program jalan tanpa error ≠ program benar". Log: `playground/mastering-claude/7-mini-project.md` |
| 8. Dari Vibe Coding ke Rekayasa Bertanggung Jawab | Selesai (2026-08-16) — 8.1-8.4 semua skor 4/4 (detail lihat `courses/mastering-claude/roadmap.md`). Mini Project: refactor nyata `7-mini-project/scraper.py` (vibes-only) jadi `8-mini-project/scraper.py` (disciplined) lewat siklus SDD penuh + 3 gerbang verifikasi sungguhan (diff review nemuin bug encoding mojibake, 14 test/99% coverage, security review 0 finding). Perbandingan tertulis: "vibe coding optimalkan speed-to-code, disciplined optimalkan speed-to-verified-result". Log: `playground/mastering-claude/8-mini-project.md`, `.review.md` |
| Enterprise Project (Capstone) | Selesai (2026-08-16), skor 90/100 setelah 1x revisi. Playbook pribadi (`portofolio/mastering-claude-playbook.md`): decision framework CLAUDE.md/Skill/Plugin/Hook, decision matrix vibe vs spec-driven, automation nyata (`tdd-toolkit` v1.1.0, 2 hook TDD gate diverifikasi 7 skenario), ROI case study scraper vibes-only vs disciplined (n=1, gap token-cost diakui eksplisit). Course **Mastering Claude tuntas penuh**. |

### 2. Character Development — `courses/character-development/roadmap.md`

Current Module: Module 1 — Fondasi: Kerendahan Hati Sejati vs Ego & Self-Deprecation
Current Lesson: 1.1 Peta tiga kondisi — kesombongan vs kerendahan hati sehat vs self-deprecation toxic
Course Completion: 0/6 module (0%)

| Lesson | Status | Quiz | Exercise |
| ------ | ------ | ---- | -------- |
| 1.1 Peta tiga kondisi — kesombongan vs kerendahan hati sehat vs self-deprecation toxic | In Progress | - | - |

### 3. Backend Engineering Fundamentals Rebuild — `courses/backend-fundamentals/roadmap.md`

Resume 2026-08-19 atas permintaan eksplisit user, mengisi slot Mastering Claude yang sudah selesai (override cap 2-course-aktif).

Current Module: Module 3 — JVM Internals untuk Debugging Production (in progress)
Current Lesson: 3.2 Garbage Collector (belum dimulai)
Course Completion: 2/15 module (13%) — Module 1 & 2 tuntas penuh (termasuk Mini Project), Module 3 in progress

| Lesson | Status | Task |
| ------ | ------ | ---- |
| 1.1 Generics mendalam: bounded type, wildcard, type erasure | Selesai (2026-08-19), skor 95/100 | Dikerjakan, direview |
| 1.2 Collections internals: `ArrayList`/`LinkedList`/`HashMap`/`TreeMap`, mutable key & hashCode/equals contract | Selesai (2026-08-20), skor 93/100 | Dikerjakan, direview |
| 1.3 Exception handling idiomatis: checked vs unchecked, custom exception, anti-pattern `catch (Exception e) {}` | Selesai (2026-08-25), skor 95/100 | Dikerjakan, direview |
| 1.4 Immutability by default, `equals`/`hashCode`/`toString`, static factory vs constructor | Selesai (2026-08-25), skor 84/100 | Dikerjakan, direview |
| 1.5 Naming & code style sesuai `standards/NAMING_CONVENTIONS.md` | Selesai (2026-08-25), skor 88/100 | Dikerjakan, direview |
| Module 1 Mini Project | Selesai (2026-08-25), skor 90/100 | Dikerjakan, direview |
| 2.1 Race condition, visibility problem, kenapa `synchronized` bukan solusi ajaib | Selesai (2026-08-26), skor 84/100 | Dikerjakan, direview |
| 2.2 `ExecutorService`, thread pool sizing, `ConcurrentHashMap` vs `synchronizedMap` | Selesai (2026-08-27), skor 80/100 | Dikerjakan, direview |
| 2.3 `CompletableFuture`: composing async, exception handling di rantai async, kapan vs `Mono`/`Flux` | Selesai (2026-08-27), skor 82/100 | Dikerjakan, direview |
| 2.4 Virtual Threads (Project Loom, Java 25): beda fundamental dengan platform thread, kapan menguntungkan untuk I/O-bound | Selesai (2026-08-28), skor 91/100 | Dikerjakan, direview |
| 2.5 Kelas bug concurrency umum di production: deadlock, livelock, stale read — cara mendiagnosisnya dari thread dump | Selesai (2026-08-29), skor 92/100 | Dikerjakan, direview |
| Module 2 Mini Project — Paralelkan batch order enrichment tanpa race condition | Selesai (2026-08-29), skor 93/100 | Dikerjakan, direview |
| 3.1 Memory model JVM: heap vs stack vs metaspace, object lifecycle, eligible GC | Selesai (2026-09-02), skor 90/100 | Dikerjakan, direview |

---

# Projects

| Project | Status |
| ------- | ------ |

---

# Weak Topics

*

---

# Strong Topics

*

---

# Review Schedule

Rule: every completed module gets a +3 hari (recall cepat) dan +14 hari (retensi) check — jawab 2-3 pertanyaan inti modul itu dari ingatan, tanpa buka lesson lama. Kalau gagal recall, tandai "Revision Needed" dan re-review sebelum lanjut modul baru.

| Module | Selesai | +3 hari | +14 hari | Status |
| ------ | ------- | ------- | -------- | ------ |
| Mastering Claude 1-2 (Fondasi, Token Economy) | 2026-08-06 | 2026-08-09 ✓ (lewat, belum di-log) | 2026-08-20 | Due +14 hari |
| Mastering Claude 3 (Claude Code Mendalam) | 2026-08-13 | 2026-08-16 | 2026-08-27 | Due +3 hari |
| Mastering Claude 4 (Skills & Plugins) | 2026-08-14 | 2026-08-17 | 2026-08-28 | Upcoming |

Revision Needed: -

---

# Notes

* 2026-08-07: Course aktif dipersempit jadi 2 (mastering-claude, character-development) atas permintaan user untuk hemat pemakaian token — semua course lain (termasuk yang sebelumnya "In Progress" seperti backend-fundamentals, ml-fundamentals, ci-cd, personal-finance, dst.) dipause, bukan dihentikan. Resume kapan saja diminta user.
* Mastering Claude: track paralel/praktis (bukan jalur utama), dimulai 2026-08-06 dari kebutuhan langsung user.
* Character Development: domain pack, di-approve & dimulai 2026-08-07. Applied Practice-nya butuh jurnal reflektif jujur + feedback nyata dari orang lain — pace mengikuti ritme praktik nyata, bukan dikebut.
* 2026-08-19: Backend Engineering Fundamentals Rebuild di-resume atas permintaan eksplisit user, mengisi slot ke-3 (Mastering Claude sudah selesai 2026-08-16, jadi ini bukan pelanggaran cap 2-course-aktif — pilihan sadar mengisi slot kosong, bukan menambah beban paralel baru). Lesson 1.1 (Generics mendalam) materi + task langsung ditulis ke vault Obsidian sesuai `ai-los/OBSIDIAN_SYNC.md`, tidak ditampilkan di chat.
* 2026-08-20: Lesson 1.2 (Collections internals) selesai, skor 93/100. Jawaban user solid di Bagian 1/2/3 dan Interview Questions — dua catatan level produksi diberikan: `record` sebagai idiom modern pengganti class immutable manual, dan rank query leaderboard tidak murah di `TreeMap` polos (perlu order-statistics tree atau Redis `ZSET`/`ZRANK` di production).
* 2026-08-25: Lesson 1.3 (Exception handling idiomatis) selesai, skor 95/100. Custom unchecked exception + root cause preservation + batch failure collection di Bagian 1, diagnosis silent-failure job direkonstruksi mandiri di Bagian 2, checked/unchecked Bagian 3 semua tepat termasuk kasus nuanced payment gateway timeout. Dua catatan produksi: trade-off return-result vs throw untuk batch API, dan exception chain (`getCause()`) lebih dalam dari sekadar "cause disertakan".
* 2026-08-25: Lesson 1.4 (Immutability by default, `equals`/`hashCode`/`toString`, static factory vs constructor) selesai, skor 84/100. Bagian 1 (refactor `DiscountCoupon`) dan Bagian 3 (constructor vs static factory, 3 skenario) kuat penuh. Bagian 2 (diagnosis `Range`/`PremiumRange` sebagai `Map` key) salah identifikasi properti kontrak: jawaban user bilang symmetry pecah, padahal sesuai logika kondisional yang dideskripsikan soal, yang sebenarnya pecah adalah **transitivity** (varian "mixed comparison" Effective Java Item 10, beda dari contoh `getClass()`/Point-ColorPoint di Theory) — perbaikan struktural (composition over inheritance) yang diusulkan tetap benar, cuma diagnosisnya meleset. Detail lengkap review ada di vault.
* 2026-08-26: Module 2 dimulai. Lesson 2.1 (Model mental thread-safety: race condition, visibility problem, kenapa `synchronized` bukan solusi ajaib) selesai, skor 84/100. Diagnosis 3 masalah tepat (race condition di `stock` & `lowStockAlerted`, visibility problem di `getStock()`), fix `synchronized` konsisten di semua jalur akses benar, Bagian 2 (happens-before) direkonstruksi mandiri dengan tepat. Dua catatan turun skor: (1) soal minta perbandingan eksplisit vs 2 alternatif (`volatile`/`Atomic*`) — tidak dijawab, kesimpulan `synchronized` benar tapi reasoning pembandingnya hilang; (2) `sendLowStockAlert()` dipanggil di dalam `synchronized` block tidak ditandai sebagai isu granularity/lock-held-during-I/O, padahal ini persis poin "granularity salah = performa hancur" dari Theory lesson yang sama. Detail lengkap review ada di vault.
* 2026-08-27: Lesson 2.2 (`ExecutorService`, thread pool sizing, `ConcurrentHashMap` vs `synchronizedMap`) selesai, skor 80/100. `ThreadPoolExecutor` eksplisit + bounded queue benar, tapi rumus sizing dipakai dengan `W` yang salah unit (dipakai total waktu sekuensial lama, bukan wait time per-task), dan bagian deadlock starvation (task pool nunggu task lain di pool yang sama) tidak terjawab meski ditanya eksplisit. Detail lengkap review ada di vault.
* 2026-08-27: Lesson 2.3 (`CompletableFuture`: composing async, exception handling di rantai async, kapan vs `Mono`/`Flux`) selesai, skor 82/100. Mekanik inti kuat — `thenCompose` dipakai tepat untuk rantai dependen, `thenCombine` untuk gabung hasil independen, dan `exceptionally()` ditaruh presisi di titik yang tepat (bukan di ujung chain) sehingga kegagalan `fetchDiscountForTier` bisa fallback tapi kegagalan `fetchCustomer` tetap gagal total. Tiga catatan turun skor: (1) executor mundur ke `Executors.newFixedThreadPool` mentah, padahal Review 2.2 sudah eksplisit menandai itu risiko unbounded-queue OOM; (2) sizing `W/C` diklaim tanpa diturunkan dari angka ms nyata di soal; (3) analisis `CompletableFuture` vs `Mono`/`Flux` benar arah tapi belum menyentuh dua argumen produksi paling kuat (eager execution mematikan gunanya backpressure, dan `CompletableFuture.cancel()` tidak benar-benar menghentikan kerja yang sedang berjalan). Detail lengkap review ada di vault.
* 2026-08-28: Lesson 2.4 (Virtual Threads, Project Loom, Java 25) selesai, skor 91/100. Sizing manual `W/C` diturunkan eksplisit dari angka di soal (catatan yang menahan skor 2.3 langsung diperbaiki), refactor ke `Executors.newVirtualThreadPerTaskExecutor()` benar, pinning risk & JEP 491 dijelaskan tepat. Satu catatan: alternatif `ReentrantLock` sebagai opsi pinning-safe gak disebut di bagian pinning risk.
* 2026-08-29: Lesson 2.5 (Kelas bug concurrency production: deadlock, livelock, stale read — diagnosis dari thread dump) selesai, skor 92/100, **menutup semua lesson Module 2**. Diagnosis dipisah tepat berdasarkan state thread (bukan tebak dari nama gejala), 4 kondisi Coffman dibuktikan langsung dari kode, ekspektasi output `jstack` tepat. Bagian terkuat: pembedaan stale read level JVM (sudah aman via `volatile`) vs level sistem (gap `notifyStockSystem()` HTTP ~150ms setelah mutasi lokal) — dan kenapa `jstack` gak akan pernah membuktikan yang kedua. Fix lock ordering via `System.identityHashCode()` benar untuk kasus many-warehouse/runtime-direction, plus bonus guard `from == to` yang gak diminta. Dua catatan turun skor: (1) tidak menangani kasus langka `identityHashCode()` collision yang bisa bikin ordering gak deterministik lagi; (2) fix lock-ordering (bagian 4) dan fix pindah `notifyStockSystem()` keluar lock (bagian 5) dijawab terpisah, padahal saling memperkuat sebagai lapisan berbeda. Lanjut ke Mini Project Module 2. Detail lengkap review ada di vault.
* 2026-08-29: Module 2 Mini Project (Paralelkan `OrderEnrichmentService` — batch 500 order/hari, 2 HTTP call sekuensial jadi paralel aman) selesai, skor 93/100, **menutup Module 2 sepenuhnya**. Submission terkuat Module 2: 3 variabel race condition diidentifikasi presisi dengan pemisahan atomicity vs struktur-data-tidak-thread-safe (Tugas 1), refactor `CompletableFuture` bersih tanpa shared mutable state selama fase paralel (Tugas 2), rumus sizing Lesson 2.2 tidak ditelan mentah — sadar sendiri rumus meledak kalau compute time dianggap 0, lalu memilih ukuran pool praktis dengan alasan kapasitas downstream (Tugas 3), exception handling per-order lewat `.handle()` di titik yang tepat (Tugas 4), keempat penjelasan lesson 2.1-2.4 spesifik dan benar termasuk catatan virtual thread tetap butuh rate limiter (Tugas 5), bonus soal thread-pool starvation vs deadlock Coffman terjawab tepat (Tugas 6). Dua catatan turun skor: (1) `allOf().join()` lalu masih `join()` manual per-future di loop — redundan, dan invariant "kenapa `allOf().join()` di sini dijamin gak pernah throw" tidak diucapkan eksplisit; (2) ukuran pool 64/128 belum dihubungkan balik ke kapasitas konkret `PricingService`/`ShippingService`. Module 2 — Concurrency & Multithreading **tuntas sepenuhnya** (5 lesson + Mini Project). Lanjut ke Module 3 — JVM Internals untuk Debugging Production. Detail lengkap review ada di vault.

---

# Paused Courses

*Dipause 2026-08-06 (alasan lama):*

* Docker, Kubernetes & OpenShift — `courses/kubernetes/roadmap.md`
  * Module 1 — Docker Fundamentals, Lesson 1.1 Container vs VM, arsitektur Docker Engine (In Progress, belum selesai)
  * Alasan pause: user memilih mulai jalur AI (ml-fundamentals) dulu, 2026-08-06. Kebutuhan kerja aktif — resume kapan saja user minta "lanjut Kubernetes".

*Dipause 2026-08-07 untuk hemat token (semua "In Progress" sebelumnya, resume kapan saja atas permintaan user — detail rasional lengkap ada di `courses/<nama>/roadmap.md` masing-masing dan git history file ini):*

* Machine Learning & AI Fundamentals — `courses/ml-fundamentals/roadmap.md` — Module 1, Lesson 1.1 (AI vs ML vs DL vs LLM)
* CI/CD Mastery — `courses/ci-cd/roadmap.md` — Module 1, Lesson 1.1
* Personal Finance — `courses/personal-finance/roadmap.md` — Module 1, Lesson 1.1
* n8n Automation — `courses/n8n-automation/roadmap.md` — Module 1, Lesson 1.1
* Communication Skills — `courses/communication-skills/roadmap.md` — Module 1, Lesson 1.1
* Critical Thinking & Problem Solving — `courses/critical-thinking/roadmap.md` — Module 1, Lesson 1.1. Domain Goal dikunci final 2026-08-09 di `domains/critical-thinking/DOMAIN.md` (Einstein/Musk/Buffett/Edison/Tesla sebagai referensi prinsip berpikir, bukan panutan utuh — lihat bagian "Tokoh Referensi" di domain pack) — status tetap Paused, resume kapan saja diminta.
* World History & Historical Discernment — `courses/world-history/roadmap.md` — Module 1, Lesson 1.1
* Gitar — `courses/guitar/roadmap.md` — Module 1, Lesson 1.1
* Self-Love — `courses/self-love/roadmap.md` — Module 1, Lesson 1.1
* Computational Thinking & Algoritma — `courses/computational-thinking/roadmap.md` — Module 1, Lesson 1.1
* Film Criticism & Analysis — `courses/film-criticism/roadmap.md` — Module 1, Lesson 1.1
* Bahasa Korea — `courses/korean/roadmap.md` — Module 1, Lesson 1.1
* Cryptocurrency — `courses/cryptocurrency/roadmap.md` — Module 1, Lesson 1.1

*Didaftarkan paused sejak awal 2026-08-08 (konsisten dengan narrowing 2026-08-07), roadmap dibangun lengkap atas permintaan user meski belum aktif:*

* Pornography Recovery — `courses/pornography-recovery/roadmap.md` — Module 1, Lesson 1.1. Domain pack: `domains/pornography-recovery/DOMAIN.md`. House rule anti-shame eksplisit + eskalasi wajib ke profesional untuk indikasi klinis — lihat domain pack sebelum resume.
* Media & Political Literacy — `courses/media-literacy/roadmap.md` — Module 1, Lesson 1.1. Domain pack: `domains/media-literacy/DOMAIN.md`. Analisis berita/pernyataan pemerintah/kebijakan/isu politik kritis & berbasis bukti, tanpa fanatik ke kelompok/ideologi mana pun. House rule paling ketat: mentor tidak pernah mengambil posisi politik — lihat domain pack sebelum resume.
* Vocal Coaching — `courses/vocal-coaching/roadmap.md` — Module 1, Lesson 1.1. Domain pack: `domains/vocal-coaching/DOMAIN.md`. Teknik vokal pop/CCM dari 0 untuk suara bass/baritone, sesi via instruksi verbal + self-report (bukan rekaman, mentor tidak bisa mendengar langsung). House rule paling ketat: hard boundary eskalasi kesehatan vokal (nyeri/serak berkepanjangan/kehilangan suara mendadak → wajib rujuk THT/laringolog) — lihat domain pack sebelum resume.
* Football Tactical Analysis — `courses/football-tactics/roadmap.md` — Module 1, Lesson 1.1. Domain pack: `domains/football-tactics/DOMAIN.md`. Membaca taktik sepak bola dari 0 (anchor liga Premier League), skill inti membedakan kesalahan pemain vs sistem dan taktik vs eksekusi (Module 6). Mode analisis memakai pertandingan terkenal yang sudah terdokumentasi luas, bukan nonton live bareng — lihat domain pack sebelum resume.
