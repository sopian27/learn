# Course Recommendation (`/recommend`)

Acts as a learning advisor: finds and evaluates real, currently available external courses for whatever skill I should learn next, so I don't have to search myself.

---

# Trigger

Command: `/recommend`

Natural-language equivalents: "recommend course", "rekomendasi course", "cari course lanjutan", "course apa selanjutnya".

Every invocation runs a fresh search. Never reuse a course list from a previous `/recommend` run or from memory — freshness (point 4 below) depends on searching at call time.

---

# Step 1 — Read Context

Before searching, read:

1. `progress/roadmap.md` — Master Goal, Gap Analysis, "Course Aktif" section (current active courses + their current module), completed courses (marked "Selesai").
2. `progress/progress.md` — Active Courses table, current module/lesson, completion %, Weak Topics.
3. `ai-los/ACTIVE_DOMAIN.md` — active domain(s), in case the recommendation should respect domain scope.
4. The current active course's own `courses/<name>/roadmap.md` — for the specific skill of the current/next module (not just the course title).

---

# Step 2 — Determine the Recommended Next Skill

Default source: `progress/roadmap.md`'s **Gap Analysis**, picking whichever unfilled gap best serves the Master Goal profile (e.g. "world-class / international-caliber software engineer"). This applies regardless of active-course completion % — `/recommend` finds external resources, it does not draft new internal courses, so it does not trip the `CLAUDE.md` § Course Creation Discipline guard (that guard governs `courses/` roadmap creation, not external links logged to `progress/resources.md`).

Exceptions, in order:

1. If a "Weak Topics" entry exists in `progress/progress.md` for an active course, or I explicitly ask for a course tied to the current module (e.g. "course buat modul yang lagi jalan"), recommend from that module instead of the Gap Analysis.
2. If no Gap Analysis gap remains unfilled and no weak-topic/module request applies, fall back to the next course in "Course Disetujui — Siap Dimulai".

State which source applied and why, in one line, before the recommendation output.

---

# Step 3 — Search

Use WebSearch / WebFetch (or Context7 for library/framework docs where applicable) to find currently available courses. Do not answer from training-data memory — course catalogs, pricing, and platform offerings change.

Search broader than keyword matching on the skill name: also search platform-specific listings (Coursera, Udemy, Pluralsight, O'Reilly, egghead, Frontend Masters, official vendor training, YouTube full courses from recognized instructors, etc.) and, where relevant, official documentation-based learning paths.

Also check what's currently trending/hyped for the Step 2 skill, as a signal (not the only signal — still weigh against Step 4 quality criteria, hype alone doesn't rank a course up):

* GitHub Trending (trending repos/tools in the skill's language or ecosystem)
* Reddit (e.g. r/programming, r/cscareerquestions, r/learnprogramming, r/ExperiencedDevs, or the skill's dedicated subreddit)
* Hacker News (front page / recent discussions on the skill)
* Dev.to, Hashnode, or similar dev-community posts

Use trending signals to catch newly-released or suddenly-relevant resources that static platform listings might miss (a new framework, a course riding a real shift in the industry) — flag in the output if a pick was sourced this way.

---

# Step 4 — Evaluate & Rank

For every candidate course, evaluate ALL of:

* Relevance to `progress/roadmap.md` and the Step 2 skill decision
* Difficulty level match (check current skill level in `progress/progress.md`)
* Course quality / instructor or platform reputation
* Freshness — last updated / recorded date, especially for fast-moving tech
* Duration
* Price (if available)
* Fit to current skill gap (not just topic overlap)

Rank and shortlist the 3–5 strongest candidates.

---

# Step 5 — Exclusions

Never recommend a course that duplicates one already marked "Selesai" in `progress/roadmap.md` or `progress/progress.md` (e.g. Mastering Claude is done — never recommend another "intro to Claude Code" course).

---

# Step 6 — Honesty Clause

If no genuinely good match is found, say so explicitly. Do not force a mediocre recommendation just to fill the output template. It is acceptable to return fewer than 3 courses, or none, with a one-line explanation of why.

---

# Output Format

```
🎯 Learning Recommendation

Current Focus: ...
Recommended Next Skill: ...

🥇 AI Pick

[Course Name]

Platform:
Level:
Duration:
Price:
Why:
Link:

Other Recommendations

1. ...
2. ...
3. ...

Why this is the right next step: ...
```

---

# After the Recommendation

If I decide to start one of the recommended courses, that's an external resource — log it in `progress/resources.md`, it does not need to go through `ai-los/COURSE_CREATION.md` (that flow is for AI-LOS-authored internal courses under `courses/`, not external course links).
