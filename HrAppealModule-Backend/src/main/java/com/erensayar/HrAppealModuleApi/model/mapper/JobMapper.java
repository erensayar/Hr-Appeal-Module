package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.response_dto.JobPublicDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.repo.ApplicantRepo;
import com.erensayar.HrAppealModuleApi.service.util.UtilClass;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JobMapper {

  private final ApplicantRepo applicantRepo;
  private final UtilClass utilClass;

  public Job dtoToEntity(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
    List<String> jobApplicants = jobCreateOrUpdateDto.getApplicants();
    return Job.builder()
        .id(jobCreateOrUpdateDto.getId())
        .name(jobCreateOrUpdateDto.getName())
        .summary(jobCreateOrUpdateDto.getSummary())
        .description(jobCreateOrUpdateDto.getDescription())
        .expectedQualification(jobCreateOrUpdateDto.getExpectedQualification())
        .numberOfToHire(jobCreateOrUpdateDto.getNumberOfToHire())
        .lastApplicationDate(jobCreateOrUpdateDto.getLastApplicationDate())
        .isArchived(jobCreateOrUpdateDto.getIsArchived())
        .location(jobCreateOrUpdateDto.getLocation())
        .creationDate(jobCreateOrUpdateDto.getCreationDate())
        .benefits(jobCreateOrUpdateDto.getBenefits())
        .applicants(getApplicantsFromApplicantIdList(jobApplicants))
        .build();
  }

  // This block wrote for getting object, from dto id list
  private List<Applicant> getApplicantsFromApplicantIdList(List<String> applicantIdList) {
    if (applicantIdList == null) {
      return null;
    }
    List<Applicant> applicants = new ArrayList<>();
    for (String applicantId : applicantIdList) {
      applicants.add(utilClass.optEmptyControl(applicantRepo.findById(applicantId)));
    }
    return applicants;

  }

  public JobPublicDto entityToDto(Job job) {
    return JobPublicDto.builder()
        .id(job.getId())
        .name(job.getName())
        .summary(job.getSummary())
        .description(job.getDescription())
        .expectedQualification(job.getExpectedQualification())
        .creationDate(job.getCreationDate())
        .lastApplicationDate(job.getLastApplicationDate())
        .location(job.getLocation())
        .benefits(job.getBenefits())
        .build();
  }

  public List<JobPublicDto> entityListToDtoList(List<Job> jobs) {
    return jobs.stream().map(this::entityToDto).collect(Collectors.toList());
  }

}
