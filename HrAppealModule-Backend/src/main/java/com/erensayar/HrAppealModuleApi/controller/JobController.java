package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.model.dto.mainResponseDto.ApplicantDto;
import com.erensayar.HrAppealModuleApi.model.dto.mainResponseDto.JobDto;
import com.erensayar.HrAppealModuleApi.model.dto.requestDto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.responseDto.JobGetDtoForPublic;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.mapper.MapperOfApplicant;
import com.erensayar.HrAppealModuleApi.model.mapper.MapperOfJob;
import com.erensayar.HrAppealModuleApi.service.JobService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
@RestController
public class JobController {

  private final JobService jobService;
  private final MapperOfJob mapperOfJob;
  private final MapperOfApplicant mapperOfApplicant;

  // Public Endpoints
  // <=============================================================================================>

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobsDtoForPublic() {
    List<Job> jobs = jobService.getJobs();
    List<JobGetDtoForPublic> jobGetDtoForPublicList =
        mapperOfJob.entityListToJobGetDtoForPublicList(jobs);
    return new ResponseEntity<>(jobGetDtoForPublicList, HttpStatus.OK);
  }

  @GetMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobDtoByIdForPublic(@PathVariable("id") Integer id) {
    Job job = jobService.getJobById(id);
    JobGetDtoForPublic jobGetDtoForPublic = mapperOfJob.entityToJobGetDtoForPublic(job);
    return new ResponseEntity<>(jobGetDtoForPublic, HttpStatus.OK);
  }

  // Private Endpoints (For Admin)
  // <=============================================================================================>

  @GetMapping(value = "/authorized/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobById(@PathVariable("id") Integer id) {
    Job job = jobService.getJobById(id);
    JobDto jobDto = new JobDto(job);
    return new ResponseEntity<>(jobDto, HttpStatus.OK);
  }

  @GetMapping(value = "/authorized/{id}/applicants", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobApplicantsById(@PathVariable("id") Integer id) {
    List<Applicant> jobApplicantsList = jobService.getJobApplicantsById(id);
    List<ApplicantDto> applicantDtoList = mapperOfApplicant.entityListToDtoList(jobApplicantsList);
    return new ResponseEntity<>(applicantDtoList, HttpStatus.OK);
  }

  @GetMapping(value = "/authorized", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobs() {
    List<Job> jobs = jobService.getJobs();
    List<JobDto> jobDtoList = mapperOfJob.entityListToJobDtoList(jobs);
    return new ResponseEntity<>(jobDtoList, HttpStatus.OK);
  }

  @PostMapping(value = "/authorized", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createJob(@RequestBody JobCreateOrUpdateDto jobCreateOrUpdateDto) {
    Job job = jobService.createJob(jobCreateOrUpdateDto);
    JobDto jobDto = new JobDto(job);
    return new ResponseEntity<>(jobDto, HttpStatus.CREATED);
  }

  @PutMapping(value = "/authorized", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateJob(@RequestBody JobCreateOrUpdateDto jobCreateOrUpdateDto) {
    Job job = jobService.updateJob(jobCreateOrUpdateDto);
    JobDto jobDto = new JobDto(job);
    return new ResponseEntity<>(jobDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/authorized/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteJobById(@PathVariable("id") Integer id) {
    jobService.deleteJobById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}