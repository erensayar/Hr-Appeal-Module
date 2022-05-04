import '../styles/ApplicationCard.scss'
import { useSelector } from 'react-redux'
import React, { useState } from 'react';
import { patchApplicant, sendApplicant, sendFile } from '../api/Api';
import { useNavigate } from "react-router-dom";

const ApplicationCard = () => {

  const jobId = useSelector((state) => state.job.jobId);
  const jobName = useSelector((state) => state.job.jobName);
  const jobLocation = useSelector((state) => state.job.jobLocation);
  const navigate = useNavigate();
  const [selectedFile, setSelectedFile] = useState();
  const [applicant, setApplicant] = useState({
    "name": null,
    "surname": null,
    "mail": null,
    "telephone": null,
    "country": null,
    "city": null,
    "district": null,
    "gitLink": null,
    "linkedInLink": null,
    "twitterLink": null,
    "applicantStatus": null,
    "applicationDate": null,
    "cvId": null,
    "personalInfoStoragePermission": false,
    "isArchived": null
  });

  const handleCheckBox = () => {
    setApplicant({ personalInfoStoragePermission: !applicant.personalInfoStoragePermission });
  };

  const handleFile = (e) => {
    setSelectedFile(e.target.files[0]);
  };


  const sendApplication = (e, applicant) => {
    e.preventDefault();
    // 1 create applicant 
    callApiForSendApplicant(applicant);
    // 2 send file and take file id
    callApiForSendFile(selectedFile);
    // 3 update applicant for create relation with added file.
    callApiForPatchApplicant(cvId);

  }


  const callApiForSendApplicant = async (applicant) => {
    try {
      await sendApplicant(applicant);
    }
    catch (error) {
      navigate("/apply/error");
      console.log(error);
    }
  }

  const callApiForSendFile = async (file) => {
    try {
      const response = await sendFile(file);
      setApplicant({cvId: response.data});
    }
    catch (error) {
      navigate("/apply/error");
      console.log(error);
    }
  }

  const callApiForPatchApplicant = async (applicantId, object) => {
    try {
      await patchApplicant(applicantId, object);
      navigate("/thanks");
    }
    catch (error) {
      navigate("/apply/error");
      console.log(error);
    }
  }

  return (
    <div className='application'>

      <div className='application-header'>
        <h1 className='pt-4'>{jobName}</h1>
        <p className='pb-2'>{jobLocation}</p>
      </div>

      <div className='application-card'>
        <form>
          <div className='row'>

            <div className='col'>
              <div className="form-group">
                <label>Name</label>
                <input className="form-control" placeholder="Enter your name" onChange={(e) => setApplicant({ name: e.target.value })} />
              </div>
              <div className="form-group">
                <label>Email address</label>
                <input type="email" className="form-control" placeholder="Enter email" onChange={(e) => setApplicant({ mail: e.target.value })} />
              </div>
              <div className="form-group">
                <label>Country</label>
                <input className="form-control" placeholder="Enter the country which you are living" onChange={(e) => setApplicant({ country: e.target.value })} />
              </div>
              <div className="form-group">
                <label>District</label>
                <input className="form-control" placeholder="Enter the district which you are living" onChange={(e) => setApplicant({ district: e.target.value })} />
              </div>
              <div className="form-group">
                <label>LinkedIn Link</label>
                <input className="form-control" placeholder="Enter your linkedin profile link" onChange={(e) => setApplicant({ linkedInLink: e.target.value })} />
              </div>
            </div>

            <div className='col'>
              <div className="form-group">
                <label>Surname</label>
                <input className="form-control" placeholder="Enter your surname" onChange={(e) => setApplicant({ surname: e.target.value })} />
              </div>
              <div className="form-group">
                <label>Phone Number</label>
                <input className="form-control" placeholder="Enter your phone number" onChange={(e) => setApplicant({ telephone: e.target.value })} />
              </div>
              <div className="form-group">
                <label>City</label>
                <input className="form-control" placeholder="Enter the city which you are living" onChange={(e) => setApplicant({ city: e.target.value })} />
              </div>
              <div className="form-group">
                <label>Github or Gitlab or Bitbucket Link</label>
                <input className="form-control" placeholder="Enter your git repo profile link" onChange={(e) => setApplicant({ gitLink: e.target.value })} />
              </div>
              <div className="form-group">
                <label>Twitter Link</label>
                <input className="form-control" placeholder="Enter your twitter profile link" onChange={(e) => setApplicant({ twitterLink: e.target.value })} />
              </div>
            </div>
          </div>

          <div className='row'>
            <div className="form-group">
              <p style={{ margin: 0 }}>Resume</p>
              <input type="file" className="form-control-file" id="file" onChange={handleFile} />
            </div>
          </div>

          <div className='row'>
            <div className="form-check">
              <input type="checkbox" checked={applicant.personalInfoStoragePermission} onChange={handleCheckBox} className="form-check-input" id="checkBox" />
              <label className="form-check-label">Do you allow us for store your data?</label>
            </div>
          </div>

          <div className='form-btn row'>
            <button className="btn btn-success" onClick={(e) => sendApplication(e, applicant)}>Send The Apply</button>
          </div>

        </form>
      </div>

    </div>
  )
}

export default ApplicationCard