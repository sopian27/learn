# Future Reading — Domain Pack

> **Status: DRAFT, terdaftar Paused, menunggu approval user.** Course: `courses/reading-the-future/roadmap.md`.

## Domain Name

Future Reading — Reading the Future: membaca arah perubahan (signal, trend, driver, konsekuensi berlapis), menyusun beberapa skenario, memberi probabilitas yang jujur, lalu menyiapkan diri lewat opsi dan bukan lewat ramalan.

## Scope

Domain ini mengajarkan **strategic foresight tingkat personal**: bagaimana seseorang (bukan lembaga riset) mengamati perubahan di sekitarnya, membedakan sinyal dari kebisingan, menelusuri konsekuensi orde pertama-kedua-ketiga, membaca sistem lewat kerangka seperti PESTLE dan megatrend, menyusun beberapa skenario alih-alih satu prediksi, memakai backcasting dari masa depan yang diinginkan, mencatat prediksi dengan confidence dan mengevaluasinya, menjalankan "Future Radar" mingguan, lalu mengubah semuanya jadi tindakan persiapan (optionality, buffer, portofolio skill) yang tetap masuk akal ketika prediksi salah.

Basis awal: dokumen "READING THE FUTURE — Seni Membaca Masa Depan Sebelum Ia Terjadi" yang di-paste user (47 bagian bernomor, framework 10 langkah, Future Journal, latihan 7 hari, challenge 30 hari, Future Readiness Scorecard, 7 level kemampuan, final project "The Next 10 Years"). Domain ini **tidak memindahkan 47 bagian apa adanya**: sekitar 40% isinya sudah punya rumah di domain lain (lihat audit di bawah) dan hanya dirujuk. Sisanya dilipat jadi 11 modul (10 isi + capstone).

**Yang membuat domain ini berbeda:** `critical-thinking` mengajarkan menilai klaim dan memutuskan (konvergen), `intuition-training` mengajarkan kalibrasi penilaian personal lewat prediksi terukur, `future-of-software-engineering` menilai tren teknologi SWE 2027-2030 dengan verdict Adopt/Trial/Assess/Hold. Domain ini mengisi **lapisan metodenya**: cara mendeteksi perubahan, menyusun skenario, dan menyiapkan diri lintas bidang (karier, uang, bisnis, hidup), bukan hanya SWE dan bukan hanya keputusan tunggal.

## Audit Overlap (2026-09-28) — bagian outline yang TIDAK diajarkan ulang di sini

Grep `courses/*/roadmap.md` pada kata kunci scenario/second-order/forecast/premortem/calibration/base rate menunjukkan:

- **Pre-mortem** (outline bagian 21) — sudah `critical-thinking` Module 4.3 (pre-mortem Kahneman/Klein) dan `intuition-training` Module 7.2. Di sini hanya dipakai sebagai alat di Module 6 dan 10 tanpa mengajarkan ulang teorinya.
- **Superforecasting, base rate, update kepercayaan granular** (outline bagian 23) — sudah `critical-thinking` Module 5.3 dan `intuition-training` Module 6 (Good Judgment Project, Ten Commandments). Module 7 di sini hanya jembatan ringkas.
- **Confidence calibration + Prediction Journal** (outline bagian 24-25) — `intuition-training` Module 6-7 punya Prediction Log dan Personal Calibration Report yang lebih dalam. Di sini dipakai sebagai format Future Journal, bukan diajarkan ulang kalibrasinya.
- **Second-order thinking** (outline bagian 10) — `critical-thinking` Module 6.2. Di sini dipakai spesifik untuk menelusuri rantai trend, bukan sebagai mental model umum.
- **Red team / consider-the-opposite / confirmation bias** (outline bagian 35, 42) — `critical-thinking` Module 5.2 dan Module 8. Dirujuk.
- **Tren teknologi SWE (AI-native, platform engineering, FinOps, MLOps) dan Technology Radar** — `future-of-software-engineering`. Contoh SWE di domain ini dipakai sebagai kasus latihan, bukan survei tren.
- **Keuangan pribadi (emergency fund, diversifikasi)** yang muncul di Scorecard dan Black Swan — `personal-finance`.
- **Correlation vs causation** — `statistics-probability` dan `critical-thinking`. Dirujuk singkat di Module 4.

