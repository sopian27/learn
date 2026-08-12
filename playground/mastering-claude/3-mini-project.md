## 1. Plan Mode

## Context

Module 3 mini-project (`courses/mastering-claude/module3-mini-project.md`, plan `module3-mini-project-plan.md`) butuh bukti Plan Mode dipakai sebelum coding. Scaffold Spring Boot udah ada (`playground/mastering-claude/module3-project/`, base package `com.mastering.claude.notes`, commit 9142e10). Ini desain fitur Note CRUD — dipakai Task 2-4 di plan eksekusi. Design only, no code, no file dibuat selain plan ini.

## 1. Model `Note`

Record, immutable, in-memory storage (`ConcurrentHashMap<Long, Note>` + `AtomicLong` sequence buat id).

```java
package com.mastering.claude.notes.model;

public record Note(Long id, String title, String content) {}
```

Field:
- `id: Long` — server-generated, auto-increment via `AtomicLong`
- `title: String` — required, non-blank
- `content: String` — optional (boleh kosong)

Request body pakai DTO terpisah (`NoteRequest(String title, String content)`), bukan `Note` langsung — client gak kirim `id`.

## 2. REST Endpoints (4)

| # | Method | Path | Request Body | Response Body | Status Code |
|---|--------|------|---------------|----------------|-------------|
| 1 | POST | `/notes` | `{ "title": string, "content": string }` | `Note` (dengan `id` terisi) | `201 Created` (title kosong → `400 Bad Request`) |
| 2 | GET | `/notes/{id}` | — | `Note` | `200 OK` (id gak ada → `404 Not Found`) |
| 3 | GET | `/notes` | — | `Note[]` (bisa array kosong) | `200 OK` |
| 4 | DELETE | `/notes/{id}` | — | — (empty body) | `204 No Content` (id gak ada → `204` juga, delete idempotent — atau `404` kalau mau strict; rekomendasi: `204` selalu, simpel buat scope mini-project) |

Catatan validasi: `400` di endpoint 1 baru aktif kalau Task 5 (MCP context7 + `@Valid`/`@NotBlank`) udah dieksekusi — di luar scope Task 2 awal (create/get minimal tanpa validasi dulu, sesuai TDD steps di plan eksekusi).

## 3. Package Layout

```
com.mastering.claude.notes
├── NotesApplication.java          (sudah ada — @SpringBootApplication)
├── model/
│   └── Note.java                  (record: id, title, content)
├── controller/
│   └── NoteController.java        (@RestController, @RequestMapping("/notes"), simpan NoteRequest sbg nested record di sini)
```

Test: `src/test/java/com/mastering/claude/notes/NoteControllerTest.java` (satu file, `@SpringBootTest` + `TestRestTemplate`, gak perlu package terpisah `test.controller` — scope kecil).

Gak ada `service/` atau `repository/` layer — storage in-memory langsung jadi field di controller (`ConcurrentHashMap`). Sengaja simpel, sesuai Scope Boundary di spec (bukan proyek produksi).

## 4. Relasi Antar Komponen

- `NotesApplication` — entry point, cuma boot Spring context, gak nyentuh domain logic.
- `NoteController` — satu-satunya komponen domain. Terima HTTP request → validasi (nanti, Task 5) → manipulasi `Map<Long, Note>` in-memory langsung → return `ResponseEntity<Note>` / `ResponseEntity<Collection<Note>>`.
- `Note` — model murni, gak ada behavior, dipakai controller sbg storage value dan response body. Gak ada dependency ke controller (arah dependency satu arah: controller → model).
- `NoteRequest` — DTO transient, cuma buat deserialize POST body, gak disimpan.

Karena storage nempel di controller (bukan repository terpisah), relasi paling flat: `Controller` depends on `Model`, titik. Ini trade-off sengaja — kalau nanti scope naik (DB/Postgres, course lain `courses/rest-api-design/`), baru dipisah jadi `service/` + `repository/` layer.

## Verifikasi

Design-only, gak ada kode dieksekusi. Verifikasi desain ini benar via review manual: cocokkan field/endpoint/package di atas sama Task 1 Step 2 di `module3-mini-project-plan.md` (copy-paste hasil ini ke `playground/mastering-claude/3-mini-project.md` bagian "## 1. Plan Mode"), lanjut Task 2 (TDD implementasi) baru run `mvn test` buat verifikasi nyata.


## 2. Tools & Permission

- Tool call yang meminta approval:
  - ketika membuat plan di ## 1. Plan Mode
- Tool call yang auto-allow:
  - write dan run unit test
- Permission mode saat ini:
  - acceptEdits
- Catatan:
  - ketika permission belum ada di .claude/settings.local.json