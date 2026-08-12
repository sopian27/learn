# Memory Mastery — Domain Pack (Ingatan Jangka Panjang: Encoding, Retensi, Retrieval)

## Domain Name

Memory Mastery (Ingatan Jangka Panjang: Encoding, Retensi, dan Retrieval)

## Scope

Teknik terapan untuk membuat materi apa pun (termasuk course-course lain di repo ini) nempel jangka panjang, bukan sekadar tersimpan sesaat lalu lupa. Cakupan: active recall/retrieval practice, spaced repetition (teori algoritma spacing + praktik nyata dengan Anki), Feynman Technique, elaboration & self-explanation, deliberate practice & interleaving, tidur & konsolidasi memori, sampai merancang satu sistem retensi personal yang mengintegrasikan semuanya.

Secara eksplisit TIDAK mencakup:

- **Motivasi, flow, habit design, dan kenapa belajar terasa menyenangkan/berat** — domain `learning-science` (lihat `domains/learning-science/DOMAIN.md`). Domain itu sudah menyentuh desirable difficulty & retrieval practice di Module 7-nya, tapi sengaja ringan (4 lesson, dibingkai "tetap terasa flow"). Domain ini adalah pendalaman utuhnya — evidence hierarchy lengkap, algoritma spacing, praktik Anki, Feynman, elaboration, deliberate practice, tidur — sementara `learning-science` cukup merujuk balik ke sini, tidak mengulang.
- **Memori sebagai ilmu akademik/deskriptif** (model Atkinson-Shiffrin, sensory/short-term/long-term memory, false memory & misinformation effect Loftus, implikasi kesaksian saksi mata) — domain `psychology` Module 7 (lihat `domains/psychology/DOMAIN.md`). Domain ini murni terapan/hands-on ke sesi belajar nyata, bukan literasi ilmiah tentang model memori.
- **Habit formation umum** (Four Laws of Behavior Change, environment design lintas konteks hidup) — domain `character-development` Module 7 (Atomic Habits). Domain ini hanya memakai prinsip habit sekadarnya untuk menjaga jadwal spaced repetition tetap jalan, tidak mengajarkan teorinya dari nol.
- **Self-compassion saat gagal/menunda sesi belajar** — domain `self-love`, dirujuk sesaat saja bila relevan, tidak diulang penuh.
- **Teknik belajar spesifik-materi** (mis. cara belajar Bahasa Inggris, cara belajar coding) — course masing-masing domain tetap mengajarkan teknik spesifik materinya; domain ini mengajarkan lapisan retensi memori yang berlaku ke MATERI APA PUN, dan Applied Practice-nya sengaja diterapkan ke course aktif user yang sudah berjalan sebagai laboratorium nyata.

## Trusted Sources / Research Priority

