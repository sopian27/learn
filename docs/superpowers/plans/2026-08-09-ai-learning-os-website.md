# AI Learning OS Website Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build the MVP Learning OS website — a local Express backend that reads/writes/renders the learning filesystem, plus a React frontend — scoped to `courses/mastering-claude` only, with zero database and zero AI calls from the app itself.

**Architecture:** `app/server` (Express) exposes REST endpoints that read and write Markdown+YAML-frontmatter files under `d:\learn` (courses, submissions, reviews, progress). `app/client` (React + Vite) consumes that API. The Submit→Review loop is implemented as write-then-poll: the client POSTs a submission, then polls a GET review endpoint every few seconds until Claude Code (working outside this app, in chat) has written the review file.

**Tech Stack:** Node.js + Express + gray-matter + js-yaml + cors (backend); React + react-router-dom + react-markdown + Vite (frontend); `node:test` + supertest (backend tests); Vitest + Testing Library (frontend tests).

## Global Constraints

- No database of any kind — filesystem is the only persistent store (per design spec §1).
- No AI/model calls from `app/` — all AI interaction stays in Claude Code chat (per design spec §5).
- Local-only: no auth, no deployment target, runs on `localhost` (per design spec §2).
- MVP scope is one course only: `mastering-claude` — do not build multi-course abstractions (per design spec §8).
- File formats: Markdown with YAML frontmatter, no JSON data files (per design spec §4).
- Data contracts (frontmatter shapes for lesson/exercise/rubric/submission/review/skills.yml) are fixed by design spec §4 — routes must produce/consume exactly those shapes.
- All backend file access must go through the safe path resolver (Task 2) — never build paths with raw string concatenation elsewhere.

---

## File Structure

```
app/
  package.json                    # root scripts: dev, test (Task 16)
  .gitignore                      # node_modules, dist (Task 16)
  README.md                       # how to run (Task 16)
  server/
    package.json
    src/
      config.js                   # LEARN_ROOT resolution (Task 1)
      index.js                    # Express app + route wiring (Task 1, extended per task)
      lib/
        fsPaths.js                # safe path resolver (Task 2)
        markdown.js                # frontmatter read/write (Task 3)
      routes/
        lessons.js                 # Task 4
        submissions.js              # Task 5
        reviews.js                  # Task 6
        courses.js                  # Task 7
        progress.js                 # Task 8
        mentor.js                   # Task 9
    test/
      fixtures/mastering-claude/... # shared test fixture course tree (Task 2)
      lib/fsPaths.test.js
      lib/markdown.test.js
      routes/lessons.test.js
      routes/submissions.test.js
      routes/reviews.test.js
      routes/courses.test.js
      routes/progress.test.js
      routes/mentor.test.js
  client/
    package.json
    vite.config.js
    vitest.config.js
    index.html
    src/
      main.jsx
      App.jsx                      # Task 10
      api/client.js                 # Task 10
      pages/
        Dashboard.jsx                # Task 11
        Lesson.jsx                   # Task 12
        Review.jsx                   # Task 13
        Progress.jsx                  # Task 14
        MentorContext.jsx             # Task 15
      test/setup.js
```

Content generation for the real `courses/mastering-claude/modules/03-.../{lesson,exercise,rubric}.md` is **out of scope for this plan** (per design spec §9, Open Questions) — it happens through a normal Claude Code authoring session. This plan uses fixture data for tests and ships the app pointed at whatever real content exists.

---

## Task 1: Backend scaffold + health endpoint

**Files:**
- Create: `app/server/package.json`
- Create: `app/server/src/config.js`
- Create: `app/server/src/index.js`
- Test: `app/server/test/health.test.js`

**Interfaces:**
- Produces: `config.js` exports `LEARN_ROOT` (absolute path string) and `PORT` (number). `index.js` exports `createApp()` returning an Express app (not yet listening) — later tasks import this to mount routes and to test via supertest without binding a port.

- [ ] **Step 1: Create `app/server/package.json`**

```json
{
  "name": "learning-os-server",
  "version": "0.1.0",
  "type": "module",
  "scripts": {
    "dev": "node --watch src/index.js",
    "start": "node src/index.js",
    "test": "node --test test/"
  },
  "dependencies": {
    "express": "^4.19.2",
    "gray-matter": "^4.0.3",
    "js-yaml": "^4.1.0",
    "cors": "^2.8.5"
  },
  "devDependencies": {
    "supertest": "^7.0.0"
  }
}
```

- [ ] **Step 2: Install dependencies**

Run (from `app/server/`): `npm install`

- [ ] **Step 3: Write `app/server/src/config.js`**

```js
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export const LEARN_ROOT =
  process.env.LEARN_ROOT ?? path.resolve(__dirname, '../../..');

export const PORT = Number(process.env.PORT ?? 4000);
```

- [ ] **Step 4: Write the failing test for the health endpoint**

```js
// app/server/test/health.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import request from 'supertest';
import { createApp } from '../src/index.js';

test('GET /api/health returns ok', async () => {
  const app = createApp();
  const res = await request(app).get('/api/health');
  assert.equal(res.status, 200);
  assert.deepEqual(res.body, { status: 'ok' });
});
```

- [ ] **Step 5: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — `createApp` is not exported / module not found.

- [ ] **Step 6: Write `app/server/src/index.js`**

```js
import express from 'express';
import cors from 'cors';
import { fileURLToPath } from 'node:url';
import { PORT } from './config.js';

export function createApp() {
  const app = express();
  app.use(cors());
  app.use(express.json());

  app.get('/api/health', (req, res) => {
    res.json({ status: 'ok' });
  });

  return app;
}

if (process.argv[1] === fileURLToPath(import.meta.url)) {
  const app = createApp();
  app.listen(PORT, () => {
    console.log(`learning-os-server listening on http://localhost:${PORT}`);
  });
}
```

(`fileURLToPath` normalizes `import.meta.url` to a platform-native path before comparing against `process.argv[1]` — the naive `import.meta.url === \`file://${process.argv[1]}\`` string comparison never matches on Windows, where paths use backslashes and `import.meta.url` is percent-encoded.)

- [ ] **Step 7: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 8: Commit**

```bash
git add app/server/package.json app/server/src/config.js app/server/src/index.js app/server/test/health.test.js
git commit -m "feat(server): scaffold Express app with health endpoint"
```

---

## Task 2: Safe path resolver + shared test fixtures

**Files:**
- Create: `app/server/src/lib/fsPaths.js`
- Create: `app/server/test/lib/fsPaths.test.js`
- Create: `app/server/test/fixtures/mastering-claude/roadmap.md`
- Create: `app/server/test/fixtures/mastering-claude/modules/01-fondasi/lesson.md`
- Create: `app/server/test/fixtures/mastering-claude/modules/01-fondasi/exercise.md`
- Create: `app/server/test/fixtures/mastering-claude/modules/01-fondasi/rubric.md`

**Interfaces:**
- Consumes: nothing from earlier tasks.
- Produces: `resolveSafe(root, relativePath)` — `(string, string) => string`, returns an absolute path, throws `Error('Path escapes root: <relativePath>')` if the resolved path is not inside `root`. All later route files import this from `../lib/fsPaths.js`. The fixture tree at `test/fixtures/mastering-claude/` is the `LEARN_ROOT`-relative course tree every later route test points its `LEARN_ROOT` at (via `path.join(__dirname, '../fixtures')`).

- [ ] **Step 1: Write the failing tests**

