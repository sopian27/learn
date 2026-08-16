import path from 'node:path';

export function resolveSafe(root, relativePath) {
  const resolvedRoot = path.resolve(root);
  const resolved = path.resolve(resolvedRoot, relativePath);
  const relative = path.relative(resolvedRoot, resolved);

  if (relative.startsWith('..') || path.isAbsolute(relative)) {
    throw new Error(`Path escapes root: ${relativePath}`);
  }

  return resolved;
}

// Validates a single untrusted path segment (course, moduleSlug, slug, date,
// etc.) before it is ever concatenated into a path. This is the single
// enforced boundary for path-building inputs: resolveSafe() alone is not
// enough because an attacker-controlled segment can still land inside the
// root while pointing at an unintended sibling directory (e.g. a `course`
// value of `../secrets` combined with a naive "is this still under
// `submissions/<course>`" check that derives its expectation from the same
// untrusted value). Segments must be a non-empty run of alphanumerics,
// dots, underscores, or hyphens — which by construction excludes `/`, `\`,
// and any `..` traversal.
export function assertSegment(name, value, { allowEmpty = false } = {}) {
  if (allowEmpty && value === '') {
    return value;
  }
  if (typeof value !== 'string' || !/^[A-Za-z0-9][A-Za-z0-9._-]*$/.test(value)) {
    throw Object.assign(new Error(`Invalid ${name}`), { status: 400 });
  }
  return value;
}
