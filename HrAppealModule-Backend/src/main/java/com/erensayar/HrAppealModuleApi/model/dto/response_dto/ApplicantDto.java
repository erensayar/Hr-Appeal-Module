package com.erensayar.HrAppealModuleApi.model.dto.response_dto;

import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantDto {

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
  private FileAttachmentDto cv;
  private Boolean personalInfoStoragePermission;
  private Boolean isArchived;

}
