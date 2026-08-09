# AI Learning OS — Website Design

**Status:** Approved 2026-08-09
**Author:** Design session with Claude Code

## 1. Problem & Goals

The current learning workflow (Claude creates a course → work happens in VS Code/playground → Claude reviews → repeat) is entirely chat- and copy/paste-driven. The goal is to give this workflow a dedicated interface — a personal "Learning OS" website — **without introducing a database**. The filesystem under `d:\learn` remains the single source of truth; the website is a read/write/render layer on top of it, and VS Code remains the workspace for actually doing assignments.

Non-goals: replacing VS Code, replacing Claude Code as the mentor/reviewer, multi-user support, hosting/deployment, gamification beyond an optional streak.

## 2. Product Architecture

Three components:

- **Website** (`app/`) — local-only web app: a small Node.js/Express backend plus a React frontend. The backend's sole responsibility is reading, writing, and watching files under `d:\learn`. It contains no learning logic, no grading logic, and makes no AI/model calls of any kind.
- **Filesystem** (existing `d:\learn` tree) — unchanged as source of truth. The website adds a small number of new directories/files (Section 3) but does not rename, move, or restructure anything that exists today (`courses/*/roadmap.md`, `standards/`, `ai-los/`, `templates/`, etc.).
- **AI** — the existing Claude Code session. It reads and writes the same files the website reads and writes. Website and AI are decoupled: neither needs the other running to do its own job; they only communicate through files.

The website runs on `localhost` only: single user, no auth, no deployment target.

### Submit → Review loop (the core interaction)

```
1. Learner finishes an assignment in VS Code/playground.
2. Website "Submit for Review" writes submissions/<course>/<date>-<slug>.md.
3. Website shows "Waiting for review" and polls for the matching review file.
4. Learner asks Claude Code (chat) to review it. Claude Code reads the lesson,
   rubric, submission, and learner history, then writes
   reviews/<course>/<date>-<slug>-review.md, and updates
   progress/<course>/skills.yml and progress/progress.md.
5. Website detects the new review file and renders it.
```

The website never scores, never grades, never drafts feedback. All of that stays an explicit act by Claude Code, same as today — just file-mediated instead of copy/paste-mediated.

## 3. File Architecture

Extends, never replaces, the existing structure. Directories/files marked **NEW** don't exist anywhere in the repo today; everything else is an existing convention.

```
courses/<course>/
  roadmap.md                          # unchanged — syllabus/index
  modules/                            # NEW — generated on-demand, module by module,
    01-<slug>/                        #   only when the learner actually reaches it
      lesson.md                       #   (mirrors existing COURSE_CREATION.md rule:
      exercise.md                     #    "Jangan langsung Lesson 1" — lazy generation
      rubric.md                       #    is a continuation of that rule, not a new one)

submissions/<course>/                 # NEW
  2026-08-09-<slug>.md

reviews/<course>/                     # NEW
  2026-08-09-<slug>-review.md

knowledge/                            # NEW (files only in MVP — no dedicated UI yet)
  concepts/<slug>.md
  mistakes/<date>-<slug>.md
  mental-models/<slug>.md
  questions/<slug>.md

progress/
  profile.md                         # NEW — who the learner is, learning style, preferences
  progress.md                        # existing, unchanged — current-state file
  roadmap.md                         # existing, unchanged — master goal file
  <course>/
    skills.yml                       # NEW — per-skill-area % for adaptive dashboard

app/                                  # NEW — the website itself
  server/                             #   Express backend
  client/                             #   React + Vite frontend
```

Rationale for reusing `progress/` instead of a new `learning/` directory (as originally sketched in the request): `progress/roadmap.md` and `progress/progress.md` already serve the "goals" and "current-state" roles. Adding a parallel `learning/` tree would duplicate that instead of extending it — only `profile.md` is genuinely missing.

## 4. Data Contracts

Markdown with YAML frontmatter where the website needs to parse structure; plain Markdown body for narrative content. No JSON anywhere in MVP — nothing here needs it.

**`modules/<NN-slug>/lesson.md`**
```yaml
---
title: string
module: string          # e.g. "Module 3 — Claude Code Mendalam"
order: number
status: not_started | in_progress | completed
---
```
Body: existing `templates/LESSON_TEMPLATE.md` structure (Objective, Theory, Diagram, Example, Quiz, Exercise, Challenge, Summary) — unchanged, just now persisted to its own file instead of living only in chat history.

**`modules/<NN-slug>/exercise.md`**
Existing `templates/EXERCISE_TEMPLATE.md` verbatim, unchanged.

**`modules/<NN-slug>/rubric.md`** (new template)
```yaml
---
criteria:
  - name: Correctness
    weight: number
  - name: Readability
    weight: number
  # ... maps to templates/REVIEW_TEMPLATE.md categories
---
```

