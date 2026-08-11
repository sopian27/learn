import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import ReactMarkdown from 'react-markdown';
import { getLesson, postSubmission } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function Lesson() {
  const { moduleSlug } = useParams();
  const navigate = useNavigate();
  const [lesson, setLesson] = useState(null);
  const [answer, setAnswer] = useState('');
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    getLesson(COURSE, moduleSlug).then(setLesson);
  }, [moduleSlug]);

  if (!lesson) return <p>Loading…</p>;

  async function handleSubmit(e) {
    e.preventDefault();
    setSubmitting(true);
    await postSubmission(COURSE, {
      slug: moduleSlug,
      lesson: lesson.data.title,
      module: lesson.data.module,
      content: answer,
    });
    navigate(`/review/${moduleSlug}`);
  }

  return (
    <div>
      <h1>{lesson.data.title}</h1>
      <ReactMarkdown>{lesson.content}</ReactMarkdown>
      <p>Open Playground: <code>playground/mastering-claude/{moduleSlug}.md</code></p>
      <form onSubmit={handleSubmit}>
        <label htmlFor="answer">Your answer</label>
        <textarea
          id="answer"
          value={answer}
          onChange={(e) => setAnswer(e.target.value)}
        />
        <button type="submit" disabled={submitting}>Submit for Review</button>
      </form>
    </div>
  );
}
