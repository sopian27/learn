import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import ReactMarkdown from 'react-markdown';
import { getReview } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function Review() {
  const { slug } = useParams();
  const [review, setReview] = useState(null);

  useEffect(() => {
    let cancelled = false;
    let interval;

    async function poll() {
      if (cancelled) return;
      try {
        const result = await getReview(COURSE, slug);
        if (!cancelled) {
          setReview(result);
          clearInterval(interval);
        }
      } catch (err) {
        if (!err.pending) throw err;
      }
    }

    poll();
    interval = setInterval(poll, 3000);

    return () => {
      cancelled = true;
      clearInterval(interval);
    };
  }, [slug]);

  if (!review) return <p>Waiting for review…</p>;

  return (
    <div>
      <h1>Review</h1>
      <p>Score: {review.data.score}</p>
      <ReactMarkdown>{review.content}</ReactMarkdown>
    </div>
  );
}
