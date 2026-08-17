# Mini Project Module 2

Minta feedback jujur dari 2 orang nyata (kerja/personal) tentang satu area spesifik. Dokumentasikan feedback yang diterima DAN reaksi/respons diri sendiri (defensif atau tidak, apa yang ditindaklanjuti).

**Catatan mentor (dari roadmap)**: pakai momen BARU, jangan recycle "bos tanya ulang issue production" (sudah dipakai 4x: 1.2, 1.4, Mini Project 1, 2.1). Idealnya minimal satu momen stakes lebih tinggi (menyentuh identitas/kemampuan, bukan cuma prosedural).

## Setup (sudah dikerjakan di 2.3 — tinggal eksekusi nyata)

Area: kemampuan komunikasi proaktif saat menangani pekerjaan stakes/kompleksitas tinggi (update progres sebelum ditanya).

Ini sudah menyentuh identitas ("ingin dianggap reliable sebagai software engineer", diakui sendiri di 2.4 poin 3) — bukan cuma prosedural — jadi sudah pass syarat stakes mentor di atas. Anchor insiden yang dipakai di pertanyaan (restart server slm04) beda dari "bos tanya ulang issue production" yang sudah dipakai 4x — aman, bukan recycle.

Orang 1: Bos/atasan. Orang 2: rekan kerja.

Pertanyaan sudah dirancang di `2.3.md` (undangan eksplisit + spesifisitas + forced-single-pick, follow-up "ada lagi?").

## Yang perlu dikerjakan sekarang

Ajukan pertanyaan itu ke 2 orang tersebut SECARA NYATA (bukan simulasi/hipotetis — Applied Practice Definition domain ini eksplisit menolak itu). Lalu isi bagian bawah ini apa adanya, urutan jangan dibalik:

### Orang 1 — [Jaelani/Manager]

1. Feedback verbatim yang diterima (termasuk jawaban follow-up "ada lagi?" kalau ada):
    "kamu kalau sudah selesai task BE nya, test nya jangan cuman positive case nya saja, coba banyakin negative case nya, coba juga di call via fe jangan cuman mengandalkan postman"

2. Reaksi internal pertama, sebelum disaring (jalur dissonance mana yang sempat aktif — 2.1):
    "Menyakitkan. Rasanya seperti saya dianggap belum cukup bagus dalam melakukan pekerjaan saya."

3. Tiga uji discernment (2.4) — korroborasi, spesifisitas/falsifiability, area kendali:
    - Korroborasi: Belum ada korroborasi langsung dari orang kedua untuk bagian testing. Namun feedback berasal dari manager yang mengamati hasil pekerjaan, sehingga layak diuji dengan melihat apakah memang selama ini testing saya lebih dominan positive case/Postman.
    - Spesifisitas/falsifiability: Kuat. Feedback menyebut perilaku yang sangat konkret: menambah negative cases dan melakukan testing melalui FE, bukan hanya Postman.
    - Area kendali: Kuat. Menambah negative case dan melakukan end-to-end testing melalui FE adalah perilaku yang berada dalam kendali saya.

4. Kesimpulan: ditindaklanjuti / perlu klarifikasi / dilepas — dan apa aksinya kalau ditindaklanjuti:
    ditindaklanjuti : Setiap menyelesaikan task BE, saya akan menambahkan negative/edge cases dan melakukan minimal satu validasi melalui FE/end-to-end flow, bukan hanya mengandalkan Postman.

### Orang 2 — [Syahrul/Business Analyst]

1. Feedback verbatim yang diterima (termasuk jawaban follow-up "ada lagi?" kalau ada):
    "kalau ada issue di client jangan nunggu di followup dulu, langsung tanyain aja di group client nya, jangan nanya lagi di group internal, biar cepet, wkkwkw"

2. Reaksi internal pertama, sebelum disaring (jalur dissonance mana yang sempat aktif — 2.1):
    "Menyenangkan. Saya merasa feedback ini masuk akal dan saya tidak merasa perlu membela diri."