1. **John Dunlosky, Katherine Rawson, Elizabeth Bjork Marsh, Mitchell Nathan, Daniel Willingham — "Improving Students' Learning With Effective Learning Techniques" (2013), meta-analisis peer-reviewed (Psychological Science in the Public Interest)** — evidence hierarchy inti course ini: mengurutkan 10 teknik belajar populer berdasarkan utility (practice testing & distributed practice = utility tinggi; rereading, highlighting, summarization = utility rendah). Setiap teknik di course ini wajib dicek posisinya di sini sebelum diajarkan sebagai "efektif". Prioritas tertinggi.
2. **Robert Bjork (UCLA) — riset Desirable Difficulties, peer-reviewed** — sudah jadi Trusted Source `domains/learning-science/DOMAIN.md` untuk versi ringannya; di sini didalami penuh sebagai dasar kenapa retrieval practice & spacing terasa lebih susah tapi jauh lebih efektif daripada re-reading.
3. **Henry Roediger III & Jeffrey Karpicke (Washington University in St. Louis) — riset Testing Effect, peer-reviewed** — dasar ilmiah retrieval practice/active recall sebagai teknik belajar, bukan cuma alat asesmen.
4. **Hermann Ebbinghaus — forgetting curve (1885, direplikasi ulang riset modern)** — fondasi historis kurva lupa yang mendasari kenapa spacing diperlukan; diajarkan bersama replikasi modernnya, bukan cuma studi 1885 sebagai fakta berdiri sendiri.
5. **Piotr Wozniak (SuperMemo) & FSRS (Free Spaced Repetition Scheduler)** — dasar rekayasa algoritma spaced repetition modern (SM-2 dan penerusnya FSRS, algoritma default Anki terbaru) — dipakai di modul praktik Anki.
6. **Michelene Chi — riset Self-Explanation Effect, peer-reviewed** — dasar ilmiah ASLI dari Feynman Technique. Feynman Technique sendiri adalah teknik populer (bukan hasil riset peer-reviewed Richard Feynman), wajib disilangkan ke riset Chi ini sebelum diajarkan sebagai teknik berbasis bukti.
7. **Anders Ericsson — riset Deliberate Practice, peer-reviewed (dasar buku *Peak*)** — prinsip practice yang menghasilkan peningkatan nyata (feedback loop, fokus di titik lemah), dibedakan dari sekadar "jam terbang".
8. **Doug Rohrer & Kelli Taylor — riset Interleaving, peer-reviewed** — dasar ilmiah kenapa mencampur topik/jenis soal (bukan blocked practice) meningkatkan retensi & transfer jangka panjang.
9. **Susanne Diekelmann & Jan Born — "The Memory Function of Sleep" (2010), review peer-reviewed (Nature Reviews Neuroscience)** — dasar ilmiah primer konsolidasi memori saat tidur; sumber utama Modul 8, dipakai untuk menyilangkan klaim populer soal tidur (termasuk buku-buku populer yang sebagian klaimnya pernah dikritik) sebelum diajarkan sebagai fakta.
10. **Peter C. Brown, Henry Roediger III, Mark McDaniel — *Make It Stick* (2014)** — sudah jadi Trusted Source `domains/learning-science/DOMAIN.md`; di sini dirujuk ulang sebagai sintesis praktis, selalu disilangkan ke riset primer (poin 1-3, 8) sebelum jadi dasar lesson.

**House rule khusus domain ini:** setiap teknik wajib dicek dulu posisinya di evidence hierarchy Dunlosky (sumber 1) sebelum diajarkan sebagai "efektif" — tolak klaim speed-reading, "photographic memory", atau teknik viral tanpa dasar riset. Klaim soal tidur wajib disilang ke riset primer (sumber 9), tidak cukup mengandalkan buku populer saja.

## Applied Practice Definition

Bukan cuma menulis refleksi teori — setiap lesson diterapkan ke materi NYATA dari salah satu course aktif learner di repo ini (mis. `courses/mastering-claude` atau `courses/character-development`) sebagai laboratorium nyata, konsisten dengan pola `domains/learning-science/DOMAIN.md`. Bedanya dengan domain itu: bukti Applied Practice di sini adalah **skor retensi nyata** (self-test/kuis before-after), bukan cuma rating enjoyment. Bentuknya: membuat Anki deck asli dari materi course lain, menjalankan retrieval self-quiz sebelum dan sesudah menerapkan satu teknik, mencatat skor & jumlah materi yang benar-benar diingat di log mingguan.

## Project Tiers

- **Mini Project** (per modul): satu teknik dari modul diterapkan ke satu sesi belajar materi course lain minggu ini, skor retensi self-test dicatat sebelum-sesudah beserta refleksi singkat.
- **Intermediate Project** (per beberapa modul): menggabungkan 2-3 teknik jadi satu sistem retensi mingguan untuk satu topik course lain, dijalankan nyata 1-2 minggu berturut-turut, termasuk Anki deck asli yang benar-benar dipakai (mis. menggabungkan retrieval practice + spacing + elaboration untuk satu topik).
- **Capstone / Portfolio Project** (akhir course): **Personal Long-Term Memory System** — pipeline retensi pribadi (spaced repetition + active recall + elaboration + jadwal yang sadar-tidur) yang benar-benar dijalankan lawan materi course aktif nyata selama minimal 2-3 minggu, dengan Anki deck fungsional + data kurva retensi asli (skor kuis dari waktu ke waktu) sebagai bukti, disimpan ke `portofolio/`.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak juga dicek lewat lensa:

