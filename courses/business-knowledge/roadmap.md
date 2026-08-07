# Course Roadmap

## Course Information

* Course Name: Product & Business Knowledge for Engineers — Product Thinking, Metrics, A/B Testing, Cost Consciousness
* Category: Business/Product Fluency untuk Software Engineer — melengkapi (bukan menggantikan) `courses/data-analysis-visualization` (tooling analis: SQL/Pandas/stats/Power BI) dan `courses/cloud-devops-foundations` (mekanisme SLA/SLO/error budget sudah dibahas di sana); course ini fokus ke *judgment* & *komunikasi* bisnis untuk engineer, bukan jadi analis
* Difficulty: Level 1 (murni engineer, belum pernah berpikir eksplisit dalam kerangka bisnis/produk) → Level 3 (bisa ikut diskusi prioritas fitur, merancang eksperimen A/B, dan mengomunikasikan trade-off teknis dalam bahasa yang dipahami stakeholder non-teknis)
* Estimated Duration: 7 modul, 3-4 lesson/modul, ~30-40 menit/lesson. Perkiraan ≈ 2-3 bulan pace santai (bisa paralel dengan course teknis lain).
* Prerequisites: Tidak ada prasyarat teknis. Course ini murni cara berpikir & komunikasi, cocok dijalankan paralel dengan course apa pun.

---

# Catatan Scope (draft — menunggu approval & kalibrasi user)

Draft ini dibuat atas permintaan eksplisit user (2026-08-07) untuk mengisi gap "Business Knowledge (Product/KPI/A-B Testing)". **Belum ada sesi kalibrasi** — asumsi di bawah ini perlu dikonfirmasi:

1. **Tidak menduplikasi `courses/data-analysis-visualization`**: course itu adalah tooling analis (SQL analytics, Pandas, statistik terapan, Power BI/DAX). Course ini adalah *judgment* level — bagaimana engineer ikut mikir dan bicara soal produk/bisnis, bukan menjadi Data/BI Analyst. Module 4 (A/B Testing) sengaja dijaga tetap intuitif/applied, bukan mendalami statistik penuh (itu ranah data-analysis-visualization).
2. **Tidak menduplikasi `courses/cloud-devops-foundations`**: SLA/SLO/error budget *mekanismenya* sudah dibahas di sana (Google SRE framing). Module 6 di sini adalah framing "kenapa itu penting sebagai alat negosiasi bisnis", bukan mengulang cara menghitungnya.
3. **Konteks freelance eksplisit**: karena Master Goal user adalah freelance internasional, Module 7 (komunikasi trade-off) dan capstone sengaja diarahkan ke skenario "meyakinkan klien", bukan "naik jenjang karir di perusahaan" — konsisten dengan keputusan yang sudah diambil di `courses/engineering-culture`.
4. **Cost Optimization (Module 5)** dibatasi ke level kesadaran biaya keputusan teknis (unit economics, build vs buy) — FinOps penuh/mendalam sengaja **tidak** di sini, sudah dialokasikan sebagai modul tersendiri di draft `courses/future-of-software-engineering` (Module 7) karena FinOps sebagai disiplin formal lebih relevan sebagai topik "mendalam & muncul", bukan pengantar.

Referensi riset (untuk disusun jadi roadmap final saat approval):

* *Escaping the Build Trap* (Melissa Perri) — fondasi product thinking, Module 1
* *Inspired* (Marty Cagan) — cross-ref product thinking praktisi, Module 1-2
* *Lean Analytics* (Alistair Croll & Benjamin Yoskovitz) — metrik & North Star metric, Module 3
* *Trustworthy Online Controlled Experiments* (Ron Kohavi, Diane Tang, Ya Xie) — rigor A/B testing, Module 4
* *Google SRE Book* (sre.google/books) — cross-ref `courses/cloud-devops-foundations`, dipakai di Module 6
* RICE/ICE prioritization framework (Intercom/Sean Ellis, sintesis umum industri) — Module 2

---

# Learning Objectives

* [ ] Membedakan problem space vs solution space, berpikir dalam outcome bukan sekadar output/ticket
* [ ] Menggunakan framework prioritas (RICE/ICE/MoSCoW) untuk mengambil keputusan "apa yang dikerjakan dulu" secara terstruktur
* [ ] Memilih metrik yang benar-benar penting (leading vs lagging indicator), menghindari jebakan vanity metric
* [ ] Merancang eksperimen A/B secara benar: hipotesis, metrik sukses, menghindari pitfall umum (peeking, novelty effect)
* [ ] Membuat keputusan teknis dengan kesadaran biaya (unit economics, build vs buy)
* [ ] Menjelaskan SLA/SLO/error budget sebagai alat negosiasi bisnis, bukan sekadar metrik SRE
* [ ] Mengomunikasikan trade-off teknis (technical debt, latency, reliability) ke stakeholder non-teknis/klien secara meyakinkan

