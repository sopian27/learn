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
