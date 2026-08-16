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

test('POST submission returns 400 when a required field is missing', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));

  const res = await request(buildApp(root))
    .post('/api/courses/mastering-claude/submissions')
    .send({ lesson: 'L', module: 'M', content: 'C' }); // slug missing

  assert.equal(res.status, 400);
});

test('POST submission rejects a slug that attempts path traversal', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));

  const res = await request(buildApp(root))
    .post('/api/courses/mastering-claude/submissions')
    .send({
      slug: '../../../../courses/mastering-claude/modules/foo/lesson',
      lesson: 'L',
      module: 'M',
      content: 'malicious overwrite attempt',
    });

  assert.equal(res.status, 400);
  assert.equal(
    fs.existsSync(path.join(root, 'courses/mastering-claude/modules/foo/lesson.md')),
    false
  );
});

test('POST submission rejects a date that attempts path traversal', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));

  const res = await request(buildApp(root))
    .post('/api/courses/mastering-claude/submissions')
    .send({
      slug: 'hooks',
      lesson: 'L',
      module: 'M',
      content: 'malicious overwrite attempt',
      date: '../../../../evil',
    });

  assert.equal(res.status, 400);
  assert.equal(fs.existsSync(path.join(root, 'evil.md')), false);
});

test('POST submission rejects a course containing an encoded traversal segment and does not escape submissions/', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  fs.mkdirSync(path.join(root, 'secrets'), { recursive: true });

  // The literal request path segment is "..%2Fsecrets" — Express decodes
  // %2F into a literal "/" when populating req.params.course, so the
  // route still sees this as a single URL path segment even though the
  // decoded param value contains "../secrets".
  const res = await request(buildApp(root))
    .post('/api/courses/..%2Fsecrets/submissions')
    .send({
      slug: 'file',
      lesson: 'L',
      module: 'M',
      content: 'malicious overwrite attempt',
      date: 'a',
    });

  assert.equal(res.status, 400);
  assert.equal(fs.existsSync(path.join(root, 'secrets', 'a-file.md')), false);
});
