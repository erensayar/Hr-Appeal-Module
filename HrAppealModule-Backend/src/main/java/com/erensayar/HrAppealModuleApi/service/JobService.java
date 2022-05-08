package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import java.util.List;

public interface JobService {

  Job createJob(JobCreateOrUpdateDto jobCreateOrUpdateDto);

  Job getJobById(Integer id);

  List<Job> getJobs();

  Job updateJob(JobCreateOrUpdateDto jobCreateOrUpdateDto);

  void deleteJobById(Integer id);

  List<Applicant> getJobApplicantsByJobId(Integer jobId);

}
