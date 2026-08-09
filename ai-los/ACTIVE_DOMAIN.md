# ACTIVE_DOMAIN.md

This file controls which subject-matter domain(s) are currently registered and active. It is the domain-layer counterpart to `ACTIVE_MODE.md`, which controls pedagogical *mode* instead. Read this alongside `ACTIVE_MODE.md` at session start.

A "domain" is a subject-matter field (software-engineering, personal-finance, public-speaking, etc.). Each registered domain has a manifest at `domains/<name>/DOMAIN.md`. Pedagogical modes (LEARNING_MODE, REVIEW_MODE, etc.) apply the same way regardless of which domain is active — modes are HOW to teach, domains are WHAT.

`ai-los/CORE_LOS.md` defines the domain-agnostic process this registry plugs into — the Universal Learning Loop, project tiering, review rubric shape, etc. It should be read regardless of which domain is active, including the default `software-engineering` domain.

---

# Registered Domains

**Update 2026-08-07:** Active domains narrowed to 2, at user request, to save tokens (mirrors `progress/roadmap.md` "Course Aktif" narrowing). All other registered domains below are marked Paused — not deregistered, resume any of them anytime by asking. Full goal descriptions for paused domains live in their own `domains/<name>/DOMAIN.md` manifest.

## Active

- `software-engineering` — default domain. Manifest: `domains/software-engineering/DOMAIN.md` (thin pointer to `ai-los/AI-LOS.md` + `standards/*` + `courses/*`). Covers `courses/mastering-claude/roadmap.md`.
- `character-development` — registered 2026-08-07, roadmap approved. Manifest: `domains/character-development/DOMAIN.md`. Active course: `courses/character-development/roadmap.md`. Goal: build genuine humility (accurate self-view + real feedback-seeking, not self-deprecation), sustainable work ethic (not burnout-driven grind), and consistent respect for others (no belittling), tested through real applied practice rather than theory alone.

## Paused (2026-08-07, hemat token — resume anytime)

