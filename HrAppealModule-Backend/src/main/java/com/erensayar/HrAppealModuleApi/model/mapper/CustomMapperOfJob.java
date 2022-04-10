package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomMapperOfJob { // TODO: Mapstruct icinde burayi gerceklestirebilir miyim?

  // INJECTIONS
  //<==============================================================================================>
  private ApplicantService applicantService;
  @Autowired
  public void setApplicantService(
      ApplicantService applicantService) {
    this.applicantService = applicantService;
  }

  //<==============================================================================================>
  public Job jobCreateOrUpdateDtoMapToEntity(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
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
        applicants.add(applicantService.getApplicantById(applicantId));
      }
      return applicants;
    } else {
      return null;
    }
  }

}

