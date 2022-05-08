package com.erensayar.HrAppealModuleApi.service.impl;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.mapper.JobMapper;
import com.erensayar.HrAppealModuleApi.repo.JobRepo;
import com.erensayar.HrAppealModuleApi.service.JobService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {

  private final JobRepo jobRepo;
  private final JobMapper jobMapper;


  @Override
  public Job createJob(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
    jobCreateOrUpdateDto.setId(null);
    return jobRepo.save(jobMapper.dtoToEntity(jobCreateOrUpdateDto));
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
  public List<Applicant> getJobApplicantsByJobId(Integer jobId) {
    return getJobById(jobId).getApplicants();
  }

  @Override
  public Job updateJob(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
    if (jobCreateOrUpdateDto.getId() == null)
      throw new BadRequestException("Id can not be empty");
    return jobRepo.save(jobMapper.dtoToEntity(jobCreateOrUpdateDto));
  }

  @Override
  public void deleteJobById(Integer id) {
    jobRepo.findById(id).orElseThrow(NoContentException::new);
    jobRepo.deleteById(id);
  }

}
