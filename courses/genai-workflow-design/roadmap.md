# Course Roadmap

## Course Information

* Course Name: Generative AI & Workflow Design — dari 0 sampai Expert (Consulting/Freelance Track)
* Category: AI-Augmented Process Design / Automation Consulting
* Difficulty: Level 1 (sudah nyaman pakai AI tools, belum pernah mendesain workflow sebagai disiplin) → Level 4 (Expert, mampu menjalankan engagement desain workflow end-to-end untuk klien)
* Estimated Duration: 8 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Nyaman memakai AI tools (ChatGPT/Claude) untuk pekerjaan sehari-hari. Tidak perlu pengalaman BPM/process consulting/RPA sebelumnya — course ini yang membangun fondasi itu dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda (2026-08-06):

1. **Bukan pengganti `courses/llm`, `courses/agents`, atau `courses/mastering-claude`.** Course ini beroperasi di layer yang berbeda: `courses/llm` mengajarkan cara kerja LLM, `courses/agents` mengajarkan cara membangun agent, `courses/mastering-claude` mengajarkan tool Claude Code — semuanya layer *implementasi*. Course ini adalah layer *desain*: bagaimana menganalisis sebuah proses bisnis/engineering, memutuskan bagian mana yang layak diotomasi vs diaugmentasi vs tetap human-only, dan merancang workflow-nya — sebelum satu baris kode atau satu agent pun dibangun. Ketiga course lain tidak diubah.
2. **Bukan pengganti `courses/n8n-automation`, tapi lapisan di atasnya.** `courses/n8n-automation` adalah "cara membangun automation di n8n" (tool-specific, hands-on). Course ini adalah "cara berpikir sebelum membangun apa pun" (tool-agnostic): analisis proses, keputusan desain, human-in-the-loop, ROI case. Modul 4 & 8 course ini secara eksplisit merujuk ke `courses/n8n-automation` untuk prototyping hands-on, bukan mengulang materinya.
3. **Tujuan: skill yang bisa dijual (consulting/freelance service)**, sejalan dengan Master Goal freelance di `progress/roadmap.md` meski track ini tetap paralel/independen (bukan prasyarat Master Goal). Karena itu capstone-nya bukan dokumen belajar biasa, tapi *client-ready deliverable*: audit proses, workflow design doc, prototype, value case, dan proposal — persis yang akan Anda kirim ke calon klien sungguhan.
4. **Level awal: sudah nyaman pakai AI tools.** Course ini **tidak** mengulang "apa itu prompt" atau dasar pemakaian ChatGPT/Claude — langsung mulai dari kerangka berpikir analisis proses & desain workflow di Modul 1.
5. **Modul 7 (packaging & selling) dibatasi ketat ke jasa desain workflow saja** — bukan silabus freelance umum (menulis proposal untuk servis apa pun, kontrak internasional, dsb). Itu domain course terpisah "Freelance & International Career Readiness" yang belum dibuat (lihat Gap Analysis `progress/roadmap.md`); begitu course itu ada, Modul 7 di sini akan merujuk ke sana alih-alih mengulang.

Referensi riset yang dipakai menyusun roadmap ini (sintesis, bukan copy-paste):

