package com.erensayar.HrAppealModuleApi.service.impl;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.JobPublicDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.repo.JobRepo;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import com.erensayar.HrAppealModuleApi.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    // INJECTIONS
    //<================================================================================================================>
    private final JobRepo jobRepo;
    private ApplicantService applicantService;

    @Autowired
    public void setApplicantService(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    // PUBLIC METHODS
    //<================================================================================================================>
    @Override
    public Job createJob(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
        jobCreateOrUpdateDto.setId(null);
        return jobRepo.save(this.converterOfJob(jobCreateOrUpdateDto));
    }

    @Override
    public Job getJobById(Integer id) {
        return jobRepo.findById(id).orElseThrow(NoContentException::new);
    }

    @Override
    public List<Job> getJobs() {
        List<Job> jobs = jobRepo.findAll();
        if (jobs.isEmpty())
            throw new NoContentException();
        return jobs;
    }

    @Override
    public List<Applicant> getJobApplicantsById(Integer jobId) {
        return this.getJobById(jobId).getApplicants();
    }

    @Override
    public Job updateJob(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
        if (jobCreateOrUpdateDto.getId() == null)
            throw new BadRequestException("Id can not be empty");
        return jobRepo.save(this.converterOfJob(jobCreateOrUpdateDto));
    }

    @Override
    public void deleteJobById(Integer id) {
        jobRepo.findById(id).orElseThrow(NoContentException::new);
        jobRepo.deleteById(id);
    }

    @Override
    public JobPublicDto getJobDtoForPublicById(Integer id) {
        Job job = this.getJobById(id);
        return JobPublicDto.builder()
                .id(job.getId())
                .name(job.getName())
                .summary(job.getSummary())
                .description(job.getDescription())
                .expectedQualification(job.getExpectedQualification())
                .lastApplicationDate(job.getLastApplicationDate())
                .build();
    }

    @Override
    public List<JobPublicDto> getJobsDtoForPublic() {
        List<Job> jobs = this.getJobs();
        return jobs.stream().map(job -> JobPublicDto.builder()
                .id(job.getId())
                .name(job.getName())
                .summary(job.getSummary())
                .description(job.getDescription())
                .expectedQualification(job.getExpectedQualification())
                .lastApplicationDate(job.getLastApplicationDate())
                .build()).collect(Collectors.toList());
    }

    // PRIVATE METHODS
    //<================================================================================================================>
    private Job converterOfJob(JobCreateOrUpdateDto jobCreateOrUpdateDto) {
        return Job.builder()
                .id(jobCreateOrUpdateDto.getId())
                .name(jobCreateOrUpdateDto.getName())
                .summary(jobCreateOrUpdateDto.getSummary())
                .description(jobCreateOrUpdateDto.getDescription())
                .expectedQualification(jobCreateOrUpdateDto.getExpectedQualification())
                .numberOfToHire(jobCreateOrUpdateDto.getNumberOfToHire())
                .lastApplicationDate(jobCreateOrUpdateDto.getLastApplicationDate())
                .isArchived(jobCreateOrUpdateDto.getIsArchived())
                .creationDate(jobCreateOrUpdateDto.getCreationDate())
                .applicants(this.getApplicantsFromApplicantIdList(jobCreateOrUpdateDto.getApplicants())) // It's not necessary
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
