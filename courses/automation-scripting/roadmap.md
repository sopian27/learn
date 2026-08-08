# Course Roadmap

## Course Information

* Course Name: Automation & Scripting — dari 0 sampai Expert
* Category: Software Engineering / DevOps / Productivity Engineering
* Difficulty: Level 0 (belum pernah scripting) → Level 4 (Expert, mampu merancang & membangun toolkit automation pribadi/tim dari nol)
* Estimated Duration: 10 modul, 3-4 lesson/modul, ~30-40 menit/lesson. Pace disesuaikan sambil jalan.
* Prerequisites: Linux CLI dasar (navigasi, permission — beririsan dengan `courses/vps` Modul 2), familiar konsep umum programming (variabel, fungsi, loop — sudah dimiliki dari Java/Spring Boot).

---

# Catatan Scope (baca dulu sebelum mulai)

Berdasarkan jawaban kalibrasi Anda (2026-08-08):

1. **Bahasa utama: Python + Bash, dua-duanya.** Bash untuk automation level OS/Linux (file, cron, sysadmin, tie-in `courses/vps`). Python untuk automation yang butuh logika lebih kompleks (parsing, API, CLI tool proper). Setiap topik dijelaskan "kenapa pilih bahasa ini" dulu, bukan cuma sintaks.
2. **Cakupan: termasuk API & web automation.** Selain scripting sistem/dev-workflow, course ini juga mencakup automasi panggil REST API pihak ketiga dan web/browser automation (BeautifulSoup, Playwright) — dengan penekanan etika & legalitas (ToS, robots.txt) di tiap lesson terkait.
3. **Level awal: nol total** — course dimulai dari mental model "kapan automate, kapan tidak" sebelum sintaks apapun, sesuai instruksi "dari 0 sampai expert".
4. **Non-overlap dengan course lain di repo ini (baca supaya tidak bingung nanti):**
   * `courses/ci-cd` — automation di level **pipeline CI/CD** (GitLab CI, deploy strategy). Course ini automation di level **host/dev-machine** (script yang *dipanggil oleh* pipeline atau jalan mandiri di server), bukan pengganti.
   * `courses/cloud-devops-foundations` — Infrastructure as Code **deklaratif** (Terraform, provisioning). Course ini scripting **imperatif** (task automation, bukan provisioning infrastruktur).
   * `courses/vps` — hardening & operasional 1 server Linux (nginx, SSH, firewall). Script yang dibangun di course ini bisa dijalankan *di atas* VPS tersebut, tapi VPS course tidak mengajarkan scripting mendalam.
   * `courses/n8n-automation` — automation **no-code/low-code** berbasis visual workflow. Course ini automation **code-first**, paradigma berbeda, saling melengkapi bukan duplikat.
   * `courses/aws-api-integration` — spesifik AWS SDK. Module 6 course ini (API automation) generik REST/webhook, bukan AWS-spesifik.
   * `courses/cybersecurity` — AppSec mendalam. Secrets management di Module 9 course ini hanya level "jangan hardcode secret di script", bukan pengganti course keamanan.

