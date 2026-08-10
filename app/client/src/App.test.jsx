// app/client/src/App.test.jsx
import { render, screen } from '@testing-library/react';
import { test, expect, vi, beforeEach } from 'vitest';
import App, { COURSE } from './App.jsx';

beforeEach(() => {
  global.fetch = vi.fn(() =>
    Promise.resolve({
      ok: true,
      json: () => Promise.resolve({ roadmap: '', modules: [] }),
    })
  );
});

test('App renders navigation and exports the MVP course constant', () => {
  render(<App />);
  expect(screen.getByRole('link', { name: /dashboard/i })).toBeInTheDocument();
  expect(screen.getByRole('link', { name: /progress/i })).toBeInTheDocument();
  expect(screen.getByRole('link', { name: /mentor/i })).toBeInTheDocument();
  expect(COURSE).toBe('mastering-claude');
});
