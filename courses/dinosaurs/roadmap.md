# Course Roadmap

## Course Information

* Course Name: Dinosaurs — dari 0 sampai Expert
* Category: Paleontologi & Prasejarah (Domain: dinosaurs — lihat `domains/dinosaurs/DOMAIN.md`)
* Difficulty: Level 0 (paham dinosaurus level pop-culture/Jurassic Park, belum pernah membaca satu paper paleontologi asli atau membangun cladogram sendiri) → Level 3 (mampu membaca & mensintesis literatur paleontologi primer, membangun cladogram sederhana dari karakter bersama, membedakan klaim solid/inferensi/hipotesis/spekulasi pada topik yang masih diperdebatkan aktif seperti asal-usul bulu dan metabolisme, dan mengoreksi miskonsepsi populer dengan bukti bersumber)
* Estimated Duration: 14 modul (13 modul konten + 1 modul capstone), rata-rata 3-4 lesson/modul (~48 lesson)
* Prerequisites: Tidak ada prasyarat akademik atau teknis (tidak butuh coding/matematika, berbeda dari course sains lain di repo ini seperti `black-hole`/`astronomy-and-life-in-the-universe`). Kesediaan menulis laporan berbasis bukti (bukan cuma menghafal nama dinosaurus) sesuai `domains/dinosaurs/DOMAIN.md` bagian "Applied Practice Definition".

---

# Catatan Scope

**Zero overlap dikonfirmasi.** Sebelum course ini didesain, `progress/roadmap.md` dicek penuh (grep untuk "dinosaur", "paleontolog", "Mesozoic", "T-Rex" — nihil hasil) dan `courses/`/`domains/` di-glob untuk folder terkait — tidak ada satu pun course atau domain existing yang menyentuh dinosaurus atau paleontologi. Ini domain baru murni, bukan perluasan dari course lain.

**Batas scope eksplisit — apa yang TIDAK dicakup:**

* **Bukan course "prehistoric life" umum.** Tidak mencakup evolusi kehidupan pra-Mesozoikum secara mendalam (Cambrian explosion, trilobita, dll.), megafauna Zaman Es (mammoth, dll.), atau evolusi manusia — semua itu di luar scope dan butuh domain terpisah kalau diminta di masa depan.
* **Bukan course astronomi/geologi umum.** `courses/astronomy-and-life-in-the-universe/roadmap.md` sudah menyinggung evolusi kehidupan Bumi secara sangat singkat di Module 6 (astrobiologi/abiogenesis) tapi TIDAK pernah menyebut dinosaurus atau Mesozoikum sama sekali — tidak ada overlap.
* **Bukan course world-history.** `courses/world-history/roadmap.md` mencakup sejarah MANUSIA (prasejarah manusia ke atas) — dinosaurus punah 66 juta tahun sebelum manusia modern ada, tidak pernah disinggung di course itu.
* **Pterosaurus dan reptil laut (plesiosaurus, mosasaurus, ichthyosaurus) disebut hanya sebagai KONTEKS ekosistem di Module 11 (Paleoekologi)** — course ini secara eksplisit menegaskan bahwa keduanya BUKAN dinosaurus (miskonsepsi populer umum), tapi tidak membangun anatomi/klasifikasi mendalam untuk kelompok itu sendiri (di luar scope).

**Kalibrasi gaya:** Mengikuti pola "popular-but-rigorous" dari `courses/astronomy-and-life-in-the-universe/roadmap.md` — bukan course matematis-derivasional seperti `courses/black-hole/roadmap.md` (tidak ada aljabar/fisika berat), tapi tetap disiplin secara epistemik: paleontologi adalah bidang yang berubah cepat 20-30 tahun terakhir (revolusi bulu, revisi metabolisme, kladistik menggantikan taksonomi Linnaean lama) dan penuh perdebatan riset aktif (asal-usul bulu, status hangat/dingin-berdarah, tunggal vs ganda penyebab kepunahan K-Pg) — course ini WAJIB melabeli status epistemik tiap klaim besar (Fakta Solid / Inferensi dari Bukti / Hipotesis Riset Terbuka / Spekulasi), pola yang sama dipakai `domains/black-hole/DOMAIN.md` dan `domains/astronomy-and-life-in-the-universe/DOMAIN.md`. Rincian lengkap ada di `domains/dinosaurs/DOMAIN.md`.

