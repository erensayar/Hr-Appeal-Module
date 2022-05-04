package com.erensayar.HrAppealModuleApi.model.dto.mainResponseDto;

import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import java.time.LocalDateTime;
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
public class ApplicantDto {

  private String id;
  private String name;
  private String surname;
  private String mail;
  private String telephone;
  private String country;
  private String city;
  private String district;
  private String gitLink;
  private String linkedInLink;
  private String twitterLink;
  private ApplicantStatus applicantStatus;
  private LocalDateTime applicationDateAndTime;
  private Boolean personalInfoStoragePermission;
  private Boolean isArchived;
  private FileAttachment cv;
  // private FileAttachmentDto cv; Buna cevrilecek
  private List<JobDto> jobs;

  public ApplicantDto(Applicant applicant) {
    this.id = applicant.getId();
    this.name = applicant.getName();
    this.surname = applicant.getSurname();
    this.mail = applicant.getMail();
    this.telephone = applicant.getTelephone();
    this.country = applicant.getCountry();
    this.city = applicant.getCity();
    this.district = applicant.getDistrict();
    this.gitLink = applicant.getGitLink();
    this.linkedInLink = applicant.getLinkedInLink();
    this.twitterLink = applicant.getTwitterLink();
    this.applicantStatus = applicant.getApplicantStatus();
    this.applicationDateAndTime = applicant.getApplicationDateAndTime();
    this.personalInfoStoragePermission = applicant.getPersonalInfoStoragePermission();
    this.isArchived = applicant.getIsArchived();
    this.cv = applicant.getCv();
    if (!applicant.getJobs().isEmpty()) {
      List<Job> jobs = applicant.getJobs();
      this.jobs = jobs.stream().map(this::jobEntityToDtoForApplicantDto).collect(Collectors.toList());
    }
  }

  private JobDto jobEntityToDtoForApplicantDto(Job job) {
    return JobDto.builder()
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

}
