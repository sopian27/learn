# Lazy Context Loading Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Shrink `ai-los/ACTIVE_DOMAIN.md` from 52.8KB (growing forever) to a flat-sized index, by moving each domain's descriptive paragraph into that domain's own `DOMAIN.md` (read only on-demand), and deduplicating the identical copy in `domains/README.md`.

**Architecture:** Index/detail split. `ACTIVE_DOMAIN.md` keeps one line per domain (name, path, status, course). Each domain's narrative/provenance text moves to a new `## Registration History` section in its own `domains/<name>/DOMAIN.md`, loaded only when that domain is the active topic. `domains/README.md` stops duplicating the registry and points at `ACTIVE_DOMAIN.md` instead.

**Tech Stack:** Plain markdown files, no code, no build/test tooling. This repo has no test runner — "testing" a task means reading the file back and confirming the expected content/structure is present, using Grep, per the convention already established in `ai-los/PLAN_UNIVERSAL_LOS.md`.

**Spec:** `ai-los/ARCHITECTURE_LAZY_CONTEXT.md`

## Global Constraints

- Non-destructive: no domain history is deleted, only moved. Every paragraph currently in `ACTIVE_DOMAIN.md`/`domains/README.md` must end up either in a domain's `Scope` section (already there) or its new `Registration History` section (moved there) before its old copy is removed.
- Migration only touches the 44 domains currently listed in `ai-los/ACTIVE_DOMAIN.md`'s "Registered Domains" section. 8 domain folders exist on disk (`psychology`, `entrepreneurship`, `driving`, `investing-stocks`, `public-speaking`, `masculine-development`, `nuclear-technology`, `rocket-technology`) but were never added to the registry — out of scope here, do not touch them or register them as a side effect.
- Within any task that edits `ai-los/ACTIVE_DOMAIN.md` more than once, always re-locate a domain's current line via `Grep "^- \`<domain>\`" ai-los/ACTIVE_DOMAIN.md` immediately before editing it — do not reuse a line number read earlier in the same task, since prior edits in the same task shift subsequent line numbers.
- Registration History format (established in Task 5, reused verbatim in Tasks 6-9): a new `## Registration History` heading appended after the domain's existing `## Domain Goal` section (or at end of file if no `Domain Goal` section exists), containing only the parts of the old `ACTIVE_DOMAIN.md` paragraph NOT already covered by that file's own `## Scope` section — typically: registration date, the user's original request quote, and any "diperluas"/extend-vs-new-domain reasoning.
- Index line format (established in Task 5, reused verbatim in Tasks 6-9): `- \`<name>\` — domains/<name>/DOMAIN.md — Status: Active|Paused — Course: courses/<name>/roadmap.md` (or `Course: none yet` if no roadmap file exists).
- Course-module build status (e.g. "Module 7/8/9 still Draft, pending approval") is course-progress data, not domain-registration provenance — it belongs in `courses/<name>/roadmap.md` (where it's already tracked in more detail), not in `Registration History`. If an old `ACTIVE_DOMAIN.md` entry mentions module/lesson status, that's fine to drop from the migration entirely; it isn't data loss since the index line's `Course:` pointer leads there. (Ruling from Task 5 review — see ledger.)

---

## File Structure

```
templates/DOMAIN_TEMPLATE.md          — MODIFY: add optional "Registration History" field
ai-los/ACTIVE_DOMAIN.md               — MODIFY: workflow section (Task 4), then 44 entries slimmed (Tasks 5-9)
ai-los/CORE_LOS.md                    — MODIFY: reword Adaptive Learning line (Task 3)
CLAUDE.md                             — MODIFY: reword Repository Priority intro (Task 2)
domains/<name>/DOMAIN.md  (44 files)  — MODIFY: append Registration History section (Tasks 5-9)
domains/README.md                     — MODIFY: replace registry list with pointer (Task 10)
```

---

### Task 1: Add Registration History field to the domain template

**Files:**
- Modify: `templates/DOMAIN_TEMPLATE.md`

**Interfaces:**
- Produces: the `## Registration History` section heading text that Tasks 5-9 replicate in each `domains/<name>/DOMAIN.md`.

- [ ] **Step 1: Add the new section**

Open `templates/DOMAIN_TEMPLATE.md` and insert this new section immediately after the existing `## Domain Goal (optional)` section (currently the last section in the file):

```markdown

## Registration History (optional)

When this domain was registered/approved, the original request that prompted it (quote if useful), and — if this domain was extended later rather than a new domain being created — the reasoning for extending vs. splitting. This is provenance, not scope: if something belongs in `## Scope` instead, put it there and don't duplicate it here.
```

- [ ] **Step 2: Verify**

Run: `Grep "## Registration History" templates/DOMAIN_TEMPLATE.md`
Expected: one match.

- [ ] **Step 3: Commit**

```bash
git add templates/DOMAIN_TEMPLATE.md
git commit -m "docs(ai-los): add optional Registration History field to domain template"
```

---

### Task 2: Reword CLAUDE.md Repository Priority section

**Files:**
- Modify: `CLAUDE.md` (the "Repository Priority" section, currently starting "Always load and follow the repository in this order:")

**Interfaces:**
- None (isolated wording change, no other task depends on the exact new sentence).

- [ ] **Step 1: Reword the section intro**

Find the line: `Always load and follow the repository in this order:`

Replace it with:

```markdown
When multiple documents overlap or conflict, resolve using this precedence order (this is not a checklist to read in full every session — most of these are read on-demand when relevant):
```

- [ ] **Step 2: Verify**

Run: `Grep "precedence order" CLAUDE.md`
Expected: one match.

Run: `Grep "Always load and follow the repository in this order" CLAUDE.md`
Expected: no match (old wording gone).

- [ ] **Step 3: Commit**

```bash
git add CLAUDE.md
git commit -m "docs: clarify Repository Priority as conflict precedence, not a load-order checklist"
```

---

### Task 3: Reword CORE_LOS.md Adaptive Learning line

**Files:**
- Modify: `ai-los/CORE_LOS.md` (the "Adaptive Learning" section)

**Interfaces:**
- None.

- [ ] **Step 1: Reword the line**

Find this sentence in the `# Adaptive Learning` section:

```
Use previous progress (`progress/roadmap.md` and the relevant course's own roadmap) before deciding what I already know.
```

Replace it with:

```
Consult `progress/roadmap.md` and the relevant course's own roadmap when relevant to the current course or decision — not implied to be re-read every turn.
```

- [ ] **Step 2: Verify**

Run: `Grep "not implied to be re-read every turn" ai-los/CORE_LOS.md`
Expected: one match.

- [ ] **Step 3: Commit**

```bash
git add ai-los/CORE_LOS.md
git commit -m "docs(ai-los): clarify progress/roadmap.md is read on-demand, not every turn"
```

---

### Task 4: Update the domain-registration workflow instructions

**Files:**
- Modify: `ai-los/ACTIVE_DOMAIN.md` (the "Registering a New Domain" section, currently steps 1-4 near the end of the file)

**Interfaces:**
- Consumes: the index line format and Registration History format defined in Global Constraints above.
- Produces: the workflow later domain registrations (after this plan ships) must follow — so it must state the index-line + DOMAIN.md-history split explicitly, or the next registration silently reintroduces the bloat.

- [ ] **Step 1: Rewrite the workflow section**

Find the `# Registering a New Domain` section (currently 4 numbered steps). Replace its body with:

```markdown
Whenever I ask to learn a new subject that isn't already registered (e.g. "belajar public speaking dari 0"):

1. Check whether `domains/<name>/DOMAIN.md` already exists.
2. If not, scaffold it from `templates/DOMAIN_TEMPLATE.md`, research trusted sources the same way `ai-los/COURSE_CREATION.md` requires for course roadmaps, and wait for my approval before treating it as final.
3. Once approved, add ONE line to the "Registered Domains" list below, in this exact format: `- \`<name>\` — domains/<name>/DOMAIN.md — Status: Active|Paused — Course: courses/<name>/roadmap.md (or "none yet")`. Do not add a paragraph here.
4. Write the registration's provenance (date, original request, why this domain vs. an existing one) into the new `domains/<name>/DOMAIN.md`'s own `## Registration History` section — never back into this file.
5. Build the course as usual under `courses/<name>/roadmap.md`, following `templates/COURSE_TEMPLATE.md` — the course structure itself never changes between domains, only the domain pack's Applied Practice / Assessment / Trusted Sources content does.
```

- [ ] **Step 2: Verify**

Run: `Grep "Do not add a paragraph here" ai-los/ACTIVE_DOMAIN.md`
Expected: one match.

- [ ] **Step 3: Commit**

```bash
git add ai-los/ACTIVE_DOMAIN.md
git commit -m "docs(ai-los): update domain-registration workflow to index-line + DOMAIN.md history split"
```

---

### Task 5: Migrate batch 1 — software-engineering, character-development, personal-finance, n8n-automation, communication-skills, critical-thinking, world-history, guitar, self-love

**Files:**
- Modify: `ai-los/ACTIVE_DOMAIN.md` (the 9 entries for these domains, inside "Registered Domains")
- Modify: `domains/software-engineering/DOMAIN.md`, `domains/character-development/DOMAIN.md`, `domains/personal-finance/DOMAIN.md`, `domains/n8n-automation/DOMAIN.md`, `domains/communication-skills/DOMAIN.md`, `domains/critical-thinking/DOMAIN.md`, `domains/world-history/DOMAIN.md`, `domains/guitar/DOMAIN.md`, `domains/self-love/DOMAIN.md`

**Interfaces:**
- Consumes: Registration History section format and index line format from Global Constraints (this task is where that format gets its first, fully-worked example — Tasks 6-9 replicate it exactly).

This task establishes the pattern. Work one domain at a time, in this order, repeating steps 1-4 for each:

- [ ] **Step 1: Locate and read the domain's current entry**

Run: `Grep "^- \`<domain>\`" ai-los/ACTIVE_DOMAIN.md` to get the current line number, then Read that single line.

- [ ] **Step 2: Worked example — `character-development`**

Its current entry (as of this plan) is the paragraph starting `` - `character-development` — registered 2026-08-07, roadmap approved, **diperluas 2026-08-13**... `` in `ai-los/ACTIVE_DOMAIN.md`.

Cross-check against `domains/character-development/DOMAIN.md`'s `## Scope` section (already read in full during spec research — it already states the "diperluas 2026-08-13" expansion and its content). What's NOT already in Scope: the registration date, the exact user request quote that prompted the expansion, and the fact it was confirmed via AskUserQuestion rather than assumed.

Append to `domains/character-development/DOMAIN.md`, after its `## Domain Goal` section:

```markdown

## Registration History

Registered 2026-08-07, roadmap approved. Extended 2026-08-13 at the user's request ("buat course melatih kerendahan hati dan ketenangan batin: atasi sombong, haus validasi, gila hormat, mencari perhatian, self-centeredness, dan rasa seolah semua orang memperhatikan saya"), confirmed via AskUserQuestion to extend this domain rather than create a separate one — avoiding duplication with the ego/sombong material already covered in depth in Modules 1-5.
```

Replace its line in `ai-los/ACTIVE_DOMAIN.md`'s "Registered Domains" (Active) section with:

```markdown
- `character-development` — domains/character-development/DOMAIN.md — Status: Active — Course: courses/character-development/roadmap.md
```

- [ ] **Step 3: Apply the same process to the remaining 8 domains in this batch**

For each of `software-engineering`, `personal-finance`, `n8n-automation`, `communication-skills`, `critical-thinking`, `world-history`, `guitar`, `self-love`: read its current `ACTIVE_DOMAIN.md` entry (Step 1), read its `domains/<name>/DOMAIN.md` Scope section, append a `## Registration History` section with whatever is provenance-only (not already in Scope — several of these entries, e.g. `software-engineering`, have little or no provenance beyond the pointer note and may need only a one-line History, or none if truly nothing is provenance-only), then replace the `ACTIVE_DOMAIN.md` line with the slim index form:

```markdown
- `<name>` — domains/<name>/DOMAIN.md — Status: Active|Paused — Course: courses/<name>/roadmap.md
```

(`software-engineering` and `character-development` are Status: Active per the current "Active" subsection; the other 7 in this batch are Status: Active or Paused matching their current subsection — `personal-finance`, `n8n-automation`, `communication-skills`, `critical-thinking`, `world-history`, `guitar`, `self-love` are all currently under "Paused".)

- [ ] **Step 4: Verify the whole batch**

Run: `Grep "## Registration History" domains/software-engineering/DOMAIN.md domains/character-development/DOMAIN.md domains/personal-finance/DOMAIN.md domains/n8n-automation/DOMAIN.md domains/communication-skills/DOMAIN.md domains/critical-thinking/DOMAIN.md domains/world-history/DOMAIN.md domains/guitar/DOMAIN.md domains/self-love/DOMAIN.md`
Expected: one match per file (9 total) — or fewer if a domain genuinely had no provenance-only content to move (note which ones in the task's completion notes).

Run: `Grep "diperluas|registered 2026|approved 2026" ai-los/ACTIVE_DOMAIN.md`
Expected: no matches among these 9 domains' lines (old paragraph text is gone from this file for this batch — other batches' un-migrated entries will still match until their own task runs).

- [ ] **Step 5: Commit**

```bash
git add ai-los/ACTIVE_DOMAIN.md domains/software-engineering/DOMAIN.md domains/character-development/DOMAIN.md domains/personal-finance/DOMAIN.md domains/n8n-automation/DOMAIN.md domains/communication-skills/DOMAIN.md domains/critical-thinking/DOMAIN.md domains/world-history/DOMAIN.md domains/guitar/DOMAIN.md domains/self-love/DOMAIN.md
git commit -m "docs(ai-los): migrate domain registry batch 1 (9 domains) to index/history split"
```

---

### Task 6: Migrate batch 2 — film-criticism, korean, cryptocurrency, conspiracy-theories, japanese, stoicism, pornography-recovery, english, geospatial-technology

**Files:**
- Modify: `ai-los/ACTIVE_DOMAIN.md` (9 entries)
- Modify: `domains/film-criticism/DOMAIN.md`, `domains/korean/DOMAIN.md`, `domains/cryptocurrency/DOMAIN.md`, `domains/conspiracy-theories/DOMAIN.md`, `domains/japanese/DOMAIN.md`, `domains/stoicism/DOMAIN.md`, `domains/pornography-recovery/DOMAIN.md`, `domains/english/DOMAIN.md`, `domains/geospatial-technology/DOMAIN.md`

**Interfaces:**
- Consumes: exact `## Registration History` and index-line formats established in Task 5, Step 2.

- [ ] **Step 1: Migrate each of the 9 domains in this batch**

For each of `film-criticism`, `korean`, `cryptocurrency`, `conspiracy-theories`, `japanese`, `stoicism`, `pornography-recovery`, `english`, `geospatial-technology`:

1. Run `Grep "^- \`<domain>\`" ai-los/ACTIVE_DOMAIN.md` to find its current entry's line number, then Read that line.
2. Read that domain's `domains/<name>/DOMAIN.md` `## Scope` section.
3. Identify what in the `ACTIVE_DOMAIN.md` line is NOT already stated in Scope — typically registration date, the original request quote, and any extend-vs-new-domain reasoning.
4. Append that remainder to `domains/<name>/DOMAIN.md`, after its `## Domain Goal` section, as:

```markdown

## Registration History

<the provenance-only remainder identified in step 3, written as prose>
```

5. Replace the domain's line in `ai-los/ACTIVE_DOMAIN.md`'s "Registered Domains" section with:

```markdown
- `<name>` — domains/<name>/DOMAIN.md — Status: Paused — Course: courses/<name>/roadmap.md
```

All 9 domains in this batch are currently listed under the "Paused" subsection, so every one gets `Status: Paused`.

- [ ] **Step 2: Verify the batch**

Run: `Grep "## Registration History" domains/film-criticism/DOMAIN.md domains/korean/DOMAIN.md domains/cryptocurrency/DOMAIN.md domains/conspiracy-theories/DOMAIN.md domains/japanese/DOMAIN.md domains/stoicism/DOMAIN.md domains/pornography-recovery/DOMAIN.md domains/english/DOMAIN.md domains/geospatial-technology/DOMAIN.md`
Expected: one match per file.

- [ ] **Step 3: Commit**

```bash
git add ai-los/ACTIVE_DOMAIN.md domains/film-criticism/DOMAIN.md domains/korean/DOMAIN.md domains/cryptocurrency/DOMAIN.md domains/conspiracy-theories/DOMAIN.md domains/japanese/DOMAIN.md domains/stoicism/DOMAIN.md domains/pornography-recovery/DOMAIN.md domains/english/DOMAIN.md domains/geospatial-technology/DOMAIN.md
git commit -m "docs(ai-los): migrate domain registry batch 2 (9 domains) to index/history split"
```

---

### Task 7: Migrate batch 3 — physics-for-software-engineers, relativity, intelligence-studies, content-creation, affiliate-marketing, media-literacy, vocal-coaching, football-tactics, life-mastery

**Files:**
- Modify: `ai-los/ACTIVE_DOMAIN.md` (9 entries)
- Modify: `domains/physics-for-software-engineers/DOMAIN.md`, `domains/relativity/DOMAIN.md`, `domains/intelligence-studies/DOMAIN.md`, `domains/content-creation/DOMAIN.md`, `domains/affiliate-marketing/DOMAIN.md`, `domains/media-literacy/DOMAIN.md`, `domains/vocal-coaching/DOMAIN.md`, `domains/football-tactics/DOMAIN.md`, `domains/life-mastery/DOMAIN.md`

**Interfaces:**
- Consumes: same formats as Task 5/6.

- [ ] **Step 1: Migrate each of the 9 domains in this batch**

For each of `physics-for-software-engineers`, `relativity`, `intelligence-studies`, `content-creation`, `affiliate-marketing`, `media-literacy`, `vocal-coaching`, `football-tactics`, `life-mastery`:

1. Run `Grep "^- \`<domain>\`" ai-los/ACTIVE_DOMAIN.md` to find its current entry's line number, then Read that line.
2. Read that domain's `domains/<name>/DOMAIN.md` `## Scope` section.
3. Identify what in the `ACTIVE_DOMAIN.md` line is NOT already stated in Scope — typically registration date, the original request quote, and any extend-vs-new-domain reasoning. For `relativity` and `intelligence-studies`, this includes the fact their registry line was itself added late ("corrected 2026-08-09") — keep that correction fact. For `life-mastery` (orchestrator domain, unusually long entry), most of the text describes what it teaches directly — that belongs in Scope; verify it's already there, then keep only the registration date/request/course-count facts as History.
4. Append that remainder to `domains/<name>/DOMAIN.md`, after its `## Domain Goal` section, as:

```markdown

## Registration History

<the provenance-only remainder identified in step 3, written as prose>
```

5. Replace the domain's line in `ai-los/ACTIVE_DOMAIN.md`'s "Registered Domains" section with:

```markdown
- `<name>` — domains/<name>/DOMAIN.md — Status: Paused — Course: courses/<name>/roadmap.md
```

All 9 domains in this batch are currently listed under the "Paused" subsection, so every one gets `Status: Paused`.

- [ ] **Step 2: Verify the batch**

Run: `Grep "## Registration History" domains/physics-for-software-engineers/DOMAIN.md domains/relativity/DOMAIN.md domains/intelligence-studies/DOMAIN.md domains/content-creation/DOMAIN.md domains/affiliate-marketing/DOMAIN.md domains/media-literacy/DOMAIN.md domains/vocal-coaching/DOMAIN.md domains/football-tactics/DOMAIN.md domains/life-mastery/DOMAIN.md`
Expected: one match per file.

- [ ] **Step 3: Commit**

```bash
git add ai-los/ACTIVE_DOMAIN.md domains/physics-for-software-engineers/DOMAIN.md domains/relativity/DOMAIN.md domains/intelligence-studies/DOMAIN.md domains/content-creation/DOMAIN.md domains/affiliate-marketing/DOMAIN.md domains/media-literacy/DOMAIN.md domains/vocal-coaching/DOMAIN.md domains/football-tactics/DOMAIN.md domains/life-mastery/DOMAIN.md
git commit -m "docs(ai-los): migrate domain registry batch 3 (9 domains) to index/history split"
```

---

### Task 8: Migrate batch 4 — astronomy-and-life-in-the-universe, learning-science, memory-mastery, research-methods, notulen-mastery, emotional-resilience, storytelling, intuition-training, realistic-manifestation

**Files:**
- Modify: `ai-los/ACTIVE_DOMAIN.md` (9 entries)
- Modify: `domains/astronomy-and-life-in-the-universe/DOMAIN.md`, `domains/learning-science/DOMAIN.md`, `domains/memory-mastery/DOMAIN.md`, `domains/research-methods/DOMAIN.md`, `domains/notulen-mastery/DOMAIN.md`, `domains/emotional-resilience/DOMAIN.md`, `domains/storytelling/DOMAIN.md`, `domains/intuition-training/DOMAIN.md`, `domains/realistic-manifestation/DOMAIN.md`

**Interfaces:**
- Consumes: same formats as Task 5/6/7.

- [ ] **Step 1: Migrate each of the 9 domains in this batch**

For each of `astronomy-and-life-in-the-universe`, `learning-science`, `memory-mastery`, `research-methods`, `notulen-mastery`, `emotional-resilience`, `storytelling`, `intuition-training`, `realistic-manifestation`:

1. Run `Grep "^- \`<domain>\`" ai-los/ACTIVE_DOMAIN.md` to find its current entry's line number, then Read that line.
2. Read that domain's `domains/<name>/DOMAIN.md` `## Scope` section.
3. Identify what in the `ACTIVE_DOMAIN.md` line is NOT already stated in Scope — typically registration date, the original request quote, and any extend-vs-new-domain reasoning. For `storytelling`: its current entry has a formatting quirk — a second domain's descriptive text (`learning-science`'s full paragraph, starting "Sains kognitif terapan...") is appended directly after `storytelling`'s own paragraph without a line break, inside the same list bullet. Split this correctly: `storytelling`'s own paragraph is its provenance-only remainder; the trailing "Sains kognitif terapan..." text is `learning-science`'s Scope content duplicated by the formatting bug — cross-check `domains/learning-science/DOMAIN.md` to confirm it's already there, then discard the duplicate rather than filing it under `storytelling`'s history.
4. Append that remainder to `domains/<name>/DOMAIN.md`, after its `## Domain Goal` section, as:

