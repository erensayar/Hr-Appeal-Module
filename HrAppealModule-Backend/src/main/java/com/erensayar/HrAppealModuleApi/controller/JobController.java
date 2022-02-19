package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.model.dto.JobDto;
import com.erensayar.HrAppealModuleApi.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
@RestController
public class JobController {

    private final JobService jobService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJobById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(jobService.getJobById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/applicants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJobApplicantsById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(jobService.getJobApplicantsById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJobs() {
        return new ResponseEntity<>(jobService.getJobs(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createJob(@RequestBody JobDto jobDto) {
        return new ResponseEntity<>(jobService.createJob(jobDto), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateJob(@RequestBody JobDto updatedJob) {
        return new ResponseEntity<>(jobService.updateJob(updatedJob), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteJobById(@PathVariable("id") Integer id) {
        jobService.deleteJobById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}