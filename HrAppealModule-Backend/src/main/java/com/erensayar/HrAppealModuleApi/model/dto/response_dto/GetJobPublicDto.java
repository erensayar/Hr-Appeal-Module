package com.erensayar.HrAppealModuleApi.model.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetJobPublicDto {
    private Integer id;
    private String name;
    private String summary;
    private String description;
    private String expectedQualification;
    private LocalDate creationDate;
    private LocalDate lastApplicationDate;
    private String location;
    private String benefits;
}
