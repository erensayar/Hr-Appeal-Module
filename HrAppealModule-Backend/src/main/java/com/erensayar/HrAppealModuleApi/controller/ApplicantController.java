package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.model.dto.mainResponseDto.ApplicantDto;
import com.erensayar.HrAppealModuleApi.model.dto.requestDto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.mapper.MapperOfApplicant;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import java.util.List;
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
  private final MapperOfApplicant mapperOfApplicant;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getApplicantById(@PathVariable("id") String id) {
    Applicant applicant = applicantService.getApplicantById(id);
    ApplicantDto applicantDto = new ApplicantDto(applicant);
    return new ResponseEntity<>(applicantDto, HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getApplicants() {
    List<Applicant> applicantList = applicantService.getApplicants();
    List<ApplicantDto> applicantDtoList = mapperOfApplicant.entityListToDtoList(applicantList);
    return new ResponseEntity<>(applicantDtoList, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createApplicant(@RequestBody ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
    Applicant applicant = applicantService.createApplicant(applicantCreateOrUpdateDto);
    ApplicantDto applicantDto = new ApplicantDto(applicant);
    return new ResponseEntity<>(applicantDto, HttpStatus.CREATED);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateApplicant(@RequestBody ApplicantCreateOrUpdateDto updatedApplicant) {
    Applicant applicant = applicantService.updateApplicant(updatedApplicant);
    ApplicantDto applicantDto = new ApplicantDto(applicant);
    return new ResponseEntity<>(applicantDto, HttpStatus.OK);
  }

  @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> patchApplicant(@PathVariable String id, @RequestBody Map<String, Object> fields) {
    Applicant applicant = applicantService.patchApplicant(id, fields);
    ApplicantDto applicantDto = new ApplicantDto(applicant);
    return new ResponseEntity<>(applicantDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteApplicantById(@PathVariable String id) {
    applicantService.deleteApplicantById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}