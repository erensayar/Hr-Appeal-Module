package com.erensayar.HrWebApplicationApi.model.dto;

import com.erensayar.HrWebApplicationApi.model.enums.ApplicantStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Received object if save or update to db as directly, this situation creates a security vulnerability.
 * This class created for made to avoid security vulnerabilities.
 */
@Getter
@Setter
@Builder
public class ApplicantDto {
    private String id;
    private String name;
    private String surname;
    private String mail;
    private String telephone;
    private String city;
    private String district;
    private String gitLink;
    private String linkedInLink;
    private ApplicantStatus applicantStatus;
    private LocalDate applicationDate;
    private String cv; // cv id
    private Boolean personalInfoStoragePermission;
}
