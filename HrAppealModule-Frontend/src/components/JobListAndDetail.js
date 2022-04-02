import '../styles/ListAndDetail.scss'
import React from 'react'
import JobList from './JobList'
import JobDetail from './JobDetail'

const JobListAndDetail = () => {
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

export default JobListAndDetail