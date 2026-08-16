import path from 'node:path';
import { fileURLToPath } from 'node:url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export const LEARN_ROOT =
  process.env.LEARN_ROOT ?? path.resolve(__dirname, '../../..');

export const PORT = Number(process.env.PORT ?? 4000);
