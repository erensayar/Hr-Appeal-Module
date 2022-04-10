import '../styles/JobDetailCard.scss'
import { useSelector } from 'react-redux'
import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import { getJobById } from '../api/Api'

const JobDetail = () => {

  const jobId = useSelector((state) => state.jobId.jobId)
  const [job, setJob] = useState([]);

  useEffect(() => { callApiGetJobById(jobId); }, [jobId]);
  
  const callApiGetJobById = async (jobId) => {
    try {
      console.log(jobId);
      const response = await getJobById(jobId);
      setJob(response.data);
      console.log(job);
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
          <p>We searching software engineer to develop mobile app back end module with spring boot, hibernate, maven We searching software engineer to develop mobile app back end module with spring boot, hibernate, mavenWe searching software engineer to develop mobile app back end module with spring boot, hibernate, maven We searching software engineer to We searching software engineer to develop mobile app back end module with spring boot, hibernate, maven We searching software engineer to develop mobile app back end module with spring boot, hibernate, mavenWe searching software engineer to develop mobile app back end module with spring boot, hibernate, maven We searching software engineer to develop mobile app back end module with spring boot, hibernate, mavendevelop mobile app back end module with spring boot, hibernate, maven</p>
          <h4>Expected Qualifications</h4>
          <p>We searching software engineer to develop mobile app back end module with spring boot, hibernate, maven</p>
          <h4>Creation Date</h4>
          <p>2022-02-01</p>
          <h4>Last Application Date</h4>
          <p>2022-02-01</p>
          <h4>Benefits</h4>
          <p>Multinet</p>
          <h4>Location</h4>
          <p>Ankara-Office</p>
        </div>

        <Link className='btn btn-success' to="/apply">Apply</Link>

      </div>
    </div>
  )
}

export default JobDetail