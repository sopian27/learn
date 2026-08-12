# Module 3 Mini Project — Implementation Plan

> Catatan penting (beda dari plan software biasa): mini project ini tujuannya kamu **mengalami langsung** kelima mekanisme Claude Code (Plan Mode, Tools/Permission, Hooks, MCP, Background Agent) — bukan cuma dapat kode CRUD jadi. Karena itu plan ini dieksekusi **inline, kamu yang jalankan tiap tool call**, bukan didelegasikan ke subagent otomatis. Saya (mentor) kasih scaffold boilerplate yang membosankan, tapi 5 langkah pemicu mekanisme kamu jalankan sendiri, saya pandu.

**Goal:** Note CRUD API kecil di Spring Boot, dibangun sambil memicu & mencatat bukti kelima mekanisme Module 3.

**Architecture:** Single-module Maven Spring Boot app, in-memory storage (`ConcurrentHashMap`), 1 controller + 1 model, no DB/Docker.

**Tech Stack:** Java 25, Spring Boot 3.x (Web starter only), JUnit 5, Maven.

## Global Constraints

- Scope: 1 resource (`Note`), 4 endpoint (create/get/list/delete) — dari spec `courses/mastering-claude/module3-mini-project.md`
- No Postgres/Kafka/Docker/K8s — di luar scope mini project ini
- Semua bukti 5 mekanisme dicatat di `playground/mastering-claude/3-mini-project.md`

---

### Task 0: Scaffold project (boilerplate, saya siapkan)

**Files:**
- Create: `playground/mastering-claude/module3-project/pom.xml`
- Create: `playground/mastering-claude/module3-project/src/main/java/com/mastering/claude/notes/NotesApplication.java`
- Create: `playground/mastering-claude/module3-project/src/main/resources/application.properties`

- [ ] **Step 1:** Saya generate pom.xml (Spring Boot 3.3.x parent, `spring-boot-starter-web`, `spring-boot-starter-test`, Java 25 source/target) + `NotesApplication.java` (`@SpringBootApplication` + `main`) + `application.properties` kosong (default port 8080).
- [ ] **Step 2:** Kamu run `mvn spring-boot:run` (atau `./mvnw` kalau saya generate wrapper) — verifikasi app nyala tanpa error, lalu stop (Ctrl+C).
- [ ] **Step 3:** Commit.

```bash
git add playground/mastering-claude/module3-project/
git commit -m "chore(module3-project): scaffold Spring Boot skeleton"
```

---

### Task 1: Plan Mode — desain fitur Note (mekanisme 3.3)

**Interfaces:**
- Produces: keputusan desain yang dipakai Task 2-4 — nama field `Note` (`id: Long`, `title: String`, `content: String`), 4 endpoint path+method, package layout (`notes.model`, `notes.controller`)

- [ ] **Step 1:** Kamu masuk **Plan Mode** di sesi Claude Code (bukan saya yang trigger — kamu yang switch mode). Minta desain: model `Note`, kontrak 4 endpoint (`POST /notes`, `GET /notes/{id}`, `GET /notes`, `DELETE /notes/{id}`), struktur package.
- [ ] **Step 2:** Setelah plan keluar, **copy-paste hasil plan mode apa adanya** ke `playground/mastering-claude/3-mini-project.md` bagian "## 1. Plan Mode" — ini bukti wajib, jangan diringkas.
- [ ] **Step 3:** Exit plan mode, lanjut approve eksekusi.
- [ ] **Step 4:** Commit log.

```bash
git add playground/mastering-claude/3-mini-project.md
git commit -m "docs(module3-project): capture plan mode output"
```

---

### Task 2: Tools & Permission — model + controller pertama (mekanisme 3.1)

**Files:**
- Create: `playground/mastering-claude/module3-project/src/main/java/com/mastering/claude/notes/model/Note.java`
- Create: `playground/mastering-claude/module3-project/src/main/java/com/mastering/claude/notes/controller/NoteController.java`
- Test: `playground/mastering-claude/module3-project/src/test/java/com/mastering/claude/notes/NoteControllerTest.java`

**Interfaces:**
- Consumes: kontrak dari Task 1 (field `Note`, path endpoint)
- Produces: `Note(Long id, String title, String content)` (record atau POJO — pilih record, lebih ringkas), `NoteController` dengan method `create`, `getById`, `list`, `delete`

- [ ] **Step 1: Tulis failing test dulu** (TDD) — test create + get:

