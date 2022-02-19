package com.erensayar.HrAppealModuleApi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JobDtoForPublic {
    private String name;
    private String summary;
    private String description;
    private String expectedQualification;
    private Integer numberOfToHire;
    private LocalDate lastApplicationDate;
}
