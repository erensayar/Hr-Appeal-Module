package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.applicant.ApplicantCreateDto;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.applicant.ApplicantUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import java.util.List;
import java.util.Map;

public interface ApplicantService {

  Applicant createApplicant(ApplicantCreateDto applicantCreateDto);

  Applicant getApplicantById(String id);

  List<Applicant> getApplicants();

  Applicant updateApplicant(ApplicantUpdateDto applicantUpdateDto);

  void deleteApplicantById(String id);

  Applicant patchApplicant(String id, Map<String, Object> fields);

}
