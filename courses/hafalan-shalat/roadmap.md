# Course Roadmap

> **STATUS: DRAFT — menunggu approval user.** Jangan mulai Lesson 1 sebelum roadmap ini disetujui (`ai-los/COURSE_CREATION.md`).

## Course Information

* Course Name: Hafalan Shalat seperti Delisa — Lancar, Paham, dan Khusyuk
* Category: Islamic Worship Practice (Domain: islamic-worship — reuse `domains/islamic-worship/DOMAIN.md`, tidak bikin domain baru, sama seperti `when-they-pull-away` reuse `relationships`)
* Difficulty: Level 0 (bacaan shalat belum hafal penuh, atau hafal tapi bergantung teks/urutan sering tertukar, arti belum dipahami) → Level 2 (semua bacaan utama shalat lancar tanpa teks, urutan benar, tahu arti garis besar tiap bacaan, punya sistem murojaah harian, tahu cara menangani lupa di tengah shalat) — bukan level hafidz, tapi shalat yang tidak lagi bergantung contekan
* Estimated Duration: 15 modul (lesson ringan, 2-4/modul) + 30-Day Hafalan Challenge + Final Test + Final Reflection. Pace sengaja lambat dan bertahap — hafalan butuh pengulangan berjarak nyata, bukan sekadar dibaca.
* Prerequisites: Bisa membaca Arab dasar (atau transliterasi sebagai jembatan awal), sudah tahu gerakan shalat secara umum. Tidak perlu sudah hafal apa pun. Course ini fokus **menghafal + lancar + paham garis besar arti**; kedalaman makna dan khusyuk ada di `courses/khusyuk-ibadah/roadmap.md` (lihat Catatan Scope).

---

# Catatan Scope (dibuat 2026-09-25, dikonfirmasi lewat AskUserQuestion 2x)

* **Dibuat saat Course Aktif "Character Development" pas di garis 50% (3/6 modul)** — user eksplisit konfirmasi lanjut override guard `ai-los/COURSE_CREATION.md` lewat AskUserQuestion, pola sama seperti course lain yang terdaftar "Paused" di `ai-los/ACTIVE_DOMAIN.md` sejak 2026-08-16.
* **Reuse domain `islamic-worship`, bukan domain baru.** Domain itu awalnya mengasumsikan learner sudah hafal bacaan shalat dan fokus ke kualitas batin; course ini mengisi gap sebaliknya (hafalan + kelancaran). Scope domain diperluas ringan — lihat "Registration History" di `domains/islamic-worship/DOMAIN.md`.
* **Overlap dikonfirmasi lewat AskUserQuestion: standalone + cross-reference.** `courses/khusyuk-ibadah/roadmap.md` Module 6 sudah membedah MAKNA bacaan (iftitah, Al-Fatihah, rukuk, sujud, tasyahud/salam) dan Module 7 teknik khusyuk. Pembagian batas:
  * **Course ini (hafalan-shalat):** metode menghafal, kelancaran, urutan, murojaah, mengatasi lupa, arti garis besar per bacaan (cukup untuk tahu apa yang diucapkan — 1-2 kalimat per bagian, bukan bedah tafsir).
  * **`khusyuk-ibadah` Module 6-7:** kedalaman makna per bacaan, teknik menjaga fokus, kehadiran hati. Course ini merujuk ke sana di Module 12 dan 15, tidak menduplikasi.
  * Rekomendasi urutan: boleh dijalani BERSAMA atau SEBELUM `khusyuk-ibadah`; hafalan lancar membuat Module 6-7 di sana lebih mudah dipraktikkan.