Yang **tersisa dan benar-benar mengisi celah** (tidak ditemukan modul setara di domain manapun): futures cone (possible/plausible/probable/preferable) dan horizon waktu, pemisahan observation vs interpretation sebagai disiplin, taksonomi fad/trend/structural + driver + orde konsekuensi, leading indicator + weak signal + uji signal-vs-noise, sistem dan PESTLE + megatrend, scenario planning (termasuk sumbu 2x2 dan indikator per skenario), backcasting, Future Radar mingguan, membaca masa depan per domain (karier, bisnis, tahap kematangan teknologi), serta antifragility/optionality/black swan sebagai strategi saat prediksi salah.

Secara eksplisit TIDAK mencakup (dirujuk, tidak diduplikasi):

- **Evaluasi argumen, bias kognitif, mental models** — [[critical-thinking]].
- **Kalibrasi penilaian dan intuisi, Prediction Log mendalam** — [[intuition-training]].
- **Survei tren SWE 2027-2030** — [[future-of-software-engineering]].
- **Dasar probabilitas, Bayes, base rate matematis** — [[statistics-probability]].
- **Perencanaan keuangan, dana darurat** — [[personal-finance]].
- **Studi tokoh, sejarah, dan prediksi keagamaan tentang akhir zaman** — [[world-history]], [[islamic-end-times]]. Domain ini tidak memberi ramalan supranatural maupun klaim tentang peristiwa spesifik; hanya metode berbasis bukti.
- **Nasihat investasi atau trading spesifik** — [[investing-stocks]], [[scalping-trading]]. Domain ini tidak menyebut aset mana yang akan naik.

## Trusted Sources / Research Priority

Setiap klaim dicek ulang (nama, tahun, argumen inti) sebelum lesson ditulis, bukan dikutip dari ingatan. Hasil riset awal sesi 2026-09-28 dicatat di kolom Catatan Verifikasi.

