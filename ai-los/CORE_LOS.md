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

If I already understand something, skip unnecessary repetition. If I struggle, slow down, use analogies, and give additional exercises. Never continue until the concept is understood. Consult `progress/roadmap.md` and the relevant course's own roadmap when relevant to the current course or decision — not implied to be re-read every turn.

---

# Success Criteria Shape

A lesson is complete only when: theory is understood, assessment is passed, Applied Practice is completed, the artifact has been reviewed, and the summary is understood. A course is complete only when every module's mini project, every section's intermediate project, and the course's capstone/portfolio project are all done.

---

# Relationship to Domain Packs

This file never mentions a specific domain. Concrete stack choices, trusted sources, and what an "Applied Practice" artifact literally is always live in `domains/<name>/DOMAIN.md`. `CORE_LOS.md` changes only when the *process* itself needs to change, not when a new domain is added.

**Note on the default domain:** for the default `software-engineering` domain, the existing SWE-specific loop described in root `CLAUDE.md` ("Learning Loop"), `ai-los/LEARNING_MODE.md` ("Lesson Structure"), and `ai-los/AI-LOS.md` ("Teaching Philosophy" lesson checklist, "Course Structure") — Theory → Visualization → Simple Example → Real-world Example → Coding → Exercises → Code Review → Common Mistakes → Interview Questions/Quiz → Summary — IS that domain's concrete instantiation of this Universal Learning Loop, not a separate, competing process. `CLAUDE.md`'s "Coding" step is this loop's Applied Practice step, "Code Review" is this loop's Review step, and "Interview Questions"/"Quiz" is this loop's Assessment step. All three SWE files are the same ten-step loop phrased for different purposes (persona framing, mode-switch behavior, domain-pack content respectively) — none of them defines a competing fourth process. Every other domain instantiates the same ten-step loop with its own domain-appropriate labels, defined in that domain's `DOMAIN.md`.
