import test from 'node:test';
import assert from 'node:assert/strict';
import path from 'node:path';
import { resolveSafe, assertSegment } from '../../src/lib/fsPaths.js';

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

test('assertSegment accepts a plain alphanumeric segment', () => {
  assert.equal(assertSegment('course', 'mastering-claude'), 'mastering-claude');
});

test('assertSegment accepts dots and underscores after the first character', () => {
  assert.equal(assertSegment('date', '2026-08-09'), '2026-08-09');
});

test('assertSegment rejects a segment containing a traversal sequence', () => {
  assert.throws(
    () => assertSegment('course', '../secrets'),
    /Invalid course/
  );
});

test('assertSegment rejects a segment containing a forward slash', () => {
  assert.throws(
    () => assertSegment('slug', 'foo/bar'),
    /Invalid slug/
  );
});

test('assertSegment rejects a segment containing a backslash', () => {
  assert.throws(
    () => assertSegment('slug', 'foo\\bar'),
    /Invalid slug/
  );
});

test('assertSegment rejects the empty string by default', () => {
  assert.throws(() => assertSegment('course', ''), /Invalid course/);
});

test('assertSegment allows the empty string when allowEmpty is set', () => {
  assert.equal(assertSegment('course', '', { allowEmpty: true }), '');
});

test('assertSegment still rejects traversal when allowEmpty is set', () => {
  assert.throws(
    () => assertSegment('course', '../secrets', { allowEmpty: true }),
    /Invalid course/
  );
});

test('assertSegment sets status 400 on the thrown error', () => {
  try {
    assertSegment('course', '../secrets');
    assert.fail('expected assertSegment to throw');
  } catch (err) {
    assert.equal(err.status, 400);
  }
});