---

# Course Modules

## Module 1 — Product Thinking untuk Engineer

Description: Titik awal — mengubah kebiasaan "menerima ticket dan mengerjakannya" jadi "memahami masalah di baliknya".

Lessons:

* [ ] 1.1 Problem space vs solution space: kenapa terjun ke solusi terlalu cepat sering salah sasaran
* [ ] 1.2 Output vs outcome: ticket selesai bukan berarti masalah user selesai
* [ ] 1.3 Pengantar Jobs-to-be-Done: memahami "pekerjaan" yang sebenarnya ingin diselesaikan user
* [ ] 1.4 Peran engineer dalam diskusi produk: kapan bertanya "kenapa" itu bagian dari pekerjaan, bukan mengganggu

Mini Project: Ambil 1 fitur/ticket yang pernah Anda kerjakan — tulis ulang dari sudut pandang problem space (masalah user apa yang sebenarnya coba diselesaikan) vs solution space (implementasi yang dipilih).

Status: Not Started

---

## Module 2 — Framework Prioritas

Description: Cara mengatakan "tidak" atau "nanti dulu" dengan alasan yang bisa dipertahankan, bukan sekadar perasaan.

Lessons:

* [ ] 2.1 RICE (Reach, Impact, Confidence, Effort) — cara menghitung dan kapan berguna
* [ ] 2.2 ICE & MoSCoW sebagai alternatif lebih sederhana — kapan masing-masing framework lebih cocok
* [ ] 2.3 Opportunity cost: setiap "ya" untuk satu fitur adalah "tidak" untuk fitur lain — membuat trade-off ini eksplisit
* [ ] 2.4 Menggunakan framework prioritas dalam diskusi nyata (bukan hanya dokumen internal)

Mini Project: Ambil 3-5 fitur/permintaan (nyata atau hipotetis) dan urutkan prioritasnya memakai RICE, dengan skor & justifikasi tertulis untuk tiap item.

Status: Not Started

---

## Module 3 — Metrik yang Benar-Benar Penting

Description: Membedakan angka yang terlihat bagus di dashboard dari angka yang benar-benar menunjukkan kesehatan produk.

Lessons:

* [ ] 3.1 North Star Metric: satu metrik yang mewakili nilai inti produk untuk user
* [ ] 3.2 Leading vs lagging indicator — kenapa menunggu lagging indicator saja terlalu lambat untuk mengambil keputusan
* [ ] 3.3 Vanity metric trap: kenapa "jumlah pengguna terdaftar" sering menyesatkan dibanding "pengguna aktif yang mendapat value"
* [ ] 3.4 Memilih KPI untuk 1 fitur spesifik — menghubungkan metrik fitur ke North Star Metric produk

Mini Project: Untuk 1 fitur (boleh sama dengan Module 1), tentukan 1 leading indicator dan 1 lagging indicator, jelaskan kenapa keduanya perlu dipantau bersamaan.

Status: Not Started

---

## Module 4 — A/B Testing & Eksperimentasi untuk Engineer

Description: Cukup rigor untuk merancang eksperimen yang valid dan menghindari kesimpulan palsu — tanpa perlu jadi ahli statistik (statistik mendalam ada di `courses/data-analysis-visualization`).

Lessons:

* [ ] 4.1 Merancang hipotesis eksperimen: hipotesis yang bisa difalsifikasi, bukan sekadar "coba-coba"
* [ ] 4.2 Intuisi statistical significance & power untuk engineer: kenapa sample size kecil bisa menyesatkan (tanpa rumus mendalam)
* [ ] 4.3 Pitfall umum A/B testing: peeking (berhenti terlalu dini), novelty effect, sample ratio mismatch
* [ ] 4.4 Dari hasil eksperimen ke keputusan: kapan hasil "signifikan" tapi tetap tidak layak di-ship (effect size kecil, trade-off lain)

Mini Project: Rancang 1 desain eksperimen A/B lengkap (hipotesis, metrik sukses, kriteria berhenti, ancaman validitas) untuk 1 perubahan fitur nyata/hipotetis — tanpa perlu benar-benar menjalankannya.

Status: Not Started

---

## Module 5 — Kesadaran Biaya dalam Keputusan Teknis

Description: Setiap keputusan arsitektur punya konsekuensi rupiah — engineer yang dipercaya klien besar paham ini secara eksplisit.

Lessons:

