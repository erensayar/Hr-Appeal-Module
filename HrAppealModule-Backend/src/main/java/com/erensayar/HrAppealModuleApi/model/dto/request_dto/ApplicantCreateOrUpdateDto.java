package com.erensayar.HrAppealModuleApi.model.dto.request_dto;

import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import java.time.LocalDate;
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
public class ApplicantCreateOrUpdateDto {

  private String id;
  private String name;
  private String surname;
  private String mail;
  private String telephone;
  private String country;
  private String city;
  private String district;
  private String gitLink;
  private String linkedInLink;
  private String twitterLink;
  private ApplicantStatus applicantStatus;
  private LocalDate applicationDate;
  private String cv; // cv id
  private Boolean personalInfoStoragePermission;
  private Boolean isArchived;
}
