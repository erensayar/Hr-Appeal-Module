package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.JobDto;
import com.erensayar.HrAppealModuleApi.model.dto.JobDtoForPublic;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;

import java.util.List;

public interface JobService {

    // Admin methods

    Job createJob(JobDto jobDto);

    Job getJobById(Integer id);

    List<Job> getJobs();

    List<Applicant> getJobApplicantsById(Integer jobId);

    Job updateJob(JobDto jobDto);

    void deleteJobById(Integer id);

    // Public methods

    JobDtoForPublic getJobDtoForPublicById(Integer id);

    List<JobDtoForPublic> getJobsDtoForPublic();
}
