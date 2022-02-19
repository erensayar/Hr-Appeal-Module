package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.ApplicantDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;

import java.util.List;

public interface ApplicantService {

    Applicant createApplicant(ApplicantDto applicantDto);

    Applicant getApplicantById(String id);

    List<Applicant> getApplicants();

    Applicant updateApplicant(ApplicantDto applicantDto);

    Applicant updateApplicant(Applicant applicant);

    void deleteApplicantById(String id);

}
