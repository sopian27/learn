# Course Roadmap

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Course Information

* Course Name: Teknologi Nuklir — dari 0 sampai Literasi Tingkat Tinggi
* Category: Nuclear Technology Literacy (Sains, Keselamatan & Kebijakan)
* Difficulty: Level 1 (belum pernah belajar fisika nuklir formal) → Level 4 (literasi tingkat tinggi: paham fisika, keselamatan, regulasi, dan kebijakan nuklir cukup untuk menganalisis isu nuklir secara kritis dan menulis laporan teknis-kebijakan) — **bukan** jenjang lisensi profesional nuclear engineering/operator reaktor.
* Estimated Duration: 10 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Matematika dasar (aljabar, eksponensial/logaritma secukupnya untuk peluruhan radioaktif), tidak perlu latar belakang fisika/teknik sebelumnya — Modul 1-2 dibangun dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

1. **Ini domain literasi, bukan pendidikan profesional.** Course ini tidak menggantikan gelar Nuclear Engineering terakreditasi, sertifikasi operator reaktor resmi (BAPETEN/regulator setara), atau sertifikasi radiologi medis/fisikawan medis. Soal hitungan (kritikalitas, dosis, peluruhan) dikalibrasi ke level pengantar (mengikuti gaya MIT OCW 22.01 dan bab awal Lamarsh & Baratta), bukan level S2/S3 atau desain inti reaktor produksi.
2. **Desain senjata nuklir eksplisit di luar scope**, konsisten dengan `domains/nuclear-technology/DOMAIN.md` — tidak ada lesson/soal yang menyentuh detail pengayaan tingkat senjata, desain hulu ledak, atau device engineering. Modul 10 (Nonproliferasi) membahas *kebijakan* pencegahan proliferasi (NPT, IAEA safeguards), bukan cara kerja teknis senjata.
3. **Aplikasi medis dibahas literasi-only.** Modul 9 menjelaskan *bagaimana* isotop medis bekerja secara konseptual (radiofarmasi, terapi Lu-177, dsb.) untuk pemahaman sains — bukan pelatihan praktik klinis, dan tidak pernah dipakai memberi saran medis untuk kasus nyata.
4. **Referensi riset yang dipakai menyusun roadmap ini**: [IAEA — Fundamental Safety Principles (SF-1)](https://www.iaea.org/publications/7592/fundamental-safety-principles), [IAEA Safety Standards portal](https://www.iaea.org/resources/safety-standards), [World Nuclear Association — Nuclear Power Reactors](https://world-nuclear.org/information-library/nuclear-power-reactors/overview/nuclear-power-reactors) dan [Nuclear Fuel Cycle Overview](https://world-nuclear.org/information-library/nuclear-fuel-cycle/introduction/nuclear-fuel-cycle-overview), Lamarsh & Baratta *Introduction to Nuclear Engineering* (4th ed., Pearson 2017), [MIT OCW 22.01 — Introduction to Nuclear Engineering and Ionizing Radiation](https://ocw.mit.edu/courses/22-01-introduction-to-nuclear-engineering-and-ionizing-radiation-spring-2024/) dan [22.05 — Neutron Science and Reactor Physics](https://ocw.mit.edu/courses/22-05-neutron-science-and-reactor-physics-fall-2009/), [ICRP Publication 103](https://www.icrp.org/docs/icrp_publication_103-annals_of_the_icrp_37(2-4)-free_extract.pdf) untuk prinsip proteksi radiasi, [IAEA — Radionuclide Therapy](https://www.iaea.org/topics/radionuclide-therapy) untuk aplikasi medis, dan untuk konteks Indonesia: [BAPETEN — Capacity Building Awareness Nuklir 2026](https://www.bapeten.go.id/berita/bapeten-tekankan-pentingnya-pemahaman-publik-terhadap-keselamatan-dan-manfaat-pltn-dalam-capacity-building-awareness-nuklir-2026-120811?lang=id) dan [BRIN — Menyongsong Era Nuklir di Indonesia](https://www.brin.go.id/news/122725/menyongsong-era-nuklir-di-indonesia).
5. **Track independen**, tidak terkait Master Goal software-engineering di repo ini — murni domain minat pribadi, dikonsumsi lewat teks, soal konseptual, dan analisis studi kasus.

---

# Learning Objectives

* [ ] Memahami fisika nuklir fundamental: struktur inti atom, radioaktivitas, fisi & fusi, binding energy
* [ ] Mampu menjelaskan dan menghitung konsep dasar fisika reaktor: kritikalitas (k-eff), peran moderator & kontrol
* [ ] Mampu membandingkan jenis-jenis reaktor (PWR/BWR/CANDU/SMR) dari segi prinsip kerja, trade-off keselamatan & ekonomi
* [ ] Memahami siklus bahan bakar nuklir penuh (front end sampai back end) dan isu limbah radioaktif
* [ ] Mampu menghitung/estimasi dosis radiasi dasar dan menjelaskan prinsip ALARA & proteksi radiasi
* [ ] Mampu menganalisis root cause & pelajaran safety culture dari insiden besar (Chernobyl/TMI/Fukushima) berbasis sumber institusional
* [ ] Memahami kerangka regulasi & keselamatan IAEA serta bagaimana regulator nasional (BAPETEN) bekerja
* [ ] Memahami aplikasi nuklir non-daya (medis, industri) pada level literasi konseptual
* [ ] Memahami dasar nonproliferasi (NPT, IAEA safeguards) dan status/konteks program nuklir Indonesia (BRIN/BAPETEN, debat kelayakan PLTN)
* [ ] Menyelesaikan proyek portofolio: laporan teknis-kebijakan tertulis (mis. analisis komparatif teknologi reaktor untuk konteks PLTN Indonesia)

---

# Course Modules

## Module 1 — Sejarah & Fondasi Fisika Nuklir

Description: Titik berangkat dari nol — memahami penemuan inti atom, radioaktivitas, dan bagaimana fisi/fusi bisa melepaskan energi sebesar itu, sebelum masuk ke reaktor atau kebijakan.

Lessons:

* [ ] 1.1 Sejarah singkat: penemuan radioaktivitas (Becquerel, Curie), model atom (Rutherford, Bohr), penemuan fisi (Hahn/Strassmann/Meitner)
* [ ] 1.2 Struktur inti atom: proton, neutron, isotop, notasi nuklir
* [ ] 1.3 Binding energy & mass defect: kenapa fisi dan fusi melepaskan energi (kurva binding energy per nukleon)
* [ ] 1.4 Fisi vs fusi: perbedaan mekanisme, reaksi berantai (chain reaction), kenapa fisi dipakai untuk PLTN saat ini dan fusi masih riset

Mini Project: Problem set konseptual — menghitung mass defect & binding energy sederhana untuk satu isotop, dan menjelaskan kurva binding energy dalam kata-kata sendiri (bukan hafalan).

Status: Not Started

---

## Module 2 — Struktur Atom, Inti & Radioaktivitas

Description: Memperdalam radioaktivitas sebagai fenomena — jenis peluruhan, waktu paruh, dan kenapa ini jadi dasar dosimetri di Modul 6.

Lessons:

* [ ] 2.1 Jenis peluruhan radioaktif: alpha, beta, gamma — perbedaan daya tembus & bahaya relatif
* [ ] 2.2 Waktu paruh (half-life) & hukum peluruhan eksponensial
* [ ] 2.3 Deret peluruhan (decay chain) dan kesetimbangan radioaktif (radioactive equilibrium) secukupnya
* [ ] 2.4 Radiasi latar alami (background radiation): sumber alami vs buatan manusia, skala perbandingan

Mini Project: Soal hitungan half-life/decay (mis. berapa lama aktivitas suatu isotop turun ke X%), plus tabel perbandingan radiasi latar dari berbagai sumber (penerbangan, CT scan, batu granit, dsb.) dengan sumber institusional.

Status: Not Started

---

## Module 3 — Fisika Reaktor Dasar

Description: Bagaimana reaksi fisi terkendali menjadi sumber daya yang stabil, bukan bom — inti dari "kenapa reaktor tidak meledak seperti senjata nuklir".

Lessons:

* [ ] 3.1 Reaksi berantai terkendali: neutron cepat vs neutron termal, penampang lintang (cross-section) secara konseptual
* [ ] 3.2 Kritikalitas: subcritical, critical, supercritical, k-effective (k-eff)
* [ ] 3.3 Peran moderator (air, air berat, grafit) dalam memperlambat neutron
* [ ] 3.4 Kontrol reaktor: control rods, neutron poisons (xenon-135), konsep dasar reactor kinetics (delayed neutrons sebagai alasan reaktor bisa dikendalikan manusia)

Mini Project: Problem set k-eff sederhana (klasifikasi subcritical/critical/supercritical dari skenario diberi) mengikuti gaya soal MIT OCW 22.05, plus penjelasan tertulis kenapa delayed neutron krusial untuk kendali reaktor.

Status: Not Started

---

## Module 4 — Jenis & Desain Reaktor

Description: Dari fisika ke rekayasa — bagaimana prinsip Modul 3 diwujudkan jadi desain reaktor nyata yang berbeda-beda di seluruh dunia.

Lessons:

* [ ] 4.1 Pressurized Water Reactor (PWR): prinsip kerja, kenapa jadi ~70% armada reaktor global
* [ ] 4.2 Boiling Water Reactor (BWR): perbedaan dari PWR, trade-off desain
* [ ] 4.3 Pressurized Heavy Water Reactor / CANDU: kenapa bisa pakai uranium alami (bukan enriched)
* [ ] 4.4 Generasi berikutnya: Generation III+/IV, Small Modular Reactor (SMR) — apa yang baru dan kenapa relevan untuk negara seperti Indonesia

Mini Project: Reactor Type Comparison Sheet — tabel perbandingan PWR/BWR/CANDU/SMR dari segi bahan bakar, moderator/coolant, keunggulan, kelemahan, dan negara pengguna utama, disusun dari sumber World Nuclear Association.

Status: Not Started

---

## Module 5 — Siklus Bahan Bakar Nuklir

Description: Dari tambang uranium sampai limbah akhir — siklus penuh yang sering dilewatkan pembahasan populer soal energi nuklir.

Lessons:

* [ ] 5.1 Front end: penambangan & penggilingan uranium (mining & milling), konversi
* [ ] 5.2 Enrichment: kenapa uranium alami perlu diperkaya untuk sebagian besar reaktor (level konsep, bukan detail teknik pengayaan sensitif)
* [ ] 5.3 Fabrikasi bahan bakar & penggunaan di reaktor (burnup secukupnya)
* [ ] 5.4 Back end: spent fuel storage, reprocessing, dan pengelolaan limbah radioaktif tingkat tinggi/rendah — isu kebijakan penyimpanan jangka panjang

Mini Project: Diagram alur siklus bahan bakar nuklir (front end → penggunaan → back end) dibuat sendiri oleh learner, dengan anotasi isu kebijakan/keselamatan di tiap tahap, dirujuk ke World Nuclear Association & IAEA.

Status: Not Started

---

## Module 6 — Proteksi Radiasi & Dosimetri

Description: Bagaimana radiasi diukur dan dikelola agar aman — dasar untuk memahami klaim keselamatan di modul-modul berikutnya secara kuantitatif, bukan cuma kualitatif.

Lessons:

* [ ] 6.1 Besaran dosimetri: absorbed dose (Gy), equivalent dose & effective dose (Sv) — bedanya dan kenapa dipisah
* [ ] 6.2 Prinsip ICRP: justification, optimisation (ALARA), dose limitation
* [ ] 6.3 Batas dosis untuk publik vs pekerja radiasi, dan konteksnya (mSv/tahun)
* [ ] 6.4 Efek biologis radiasi secukupnya: deterministic vs stochastic effects (tanpa masuk ke level kedokteran klinis)

Mini Project: Latihan hitungan dosis radiasi — mengestimasi dosis tahunan dari kombinasi skenario nyata (penerbangan internasional, tinggal di daerah radiasi latar tinggi, satu kali CT scan) dan membandingkannya dengan batas dosis ICRP, disertai kesimpulan tertulis soal ALARA.

Status: Not Started

---

## Module 7 — Budaya Keselamatan Nuklir & Studi Kasus Insiden Besar

Description: Modul paling sensitif dan paling penting — membedah insiden nyata secara jujur, berbasis root cause, bukan sensasi maupun downplay.

Lessons:

* [ ] 7.1 Three Mile Island (1979): kronologi, root cause teknis & human factor, dampak nyata (bukan mitos populer)
* [ ] 7.2 Chernobyl (1986): kronologi, kombinasi desain reaktor bermasalah + kegagalan safety culture, lahirnya konsep "safety culture" (IAEA INSAG-4)
* [ ] 7.3 Fukushima Daiichi (2011): kronologi, kegagalan yang dipicu bencana alam + masalah kelembagaan (regulatory capture, resistensi terhadap tekanan regulasi)
* [ ] 7.4 Sintesis: pola bersama ketiga insiden, evolusi regulasi pasca-insiden (Convention on Nuclear Safety 1994), apa artinya "defense in depth"

Mini Project: Case Study Post-Mortem lengkap untuk satu insiden pilihan — root cause analysis (teknis + organisasi/safety culture) dan pelajaran yang diambil regulator global, ditulis berbasis sumber IAEA/laporan resmi, bukan narasi media populer semata.

Status: Not Started

---

## Module 8 — Regulasi & Kerangka Keselamatan IAEA

Description: Bagaimana pelajaran dari Modul 7 diterjemahkan jadi sistem regulasi yang berjalan hari ini, global maupun di Indonesia.

Lessons:

* [ ] 8.1 Struktur IAEA Safety Standards: Safety Fundamentals, Safety Requirements, Safety Guides
* [ ] 8.2 Fundamental Safety Principles (SF-1): sepuluh prinsip keselamatan dan intinya
* [ ] 8.3 Defense in depth & konsep regulasi independen (kenapa regulator harus terpisah dari operator)
* [ ] 8.4 Regulasi nuklir Indonesia: peran BAPETEN sebagai regulator, dan BRIN (eks-BATAN) sebagai lembaga riset — pembagian peran keduanya

Mini Project: Ringkasan analitis (1 halaman) yang memetakan sepuluh prinsip Fundamental Safety Principles IAEA ke satu insiden dari Modul 7 — prinsip mana yang dilanggar dan bagaimana regulasi berubah sesudahnya.

Status: Not Started

---

## Module 9 — Aplikasi Nuklir Non-Daya

Description: Teknologi nuklir bukan cuma soal pembangkit listrik — modul ini literasi-only, secara eksplisit bukan pelatihan klinis.

Lessons:

* [ ] 9.1 Kedokteran nuklir — diagnostik: bagaimana radiofarmasi & pencitraan (PET/SPECT) bekerja secara konseptual
* [ ] 9.2 Kedokteran nuklir — terapi: prinsip radionuclide therapy (mis. Lu-177) untuk kanker, secara konseptual
* [ ] 9.3 Aplikasi industri: radiografi non-destruktif, sterilisasi radiasi, pengukuran industri
* [ ] 9.4 Riset & produksi isotop: peran reaktor riset (termasuk konteks reaktor riset BRIN di Indonesia)

Mini Project: Ringkasan konseptual tertulis — pilih satu aplikasi non-daya (medis atau industri), jelaskan prinsip fisika di baliknya dan sumber institusional yang dipakai, dengan catatan eksplisit "ini literasi sains, bukan panduan klinis".

Status: Not Started

---

## Module 10 — Nonproliferasi, Kebijakan & Program Nuklir Indonesia

Description: Modul penutup — menyatukan sains dan keselamatan (Modul 1-8) dengan lanskap kebijakan global dan nasional, jadi bekal untuk Capstone Project.

Lessons:

* [ ] 10.1 Dasar nonproliferasi: Non-Proliferation Treaty (NPT), tiga pilarnya (non-proliferasi, penggunaan damai, disarmament)
* [ ] 10.2 IAEA safeguards: bagaimana IAEA memverifikasi penggunaan damai tanpa membahas detail teknis sensitif
* [ ] 10.3 Lanskap energi nuklir global saat ini: negara-negara dengan PLTN aktif, tren SMR, debat nuklir vs energi terbarukan dalam transisi energi
* [ ] 10.4 Program nuklir Indonesia: sejarah BATAN → BRIN, roadmap PLTN (fase persiapan 2025-2029, target komisioning ~2032 per RUPTL 2025-2034), debat kelayakan (kebutuhan energi, biaya, kesiapan regulasi, penerimaan publik)

Mini Project: Policy brief singkat (1-2 halaman) tentang kelayakan PLTN pertama Indonesia, mempertimbangkan argumen pro/kontra berbasis sumber BRIN/BAPETEN/Bappenas, bukan opini tanpa data.

Status: Not Started

---

# Capstone Project

Description: **Laporan Teknis-Kebijakan: Analisis Komparatif Teknologi Reaktor untuk Konteks PLTN Indonesia** — mensintesis seluruh course jadi satu laporan tertulis yang membandingkan opsi teknologi reaktor (mis. PWR konvensional vs SMR) untuk konteks nyata Indonesia, mempertimbangkan: (1) prinsip fisika & keselamatan reaktor (Modul 1-3), (2) trade-off desain reaktor (Modul 4), (3) implikasi siklus bahan bakar & limbah (Modul 5), (4) pertimbangan proteksi radiasi & keselamatan publik (Modul 6), (5) pelajaran safety culture dari insiden besar dan bagaimana itu memengaruhi desain/regulasi modern (Modul 7-8), dan (6) konteks kebijakan & kesiapan nasional Indonesia (Modul 10).

Acceptance Criteria:

* Laporan membandingkan minimal 2 opsi teknologi reaktor secara eksplisit (bukan hanya mendeskripsikan satu opsi)
* Setiap klaim teknis/keselamatan dirujuk ke sumber institusional (IAEA/World Nuclear Association/ICRP/BAPETEN/BRIN) — bukan opini tanpa sumber
* Memuat analisis pelajaran safety culture dari minimal satu insiden besar (Chernobyl/TMI/Fukushima) sebagai bagian pertimbangan keselamatan
* Memuat penilaian jujur atas trade-off (biaya, waktu pembangunan, limbah, kesiapan regulasi, penerimaan publik) — tidak sepihak pro maupun kontra nuklir tanpa dasar
* Eksplisit menyatakan diri sebagai laporan literasi/sintesis, bukan dokumen rekayasa teknik atau rekomendasi kebijakan resmi
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Roadmap draft — menunggu approval user, belum dimulai (mengikuti kebijakan hemat-token repo, tidak otomatis jadi course aktif)

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Sejarah singkat: penemuan radioaktivitas (Becquerel, Curie), model atom (Rutherford, Bohr), penemuan fisi (Hahn/Strassmann/Meitner)
