// app/client/src/api/client.test.js
//
// Every other client test mocks the whole `api` module (vi.spyOn(api, ...))
// or shadows the api functions directly, so handle() — the shared response
// handler inside client.js that turns a non-2xx response into a thrown
// Error with `.status` and `.pending` set — never actually executes in any
// existing test. Review.jsx's entire polling loop depends on `.pending`
// being set correctly, so this file mocks global.fetch directly to exercise
// handle() for real.
import { test, expect, vi, beforeEach, afterEach } from 'vitest';
import { getCourse, getReview } from './client.js';

beforeEach(() => {
  vi.stubGlobal('fetch', vi.fn());
});

afterEach(() => {
  vi.unstubAllGlobals();
});

test('resolves with parsed JSON on a successful response', async () => {
  fetch.mockResolvedValue({
    ok: true,
    status: 200,
    json: () => Promise.resolve({ roadmap: '# Roadmap', modules: [] }),
  });

  const result = await getCourse('mastering-claude');

  expect(result).toEqual({ roadmap: '# Roadmap', modules: [] });
});

test('throws an Error with the server message and status on a non-2xx error body', async () => {
  fetch.mockResolvedValue({
    ok: false,
    status: 400,
    json: () => Promise.resolve({ error: 'Invalid course' }),
  });

  await expect(getCourse('..%2Fsecrets')).rejects.toMatchObject({
    message: 'Invalid course',
    status: 400,
    pending: false,
  });
});

test('throws an Error with .pending set on a 404 {pending:true} body', async () => {
  fetch.mockResolvedValue({
    ok: false,
    status: 404,
    json: () => Promise.resolve({ pending: true }),
  });

  await expect(getReview('mastering-claude', 'hooks')).rejects.toMatchObject({
    status: 404,
    pending: true,
  });
});