**Riset dasar course ini** (ringkasan, sumber lengkap dengan sitasi di `domains/dinosaurs/DOMAIN.md`): dicek terhadap penelitian/tinjauan terkini termasuk status Ornithoscelida (Baron, Norman & Barrett 2017; Langer & Ezcurra 2024 review — masih hipotesis minoritas, belum konsensus), status perdebatan asal-usul bulu (Royal Society Biology Letters 2024, NHM 2025 — masih belum ada konsensus soal seberapa jauh ke belakang bulu berevolusi), status metabolisme dinosaurus (studi biomarker lipid 2022 vs reanalisis UC Davis, Chiarenza et al. 2024), dan status konsensus kepunahan K-Pg (tinjauan 41 ilmuwan 12 negara 2024-2025 menguatkan Chicxulub sebagai pemicu utama, Deccan Traps sebagai faktor penekan ekosistem sebelum dampak, bukan penyebab primer independen).

---

# Learning Objectives

* [ ] Menjelaskan paleontologi sebagai disiplin ilmiah: metode, jenis bukti (fosil tubuh, jejak/ichnofossil, koprolit), dan proses taphonomy yang membuat catatan fosil secara inheren bias/tidak lengkap
* [ ] Menempatkan dinosaurus dalam konteks waktu geologis yang benar — Triassic, Jurassic, Cretaceous — masing-masing dengan geografi benua, iklim, dan flora/fauna yang berbeda signifikan
* [ ] Menjelaskan metode kladistik untuk membangun pohon kekerabatan dari karakter bersama, dan menerapkannya untuk memahami klasifikasi Saurischia vs Ornithischia (termasuk ironi penamaan "bird-hipped" vs "lizard-hipped")
* [ ] Menjelaskan biologi & biomekanika theropoda termasuk Tyrannosaurus rex secara berbasis bukti (bite force, growth curve, indra), bukan mitos populer
* [ ] Membedakan fakta anatomi Dromaeosauridae (raptor) yang sesungguhnya dari penggambaran Jurassic Park, dan menjelaskan revolusi bulu yang mengubah paleontologi sejak 1990-an
* [ ] Menjelaskan hubungan evolusioner theropoda-ke-burung sebagai kesimpulan kladistik solid, termasuk mengapa "burung adalah dinosaurus" bukan sekadar analogi
* [ ] Menjelaskan anatomi & strategi hidup kelompok herbivora utama: sauropoda (gigantisme), ceratopsia, hadrosauria, stegosauria, dan ankylosauria — termasuk perdebatan fungsi struktur ikonik (tanduk, frill, plate)
* [ ] Menjelaskan perilaku dinosaurus (nesting, herding, hunting) secara ketat berbasis bukti trace fossil, membedakan eksplisit dari ekstrapolasi spekulatif
* [ ] Merekonstruksi ekosistem era Mesozoikum (flora, fauna kontemporer non-dinosaurus) dari satu formasi geologis nyata sebagai studi kasus
* [ ] Menjelaskan kepunahan K-Pg secara kuantitatif-evidential: bukti dampak Chicxulub, peran Deccan Traps, dan status konsensus ilmiah terkini
* [ ] Mengoreksi miskonsepsi populer (dari media seperti Jurassic Park) terhadap sains terkini, termasuk sejarah paleoart dan mengapa rekonstruksi dinosaurus terus berubah
* [ ] Menyelesaikan Capstone Project berbasis bukti yang disimpan ke `portofolio/`

