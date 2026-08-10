// app/client/src/App.test.jsx
import { render, screen, waitFor } from '@testing-library/react';
import { test, expect, vi, beforeEach } from 'vitest';
import App, { COURSE } from './App.jsx';
import * as api from './api/client.js';

beforeEach(() => {
  global.fetch = vi.fn(() =>
    Promise.resolve({
      ok: true,
      json: () => Promise.resolve({ roadmap: '', modules: [] }),
    })
  );
  vi.spyOn(api, 'getCourse').mockResolvedValue({ roadmap: '', modules: [] });
  vi.spyOn(api, 'getProgress').mockResolvedValue({ progress: '', skills: {} });
});

test('App renders navigation and exports the MVP course constant', async () => {
  render(<App />);
  expect(screen.getByRole('link', { name: /dashboard/i })).toBeInTheDocument();
  expect(screen.getByRole('link', { name: /progress/i })).toBeInTheDocument();
  expect(screen.getByRole('link', { name: /mentor/i })).toBeInTheDocument();
  expect(COURSE).toBe('mastering-claude');

  // Flush Dashboard's data-fetching effect (mounted at "/") so its state
  // updates resolve inside this test's act() scope instead of leaking into
  // the next test/cleanup cycle.
  await waitFor(() => {
    expect(screen.getByText(/all modules completed/i)).toBeInTheDocument();
  });
});
