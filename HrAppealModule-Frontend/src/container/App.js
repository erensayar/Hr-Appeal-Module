import '../styles/App.scss';
import Navbar from "../components/Navbar";
import JobsPage from "../pages/JobsPage";
import ApplicationPage from '../pages/ApplicationPage';
import LoginPage from "../pages/LoginPage";
import { Routes, Route, Navigate } from "react-router-dom";

function App() {

  return (

    <div className="App">
      <Navbar></Navbar>

      <Routes>
        <Route path="/" element={<JobsPage />} />
        <Route path="/jobs" element={<JobsPage />} />
        <Route path="/apply" element={<ApplicationPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </div>

  );
}

export default App;
