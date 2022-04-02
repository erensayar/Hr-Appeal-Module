import React from 'react'
import ApplicantList from './ApplicantList'
import ApplicantDetail from './ApplicantDetail'

const ApplicantListAndDetail = () => {
  return (
    <div className='list-and-detail'>
      <div className='row'>

        <div className='col'>
          <ApplicantList></ApplicantList>
        </div>

        <div className='col'>
          <ApplicantDetail></ApplicantDetail>
        </div>

      </div>
    </div>
  )
}

export default ApplicantListAndDetail