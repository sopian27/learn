# Tech Info Feed (`/info-tech`)

Acts as a Technology Intelligence Feed: surfaces current tech news that is both significant and relevant to my learning roadmap, so I stay current without doomscrolling.

---

# Trigger

Command: `/info-tech`

Natural-language equivalents: "info tech", "berita tech", "update teknologi".

Every invocation runs a fresh web search. Never reuse a previous run's results from memory.

---

# Step 0 — Recall Covered Topics

Before searching, read `info tech/_topics-log.md` in the vault (see [[reference_obsidian_vault]] memory for vault path). If it doesn't exist yet, treat the topic list as empty and create it after Step 4.

Each line is `YYYY-MM-DD: tag1, tag2, ...` — normalized kebab-case topic tags (e.g. `spring-boot-4`, `virtual-threads`, `jdk21-license`, `k8s-dra`, `claude-model-pricing`). Build the exclusion set from this log before curating anything.

---

# Step 1 — Search

Search the web for current tech news. Curate 6 items per run (not 3). Prioritize:

- Topics that are significant or trending, not just popular/clickbait.
- Topics relevant to my roadmap: Software Engineering, Backend Engineering, Java/Spring, Software Architecture, System Design, AI/LLM/AI Engineering, Cloud Native, Kubernetes, DevOps, Database, Distributed Systems, Developer Tools.

Use credible sources, prefer the most recent coverage. Skip clickbait, unconfirmed rumors, and news with no technical substance. If multiple sources cover the same story, merge into one insight instead of listing duplicates.

## No-Repeat Rule

Cross-check every candidate topic against the tag log from Step 0.

- If a candidate matches a logged tag and there is no concrete state change since it was last covered (same status, same numbers, just reworded or re-reported) — **drop it**. Rewording an already-covered story is not a new item; find a genuinely different one instead.
- Only re-cover a logged topic if something material actually changed (e.g. beta → GA, milestone → stable release, a new number/price, an announced thing actually shipped, a resolved outcome). In that case the item must be framed as a follow-up, not fresh news: open **Apa yang terjadi?** with what changed since `[tanggal terakhir dibahas]`, and skip re-explaining background already covered before.
- If after filtering there are fewer than 6 genuinely new/updated items, it's fine to report fewer than 6 — never pad with reworded repeats to hit the count.

---

# Step 2 — Curate & Explain (Bahasa Indonesia, santai)

For each item, do not translate literally — explain what it actually means and why it matters, using this structure:

- **Apa yang terjadi?** — plain explanation.
- **Kenapa ramai?** — why this is getting attention now.
- **Kenapa saya harus peduli?** — tie back to my skills, work, or roadmap.
- **💡 Takeaway** — one or two sentences on what to internalize.

Writing style: casual Bahasa Indonesia, clear, to the point, not like a formal news article. Focus on: *apa yang terjadi → kenapa penting → apa dampaknya buat saya sebagai software engineer.*

---

# Step 3 — Chat Output Format

```
🧠 TECH INFO — [tanggal]

🔥 Yang Lagi Ramai

1. [Topik / Headline]
Apa yang terjadi? ...
Kenapa ramai? ...
Kenapa saya harus peduli? ...
💡 Takeaway: ...

2. [Topik / Headline]
...

🎯 AI Insight
Kalau saya berada di posisi kamu, hal yang paling perlu diperhatikan adalah...
(maksimal 3 poin)
```

If any topic is better learned via a structured course, flag it: `[Topik] → /recommend`.

---

# Step 4 — Save to Obsidian

Target: `info tech/YYYY-MM-DD.md` inside the Obsidian vault (see [[reference_obsidian_vault]] memory for vault path).

- One file per day. Filename = `YYYY-MM-DD.md`.
- File holds curated output only — never raw/unprocessed search dumps.
- Structure:

```markdown
# Tech Info — YYYY-MM-DD

## 🔥 Yang Lagi Ramai

### [Topik]

**Apa yang terjadi?**
...

**Kenapa ramai?**
...

**Kenapa saya harus peduli?**
...

**💡 Takeaway**
...

**Source**
- [Nama sumber](URL)

---

## 🎯 AI Insight

1. ...
2. ...
3. ...

## 📚 Related Learning

- [Topik] → `/recommend`
```

### Duplicate Handling

If `YYYY-MM-DD.md` already exists for today:

- Do not create a duplicate file — update/merge into the existing one.
- Never delete existing important information.
- Never list the same news item twice.

### Update the Topic Log

After saving the daily file, append one line to `info tech/_topics-log.md`:

```
YYYY-MM-DD: tag1, tag2, tag3, ...
```

One tag per item covered today (same normalized tags used for the Step 0 exclusion check). Create the file with this format if it doesn't exist yet. Never rewrite or delete earlier lines — append only.

---

# Separation of Responsibilities

`/info-tech` searches for current tech developments, curates them, gives insight, and saves to the vault's `info tech/` folder. It does not draft course roadmaps (that's `/recommend` / `ai-los/COURSE_RECOMMENDATION.md`) and does not touch `progress/roadmap.md`.
