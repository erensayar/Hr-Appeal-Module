package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.mainResponseDto.JobDto;
import com.erensayar.HrAppealModuleApi.model.dto.requestDto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.responseDto.JobGetDtoForPublic;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.repo.ApplicantRepo;
import com.erensayar.HrAppealModuleApi.service.UtilClass;
import java.time.LocalDate;
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
        .location(jobCreateOrUpdateDto.getLocation())
        .benefits(jobCreateOrUpdateDto.getBenefits())
        .applicants(this.getApplicantsFromApplicantIdList(jobCreateOrUpdateDto.getApplicants()))
        // Defaults
        .isArchived(false)
        .creationDate(LocalDate.now())
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

  public JobGetDtoForPublic entityToJobGetDtoForPublic(Job job) {
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

  public List<JobGetDtoForPublic> entityListToJobGetDtoForPublicList(List<Job> jobs) {
    return jobs.stream().map(this::entityToJobGetDtoForPublic).collect(Collectors.toList());
  }

  // Methods For Bidirectional
  // <=============================================================================================>
  public List<JobDto> entityListToJobDtoList(List<Job> jobs) {
    List<JobDto> jobDtoList = new ArrayList<>();
    for (Job j : jobs) {
      jobDtoList.add(new JobDto(j));
    }
    return jobDtoList;
  }
  // <=============================================================================================>
}
