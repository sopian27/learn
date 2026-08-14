const API_BASE = import.meta.env.VITE_API_BASE ?? 'http://localhost:4000';

async function handle(res) {
  if (!res.ok) {
    const body = await res.json().catch(() => ({}));
    const error = new Error(body.error ?? `Request failed: ${res.status}`);
    error.status = res.status;
    error.pending = body.pending ?? false;
    throw error;
  }
  return res.json();
}

export function getCourse(course) {
  return fetch(`${API_BASE}/api/courses/${course}`).then(handle);
}

export function getLesson(course, moduleSlug) {
  return fetch(`${API_BASE}/api/courses/${course}/modules/${moduleSlug}/lesson`).then(handle);
}

export function postSubmission(course, payload) {
  return fetch(`${API_BASE}/api/courses/${course}/submissions`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  }).then(handle);
}

export function getReview(course, slug) {
  return fetch(`${API_BASE}/api/courses/${course}/reviews/${slug}`).then(handle);
}

export function getProgress(course) {
  return fetch(`${API_BASE}/api/courses/${course}/progress`).then(handle);
}

export function getMentorContext(course) {
  return fetch(`${API_BASE}/api/mentor-context?course=${course}`).then(handle);
}