```js
// app/server/test/lib/fsPaths.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import path from 'node:path';
import { resolveSafe } from '../../src/lib/fsPaths.js';

const ROOT = path.resolve('/learn-root');

test('resolves a normal relative path under root', () => {
  const result = resolveSafe(ROOT, 'courses/mastering-claude/roadmap.md');
  assert.equal(result, path.join(ROOT, 'courses/mastering-claude/roadmap.md'));
});

test('throws on path traversal attempts', () => {
  assert.throws(
    () => resolveSafe(ROOT, '../../etc/passwd'),
    /Path escapes root/
  );
});

test('throws on absolute path injection', () => {
  assert.throws(
    () => resolveSafe(ROOT, '/etc/passwd'),
    /Path escapes root/
  );
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — module `../../src/lib/fsPaths.js` not found.

- [ ] **Step 3: Write `app/server/src/lib/fsPaths.js`**

```js
import path from 'node:path';

export function resolveSafe(root, relativePath) {
  const resolvedRoot = path.resolve(root);
  const resolved = path.resolve(resolvedRoot, relativePath);
  const relative = path.relative(resolvedRoot, resolved);

  if (relative.startsWith('..') || path.isAbsolute(relative)) {
    throw new Error(`Path escapes root: ${relativePath}`);
  }

  return resolved;
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 5: Create fixture course tree**

```markdown
<!-- app/server/test/fixtures/mastering-claude/roadmap.md -->
# Mastering Claude — Roadmap

Fixture roadmap for tests.
```

```markdown
<!-- app/server/test/fixtures/mastering-claude/modules/01-fondasi/lesson.md -->
---
title: Fondasi Mental Model & Prompting
module: "Module 1 — Fondasi"
order: 1
status: completed
---

## Objective

Understand the base mental model.

## Theory

Fixture theory content.
```

```markdown
<!-- app/server/test/fixtures/mastering-claude/modules/01-fondasi/exercise.md -->
# Exercise

## Title

Fixture Exercise

## Difficulty

* Beginner
```

```yaml
# app/server/test/fixtures/mastering-claude/modules/01-fondasi/rubric.md
---
criteria:
  - name: Correctness
    weight: 60
  - name: Readability
    weight: 40
---
```

- [ ] **Step 6: Commit**

```bash
git add app/server/src/lib/fsPaths.js app/server/test/lib/fsPaths.test.js app/server/test/fixtures
git commit -m "feat(server): add safe path resolver and shared test fixtures"
```

---

## Task 3: Markdown/frontmatter read-write library

**Files:**
- Create: `app/server/src/lib/markdown.js`
- Create: `app/server/test/lib/markdown.test.js`
- Modify: `app/server/package.json` (add `gray-matter` — already listed in Task 1; no change needed if Task 1 done first)

**Interfaces:**
- Consumes: none.
- Produces: `readMarkdown(absPath)` — `(string) => {data: object, content: string}`, throws with `.code === 'ENOENT'` if file missing (propagates Node's native error, routes check `err.code`). `writeMarkdown(absPath, {data, content})` — `(string, {data: object, content: string}) => void`, creates parent directories recursively if needed.

- [ ] **Step 1: Write the failing tests**

```js
// app/server/test/lib/markdown.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import os from 'node:os';
import path from 'node:path';
import { readMarkdown, writeMarkdown } from '../../src/lib/markdown.js';

test('readMarkdown parses frontmatter and content', () => {
  const fixture = path.resolve('test/fixtures/mastering-claude/modules/01-fondasi/lesson.md');
  const { data, content } = readMarkdown(fixture);
  assert.equal(data.title, 'Fondasi Mental Model & Prompting');
  assert.equal(data.order, 1);
  assert.match(content, /Understand the base mental model/);
});

test('readMarkdown throws ENOENT for missing file', () => {
  assert.throws(
    () => readMarkdown('/nonexistent/file.md'),
    (err) => err.code === 'ENOENT'
  );
});

test('writeMarkdown writes frontmatter + content and creates dirs', () => {
  const tmpDir = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  const target = path.join(tmpDir, 'nested', 'file.md');

  writeMarkdown(target, { data: { status: 'pending' }, content: 'Body text' });

  const { data, content } = readMarkdown(target);
  assert.equal(data.status, 'pending');
  assert.match(content, /Body text/);
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — module not found.

- [ ] **Step 3: Write `app/server/src/lib/markdown.js`**

```js
import fs from 'node:fs';
import path from 'node:path';
import matter from 'gray-matter';

export function readMarkdown(absPath) {
  const raw = fs.readFileSync(absPath, 'utf8');
  const parsed = matter(raw);
  return { data: parsed.data, content: parsed.content.trim() };
}

export function writeMarkdown(absPath, { data, content }) {
  fs.mkdirSync(path.dirname(absPath), { recursive: true });
  const output = matter.stringify(`\n${content}\n`, data);
  fs.writeFileSync(absPath, output, 'utf8');
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add app/server/src/lib/markdown.js app/server/test/lib/markdown.test.js
git commit -m "feat(server): add markdown frontmatter read/write library"
```

---

## Task 4: Lesson route — `GET /api/courses/:course/modules/:moduleSlug/lesson`

**Files:**
- Create: `app/server/src/routes/lessons.js`
- Create: `app/server/test/routes/lessons.test.js`
- Modify: `app/server/src/index.js` (mount route)

**Interfaces:**
- Consumes: `resolveSafe` from `../lib/fsPaths.js` (Task 2), `readMarkdown` from `../lib/markdown.js` (Task 3).
- Produces: Express router mounted at `/api/courses`. Route file exports `createLessonsRouter(learnRoot)` — `(string) => express.Router` — this pattern (a factory taking `learnRoot`) is reused by every later route file so tests can point each router at the fixture tree instead of the real `LEARN_ROOT`.

- [ ] **Step 1: Write the failing test**

```js
// app/server/test/routes/lessons.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import path from 'node:path';
import express from 'express';
import request from 'supertest';
import { createLessonsRouter } from '../../src/routes/lessons.js';

const FIXTURES = path.resolve('test/fixtures');

function buildApp() {
  const app = express();
  app.use('/api/courses', createLessonsRouter(FIXTURES));
  return app;
}

test('GET lesson returns parsed frontmatter and content', async () => {
  const res = await request(buildApp()).get(
    '/api/courses/mastering-claude/modules/01-fondasi/lesson'
  );
  assert.equal(res.status, 200);
  assert.equal(res.body.data.title, 'Fondasi Mental Model & Prompting');
  assert.match(res.body.content, /Understand the base mental model/);
});

test('GET lesson returns 404 when module has no lesson.md', async () => {
  const res = await request(buildApp()).get(
    '/api/courses/mastering-claude/modules/99-missing/lesson'
  );
  assert.equal(res.status, 404);
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — module not found.

- [ ] **Step 3: Write `app/server/src/routes/lessons.js`**

```js
import express from 'express';
import path from 'node:path';
import { resolveSafe } from '../lib/fsPaths.js';
import { readMarkdown } from '../lib/markdown.js';

export function createLessonsRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course/modules/:moduleSlug/lesson', (req, res) => {
    const { course, moduleSlug } = req.params;
    const relPath = path.join(
      'courses', course, 'modules', moduleSlug, 'lesson.md'
    );

    try {
      const absPath = resolveSafe(learnRoot, relPath);
      const { data, content } = readMarkdown(absPath);
      res.json({ data, content });
    } catch (err) {
      if (err.code === 'ENOENT') {
        res.status(404).json({ error: 'Lesson not found' });
      } else {
        res.status(400).json({ error: err.message });
      }
    }
  });

  return router;
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 5: Mount the router in `app/server/src/index.js`**

```js
// add near the top:
import { createLessonsRouter } from './routes/lessons.js';
import { LEARN_ROOT, PORT } from './config.js';

// inside createApp(), after app.use(express.json()):
  app.use('/api/courses', createLessonsRouter(LEARN_ROOT));
```

- [ ] **Step 6: Commit**

```bash
git add app/server/src/routes/lessons.js app/server/test/routes/lessons.test.js app/server/src/index.js
git commit -m "feat(server): add lesson read route"
```

---

## Task 5: Submission route — `POST /api/courses/:course/submissions`

**Files:**
- Create: `app/server/src/routes/submissions.js`
- Create: `app/server/test/routes/submissions.test.js`
- Modify: `app/server/src/index.js` (mount route)

**Interfaces:**
- Consumes: `resolveSafe`, `writeMarkdown`.
- Produces: `createSubmissionsRouter(learnRoot)`. On success returns `201 { path: string (relative), data: { lesson, module, date, status: 'pending' } }`. Written file: `submissions/<course>/<date>-<slug>.md`. Body must include `slug`, `lesson`, `module`, `content`; `date` optional (defaults to today, `YYYY-MM-DD`).

- [ ] **Step 1: Write the failing tests**

```js
// app/server/test/routes/submissions.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import os from 'node:os';
import path from 'node:path';
import express from 'express';
import request from 'supertest';
import { createSubmissionsRouter } from '../../src/routes/submissions.js';
import { readMarkdown } from '../../src/lib/markdown.js';

function buildApp(root) {
  const app = express();
  app.use(express.json());
  app.use('/api/courses', createSubmissionsRouter(root));
  return app;
}

test('POST submission writes a file with pending status', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));

  const res = await request(buildApp(root))
    .post('/api/courses/mastering-claude/submissions')
    .send({
      slug: 'hooks',
      lesson: '3.2 Hooks',
      module: 'Module 3',
      content: 'My answer text',
      date: '2026-08-09',
    });

  assert.equal(res.status, 201);
  assert.equal(res.body.data.status, 'pending');

  const written = readMarkdown(
    path.join(root, 'submissions/mastering-claude/2026-08-09-hooks.md')
  );
  assert.equal(written.data.lesson, '3.2 Hooks');
  assert.match(written.content, /My answer text/);
});

test('POST submission defaults date to today when omitted', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  const today = new Date().toISOString().slice(0, 10);

  const res = await request(buildApp(root))
    .post('/api/courses/mastering-claude/submissions')
    .send({ slug: 'x', lesson: 'L', module: 'M', content: 'C' });

  assert.equal(res.status, 201);
  assert.equal(res.body.data.date, today);
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — module not found.

- [ ] **Step 3: Write `app/server/src/routes/submissions.js`**

```js
import express from 'express';
import path from 'node:path';
import { resolveSafe } from '../lib/fsPaths.js';
import { writeMarkdown } from '../lib/markdown.js';

export function createSubmissionsRouter(learnRoot) {
  const router = express.Router();

  router.post('/:course/submissions', (req, res) => {
    const { course } = req.params;
    const { slug, lesson, module, content, date } = req.body;

    if (!slug || !lesson || !module || !content) {
      res.status(400).json({ error: 'slug, lesson, module, content are required' });
      return;
    }

    const submissionDate = date ?? new Date().toISOString().slice(0, 10);
    const relPath = path.join(
      'submissions', course, `${submissionDate}-${slug}.md`
    );

    try {
      const absPath = resolveSafe(learnRoot, relPath);
      const data = { lesson, module, date: submissionDate, status: 'pending' };
      writeMarkdown(absPath, { data, content });
      res.status(201).json({ path: relPath, data });
    } catch (err) {
      res.status(400).json({ error: err.message });
    }
  });

  return router;
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 5: Mount the router in `app/server/src/index.js`**

```js
// add import:
import { createSubmissionsRouter } from './routes/submissions.js';
// add mount, after the lessons router mount:
  app.use('/api/courses', createSubmissionsRouter(LEARN_ROOT));
```

- [ ] **Step 6: Commit**

```bash
git add app/server/src/routes/submissions.js app/server/test/routes/submissions.test.js app/server/src/index.js
git commit -m "feat(server): add submission write route"
```

---

## Task 6: Review route — `GET /api/courses/:course/reviews/:slug`

**Files:**
- Create: `app/server/src/routes/reviews.js`
- Create: `app/server/test/routes/reviews.test.js`
- Modify: `app/server/src/index.js` (mount route)

**Interfaces:**
- Consumes: `resolveSafe`, `readMarkdown`.
- Produces: `createReviewsRouter(learnRoot)`. Looks up the review file by slug regardless of date prefix (filename is `<date>-<slug>-review.md`) by scanning `reviews/<course>/`. Returns `404 { pending: true }` when no matching file exists yet (this is the "not reviewed yet" state the frontend polls on), `200 { data, content }` once it does.

- [ ] **Step 1: Write the failing tests**

```js
// app/server/test/routes/reviews.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import os from 'node:os';
import path from 'node:path';
import express from 'express';
import request from 'supertest';
import { createReviewsRouter } from '../../src/routes/reviews.js';
import { writeMarkdown } from '../../src/lib/markdown.js';

function buildApp(root) {
  const app = express();
  app.use('/api/courses', createReviewsRouter(root));
  return app;
}

test('GET review returns pending 404 when file does not exist', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  const res = await request(buildApp(root)).get(
    '/api/courses/mastering-claude/reviews/hooks'
  );
  assert.equal(res.status, 404);
  assert.equal(res.body.pending, true);
});

test('GET review returns the review once the file exists', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  writeMarkdown(
    path.join(root, 'reviews/mastering-claude/2026-08-09-hooks-review.md'),
    { data: { score: 85, submission_ref: 'submissions/mastering-claude/2026-08-09-hooks.md' },
      content: '## Strength\n\nGood use of hooks.' }
  );

  const res = await request(buildApp(root)).get(
    '/api/courses/mastering-claude/reviews/hooks'
  );
  assert.equal(res.status, 200);
  assert.equal(res.body.data.score, 85);
  assert.match(res.body.content, /Good use of hooks/);
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — module not found.

- [ ] **Step 3: Write `app/server/src/routes/reviews.js`**

```js
import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import { resolveSafe } from '../lib/fsPaths.js';
import { readMarkdown } from '../lib/markdown.js';

export function createReviewsRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course/reviews/:slug', (req, res) => {
    const { course, slug } = req.params;
    const dirRelPath = path.join('reviews', course);

    try {
      const dirAbsPath = resolveSafe(learnRoot, dirRelPath);
      const entries = fs.existsSync(dirAbsPath) ? fs.readdirSync(dirAbsPath) : [];
      const match = entries.find((f) => f.endsWith(`-${slug}-review.md`));

      if (!match) {
        res.status(404).json({ pending: true });
        return;
      }

      const { data, content } = readMarkdown(path.join(dirAbsPath, match));
      res.json({ data, content });
    } catch (err) {
      res.status(400).json({ error: err.message });
    }
  });

  return router;
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 5: Mount the router in `app/server/src/index.js`**

```js
// add import:
import { createReviewsRouter } from './routes/reviews.js';
// add mount:
  app.use('/api/courses', createReviewsRouter(LEARN_ROOT));
```

- [ ] **Step 6: Commit**

```bash
git add app/server/src/routes/reviews.js app/server/test/routes/reviews.test.js app/server/src/index.js
git commit -m "feat(server): add review lookup route with pending state"
```

---

## Task 7: Course route — `GET /api/courses/:course`

**Files:**
- Create: `app/server/src/routes/courses.js`
- Create: `app/server/test/routes/courses.test.js`
- Modify: `app/server/src/index.js` (mount route)
- Create: `app/server/test/fixtures/mastering-claude/modules/02-token-economy/lesson.md` (second fixture module, needed to test ordering)

**Interfaces:**
- Consumes: `resolveSafe`, `readMarkdown`.
- Produces: `createCoursesRouter(learnRoot)`. Returns `200 { roadmap: string, modules: [{ slug, title, order, status }] }` sorted by `order` ascending. If `modules/` doesn't exist, `modules: []`. `404` if `roadmap.md` itself is missing (a course that doesn't exist).

- [ ] **Step 1: Add second fixture module**

```markdown
<!-- app/server/test/fixtures/mastering-claude/modules/02-token-economy/lesson.md -->
---
title: Token Economy Claude Code
module: "Module 2 — Token Economy"
order: 2
status: completed
---

## Objective

Understand token cost.
```

- [ ] **Step 2: Write the failing tests**

```js
// app/server/test/routes/courses.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import path from 'node:path';
import express from 'express';
import request from 'supertest';
import { createCoursesRouter } from '../../src/routes/courses.js';

const FIXTURES = path.resolve('test/fixtures');

function buildApp() {
  const app = express();
  app.use('/api/courses', createCoursesRouter(FIXTURES));
  return app;
}

test('GET course returns roadmap text and modules sorted by order', async () => {
  const res = await request(buildApp()).get('/api/courses/mastering-claude');
  assert.equal(res.status, 200);
  assert.match(res.body.roadmap, /Mastering Claude — Roadmap/);
  assert.deepEqual(
    res.body.modules.map((m) => m.slug),
    ['01-fondasi', '02-token-economy']
  );
  assert.equal(res.body.modules[0].status, 'completed');
});

test('GET course returns 404 for unknown course', async () => {
  const res = await request(buildApp()).get('/api/courses/does-not-exist');
  assert.equal(res.status, 404);
});
```

- [ ] **Step 3: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — module not found.

- [ ] **Step 4: Write `app/server/src/routes/courses.js`**

```js
import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import { resolveSafe } from '../lib/fsPaths.js';
import { readMarkdown } from '../lib/markdown.js';

export function createCoursesRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course', (req, res) => {
    const { course } = req.params;

    let roadmap;
    try {
      const roadmapPath = resolveSafe(learnRoot, path.join('courses', course, 'roadmap.md'));
      roadmap = fs.readFileSync(roadmapPath, 'utf8');
    } catch (err) {
      res.status(404).json({ error: 'Course not found' });
      return;
    }

    const modulesDir = resolveSafe(learnRoot, path.join('courses', course, 'modules'));
    const modules = [];

    if (fs.existsSync(modulesDir)) {
      for (const slug of fs.readdirSync(modulesDir)) {
        const lessonPath = path.join(modulesDir, slug, 'lesson.md');
        if (!fs.existsSync(lessonPath)) continue;
        const { data } = readMarkdown(lessonPath);
        modules.push({ slug, title: data.title, order: data.order, status: data.status });
      }
    }

    modules.sort((a, b) => a.order - b.order);
    res.json({ roadmap, modules });
  });

  return router;
}
```

- [ ] **Step 5: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 6: Mount the router in `app/server/src/index.js`**

```js
// add import:
import { createCoursesRouter } from './routes/courses.js';
// add mount — must come AFTER the more specific routers (lessons/submissions/reviews)
// since this one matches the bare "/:course" path:
  app.use('/api/courses', createCoursesRouter(LEARN_ROOT));
