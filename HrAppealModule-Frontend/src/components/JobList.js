import '../styles/JobList.scss'
import React, { useState, useEffect } from 'react';
import JobListElemet from './JobListElement'
import { getJobs } from '../api/Api'

const JobList = () => {

  const [jobs, setJobs] = useState([]);

  useEffect(() => { callGetJobs(); }, []);
  const callGetJobs = async () => {
    try {
      const response = await getJobs();
      setJobs(response.data);
    } 
    catch (error) {
      console.log(error);
    }
  }

  return (
    <div className='job-list'>
      {jobs && jobs.map(
        (jobs) => (<JobListElemet key={jobs.id} job={jobs} />)
      )}
    </div>
  )
}

export default JobList