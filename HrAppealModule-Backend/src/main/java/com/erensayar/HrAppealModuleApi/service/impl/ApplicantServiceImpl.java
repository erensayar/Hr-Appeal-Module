package com.erensayar.HrAppealModuleApi.service.impl;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import com.erensayar.HrAppealModuleApi.model.mapper.MapperOfApplicant;
import com.erensayar.HrAppealModuleApi.repo.ApplicantRepo;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import com.erensayar.HrAppealModuleApi.service.JobService;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

  private final ApplicantRepo applicantRepo;
  private final MapperOfApplicant mapperOfApplicant;
  private final JobService jobService;


  @Override
  public Applicant createApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
    applicantCreateOrUpdateDto.setId("APL" + UUID.randomUUID().toString().replaceAll("-", ""));
    applicantCreateOrUpdateDto.setApplicantStatus(ApplicantStatus.TO_BE_EVALUATED);
    applicantCreateOrUpdateDto.setApplicationDate(LocalDate.now());
    return applicantRepo.save(mapperOfApplicant.dtoToEntity(applicantCreateOrUpdateDto));
  }

  @Override
  public Applicant getApplicantById(String id) {
    return applicantRepo.findById(id).orElseThrow(NoContentException::new);
  }

  @Override
  public List<Applicant> getApplicants() {
    List<Applicant> applicants = applicantRepo.findAll();
    if (applicants.isEmpty()) {
      throw new NoContentException();
    }
    return applicants;
  }

  @Override
  public Applicant updateApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
    if (applicantCreateOrUpdateDto.getId() == null) {
      throw new BadRequestException("Id can not be empty");
    }
    return applicantRepo.save(mapperOfApplicant.dtoToEntity(applicantCreateOrUpdateDto));
  }

  @Override
  public void deleteApplicantById(String id) {
    // is exist?
    applicantRepo.findById(id).orElseThrow(NoContentException::new);

    // Remove relation applicant and job
    List<Job> jobs = jobService.getJobs();
    for (Job j : jobs) {
      List<Applicant> applicantsInJob = j.getApplicants();
      applicantsInJob.removeIf(a -> a.getId().equals(id));
    }
    // TODO: This solution is not very good for remove relation.
    //  Look at hibernate tricks. (I think cascade option is not helpful for this situation)

    // Now we can delete
    applicantRepo.deleteById(id);
  }

}
