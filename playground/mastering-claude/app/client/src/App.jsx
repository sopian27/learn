import { BrowserRouter, Routes, Route, Link, Navigate } from 'react-router-dom';
import Dashboard from './pages/Dashboard.jsx';
import Lesson from './pages/Lesson.jsx';
import Review from './pages/Review.jsx';
import Progress from './pages/Progress.jsx';
import MentorContext from './pages/MentorContext.jsx';

export const COURSE = 'mastering-claude';

export default function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Dashboard</Link>
        <Link to="/progress">Progress</Link>
        <Link to="/mentor">Mentor</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/lesson/:moduleSlug" element={<Lesson />} />
        <Route path="/review/:slug" element={<Review />} />
        <Route path="/progress" element={<Progress />} />
        <Route path="/mentor" element={<MentorContext />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
}