---

# Course Modules

## Module 1 — Paleontologi sebagai Sains & Bagaimana Fosil Terbentuk (Taphonomy)

Description: Fondasi metodologis sebelum menyentuh satu dinosaurus pun — bagaimana paleontologi bekerja sebagai sains, dan kenapa catatan fosil yang kita punya hari ini sangat bias dan tidak lengkap.

Lessons:

* [ ] 1.1 Paleontologi sebagai disiplin ilmiah — jenis bukti (fosil tubuh, trace fossil/ichnofossil, koprolit) dan bagaimana klaim diverifikasi/direvisi
* [ ] 1.2 Taphonomy — proses fosilisasi (penguburan cepat, permineralisasi, cetakan/mold-cast) dan kenapa jaringan lunak nyaris tidak pernah terawetkan (kecuali kondisi luar biasa)
* [ ] 1.3 Lagerstätten — situs pengawetan luar biasa (Liaoning Tiongkok, Solnhofen Jerman) yang jadi sumber sebagian besar temuan bulu dan jaringan lunak
* [ ] 1.4 Metode penanggalan — stratigrafi (relative dating) dan radiometric dating (absolute dating), dan kenapa keduanya dipakai bersama

Mini Project: Tulisan pendek (1 halaman) menganalisis bias catatan fosil — kenapa dinosaurus kecil/bertulang rapuh/hidup di lingkungan tertentu jauh lebih jarang ditemukan dibanding dinosaurus besar bertulang kuat di lingkungan pengendapan aktif, dengan minimal 2 contoh nyata.

Status: Not Started

---

## Module 2 — Waktu Geologis & Dunia Mesozoikum: Triassic, Jurassic, Cretaceous

Description: Konteks dunia tempat dinosaurus hidup — tiga periode dengan geografi benua, iklim, dan tekanan evolusioner yang sangat berbeda satu sama lain, bukan "zaman dinosaurus" yang monolitik.

Lessons:

* [ ] 2.1 Deep time & skala waktu geologis — kenapa 165 juta tahun era dinosaurus (Triassic akhir–Cretaceous akhir) jauh lebih lama dari jarak waktu manusia ke T-Rex terakhir
* [ ] 2.2 Triassic (~252-201 juta tahun lalu) — superbenua Pangea, pemulihan pasca-kepunahan Permian-Triassic, iklim ekstrem, dan dinosaurus sebagai pemain kecil di antara archosaur lain
* [ ] 2.3 Jurassic (~201-145 juta tahun lalu) — Pangea mulai terpecah, iklim lebih lembap, kebangkitan sauropoda raksasa
* [ ] 2.4 Cretaceous (~145-66 juta tahun lalu) — benua makin terpisah, radiasi tumbuhan berbunga (angiosperma), puncak diversifikasi dinosaurus sebelum kepunahan

Mini Project: Buat linimasa/peta visual (boleh diagram sederhana) yang menunjukkan posisi benua di tiap periode dan mengaitkannya dengan kapan kelompok dinosaurus utama (yang akan dipelajari Module 4-9) pertama muncul.

Status: Not Started

---

## Module 3 — Anatomi Dinosaurus & Fondasi Klasifikasi (Kladistik)

Description: Apa yang secara teknis mendefinisikan "dinosaurus" (bukan sekadar "reptil besar prasejarah"), dan bagaimana paleontolog membangun pohon kekerabatan secara ilmiah lewat kladistik — termasuk perdebatan klasifikasi yang masih berjalan hari ini.

Lessons:

* [ ] 3.1 Apa yang mendefinisikan dinosaurus secara anatomis — postur tegak (upright stance) dari soket pinggul erect, dibedakan eksplisit dari reptil bersayap (pterosaurus) dan reptil laut (plesiosaurus/mosasaurus) yang BUKAN dinosaurus
* [ ] 3.2 Kladistik — metode membangun pohon kekerabatan dari karakter turunan bersama (shared derived characters), menggantikan taksonomi Linnaean lama
* [ ] 3.3 Saurischia ("berpinggul kadal") vs Ornithischia ("berpinggul burung") — dan ironi bahwa burung sesungguhnya berevolusi dari kelompok Saurischia, bukan Ornithischia
* [ ] 3.4 Perdebatan klasifikasi terkini — proposal Ornithoscelida (Baron, Norman & Barrett 2017) yang menantang pembagian Saurischia/Ornithischia 130 tahun; dinyatakan eksplisit sebagai hipotesis MINORITAS yang belum jadi konsensus (Langer & Ezcurra 2024)

Mini Project: Bangun cladogram sederhana sendiri dari satu set karakter bersama yang diberikan (5-6 takson hipotetik atau nyata) — latihan tangan metode kladistik, bukan menghafal hasil jadi.

Status: Not Started

---

## Module 4 — Triassic: Asal-Usul Dinosaurus

Description: Dinosaurus paling awal bukanlah penguasa dunia — mereka pemain kecil di antara kerabat archosaur lain (pseudosuchian/crurotarsan) yang justru mendominasi Triassic.

Lessons:

* [ ] 4.1 Dinosaurus paling awal — Eoraptor, Herrerasaurus, Coelophysis — anatomi dan ukuran tubuh yang jauh lebih kecil dari bayangan populer
* [ ] 4.2 Kenapa dinosaurus bukan penguasa Triassic — pseudosuchian (kerabat buaya) mendominasi banyak niche ekologis yang belakangan diambil alih dinosaurus
* [ ] 4.3 Kepunahan akhir-Triassic dan kebangkitan oportunistik dinosaurus mengisi niche yang kosong menuju Jurassic

Mini Project: Studi kasus perbandingan tertulis (1 halaman) anatomi satu dinosaurus Triassic awal vs satu pseudosuchian kontemporer — apa persamaan/perbedaan yang membuat keduanya awalnya tampak "setara" secara evolusioner.

Status: Not Started

---

## Module 5 — Theropoda I: Anatomi Predator, Biomekanika, dan Tyrannosaurus rex

Description: Rencana tubuh theropoda (bipedal, karnivora) sebagai fondasi, lalu deep-dive ke dinosaurus paling terkenal di dunia — T-Rex — secara berbasis bukti, bukan mitos film.

Lessons:

* [ ] 5.1 Rencana tubuh theropoda — bipedalisme, tengkorak, gigi serrated, cakar
* [ ] 5.2 T-Rex: bite force, biomekanika rahang, dan studi ontogeni (kurva pertumbuhan dari juvenil ke dewasa berdasarkan histologi tulang)
* [ ] 5.3 T-Rex: indra (bulbus olfaktori besar → penciuman tajam) dan perdebatan kecepatan lari (bukan pelari cepat seperti di film, estimasi ilmiah jauh lebih lambat)
* [ ] 5.4 Temuan jaringan lunak Schweitzer (kolagen, pembuluh darah dari fosil femur T-Rex) — status epistemik: penemuan solid, tapi interpretasi/mekanisme pengawetan masih diperdebatkan

Mini Project: Laporan sintesis (1-2 halaman) yang mengklasifikasikan 5 klaim populer tentang T-Rex (kecepatan lari, penglihatan hanya mendeteksi gerakan, bulu di masa juvenil, dll.) ke dalam Fakta Solid/Inferensi/Hipotesis Terbuka/Mitos-Terbantahkan, berdasar sumber primer.

Status: Not Started

---

## Module 6 — Raptor, Dromaeosauridae, dan Revolusi Bulu

Description: Kelompok yang paling salah digambarkan di budaya populer — dan modul di mana revolusi bulu paleontologi (dimulai 1990-an) diperkenalkan penuh.

Lessons:

