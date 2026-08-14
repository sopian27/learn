import { render, screen, waitFor } from '@testing-library/react';
import { test, expect, vi, beforeEach } from 'vitest';
import Progress from './Progress.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.spyOn(api, 'getProgress').mockResolvedValue({
    progress: '# Learning Progress\n\nCurrent Module: Module 3',
    skills: { functions: 90, debugging: 42 },
  });
});

test('Progress page renders progress text and skill percentages', async () => {
  render(<Progress />);
  await waitFor(() => {
    expect(screen.getByText(/Current Module: Module 3/)).toBeInTheDocument();
  });
  expect(screen.getByText(/functions/)).toBeInTheDocument();
  expect(screen.getByText(/90%/)).toBeInTheDocument();
  expect(screen.getByText(/debugging/)).toBeInTheDocument();
  expect(screen.getByText(/42%/)).toBeInTheDocument();
});

test('Progress page shows an error message when fetching progress fails', async () => {
  vi.spyOn(api, 'getProgress').mockRejectedValue(new Error('network error'));

  render(<Progress />);

  await waitFor(() => {
    expect(screen.getByText(/something went wrong loading your progress/i)).toBeInTheDocument();
  });
});
