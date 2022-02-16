package com.erensayar.HrWebApplicationApi.service.impl;

import com.erensayar.HrWebApplicationApi.error.exception.BadRequestException;
import com.erensayar.HrWebApplicationApi.error.exception.NoContentException;
import com.erensayar.HrWebApplicationApi.model.dto.ApplicantDto;
import com.erensayar.HrWebApplicationApi.model.entity.Applicant;
import com.erensayar.HrWebApplicationApi.model.entity.FileAttachment;
import com.erensayar.HrWebApplicationApi.model.entity.Job;
import com.erensayar.HrWebApplicationApi.model.enums.ApplicantStatus;
import com.erensayar.HrWebApplicationApi.repo.ApplicantRepo;
import com.erensayar.HrWebApplicationApi.service.ApplicantService;
import com.erensayar.HrWebApplicationApi.service.FileAttachmentService;
import com.erensayar.HrWebApplicationApi.service.JobService;
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
    public Applicant createApplicant(ApplicantDto applicantDto) {
        applicantDto.setId("APL" + UUID.randomUUID().toString().replaceAll("-", ""));
        applicantDto.setApplicantStatus(ApplicantStatus.TO_BE_EVALUATED);
        applicantDto.setApplicationDate(LocalDate.now());
        return applicantRepo.save(this.converterOfApplicant(applicantDto));
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
    public Applicant updateApplicant(ApplicantDto applicantDto) {
        if (applicantDto.getId() == null)
            throw new BadRequestException("Id cannot be empty");
        return applicantRepo.save(this.converterOfApplicant(applicantDto));
    }

    @Override
    public Applicant updateApplicant(Applicant applicant) {
        if (applicant.getId() == null)
            throw new BadRequestException("Id cannot be empty");
        return applicantRepo.save(applicant);
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
    private Applicant converterOfApplicant(ApplicantDto applicantDto) {
        return Applicant.builder()
                .id(applicantDto.getId())
                .name(applicantDto.getName())
                .surname(applicantDto.getSurname())
                .mail(applicantDto.getMail())
                .telephone(applicantDto.getTelephone())
                .country(applicantDto.getCountry())
                .city(applicantDto.getCity())
                .district(applicantDto.getDistrict())
                .gitLink(applicantDto.getGitLink())
                .linkedInLink(applicantDto.getLinkedInLink())
                .applicantStatus(applicantDto.getApplicantStatus())
                .applicationDate(applicantDto.getApplicationDate())
                .cv(getCvAndCheckException(applicantDto.getCv()))
                .personalInfoStoragePermission(applicantDto.getPersonalInfoStoragePermission())
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
