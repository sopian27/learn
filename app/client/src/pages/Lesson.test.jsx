import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { test, expect, vi, beforeEach } from 'vitest';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import Lesson from './Lesson.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.spyOn(api, 'getLesson').mockResolvedValue({
    data: { title: 'Hooks', module: 'Module 3', order: 2, status: 'in_progress' },
    content: 'Learn about hooks.',
  });
  vi.spyOn(api, 'postSubmission').mockResolvedValue({
    path: 'submissions/mastering-claude/2026-08-09-02-hooks.md',
    data: { lesson: 'Hooks', module: 'Module 3', date: '2026-08-09', status: 'pending' },
  });
});

test('Lesson renders content and submits an answer', async () => {
  const user = userEvent.setup();
  render(
    <MemoryRouter initialEntries={['/lesson/02-hooks']}>
      <Routes>
        <Route path="/lesson/:moduleSlug" element={<Lesson />} />
        <Route path="/review/:slug" element={<div>Review page</div>} />
      </Routes>
    </MemoryRouter>
  );

  await waitFor(() => expect(screen.getByText('Hooks')).toBeInTheDocument());
  expect(screen.getByText(/Learn about hooks/)).toBeInTheDocument();

  await user.type(screen.getByLabelText(/your answer/i), 'My submission text');
  await user.click(screen.getByRole('button', { name: /submit for review/i }));

  await waitFor(() => expect(screen.getByText('Review page')).toBeInTheDocument());
  expect(api.postSubmission).toHaveBeenCalledWith('mastering-claude', {
    slug: '02-hooks',
    lesson: 'Hooks',
    module: 'Module 3',
    content: 'My submission text',
  });
});

test('Lesson shows an error message when fetching the lesson fails', async () => {
  vi.spyOn(api, 'getLesson').mockRejectedValue(new Error('network error'));

  render(
    <MemoryRouter initialEntries={['/lesson/02-hooks']}>
      <Routes>
        <Route path="/lesson/:moduleSlug" element={<Lesson />} />
        <Route path="/review/:slug" element={<div>Review page</div>} />
      </Routes>
    </MemoryRouter>
  );

  await waitFor(() => {
    expect(screen.getByText(/something went wrong loading this lesson/i)).toBeInTheDocument();
  });
});

test('Lesson shows a submission error and re-enables submit when postSubmission fails', async () => {
  vi.spyOn(api, 'postSubmission').mockRejectedValue(new Error('network error'));
  const user = userEvent.setup();

  render(
    <MemoryRouter initialEntries={['/lesson/02-hooks']}>
      <Routes>
        <Route path="/lesson/:moduleSlug" element={<Lesson />} />
        <Route path="/review/:slug" element={<div>Review page</div>} />
      </Routes>
    </MemoryRouter>
  );

  await waitFor(() => expect(screen.getByText('Hooks')).toBeInTheDocument());

  await user.type(screen.getByLabelText(/your answer/i), 'My submission text');
  const submitButton = screen.getByRole('button', { name: /submit for review/i });
  await user.click(submitButton);

  await waitFor(() => {
    expect(screen.getByText(/something went wrong submitting your answer/i)).toBeInTheDocument();
  });
  expect(submitButton).not.toBeDisabled();
  expect(screen.queryByText('Review page')).not.toBeInTheDocument();
});