```

- [ ] **Step 7: Commit**

```bash
git add app/server/src/routes/courses.js app/server/test/routes/courses.test.js app/server/test/fixtures/mastering-claude/modules/02-token-economy app/server/src/index.js
git commit -m "feat(server): add course summary route with module listing"
```

---

## Task 8: Progress route — `GET /api/courses/:course/progress`

**Files:**
- Create: `app/server/src/routes/progress.js`
- Create: `app/server/test/routes/progress.test.js`
- Modify: `app/server/src/index.js` (mount route)
- Create: `app/server/test/fixtures/progress/progress.md`
- Create: `app/server/test/fixtures/progress/mastering-claude/skills.yml`

**Interfaces:**
- Consumes: `resolveSafe`, `js-yaml`'s `load`.
- Produces: `createProgressRouter(learnRoot)`. Returns `200 { progress: string, skills: object }`. If `skills.yml` is missing, `skills: {}` (not an error — a course may not have graded work yet).

- [ ] **Step 1: Create fixtures**

```markdown
<!-- app/server/test/fixtures/progress/progress.md -->
# Learning Progress

Current Module: Module 3
```

```yaml
# app/server/test/fixtures/progress/mastering-claude/skills.yml
functions: 90
debugging: 42
```

- [ ] **Step 2: Write the failing tests**

```js
// app/server/test/routes/progress.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import path from 'node:path';
import express from 'express';
import request from 'supertest';
import { createProgressRouter } from '../../src/routes/progress.js';