- **Evidence Tier Check** — apakah teknik yang dipakai berada di tier utility tinggi menurut Dunlosky et al. (sumber 1), atau sekadar "hack" populer tanpa dasar riset?
- **Retention Evidence** — apakah ada data skor kuis/self-test before-after yang nyata, bukan cuma laporan subjektif "kayaknya lebih inget"?
- **Desirable Difficulty Calibration** — apakah tingkat kesulitan retrieval pas (produktif, sesuai Bjork) atau kebablasan sampai bikin learner menyerah?
- **Sustainability** — apakah sistem retensi realistis dijalankan berkelanjutan (jadwal spacing yang benar-benar bisa diikuti), bukan burst semangat sekali lalu berhenti.

## Assessment Form

Quiz retrieval-practice singkat atas materi course ini sendiri setelah tiap bagian teori (course ini sengaja mempraktikkan tekniknya sendiri ke materinya sendiri — meta-consistency), ditambah data skor retensi self-test before-after dari Applied Practice sebagai bukti assessment utama, dan evaluasi skenario: diberi 1 materi course lain nyata milik learner, learner merancang rencana retensi (kombinasi teknik) dan membela pilihannya berdasarkan evidence hierarchy Dunlosky.

## Practitioner Reference Frame

Bandingkan bagaimana cognitive psychology lab (Bjork Learning & Forgetting Lab UCLA, Roediger/Karpicke lab WashU) dan komunitas rekayasa spaced repetition open-source (SuperMemo, pengembang algoritma FSRS di Anki) mendekati "cara membuat ingatan bertahan lama", dibanding konten "hack belajar" media sosial yang sering menjual jalan pintas tanpa dasar riset (klaim speed-reading ekstrem, "unlock ingatan fotografis", learning styles VAK yang sudah dibantah riset — echo pola yang sama di `domains/learning-science/DOMAIN.md`).

## Domain-Specific Standards

- **Tolak klaim speed-reading ekstrem, "photographic memory", atau teknik viral tanpa dasar riset peer-reviewed.** Setiap teknik wajib melewati house rule evidence hierarchy Dunlosky di atas.
- **Feynman Technique wajib disilangkan ke self-explanation effect (Chi)** sebelum diajarkan — tidak pernah diajarkan sebagai teknik berdiri sendiri tanpa dasar risetnya.
- **Klaim soal tidur wajib disilang ke riset primer** (Diekelmann & Born dan riset sejenis), tidak cukup mengandalkan buku populer semata, mengingat sebagian klaim buku populer soal tidur pernah dikritik oleh peneliti lain.
- **Bukan pengganti asesmen klinis.** Bila Applied Practice berulang kali menunjukkan gangguan memori yang jauh di luar lupa normal (mis. kesulitan mengingat informasi baru secara konsisten di luar konteks belajar, bukan sekadar butuh lebih banyak pengulangan), arahkan ke profesional (psikolog/neurolog) — mirror pola eskalasi `domains/learning-science/DOMAIN.md` dan `domains/psychology/DOMAIN.md`.
- Selebihnya, `standards/UNIVERSAL_STANDARDS.md` cukup — `domains/memory-mastery/standards/` dibuat hanya kalau kebutuhan rigor spesifik muncul berulang.

## Domain Goal

Learner memiliki Personal Long-Term Memory System yang benar-benar berjalan lawan materi course aktif nyata di repo ini, dibuktikan lewat data retensi asli (skor kuis before-after dari waktu ke waktu) — bukan sekadar tahu teori active recall/spacing/Feynman/elaboration/tidur tanpa pernah menerapkannya secara konsisten.
