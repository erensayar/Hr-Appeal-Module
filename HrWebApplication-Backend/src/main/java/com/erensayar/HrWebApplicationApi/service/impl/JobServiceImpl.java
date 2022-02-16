package com.erensayar.HrWebApplicationApi.service.impl;

import com.erensayar.HrWebApplicationApi.error.exception.BadRequestException;
import com.erensayar.HrWebApplicationApi.error.exception.NoContentException;
import com.erensayar.HrWebApplicationApi.model.dto.JobDto;
import com.erensayar.HrWebApplicationApi.model.entity.Applicant;
import com.erensayar.HrWebApplicationApi.model.entity.Job;
import com.erensayar.HrWebApplicationApi.repo.JobRepo;
import com.erensayar.HrWebApplicationApi.service.ApplicantService;
import com.erensayar.HrWebApplicationApi.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Job createJob(JobDto jobDto) {
        jobDto.setId(null);
        return jobRepo.save(this.converterOfJob(jobDto));
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
    public Job updateJob(JobDto jobDto) {
        if (jobDto.getId() == null)
            throw new BadRequestException("Id cannot be empty");
        return jobRepo.save(this.converterOfJob(jobDto));
    }

    @Override
    public void deleteJobById(Integer id) {
        jobRepo.findById(id).orElseThrow(NoContentException::new);
        jobRepo.deleteById(id);
    }

    // PRIVATE METHODS
    //<================================================================================================================>
    private Job converterOfJob(JobDto jobDto) {
        return Job.builder()
                .id(jobDto.getId())
                .name(jobDto.getName())
                .summary(jobDto.getSummary())
                .description(jobDto.getDescription())
                .expectedQualification(jobDto.getExpectedQualification())
                .numberOfToHire(jobDto.getNumberOfToHire())
                .lastApplicationDate(jobDto.getLastApplicationDate())
                .applicants(this.getApplicantsFromApplicantIdList(jobDto.getApplicants()))
                .build();
    }

    // This block wrote for getting object, from id list
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