* [ ] 6.1 Dromaeosauridae sesungguhnya — Velociraptor (seukuran kalkun, jauh lebih kecil dari film), Deinonychus, Utahraptor — dan kenapa Jurassic Park menggambarkan ukuran Deinonychus dengan nama Velociraptor
* [ ] 6.2 Cakar sabit (sickle claw) — perdebatan fungsi: alat membunuh mangsa besar vs alat memanjat/mencengkeram
* [ ] 6.3 Revolusi bulu — temuan Sinosauropteryx (1996) dan Microraptor (dinosaurus bersayap empat) dari Lagerstätten Liaoning, mengubah total pemahaman soal penampilan dinosaurus
* [ ] 6.4 Perdebatan asal-usul bulu yang MASIH terbuka per 2025 — apakah bulu berevolusi di leluhur bersama dinosaurus+pterosaurus (Avemetatarsalia), khusus dinosaurus, atau khusus theropoda — dinyatakan eksplisit sebagai riset belum tuntas

Mini Project: Laporan "mitos vs sains" (1-2 halaman) membandingkan Velociraptor budaya populer vs Velociraptor ilmiah, dengan sumber bersitasi untuk tiap koreksi.

Status: Not Started

---

## Module 7 — Evolusi Menuju Burung: Dinosaurus yang Tidak Pernah Punah

Description: Kesimpulan paling mengejutkan sekaligus paling solid dalam paleontologi modern — burung BUKAN keturunan dinosaurus dalam arti metaforis, burung SECARA KLADISTIK adalah dinosaurus theropoda yang masih hidup.

Lessons:

* [ ] 7.1 Archaeopteryx sebagai fosil transisi klasik — anatomi campuran (gigi, cakar tangan, ekor bertulang + bulu penerbangan) dan posisi kladistiknya
* [ ] 7.2 Karakter theropoda yang diwariskan burung — tulang berongga, sistem kantung udara, furcula (wishbone), bulu asimetris untuk terbang
* [ ] 7.3 Argumen kladistik "burung ADALAH dinosaurus" — kenapa ini kesimpulan filogenetik ketat, bukan sekadar analogi populer
* [ ] 7.4 Mengapa hanya garis burung yang selamat dari kepunahan K-Pg di antara seluruh dinosaurus (preview eksplisit ke Module 12)

Mini Project: Tabel bukti (evidence table) yang memetakan minimal 6 fitur transisional theropoda → burung, tiap baris disertai fosil/sumber rujukan.

Status: Not Started

---

## Module 8 — Herbivora I: Sauropoda — Raksasa Berleher Panjang

Description: Hewan darat terbesar yang pernah hidup — bagaimana biomekanika memungkinkan gigantisme ekstrem, dan kenapa itu penting secara ilmiah, bukan cuma "besar keren".

Lessons:

* [ ] 8.1 Rencana tubuh sauropoda — leher panjang, kaki seperti tiang (columnar), ekor panjang sebagai penyeimbang
* [ ] 8.2 Biomekanika gigantisme — sistem pernapasan mirip burung (kantung udara, pneumatisitas tulang) sebagai kunci kenapa sauropoda bisa setumbuh itu tanpa kolaps sistem pernapasan
* [ ] 8.3 Perdebatan postur leher — jangkauan gerak leher sauropoda (horizontal luas vs elevasi tinggi) masih diperdebatkan tergantung genus
* [ ] 8.4 Keragaman sauropoda — Diplodocus, Brachiosaurus, Argentinosaurus, Patagotitan — dan metode estimasi massa tubuh dari fosil parsial

Mini Project: Perbandingan tertulis metode estimasi massa tubuh (volumetric vs scaling dari lingkar tulang paha) untuk 2 sauropoda berbeda, dengan rentang ketidakpastian dinyatakan eksplisit (bukan angka tunggal seolah pasti).

Status: Not Started

---

