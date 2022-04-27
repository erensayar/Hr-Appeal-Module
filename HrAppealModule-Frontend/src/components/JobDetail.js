import '../styles/JobDetailCard.scss'
import { useSelector } from 'react-redux'
import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import { getJobs } from '../api/Api'

const JobDetail = () => {

  const jobId = useSelector((state) => state.job.jobId);
  const [job, setJob] = useState({});

  useEffect(() => {
    callApiForGetJobById(jobId); 
  },[jobId]);

  const callApiForGetJobById = async (jobId) => {
    try {
      const response = await getJobs(jobId);
      setJob(response.data);
    } 
    catch (error) {
      console.log(error);
    }
  }


  return (
    <div className='job-detail-card'>
      <div className='job-detail-container'>
      
        <h2 className='job-title'>Java Software Engineer</h2>
        <div className='job-informations'>
          <h4>Description</h4>
          <p>{job.description}</p>
          <h4>Expected Qualifications</h4>
          <p>{job.expectedQualification}</p>
          <h4>Creation Date</h4>
          <p>{job.creationDate}</p>
          <h4>Last Application Date</h4>
          <p>{job.lastApplicationDate}</p>
          <h4>Benefits</h4>
          <p>{job.benefits}</p>
          <h4>Location</h4>
          <p>{job.location}</p>
        </div>

        <Link className='btn btn-success' to="/apply">Apply</Link>

      </div>
    </div>
  )
}

export default JobDetail