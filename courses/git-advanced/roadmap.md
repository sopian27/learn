# Course Roadmap

## Course Information

* Course Name: Git Intermediate–Advanced — dari Percaya Diri Pakai Git Sampai Expert
* Category: Software Engineering Practice / Developer Tooling
* Difficulty: Level 2 (Intermediate) → Level 4 (Expert)
* Estimated Duration: 10 modules, ~3-5 lessons each (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Sudah terbiasa Git basics — `add`, `commit`, `push`, `pull`, `branch`/`checkout` dasar, `clone`, `merge` sederhana. Course ini **tidak** mengajarkan basic tersebut dari nol; dimulai langsung dari branching strategy tingkat tim.

---

# Catatan Scope (baca dulu sebelum approve)

1. **Domain**: `software-engineering` (Active, reuse — bukan domain baru). Course ini pure technical skill, konsisten dengan katalog `courses/` yang sudah ada (ci-cd, code-review, kubernetes, dll).
2. **Overlap dengan course lain (cek sekilas, bukan duplikasi)**:
   * `courses/ci-cd/roadmap.md` — mencantumkan "Git dasar" sebagai prerequisite, tidak mengajarkan rebase/reflog/internals. Tidak ada duplikasi; course ini bisa jadi prerequisite lanjutan untuk CI/CD (mis. rebase workflow sebelum trigger pipeline).
   * `courses/code-review/roadmap.md` — fokus ke *cara mereview kode* (mindset, feedback, arsitektur), bukan ke mekanika Git. Module 10 course ini ("Workflow Produksi Tim Besar") akan cross-reference ke `courses/code-review/roadmap.md` untuk bagian review flow, supaya tidak mengulang isinya.
   * Kesimpulan: scope Git di kedua course tersebut dangkal (level pemakaian harian), sedangkan course ini masuk jauh lebih dalam (interactive rebase, reflog recovery, internals, hooks) — course baru tetap dibuat penuh.
3. **Level awal**: Intermediate — asumsi sudah nyaman commit/push/pull/branch harian, tapi belum percaya diri dengan rebase, reflog, bisect, atau internals. Course dimulai dari branching strategy (konteks kolaborasi tim), baru masuk ke mekanika history-rewriting yang lebih berisiko.
4. **Konvensi**: Seluruh istilah branch naming, commit message (Conventional Commits), PR checklist, dan merge strategy mengikuti `standards/GIT_CONVENTIONS.md` sebagai rujukan tertinggi (prioritas `standards/` di atas `courses/` sesuai root `CLAUDE.md`, section "Repository Priority"). Setiap module yang bersinggungan dengan konvensi ini akan mengutip `standards/GIT_CONVENTIONS.md` secara eksplisit.
5. **Pendekatan praktik**: Karena topiknya berisiko merusak history, setiap module dengan operasi destruktif (rebase, reset --hard, reflog recovery) menggunakan skenario **repo latihan terpisah/disposable** (bukan repo `d:\learn` ini) — dibuat & dirusak sengaja lalu dipulihkan, sesuai semangat `ai-los/AI-LOS.md` "Learning by Doing" tapi tetap aman.

Referensi riset yang dipakai menyusun roadmap ini: [Pro Git Book](https://git-scm.com/book/en/v2) (Chapter 3 Branching, Chapter 7 Git Tools — Rebasing, Reset, Reflog, Bisect, Rewriting History; Chapter 8 Customizing Git — Hooks; Chapter 10 Git Internals), [Atlassian Git Tutorials — Advanced](https://www.atlassian.com/git/tutorials/advanced-overview) (rebase, cherry-pick, reset/checkout/revert, bisect), dan `standards/GIT_CONVENTIONS.md` (konvensi internal repo ini).

---

# Learning Objectives

* [ ] Memilih & menjelaskan trade-off branching strategy (Git Flow, trunk-based, GitHub Flow) sesuai konteks tim/produk, bukan ikut-ikutan tren
* [ ] Mahir rebase & interactive rebase untuk merapikan history sebelum PR (reorder, squash, edit, drop, fixup)
* [ ] Memahami kapan squash vs merge commit, dan menggunakan cherry-pick secara tepat (bukan sebagai pengganti merge/rebase)
* [ ] Membedakan revert vs reset (soft/mixed/hard) secara presisi, dan tahu kapan masing-masing aman dipakai (khususnya di branch yang sudah di-push/shared)
* [ ] Mampu memakai reflog untuk memulihkan commit "hilang", branch terhapus, atau hasil reset/rebase yang salah
* [ ] Menyelesaikan conflict tingkat lanjut (rebase conflict berlapis, merge vs rebase conflict, rerere) dengan percaya diri
* [ ] Menggunakan `git bisect` (manual & automated dengan script) untuk melacak commit penyebab regresi
* [ ] Menulis & memasang Git hooks (client-side: pre-commit, commit-msg; server-side: pre-receive) untuk enforcement otomatis
* [ ] Memahami Git internals: objects (blob/tree/commit/tag), refs, packfiles — cukup dalam untuk debug masalah aneh dan menjelaskan "kenapa" di balik setiap perintah
* [ ] Menerapkan workflow Git level produksi tim besar: code review flow, monorepo vs polyrepo, dan menyusun/mengaudit Git convention untuk tim

---

# Course Modules

## Module 1 — Branching Strategy untuk Kolaborasi Tim

Description: Sebelum masuk mekanika lanjutan, kalibrasi dulu konteks kolaborasi: kenapa strategi branching berbeda-beda dipilih tim berbeda. Membandingkan Git Flow (rilis terjadwal, banyak branch long-lived), trunk-based development (CI/CD ketat, feature flag), dan GitHub Flow (deploy kontinu, sederhana) — bukan menghafal diagram, tapi memahami trade-off skala tim, frekuensi rilis, dan maturity testing/CI yang membuat satu strategi lebih cocok dari yang lain.

Lessons:

* [ ] Kenapa branching strategy penting — biaya strategi yang salah (merge hell, rilis tertunda, CI lambat)
* [ ] Git Flow: anatomi (main, develop, feature, release, hotfix) & kapan masih relevan
* [ ] Trunk-based development: short-lived branch, feature flag, kebutuhan CI/CD matang
* [ ] GitHub Flow: model sederhana untuk deploy kontinu
* [ ] Memilih strategi sesuai konteks tim (ukuran tim, frekuensi rilis, maturity testing) & menyelaraskan dengan `standards/GIT_CONVENTIONS.md` (branch naming: `feature/`, `bugfix/`, `hotfix/`, dll)

Mini Project: Simulasikan 1 fitur yang dikerjakan 2 "developer" (2 clone lokal) di bawah trunk-based development dengan feature flag sederhana — dokumentasikan juga bagaimana skenario yang sama akan berbeda alurnya di bawah Git Flow.

Status: Not Started

---

## Module 2 — Rebase & Interactive Rebase

Description: Jantung dari "merapikan history" — memahami rebase secara mental model (replay commit, bukan gabung dua history), bukan cuma menghafal perintah. Termasuk bahaya rebase pada branch yang sudah dibagi (shared history).

Lessons:

* [ ] Rebase vs merge — mental model "replay" vs "gabung", visualisasi graph sebelum/sesudah
* [ ] Basic rebase: `git rebase <branch>`, menyelaraskan feature branch dengan `main` terbaru
* [ ] Interactive rebase: `git rebase -i` — reorder, reword, edit, drop, squash, fixup
* [ ] `git commit --fixup` + `git rebase -i --autosquash` untuk workflow cepat
* [ ] The Perils of Rebasing — golden rule "jangan rebase history yang sudah di-push & dipakai orang lain", dan cara aman jika terpaksa (`--force-with-lease`)

Mini Project: Ambil branch dengan 6-8 commit "berantakan" (typo message, commit "wip", urutan salah) di repo latihan, rapikan jadi history yang bersih & logis pakai interactive rebase, lalu bandingkan graph sebelum/sesudah.

Status: Not Started

---

## Module 3 — Squash & Cherry-Pick

Description: Dua teknik "mengambil sebagian history" yang sering disalahgunakan kalau tidak paham kapan tepat dipakai — squash untuk merapikan sebelum merge, cherry-pick untuk memindahkan commit spesifik antar branch (mis. hotfix ke banyak branch rilis).

Lessons:

* [ ] Squash commit: interactive rebase squash vs "squash merge" saat PR (selaras `standards/GIT_CONVENTIONS.md` — "Merge Strategy: Prefer Squash Merge")
* [ ] `git cherry-pick` — memindahkan 1 atau beberapa commit spesifik antar branch
* [ ] Cherry-pick dengan conflict & `-n`/`--no-commit` untuk kontrol lebih halus
* [ ] Kapan cherry-pick tepat (hotfix ke multiple release branch) vs kapan ini anti-pattern (pengganti merge/rebase yang malas)

Mini Project: Skenario hotfix — bug kritis diperbaiki di `main`, harus di-cherry-pick ke 2 branch rilis lama (`release/1.x`, `release/2.x`) tanpa membawa perubahan lain yang tidak relevan.

Status: Not Started

---

## Module 4 — Revert vs Reset: Soft, Mixed, Hard

Description: Perbedaan paling sering disalahpahami pemula-menengah — revert membuat commit baru (aman untuk shared branch), reset memindahkan HEAD/index/working directory (berbahaya untuk shared branch). Modul ini presisi soal apa yang disentuh masing-masing mode reset.

Lessons:

* [ ] `git revert` — commit baru yang membalikkan perubahan, aman untuk branch yang sudah di-push
* [ ] `git reset --soft` — pindah HEAD saja, staged changes tetap ada
* [ ] `git reset --mixed` (default) — pindah HEAD & unstage, working directory tetap
* [ ] `git reset --hard` — pindah HEAD, index, DAN working directory (destruktif, hilang perubahan)
* [ ] Decision framework: kapan revert vs reset, dan kenapa reset --hard di shared branch adalah bencana

Mini Project: Di repo latihan, buat 3 skenario terpisah yang masing-masing menuntut revert, reset --soft, dan reset --hard sebagai solusi paling tepat — jelaskan alasannya per skenario.

Status: Not Started

---

## Module 5 — Reflog & Recovery dari Kesalahan Fatal

Description: "Jaring pengaman" Git yang jarang diketahui pemula — reflog mencatat semua pergerakan HEAD lokal, sehingga hampir semua kesalahan (branch terhapus, commit "hilang" setelah reset --hard, rebase yang salah) bisa dipulihkan.

Lessons:

* [ ] Apa itu reflog & kenapa ini berbeda dari commit history biasa (lokal, expire, tidak ikut ke-push)
* [ ] Memulihkan commit yang "hilang" setelah `reset --hard` yang salah
* [ ] Memulihkan branch yang tidak sengaja terhapus
* [ ] Memulihkan hasil rebase/cherry-pick yang salah/kacau
* [ ] Batas reflog — kapan recovery tetap tidak mungkin (expired, garbage collected, tidak pernah di-commit)

Mini Project (skenario "kacaukan lalu recovery"): Di repo latihan disposable, sengaja: (1) hapus branch yang belum di-merge, (2) `reset --hard` ke commit lama sehingga 3 commit "hilang", (3) rebase yang salah menimpa commit penting — lalu pulihkan ketiganya murni pakai reflog, dokumentasikan tiap langkah investigasi.

Status: Not Started

---

## Module 6 — Conflict Resolution Tingkat Lanjut

Description: Melampaui "resolve conflict manual sekali lalu commit" — menangani conflict berulang saat rebase multi-commit, memahami perbedaan tampilan conflict merge vs rebase, dan memakai `rerere` supaya Git mengingat resolusi yang sudah pernah dilakukan.

Lessons:

* [ ] Anatomi conflict marker & cara membaca `<<<<<<<`, `=======`, `>>>>>>>` dengan benar di konteks merge vs rebase (arah "ours"/"theirs" terbalik antara keduanya!)
* [ ] Conflict berlapis saat interactive rebase multi-commit — strategi menyelesaikan tanpa kehilangan konteks
* [ ] `git rerere` (reuse recorded resolution) untuk conflict berulang
* [ ] Tools bantu: `git mergetool`, `git diff --diff-filter`, strategi `-X ours`/`-X theirs` (dan kenapa harus hati-hati memakainya)
* [ ] Membatalkan proses yang macet: `--abort` pada merge/rebase/cherry-pick

Mini Project: Buat 2 branch yang sengaja bertabrakan di banyak titik (>=3 commit dengan conflict berbeda-beda), selesaikan lewat rebase (bukan merge), aktifkan `rerere` untuk conflict yang berulang di beberapa commit.

Status: Not Started

---

## Module 7 — Bisect untuk Debugging Regresi

Description: Teknik pencarian biner untuk melacak commit persis penyebab bug regresi di history yang panjang — jauh lebih cepat & sistematis daripada menelusuri manual satu-satu.

Lessons:

* [ ] Mental model binary search pada history commit
* [ ] `git bisect start/good/bad` — alur manual step-by-step
* [ ] `git bisect run` dengan script otomatis (test/build script sebagai oracle good/bad)
* [ ] Menangani "noise": commit yang tidak bisa di-build/di-test (`git bisect skip`)
* [ ] `git bisect log` & `git bisect replay` untuk mendokumentasikan/mengulang sesi bisect

Mini Project: Di repo latihan dengan riwayat commit yang disiapkan sengaja mengandung 1 bug regresi tersembunyi di tengah history (mis. 30+ commit), gunakan `git bisect run` dengan script test otomatis untuk menemukan commit penyebab dalam < log2(N) langkah.

Status: Not Started

---

## Module 8 — Git Hooks

Description: Otomatisasi berbasis event Git — client-side untuk feedback cepat sebelum commit/push, server-side untuk enforcement yang tidak bisa dilewati developer individu (berbeda dari hook client yang bisa di-bypass dengan `--no-verify`).

Lessons:

* [ ] Anatomi `.git/hooks/` & lifecycle hook (kapan tiap hook dieksekusi Git)
* [ ] Client-side hooks: `pre-commit` (lint/format check), `commit-msg` (validasi format sesuai Conventional Commits di `standards/GIT_CONVENTIONS.md`), `pre-push`
* [ ] Server-side hooks: `pre-receive`, `update`, `post-receive` — enforcement yang tidak bisa di-bypass developer
* [ ] Distribusi hook ke tim: keterbatasan `.git/hooks` (tidak ikut ter-clone) vs tools seperti Husky/pre-commit framework/`core.hooksPath`
* [ ] Kapan hook adalah solusi tepat vs kapan sebaiknya di CI (nyambung ke `courses/ci-cd/roadmap.md` sebagai lapisan enforcement kedua)

Mini Project: Pasang `pre-commit` hook yang menolak commit jika ada trailing whitespace/format salah, dan `commit-msg` hook yang menolak commit message yang tidak mengikuti Conventional Commits (`feat:`, `fix:`, dll sesuai `standards/GIT_CONVENTIONS.md`) — uji dengan commit yang sengaja melanggar.

Status: Not Started

---

## Module 9 — Git Internals

Description: Membuka "kotak hitam" Git — begitu paham objects, refs, dan packfiles, hampir semua perintah di module sebelumnya (rebase, reset, reflog, cherry-pick) berhenti terasa seperti mantra dan mulai terasa seperti konsekuensi logis dari model data yang sederhana.

Lessons:

* [ ] Git object model: blob (isi file), tree (struktur direktori), commit (snapshot + metadata), tag (annotated tag) — dan bagaimana `.git/objects` menyimpannya (content-addressable storage, SHA hash)
* [ ] Refs: apa itu branch & tag sebenarnya (cuma file berisi pointer ke commit hash) — `.git/refs`, `HEAD` sebagai ref simbolik
* [ ] Plumbing commands: `git cat-file`, `git hash-object`, `git ls-tree`, `git rev-parse` untuk membongkar objek secara langsung
* [ ] Packfiles & garbage collection — kenapa `.git` tidak membengkak tak terbatas, delta compression
* [ ] Menghubungkan kembali ke module sebelumnya: kenapa reset cuma "memindahkan pointer", kenapa rebase membuat commit baru dengan hash berbeda, kenapa reflog bisa memulihkan objek yang "belum di-GC"

Mini Project: Dari repo latihan kecil, telusuri manual dengan plumbing command (`cat-file -p`, `ls-tree`) dari sebuah commit hash sampai ke isi 1 file di dalamnya — gambarkan graph objects (commit → tree → blob) yang ditemukan.

Status: Not Started

---

## Module 10 — Workflow Produksi Tim Besar

Description: Menyatukan semua module sebelumnya ke dalam konteks operasional tim besar — bagaimana review flow, struktur repo, dan konvensi tim saling berkaitan dengan mekanika Git yang sudah dikuasai.

Lessons:

* [ ] Code review flow & Git: PR/MR kecil vs besar, stacked PR, rebase-before-merge vs merge-before-review (cross-reference `courses/code-review/roadmap.md` untuk sisi *mindset* review, module ini fokus sisi *mekanika Git*-nya)
* [ ] Monorepo vs polyrepo: trade-off untuk Git secara spesifik (clone/fetch performance, partial clone, sparse checkout, submodule vs subtree)
* [ ] Menyusun/mengaudit Git convention untuk tim: branch naming, commit message, PR checklist, merge strategy — mengambil `standards/GIT_CONVENTIONS.md` sebagai baseline lalu mendiskusikan penyesuaian konteks
* [ ] Git di CI/CD: shallow clone, cache, trigger berbasis tag/branch pattern (cross-reference `courses/ci-cd/roadmap.md`)
* [ ] Studi kasus: bagaimana tim besar (mis. kernel Linux dengan `git format-patch`/mailing list, tim di Google/Meta dengan monorepo, tim GitHub Flow-style dengan trunk-based + feature flag) memilih & menyesuaikan workflow Git mereka

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Git Incident Response & Team Convention Simulation** — simulasikan 1 hari kerja sebagai engineer yang harus menangani serangkaian insiden Git berurutan di repo latihan yang sama, lalu menutupnya dengan menyusun dokumen konvensi tim: (1) sebuah branch penting ter-reset --hard secara tidak sengaja oleh rekan tim dan harus dipulihkan lewat reflog, (2) sebuah bug regresi harus dilacak lewat `git bisect run` di history >30 commit, (3) sebuah hotfix harus di-cherry-pick ke 2 branch rilis berbeda tanpa membawa perubahan tidak relevan, (4) sebuah feature branch dengan history berantakan (10+ commit) harus dirapikan lewat interactive rebase sebelum PR, dengan conflict yang harus diselesaikan di tengah proses, (5) pre-commit & commit-msg hook harus dipasang untuk mencegah insiden serupa berulang di masa depan, (6) ditutup dengan 1 dokumen "Team Git Convention" yang mengaudit & menyesuaikan `standards/GIT_CONVENTIONS.md` untuk konteks tim fiktif (ukuran tim, frekuensi rilis, monorepo/polyrepo).

Acceptance Criteria:

* Branch yang ter-reset --hard berhasil dipulihkan 100% tanpa kehilangan commit, didokumentasikan langkah investigasi reflog-nya
* Commit penyebab regresi ditemukan lewat `git bisect run` (bukan manual guessing), dengan bukti log bisect
* Hotfix ter-cherry-pick bersih ke kedua branch rilis tanpa membawa perubahan tidak relevan (diverifikasi via `git diff`)
* History feature branch rapi & logis setelah interactive rebase (diverifikasi via `git log --graph`), conflict terselesaikan dengan benar (bukan asal `-X ours`)
* Hook `pre-commit` & `commit-msg` terbukti menolak commit yang melanggar (diuji dengan commit percobaan yang sengaja melanggar)
* Dokumen "Team Git Convention" selesai, mereferensikan `standards/GIT_CONVENTIONS.md` secara eksplisit dan menjelaskan setiap penyesuaian
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Kenapa branching strategy penting (biaya strategi yang salah)
