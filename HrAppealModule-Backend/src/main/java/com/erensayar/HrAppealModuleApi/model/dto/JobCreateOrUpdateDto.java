package com.erensayar.HrAppealModuleApi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Received object if save or update to db as directly, this situation creates a security vulnerability.
 * This class created for made to avoid security vulnerabilities.
 */
@Getter
@Setter
@Builder
public class JobCreateOrUpdateDto {
    private Integer id;
    private String name;
    private String summary;
    private String description;
    private String expectedQualification;
    private Integer numberOfToHire;
    private LocalDate lastApplicationDate;
    private List<String> applicants; // Applicant id list
    private Boolean isArchived;
}