const FIXTURES = path.resolve('test/fixtures');

function buildApp(root) {
  const app = express();
  app.use('/api/courses', createProgressRouter(root));
  return app;
}

test('GET progress returns progress text and skills map', async () => {
  const res = await request(buildApp(FIXTURES)).get(
    '/api/courses/mastering-claude/progress'
  );
  assert.equal(res.status, 200);
  assert.match(res.body.progress, /Current Module: Module 3/);
  assert.equal(res.body.skills.functions, 90);
});

test('GET progress returns empty skills object when skills.yml is missing', async () => {
  const res = await request(buildApp(FIXTURES)).get(
    '/api/courses/no-skills-course/progress'
  );
  assert.equal(res.status, 200);
  assert.deepEqual(res.body.skills, {});
});
```

- [ ] **Step 3: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — module not found.

- [ ] **Step 4: Write `app/server/src/routes/progress.js`**

```js
import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import yaml from 'js-yaml';
import { resolveSafe } from '../lib/fsPaths.js';

export function createProgressRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course/progress', (req, res) => {
    const { course } = req.params;

    const progressPath = resolveSafe(learnRoot, path.join('progress', 'progress.md'));
    const progress = fs.existsSync(progressPath)
      ? fs.readFileSync(progressPath, 'utf8')
      : '';

    const skillsPath = resolveSafe(learnRoot, path.join('progress', course, 'skills.yml'));
    // js-yaml's load() uses DEFAULT_SCHEMA and is safe by default (unlike
    // PyYAML's load()) — it cannot construct arbitrary types.
    const skills = fs.existsSync(skillsPath)
      ? yaml.load(fs.readFileSync(skillsPath, 'utf8')) ?? {}
      : {};

    res.json({ progress, skills });
  });

  return router;
}
```

- [ ] **Step 5: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 6: Mount the router in `app/server/src/index.js`**

```js
// add import:
import { createProgressRouter } from './routes/progress.js';
// mount BEFORE createCoursesRouter (more specific path first):
  app.use('/api/courses', createProgressRouter(LEARN_ROOT));
