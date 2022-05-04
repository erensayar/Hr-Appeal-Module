package com.erensayar.HrAppealModuleApi.model.dto.responseDto;

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
public class JobGetDtoForPublic {

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
