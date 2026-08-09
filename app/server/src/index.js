import express from 'express';
import cors from 'cors';
import { PORT } from './config.js';

export function createApp() {
  const app = express();
  app.use(cors());
  app.use(express.json());

  app.get('/api/health', (req, res) => {
    res.json({ status: 'ok' });
  });

  return app;
}

if (import.meta.url === `file://${process.argv[1]}`) {
  const app = createApp();
  app.listen(PORT, () => {
    console.log(`learning-os-server listening on http://localhost:${PORT}`);
  });
}