| # | Sumber | Dipakai untuk | Catatan Verifikasi (2026-09-28) |
|---|---|---|---|
| 1 | **Philip Tetlock, *Expert Political Judgment* (2005)** | Modul 1: batas kemampuan ahli meramal | Terkonfirmasi: studi ±20 tahun, 284 ahli, ±82.000 prediksi; temuan inti ahli sering tak lebih baik dari orang lain dan jarang dimintai pertanggungjawaban; "fox" lebih akurat dari "hedgehog". Ungkapan populer "lebih buruk dari simpanse melempar dart" adalah plesetan, bukan temuan literal. Baca ulang bab metodologi sebelum menulis. |
| 2 | **Tetlock & Gardner, *Superforecasting* (2015)** dan Good Judgment Project (IARPA, mulai 2011) | Modul 7 (jembatan ke `intuition-training`) | Terkonfirmasi: GJP bagian dari program ACE IARPA; superforecaster Brier ±0,166 vs ±0,259 forecaster biasa; agregasi memperbaiki akurasi. Angka persis dari laporan sekunder, cek ulang dari sumber primer. |
| 3 | **Pierre Wack (Shell), "Scenarios: Uncharted Waters Ahead" dan "Scenarios: Shooting the Rapids", *HBR* 1985** | Modul 6: asal scenario planning korporat | Terkonfirmasi lewat sumber sekunder: Wack memimpin scenario planning di Group Planning Shell akhir 1960-an sampai 1970-an; presentasi 1972 memuat beberapa skenario. Klaim populer "Shell satu-satunya yang siap" dan "naik dari peringkat 7 ke 2" berasal dari cerita ulang, **label [Kisah sekunder] sampai dicek ke sumber primer**. |
| 4 | **Peter Schwartz, *The Art of the Long View* (1991)** | Modul 6: langkah praktis skenario | Terkonfirmasi: Schwartz memimpin scenario planning Shell 1982-1986 dan ikut mendirikan Global Business Network. |
| 5 | **Joseph Voros, futures cone (2003) dari Hancock & Bezold (1994) dan taksonomi Henchey (1978)** | Modul 1: possible/plausible/probable/preferable | Terkonfirmasi: cone dikaitkan Hancock & Bezold (1994), dikembangkan Voros (2003, 7 kategori), asal taksonomi Henchey (1978). Outline user memakai 3 dari 4 kata ini; pemetaan ke cone dibuat eksplisit. |
| 6 | **Igor Ansoff, "Managing Strategic Surprise by Response to Weak Signals" (*California Management Review*, 1975)** | Modul 4: weak signals | Terkonfirmasi: konsep weak signal diperkenalkan Ansoff pada 1970-an. Dalam literatur foresight, weak signal sendiri kontroversial (mudah jadi pembenaran ex-post); Modul 4 mengajarkan skeptisisme itu. |
| 7 | **Francis Aguilar, *Scanning the Business Environment* (1967)** | Modul 5: PESTLE | Terkonfirmasi: asalnya ETPS (Economic, Technical, Political, Social), varian PEST/PESTLE/STEEPLE muncul kemudian. PESTLE adalah checklist pemindaian, bukan model prediktif. |
| 8 | **John B. Robinson, backcasting (1982)** | Modul 7: backcasting | Terkonfirmasi lewat sumber sekunder: istilah dikaitkan Robinson 1982 pada konteks studi energi. Verifikasi paper asli. |
| 9 | **Donella Meadows, *Thinking in Systems* (2008)** | Modul 5: feedback loop, nonlinearitas | Belum dicek ulang di sesi ini; wajib dibaca sebelum Modul 5 ditulis. |
| 10 | **Hodgson, Curry, Leicester dkk., Three Horizons** (dan Curry & Hodgson, *Journal of Futures Studies*, 2008) | Modul 1: horizon waktu | Terkonfirmasi: Three Horizons adalah kerangka perubahan (H1 sistem sekarang, H3 masa depan, H2 inovasi transisi), **bukan** pembagian waktu 1/5/15/50 tahun seperti outline user. Empat horizon outline dilabel [Kerangka user], dijelaskan sebagai heuristik kalender, dan dibandingkan dengan Three Horizons. |
| 11 | **Roy Amara ("Amara's Law")** | Modul 3: salah mengukur jangka pendek vs panjang | Terkonfirmasi: dikaitkan ke Roy Amara (Institute for the Future); tahun pernyataan bervariasi antar sumber (1960-an sampai 1978). Disajikan sebagai heuristik, bukan hukum empiris. |
| 12 | **Steinert & Leifer (2010), Dedehayir & Steinert (2016)** — kritik Gartner Hype Cycle | Modul 9: hype vs maturity | Terkonfirmasi: kajian ilmiah menemukan dukungan empiris lemah, sedikit teknologi yang mengikuti seluruh kurva. Outline user memakai Hype Cycle sebagai fakta; di sini dilabel **[Model populer, bukti empiris lemah]**. |
| 13 | **Nassim Taleb, *The Black Swan* (2007) dan *Antifragile* (2012)** | Modul 10: optionality, black swan | Terkonfirmasi ringkas: optionality = memiliki hak tanpa kewajiban; kritik yang perlu ditampilkan: opsi punya biaya (opportunity cost) dan sulit dieksekusi. Modul menyajikan kedua sisi. |
| 14 | **Gary Klein, "Performing a Project Premortem" (*HBR*, 2007)** | Dirujuk ke `critical-thinking` 4.3 | Klaim "meningkatkan identifikasi risiko 30%" berasal dari riset prospective hindsight (Mitchell, Russo & Pennington, 1989) yang sering dikutip longgar; **tidak dipakai sebagai angka pasti** sampai paper primer dibaca. |

Tidak ada kutipan viral ("masa depan milik mereka yang...") yang dipakai tanpa sumber tertulis. House rule sama dengan `sun-tzu-strategy` (Ledger Kutipan).

## Applied Practice Definition