```

- [ ] **Step 7: Commit**

```bash
git add app/server/src/routes/progress.js app/server/test/routes/progress.test.js app/server/test/fixtures/progress app/server/src/index.js
git commit -m "feat(server): add progress + skills route"
```

---

## Task 9: Mentor context route — `GET /api/mentor-context`

**Files:**
- Create: `app/server/src/routes/mentor.js`
- Create: `app/server/test/routes/mentor.test.js`
- Modify: `app/server/src/index.js` (mount route)
- Create: `app/server/test/fixtures/progress/profile.md`
- Create: `app/server/test/fixtures/progress/roadmap.md`
- Create: `app/server/test/fixtures/reviews/mastering-claude/2026-08-07-hooks-review.md`

**Interfaces:**
- Consumes: `resolveSafe`, `fs`.
- Produces: `createMentorRouter(learnRoot)`. Returns `200 { context: string }` — a single assembled Markdown document with `## Profile`, `## Goals`, `## Recent Reviews`, `## Skills` sections. Missing `profile.md` is tolerated (section present but empty), matching design spec §5 ("profile.md may not exist yet").

- [ ] **Step 1: Create fixtures**

```markdown
<!-- app/server/test/fixtures/progress/roadmap.md -->
# Master Goal

World-class backend developer.
```

```markdown
<!-- app/server/test/fixtures/reviews/mastering-claude/2026-08-07-hooks-review.md -->
---
score: 80
submission_ref: submissions/mastering-claude/2026-08-07-hooks.md
---

Good grasp of pre/post tool call hooks.
```

(No `profile.md` fixture is created — the test relies on it being absent to prove the tolerant-missing-file behavior.)

- [ ] **Step 2: Write the failing test**

```js
// app/server/test/routes/mentor.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import path from 'node:path';
import express from 'express';
import request from 'supertest';
import { createMentorRouter } from '../../src/routes/mentor.js';

const FIXTURES = path.resolve('test/fixtures');

test('GET mentor-context assembles profile, goals, reviews, and tolerates missing profile.md', async () => {
  const app = express();
  app.use('/api', createMentorRouter(FIXTURES));

  const res = await request(app).get('/api/mentor-context?course=mastering-claude');
  assert.equal(res.status, 200);
  assert.match(res.body.context, /## Goals/);
  assert.match(res.body.context, /World-class backend developer/);
  assert.match(res.body.context, /## Recent Reviews/);
  assert.match(res.body.context, /Good grasp of pre\/post tool call hooks/);
  assert.match(res.body.context, /## Profile/);
});
```

- [ ] **Step 3: Run test to verify it fails**

Run: `npm --prefix app/server test`
Expected: FAIL — module not found.

- [ ] **Step 4: Write `app/server/src/routes/mentor.js`**

```js
import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import { resolveSafe } from '../lib/fsPaths.js';

function readIfExists(absPath) {
  return fs.existsSync(absPath) ? fs.readFileSync(absPath, 'utf8').trim() : '';
}

export function createMentorRouter(learnRoot) {
  const router = express.Router();

  router.get('/mentor-context', (req, res) => {
    const course = req.query.course ?? '';

    const profile = readIfExists(resolveSafe(learnRoot, path.join('progress', 'profile.md')));
    const goals = readIfExists(resolveSafe(learnRoot, path.join('progress', 'roadmap.md')));

    const reviewsDir = resolveSafe(learnRoot, path.join('reviews', course));
    const recentReviews = fs.existsSync(reviewsDir)
      ? fs.readdirSync(reviewsDir)
          .sort()
          .slice(-3)
          .map((f) => readIfExists(path.join(reviewsDir, f)))
          .join('\n\n---\n\n')
      : '';

    const context = [
      '## Profile',
      profile,
      '## Goals',
      goals,
      '## Recent Reviews',
      recentReviews,
    ].join('\n\n');

    res.json({ context });
  });

  return router;
}
```

- [ ] **Step 5: Run test to verify it passes**

Run: `npm --prefix app/server test`
Expected: PASS

- [ ] **Step 6: Mount the router in `app/server/src/index.js`**

```js
// add import:
import { createMentorRouter } from './routes/mentor.js';
// add mount (note: base path '/api', not '/api/courses'):
  app.use('/api', createMentorRouter(LEARN_ROOT));
```

- [ ] **Step 7: Commit**

```bash
git add app/server/src/routes/mentor.js app/server/test/routes/mentor.test.js app/server/test/fixtures/progress app/server/test/fixtures/reviews app/server/src/index.js
git commit -m "feat(server): add mentor context assembly route"
```

---

## Task 10: Frontend scaffold — Vite + React + router + API client

**Files:**
- Create: `app/client/package.json`
- Create: `app/client/vite.config.js`
- Create: `app/client/vitest.config.js`
- Create: `app/client/index.html`
- Create: `app/client/src/main.jsx`
- Create: `app/client/src/App.jsx`
- Create: `app/client/src/api/client.js`
- Create: `app/client/src/test/setup.js`
- Create: `app/client/src/App.test.jsx`

**Interfaces:**
- Produces: `api/client.js` exports `getCourse(course)`, `getLesson(course, moduleSlug)`, `postSubmission(course, payload)`, `getReview(course, slug)`, `getProgress(course)`, `getMentorContext(course)` — all `async`, all calling `fetch` against `API_BASE` (`import.meta.env.VITE_API_BASE ?? 'http://localhost:4000'`) and returning parsed JSON (or throwing on non-2xx via a shared `handle(res)` helper). Every later page task imports from this file — do not call `fetch` directly in components.
- `App.jsx` hardcodes `const COURSE = 'mastering-claude'` (module-level constant) — per MVP single-course scope (design spec §8) — and exports it so page components can import `{ COURSE } from '../App.jsx'`.

- [ ] **Step 1: Create `app/client/package.json`**

```json
{
  "name": "learning-os-client",
  "version": "0.1.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "test": "vitest run"
  },
  "dependencies": {
    "react": "^18.3.1",
    "react-dom": "^18.3.1",
    "react-router-dom": "^6.26.0",
    "react-markdown": "^9.0.1"
  },
  "devDependencies": {
    "@vitejs/plugin-react": "^4.3.1",
    "vite": "^5.4.0",
    "vitest": "^2.0.5",
    "@testing-library/react": "^16.0.0",
    "@testing-library/jest-dom": "^6.4.8",
    "jsdom": "^25.0.0"
  }
}
```

- [ ] **Step 2: Install dependencies**

Run (from `app/client/`): `npm install`

- [ ] **Step 3: Write `app/client/vite.config.js`**

```js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
});
```

- [ ] **Step 4: Write `app/client/vitest.config.js`**

```js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  test: {
    environment: 'jsdom',
    setupFiles: './src/test/setup.js',
    globals: true,
  },
});
```

- [ ] **Step 5: Write `app/client/src/test/setup.js`**

```js
import '@testing-library/jest-dom/vitest';
```

- [ ] **Step 6: Write `app/client/index.html`**

```html
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Learning OS</title>
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.jsx"></script>
  </body>
</html>
```

- [ ] **Step 7: Write `app/client/src/api/client.js`**

```js
const API_BASE = import.meta.env.VITE_API_BASE ?? 'http://localhost:4000';

async function handle(res) {
  if (!res.ok) {
    const body = await res.json().catch(() => ({}));
    const error = new Error(body.error ?? `Request failed: ${res.status}`);
    error.status = res.status;
    error.pending = body.pending ?? false;
    throw error;
  }
  return res.json();
}

export function getCourse(course) {
  return fetch(`${API_BASE}/api/courses/${course}`).then(handle);
}

export function getLesson(course, moduleSlug) {
  return fetch(`${API_BASE}/api/courses/${course}/modules/${moduleSlug}/lesson`).then(handle);
}

export function postSubmission(course, payload) {
  return fetch(`${API_BASE}/api/courses/${course}/submissions`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  }).then(handle);
}

export function getReview(course, slug) {
  return fetch(`${API_BASE}/api/courses/${course}/reviews/${slug}`).then(handle);
}

export function getProgress(course) {
  return fetch(`${API_BASE}/api/courses/${course}/progress`).then(handle);
}

export function getMentorContext(course) {
  return fetch(`${API_BASE}/api/mentor-context?course=${course}`).then(handle);
}
```

