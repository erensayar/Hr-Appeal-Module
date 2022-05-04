package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.requestDto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.responseDto.JobGetDtoForPublic;
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

    // Public methods // TODO: move to controller under lines

    JobGetDtoForPublic getJobPublicDtoById(Integer id);

    List<JobGetDtoForPublic> getJobPublicDtoList();
}