Referensi: `ai-los/AI-LOS.md` bagian "Course Creation" (prioritas sumber), [roadmap.sh DevOps Roadmap](https://roadmap.sh/devops) (bagian scripting), [Google Shell Style Guide](https://google.github.io/styleguide/shellguide.html), [GNU Bash Reference Manual](https://www.gnu.org/software/bash/manual/), [Python official docs](https://docs.python.org/3/), [Real Python](https://realpython.com/), [ShellCheck](https://www.shellcheck.net/), [Google SRE Book — ch. "Eliminating Toil"](https://sre.google/sre-book/eliminating-toil/), [Playwright docs](https://playwright.dev/python/), [crontab.guru](https://crontab.guru/) & `man systemd.timer`.

---

# Learning Objectives

* [ ] Menentukan secara sadar kapan sesuatu layak diautomasi (ROI, frekuensi, risiko) dan kapan tidak — bukan automate demi automate
* [ ] Menguasai Bash scripting dari dasar sampai lanjut: control flow, text processing (grep/sed/awk), array, argument parsing, testing (bats, shellcheck)
* [ ] Menguasai Python untuk automation: file/path handling modern, `subprocess` yang aman, logging, CLI tool proper (argparse/click)
* [ ] Menguasai task scheduling: cron mendalam, systemd timer, file/event watcher, dan prinsip idempotency/lock file
* [ ] Mampu mengautomasi integrasi API pihak ketiga (auth, retry/backoff, notifikasi Slack/Telegram) secara etis
* [ ] Mampu melakukan web/browser automation dasar (Playwright) untuk kebutuhan legal (bukan scraping yang melanggar ToS)
* [ ] Mengautomasi dev workflow sendiri: git hooks, task runner (Makefile), quality gate lokal sebelum commit
* [ ] Membangun automation yang reliable: testing, error handling/alerting, dry-run mode, secrets management yang benar
* [ ] Mampu merancang dan membangun toolkit automation pribadi yang terstruktur, terdokumentasi, dan bisa dipakai jangka panjang (siap portofolio freelance)

---

# Course Modules

## Module 1 — Automation Mindset & Foundations

Description: Fondasi cara berpikir sebelum sentuh kode apapun — automation yang baik dimulai dari keputusan yang tepat, bukan skill sintaks.

Lessons:

* [ ] 1.1 Kapan harus automate, kapan tidak — ROI, frekuensi vs effort, "rule of automation" ala Google SRE (Eliminating Toil)
* [ ] 1.2 Level automasi: script manual → scheduled → event-driven → self-healing, dengan contoh nyata tiap level
* [ ] 1.3 Prinsip automation yang baik: idempotency, dry-run, observability, fail-safe (gagal dengan aman, bukan diam-diam rusak)
* [ ] 1.4 Setup environment: WSL/Linux, Python venv, shebang & permission Bash, editor tooling (shellcheck, ruff)

Mini Project: Automation audit — daftar 10 tugas manual sehari-hari/kerjaan, klasifikasikan worth-automating vs not, dengan alasan ROI eksplisit per item.

Status: Not started

---

## Module 2 — Bash Scripting Fundamentals

Description: Dasar shell scripting yang benar — bukan sekadar kumpulan command yang kebetulan jalan.

Lessons:

* [ ] 2.1 Shell basics: variabel, quoting (kenapa penting), exit code, shebang
* [ ] 2.2 Control flow: if/case/loop, fungsi, argumen positional (`$1`, `$@`, `$#`)
* [ ] 2.3 Text processing Unix philosophy: grep, sed, awk, cut, sort, xargs, pipeline
* [ ] 2.4 Error handling: `set -euo pipefail`, `trap`, logging sederhana ke file

Mini Project: Script backup folder dengan rotasi otomatis (hapus backup lebih dari N hari), robust terhadap error (folder tidak ada, disk penuh, dsb).

Status: Not started

---

## Module 3 — Bash Scripting Lanjutan

Description: Level yang membedakan script "asal jalan" dari script yang siap dipakai orang lain.

Lessons:

* [ ] 3.1 Array & associative array, string manipulation lanjutan (parameter expansion)
* [ ] 3.2 Argument parsing proper dengan `getopts` untuk CLI tool ber-flag
* [ ] 3.3 Process & job control: background job, `wait`, eksekusi paralel (`xargs -P`, GNU parallel)
* [ ] 3.4 Testing Bash script (bats-core) dan `shellcheck` sebagai linter wajib

Mini Project: CLI tool Bash "system health check" (cek disk/memory/status service) dengan flag opsional, exit code yang benar, dan lulus `shellcheck` tanpa warning.

Status: Not started

---

## Module 4 — Python untuk Automation

Description: Kapan naik level dari Bash ke Python, dan cara pakai Python idiomatis untuk automation (bukan gaya Java yang dipaksakan).

Lessons:

* [ ] 4.1 Kenapa Python untuk automation dibanding Bash — kapan pilih yang mana (aturan praktis)
* [ ] 4.2 File & path handling modern: `pathlib`, context manager, baca/tulis CSV/JSON/YAML
* [ ] 4.3 `subprocess` — memanggil command shell dari Python dengan aman (hindari shell injection)
* [ ] 4.4 Logging & konfigurasi: modul `logging`, `argparse`/`click`, `python-dotenv` untuk secret

Mini Project: Script Python "log analyzer" — parse log file, ekstrak pola error, generate laporan ringkas (terminal + file).

Status: Not started

---

## Module 5 — Task Scheduling & Event-Driven Automation

Description: Automation yang jalan sendiri tanpa dipicu manual — dan cara membuatnya aman dijalankan berulang.

Lessons:

* [ ] 5.1 Cron mendalam: syntax, best practice, jebakan umum (timezone, environment variable minim)
* [ ] 5.2 systemd timer sebagai alternatif modern cron — kapan pilih yang mana
* [ ] 5.3 File/event watcher (`watchdog` di Python, inotify) — automation berbasis trigger, bukan jadwal
* [ ] 5.4 Idempotency & lock file — mencegah automation jalan dobel/overlap

Mini Project: Jadwalkan script Module 4 (log analyzer) via cron ATAU systemd timer, dilengkapi lock file dan logging hasil eksekusi.

Status: Not started

---

## Module 6 — Automasi API & Integrasi Pihak Ketiga

Description: Automation yang berbicara dengan dunia luar lewat API — dengan etika, bukan cuma teknis.

Lessons:

* [ ] 6.1 Memanggil REST API terprogram: `requests`, autentikasi (API key/OAuth token), retry & exponential backoff
* [ ] 6.2 Automasi notifikasi: kirim pesan ke Slack/Telegram/email dari script
* [ ] 6.3 Rate limiting & etika automation terhadap API pihak ketiga (ToS, fair use)
* [ ] 6.4 Web scraping dasar: BeautifulSoup untuk HTML statis, cek `robots.txt` sebelum scraping apapun

Mini Project: Script yang polling API publik (misal cuaca/harga), memproses data, dan kirim notifikasi Telegram/Slack saat kondisi tertentu terpenuhi.

Status: Not started

---

## Module 7 — Browser Automation

Description: Ketika HTML statis tidak cukup — automasi yang mengendalikan browser sungguhan, dalam batas legal/etis.

Lessons:

* [ ] 7.1 Kapan butuh browser automation (situs JS-heavy) vs scraping HTML biasa
* [ ] 7.2 Playwright dasar: navigasi, selector, wait strategy yang stabil (bukan `sleep` sembarangan)
* [ ] 7.3 Automasi headless: screenshot & generate PDF otomatis
* [ ] 7.4 Anti-pattern & risiko: automation flaky, pelanggaran ToS, deteksi bot — batas etis yang wajib dijaga

Mini Project: Automasi browser untuk tugas legal milik sendiri (misal screenshot dashboard internal harian atau isi form testing sendiri) — bukan scraping situs pihak ketiga yang melarang.

Status: Not started

---

## Module 8 — Automasi Dev Workflow

Description: Mengautomasi pekerjaan berulang di mesin developer sendiri — layer sebelum kode masuk ke CI/CD pipeline (`courses/ci-cd`).

Lessons:

* [ ] 8.1 Git hooks (`pre-commit`, `pre-push`) untuk automasi lokal
* [ ] 8.2 Task runner: Makefile/Taskfile — standarisasi perintah dev sehari-hari
* [ ] 8.3 Automasi quality gate lokal: linter/formatter otomatis sebelum commit (level dev-machine, bukan pipeline)
* [ ] 8.4 Automasi generate boilerplate (script scaffolding project)

Mini Project: Setup pre-commit hook + Makefile untuk 1 repo Spring Boot — format otomatis, lint, dan unit test cepat berjalan sebelum commit diterima.

Status: Not started

---

## Module 9 — Reliability, Testing & Observability Automation

Description: Level yang membedakan automation "iseng" dari automation yang layak dipercaya untuk production/klien.

Lessons:

* [ ] 9.1 Testing automation script sendiri: `pytest` untuk Python, `bats` untuk Bash
* [ ] 9.2 Error handling & alerting — automation yang gagal harus "berteriak", bukan diam
* [ ] 9.3 Secrets management dalam script: environment variable, dasar vault, larangan hardcode
* [ ] 9.4 Dry-run mode & rollback safety untuk automation yang mengubah state (file/DB/infra)

Mini Project: Tambahkan test, dry-run mode, dan alerting kegagalan ke salah satu script dari module sebelumnya.

Status: Not started

---

## Module 10 — Capstone: Personal Automation Toolkit

Description: Menggabungkan semua modul menjadi satu toolkit automation nyata yang terstruktur dan bisa dipakai/ditunjukkan sebagai portofolio.

Lessons:

* [ ] 10.1 Desain arsitektur toolkit: struktur folder, shared library, konfigurasi terpusat
* [ ] 10.2 Orkestrasi banyak script jadi satu CLI dengan subcommand (`toolkit backup`, `toolkit report`, dst.)
* [ ] 10.3 Packaging & distribusi (pip-installable, atau bundel script + PATH)
* [ ] 10.4 Dokumentasi & rencana maintenance jangka panjang

Portfolio Project: "Personal Automation Toolkit" — gabungan minimal 4 automation dari module sebelumnya (backup, log analyzer, API notifier, browser automation) menjadi satu CLI toolkit yang terdokumentasi, diuji, dan aman dijalankan berulang. Disimpan di `portofolio/`.

Status: Not started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: 1.1 Kapan harus automate, kapan tidak