- [ ] **Step 8: Write the failing smoke test for `App.jsx`**

```jsx
// app/client/src/App.test.jsx
import { render, screen } from '@testing-library/react';
import { test, expect, vi, beforeEach } from 'vitest';
import App, { COURSE } from './App.jsx';

beforeEach(() => {
  global.fetch = vi.fn(() =>
    Promise.resolve({
      ok: true,
      json: () => Promise.resolve({ roadmap: '', modules: [] }),
    })
  );
});

test('App renders navigation and exports the MVP course constant', () => {
  render(<App />);
  expect(screen.getByRole('link', { name: /dashboard/i })).toBeInTheDocument();
  expect(screen.getByRole('link', { name: /progress/i })).toBeInTheDocument();
  expect(screen.getByRole('link', { name: /mentor/i })).toBeInTheDocument();
  expect(COURSE).toBe('mastering-claude');
});
```

- [ ] **Step 9: Run test to verify it fails**

Run: `npm --prefix app/client test`
Expected: FAIL — `./App.jsx` not found.

- [ ] **Step 10: Write `app/client/src/App.jsx`**

```jsx
import { BrowserRouter, Routes, Route, Link, Navigate } from 'react-router-dom';
import Dashboard from './pages/Dashboard.jsx';
import Lesson from './pages/Lesson.jsx';
import Review from './pages/Review.jsx';
import Progress from './pages/Progress.jsx';
import MentorContext from './pages/MentorContext.jsx';

export const COURSE = 'mastering-claude';

export default function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Dashboard</Link>
        <Link to="/progress">Progress</Link>
        <Link to="/mentor">Mentor</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/lesson/:moduleSlug" element={<Lesson />} />
        <Route path="/review/:slug" element={<Review />} />
        <Route path="/progress" element={<Progress />} />
        <Route path="/mentor" element={<MentorContext />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
}
```

- [ ] **Step 11: Write `app/client/src/main.jsx`**

```jsx
import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App.jsx';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>
);
```

Note: Steps 10–11 reference `pages/Dashboard.jsx`, `pages/Lesson.jsx`, `pages/Review.jsx`, `pages/Progress.jsx`, `pages/MentorContext.jsx`, which don't exist until Tasks 11–15. The smoke test in this task will still fail to import until those files exist — **create minimal stub components first** so this task is independently completable:

```jsx
// app/client/src/pages/Dashboard.jsx (stub, replaced fully in Task 11)
export default function Dashboard() {
  return <div>Dashboard</div>;
}
```
```jsx
// app/client/src/pages/Lesson.jsx (stub, replaced fully in Task 12)
export default function Lesson() {
  return <div>Lesson</div>;
}
```
```jsx
// app/client/src/pages/Review.jsx (stub, replaced fully in Task 13)
export default function Review() {
  return <div>Review</div>;
}
```
```jsx
// app/client/src/pages/Progress.jsx (stub, replaced fully in Task 14)
export default function Progress() {
  return <div>Progress</div>;
}
```
```jsx
// app/client/src/pages/MentorContext.jsx (stub, replaced fully in Task 15)
export default function MentorContext() {
  return <div>Mentor</div>;
}
```

- [ ] **Step 12: Run test to verify it passes**

Run: `npm --prefix app/client test`
Expected: PASS

- [ ] **Step 13: Commit**

```bash
git add app/client
git commit -m "feat(client): scaffold Vite/React app with routing and API client"
```

---

## Task 11: Dashboard page

**Files:**
- Modify: `app/client/src/pages/Dashboard.jsx` (replace stub)
- Create: `app/client/src/pages/Dashboard.test.jsx`

**Interfaces:**
- Consumes: `getCourse`, `getProgress` from `../api/client.js`; `COURSE` from `../App.jsx`.
- Produces: renders "Continue Learning" (first module whose `status !== 'completed'`) and a completion percentage computed as `completedCount / totalCount`.

- [ ] **Step 1: Write the failing test**

```jsx
// app/client/src/pages/Dashboard.test.jsx
import { render, screen, waitFor } from '@testing-library/react';
import { test, expect, vi, beforeEach } from 'vitest';
import { MemoryRouter } from 'react-router-dom';
import Dashboard from './Dashboard.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.spyOn(api, 'getCourse').mockResolvedValue({
    roadmap: '# Roadmap',
    modules: [
      { slug: '01-fondasi', title: 'Fondasi', order: 1, status: 'completed' },
      { slug: '02-token', title: 'Token Economy', order: 2, status: 'in_progress' },
    ],
  });
  vi.spyOn(api, 'getProgress').mockResolvedValue({
    progress: '# Progress',
    skills: { functions: 90 },
  });
});

test('Dashboard shows the first non-completed module and completion percentage', async () => {
  render(
    <MemoryRouter>
      <Dashboard />
    </MemoryRouter>
  );

  await waitFor(() => {
    expect(screen.getByText(/Token Economy/)).toBeInTheDocument();
  });
  expect(screen.getByText(/50%/)).toBeInTheDocument();
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/client test`
Expected: FAIL — stub doesn't fetch or render module data.

- [ ] **Step 3: Write `app/client/src/pages/Dashboard.jsx`**

```jsx
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getCourse, getProgress } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function Dashboard() {
  const [course, setCourse] = useState(null);
  const [progress, setProgress] = useState(null);

  useEffect(() => {
    getCourse(COURSE).then(setCourse);
    getProgress(COURSE).then(setProgress);
  }, []);

  if (!course || !progress) return <p>Loading…</p>;

  const nextModule = course.modules.find((m) => m.status !== 'completed');
  const completedCount = course.modules.filter((m) => m.status === 'completed').length;
  const percent = course.modules.length
    ? Math.round((completedCount / course.modules.length) * 100)
    : 0;

  return (
    <div>
      <h1>Dashboard</h1>
      <section>
        <h2>Continue Learning</h2>
        {nextModule ? (
          <Link to={`/lesson/${nextModule.slug}`}>{nextModule.title}</Link>
        ) : (
          <p>All modules completed.</p>
        )}
      </section>
      <section>
        <h2>Course Completion</h2>
        <p>{percent}%</p>
      </section>
    </div>
  );
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/client test`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add app/client/src/pages/Dashboard.jsx app/client/src/pages/Dashboard.test.jsx
git commit -m "feat(client): implement Dashboard page"
```

---

## Task 12: Lesson page + submit form

**Files:**
- Modify: `app/client/src/pages/Lesson.jsx` (replace stub)
- Create: `app/client/src/pages/Lesson.test.jsx`

**Interfaces:**
- Consumes: `getLesson`, `postSubmission` from `../api/client.js`; `COURSE`; `useParams` for `moduleSlug`; `useNavigate` to go to `/review/:slug` after submit.
- Produces: on successful submit, navigates to `/review/<slug>` where `<slug>` is derived from `moduleSlug` (used as the submission slug for MVP — one submission per module, matching design spec's single-course scope).

- [ ] **Step 1: Write the failing test**

```jsx
// app/client/src/pages/Lesson.test.jsx
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { test, expect, vi, beforeEach } from 'vitest';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import Lesson from './Lesson.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.spyOn(api, 'getLesson').mockResolvedValue({
    data: { title: 'Hooks', module: 'Module 3', order: 2, status: 'in_progress' },
    content: 'Learn about hooks.',
  });
  vi.spyOn(api, 'postSubmission').mockResolvedValue({
    path: 'submissions/mastering-claude/2026-08-09-02-hooks.md',
    data: { lesson: 'Hooks', module: 'Module 3', date: '2026-08-09', status: 'pending' },
  });
});