```markdown

## Registration History

<the provenance-only remainder identified in step 3, written as prose>
```

5. Replace the domain's line in `ai-los/ACTIVE_DOMAIN.md`'s "Registered Domains" section with:

```markdown
- `<name>` — domains/<name>/DOMAIN.md — Status: Paused — Course: courses/<name>/roadmap.md
```

All 9 domains in this batch are currently listed under the "Paused" subsection, so every one gets `Status: Paused`.

- [ ] **Step 2: Verify the batch**

Run: `Grep "## Registration History" domains/astronomy-and-life-in-the-universe/DOMAIN.md domains/learning-science/DOMAIN.md domains/memory-mastery/DOMAIN.md domains/research-methods/DOMAIN.md domains/notulen-mastery/DOMAIN.md domains/emotional-resilience/DOMAIN.md domains/storytelling/DOMAIN.md domains/intuition-training/DOMAIN.md domains/realistic-manifestation/DOMAIN.md`
Expected: one match per file.

- [ ] **Step 3: Commit**

```bash
git add ai-los/ACTIVE_DOMAIN.md domains/astronomy-and-life-in-the-universe/DOMAIN.md domains/learning-science/DOMAIN.md domains/memory-mastery/DOMAIN.md domains/research-methods/DOMAIN.md domains/notulen-mastery/DOMAIN.md domains/emotional-resilience/DOMAIN.md domains/storytelling/DOMAIN.md domains/intuition-training/DOMAIN.md domains/realistic-manifestation/DOMAIN.md
git commit -m "docs(ai-los): migrate domain registry batch 4 (9 domains) to index/history split"
```

