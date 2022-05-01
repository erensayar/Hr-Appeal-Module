package com.erensayar.HrAppealModuleApi.service.impl;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.response_dto.GetJobDtoForPublic;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.mapper.MapperOfJob;
import com.erensayar.HrAppealModuleApi.repo.JobRepo;
import com.erensayar.HrAppealModuleApi.service.JobService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {

  // INJECTIONS
  //<==============================================================================================>
  private final JobRepo jobRepo;
  private final MapperOfJob mapperOfJob;


  // PUBLIC METHODS
  //<==============================================================================================>
  @Override
  public Job createJob(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
    jobCreateOrUpdateDto.setId(null);
    return jobRepo.save(mapperOfJob.dtoToEntity(jobCreateOrUpdateDto));
  }

  @Override
  public Job getJobById(Integer id) {
    return jobRepo.findById(id).orElseThrow(NoContentException::new);
  }

  @Override
  public List<Job> getJobs() {
    List<Job> jobs = jobRepo.findAll();
    if (jobs.isEmpty())
      throw new NoContentException();
    return jobs;
  }

  @Override
  public List<Applicant> getJobApplicantsById(Integer jobId) {
    return this.getJobById(jobId).getApplicants();
  }

  @Override
  public Job updateJob(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
    if (jobCreateOrUpdateDto.getId() == null)
      throw new BadRequestException("Id can not be empty");
    return jobRepo.save(mapperOfJob.dtoToEntity(jobCreateOrUpdateDto));
  }

  @Override
  public void deleteJobById(Integer id) {
    jobRepo.findById(id).orElseThrow(NoContentException::new);
    jobRepo.deleteById(id);
  }

  @Override
  public GetJobDtoForPublic getJobPublicDtoById(Integer id) {
    return mapperOfJob.entityToGetJobDto(this.getJobById(id));
  }

  @Override
  public List<GetJobDtoForPublic> getJobPublicDtoList() {
    return mapperOfJob.entityListToGetJobDtoList(this.getJobs());
  }

}
