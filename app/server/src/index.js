import express from 'express';
import cors from 'cors';
import { fileURLToPath } from 'node:url';
import { PORT, LEARN_ROOT } from './config.js';
import { createLessonsRouter } from './routes/lessons.js';

export function createApp() {
  const app = express();
  app.use(cors());
  app.use(express.json());

  app.get('/api/health', (req, res) => {
    res.json({ status: 'ok' });
  });

  app.use('/api/courses', createLessonsRouter(LEARN_ROOT));

  return app;
}

if (process.argv[1] === fileURLToPath(import.meta.url)) {
  const app = createApp();
  app.listen(PORT, () => {
    console.log(`learning-os-server listening on http://localhost:${PORT}`);
  });
}
