package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.response_dto.ApplicantGetDto;
import com.erensayar.HrAppealModuleApi.model.dto.response_dto.FileAttachmentGetDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.repo.FileAttachmentRepo;
import com.erensayar.HrAppealModuleApi.repo.JobRepo;
import com.erensayar.HrAppealModuleApi.service.UtilClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MapperOfApplicant {

  private final FileAttachmentRepo fileAttachmentRepo;
  private final UtilClass utilClass;
  private final JobRepo jobRepo;

  public Applicant dtoToEntity(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
    return Applicant.builder()
        .id(applicantCreateOrUpdateDto.getId())
        .name(applicantCreateOrUpdateDto.getName())
        .surname(applicantCreateOrUpdateDto.getSurname())
        .mail(applicantCreateOrUpdateDto.getMail())
        .telephone(applicantCreateOrUpdateDto.getTelephone())
        .country(applicantCreateOrUpdateDto.getCountry())
        .city(applicantCreateOrUpdateDto.getCity())
        .district(applicantCreateOrUpdateDto.getDistrict())
        .gitLink(applicantCreateOrUpdateDto.getGitLink())
        .linkedInLink(applicantCreateOrUpdateDto.getLinkedInLink())
        .twitterLink(applicantCreateOrUpdateDto.getTwitterLink())
        .applicantStatus(applicantCreateOrUpdateDto.getApplicantStatus())
        .applicationDate(applicantCreateOrUpdateDto.getApplicationDate())
        .cv(getFileAttachment(applicantCreateOrUpdateDto.getCv()))
        .personalInfoStoragePermission(applicantCreateOrUpdateDto.getPersonalInfoStoragePermission())
        .isArchived(applicantCreateOrUpdateDto.getIsArchived())
        //.jobs(getJobsFromJobIdList(applicantDto.getJobs())) // Cift yonlu ilişki kurulursa kullanilir
        .build();
  }

/*

  public ApplicantGetDto entityToDto(Applicant applicant) {
    return ApplicantGetDto.builder()
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
        .applicationDate(applicant.getApplicationDate())
        .cv(mapFileAttachmentWithNullCheck(applicant.getCv()))
        .personalInfoStoragePermission(applicant.getPersonalInfoStoragePermission())
        .isArchived(applicant.getIsArchived())
        .build();
  }


  public List<ApplicantGetDto> entityListToDtoList(List<Applicant> applicants) {
    return applicants.stream().map(this::entityToDto).collect(Collectors.toList());
  }

  private FileAttachmentGetDto mapFileAttachmentWithNullCheck(FileAttachment fileAttachment) {
    if (fileAttachment == null) {
      return null;
    }
    return mapperOfFileAttachment.entityToDto(fileAttachment);
  }

 */

  public FileAttachment getFileAttachment(String cvId) {
    if (cvId == null) {
      return null;
    }
    Optional<FileAttachment> optFileAttachment = fileAttachmentRepo.findById(cvId);
    return utilClass.optEmptyControl(optFileAttachment);
  }


  // This block wrote for getting object, from id list
  private List<Job> getJobsFromJobIdList(List<Integer> jobIdList) {
    List<Job> jobs = new ArrayList<>();
    if (jobIdList != null) {
      for (Integer jobId : jobIdList) {
        jobs.add(utilClass.optEmptyControl(jobRepo.findById(jobId)));
      }
      return jobs;
    } else {
      return null;
    }
  }

}
