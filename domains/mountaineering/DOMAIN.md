# Mountaineering — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Mountaineering (Pendakian Gunung & Alpine Travel — dari Hiking Dasar sampai Technical Climbing dan Alpine Safety)

## Scope

Kompetensi bergerak aman dan mandiri di medan gunung, dari hiking terstruktur pertama sampai merencanakan/memimpin ekspedisi multi-hari dengan elemen technical climbing dasar: **fondasi fisik** (fitness spesifik mendaki, load carrying), **perlengkapan** (layering system, Ten Essentials, gear per level objektif), **hiking & trail skills dasar**, **navigasi gunung** (peta, kompas, GPS/altimeter, route finding), **camping & manajemen perkemahan di medan gunung**, **cuaca gunung & pengambilan keputusan berbasis cuaca**, **first aid & kesadaran altitude** (AMS/HACE/HAPE), **manajemen risiko & perencanaan trip**, **trekking multi-hari & expedition behavior**, **kepemimpinan di gunung**, **pengantar technical climbing & rope skills** (knot, anchor, belay — konseptual), dan **alpine safety** (glacier travel, self-arrest, awareness bahaya avalanche & crevasse).

Domain ini **spesifik ke medan gunung dan elevasi** — bukan pengganti domain survival umum yang sudah/akan ada di repo ini. Secara eksplisit TIDAK mencakup (untuk menghindari duplikasi):

- **Fundamental survival backcountry generik** — membuat api dari nol, mencari makanan liar (foraging), membangun shelter darurat dari bahan alam, dan sinyal darurat umum (bukan spesifik pegunungan) — sudah/akan dicakup penuh di domain `wilderness-survival` (lihat `domains/wilderness-survival/DOMAIN.md` dan `courses/wilderness-survival/roadmap.md`, dibangun paralel oleh agent lain per 2026-08-14). Domain ini **merujuk**, tidak mengulang — fokus native domain ini adalah hal yang spesifik ke medan GUNUNG dan ELEVASI: progresi hiking/trekking, gear spesifik gunung, cuaca/altitude gunung, technical climbing dan rope skills (belaying, anchor, rappelling), bahaya spesifik glacier/alpine, dan kepemimpinan ekspedisi.
- **Rock climbing/sport climbing sebagai olahraga tersendiri** (grading system, program latihan bouldering/gym, kompetisi climbing) — domain ini hanya mengajarkan rope skills dan movement teknis sejauh dibutuhkan untuk mencapai objektif mountaineering (mis. melewati pitch pendek di rute alpine), bukan progresi climbing gym/sport sebagai tujuan itu sendiri. Kandidat domain terpisah `rock-climbing` di masa depan bila dibutuhkan.
- **Ski mountaineering/ski touring sebagai teknik ski** — teknik meluncur di ski di luar cakupan; domain ini hanya menyentuh ski mountaineering di level kesadaran konsep (bagian dari disiplin IFMGA), bukan pelatihan teknik ski.
- **Sertifikasi medis/wilderness first responder (WFR/WFA) penuh** — modul first aid domain ini membangun kesadaran (recognize-decide-act dasar, AMS/HACE/HAPE), bukan pengganti kursus first aid bersertifikat yang wajib diambil terpisah dari penyedia resmi (mis. NOLS Wilderness Medicine, Red Cross).
- **Logistik ekspedisi spesifik lokasi nyata** (perizinan puncak tertentu, booking porter/guide komersial, dokumen imigrasi) — berubah-ubah per negara/gunung dan di luar cakupan course berbasis prinsip ini; course mengajarkan KERANGKA perencanaan, bukan paperwork spesifik satu gunung.
- **Diagnosis/terapi medis klinis** di luar kesadaran first aid dasar — wajib eskalasi ke tenaga medis berlisensi (lihat "Domain-Specific Standards").

## Trusted Sources / Research Priority

