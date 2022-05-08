package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.response_dto.JobPublicDto;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.mapper.JobMapper;
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
  private final JobMapper jobMapper;

  // Public Endpoints
  // <=============================================================================================>
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobDtoListForPublic() {
    List<Job> jobs = jobService.getJobs();
    List<JobPublicDto> jobDtoList = jobMapper.entityListToDtoList(jobs);
    return new ResponseEntity<>(jobDtoList, HttpStatus.OK);
  }

  @GetMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobDtoByIdForPublic(@PathVariable("id") Integer id) {
    Job job = jobService.getJobById(id);
    JobPublicDto jobDto = jobMapper.entityToDto(job);
    return new ResponseEntity<>(jobDto, HttpStatus.OK);
  }

  // Private Endpoints (For Admin)
  // <=============================================================================================>
  @GetMapping(value = "/authorized/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobById(@PathVariable("id") Integer id) {
    return new ResponseEntity<>(jobService.getJobById(id), HttpStatus.OK);
  }

  @GetMapping(value = "/authorized/{id}/applicants", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobApplicantsByJobId(@PathVariable("id") Integer id) {
    return new ResponseEntity<>(jobService.getJobApplicantsByJobId(id), HttpStatus.OK);
  }

  @GetMapping(value = "/authorized", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getJobs() {
    return new ResponseEntity<>(jobService.getJobs(), HttpStatus.OK);
  }

  @PostMapping(value = "/authorized", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createJob(@RequestBody JobCreateOrUpdateDto jobCreateOrUpdateDto) {
    return new ResponseEntity<>(jobService.createJob(jobCreateOrUpdateDto), HttpStatus.CREATED);
  }

  @PutMapping(value = "/authorized", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateJob(@RequestBody JobCreateOrUpdateDto updatedJob) {
    return new ResponseEntity<>(jobService.updateJob(updatedJob), HttpStatus.OK);
  }

  @DeleteMapping(value = "/authorized/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteJobById(@PathVariable("id") Integer id) {
    jobService.deleteJobById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}