**Future Journal Entry** — satu entri nyata dengan format tetap (dari outline bagian 38, diperluas dengan label bukti): Signal, Evidence [Terdokumentasi/Anekdot/Opini], Trend (fad/trend/structural), Driver, First-order, Second-order, Third-order, Scenario (minimal 3), Confidence (angka + alasan), What Could Prove Me Wrong (indikator konkret + tanggal), Preparation (satu tindakan kecil), Review Date. Setiap modul menuntut minimal satu entri yang benar-benar diisi tentang hal nyata di sekitar learner, bukan contoh fiktif. Entri yang review-date-nya sudah lewat wajib dievaluasi (benar/salah/sebagian, apa yang dipelajari).

## Project Tiers

- **Mini Project** (per modul): satu latihan konkret (10 observasi tanpa interpretasi, klasifikasi 3 perubahan fad/trend/structural, satu rantai orde 1-3, satu Future Radar minggu ini, satu skenario 2x2, satu backcasting, dst).
- **Intermediate Project**: **30-Day Challenge** (4 minggu: See, Think, Forecast, Prepare) dari outline bagian 40, dengan tambahan wajib: minimal 5 prediksi bertanggal evaluasi yang benar-benar dievaluasi pada akhir course atau dijadwalkan ulang.
- **Capstone**: **"The Next 10 Years"** (outline bagian 44) tentang satu topik pilihan learner, ditambah **Future Readiness Plan** (Karier, Uang, Hidup). Disimpan ke `portofolio/`. Rekomendasi topik default bila bingung: AI dan pekerjaan software engineer (selaras Master Goal), dengan rujukan ke `future-of-software-engineering` untuk data tren.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), tiap entri dicek:

- **Observation vs interpretation terpisah** — apakah data dan kesimpulan tidak dicampur?
- **Label bukti** — apakah tiap klaim tren diberi sumber dan jenis bukti, bukan "katanya" atau "semua orang bilang"?
- **Minimal 3 skenario, bukan 1 prediksi yang dibungkus 3 nama** — apakah skenario benar-benar berbeda pada driver kunci?
- **Indikator yang bisa diamati** — apakah setiap skenario punya tanda konkret yang bisa dicek, bukan "kalau situasi membaik"?
- **Confidence berangka dan bisa disalahkan** — apakah ada tanggal evaluasi dan kondisi yang akan membantah?
- **Rantai konsekuensi tidak melompat** — apakah orde 2 dan 3 punya mekanisme perantara, bukan asumsi?
- **Persiapan tetap masuk akal jika salah** — apakah rencana tahan di lebih dari satu skenario?
- **Tidak jadi peramal** — apakah ada klaim kepastian ("pasti", "tidak mungkin tidak") yang perlu direvisi?
- **Dark side dicek** — apakah ada tanda doom addiction, hype addiction, atau prediction addiction (outline bagian 42)?

## Assessment Form

Studi kasus tertulis: satu Future Journal lengkap, satu set skenario 2x2 dengan indikator, satu backcasting, satu Future Radar mingguan (4 minggu), satu evaluasi prediksi yang sudah jatuh tempo (termasuk yang salah), dan capstone "The Next 10 Years". Bukan quiz hafalan istilah.

## Practitioner Reference Frame

- **Futurist / foresight practitioner** (Shell scenarios, Schwartz, Voros, Hodgson/Curry) — lensa skenario dan horizon.
- **Peneliti forecasting** (Tetlock) — lensa akurasi dan kerendahan hati epistemik.
- **Peneliti strategi** (Ansoff, Aguilar) — lensa scanning dan sinyal lemah.
- **Konteks software engineer:** contoh utama memakai kasus SWE dan AI (skill yang bertahan, biaya AI, struktur kerja) sebagai salah satu konteks, bukan satu-satunya; kasus lain dari uang, kesehatan, dan pendidikan.

## Domain-Specific Standards

