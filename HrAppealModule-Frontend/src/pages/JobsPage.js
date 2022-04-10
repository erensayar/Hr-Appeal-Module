import React from 'react'
import '../styles/ListAndDetail.scss'
import JobList from '../components/JobList'
import JobDetail from '../components/JobDetail'

const JobsPage = () => {

    return (
        <div className='list-and-detail'>
        <div className='row'>
  
          <div className='col'>
            <JobList></JobList>
          </div>
  
          <div className='col'>
            <JobDetail></JobDetail>
          </div>
  
        </div>
      </div>
    )

}

export default JobsPage