import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import { resolveSafe } from '../lib/fsPaths.js';

function readIfExists(absPath) {
  try {
    return fs.existsSync(absPath) ? fs.readFileSync(absPath, 'utf8').trim() : '';
  } catch {
    return '';
  }
}

export function createMentorRouter(learnRoot) {
  const router = express.Router();

  router.get('/mentor-context', (req, res) => {
    const course = req.query.course ?? '';

    let profile = '';
    try {
      profile = readIfExists(resolveSafe(learnRoot, path.join('progress', 'profile.md')));
    } catch {
      // resolveSafe rejection — degrade to empty profile.
    }

    let goals = '';
    try {
      goals = readIfExists(resolveSafe(learnRoot, path.join('progress', 'roadmap.md')));
    } catch {
      // resolveSafe rejection — degrade to empty goals.
    }

    let recentReviews = '';
    try {
      const reviewsDir = resolveSafe(learnRoot, path.join('reviews', course));
      recentReviews = fs.existsSync(reviewsDir)
        ? fs.readdirSync(reviewsDir)
            .sort()
            .slice(-3)
            .map((f) => readIfExists(path.join(reviewsDir, f)))
            .join('\n\n---\n\n')
        : '';
    } catch {
      // resolveSafe rejection (e.g. a crafted course query param) — degrade
      // to no recent reviews rather than erroring.
    }

    const context = [
      '## Profile',
      profile,
      '## Goals',
      goals,
      '## Recent Reviews',
      recentReviews,
    ].join('\n\n');

    res.json({ context });
  });

  return router;
}
