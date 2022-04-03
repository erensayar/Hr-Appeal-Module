import '../styles/ApplicationCard.scss'
import { Link } from "react-router-dom";
import React from 'react'

const ApplicationCard = () => {
  return (
    <div className='application'>

      <div className='application-header'>
        <h1 className='pt-4'>Java Software Engineer</h1>
        <p className='pb-2'>Ankara - Hybrid</p>
      </div>

      <div className='application-card'>
        <form>
          <div className='row'>

            <div className='col'>
              <div className="form-group">
                <label>Name</label>
                <input type="email" className="form-control" placeholder="Enter your name" />
              </div>
              <div className="form-group">
                <label>Email address</label>
                <input type="email" className="form-control" placeholder="Enter email" />
              </div>
              <div className="form-group">
                <label>Country</label>
                <input type="email" className="form-control" placeholder="Enter the country which you are living" />
              </div>
              <div className="form-group">
                <label>District</label>
                <input type="email" className="form-control" placeholder="Enter the district which you are living" />
              </div>
              <div className="form-group">
                <label>LinkedIn Link</label>
                <input type="email" className="form-control" placeholder="Enter your linkedin profile link" />
              </div>
            </div>

            <div className='col'>
              <div className="form-group">
                <label>Surname</label>
                <input type="email" className="form-control" placeholder="Enter your surname" />
              </div>
              <div className="form-group">
                <label>Phone Number</label>
                <input type="email" className="form-control" placeholder="Enter your phone number" />
              </div>
              <div className="form-group">
                <label>City</label>
                <input type="email" className="form-control" placeholder="Enter the city which you are living" />
              </div>
              <div className="form-group">
                <label>Github or Gitlab or Bitbucket Link</label>
                <input type="email" className="form-control" placeholder="Enter your git repo profile link" />
              </div>
              <div className="form-group">
                <label>Twitter Link</label>
                <input type="email" className="form-control" placeholder="Enter your twitter profile link" />
              </div>
            </div>
          </div>

          <div className='row'>
            <div className="form-group">
              <p style={{ margin: 0 }}>Resume</p>
              <input type="file" className="form-control-file" id="file" />
            </div>
          </div>

          <div className='row'>
            <div className="form-check">
              <input type="checkbox" className="form-check-input" id="checkBox" />
              <label className="form-check-label">Do you allow us to store your data?</label>
            </div>
          </div>

          <div className='form-btn row'>
            <Link className='btn btn-success' to="/thanks">Send The Apply</Link>
          </div>
        </form>
      </div>

    </div>
  )
}

export default ApplicationCard