---

### Task 9: Migrate batch 5 — chess-mastery, poetry-appreciation, art-appreciation, westeros-history, harry-potter, middle-earth, anti-scam-risk-management, relationships

**Files:**
- Modify: `ai-los/ACTIVE_DOMAIN.md` (8 entries)
- Modify: `domains/chess-mastery/DOMAIN.md`, `domains/poetry-appreciation/DOMAIN.md`, `domains/art-appreciation/DOMAIN.md`, `domains/westeros-history/DOMAIN.md`, `domains/harry-potter/DOMAIN.md`, `domains/middle-earth/DOMAIN.md`, `domains/anti-scam-risk-management/DOMAIN.md`, `domains/relationships/DOMAIN.md`

**Interfaces:**
- Consumes: same formats as Task 5/6/7/8.

- [ ] **Step 1: Migrate each of the 8 domains in this batch**

For each of `chess-mastery`, `poetry-appreciation`, `art-appreciation`, `westeros-history`, `harry-potter`, `middle-earth`, `anti-scam-risk-management`, `relationships`:

1. Run `Grep "^- \`<domain>\`" ai-los/ACTIVE_DOMAIN.md` to find its current entry's line number, then Read that line.
2. Read that domain's `domains/<name>/DOMAIN.md` `## Scope` section.
3. Identify what in the `ACTIVE_DOMAIN.md` line is NOT already stated in Scope — typically registration date, the original request quote, and any extend-vs-new-domain reasoning. For `relationships`: its current entry states its registry line itself was missing and "corrected 2026-08-12" plus extended the same day via AskUserQuestion — both facts are provenance, keep them in its `Registration History`. `chess-mastery`, `harry-potter`, `middle-earth`, and `anti-scam-risk-management` each note an "approved" date but stay `Status: Paused` per the 2-course-active-cap policy — the approval date is a History fact, not a Status change.
4. Append that remainder to `domains/<name>/DOMAIN.md`, after its `## Domain Goal` section, as:

