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

test('GET course rejects a course containing an encoded traversal segment', async () => {
  const res = await request(buildApp()).get('/api/courses/..%2Fsecrets');
  assert.equal(res.status, 400);
});