```java
package com.mastering.claude.notes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteControllerTest {

    @Autowired
    private TestRestTemplate rest;

    @LocalServerPort
    private int port;

    @Test
    void createThenGetReturnsNote() {
        var payload = new NoteRequest("Judul", "Isi catatan");

        ResponseEntity<Note> created = rest.postForEntity(url("/notes"), payload, Note.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(created.getBody()).isNotNull();
        Long id = created.getBody().id();

        ResponseEntity<Note> fetched = rest.getForEntity(url("/notes/" + id), Note.class);
        assertThat(fetched.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(fetched.getBody().title()).isEqualTo("Judul");
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    record NoteRequest(String title, String content) {}
}
```

- [ ] **Step 2:** Run test, verifikasi FAIL (`NoteController`/`Note` belum ada).

Run: `mvn test -Dtest=NoteControllerTest`
Expected: FAIL (compile error — class not found)

- [ ] **Step 3: Tulis `Note` model:**

```java
package com.mastering.claude.notes.model;

public record Note(Long id, String title, String content) {}
```

- [ ] **Step 4: Tulis `NoteController` minimal (create + get saja dulu):**

```java
package com.mastering.claude.notes.controller;

import com.mastering.claude.notes.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final Map<Long, Note> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    record NoteRequest(String title, String content) {}

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody NoteRequest request) {
        long id = sequence.incrementAndGet();
        Note note = new Note(id, request.title(), request.content());
        store.put(id, note);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id) {
        Note note = store.get(id);
        return note == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(note);
    }
}
```

- [ ] **Step 5:** Run test, verifikasi PASS.

Run: `mvn test -Dtest=NoteControllerTest`
Expected: PASS

- [ ] **Step 6 — bukti mekanisme (WAJIB, ini intinya):** Sebelum lanjut, buka `playground/mastering-claude/3-mini-project.md` bagian "## 2. Tools & Permission" — catat: tool call apa yang minta approval selama Step 1-5 (`Write` file baru? `Bash mvn test`?), mana yang auto-allow, mana yang prompt, dan permission mode apa yang aktif saat itu.
- [ ] **Step 7:** Commit.

```bash
git add playground/mastering-claude/module3-project/ playground/mastering-claude/3-mini-project.md
git commit -m "feat(module3-project): add Note create/get with TDD"
```

---

### Task 3: Lengkapi list + delete (lanjutan TDD, tanpa mekanisme baru)

**Files:**
- Modify: `playground/mastering-claude/module3-project/src/main/java/com/mastering/claude/notes/controller/NoteController.java`
- Modify: `playground/mastering-claude/module3-project/src/test/java/com/mastering/claude/notes/NoteControllerTest.java`

**Interfaces:**
- Consumes: `NoteController` dari Task 2, `store` field
- Produces: `GET /notes` (list), `DELETE /notes/{id}` (delete)

- [ ] **Step 1: Tambah failing test** (append ke `NoteControllerTest`):

```java
    @Test
    void listThenDeleteRemovesNote() {
        var payload = new NoteRequest("A", "B");
        Long id = rest.postForEntity(url("/notes"), payload, Note.class).getBody().id();

        ResponseEntity<Note[]> listed = rest.getForEntity(url("/notes"), Note[].class);
        assertThat(listed.getBody()).isNotEmpty();

        rest.delete(url("/notes/" + id));
        ResponseEntity<Note> afterDelete = rest.getForEntity(url("/notes/" + id), Note.class);
        assertThat(afterDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
```

- [ ] **Step 2:** Run test, verifikasi FAIL (endpoint belum ada).

Run: `mvn test -Dtest=NoteControllerTest`
Expected: FAIL

- [ ] **Step 3: Tambah method di `NoteController`:**

```java
    @GetMapping
    public ResponseEntity<java.util.Collection<Note>> list() {
        return ResponseEntity.ok(store.values());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        store.remove(id);
        return ResponseEntity.noContent().build();
    }
```

- [ ] **Step 4:** Run test, verifikasi PASS.

Run: `mvn test -Dtest=NoteControllerTest`
Expected: PASS (semua 2 test)

- [ ] **Step 5:** Commit.

```bash
git add playground/mastering-claude/module3-project/
git commit -m "feat(module3-project): add Note list/delete with TDD"
```

---

### Task 4: Hooks — auto-run test tiap Edit/Write (mekanisme 3.2)

**Files:**
- Create/Modify: `.claude/settings.json` (root repo atau scoped ke `module3-project/` — pilih root untuk simpel)

