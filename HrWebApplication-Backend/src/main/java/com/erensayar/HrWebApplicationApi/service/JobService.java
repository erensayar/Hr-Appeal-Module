package com.erensayar.HrWebApplicationApi.service;

import com.erensayar.HrWebApplicationApi.model.dto.JobDto;
import com.erensayar.HrWebApplicationApi.model.entity.Applicant;
import com.erensayar.HrWebApplicationApi.model.entity.Job;

import java.util.List;

public interface JobService {

    Job createJob(JobDto jobDto);

    Job getJobById(Integer id);

    List<Job> getJobs();

    List<Applicant> getJobApplicantsById(Integer jobId);

    Job updateJob(JobDto jobDto);

    void deleteJobById(Integer id);
}
