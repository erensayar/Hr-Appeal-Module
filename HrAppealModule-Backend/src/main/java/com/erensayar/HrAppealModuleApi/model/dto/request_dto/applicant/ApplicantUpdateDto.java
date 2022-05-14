package com.erensayar.HrAppealModuleApi.model.dto.request_dto.applicant;

import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import java.time.LocalDateTime;
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
public class ApplicantUpdateDto extends ApplicantDto{

  private String id;
  private ApplicantStatus applicantStatus;
  private LocalDateTime applicationDateAndTime;
  private Boolean isArchived;

}