3. Tiga uji discernment (2.4) — korroborasi, spesifisitas/falsifiability, area kendali:
    - Korroborasi: Belum terkorroborasi oleh orang lain. Namun orang kedua memberikan observasi spesifik mengenai pola komunikasi dalam konteks client.
    - Spesifisitas/falsifiability: Kuat. Perilakunya jelas: apakah saya menunggu follow-up atau secara proaktif bertanya di group client.
    - Area kendali: Kuat. Saya bisa mengubah perilaku tersebut dengan langsung mengomunikasikan issue kepada client ketika memang diperlukan.

4. Kesimpulan: ditindaklanjuti / perlu klarifikasi / dilepas — dan apa aksinya kalau ditindaklanjuti:
    ditindaklanjuti : Ketika menemukan issue yang membutuhkan informasi/konfirmasi dari client, saya tidak akan menunggu client atau orang internal melakukan follow-up terlebih dahulu. Jika konteks dan jalur komunikasinya memungkinkan, saya akan langsung mengangkat pertanyaan di group client.

### Sintesis

Dua feedback ini sepakat atau beda? Kalau beda, gimana cara nilai — bukan pilih yang paling nyaman, tapi pakai tiga uji dari masing-masing.
    Dua feedback ini sebagian berbeda, tetapi keduanya mengarah pada pola yang sama: meningkatkan proaktivitas sebelum pekerjaan dianggap selesai. Feedback Pak Jaelani berfokus pada proaktivitas dalam validasi teknis: jangan berhenti pada positive case/Postman, tetapi secara aktif mencari negative case dan melakukan validasi melalui FE. Feedback Syahrul berfokus pada proaktivitas dalam komunikasi: ketika ada issue client, jangan menunggu follow-up, tetapi langsung menghubungi pihak yang relevan. Jadi saya tidak akan memilih feedback Syahrul hanya karena terasa "menyenangkan", dan tidak akan menolak feedback Pak Jaelani hanya karena "menyakitkan".

    Dengan tiga uji discernment:

    Feedback | Korroborasi | Spesifik/Falsifiable | Area kendali | Kesimpulan
    Negative case + FE testing | Belum terkorroborasi oleh sumber kedua. Feedback berasal dari manager yang mengamati hasil pekerjaan; perlu dicek melalui observasi task berikutnya dan/atau feedback dari engineer lain. | Ya — perilakunya konkret dan dapat diverifikasi dari cara testing dilakukan. | Ya — saya dapat menambah negative/edge cases dan melakukan validasi melalui FE. | Ditindaklanjuti, sambil mencari korroborasi tambahan.
    Proaktif ke client | Belum terkorroborasi oleh sumber kedua. Saat ini baru ada observasi dari Syahrul dalam konteks komunikasi dengan client. Perlu diamati apakah pola yang sama muncul pada issue client berikutnya atau dikonfirmasi oleh pihak lain. | Ya — jelas apakah saya menunggu follow-up atau langsung mengangkat issue di group client. | Ya — saya dapat mengubah perilaku komunikasi tersebut. | Ditindaklanjuti, sambil mencari korroborasi tambahan.
    
    Yang paling penting: kedua feedback utama lolos dua uji yang sangat kuat—spesifik dan berada dalam area kendali. Korroborasi masih menjadi bagian yang perlu dicari dari observasi berikutnya.

Pola apa yang kelihatan dari kedua reaksi digabung (defensif konsisten, sociometer nyala di titik yang sama, dll)?
    Feedback yang menyentuh standar kompetensi terasa menyakitkan, sedangkan feedback tentang perilaku komunikasi terasa menyenangkan. Ini bisa menjadi sinyal bahwa sociometer/ego threat lebih sensitif ketika feedback menyentuh identitas sebagai software engineer yang kompeten/reliable.

Satu aksi konkret yang benar-benar mau dijalankan minggu ini, bukan cuma "diterima secara verbal".
    Untuk setiap task BE yang saya kerjakan minggu ini, sebelum menyatakan task selesai saya akan melakukan checklist: minimal 1 negative/edge case dan 1 validasi melalui FE/end-to-end flow jika flow tersebut tersedia.