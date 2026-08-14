import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import os from 'node:os';
import path from 'node:path';
import { readMarkdown, writeMarkdown } from '../../src/lib/markdown.js';

test('readMarkdown parses frontmatter and content', () => {
  const fixture = path.resolve('test/fixtures/courses/mastering-claude/modules/01-fondasi/lesson.md');
  const { data, content } = readMarkdown(fixture);
  assert.equal(data.title, 'Fondasi Mental Model & Prompting');
  assert.equal(data.order, 1);
  assert.match(content, /Understand the base mental model/);
});

test('readMarkdown throws ENOENT for missing file', () => {
  assert.throws(
    () => readMarkdown('/nonexistent/file.md'),
    (err) => err.code === 'ENOENT'
  );
});

test('writeMarkdown writes frontmatter + content and creates dirs', () => {
  const tmpDir = fs.mkdtempSync(path.join(os.tmpdir(), 'learning-os-'));
  const target = path.join(tmpDir, 'nested', 'file.md');

  writeMarkdown(target, { data: { status: 'pending' }, content: 'Body text' });

  const { data, content } = readMarkdown(target);
  assert.equal(data.status, 'pending');
  assert.match(content, /Body text/);
});
