import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import yaml from 'js-yaml';
import { resolveSafe, assertSegment } from '../lib/fsPaths.js';

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

    try {
      assertSegment('course', course, { allowEmpty: true });
    } catch (err) {
      res.status(400).json({ error: err.message });
      return;
    }

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

    let skillsText = '';
    try {
      const skillsPath = resolveSafe(learnRoot, path.join('progress', course, 'skills.yml'));
      if (fs.existsSync(skillsPath)) {
        // js-yaml's load() uses DEFAULT_SCHEMA and is safe by default (unlike
        // PyYAML's load()) — it cannot construct arbitrary types.
        const skills = yaml.load(fs.readFileSync(skillsPath, 'utf8')) ?? {};
        skillsText = Object.entries(skills)
          .map(([name, value]) => `- ${name}: ${value}%`)
          .join('\n');
      }
    } catch {
      // resolveSafe rejection or malformed YAML — degrade to no skills data.
    }

    const context = [
      '## Profile',
      profile,
      '## Goals',
      goals,
      '## Recent Reviews',
      recentReviews,
      '## Skills',
      skillsText,
    ].join('\n\n');

    res.json({ context });
  });

  return router;
}
