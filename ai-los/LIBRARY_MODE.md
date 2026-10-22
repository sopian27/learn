# Library Mode

When this mode is active, you become a library: you write reading material only. No exercises, no grading, no waiting for me.

This mode changes the OUTPUT FORMAT only. It does not change which course or which module comes next — that always follows the existing roadmap.

---

# Trigger

* Skill `/library <nama-course> [module N]`
* Or the phrase: "Switch to LIBRARY_MODE" / "mode perpustakaan"

Library mode is a temporary overlay. LEARNING_MODE stays the Primary Mode in `ai-los/ACTIVE_MODE.md`. After the module is written, return to the previous modes.

---

# Source of Truth

Follow the roadmap. Never invent topics.

1. Course list and status: `progress/roadmap.md` (Course Aktif and Paused both allowed).
2. Curriculum: `courses/<slug>/roadmap.md`. Read ONLY the heading block of the target module (Grep `^## Module` for the line numbers, then Read with offset/limit). Never read the whole roadmap.
3. Module titles, lesson numbers, and lesson order come from that block, verbatim.

Library mode creates no new course and no new roadmap, so the Course Creation Discipline guard does not apply.

---

# Scope: ONE module per request

Token budget rule. Never generate more than one module per request, even if the course has 10.

Pick the target module in this order:

1. User names a module ("module 3", "modul 2") → that module.
2. User names none → the highest module number already listed in `progress/library.md` for that course, plus 1.
3. Course has no entry in `progress/library.md` → Module 1.
4. Computed module is beyond the last module in the roadmap → say the course is fully covered, generate nothing.

If the target module's library file already exists in the vault, skip it. Do not overwrite and do not regenerate; say where it is.

One module file contains ALL lessons of that module (as sections), in roadmap order.

---

# Reading Format

Target: about 15 minutes of reading, roughly 2,500 words max. Cut depth before cutting the applied section.

Per module file:

1. **Peta modul**: the lessons covered (copied from roadmap) and one paragraph on why this module matters for a software engineer.
2. **Per lesson**: Why, How, Trade-offs / Alternatives. A text diagram or table where it clarifies. One real-world example (companies such as Netflix, Uber, Stripe, GitHub, Gojek where fitting).
3. **Kesalahan umum**: short list.
4. **Cara pakai di kerjaan** (mandatory, never dropped):
   * Kapan dipakai
   * Kapan dihindari
   * Checklist untuk PR / design review / meeting (3-6 items)
5. **Satu hal yang bisa dicoba minggu ini**: exactly one line, clearly optional.
6. **Ringkasan**: 3-5 bullets.

Language and tone: Bahasa Indonesia, mentor-friend tone, simple words, deep content. Technical terms stay in English. Java 25 / Spring Boot examples when the course is technical.

---

# What Is Skipped

Exercises, Coding Exercise, Mini Challenge, Quiz, Code Review of my work, Interview Questions, Applied Practice, Reasoned-Choice blocks, Assessment, Mini Project. Do not mention them as "to do later".

Do not ask me to submit anything. Do not wait for me.

---

# Persistence

Content goes to the Obsidian vault, not chat (see `ai-los/OBSIDIAN_SYNC.md`).

* File: `Library/<Nama Course>/Module N - <Judul Module>.md` (top-level `Library/` folder in the vault, one subfolder per course)
* The separate `Library/` root keeps these files apart from `Courses/`, so a later LEARNING_MODE run of the same module is not blocked by the "file exists, skip" rule.
* Before writing: check the file does not exist.
* Chat reply: link `[[...]]` + 2-3 line summary. Do not paste the content.

---

# Progress Tracking

Track in `progress/library.md` (one row per module, status `Dibuat` or `Dibaca`).

* After writing a module: add the row with status `Dibuat` and the date.
* When I say I read it: change to `Dibaca` with the date. Do not assume I read it.
* NEVER edit lesson/module status in `courses/<slug>/roadmap.md` or `progress/roadmap.md`. In the roadmap, "Selesai" means exercises and assessment passed. Reading is not that, and counting it would inflate module completion.
* Do not touch `progress/progress.md`, `notes.md`, or the interview files from this mode.
* Do not change a course from Paused to Active.

---

# Success Criteria

A library module is done only if:

The target module followed the roadmap order.

The file exists in the vault under `Library/<Nama Course>/`.

`progress/library.md` has the row.

"Cara pakai di kerjaan" is present and concrete.
