import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import { resolveSafe } from '../lib/fsPaths.js';
import { readMarkdown } from '../lib/markdown.js';

export function createReviewsRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course/reviews/:slug', (req, res) => {
    const { course, slug } = req.params;
    const dirRelPath = path.join('reviews', course);

    try {
      const dirAbsPath = resolveSafe(learnRoot, dirRelPath);
      const entries = fs.existsSync(dirAbsPath) ? fs.readdirSync(dirAbsPath) : [];
      const match = entries.find((f) => f.endsWith(`-${slug}-review.md`));

      if (!match) {
        res.status(404).json({ pending: true });
        return;
      }

      const { data, content } = readMarkdown(path.join(dirAbsPath, match));
      res.json({ data, content });
    } catch (err) {
      res.status(400).json({ error: err.message });
    }
  });

  return router;
}
