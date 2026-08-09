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
