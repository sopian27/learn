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

test('GET review does not match a different slug that shares a hyphenated suffix', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  writeMarkdown(
    path.join(root, 'reviews/mastering-claude/2026-08-09-custom-hooks-review.md'),
    { data: { score: 70 }, content: 'Review for the custom-hooks lesson.' }
  );

  const res = await request(buildApp(root)).get(
    '/api/courses/mastering-claude/reviews/hooks'
  );
  assert.equal(res.status, 404);
  assert.equal(res.body.pending, true);
});

test('GET review returns the latest when multiple reviews exist for the same slug', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  writeMarkdown(
    path.join(root, 'reviews/mastering-claude/2026-08-07-hooks-review.md'),
    { data: { score: 60 }, content: 'Older review.' }
  );
  writeMarkdown(
    path.join(root, 'reviews/mastering-claude/2026-08-09-hooks-review.md'),
    { data: { score: 90 }, content: 'Newer review.' }
  );

  const res = await request(buildApp(root)).get(
    '/api/courses/mastering-claude/reviews/hooks'
  );
  assert.equal(res.status, 200);
  assert.equal(res.body.data.score, 90);
});
