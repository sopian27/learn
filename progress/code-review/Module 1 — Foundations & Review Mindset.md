# Foundations & Review Mindset

   # Tujuan sebenarnya dari code review (defect prevention, knowledge sharing, konsistensi, mentoring)

   defect prevention : "Kapan ini salah? Dipanggil dua kali? Input nol atau negatif? 
   knowledge sharing : "Selain author, siapa yang sekarang ngerti ini?"
   konsistensi : "Ini ngikutin pola yang udah ada?"
   mentoring : "Author pulang bawa pemahaman apa?"

   real world:
   Riset di Microsoft (Bacchelli & Bird, 2013) nemu: orang bilang motivasi utama review itu cari bug, tapi komentar soal defect ternyata porsinya kecil. Hasil yang banyak muncul justru perbaikan kode, transfer pengetahuan, dan kesadaran tim. Panduan review publik Google juga bilang: approve kalau perubahan itu jelas bikin kesehatan kode lebih baik, walau belum sempurna.

   jangan di lakukan:
   1. Rubber-stamping: LGTM ("Looks Good to Me") tanpa baca test. Rasanya bantu, padahal cuma nambah stempel.
   2. Bikeshedding: 15 komentar soal nama, nol soal "kalau gagal di tengah gimana".
   3. Nganggep approve = tanggung jawab pindah ke reviewer. Author tetep pemilik kodenya.
   4. typo, bump versi yang udah dijaga CI, spike yang bakal dibuang. Hotfix darurat boleh review menyusul, asal beneran disusul.

   Tes Feynman: orang lain ngecek kerjaan lu sebelum dipakai, supaya salahnya ketahuan dan ilmunya nyebar.

   Aturan Emas: Review yang bagus ninggalin kode lebih aman dan orangnya lebih paham.

   Terapin: sebelum klik approve, sebutin dalam satu kalimat apa risiko terbesar PR itu. Gak bisa? Berarti belum selesai baca.

   # Anatomy of a bad review vs a great review

   Bad review: kabur, nyerang orang atau selera, tanpa prioritas.
   
   Great review: label, lokasi, dampak, usul atau pertanyaan.

   komentar bagus:
   [label]  +  lokasi/observasi  +  dampak (kapan salahnya)  +  usul atau pertanyaan

   jangan di lakukan:
   1. Nulis ulang solusi sesuai selera sendiri padahal punya author udah bener.
   2. Nyimpen keberatan desain besar sampai ronde ketiga.
   3. Cuma nulis yang jelek. Sebut juga yang bagus, asal spesifik dan beneran.
   4. urusan format (serahin ke linter), atau masalah sebesar desain ulang yang mending dibahas langsung.

   Tes Feynman: komentar yang bagus bikin orang tahu apa yang salah, kenapa, dan harus ngapain.

   Aturan Emas: Kalau komentar lu gak bisa ditindaklanjuti, itu cuma keluhan.

   Terapin: sebelum kirim komentar, tanya "setelah baca ini, dia tahu langkah berikutnya gak?"

   # Psychological safety & blameless culture dalam review

   Psychological safety artinya orang berani nanya, ngaku gak tahu, atau bilang "kayaknya ini salah" tanpa takut dipermalukan.
   
   Blameless artinya waktu ada yang salah, yang dibongkar kondisi yang bikin kesalahan itu bisa terjadi dan lolos.

   jangan di lakukan:
   1. Nulis "blameless" di wiki, tapi rapatnya tetep nyari nama.
   2. Action item "lebih hati-hati". Itu harapan, bukan perbaikan. Cari guard: tipe, test, validasi, CI.
   3. Kritik keras di thread publik padahal bisa dibahas langsung.

   Tes Feynman: tim yang aman buat nanya nemu masalah lebih cepet, lalu benerin sebabnya, bukan nyalahin orangnya.

   Aturan Emas: Tanya "apa yang bikin ini bisa terjadi?" sebelum "siapa yang ngelakuin?"

   Terapin: minggu ini, waktu lu gak paham sesuatu di PR, tulis pertanyaannya apa adanya.