## Module 9 — Herbivora II: Ceratopsia, Hadrosauria, Stegosauria, dan Ankylosauria

Description: Empat kelompok Ornithischia paling ikonik — dengan fokus pada perdebatan fungsi struktur anatomi mencolok mereka (tanduk, frill, crest, plate, gada ekor) yang sering diklaim tunggal secara populer padahal multi-fungsi.

Lessons:

* [ ] 9.1 Ceratopsia (Triceratops, Protoceratops) — tanduk & frill: perdebatan fungsi (pertahanan vs display/seleksi seksual vs termoregulasi), tidak ada jawaban tunggal yang disepakati penuh
* [ ] 9.2 Hadrosauria ("bebek-berparuh") — baterai gigi (dental battery) untuk mengunyah efisien, dan crest berongga sebagai kemungkinan alat vokalisasi (dianalisis lewat model akustik CT-scan)
* [ ] 9.3 Stegosauria — plate punggung Stegosaurus: perdebatan fungsi (pertahanan vs termoregulasi vs display), dan gada duri ekor (thagomizer) sebagai senjata defensif yang lebih solid buktinya
* [ ] 9.4 Ankylosauria — armor tubuh penuh dan gada ekor (tail club) sebagai adaptasi defensif terspesialisasi

Mini Project: Laporan fungsional-komparatif (1-2 halaman) untuk SATU struktur ikonik pilihan sendiri (plate Stegosaurus, frill Triceratops, atau crest hadrosaur) yang memetakan seluruh hipotesis fungsi yang pernah diajukan beserta bukti pendukung/lemahnya masing-masing.

Status: Not Started

---

## Module 10 — Perilaku & Kehidupan Sosial Dinosaurus (Berbasis Bukti)

Description: Modul paling rawan overclaim di paleontologi populer — perilaku HANYA bisa diinferensi dari trace fossil dan analog modern, course ini melatih disiplin membedakan bukti solid dari ekstrapolasi.

Lessons:

* [ ] 10.1 Nesting & parental care — Maiasaura ("kadal induk baik"), sarang, cengkeraman telur sebagai bukti langsung perilaku merawat anak
* [ ] 10.2 Perilaku berkelompok (herding/gregarious) — bukti dari trackway (jejak massal) dan bonebed (kuburan massal satu spesies)
* [ ] 10.3 Strategi berburu theropoda — perdebatan pack-hunting: bukti sesungguhnya jauh lebih ambigu daripada asumsi populer (kebanyakan berasal dari 1-2 lokasi trackway/bonebed yang diinterpretasi berbeda-beda oleh peneliti berbeda)
* [ ] 10.4 House rule epistemik eksplisit: perilaku dinosaurus HANYA didukung sejauh trace fossil membuktikannya — analogi dari hewan modern (burung, buaya) adalah HIPOTESIS pembanding, bukan bukti langsung

Mini Project: Klasifikasikan 5 klaim populer tentang perilaku dinosaurus (mis. "T-Rex berburu berkelompok", "Triceratops hidup soliter") ke dalam Fakta Solid/Inferensi Trace-Fossil/Hipotesis dari Analog Modern/Spekulasi Tak Berdasar, dengan justifikasi tertulis tiap klasifikasi.

Status: Not Started

---

## Module 11 — Paleoekologi: Ekosistem Era Mesozoikum

Description: Dinosaurus tidak hidup sendirian — modul ini merekonstruksi ekosistem penuh: flora, dan fauna kontemporer yang SERING disalahartikan sebagai dinosaurus padahal bukan.

Lessons:

* [ ] 11.1 Flora Mesozoikum — dominasi pakis/sikas/konifer di Triassic-Jurassic, lalu radiasi tumbuhan berbunga (angiosperma) di Cretaceous dan koevolusinya dengan herbivora & penyerbuk
* [ ] 11.2 Fauna kontemporer BUKAN dinosaurus — pterosaurus (reptil terbang) dan reptil laut (plesiosaurus, mosasaurus, ichthyosaurus) dinyatakan eksplisit sebagai kelompok terpisah, miskonsepsi populer dikoreksi langsung
* [ ] 11.3 Mamalia awal era Mesozoikum — kecil, kemungkinan besar nokturnal, mengisi niche marginal di bawah bayang-bayang dominasi dinosaurus
* [ ] 11.4 Studi kasus formasi geologis nyata (Morrison Formation Jurassic Amerika Utara ATAU Hell Creek Formation Cretaceous akhir) sebagai rekonstruksi jaring makanan (food web) yang terdokumentasi baik

Mini Project: Bangun diagram jaring makanan (food web) untuk SATU formasi geologis pilihan, dengan daftar spesies bersumber dari literatur paleontologi/museum (bukan daftar dari ingatan).

Status: Not Started

---

## Module 12 — Kepunahan K-Pg: Bukti, Chicxulub, dan Status Konsensus Ilmiah

Description: Bagaimana era dinosaurus (non-unggas) berakhir — dianalisis lewat bukti kuantitatif nyata, bukan narasi "meteor jatuh, semua mati" yang oversimplified.

Lessons:

* [ ] 12.1 Batas K-Pg (66 juta tahun lalu) — apa yang punah vs apa yang selamat (termasuk burung, mamalia, sebagian besar reptil kecil)
* [ ] 12.2 Bukti dampak Chicxulub — lapisan iridium global, shocked quartz, tektit, kawah 150 km di Meksiko — dan kenapa bukti ini dianggap sangat kuat oleh komunitas ilmiah
* [ ] 12.3 Deccan Traps — vulkanisme besar-besaran di India yang menekan ekosistem SEBELUM dampak, dinyatakan eksplisit sebagai faktor kontribusi/penekan, BUKAN penyebab tunggal independen menurut konsensus terkini
* [ ] 12.4 Status konsensus 2024-2025 — tinjauan multi-institusi (41 ilmuwan, 12 negara) menguatkan Chicxulub sebagai pemicu utama; apa yang masih diperdebatkan (kontribusi relatif, waktu tepat interaksi dampak-vulkanisme) dinyatakan eksplisit sebagai riset terbuka, bukan settled 100%

Mini Project: Laporan penimbangan bukti (evidence-weighing report, 1-2 halaman) yang membandingkan hipotesis dampak tunggal vs kontribusi ganda (impact + volcanism), mengutip minimal 2 sumber primer/tinjauan ilmiah.

Status: Not Started

---

## Module 13 — Rekonstruksi Modern: Dari Paleoart Usang ke Sains Terkini

Description: Modul penutup materi — bagaimana sains menentukan rupa dinosaurus hari ini, kenapa rekonstruksi terus berubah, dan koreksi eksplisit terhadap mitos populer dari film/media yang sudah ketinggalan zaman.

Lessons:

* [ ] 13.1 Sejarah paleoart — dari patung Crystal Palace 1850an (postur salah total, "reptil besar gemuk") ke Dinosaur Renaissance (Robert Bakker, 1960-70an, dinosaurus aktif & lincah) ke era bulu (1990an-sekarang)
* [ ] 13.2 Bukti jaringan lunak & warna — melanosom sebagai indikator warna (Schweitzer dkk.), status epistemik eksplisit: metode solid untuk sebagian kelompok (pennaraptora/burung), MASIH diperdebatkan penerapannya di luar kelompok itu
* [ ] 13.3 Perdebatan metabolisme — dingin-berdarah vs hangat-berdarah vs mesotermi: ringkasan studi biomarker 2022 vs reanalisis 2023, dan kenapa jawabannya kemungkinan BERAGAM antar kelompok dinosaurus, bukan satu jawaban universal (Chiarenza dkk. 2024)
* [ ] 13.4 Koreksi mitos Jurassic Park & media populer secara sistematis — ukuran Velociraptor, Dilophosaurus (frill/racun sepenuhnya fiksi), penglihatan T-Rex, desain ulang Spinosaurus 2014 (semi-akuatik) — setiap koreksi disertai sumber