* **Batas dengan domain `islamic-worship`:** tidak mengajarkan fikih teknis (syarat/rukun/pembatal shalat), tidak jadi debat mazhab, tidak mengajarkan nahwu-sharaf. Untuk bacaan yang punya beberapa varian sahih (mis. doa iftitah, tasyahud, shalawat), course ini **memakai satu versi utama yang biasa dipakai learner, dan menandai eksplisit bahwa ada varian lain** tanpa membuka perbandingan mazhab. Versi utama dikonfirmasi ke learner di Module 1 (kalibrasi: guru/kebiasaan/mazhab yang biasa diikuti). Hal khilafiyah seperti status basmalah sebagai ayat pertama Al-Fatihah disebut sebagai "ada pendapat berbeda", tidak dijadikan topik perdebatan.
* **House rule domain berlaku di setiap modul:** status hadits (shahih/hasan/dhaif) disebut eksplisit tiap kali bacaan bersumber hadits; dalil kuat, pendapat ulama, dan kisah lemah dipisah. **Takhrij tiap bacaan diverifikasi ke sumber saat lesson digenerate**, bukan diasumsikan dari draft ini. Contoh hasil riset awal: doa duduk di antara dua sujud diriwayatkan At-Tirmidzi (no. 284) dengan penshahihan Al-Albani, dan beberapa lafaznya juga diriwayatkan Ibnu Majah dan Ahmad dengan variasi lafaz; doa iftitah "Wajjahtu wajhiya" bersumber dari riwayat Ali bin Abi Thalib (Muslim), dengan versi berawalan "Inni" ada di Abu Dawud/Ibnu Majah/Ahmad.
* **Delisa (novel *Hafalan Shalat Delisa*, Tere Liye) dipakai sebagai inspirasi framing** — ketulusan seorang anak yang menghafal shalat dengan sungguh-sungguh — bukan sebagai sumber hukum atau dalil. Tidak ada klaim fakta dari novel/film yang dijadikan dasar materi.
* **Dasar metode menghafal:** retrieval practice / testing effect (Roediger & Karpicke, 2006 — mengingat aktif tanpa melihat teks bertahan lebih baik daripada membaca ulang) dan spacing effect (Cepeda et al., 2006 — pengulangan berjarak mengalahkan pengulangan menumpuk). Detail teori belajar dirujuk ke `courses/learning-science/roadmap.md` dan `courses/memory-mastery/roadmap.md` (keduanya masih Draft) tanpa diulang; course ini hanya memakai prinsipnya secara praktis.
* Sumber konten: draft 15 modul + 30-Day Challenge + Final Test + Final Reflection + Personal Shalat Code diberikan langsung oleh user, dipetakan ke format `templates/ROADMAP_TEMPLATE.md` tanpa mengubah substansi; lesson, mini project, dan cross-reference ditambahkan.

---

# Learning Objectives

* [ ] Menyebutkan urutan 13 tahap bacaan shalat dari niat sampai salam tanpa tertukar (peta shalat)
* [ ] Menghafal dan melafalkan dengan benar: takbiratul ihram, doa iftitah, Al-Fatihah, satu surat pendek (lalu bertambah), bacaan rukuk, i'tidal, sujud, duduk di antara dua sujud, tasyahud, shalawat, dan salam
* [ ] Menjelaskan arti garis besar tiap bacaan dan kapan/di gerakan mana ia dibaca
* [ ] Menghafal dengan metode bertahap (pecah → hafal → sambung → tutup teks → recall) dan menerapkannya pada bacaan baru
* [ ] Menjalankan sistem murojaah harian (pagi, sebelum tidur, sebelum shalat, saat shalat) secara konsisten
* [ ] Menangani lupa di tengah shalat dengan tenang (langkah 1-6) dan memperbaiki bagian yang terlupa setelahnya
* [ ] Mengurangi ketergantungan teks lewat 4 level latihan: melihat teks, melihat hanya saat lupa, tanpa teks, praktik dalam shalat
* [ ] Membedakan "hafal", "paham", dan "otomatis khusyuk" — dan tahu hafalan adalah fondasi, bukan tujuan akhir
* [ ] Lulus Final Test "Shalat Tanpa Contekan"
* [ ] Menulis Final Reflection dan Personal Shalat Code sebagai pegangan pribadi

---

# Course Modules

## Module 1 — Mengenal Peta Bacaan Shalat

