package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.service.FileAttachmentService;
import com.erensayar.HrAppealModuleApi.service.JobService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomMapperOfApplicant {

  private final FileAttachmentService fileAttachmentService;
  private final JobService jobService;

  public Applicant applicantCreateOrUpdateDtoToEntity(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
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
        .cv(getCvAndCheckException(applicantCreateOrUpdateDto.getCv()))
        .personalInfoStoragePermission(applicantCreateOrUpdateDto.getPersonalInfoStoragePermission())
        .isArchived(applicantCreateOrUpdateDto.getIsArchived())
        //.jobs(getJobsFromJobIdList(applicantDto.getJobs())) // TODO: Kontrol
        .build();
  }

  // Written for 204 convert to 400
  // If cv id is wrong then we tell the client in response body as message
  private FileAttachment getCvAndCheckException(String cvId) {
    try {
      return fileAttachmentService.getFileById(cvId);
    } catch (NoContentException e) {
      throw new BadRequestException("Cv id is invalid!");
    }
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