- [ ] **Step 1:** Kamu tambah PostToolUse hook di `.claude/settings.json` yang jalankan `mvn -f playground/mastering-claude/module3-project/pom.xml test -q` tiap kali tool `Edit`/`Write` menyentuh path `module3-project/src/**`. (Pakai skill `update-config` kalau butuh bantuan syntax hook — jangan saya tulis manual di sini, itu bagian latihan kamu dari lesson 3.2.)
- [ ] **Step 2:** Trigger hook nyata — edit satu file kecil di `module3-project/src` (misal tambah komentar atau ubah 1 line), lihat hook jalan otomatis.
- [ ] **Step 3 — bukti mekanisme:** Copy config hook + log output run yang ke-trigger ke `playground/mastering-claude/3-mini-project.md` bagian "## 3. Hooks".
- [ ] **Step 4:** Commit.

```bash
git add .claude/settings.json playground/mastering-claude/3-mini-project.md
git commit -m "feat(module3-project): add post-edit test hook"
```

---

### Task 5: MCP context7 — cek 1 keputusan implementasi (mekanisme 3.4)

**Files:**
- Modify: `playground/mastering-claude/module3-project/src/main/java/com/mastering/claude/notes/controller/NoteController.java` (tambah validasi)
- Modify: `playground/mastering-claude/module3-project/src/main/java/com/mastering/claude/notes/model/Note.java` atau `NoteRequest` (kalau butuh anotasi)

- [ ] **Step 1:** Kamu query MCP `context7` untuk cek cara idiomatis validasi request body di Spring Boot terkini (`@Valid` + `@NotBlank` dari `spring-boot-starter-validation`) — pastikan versi/API yang direkomendasikan cocok Spring Boot 3.x.
- [ ] **Step 2:** Terapkan hasilnya: tambah dependency `spring-boot-starter-validation` di `pom.xml`, tambah `@NotBlank` di `NoteRequest.title()`, tambah `@Valid` di parameter `create()`.
- [ ] **Step 3:** Tulis 1 test tambahan: POST tanpa title → expect `400 Bad Request`. Run, pastikan PASS.
- [ ] **Step 4 — bukti mekanisme:** Catat query context7 yang dipakai + potongan hasil yang menentukan kode di atas, ke `playground/mastering-claude/3-mini-project.md` bagian "## 4. MCP Servers".
- [ ] **Step 5:** Commit.

```bash
git add playground/mastering-claude/module3-project/ playground/mastering-claude/3-mini-project.md
git commit -m "feat(module3-project): validate Note title via context7-checked pattern"
```

---

### Task 6: Background Agent — unit test tambahan paralel (mekanisme 3.5)

- [ ] **Step 1:** Kamu dispatch **Agent tool** dengan `run_in_background: true`, task: "tulis unit test JUnit tambahan untuk edge case Note API (id tidak ditemukan, list kosong, delete id yang tidak ada) di `NoteControllerTest.java`, jangan ubah kode production." — kamu yang tulis prompt-nya sendiri (bagian dari latihan 3.5).
- [ ] **Step 2:** Sambil nunggu, lanjut kerja lain di sesi utama (misal: tulis draft "## 5. Background Agent" section duluan tanpa isi hasil).
- [ ] **Step 3:** Tunggu notifikasi selesai — **jangan tulis/tebak hasil sebelum notifikasi datang** (ini prinsip inti 3.5, jadi bagian penilaian mini project).
- [ ] **Step 4:** Setelah notifikasi datang, review diff yang dihasilkan agent, jalankan test, pastikan PASS.

Run: `mvn test`
Expected: PASS (semua test termasuk yang baru dari agent)

- [ ] **Step 5 — bukti mekanisme:** Catat prompt dispatch + ringkasan hasil agent ke bagian "## 5. Background Agent".
- [ ] **Step 6:** Commit.

```bash
git add playground/mastering-claude/module3-project/ playground/mastering-claude/3-mini-project.md
git commit -m "test(module3-project): add edge case tests via background agent"
```

---

### Task 7: Refleksi akhir + wrap-up

**Files:**
- Modify: `playground/mastering-claude/3-mini-project.md`
- Modify: `courses/mastering-claude/roadmap.md`
- Modify: `progress/progress.md`

- [ ] **Step 1:** Tulis "## Refleksi" di `3-mini-project.md` — bagaimana kelima mekanisme saling nyambung jadi satu workflow agentic (jembatan ke preview "agentic engineering" Module 8).
- [ ] **Step 2:** Update `roadmap.md` Module 3 status → "Selesai (tanggal)".
- [ ] **Step 3:** Update `progress.md` Module Completion 3/8, Current Module → Module 4.
- [ ] **Step 4:** Commit.

```bash
git add playground/mastering-claude/3-mini-project.md courses/mastering-claude/roadmap.md progress/progress.md
git commit -m "docs(module3-project): close out mini project with reflection"
```
