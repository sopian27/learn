Course/lesson persistence — jangan simpan materi course di Claude session

Vault: `C:\Users\eluon\OneDrive\Documents\Obsidian Vault`

Struktur folder: `Courses/<Nama Course Title Case>/`

File index: `Courses/<Nama Course>/COURSE.md`

* Isi = salinan `courses/<slug>/roadmap.md`
* Tiap module heading disisipkan link `[[Module N - <Judul Module>]]`
* roadmap.md tetap sumber kebenaran status/progress — sinkron ulang COURSE.md dari situ tiap update

File module: `Courses/<Nama Course>/Module N - <Judul Module>.md`

* Nomor N ikut roadmap.md, tanpa zero-padding
* 1 file = 1 module utuh: semua lesson di dalamnya (Objective/Prerequisite/Theory/Diagram/Example/Quiz/Exercise sesuai `LESSON_RULES.md`) + Mini Project module tsb

Sebelum generate module baru

* Cek file module sudah ada di vault
* Kalau sudah ada: skip, jangan overwrite, jangan generate ulang
* Kalau belum ada: generate lalu tulis langsung ke vault (bukan cuma ditampilkan di chat)

Enterprise Project / Capstone (course selesai penuh)

* File: `Courses/<Nama Course>/Enterprise Project.md` (atau nama capstone-nya sesuai roadmap), isi = materi capstone lengkap (sama pola dengan file module)
* **Final Course Wisdom wajib ikut ditulis ke file ini**, bukan cuma ditampilkan di chat — section `## Final Course Wisdom` di akhir file, isi teks reflection lengkap (bukan placeholder/link balik ke chat)