1. **Mountaineers Books — *Mountaineering: The Freedom of the Hills* (10th ed.)**, diterbitkan The Mountaineers (Mountaineers.org, Seattle) — buku teks komprehensif berbahasa Inggris yang paling dianggap "canonical" di dunia mountaineering, mencakup hampir seluruh subtopik domain ini secara sistematis. Dipakai sebagai kerangka struktur utama course, setara peran Stephen Lucas di domain `public-speaking`.
2. **UIAA — International Climbing and Mountaineering Federation** — badan federasi global untuk standar keamanan peralatan (tali, harness, carabiner, helm lewat sertifikasi UIAA/CE) dan kode etik pendakian. Prioritas tertinggi untuk semua konten terkait gear teknis dan safety standard.
3. **IFMGA — International Federation of Mountain Guides Associations**, direpresentasikan di AS oleh **AMGA (American Mountain Guides Association)** — badan yang menetapkan standar sertifikasi guide gunung profesional (disiplin Rock/Alpine/Ski, minimal ~94 hari training dengan mayoritas praktik langsung di medan alpine). Dipakai sebagai kerangka acuan "seperti apa kompetensi teknis penuh yang sebenarnya", sekaligus dasar eksplisit kenapa course ini BUKAN pengganti sertifikasi tersebut.
4. **American Alpine Club (AAC) — laporan tahunan *Accidents in North American Climbing*** — kompilasi insiden nyata dengan analisis penyebab, dipakai sebagai dasar studi kasus manajemen risiko berbasis data kejadian sungguhan, bukan skenario hipotetis semata.
5. **NOLS (National Outdoor Leadership School)** — kurikulum wilderness leadership & expedition behavior yang sudah divalidasi puluhan tahun di ekspedisi nyata, plus **NOLS Wilderness Medicine** — dipakai sebagai kerangka modul kepemimpinan dan first aid/altitude awareness.
6. **Wilderness Medical Society (WMS) — *Clinical Practice Guidelines for the Prevention, Diagnosis, and Treatment of Acute Altitude Illness* (2024 Update)** — panduan medis peer-reviewed (evidence-graded, mengikuti kriteria American College of Chest Physicians) untuk AMS/HACE/HAPE, sumber tertinggi di atas tips populer soal "obat ketinggian".
7. **Petzl Technical Resources** dan standar **UIAA/CE** untuk sistem tali, anchor, dan belay — dipakai untuk modul rope skills, selalu disertai catatan wajib supervisi bersertifikat sebelum praktik nyata.
8. **National Avalanche Center / avalanche.org (AS)** — dasar riset untuk kesadaran bahaya avalanche di modul alpine safety (bukan pengganti kursus AIARE Level 1/2 yang wajib diambil terpisah untuk travel di medan bersalju berisiko avalanche).
9. **Leave No Trace Center for Outdoor Ethics** — tujuh prinsip etika berkegiatan di alam liar, dipakai untuk modul camping & manajemen perkemahan gunung.
10. **`domains/wilderness-survival/DOMAIN.md` dan `courses/wilderness-survival/roadmap.md`** (lihat "Scope" di atas) — dirujuk untuk fundamental survival backcountry generik (api, shelter darurat, air, foraging, sinyal darurat umum), sengaja tidak diulang di domain ini.

Tidak pernah membangun satu lesson hanya dari satu sumber non-primer (mis. blog gear review, forum) tanpa menyilangkan ke sumber 1-3 (fondasi teknis) atau 6 (medis). Modul first aid/altitude wajib berpijak ke riset medis peer-reviewed (sumber 6), bukan sekadar mitos populer soal ketinggian ("minum kopi biar kuat", dsb).

## Applied Practice Definition

Log/dokumen nyata per lesson/modul, disesuaikan level risiko — bukan cuma teori: **gear checklist** diterapkan ke persiapan trip nyata (day-hike sampai overnight), **rute navigasi** dipraktikkan nyata dengan peta & kompas (atau area familiar berisiko rendah bila belum pernah trekking sungguhan) dan dicatat waypoint-nya, **trip plan tertulis** (rute, cuaca, kontinjensi, turn-around time) untuk trip nyata atau rencana realistis, **speech/refleksi risk-assessment** dari kondisi cuaca/medan nyata yang dihadapi, dan **mock expedition plan** di level lanjut. Untuk modul **technical climbing, rope skills, dan alpine/glacier travel**: Applied Practice di course ini terbatas pada simulasi konseptual di darat/dinding rendah yang aman (mis. latihan simpul di rumah, latihan sistem belay dengan pengawasan instruktur bersertifikat) — **praktik nyata di medan sebenarnya (tebing, glacier) wajib dilakukan di bawah supervisi langsung instruktur/guide bersertifikat IFMGA/AMGA atau kursus resmi setara**, course ini tidak pernah menyuruh latihan rope/glacier travel nyata tanpa supervisi.

## Project Tiers

- **Mini Project** (per modul): satu log/dokumen terapan berskala kecil — mis. gear checklist untuk satu day-hike nyata, rute map-and-compass yang dipraktikkan dan dicatat, atau worksheet risk-assessment dari satu skenario cuaca.
- **Intermediate Project** (per beberapa modul, checkpoint): gabungan artefak lintas modul — mis. **rencana trip overnight lengkap** (gear + navigasi + camping + cuaca) setelah modul fondasi, atau **trip leader packet** (rencana risiko + logistik multi-hari + rencana kepemimpinan kelompok) setelah modul lanjut.
- **Capstone / Portfolio Project** (akhir course): **Mock Multi-Day Expedition Plan** — rencana ekspedisi gunung multi-hari lengkap dan realistis (rute, fitness prep, gear list penuh, rencana navigasi, kontinjensi cuaca, protokol risiko/altitude, rencana kepemimpinan kelompok, dan bagian teknis — kapan rope skills/technical climbing dibutuhkan beserta rencana mendapatkan supervisi bersertifikat untuk itu) — **dokumen perencanaan, bukan eksekusi nyata rope/glacier travel tanpa guide**. Disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak juga dicek lewat lensa berikut:

