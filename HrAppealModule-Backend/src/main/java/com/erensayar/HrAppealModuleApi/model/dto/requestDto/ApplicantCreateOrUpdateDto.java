package com.erensayar.HrAppealModuleApi.model.dto.requestDto;

import com.erensayar.HrAppealModuleApi.model.entity.Job;
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
  private Boolean personalInfoStoragePermission;
  private String cv; // cv id
  private List<Integer> jobs; // job id list
}
