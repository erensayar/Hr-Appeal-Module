package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.applicant.ApplicantCreateDto;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.applicant.ApplicantUpdateDto;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<?> createApplicant(@RequestBody ApplicantCreateDto applicantCreateDto) {
    return new ResponseEntity<>(applicantService.createApplicant(applicantCreateDto), HttpStatus.CREATED);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateApplicant(@RequestBody ApplicantUpdateDto applicantUpdateDto) {
    return new ResponseEntity<>(applicantService.updateApplicant(applicantUpdateDto), HttpStatus.OK);
  }

  @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> patchApplicant(@PathVariable String id, @RequestBody Map<String, Object> fields) {
    return new ResponseEntity<>(applicantService.patchApplicant(id, fields), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteApplicantById(@PathVariable String id) {
    applicantService.deleteApplicantById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}