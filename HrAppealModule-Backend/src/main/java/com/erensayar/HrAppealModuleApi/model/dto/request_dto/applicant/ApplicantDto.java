package com.erensayar.HrAppealModuleApi.model.dto.request_dto.applicant;

import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApplicantDto {

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
  private Boolean personalInfoStoragePermission;
  private FileAttachment cv;

}
