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

## 4: Hooks
{
  "hooks": {
    "PostToolUse": [
      {
        "matcher": "Edit|Write",
        "hooks": [
          {
            "type": "command",
            "if": "Edit(playground/mastering-claude/module3-project/src/**)",
            "command": "mvn -f playground/mastering-claude/module3-project/pom.xml test -q",
            "statusMessage": "Running module3-project tests..."
          },
          {
            "type": "command",
            "if": "Write(playground/mastering-claude/module3-project/src/**)",
            "command": "mvn -f playground/mastering-claude/module3-project/pom.xml test -q",
            "statusMessage": "Running module3-project tests..."
          }
        ]
      }
    ]
  }
}

log : Running module3-project tests..

## 4. MCP Servers

- Query 1 (`resolve-library-id`, libraryName "Spring Boot", query "request body validation @Valid @NotBlank Bean Validation") → resolve ke `/websites/spring_io_spring-boot_3_5` (cocok versi parent pom, Spring Boot 3.5.0).
- Query 2 (`query-docs`, libraryId `/websites/spring_io_spring-boot_3_5`, query "validate request body with @Valid and Bean Validation starter dependency") → hasil dominan soal `@ConfigurationProperties` + `@Validated` (bukan target), tapi konfirmasi `ValidationAutoConfiguration` otomatis nyediain `Validator` bean begitu `spring-boot-starter-validation` ada di classpath, dan pola anotasi standar dari `jakarta.validation.constraints` (mis. `@NotNull`, `@NotEmpty`).
- Query 3 (`query-docs`, query "spring-boot-starter-validation dependency for @RequestBody @Valid in REST controller") → hasil nunjukin pola `@RestController` standar (`@RequestMapping`, `@PathVariable`, dll), belum ada contoh literal `@RequestBody @Valid` — context7 gak punya snippet controller-validation persis, tapi cukup buat konfirmasi: (1) starter `spring-boot-starter-validation` yang benar buat auto-config validator, (2) `jakarta.validation.constraints.NotBlank` paket yang benar buat Spring Boot 3.x (bukan `javax.validation`, yang udah deprecated sejak Spring Boot 3 pindah ke Jakarta EE 9+).

**Keputusan kode yang ditentukan hasil query:**

1. `pom.xml` — tambah dependency `spring-boot-starter-validation` (bukan taruh manual `jakarta.validation` API, biar auto-config `ValidationAutoConfiguration` jalan).
2. `NoteController.NoteRequest` — `@NotBlank` dari `jakarta.validation.constraints` di field `title`.
3. `NoteController.create()` — `@Valid` di depan `@RequestBody NoteRequest request`, gak perlu custom exception handler karena Spring udah auto-return `400 Bad Request` (`MethodArgumentNotValidException`) begitu `@Valid` gagal.

**Verifikasi:** test baru `createWithoutTitleReturnsBadRequest` (POST title `null`) → `mvn test` PASS (3 test total), log konfirmasi `MethodArgumentNotValidException` ke-trigger dan direspon `400`.

## 5. Background Agent

### Task

Dispatch `general-purpose` agent, `run_in_background: true`, buat tulis JUnit test tambahan edge case Note API di `NoteControllerTest.java`:

1. GET id yang gak ketemu
2. GET list pas kosong
3. DELETE id yang gak ada

Constraint: gak boleh ubah production code, cuma tambah/ubah test yang perlu, gak boleh karang hasil test.

### Agent Result

File diubah: `NoteControllerTest.java` doang (test-only, prod code gak disentuh — dicek `git status` module3-project cuma munculin file ini).

Agent baca dulu `NoteController.java` sebelum nulis assertion (gak nebak status code):

- `GET /notes/{id}` id gak ada → `404 Not Found`
- `GET /notes` kosong → `200 OK`, body kosong
- `DELETE /notes/{id}` id gak ada → controller gak ada existence check, `store.remove()` unconditional → selalu `204 No Content` (bukan 404)

Test ditambah (3):

1. `getByIdWhenNotFoundReturnsNotFound` — GET id `999999999` → assert `404`
2. `listWhenEmptyReturnsEmptyList` — drain store dulu via DELETE (biar gak kena residual data test lain di shared `ConcurrentHashMap`) → assert `GET /notes` `200` + body kosong
3. `deleteWhenIdNotFoundReturnsNoContent` — DELETE id `999999999` → assert `204` (sesuai behavior nyata, bukan asumsi 404)

Import baru: `org.springframework.http.HttpMethod` (buat `rest.exchange(..., DELETE, ...)`).

`mvn test` → `Tests run: 6, Failures: 0, Errors: 0` — BUILD SUCCESS (3 test lama + 3 baru, semua hijau).

### Review

- Diff: `+30` baris, cuma di `NoteControllerTest.java`, 1 file. Sesuai constraint no-prod-code.
- Konsisten gaya file existing: `TestRestTemplate` + `ResponseEntity`, gak introduce MockMvc.
- Assertion dicocokkan ke behavior nyata controller (baca kode dulu), bukan tebakan — sesuai constraint "jangan mengarang hasil test".
- Temuan sampingan (bukan bug fix, cuma catatan): DELETE id-gak-ada balikin `204`, bukan `404`. Ini sesuai desain awal (§1 endpoint table: "delete idempotent... rekomendasi 204 selalu"), jadi bukan bug — cocok sama keputusan desain, tinggal dikonfirmasi test-nya emang nangkep itu.
- Verifikasi: `mvn test` run nyata, 6/6 pass, bukan klaim tanpa bukti.


## Refleksi
1. Plan Mode — menentukan rencana sebelum implementasi. Claude memahami requirement, memetakan perubahan, dan menunggu approval sebelum melakukan perubahan.

2. Tools & Permission — mengontrol kemampuan Claude untuk melakukan tindakan. Read-only, Edit/Write.

3. Hooks — menjalankan automation berdasarkan event. Dalam mini-project ini, setiap Edit/Write pada source module3-project memicu Maven test secara otomatis melalui PostToolUse.

4. MCP — memperluas kemampuan Claude dengan sumber/tool eksternal. Context7 digunakan untuk mencari dokumentasi Spring Boot terkini sebelum menentukan implementasi validation dengan @Valid dan @NotBlank.

5. Background Agent — memungkinkan pekerjaan yang independen dan panjang didelegasikan secara asynchronous. Agent dapat menulis unit test sementara sesi utama tetap digunakan untuk pekerjaan lain. Hasilnya tetap harus diverifikasi melalui diff dan test.
