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
