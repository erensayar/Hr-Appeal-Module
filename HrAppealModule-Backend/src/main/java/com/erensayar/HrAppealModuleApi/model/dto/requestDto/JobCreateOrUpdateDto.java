package com.erensayar.HrAppealModuleApi.model.dto.requestDto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Received object if save or update to db as directly, this situation creates a security
 * vulnerability. This class created for made to avoid security vulnerabilities.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCreateOrUpdateDto {

  private Integer id;
  private String name;
  private String summary;
  private String description;
  private String expectedQualification;
  private Integer numberOfToHire;
  private LocalDate creationDate;
  private LocalDate lastApplicationDate;
  private Boolean isArchived;
  private String location;
  private String benefits;
  private List<String> applicants; // Applicant id list

}