test('Lesson renders content and submits an answer', async () => {
  const user = userEvent.setup();
  render(
    <MemoryRouter initialEntries={['/lesson/02-hooks']}>
      <Routes>
        <Route path="/lesson/:moduleSlug" element={<Lesson />} />
        <Route path="/review/:slug" element={<div>Review page</div>} />
      </Routes>
    </MemoryRouter>
  );

  await waitFor(() => expect(screen.getByText('Hooks')).toBeInTheDocument());
  expect(screen.getByText(/Learn about hooks/)).toBeInTheDocument();

  await user.type(screen.getByLabelText(/your answer/i), 'My submission text');
  await user.click(screen.getByRole('button', { name: /submit for review/i }));

  await waitFor(() => expect(screen.getByText('Review page')).toBeInTheDocument());
  expect(api.postSubmission).toHaveBeenCalledWith('mastering-claude', {
    slug: '02-hooks',
    lesson: 'Hooks',
    module: 'Module 3',
    content: 'My submission text',
  });
});
```

- [ ] **Step 2: Install `@testing-library/user-event`**

Modify `app/client/package.json` devDependencies to add `"@testing-library/user-event": "^14.5.2"`, then run (from `app/client/`): `npm install`

- [ ] **Step 3: Run test to verify it fails**

Run: `npm --prefix app/client test`
Expected: FAIL — stub doesn't render lesson content or a form.

- [ ] **Step 4: Write `app/client/src/pages/Lesson.jsx`**

```jsx
import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import ReactMarkdown from 'react-markdown';
import { getLesson, postSubmission } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function Lesson() {
  const { moduleSlug } = useParams();
  const navigate = useNavigate();
  const [lesson, setLesson] = useState(null);
  const [answer, setAnswer] = useState('');
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    getLesson(COURSE, moduleSlug).then(setLesson);
  }, [moduleSlug]);

  if (!lesson) return <p>Loading…</p>;

  async function handleSubmit(e) {
    e.preventDefault();
    setSubmitting(true);
    await postSubmission(COURSE, {
      slug: moduleSlug,
      lesson: lesson.data.title,
      module: lesson.data.module,
      content: answer,
    });
    navigate(`/review/${moduleSlug}`);
  }

  return (
    <div>
      <h1>{lesson.data.title}</h1>
      <ReactMarkdown>{lesson.content}</ReactMarkdown>
      <p>Open Playground: <code>playground/mastering-claude/{moduleSlug}.md</code></p>
      <form onSubmit={handleSubmit}>
        <label htmlFor="answer">Your answer</label>
        <textarea
          id="answer"
          value={answer}
          onChange={(e) => setAnswer(e.target.value)}
        />
        <button type="submit" disabled={submitting}>Submit for Review</button>
      </form>
    </div>
  );
}
```

- [ ] **Step 5: Run test to verify it passes**

Run: `npm --prefix app/client test`
Expected: PASS

- [ ] **Step 6: Commit**

```bash
git add app/client/src/pages/Lesson.jsx app/client/src/pages/Lesson.test.jsx app/client/package.json app/client/package-lock.json
git commit -m "feat(client): implement Lesson page with submit-for-review form"
```

---

## Task 13: Review page with waiting/poll state

**Files:**
- Modify: `app/client/src/pages/Review.jsx` (replace stub)
- Create: `app/client/src/pages/Review.test.jsx`

**Interfaces:**
- Consumes: `getReview` from `../api/client.js`; `COURSE`; `useParams` for `slug`.
- Produces: polls `getReview(COURSE, slug)` every 3000ms (via `setInterval`) while the request throws `{ pending: true }` (per Task 6's 404 contract); stops polling and renders the review once it resolves; clears the interval on unmount.

- [ ] **Step 1: Write the failing test**

```jsx
// app/client/src/pages/Review.test.jsx
import { render, screen, waitFor, act } from '@testing-library/react';
import { test, expect, vi, beforeEach, afterEach } from 'vitest';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import Review from './Review.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.useFakeTimers();
});

afterEach(() => {
  vi.useRealTimers();
});

test('Review page polls until the review is ready, then renders it', async () => {
  const pendingError = Object.assign(new Error('pending'), { pending: true, status: 404 });
  const getReview = vi
    .spyOn(api, 'getReview')
    .mockRejectedValueOnce(pendingError)
    .mockResolvedValueOnce({
      data: { score: 85, submission_ref: 'submissions/mastering-claude/x.md' },
      content: 'Great work on hooks.',
    });

  render(
    <MemoryRouter initialEntries={['/review/02-hooks']}>
      <Routes>
        <Route path="/review/:slug" element={<Review />} />
      </Routes>
    </MemoryRouter>
  );

  expect(await screen.findByText(/waiting for review/i)).toBeInTheDocument();
  expect(getReview).toHaveBeenCalledTimes(1);

  await act(async () => {
    vi.advanceTimersByTime(3000);
  });

  await waitFor(() => expect(screen.getByText(/Great work on hooks/)).toBeInTheDocument());
  expect(screen.getByText(/85/)).toBeInTheDocument();
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/client test`
Expected: FAIL — stub doesn't poll or render pending/loaded states.

- [ ] **Step 3: Write `app/client/src/pages/Review.jsx`**

```jsx
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import ReactMarkdown from 'react-markdown';
import { getReview } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function Review() {
  const { slug } = useParams();
  const [review, setReview] = useState(null);

  useEffect(() => {
    let cancelled = false;
    let interval;

    async function poll() {
      if (cancelled) return;
      try {
        const result = await getReview(COURSE, slug);
        if (!cancelled) {
          setReview(result);
          clearInterval(interval);
        }
      } catch (err) {
        if (!err.pending) throw err;
      }
    }

    poll();
    interval = setInterval(poll, 3000);

    return () => {
      cancelled = true;
      clearInterval(interval);
    };
  }, [slug]);

  if (!review) return <p>Waiting for review…</p>;

  return (
    <div>
      <h1>Review</h1>
      <p>Score: {review.data.score}</p>
      <ReactMarkdown>{review.content}</ReactMarkdown>
    </div>
  );
}
```

`interval` is declared with `let` before `poll` so the closure can see it, and assigned via `setInterval` right after the first synchronous `poll()` call — by the time that first call's `await` resolves (a microtask, always after the current synchronous code finishes), `interval` already holds a real id, so `clearInterval(interval)` on success works correctly even for the very first poll.

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/client test`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add app/client/src/pages/Review.jsx app/client/src/pages/Review.test.jsx
git commit -m "feat(client): implement Review page with polling for pending reviews"
```

---

## Task 14: Progress page

**Files:**
- Modify: `app/client/src/pages/Progress.jsx` (replace stub)
- Create: `app/client/src/pages/Progress.test.jsx`

**Interfaces:**
- Consumes: `getProgress` from `../api/client.js`; `COURSE`.
- Produces: renders raw `progress` text and a `skills` bar list (one row per key, `<div style={{width: `${value}%`}}>`).

- [ ] **Step 1: Write the failing test**

```jsx
// app/client/src/pages/Progress.test.jsx
import { render, screen, waitFor } from '@testing-library/react';
import { test, expect, vi, beforeEach } from 'vitest';
import Progress from './Progress.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.spyOn(api, 'getProgress').mockResolvedValue({
    progress: '# Learning Progress\n\nCurrent Module: Module 3',
    skills: { functions: 90, debugging: 42 },
  });
});

