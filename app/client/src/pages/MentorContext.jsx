import { useEffect, useState } from 'react';
import { getMentorContext } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function MentorContext() {
  const [context, setContext] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    getMentorContext(COURSE)
      .then((res) => setContext(res.context))
      .catch(setError);
  }, []);

  if (error) return <p>Something went wrong loading your mentor context.</p>;
  if (context === null) return <p>Loading…</p>;

  return (
    <div>
      <h1>Mentor Context</h1>
      <button onClick={() => navigator.clipboard.writeText(context)}>
        Copy for Claude Code
      </button>
      <pre>{context}</pre>
    </div>
  );
}
