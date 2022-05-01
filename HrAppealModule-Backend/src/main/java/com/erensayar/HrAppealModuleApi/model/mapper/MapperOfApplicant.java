package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.repo.FileAttachmentRepo;
import com.erensayar.HrAppealModuleApi.repo.JobRepo;
import com.erensayar.HrAppealModuleApi.service.UtilClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    FileAttachment fileAttachment = getFileAttachment(applicantCreateOrUpdateDto.getCv());
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
        .cv(fileAttachment)
        .personalInfoStoragePermission(applicantCreateOrUpdateDto.getPersonalInfoStoragePermission())
        .isArchived(applicantCreateOrUpdateDto.getIsArchived())
        //.jobs(getJobsFromJobIdList(applicantDto.getJobs())) // Cift yonlu ilişki kurulursa kullanilir
        .build();
  }

  private FileAttachment getFileAttachment(String cvId) {
    if (cvId == null) {
      log.debug("First you need upload the file and enter the file id. ");
      throw new BadRequestException("First you need upload the file and enter the file id. ");
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
