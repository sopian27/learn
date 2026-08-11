// app/client/src/pages/Review.test.jsx
import { render, screen, waitFor, act } from '@testing-library/react';
import { test, expect, vi, beforeEach, afterEach } from 'vitest';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import Review from './Review.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.useFakeTimers();
});

afterEach(() => {
  vi.useRealTimers();
});

test('Review page polls until the review is ready, then renders it', async () => {
  const pendingError = Object.assign(new Error('pending'), { pending: true, status: 404 });
  const getReview = vi
    .spyOn(api, 'getReview')
    .mockRejectedValueOnce(pendingError)
    .mockResolvedValueOnce({
      data: { score: 85, submission_ref: 'submissions/mastering-claude/x.md' },
      content: 'Great work on hooks.',
    });

  render(
    <MemoryRouter initialEntries={['/review/02-hooks']}>
      <Routes>
        <Route path="/review/:slug" element={<Review />} />
      </Routes>
    </MemoryRouter>
  );

  expect(await screen.findByText(/waiting for review/i)).toBeInTheDocument();
  expect(getReview).toHaveBeenCalledTimes(1);

  await act(async () => {
    vi.advanceTimersByTime(3000);
  });

  await waitFor(() => expect(screen.getByText(/Great work on hooks/)).toBeInTheDocument());
  expect(screen.getByText(/85/)).toBeInTheDocument();
});
