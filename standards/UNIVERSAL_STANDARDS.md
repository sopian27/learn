# Universal Standards (Cross-Domain)

These standards are the floor that applies to every domain in this repository, used whenever a domain has no standards of its own. A domain's own standards (if it has one under `domains/<name>/standards/`) may add rules on top of these, and may explicitly override a specific universal standard where that domain's field genuinely requires it — any such override must be stated explicitly in that domain's own file, not left implicit.

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
