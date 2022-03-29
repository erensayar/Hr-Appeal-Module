package com.erensayar.HrAppealModuleApi.service.impl;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import com.erensayar.HrAppealModuleApi.repo.ApplicantRepo;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import com.erensayar.HrAppealModuleApi.service.FileAttachmentService;
import com.erensayar.HrAppealModuleApi.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

    // INJECTIONS
    //<================================================================================================================>
    private final ApplicantRepo applicantRepo;

    private FileAttachmentService fileAttachmentService;
    private JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @Autowired
    public void setFileAttachmentService(FileAttachmentService fileAttachmentService) {
        this.fileAttachmentService = fileAttachmentService;
    }

    // PUBLIC METHODS
    //<================================================================================================================>
    @Override
    public Applicant createApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
        applicantCreateOrUpdateDto.setId("APL" + UUID.randomUUID().toString().replaceAll("-", ""));
        applicantCreateOrUpdateDto.setApplicantStatus(ApplicantStatus.TO_BE_EVALUATED);
        applicantCreateOrUpdateDto.setApplicationDate(LocalDate.now());
        return applicantRepo.save(this.converterOfApplicant(applicantCreateOrUpdateDto));
    }

    @Override
    public Applicant getApplicantById(String id) {
        return applicantRepo.findById(id).orElseThrow(NoContentException::new);
    }

    @Override
    public List<Applicant> getApplicants() {
        List<Applicant> applicants = applicantRepo.findAll();
        if (applicants.isEmpty())
            throw new NoContentException();
        return applicants;
    }

    @Override
    public Applicant updateApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
        if (applicantCreateOrUpdateDto.getId() == null)
            throw new BadRequestException("Id can not be empty");
        return applicantRepo.save(this.converterOfApplicant(applicantCreateOrUpdateDto));
    }

    @Override
    public void deleteApplicantById(String id) {
        // is exist?
        applicantRepo.findById(id).orElseThrow(NoContentException::new);

        // Remove relation applicant and job
        List<Job> jobs = jobService.getJobs();
        for (Job j : jobs) {
            List<Applicant> applicantsInJob = j.getApplicants();
            applicantsInJob.removeIf(a -> a.getId().equals(id));
        }
        // TODO: This solution is not very good for remove relation.
        //  Look at hibernate tricks. (I think cascade option is not helpful for this situation)

        // Now we can delete
        applicantRepo.deleteById(id);
    }

    // PRIVATE METHODS
    //<================================================================================================================>
    private Applicant converterOfApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
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
                //.jobs(getJobsFromJobIdList(applicantDto.getJobs()))
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
