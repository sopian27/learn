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
      const SUFFIX = '-review.md';
      const DATE_PREFIX_LENGTH = 11; // 'YYYY-MM-DD-'
      const matches = entries.filter((f) => {
        if (!f.endsWith(SUFFIX)) return false;
        return f.slice(0, -SUFFIX.length).slice(DATE_PREFIX_LENGTH) === slug;
      });

      if (matches.length === 0) {
        res.status(404).json({ pending: true });
        return;
      }

      const latest = matches.sort().at(-1);
      const { data, content } = readMarkdown(path.join(dirAbsPath, latest));
      res.json({ data, content });
    } catch (err) {
      res.status(400).json({ error: err.message });
    }
  });

  return router;
}