- **Risk Awareness & Margin** — apakah rencana punya turn-around time yang jelas, margin waktu/cuaca, dan rencana kontinjensi realistis — atau optimis berlebihan (best-case-only planning) yang jadi pola umum penyebab kecelakaan di laporan AAC *Accidents*?
- **Technical Accuracy** — apakah pilihan gear, teknik navigasi, dan (bila relevan) logika sistem tali/anchor sesuai standar nyata (Freedom of the Hills, UIAA), bukan asumsi yang terdengar masuk akal tapi salah?
- **Preparedness Realism** — apakah fitness, pengalaman, dan logistik yang dipersiapkan benar-benar match dengan skala objektif (day-hike vs overnight vs multi-hari vs technical), bukan melompat level?
- **Scope Honesty** — apakah rencana secara eksplisit menandai bagian mana yang butuh supervisi/sertifikasi bersertifikat (rope skills, glacier travel, avalanche terrain) vs bagian yang aman dijalankan mandiri — pelanggaran "confidently under-qualified" (pola penyebab kecelakaan paling sering di laporan AAC) ditandai eksplisit.

## Assessment Form

Concept-check singkat setelah bagian teori tiap lesson, skenario go/no-go (diberi kondisi cuaca/medan/kondisi tim tertentu, learner memutuskan lanjut/mundur dan menjelaskan alasan berbasis prinsip domain ini — bukan tebakan), dan self-assessment checklist kesiapan yang disilangkan ke kerangka kompetensi *Freedom of the Hills*/AMGA per level objektif.

## Practitioner Reference Frame

Bandingkan beberapa lensa untuk topik yang sama: **guide bersertifikat IFMGA/AMGA** (standar kompetensi teknis penuh, terlatih puluhan hari praktik langsung) vs **instruktur wilderness leadership NOLS** (fokus keputusan kelompok & expedition behavior di ekspedisi panjang) vs **analis insiden AAC** (pembelajaran dari data kecelakaan nyata, bukan teori) vs **pendaki akhir pekan berpengalaman tanpa sertifikasi formal**. Kontraskan eksplisit dengan pola "confidently under-qualified" — pendaki yang percaya diri berlebih tanpa kompetensi/persiapan yang sepadan dengan objektifnya — ditandai sebagai pola yang harus dihindari, bukan ditiru, konsisten dengan tema berulang di laporan AAC *Accidents in North American Climbing*.

## Domain-Specific Standards

`domains/mountaineering/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit dan wajib:

- **Peringatan risiko wajib dan tidak bisa dilewati.** Mountaineering, technical climbing, dan alpine travel membawa risiko cedera serius bahkan kematian yang nyata dan didokumentasikan luas (lihat AAC *Accidents in North American Climbing*). Ini bukan disclaimer formalitas — risikonya sungguhan.
- **Rope skills dan glacier travel wajib instruksi langsung bersertifikat sebelum penerapan nyata.** Simpul, sistem anchor, belay, rappelling, self-arrest, dan glacier travel/crevasse rescue TIDAK PERNAH diajarkan di course ini sebagai sesuatu yang siap dipraktikkan mandiri di medan sebenarnya — wajib diambil lewat instruktur/guide bersertifikat IFMGA/AMGA (atau badan setara nasional) atau kursus resmi (mis. AIARE untuk avalanche) sebelum penerapan nyata di tebing/glacier.
- **Course ini mengajarkan konsep dan persiapan, bukan pengganti pelatihan bersertifikat/guide.** Modul teknis (climbing, rope skills, alpine safety) memberi pemahaman konseptual, kosakata teknis, dan kerangka pengambilan keputusan yang benar — supaya saat belajar langsung dengan guide bersertifikat, learner sudah tidak buta konsep — bukan sertifikasi atau izin praktik mandiri.
- **Eskalasi ke profesional/otoritas resmi**: kondisi medis di ketinggian yang tidak membaik dengan turun (descent) segera, gejala HACE/HAPE, cedera serius, atau kondisi cuaca/medan ekstrem — mentor mengarahkan ke evakuasi/pertolongan medis darurat resmi (SAR/rescue lokal, tenaga medis berlisensi), bukan menyarankan menunggu atau menangani sendiri di luar first aid dasar.

## Domain Goal

Learner memiliki fondasi lengkap untuk merencanakan dan (secara konsep) memimpin kegiatan gunung secara aman dan bertanggung jawab — dari hiking terstruktur pertama dengan gear dan navigasi dasar yang benar, sampai mampu menyusun rencana ekspedisi multi-hari yang realistis (fitness, logistik, navigasi, cuaca, risiko, kepemimpinan kelompok) dan memahami secara konseptual kapan serta bagaimana technical climbing/rope skills/alpine safety dibutuhkan — dibuktikan lewat rangkaian log/trip plan nyata sepanjang course dan Mock Multi-Day Expedition Plan sebagai capstone, dengan kesadaran eksplisit dan konsisten bahwa penerapan teknis nyata (rope, glacier) wajib lewat instruktur/guide bersertifikat, bukan hasil membaca course ini saja.

## Registration History

Diminta 2026-08-14 sebagai bagian dari satu batch course yang dibangun paralel oleh background agent, berdampingan dengan domain `wilderness-survival` (dibangun agent lain di batch yang sama, lihat "Scope" untuk pembagian cakupan eksplisit antara keduanya).
