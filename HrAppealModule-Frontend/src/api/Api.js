import axios from "axios";

export const getJobs = () => {
  return axios.get(`/api/v1/jobs`);
}

export const getJobById = (id) => {
  return axios.get(`/api/v1/jobs/${id}`);
}