- `personal-finance` — Manifest: `domains/personal-finance/DOMAIN.md`. Course: `courses/personal-finance/roadmap.md`.
- `n8n-automation` — Manifest: `domains/n8n-automation/DOMAIN.md`. Course: `courses/n8n-automation/roadmap.md`.
- `communication-skills` — Manifest: `domains/communication-skills/DOMAIN.md`. Course: `courses/communication-skills/roadmap.md`.
- `critical-thinking` — Manifest: `domains/critical-thinking/DOMAIN.md`. Course: `courses/critical-thinking/roadmap.md`.
- `world-history` — Manifest: `domains/world-history/DOMAIN.md`. Course: `courses/world-history/roadmap.md`.
- `guitar` — Manifest: `domains/guitar/DOMAIN.md`. Course: `courses/guitar/roadmap.md`.
- `self-love` — Manifest: `domains/self-love/DOMAIN.md`. Course: `courses/self-love/roadmap.md`.
- `film-criticism` — Manifest: `domains/film-criticism/DOMAIN.md`. Course: `courses/film-criticism/roadmap.md`.
- `korean` — Manifest: `domains/korean/DOMAIN.md`. Course: `courses/korean/roadmap.md`.
- `cryptocurrency` — Manifest: `domains/cryptocurrency/DOMAIN.md`. Course: `courses/cryptocurrency/roadmap.md`.
- `conspiracy-theories` — registered 2026-08-08, domain pack approved. Manifest: `domains/conspiracy-theories/DOMAIN.md`. No course roadmap yet — user chose to register paused first; build `courses/conspiracy-theories/roadmap.md` (with approval before Lesson 1, per `ai-los/COURSE_CREATION.md`) when this domain is activated.
- `japanese` — registered 2026-08-08, domain pack approved. Manifest: `domains/japanese/DOMAIN.md`. Bahasa Jepang dari 0 sampai Expert, dikalibrasi ke JLPT N5-N1 (pola sejajar dengan `korean`). No course roadmap yet — user chose to register paused first (konsisten dengan narrowing 2026-08-07); build `courses/japanese/roadmap.md` (dengan riset + approval sebelum Lesson 1, per `ai-los/COURSE_CREATION.md`) saat domain ini diaktifkan.
- `stoicism` — registered 2026-08-08, domain pack approved. Manifest: `domains/stoicism/DOMAIN.md`. Stoicism sebagai sistem filosofis utuh (sejarah, Logic/Physics/Ethics, teks primer Meditations/Enchiridion/Letters from a Stoic) dari dasar sampai expert, eksplisit dipisah dari irisan praktisnya di `character-development` (yang cuma pakai Stoic sebagai satu sumber ego-management). No course roadmap yet — user chose to register paused first (konsisten dengan narrowing 2026-08-07); build `courses/stoicism/roadmap.md` (dengan riset + approval sebelum Lesson 1, per `ai-los/COURSE_CREATION.md`) saat domain ini diaktifkan.
- `pornography-recovery` — registered 2026-08-08, domain pack approved. Manifest: `domains/pornography-recovery/DOMAIN.md`. Pemulihan dari pola konsumsi pornografi kompulsif, berbasis psikologi adiksi perilaku (relapse prevention, ACT, habit reversal) dan kerangka sains sekuler murni (bukan moral/agama), dengan house rule anti-shame eksplisit dan eskalasi wajib ke profesional untuk indikasi klinis. Registered paused per preferensi user (konsisten dengan narrowing 2026-08-07), tapi course roadmap-nya tetap dibangun sekarang atas permintaan user — lihat `courses/pornography-recovery/roadmap.md`.
- `english` — registered 2026-08-08, domain pack + course roadmap approved. Manifest: `domains/english/DOMAIN.md`. Fluency Bahasa Inggris menyeluruh empat skill, dikalibrasi ke CEFR A1-C2 (EF SET untuk placement/kalibrasi), bobot lebih besar di listening & speaking (skill terlemah user), dengan fokus khusus konsumsi media native (film/series) tanpa subtitle terjemahan lewat metodologi "Subtitle Gap Log", plus teknik shadowing berbasis riset untuk pronunciation/fluency. Kalibrasi awal: user BUKAN nol total — sudah bisa bicara tapi belum pernah dilatih sengaja, dan listening pasif dengan subtitle Inggris ~70-80% (perkiraan CEFR B1-B2) — placement test EF SET di Module 1 menentukan titik mulai riil per-skill. Registered paused per preferensi user (konsisten dengan kebijakan hemat-token 2026-08-07); course roadmap sudah lengkap 10 modul — lihat `courses/english/roadmap.md`, lanjut kapan saja atas permintaan "lanjut course Bahasa Inggris".
- `geospatial-technology` — registered 2026-08-09, domain pack approved (via permintaan user langsung membangun course roadmap-nya). Manifest: `domains/geospatial-technology/DOMAIN.md`. Geodesi/CRS, model data vector/raster, database spasial (PostGIS, spatial indexing R-tree/geohash/H3/S2), analisis spasial, remote sensing/Earth observation tingkat literasi-terapan, GPS/GNSS, dan web mapping — dikonsumsi lewat coding nyata, bersandar pada skill software-engineering yang sudah dipunya user. Registered paused per kebijakan hemat-token 2026-08-07; course roadmap dibangun sekarang atas permintaan user — lihat `courses/geospatial-technology/roadmap.md`.
- `physics-for-software-engineers` — registered 2026-08-09, domain pack approved (via permintaan user langsung membangun course roadmap-nya). Manifest: `domains/physics-for-software-engineers/DOMAIN.md`. Fisika klasik/Newtonian terapan lewat kode (kinematika/dinamika, energi/momentum, rotasi, metode integrasi numerik, rigid body & collision detection/response, optik/graphics dasar, literasi sensor/hardware) — eksplisit BUKAN Relativitas (lihat domain `relativity` untuk itu). Registered paused per kebijakan hemat-token 2026-08-07; course roadmap dibangun sekarang atas permintaan user — lihat `courses/physics-for-software-engineers/roadmap.md`.
- `relativity` — domain pack sudah ada sejak 2026-08-08 (`domains/relativity/DOMAIN.md`) tapi terlewat tidak pernah ditambahkan ke registry ini — dikoreksi 2026-08-09. Relativitas Khusus & Umum dari fondasi konseptual sampai level pascasarjana awal fisika teoretis (Taylor & Wheeler, Schutz, MTW), dengan Derivation & Simulation Lab sebagai Applied Practice. Registered paused per kebijakan hemat-token 2026-08-07; course roadmap dibangun sekarang atas permintaan user — lihat `courses/relativity/roadmap.md`.
- `intelligence-studies` — domain pack sudah ada sejak 2026-08-08 (`domains/intelligence-studies/DOMAIN.md`) tapi terlewat tidak pernah ditambahkan ke registry ini — dikoreksi 2026-08-09. Studi intelijen sebagai disiplin akademik (siklus intelijen, jenis-jenis INT, analytic tradecraft, studi kasus kegagalan/keberhasilan, oversight & etika, OSINT legal ala Bellingcat) — eksplisit BUKAN pelatihan tradecraft operasional. Registered paused per kebijakan hemat-token 2026-08-07; course roadmap dibangun sekarang atas permintaan user — lihat `courses/intelligence-studies/roadmap.md`.
- `content-creation` — registered 2026-08-09, domain pack approved. Manifest: `domains/content-creation/DOMAIN.md`. Produksi konten digital **faceless permanen** (bukan tahap sementara) dan **multi-platform sejak awal** (sistem repurposing satu ide → banyak format), termasuk mengajarkan skill editing video dari nol (DaVinci Resolve/CapCut, teori editing Walter Murch) sebagai bagian kurikulum — tiga kalibrasi eksplisit dari user. Eksplisit dipisah dari `public-speaking` (tampil kamera) dan `entrepreneurship` (operasional bisnis penuh dari hasil konten). House rule: tolak taktik "guru hasil instan" tanpa craft nyata, wajib cek kebijakan resmi platform (reused/inauthentic content) sebelum ajarkan teknik otomasi/AI, copyright aset wajib bersih. Registered paused per kebijakan hemat-token 2026-08-07; course roadmap dibangun sekarang atas permintaan user — lihat `courses/content-creation/roadmap.md`.