```markdown

## Registration History

<the provenance-only remainder identified in step 3, written as prose>
```

5. Replace the domain's line in `ai-los/ACTIVE_DOMAIN.md`'s "Registered Domains" section with:

```markdown
- `<name>` — domains/<name>/DOMAIN.md — Status: Paused — Course: courses/<name>/roadmap.md
```

All 8 domains in this batch are currently listed under the "Paused" subsection, so every one gets `Status: Paused`.

- [ ] **Step 2: Verify the batch**

Run: `Grep "## Registration History" domains/chess-mastery/DOMAIN.md domains/poetry-appreciation/DOMAIN.md domains/art-appreciation/DOMAIN.md domains/westeros-history/DOMAIN.md domains/harry-potter/DOMAIN.md domains/middle-earth/DOMAIN.md domains/anti-scam-risk-management/DOMAIN.md domains/relationships/DOMAIN.md`
Expected: one match per file.

Run: `Grep "^- \`" ai-los/ACTIVE_DOMAIN.md` (count all entries)
Expected: 44 matches total, every one in the slim `- \`name\` — domains/... — Status: ... — Course: ...` form, none containing a multi-sentence paragraph.

- [ ] **Step 3: Commit**

```bash
git add ai-los/ACTIVE_DOMAIN.md domains/chess-mastery/DOMAIN.md domains/poetry-appreciation/DOMAIN.md domains/art-appreciation/DOMAIN.md domains/westeros-history/DOMAIN.md domains/harry-potter/DOMAIN.md domains/middle-earth/DOMAIN.md domains/anti-scam-risk-management/DOMAIN.md domains/relationships/DOMAIN.md
git commit -m "docs(ai-los): migrate domain registry batch 5 (8 domains) to index/history split"
```

