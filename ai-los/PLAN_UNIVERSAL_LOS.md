# Universal AI Learning OS Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add a domain-agnostic kernel and domain-registry layer to `d:\learn` so it can teach any subject (starting with a Personal Finance pilot), without moving, renaming, or rewriting any existing software-engineering content.

**Architecture:** Three layers — Kernel (`ai-los/CORE_LOS.md`, `standards/UNIVERSAL_STANDARDS.md`, existing generic templates/modes), Domain Registry (`ai-los/ACTIVE_DOMAIN.md`, `domains/<name>/DOMAIN.md`), Content (existing `courses/*`, `standards/*.md`, `ai-los/AI-LOS.md` unchanged, now understood as the `software-engineering` domain pack). Full rationale in `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md`.

**Tech Stack:** Plain Markdown files, no code, no build step, no git (repo is not version-controlled by user's choice).

## Global Constraints

- No existing file in `ai-los/`, `standards/`, `templates/`, `courses/`, `progress/`, `portofolio/` is moved, renamed, or rewritten — only new files and two small additive edits (`CLAUDE.md`, `ai-los/ACTIVE_MODE.md`).
- **Update 2026-08-06:** to support Subagent-Driven Development execution (commit-per-task, diff-based review packages), a local-only `git init` (no remote, never pushed anywhere) was approved by the user specifically for this workflow. This supersedes the earlier blanket "no git" constraint for execution purposes only — it does not change anything about the content decisions above.
- Every new file must be self-contained real content — no "TBD" or placeholder sections.
- "Testing" a task means: read the file back and confirm every required section header from this plan is present, using Grep. There is no code to execute.

---

## Phase 1 — Kernel

### Task 1: Core domain-agnostic learning engine

**Files:**
- Create: `ai-los/CORE_LOS.md`

**Interfaces:**
- Consumes: nothing (foundational file)
- Produces: referenced by `ai-los/ACTIVE_DOMAIN.md` (Task 2), `domains/README.md` (Task 5), `templates/DOMAIN_TEMPLATE.md` (Task 4), and `CLAUDE.md`'s Domain Scope section (Task 7)

- [ ] **Step 1: Write the file**

```markdown
# Core Learning OS (Domain-Agnostic Kernel)

This file defines the mentoring engine that applies to EVERY domain in this repository, regardless of subject matter. It is read alongside whichever domain pack is active (see `ACTIVE_DOMAIN.md` and `domains/<name>/DOMAIN.md`).

Where a domain pack defines domain-specific content (trusted sources, what "Applied Practice" looks like, project shapes), this file defines the process that content plugs into.

---

# Universal Learning Loop

Every lesson, in any domain, follows this order:

1. Theory
2. Visualization
3. Simple Example
4. Real-world Example
5. Applied Practice — the domain pack defines what this is (coding exercise, roleplay, case study, drafting a document, a recorded performance, a simulation)
6. Exercises
7. Review — the domain pack defines the review lens (code review, speech critique, plan review, negotiation debrief)
8. Common Mistakes
9. Assessment — the domain pack defines the form (quiz, interview questions, critique panel, self-evaluation rubric)
10. Summary

---

# Hands-on First

Theory is at most 20% of any lesson. Applied Practice is at least 80%. Never let a lesson stay purely theoretical — always produce or attempt something concrete, in whatever medium the domain uses.

---

# Project Tiering

Every domain organizes practice into three tiers, regardless of what the artifact actually is:

- **Mini Project** — ends every module. Small, scoped to that module's skill only.
- **Intermediate Project** — ends every major section. Combines multiple modules' skills.
- **Capstone / Portfolio Project** — ends every course. Production-quality, presentable, saved to `portofolio/`.

The domain pack defines what "production-quality" and "presentable" mean for that domain's artifacts.

---

# Practitioner Reference Frame

Whenever introducing a concept, compare how recognized leading practitioners or organizations in that field approach the same problem (the domain pack's "Practitioner Reference Frame" section names who these are — e.g. Netflix/Google/Stripe for software engineering, CFPs/Bogleheads for personal finance, Toastmasters/TED speakers for public speaking). Compare a beginner/informal approach against an expert/institutional approach, and explain the trade-offs.

---

# Universal Review Rubric

Whenever I submit any artifact for review — code, an essay, a financial plan, a speech recording, a negotiation transcript — review it like a domain expert:

- Strengths
- Weaknesses
- Actionable Improvements
- Score (0–100)

Never simply say "looks good." The domain pack's Assessment/Review Style section may add domain-specific criteria on top of this shape, but never replaces it.

---

# Adaptive Learning

If I already understand something, skip unnecessary repetition. If I struggle, slow down, use analogies, and give additional exercises. Never continue until the concept is understood. Use previous progress (`progress/roadmap.md` and the relevant course's own roadmap) before deciding what I already know.

---

# Success Criteria Shape

A lesson is complete only when: theory is understood, assessment is passed, Applied Practice is completed, the artifact has been reviewed, and the summary is understood. A course is complete only when every module's mini project, every section's intermediate project, and the course's capstone/portfolio project are all done.

---

# Relationship to Domain Packs

This file never mentions a specific domain. Concrete stack choices, trusted sources, and what an "Applied Practice" artifact literally is always live in `domains/<name>/DOMAIN.md`. `CORE_LOS.md` changes only when the *process* itself needs to change, not when a new domain is added.
```

- [ ] **Step 2: Verify required sections are present**

Run: `grep -c "^# " "d:\learn\ai-los\CORE_LOS.md"`
Expected: `9` (nine top-level `#` sections: Core Learning OS title, Universal Learning Loop, Hands-on First, Project Tiering, Practitioner Reference Frame, Universal Review Rubric, Adaptive Learning, Success Criteria Shape, Relationship to Domain Packs)

---

### Task 2: Domain switch/registry file

**Files:**
- Create: `ai-los/ACTIVE_DOMAIN.md`

**Interfaces:**
- Consumes: `ai-los/CORE_LOS.md` (Task 1, referenced conceptually, no literal link required)
- Produces: read at session start per `CLAUDE.md`'s Session Start section (Task 7); referenced by `domains/README.md` (Task 5)

- [ ] **Step 1: Write the file**

```markdown
# ACTIVE_DOMAIN.md

This file controls which subject-matter domain(s) are currently registered and active. It is the domain-layer counterpart to `ACTIVE_MODE.md`, which controls pedagogical *mode* instead. Read this alongside `ACTIVE_MODE.md` at session start.

A "domain" is a subject-matter field (software-engineering, personal-finance, public-speaking, etc.). Each registered domain has a manifest at `domains/<name>/DOMAIN.md`. Pedagogical modes (LEARNING_MODE, REVIEW_MODE, etc.) apply the same way regardless of which domain is active — modes are HOW to teach, domains are WHAT.

---

# Registered Domains

- `software-engineering` — default domain. Manifest: `domains/software-engineering/DOMAIN.md` (thin pointer to `ai-los/AI-LOS.md` + `standards/*` + `courses/*`).
- `personal-finance` — manifest: `domains/personal-finance/DOMAIN.md`.

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
```

- [ ] **Step 2: Verify required sections are present**

Run: `grep -c "^# " "d:\learn\ai-los\ACTIVE_DOMAIN.md"`
Expected: `6`

---

### Task 3: Universal cross-domain standards

**Files:**
- Create: `standards/UNIVERSAL_STANDARDS.md`

**Interfaces:**
- Consumes: `ai-los/CORE_LOS.md` (Task 1, its Universal Review Rubric section is referenced)
- Produces: referenced by `templates/DOMAIN_TEMPLATE.md` (Task 4) and `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md`'s priority-order rule

- [ ] **Step 1: Write the file**

```markdown
# Universal Standards (Cross-Domain)

These standards apply to every domain in this repository. They sit above any single domain's own standards in priority for anything they cover; a domain's own standards (if it has one under `domains/<name>/standards/`) may ADD domain-specific rules but may not contradict these.

---

# Writing a Good Learning Objective

- Start with an action verb from Bloom's Taxonomy appropriate to the target depth: Remember/Understand (define, explain) → Apply/Analyze (build, debug, compare) → Evaluate/Create (critique, design, produce).
- Every objective must be demonstrable — there must be a concrete Applied Practice artifact that proves it was met. "Understand X" is not verifiable; "Explain X to a beginner and build a working example of X" is.
- One objective should map to one lesson. If a lesson needs 5+ objectives, it is scoped too broadly and should split.

---

# Designing a Rubric

- Define 3–6 criteria specific to the artifact being reviewed (never fewer than 3 — anything less isn't really a rubric).
- For each criterion, define what a 0, a pass-level, and an expert-level result look like — don't leave scoring to unstated intuition.
- Always end with the Universal Review Rubric shape from `ai-los/CORE_LOS.md`: Strengths, Weaknesses, Actionable Improvements, Score (0–100).
- A rubric never rewards effort alone — it rewards the artifact meeting the criteria.

---

# Vetting a Source

Before treating a source as "trusted" for a domain's `DOMAIN.md`, check:

- **Authority** — is it the official body/institution/publisher for that field, or a recognized practitioner with a verifiable track record?
- **Currency** — is it current enough that its guidance hasn't been superseded (matters most in fast-moving fields like software engineering, less in fields with stable canon)?
- **Bias** — does the source have a commercial or ideological incentive that could distort its advice? Name it if so, and cross-check against at least one independent source.
- Never build a curriculum from a single source, regardless of how authoritative — synthesize from multiple.

---

# "Portfolio-Worthy" Bar

An artifact (project, plan, recording, writeup) is portfolio-worthy only if it is:

- **Complete** — not a fragment; it stands on its own without additional explanation.
- **Reviewed** — it has been through the Universal Review Rubric at least once and the Actionable Improvements were addressed.
- **Presentable** — a stranger in that field could look at it and understand what it demonstrates without live narration.
- **Original** — it reflects the learner's own work/decisions, not a copied template with names swapped.
```

- [ ] **Step 2: Verify required sections are present**

Run: `grep -c "^# " "d:\learn\standards\UNIVERSAL_STANDARDS.md"`
Expected: `5`

---

### Task 4: Domain pack scaffold template

**Files:**
- Create: `templates/DOMAIN_TEMPLATE.md`

**Interfaces:**
- Consumes: `ai-los/CORE_LOS.md` (Task 1), `standards/UNIVERSAL_STANDARDS.md` (Task 3), `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md` (priority rule reference)
- Produces: used as the scaffold source in Task 6 (`domains/software-engineering/DOMAIN.md`) and Task 9 (`domains/personal-finance/DOMAIN.md`); referenced by `domains/README.md` (Task 5)

- [ ] **Step 1: Write the file**

```markdown
# Domain Pack Template

Copy this file to `domains/<name>/DOMAIN.md` when registering a new subject-matter domain. Every field below is required unless marked optional. See `domains/README.md` for the contract this template implements, and `domains/personal-finance/DOMAIN.md` for a worked example.

## Domain Name

## Scope

What this domain covers, and explicitly what it does NOT cover (to keep it from overlapping an existing domain).

## Trusted Sources / Research Priority

Ranked list of the sources courses in this domain must synthesize from when building a roadmap (mirrors the priority list `ai-los/AI-LOS.md` uses for software engineering: roadmap.sh, official docs, etc., but specific to this field).

## Applied Practice Definition

What "hands-on practice" concretely means in this domain (the domain-specific fill-in for Applied Practice in `ai-los/CORE_LOS.md`'s Universal Learning Loop step 5). Example: for software-engineering it's writing/debugging/refactoring code; for public-speaking it's a recorded or live delivery; for personal-finance it's producing/revising a real financial artifact.

## Project Tiers

What a Mini / Intermediate / Capstone project (per `ai-los/CORE_LOS.md`'s Project Tiering) actually looks like as an artifact in this domain.

## Assessment / Review Style

What "review" and "assessment" mean here on top of the Universal Review Rubric — e.g. code review checklist vs. speech critique vs. plan review vs. negotiation debrief. Name the domain-specific criteria added to the rubric.

## Practitioner Reference Frame

Who the recognized leading practitioners/organizations are in this field, used for the "how would an expert approach this" comparisons `ai-los/CORE_LOS.md` calls for.

## Domain-Specific Standards (optional)

If this domain needs its own `domains/<name>/standards/` files (analogous to `standards/*.md` for software engineering), list/link them here. Domain-specific standards take priority over `standards/UNIVERSAL_STANDARDS.md` on conflict, per `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md`.

## Domain Goal (optional)

This domain's own overarching goal, if it has one distinct from any single course (parallel to the "Master Goal" `progress/roadmap.md` tracks for software-engineering).
```

- [ ] **Step 2: Verify required sections are present**

Run: `grep -c "^## " "d:\learn\templates\DOMAIN_TEMPLATE.md"`
Expected: `9`

---

### Task 5: Domain registry README

**Files:**
- Create: `domains/README.md`

**Interfaces:**
- Consumes: `templates/DOMAIN_TEMPLATE.md` (Task 4), `ai-los/ACTIVE_DOMAIN.md` (Task 2), `ai-los/CORE_LOS.md` (Task 1)
- Produces: nothing downstream depends on this file's content structurally — it's documentation for humans/agents browsing `domains/`

- [ ] **Step 1: Write the file**

```markdown
# Domains Registry

This folder holds one subfolder per subject-matter domain this repository can teach. Each domain has exactly one file that matters structurally: `domains/<name>/DOMAIN.md`.

---

# The Domain Pack Contract

A `DOMAIN.md` must define, at minimum, every section in `templates/DOMAIN_TEMPLATE.md`: Domain Name, Scope, Trusted Sources, Applied Practice Definition, Project Tiers, Assessment/Review Style, Practitioner Reference Frame — plus optional Domain-Specific Standards and Domain Goal.

---

# Why This Exists

`ai-los/CORE_LOS.md` defines the teaching process the same way for every domain. It never names a specific field. `DOMAIN.md` files are what make that process concrete for a given subject — they supply the content the process needs (what counts as practice, who the field's leading practitioners are, what a finished artifact looks like) without changing the process itself.

---

# Registering a Domain

See "Registering a New Domain" in `ai-los/ACTIVE_DOMAIN.md` for the full workflow. In short: scaffold from `templates/DOMAIN_TEMPLATE.md`, get it approved, register it in `ai-los/ACTIVE_DOMAIN.md`, then build the course under `courses/<name>/roadmap.md` as usual.

---

# Domains Currently Registered

- `software-engineering` — see `domains/software-engineering/DOMAIN.md` (pointer to existing `ai-los/`, `standards/`, `courses/` content — nothing duplicated).
- `personal-finance` — see `domains/personal-finance/DOMAIN.md`.
```

- [ ] **Step 2: Verify required sections are present**

Run: `grep -c "^# " "d:\learn\domains\README.md"`
Expected: `5`

---

### Task 6: Software-engineering domain pointer pack

**Files:**
- Create: `domains/software-engineering/DOMAIN.md`

**Interfaces:**
- Consumes: `templates/DOMAIN_TEMPLATE.md` (Task 4) as its structural shape; points to (does not duplicate) `ai-los/AI-LOS.md`, `ai-los/LEARNING_MODE.md`, `standards/*.md`, `courses/*`, `progress/roadmap.md`
- Produces: registered in `ai-los/ACTIVE_DOMAIN.md`'s list (already added in Task 2)

- [ ] **Step 1: Write the file**

```markdown
## Domain Name

Software Engineering

## Scope

Backend/full-stack software engineering, system design, DevOps, and adjacent technical fields — the entire existing `courses/` catalog (Kubernetes, React, LLM, Agents, ML Fundamentals, System Design, AWS, Blockchain, CI/CD, Code Review, Design Patterns, Mastering Claude). Does not cover non-technical career skills like freelance client management as a separate domain — see `ai-los/CAREER_MODE.md` for how that is handled within this same domain today.

## Trusted Sources / Research Priority

This is a pointer, not a duplicate list. See `ai-los/AI-LOS.md`, section "Course Creation", for the authoritative, maintained priority list (roadmap.sh, official documentation, CNCF, AWS/Spring/Oracle/PostgreSQL/Redis/Kubernetes docs, etc.).

## Applied Practice Definition

See `ai-los/AI-LOS.md` ("Hands-on Coding", "Integration Practice") and `ai-los/LEARNING_MODE.md` ("Lesson Structure" — Backend Example / Spring Boot Example). Writing, debugging, refactoring, testing, and integrating real code.

## Project Tiers

See `ai-los/AI-LOS.md`, section "Projects": Mini Project per module, Intermediate Project per major section, Enterprise-grade Portfolio Project per course, saved to `portofolio/`.

## Assessment / Review Style

See `ai-los/AI-LOS.md`, section "Code Review Mode": Correctness, Architecture, Clean Code, Naming, Maintainability, Performance, Security, Scalability, Testing, Spring Best Practices, Database Design, Production Readiness, plus Strengths/Weaknesses/Actionable Improvements/Score (0–100).

## Practitioner Reference Frame

See `ai-los/AI-LOS.md`, section "Real World" and root `CLAUDE.md`: Netflix, Uber, Amazon, Google, Grab, Gojek, Stripe, GitHub.

## Domain-Specific Standards

See `standards/*.md` (23 files: `JAVA_STANDARDS.md`, `SPRING_BOOT_STANDARDS.md`, `DATABASE_STANDARDS.md`, `SECURITY_STANDARDS.md`, etc.) — the existing repository-wide `standards/` folder is this domain's standards set.

## Domain Goal

See `progress/roadmap.md`, section "Master Goal": World-Class Backend Developer.
```

- [ ] **Step 2: Verify required sections are present**

Run: `grep -c "^## " "d:\learn\domains\software-engineering\DOMAIN.md"`
Expected: `9`

---

### Task 7: Wire the kernel into CLAUDE.md

**Files:**
- Modify: `d:\learn\CLAUDE.md:8-25`

**Interfaces:**
- Consumes: `ai-los/ACTIVE_DOMAIN.md` (Task 2), `ai-los/CORE_LOS.md` (Task 1), `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md`, `domains/` (Task 5)
- Produces: nothing downstream — this is the root instruction file

- [ ] **Step 1: Update Session Start and add Domain Scope section**

Replace this exact block (lines 8–10):

```markdown
# Session Start

At the start of every session, read `ai-los/ACTIVE_MODE.md` first to determine active modes before applying any other instructions below.
```

With:

```markdown
# Session Start

At the start of every session, read `ai-los/ACTIVE_MODE.md` first to determine active modes, then read `ai-los/ACTIVE_DOMAIN.md` to determine which subject-matter domain(s) are active, before applying any other instructions below.

# Domain Scope

The Role and Spring Boot sections above describe the `software-engineering` domain, which is the default and currently the primary domain with content in this repository. When another domain is registered and active per `ai-los/ACTIVE_DOMAIN.md`, compose persona and instructions from `ai-los/CORE_LOS.md` plus that domain's `domains/<name>/DOMAIN.md` instead — see `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md` for the full design.
```

- [ ] **Step 2: Add `domains/` to Repository Priority**

Replace this exact block (lines 12–25, the full "Repository Priority" section):

```markdown
# Repository Priority
Always load and follow the repository in this order:

1. standards/ — engineering conventions and best practices (highest priority on conflict)
2. ai-los/ — mentor operating modes (see ACTIVE_MODE.md) and course-creation rules
3. templates/ — reusable templates for courses, lessons, exercises, reviews, projects
4. courses/ — generated curricula and lessons
5. portofolio/ — production-quality portfolio projects
6. playground/ — throwaway experiments and prototypes
7. challenges/ — coding challenges and practice problems
8. assets/ — supporting files (diagrams, images, etc.)
9. scripts/ — automation scripts

If multiple documents overlap, the standards directory has the highest priority.
```

With:

```markdown
# Repository Priority
Always load and follow the repository in this order:

1. standards/ — engineering conventions and best practices, including cross-domain UNIVERSAL_STANDARDS.md (highest priority on conflict)
2. ai-los/ — mentor operating modes (see ACTIVE_MODE.md), domain switching (see ACTIVE_DOMAIN.md), the domain-agnostic kernel (CORE_LOS.md), and course-creation rules
3. domains/ — subject-matter domain manifests (see ACTIVE_DOMAIN.md and domains/README.md)
4. templates/ — reusable templates for courses, lessons, exercises, reviews, projects, and domain packs
5. courses/ — generated curricula and lessons
6. portofolio/ — production-quality portfolio projects
7. playground/ — throwaway experiments and prototypes
8. challenges/ — coding challenges and practice problems
9. assets/ — supporting files (diagrams, images, etc.)
10. scripts/ — automation scripts

If multiple documents overlap, the standards directory has the highest priority; within standards/, a domain's own domain-specific standards (if any, under domains/<name>/standards/) take priority over standards/UNIVERSAL_STANDARDS.md, which takes priority over kernel defaults.
```

- [ ] **Step 3: Verify the edit**

Run: `grep -n "ACTIVE_DOMAIN" "d:\learn\CLAUDE.md"`
Expected: at least 2 matches (Session Start line and Domain Scope section)

---

### Task 8: Cross-reference ACTIVE_MODE.md

**Files:**
- Modify: `d:\learn\ai-los\ACTIVE_MODE.md:1-9`

**Interfaces:**
- Consumes: `ai-los/ACTIVE_DOMAIN.md` (Task 2)
- Produces: nothing downstream

- [ ] **Step 1: Add the clarifying line**

Replace this exact block (lines 1–9):

```markdown
# ACTIVE_MODE.md

This file controls which AI modes are currently active.

Whenever a conversation starts, read this file first before following any other mode-specific instructions.

The modes listed here determine your primary behavior.

---
```

With:

```markdown
# ACTIVE_MODE.md

This file controls which AI modes are currently active.

Whenever a conversation starts, read this file first before following any other mode-specific instructions.

The modes listed here determine your primary behavior.

These modes are domain-agnostic — they apply the same way regardless of which subject-matter domain is active. See `ai-los/ACTIVE_DOMAIN.md` for domain (subject) selection, which is a separate, orthogonal axis: modes are HOW you teach, domains are WHAT you teach.

---
```

- [ ] **Step 2: Verify the edit**

Run: `grep -n "ACTIVE_DOMAIN" "d:\learn\ai-los\ACTIVE_MODE.md"`
Expected: 1 match

---

## Phase 1 Acceptance Check

- [ ] Run: `grep -rl "ACTIVE_DOMAIN" "d:\learn\CLAUDE.md" "d:\learn\ai-los\ACTIVE_MODE.md" "d:\learn\ai-los\ACTIVE_DOMAIN.md"`
  Expected: all 3 files listed.
- [ ] Confirm no file under `d:\learn\courses\`, `d:\learn\standards\` (except the new `UNIVERSAL_STANDARDS.md`), `d:\learn\progress\`, or `d:\learn\portofolio\` was modified — Phase 1 only touches the 6 new files plus `CLAUDE.md` and `ai-los/ACTIVE_MODE.md`.
- [ ] Re-read `CLAUDE.md` end to end and confirm the `software-engineering` domain's behavior (Role, Spring Boot stack list, Learning Loop, Review Mode, etc.) is unchanged in substance — this phase only adds pointers, it must not alter existing SWE mentoring behavior.

---

## Phase 2 — Pilot: Personal Finance

### Task 9: Personal-finance domain pack

**Files:**
- Create: `domains/personal-finance/DOMAIN.md`

**Interfaces:**
- Consumes: `templates/DOMAIN_TEMPLATE.md` (Task 4) for structure; `standards/UNIVERSAL_STANDARDS.md` (Task 3) for the source-vetting bar
- Produces: consumed by `courses/personal-finance/roadmap.md` (Task 10); registered in `ai-los/ACTIVE_DOMAIN.md`'s list (already added in Task 2)

- [ ] **Step 1: Write the file**

```markdown
## Domain Name

Personal Finance

## Scope

Individual/household money management: budgeting, saving, debt, insurance, investing, retirement planning, and basic tax and estate awareness — from the perspective of a single person managing their own finances. Does NOT cover corporate finance, professional financial advising licensure, or country-specific tax law in full legal depth (tax/estate lessons stay conceptual and flag "confirm with a licensed professional in your jurisdiction" where it matters).

## Trusted Sources / Research Priority

1. Bogleheads Wiki and forum (community-vetted, low-cost index-investing philosophy, no commercial product bias)
2. CFP Board's official curriculum topic list (defines the standard body of knowledge a licensed planner is expected to know)
3. Official government/regulatory sources for the learner's jurisdiction (e.g. for Indonesia: OJK for financial-product regulation, DJP for tax basics) — used for any lesson touching tax, retirement accounts, or regulated products
4. Investopedia — useful for definitions and breadth, but ad-supported; always cross-check against source 1–3 before treating as authoritative, per `standards/UNIVERSAL_STANDARDS.md`'s Vetting a Source bar
5. Books with a durable, non-trend-chasing track record (e.g. "The Bogleheads' Guide to Investing", "A Random Walk Down Wall Street") for deeper conceptual grounding

Never build a lesson from Investopedia alone — always triangulate with at least one of sources 1–3.

## Applied Practice Definition

Producing or revising a real (or realistic) personal-finance artifact every lesson — not a hypothetical worksheet with someone else's numbers. Examples: a personal net-worth statement, a monthly cash-flow/budget worksheet, a debt payoff schedule, an emergency-fund target calculation, an asset-allocation decision with written rationale, a retirement-contribution projection. The artifact must use the learner's own real or realistically-estimated numbers whenever the learner is willing to share them; otherwise a clearly-labeled realistic scenario is used instead.

## Project Tiers

- **Mini Project** (per module): a single-purpose worksheet or calculation — e.g. one month's cash-flow statement, or one debt's payoff schedule.
- **Intermediate Project** (per major section): a multi-part plan combining several modules' skills — e.g. a combined emergency-fund + debt-payoff plan, or a full Investment Policy Statement (goals, risk tolerance, target asset allocation, rebalancing rule).
- **Capstone / Portfolio Project** (end of course): a complete personal financial plan document integrating cash flow, emergency fund, debt strategy, insurance review, investment policy statement, and a retirement projection into one presentable artifact, saved to `portofolio/`.

## Assessment / Review Style

On top of the Universal Review Rubric (Strengths / Weaknesses / Actionable Improvements / Score 0–100), every plan review additionally checks:

- **Feasibility** — do the numbers actually work given the stated income/expenses, or does the plan assume unrealistic savings rates or returns?
- **Risk Alignment** — does the investment/insurance strategy match the stated risk tolerance and time horizon, not a generic template?
- **Completeness** — are all required components present for that project tier (see Project Tiers above)?
- **Actionability** — could the learner literally execute the next step tomorrow, or is it too abstract to act on?

## Practitioner Reference Frame

Compare how a fee-only CFP would approach a problem against how the Bogleheads/DIY community would approach it against how a robo-advisor (e.g. Betterment/Wealthfront-style rules-based allocation) would approach it — three different cost/complexity/personalization trade-offs for the same problem. For the FIRE (Financial Independence, Retire Early)-flavored questions, additionally reference the FIRE community's savings-rate-driven approach as a fourth, more aggressive reference point.

## Domain-Specific Standards

None yet — `standards/UNIVERSAL_STANDARDS.md` is sufficient for this domain today. Add `domains/personal-finance/standards/` only if a recurring need for domain-specific rigor rules emerges (e.g. a house rule for how conservative return assumptions must be).

## Domain Goal

To be refined with the learner when `courses/personal-finance/roadmap.md` is approved (see Task 10) — provisionally: produce a complete, feasible, risk-aligned personal financial plan the learner can explain and defend every decision in, and be equipped to keep it updated independently afterward.
```

- [ ] **Step 2: Verify required sections are present**

Run: `grep -c "^## " "d:\learn\domains\personal-finance\DOMAIN.md"`
Expected: `9`

---

### Task 10: Personal-finance course roadmap

**Files:**
- Create: `courses/personal-finance/roadmap.md`

**Interfaces:**
- Consumes: `domains/personal-finance/DOMAIN.md` (Task 9) for Applied Practice/Project Tier/Assessment definitions; `templates/COURSE_TEMPLATE.md` for section shape (Overview, Objective, Roadmap, Modules, Resources, Projects, Interview, Final Exam)
- Produces: entry point for future live lesson delivery (validated manually in Task 11); referenced by `progress/roadmap.md` update (this same task, Step 2)

- [ ] **Step 1: Write the roadmap file**

```markdown
# Personal Finance — dari 0 sampai Punya Rencana Keuangan Lengkap

Domain: personal-finance (lihat `domains/personal-finance/DOMAIN.md`)

Status: Not Started — menunggu approval roadmap ini sebelum Lesson 1.1 dimulai (mengikuti alur yang sama dengan `ai-los/COURSE_CREATION.md`).

---

## Overview

Course ini membangun kemampuan mengelola keuangan pribadi dari nol: memahami arus kas sendiri, membangun dana darurat, mengelola utang, memahami asuransi sebagai risk transfer, investasi berbasis index/diversifikasi ala Bogleheads, dana pensiun, hingga menyusun satu dokumen rencana keuangan pribadi yang lengkap dan bisa dipertahankan alasannya di setiap keputusan.

## Objective

Di akhir course ini, learner mampu:
- Membaca kondisi keuangannya sendiri lewat net worth statement dan cash-flow statement
- Membangun dan menjustifikasi dana darurat, strategi pelunasan utang, dan proteksi asuransi dasar
- Menyusun Investment Policy Statement dan portofolio investasi terdiversifikasi sesuai profil risiko sendiri
- Memproyeksikan kebutuhan dana pensiun dan memahami dasar pajak & estate planning yang relevan
- Menghasilkan satu dokumen Rencana Keuangan Pribadi lengkap sebagai capstone/portfolio project

## Roadmap (Modules)

1. **Financial Foundations & Mindset** — net worth statement, cash-flow statement, bias psikologis umum dalam keputuan keuangan (loss aversion, lifestyle inflation, dll)
2. **Budgeting & Cash Flow Management** — metode zero-based vs 50/30/20; Mini Project: budget worksheet bulan berjalan
3. **Emergency Fund & Risk Basics** — menghitung target dana darurat sesuai profil risiko pekerjaan/keluarga
4. **Debt Management & Payoff Strategies** — avalanche vs snowball, membedakan utang produktif vs konsumtif; Mini Project: jadwal pelunasan salah satu utang
5. **Insurance Fundamentals** — asuransi sebagai risk transfer, jenis-jenis dasar (jiwa, kesehatan, proteksi pendapatan), cara menghitung kebutuhan uang pertanggungan
6. **Investing Fundamentals** — kelas aset, hubungan risk/return, diversifikasi, compounding
7. **Index Investing & Portfolio Construction** — filosofi Bogleheads, asset allocation, rebalancing; Intermediate Project: Investment Policy Statement pribadi
8. **Retirement Planning** — proyeksi kebutuhan dana pensiun, instrumen pensiun yang relevan di yurisdiksi learner, strategi penarikan dana
9. **Tax Basics for Personal Finance** — dampak pajak terhadap keputusan keuangan pribadi secara konseptual, mengacu sumber resmi yurisdiksi learner
10. **Estate & Legacy Basics** — wasiat, penunjukan ahli waris/beneficiary, konsep dasar estate planning
11. **Capstone: Rencana Keuangan Pribadi Lengkap** — integrasi semua modul jadi satu dokumen presentable, disimpan ke `portofolio/`

## Resources

Lihat `domains/personal-finance/DOMAIN.md`, bagian "Trusted Sources / Research Priority", untuk daftar lengkap dan urutan prioritasnya (Bogleheads, kurikulum CFP Board, sumber resmi yurisdiksi, Investopedia dengan catatan bias, buku-buku pendukung).

## Projects

Mengikuti `domains/personal-finance/DOMAIN.md`, bagian "Project Tiers": Mini Project di setiap module di atas, Intermediate Project di Module 7 (Investment Policy Statement), Capstone Project di Module 11 (Rencana Keuangan Pribadi Lengkap), disimpan ke `portofolio/` sesuai standar "portfolio-worthy" di `standards/UNIVERSAL_STANDARDS.md`.

## Interview / Assessment

Setiap module ditutup dengan review artifact memakai `domains/personal-finance/DOMAIN.md` bagian "Assessment / Review Style" (Feasibility, Risk Alignment, Completeness, Actionability, plus Universal Review Rubric).

## Final Exam

Review penuh atas Capstone Project (Module 11) memakai rubric yang sama, plus sesi tanya-jawab mempertahankan setiap keputusan dalam rencana keuangan yang disusun.

---

## Overall Progress

Status: Not Started — roadmap ini menunggu approval user sebelum Lesson 1.1 dimulai.
```

- [ ] **Step 2: Register the course in the repo-level progress index**

Read `d:\learn\progress\roadmap.md` first to find its "Course Aktif" section (or equivalent listing section), then add one line following the existing entry format used for other courses in that section:

```markdown
* **Personal Finance — dari 0 sampai Punya Rencana Keuangan Lengkap** — `courses/personal-finance/roadmap.md` — Domain: personal-finance — Status: Not Started, menunggu approval roadmap (dibuat sebagai pilot domain non-SWE pertama, lihat `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md`)
```

- [ ] **Step 3: Verify required sections are present**

Run: `grep -c "^## " "d:\learn\courses\personal-finance\roadmap.md"`
Expected: `8`

Run: `grep -n "personal-finance" "d:\learn\progress\roadmap.md"`
Expected: at least 1 match (the line added in Step 2)

---

### Task 11: Live validation of the pilot (manual, not a file-writing task)

**Files:** none created or modified — this task validates Tasks 1–10 by using them.

**Interfaces:**
- Consumes: everything from Phase 1 and Phase 2

- [ ] **Step 1: Get roadmap approval**

Present `courses/personal-finance/roadmap.md` to the user for approval, same as any other course roadmap per `ai-los/COURSE_CREATION.md`.

- [ ] **Step 2: Teach Lesson 1.1 live, interactively, in conversation**

Follow `ai-los/CORE_LOS.md`'s Universal Learning Loop combined with `domains/personal-finance/DOMAIN.md`'s Applied Practice Definition (Module 1: Financial Foundations & Mindset — building a net-worth statement and cash-flow statement). Do not pre-write this lesson's content as a static file — this repository's existing courses (e.g. `courses/react/roadmap.md`, `courses/kubernetes`) are taught live, session by session, not pre-authored, and the pilot must be validated the same way the system actually operates.

- [ ] **Step 3: Confirm the design holds**

Acceptance criteria for the pilot to be considered successful:
- The lesson followed the Universal Learning Loop from `ai-los/CORE_LOS.md` without needing any SWE-specific wording (no "Backend Example"/"Spring Boot Example" leaked in).
- The Applied Practice artifact produced (a net-worth or cash-flow statement) matches `domains/personal-finance/DOMAIN.md`'s Applied Practice Definition.
- The review given after the exercise used the Universal Review Rubric plus the domain's Feasibility/Risk Alignment/Completeness/Actionability criteria.
- If any of the above required improvising outside what `CORE_LOS.md` or `DOMAIN.md` already defined, note the gap — that is a signal `ai-los/ARCHITECTURE_UNIVERSAL_LOS.md` or one of the Phase 1/2 files needs a follow-up revision.

---

## Phase 2 Acceptance Check

- [ ] `domains/personal-finance/DOMAIN.md` and `courses/personal-finance/roadmap.md` exist and pass their grep checks above.
- [ ] `progress/roadmap.md` lists the new course with a `Domain:` tag.
- [ ] Lesson 1.1 was delivered live and the Task 11 acceptance criteria were confirmed with the user.
