# Learning OS Website

Local dashboard for the file-based learning workflow in this repo. Reads and
writes Markdown files under `courses/`, `submissions/`, `reviews/`, and
`progress/` — no database. AI review still happens in Claude Code chat; this
app only displays lessons, captures submissions, and polls for reviews.

## Run

From this directory:

```bash
npm install
npm run dev
```

- Backend: http://localhost:4000
- Frontend: http://localhost:5173

## Test

```bash
npm test
```

## Scope

MVP is scoped to `courses/mastering-claude` only (hardcoded as `COURSE` in
`client/src/App.jsx`). See
`docs/superpowers/specs/2026-08-09-ai-learning-os-website-design.md` for the
full design and `docs/superpowers/plans/2026-08-09-ai-learning-os-website.md`
for how it was built.