test('Progress page renders progress text and skill percentages', async () => {
  render(<Progress />);
  await waitFor(() => {
    expect(screen.getByText(/Current Module: Module 3/)).toBeInTheDocument();
  });
  expect(screen.getByText(/functions/)).toBeInTheDocument();
  expect(screen.getByText(/90%/)).toBeInTheDocument();
  expect(screen.getByText(/debugging/)).toBeInTheDocument();
  expect(screen.getByText(/42%/)).toBeInTheDocument();
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/client test`
Expected: FAIL — stub doesn't fetch or render.

- [ ] **Step 3: Write `app/client/src/pages/Progress.jsx`**

```jsx
import { useEffect, useState } from 'react';
import ReactMarkdown from 'react-markdown';
import { getProgress } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function Progress() {
  const [progress, setProgress] = useState(null);

  useEffect(() => {
    getProgress(COURSE).then(setProgress);
  }, []);

  if (!progress) return <p>Loading…</p>;

  return (
    <div>
      <h1>Progress</h1>
      <ReactMarkdown>{progress.progress}</ReactMarkdown>
      <h2>Skills</h2>
      <ul>
        {Object.entries(progress.skills).map(([name, value]) => (
          <li key={name}>
            {name}: {value}%
          </li>
        ))}
      </ul>
    </div>
  );
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/client test`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add app/client/src/pages/Progress.jsx app/client/src/pages/Progress.test.jsx
git commit -m "feat(client): implement Progress page"
```

---

## Task 15: Mentor Context page

**Files:**
- Modify: `app/client/src/pages/MentorContext.jsx` (replace stub)
- Create: `app/client/src/pages/MentorContext.test.jsx`

**Interfaces:**
- Consumes: `getMentorContext` from `../api/client.js`; `COURSE`.
- Produces: renders the assembled context text in a `<pre>` and a "Copy for Claude Code" button calling `navigator.clipboard.writeText(context)`.

- [ ] **Step 1: Write the failing test**

```jsx
// app/client/src/pages/MentorContext.test.jsx
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { test, expect, vi, beforeEach } from 'vitest';
import MentorContext from './MentorContext.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.spyOn(api, 'getMentorContext').mockResolvedValue({
    context: '## Profile\n\n## Goals\n\nWorld-class backend developer.',
  });
  Object.assign(navigator, { clipboard: { writeText: vi.fn().mockResolvedValue() } });
});

test('MentorContext renders assembled text and copies it on click', async () => {
  const user = userEvent.setup();
  render(<MentorContext />);

  await waitFor(() => {
    expect(screen.getByText(/World-class backend developer/)).toBeInTheDocument();
  });

  await user.click(screen.getByRole('button', { name: /copy for claude code/i }));
  expect(navigator.clipboard.writeText).toHaveBeenCalledWith(
    '## Profile\n\n## Goals\n\nWorld-class backend developer.'
  );
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `npm --prefix app/client test`
Expected: FAIL — stub doesn't fetch, render, or expose a copy button.

- [ ] **Step 3: Write `app/client/src/pages/MentorContext.jsx`**

```jsx
import { useEffect, useState } from 'react';
import { getMentorContext } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function MentorContext() {
  const [context, setContext] = useState(null);

  useEffect(() => {
    getMentorContext(COURSE).then((res) => setContext(res.context));
  }, []);

  if (context === null) return <p>Loading…</p>;

  return (
    <div>
      <h1>Mentor Context</h1>
      <button onClick={() => navigator.clipboard.writeText(context)}>
        Copy for Claude Code
      </button>
      <pre>{context}</pre>
    </div>
  );
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `npm --prefix app/client test`
Expected: PASS

- [ ] **Step 5: Commit**

```bash
git add app/client/src/pages/MentorContext.jsx app/client/src/pages/MentorContext.test.jsx
git commit -m "feat(client): implement Mentor Context page"
```

---

## Task 16: Root workspace wiring, gitignore, README, manual end-to-end check

**Files:**
- Create: `app/package.json`
- Create: `app/.gitignore`
- Create: `app/README.md`
- Modify: `app/server/src/index.js` (verify `LEARN_ROOT` default reaches the real repo root — no code change expected, this task validates it)

**Interfaces:**
- Consumes: `app/server` and `app/client` `dev` scripts (Tasks 1 and 10).
- Produces: a single `npm run dev` from `app/` that starts both.

- [ ] **Step 1: Write `app/package.json`**

```json
{
  "name": "learning-os",
  "version": "0.1.0",
  "private": true,
  "scripts": {
    "dev": "concurrently \"npm:dev:server\" \"npm:dev:client\"",
    "dev:server": "npm --prefix server run dev",
    "dev:client": "npm --prefix client run dev",
    "test": "npm --prefix server test && npm --prefix client test"
  },
  "devDependencies": {
    "concurrently": "^8.2.2"
  }
}
```

- [ ] **Step 2: Install root dependency**

Run (from `app/`): `npm install`

- [ ] **Step 3: Write `app/.gitignore`**

```
node_modules/
dist/
```

- [ ] **Step 4: Write `app/README.md`**

```markdown
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
```

- [ ] **Step 5: Run full test suite from repo root**

Run: `npm --prefix app/server test && npm --prefix app/client test`
Expected: PASS (all tests from Tasks 1–15)

- [ ] **Step 6: Manual smoke test**

Run: `npm --prefix app run dev` (or `cd app && npm run dev`)
Open http://localhost:5173 — expect the Dashboard to load. Since
`courses/mastering-claude/modules/` doesn't exist in the real repo yet (see
plan header note), the Dashboard will show "All modules completed." (empty
modules list) rather than erroring — confirm no console errors, then stop
the dev server (Ctrl+C).

- [ ] **Step 7: Commit**

```bash
git add app/package.json app/package-lock.json app/.gitignore app/README.md
git commit -m "chore(app): wire up root dev/test scripts and document usage"
```

---

## Self-Review Notes

- **Spec coverage:** §2 (submit/review loop) → Tasks 5, 6, 13. §3 (file architecture) → Task file structure section + Tasks 4–9. §4 (data contracts) → Tasks 3–9 route/fixture shapes match verbatim. §5 (AI architecture, no model calls) → enforced by Global Constraints, no task adds any AI/HTTP-to-Anthropic dependency. §6 (user flow) → Tasks 11–15 implement each screen in sequence. §7 (tech stack) → Task 1 and Task 10 package.json choices match exactly (Express, gray-matter, js-yaml, cors, React, react-router-dom, react-markdown, Vite). §8 (MVP scope) → `COURSE` constant hardcodes single-course scope; Knowledge UI, streak, VS Code deep-link, auth are excluded from every task. §9 (open questions) → addressed by explicitly excluding real Module 3 content generation from this plan's scope (see File Structure section) and calling it out again in Task 16's manual smoke test.
- **Placeholder scan:** no TBD/TODO markers; every step has runnable code or an exact command.
- **Type consistency:** route factory signature `create*Router(learnRoot)` is consistent across Tasks 4–9; `getReview`'s rejected-promise-with-`.pending`-flag contract (Task 6 → client.js `handle()` → Task 13's `catch (err) { if (!err.pending) throw err; }`) is consistent between server and client. `COURSE` constant is defined once in `App.jsx` (Task 10) and only ever imported, never redefined, in Tasks 11–15.
