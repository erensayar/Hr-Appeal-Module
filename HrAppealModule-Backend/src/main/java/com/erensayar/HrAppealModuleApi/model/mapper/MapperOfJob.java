package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.response_dto.JobGetDtoForPublic;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.repo.ApplicantRepo;
import com.erensayar.HrAppealModuleApi.service.UtilClass;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapperOfJob {

  private final ApplicantRepo applicantRepo;
  private final UtilClass utilClass;

  public Job dtoToEntity(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
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
        .applicants(this.getApplicantsFromApplicantIdList(
            jobCreateOrUpdateDto.getApplicants())) // It's not necessary
        .build();
  }

  // This block wrote for getting object, from dto id list
  private List<Applicant> getApplicantsFromApplicantIdList(List<String> applicantIdList) {
    List<Applicant> applicants = new ArrayList<>();
    if (applicantIdList != null) {
      for (String applicantId : applicantIdList) {
        applicants.add(utilClass.optEmptyControl(applicantRepo.findById(applicantId)));
      }
      return applicants;
    } else {
      return null;
    }
  }

  public JobGetDtoForPublic entityToDto(Job job) {
    return JobGetDtoForPublic.builder()
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

  public List<JobGetDtoForPublic> entityListToDtoList(List<Job> jobs) {
    return jobs.stream().map(this::entityToDto).collect(Collectors.toList());
  }

}
