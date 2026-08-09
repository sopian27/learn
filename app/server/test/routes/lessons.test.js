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
