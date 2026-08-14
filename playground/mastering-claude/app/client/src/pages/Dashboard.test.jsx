import { render, screen, waitFor } from '@testing-library/react';
import { test, expect, vi, beforeEach } from 'vitest';
import { MemoryRouter } from 'react-router-dom';
import Dashboard from './Dashboard.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.spyOn(api, 'getCourse').mockResolvedValue({
    roadmap: '# Roadmap',
    modules: [
      { slug: '01-fondasi', title: 'Fondasi', order: 1, status: 'completed' },
      { slug: '02-token', title: 'Token Economy', order: 2, status: 'in_progress' },
    ],
  });
  vi.spyOn(api, 'getProgress').mockResolvedValue({
    progress: '# Progress',
    skills: { functions: 90 },
  });
});

test('Dashboard shows the first non-completed module and completion percentage', async () => {
  render(
    <MemoryRouter>
      <Dashboard />
    </MemoryRouter>
  );

  await waitFor(() => {
    expect(screen.getByText(/Token Economy/)).toBeInTheDocument();
  });
  expect(screen.getByText(/50%/)).toBeInTheDocument();
});

test('Dashboard shows an error message when fetching course data fails', async () => {
  vi.spyOn(api, 'getCourse').mockRejectedValue(new Error('network error'));

  render(
    <MemoryRouter>
      <Dashboard />
    </MemoryRouter>
  );

  await waitFor(() => {
    expect(screen.getByText(/something went wrong loading the dashboard/i)).toBeInTheDocument();
  });
});
