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

  const [cvId, setcvId] = useState();
  const [selectedFile, setSelectedFile] = useState();

  const [applicantId, setApplicantId] = useState();
  const [name, setName] = useState();
  const [surname, setSurname] = useState();
  const [mail, setMail] = useState();
  const [telephone, setTelephone] = useState();
  const [country, setCountry] = useState();
  const [city, setCity] = useState();
  const [district, setDistrict] = useState();
  const [gitLink, setGitLink] = useState();
  const [linkedInLink, setLinkedInLink] = useState();
  const [twitterLink, setTwitterLink] = useState();
  const [personalInfoStoragePermission, setPersonalInfoStoragePermission] = useState(false);
  const applicant = {
    name: name,
    surname: surname,
    mail: mail,
    telephone: telephone,
    country: country,
    city: city,
    district: district,
    gitLink: gitLink,
    linkedInLink: linkedInLink,
    twitterLink: twitterLink,
    personalInfoStoragePermission: personalInfoStoragePermission,
  };

  // Handle
  // <========================================================>
  const handleCheckBox = () => {
    setPersonalInfoStoragePermission(!personalInfoStoragePermission);
  };

  const handleFile = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  // Api Calls
  // <========================================================>
  const sendApplication = (e) => {
    e.preventDefault();
    console.log(applicant);
    console.log(applicant);
    // 0 Control storage permission
    if (!applicant.personalInfoStoragePermission) {
      window.alert("Allow permission!  We want this permission to review your resume.");
      return;
    }
    // 1 create applicant 
    callSendApplicant(applicant);
    // 2 send file and take file id
    callSendFile(selectedFile, applicantId);
    // 3 update applicant for create relation with saved file.
    callPatchApplicant(applicantId, cvId);
  }

  const callSendApplicant = async (applicant) => {
    try {
      const response = await sendApplicant(applicant);
      setApplicantId(response.data.id);
    }
    catch (error) {
      navigate("/apply/error");
      console.log(error);
    }
  }

  const callSendFile = async (file, applicantId) => {
    try {
      const response = await sendFile(file, applicantId);
      setcvId({ cvId: response.data });
    }
    catch (error) {
      navigate("/apply/error");
      console.log(error);
    }
  }

  const callPatchApplicant = async (applicantId, object) => {
    try {
      await patchApplicant(applicantId, object);
      navigate("/thanks");
    }
    catch (error) {
      navigate("/apply/error");
      console.log(error);
    }
  }

  // <========================================================>
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
                <input className="form-control" placeholder="Enter your name" onChange={(e) => setName(e.target.value)} />
              </div>
              <div className="form-group">
                <label>Email address</label>
                <input type="email" className="form-control" placeholder="Enter email" onChange={(e) => setMail(e.target.value)} />
              </div>
              <div className="form-group">
                <label>Country</label>
                <input className="form-control" placeholder="Enter the country which you are living" onChange={(e) => setCountry(e.target.value)} />
              </div>
              <div className="form-group">
                <label>District</label>
                <input className="form-control" placeholder="Enter the district which you are living" onChange={(e) => setDistrict(e.target.value)} />
              </div>
              <div className="form-group">
                <label>LinkedIn Link</label>
                <input className="form-control" placeholder="Enter your linkedin profile link" onChange={(e) => setLinkedInLink(e.target.value)} />
              </div>
            </div>

            <div className='col'>
              <div className="form-group">
                <label>Surname</label>
                <input className="form-control" placeholder="Enter your surname" onChange={(e) => setSurname(e.target.value)} />
              </div>
              <div className="form-group">
                <label>Phone Number</label>
                <input className="form-control" placeholder="Enter your phone number" onChange={(e) => setTelephone(e.target.value)} />
              </div>
              <div className="form-group">
                <label>City</label>
                <input className="form-control" placeholder="Enter the city which you are living" onChange={(e) => setCity(e.target.value)} />
              </div>
              <div className="form-group">
                <label>Github or Gitlab or Bitbucket Link</label>
                <input className="form-control" placeholder="Enter your git repo profile link" onChange={(e) => setGitLink(e.target.value)} />
              </div>
              <div className="form-group">
                <label>Twitter Link</label>
                <input className="form-control" placeholder="Enter your twitter profile link" onChange={(e) => setTwitterLink(e.target.value)} />
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
            <button className="btn btn-success" onClick={(e) => sendApplication(e)}>Send The Apply</button>
          </div>

        </form>
      </div>

    </div>
  )
}

export default ApplicationCard

/*
Notes:
<div className="form-group">
  <label>Phone Number</label>
  <input className="form-control" placeholder="Enter your phone number" onChange={(e) => setApplicant({ telephone: e.target.value })} />
</div>

const [applicant, setApplicant] = useState({
  name: null,
  surname: null,
  mail: null,
  telephone: null,
  country: null,
  city: null,
  district: null,
  gitLink: null,
  linkedInLink: null,
  twitterLink: null,
  personalInfoStoragePermission: false,
});

const handleCheckBox = () => {
  setApplicant({ personalInfoStoragePermission: !applicant.personalInfoStoragePermission });
};
*/