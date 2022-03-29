package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;

import java.util.List;

public interface ApplicantService {

    Applicant createApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto);

    Applicant getApplicantById(String id);

    List<Applicant> getApplicants();

    Applicant updateApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto);

    void deleteApplicantById(String id);

}
