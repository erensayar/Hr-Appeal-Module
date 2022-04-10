package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.JobPublicDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;

import java.util.List;

public interface JobService {

    // Admin methods

    Job createJob(JobCreateOrUpdateDto jobCreateOrUpdateDto);

    Job getJobById(Integer id);

    List<Job> getJobs();

    List<Applicant> getJobApplicantsById(Integer jobId);

    Job updateJob(JobCreateOrUpdateDto jobCreateOrUpdateDto);

    void deleteJobById(Integer id);

    // Public methods

    JobPublicDto getJobPublicDtoById(Integer id);

    List<JobPublicDto> getJobPublicDtoList();
}
