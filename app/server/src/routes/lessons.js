import express from 'express';
import path from 'node:path';
import { resolveSafe } from '../lib/fsPaths.js';
import { readMarkdown } from '../lib/markdown.js';

export function createLessonsRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course/modules/:moduleSlug/lesson', (req, res) => {
    const { course, moduleSlug } = req.params;
    const relPath = path.join(
      'courses', course, 'modules', moduleSlug, 'lesson.md'
    );

    try {
      const absPath = resolveSafe(learnRoot, relPath);
      const { data, content } = readMarkdown(absPath);
      res.json({ data, content });
    } catch (err) {
      if (err.code === 'ENOENT') {
        res.status(404).json({ error: 'Lesson not found' });
      } else {
        res.status(400).json({ error: err.message });
      }
    }
  });

  return router;
}
