package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import java.util.List;
import java.util.Map;

public interface ApplicantService {

  Applicant createApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto);

  Applicant getApplicantById(String id);

  List<Applicant> getApplicants();

  Applicant updateApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto);

  void deleteApplicantById(String id);

  Applicant patchApplicant(String id, Map<String, Object> fields);

}
