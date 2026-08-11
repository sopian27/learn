import '@testing-library/jest-dom/vitest';
import { vi } from 'vitest';

// @testing-library/react's internal `asyncWrapper` (used by findBy*/waitFor)
// drains the microtask queue via a real `setTimeout(0)` after every check.
// It only knows to nudge that along with a fake-timer tick when it detects a
// `jest`-like global (`typeof jest !== 'undefined'`). Under Vitest there is
// no such global by default, so any test using `vi.useFakeTimers()` together
// with `findBy*`/`waitFor` deadlocks forever waiting on that drain timer.
// Aliasing `jest` to `vi` here is the standard, no-op-when-unused workaround:
// it only changes behavior for tests that actually enable fake timers.
globalThis.jest = vi;
