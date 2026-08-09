import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import yaml from 'js-yaml';
import { resolveSafe } from '../lib/fsPaths.js';

export function createProgressRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course/progress', (req, res) => {
    const { course } = req.params;

    const progressPath = resolveSafe(learnRoot, path.join('progress', 'progress.md'));
    const progress = fs.existsSync(progressPath)
      ? fs.readFileSync(progressPath, 'utf8')
      : '';

    const skillsPath = resolveSafe(learnRoot, path.join('progress', course, 'skills.yml'));
    // js-yaml's load() uses DEFAULT_SCHEMA and is safe by default (unlike
    // PyYAML's load()) — it cannot construct arbitrary types.
    const skills = fs.existsSync(skillsPath)
      ? yaml.load(fs.readFileSync(skillsPath, 'utf8')) ?? {}
      : {};

    res.json({ progress, skills });
  });

  return router;
}