- **Tidak ada klaim kepastian tentang masa depan.** Semua pernyataan berbentuk kemungkinan dengan alasan, confidence, dan kondisi pembantah. Frasa "pasti terjadi" hanya boleh muncul sebagai contoh kesalahan.
- **Label bukti eksplisit** pada setiap klaim tren atau sejarah: [Terdokumentasi] (sumber primer atau ulasan sistematik), [Kisah sekunder] (cerita ulang populer), [Model populer, bukti lemah] (Gartner Hype Cycle, weak signal), [Kerangka user] (empat horizon, Future Radar), [Heuristik].
- **Cerita sukses ramalan diperlakukan sebagai survivorship bias sampai dicek** — untuk setiap "yang meramal X" wajib ditanya siapa yang meramal sebaliknya dan salah.
- **Bukan nasihat investasi, medis, hukum, atau karier individual.** Contoh finansial hanya untuk latihan berpikir; keputusan uang nyata dirujuk ke `personal-finance` dan profesional berlisensi.
- **Tidak ada ramalan supranatural, numerologi, atau tanda kiamat.** Bila learner membawa topik itu, dirujuk ke `islamic-end-times` atau ditolak sebagai di luar scope; domain ini hanya metode berbasis bukti.
- **Skenario bukan prediksi** — modul skenario wajib menegaskan bahwa tujuannya menguji ketahanan rencana, bukan memilih pemenang.
- **Doom dan hype dicek balik** — setiap Future Radar wajib memuat minimal satu sinyal yang membantah narasi favorit learner.
- **Kecemasan sebagai batas** — bila latihan risiko memicu cemas berlebih atau doomscrolling, hentikan Future Radar, rujuk `emotional-resilience`, dan profesional bila perlu. House rule sama seperti `courage-to-try` dan `character-development`.
- **Sumber dicek, bukan diingat** — nama peneliti/buku/tahun dibaca ulang sebelum ditulis ke lesson (lihat tabel Trusted Sources).
- **Tidak mengulang materi domain lain** yang sudah dirujuk di "Audit Overlap".

## Domain Goal

Learner dapat mengamati perubahan tanpa langsung menafsirkan, membedakan fad/trend/structural, mencari driver dan menelusuri konsekuensi sampai orde ketiga, memisahkan sinyal dari noise, membaca sistem lewat PESTLE dan megatrend, menyusun skenario 2x2 dengan indikator, memakai backcasting, mencatat prediksi bertanggal dan mengevaluasinya jujur, menjalankan Future Radar mingguan, dan menyiapkan opsi (skill, tabungan, jaringan, kesehatan, reputasi) yang tetap bekerja ketika prediksinya salah. Prinsip utama (dari mantra outline user): *cukup peka melihat perubahan, cukup rendah hati mengakui ketidakpastian, dan cukup siap merespons ketika masa depan datang.*

## Registration History

Dibuat 2026-09-28 atas permintaan langsung user ("buatkan course :" + paste dokumen "READING THE FUTURE — Seni Membaca Masa Depan Sebelum Ia Terjadi", 47 bagian). Guard "Course Aktif di bawah 50%" dicek: Character Development tepat di 3/6 modul (50%, bukan di bawah), Mastering Claude 100% selesai — guard tidak terpicu secara literal, tapi konsisten dengan preseden (`sun-tzu-strategy`, `insanity-thinking`) mentor menanyakan lewat AskUserQuestion. Mentor menyajikan hasil audit overlap awal dan empat opsi (standalone terlipat, standalone penuh 47 bagian, extend `intuition-training`/`critical-thinking`, simpan ke vault saja); user memilih **standalone terlipat**.

Nama domain `future-reading` dan course `reading-the-future` adalah asumsi mentor mengikuti judul dokumen sumber — belum dikonfirmasi user, dikonfirmasi saat approval. **Belum diaudit:** apakah paste berasal dari buku atau catatan sendiri (tidak ada penulis disebut, jadi klaim outline tidak bisa dipetakan ke satu sumber), dan topik capstone mana yang dipilih user (default: AI dan pekerjaan SWE). Ditanyakan saat approval.

Koreksi/label atas outline (semua tercatat di roadmap course): (1) "empat horizon" bukan kerangka standar; (2) Gartner Hype Cycle dipresentasikan outline sebagai model valid padahal bukti empirisnya lemah; (3) Shell/1973 tidak ada di outline tapi akan jadi studi kasus dan berlabel [Kisah sekunder]; (4) angka "80% yakin harus benar 80%" adalah definisi kalibrasi yang benar, tapi memerlukan puluhan prediksi sebelum bermakna (sampel kecil tidak cukup); (5) pola "Technology → ... → Commodity" (outline bagian 30) adalah heuristik, bukan hukum.
