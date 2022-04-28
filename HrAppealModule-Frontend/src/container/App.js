import '../styles/App.scss';
import Navbar from "../components/Navbar";
import JobsPage from "../pages/JobsPage";
import ApplyPage from '../pages/ApplyPage';
import ThanksPage from '../pages/ThanksPage';
import LoginPage from "../pages/LoginPage";
import { Routes, Route, Navigate } from "react-router-dom";
import ApplyErrorPage from '../pages/ApplyErrorPage';

function App() {

  return (

    <div className="App">
      <Navbar></Navbar>

      <Routes>
        <Route path="/" element={<JobsPage />} />
        <Route path="/jobs" element={<JobsPage />} />
        <Route path="/apply" element={<ApplyPage />} />
        <Route path="/apply/error" element={<ApplyErrorPage />} />
        <Route path="/thanks" element={<ThanksPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </div>

  );
}

export default App;
