import fs from 'node:fs';
import path from 'node:path';
import matter from 'gray-matter';

export function readMarkdown(absPath) {
  const raw = fs.readFileSync(absPath, 'utf8');
  const parsed = matter(raw);
  return { data: parsed.data, content: parsed.content.trim() };
}

export function writeMarkdown(absPath, { data, content }) {
  fs.mkdirSync(path.dirname(absPath), { recursive: true });
  const output = matter.stringify(`\n${content}\n`, data);
  fs.writeFileSync(absPath, output, 'utf8');
}
