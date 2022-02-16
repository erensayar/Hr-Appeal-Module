package com.erensayar.HrWebApplicationApi.service;

import com.erensayar.HrWebApplicationApi.model.dto.ApplicantDto;
import com.erensayar.HrWebApplicationApi.model.entity.Applicant;

import java.util.List;

public interface ApplicantService {

    Applicant createApplicant(ApplicantDto applicantDto);

    Applicant getApplicantById(String id);

    List<Applicant> getApplicants();

    Applicant updateApplicant(ApplicantDto applicantDto);

    Applicant updateApplicant(Applicant applicant);

    void deleteApplicantById(String id);

}
