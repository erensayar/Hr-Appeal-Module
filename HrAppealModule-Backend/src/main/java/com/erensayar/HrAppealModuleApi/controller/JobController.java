package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
@RestController
public class JobController {

    private final JobService jobService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJobsDtoForPublic() {
        return new ResponseEntity<>(jobService.getJobPublicDtoList(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJobDtoByIdForPublic(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(jobService.getJobPublicDtoById(id), HttpStatus.OK);
    }

}