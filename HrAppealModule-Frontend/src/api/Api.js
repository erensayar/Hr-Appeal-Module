import axios from "axios";

export const getJobs = (id) => {
  const path = id
    ? `/api/v1/jobs/${id}`
    : `/api/v1/jobs`
  return axios.get(path);
}

export const sendApplicant = (applicant) => {
  return axios.post(`/api/v1/applicants`, applicant);
}

export const patchApplicant = (applicantId, body) => {
  return axios.patch(`/api/v1/applicants/${applicantId}`, body);
}

export const sendFile = (file) => {
  return axios.post(`/api/v1/files/upload`, file);
}
