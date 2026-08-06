# Universal AI Learning OS — Architecture Design

Status: Approved by user 2026-08-06, pending implementation (see "Rollout Plan").

## Problem

`d:\learn` today is an AI Learning Operating System, but everything — `CLAUDE.md`, `ai-los/AI-LOS.md`, `ai-los/LEARNING_MODE.md`, `standards/` — hard-codes software engineering as the subject matter (Java/Spring Boot stack lists, "Backend Example" / "Spring Boot Example" as mandatory lesson sections, SWE-specific trusted-source lists). The goal is to let this repo teach *any* domain (personal finance, public speaking, negotiation, leadership, languages, design, psychology, etc.) to expert level, using the same mentoring rigor, without breaking the software-engineering courses already in progress (Kubernetes Module 1, ML Fundamentals Module 1, CI/CD Module 1, Mastering Claude, etc.).

## Decisions Locked In

1. **Non-destructive**: no existing file in `ai-los/`, `standards/`, `templates/`, `courses/`, `progress/`, `portofolio/` is moved, renamed, or rewritten. New capability is added as a parallel layer.
2. **Root persona stays software-engineering by default.** `CLAUDE.md` is not generalized into a domain-neutral identity. Other domains are entered explicitly, the same way `ACTIVE_MODE.md` already lets the user switch pedagogical mode (LEARNING_MODE, REVIEW_MODE, etc.) — a parallel `ACTIVE_DOMAIN.md` lets the user switch *subject-matter domain*.
3. **One universal lesson template, not one template per domain category.** `templates/LESSON_TEMPLATE.md` (Objective, Theory, Diagram, Example, Quiz, Exercise, Challenge, Summary) turns out to already be domain-neutral — it has no hard-coded SWE wording. The SWE-specific mandatory additions ("Backend Example", "Spring Boot Example") live only in `ai-los/AI-LOS.md` and `ai-los/LEARNING_MODE.md`, which stay as-is and are understood as the `software-engineering` domain pack's own flavor of the universal template.
4. **Design → written spec → user review → implementation**, per the standard brainstorming flow. This document is that spec.

## Architecture: 3 Layers

```
Layer 1 — KERNEL (domain-agnostic, applies to every domain)
  ai-los/CORE_LOS.md                → universal learning philosophy, learning loop,
                                        hands-on-first ratio, project tiering, review rubric shape
  ai-los/ACTIVE_MODE.md             → pedagogical modes — already generic, untouched
  templates/*.md                    → already generic, untouched
  standards/UNIVERSAL_STANDARDS.md  → cross-domain quality bar (learning objectives,
                                        rubric design, source vetting, "portfolio-worthy" bar)

Layer 2 — DOMAIN REGISTRY (one manifest per subject-matter domain)
  ai-los/ACTIVE_DOMAIN.md           → which domain(s) are registered/active — parallel to ACTIVE_MODE.md
  domains/<name>/DOMAIN.md          → trusted sources, what "Applied Practice" means in this domain,
                                        project-tier definitions, assessment/review style,
                                        practitioner reference frame, optional domain-specific standards

Layer 3 — CONTENT (unchanged)
  courses/<topic>/roadmap.md        → actual curricula, format already generic
  standards/*.md (23 files)         → become the software-engineering domain pack's own standards
  ai-los/AI-LOS.md, LEARNING_MODE.md, etc. → stay exactly as-is; this IS the software-engineering
                                              domain pack's content, referenced by a thin pointer
```

`software-engineering` becomes domain pack #1, but nothing about it moves — `domains/software-engineering/DOMAIN.md` is a thin pointer stating its content lives in `ai-los/AI-LOS.md` + `standards/*.md` + `courses/*`.

## New Files

| File | Purpose |
|---|---|
| `ai-los/CORE_LOS.md` | Domain-agnostic learning engine: learning loop with neutral labels (Theory → Visualization → Simple Example → Real-world Example → **Applied Practice** → Exercise → Review → Common Mistakes → **Assessment** → Summary), 20/80 theory:practice ratio, project tiering (mini/intermediate/capstone as a general pattern), "compare how leading practitioners/organizations in the field approach X" principle (generalized from "how would Netflix/Google do this"), generic review rubric (Strengths/Weaknesses/Actionable Improvements/Score 0–100, generalized from Code Review Mode) |
| `ai-los/ACTIVE_DOMAIN.md` | Parallel to `ACTIVE_MODE.md`. Default registered domain: `software-engineering`. New domains are added here once their `DOMAIN.md` is approved. Switched via phrases like "belajar `<topic>` dari 0" / "switch domain to `<X>`". |
| `domains/README.md` | The domain-pack contract: required fields in a `DOMAIN.md` |
| `domains/software-engineering/DOMAIN.md` | Thin pointer to `ai-los/AI-LOS.md` + `standards/*` + `courses/*` — no duplication |
| `domains/personal-finance/DOMAIN.md` | First real (non-pointer) domain pack — see Pilot section below |
| `templates/DOMAIN_TEMPLATE.md` | Scaffold for creating a new domain pack: Scope, Trusted Sources, Applied Practice definition, Project Tier definitions, Assessment/Review style, Practitioner Reference Frame, optional domain-specific standards |
| `standards/UNIVERSAL_STANDARDS.md` | Cross-domain quality bar: how to write a good learning objective, how to design a rubric, how to vet a source, minimum bar for a "portfolio-worthy" artifact |

## Additive Edits (small, non-destructive)

| File | Edit |
|---|---|
| `CLAUDE.md` | Add to "Session Start": also read `ai-los/ACTIVE_DOMAIN.md`. Add a short note: the Role section's SWE framing and stack list apply when domain = `software-engineering` (the default); when another domain is active, persona composition uses `ai-los/CORE_LOS.md` + `domains/<active>/DOMAIN.md` instead. |
| `ai-los/ACTIVE_MODE.md` | One clarifying line: pedagogical modes here are domain-agnostic and apply regardless of which domain is active; subject-matter domain is governed separately by `ACTIVE_DOMAIN.md`. |

