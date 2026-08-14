# Lazy Context Loading — Architecture Design

Status: Approved by user 2026-08-14, pending implementation (see "Rollout Plan").

## Problem

`d:\learn` is a growing AI Learning OS with 40+ registered domains. Every domain registration appends a full descriptive paragraph (backstory, calibration notes, house rules summary) to `ai-los/ACTIVE_DOMAIN.md`, which `CLAUDE.md`'s Session Start section forces to be read in full every session, regardless of which domain is actually being discussed.

Measured today:

- `ai-los/ACTIVE_DOMAIN.md`: 52.8KB (~13k tokens), forced every session.
- `domains/README.md`: 22.5KB — duplicates the exact same per-domain paragraphs as `ACTIVE_DOMAIN.md`. Not force-read at session start, but a second copy of the same bloat.
- Much of that paragraph text is a *third* copy of content that already lives properly in each domain's own `domains/<name>/DOMAIN.md` (Scope section).
- `ai-los/ACTIVE_MODE.md` (3.5KB) and `ai-los/CORE_LOS.md` (4.5KB) are also forced every session but stay flat-sized — not the problem.
- `standards/*.md` (15,400 lines total) and the per-mode files (`LEARNING_MODE.md`, `MENTOR_MODE.md`, etc., 597 lines) are NOT force-read — `ACTIVE_MODE.md` never links out to them. Already effectively on-demand (or unused); not in scope here.

Net effect: session-start cost grows unboundedly with every new domain registered, most of it duplicate content never needed unless that specific domain is the topic of conversation.

## Decisions Locked In

1. **Non-destructive.** No domain history is deleted. Content moves from the shared registry files into each domain's own `DOMAIN.md`, where it's read only on-demand.
2. **Single source of truth for the registry.** `ai-los/ACTIVE_DOMAIN.md` becomes the one authoritative list of registered domains. `domains/README.md` stops duplicating it and points to it instead.
3. **Index vs. detail split.** The registry (`ACTIVE_DOMAIN.md`) holds only enough per domain to route a request: name, path, status, course path. Anything narrative (why it was registered, extend-vs-new-domain reasoning, calibration quotes) lives in that domain's own `DOMAIN.md`, loaded only when the domain becomes the active topic.
4. **`standards/` and per-mode files are out of scope.** They are already effectively on-demand; only wording in `CLAUDE.md`'s Repository Priority section is tightened so it can't be misread as "load everything."

## Architecture: Index / Detail Split

```
FORCED EVERY SESSION (flat-sized, does not grow with domain count)
  ai-los/ACTIVE_MODE.md         → pedagogical mode state (unchanged, already flat)
  ai-los/ACTIVE_DOMAIN.md       → SLIMMED: one line per domain (name, path, status, course),
                                    no paragraphs. Still names which domain(s) are active.
  ai-los/CORE_LOS.md            → domain-agnostic learning engine (unchanged, already flat)
  domains/<active>/DOMAIN.md    → only the domain(s) actually marked active in ACTIVE_DOMAIN.md

ON-DEMAND (read only when that domain becomes the conversation's topic)
  domains/<name>/DOMAIN.md      → full Scope/Trusted Sources/etc., now including a new
                                    "Registration History" section carrying the provenance
                                    text moved out of ACTIVE_DOMAIN.md / domains/README.md
  progress/roadmap.md           → read when relevant to the current course/decision,
                                    not implied every turn
  standards/*.md                → already on-demand; wording clarified, not restructured
```

## File Edits

| File | Edit |
|---|---|
| `templates/DOMAIN_TEMPLATE.md` | Add new optional section: `## Registration History` — date approved, original request quote, extend-vs-new-domain reasoning if applicable. Placed after `Domain Goal`. |
| `domains/<name>/DOMAIN.md` (all 40+) | Append `## Registration History` populated from the paragraph currently duplicated in `ACTIVE_DOMAIN.md`/`domains/README.md`, minus whatever is already covered by that file's own `Scope` section (no re-duplication). Mechanical, one file at a time. |
| `ai-los/ACTIVE_DOMAIN.md` | Rewrite "Registered Domains" section: each entry becomes one line — `- <name> — domains/<name>/DOMAIN.md — Status: Active/Paused — Course: courses/<name>/roadmap.md (or "none yet")`. "Registering a New Domain" workflow updated: new registrations append one index line here + write the full paragraph into the new domain's own `DOMAIN.md` under `Registration History`, never back into this file. |
| `domains/README.md` | Replace "Domains Currently Registered" section body with a one-line pointer: "See `ai-los/ACTIVE_DOMAIN.md` for the live registry — this file is the single source of truth, not duplicated here." |
| `CLAUDE.md` | Reword "Repository Priority" section header/intro so it reads as precedence-on-conflict ("if multiple documents overlap, resolve using this order") rather than a load-order checklist — no behavior change, ambiguity removal only. |
| `ai-los/CORE_LOS.md` | Reword the Adaptive Learning line: "consult `progress/roadmap.md` when relevant to the current course or decision" instead of implying it's read every turn. |

