import '../styles/ListAndDetail.scss'
import React from 'react'
import JobList from './JobList'
import DetailCard from './DetailCard'

const ListAndDetail = () => {
  return (
    <div className='list-and-detail'>

      <div className='row'>
        <div className='col'>
          <div className='list row'>
            <p className='h3'>List</p>
          </div>
          <JobList></JobList>
        </div>
        <div className='col'>
          <div className='detail row'>
            <p className='h3'>Detail</p>
          </div>
          <DetailCard></DetailCard>
        </div>
      </div>

    </div>
  )
}

export default ListAndDetail