import express from 'express';
import path from 'node:path';
import { resolveSafe, assertSegment } from '../lib/fsPaths.js';
import { writeMarkdown } from '../lib/markdown.js';

export function createSubmissionsRouter(learnRoot) {
  const router = express.Router();

  router.post('/:course/submissions', (req, res) => {
    const { course } = req.params;
    const { slug, lesson, module, content, date } = req.body;

    if (!slug || !lesson || !module || !content) {
      res.status(400).json({ error: 'slug, lesson, module, content are required' });
      return;
    }

    const submissionDate = date ?? new Date().toISOString().slice(0, 10);

    try {
      assertSegment('course', course);
      assertSegment('slug', slug);
      assertSegment('date', submissionDate);

      const relPath = path.join(
        'submissions', course, `${submissionDate}-${slug}.md`
      );
      const absPath = resolveSafe(learnRoot, relPath);

      const data = { lesson, module, date: submissionDate, status: 'pending' };
      writeMarkdown(absPath, { data, content });
      res.status(201).json({ path: relPath, data });
    } catch (err) {
      res.status(err.status ?? 400).json({ error: err.message });
    }
  });

  return router;
}
