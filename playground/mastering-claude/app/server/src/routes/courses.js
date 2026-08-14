import express from 'express';
import fs from 'node:fs';
import path from 'node:path';
import { resolveSafe, assertSegment } from '../lib/fsPaths.js';
import { readMarkdown } from '../lib/markdown.js';

export function createCoursesRouter(learnRoot) {
  const router = express.Router();

  router.get('/:course', (req, res) => {
    const { course } = req.params;

    try {
      assertSegment('course', course);
    } catch (err) {
      res.status(400).json({ error: err.message });
      return;
    }

    let roadmap;
    try {
      const roadmapPath = resolveSafe(learnRoot, path.join('courses', course, 'roadmap.md'));
      roadmap = fs.readFileSync(roadmapPath, 'utf8');
    } catch (err) {
      res.status(404).json({ error: 'Course not found' });
      return;
    }

    const modulesDir = resolveSafe(learnRoot, path.join('courses', course, 'modules'));
    const modules = [];

    if (fs.existsSync(modulesDir)) {
      for (const slug of fs.readdirSync(modulesDir)) {
        const lessonPath = path.join(modulesDir, slug, 'lesson.md');
        if (!fs.existsSync(lessonPath)) continue;
        const { data } = readMarkdown(lessonPath);
        modules.push({ slug, title: data.title, order: data.order, status: data.status });
      }
    }

    modules.sort((a, b) => a.order - b.order);
    res.json({ roadmap, modules });
  });

  return router;
}
