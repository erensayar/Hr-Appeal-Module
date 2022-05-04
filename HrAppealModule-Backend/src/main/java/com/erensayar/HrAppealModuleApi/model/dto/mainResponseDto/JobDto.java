package com.erensayar.HrAppealModuleApi.model.dto.mainResponseDto;

import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {

  private Integer id;
  private String name;
  private String summary;
  private String description;
  private String expectedQualification;
  private Integer numberOfToHire;
  private LocalDate creationDate;
  private LocalDate lastApplicationDate;
  private Boolean isArchived;
  private String location;
  private String benefits;
  private List<ApplicantDto> applicants;

  public JobDto(Job job) {
    this.id = job.getId();
    this.name = job.getName();
    this.summary = job.getSummary();
    this.description = job.getDescription();
    this.expectedQualification = job.getExpectedQualification();
    this.numberOfToHire = job.getNumberOfToHire();
    this.creationDate = job.getCreationDate();
    this.lastApplicationDate = job.getLastApplicationDate();
    this.isArchived = job.getIsArchived();
    this.location = job.getLocation();
    this.benefits = job.getBenefits();
    if (!job.getApplicants().isEmpty()) {
      List<Applicant> applicants = job.getApplicants();
      this.applicants = applicants.stream().map(this::applicantEntityToDtoForJobDto).collect(Collectors.toList());
    }
  }

  private ApplicantDto applicantEntityToDtoForJobDto(Applicant applicant) {
    return ApplicantDto.builder()
        .id(applicant.getId())
        .name(applicant.getName())
        .surname(applicant.getSurname())
        .mail(applicant.getMail())
        .telephone(applicant.getTelephone())
        .country(applicant.getCountry())
        .city(applicant.getCity())
        .district(applicant.getDistrict())
        .gitLink(applicant.getGitLink())
        .linkedInLink(applicant.getLinkedInLink())
        .twitterLink(applicant.getTwitterLink())
        .applicantStatus(applicant.getApplicantStatus())
        .applicationDateAndTime(applicant.getApplicationDateAndTime())
        .personalInfoStoragePermission(applicant.getPersonalInfoStoragePermission())
        .isArchived(applicant.getIsArchived())
        .cv(applicant.getCv())
        .build();
  }
}