Mini Project: Tabel "mitos vs sains" (minimal 5 klaim populer) dengan koreksi bersumber untuk tiap baris — menyatukan seluruh house rule epistemik course ini dalam satu artefak, jadi persiapan langsung untuk Capstone.

Status: Not Started

---

## Module 14 — Capstone: Dinosaur Research Portfolio

Description: Mengintegrasikan seluruh Module 1-13 jadi satu capstone project penuh, mengikuti `domains/dinosaurs/DOMAIN.md` bagian "Project Tiers".

Lessons:

* [ ] 14.1 Menentukan jalur capstone & scope: (A) Dinosaur Family Cladogram & Field Guide, (B) Paleoart Accuracy Audit, atau (C) K-Pg Extinction Research Report
* [ ] 14.2 Riset & pengumpulan sumber primer/institusional sesuai jalur yang dipilih
* [ ] 14.3 Penyusunan artefak inti sesuai jalur (cladogram/audit visual/laporan riset), termasuk validasi terhadap sumber primer
* [ ] 14.4 Dokumentasi tertulis final: status epistemis eksplisit tiap klaim besar (Fakta Solid/Inferensi/Hipotesis Terbuka/Spekulasi-Terbantahkan), submit ke `portofolio/`

Capstone Project: Pilih SATU — **(A) Dinosaur Family Cladogram & Field Guide**: bangun cladogram untuk satu klad pilihan (mis. Theropoda atau Ornithischia) dari karakter bersama nyata, plus field-guide singkat tiap taksonnya (anatomi, periode, formasi, status bukti bulu/perilaku). **(B) Paleoart Accuracy Audit**: pilih satu penggambaran dinosaurus terkenal (film, buku anak, museum lama), audit akurasinya poin demi poin terhadap sains terkini, dan tulis deskripsi rekonstruksi terkoreksi lengkap dengan sitasi. **(C) K-Pg Extinction Research Report**: deep-dive Module 12 diperluas jadi laporan riset penuh berbasis sumber primer, mencakup bukti Chicxulub, peran Deccan Traps, dan pola kepunahan selektif (apa yang selamat dan kenapa). Semua jalur wajib validasi terhadap sumber primer/institusional, plus dokumentasi status epistemis eksplisit untuk tiap klaim besar.

Status: Not Started

---

# Capstone Project

Description: Lihat Module 14 di atas untuk rincian lengkap tiga jalur capstone (Dinosaur Family Cladogram & Field Guide / Paleoart Accuracy Audit / K-Pg Extinction Research Report). Capstone course ini SATU dengan Capstone Project Module 14 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/dinosaurs/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md` DITAMBAH lensa Review Style domain ini dari `domains/dinosaurs/DOMAIN.md`: **Evidentiary Grounding** (tiap klaim besar bisa ditelusuri ke fosil/sumber nyata), **Epistemic Discipline** (Fakta Solid/Inferensi/Hipotesis Terbuka/Spekulasi dibedakan eksplisit, terutama untuk topik yang masih diperdebatkan aktif seperti asal-usul bulu, metabolisme, dan kontribusi relatif Chicxulub vs Deccan Traps), **Anatomical/Functional Reasoning** (penjelasan mengikuti logika biomekanika, bukan sekadar narasi), dan **Currency Check** (rekonstruksi/klaim mencerminkan revisi paleontologi pasca-2000, bukan citra dinosaurus usang gaya 1990an). Disimpan ke `portofolio/` disertai dokumentasi status epistemis eksplisit.

Status: Not Started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: Belum dimulai — Draft, menunggu approval user

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Paleontologi sebagai disiplin ilmiah
