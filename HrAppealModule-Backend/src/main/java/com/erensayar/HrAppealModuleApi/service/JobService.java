package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.response_dto.GetJobDtoForPublic;
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

    GetJobDtoForPublic getJobPublicDtoById(Integer id);

    List<GetJobDtoForPublic> getJobPublicDtoList();
}
