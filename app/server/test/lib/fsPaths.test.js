import test from 'node:test';
import assert from 'node:assert/strict';
import path from 'node:path';
import { resolveSafe } from '../../src/lib/fsPaths.js';

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
