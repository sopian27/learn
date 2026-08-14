// app/server/test/routes/mentor.test.js
import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import os from 'node:os';
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
  assert.match(res.body.context, /## Skills/);
  assert.match(res.body.context, /functions: 90%/);
});

test('GET mentor-context tolerates a missing course (empty string default)', async () => {
  const app = express();
  app.use('/api', createMentorRouter(FIXTURES));

  const res = await request(app).get('/api/mentor-context');
  assert.equal(res.status, 200);
  assert.match(res.body.context, /## Profile/);
});

test('GET mentor-context rejects an encoded traversal in ?course= and does not leak sibling directories', async () => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  fs.mkdirSync(path.join(root, 'secrets'), { recursive: true });
  fs.writeFileSync(
    path.join(root, 'secrets', '0001-secret.md'),
    'TOP SECRET CONTENTS',
    'utf8'
  );

  const app = express();
  app.use('/api', createMentorRouter(root));

  const res = await request(app).get(
    `/api/mentor-context?course=${encodeURIComponent('../secrets')}`
  );

  assert.equal(res.status, 400);
  assert.doesNotMatch(JSON.stringify(res.body), /TOP SECRET/);
});

test('GET mentor-context rejects a course containing a literal slash', async () => {
  const app = express();
  app.use('/api', createMentorRouter(FIXTURES));

  const res = await request(app).get(
    `/api/mentor-context?course=${encodeURIComponent('foo/bar')}`
  );
  assert.equal(res.status, 400);
});
