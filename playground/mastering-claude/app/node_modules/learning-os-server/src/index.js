import express from 'express';
import cors from 'cors';
import { fileURLToPath } from 'node:url';
import { PORT, LEARN_ROOT } from './config.js';
import { createLessonsRouter } from './routes/lessons.js';
import { createSubmissionsRouter } from './routes/submissions.js';
import { createReviewsRouter } from './routes/reviews.js';
import { createProgressRouter } from './routes/progress.js';
import { createCoursesRouter } from './routes/courses.js';
import { createMentorRouter } from './routes/mentor.js';

export function createApp() {
  const app = express();
  app.use(cors({ origin: 'http://localhost:5173' }));
  app.use(express.json());

  app.get('/api/health', (req, res) => {
    res.json({ status: 'ok' });
  });

  app.use('/api/courses', createLessonsRouter(LEARN_ROOT));
  app.use('/api/courses', createSubmissionsRouter(LEARN_ROOT));
  app.use('/api/courses', createReviewsRouter(LEARN_ROOT));
  app.use('/api/courses', createProgressRouter(LEARN_ROOT));
  app.use('/api/courses', createCoursesRouter(LEARN_ROOT));
  app.use('/api', createMentorRouter(LEARN_ROOT));

  return app;
}

if (process.argv[1] === fileURLToPath(import.meta.url)) {
  const app = createApp();
  app.listen(PORT, () => {
    console.log(`learning-os-server listening on http://localhost:${PORT}`);
  });
}