---

### Task 10: Dedupe domains/README.md

**Files:**
- Modify: `domains/README.md` (the "Domains Currently Registered" section)

**Interfaces:**
- Consumes: Task 9 must be complete (all 44 `ACTIVE_DOMAIN.md` entries slimmed) before this task removes the second copy.

- [ ] **Step 1: Replace the section body**

Find the `# Domains Currently Registered` section (currently a bulleted list of all 44 domains with full paragraphs). Replace its entire bulleted list with:

```markdown
See `ai-los/ACTIVE_DOMAIN.md`'s "Registered Domains" section for the live, authoritative list — not duplicated here to avoid drift between two copies.
```

- [ ] **Step 2: Verify**

Run: `Grep "not duplicated here to avoid drift" domains/README.md`
Expected: one match.

Run: `wc -c domains/README.md`
Expected: well under 5KB (was 22.5KB before this task).

- [ ] **Step 3: Commit**

```bash
git add domains/README.md
git commit -m "docs(domains): stop duplicating the domain registry, point to ACTIVE_DOMAIN.md instead"
```

---

### Task 11: Final verification

**Files:**
- None modified — read-only verification.

**Interfaces:**
- Consumes: the completed state of all prior tasks.

- [ ] **Step 1: Confirm size reduction**

Run: `wc -c ai-los/ACTIVE_DOMAIN.md domains/README.md`
Expected: `ai-los/ACTIVE_DOMAIN.md` well under 8KB (was 52.8KB); `domains/README.md` well under 5KB (was 22.5KB).