No file is deleted. No folder is restructured. `standards/`, `templates/*` (besides the one additive field), `courses/`, `portofolio/`, per-mode files — untouched.

## Migration Mechanics

For each of the 40+ domains currently in `ai-los/ACTIVE_DOMAIN.md`:

1. Take that domain's existing paragraph (from `ACTIVE_DOMAIN.md`, cross-checked against the near-identical copy in `domains/README.md`).
2. Diff it against the domain's own `DOMAIN.md` Scope section — keep only what Scope doesn't already say (registration date, original request quote, extend-vs-new-domain decision, "diperluas" history).
3. Append that trimmed remainder as `## Registration History` in `domains/<name>/DOMAIN.md`.
4. Replace the domain's entry in `ACTIVE_DOMAIN.md` with the one-line index form.
5. Delete the domain's paragraph from `domains/README.md` once all entries are migrated (single pass at the end, since the whole "Domains Currently Registered" section is replaced by a pointer).

This is repetitive, mechanical, and independent per domain — suited to batching in the implementation plan, not something requiring per-domain design decisions.

## Edge Cases

- **Currently active domains** (`software-engineering`, `character-development`): their `DOMAIN.md` is read every session regardless of this change (they're the active topic) — migration doesn't reduce their per-session cost, only removes the duplicate paragraph sitting in the registry files.
- **Domains with no course roadmap yet** (e.g. `conspiracy-theories` at registration time): index line still needs a "no course yet" marker instead of a course path — keep the existing convention already used in `domains/README.md`.
- **`life-mastery` orchestrator domain**: its paragraph is unusually long because it enumerates cross-domain checkpoints. Same treatment — Scope section in its own `DOMAIN.md` should already carry this; only trim what's provenance-only.
- **Future domain registration workflow**: must be updated in the same change, or the next registration silently reintroduces the bloat pattern by appending a full paragraph back into `ACTIVE_DOMAIN.md`.

## Rollout Plan

1. **Phase 1 — Contract changes (small, low-risk)**: add `Registration History` to `templates/DOMAIN_TEMPLATE.md`; reword `CLAUDE.md` Repository Priority and `ai-los/CORE_LOS.md` Adaptive Learning lines; update `ai-los/ACTIVE_DOMAIN.md`'s "Registering a New Domain" workflow instructions. Validate: registering a new test domain follows the new (index-line + DOMAIN.md history) pattern correctly.
2. **Phase 2 — Migration (mechanical, batchable)**: for each of the 40+ existing domains, move its paragraph into its own `DOMAIN.md` as `Registration History`, replace its `ACTIVE_DOMAIN.md` entry with the one-line index form. Batch in groups (e.g. 5-10 domains per pass) to keep each step reviewable.
3. **Phase 3 — Dedupe `domains/README.md`**: once all entries are migrated, replace its "Domains Currently Registered" section with the pointer to `ACTIVE_DOMAIN.md`.
4. **Phase 4 — Verification**: confirm `ai-los/ACTIVE_DOMAIN.md` byte size dropped from ~52.8KB to low single-digit KB; confirm `domains/README.md` dropped similarly; spot-check 3-4 migrated domains to confirm no provenance detail was lost (compare old paragraph vs. new `Registration History` + existing `Scope`).

## Out of Scope (explicitly deferred, not forgotten)

- Restructuring `standards/`, `courses/`, `progress/`, or `portofolio/` — not part of the measured bloat problem.
- Wiring the currently-unused per-mode files (`LEARNING_MODE.md`, `MENTOR_MODE.md`, etc.) into `ACTIVE_MODE.md` — separate concern, not requested.
- Any change to how `progress/roadmap.md` itself is structured — only the *instruction* about when to read it is reworded.
- Tooling/automation for future domain registration (e.g. a script) — the workflow stays manual, per existing repo convention.
