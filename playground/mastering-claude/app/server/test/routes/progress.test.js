import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import os from 'node:os';
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

test('GET progress rejects a course containing an encoded traversal segment', async () => {
  const res = await request(buildApp(FIXTURES)).get(
    '/api/courses/..%2Fsecrets/progress'
  );
  assert.equal(res.status, 400);
});

test('GET progress degrades to empty skills when skills.yml is malformed', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  fs.mkdirSync(path.join(root, 'progress', 'broken-skills-course'), { recursive: true });
  fs.writeFileSync(
    path.join(root, 'progress', 'broken-skills-course', 'skills.yml'),
    ': this is not : valid : yaml : [', // deliberately malformed
    'utf8'
  );

  const res = await request(buildApp(root)).get(
    '/api/courses/broken-skills-course/progress'
  );
  assert.equal(res.status, 200);
  assert.deepEqual(res.body.skills, {});
});
