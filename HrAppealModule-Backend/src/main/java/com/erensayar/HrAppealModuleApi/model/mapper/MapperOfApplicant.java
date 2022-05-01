package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.service.FileAttachmentService;
import com.erensayar.HrAppealModuleApi.service.JobService;
import com.erensayar.HrAppealModuleApi.service.UtilClass;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapperOfApplicant {

  private final FileAttachmentService fileAttachmentService;
  private final JobService jobService;

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
        .cv(fileAttachmentService.getFileById(applicantCreateOrUpdateDto.getCv()))
        .personalInfoStoragePermission(applicantCreateOrUpdateDto.getPersonalInfoStoragePermission())
        .isArchived(applicantCreateOrUpdateDto.getIsArchived())
        //.jobs(getJobsFromJobIdList(applicantDto.getJobs())) // TODO: Kontrol
        .build();
  }

  // This block wrote for getting object, from id list
  private List<Job> getJobsFromJobIdList(List<Integer> jobIdList) {
    List<Job> jobs = new ArrayList<>();
    if (jobIdList != null) {
      for (Integer jobId : jobIdList) {
        jobs.add(jobService.getJobById(jobId));
      }
      return jobs;
    } else {
      return null;
    }
  }

}
