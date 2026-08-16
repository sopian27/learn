import { useEffect, useState } from 'react';
import ReactMarkdown from 'react-markdown';
import { getProgress } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function Progress() {
  const [progress, setProgress] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    getProgress(COURSE).then(setProgress).catch(setError);
  }, []);

  if (error) return <p>Something went wrong loading your progress.</p>;
  if (!progress) return <p>Loading…</p>;

  return (
    <div>
      <h1>Progress</h1>
      <ReactMarkdown>{progress.progress}</ReactMarkdown>
      <h2>Skills</h2>
      <ul>
        {Object.entries(progress.skills).map(([name, value]) => (
          <li key={name}>
            {name}: {value}%
          </li>
        ))}
      </ul>
    </div>
  );
}