Description: Titik masuk — lihat shalat sebagai satu alur utuh, bukan kumpulan teks terpisah. Urutan 13 tahap: niat, takbiratul ihram, doa iftitah, Al-Fatihah, surat pendek, rukuk, i'tidal, sujud, duduk di antara dua sujud, sujud kedua, tasyahud, shalawat, salam.

Lessons:

* [ ] 1.1 Peta shalat 13 tahap — tiap tahap: bacaan apa, gerakan apa, kira-kira berapa lama
* [ ] 1.2 Kalibrasi pribadi: audit jujur — bacaan mana yang sudah hafal, setengah hafal, belum; versi mana (iftitah, tasyahud, shalawat) yang biasa dipakai/diajarkan guru
* [ ] 1.3 Cara belajar course ini: retrieval practice dan spacing secara praktis (kenapa membaca ulang saja kurang efektif dibanding tutup teks lalu ingat)

Mini Project: Peta Shalat Pribadi — satu halaman (gambar/tabel tulis tangan) urutan 13 tahap + status hafalan tiap bagian (hijau/kuning/merah).

Status: Not started

---

## Module 2 — Takbir & Doa Iftitah

Description: Hafalkan takbiratul ihram dan satu versi doa iftitah yang dipakai. Pelajari kapan dibaca, arti tiap bagian, dan makna menghadap Allah — cukup untuk tahu apa yang diucapkan (kedalaman makna → `khusyuk-ibadah` Module 6.1).

Lessons:

* [ ] 2.1 Takbiratul ihram — lafaz, posisi tangan dan kapan diucapkan, artinya
* [ ] 2.2 Doa iftitah versi utama — pecah bagian, hafalkan bertahap, artinya; sebut ada varian sahih lain tanpa membahas perbandingan mazhab
* [ ] 2.3 Praktik: baca perlahan tanpa teks, dari takbir sampai sebelum Al-Fatihah

Mini Project: Rekam suara sendiri (atau tulis dari ingatan) takbir + iftitah tanpa teks; bandingkan dengan teks, tandai kata yang tertukar/lupa.

Status: Not started

---

## Module 3 — Al-Fatihah

Description: Pusat latihan — dibaca di setiap rakaat. Ayat demi ayat: Arab → transliterasi → arti → makna → hafalan → praktik. Ketepatan dan kelancaran dulu, bukan kecepatan.

Lessons:

* [ ] 3.1 Ayat 1-3 (Basmalah dijelaskan sebagai bagian yang dibaca; catatan singkat "ada pendapat berbeda soal status ayat", lalu Al-Hamdu... Ar-Rahmanir-Rahim, Maliki yaumid-din)
* [ ] 3.2 Ayat 4-5 (Iyyaka na'budu wa iyyaka nasta'in) — inti perjanjian hamba, arti dan pengulangan kata
* [ ] 3.3 Ayat 6-7 — permohonan hidayah dan jalan yang lurus
* [ ] 3.4 Menyambung seluruh Al-Fatihah tanpa teks + cek tajwid dasar bagian yang sering keliru (dhad, dzal, panjang-pendek)

Mini Project: Recall Test Al-Fatihah — tutup teks, ucapkan, catat titik macet; ulangi 3 hari berbeda (spacing) dan bandingkan hasilnya.

Status: Not started

---

## Module 4 — Surat Pendek

