import '../styles/JobListElement.scss'
import React from 'react'

const JobListElemet = () => {
    return (
        <div className='job-list-element'>
            <div className='job-list-element-container row'>
                <div className='job-name-and-sum col-9'>
                    <h3>Java Developer</h3>
                    <p>We searching software engineer to develop mobile app back end module with spring boot</p>
                </div>
                <div className='job-meta-data col-3'>
                    <p className='job-created-date-letter'>Created Date</p>
                    <p className='job-created-date'>30.12.2022</p>
                    <p className='job-location'>İstanbul - Hybrid</p>
                </div>
            </div>
        </div>
    )
}

export default JobListElemet