* [Google PAIR Guidebook](https://pair.withgoogle.com/guidebook-v2/) — kerangka automation vs augmentation, mental models, feedback+control, errors+graceful failure; dipakai sebagai kerangka Modul 1 & 3
* [Anthropic — Building Effective AI Agents](https://www.anthropic.com/engineering/building-effective-agents) — pembeda *workflow* (predefined path) vs *agent* (LLM mengarahkan proses sendiri), dipakai ulang di sini sebagai keputusan *desain* (bukan implementasi seperti di `courses/agents`)
* [BPMN — Object Management Group](https://www.omg.org/bpmn/) & [BPMN 101 (Smartsheet)](https://www.smartsheet.com/beginners-guide-business-process-modeling-and-notation-bpmn) — notasi standar pemetaan proses as-is/to-be, dipakai sebagai kerangka Modul 2 & 3
* Sintesis struktur engagement konsulting automation (Discovery → Process/Workflow Audit → Opportunity Mapping → Solution Design → Prototype → Value Case → Proposal), umum dipakai di industri AI automation consulting 2026 — dipakai sebagai kerangka Modul 6-8

---

# Learning Objectives

* [ ] Mampu membedakan tugas yang layak diotomasi penuh, diaugmentasi (AI-assist dengan manusia tetap in-the-loop), vs harus tetap human-only — dan menjelaskan alasannya
* [ ] Mahir memetakan proses as-is memakai notasi BPMN dari hasil discovery (interview, shadowing, artifact review)
* [ ] Mampu merancang proses to-be dengan AI-augmented/automated step, human-in-the-loop gate, dan exception/failure path yang eksplisit
* [ ] Paham dan mampu menerapkan kerangka *workflow vs agent* (Anthropic) sebagai keputusan desain, bukan sekadar istilah teknis
* [ ] Mampu memilih tool automation yang tepat (no-code seperti n8n, agent framework, custom code) berdasarkan kriteria klien, bukan preferensi pribadi
* [ ] Mahir merancang "AI step" di dalam workflow: kontrak input/output, prompt yang reliable (bukan kreatif), guardrail, dan ambang eskalasi ke manusia
* [ ] Mampu membangun value case (before/after, ROI) yang meyakinkan untuk sebuah redesign workflow
* [ ] Mampu menyusun proposal jasa audit/desain workflow yang siap dikirim ke calon klien
* [ ] Menyelesaikan proyek portofolio: satu engagement desain workflow end-to-end dari discovery sampai deliverable klien

---

# Course Modules

## Module 1 — Fondasi: Apa Itu "AI Workflow Design" Sebenarnya

Description: Kerangka berpikir paling dasar sebelum menyentuh proses/tool apa pun — membedakan disiplin ini dari sekadar "pakai AI".

Lessons:

* [ ] 1.1 Automation vs augmentation vs human-only — kerangka keputusan (Google PAIR)
* [ ] 1.2 Task decomposition — memecah proses jadi langkah atomik yang bisa diklasifikasi satu per satu
* [ ] 1.3 Workflow vs Agent (Anthropic) sebagai keputusan desain: kapan proses butuh jalur tetap vs kapan butuh LLM yang mengarahkan dirinya sendiri
* [ ] 1.4 Silsilah disiplin ini: dari BPM/RPA tradisional ke AI-native workflow design — apa yang berubah, apa yang tidak

Mini Project: Ambil 1 proses nyata yang berulang (dari pekerjaan atau kehidupan sehari-hari Anda), pecah jadi langkah atomik, klasifikasikan tiap langkah (automate/augment/human-only) beserta alasannya.

Status: Not Started

---

## Module 2 — Process Discovery & Mapping

Description: Sebelum mendesain apa pun, harus bisa memotret proses yang ada sekarang secara akurat — skill yang sering diremehkan pemula.

Lessons:

* [ ] 2.1 Teknik discovery: interview proses, shadowing, review artifact (dokumen, tiket, email, log)
* [ ] 2.2 Notasi BPMN dasar: task, gateway, event, sequence flow
* [ ] 2.3 Memetakan proses as-is dalam BPMN dari hasil discovery
* [ ] 2.4 Mengidentifikasi bottleneck, titik rawan error, dan langkah yang cocok untuk AI (data tak terstruktur, judgment call, tugas repetitif berbasis teks)

Mini Project: Petakan diagram BPMN as-is untuk 1 proses nyata (proses Anda sendiri atau proses hipotetis calon klien), lengkap dengan anotasi titik bottleneck/error.

Status: Not Started

---

## Module 3 — Merancang Workflow To-Be

Description: Inti dari course ini — mengubah peta as-is jadi desain to-be yang menggabungkan AI, human-in-the-loop, dan penanganan kegagalan secara eksplisit.

Lessons:

* [ ] 3.1 Desain proses to-be: urutan langkah AI-augmented/otomatis, decision gateway, exception path
* [ ] 3.2 Pola human-in-the-loop (PAIR): approval gate, review queue, escalation — kapan manusia harus tetap di dalam loop
* [ ] 3.3 Kalibrasi trust: seberapa besar otonomi diberikan ke AI di tiap langkah, dan bagaimana ini berubah seiring waktu saat trust terbukti
* [ ] 3.4 Desain untuk kegagalan: apa yang terjadi saat langkah AI salah (fallback, human review, retry) — graceful failure, bukan silent failure

Mini Project: Redesign proses dari Modul 2 jadi diagram BPMN to-be dengan langkah AI-augmented/otomatis, human-in-the-loop gate, dan exception path yang ditandai jelas.

Status: Not Started

---

## Module 4 — Lanskap Tool & Pilihan Prototyping

Description: Setelah desain jadi, harus bisa memilih (bukan asal pakai) tool yang tepat untuk memvalidasinya secara cepat dan murah.

Lessons:

* [ ] 4.1 Kategori tool automation: no-code (n8n/Zapier/Make), agent framework (rujuk `courses/agents`, tidak diulang), custom code orchestration
* [ ] 4.2 Kriteria memilih tool: biaya, kontrol, stack klien yang sudah ada, maintainability jangka panjang
* [ ] 4.3 Kapan merekomendasikan/membangun di n8n vs tool lain — rujukan hands-on: `courses/n8n-automation`
* [ ] 4.4 Mindset rapid prototyping: cara tervalidasi-tercepat untuk menguji desain sebelum dibangun penuh

Mini Project: Prototipekan minimal 1 langkah dari workflow to-be Modul 3 di n8n (atau tool no-code lain) — cukup untuk memvalidasi keputusan desain, bukan implementasi lengkap.

Status: Not Started

---

## Module 5 — Mendesain "AI Step" di Dalam Workflow

Description: Langkah AI di dalam workflow production butuh desain berbeda dari chat biasa — harus reliable & terprediksi, bukan kreatif.

Lessons:

* [ ] 5.1 Kontrak input/output untuk AI step: structured output, skema yang jelas
* [ ] 5.2 Desain prompt untuk konteks workflow (konsisten & reliable) — beda dengan prompting percakapan bebas
* [ ] 5.3 Guardrail di dalam AI step: validasi output, ambang confidence, kapan harus eskalasi ke manusia (bukan menebak)

Mini Project: Rancang & uji spesifikasi prompt/AI-step untuk langkah paling berisiko di workflow Modul 3, termasuk perilaku kegagalan/eskalasinya.

Status: Not Started

---

## Module 6 — Mengukur Value & ROI

Description: Desain yang bagus tidak berarti apa-apa untuk klien tanpa angka yang membuktikannya.

Lessons:

* [ ] 6.1 Metrik yang penting bagi klien: waktu yang dihemat, penurunan error rate, biaya per transaksi, throughput
* [ ] 6.2 Merancang pengukuran before/after yang valid — cara membuktikan redesign benar-benar bekerja, bukan klaim subjektif
* [ ] 6.3 Menyusun value case sederhana — angka yang membuat klien bilang "ya"

Mini Project: Tulis bagian "value case" untuk redesign workflow Anda — estimasi before/after yang terukur.

Status: Not Started

---

## Module 7 — Mengemas & Menjual Workflow Design sebagai Jasa

Description: Skill desain saja tidak cukup untuk freelance — harus bisa dikemas jadi penawaran yang bisa dijual. **Dibatasi ketat ke jasa desain workflow** (bukan silabus freelance umum — lihat Catatan Scope poin 5).

Lessons:

* [ ] 7.1 Model jasa terproduktisasi: paket audit/desain fixed-scope (Discovery → Audit → Design → Prototype)
* [ ] 7.2 Menulis proposal audit/desain workflow: scope, deliverable, struktur harga
* [ ] 7.3 Komunikasi ke klien: mempresentasikan redesign, menangani keberatan ("ini menggantikan pekerjaan saya?"), dasar change management

Mini Project: Tulis template proposal jasa audit/desain 1 halaman yang benar-benar bisa dikirim ke calon klien.

Status: Not Started

---

## Module 8 — Menjadi AI Workflow Designer yang Dipercaya

Description: Level di mana Anda bisa menjalankan 1 engagement penuh dari awal sampai akhir, bukan cuma mengerjakan potongan-potongan latihan.

Lessons:

* [ ] 8.1 Menyusun seluruh alur engagement jadi satu narasi: discovery → as-is → to-be → AI-step spec → prototype → value case → proposal
* [ ] 8.2 Studi kasus singkat: bagaimana automation/consulting agency memaketkan jasa serupa (produktisasi, repeatable framework, blueprint)
* [ ] 8.3 Refleksi & iterasi: bagaimana menangani feedback klien dan mendesain ulang setelah engagement pertama

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Full AI Workflow Design Engagement** — pilih 1 proses bisnis/engineering nyata (atau realistis), jalankan seluruh engagement end-to-end: (1) discovery & pemetaan as-is (BPMN, Modul 2), (2) desain to-be dengan AI-augmented step + human-in-the-loop gate + exception path (Modul 3), (3) spesifikasi AI-step untuk langkah paling berisiko termasuk guardrail & eskalasi (Modul 5), (4) prototype minimal 1 langkah berdampak tinggi di n8n/tool no-code (Modul 4), (5) value case terukur (Modul 6), (6) proposal jasa 1 halaman (Modul 7).

Acceptance Criteria:

* Diagram BPMN as-is dan to-be lengkap dan konsisten satu sama lain
* Minimal 1 human-in-the-loop gate dan 1 exception/failure path didesain eksplisit (bukan diasumsikan "selalu berhasil")
* AI-step tervalidasi: prompt diuji, guardrail & ambang eskalasi terdefinisi jelas
* Prototype berjalan nyata (bukan mockup) untuk minimal langkah berdampak tertinggi
* Value case berisi angka before/after yang bisa dipertanggungjawabkan, bukan estimasi asal
* Proposal siap dikirim ke calon klien sungguhan
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/8

Overall Completion: 0%

Current Module: Belum dimulai — roadmap approved 2026-08-06, menunggu mulai

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Automation vs augmentation vs human-only