* [ ] 5.1 Unit economics dasar: biaya per-request/per-user, kenapa ini relevan untuk keputusan desain
* [ ] 5.2 Build vs buy: kapan membangun sendiri lebih murah jangka panjang, kapan pakai layanan pihak ketiga lebih masuk akal
* [ ] 5.3 Technical decision dengan konsekuensi biaya nyata: memilih database, caching strategy, arsitektur cloud dengan mempertimbangkan biaya operasional, bukan cuma performa
* [ ] 5.4 Cost-consciousness tanpa jadi penghambat: kapan optimasi biaya prematur justru merugikan (over-engineering demi hemat $10/bulan)

Mini Project: Untuk 1 keputusan teknis yang pernah/akan Anda buat (misal: pilih database, pilih caching layer), tulis estimasi biaya kasar untuk 2-3 opsi dan justifikasi pilihan berdasarkan itu.

Status: Not Started

---

## Module 6 — Reliability sebagai Konsep Bisnis

Description: SLA/SLO/error budget bukan sekadar metrik SRE — mereka adalah alat negosiasi antara engineering dan bisnis.

Lessons:

* [ ] 6.1 Recap singkat SLI/SLO/error budget (cross-ref `courses/cloud-devops-foundations` untuk mekanisme lengkap) — fokus di sini ke *kenapa* konsepnya ada
* [ ] 6.2 Error budget sebagai alat negosiasi: kapan tim boleh fokus ke fitur baru vs harus fokus ke stabilitas, berdasarkan data bukan perdebatan
* [ ] 6.3 SLA sebagai janji ke klien: bagaimana menerjemahkan SLO internal jadi komitmen SLA yang realistis ke klien eksternal
* [ ] 6.4 Biaya reliability: 99.9% vs 99.99% bukan sekadar angka — menjelaskan kenapa "nine" tambahan itu mahal dan kapan sepadan

Mini Project: Tulis 1 halaman yang menjelaskan (untuk audiens klien non-teknis) kenapa Anda merekomendasikan target SLA tertentu untuk 1 sistem, termasuk trade-off biaya vs reliability.

Status: Not Started

---

## Module 7 — Mengomunikasikan Trade-off Teknis ke Non-Teknis

Description: Modul penutup — mengubah semua pemahaman di atas jadi kemampuan komunikasi nyata, skill paling langsung relevan untuk kepercayaan klien freelance.

Lessons:

* [ ] 7.1 Menerjemahkan technical debt ke bahasa bisnis: "kalau tidak diperbaiki, ini konsekuensinya ke kecepatan/biaya/risiko ke depan"
* [ ] 7.2 Cost of delay: mengkomunikasikan kenapa menunda keputusan teknis tertentu juga punya biaya
* [ ] 7.3 Menyusun argumen trade-off yang meyakinkan: data (Module 1-6) + framing yang relevan untuk kepentingan pendengar (klien vs tim internal)
* [ ] 7.4 Latihan: menyampaikan 1 rekomendasi teknis kontroversial (misal: "kita perlu refactor sebelum lanjut fitur baru") dalam format singkat untuk stakeholder non-teknis

Mini Project: Tulis (dan idealnya rekam diri menyampaikan) 1 pitch 3-5 menit yang mengomunikasikan 1 trade-off teknis nyata ke audiens non-teknis, memakai kerangka dari Module 1-6.

Status: Not Started

---

# Capstone Project

Description: **Business Brief untuk Satu Fitur** — pilih 1 fitur nyata atau hipotetis yang relevan dengan portofolio Anda. Tulis satu halaman yang mencakup: problem statement (Module 1), prioritas & justifikasi RICE (Module 2), metrik sukses leading+lagging (Module 3), desain eksperimen A/B (Module 4), estimasi biaya (Module 5), dan komitmen reliability yang realistis (Module 6) — dikemas sebagai dokumen yang bisa dikirim ke calon klien freelance untuk menunjukkan kefasihan bisnis, bukan cuma kemampuan coding.

Acceptance Criteria:

* Problem statement jelas membedakan problem space dari solution space
* Skor prioritas (RICE/ICE) disertai justifikasi, bukan angka asal
* Minimal 1 leading indicator dan 1 lagging indicator didefinisikan dan saling terhubung
* Desain eksperimen A/B lengkap dengan kriteria berhenti dan ancaman validitas yang diantisipasi
* Estimasi biaya untuk minimal 2 opsi teknis dibandingkan
* Target SLA/reliability dijelaskan dengan trade-off biaya yang jujur
* Dokumen ditulis dalam bahasa yang bisa dipahami pembaca non-teknis
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/7

Overall Completion: 0%

Current Module: Belum dimulai — roadmap draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Problem space vs solution space