No file is deleted. No folder is restructured. `courses/`, `standards/*.md` (the 23 SWE docs), `templates/*`, `progress/*`, `portofolio/` are untouched in this design.

## Domain-Switch Workflow

Example: user says "saya mau belajar personal finance dari 0."

1. Read `ai-los/ACTIVE_DOMAIN.md` → check whether `domains/personal-finance/DOMAIN.md` exists.
2. **If not** → scaffold it from `templates/DOMAIN_TEMPLATE.md` first (trusted sources, Applied Practice definition, project tiers, assessment style) and get user approval — same approval gate `COURSE_CREATION.md` already requires for roadmaps.
3. Once approved, register the domain in `ai-los/ACTIVE_DOMAIN.md`.
4. Build the course as usual at `courses/personal-finance/roadmap.md` — same `COURSE_TEMPLATE.md`/`LESSON_TEMPLATE.md` format as any SWE course; only the "Applied Practice" content differs, driven by `DOMAIN.md`.
5. Existing SWE courses are unaffected — they implicitly stay on the default `software-engineering` domain.

## Keeping Progress & Portfolio Relevant Across Domains

No structural change to `progress/` or `portofolio/`.

- `progress/roadmap.md` stays one index. New convention: new course entries add a `Domain: <name>` tag (e.g. `**Personal Finance Foundations** — courses/personal-finance/roadmap.md — Domain: personal-finance — Status: ...`). Existing SWE entries need no edit — default domain is implicit.
- The existing "Master Goal" in `progress/roadmap.md` ("World-Class Backend Developer") stays scoped to the `software-engineering` domain — it is not generalized. A domain with its own overarching goal states it in its own `DOMAIN.md` (e.g. `personal-finance`'s `DOMAIN.md` might state a "Domain Goal") rather than replacing the SWE Master Goal.
- `portofolio/` stays a single folder; projects from any domain are tagged by domain in their entry, not siloed into subfolders, until volume actually justifies restructuring (YAGNI).

## Edge Cases

- **Conflicting standards**: priority order is domain-specific standards (if a `domains/<x>/standards/` exists) > `standards/UNIVERSAL_STANDARDS.md` > kernel defaults. This extends the existing "standards/ has highest priority on conflict" rule in `CLAUDE.md` to two tiers instead of one.
- **Multiple active domains**: `ACTIVE_DOMAIN.md` can register more than one domain at once, mirroring how `ACTIVE_MODE.md` already combines multiple pedagogical modes. Which domain governs a given conversation is determined by which course is being discussed, not a single global switch.
- **One-off questions outside SWE**: a domain pack is only scaffolded when the user explicitly wants a structured course in that field. Casual questions outside software engineering are answered directly via `ai-los/CORE_LOS.md` without forcing a `DOMAIN.md` to be created — avoids `domains/` filling up with unused manifests.

## Pilot Domain: Personal Finance

Chosen to validate the abstraction because its Applied Practice is document/plan-based (structurally closer to code than a recording or roleplay would be), while its rubric and trusted-source frame are entirely different from a code review — a good first test of whether `DOMAIN.md` + `CORE_LOS.md` genuinely decouple from SWE assumptions.

`domains/personal-finance/DOMAIN.md` will need to define, at minimum:
- Trusted sources (e.g. Bogleheads, CFP Board curriculum, official tax/regulatory docs for the user's jurisdiction)
- Applied Practice = building/revising a real (or realistic) financial plan/budget/portfolio artifact per lesson
- Project tiers: mini (e.g. a single budget worksheet) → intermediate (e.g. a debt payoff or investment plan) → capstone (a full personal financial plan, saved to `portofolio/`)
- Assessment style: plan review against a financial-planning rubric (Strengths/Weaknesses/Actionable Improvements/Score), not a code review
- Practitioner reference frame: how CFPs / robo-advisors / FIRE community practitioners approach the same problem, compared across risk tolerances

Building this file out is implementation work, not part of this design doc — it happens once the Rollout Plan below is executed.

## Rollout Plan

1. **Phase 1 — Kernel**: create `ai-los/CORE_LOS.md`, `ai-los/ACTIVE_DOMAIN.md`, `standards/UNIVERSAL_STANDARDS.md`, `templates/DOMAIN_TEMPLATE.md`, `domains/README.md`, `domains/software-engineering/DOMAIN.md`; apply the two additive edits to `CLAUDE.md` and `ai-los/ACTIVE_MODE.md`. Validate that the `software-engineering` domain still behaves identically to today (regression check, not a new capability).
2. **Phase 2 — Pilot**: scaffold `domains/personal-finance/DOMAIN.md` from `templates/DOMAIN_TEMPLATE.md`, get it approved, then build `courses/personal-finance/roadmap.md` through at least one full lesson to confirm the universal lesson template + `CORE_LOS.md` + `DOMAIN.md` combination produces a coherent, non-SWE-flavored lesson without further architecture changes.

## Out of Scope (explicitly deferred, not forgotten)

- Restructuring `courses/`, `progress/`, or `portofolio/` into per-domain subfolders — deferred until volume justifies it.
- Generalizing `CLAUDE.md`'s root persona away from software-engineering-default — explicitly rejected by the user for now.
- Per-domain-category lesson templates (Technical vs Soft-skill vs Knowledge vs Creative) — explicitly rejected in favor of one universal template with a flexible Applied Practice slot.
- git-based version control for this repo — out of scope for this design; the repo continues to be tracked as plain files.
