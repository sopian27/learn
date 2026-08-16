import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { test, expect, vi, beforeEach } from 'vitest';
import MentorContext from './MentorContext.jsx';
import * as api from '../api/client.js';

beforeEach(() => {
  vi.spyOn(api, 'getMentorContext').mockResolvedValue({
    context: '## Profile\n\n## Goals\n\nWorld-class backend developer.',
  });
});

test('MentorContext renders assembled text and copies it on click', async () => {
  // userEvent.setup() installs its own navigator.clipboard stub (overwriting any
  // pre-existing mock), so the writeText spy must be attached after this call.
  const user = userEvent.setup();
  vi.spyOn(navigator.clipboard, 'writeText').mockResolvedValue();

  render(<MentorContext />);

  await waitFor(() => {
    expect(screen.getByText(/World-class backend developer/)).toBeInTheDocument();
  });

  await user.click(screen.getByRole('button', { name: /copy for claude code/i }));
  expect(navigator.clipboard.writeText).toHaveBeenCalledWith(
    '## Profile\n\n## Goals\n\nWorld-class backend developer.'
  );
});

test('MentorContext shows an error message when fetching context fails', async () => {
  vi.spyOn(api, 'getMentorContext').mockRejectedValue(new Error('network error'));

  render(<MentorContext />);

  await waitFor(() => {
    expect(
      screen.getByText(/something went wrong loading your mentor context/i)
    ).toBeInTheDocument();
  });
});