**`submissions/<course>/<date>-<slug>.md`**
```yaml
---
lesson: string
module: string
date: YYYY-MM-DD
status: pending | reviewed
---
```
Body: the learner's work (code, answers, links to playground files).

**`reviews/<course>/<date>-<slug>-review.md`**
```yaml
---
score: number
submission_ref: path
---
```
Body: existing `templates/REVIEW_TEMPLATE.md` categories (Overall Score, Strength, Weakness, Bug, Security, Performance, Suggestion).

**`progress/<course>/skills.yml`**
```yaml
functions: 90
loops: 85
debugging: 42
oop: 55
```
Written by Claude Code as part of writing a review — never computed or inferred by the website itself.

**`progress/profile.md`**
Free-form Markdown: role, experience, learning preferences, constraints (e.g. "prompts in Bahasa Indonesia", token-budget preferences already recorded in memory).

## 5. AI Architecture

The website makes zero model calls. Its AI-adjacent responsibilities are strictly read/write/present:

- **Read** `lesson.md` / `exercise.md` / `rubric.md` → render the lesson page.
- **Write** `submissions/...` on Submit.
- **Watch** `reviews/...` (via `chokidar`) → render when it appears.
- **Assemble** a "Mentor Context" view: `profile.md` + `progress/roadmap.md` (goals) + recent `reviews/` + `skills.yml`, with a "Copy for Claude Code" button. The learner pastes this into the Claude Code chat to ask context-aware questions ("why do I keep struggling with recursion") — Claude Code answers using the same files it already has full access to.
- **Never**: decide scores, generate lesson content, draft feedback, or hold a model conversation itself. All of that remains an explicit act the learner requests from Claude Code, identical to the current workflow, just file-mediated.

This is a deliberate trade-off accepted in the brainstorming session: the website is not a standalone AI app, it's an interface layer over a workflow where Claude Code remains the intelligence. Revisit only if the learner later wants the website usable without a Claude Code session open.

## 6. User Flow

```
Dashboard
  → pick Course → pick Module → Lesson page (renders lesson.md)
  → "Open Playground" (shows path; deep-link to VS Code deferred post-MVP)
  → learner works in VS Code
  → "Submit for Review" → writes submissions/...
  → Website: "Waiting — ask Claude Code to review this"
  → learner asks Claude Code in chat
  → Claude Code writes reviews/... and updates skills.yml / progress.md
  → Website detects the new review file, shows Review page
  → "Continue to next lesson"
```

## 7. Technical Stack

- **Backend**: Node.js + Express — file read/write/watch endpoints only (`chokidar` for watching). No ORM, no DB driver, no auth middleware.
- **Frontend**: React + Vite. `react-markdown` for rendering, `gray-matter` for YAML frontmatter parsing. Minimal custom CSS — no heavy design system (matches the "personal learning OS, not Udemy" design philosophy from the request).
- **Dev loop**: single `npm run dev` starts backend + frontend together.

Rejected alternatives and why:
- **Next.js** — its server/routing conventions solve problems (SSR, deployment) this project doesn't have; plain Vite is simpler to reason about for a local-only tool.
- **Electron** — packaging overhead with no benefit over a localhost browser tab for a single-user local tool.
- **Python backend** — no reason to introduce a second language; `chokidar` (Node) is a good fit for file-watching and keeps the stack to one language.

## 8. MVP Scope

Built against **one course only** — `courses/mastering-claude` (already active, has real history to build against). Generalization to other courses/domains happens after the pattern is proven, by following the same file convention, not by special-casing course names in code.

**In scope:**
- Dashboard: Today's Learning, Continue Learning, current course progress
- Lesson page (reads `lesson.md`)
- Submit for Review (writes submission, polls for review)
- Review page (renders review, reflects `skills.yml` changes)
- Mentor Context panel (assemble + copy-to-clipboard; no embedded chat)
- Progress page (reads `progress/progress.md`, `progress/<course>/skills.yml`)

**Explicitly deferred (not designed away, just not built yet):**
- Multi-course/multi-domain generalization
- Knowledge system UI (concepts/mistakes/mental-models — files can still be authored manually by Claude Code; no dedicated page yet)
- Learning streak
- "Open in VS Code" deep-link (MVP shows the path only)
- Any authentication or hosting concerns

## 9. Open Questions / Risks

- `courses/mastering-claude` currently has no `modules/` directory yet (only `roadmap.md`) — the first implementation task will need to generate `modules/03-.../{lesson,exercise,rubric}.md` for the module currently in progress (Module 3, Lesson 3.2 — Hooks) before the website has anything real to render. This should happen through the normal Claude Code course-authoring flow, not be invented by the website.
- File-watching polling interval/UX (how long "Waiting for review" waits before prompting the learner) is an implementation detail left to the plan, not fixed here.
- No decision yet on whether `app/` should be gitignored (node_modules definitely should be) or fully committed — default assumption is normal commit of source, gitignore of `node_modules`/build output, consistent with "git-friendly" requirement.
