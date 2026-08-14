import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import yaml from 'js-yaml';
import { resolveSafe, assertSegment } from '../lib/fsPaths.js';

export function createProgressRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course/progress', (req, res) => {
    const { course } = req.params;

    try {
      assertSegment('course', course);
    } catch (err) {
      res.status(400).json({ error: err.message });
      return;
    }

    let progress = '';
    try {
      const progressPath = resolveSafe(learnRoot, path.join('progress', 'progress.md'));
      if (fs.existsSync(progressPath)) {
        progress = fs.readFileSync(progressPath, 'utf8');
      }
    } catch {
      // resolveSafe rejection or read failure — degrade to empty progress
      // rather than erroring; this route never fails the client.
    }

    let skills = {};
    try {
      const skillsPath = resolveSafe(learnRoot, path.join('progress', course, 'skills.yml'));
      if (fs.existsSync(skillsPath)) {
        // js-yaml's load() uses DEFAULT_SCHEMA and is safe by default (unlike
        // PyYAML's load()) — it cannot construct arbitrary types.
        skills = yaml.load(fs.readFileSync(skillsPath, 'utf8')) ?? {};
      }
    } catch {
      // resolveSafe rejection or malformed YAML — degrade to an empty skills
      // map rather than erroring; a corrupt local file shouldn't 500 the route.
    }

    res.json({ progress, skills });
  });

  return router;
}
