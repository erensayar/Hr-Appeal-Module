import '../styles/JobList.scss'
import React, { useState, useEffect } from 'react';
import JobListElemet from './JobListElemet'
import { getJobs } from '../api/Api'

const JobList = () => {

  const [jobs, setJobs] = useState([]);

  useEffect(() => { callApi(); }, []);

  const callApi = async () => {
    try {
      const response = await getJobs();
      setJobs(response.data);
    } catch (error) {
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