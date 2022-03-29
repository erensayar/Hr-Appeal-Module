package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.model.dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/applicants")
@RequiredArgsConstructor
@RestController
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getApplicantById(@PathVariable("id") String id) {
        return new ResponseEntity<>(applicantService.getApplicantById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getApplicants() {
        return new ResponseEntity<>(applicantService.getApplicants(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createApplicant(@RequestBody ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
        return new ResponseEntity<>(applicantService.createApplicant(applicantCreateOrUpdateDto), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateApplicant(@RequestBody ApplicantCreateOrUpdateDto updatedApplicant) {
        return new ResponseEntity<>(applicantService.updateApplicant(updatedApplicant), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteApplicantById(@PathVariable("id") String id) {
        applicantService.deleteApplicantById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}