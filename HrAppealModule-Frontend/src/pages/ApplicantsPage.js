import React from 'react'
import ApplicantList from '../components/ApplicantList'
import ApplicantDetail from '../components/ApplicantDetail'

const ApplicantsPage = () => {
  
  return (
    <div className='applicant-page'>
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
    </div>
  )
}

export default ApplicantsPage