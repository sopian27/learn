import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getCourse, getProgress } from '../api/client.js';
import { COURSE } from '../App.jsx';

export default function Dashboard() {
  const [course, setCourse] = useState(null);
  const [progress, setProgress] = useState(null);

  useEffect(() => {
    getCourse(COURSE).then(setCourse);
    getProgress(COURSE).then(setProgress);
  }, []);

  if (!course || !progress) return <p>Loading…</p>;

  const nextModule = course.modules.find((m) => m.status !== 'completed');
  const completedCount = course.modules.filter((m) => m.status === 'completed').length;
  const percent = course.modules.length
    ? Math.round((completedCount / course.modules.length) * 100)
    : 0;

  return (
    <div>
      <h1>Dashboard</h1>
      <section>
        <h2>Continue Learning</h2>
        {nextModule ? (
          <Link to={`/lesson/${nextModule.slug}`}>{nextModule.title}</Link>
        ) : (
          <p>All modules completed.</p>
        )}
      </section>
      <section>
        <h2>Course Completion</h2>
        <p>{percent}%</p>
      </section>
    </div>
  );
}
