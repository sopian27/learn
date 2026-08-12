# Module 3 Mini Project — Build 1 Fitur, Pakai 5 Mekanisme

Status: Disetujui (2026-08-12), belum dieksekusi.

## Tujuan

Mini project ini gabung kelima lesson Module 3 (Tools & Permission, Hooks, Plan Mode, MCP Servers, Background Agents) jadi satu alur kerja nyata — bukan latihan terpisah per lesson. Vehicle-nya: fitur Spring Boot kecil, dibangun sambil sengaja memicu tiap mekanisme dan mencatat buktinya.

## Setup

Project baru: `playground/mastering-claude/module3-project/`

- Spring Boot minimal (Java 25, Maven/Gradle bebas pilih user)
- Satu resource: `Note` — field `id`, `title`, `content`
- Endpoint: create, get by id, list all, delete
- Storage: in-memory (List/Map) atau H2 — tidak perlu Postgres/Docker untuk scope sekecil ini

## Langkah Wajib (mapping ke lesson)

### 1. Plan Mode (3.3)
Sebelum kode apa pun ditulis, masuk plan mode. Desain: struktur package, model `Note`, kontrak endpoint (method, path, request/response shape). Simpan hasil plan mode ke log (lihat "Deliverable").

### 2. Tools & Permission (3.1)
Selama build jalan, perhatikan dan catat tool call yang minta approval vs auto-allow — minimal: satu Bash call (`mvn`/`gradle`), satu Write call (file baru). Catat kenapa masing-masing di-allow/prompt (permission mode yang aktif saat itu).

### 3. Hooks (3.2)
Pasang satu hook nyata di `.claude/settings.json`: PostToolUse pada Edit/Write yang auto-run test (`mvn test` atau setara) tiap kode Note API berubah. Bukti: konfigurasi hook + minimal satu log run hook ke-trigger.

### 4. MCP Servers (3.4)
Pakai MCP context7 buat cek dokumentasi Spring Boot terkini soal satu detail implementasi nyata (misal validation annotation `@Valid`/`@NotBlank`, atau `ResponseEntity` status code convention). Catat query yang dipakai dan bagaimana hasilnya mengubah/mengonfirmasi kode.

### 5. Background Agent (3.5)
Dispatch background agent (Agent tool, `run_in_background: true`) buat nulis unit test JUnit untuk Note API, paralel sambil sesi utama lanjut kerja lain (misal nulis dokumentasi endpoint). Tidak boleh nebak/tulis hasil agent sebelum notifikasi selesai datang — sesuai prinsip yang sudah dipelajari di 3.5.

## Deliverable

1. Kode jalan di `playground/mastering-claude/module3-project/` (build + test pass)
2. `.claude/settings.json` — hook config dari langkah 3
3. Log `playground/mastering-claude/3-mini-project.md` berisi bukti kelima langkah (plan mode output, catatan permission, hook trigger log, MCP query+hasil, background agent dispatch+hasil) + refleksi akhir: bagaimana kelima mekanisme ini saling nyambung jadi satu workflow agentic (jembatan ke preview "agentic engineering" Module 8)

## Acceptance Criteria

- [ ] Note API jalan, test pass
- [ ] Plan mode dipakai sebelum coding, hasil plan tercatat
- [ ] Minimal 1 tool call permission diamati & dicatat (allow/prompt + alasan)
- [ ] Hook aktif & terbukti ke-trigger minimal 1x
- [ ] MCP context7 dipakai buat 1 keputusan implementasi nyata, tercatat
- [ ] Background agent dipakai buat task paralel nyata, hasil tidak ditebak sebelum notifikasi
- [ ] Refleksi akhir ditulis di log

## Scope Boundary

Bukan proyek produksi — tidak perlu security review penuh, tidak perlu Postgres/Kafka/Docker/K8s (itu porsi course Spring Boot lain di repo ini, `courses/rest-api-design/`). Fokus murni: buktikan 5 mekanisme Claude Code kepakai nyata dalam satu alur kerja kecil.