---

# Default Domain

If no course or conversation makes the domain explicit, assume `software-engineering`.

---

# Registering a New Domain

Whenever I ask to learn a new subject that isn't already registered (e.g. "belajar public speaking dari 0"):

1. Check whether `domains/<name>/DOMAIN.md` already exists.
2. If not, scaffold it from `templates/DOMAIN_TEMPLATE.md`, research trusted sources the same way `ai-los/COURSE_CREATION.md` requires for course roadmaps, and wait for my approval before treating it as final.
3. Once approved, add it to the "Registered Domains" list above.
4. Build the course as usual under `courses/<name>/roadmap.md`, following `templates/COURSE_TEMPLATE.md` — the course structure itself never changes between domains, only the domain pack's Applied Practice / Assessment / Trusted Sources content does.

---

# Multiple Active Domains

More than one domain can be registered and active at once, the same way `ACTIVE_MODE.md` combines multiple pedagogical modes. Which domain governs a given conversation is determined by which course/topic is currently being discussed, not a single global switch.

---

# Domain vs Mode

Never confuse the two:

- `ACTIVE_MODE.md` = HOW I teach right now (Learning/Review/Debug/Architect/etc.)
- `ACTIVE_DOMAIN.md` = WHAT subject I'm teaching right now (software-engineering/personal-finance/etc.)

Both apply simultaneously. A conversation can be in `REVIEW_MODE` for the `personal-finance` domain at the same time.