Description: Mulai dari surat yang paling familiar (Al-Ikhlas, Al-Falaq, An-Nas, Al-Kautsar, Al-'Asr, Al-Fil, Quraisy). Metode: 1 surat → pecah ayat → hafalkan → sambungkan → praktik dalam shalat.

Lessons:

* [ ] 4.1 Pilih 1 surat awal (yang paling familiar) dan lengkapi dengan metode pecah-hafal-sambung
* [ ] 4.2 Menambah surat kedua dan ketiga — gunakan sistem yang sama, jangan terburu-buru
* [ ] 4.3 Arti tiap surat dalam satu kalimat — cukup untuk tahu apa yang dibaca

Mini Project: Set 3 surat pendek yang lancar tanpa teks + satu shalat fardhu di mana surat pendek dibaca tanpa contekan.

Status: Not started

---

## Module 5 — Bacaan Rukuk & I'tidal

Description: Hafalkan bacaan rukuk, bangkit dari rukuk, dan i'tidal beserta artinya. Tujuan: rukuk bukan sekadar "waktunya baca teks", tapi paham sedang mengagungkan Allah.

Lessons:

* [ ] 5.1 Bacaan rukuk — lafaz, arti, dan status sumber hadits
* [ ] 5.2 Bangkit dari rukuk dan bacaan i'tidal — bedakan bacaan imam/makmum/munfarid tanpa masuk perdebatan
* [ ] 5.3 Sambung: dari surat pendek → rukuk → i'tidal tanpa teks

Mini Project: Sambung 3 tahap (surat → rukuk → i'tidal) tanpa teks, rekam, lalu tulis 1 kalimat: "saya sedang mengagungkan Allah dengan mengucapkan...".

Status: Not started

---

## Module 6 — Bacaan Sujud

Description: Hafalkan bacaan sujud; pelajari makna sujud, kerendahan hati, dan kedekatan hamba kepada Allah (kedalaman → `khusyuk-ibadah` Module 6.2-6.3).

Lessons:

* [ ] 6.1 Bacaan sujud — lafaz, arti, sumber
* [ ] 6.2 Kenapa sujud bukan sekadar gerakan fisik — makna singkat + dalil kuat tentang kedekatan saat sujud (hadits Muslim, diverifikasi saat lesson)
* [ ] 6.3 Latihan jeda — setelah membaca, berhenti beberapa detik dan sadari artinya

Mini Project: Murojaah bacaan sujud 7 hari berturut-turut dengan jeda 5 detik setelah tiap bacaan; catat apa yang berubah dari cara membacanya.

Status: Not started

---

## Module 7 — Duduk di Antara Dua Sujud

Description: Hafalkan doa dengan memahami permintaan di dalamnya: ampunan, rahmat, perbaikan keadaan, derajat, rezeki, petunjuk, kesehatan/afiat, dan maaf. Target: "Ketika membaca doa ini, saya tahu apa yang sedang saya minta."

Lessons:

* [ ] 7.1 Doa duduk di antara dua sujud — pecah per permintaan (Rabbighfirli, warhamni, wajburni, warfa'ni, warzuqni, wahdini, wa'afini, wa'fu 'anni); sebut ada beberapa lafaz sahih dengan jumlah permintaan berbeda
* [ ] 7.2 Arti tiap permintaan dan kaitannya dengan kebutuhan hidup seorang hamba
* [ ] 7.3 Bacaan pendek pilihan: Rabbighfirli (versi ringkas) sebagai jaring pengaman saat lupa

Mini Project: Tulis ulang doa ini dengan kata-kata sendiri (parafrase arti, bukan terjemah kaku) lalu bandingkan dengan terjemah baku.

Status: Not started

---

## Module 8 — Tasyahud

Description: Hafalkan bertahap: bagian 1 → sambung → bagian 2 → sambung → lengkap. Jangan sekaligus. Pelajari makna penghormatan, syahadat, kesaksian, dan hubungan hamba dengan Allah.

Lessons:

* [ ] 8.1 Bagian 1: At-tahiyyatu lillahi... (penghormatan) — pecah dan hafal
* [ ] 8.2 Bagian 2: salam kepada Nabi dan hamba-hamba saleh, dilanjut syahadatain — pecah dan hafal
* [ ] 8.3 Menyambung seluruh tasyahud + arti garis besar; sebut ada varian riwayat tasyahud sahih tanpa membuka perbandingan mazhab
* [ ] 8.4 Tasyahud awal vs akhir — apa yang dibaca di mana (tanpa detail fikih teknis)

Mini Project: Recall bertahap tasyahud: hari 1 bagian 1, hari 2 bagian 2, hari 3 sambung; log titik lupa.

Status: Not started

---

## Module 9 — Shalawat

Description: Hafalkan shalawat yang dibaca dalam tasyahud akhir; pelajari makna shalawat, kedudukan Nabi Muhammad ﷺ, dan kenapa bagian ini hadir dalam shalat.

Lessons:

* [ ] 9.1 Shalawat Ibrahimiyah — pecah, hafalkan, sambung
* [ ] 9.2 Arti dan hubungan dengan Nabi Ibrahim — kenapa disebut; status hadits sumbernya
* [ ] 9.3 Kenapa shalawat ada di dalam shalat (dalil kuat, tidak masuk debat hukum wajib/sunnah)

Mini Project: Sambung tasyahud + shalawat tanpa teks dalam satu tarikan; catat lancar/tidak dan titik sambungan yang macet.

Status: Not started

---

## Module 10 — Salam

Description: Hafalkan bacaan salam; pelajari makna mengakhiri shalat dan menoleh ke kanan dan kiri.

Lessons:

* [ ] 10.1 Bacaan salam — lafaz, arti, gerakan menoleh
* [ ] 10.2 Makna menutup shalat: kembali ke dunia dengan salam dan rahmat

Mini Project: Rekam murojaah akhir shalat (tasyahud → shalawat → salam) tanpa teks; catat 1 kalimat apa arti salam bagi saya.

Status: Not started

---

## Module 11 — Menggabungkan Semua Hafalan

Description: Berhenti menghafal terpisah. Latih sambung: Takbir → Iftitah → Al-Fatihah → Surat → Rukuk → I'tidal → Sujud → Duduk → Sujud → ... → Salam. Empat level: (1) lihat teks, (2) lihat teks hanya saat lupa, (3) tanpa teks, (4) praktik dalam shalat.

Lessons:

* [ ] 11.1 Level 1-2: shalat 2 rakaat dengan teks terbuka, lalu teks hanya saat lupa
* [ ] 11.2 Level 3: simulasi runtut tanpa teks di luar waktu shalat (duduk/berdiri, ucapkan urutan) untuk uji urutan sebelum dipraktikkan di shalat sungguhan
* [ ] 11.3 Level 4: praktik dalam shalat sungguhan, catat pos macet
* [ ] 11.4 Membangun jembatan antar bacaan (kalimat pertama tiap bagian sebagai "kait" ingatan)

Mini Project: Full Run 2 rakaat dan 4 rakaat dari takbir sampai salam tanpa teks; log pos macet dan perbaikannya. **Intermediate Project.**

Status: Not started

---

## Module 12 — Hafalan dengan Makna

Description: Tiga lapisan: hafal → paham → rasakan kesadarannya. Bukan mengejar perasaan tertentu, tapi membangun kesadaran dalam ibadah. Kedalaman makna dirujuk ke `courses/khusyuk-ibadah/roadmap.md` Module 6, tidak diulang di sini.

Lessons:

* [ ] 12.1 Tiga lapisan: "saya hafal bacaan sujud" vs "saya tahu apa yang saya ucapkan saat sujud"
* [ ] 12.2 Menghubungkan bacaan dengan gerakan (gerakan sebagai jangkar ingatan sekaligus jangkar makna)
* [ ] 12.3 Peta arti satu halaman: 13 tahap dengan 1 kalimat arti tiap bacaan

Mini Project: Peta Arti — satu halaman ringkas berisi arti tiap bacaan dengan kata-kata sendiri (bukan copy terjemah).

Status: Not started

---

## Module 13 — Murojaah

Description: Sistem murojaah: pagi 5-10 menit, sebelum tidur 5-10 menit, sebelum shalat review singkat, saat shalat praktik. Prinsip: sedikit tapi setiap hari > banyak tapi sesekali.

Lessons:

* [ ] 13.1 Kenapa murojaah berjarak lebih kuat daripada sekali menumpuk (spacing, tanpa jargon berlebihan)
* [ ] 13.2 Mendesain jadwal murojaah pribadi 4 slot yang realistis untuk hidup sendiri
* [ ] 13.3 Melacak murojaah tanpa jadi beban (habit tracker sederhana, bukan target berat)

Mini Project: Jalankan sistem murojaah 7 hari; log slot yang jalan, slot yang bolong, dan penyesuaian. Cross-ref [[learning-science]] dan [[memory-mastery]] untuk teori lebih dalam (opsional, tidak wajib).

Status: Not started

---

## Module 14 — Mengatasi Lupa

Description: Kalau tiba-tiba lupa di tengah shalat: tenang. Berhenti sebentar → ingat kata pertama → sambung → setelah shalat cari bagian yang lupa → murojaah beberapa kali → praktikkan lagi. Lupa bukan tanda gagal.

Lessons:

* [ ] 14.1 Langkah 1-6 menangani lupa — dan praktik sebelum benar-benar lupa
* [ ] 14.2 Fallback ringan saat benar-benar buntu: bacaan pendek yang sudah lancar; detail hukum sujud sahwi hanya disebut sekilas, rujuk ke guru/fikih untuk rincian
* [ ] 14.3 Analisis penyebab lupa: kurang murojaah, tergesa, pikiran melayang, bagian sambungan — dan perbaikan spesifik tiap penyebab

Mini Project: Skenario Lupa — simulasi 3 skenario lupa (Al-Fatihah ayat 5, tasyahud bagian 2, shalawat) lalu praktikkan langkah 1-6; log apa yang berhasil.

Status: Not started

---

## Module 15 — Dari Hafalan Menuju Khusyuk

Description: Setelah hafalan mulai otomatis, arahkan perhatian ke makna, posisi tubuh, bacaan, rasa butuh kepada Allah, ketenangan, dan kehadiran hati. Hafal ≠ otomatis khusyuk; hafalan adalah salah satu fondasi. Ini jembatan ke `courses/khusyuk-ibadah/roadmap.md` Module 7 (teknik khusyuk), tidak diduplikasi.

Lessons:

* [ ] 15.1 Hafal ≠ otomatis khusyuk — kenapa hafalan lancar bisa justru memicu shalat "autopilot"
* [ ] 15.2 Enam pintu perhatian: makna, posisi tubuh, bacaan, rasa butuh, ketenangan, kehadiran hati
* [ ] 15.3 Langkah lanjutan: lanjut ke `khusyuk-ibadah` (rekomendasi, bukan kewajiban)

Mini Project: Satu shalat fardhu dijalani dengan fokus pada SATU pintu perhatian (pilih dari 15.2); refleksi jujur, bukan dilaporkan sempurna.

Status: Not started

---

# 30-Day Hafalan Shalat Challenge

Description: Praktik 30 hari, jembatan antara modul dan Final Test. Bukan modul teori tambahan.

Jadwal:

* [ ] Hari 1-5: Takbir + Iftitah
* [ ] Hari 6-10: Al-Fatihah
* [ ] Hari 11-15: Surat pendek
* [ ] Hari 16-20: Rukuk + I'tidal + Sujud + Duduk
* [ ] Hari 21-25: Tasyahud + Shalawat + Salam
* [ ] Hari 26-30: Full prayer practice + murojaah

Ritme harian: Hafalkan → Ucapkan → Tutup teks → Recall → Praktikkan.

Aturan realistis: hari yang terlewat tidak diulang dari nol; lanjutkan dari titik terakhir dan catat penyebab bolong (sesuai house rule domain: bertahap, tanpa rasa bersalah berlebihan).

Status: Not started

---

# Final Test — Shalat Tanpa Contekan

Kriteria:

* [ ] Seluruh bacaan dapat diingat
* [ ] Urutan benar dari takbir sampai salam
* [ ] Tidak bergantung pada teks
* [ ] Dapat membaca dengan tenang, tidak terburu-buru
* [ ] Paham garis besar arti tiap bacaan
* [ ] Mampu mempraktikkannya dalam shalat

Cara ujian: self-assessment dengan rekaman/praktik shalat 4 rakaat tanpa teks + tanya-jawab lisan arti garis besar. Mentor menilai berdasarkan catatan learner, tidak bisa menilai lafaz secara langsung — **catatan jujur: pelafalan Arab tidak bisa diverifikasi AI dari teks saja; disarankan setor bacaan ke guru/ustadz yang dipercaya untuk cek tajwid dan makhraj.**

Status: Not started

---

# Final Reflection

Jawab tertulis:

1. Bacaan mana yang paling sulit saya hafalkan?
2. Mengapa?
3. Bacaan mana yang paling saya pahami maknanya?
4. Apakah selama ini saya membaca tanpa memahami?
5. Apa yang berubah setelah saya memahami maknanya?
6. Bagaimana saya ingin menjaga hafalan ini?

Status: Not started

---

# Final Project — Personal Shalat Code

Description: Pernyataan pribadi + sistem pemeliharaan hafalan, disimpan ke `portofolio/`. Mengintegrasikan Peta Shalat (Module 1), Peta Arti (Module 12), sistem murojaah (Module 13), catatan lupa (Module 14), dan log 30-Day Challenge.

Isi awal (dari user, bisa diedit): "Saya tidak ingin sekadar hafal bacaan shalat. Saya ingin tahu apa yang saya ucapkan, kepada siapa saya mengucapkannya, dan belajar menghadirkan hati ketika mengucapkannya. Hafalan adalah pintu. Pemahaman membawa kesadaran. Dan kesadaran membantu kita mendekat kepada Allah."

Acceptance Criteria: Personal Shalat Code berisi (a) pernyataan pribadi dengan kata sendiri, (b) peta 13 tahap terbaru, (c) jadwal murojaah pemeliharaan pasca-course, (d) rencana menangani lupa, (e) satu langkah berikutnya (mis. lanjut `khusyuk-ibadah`) — dinilai memakai Universal Review Rubric (`ai-los/CORE_LOS.md`) plus Review Style domain `islamic-worship` (Dalil Separation Rigor, Realism bukan Performa, Bertahap bukan Berlebihan, Fokus Hati bukan Sekadar Checklist).

Status: Not started

---

# Resources

Lihat `domains/islamic-worship/DOMAIN.md` bagian "Trusted Sources / Research Priority". Khusus course ini:

* Al-Qur'an dengan terjemah Kemenag RI (Al-Fatihah dan surat pendek).
* Kitab hadits standar (Shahih Muslim, Sunan At-Tirmidzi, Abu Dawud, Ibnu Majah, Ahmad) dengan status takhrij dari Al-Albani atau Syu'aib Al-Arnauth; diverifikasi per bacaan saat lesson digenerate.
* Riset awal yang sudah dicek: [Rumaysho — variasi bacaan duduk antara dua sujud](https://rumaysho.com/21692-manhajus-salikin-variasi-bacaan-ketika-duduk-antara-dua-sujud.html), [Persis — macam-macam doa iftitah](https://persis.or.id/kajian/read/macam-macam-doa-iftitah-keutamaan-dan-panduan-dalam-shalat), [Radio Rodja — doa iftitah Wajjahtu wajhiya](https://www.radiorodja.com/54880-doa-iftitah-wajjahtu-wajhiya/). Ini rujukan awal untuk arah, bukan pengganti verifikasi kitab hadits saat lesson.
* Metode menghafal: Roediger & Karpicke (2006, testing effect), Cepeda et al. (2006, spacing effect; [ringkasan meta-analisis](http://www.lscp.net/persons/ramus/docs/EPR20.pdf)).
* Guru/ustadz yang dipercaya learner untuk cek tajwid dan makhraj.

# Projects

15 Mini Project (satu per modul, Module 11 juga Intermediate Project) + 30-Day Challenge (log harian) + Final Test + Final Reflection + 1 Final Project (Personal Shalat Code).

# Interview

Ditambahkan saat mendekati Final Project: evaluasi skenario — diberi situasi ("lupa tasyahud bagian 2", "urutan tertukar setelah sujud", "hafal tapi terasa autopilot"), learner menjelaskan langkah dari Module 14-15.

# Final Exam

Final Test "Shalat Tanpa Contekan" + Final Project (Personal Shalat Code), dinilai memakai Universal Review Rubric plus Review Style domain `islamic-worship`.

---

# Overall Progress

Module Completion: 0/15

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: —

Next Lesson: 1.1 Peta shalat 13 tahap
