import '../styles/JobListElement.scss'
import { useDispatch } from 'react-redux'
import { setJobLocation, setJobId, setJobName } from '../redux/reducer/JobReducer'
import React from 'react'

const JobListElemet = (props) => {

    const { job } = props;
    const dispatch = useDispatch();
    
    const dispatchJob = () => {
        dispatch(setJobId(job.id))
        dispatch(setJobName(job.name))
        dispatch(setJobLocation(job.location))
    }

    return (
        <div className='job-list-element'>
            <div className='job-list-element-container row' onClick={dispatchJob}>
                <div className='job-name-and-sum col-9'>
                    <h3>{job.name}</h3>
                    <p>{job.summary}</p>
                </div>
                <div className='job-meta-data col-3'>
                    <p className='job-created-date-letter'>Created Date</p>
                    <p className='job-created-date'>{job.creationDate}</p>
                    <p className='job-location'>{job.location}</p>
                </div>
            </div>
        </div>
    )
}

export default JobListElemet