- [ ] **Step 2: Confirm no orphaned paragraph text remains in the registry files**

Run: `Grep "peer-reviewed|riset|registered 2026|approved 2026" ai-los/ACTIVE_DOMAIN.md domains/README.md`
Expected: no matches (these phrases only occurred inside the old long-form paragraphs, now moved out).

- [ ] **Step 3: Spot-check 3 migrated domains for lost content**

Pick `character-development`, `life-mastery`, and `relationships` (one simple, one complex/orchestrator, one with a "corrected" registry-history quirk). For each, confirm its `domains/<name>/DOMAIN.md` now contains both an intact `## Scope` section and a `## Registration History` section, and that reading both together reconstructs everything the old `ACTIVE_DOMAIN.md` paragraph said.

Run: `Grep "## Scope|## Registration History" domains/character-development/DOMAIN.md domains/life-mastery/DOMAIN.md domains/relationships/DOMAIN.md`
Expected: 2 matches per file (6 total).

- [ ] **Step 4: Confirm the new registration workflow is self-consistent**

Run: `Grep "Do not add a paragraph here" ai-los/ACTIVE_DOMAIN.md` (from Task 4)
Expected: one match — confirms future registrations won't reintroduce the bloat.

No commit for this task — it's verification only. If any check fails, return to the relevant batch task and fix before considering the plan complete.
