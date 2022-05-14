package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.applicant.ApplicantCreateDto;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.applicant.ApplicantUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import com.erensayar.HrAppealModuleApi.repo.FileAttachmentRepo;
import com.erensayar.HrAppealModuleApi.repo.JobRepo;
import com.erensayar.HrAppealModuleApi.service.util.UtilClass;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApplicantMapper {

  private final FileAttachmentRepo fileAttachmentRepo;
  private final UtilClass utilClass;
  private final JobRepo jobRepo;

  public Applicant dtoToEntity(ApplicantCreateDto applicantCreateDto) {
    return Applicant.builder()
        .name(applicantCreateDto.getName())
        .surname(applicantCreateDto.getSurname())
        .mail(applicantCreateDto.getMail())
        .telephone(applicantCreateDto.getTelephone())
        .country(applicantCreateDto.getCountry())
        .city(applicantCreateDto.getCity())
        .district(applicantCreateDto.getDistrict())
        .gitLink(applicantCreateDto.getGitLink())
        .linkedInLink(applicantCreateDto.getLinkedInLink())
        .twitterLink(applicantCreateDto.getTwitterLink())
        .cv(getFileAttachment(applicantCreateDto.getCv().getId()))
        .personalInfoStoragePermission(applicantCreateDto.getPersonalInfoStoragePermission())
        .build();
  }

  public Applicant dtoToEntity(ApplicantUpdateDto applicantUpdateDto) {
    return Applicant.builder()
        .id(applicantUpdateDto.getId())
        .name(applicantUpdateDto.getName())
        .surname(applicantUpdateDto.getSurname())
        .mail(applicantUpdateDto.getMail())
        .telephone(applicantUpdateDto.getTelephone())
        .country(applicantUpdateDto.getCountry())
        .city(applicantUpdateDto.getCity())
        .district(applicantUpdateDto.getDistrict())
        .gitLink(applicantUpdateDto.getGitLink())
        .linkedInLink(applicantUpdateDto.getLinkedInLink())
        .twitterLink(applicantUpdateDto.getTwitterLink())
        .cv(getFileAttachment(applicantUpdateDto.getCv().getId()))
        .personalInfoStoragePermission(applicantUpdateDto.getPersonalInfoStoragePermission())
        .applicantStatus(applicantUpdateDto.getApplicantStatus())
        .applicationDateAndTime(applicantUpdateDto.getApplicationDateAndTime())
        .isArchived(applicantUpdateDto.getIsArchived())
        .build();
  }

  public FileAttachment getFileAttachment(String cvId) {
    if (cvId == null) {
      return null;
    }
    Optional<FileAttachment> optFileAttachment = fileAttachmentRepo.findById(cvId);
    return utilClass.optEmptyControl(optFileAttachment);
  }


  // This block wrote for getting object, from id list
  // If create bidirectional relation then u can use this
  private List<Job> getJobsFromJobIdList(List<Integer> jobIdList) {
    if (jobIdList == null) {
      return null;
    }
    List<Job> jobs = new ArrayList<>();
    for (Integer jobId : jobIdList) {
      jobs.add(utilClass.optEmptyControl(jobRepo.findById(jobId)));
    }
    return jobs;
  }

}
